package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public class ExecutionContext<T extends OSSRequest> {
    private CancellationHandler cancellationHandler = new CancellationHandler();
    private OkHttpClient client;
    private OSSCompletedCallback completedCallback;
    private OSSProgressCallback progressCallback;
    private T request;

    public ExecutionContext(OkHttpClient client, T request) {
        setClient(client);
        setRequest(request);
    }

    public T getRequest() {
        return this.request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public OkHttpClient getClient() {
        return this.client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public CancellationHandler getCancellationHandler() {
        return this.cancellationHandler;
    }

    public OSSCompletedCallback getCompletedCallback() {
        return this.completedCallback;
    }

    public void setCompletedCallback(OSSCompletedCallback completedCallback) {
        this.completedCallback = completedCallback;
    }

    public OSSProgressCallback getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(OSSProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
    }
}
