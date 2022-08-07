package com.yolanda.nohttp.cookie;

import java.net.HttpCookie;
import java.net.URI;

/* loaded from: classes2.dex */
public interface CookieStoreListener {
    void onRemoveCookie(URI uri, HttpCookie httpCookie);

    void onSaveCookie(URI uri, HttpCookie httpCookie);
}
