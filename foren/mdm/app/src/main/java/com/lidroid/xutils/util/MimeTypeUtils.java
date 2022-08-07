package com.lidroid.xutils.util;

import android.webkit.MimeTypeMap;

/* loaded from: classes2.dex */
public class MimeTypeUtils {
    private MimeTypeUtils() {
    }

    public static String getMimeType(String fileName) {
        int extPos = fileName.lastIndexOf(".");
        if (extPos == -1) {
            return "application/octet-stream";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileName.substring(extPos + 1));
    }
}
