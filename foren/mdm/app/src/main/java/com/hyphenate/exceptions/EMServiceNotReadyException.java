package com.hyphenate.exceptions;

/* loaded from: classes2.dex */
public class EMServiceNotReadyException extends HyphenateException {
    private static final long serialVersionUID = 1;

    public EMServiceNotReadyException() {
    }

    public EMServiceNotReadyException(int i, String str) {
        super(i, str);
    }

    public EMServiceNotReadyException(String str) {
        super(str);
    }

    public EMServiceNotReadyException(String str, Throwable th) {
        super(str);
        super.initCause(th);
    }
}
