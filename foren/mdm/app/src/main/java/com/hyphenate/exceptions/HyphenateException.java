package com.hyphenate.exceptions;

import com.hyphenate.chat.adapter.EMAError;

/* loaded from: classes2.dex */
public class HyphenateException extends Exception {
    private static final long serialVersionUID = 1;
    protected String desc;
    protected int errorCode;

    public HyphenateException() {
        this.errorCode = -1;
        this.desc = "";
    }

    public HyphenateException(int i, String str) {
        super(str);
        this.errorCode = -1;
        this.desc = "";
        this.errorCode = i;
        this.desc = str;
    }

    public HyphenateException(EMAError eMAError) {
        super(eMAError.errMsg());
        this.errorCode = -1;
        this.desc = "";
        this.errorCode = eMAError.errCode();
    }

    public HyphenateException(String str) {
        super(str);
        this.errorCode = -1;
        this.desc = "";
    }

    public HyphenateException(String str, Throwable th) {
        super(str);
        this.errorCode = -1;
        this.desc = "";
        super.initCause(th);
    }

    public String getDescription() {
        return this.desc;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }
}
