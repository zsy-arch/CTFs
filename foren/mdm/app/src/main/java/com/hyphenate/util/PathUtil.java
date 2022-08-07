package com.hyphenate.util;

import android.content.Context;
import android.os.Environment;
import java.io.File;

/* loaded from: classes2.dex */
public class PathUtil {
    public static final String filePathName = "/file/";
    public static final String historyPathName = "/chat/";
    public static final String imagePathName = "/image/";
    public static final String meetingPathName = "/meeting/";
    public static final String netdiskDownloadPathName = "/netdisk/";
    public static String pathPrefix = null;
    public static final String videoPathName = "/video/";
    public static final String voicePathName = "/voice/";
    private File filePath;
    private static File storageDir = null;
    private static PathUtil instance = null;
    private File voicePath = null;
    private File imagePath = null;
    private File historyPath = null;
    private File videoPath = null;

    private PathUtil() {
    }

    private static File generateFiePath(String str, String str2, Context context) {
        return new File(getStorageDir(context), str == null ? pathPrefix + str2 + filePathName : pathPrefix + str + "/" + str2 + filePathName);
    }

    private static File generateHistoryPath(String str, String str2, Context context) {
        return new File(getStorageDir(context), str == null ? pathPrefix + str2 + historyPathName : pathPrefix + str + "/" + str2 + historyPathName);
    }

    private static File generateImagePath(String str, String str2, Context context) {
        return new File(getStorageDir(context), str == null ? pathPrefix + str2 + imagePathName : pathPrefix + str + "/" + str2 + imagePathName);
    }

    private static File generateVideoPath(String str, String str2, Context context) {
        return new File(getStorageDir(context), str == null ? pathPrefix + str2 + videoPathName : pathPrefix + str + "/" + str2 + videoPathName);
    }

    private static File generateVoicePath(String str, String str2, Context context) {
        return new File(getStorageDir(context), str == null ? pathPrefix + str2 + voicePathName : pathPrefix + str + "/" + str2 + voicePathName);
    }

    public static PathUtil getInstance() {
        if (instance == null) {
            instance = new PathUtil();
        }
        return instance;
    }

    private static File getStorageDir(Context context) {
        if (storageDir == null) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory.exists()) {
                return externalStorageDirectory;
            }
            storageDir = context.getFilesDir();
        }
        return storageDir;
    }

    public static File getTempPath(File file) {
        return new File(file.getAbsoluteFile() + ".tmp");
    }

    public File getFilePath() {
        return this.filePath;
    }

    public File getHistoryPath() {
        return this.historyPath;
    }

    public File getImagePath() {
        return this.imagePath;
    }

    public File getVideoPath() {
        return this.videoPath;
    }

    public File getVoicePath() {
        return this.voicePath;
    }

    public void initDirs(String str, String str2, Context context) {
        pathPrefix = "/Android/data/" + context.getPackageName() + "/";
        this.voicePath = generateVoicePath(str, str2, context);
        if (!this.voicePath.exists()) {
            this.voicePath.mkdirs();
        }
        this.imagePath = generateImagePath(str, str2, context);
        if (!this.imagePath.exists()) {
            this.imagePath.mkdirs();
        }
        this.historyPath = generateHistoryPath(str, str2, context);
        if (!this.historyPath.exists()) {
            this.historyPath.mkdirs();
        }
        this.videoPath = generateVideoPath(str, str2, context);
        if (!this.videoPath.exists()) {
            this.videoPath.mkdirs();
        }
        this.filePath = generateFiePath(str, str2, context);
        if (!this.filePath.exists()) {
            this.filePath.mkdirs();
        }
    }
}
