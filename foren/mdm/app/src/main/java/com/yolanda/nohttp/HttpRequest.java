package com.yolanda.nohttp;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class HttpRequest<E> {
    public final Request<E> request;
    public final OnResponseListener<E> responseListener;
    public final int what;

    public HttpRequest(int what, Request<E> request, OnResponseListener<E> responseListener) {
        this.what = what;
        this.request = request;
        this.responseListener = responseListener;
    }
}
