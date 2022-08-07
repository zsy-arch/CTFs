package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class NetworkError extends Exception {
    private static final long serialVersionUID = 11548468;

    public NetworkError() {
    }

    public NetworkError(String detailMessage) {
        super(detailMessage);
    }

    public NetworkError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NetworkError(Throwable throwable) {
        super(throwable);
    }
}
