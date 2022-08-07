package com.ta.utdid2.android.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import java.io.File;

/* loaded from: classes2.dex */
public class SystemUtils {
    /* JADX WARN: Removed duplicated region for block: B:16:0x0049 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getCpuInfo() {
        /*
            r7 = 0
            r4 = 0
            r1 = 0
            java.io.FileReader r5 = new java.io.FileReader     // Catch: FileNotFoundException -> 0x003e
            java.lang.String r8 = "/proc/cpuinfo"
            r5.<init>(r8)     // Catch: FileNotFoundException -> 0x003e
            if (r5 == 0) goto L_0x0056
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: IOException -> 0x0032, FileNotFoundException -> 0x004c
            r8 = 1024(0x400, float:1.435E-42)
            r2.<init>(r5, r8)     // Catch: IOException -> 0x0032, FileNotFoundException -> 0x004c
            java.lang.String r7 = r2.readLine()     // Catch: IOException -> 0x0053, FileNotFoundException -> 0x004f
            r2.close()     // Catch: IOException -> 0x0053, FileNotFoundException -> 0x004f
            r5.close()     // Catch: IOException -> 0x0053, FileNotFoundException -> 0x004f
            r1 = r2
            r4 = r5
        L_0x001f:
            if (r7 == 0) goto L_0x0049
            r8 = 58
            int r8 = r7.indexOf(r8)
            int r0 = r8 + 1
            java.lang.String r7 = r7.substring(r0)
            java.lang.String r8 = r7.trim()
        L_0x0031:
            return r8
        L_0x0032:
            r6 = move-exception
        L_0x0033:
            java.lang.String r8 = "Could not read from file /proc/cpuinfo"
            java.lang.String r9 = r6.toString()     // Catch: FileNotFoundException -> 0x004c
            android.util.Log.e(r8, r9)     // Catch: FileNotFoundException -> 0x004c
            r4 = r5
            goto L_0x001f
        L_0x003e:
            r3 = move-exception
        L_0x003f:
            java.lang.String r8 = "BaseParameter-Could not open file /proc/cpuinfo"
            java.lang.String r9 = r3.toString()
            android.util.Log.e(r8, r9)
            goto L_0x001f
        L_0x0049:
            java.lang.String r8 = ""
            goto L_0x0031
        L_0x004c:
            r3 = move-exception
            r4 = r5
            goto L_0x003f
        L_0x004f:
            r3 = move-exception
            r1 = r2
            r4 = r5
            goto L_0x003f
        L_0x0053:
            r6 = move-exception
            r1 = r2
            goto L_0x0033
        L_0x0056:
            r4 = r5
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.android.utils.SystemUtils.getCpuInfo():java.lang.String");
    }

    public static int getSystemVersion() {
        try {
            return Build.VERSION.class.getField("SDK_INT").getInt(null);
        } catch (Exception e) {
            try {
                return Integer.parseInt((String) Build.VERSION.class.getField("SDK").get(null));
            } catch (Exception e2) {
                e2.printStackTrace();
                return 2;
            }
        }
    }

    public static File getRootFolder(String folderName) {
        File sdCardFile = Environment.getExternalStorageDirectory();
        if (sdCardFile == null) {
            return null;
        }
        File rootFolder = new File(String.format("%s%s%s", sdCardFile.getAbsolutePath(), File.separator, folderName));
        if (rootFolder == null || rootFolder.exists()) {
            return rootFolder;
        }
        rootFolder.mkdirs();
        return rootFolder;
    }

    public static String getAppLabel(Context pContext) {
        try {
            PackageManager lPM = pContext.getPackageManager();
            String lPackageName = pContext.getPackageName();
            if (!(lPM == null || lPackageName == null)) {
                return lPM.getApplicationLabel(lPM.getPackageInfo(lPackageName, 1).applicationInfo).toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
