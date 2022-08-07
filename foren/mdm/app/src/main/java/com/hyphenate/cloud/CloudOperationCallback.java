package com.hyphenate.cloud;

/* loaded from: classes2.dex */
public interface CloudOperationCallback {
    void onError(String str);

    void onProgress(int i);

    void onSuccess(String str);
}
