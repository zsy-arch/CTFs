package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.a.cj;
import com.amap.api.services.a.cn;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DynamicClassLoader.java */
/* loaded from: classes.dex */
public class ck extends cg {
    private PublicKey g = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ck(Context context, be beVar, boolean z) throws Exception {
        super(context, beVar, z);
        String b = cj.b(context, beVar.a(), beVar.b());
        String a = cj.a(context);
        b(b, a);
        File file = new File(b);
        if (z) {
            a(b, a + File.separator + cj.a(file.getName()));
            b(context, b, a);
        }
    }

    @Override // java.lang.ClassLoader
    protected Class<?> findClass(String str) throws ClassNotFoundException {
        Throwable th;
        try {
            if (this.c == null) {
                throw new ClassNotFoundException(str);
            }
            try {
                synchronized (this.b) {
                    Class cls = (Class) this.b.get(str);
                    try {
                        if (cls == null) {
                            cls = this.c.loadClass(str, this);
                            if (cls == null) {
                                throw new ClassNotFoundException(str);
                            }
                            synchronized (this.b) {
                                this.b.put(str, cls);
                            }
                        }
                        return cls;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            co.a(th4, "dLoader", "findCl");
            throw new ClassNotFoundException(str);
        }
    }

    void a(String str, String str2) throws Exception {
        try {
            if (this.c == null) {
                b();
                this.c = DexFile.loadDex(str, str2, 0);
            }
        } catch (Throwable th) {
            co.a(th, "dLoader", "loadFile");
            throw new Exception("load file fail");
        }
    }

    private void c() {
        if (this.g == null) {
            this.g = co.a();
        }
    }

    private void a(JarFile jarFile, JarEntry jarEntry) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = jarFile.getInputStream(jarEntry);
            do {
            } while (inputStream.read(new byte[8192]) > 0);
        } finally {
            try {
                co.a(inputStream);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean a(File file, Certificate[] certificateArr) {
        int length;
        try {
            if (certificateArr.length > 0 && certificateArr.length - 1 >= 0) {
                certificateArr[length].verify(this.g);
                return true;
            }
        } catch (Exception e) {
            co.a(e, "DyLoader", "check");
        }
        return false;
    }

    private boolean a(File file) {
        JarFile jarFile;
        boolean z;
        Throwable th;
        try {
            z = false;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            c();
            jarFile = new JarFile(file);
            try {
                JarEntry jarEntry = jarFile.getJarEntry("classes.dex");
                if (jarEntry != null) {
                    a(jarFile, jarEntry);
                    Certificate[] certificates = jarEntry.getCertificates();
                    if (certificates != null) {
                        z = a(file, certificates);
                        if (jarFile != null) {
                            try {
                                jarFile.close();
                            } catch (Throwable th3) {
                            }
                        }
                    } else if (jarFile != null) {
                        try {
                            jarFile.close();
                        } catch (Throwable th4) {
                        }
                    }
                } else if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (Throwable th5) {
                    }
                }
            } catch (Throwable th6) {
                th = th6;
                co.a(th, "DyLoader", "verify");
                if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (Throwable th7) {
                    }
                }
                return z;
            }
        } catch (Throwable th8) {
            th = th8;
            jarFile = null;
        }
        return z;
    }

    private boolean a(bq bqVar, be beVar, String str) {
        if (a(new File(str))) {
            return co.a(bqVar, cj.a(this.a, beVar.a(), beVar.b()), str, beVar);
        }
        return false;
    }

    private boolean a(bq bqVar, String str, String str2) {
        String a = cj.a(this.a, str);
        if (co.a(bqVar, str, a, this.e)) {
            return true;
        }
        if (cj.a.a(bqVar, str) != null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.f)) {
            cj.a.a(bqVar, new cn.a(str, bc.a(a), this.e.a(), this.e.b(), str2).a("useod").a(), cn.b(str));
        }
        return true;
    }

    private void b(String str, String str2) throws Exception {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
    }

    private void b(final Context context, final String str, final String str2) {
        bk.b().submit(new Runnable() { // from class: com.amap.api.services.a.ck.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ck.this.a(context, str, str2);
                } catch (Throwable th) {
                    co.a(th, "dLoader", "run()");
                }
            }
        });
    }

    private void a(bq bqVar, File file) {
        cn a = cj.a.a(bqVar, file.getName());
        if (a != null) {
            this.f = a.e();
        }
    }

    private void b(bq bqVar, File file) {
        this.d = false;
        cj.a(this.a, bqVar, file.getName());
        String a = cj.a(this.a, bqVar, this.e);
        if (!TextUtils.isEmpty(a)) {
            this.f = a;
            cj.a(this.a, this.e);
        }
    }

    void a(Context context, String str, String str2) throws Exception {
        bq bqVar;
        File file;
        new Date().getTime();
        try {
            bqVar = new bq(context, cm.c());
            file = new File(str);
            a(bqVar, file);
            if (!a(bqVar, this.e, file.getAbsolutePath())) {
                b(bqVar, file);
            }
        } catch (Throwable th) {
            co.a(th, "dLoader", "verifyD()");
        }
        if (file.exists()) {
            if (new File(str2 + File.separator + cj.a(file.getName())).exists() && !a(bqVar, cj.a(file.getName()), this.f)) {
                cj.a(this.a, this.e);
            }
            new Date().getTime();
        }
    }
}
