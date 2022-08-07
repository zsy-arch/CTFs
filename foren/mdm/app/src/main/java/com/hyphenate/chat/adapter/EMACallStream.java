package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public class EMACallStream extends EMABase {
    public EMACallStream() {
        nativeInit();
    }

    public EMACallStream(EMACallStream eMACallStream) {
        nativeInit(eMACallStream);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public String getFullName() {
        return nativeGetFullName();
    }

    public String getMemId() {
        return nativeGetMemId();
    }

    public String getStreamId() {
        return nativeGetStreamId();
    }

    public String getUserName() {
        return nativeGetUserName();
    }

    native void nativeFinalize();

    native String nativeGetFullName();

    native String nativeGetMemId();

    native String nativeGetStreamId();

    native String nativeGetUserName();

    native void nativeInit();

    native void nativeInit(EMACallStream eMACallStream);
}
