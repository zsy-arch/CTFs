package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class NotFoundFileError extends Exception {
    private static final long serialVersionUID = 115361;

    public NotFoundFileError() {
    }

    public NotFoundFileError(String detailMessage) {
        super(detailMessage);
    }

    public NotFoundFileError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NotFoundFileError(Throwable throwable) {
        super(throwable);
    }
}
