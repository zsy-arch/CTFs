package com.yolanda.nohttp.tools;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StatFs;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public class FileUtil {
    @TargetApi(18)
    public static long getDirSize(String path) {
        long blockSize;
        long availableBlocks;
        StatFs stat = new StatFs(path);
        if (Build.VERSION.SDK_INT >= 18) {
            blockSize = stat.getBlockSizeLong();
            availableBlocks = stat.getAvailableBlocksLong();
        } else {
            blockSize = stat.getBlockSize();
            availableBlocks = stat.getAvailableBlocks();
        }
        return availableBlocks * blockSize;
    }

    public static boolean canWrite(String path) {
        return new File(path).canWrite();
    }

    public static boolean canRead(String path) {
        return new File(path).canRead();
    }

    public static boolean createFolder(String folderPath) {
        if (!TextUtils.isEmpty(folderPath)) {
            return createFolder(new File(folderPath));
        }
        return false;
    }

    public static boolean createFolder(File targetFolder) {
        if (targetFolder.exists()) {
            return true;
        }
        return targetFolder.mkdirs();
    }

    public static boolean createFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            return createFile(new File(filePath));
        }
        return false;
    }

    public static boolean createFile(File targetFile) {
        if (targetFile.exists()) {
            return true;
        }
        try {
            return targetFile.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean createNewFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            return createNewFile(new File(filePath));
        }
        return false;
    }

    public static boolean createNewFile(File targetFile) {
        if (targetFile.exists()) {
            targetFile.delete();
        }
        try {
            return targetFile.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    public static String getMimeTypeByUrl(String url) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
    }
}
