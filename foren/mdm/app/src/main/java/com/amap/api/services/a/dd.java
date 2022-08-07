package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: OfflineLocManager.java */
/* loaded from: classes.dex */
public class dd {
    public static void a(Context context) {
        try {
            if (e(context)) {
                a(context, System.currentTimeMillis());
                String b = b(context);
                if (!TextUtils.isEmpty(b)) {
                    ct.a().b(new bj(bf.c(bf.a(b)), "6"));
                }
            }
        } catch (Throwable th) {
            bh.a(th, "OfflineLocManager", "updateOfflineLocData");
        }
    }

    private static void a(Context context, long j) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Throwable th2;
        Throwable th3;
        FileOutputStream fileOutputStream2;
        try {
            fileOutputStream = null;
            try {
                File file = new File(bi.a(context, "f.log"));
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                fileOutputStream2 = new FileOutputStream(file);
            } catch (Throwable th4) {
                th3 = th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
        try {
            fileOutputStream2.write(bf.a(String.valueOf(j)));
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Throwable th6) {
                    th2 = th6;
                    th2.printStackTrace();
                }
            }
        } catch (Throwable th7) {
            th3 = th7;
            fileOutputStream = fileOutputStream2;
            bh.a(th3, "OfflineLocManager", "updateLogUpdateTime");
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th8) {
                    th2 = th8;
                    th2.printStackTrace();
                }
            }
        }
    }

    private static String a(String str) {
        cp cpVar;
        Throwable th;
        try {
            boolean z = true;
            StringBuilder sb = new StringBuilder();
            cpVar = null;
            try {
                cpVar = cp.a(new File(str), 1, 1, 204800L);
                File file = new File(str);
                if (file != null && file.exists()) {
                    String[] list = file.list();
                    for (String str2 : list) {
                        if (str2.contains(".0")) {
                            String a = bf.a(dg.a(cpVar, str2.split("\\.")[0]));
                            if (z) {
                                z = false;
                            } else {
                                sb.append(",");
                            }
                            sb.append("{\"log\":\"").append(a).append("\"}");
                        }
                    }
                }
                if (cpVar != null) {
                    try {
                        cpVar.close();
                    } catch (Throwable th2) {
                        th = th2;
                        th.printStackTrace();
                        return sb.toString();
                    }
                }
            } catch (IOException e) {
                bh.a(e, "StatisticsManager", "getContent");
                if (cpVar != null) {
                    try {
                        cpVar.close();
                    } catch (Throwable th3) {
                        th = th3;
                        th.printStackTrace();
                        return sb.toString();
                    }
                }
            }
            return sb.toString();
        } catch (Throwable th4) {
            if (cpVar != null) {
                try {
                    cpVar.close();
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
            }
            throw th4;
        }
    }

    private static String b(Context context) {
        String a = a(bi.a(context, bi.f));
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        String f = f(context);
        StringBuilder sb = new StringBuilder();
        sb.append("{\"pinfo\":\"").append(f).append("\",\"els\":[");
        sb.append(a);
        sb.append("]}");
        return sb.toString();
    }

    private static int c(Context context) {
        try {
            File file = new File(bi.a(context, bi.f));
            if (!file.exists()) {
                return 0;
            }
            return file.list().length;
        } catch (Throwable th) {
            bh.a(th, "OfflineLocManager", "getFileNum");
            return 0;
        }
    }

    private static long d(Context context) {
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        FileInputStream fileInputStream2;
        File file = new File(bi.a(context, "f.log"));
        if (!file.exists()) {
            return 0L;
        }
        try {
            fileInputStream = null;
            try {
                fileInputStream2 = new FileInputStream(file);
            } catch (Throwable th3) {
                th2 = th3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        try {
            byte[] bArr = new byte[fileInputStream2.available()];
            fileInputStream2.read(bArr);
            long parseLong = Long.parseLong(bf.a(bArr));
            if (fileInputStream2 == null) {
                return parseLong;
            }
            try {
                fileInputStream2.close();
                return parseLong;
            } catch (Throwable th5) {
                th5.printStackTrace();
                return parseLong;
            }
        } catch (Throwable th6) {
            th = th6;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Throwable th7) {
                    th7.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static boolean e(Context context) {
        try {
            if (ba.m(context) == 1) {
                if (System.currentTimeMillis() - d(context) > 604800000) {
                    return true;
                }
                return c(context) >= 100;
            }
        } catch (Throwable th) {
            bh.a(th, "StatisticsManager", "isUpdate");
        }
        return false;
    }

    private static String f(Context context) {
        return az.b(context, bf.a(g(context)));
    }

    private static String g(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"").append(aw.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(ba.q(context)).append("\",\"mac\":\"").append(ba.i(context)).append("\",\"tid\":\"").append(ba.f(context)).append("\",\"manufacture\":\"").append(Build.MANUFACTURER).append("\",\"device\":\"").append(Build.DEVICE).append("\",\"sim\":\"").append(ba.r(context)).append("\",\"pkg\":\"").append(aw.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appversion\":\"").append(aw.d(context)).append("\"");
        } catch (Throwable th) {
            bh.a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }
}
