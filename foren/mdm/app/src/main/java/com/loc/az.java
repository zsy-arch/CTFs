package com.loc;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DynamicClassLoader.java */
/* loaded from: classes2.dex */
public final class az extends av {
    private PublicKey g = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public az(final Context context, s sVar) throws Exception {
        super(context, sVar);
        final String b = ay.b(context, sVar.a(), sVar.b());
        final String a = ay.a(context);
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(a)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
        String str = a + File.separator + ay.a(new File(b).getName());
        try {
            if (this.c == null) {
                b();
                this.c = DexFile.loadDex(b, str, 0);
            }
            z.b().submit(new Runnable() { // from class: com.loc.az.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        az.this.a(context, b, a);
                    } catch (Throwable th) {
                        w.a(th, "dLoader", "run()");
                    }
                }
            });
        } catch (Throwable th) {
            w.a(th, "dLoader", "loadFile");
            throw new Exception("load file fail");
        }
    }

    private static void a(JarFile jarFile, JarEntry jarEntry) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = jarFile.getInputStream(jarEntry);
            do {
            } while (inputStream.read(new byte[8192]) > 0);
        } finally {
            try {
                bd.a(inputStream);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean a(File file) {
        Throwable th;
        boolean z;
        JarFile jarFile;
        Throwable th2;
        JarFile jarFile2;
        try {
            z = false;
            jarFile = null;
            try {
                if (this.g == null) {
                    this.g = bd.a();
                }
                jarFile2 = new JarFile(file);
            } catch (Throwable th3) {
                th2 = th3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        try {
            JarEntry jarEntry = jarFile2.getJarEntry("classes.dex");
            if (jarEntry == null) {
                try {
                    jarFile2.close();
                } catch (Throwable th5) {
                }
            } else {
                a(jarFile2, jarEntry);
                Certificate[] certificates = jarEntry.getCertificates();
                if (certificates == null) {
                    try {
                        jarFile2.close();
                    } catch (Throwable th6) {
                    }
                } else {
                    z = a(certificates);
                    try {
                        jarFile2.close();
                    } catch (Throwable th7) {
                    }
                }
            }
        } catch (Throwable th8) {
            th2 = th8;
            jarFile = jarFile2;
            w.a(th2, "DyLoader", "verify");
            if (jarFile != null) {
                try {
                    jarFile.close();
                } catch (Throwable th9) {
                }
            }
            return z;
        }
        return z;
    }

    private boolean a(Certificate[] certificateArr) {
        int length;
        try {
            if (certificateArr.length > 0 && certificateArr.length - 1 >= 0) {
                certificateArr[length].verify(this.g);
                return true;
            }
        } catch (Exception e) {
            w.a(e, "DyLoader", "check");
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00c2 A[Catch: Throwable -> 0x0102, TRY_LEAVE, TryCatch #0 {Throwable -> 0x0102, blocks: (B:3:0x0009, B:5:0x0021, B:6:0x0027, B:8:0x0038, B:10:0x004c, B:12:0x0066, B:13:0x006f, B:17:0x0078, B:19:0x00a2, B:21:0x00ba, B:24:0x00c2, B:26:0x00d2, B:28:0x00da), top: B:32:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void a(android.content.Context r9, java.lang.String r10, java.lang.String r11) throws java.lang.Exception {
        /*
            r8 = this;
            r0 = 0
            java.util.Date r1 = new java.util.Date
            r1.<init>()
            r1.getTime()
            com.loc.af r6 = new com.loc.af     // Catch: Throwable -> 0x0102
            com.loc.bb r1 = com.loc.bb.b()     // Catch: Throwable -> 0x0102
            r6.<init>(r9, r1)     // Catch: Throwable -> 0x0102
            java.io.File r2 = new java.io.File     // Catch: Throwable -> 0x0102
            r2.<init>(r10)     // Catch: Throwable -> 0x0102
            java.lang.String r1 = r2.getName()     // Catch: Throwable -> 0x0102
            com.loc.bc r1 = com.loc.ay.a.a(r6, r1)     // Catch: Throwable -> 0x0102
            if (r1 == 0) goto L_0x0027
            java.lang.String r1 = r1.e()     // Catch: Throwable -> 0x0102
            r8.f = r1     // Catch: Throwable -> 0x0102
        L_0x0027:
            com.loc.s r1 = r8.e     // Catch: Throwable -> 0x0102
            java.lang.String r3 = r2.getAbsolutePath()     // Catch: Throwable -> 0x0102
            java.io.File r4 = new java.io.File     // Catch: Throwable -> 0x0102
            r4.<init>(r3)     // Catch: Throwable -> 0x0102
            boolean r4 = r8.a(r4)     // Catch: Throwable -> 0x0102
            if (r4 == 0) goto L_0x0076
            android.content.Context r4 = r8.a     // Catch: Throwable -> 0x0102
            java.lang.String r5 = r1.a()     // Catch: Throwable -> 0x0102
            java.lang.String r7 = r1.b()     // Catch: Throwable -> 0x0102
            java.lang.String r4 = com.loc.ay.a(r4, r5, r7)     // Catch: Throwable -> 0x0102
            boolean r1 = com.loc.bd.a(r6, r4, r3, r1)     // Catch: Throwable -> 0x0102
        L_0x004a:
            if (r1 != 0) goto L_0x006f
            r1 = 0
            r8.d = r1     // Catch: Throwable -> 0x0102
            android.content.Context r1 = r8.a     // Catch: Throwable -> 0x0102
            java.lang.String r3 = r2.getName()     // Catch: Throwable -> 0x0102
            com.loc.ay.a(r1, r6, r3)     // Catch: Throwable -> 0x0102
            android.content.Context r1 = r8.a     // Catch: Throwable -> 0x0102
            com.loc.s r3 = r8.e     // Catch: Throwable -> 0x0102
            java.lang.String r1 = com.loc.ay.a(r1, r6, r3)     // Catch: Throwable -> 0x0102
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: Throwable -> 0x0102
            if (r3 != 0) goto L_0x006f
            r8.f = r1     // Catch: Throwable -> 0x0102
            android.content.Context r1 = r8.a     // Catch: Throwable -> 0x0102
            com.loc.s r3 = r8.e     // Catch: Throwable -> 0x0102
            com.loc.ay.a(r1, r3)     // Catch: Throwable -> 0x0102
        L_0x006f:
            boolean r1 = r2.exists()     // Catch: Throwable -> 0x0102
            if (r1 != 0) goto L_0x0078
        L_0x0075:
            return
        L_0x0076:
            r1 = r0
            goto L_0x004a
        L_0x0078:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0102
            r1.<init>()     // Catch: Throwable -> 0x0102
            java.lang.StringBuilder r1 = r1.append(r11)     // Catch: Throwable -> 0x0102
            java.lang.String r3 = java.io.File.separator     // Catch: Throwable -> 0x0102
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch: Throwable -> 0x0102
            java.lang.String r3 = r2.getName()     // Catch: Throwable -> 0x0102
            java.lang.String r3 = com.loc.ay.a(r3)     // Catch: Throwable -> 0x0102
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch: Throwable -> 0x0102
            java.lang.String r1 = r1.toString()     // Catch: Throwable -> 0x0102
            java.io.File r3 = new java.io.File     // Catch: Throwable -> 0x0102
            r3.<init>(r1)     // Catch: Throwable -> 0x0102
            boolean r1 = r3.exists()     // Catch: Throwable -> 0x0102
            if (r1 == 0) goto L_0x00c9
            java.lang.String r1 = r2.getName()     // Catch: Throwable -> 0x0102
            java.lang.String r1 = com.loc.ay.a(r1)     // Catch: Throwable -> 0x0102
            java.lang.String r5 = r8.f     // Catch: Throwable -> 0x0102
            android.content.Context r2 = r8.a     // Catch: Throwable -> 0x0102
            java.lang.String r2 = com.loc.ay.a(r2, r1)     // Catch: Throwable -> 0x0102
            com.loc.s r3 = r8.e     // Catch: Throwable -> 0x0102
            boolean r3 = com.loc.bd.a(r6, r1, r2, r3)     // Catch: Throwable -> 0x0102
            if (r3 != 0) goto L_0x0100
            com.loc.bc r3 = com.loc.ay.a.a(r6, r1)     // Catch: Throwable -> 0x0102
            if (r3 == 0) goto L_0x00d2
        L_0x00c0:
            if (r0 != 0) goto L_0x00c9
            android.content.Context r0 = r8.a     // Catch: Throwable -> 0x0102
            com.loc.s r1 = r8.e     // Catch: Throwable -> 0x0102
            com.loc.ay.a(r0, r1)     // Catch: Throwable -> 0x0102
        L_0x00c9:
            java.util.Date r0 = new java.util.Date
            r0.<init>()
            r0.getTime()
            goto L_0x0075
        L_0x00d2:
            java.lang.String r0 = r8.f     // Catch: Throwable -> 0x0102
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: Throwable -> 0x0102
            if (r0 != 0) goto L_0x0100
            com.loc.bc$a r0 = new com.loc.bc$a     // Catch: Throwable -> 0x0102
            java.lang.String r2 = com.loc.p.a(r2)     // Catch: Throwable -> 0x0102
            com.loc.s r3 = r8.e     // Catch: Throwable -> 0x0102
            java.lang.String r3 = r3.a()     // Catch: Throwable -> 0x0102
            com.loc.s r4 = r8.e     // Catch: Throwable -> 0x0102
            java.lang.String r4 = r4.b()     // Catch: Throwable -> 0x0102
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: Throwable -> 0x0102
            java.lang.String r2 = "useod"
            com.loc.bc$a r0 = r0.a(r2)     // Catch: Throwable -> 0x0102
            com.loc.bc r0 = r0.a()     // Catch: Throwable -> 0x0102
            java.lang.String r1 = com.loc.bc.b(r1)     // Catch: Throwable -> 0x0102
            r6.a(r0, r1)     // Catch: Throwable -> 0x0102
        L_0x0100:
            r0 = 1
            goto L_0x00c0
        L_0x0102:
            r0 = move-exception
            java.lang.String r1 = "dLoader"
            java.lang.String r2 = "verifyD()"
            com.loc.w.a(r0, r1, r2)
            goto L_0x00c9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.az.a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    @Override // java.lang.ClassLoader
    protected final Class<?> findClass(String str) throws ClassNotFoundException {
        Throwable th;
        Class cls;
        try {
            if (this.c == null) {
                throw new ClassNotFoundException(str);
            }
            synchronized (this.b) {
                try {
                    cls = (Class) this.b.get(str);
                } catch (Throwable th2) {
                    th = th2;
                }
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
                } catch (Throwable th3) {
                    th = th3;
                    throw th;
                }
            }
        } catch (Throwable th4) {
            w.a(th4, "dLoader", "findCl");
            throw new ClassNotFoundException(str);
        }
    }
}
