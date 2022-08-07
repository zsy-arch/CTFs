package cn.jpush.android.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: classes.dex */
public final class r {
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
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "f\u0011n34o";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u000b}\u001d'S";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "?J/c\u00040\u00138x\u000e?]>B\u001f3E\u001dc\u0007;\tv*\r7E>Z\n*Aa";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0010|\u0017FK=F5~\u000e&]";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "r\t8e\u0005*L5~Q";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.r.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0067, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0068, code lost:
        r9 = '^';
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006b, code lost:
        r9 = ')';
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006e, code lost:
        r9 = '[';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
        r9 = '\n';
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
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = 'k';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 6
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "\u0019ki9Zl"
            r0 = -1
            r4 = r3
        L_0x0008:
            char[] r1 = r1.toCharArray()
            int r5 = r1.length
            r6 = 0
            r7 = 1
            if (r5 > r7) goto L_0x002d
        L_0x0011:
            r7 = r1
            r8 = r6
            r11 = r5
            r5 = r1
            r1 = r11
        L_0x0016:
            char r10 = r5[r6]
            int r9 = r8 % 5
            switch(r9) {
                case 0: goto L_0x0068;
                case 1: goto L_0x006b;
                case 2: goto L_0x006e;
                case 3: goto L_0x0071;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 107(0x6b, float:1.5E-43)
        L_0x001f:
            r9 = r9 ^ r10
            char r9 = (char) r9
            r5[r6] = r9
            int r6 = r8 + 1
            if (r1 != 0) goto L_0x002b
            r5 = r7
            r8 = r6
            r6 = r1
            goto L_0x0016
        L_0x002b:
            r5 = r1
            r1 = r7
        L_0x002d:
            if (r5 > r6) goto L_0x0011
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            java.lang.String r1 = r5.intern()
            switch(r0) {
                case 0: goto L_0x0043;
                case 1: goto L_0x004b;
                case 2: goto L_0x0053;
                case 3: goto L_0x005b;
                case 4: goto L_0x0063;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "f\u0011n34o"
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "\u000b}\u001d'S"
            r0 = 1
            r3 = r4
            goto L_0x0008
        L_0x004b:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "?J/c\u00040\u00138x\u000e?]>B\u001f3E\u001dc\u0007;\tv*\r7E>Z\n*Aa"
            r0 = 2
            r3 = r4
            goto L_0x0008
        L_0x0053:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "\u0010|\u0017FK=F5~\u000e&]"
            r0 = 3
            r3 = r4
            goto L_0x0008
        L_0x005b:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "r\t8e\u0005*L5~Q"
            r0 = 4
            r3 = r4
            goto L_0x0008
        L_0x0063:
            r3[r2] = r1
            cn.jpush.android.util.r.z = r4
            return
        L_0x0068:
            r9 = 94
            goto L_0x001f
        L_0x006b:
            r9 = 41
            goto L_0x001f
        L_0x006e:
            r9 = 91
            goto L_0x001f
        L_0x0071:
            r9 = 10
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.r.<clinit>():void");
    }

    public static ArrayList<String> a(InputStream inputStream) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, z[2]);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 2048);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String trim = readLine.trim();
                if (!"".equals(trim)) {
                    arrayList.add(trim);
                }
            }
            inputStreamReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.getMessage();
            ac.e();
        }
        return arrayList;
    }

    private static void a(File file, ZipOutputStream zipOutputStream, String str) {
        String str2 = new String((str + (str.trim().length() == 0 ? "" : File.separator) + file.getName()).getBytes(z[1]), z[0]);
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                a(file2, zipOutputStream, str2);
            }
            return;
        }
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        zipOutputStream.putNextEntry(new ZipEntry(str2));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                zipOutputStream.write(bArr, 0, read);
            } else {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
        }
    }

    public static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File file2 : listFiles) {
                    a(file2.getAbsolutePath());
                    file2.delete();
                }
            }
            file.delete();
        }
    }

    public static void a(Collection<File> collection, File file) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File file2 : collection) {
            a(file2, zipOutputStream, "");
        }
        zipOutputStream.close();
    }

    public static boolean a(String str, String str2, Context context) {
        Throwable th;
        if (context == null) {
            throw new IllegalArgumentException(z[4]);
        }
        new StringBuilder(z[3]).append(str).append(z[5]).append(str2);
        ac.a();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(str2.getBytes(z[2]));
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        return true;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException e) {
                ac.g();
            }
        }
        return false;
    }

    public static boolean a(String str, byte[] bArr) {
        Throwable th;
        if (TextUtils.isEmpty(str) || bArr.length <= 0) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static String b(String str) {
        if (!ao.a(str)) {
            int lastIndexOf = str.lastIndexOf(".");
            int length = str.length();
            if (lastIndexOf > 0 && lastIndexOf + 1 != length) {
                return str.substring(lastIndexOf, str.length());
            }
        }
        return "";
    }

    public static String c(String str) {
        return ao.a(str) ? "" : str.substring(str.lastIndexOf("/") + 1, str.length());
    }
}
