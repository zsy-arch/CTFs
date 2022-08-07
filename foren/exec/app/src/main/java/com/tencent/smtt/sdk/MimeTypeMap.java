package com.tencent.smtt.sdk;

/* loaded from: classes.dex */
public class MimeTypeMap {

    /* renamed from: a  reason: collision with root package name */
    public static MimeTypeMap f1126a;

    public static String getFileExtensionFromUrl(String str) {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getFileExtensionFromUrl(str) : a2.c().h(str);
    }

    public static synchronized MimeTypeMap getSingleton() {
        MimeTypeMap mimeTypeMap;
        synchronized (MimeTypeMap.class) {
            if (f1126a == null) {
                f1126a = new MimeTypeMap();
            }
            mimeTypeMap = f1126a;
        }
        return mimeTypeMap;
    }

    public String getExtensionFromMimeType(String str) {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getSingleton().getExtensionFromMimeType(str) : a2.c().l(str);
    }

    public String getMimeTypeFromExtension(String str) {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(str) : a2.c().j(str);
    }

    public boolean hasExtension(String str) {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getSingleton().hasExtension(str) : a2.c().k(str);
    }

    public boolean hasMimeType(String str) {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getSingleton().hasMimeType(str) : a2.c().i(str);
    }
}
