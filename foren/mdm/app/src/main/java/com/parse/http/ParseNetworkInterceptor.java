package com.parse.http;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface ParseNetworkInterceptor {

    /* loaded from: classes2.dex */
    public interface Chain {
        ParseHttpRequest getRequest();

        ParseHttpResponse proceed(ParseHttpRequest parseHttpRequest) throws IOException;
    }

    ParseHttpResponse intercept(Chain chain) throws IOException;
}
