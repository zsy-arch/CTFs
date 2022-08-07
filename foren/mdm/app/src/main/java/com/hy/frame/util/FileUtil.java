package com.hy.frame.util;

import android.util.Log;
import com.vsf2f.f2f.ui.utils.area.ConvertUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

/* loaded from: classes2.dex */
public class FileUtil {
    public static final int SIZETYPE_B = 1;
    public static final int SIZETYPE_GB = 4;
    public static final int SIZETYPE_KB = 2;
    public static final int SIZETYPE_MB = 3;

    public static double getFileOrFilesSize(String filePath, int sizeType) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        return FormatFileSize(blockSize, sizeType);
    }

    public static String getAutoFileOrFilesSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        return FormatFileSize(blockSize);
    }

    private static long getFileSize(File file) throws Exception {
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            long size = fis.available();
            fis.close();
            return size;
        }
        file.createNewFile();
        Log.e("获取文件大小", "文件不存在!");
        return 0L;
    }

    private static long getFileSizes(File f) throws Exception {
        long fileSize;
        long size = 0;
        File[] flist = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                fileSize = getFileSizes(flist[i]);
            } else {
                fileSize = getFileSize(flist[i]);
            }
            size += fileSize;
        }
        return size;
    }

    private static String FormatFileSize(long fileS) {
        String fileSizeString;
        DecimalFormat df = new DecimalFormat("0.00");
        if (fileS == 0) {
            return "0B";
        }
        if (fileS < 1024) {
            fileSizeString = df.format(fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format(fileS / 1024.0d) + "KB";
        } else if (fileS < ConvertUtils.GB) {
            fileSizeString = df.format(fileS / 1048576.0d) + "MB";
        } else {
            fileSizeString = df.format(fileS / 1.073741824E9d) + "GB";
        }
        return fileSizeString;
    }

    private static double FormatFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("0.00");
        switch (sizeType) {
            case 1:
                return Double.valueOf(df.format(fileS)).doubleValue();
            case 2:
                return Double.valueOf(df.format(fileS / 1024.0d)).doubleValue();
            case 3:
                return Double.valueOf(df.format(fileS / 1048576.0d)).doubleValue();
            case 4:
                return Double.valueOf(df.format(fileS / 1.073741824E9d)).doubleValue();
            default:
                return 0.0d;
        }
    }

    public static void delAllFile(String path) {
        File temp;
        if (path != null) {
            File file = new File(path);
            if (file.exists() && file.isDirectory()) {
                String[] tempList = file.list();
                for (int i = 0; i < tempList.length; i++) {
                    if (path.endsWith(File.separator)) {
                        temp = new File(path + tempList[i]);
                    } else {
                        temp = new File(path + File.separator + tempList[i]);
                    }
                    if (temp.isFile()) {
                        temp.delete();
                    }
                    if (temp.isDirectory()) {
                        delAllFile(path + "/" + tempList[i]);
                        delFolder(path + "/" + tempList[i]);
                    }
                }
            }
        }
    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            new File(folderPath.toString()).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File downloadFile(String folderPath, String filename) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(folderPath + filename).openConnection();
            conn.connect();
            conn.getContentLength();
            InputStream is = conn.getInputStream();
            File file = new File("", "");
            if (file.exists()) {
                file.delete();
            }
            byte[] bArr = new byte[1024];
            new FileOutputStream(file).close();
            is.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
