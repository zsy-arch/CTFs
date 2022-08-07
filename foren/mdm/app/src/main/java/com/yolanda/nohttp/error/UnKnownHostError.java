package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class UnKnownHostError extends Exception {
    private static final long serialVersionUID = 1149646;

    public UnKnownHostError() {
    }

    public UnKnownHostError(String detailMessage) {
        super(detailMessage);
    }

    public UnKnownHostError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public UnKnownHostError(Throwable throwable) {
        super(throwable);
    }
}
