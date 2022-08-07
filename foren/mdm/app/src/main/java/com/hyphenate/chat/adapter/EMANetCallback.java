package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public abstract class EMANetCallback extends EMABase {
    public EMANetCallback() {
        nativeInit();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public abstract int getNetState();

    native void nativeFinalize();

    native void nativeInit();
}
