package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.loc.bc;
import dalvik.system.DexFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexFileManager.java */
/* loaded from: classes2.dex */
public final class ay {

    /* compiled from: DexFileManager.java */
    /* loaded from: classes2.dex */
    public static class a {
        public static bc a(af afVar, String str) {
            List b = afVar.b(bc.b(str), bc.class);
            if (b == null || b.size() <= 0) {
                return null;
            }
            return (bc) b.get(0);
        }

        public static List<bc> a(af afVar, String str, String str2) {
            return afVar.b(bc.b(str, str2), bc.class);
        }
    }

    public static String a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "pngex";
    }

    public static String a(Context context, af afVar, s sVar) {
        List b = afVar.b(bc.b(sVar.a(), "copy"), bc.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        bd.a(b);
        for (int i = 0; i < b.size(); i++) {
            bc bcVar = (bc) b.get(i);
            String a2 = bcVar.a();
            if (bd.a(afVar, a2, a(context, a2), sVar)) {
                try {
                    a(context, afVar, sVar, a(context, bcVar.a()), bcVar.e());
                    return bcVar.e();
                } catch (Throwable th) {
                    w.a(th, "FileManager", "loadAvailableD");
                }
            } else {
                c(context, afVar, bcVar.a());
            }
        }
        return null;
    }

    public static String a(Context context, String str) {
        return a(context) + File.separator + str;
    }

    public static String a(Context context, String str, String str2) {
        return p.b(str + str2 + n.q(context)) + ".jar";
    }

    public static String a(String str) {
        return str + ".o";
    }

    public static void a(Context context, af afVar, s sVar, String str, String str2) throws Throwable {
        FileInputStream fileInputStream;
        RandomAccessFile randomAccessFile;
        Throwable th;
        Throwable th2;
        File file;
        try {
            String a2 = sVar.a();
            String a3 = a(context, a2, sVar.b());
            a(context, afVar, a3);
            fileInputStream = new FileInputStream(new File(str));
            try {
                fileInputStream.read(new byte[32]);
                file = new File(b(context, a2, sVar.b()));
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
            }
            try {
                byte[] bArr = new byte[1024];
                int i = 0;
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    if (read == 1024) {
                        randomAccessFile.seek(i);
                        randomAccessFile.write(bArr);
                    } else {
                        byte[] bArr2 = new byte[read];
                        System.arraycopy(bArr, 0, bArr2, 0, read);
                        randomAccessFile.seek(i);
                        randomAccessFile.write(bArr2);
                    }
                    i += read;
                }
                bc a4 = new bc.a(a3, p.a(file.getAbsolutePath()), a2, sVar.b(), str2).a("used").a();
                afVar.a(a4, bc.b(a4.a()));
                try {
                    bd.a(fileInputStream);
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
                try {
                    bd.a(randomAccessFile);
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
            } catch (Throwable th6) {
                th = th6;
                bd.a(fileInputStream);
                bd.a(randomAccessFile);
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            randomAccessFile = null;
            fileInputStream = null;
        }
    }

    public static void a(Context context, af afVar, String str) {
        c(context, afVar, str);
        c(context, afVar, a(str));
    }

    public static void a(Context context, s sVar) {
        try {
            String b = b(context, sVar.a(), sVar.b());
            if (!TextUtils.isEmpty(b)) {
                File file = new File(b);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    String a2 = a(context, a(file.getName()));
                    DexFile loadDex = DexFile.loadDex(b, a2, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        String str = null;
                        af afVar = new af(context, bb.b());
                        bc a3 = a.a(afVar, file.getName());
                        if (a3 != null) {
                            str = a3.e();
                        }
                        File file2 = new File(a2);
                        if (!TextUtils.isEmpty(str) && file2.exists()) {
                            String a4 = p.a(a2);
                            String name = file2.getName();
                            afVar.a(new bc.a(name, a4, sVar.a(), sVar.b(), str).a("useod").a(), bc.b(name));
                        }
                    }
                } else if (parentFile != null && parentFile.exists()) {
                    c(context, sVar.a(), sVar.b());
                }
            }
        } catch (Throwable th) {
            w.a(th, "BaseClassLoader", "getInstanceByThread()");
        }
    }

    public static void a(af afVar, Context context, String str) {
        List<bc> a2 = a.a(afVar, str, "used");
        if (a2 != null && a2.size() > 0) {
            for (bc bcVar : a2) {
                if (bcVar != null && bcVar.c().equals(str)) {
                    a(context, afVar, bcVar.a());
                    List b = afVar.b(bc.a(str, bcVar.e()), bc.class);
                    if (b != null && b.size() > 0) {
                        bc bcVar2 = (bc) b.get(0);
                        bcVar2.c("errorstatus");
                        afVar.a(bcVar2, bc.b(bcVar2.a()));
                        File file = new File(a(context, bcVar2.a()));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    public static String b(Context context, String str, String str2) {
        return a(context, a(context, str, str2));
    }

    public static void b(Context context, String str) {
        af afVar = new af(context, bb.b());
        List<bc> a2 = a.a(afVar, str, "copy");
        bd.a(a2);
        if (a2 != null && a2.size() > 1) {
            int size = a2.size();
            for (int i = 1; i < size; i++) {
                c(context, afVar, a2.get(i).a());
            }
        }
    }

    public static void c(Context context, af afVar, String str) {
        File file = new File(a(context, str));
        if (file.exists()) {
            file.delete();
        }
        afVar.a(bc.b(str), bc.class);
    }

    public static void c(final Context context, final String str, final String str2) {
        z.b().submit(new Runnable() { // from class: com.loc.ay.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    af afVar = new af(context, bb.b());
                    List<bc> b = afVar.b(bc.a(str), bc.class);
                    if (b != null && b.size() > 0) {
                        for (bc bcVar : b) {
                            if (!str2.equalsIgnoreCase(bcVar.d())) {
                                ay.c(context, afVar, bcVar.a());
                            }
                        }
                    }
                } catch (Throwable th) {
                    w.a(th, "FileManager", "clearUnSuitableV");
                }
            }
        });
    }
}
