package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.a.cn;
import dalvik.system.DexFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexFileManager.java */
/* loaded from: classes.dex */
public class cj {

    /* compiled from: DexFileManager.java */
    /* loaded from: classes.dex */
    public static class a {
        public static void a(bq bqVar, cn cnVar, String str) {
            bqVar.a(cnVar, str);
        }

        public static cn a(bq bqVar, String str) {
            List b = bqVar.b(cn.b(str), cn.class);
            if (b == null || b.size() <= 0) {
                return null;
            }
            return (cn) b.get(0);
        }

        public static List<cn> a(bq bqVar, String str, String str2) {
            return bqVar.b(cn.b(str, str2), cn.class);
        }
    }

    public static String a(String str) {
        return str + ".o";
    }

    public static String a(Context context, String str, String str2) {
        return bc.b(str + str2 + ba.q(context)) + ".jar";
    }

    public static String b(Context context, String str, String str2) {
        return a(context, a(context, str, str2));
    }

    public static String a(Context context, String str) {
        return a(context) + File.separator + str;
    }

    public static String a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "pngex";
    }

    public static void a(Context context, be beVar) {
        try {
            String b = b(context, beVar.a(), beVar.b());
            if (!TextUtils.isEmpty(b)) {
                File file = new File(b);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    String a2 = a(context, a(file.getName()));
                    DexFile loadDex = DexFile.loadDex(b, a2, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        a(context, file, a2, beVar);
                    }
                } else if (parentFile != null && parentFile.exists()) {
                    c(context, beVar.a(), beVar.b());
                }
            }
        } catch (Throwable th) {
            co.a(th, "BaseClassLoader", "getInstanceByThread()");
        }
    }

    public static void b(Context context, String str) {
        bq bqVar = new bq(context, cm.c());
        List<cn> a2 = a.a(bqVar, str, "copy");
        co.a(a2);
        if (a2 != null && a2.size() > 1) {
            int size = a2.size();
            for (int i = 1; i < size; i++) {
                c(context, bqVar, a2.get(i).a());
            }
        }
    }

    public static void a(Context context, bq bqVar, String str) {
        c(context, bqVar, str);
        c(context, bqVar, a(str));
    }

    static void c(final Context context, final String str, final String str2) {
        bk.b().submit(new Runnable() { // from class: com.amap.api.services.a.cj.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    bq bqVar = new bq(context, cm.c());
                    List<cn> b = bqVar.b(cn.a(str), cn.class);
                    if (b != null && b.size() > 0) {
                        for (cn cnVar : b) {
                            if (!str2.equalsIgnoreCase(cnVar.d())) {
                                cj.c(context, bqVar, cnVar.a());
                            }
                        }
                    }
                } catch (Throwable th) {
                    co.a(th, "FileManager", "clearUnSuitableV");
                }
            }
        });
    }

    public static void a(bq bqVar, Context context, String str) {
        List<cn> a2 = a.a(bqVar, str, "used");
        if (a2 != null && a2.size() > 0) {
            for (cn cnVar : a2) {
                if (cnVar != null && cnVar.c().equals(str)) {
                    a(context, bqVar, cnVar.a());
                    List b = bqVar.b(cn.a(str, cnVar.e()), cn.class);
                    if (b != null && b.size() > 0) {
                        cn cnVar2 = (cn) b.get(0);
                        cnVar2.c("errorstatus");
                        a.a(bqVar, cnVar2, cn.b(cnVar2.a()));
                        File file = new File(a(context, cnVar2.a()));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    public static void a(Context context, bq bqVar, be beVar, String str, String str2) throws Throwable {
        FileInputStream fileInputStream;
        RandomAccessFile randomAccessFile;
        Throwable th;
        Throwable th2;
        File file;
        try {
            String a2 = beVar.a();
            String a3 = a(context, a2, beVar.b());
            a(context, bqVar, a3);
            fileInputStream = new FileInputStream(new File(str));
            try {
                fileInputStream.read(new byte[32]);
                file = new File(b(context, a2, beVar.b()));
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
                cn a4 = new cn.a(a3, bc.a(file.getAbsolutePath()), a2, beVar.b(), str2).a("used").a();
                a.a(bqVar, a4, cn.b(a4.a()));
                try {
                    co.a(fileInputStream);
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
                try {
                    co.a(randomAccessFile);
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
            } catch (Throwable th6) {
                th = th6;
                co.a(fileInputStream);
                co.a(randomAccessFile);
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            randomAccessFile = null;
            fileInputStream = null;
        }
    }

    public static String a(Context context, bq bqVar, be beVar) {
        List b = bqVar.b(cn.b(beVar.a(), "copy"), cn.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        co.a(b);
        for (int i = 0; i < b.size(); i++) {
            cn cnVar = (cn) b.get(i);
            if (co.a(context, bqVar, cnVar.a(), beVar)) {
                try {
                    a(context, bqVar, beVar, a(context, cnVar.a()), cnVar.e());
                    return cnVar.e();
                } catch (Throwable th) {
                    co.a(th, "FileManager", "loadAvailableD");
                }
            } else {
                c(context, bqVar, cnVar.a());
            }
        }
        return null;
    }

    public static void a(Context context, File file, be beVar) {
        File parentFile = file.getParentFile();
        if (!file.exists() && parentFile != null && parentFile.exists()) {
            c(context, beVar.a(), beVar.b());
        }
    }

    public static void c(Context context, bq bqVar, String str) {
        File file = new File(a(context, str));
        if (file.exists()) {
            file.delete();
        }
        bqVar.a(cn.b(str), cn.class);
    }

    private static void a(Context context, File file, String str, be beVar) {
        String str2 = null;
        bq bqVar = new bq(context, cm.c());
        cn a2 = a.a(bqVar, file.getName());
        if (a2 != null) {
            str2 = a2.e();
        }
        File file2 = new File(str);
        if (!TextUtils.isEmpty(str2) && file2.exists()) {
            String a3 = bc.a(str);
            String name = file2.getName();
            a.a(bqVar, new cn.a(name, a3, beVar.a(), beVar.b(), str2).a("useod").a(), cn.b(name));
        }
    }
}
