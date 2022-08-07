package com.cdlinglu.server.audio;

import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class Utils {
    public static void cleanDirectory(File file) throws IOException {
        File[] contentFiles;
        if (file.exists() && (contentFiles = file.listFiles()) != null) {
            for (File contentFile : contentFiles) {
                delete(contentFile);
            }
        }
    }

    private static void delete(File file) throws IOException {
        if (!file.isFile() || !file.exists()) {
            cleanDirectory(file);
            deleteOrThrow(file);
            return;
        }
        deleteOrThrow(file);
    }

    private static void deleteOrThrow(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException(String.format("File %s can't be deleted", file.getAbsolutePath()));
        }
    }
}
