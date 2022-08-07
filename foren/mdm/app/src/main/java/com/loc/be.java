package com.loc;

import com.hyphenate.util.HanziToPinyin;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* compiled from: DiskLruCache.java */
/* loaded from: classes2.dex */
public final class be implements Closeable {
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private long h;
    private Writer k;
    private int n;
    private bf o;
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final ThreadFactory q = new ThreadFactory() { // from class: com.loc.be.1
        private final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "disklrucache#" + this.a.getAndIncrement());
        }
    };
    static ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), q);
    private static final OutputStream s = new OutputStream() { // from class: com.loc.be.3
        @Override // java.io.OutputStream
        public final void write(int i) throws IOException {
        }
    };
    private long j = 0;
    private int l = 1000;
    private final LinkedHashMap<String, c> m = new LinkedHashMap<>(0, 0.75f, true);
    private long p = 0;
    private final Callable<Void> r = new Callable<Void>() { // from class: com.loc.be.2
        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public Void call() throws Exception {
            synchronized (be.this) {
                if (be.this.k != null) {
                    be.this.l();
                    if (be.this.j()) {
                        be.this.i();
                        be.this.n = 0;
                    }
                }
            }
            return null;
        }
    };
    private final int g = 1;
    private final int i = 1;

    /* compiled from: DiskLruCache.java */
    /* loaded from: classes2.dex */
    public final class a {
        private final c b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: DiskLruCache.java */
        /* renamed from: com.loc.be$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public class C0045a extends FilterOutputStream {
            private C0045a(OutputStream outputStream) {
                super(outputStream);
            }

            /* synthetic */ C0045a(a aVar, OutputStream outputStream, byte b) {
                this(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public final void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    a.this.d = true;
                }
            }
        }

        private a(c cVar) {
            this.b = cVar;
            this.c = cVar.d ? null : new boolean[be.this.i];
        }

        /* synthetic */ a(be beVar, c cVar, byte b) {
            this(cVar);
        }

        public final OutputStream a() throws IOException {
            OutputStream outputStream;
            FileOutputStream fileOutputStream;
            if (be.this.i <= 0) {
                throw new IllegalArgumentException("Expected index 0 to be greater than 0 and less than the maximum value count of " + be.this.i);
            }
            synchronized (be.this) {
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[0] = true;
                }
                File b = this.b.b(0);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    be.this.c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        outputStream = be.s;
                    }
                }
                outputStream = new C0045a(this, fileOutputStream, (byte) 0);
            }
            return outputStream;
        }

        public final void b() throws IOException {
            if (this.d) {
                be.this.a(this, false);
                be.this.c(this.b.b);
            } else {
                be.this.a(this, true);
            }
            this.e = true;
        }

        public final void c() throws IOException {
            be.this.a(this, false);
        }
    }

    /* compiled from: DiskLruCache.java */
    /* loaded from: classes2.dex */
    public final class b implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        /* synthetic */ b(be beVar, String str, long j, InputStream[] inputStreamArr, long[] jArr, byte b) {
            this(str, j, inputStreamArr, jArr);
        }

        public final InputStream a() {
            return this.d[0];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            for (InputStream inputStream : this.d) {
                bh.a(inputStream);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DiskLruCache.java */
    /* loaded from: classes2.dex */
    public final class c {
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private c(String str) {
            this.b = str;
            this.c = new long[be.this.i];
        }

        /* synthetic */ c(be beVar, String str, byte b) {
            this(str);
        }

        private static IOException a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        static /* synthetic */ void a(c cVar, String[] strArr) throws IOException {
            if (strArr.length != be.this.i) {
                throw a(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    cVar.c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException e) {
                    throw a(strArr);
                }
            }
        }

        public final File a(int i) {
            return new File(be.this.c, this.b + "." + i);
        }

        public final String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.c) {
                sb.append(' ').append(j);
            }
            return sb.toString();
        }

        public final File b(int i) {
            return new File(be.this.c, this.b + "." + i + ".tmp");
        }
    }

    private be(File file, long j) {
        this.c = file;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.h = j;
    }

    public static be a(File file, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        be beVar = new be(file, j);
        if (beVar.d.exists()) {
            try {
                beVar.g();
                beVar.h();
                beVar.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beVar.d, true), bh.a));
                return beVar;
            } catch (Throwable th) {
                beVar.d();
            }
        }
        file.mkdirs();
        be beVar2 = new be(file, j);
        beVar2.i();
        return beVar2;
    }

    public static void a() {
        if (b != null && !b.isShutdown()) {
            b.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(a aVar, boolean z) throws IOException {
        synchronized (this) {
            c cVar = aVar.b;
            if (cVar.e != aVar) {
                throw new IllegalStateException();
            }
            if (z && !cVar.d) {
                for (int i = 0; i < this.i; i++) {
                    if (!aVar.c[i]) {
                        aVar.c();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    } else if (!cVar.b(i).exists()) {
                        aVar.c();
                        break;
                    }
                }
            }
            for (int i2 = 0; i2 < this.i; i2++) {
                File b2 = cVar.b(i2);
                if (!z) {
                    a(b2);
                } else if (b2.exists()) {
                    File a2 = cVar.a(i2);
                    b2.renameTo(a2);
                    long j = cVar.c[i2];
                    long length = a2.length();
                    cVar.c[i2] = length;
                    this.j = (this.j - j) + length;
                }
            }
            this.n++;
            cVar.e = null;
            if (cVar.d || z) {
                cVar.d = true;
                this.k.write("CLEAN " + cVar.b + cVar.a() + '\n');
                if (z) {
                    long j2 = this.p;
                    this.p = 1 + j2;
                    cVar.f = j2;
                }
            } else {
                this.m.remove(cVar.b);
                this.k.write("REMOVE " + cVar.b + '\n');
            }
            this.k.flush();
            if (this.j > this.h || j()) {
                f().submit(this.r);
            }
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    private synchronized a d(String str) throws IOException {
        c cVar;
        a aVar;
        k();
        e(str);
        c cVar2 = this.m.get(str);
        if (-1 == -1 || (cVar2 != null && cVar2.f == -1)) {
            if (cVar2 == null) {
                c cVar3 = new c(this, str, (byte) 0);
                this.m.put(str, cVar3);
                cVar = cVar3;
            } else if (cVar2.e != null) {
                aVar = null;
            } else {
                cVar = cVar2;
            }
            aVar = new a(this, cVar, (byte) 0);
            cVar.e = aVar;
            this.k.write("DIRTY " + str + '\n');
            this.k.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    private static void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    private static ThreadPoolExecutor f() {
        try {
            if (b == null || b.isShutdown()) {
                b = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), q);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return b;
    }

    private void g() throws IOException {
        String a2;
        String str;
        int i = 0;
        bg bgVar = new bg(new FileInputStream(this.d), bh.a);
        try {
            String a3 = bgVar.a();
            String a4 = bgVar.a();
            String a5 = bgVar.a();
            String a6 = bgVar.a();
            String a7 = bgVar.a();
            if (!"libcore.io.DiskLruCache".equals(a3) || !"1".equals(a4) || !Integer.toString(this.g).equals(a5) || !Integer.toString(this.i).equals(a6) || !"".equals(a7)) {
                throw new IOException("unexpected journal header: [" + a3 + ", " + a4 + ", " + a6 + ", " + a7 + "]");
            }
            while (true) {
                try {
                    a2 = bgVar.a();
                    int indexOf = a2.indexOf(32);
                    if (indexOf == -1) {
                        throw new IOException("unexpected journal line: " + a2);
                    }
                    int i2 = indexOf + 1;
                    int indexOf2 = a2.indexOf(32, i2);
                    if (indexOf2 == -1) {
                        String substring = a2.substring(i2);
                        if (indexOf != 6 || !a2.startsWith("REMOVE")) {
                            str = substring;
                        } else {
                            this.m.remove(substring);
                            i++;
                        }
                    } else {
                        str = a2.substring(i2, indexOf2);
                    }
                    c cVar = this.m.get(str);
                    if (cVar == null) {
                        cVar = new c(this, str, (byte) 0);
                        this.m.put(str, cVar);
                    }
                    if (indexOf2 != -1 && indexOf == 5 && a2.startsWith("CLEAN")) {
                        String[] split = a2.substring(indexOf2 + 1).split(HanziToPinyin.Token.SEPARATOR);
                        cVar.d = true;
                        cVar.e = null;
                        c.a(cVar, split);
                    } else if (indexOf2 == -1 && indexOf == 5 && a2.startsWith("DIRTY")) {
                        cVar.e = new a(this, cVar, (byte) 0);
                    } else if (indexOf2 != -1 || indexOf != 4 || !a2.startsWith("READ")) {
                        break;
                    }
                    i++;
                } catch (EOFException e) {
                    this.n = i - this.m.size();
                    bh.a(bgVar);
                    return;
                }
            }
            throw new IOException("unexpected journal line: " + a2);
        } catch (Throwable th) {
            bh.a(bgVar);
            throw th;
        }
    }

    private void h() throws IOException {
        a(this.e);
        Iterator<c> it = this.m.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            if (next.e == null) {
                for (int i = 0; i < this.i; i++) {
                    this.j += next.c[i];
                }
            } else {
                next.e = null;
                for (int i2 = 0; i2 < this.i; i2++) {
                    a(next.a(i2));
                    a(next.b(i2));
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void i() throws IOException {
        if (this.k != null) {
            this.k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), bh.a));
        bufferedWriter.write("libcore.io.DiskLruCache");
        bufferedWriter.write("\n");
        bufferedWriter.write("1");
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.g));
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.i));
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        for (c cVar : this.m.values()) {
            if (cVar.e != null) {
                bufferedWriter.write("DIRTY " + cVar.b + '\n');
            } else {
                bufferedWriter.write("CLEAN " + cVar.b + cVar.a() + '\n');
            }
        }
        bufferedWriter.close();
        if (this.d.exists()) {
            a(this.d, this.f, true);
        }
        a(this.e, this.d, false);
        this.f.delete();
        this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), bh.a));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        return this.n >= 2000 && this.n >= this.m.size();
    }

    private void k() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() throws IOException {
        while (true) {
            if (this.j > this.h || this.m.size() > this.l) {
                String key = this.m.entrySet().iterator().next().getKey();
                c(key);
                if (this.o != null) {
                    this.o.a(key);
                }
            } else {
                return;
            }
        }
    }

    public final synchronized b a(String str) throws IOException {
        b bVar = null;
        synchronized (this) {
            k();
            e(str);
            c cVar = this.m.get(str);
            if (cVar != null && cVar.d) {
                InputStream[] inputStreamArr = new InputStream[this.i];
                for (int i = 0; i < this.i; i++) {
                    try {
                        inputStreamArr[i] = new FileInputStream(cVar.a(i));
                    } catch (FileNotFoundException e) {
                        for (int i2 = 0; i2 < this.i && inputStreamArr[i2] != null; i2++) {
                            bh.a(inputStreamArr[i2]);
                        }
                    }
                }
                this.n++;
                this.k.append((CharSequence) ("READ " + str + '\n'));
                if (j()) {
                    f().submit(this.r);
                }
                bVar = new b(this, str, cVar.f, inputStreamArr, cVar.c, (byte) 0);
            }
        }
        return bVar;
    }

    public final void a(int i) {
        if (i < 10) {
            i = 10;
        } else if (i > 10000) {
            i = 10000;
        }
        this.l = i;
    }

    public final void a(bf bfVar) {
        this.o = bfVar;
    }

    public final a b(String str) throws IOException {
        return d(str);
    }

    public final synchronized boolean b() {
        return this.k == null;
    }

    public final synchronized void c() throws IOException {
        k();
        l();
        this.k.flush();
    }

    public final synchronized boolean c(String str) throws IOException {
        boolean z;
        synchronized (this) {
            k();
            e(str);
            c cVar = this.m.get(str);
            if (cVar == null || cVar.e != null) {
                z = false;
            } else {
                for (int i = 0; i < this.i; i++) {
                    File a2 = cVar.a(i);
                    if (!a2.exists() || a2.delete()) {
                        this.j -= cVar.c[i];
                        cVar.c[i] = 0;
                    } else {
                        throw new IOException("failed to delete " + a2);
                    }
                }
                this.n++;
                this.k.append((CharSequence) ("REMOVE " + str + '\n'));
                this.m.remove(str);
                if (j()) {
                    f().submit(this.r);
                }
                z = true;
            }
        }
        return z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (this.k != null) {
            Iterator it = new ArrayList(this.m.values()).iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.e != null) {
                    cVar.e.c();
                }
            }
            l();
            this.k.close();
            this.k = null;
        }
    }

    public final void d() throws IOException {
        close();
        bh.a(this.c);
    }
}
