package com.lidroid.xutils.exception;

/* loaded from: classes2.dex */
public class DbException extends BaseException {
    private static final long serialVersionUID = 1;

    public DbException() {
    }

    public DbException(String detailMessage) {
        super(detailMessage);
    }

    public DbException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DbException(Throwable throwable) {
        super(throwable);
    }
}
