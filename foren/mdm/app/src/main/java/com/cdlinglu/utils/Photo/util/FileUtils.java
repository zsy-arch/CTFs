package com.cdlinglu.utils.Photo.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;

/* loaded from: classes.dex */
public class FileUtils {
    public static void saveBitmap(Bitmap bitmap, String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveBitmap(Context context, Bitmap bitmap, String filePath, boolean showAlbum) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            if (showAlbum) {
                saveAlbum(context, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveBitmap(Context context, Bitmap bitmap, String fileFolder, String fileName, boolean showAlbum) {
        saveBitmap(context, bitmap, fileFolder + File.separator + fileName + ".jpg", showAlbum);
    }

    public static void saveAlbum(Context context, File file) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }

    public static void saveView(Context context, View view, String path) {
        if (view != null) {
            view.setDrawingCacheEnabled(true);
            saveBitmap(context, view.getDrawingCache(), path, true);
        }
    }

    public static boolean isFileExist(String filePath, String fileName) {
        File file = new File(filePath + File.separator + fileName);
        file.isFile();
        return file.exists();
    }

    public static void delFile(String filePath, String fileName) {
        File file = new File(filePath + File.separator + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    public static void deleteDir(String filePath) {
        File dir = new File(filePath);
        if (dir != null && dir.exists() && dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            for (File file : listFiles) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            dir.delete();
        }
    }

    public static boolean fileIsExists(String path) {
        try {
            if (!new File(path).exists()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
