package com.yolanda.nohttp.download;

import com.yolanda.nohttp.Headers;

/* loaded from: classes2.dex */
public interface DownloadListener {
    void onCancel(int i);

    void onDownloadError(int i, Exception exc);

    void onFinish(int i, String str);

    void onProgress(int i, int i2, long j);

    void onStart(int i, boolean z, long j, Headers headers, long j2);
}
