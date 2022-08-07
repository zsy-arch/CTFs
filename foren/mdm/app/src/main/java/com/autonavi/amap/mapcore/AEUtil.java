package com.autonavi.amap.mapcore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.autonavi.ae.utils.NaviUtils;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import u.aly.d;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class AEUtil {
    private static final int BUFFER = 1024;
    public static final String CONFIGNAME = "GNaviConfig.xml";
    public static final boolean IS_AE = true;
    public static final String RESZIPNAME = "res.zip";
    private static final String TAG = AEUtil.class.getSimpleName();
    private static String currentPath = null;

    /* loaded from: classes.dex */
    public static class UnZipFileBrake {
        public boolean mIsAborted = false;
    }

    /* loaded from: classes.dex */
    public interface ZipCompressProgressListener {
        void onFinishProgress(long j);
    }

    static {
        System.loadLibrary("GNaviUtils");
        System.loadLibrary("GNaviData");
        System.loadLibrary("GNaviSearch");
        System.loadLibrary("RoadLineRebuildAPI");
        System.loadLibrary("GNaviMap");
        System.loadLibrary("GNaviMapex");
    }

    public static void init(Context context) {
        currentPath = FileUtil.getMapBaseStorage(context);
        File file = new File(currentPath);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(currentPath, CONFIGNAME);
        if (!file2.exists() || !file2.isFile() || file2.length() <= 0) {
            byte[] readAssetsFile = readAssetsFile("ae/GNaviConfig.xml", context);
            if (readAssetsFile != null && readAssetsFile.length > 0) {
                NaviUtils.nativeSetConfigMem(currentPath, getString(readAssetsFile, "utf-8"));
                return;
            }
            return;
        }
        NaviUtils.nativeSetConfigFile(currentPath, currentPath + CONFIGNAME);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.InputStream] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void loadEngineRes(java.lang.String r4, android.content.Context r5) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "res"
            r0.<init>(r4, r1)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x0013
            boolean r1 = r0.isDirectory()
            if (r1 != 0) goto L_0x0019
        L_0x0013:
            boolean r1 = r0.mkdirs()
            if (r1 == 0) goto L_0x0019
        L_0x0019:
            boolean r1 = checkEngineRes(r0)
            if (r1 != 0) goto L_0x0036
            r1 = 0
            android.content.res.AssetManager r2 = r5.getAssets()     // Catch: Exception -> 0x003c, OutOfMemoryError -> 0x004b, all -> 0x005a
            java.lang.String r3 = "ae/res.zip"
            java.io.InputStream r1 = r2.open(r3)     // Catch: Exception -> 0x003c, OutOfMemoryError -> 0x004b, all -> 0x005a
            java.lang.String r0 = r0.getAbsolutePath()     // Catch: Exception -> 0x003c, OutOfMemoryError -> 0x004b, all -> 0x005a
            decompress(r1, r0)     // Catch: Exception -> 0x003c, OutOfMemoryError -> 0x004b, all -> 0x005a
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch: IOException -> 0x0037
        L_0x0036:
            return
        L_0x0037:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0036
        L_0x003c:
            r0 = move-exception
            r0.printStackTrace()     // Catch: all -> 0x005a
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch: IOException -> 0x0046
            goto L_0x0036
        L_0x0046:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0036
        L_0x004b:
            r0 = move-exception
            r0.printStackTrace()     // Catch: all -> 0x005a
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch: IOException -> 0x0055
            goto L_0x0036
        L_0x0055:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0036
        L_0x005a:
            r0 = move-exception
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch: IOException -> 0x0061
        L_0x0060:
            throw r0
        L_0x0061:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.AEUtil.loadEngineRes(java.lang.String, android.content.Context):void");
    }

    private static boolean checkEngineRes(File file) {
        try {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return false;
            }
            if (listFiles.length >= 4) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static String getEngineVersion() {
        return "n/a";
    }

    public static String getNaviRouteVersion() {
        return "n/a";
    }

    public static String getPosVersion() {
        return "n/a";
    }

    public static String getMapVersion() {
        return "n/a";
    }

    private static String getDataVersion() {
        return "";
    }

    public static byte[] readAssetsFile(String str, Context context) {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        OutOfMemoryError e;
        byte[] bArr = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                try {
                    inputStream = context.getAssets().open(str);
                    try {
                        byteArrayOutputStream2 = new ByteArrayOutputStream();
                        try {
                            byte[] bArr2 = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr2, 0, 1024);
                                if (read <= 0) {
                                    break;
                                }
                                byteArrayOutputStream2.write(bArr2, 0, read);
                            }
                            bArr = byteArrayOutputStream2.toByteArray();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArrayOutputStream2.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        } catch (Exception e4) {
                            e = e4;
                            e.printStackTrace();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArrayOutputStream2.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                            }
                            return bArr;
                        } catch (OutOfMemoryError e7) {
                            e = e7;
                            e.printStackTrace();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                            }
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArrayOutputStream2.close();
                                } catch (IOException e9) {
                                    e9.printStackTrace();
                                }
                            }
                            return bArr;
                        }
                    } catch (Exception e10) {
                        e = e10;
                        byteArrayOutputStream2 = null;
                    } catch (OutOfMemoryError e11) {
                        e = e11;
                        byteArrayOutputStream2 = null;
                    } catch (Throwable th) {
                        byteArrayOutputStream = null;
                        th = th;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e12) {
                                e12.printStackTrace();
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e13) {
                                e13.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e14) {
                    e = e14;
                    byteArrayOutputStream2 = null;
                    inputStream = null;
                } catch (OutOfMemoryError e15) {
                    e = e15;
                    byteArrayOutputStream2 = null;
                    inputStream = null;
                } catch (Throwable th2) {
                    byteArrayOutputStream = null;
                    inputStream = null;
                    th = th2;
                }
            }
            return bArr;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static void createNoMediaFileIfNotExist(String str) {
        try {
            File file = new File(str + "/autonavi/.nomedia");
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.lastModified() > 0) {
                file.setLastModified(0L);
            }
        } catch (Throwable th) {
        }
    }

    public static File getCacheDir(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            cacheDir = context.getDir("cache", 0);
        }
        if (cacheDir == null) {
            cacheDir = new File(d.a + context.getPackageName() + "/app_cache");
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    public static String getString(byte[] bArr, String str) {
        if (bArr != null) {
            return getString(bArr, 0, bArr.length, str);
        }
        throw new IllegalArgumentException("Parameter may not be null");
    }

    public static String getString(byte[] bArr, int i, int i2, String str) {
        if (bArr == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        } else if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("charset may not be null or empty");
        } else {
            try {
                return new String(bArr, i, i2, str);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    public static void decompress(InputStream inputStream, String str) throws Exception {
        decompress(inputStream, str, 0L, null);
    }

    private static void decompress(InputStream inputStream, String str, long j, ZipCompressProgressListener zipCompressProgressListener) throws Exception {
        CheckedInputStream checkedInputStream = new CheckedInputStream(inputStream, new CRC32());
        ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);
        decompress(null, new File(str), zipInputStream, j, zipCompressProgressListener, null);
        zipInputStream.close();
        checkedInputStream.close();
    }

    private static void decompress(File file, File file2, ZipInputStream zipInputStream, long j, ZipCompressProgressListener zipCompressProgressListener, UnZipFileBrake unZipFileBrake) throws Exception {
        boolean z;
        int i = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                z = false;
                break;
            } else if (unZipFileBrake == null || !unZipFileBrake.mIsAborted) {
                String name = nextEntry.getName();
                if (TextUtils.isEmpty(name) || name.contains("../")) {
                    break;
                }
                File file3 = new File(file2.getPath() + File.separator + name);
                fileProber(file3);
                if (nextEntry.isDirectory()) {
                    file3.mkdirs();
                    i = i;
                } else {
                    i = decompressFile(file3, zipInputStream, i, j, zipCompressProgressListener, unZipFileBrake) + i;
                }
                zipInputStream.closeEntry();
            } else {
                zipInputStream.closeEntry();
                return;
            }
        }
        z = true;
        if (z && file != null) {
            try {
                file.delete();
            } catch (Exception e) {
            }
        }
    }

    private static void fileProber(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            fileProber(parentFile);
            parentFile.mkdir();
        }
    }

    private static int decompressFile(File file, ZipInputStream zipInputStream, long j, long j2, ZipCompressProgressListener zipCompressProgressListener, UnZipFileBrake unZipFileBrake) throws Exception {
        int i = 0;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bArr = new byte[1024];
        while (true) {
            int read = zipInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                if (unZipFileBrake != null && unZipFileBrake.mIsAborted) {
                    bufferedOutputStream.close();
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
                i += read;
                if (j2 > 0 && zipCompressProgressListener != null) {
                    long j3 = ((i + j) * 100) / j2;
                    if (unZipFileBrake == null || !unZipFileBrake.mIsAborted) {
                        zipCompressProgressListener.onFinishProgress(j3);
                    }
                }
            } else {
                bufferedOutputStream.close();
                break;
            }
        }
        return i;
    }
}
