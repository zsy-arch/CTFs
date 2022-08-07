package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class ClientError extends Exception {
    private static final long serialVersionUID = 11561;

    public ClientError() {
    }

    public ClientError(String detailMessage) {
        super(detailMessage);
    }

    public ClientError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ClientError(Throwable throwable) {
        super(throwable);
    }
}
