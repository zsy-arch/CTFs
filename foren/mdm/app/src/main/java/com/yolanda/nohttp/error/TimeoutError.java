package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class TimeoutError extends Exception {
    private static final long serialVersionUID = 1164986;

    public TimeoutError() {
    }

    public TimeoutError(String detailMessage) {
        super(detailMessage);
    }

    public TimeoutError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public TimeoutError(Throwable throwable) {
        super(throwable);
    }
}
