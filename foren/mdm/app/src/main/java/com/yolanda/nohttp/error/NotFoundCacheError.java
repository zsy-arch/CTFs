package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class NotFoundCacheError extends Exception {
    private static final long serialVersionUID = 115481468;

    public NotFoundCacheError() {
    }

    public NotFoundCacheError(String detailMessage) {
        super(detailMessage);
    }

    public NotFoundCacheError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NotFoundCacheError(Throwable throwable) {
        super(throwable);
    }
}
