package com.hyphenate.chat.adapter;

import com.hyphenate.EMCallBack;

/* loaded from: classes2.dex */
public class EMACallback extends EMABase {
    private EMCallBack owner;

    public EMACallback(EMCallBack eMCallBack) {
        this.owner = null;
        this.owner = eMCallBack;
        nativeInit();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native void nativeFinalize();

    native void nativeInit();

    public void onError(int i, String str) {
        if (this.owner != null) {
            this.owner.onError(i, str);
            this.owner = null;
        }
    }

    public void onProgress(int i, String str) {
        if (this.owner != null) {
            this.owner.onProgress(i, str);
        }
    }

    public void onSuccess() {
        if (this.owner != null) {
            this.owner.onSuccess();
            this.owner = null;
        }
    }

    void setOwner(EMCallBack eMCallBack) {
        this.owner = eMCallBack;
    }
}
