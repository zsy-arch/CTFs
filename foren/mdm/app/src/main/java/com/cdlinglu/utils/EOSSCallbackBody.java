package com.cdlinglu.utils;

import com.alipay.sdk.util.h;

/* loaded from: classes.dex */
public enum EOSSCallbackBody {
    bucket,
    object,
    etag,
    size,
    mimeType,
    imageInfo_height,
    imageInfo_width,
    imageInfo_format;

    @Override // java.lang.Enum
    public String toString() {
        return name().replaceAll("_", ".");
    }

    public static EOSSCallbackBody[] getValues(boolean isImg) {
        return isImg ? values() : new EOSSCallbackBody[]{bucket, etag, object, size, mimeType};
    }

    public static String getCallbackBodyStr(EOSSCallbackBody[] sysCallbackBody) {
        if (sysCallbackBody == null || sysCallbackBody.length <= 0) {
            return null;
        }
        StringBuffer callbackBody = new StringBuffer("{");
        int i = 0;
        for (EOSSCallbackBody cb : sysCallbackBody) {
            String cbName = cb.toString();
            callbackBody.append(String.format("\"%s\":${%s}", cbName, cbName));
            if (i < sysCallbackBody.length - 1) {
                callbackBody.append(",");
            }
            i++;
        }
        callbackBody.append(h.d);
        return callbackBody.toString();
    }
}
