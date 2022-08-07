package com.lidroid.xutils.cache;

import com.hyphenate.util.HanziToPinyin;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class LruDiskCache implements Closeable {
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final char CLEAN = 'C';
    private static final char DELETE = 'D';
    private static final char EXPIRY_PREFIX = 't';
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() { // from class: com.lidroid.xutils.cache.LruDiskCache.2
        @Override // java.io.OutputStream
        public void write(int b) throws IOException {
        }
    };
    private static final char READ = 'R';
    private static final char UPDATE = 'U';
    static final String VERSION = "1";
    private final int appVersion;
    private final File directory;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private Writer journalWriter;
    private long maxSize;
    private int redundantOpCount;
    private final int valueCount;
    private long size = 0;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long nextSequenceNumber = 0;
    final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Callable<Void> cleanupCallable = new Callable<Void>() { // from class: com.lidroid.xutils.cache.LruDiskCache.1
        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            synchronized (LruDiskCache.this) {
                if (LruDiskCache.this.journalWriter != null) {
                    LruDiskCache.this.trimToSize();
                    if (LruDiskCache.this.journalRebuildRequired()) {
                        LruDiskCache.this.rebuildJournal();
                        LruDiskCache.this.redundantOpCount = 0;
                    }
                }
            }
            return null;
        }
    };
    private FileNameGenerator fileNameGenerator = new MD5FileNameGenerator();

    private LruDiskCache(File directory, int appVersion, int valueCount, long maxSize) {
        this.directory = directory;
        this.appVersion = appVersion;
        this.journalFile = new File(directory, JOURNAL_FILE);
        this.journalFileTmp = new File(directory, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(directory, JOURNAL_FILE_BACKUP);
        this.valueCount = valueCount;
        this.maxSize = maxSize;
    }

    public static LruDiskCache open(File directory, int appVersion, int valueCount, long maxSize) throws IOException {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (valueCount <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File backupFile = new File(directory, JOURNAL_FILE_BACKUP);
            if (backupFile.exists()) {
                File journalFile = new File(directory, JOURNAL_FILE);
                if (journalFile.exists()) {
                    backupFile.delete();
                } else {
                    renameTo(backupFile, journalFile, false);
                }
            }
            LruDiskCache cache = new LruDiskCache(directory, appVersion, valueCount, maxSize);
            if (cache.journalFile.exists()) {
                try {
                    cache.readJournal();
                    cache.processJournal();
                    cache.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cache.journalFile, true), "US-ASCII"));
                    return cache;
                } catch (Throwable journalIsCorrupt) {
                    LogUtils.e("DiskLruCache " + directory + " is corrupt: " + journalIsCorrupt.getMessage() + ", removing", journalIsCorrupt);
                    cache.delete();
                }
            }
            if (directory.exists() || directory.mkdirs()) {
                cache = new LruDiskCache(directory, appVersion, valueCount, maxSize);
                cache.rebuildJournal();
            }
            return cache;
        }
    }

    private void readJournal() throws IOException {
        StrictLineReader reader = null;
        try {
            StrictLineReader reader2 = new StrictLineReader(this, new FileInputStream(this.journalFile));
            try {
                String magic = reader2.readLine();
                String version = reader2.readLine();
                String appVersionString = reader2.readLine();
                String valueCountString = reader2.readLine();
                String blank = reader2.readLine();
                if (!MAGIC.equals(magic) || !"1".equals(version) || !Integer.toString(this.appVersion).equals(appVersionString) || !Integer.toString(this.valueCount).equals(valueCountString) || !"".equals(blank)) {
                    throw new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + "]");
                }
                int lineCount = 0;
                while (true) {
                    try {
                        readJournalLine(reader2.readLine());
                        lineCount++;
                    } catch (EOFException e) {
                        this.redundantOpCount = lineCount - this.lruEntries.size();
                        IOUtils.closeQuietly(reader2);
                        return;
                    }
                }
            } catch (Throwable th) {
                th = th;
                reader = reader2;
                IOUtils.closeQuietly(reader);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void readJournalLine(String line) throws IOException {
        String sb;
        String diskKey;
        int firstSpace = line.indexOf(32);
        if (firstSpace == 1) {
            char lineTag = line.charAt(0);
            int keyBegin = firstSpace + 1;
            int secondSpace = line.indexOf(32, keyBegin);
            if (secondSpace == -1) {
                diskKey = line.substring(keyBegin);
                if (lineTag == 'D') {
                    this.lruEntries.remove(diskKey);
                    return;
                }
            } else {
                diskKey = line.substring(keyBegin, secondSpace);
            }
            Entry entry = this.lruEntries.get(diskKey);
            if (entry == null) {
                entry = new Entry(diskKey);
                this.lruEntries.put(diskKey, entry);
            }
            switch (lineTag) {
                case 'C':
                    entry.readable = true;
                    entry.currentEditor = null;
                    String[] parts = line.substring(secondSpace + 1).split(HanziToPinyin.Token.SEPARATOR);
                    if (parts.length > 0) {
                        try {
                            if (parts[0].charAt(0) == 't') {
                                entry.expiryTimestamp = Long.valueOf(parts[0].substring(1)).longValue();
                                entry.setLengths(parts, 1);
                            } else {
                                entry.expiryTimestamp = Long.MAX_VALUE;
                                entry.setLengths(parts, 0);
                            }
                            return;
                        } finally {
                            IOException iOException = new IOException("unexpected journal line: " + line);
                        }
                    } else {
                        return;
                    }
                case 'R':
                    return;
                case 'U':
                    entry.currentEditor = new Editor(entry);
                    return;
                default:
                    throw new IOException(sb);
            }
        } else {
            throw new IOException(sb);
        }
    }

    private void processJournal() throws IOException {
        deleteIfExists(this.journalFileTmp);
        Iterator<Entry> i = this.lruEntries.values().iterator();
        while (i.hasNext()) {
            Entry entry = i.next();
            if (entry.currentEditor == null) {
                for (int t = 0; t < this.valueCount; t++) {
                    this.size += entry.lengths[t];
                }
            } else {
                entry.currentEditor = null;
                for (int t2 = 0; t2 < this.valueCount; t2++) {
                    deleteIfExists(entry.getCleanFile(t2));
                    deleteIfExists(entry.getDirtyFile(t2));
                }
                i.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        if (this.journalWriter != null) {
            IOUtils.closeQuietly(this.journalWriter);
        }
        Writer writer = null;
        try {
            Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), "US-ASCII"));
            try {
                writer2.write(MAGIC);
                writer2.write("\n");
                writer2.write("1");
                writer2.write("\n");
                writer2.write(Integer.toString(this.appVersion));
                writer2.write("\n");
                writer2.write(Integer.toString(this.valueCount));
                writer2.write("\n");
                writer2.write("\n");
                for (Entry entry : this.lruEntries.values()) {
                    if (entry.currentEditor != null) {
                        writer2.write("U " + entry.diskKey + '\n');
                    } else {
                        writer2.write("C " + entry.diskKey + HanziToPinyin.Token.SEPARATOR + EXPIRY_PREFIX + entry.expiryTimestamp + entry.getLengths() + '\n');
                    }
                }
                IOUtils.closeQuietly(writer2);
                if (this.journalFile.exists()) {
                    renameTo(this.journalFile, this.journalFileBackup, true);
                }
                renameTo(this.journalFileTmp, this.journalFile, false);
                this.journalFileBackup.delete();
                this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), "US-ASCII"));
            } catch (Throwable th) {
                th = th;
                writer = writer2;
                IOUtils.closeQuietly(writer);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void deleteIfExists(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void renameTo(File from, File to, boolean deleteDestination) throws IOException {
        if (deleteDestination) {
            deleteIfExists(to);
        }
        if (!from.renameTo(to)) {
            throw new IOException();
        }
    }

    public synchronized long getExpiryTimestamp(String key) throws IOException {
        long j;
        String diskKey = this.fileNameGenerator.generate(key);
        checkNotClosed();
        Entry entry = this.lruEntries.get(diskKey);
        if (entry == null) {
            j = 0;
        } else {
            j = entry.expiryTimestamp;
        }
        return j;
    }

    public File getCacheFile(String key, int index) {
        File result = new File(this.directory, this.fileNameGenerator.generate(key) + "." + index);
        if (result.exists()) {
            return result;
        }
        try {
            remove(key);
        } catch (IOException e) {
        }
        return null;
    }

    public Snapshot get(String key) throws IOException {
        return getByDiskKey(this.fileNameGenerator.generate(key));
    }

    private synchronized Snapshot getByDiskKey(String diskKey) throws IOException {
        Snapshot snapshot = null;
        synchronized (this) {
            checkNotClosed();
            Entry entry = this.lruEntries.get(diskKey);
            if (entry != null && entry.readable) {
                if (entry.expiryTimestamp < System.currentTimeMillis()) {
                    for (int i = 0; i < this.valueCount; i++) {
                        File file = entry.getCleanFile(i);
                        if (!file.exists() || file.delete()) {
                            this.size -= entry.lengths[i];
                            entry.lengths[i] = 0;
                        } else {
                            throw new IOException("failed to delete " + file);
                        }
                    }
                    this.redundantOpCount++;
                    this.journalWriter.append((CharSequence) ("D " + diskKey + '\n'));
                    this.lruEntries.remove(diskKey);
                    if (journalRebuildRequired()) {
                        this.executorService.submit(this.cleanupCallable);
                    }
                } else {
                    FileInputStream[] ins = new FileInputStream[this.valueCount];
                    for (int i2 = 0; i2 < this.valueCount; i2++) {
                        try {
                            ins[i2] = new FileInputStream(entry.getCleanFile(i2));
                        } catch (FileNotFoundException e) {
                            for (int i3 = 0; i3 < this.valueCount && ins[i3] != null; i3++) {
                                IOUtils.closeQuietly(ins[i3]);
                            }
                        }
                    }
                    this.redundantOpCount++;
                    this.journalWriter.append((CharSequence) ("R " + diskKey + '\n'));
                    if (journalRebuildRequired()) {
                        this.executorService.submit(this.cleanupCallable);
                    }
                    snapshot = new Snapshot(diskKey, entry.sequenceNumber, ins, entry.lengths);
                }
            }
        }
        return snapshot;
    }

    public Editor edit(String key) throws IOException {
        return editByDiskKey(this.fileNameGenerator.generate(key), -1L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0060, code lost:
        if (r1.currentEditor != null) goto L_0x001d;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.lidroid.xutils.cache.LruDiskCache.Editor editByDiskKey(java.lang.String r7, long r8) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            monitor-enter(r6)
            r6.checkNotClosed()     // Catch: all -> 0x0059
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r2 = r6.lruEntries     // Catch: all -> 0x0059
            java.lang.Object r1 = r2.get(r7)     // Catch: all -> 0x0059
            com.lidroid.xutils.cache.LruDiskCache$Entry r1 = (com.lidroid.xutils.cache.LruDiskCache.Entry) r1     // Catch: all -> 0x0059
            r2 = -1
            int r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x001f
            if (r1 == 0) goto L_0x001d
            long r2 = com.lidroid.xutils.cache.LruDiskCache.Entry.access$1300(r1)     // Catch: all -> 0x0059
            int r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r2 == 0) goto L_0x001f
        L_0x001d:
            monitor-exit(r6)
            return r0
        L_0x001f:
            if (r1 != 0) goto L_0x005c
            com.lidroid.xutils.cache.LruDiskCache$Entry r1 = new com.lidroid.xutils.cache.LruDiskCache$Entry     // Catch: all -> 0x0059
            r2 = 0
            r1.<init>(r7)     // Catch: all -> 0x0059
            java.util.LinkedHashMap<java.lang.String, com.lidroid.xutils.cache.LruDiskCache$Entry> r2 = r6.lruEntries     // Catch: all -> 0x0059
            r2.put(r7, r1)     // Catch: all -> 0x0059
        L_0x002c:
            com.lidroid.xutils.cache.LruDiskCache$Editor r0 = new com.lidroid.xutils.cache.LruDiskCache$Editor     // Catch: all -> 0x0059
            r2 = 0
            r0.<init>(r1)     // Catch: all -> 0x0059
            com.lidroid.xutils.cache.LruDiskCache.Entry.access$702(r1, r0)     // Catch: all -> 0x0059
            java.io.Writer r2 = r6.journalWriter     // Catch: all -> 0x0059
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x0059
            r3.<init>()     // Catch: all -> 0x0059
            java.lang.String r4 = "U "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: all -> 0x0059
            java.lang.StringBuilder r3 = r3.append(r7)     // Catch: all -> 0x0059
            r4 = 10
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: all -> 0x0059
            java.lang.String r3 = r3.toString()     // Catch: all -> 0x0059
            r2.write(r3)     // Catch: all -> 0x0059
            java.io.Writer r2 = r6.journalWriter     // Catch: all -> 0x0059
            r2.flush()     // Catch: all -> 0x0059
            goto L_0x001d
        L_0x0059:
            r2 = move-exception
            monitor-exit(r6)
            throw r2
        L_0x005c:
            com.lidroid.xutils.cache.LruDiskCache$Editor r2 = com.lidroid.xutils.cache.LruDiskCache.Entry.access$700(r1)     // Catch: all -> 0x0059
            if (r2 == 0) goto L_0x002c
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.cache.LruDiskCache.editByDiskKey(java.lang.String, long):com.lidroid.xutils.cache.LruDiskCache$Editor");
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
        this.executorService.submit(this.cleanupCallable);
    }

    public synchronized long size() {
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void completeEdit(Editor editor, boolean success) throws IOException {
        Entry entry = editor.entry;
        if (entry.currentEditor != editor) {
            throw new IllegalStateException();
        }
        if (success && !entry.readable) {
            for (int i = 0; i < this.valueCount; i++) {
                if (!editor.written[i]) {
                    editor.abort();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                } else if (!entry.getDirtyFile(i).exists()) {
                    editor.abort();
                    break;
                }
            }
        }
        for (int i2 = 0; i2 < this.valueCount; i2++) {
            File dirty = entry.getDirtyFile(i2);
            if (!success) {
                deleteIfExists(dirty);
            } else if (dirty.exists()) {
                File clean = entry.getCleanFile(i2);
                dirty.renameTo(clean);
                long oldLength = entry.lengths[i2];
                long newLength = clean.length();
                entry.lengths[i2] = newLength;
                this.size = (this.size - oldLength) + newLength;
            }
        }
        this.redundantOpCount++;
        entry.currentEditor = null;
        if (entry.readable || success) {
            entry.readable = true;
            this.journalWriter.write("C " + entry.diskKey + HanziToPinyin.Token.SEPARATOR + EXPIRY_PREFIX + entry.expiryTimestamp + entry.getLengths() + '\n');
            if (success) {
                long j = this.nextSequenceNumber;
                this.nextSequenceNumber = 1 + j;
                entry.sequenceNumber = j;
            }
        } else {
            this.lruEntries.remove(entry.diskKey);
            this.journalWriter.write("D " + entry.diskKey + '\n');
        }
        this.journalWriter.flush();
        if (this.size > this.maxSize || journalRebuildRequired()) {
            this.executorService.submit(this.cleanupCallable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }

    public boolean remove(String key) throws IOException {
        return removeByDiskKey(this.fileNameGenerator.generate(key));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean removeByDiskKey(String diskKey) throws IOException {
        boolean z;
        checkNotClosed();
        Entry entry = this.lruEntries.get(diskKey);
        if (entry == null || entry.currentEditor != null) {
            z = false;
        } else {
            for (int i = 0; i < this.valueCount; i++) {
                File file = entry.getCleanFile(i);
                if (!file.exists() || file.delete()) {
                    this.size -= entry.lengths[i];
                    entry.lengths[i] = 0;
                } else {
                    throw new IOException("failed to delete " + file);
                }
            }
            this.redundantOpCount++;
            this.journalWriter.append((CharSequence) ("D " + diskKey + '\n'));
            this.lruEntries.remove(diskKey);
            if (journalRebuildRequired()) {
                this.executorService.submit(this.cleanupCallable);
            }
            z = true;
        }
        return z;
    }

    public synchronized boolean isClosed() {
        return this.journalWriter == null;
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        checkNotClosed();
        trimToSize();
        this.journalWriter.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.journalWriter != null) {
            Iterator it = new ArrayList(this.lruEntries.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeByDiskKey(this.lruEntries.entrySet().iterator().next().getKey());
        }
    }

    public void delete() throws IOException {
        IOUtils.closeQuietly(this);
        deleteContents(this.directory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String inputStreamToString(InputStream in) throws IOException {
        return readFully(new InputStreamReader(in, "UTF-8"));
    }

    /* loaded from: classes2.dex */
    public final class Snapshot implements Closeable {
        private final String diskKey;
        private final FileInputStream[] ins;
        private final long[] lengths;
        private final long sequenceNumber;

        private Snapshot(String diskKey, long sequenceNumber, FileInputStream[] ins, long[] lengths) {
            this.diskKey = diskKey;
            this.sequenceNumber = sequenceNumber;
            this.ins = ins;
            this.lengths = lengths;
        }

        public Editor edit() throws IOException {
            return LruDiskCache.this.editByDiskKey(this.diskKey, this.sequenceNumber);
        }

        public FileInputStream getInputStream(int index) {
            return this.ins[index];
        }

        public String getString(int index) throws IOException {
            return LruDiskCache.inputStreamToString(getInputStream(index));
        }

        public long getLength(int index) {
            return this.lengths[index];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (InputStream in : this.ins) {
                IOUtils.closeQuietly(in);
            }
        }
    }

    /* loaded from: classes2.dex */
    public final class Editor {
        private boolean committed;
        private final Entry entry;
        private boolean hasErrors;
        private final boolean[] written;

        private Editor(Entry entry) {
            this.entry = entry;
            this.written = entry.readable ? null : new boolean[LruDiskCache.this.valueCount];
        }

        public void setEntryExpiryTimestamp(long timestamp) {
            this.entry.expiryTimestamp = timestamp;
        }

        public InputStream newInputStream(int index) throws IOException {
            synchronized (LruDiskCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                } else if (!this.entry.readable) {
                    return null;
                } else {
                    try {
                        return new FileInputStream(this.entry.getCleanFile(index));
                    } catch (FileNotFoundException e) {
                        return null;
                    }
                }
            }
        }

        public String getString(int index) throws IOException {
            InputStream in = newInputStream(index);
            if (in != null) {
                return LruDiskCache.inputStreamToString(in);
            }
            return null;
        }

        public OutputStream newOutputStream(int index) throws IOException {
            OutputStream outputStream;
            FileOutputStream outputStream2;
            synchronized (LruDiskCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable) {
                    this.written[index] = true;
                }
                File dirtyFile = this.entry.getDirtyFile(index);
                try {
                    outputStream2 = new FileOutputStream(dirtyFile);
                } catch (FileNotFoundException e) {
                    LruDiskCache.this.directory.mkdirs();
                    try {
                        outputStream2 = new FileOutputStream(dirtyFile);
                    } catch (FileNotFoundException e2) {
                        outputStream = LruDiskCache.NULL_OUTPUT_STREAM;
                    }
                }
                outputStream = new FaultHidingOutputStream(outputStream2);
            }
            return outputStream;
        }

        public void set(int index, String value) throws IOException {
            Throwable th;
            Writer writer;
            Writer writer2 = null;
            try {
                writer = new OutputStreamWriter(newOutputStream(index), "UTF-8");
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                writer.write(value);
                IOUtils.closeQuietly(writer);
            } catch (Throwable th3) {
                th = th3;
                writer2 = writer;
                IOUtils.closeQuietly(writer2);
                throw th;
            }
        }

        public void commit() throws IOException {
            if (this.hasErrors) {
                LruDiskCache.this.completeEdit(this, false);
                LruDiskCache.this.removeByDiskKey(this.entry.diskKey);
            } else {
                LruDiskCache.this.completeEdit(this, true);
            }
            this.committed = true;
        }

        public void abort() throws IOException {
            LruDiskCache.this.completeEdit(this, false);
        }

        public void abortUnlessCommitted() {
            if (!this.committed) {
                try {
                    abort();
                } catch (Throwable th) {
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public class FaultHidingOutputStream extends FilterOutputStream {
            private FaultHidingOutputStream(OutputStream out) {
                super(out);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int oneByte) {
                try {
                    this.out.write(oneByte);
                } catch (Throwable th) {
                    Editor.this.hasErrors = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] buffer, int offset, int length) {
                try {
                    this.out.write(buffer, offset, length);
                    this.out.flush();
                } catch (Throwable th) {
                    Editor.this.hasErrors = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                try {
                    this.out.close();
                } catch (Throwable th) {
                    Editor.this.hasErrors = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public void flush() {
                try {
                    this.out.flush();
                } catch (Throwable th) {
                    Editor.this.hasErrors = true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class Entry {
        private Editor currentEditor;
        private final String diskKey;
        private long expiryTimestamp;
        private final long[] lengths;
        private boolean readable;
        private long sequenceNumber;

        private Entry(String diskKey) {
            this.expiryTimestamp = Long.MAX_VALUE;
            this.diskKey = diskKey;
            this.lengths = new long[LruDiskCache.this.valueCount];
        }

        public String getLengths() throws IOException {
            StringBuilder result = new StringBuilder();
            for (long size : this.lengths) {
                result.append(HanziToPinyin.Token.SEPARATOR).append(size);
            }
            return result.toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLengths(String[] strings, int startIndex) throws IOException {
            if (strings.length - startIndex != LruDiskCache.this.valueCount) {
                throw invalidLengths(strings);
            }
            for (int i = 0; i < LruDiskCache.this.valueCount; i++) {
                try {
                    this.lengths[i] = Long.parseLong(strings[i + startIndex]);
                } catch (NumberFormatException e) {
                    throw invalidLengths(strings);
                }
            }
        }

        private IOException invalidLengths(String[] strings) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strings));
        }

        public File getCleanFile(int i) {
            return new File(LruDiskCache.this.directory, this.diskKey + "." + i);
        }

        public File getDirtyFile(int i) {
            return new File(LruDiskCache.this.directory, this.diskKey + "." + i + ".tmp");
        }
    }

    private static String readFully(Reader reader) throws IOException {
        StringWriter writer = null;
        try {
            StringWriter writer2 = new StringWriter();
            try {
                char[] buffer = new char[1024];
                while (true) {
                    int count = reader.read(buffer);
                    if (count != -1) {
                        writer2.write(buffer, 0, count);
                    } else {
                        String stringWriter = writer2.toString();
                        IOUtils.closeQuietly(reader);
                        IOUtils.closeQuietly(writer2);
                        return stringWriter;
                    }
                }
            } catch (Throwable th) {
                th = th;
                writer = writer2;
                IOUtils.closeQuietly(reader);
                IOUtils.closeQuietly(writer);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void deleteContents(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) {
            throw new IOException("not a readable directory: " + dir);
        }
        for (File file : files) {
            if (file.isDirectory()) {
                deleteContents(file);
            }
            if (file.exists() && !file.delete()) {
                throw new IOException("failed to delete file: " + file);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class StrictLineReader implements Closeable {
        private static final byte CR = 13;
        private static final byte LF = 10;
        private byte[] buf;
        private final Charset charset;
        private int end;
        private final InputStream in;
        private int pos;

        public StrictLineReader(LruDiskCache lruDiskCache, InputStream in) {
            this(in, 8192);
        }

        public StrictLineReader(InputStream in, int capacity) {
            this.charset = Charset.forName("US-ASCII");
            if (in == null) {
                throw new NullPointerException();
            } else if (capacity < 0) {
                throw new IllegalArgumentException("capacity <= 0");
            } else {
                this.in = in;
                this.buf = new byte[capacity];
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (this.in) {
                if (this.buf != null) {
                    this.buf = null;
                    this.in.close();
                }
            }
        }

        public String readLine() throws IOException {
            int i;
            String res;
            synchronized (this.in) {
                if (this.buf == null) {
                    throw new IOException("LineReader is closed");
                }
                if (this.pos >= this.end) {
                    fillBuf();
                }
                int i2 = this.pos;
                while (true) {
                    if (i2 == this.end) {
                        ByteArrayOutputStream out = new ByteArrayOutputStream((this.end - this.pos) + 80) { // from class: com.lidroid.xutils.cache.LruDiskCache.StrictLineReader.1
                            @Override // java.io.ByteArrayOutputStream
                            public String toString() {
                                try {
                                    return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + (-1)] != 13) ? this.count : this.count - 1, StrictLineReader.this.charset.name());
                                } catch (UnsupportedEncodingException e) {
                                    throw new AssertionError(e);
                                }
                            }
                        };
                        loop1: while (true) {
                            out.write(this.buf, this.pos, this.end - this.pos);
                            this.end = -1;
                            fillBuf();
                            i = this.pos;
                            while (i != this.end) {
                                if (this.buf[i] == 10) {
                                    break loop1;
                                }
                                i++;
                            }
                        }
                        if (i != this.pos) {
                            out.write(this.buf, this.pos, i - this.pos);
                        }
                        out.flush();
                        this.pos = i + 1;
                        res = out.toString();
                    } else if (this.buf[i2] == 10) {
                        res = new String(this.buf, this.pos, ((i2 == this.pos || this.buf[i2 + (-1)] != 13) ? i2 : i2 - 1) - this.pos, this.charset.name());
                        this.pos = i2 + 1;
                    } else {
                        i2++;
                    }
                }
                return res;
            }
        }

        private void fillBuf() throws IOException {
            int result = this.in.read(this.buf, 0, this.buf.length);
            if (result == -1) {
                throw new EOFException();
            }
            this.pos = 0;
            this.end = result;
        }
    }

    public FileNameGenerator getFileNameGenerator() {
        return this.fileNameGenerator;
    }

    public void setFileNameGenerator(FileNameGenerator fileNameGenerator) {
        if (fileNameGenerator != null) {
            this.fileNameGenerator = fileNameGenerator;
        }
    }
}
