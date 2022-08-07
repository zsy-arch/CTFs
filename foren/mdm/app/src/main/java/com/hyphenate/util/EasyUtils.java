package com.hyphenate.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/* loaded from: classes2.dex */
public class EasyUtils {
    public static final String TAG = "EasyUtils";
    private static Hashtable<String, String> resourceTable = new Hashtable<>();

    public static String convertByteArrayToString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("0x%02X", Byte.valueOf(bArr[i])));
        }
        return sb.toString();
    }

    public static boolean copyFile(String str, String str2) {
        try {
            if (!new File(str).exists()) {
                return false;
            }
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            int i = 0;
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    i += read;
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean copyFolder(String str, String str2) {
        try {
            new File(str2).mkdirs();
            String[] list = new File(str).list();
            for (String str3 : list) {
                File file = str.endsWith(File.separator) ? new File(str + str3) : new File(str + File.separator + str3);
                if (file.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(str2 + "/" + file.getName());
                    byte[] bArr = new byte[5120];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                }
                if (file.isDirectory()) {
                    copyFolder(str + "/" + str3, str2 + "/" + str3);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getAppResourceString(Context context, String str) {
        String str2 = resourceTable.get(str);
        if (str2 == null && (str2 = context.getString(context.getResources().getIdentifier(str, "string", context.getPackageName()))) != null) {
            resourceTable.put(str, str2);
        }
        return str2;
    }

    public static List<String> getRunningApps(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ArrayList arrayList = new ArrayList();
        try {
            runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (runningAppProcesses == null) {
            return arrayList;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            String str = runningAppProcessInfo.processName;
            if (str.contains(":")) {
                str = str.substring(0, str.indexOf(":"));
            }
            if (!arrayList.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
    }

    public static String getTopActivityName(Context context) {
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            return (runningTasks == null || runningTasks.size() < 1) ? "" : runningTasks.get(0).topActivity.getClassName();
        } catch (SecurityException e) {
            EMLog.d(TAG, "Apk doesn't hold GET_TASKS permission");
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isAppRunningForeground(Context context) {
        boolean z = true;
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks == null || runningTasks.size() < 1) {
                return false;
            }
            boolean equalsIgnoreCase = context.getPackageName().equalsIgnoreCase(runningTasks.get(0).baseActivity.getPackageName());
            StringBuilder append = new StringBuilder().append("app running in foregroud：");
            if (!equalsIgnoreCase) {
                z = false;
            }
            EMLog.d("utils", append.append(z).toString());
            return equalsIgnoreCase;
        } catch (SecurityException e) {
            EMLog.d(TAG, "Apk doesn't hold GET_TASKS permission");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean isSingleActivity(Context context) {
        List<ActivityManager.RunningTaskInfo> list = null;
        try {
            list = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if (list == null || list.size() < 1) {
            return false;
        }
        return list.get(0).numRunning == 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.OutputStream, java.io.FileOutputStream] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean writeToZipFile(byte[] r8, java.lang.String r9) {
        /*
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: Exception -> 0x0081, all -> 0x009c
            r3.<init>(r9)     // Catch: Exception -> 0x0081, all -> 0x009c
            java.util.zip.GZIPOutputStream r1 = new java.util.zip.GZIPOutputStream     // Catch: Exception -> 0x00bc, all -> 0x00b3
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch: Exception -> 0x00bc, all -> 0x00b3
            r0.<init>(r3)     // Catch: Exception -> 0x00bc, all -> 0x00b3
            r1.<init>(r0)     // Catch: Exception -> 0x00bc, all -> 0x00b3
            r1.write(r8)     // Catch: Exception -> 0x00c0, all -> 0x00b5
            if (r1 == 0) goto L_0x0018
            r1.close()     // Catch: IOException -> 0x0077
        L_0x0018:
            if (r3 == 0) goto L_0x001d
            r3.close()     // Catch: IOException -> 0x007c
        L_0x001d:
            boolean r0 = com.hyphenate.util.EMLog.debugMode
            if (r0 == 0) goto L_0x0075
            java.io.File r0 = new java.io.File
            r0.<init>(r9)
            java.text.DecimalFormat r1 = new java.text.DecimalFormat
            java.lang.String r2 = "#.##"
            r1.<init>(r2)
            long r2 = r0.length()
            double r2 = (double) r2
            int r4 = r8.length
            double r4 = (double) r4
            double r2 = r2 / r4
            r4 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r2 = r2 * r4
            java.lang.String r1 = r1.format(r2)
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            double r2 = r1.doubleValue()
            java.lang.String r1 = "zip"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "data size:"
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = r8.length
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " zip file size:"
            java.lang.StringBuilder r4 = r4.append(r5)
            long r6 = r0.length()
            java.lang.StringBuilder r0 = r4.append(r6)
            java.lang.String r4 = "zip file ratio%: "
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.hyphenate.util.EMLog.d(r1, r0)
        L_0x0075:
            r0 = 1
        L_0x0076:
            return r0
        L_0x0077:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0018
        L_0x007c:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001d
        L_0x0081:
            r0 = move-exception
            r1 = r2
        L_0x0083:
            r0.printStackTrace()     // Catch: all -> 0x00b8
            r0 = 0
            if (r1 == 0) goto L_0x008c
            r1.close()     // Catch: IOException -> 0x0097
        L_0x008c:
            if (r2 == 0) goto L_0x0076
            r2.close()     // Catch: IOException -> 0x0092
            goto L_0x0076
        L_0x0092:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0076
        L_0x0097:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x008c
        L_0x009c:
            r0 = move-exception
            r3 = r2
        L_0x009e:
            if (r2 == 0) goto L_0x00a3
            r2.close()     // Catch: IOException -> 0x00a9
        L_0x00a3:
            if (r3 == 0) goto L_0x00a8
            r3.close()     // Catch: IOException -> 0x00ae
        L_0x00a8:
            throw r0
        L_0x00a9:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00a3
        L_0x00ae:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00a8
        L_0x00b3:
            r0 = move-exception
            goto L_0x009e
        L_0x00b5:
            r0 = move-exception
            r2 = r1
            goto L_0x009e
        L_0x00b8:
            r0 = move-exception
            r3 = r2
            r2 = r1
            goto L_0x009e
        L_0x00bc:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0083
        L_0x00c0:
            r0 = move-exception
            r2 = r3
            goto L_0x0083
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hyphenate.util.EasyUtils.writeToZipFile(byte[], java.lang.String):boolean");
    }
}
