package com.hy.frame.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes2.dex */
public class FolderUtil {
    private static final String FILE_FOLDER = "FaceToFace";

    public static String getString(Context context, int resId) {
        return context.getString(resId);
    }

    public static String getCachePath() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        String path = Environment.getExternalStorageDirectory().getPath() + File.separator + FILE_FOLDER;
        File file = new File(path);
        if (file.exists()) {
            return path;
        }
        file.mkdirs();
        return path;
    }

    public static String getCacheSize(Context context) {
        String path = getCachePath();
        return path == null ? "0M" : FileUtil.getAutoFileOrFilesSize(path);
    }

    public static void clearCache() {
        FileUtil.delAllFile(getCachePath());
    }

    public static String getCacheUserCode() {
        String path = getCachePath();
        if (path == null) {
            return null;
        }
        String path2 = path + File.separator + FILE_FOLDER + "_User";
        File file = new File(path2);
        if (file.exists()) {
            return path2;
        }
        file.mkdirs();
        return path2;
    }

    public static String getCachePathData() {
        String path = getCachePath();
        if (path == null) {
            return null;
        }
        String path2 = path + File.separator + FILE_FOLDER + "_Data";
        File file = new File(path2);
        if (file.exists()) {
            return path2;
        }
        file.mkdirs();
        return path2;
    }

    public static String getCachePathHead() {
        String path = getCachePath();
        if (path == null) {
            return null;
        }
        String path2 = path + File.separator + FILE_FOLDER + "_Head";
        File file = new File(path2);
        if (file.exists()) {
            return path2;
        }
        file.mkdirs();
        return path2;
    }

    public static String getCachePathImage() {
        String path = getCachePath();
        if (path == null) {
            return null;
        }
        String path2 = path + File.separator + FILE_FOLDER + "_Image";
        File file = new File(path2);
        if (file.exists()) {
            return path2;
        }
        file.mkdirs();
        return path2;
    }

    public static String getCachePathVideo() {
        String path = getCachePath();
        if (path == null) {
            return null;
        }
        String path2 = path + File.separator + FILE_FOLDER + "_Video";
        File file = new File(path2);
        if (file.exists()) {
            return path2;
        }
        file.mkdirs();
        return path2;
    }

    public static String getCachePathAlbum() {
        String path = getCachePath();
        if (path == null) {
            return null;
        }
        String path2 = path + File.separator + FILE_FOLDER + "_Album";
        File file = new File(path2);
        if (file.exists()) {
            return path2;
        }
        file.mkdirs();
        return path2;
    }

    public static String getCachePathCrop() {
        String path = getCachePath();
        if (path == null) {
            return null;
        }
        String path2 = path + File.separator + FILE_FOLDER + "_CropFile";
        File file = new File(path2);
        if (file.exists()) {
            return path2;
        }
        file.mkdirs();
        return path2;
    }

    public static String getCachePathAudio() {
        String path = getCachePath();
        if (path == null) {
            return null;
        }
        String path2 = path + File.separator + FILE_FOLDER + "_AudioFile";
        File file = new File(path2);
        if (file.exists()) {
            return path2;
        }
        file.mkdirs();
        return path2;
    }

    public static String getCachePathAudioC() {
        String path = getCachePath();
        if (path == null) {
            return null;
        }
        String path2 = path + File.separator + FILE_FOLDER + "_AudioFileC";
        File file = new File(path2);
        if (file.exists()) {
            return path2;
        }
        file.mkdirs();
        return path2;
    }

    public static void deleteDir(String SDPath) {
        if (!TextUtils.isEmpty(SDPath)) {
            File dir = new File(SDPath);
            if (dir.exists() && dir.isDirectory() && dir.length() >= 1 && dir.listFiles() != null) {
                File[] listFiles = dir.listFiles();
                for (File file : listFiles) {
                    if (file.isFile()) {
                        file.delete();
                    } else if (file.isDirectory()) {
                        deleteDir(file.getPath());
                    }
                }
                dir.delete();
            }
        }
    }
}
