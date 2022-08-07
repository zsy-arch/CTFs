package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class URLError extends Exception {
    private static final long serialVersionUID = 114946;

    public URLError() {
    }

    public URLError(String detailMessage) {
        super(detailMessage);
    }

    public URLError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public URLError(Throwable throwable) {
        super(throwable);
    }
}
