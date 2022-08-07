package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class ServerError extends Exception {
    private static final long serialVersionUID = 115460;

    public ServerError() {
    }

    public ServerError(String detailMessage) {
        super(detailMessage);
    }

    public ServerError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ServerError(Throwable throwable) {
        super(throwable);
    }
}
