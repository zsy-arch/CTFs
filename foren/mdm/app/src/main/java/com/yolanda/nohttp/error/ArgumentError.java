package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class ArgumentError extends Exception {
    private static final long serialVersionUID = 1516;

    public ArgumentError() {
    }

    public ArgumentError(String detailMessage) {
        super(detailMessage);
    }

    public ArgumentError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ArgumentError(Throwable throwable) {
        super(throwable);
    }
}
