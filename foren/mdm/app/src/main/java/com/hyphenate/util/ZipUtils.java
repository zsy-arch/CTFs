package com.hyphenate.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: classes2.dex */
public class ZipUtils {
    private static final int BUFF_SIZE = 1048576;

    public static void zip(File file, File file2) throws IOException {
        if (file.exists()) {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2), 1048576));
            zipFiles(file, zipOutputStream, "");
            zipOutputStream.flush();
            zipOutputStream.close();
        }
    }

    static void zipFile(File file, ZipOutputStream zipOutputStream, String str) throws IOException {
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        if ("".equals(str)) {
            file.getName();
        } else {
            String str2 = str + "\\" + file.getName();
        }
        zipOutputStream.putNextEntry(new ZipEntry(str));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                zipOutputStream.write(bArr, 0, read);
            } else {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
        }
    }

    static void zipFiles(File file, ZipOutputStream zipOutputStream, String str) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        zipFiles(file2, zipOutputStream, str + "\\" + file2.getName());
                    }
                    return;
                }
                return;
            }
            zipFile(file, zipOutputStream, str);
        }
    }
}
