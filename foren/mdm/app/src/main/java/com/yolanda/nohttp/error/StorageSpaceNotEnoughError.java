package com.yolanda.nohttp.error;

/* loaded from: classes2.dex */
public class StorageSpaceNotEnoughError extends Exception {
    private static final long serialVersionUID = 11786348;

    public StorageSpaceNotEnoughError() {
    }

    public StorageSpaceNotEnoughError(String detailMessage) {
        super(detailMessage);
    }

    public StorageSpaceNotEnoughError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public StorageSpaceNotEnoughError(Throwable throwable) {
        super(throwable);
    }
}
