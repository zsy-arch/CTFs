package com.cdlinglu.utils;

import android.content.Context;
import android.os.Build;
import com.hy.frame.util.MyLog;
import com.tencent.smtt.sdk.CacheManager;
import com.vsf2f.f2f.ui.utils.area.ConvertUtils;
import java.io.File;

/* loaded from: classes.dex */
public class CacheUtil {
    private Context context;

    public CacheUtil(Context context) {
        this.context = context;
    }

    public String getCacheSize() {
        long fileSize = 0 + getDirSize(this.context.getApplicationContext().getFilesDir()) + getDirSize(this.context.getCacheDir());
        if (isMethodsCompat(8)) {
            fileSize += getDirSize(getExternalCacheDir(this.context));
        }
        if (fileSize > 0) {
            return formatFileSize(fileSize);
        }
        return "0KB";
    }

    public int clearAppCache() {
        int files = clearCacheFolder(this.context.getFilesDir(), System.currentTimeMillis());
        int cache = clearCacheFolder(this.context.getCacheDir(), System.currentTimeMillis());
        int exts = 0;
        if (isMethodsCompat(8)) {
            exts = clearCacheFolder(getExternalCacheDir(this.context), System.currentTimeMillis());
        }
        int total = files + cache + exts;
        MyLog.e("total:" + total + ";files:" + files + ";cache:" + cache + ";exts:" + exts);
        return total;
    }

    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                File[] listFiles = dir.listFiles();
                for (File child : listFiles) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime && child.delete()) {
                        deletedFiles++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    public static long getDirSize(File dir) {
        long dirSize = 0;
        if (dir != null && dir.isDirectory()) {
            dirSize = 0;
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    dirSize += file.length();
                } else if (file.isDirectory()) {
                    dirSize = dirSize + file.length() + getDirSize(file);
                }
            }
        }
        return dirSize;
    }

    public static String formatFileSize(long length) {
        if (length >= ConvertUtils.GB) {
            return ((((float) length) / 1.07374182E9f) + "000").substring(0, String.valueOf(((float) length) / 1.07374182E9f).indexOf(".") + 3) + "GB";
        } else if (length >= 1048576) {
            return ((((float) length) / 1048576.0f) + "000").substring(0, String.valueOf(((float) length) / 1048576.0f).indexOf(".") + 3) + "MB";
        } else if (length >= 1024) {
            return ((((float) length) / 1024.0f) + "000").substring(0, String.valueOf(((float) length) / 1024.0f).indexOf(".") + 3) + "KB";
        } else if (length < 1024) {
            return Long.toString(length) + "B";
        } else {
            return null;
        }
    }

    public static boolean isMethodsCompat(int VersionCode) {
        return Build.VERSION.SDK_INT >= VersionCode;
    }

    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    public void clearWebCache() {
        File file = CacheManager.getCacheFileBaseDir();
        if (file != null && file.exists() && file.isDirectory()) {
            for (File item : file.listFiles()) {
                item.delete();
            }
            file.delete();
        }
        this.context.deleteDatabase("webview.db");
        this.context.deleteDatabase("webview.db-shm");
        this.context.deleteDatabase("webview.db-wal");
        this.context.deleteDatabase("webviewCache.db");
        this.context.deleteDatabase("webviewCache.db-shm");
        this.context.deleteDatabase("webviewCache.db-wal");
        clearCacheFolder(this.context.getFilesDir(), System.currentTimeMillis());
        clearCacheFolder(this.context.getCacheDir(), System.currentTimeMillis());
        if (isMethodsCompat(8)) {
            clearCacheFolder(getExternalCacheDir(this.context), System.currentTimeMillis());
        }
    }
}
