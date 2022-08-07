package com.tencent.smtt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsLogReport;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class FileUtil {

    /* renamed from: a */
    public static String f1481a = null;

    /* renamed from: b */
    public static final a f1482b = new a() { // from class: com.tencent.smtt.utils.FileUtil.2
        @Override // com.tencent.smtt.utils.FileUtil.a
        public boolean a(File file, File file2) {
            return file.length() == file2.length() && file.lastModified() == file2.lastModified();
        }
    };

    /* renamed from: c */
    public static final int f1483c = 4;

    /* renamed from: d */
    public static RandomAccessFile f1484d;

    /* loaded from: classes.dex */
    public interface a {
        boolean a(File file, File file2);
    }

    /* loaded from: classes.dex */
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

    public static File a(Context context, String str) {
        String str2;
        File file = new File(context.getFilesDir(), "tbs");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            str2 = "getPermanentTbsFile -- no permission!";
        } else {
            File file2 = new File(file, str);
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (IOException e2) {
                    str2 = "getPermanentTbsFile -- exception: " + e2;
                }
            }
            return file2;
        }
        TbsLog.e("FileHelper", str2);
        return null;
    }

    public static File a(Context context, boolean z, String str) {
        String d2 = z ? d(context) : c(context);
        if (d2 == null) {
            return null;
        }
        File file = new File(d2);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return file2;
    }

    public static String a(Context context, int i) {
        return a(context, context.getApplicationInfo().packageName, i, true);
    }

    public static String a(Context context, String str, int i, boolean z) {
        String str2;
        if (context == null) {
            return BuildConfig.FLAVOR;
        }
        try {
            str2 = Environment.getExternalStorageDirectory() + File.separator;
        } catch (Exception e2) {
            e2.printStackTrace();
            str2 = BuildConfig.FLAVOR;
        }
        switch (i) {
            case 1:
                if (str2.equals(BuildConfig.FLAVOR)) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
            case 2:
                if (str2.equals(BuildConfig.FLAVOR)) {
                    return str2;
                }
                return str2 + "tbs" + File.separator + "backup" + File.separator + str;
            case 3:
                if (str2.equals(BuildConfig.FLAVOR)) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
            case 4:
                if (str2.equals(BuildConfig.FLAVOR)) {
                    return b(context, "backup");
                }
                String str3 = str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
                if (!z) {
                    return str3;
                }
                File file = new File(str3);
                if (file.exists() && file.canWrite()) {
                    return str3;
                }
                if (!file.exists()) {
                    file.mkdirs();
                    if (file.canWrite()) {
                        return str3;
                    }
                }
                return b(context, "backup");
            case 5:
                if (str2.equals(BuildConfig.FLAVOR)) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
            case 6:
                String str4 = f1481a;
                if (str4 != null) {
                    return str4;
                }
                f1481a = b(context, "tbslog");
                return f1481a;
            case 7:
                if (str2.equals(BuildConfig.FLAVOR)) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + "core";
            case 8:
                return b(context, "env");
            default:
                return BuildConfig.FLAVOR;
        }
    }

    public static FileLock a(Context context, FileOutputStream fileOutputStream) {
        FileLock tryLock;
        if (fileOutputStream == null) {
            return null;
        }
        try {
            tryLock = fileOutputStream.getChannel().tryLock();
        } catch (OverlappingFileLockException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (tryLock.isValid()) {
            return tryLock;
        }
        return null;
    }

    public static synchronized void a(Context context, FileLock fileLock) {
        synchronized (FileUtil.class) {
            TbsLog.i("FileHelper", "releaseTbsCoreRenameFileLock -- lock: " + fileLock);
            FileChannel channel = fileLock.channel();
            if (channel != null && channel.isOpen()) {
                try {
                    fileLock.release();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void a(File file, boolean z) {
        TbsLog.i("FileUtils", "delete file,ignore=" + z + file + Log.getStackTraceString(new Throwable()));
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

    public static void a(File file, boolean z, String str) {
        TbsLog.i("FileUtils", "delete file,ignore=" + z + "except" + str + file + Log.getStackTraceString(new Throwable()));
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (!file2.getName().equals(str)) {
                        a(file2, z);
                    }
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
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public static boolean a(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        return context != null && context.getApplicationContext().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        a(file, false);
        return file.mkdirs();
    }

    public static boolean a(File file, File file2) {
        return a(file.getPath(), file2.getPath());
    }

    public static boolean a(File file, File file2, FileFilter fileFilter) {
        return a(file, file2, fileFilter, f1482b);
    }

    public static boolean a(File file, File file2, FileFilter fileFilter, a aVar) {
        if (file == null || file2 == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return b(file, file2, fileFilter, aVar);
        }
        File[] listFiles = file.listFiles(fileFilter);
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

    public static boolean a(String str, long j, long j2, long j3) {
        Throwable th;
        FileInputStream fileInputStream;
        File file = new File(str);
        if (file.length() != j) {
            StringBuilder a2 = e.a.a.a.a.a("file size doesn't match: ");
            a2.append(file.length());
            a2.append(" vs ");
            a2.append(j);
            TbsLog.e("FileHelper", a2.toString());
            return true;
        }
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            CRC32 crc32 = new CRC32();
            byte[] bArr = new byte[8192];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                crc32.update(bArr, 0, read);
            }
            long value = crc32.getValue();
            TbsLog.i("FileHelper", BuildConfig.FLAVOR + file.getName() + ": crc = " + value + ", zipCrc = " + j3);
            if (value != j3) {
                fileInputStream.close();
                return true;
            }
            fileInputStream.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    @SuppressLint({"InlinedApi"})
    public static boolean a(String str, String str2) {
        String str3 = Build.CPU_ABI;
        int i = Build.VERSION.SDK_INT;
        return a(str, str2, str3, Build.CPU_ABI2, PropertyUtils.getQuickly("ro.product.cpu.upgradeabi", "armeabi"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0095, code lost:
        if (r6.regionMatches(com.tencent.smtt.utils.FileUtil.f1483c, r14, 0, r14.length()) == false) goto L_0x000e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a2, code lost:
        if (r6.charAt(com.tencent.smtt.utils.FileUtil.f1483c + r14.length()) != '/') goto L_0x000e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a4, code lost:
        if (r3 != false) goto L_0x000e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a6, code lost:
        if (r4 == false) goto L_0x00aa;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x000e, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x000e, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x000e, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, com.tencent.smtt.utils.FileUtil.b r15) {
        /*
            r0 = 0
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch: all -> 0x00d5
            r1.<init>(r11)     // Catch: all -> 0x00d5
            java.util.Enumeration r11 = r1.entries()     // Catch: all -> 0x00d3
            r0 = 1
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x000e:
            boolean r5 = r11.hasMoreElements()     // Catch: all -> 0x00d3
            if (r5 == 0) goto L_0x00cf
            java.lang.Object r5 = r11.nextElement()     // Catch: all -> 0x00d3
            java.util.zip.ZipEntry r5 = (java.util.zip.ZipEntry) r5     // Catch: all -> 0x00d3
            java.lang.String r6 = r5.getName()     // Catch: all -> 0x00d3
            if (r6 != 0) goto L_0x0021
            goto L_0x000e
        L_0x0021:
            java.lang.String r7 = "../"
            boolean r7 = r6.contains(r7)     // Catch: all -> 0x00d3
            if (r7 == 0) goto L_0x002a
            goto L_0x000e
        L_0x002a:
            java.lang.String r7 = "lib/"
            boolean r7 = r6.startsWith(r7)     // Catch: all -> 0x00d3
            if (r7 != 0) goto L_0x003b
            java.lang.String r7 = "assets/"
            boolean r7 = r6.startsWith(r7)     // Catch: all -> 0x00d3
            if (r7 != 0) goto L_0x003b
            goto L_0x000e
        L_0x003b:
            r7 = 47
            int r8 = r6.lastIndexOf(r7)     // Catch: all -> 0x00d3
            java.lang.String r8 = r6.substring(r8)     // Catch: all -> 0x00d3
            java.lang.String r9 = ".so"
            boolean r9 = r8.endsWith(r9)     // Catch: all -> 0x00d3
            if (r9 == 0) goto L_0x00aa
            int r9 = com.tencent.smtt.utils.FileUtil.f1483c     // Catch: all -> 0x00d3
            int r10 = r12.length()     // Catch: all -> 0x00d3
            boolean r9 = r6.regionMatches(r9, r12, r2, r10)     // Catch: all -> 0x00d3
            if (r9 == 0) goto L_0x0068
            int r9 = com.tencent.smtt.utils.FileUtil.f1483c     // Catch: all -> 0x00d3
            int r10 = r12.length()     // Catch: all -> 0x00d3
            int r9 = r9 + r10
            char r9 = r6.charAt(r9)     // Catch: all -> 0x00d3
            if (r9 != r7) goto L_0x0068
            r3 = 1
            goto L_0x00aa
        L_0x0068:
            if (r13 == 0) goto L_0x0089
            int r9 = com.tencent.smtt.utils.FileUtil.f1483c     // Catch: all -> 0x00d3
            int r10 = r13.length()     // Catch: all -> 0x00d3
            boolean r9 = r6.regionMatches(r9, r13, r2, r10)     // Catch: all -> 0x00d3
            if (r9 == 0) goto L_0x0089
            int r9 = com.tencent.smtt.utils.FileUtil.f1483c     // Catch: all -> 0x00d3
            int r10 = r13.length()     // Catch: all -> 0x00d3
            int r9 = r9 + r10
            char r9 = r6.charAt(r9)     // Catch: all -> 0x00d3
            if (r9 != r7) goto L_0x0089
            if (r3 == 0) goto L_0x0087
            r4 = 1
            goto L_0x000e
        L_0x0087:
            r4 = 1
            goto L_0x00aa
        L_0x0089:
            if (r14 == 0) goto L_0x000e
            int r9 = com.tencent.smtt.utils.FileUtil.f1483c     // Catch: all -> 0x00d3
            int r10 = r14.length()     // Catch: all -> 0x00d3
            boolean r9 = r6.regionMatches(r9, r14, r2, r10)     // Catch: all -> 0x00d3
            if (r9 == 0) goto L_0x000e
            int r9 = com.tencent.smtt.utils.FileUtil.f1483c     // Catch: all -> 0x00d3
            int r10 = r14.length()     // Catch: all -> 0x00d3
            int r9 = r9 + r10
            char r6 = r6.charAt(r9)     // Catch: all -> 0x00d3
            if (r6 != r7) goto L_0x000e
            if (r3 != 0) goto L_0x000e
            if (r4 == 0) goto L_0x00aa
            goto L_0x000e
        L_0x00aa:
            java.io.InputStream r6 = r1.getInputStream(r5)     // Catch: all -> 0x00d3
            java.lang.String r7 = r8.substring(r0)     // Catch: all -> 0x00c8
            boolean r5 = r15.a(r6, r5, r7)     // Catch: all -> 0x00c8
            if (r5 != 0) goto L_0x00c1
            if (r6 == 0) goto L_0x00bd
            r6.close()     // Catch: all -> 0x00d3
        L_0x00bd:
            r1.close()
            return r2
        L_0x00c1:
            if (r6 == 0) goto L_0x000e
            r6.close()     // Catch: all -> 0x00d3
            goto L_0x000e
        L_0x00c8:
            r11 = move-exception
            if (r6 == 0) goto L_0x00ce
            r6.close()     // Catch: all -> 0x00d3
        L_0x00ce:
            throw r11     // Catch: all -> 0x00d3
        L_0x00cf:
            r1.close()
            return r0
        L_0x00d3:
            r11 = move-exception
            goto L_0x00d7
        L_0x00d5:
            r11 = move-exception
            r1 = r0
        L_0x00d7:
            if (r1 == 0) goto L_0x00dc
            r1.close()
        L_0x00dc:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.FileUtil.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.smtt.utils.FileUtil$b):boolean");
    }

    public static boolean a(String str, final String str2, String str3, String str4, String str5) {
        return a(str, str3, str4, str5, new b() { // from class: com.tencent.smtt.utils.FileUtil.1
            @Override // com.tencent.smtt.utils.FileUtil.b
            public boolean a(InputStream inputStream, ZipEntry zipEntry, String str6) {
                try {
                    return FileUtil.b(inputStream, zipEntry, str2, str6);
                } catch (Exception e2) {
                    throw new Exception("copyFileIfChanged Exception", e2);
                }
            }
        });
    }

    public static FileOutputStream b(Context context, boolean z, String str) {
        File a2 = a(context, z, str);
        if (a2 == null) {
            return null;
        }
        try {
            return new FileOutputStream(a2);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(Context context, String str) {
        if (context == null) {
            return BuildConfig.FLAVOR;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        try {
            return context.getExternalFilesDir(str).getAbsolutePath();
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                return Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + context.getApplicationInfo().packageName + File.separator + "files" + File.separator + str;
            } catch (Exception e2) {
                e2.printStackTrace();
                return BuildConfig.FLAVOR;
            }
        }
    }

    public static void b(File file) {
        a(file, false);
    }

    public static boolean b(Context context) {
        long a2 = p.a();
        boolean z = a2 >= TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
        if (!z) {
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDwonloader.hasEnoughFreeSpace] freeSpace too small,  freeSpace = " + a2);
        }
        return z;
    }

    public static boolean b(File file, File file2) {
        return a(file, file2, (FileFilter) null);
    }

    public static boolean b(File file, File file2, FileFilter fileFilter, a aVar) {
        Throwable th;
        if (file == null || file2 == null) {
            return false;
        }
        if (fileFilter != null && !fileFilter.accept(file)) {
            return false;
        }
        FileChannel fileChannel = 0;
        try {
            if (file.exists() && file.isFile()) {
                if (file2.exists()) {
                    if (aVar != null && aVar.a(file, file2)) {
                        return true;
                    }
                    a(file2, false);
                }
                File parentFile = file2.getParentFile();
                if (parentFile.isFile()) {
                    a(parentFile, false);
                }
                if (!parentFile.exists() && !parentFile.mkdirs()) {
                    return false;
                }
                FileChannel channel = new FileInputStream(file).getChannel();
                try {
                    fileChannel = new FileOutputStream(file2).getChannel();
                    long size = channel.size();
                    if (fileChannel.transferFrom(channel, 0L, size) != size) {
                        a(file2, false);
                        channel.close();
                        fileChannel.close();
                        return false;
                    }
                    channel.close();
                    fileChannel.close();
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = channel;
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    throw th;
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileChannel = null;
        }
    }

    public static String c(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + "tbs" + File.separator + "file_locks";
    }

    public static boolean c(File file) {
        return file != null && file.exists() && file.isFile() && file.length() > 0;
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) {
        long a2 = a(inputStream, outputStream);
        if (a2 > 2147483647L) {
            return -1;
        }
        return (int) a2;
    }

    public static FileOutputStream d(File file) {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException(e.a.a.a.a.a("File '", file, "' could not be created"));
            }
        } else if (file.isDirectory()) {
            throw new IOException(e.a.a.a.a.a("File '", file, "' exists but is a directory"));
        } else if (!file.canWrite()) {
            throw new IOException(e.a.a.a.a.a("File '", file, "' cannot be written to"));
        }
        return new FileOutputStream(file);
    }

    public static String d(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_private");
        if (file.isDirectory() || file.mkdir()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static FileLock e(Context context) {
        boolean z;
        StringBuilder sb;
        String str;
        String str2;
        TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #1");
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        FileLock fileLock = null;
        if (!z) {
            FileOutputStream b2 = b(context, true, "tbs_rename_lock");
            if (b2 == null) {
                str2 = "init -- failed to get rename fileLock#1!";
            } else {
                fileLock = a(context, b2);
                str2 = fileLock == null ? "init -- failed to get rename fileLock#2!" : "init -- get rename fileLock success!";
            }
            TbsLog.i("FileHelper", str2);
            TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #2 renameFileLock is " + fileLock);
            return fileLock;
        }
        TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #3");
        File a2 = a(context, "tbs_rename_lock");
        TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #4 " + a2);
        try {
            f1484d = new RandomAccessFile(a2.getAbsolutePath(), "r");
            fileLock = f1484d.getChannel().tryLock(0L, Long.MAX_VALUE, true);
        } catch (Throwable th) {
            TbsLog.e("FileHelper", "getTbsCoreLoadFileLock -- exception: " + th);
        }
        if (fileLock == null) {
            fileLock = g(context);
        }
        if (fileLock == null) {
            sb = new StringBuilder();
            str = "getTbsCoreLoadFileLock -- failed: ";
        } else {
            sb = new StringBuilder();
            str = "getTbsCoreLoadFileLock -- success: ";
        }
        sb.append(str);
        sb.append("tbs_rename_lock");
        TbsLog.i("FileHelper", sb.toString());
        return fileLock;
    }

    public static FileLock f(Context context) {
        FileLock fileLock;
        String str;
        StringBuilder sb;
        File a2 = a(context, "tbs_rename_lock");
        TbsLog.i("FileHelper", "getTbsCoreRenameFileLock #1 " + a2);
        try {
            f1484d = new RandomAccessFile(a2.getAbsolutePath(), "rw");
            fileLock = f1484d.getChannel().tryLock(0L, Long.MAX_VALUE, false);
        } catch (Throwable unused) {
            TbsLog.e("FileHelper", "getTbsCoreRenameFileLock -- excpetion: tbs_rename_lock");
            fileLock = null;
        }
        if (fileLock == null) {
            sb = new StringBuilder();
            str = "getTbsCoreRenameFileLock -- failed: ";
        } else {
            sb = new StringBuilder();
            str = "getTbsCoreRenameFileLock -- success: ";
        }
        sb.append(str);
        sb.append("tbs_rename_lock");
        TbsLog.i("FileHelper", sb.toString());
        return fileLock;
    }

    public static FileLock g(Context context) {
        try {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
            tbsLogInfo.setErrorCode(803);
            File a2 = a(context, "tbs_rename_lock");
            if (!TbsDownloadConfig.getInstance(context).getTbsCoreLoadRenameFileLockWaitEnable()) {
                return null;
            }
            boolean z = false;
            FileLock fileLock = null;
            int i = 0;
            while (i < 20 && fileLock == null) {
                try {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    f1484d = new RandomAccessFile(a2.getAbsolutePath(), "r");
                    fileLock = f1484d.getChannel().tryLock(0L, Long.MAX_VALUE, true);
                    i++;
                }
            }
            if (fileLock != null) {
                tbsLogInfo.setErrorCode(802);
            } else {
                tbsLogInfo.setErrorCode(801);
            }
            TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_SDK_REPORT_INFO, tbsLogInfo);
            StringBuilder sb = new StringBuilder();
            sb.append("getTbsCoreLoadFileLock,retry num=");
            sb.append(i);
            sb.append("success=");
            if (fileLock == null) {
                z = true;
            }
            sb.append(z);
            TbsLog.i("FileHelper", sb.toString());
            return fileLock;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 1, insn: 0x0089: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:21:0x0089
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
        */
    @android.annotation.SuppressLint({"NewApi"})
    public static boolean b(
    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 1, insn: 0x0089: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:21:0x0089
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r8v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:233)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:209)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:162)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:364)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:270)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
        */
}
