package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.col.hp;
import com.amap.api.col.ht;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* compiled from: DynamicLoader.java */
/* loaded from: classes.dex */
class hs extends hn {
    private PublicKey g = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public hs(Context context, gj gjVar, boolean z) throws Exception {
        super(context, gjVar, z);
        String b = hp.b(context, gjVar.a(), gjVar.b());
        String a = hp.a(context);
        b(b, a);
        File file = new File(b);
        if (z) {
            a(b, a + File.separator + hp.a(file.getName()));
            b(context, b, a);
        }
    }

    @Override // java.lang.ClassLoader
    protected Class<?> findClass(String str) throws ClassNotFoundException {
        Throwable th;
        try {
            try {
                if (this.c == null) {
                    throw new ClassNotFoundException(str);
                }
                Class cls = null;
                try {
                } catch (Throwable th2) {
                    hv.a(th2, "dLoader", "findCl");
                }
                synchronized (this.b) {
                    try {
                        cls = (Class) this.b.get(str);
                        try {
                            if (cls == null) {
                                cls = this.c.loadClass(str, this);
                                if (cls == null) {
                                    throw new ClassNotFoundException(str);
                                }
                                try {
                                    synchronized (this.b) {
                                        this.b.put(str, cls);
                                    }
                                } catch (Throwable th3) {
                                    hv.a(th3, "dLoader", "findCl");
                                }
                            }
                            return cls;
                        } catch (Throwable th4) {
                            cls = cls;
                            th = th4;
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                }
            } catch (Throwable th6) {
                hv.a(th6, "dLoader", "findCl");
                throw new ClassNotFoundException(str);
            }
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }

    void a(String str, String str2) throws Exception {
        try {
            if (this.c == null) {
                b();
                this.c = DexFile.loadDex(str, str2, 0);
            }
        } catch (Throwable th) {
            hv.a(th, "dLoader", "loadFile");
            throw new Exception("load file fail");
        }
    }

    private void c() {
        if (this.g == null) {
            this.g = hv.a();
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
                hv.a(inputStream);
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
            hv.a(e, "DyLoader", "check");
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
                hv.a(th, "DyLoader", "verify");
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

    private boolean a(gx gxVar, gj gjVar, String str) {
        if (a(new File(str))) {
            return hv.a(gxVar, hp.a(this.a, gjVar.a(), gjVar.b()), str, gjVar);
        }
        return false;
    }

    private boolean a(gx gxVar, String str, String str2) {
        String a = hp.a(this.a, str);
        if (hv.a(gxVar, str, a, this.e)) {
            return true;
        }
        if (hp.a.a(gxVar, str) != null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.f)) {
            hp.a.a(gxVar, new ht.a(str, gg.a(a), this.e.a(), this.e.b(), str2).a("useod").a(), ht.b(str));
        }
        return true;
    }

    private void b(String str, String str2) throws Exception {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
    }

    private void b(final Context context, final String str, final String str2) {
        gr.c().submit(new Runnable() { // from class: com.amap.api.col.hs.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    hs.this.a(context, str, str2);
                } catch (Throwable th) {
                    hv.a(th, "dLoader", "run()");
                }
            }
        });
    }

    private void a(gx gxVar, File file) {
        ht a = hp.a.a(gxVar, file.getName());
        if (a != null) {
            this.f = a.e();
        }
    }

    private void b(gx gxVar, File file) {
        this.d = false;
        hp.a(this.a, gxVar, file.getName());
        String a = hp.a(this.a, gxVar, this.e);
        if (!TextUtils.isEmpty(a)) {
            this.f = a;
            hp.a(this.a, this.e);
        }
    }

    void a(Context context, String str, String str2) throws Exception {
        gx gxVar;
        File file;
        new Date().getTime();
        try {
            gxVar = new gx(context, hr.a());
            file = new File(str);
            a(gxVar, file);
            if (!a(gxVar, this.e, file.getAbsolutePath())) {
                b(gxVar, file);
            }
        } catch (Throwable th) {
            hv.a(th, "dLoader", "verifyD()");
        }
        if (file.exists()) {
            if (new File(str2 + File.separator + hp.a(file.getName())).exists() && !a(gxVar, hp.a(file.getName()), this.f)) {
                hp.a(this.a, this.e);
            }
            new Date().getTime();
        }
    }
}
