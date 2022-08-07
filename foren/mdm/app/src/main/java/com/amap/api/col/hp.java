package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.col.ht;
import dalvik.system.DexFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexFileManager.java */
/* loaded from: classes.dex */
public class hp {

    /* compiled from: DexFileManager.java */
    /* loaded from: classes.dex */
    public static class a {
        public static void a(gx gxVar, ht htVar, String str) {
            gxVar.a(htVar, str);
        }

        public static ht a(gx gxVar, String str) {
            List b = gxVar.b(ht.b(str), ht.class);
            if (b == null || b.size() <= 0) {
                return null;
            }
            return (ht) b.get(0);
        }

        public static List<ht> a(gx gxVar, String str, String str2) {
            return gxVar.b(ht.b(str, str2), ht.class);
        }
    }

    public static String a(String str) {
        return str + ".o";
    }

    public static String a(Context context, String str, String str2) {
        return gg.b(str + str2 + ge.q(context)) + ".jar";
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

    public static void a(Context context, gj gjVar) {
        try {
            String b = b(context, gjVar.a(), gjVar.b());
            if (!TextUtils.isEmpty(b)) {
                File file = new File(b);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    String a2 = a(context, a(file.getName()));
                    DexFile loadDex = DexFile.loadDex(b, a2, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        a(context, file, a2, gjVar);
                    }
                } else if (parentFile != null && parentFile.exists()) {
                    c(context, gjVar.a(), gjVar.b());
                }
            }
        } catch (Throwable th) {
            hv.a(th, "BaseLoader", "getInstanceByThread()");
        }
    }

    public static void b(Context context, String str) {
        gx gxVar = new gx(context, hr.a());
        List<ht> a2 = a.a(gxVar, str, "copy");
        hv.a(a2);
        if (a2 != null && a2.size() > 1) {
            int size = a2.size();
            for (int i = 1; i < size; i++) {
                c(context, gxVar, a2.get(i).a());
            }
        }
    }

    public static void a(Context context, gx gxVar, String str) {
        c(context, gxVar, str);
        c(context, gxVar, a(str));
    }

    static void c(final Context context, final String str, final String str2) {
        gr.c().submit(new Runnable() { // from class: com.amap.api.col.hp.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    gx gxVar = new gx(context, hr.a());
                    List<ht> b = gxVar.b(ht.a(str), ht.class);
                    if (b != null && b.size() > 0) {
                        for (ht htVar : b) {
                            if (!str2.equalsIgnoreCase(htVar.d())) {
                                hp.c(context, gxVar, htVar.a());
                            }
                        }
                    }
                } catch (Throwable th) {
                    hv.a(th, "FileManager", "clearUnSuitableV");
                }
            }
        });
    }

    public static void a(gx gxVar, Context context, String str) {
        List<ht> a2 = a.a(gxVar, str, "used");
        if (a2 != null && a2.size() > 0) {
            for (ht htVar : a2) {
                if (htVar != null && htVar.c().equals(str)) {
                    a(context, gxVar, htVar.a());
                    List b = gxVar.b(ht.a(str, htVar.e()), ht.class);
                    if (b != null && b.size() > 0) {
                        ht htVar2 = (ht) b.get(0);
                        htVar2.c("errorstatus");
                        a.a(gxVar, htVar2, ht.b(htVar2.a()));
                        File file = new File(a(context, htVar2.a()));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    public static void a(Context context, gx gxVar, gj gjVar, String str, String str2) throws Throwable {
        FileInputStream fileInputStream;
        RandomAccessFile randomAccessFile;
        Throwable th;
        Throwable th2;
        File file;
        try {
            String a2 = gjVar.a();
            String a3 = a(context, a2, gjVar.b());
            a(context, gxVar, a3);
            fileInputStream = new FileInputStream(new File(str));
            try {
                fileInputStream.read(new byte[32]);
                file = new File(b(context, a2, gjVar.b()));
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
                ht a4 = new ht.a(a3, gg.a(file.getAbsolutePath()), a2, gjVar.b(), str2).a("used").a();
                a.a(gxVar, a4, ht.b(a4.a()));
                try {
                    hv.a(fileInputStream);
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
                try {
                    hv.a(randomAccessFile);
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
            } catch (Throwable th6) {
                th = th6;
                hv.a(fileInputStream);
                hv.a(randomAccessFile);
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            randomAccessFile = null;
            fileInputStream = null;
        }
    }

    public static String a(Context context, gx gxVar, gj gjVar) {
        List b = gxVar.b(ht.b(gjVar.a(), "copy"), ht.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        hv.a(b);
        for (int i = 0; i < b.size(); i++) {
            ht htVar = (ht) b.get(i);
            if (hv.a(context, gxVar, htVar.a(), gjVar)) {
                try {
                    a(context, gxVar, gjVar, a(context, htVar.a()), htVar.e());
                    return htVar.e();
                } catch (Throwable th) {
                    hv.a(th, "FileManager", "loadAvailableD");
                }
            } else {
                c(context, gxVar, htVar.a());
            }
        }
        return null;
    }

    public static void a(Context context, File file, gj gjVar) {
        File parentFile = file.getParentFile();
        if (!file.exists() && parentFile != null && parentFile.exists()) {
            c(context, gjVar.a(), gjVar.b());
        }
    }

    public static void c(Context context, gx gxVar, String str) {
        File file = new File(a(context, str));
        if (file.exists()) {
            file.delete();
        }
        gxVar.a(ht.b(str), ht.class);
    }

    private static void a(Context context, File file, String str, gj gjVar) {
        String str2 = null;
        gx gxVar = new gx(context, hr.a());
        ht a2 = a.a(gxVar, file.getName());
        if (a2 != null) {
            str2 = a2.e();
        }
        File file2 = new File(str);
        if (!TextUtils.isEmpty(str2) && file2.exists()) {
            String a3 = gg.a(str);
            String name = file2.getName();
            a.a(gxVar, new ht.a(name, a3, gjVar.a(), gjVar.b(), str2).a("useod").a(), ht.b(name));
        }
    }
}
