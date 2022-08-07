package com.amap.api.services.a;

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
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* compiled from: DiskLruCache.java */
/* loaded from: classes.dex */
public final class cp implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final OutputStream q = new OutputStream() { // from class: com.amap.api.services.a.cp.2
        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
        }
    };
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private final int i;
    private Writer k;
    private int m;
    private cq n;
    private long j = 0;
    private final LinkedHashMap<String, c> l = new LinkedHashMap<>(0, 0.75f, true);
    private long o = 0;
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Callable<Void> p = new Callable<Void>() { // from class: com.amap.api.services.a.cp.1
        /* renamed from: a */
        public Void call() throws Exception {
            synchronized (cp.this) {
                if (cp.this.k != null) {
                    cp.this.j();
                    if (cp.this.h()) {
                        cp.this.g();
                        cp.this.m = 0;
                    }
                }
            }
            return null;
        }
    };

    public void a(cq cqVar) {
        this.n = cqVar;
    }

    private cp(File file, int i, int i2, long j) {
        this.c = file;
        this.g = i;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = i2;
        this.h = j;
    }

    public static cp a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            cp cpVar = new cp(file, i, i2, j);
            if (cpVar.d.exists()) {
                try {
                    cpVar.e();
                    cpVar.f();
                    cpVar.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cpVar.d, true), cs.a));
                    return cpVar;
                } catch (Throwable th) {
                    cpVar.c();
                }
            }
            file.mkdirs();
            cp cpVar2 = new cp(file, i, i2, j);
            cpVar2.g();
            return cpVar2;
        }
    }

    private void e() throws IOException {
        cr crVar = new cr(new FileInputStream(this.d), cs.a);
        try {
            String a2 = crVar.a();
            String a3 = crVar.a();
            String a4 = crVar.a();
            String a5 = crVar.a();
            String a6 = crVar.a();
            if (!"libcore.io.DiskLruCache".equals(a2) || !"1".equals(a3) || !Integer.toString(this.g).equals(a4) || !Integer.toString(this.i).equals(a5) || !"".equals(a6)) {
                throw new IOException("unexpected journal header: [" + a2 + ", " + a3 + ", " + a5 + ", " + a6 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    d(crVar.a());
                    i++;
                } catch (EOFException e) {
                    this.m = i - this.l.size();
                    cs.a(crVar);
                    return;
                }
            }
        } catch (Throwable th) {
            cs.a(crVar);
            throw th;
        }
    }

    private void d(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf != "REMOVE".length() || !str.startsWith("REMOVE")) {
                str2 = substring;
            } else {
                this.l.remove(substring);
                return;
            }
        } else {
            str2 = str.substring(i, indexOf2);
        }
        c cVar = this.l.get(str2);
        if (cVar == null) {
            cVar = new c(str2);
            this.l.put(str2, cVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(HanziToPinyin.Token.SEPARATOR);
            cVar.d = true;
            cVar.e = null;
            cVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            cVar.e = new a(cVar);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void f() throws IOException {
        a(this.e);
        Iterator<c> it = this.l.values().iterator();
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

    public synchronized void g() throws IOException {
        if (this.k != null) {
            this.k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), cs.a));
        bufferedWriter.write("libcore.io.DiskLruCache");
        bufferedWriter.write("\n");
        bufferedWriter.write("1");
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.g));
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.i));
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        for (c cVar : this.l.values()) {
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
        this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), cs.a));
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

    public synchronized b a(String str) throws IOException {
        b bVar = null;
        synchronized (this) {
            i();
            e(str);
            c cVar = this.l.get(str);
            if (cVar != null && cVar.d) {
                InputStream[] inputStreamArr = new InputStream[this.i];
                for (int i = 0; i < this.i; i++) {
                    try {
                        inputStreamArr[i] = new FileInputStream(cVar.a(i));
                    } catch (FileNotFoundException e) {
                        for (int i2 = 0; i2 < this.i && inputStreamArr[i2] != null; i2++) {
                            cs.a(inputStreamArr[i2]);
                        }
                    }
                }
                this.m++;
                this.k.append((CharSequence) ("READ " + str + '\n'));
                if (h()) {
                    this.b.submit(this.p);
                }
                bVar = new b(str, cVar.f, inputStreamArr, cVar.c);
            }
        }
        return bVar;
    }

    public a b(String str) throws IOException {
        return a(str, -1L);
    }

    private synchronized a a(String str, long j) throws IOException {
        c cVar;
        a aVar;
        i();
        e(str);
        c cVar2 = this.l.get(str);
        if (j == -1 || (cVar2 != null && cVar2.f == j)) {
            if (cVar2 == null) {
                c cVar3 = new c(str);
                this.l.put(str, cVar3);
                cVar = cVar3;
            } else if (cVar2.e != null) {
                aVar = null;
            } else {
                cVar = cVar2;
            }
            aVar = new a(cVar);
            cVar.e = aVar;
            this.k.write("DIRTY " + str + '\n');
            this.k.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    public synchronized void a(a aVar, boolean z) throws IOException {
        synchronized (this) {
            c cVar = aVar.b;
            if (cVar.e != aVar) {
                throw new IllegalStateException();
            }
            if (z && !cVar.d) {
                for (int i = 0; i < this.i; i++) {
                    if (!aVar.c[i]) {
                        aVar.b();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    } else if (!cVar.b(i).exists()) {
                        aVar.b();
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
            this.m++;
            cVar.e = null;
            if (cVar.d || z) {
                cVar.d = true;
                this.k.write("CLEAN " + cVar.b + cVar.a() + '\n');
                if (z) {
                    long j2 = this.o;
                    this.o = 1 + j2;
                    cVar.f = j2;
                }
            } else {
                this.l.remove(cVar.b);
                this.k.write("REMOVE " + cVar.b + '\n');
            }
            this.k.flush();
            if (this.j > this.h || h()) {
                this.b.submit(this.p);
            }
        }
    }

    public boolean h() {
        return this.m >= 2000 && this.m >= this.l.size();
    }

    public synchronized boolean c(String str) throws IOException {
        boolean z;
        synchronized (this) {
            i();
            e(str);
            c cVar = this.l.get(str);
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
                this.m++;
                this.k.append((CharSequence) ("REMOVE " + str + '\n'));
                this.l.remove(str);
                if (h()) {
                    this.b.submit(this.p);
                }
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean a() {
        return this.k == null;
    }

    private void i() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void b() throws IOException {
        i();
        j();
        this.k.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.k != null) {
            Iterator it = new ArrayList(this.l.values()).iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.e != null) {
                    cVar.e.b();
                }
            }
            j();
            this.k.close();
            this.k = null;
        }
    }

    public void j() throws IOException {
        while (this.j > this.h) {
            String key = this.l.entrySet().iterator().next().getKey();
            c(key);
            if (this.n != null) {
                this.n.a(key);
            }
        }
    }

    public void c() throws IOException {
        close();
        cs.a(this.c);
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    /* compiled from: DiskLruCache.java */
    /* loaded from: classes.dex */
    public final class b implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            cp.this = r2;
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public InputStream a(int i) {
            return this.d[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (InputStream inputStream : this.d) {
                cs.a(inputStream);
            }
        }
    }

    /* compiled from: DiskLruCache.java */
    /* loaded from: classes.dex */
    public final class a {
        private final c b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        private a(c cVar) {
            cp.this = r2;
            this.b = cVar;
            this.c = cVar.d ? null : new boolean[r2.i];
        }

        public OutputStream a(int i) throws IOException {
            OutputStream outputStream;
            FileOutputStream fileOutputStream;
            if (i < 0 || i >= cp.this.i) {
                throw new IllegalArgumentException("Expected index " + i + " to be greater than 0 and less than the maximum value count of " + cp.this.i);
            }
            synchronized (cp.this) {
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[i] = true;
                }
                File b = this.b.b(i);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    cp.this.c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        outputStream = cp.q;
                    }
                }
                outputStream = new C0020a(fileOutputStream);
            }
            return outputStream;
        }

        public void a() throws IOException {
            if (this.d) {
                cp.this.a(this, false);
                cp.this.c(this.b.b);
            } else {
                cp.this.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            cp.this.a(this, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: DiskLruCache.java */
        /* renamed from: com.amap.api.services.a.cp$a$a */
        /* loaded from: classes.dex */
        public class C0020a extends FilterOutputStream {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            private C0020a(OutputStream outputStream) {
                super(outputStream);
                a.this = r1;
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    a.this.d = true;
                }
            }
        }
    }

    /* compiled from: DiskLruCache.java */
    /* loaded from: classes.dex */
    public final class c {
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private c(String str) {
            cp.this = r2;
            this.b = str;
            this.c = new long[r2.i];
        }

        public String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.c) {
                sb.append(' ').append(j);
            }
            return sb.toString();
        }

        public void a(String[] strArr) throws IOException {
            if (strArr.length != cp.this.i) {
                throw b(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(cp.this.c, this.b + "." + i);
        }

        public File b(int i) {
            return new File(cp.this.c, this.b + "." + i + ".tmp");
        }
    }
}
