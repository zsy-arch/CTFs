package com.hyphenate.exceptions;

/* loaded from: classes2.dex */
public class EMNoActiveCallException extends HyphenateException {
    private static final long serialVersionUID = 1;

    public EMNoActiveCallException() {
    }

    public EMNoActiveCallException(String str) {
        super(str);
    }

    public EMNoActiveCallException(String str, Throwable th) {
        super(str);
        super.initCause(th);
    }
}
