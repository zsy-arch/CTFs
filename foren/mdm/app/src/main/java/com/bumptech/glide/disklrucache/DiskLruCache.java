package com.bumptech.glide.disklrucache;

import com.hyphenate.util.HanziToPinyin;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class DiskLruCache implements Closeable {
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
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
    private final Callable<Void> cleanupCallable = new Callable<Void>() { // from class: com.bumptech.glide.disklrucache.DiskLruCache.1
        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            synchronized (DiskLruCache.this) {
                if (DiskLruCache.this.journalWriter != null) {
                    DiskLruCache.this.trimToSize();
                    if (DiskLruCache.this.journalRebuildRequired()) {
                        DiskLruCache.this.rebuildJournal();
                        DiskLruCache.this.redundantOpCount = 0;
                    }
                }
            }
            return null;
        }
    };

    private DiskLruCache(File directory, int appVersion, int valueCount, long maxSize) {
        this.directory = directory;
        this.appVersion = appVersion;
        this.journalFile = new File(directory, JOURNAL_FILE);
        this.journalFileTmp = new File(directory, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(directory, JOURNAL_FILE_BACKUP);
        this.valueCount = valueCount;
        this.maxSize = maxSize;
    }

    public static DiskLruCache open(File directory, int appVersion, int valueCount, long maxSize) throws IOException {
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
            DiskLruCache cache = new DiskLruCache(directory, appVersion, valueCount, maxSize);
            if (cache.journalFile.exists()) {
                try {
                    cache.readJournal();
                    cache.processJournal();
                    return cache;
                } catch (IOException journalIsCorrupt) {
                    System.out.println("DiskLruCache " + directory + " is corrupt: " + journalIsCorrupt.getMessage() + ", removing");
                    cache.delete();
                }
            }
            directory.mkdirs();
            DiskLruCache cache2 = new DiskLruCache(directory, appVersion, valueCount, maxSize);
            cache2.rebuildJournal();
            return cache2;
        }
    }

    private void readJournal() throws IOException {
        StrictLineReader reader = new StrictLineReader(new FileInputStream(this.journalFile), Util.US_ASCII);
        try {
            String magic = reader.readLine();
            String version = reader.readLine();
            String appVersionString = reader.readLine();
            String valueCountString = reader.readLine();
            String blank = reader.readLine();
            if (!MAGIC.equals(magic) || !"1".equals(version) || !Integer.toString(this.appVersion).equals(appVersionString) || !Integer.toString(this.valueCount).equals(valueCountString) || !"".equals(blank)) {
                throw new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + "]");
            }
            int lineCount = 0;
            while (true) {
                try {
                    readJournalLine(reader.readLine());
                    lineCount++;
                } catch (EOFException e) {
                    this.redundantOpCount = lineCount - this.lruEntries.size();
                    if (reader.hasUnterminatedLine()) {
                        rebuildJournal();
                    } else {
                        this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
                    }
                    Util.closeQuietly(reader);
                    return;
                }
            }
        } catch (Throwable th) {
            Util.closeQuietly(reader);
            throw th;
        }
    }

    private void readJournalLine(String line) throws IOException {
        String key;
        int firstSpace = line.indexOf(32);
        if (firstSpace == -1) {
            throw new IOException("unexpected journal line: " + line);
        }
        int keyBegin = firstSpace + 1;
        int secondSpace = line.indexOf(32, keyBegin);
        if (secondSpace == -1) {
            key = line.substring(keyBegin);
            if (firstSpace == REMOVE.length() && line.startsWith(REMOVE)) {
                this.lruEntries.remove(key);
                return;
            }
        } else {
            key = line.substring(keyBegin, secondSpace);
        }
        Entry entry = this.lruEntries.get(key);
        if (entry == null) {
            entry = new Entry(key);
            this.lruEntries.put(key, entry);
        }
        if (secondSpace != -1 && firstSpace == CLEAN.length() && line.startsWith(CLEAN)) {
            String[] parts = line.substring(secondSpace + 1).split(HanziToPinyin.Token.SEPARATOR);
            entry.readable = true;
            entry.currentEditor = null;
            entry.setLengths(parts);
        } else if (secondSpace == -1 && firstSpace == DIRTY.length() && line.startsWith(DIRTY)) {
            entry.currentEditor = new Editor(entry);
        } else if (secondSpace != -1 || firstSpace != READ.length() || !line.startsWith(READ)) {
            throw new IOException("unexpected journal line: " + line);
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
            this.journalWriter.close();
        }
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), Util.US_ASCII));
        writer.write(MAGIC);
        writer.write("\n");
        writer.write("1");
        writer.write("\n");
        writer.write(Integer.toString(this.appVersion));
        writer.write("\n");
        writer.write(Integer.toString(this.valueCount));
        writer.write("\n");
        writer.write("\n");
        for (Entry entry : this.lruEntries.values()) {
            if (entry.currentEditor != null) {
                writer.write("DIRTY " + entry.key + '\n');
            } else {
                writer.write("CLEAN " + entry.key + entry.getLengths() + '\n');
            }
        }
        writer.close();
        if (this.journalFile.exists()) {
            renameTo(this.journalFile, this.journalFileBackup, true);
        }
        renameTo(this.journalFileTmp, this.journalFile, false);
        this.journalFileBackup.delete();
        this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
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

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0028, code lost:
        r13.redundantOpCount++;
        r13.journalWriter.append((java.lang.CharSequence) com.bumptech.glide.disklrucache.DiskLruCache.READ);
        r13.journalWriter.append(' ');
        r13.journalWriter.append((java.lang.CharSequence) r14);
        r13.journalWriter.append('\n');
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004c, code lost:
        if (journalRebuildRequired() == false) goto L_0x0055;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004e, code lost:
        r13.executorService.submit(r13.cleanupCallable);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0055, code lost:
        r1 = new com.bumptech.glide.disklrucache.DiskLruCache.Value(r13, r14, r9.sequenceNumber, r9.cleanFiles, r9.lengths, null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.bumptech.glide.disklrucache.DiskLruCache.Value get(java.lang.String r14) throws java.io.IOException {
        /*
            r13 = this;
            r1 = 0
            monitor-enter(r13)
            r13.checkNotClosed()     // Catch: all -> 0x0068
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r2 = r13.lruEntries     // Catch: all -> 0x0068
            java.lang.Object r9 = r2.get(r14)     // Catch: all -> 0x0068
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r9 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r9     // Catch: all -> 0x0068
            if (r9 != 0) goto L_0x0011
        L_0x000f:
            monitor-exit(r13)
            return r1
        L_0x0011:
            boolean r2 = com.bumptech.glide.disklrucache.DiskLruCache.Entry.access$600(r9)     // Catch: all -> 0x0068
            if (r2 == 0) goto L_0x000f
            java.io.File[] r0 = r9.cleanFiles     // Catch: all -> 0x0068
            int r12 = r0.length     // Catch: all -> 0x0068
            r11 = 0
        L_0x001b:
            if (r11 >= r12) goto L_0x0028
            r10 = r0[r11]     // Catch: all -> 0x0068
            boolean r2 = r10.exists()     // Catch: all -> 0x0068
            if (r2 == 0) goto L_0x000f
            int r11 = r11 + 1
            goto L_0x001b
        L_0x0028:
            int r1 = r13.redundantOpCount     // Catch: all -> 0x0068
            int r1 = r1 + 1
            r13.redundantOpCount = r1     // Catch: all -> 0x0068
            java.io.Writer r1 = r13.journalWriter     // Catch: all -> 0x0068
            java.lang.String r2 = "READ"
            r1.append(r2)     // Catch: all -> 0x0068
            java.io.Writer r1 = r13.journalWriter     // Catch: all -> 0x0068
            r2 = 32
            r1.append(r2)     // Catch: all -> 0x0068
            java.io.Writer r1 = r13.journalWriter     // Catch: all -> 0x0068
            r1.append(r14)     // Catch: all -> 0x0068
            java.io.Writer r1 = r13.journalWriter     // Catch: all -> 0x0068
            r2 = 10
            r1.append(r2)     // Catch: all -> 0x0068
            boolean r1 = r13.journalRebuildRequired()     // Catch: all -> 0x0068
            if (r1 == 0) goto L_0x0055
            java.util.concurrent.ThreadPoolExecutor r1 = r13.executorService     // Catch: all -> 0x0068
            java.util.concurrent.Callable<java.lang.Void> r2 = r13.cleanupCallable     // Catch: all -> 0x0068
            r1.submit(r2)     // Catch: all -> 0x0068
        L_0x0055:
            com.bumptech.glide.disklrucache.DiskLruCache$Value r1 = new com.bumptech.glide.disklrucache.DiskLruCache$Value     // Catch: all -> 0x0068
            long r4 = com.bumptech.glide.disklrucache.DiskLruCache.Entry.access$1200(r9)     // Catch: all -> 0x0068
            java.io.File[] r6 = r9.cleanFiles     // Catch: all -> 0x0068
            long[] r7 = com.bumptech.glide.disklrucache.DiskLruCache.Entry.access$1000(r9)     // Catch: all -> 0x0068
            r8 = 0
            r2 = r13
            r3 = r14
            r1.<init>(r3, r4, r6, r7)     // Catch: all -> 0x0068
            goto L_0x000f
        L_0x0068:
            r1 = move-exception
            monitor-exit(r13)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.get(java.lang.String):com.bumptech.glide.disklrucache.DiskLruCache$Value");
    }

    public Editor edit(String key) throws IOException {
        return edit(key, -1L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005c, code lost:
        if (r1.currentEditor != null) goto L_0x001d;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.bumptech.glide.disklrucache.DiskLruCache.Editor edit(java.lang.String r5, long r6) throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            monitor-enter(r4)
            r4.checkNotClosed()     // Catch: all -> 0x0055
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r2 = r4.lruEntries     // Catch: all -> 0x0055
            java.lang.Object r1 = r2.get(r5)     // Catch: all -> 0x0055
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r1 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r1     // Catch: all -> 0x0055
            r2 = -1
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x001f
            if (r1 == 0) goto L_0x001d
            long r2 = com.bumptech.glide.disklrucache.DiskLruCache.Entry.access$1200(r1)     // Catch: all -> 0x0055
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001f
        L_0x001d:
            monitor-exit(r4)
            return r0
        L_0x001f:
            if (r1 != 0) goto L_0x0058
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r1 = new com.bumptech.glide.disklrucache.DiskLruCache$Entry     // Catch: all -> 0x0055
            r2 = 0
            r1.<init>(r5)     // Catch: all -> 0x0055
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r2 = r4.lruEntries     // Catch: all -> 0x0055
            r2.put(r5, r1)     // Catch: all -> 0x0055
        L_0x002c:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r0 = new com.bumptech.glide.disklrucache.DiskLruCache$Editor     // Catch: all -> 0x0055
            r2 = 0
            r0.<init>(r1)     // Catch: all -> 0x0055
            com.bumptech.glide.disklrucache.DiskLruCache.Entry.access$702(r1, r0)     // Catch: all -> 0x0055
            java.io.Writer r2 = r4.journalWriter     // Catch: all -> 0x0055
            java.lang.String r3 = "DIRTY"
            r2.append(r3)     // Catch: all -> 0x0055
            java.io.Writer r2 = r4.journalWriter     // Catch: all -> 0x0055
            r3 = 32
            r2.append(r3)     // Catch: all -> 0x0055
            java.io.Writer r2 = r4.journalWriter     // Catch: all -> 0x0055
            r2.append(r5)     // Catch: all -> 0x0055
            java.io.Writer r2 = r4.journalWriter     // Catch: all -> 0x0055
            r3 = 10
            r2.append(r3)     // Catch: all -> 0x0055
            java.io.Writer r2 = r4.journalWriter     // Catch: all -> 0x0055
            r2.flush()     // Catch: all -> 0x0055
            goto L_0x001d
        L_0x0055:
            r2 = move-exception
            monitor-exit(r4)
            throw r2
        L_0x0058:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r2 = com.bumptech.glide.disklrucache.DiskLruCache.Entry.access$700(r1)     // Catch: all -> 0x0055
            if (r2 == 0) goto L_0x002c
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.edit(java.lang.String, long):com.bumptech.glide.disklrucache.DiskLruCache$Editor");
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
            this.journalWriter.append((CharSequence) CLEAN);
            this.journalWriter.append(' ');
            this.journalWriter.append((CharSequence) entry.key);
            this.journalWriter.append((CharSequence) entry.getLengths());
            this.journalWriter.append('\n');
            if (success) {
                long j = this.nextSequenceNumber;
                this.nextSequenceNumber = 1 + j;
                entry.sequenceNumber = j;
            }
        } else {
            this.lruEntries.remove(entry.key);
            this.journalWriter.append((CharSequence) REMOVE);
            this.journalWriter.append(' ');
            this.journalWriter.append((CharSequence) entry.key);
            this.journalWriter.append('\n');
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

    public synchronized boolean remove(String key) throws IOException {
        boolean z;
        checkNotClosed();
        Entry entry = this.lruEntries.get(key);
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
            this.journalWriter.append((CharSequence) REMOVE);
            this.journalWriter.append(' ');
            this.journalWriter.append((CharSequence) key);
            this.journalWriter.append('\n');
            this.lruEntries.remove(key);
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
            Iterator i$ = new ArrayList(this.lruEntries.values()).iterator();
            while (i$.hasNext()) {
                Entry entry = (Entry) i$.next();
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
            remove(this.lruEntries.entrySet().iterator().next().getKey());
        }
    }

    public void delete() throws IOException {
        close();
        Util.deleteContents(this.directory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String inputStreamToString(InputStream in) throws IOException {
        return Util.readFully(new InputStreamReader(in, Util.UTF_8));
    }

    /* loaded from: classes.dex */
    public final class Value {
        private final File[] files;
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;

        private Value(String key, long sequenceNumber, File[] files, long[] lengths) {
            this.key = key;
            this.sequenceNumber = sequenceNumber;
            this.files = files;
            this.lengths = lengths;
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public File getFile(int index) {
            return this.files[index];
        }

        public String getString(int index) throws IOException {
            return DiskLruCache.inputStreamToString(new FileInputStream(this.files[index]));
        }

        public long getLength(int index) {
            return this.lengths[index];
        }
    }

    /* loaded from: classes.dex */
    public final class Editor {
        private boolean committed;
        private final Entry entry;
        private final boolean[] written;

        private Editor(Entry entry) {
            this.entry = entry;
            this.written = entry.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        private InputStream newInputStream(int index) throws IOException {
            synchronized (DiskLruCache.this) {
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
                return DiskLruCache.inputStreamToString(in);
            }
            return null;
        }

        public File getFile(int index) throws IOException {
            File dirtyFile;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable) {
                    this.written[index] = true;
                }
                dirtyFile = this.entry.getDirtyFile(index);
                if (!DiskLruCache.this.directory.exists()) {
                    DiskLruCache.this.directory.mkdirs();
                }
            }
            return dirtyFile;
        }

        public void set(int index, String value) throws IOException {
            Throwable th;
            Writer writer;
            Writer writer2 = null;
            try {
                writer = new OutputStreamWriter(new FileOutputStream(getFile(index)), Util.UTF_8);
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                writer.write(value);
                Util.closeQuietly(writer);
            } catch (Throwable th3) {
                th = th3;
                writer2 = writer;
                Util.closeQuietly(writer2);
                throw th;
            }
        }

        public void commit() throws IOException {
            DiskLruCache.this.completeEdit(this, true);
            this.committed = true;
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }

        public void abortUnlessCommitted() {
            if (!this.committed) {
                try {
                    abort();
                } catch (IOException e) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class Entry {
        File[] cleanFiles;
        private Editor currentEditor;
        File[] dirtyFiles;
        private final String key;
        private final long[] lengths;
        private boolean readable;
        private long sequenceNumber;

        private Entry(String key) {
            this.key = key;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder fileBuilder = new StringBuilder(key).append('.');
            int truncateTo = fileBuilder.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                fileBuilder.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, fileBuilder.toString());
                fileBuilder.append(".tmp");
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, fileBuilder.toString());
                fileBuilder.setLength(truncateTo);
            }
        }

        public String getLengths() throws IOException {
            StringBuilder result = new StringBuilder();
            for (long size : this.lengths) {
                result.append(' ').append(size);
            }
            return result.toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLengths(String[] strings) throws IOException {
            if (strings.length != DiskLruCache.this.valueCount) {
                throw invalidLengths(strings);
            }
            for (int i = 0; i < strings.length; i++) {
                try {
                    this.lengths[i] = Long.parseLong(strings[i]);
                } catch (NumberFormatException e) {
                    throw invalidLengths(strings);
                }
            }
        }

        private IOException invalidLengths(String[] strings) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strings));
        }

        public File getCleanFile(int i) {
            return this.cleanFiles[i];
        }

        public File getDirtyFile(int i) {
            return this.dirtyFiles[i];
        }
    }
}
