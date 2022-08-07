package com.yolanda.nohttp;

/* loaded from: classes2.dex */
public interface ImplRestParser {
    <T> Response<T> parserRequest(Request<T> request);
}
