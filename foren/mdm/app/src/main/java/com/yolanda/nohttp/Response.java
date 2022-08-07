package com.yolanda.nohttp;

import java.net.HttpCookie;
import java.util.List;

/* loaded from: classes2.dex */
public interface Response<T> {
    T get();

    byte[] getByteArray();

    List<HttpCookie> getCookies();

    Exception getException();

    Headers getHeaders();

    long getNetworkMillis();

    RequestMethod getRequestMethod();

    Object getTag();

    boolean isFromCache();

    boolean isSucceed();

    String url();
}
