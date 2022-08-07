package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public class EMACallConfigs {
    public EMACallConfigs(EMACallConfigs eMACallConfigs) {
        nativeInit(eMACallConfigs);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public boolean getIsSendPushIfOffline() {
        return nativeGetIsSendPushIfOffline();
    }

    public long getVideoKbps() {
        return nativeGetVideoKbps();
    }

    public long getVideoResolutionHeight() {
        return nativeGetVideoResolutionHeight();
    }

    public long getVideoResolutionWidth() {
        return nativeGetVideoResolutionWidth();
    }

    native void nativeFinalize();

    native boolean nativeGetIsSendPushIfOffline();

    native long nativeGetVideoKbps();

    native long nativeGetVideoResolutionHeight();

    native long nativeGetVideoResolutionWidth();

    native void nativeInit();

    native void nativeInit(EMACallConfigs eMACallConfigs);

    native void nativeSetIsSendPushIfOffline(boolean z);

    native long nativeSetVideoKbps(long j);

    native long nativeSetVideoResolution(long j, long j2);

    public void setIsSendPushIfOffline(boolean z) {
        nativeSetIsSendPushIfOffline(z);
    }

    public void setVideoKbps(long j) {
        nativeSetVideoKbps(j);
    }

    public void setVideoResolution(long j, long j2) {
        nativeSetVideoResolution(j, j2);
    }
}
