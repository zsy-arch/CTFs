package com.hyphenate;

/* loaded from: classes2.dex */
public interface EMValueCallBack<T> {
    void onError(int i, String str);

    void onSuccess(T t);
}
