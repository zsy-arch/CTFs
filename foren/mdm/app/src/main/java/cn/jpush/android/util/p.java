package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class p {
    public static final String a;
    private static final String[] z;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r5 != 0) goto L_0x002b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5 > r6) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            case 5: goto L_0x006b;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "?\u0019!I(?\u0011'\u0012(";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "?\u0011'";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "t9;Lz4\u00111\u0007l:\f4\u0007";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0005#e\u00051\u0006\u0003fU,";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "tI";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "tJ";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.p.z = r3;
        r6 = new java.lang.StringBuilder().append(java.io.File.separator);
        r0 = ")\u00116@".toCharArray();
        r1 = r0.length;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0083, code lost:
        if (r1 > 1) goto L_0x00bd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0085, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008a, code lost:
        r7 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008e, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x00af;
            case 1: goto L_0x00b2;
            case 2: goto L_0x00b5;
            case 3: goto L_0x00b8;
            default: goto L_0x0091;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0091, code lost:
        r5 = '\b';
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0093, code lost:
        r1[r2] = (char) (r5 ^ r7);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0099, code lost:
        if (r1 != 0) goto L_0x00bb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009b, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x009f, code lost:
        r9 = '[';
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a3, code lost:
        r9 = 'x';
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a7, code lost:
        r9 = 'U';
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ab, code lost:
        r9 = '(';
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00af, code lost:
        r5 = '[';
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b2, code lost:
        r5 = 'x';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b5, code lost:
        r5 = 'U';
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b8, code lost:
        r5 = '(';
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00bb, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00bd, code lost:
        if (r1 > r2) goto L_0x0085;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00bf, code lost:
        cn.jpush.android.util.p.a = r6.append(new java.lang.String(r0).intern()).append(java.io.File.separator).toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d8, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r5 <= 1) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x009f;
            case 1: goto L_0x00a3;
            case 2: goto L_0x00a7;
            case 3: goto L_0x00ab;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = '\b';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.p.<clinit>():void");
    }

    public static String a(Context context) {
        if (!b.a()) {
            return "";
        }
        String str = a(Environment.getExternalStorageDirectory().getAbsolutePath()) + e(context) + z[5];
        if (!new File(str).isDirectory()) {
            d(context);
        }
        return str + File.separator;
    }

    public static String a(Context context, String str) {
        String str2 = context.getFilesDir() + "/" + str;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 + "/";
    }

    private static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.lastIndexOf(File.separator) != 0 ? str + File.separator : str;
    }

    private static boolean a(File file) {
        try {
            if (!file.exists()) {
                return false;
            }
            if (file.isFile()) {
                return file.delete();
            }
            for (String str : file.list()) {
                File file2 = new File(file, str);
                if (file2.isDirectory()) {
                    a(file2);
                } else {
                    file2.delete();
                }
            }
            return file.delete();
        } catch (Exception e) {
            ac.e();
            return false;
        }
    }

    public static String b(Context context) {
        if (!b.a()) {
            return "";
        }
        String str = a(Environment.getExternalStorageDirectory().getAbsolutePath()) + e(context) + z[6];
        if (!new File(str).isDirectory()) {
            d(context);
        }
        return str + "/";
    }

    public static String b(Context context, String str) {
        try {
            if (b.a()) {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + z[3] + context.getPackageName() + File.separator + str + File.separator;
                File file = new File(str2);
                if (file.exists()) {
                    return str2;
                }
                file.mkdirs();
                return str2;
            }
            File file2 = new File(context.getFilesDir() + a);
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles.length > 10) {
                    Arrays.sort(listFiles, new q());
                    a(listFiles[listFiles.length - 1]);
                }
            }
            return c(context, str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String c(Context context, String str) {
        String str2 = context.getFilesDir() + a + str;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 + "/";
    }

    public static void c(Context context) {
        try {
            File[] listFiles = context.getFilesDir().listFiles();
            for (File file : listFiles) {
                String name = file.getName();
                if (TextUtils.isEmpty(name) ? false : Pattern.compile(z[4]).matcher(name).matches()) {
                    r.a(file.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void d(Context context) {
        try {
            if (b.a()) {
                String e = e(context);
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(absolutePath + e);
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                File file2 = new File(absolutePath + e + z[5]);
                if (!file2.isDirectory()) {
                    file2.mkdirs();
                }
                File file3 = new File(absolutePath + e + z[6]);
                if (!file3.isDirectory()) {
                    file3.mkdirs();
                }
                File file4 = new File(absolutePath + e + File.separator + context.getPackageName());
                if (!file4.isDirectory()) {
                    file4.mkdirs();
                    return;
                }
                return;
            }
            ac.e();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static String e(Context context) {
        Exception e;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string = defaultSharedPreferences.getString(z[2], "");
        if (TextUtils.isEmpty(string)) {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String str = z[3];
            String str2 = absolutePath + str;
            String str3 = null;
            File file = new File(str2);
            try {
                if (file.exists()) {
                    ArrayList arrayList = new ArrayList();
                    File[] listFiles = file.listFiles();
                    for (File file2 : listFiles) {
                        if (file2.isDirectory()) {
                            arrayList.add(file2.getName());
                            new StringBuilder(z[1]).append(file2.getName());
                            ac.a();
                        }
                    }
                    int size = arrayList.size();
                    if (size > 0) {
                        try {
                            str3 = str + ((String) arrayList.get(size / 2));
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            new StringBuilder(z[0]).append(str3);
                            ac.c();
                            defaultSharedPreferences.edit().putString(z[2], str3).commit();
                            return string;
                        }
                    } else {
                        str3 = str + UUID.randomUUID().toString().substring(0, 5);
                    }
                } else {
                    file.mkdirs();
                    str3 = str + UUID.randomUUID().toString().substring(0, 5);
                }
            } catch (Exception e3) {
                e = e3;
            }
            new StringBuilder(z[0]).append(str3);
            ac.c();
            defaultSharedPreferences.edit().putString(z[2], str3).commit();
        }
        return string;
    }
}
