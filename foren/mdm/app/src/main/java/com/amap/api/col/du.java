package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import com.amap.api.col.hw;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.util.HashMap;

/* compiled from: ImageCache.java */
/* loaded from: classes.dex */
public class du {
    private static final Bitmap.CompressFormat a = Bitmap.CompressFormat.PNG;
    private hw b;
    private dl<String, Bitmap> c;
    private a d;
    private final Object e = new Object();
    private boolean f = true;
    private HashMap<String, WeakReference<Bitmap>> g;

    private du(a aVar) {
        b(aVar);
    }

    public static du a(a aVar) {
        return new du(aVar);
    }

    private void b(a aVar) {
        this.d = aVar;
        if (this.d.f) {
            if (dy.a()) {
                this.g = new HashMap<>();
            }
            this.c = new dl<String, Bitmap>(this.d.a) { // from class: com.amap.api.col.du.1
                public void a(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
                    if (dt.c() && du.this.g != null && bitmap != null && !bitmap.isRecycled()) {
                        du.this.g.put(str, new WeakReference(bitmap));
                    }
                }

                /* renamed from: a */
                public int b(String str, Bitmap bitmap) {
                    int a2 = du.a(bitmap);
                    if (a2 == 0) {
                        return 1;
                    }
                    return a2;
                }
            };
        }
        if (aVar.h) {
            a();
        }
    }

    public void a() {
        synchronized (this.e) {
            if (this.b == null || this.b.d()) {
                File file = this.d.c;
                if (this.d.g && file != null) {
                    try {
                        if (file.exists()) {
                            b(file);
                        }
                        file.mkdir();
                    } catch (Throwable th) {
                    }
                    if (a(file) > this.d.b) {
                        this.b = hw.a(file, 1, 1, this.d.b);
                    }
                }
            }
            this.f = false;
            this.e.notifyAll();
        }
    }

    private void b(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                b(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    public void a(String str, Bitmap bitmap) {
        OutputStream outputStream;
        Throwable th;
        if (str != null && bitmap != null && !bitmap.isRecycled()) {
            if (this.c != null) {
                this.c.a(str, bitmap);
            }
            synchronized (this.e) {
                if (this.b != null) {
                    String c = c(str);
                    OutputStream outputStream2 = null;
                    try {
                        try {
                            hw.b a2 = this.b.a(c);
                            if (a2 == null) {
                                hw.a b = this.b.b(c);
                                if (b != null) {
                                    outputStream2 = b.a(0);
                                    try {
                                        bitmap.compress(this.d.d, this.d.e, outputStream2);
                                        b.a();
                                        outputStream2.close();
                                    } catch (Throwable th2) {
                                        outputStream = outputStream2;
                                        th = th2;
                                        if (outputStream != null) {
                                            try {
                                                outputStream.close();
                                            } catch (Throwable th3) {
                                            }
                                        }
                                        throw th;
                                    }
                                }
                            } else {
                                a2.a(0).close();
                            }
                            if (outputStream2 != null) {
                                try {
                                    outputStream2.close();
                                } catch (Throwable th4) {
                                }
                            }
                        } catch (Throwable th5) {
                            outputStream = null;
                            th = th5;
                        }
                    } catch (Throwable th6) {
                        if (0 != 0) {
                            try {
                                outputStream2.close();
                            } catch (Throwable th7) {
                            }
                        }
                    }
                }
            }
        }
    }

    public Bitmap a(String str) {
        Bitmap bitmap;
        WeakReference<Bitmap> weakReference;
        if (!dt.c() || this.g == null || (weakReference = this.g.get(str)) == null) {
            bitmap = null;
        } else {
            bitmap = weakReference.get();
            if (bitmap == null || bitmap.isRecycled()) {
                bitmap = null;
            }
            this.g.remove(str);
        }
        if (bitmap == null && this.c != null) {
            bitmap = this.c.a((dl<String, Bitmap>) str);
        }
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        return bitmap;
    }

    public Bitmap b(String str) {
        InputStream inputStream;
        Throwable th;
        Bitmap bitmap = null;
        String c = c(str);
        synchronized (this.e) {
            while (this.f) {
                try {
                    this.e.wait();
                } catch (Throwable th2) {
                }
            }
            if (this.b != null) {
                try {
                    hw.b a2 = this.b.a(c);
                    if (a2 != null) {
                        inputStream = a2.a(0);
                        if (inputStream != null) {
                            try {
                                bitmap = dw.a(((FileInputStream) inputStream).getFD(), Integer.MAX_VALUE, Integer.MAX_VALUE, this);
                            } catch (Throwable th3) {
                                th = th3;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Throwable th4) {
                                    }
                                }
                                throw th;
                            }
                        }
                    } else {
                        inputStream = null;
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th5) {
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    inputStream = null;
                }
            }
        }
        return bitmap;
    }

    public void b() {
        if (dt.c() && this.g != null) {
            this.g.clear();
        }
        if (this.c != null) {
            this.c.a();
        }
        synchronized (this.e) {
            this.f = true;
            if (this.b != null && !this.b.d()) {
                try {
                    this.b.f();
                } catch (Throwable th) {
                }
                this.b = null;
                a();
            }
        }
    }

    public void c() {
        synchronized (this.e) {
            if (this.b != null) {
                try {
                    this.b.e();
                } catch (Throwable th) {
                }
            }
        }
    }

    public void d() {
        if (dt.c() && this.g != null) {
            this.g.clear();
        }
        if (this.c != null) {
            this.c.a();
        }
        synchronized (this.e) {
            if (this.b != null) {
                try {
                    if (!this.b.d()) {
                        this.b.f();
                        this.b = null;
                    }
                } catch (Throwable th) {
                }
            }
        }
    }

    /* compiled from: ImageCache.java */
    /* loaded from: classes.dex */
    public static class a {
        public File c;
        public int a = 5242880;
        public long b = 10485760;
        public Bitmap.CompressFormat d = du.a;
        public int e = 100;
        public boolean f = true;
        public boolean g = true;
        public boolean h = false;

        public a(Context context, String str) {
            this.c = du.a(context, str);
        }

        public void a(int i) {
            this.a = i;
        }

        public void a(long j) {
            if (j <= 0) {
                this.g = false;
            }
            this.b = j;
        }

        public void a(String str) {
            this.c = new File(str);
        }

        public void a(boolean z) {
            this.f = z;
        }

        public void b(boolean z) {
            this.g = z;
        }
    }

    public static File a(Context context, String str) {
        String path;
        File a2 = a(context);
        if (("mounted".equals(Environment.getExternalStorageState()) || !e()) && a2 != null) {
            path = a2.getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + str);
    }

    public static String c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("utf-8"));
            return a(instance.digest());
        } catch (Throwable th) {
            return String.valueOf(str.hashCode());
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static int a(Bitmap bitmap) {
        return dt.d() ? bitmap.getByteCount() : bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static boolean e() {
        if (dt.b()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    public static File a(Context context) {
        File file;
        try {
            if (dt.a()) {
                file = context.getExternalCacheDir();
            } else {
                file = new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
            }
            return file;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static long a(File file) {
        if (dt.b()) {
            return file.getUsableSpace();
        }
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }
}
