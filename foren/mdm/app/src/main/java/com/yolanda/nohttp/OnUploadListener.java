package com.yolanda.nohttp;

/* loaded from: classes2.dex */
public interface OnUploadListener extends ProgressHandler {
    void onCancel(int i);

    void onError(int i, Exception exc);

    void onFinish(int i);

    void onStart(int i);
}
