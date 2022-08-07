package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public abstract class EMAConnectionListener extends EMABase implements EMAConnectionListenerInterface {
    public EMAConnectionListener() {
        nativeInit();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native void nativeFinalize();

    native void nativeInit();

    public void onConnected() {
    }

    public void onDisconnected(int i) {
    }
}
