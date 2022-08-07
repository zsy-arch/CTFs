package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public class EMARHttpCallback extends EMABase {
    EMARHttpCallback() {
        nativeInit();
    }

    public EMARHttpCallback(EMARHttpCallback eMARHttpCallback) {
        nativeInit();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native void nativeFinalize();

    native void nativeInit();

    native void nativeInit(EMARHttpCallback eMARHttpCallback);

    native void native_onProgress(double d, double d2);

    public void onProgress(double d, double d2) {
        native_onProgress(d, d2);
    }
}
