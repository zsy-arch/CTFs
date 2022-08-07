package com.autonavi.amap.mapcore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import com.amap.api.maps.MapsInitializer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static void copy(Context context, String str, File file) throws Exception {
        file.delete();
        InputStream open = context.getAssets().open(str);
        byte[] bArr = new byte[open.available()];
        open.read(bArr);
        open.close();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bArr);
        fileOutputStream.close();
    }

    public static boolean deleteFile(File file) {
        File[] listFiles;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    if (!listFiles[i].delete()) {
                        return false;
                    }
                } else if (!deleteFile(listFiles[i])) {
                    return false;
                } else {
                    listFiles[i].delete();
                }
            }
        }
        file.delete();
        return true;
    }

    public static String getMapBaseStorage(Context context) {
        String string;
        if (context == null) {
            return null;
        }
        String str = "map_base_path";
        if (Build.VERSION.SDK_INT > 18) {
            str = "map_base_path_v44";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("base_path", 0);
        if (MapsInitializer.sdcardDir == null || MapsInitializer.sdcardDir.trim().length() <= 0) {
            string = sharedPreferences.getString(str, "");
        } else {
            string = MapsInitializer.sdcardDir;
        }
        if (string != null && string.length() > 2) {
            File file = new File(string);
            if (!file.exists()) {
                file.mkdir();
            }
            if (file.isDirectory()) {
                if (checkCanWrite(file)) {
                    return string;
                }
                String str2 = context.getCacheDir().toString() + File.separator + "amap" + File.separator;
                if (str2 != null && str2.length() > 2) {
                    File file2 = new File(str2);
                    if (!file2.exists()) {
                        file2.mkdir();
                    }
                    if (file2.isDirectory()) {
                        return str2;
                    }
                }
            }
        }
        String str3 = getExternalStroragePath(context) + File.separator + "amap" + File.separator;
        if (str3 != null && str3.length() > 2) {
            File file3 = new File(str3);
            if (!file3.exists()) {
                file3.mkdir();
            }
            if (file3.isDirectory() && file3.canWrite()) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(str, str3);
                edit.commit();
                createNoMediaFileIfNotExist(str3);
                return str3;
            }
        }
        String str4 = context.getCacheDir().toString() + File.separator + "amap" + File.separator;
        if (str4 == null || str4.length() <= 2) {
            return str4;
        }
        File file4 = new File(str4);
        if (!file4.exists()) {
            file4.mkdir();
        }
        if (file4.isDirectory()) {
        }
        return str4;
    }

    public static boolean checkCanWrite(File file) {
        if (file == null) {
            return false;
        }
        if (file.canWrite()) {
            File file2 = new File(file, "amap.tmp");
            try {
                file2.createNewFile();
                if (file2 == null || !file2.exists()) {
                    return false;
                }
                try {
                    file2.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return true;
    }

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
                    if (str.toLowerCase().contains("private")) {
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
                            if (externalFilesDirs == null) {
                                str = null;
                            } else if (externalFilesDirs.length > 1) {
                                str = externalFilesDirs[1].getAbsolutePath();
                            }
                        } catch (Exception e) {
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
            } catch (Throwable th) {
            }
        }
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    public static void writeDatasToFile(String str, byte[] bArr) {
        ReentrantReadWriteLock.WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        try {
            if (bArr != null) {
                if (bArr.length != 0) {
                    File file = new File(str);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(bArr);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static byte[] readFileContents(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createNoMediaFileIfNotExist(String str) {
    }

    public static void saveFile(String str, String str2, boolean z) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + str2);
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, z);
            fileOutputStream.write(str.getBytes("utf-8"));
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
