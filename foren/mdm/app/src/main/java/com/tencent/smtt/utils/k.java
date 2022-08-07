package com.tencent.smtt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import com.umeng.update.UpdateConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;

@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public class k {
    private static final int c = "lib/".length();
    public static String a = null;
    public static final a b = new m();

    /* loaded from: classes2.dex */
    public interface a {
        boolean a(File file, File file2);
    }

    /* loaded from: classes2.dex */
    public interface b {
        boolean a(InputStream inputStream, ZipEntry zipEntry, String str);
    }

    public static long a(InputStream inputStream, OutputStream outputStream) {
        if (inputStream == null) {
            return -1L;
        }
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += read;
        }
    }

    public static ByteArrayOutputStream a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream;
    }

    public static File a() {
        try {
            return Environment.getExternalStorageDirectory();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File a(Context context, boolean z, String str) {
        String d = z ? d(context) : c(context);
        if (d == null) {
            return null;
        }
        File file = new File(d);
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file2;
    }

    public static String a(Context context, int i) {
        return a(context, context.getApplicationInfo().packageName, i, true);
    }

    private static String a(Context context, String str) {
        if (context == null) {
            return "";
        }
        Context applicationContext = context.getApplicationContext();
        try {
            return applicationContext.getExternalFilesDir(str).getAbsolutePath();
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                return Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + applicationContext.getApplicationInfo().packageName + File.separator + "files" + File.separator + str;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static String a(Context context, String str, int i, boolean z) {
        if (context == null) {
            return "";
        }
        String str2 = "";
        try {
            str2 = Environment.getExternalStorageDirectory() + File.separator;
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (i) {
            case 1:
                return !str2.equals("") ? str2 + "tencent" + File.separator + "tbs" + File.separator + str : str2;
            case 2:
                return !str2.equals("") ? str2 + "tbs" + File.separator + "backup" + File.separator + str : str2;
            case 3:
                return !str2.equals("") ? str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str : str2;
            case 4:
                if (str2.equals("")) {
                    return a(context, "backup");
                }
                String str3 = str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
                if (!z) {
                    return str3;
                }
                File file = new File(str3);
                if (file.exists() && file.canWrite()) {
                    return str3;
                }
                if (file.exists()) {
                    return a(context, "backup");
                }
                file.mkdirs();
                return !file.canWrite() ? a(context, "backup") : str3;
            case 5:
                return !str2.equals("") ? str2 + "tencent" + File.separator + "tbs" + File.separator + str : str2;
            case 6:
                if (a != null) {
                    return a;
                }
                a = a(context, "tbslog");
                return a;
            default:
                return "";
        }
    }

    public static FileLock a(Context context, FileOutputStream fileOutputStream) {
        try {
            if (fileOutputStream == null) {
                return null;
            }
            try {
                FileLock tryLock = fileOutputStream.getChannel().tryLock();
                if (tryLock.isValid()) {
                    return tryLock;
                }
                try {
                    fileOutputStream.close();
                    return null;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return null;
                }
            } catch (OverlappingFileLockException e2) {
                e2.printStackTrace();
                try {
                    fileOutputStream.close();
                    return null;
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    return null;
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                try {
                    fileOutputStream.close();
                    return null;
                } catch (Exception e5) {
                    e = e5;
                    e.printStackTrace();
                    return null;
                }
            }
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e6) {
                e6.printStackTrace();
            }
        }
    }

    public static void a(File file, boolean z) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    a(file2, z);
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    public static void a(FileLock fileLock, FileOutputStream fileOutputStream) {
        if (fileLock != null) {
            try {
                FileChannel channel = fileLock.channel();
                if (channel != null && channel.isOpen()) {
                    fileLock.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        return context != null && context.getApplicationContext().checkSelfPermission(UpdateConfig.f) == 0;
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        b(file);
        return file.mkdirs();
    }

    public static boolean a(File file, File file2) {
        return a(file.getPath(), file2.getPath());
    }

    public static boolean a(File file, File file2, FileFilter fileFilter) {
        return a(file, file2, fileFilter, b);
    }

    public static boolean a(File file, File file2, FileFilter fileFilter, a aVar) {
        if (file == null || file2 == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return b(file, file2, fileFilter, aVar);
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file3 : listFiles) {
            if (!a(file3, new File(file2, file3.getName()), fileFilter)) {
                z = false;
            }
        }
        return z;
    }

    private static boolean a(String str, long j, long j2, long j3) {
        File file = new File(str);
        if (file.length() != j) {
            TbsLog.e("FileHelper", "file size doesn't match: " + file.length() + " vs " + j);
            return true;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                CRC32 crc32 = new CRC32();
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    crc32.update(bArr, 0, read);
                }
                long value = crc32.getValue();
                TbsLog.i("FileHelper", "" + file.getName() + ": crc = " + value + ", zipCrc = " + j3);
                if (value == j3) {
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    return false;
                } else if (fileInputStream2 == null) {
                    return true;
                } else {
                    fileInputStream2.close();
                    return true;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @SuppressLint({"InlinedApi"})
    public static boolean a(String str, String str2) {
        return a(str, str2, Build.CPU_ABI, Build.VERSION.SDK_INT >= 8 ? Build.CPU_ABI2 : null, r.a("ro.product.cpu.upgradeabi", "armeabi"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a8, code lost:
        if (r5.regionMatches(com.tencent.smtt.utils.k.c, r13, 0, r13.length()) == false) goto L_0x000c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b7, code lost:
        if (r5.charAt(com.tencent.smtt.utils.k.c + r13.length()) != '/') goto L_0x000c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b9, code lost:
        if (r3 != false) goto L_0x000c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bb, code lost:
        if (r2 == false) goto L_0x005d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x000c, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x000c, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x000c, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, com.tencent.smtt.utils.k.b r14) {
        /*
            r2 = 0
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch: all -> 0x00db
            r1.<init>(r10)     // Catch: all -> 0x00db
            r3 = 0
            r2 = 0
            java.util.Enumeration r4 = r1.entries()     // Catch: all -> 0x00c6
        L_0x000c:
            boolean r0 = r4.hasMoreElements()     // Catch: all -> 0x00c6
            if (r0 == 0) goto L_0x00cd
            java.lang.Object r0 = r4.nextElement()     // Catch: all -> 0x00c6
            java.util.zip.ZipEntry r0 = (java.util.zip.ZipEntry) r0     // Catch: all -> 0x00c6
            java.lang.String r5 = r0.getName()     // Catch: all -> 0x00c6
            if (r5 == 0) goto L_0x000c
            java.lang.String r6 = "lib/"
            boolean r6 = r5.startsWith(r6)     // Catch: all -> 0x00c6
            if (r6 != 0) goto L_0x002e
            java.lang.String r6 = "assets/"
            boolean r6 = r5.startsWith(r6)     // Catch: all -> 0x00c6
            if (r6 == 0) goto L_0x000c
        L_0x002e:
            r6 = 47
            int r6 = r5.lastIndexOf(r6)     // Catch: all -> 0x00c6
            java.lang.String r6 = r5.substring(r6)     // Catch: all -> 0x00c6
            java.lang.String r7 = ".so"
            boolean r7 = r6.endsWith(r7)     // Catch: all -> 0x00c6
            if (r7 == 0) goto L_0x005d
            int r7 = com.tencent.smtt.utils.k.c     // Catch: all -> 0x00c6
            r8 = 0
            int r9 = r11.length()     // Catch: all -> 0x00c6
            boolean r7 = r5.regionMatches(r7, r11, r8, r9)     // Catch: all -> 0x00c6
            if (r7 == 0) goto L_0x0078
            int r7 = com.tencent.smtt.utils.k.c     // Catch: all -> 0x00c6
            int r8 = r11.length()     // Catch: all -> 0x00c6
            int r7 = r7 + r8
            char r7 = r5.charAt(r7)     // Catch: all -> 0x00c6
            r8 = 47
            if (r7 != r8) goto L_0x0078
            r3 = 1
        L_0x005d:
            java.io.InputStream r5 = r1.getInputStream(r0)     // Catch: all -> 0x00c6
            r7 = 1
            java.lang.String r6 = r6.substring(r7)     // Catch: all -> 0x00bf
            boolean r0 = r14.a(r5, r0, r6)     // Catch: all -> 0x00bf
            if (r0 != 0) goto L_0x00d4
            r0 = 0
            if (r5 == 0) goto L_0x0072
            r5.close()     // Catch: all -> 0x00c6
        L_0x0072:
            if (r1 == 0) goto L_0x0077
            r1.close()
        L_0x0077:
            return r0
        L_0x0078:
            if (r12 == 0) goto L_0x009b
            int r7 = com.tencent.smtt.utils.k.c     // Catch: all -> 0x00c6
            r8 = 0
            int r9 = r12.length()     // Catch: all -> 0x00c6
            boolean r7 = r5.regionMatches(r7, r12, r8, r9)     // Catch: all -> 0x00c6
            if (r7 == 0) goto L_0x009b
            int r7 = com.tencent.smtt.utils.k.c     // Catch: all -> 0x00c6
            int r8 = r12.length()     // Catch: all -> 0x00c6
            int r7 = r7 + r8
            char r7 = r5.charAt(r7)     // Catch: all -> 0x00c6
            r8 = 47
            if (r7 != r8) goto L_0x009b
            r2 = 1
            if (r3 == 0) goto L_0x005d
            goto L_0x000c
        L_0x009b:
            if (r13 == 0) goto L_0x000c
            int r7 = com.tencent.smtt.utils.k.c     // Catch: all -> 0x00c6
            r8 = 0
            int r9 = r13.length()     // Catch: all -> 0x00c6
            boolean r7 = r5.regionMatches(r7, r13, r8, r9)     // Catch: all -> 0x00c6
            if (r7 == 0) goto L_0x000c
            int r7 = com.tencent.smtt.utils.k.c     // Catch: all -> 0x00c6
            int r8 = r13.length()     // Catch: all -> 0x00c6
            int r7 = r7 + r8
            char r5 = r5.charAt(r7)     // Catch: all -> 0x00c6
            r7 = 47
            if (r5 != r7) goto L_0x000c
            if (r3 != 0) goto L_0x000c
            if (r2 == 0) goto L_0x005d
            goto L_0x000c
        L_0x00bf:
            r0 = move-exception
            if (r5 == 0) goto L_0x00c5
            r5.close()     // Catch: all -> 0x00c6
        L_0x00c5:
            throw r0     // Catch: all -> 0x00c6
        L_0x00c6:
            r0 = move-exception
        L_0x00c7:
            if (r1 == 0) goto L_0x00cc
            r1.close()
        L_0x00cc:
            throw r0
        L_0x00cd:
            if (r1 == 0) goto L_0x00d2
            r1.close()
        L_0x00d2:
            r0 = 1
            goto L_0x0077
        L_0x00d4:
            if (r5 == 0) goto L_0x000c
            r5.close()     // Catch: all -> 0x00c6
            goto L_0x000c
        L_0x00db:
            r0 = move-exception
            r1 = r2
            goto L_0x00c7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.k.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.smtt.utils.k$b):boolean");
    }

    private static boolean a(String str, String str2, String str3, String str4, String str5) {
        return a(str, str3, str4, str5, new l(str2));
    }

    public static int b(InputStream inputStream, OutputStream outputStream) {
        long a2 = a(inputStream, outputStream);
        if (a2 > 2147483647L) {
            return -1;
        }
        return (int) a2;
    }

    public static FileOutputStream b(Context context, boolean z, String str) {
        File a2 = a(context, z, str);
        if (a2 != null) {
            try {
                return new FileOutputStream(a2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void b(File file) {
        a(file, false);
    }

    public static boolean b() {
        try {
            return "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(Context context) {
        long a2 = aa.a();
        boolean z = a2 >= TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
        if (!z) {
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDwonloader.hasEnoughFreeSpace] freeSpace too small,  freeSpace = " + a2);
        }
        return z;
    }

    public static boolean b(File file, File file2) {
        return a(file, file2, (FileFilter) null);
    }

    private static boolean b(File file, File file2, FileFilter fileFilter, a aVar) {
        FileChannel fileChannel;
        Throwable th;
        FileChannel channel;
        FileChannel fileChannel2 = null;
        if (file == null || file2 == null) {
            return false;
        }
        if (fileFilter != null && !fileFilter.accept(file)) {
            return false;
        }
        try {
            if (!file.exists() || !file.isFile()) {
                if (0 != 0) {
                    fileChannel2.close();
                }
                if (0 != 0) {
                    fileChannel2.close();
                }
                return false;
            }
            if (file2.exists()) {
                if (aVar == null || !aVar.a(file, file2)) {
                    b(file2);
                } else {
                    if (0 != 0) {
                        fileChannel2.close();
                    }
                    if (0 != 0) {
                        fileChannel2.close();
                    }
                    return true;
                }
            }
            File parentFile = file2.getParentFile();
            if (parentFile.isFile()) {
                b(parentFile);
            }
            if (parentFile.exists() || parentFile.mkdirs()) {
                FileChannel channel2 = new FileInputStream(file).getChannel();
                try {
                    channel = new FileOutputStream(file2).getChannel();
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel2 = channel2;
                    fileChannel = null;
                }
                try {
                    long size = channel2.size();
                    if (channel.transferFrom(channel2, 0L, size) != size) {
                        b(file2);
                        if (channel2 != null) {
                            channel2.close();
                        }
                        if (channel != null) {
                            channel.close();
                        }
                        return false;
                    }
                    if (channel2 != null) {
                        channel2.close();
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    return true;
                } catch (Throwable th3) {
                    th = th3;
                    fileChannel2 = channel2;
                    fileChannel = channel;
                    if (fileChannel2 != null) {
                        fileChannel2.close();
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    throw th;
                }
            } else {
                if (0 != 0) {
                    fileChannel2.close();
                }
                if (0 != 0) {
                    fileChannel2.close();
                }
                return false;
            }
        } catch (Throwable th4) {
            th = th4;
            fileChannel = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005b  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean b(java.io.InputStream r9, java.util.zip.ZipEntry r10, java.lang.String r11, java.lang.String r12) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File
            r1.<init>(r11)
            a(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r11)
            java.lang.String r2 = java.io.File.separator
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r12)
            java.lang.String r1 = r1.toString()
            java.io.File r8 = new java.io.File
            r8.<init>(r1)
            r3 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: IOException -> 0x00b9, all -> 0x00b3
            r2.<init>(r8)     // Catch: IOException -> 0x00b9, all -> 0x00b3
            r3 = 8192(0x2000, float:1.14794E-41)
            byte[] r3 = new byte[r3]     // Catch: IOException -> 0x003a, all -> 0x00b6
        L_0x002f:
            int r4 = r9.read(r3)     // Catch: IOException -> 0x003a, all -> 0x00b6
            if (r4 <= 0) goto L_0x005f
            r5 = 0
            r2.write(r3, r5, r4)     // Catch: IOException -> 0x003a, all -> 0x00b6
            goto L_0x002f
        L_0x003a:
            r0 = move-exception
            r1 = r2
        L_0x003c:
            b(r8)     // Catch: all -> 0x0058
            java.io.IOException r2 = new java.io.IOException     // Catch: all -> 0x0058
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x0058
            r3.<init>()     // Catch: all -> 0x0058
            java.lang.String r4 = "Couldn't write dst file "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: all -> 0x0058
            java.lang.StringBuilder r3 = r3.append(r8)     // Catch: all -> 0x0058
            java.lang.String r3 = r3.toString()     // Catch: all -> 0x0058
            r2.<init>(r3, r0)     // Catch: all -> 0x0058
            throw r2     // Catch: all -> 0x0058
        L_0x0058:
            r0 = move-exception
        L_0x0059:
            if (r1 == 0) goto L_0x005e
            r1.close()
        L_0x005e:
            throw r0
        L_0x005f:
            if (r2 == 0) goto L_0x0064
            r2.close()
        L_0x0064:
            long r2 = r10.getSize()
            long r4 = r10.getTime()
            long r6 = r10.getCrc()
            boolean r2 = a(r1, r2, r4, r6)
            if (r2 == 0) goto L_0x008f
            java.lang.String r2 = "FileHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "file is different: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r1)
        L_0x008e:
            return r0
        L_0x008f:
            long r0 = r10.getTime()
            boolean r0 = r8.setLastModified(r0)
            if (r0 != 0) goto L_0x00b1
            java.lang.String r0 = "FileHelper"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Couldn't set time for dst file "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r1)
        L_0x00b1:
            r0 = 1
            goto L_0x008e
        L_0x00b3:
            r0 = move-exception
            r1 = r3
            goto L_0x0059
        L_0x00b6:
            r0 = move-exception
            r1 = r2
            goto L_0x0059
        L_0x00b9:
            r0 = move-exception
            r1 = r3
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.k.b(java.io.InputStream, java.util.zip.ZipEntry, java.lang.String, java.lang.String):boolean");
    }

    public static byte[] b(InputStream inputStream) {
        return a(inputStream).toByteArray();
    }

    public static File c() {
        File file = new File(a().getAbsolutePath() + "/.tbs");
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String c(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + "tbs" + File.separator + "file_locks";
    }

    public static boolean c(File file) {
        return file != null && file.exists() && file.isFile() && file.length() > 0;
    }

    public static FileOutputStream d(File file) {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("File '" + file + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file);
    }

    static String d(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || file.mkdir()) {
            return file.getAbsolutePath();
        }
        return null;
    }
}
