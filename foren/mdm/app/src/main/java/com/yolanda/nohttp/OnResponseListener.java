package com.yolanda.nohttp;

/* loaded from: classes2.dex */
public interface OnResponseListener<T> {
    void onFailed(int i, String str, Object obj, Exception exc, int i2, long j);

    void onFinish(int i);

    void onStart(int i);

    void onSucceed(int i, Response<T> response);
}
