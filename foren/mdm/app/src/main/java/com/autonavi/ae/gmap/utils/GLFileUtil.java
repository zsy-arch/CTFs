package com.autonavi.ae.gmap.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import u.aly.d;

/* loaded from: classes.dex */
public class GLFileUtil {
    public static void copyAssetsFile(Context context, String str, String str2) {
        try {
            File file = new File(str2);
            if (file == null || !file.exists()) {
                InputStream open = context.getAssets().open(str);
                if (open.available() != 0) {
                    FileOutputStream fileOutputStream = new FileOutputStream(str2);
                    byte[] bArr = new byte[1024];
                    for (int read = open.read(bArr); read > 0; read = open.read(bArr)) {
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copy(Context context, String str, File file) throws Exception {
        file.delete();
        FileOutputStream open = context.getAssets().open(str);
        byte[] bArr = new byte[open.available()];
        try {
            open.read(bArr);
            closeQuietly(open);
            open = new FileOutputStream(file);
            try {
                open.write(bArr);
            } finally {
            }
        } finally {
        }
    }

    public static void deleteFile(File file) {
        if (file != null) {
            File[] listFiles = file.listFiles();
            if (file.isDirectory() && listFiles != null) {
                for (File file2 : listFiles) {
                    deleteFile(file2);
                }
            }
            file.delete();
        }
    }

    public static String getCurrentOfflineDataStorage(Context context) {
        return context.getSharedPreferences("base_path", 0).getString("offline_data_storage", "");
    }

    public static boolean checkPathIsCanUse(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isDirectory() && file.canWrite() && file.canRead();
    }

    public static String getMapBaseStorage(Context context) {
        String str = "map_base_path";
        if (Build.VERSION.SDK_INT > 18) {
            str = "map_base_path_v44";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("base_path", 0);
        String string = sharedPreferences.getString(str, "");
        if (string != null && string.length() > 2) {
            File file = new File(string);
            if (file.isDirectory()) {
                if (file.canWrite()) {
                    createNoMediaFileIfNotExist(string);
                    return string;
                }
                String file2 = getCacheDir(context).toString();
                if (file2 != null && file2.length() > 2 && new File(file2).isDirectory()) {
                    return file2;
                }
            }
        }
        String externalStroragePath = getExternalStroragePath(context);
        if (externalStroragePath == null || externalStroragePath.length() <= 2 || !new File(externalStroragePath).isDirectory()) {
            String file3 = getCacheDir(context).toString();
            if (file3 == null || file3.length() <= 2 || new File(file3).isDirectory()) {
            }
            return file3;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(str, externalStroragePath);
        if (Build.VERSION.SDK_INT >= 9) {
            edit.apply();
        } else {
            edit.commit();
        }
        createNoMediaFileIfNotExist(externalStroragePath);
        return externalStroragePath;
    }

    @SuppressLint({"NewApi"})
    @TargetApi(19)
    public static String getExternalStroragePath(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 12) {
            try {
                StorageManager storageManager = (StorageManager) context.getSystemService("storage");
                Method method = StorageManager.class.getMethod("getVolumeList", new Class[0]);
                Method method2 = StorageManager.class.getMethod("getVolumeState", String.class);
                Object[] objArr = (Object[]) method.invoke(storageManager, new Object[0]);
                Boolean.valueOf(false);
                String str = "";
                String str2 = "";
                int length = objArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        str = null;
                        break;
                    }
                    Object obj = objArr[i2];
                    Method method3 = obj.getClass().getMethod("getPath", new Class[0]);
                    Method method4 = obj.getClass().getMethod("isRemovable", new Class[0]);
                    str = (String) method3.invoke(obj, new Object[0]);
                    str2 = (String) method2.invoke(storageManager, method3.invoke(obj, new Object[0]));
                    Boolean bool = (Boolean) method4.invoke(obj, new Object[0]);
                    if (str.toLowerCase(Locale.US).contains("private")) {
                        str2 = str2;
                        str = str;
                    } else if (!bool.booleanValue()) {
                        continue;
                    } else if (str == null || str2 == null || !str2.equals("mounted")) {
                        str2 = str2;
                        str = str;
                    } else if (i <= 18) {
                        str = str;
                    } else {
                        try {
                            File[] externalFilesDirs = context.getExternalFilesDirs(null);
                            if (externalFilesDirs != null && externalFilesDirs.length > 0) {
                                String str3 = str;
                                for (File file : externalFilesDirs) {
                                    if (file != null) {
                                        str3 = file.getAbsolutePath();
                                        if (!TextUtils.isEmpty(str3) && str3.contains(str)) {
                                        }
                                    }
                                    str3 = str3;
                                }
                                str = str3;
                            }
                        } catch (Throwable th) {
                            str = str;
                        }
                    }
                    i2++;
                }
                if (i <= 18) {
                    return (str != null || str == null || str2 == null || !str2.equals("mounted")) ? str : str;
                }
                if (!(str == null || str2 == null || !str2.equals("mounted"))) {
                    str = str;
                }
                return str;
            } catch (IllegalAccessException e) {
            } catch (NoSuchMethodException e2) {
            } catch (InvocationTargetException e3) {
            }
        }
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    public static void writeDatasToFile(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        Throwable th;
        ReentrantReadWriteLock.WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        FileOutputStream fileOutputStream2 = null;
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    File file = new File(str);
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(bArr);
                        fileOutputStream.flush();
                        writeLock.unlock();
                        closeQuietly(fileOutputStream);
                        return;
                    } catch (Exception e) {
                        fileOutputStream2 = fileOutputStream;
                        writeLock.unlock();
                        closeQuietly(fileOutputStream2);
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        writeLock.unlock();
                        closeQuietly(fileOutputStream);
                        throw th;
                    }
                }
            } catch (Exception e2) {
            } catch (Throwable th3) {
                fileOutputStream = null;
                th = th3;
            }
        }
        writeLock.unlock();
        closeQuietly(null);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static byte[] readFileContents(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        byte[] bArr = null;
        try {
            File file = new File(str);
            if (!file.exists()) {
                closeQuietly(null);
            } else {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr2 = new byte[1024];
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        int read = fileInputStream.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    byteArrayOutputStream.close();
                    bArr = byteArrayOutputStream.toByteArray();
                    closeQuietly(fileInputStream);
                } catch (Exception e) {
                    closeQuietly(fileInputStream);
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
        return bArr;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAppSDCardFileDir() {
        File file;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = new File(Environment.getExternalStorageDirectory(), GeocodeSearch.AMAP);
            if (!file.exists()) {
                file.mkdir();
            }
        } else {
            file = null;
        }
        if (file == null) {
            return null;
        }
        return file.toString();
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

    public static File getFilesDir(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            filesDir = context.getDir("files", 0);
        }
        if (filesDir == null) {
            filesDir = new File(d.a + context.getPackageName() + "/app_files");
        }
        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }
        return filesDir;
    }
}
