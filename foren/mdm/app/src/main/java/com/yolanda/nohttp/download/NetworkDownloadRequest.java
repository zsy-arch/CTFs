package com.yolanda.nohttp.download;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NetworkDownloadRequest {
    public final DownloadListener downloadListener;
    public final DownloadRequest downloadRequest;
    public final int what;

    public NetworkDownloadRequest(int what, DownloadRequest downloadRequest, DownloadListener downloadListener) {
        this.what = what;
        this.downloadRequest = downloadRequest;
        this.downloadListener = downloadListener;
    }
}
