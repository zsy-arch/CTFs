package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public class EMAPushConfigs extends EMABase {
    public EMAPushConfigs() {
        nativeInit();
    }

    public EMAPushConfigs(EMAPushConfigs eMAPushConfigs) {
        nativeInit(eMAPushConfigs);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public String getDisplayNickname() {
        return nativeGetDisplayNickname();
    }

    public int getNoDisturbEndHour() {
        return nativeGetNoDisturbEndHour();
    }

    public int getNoDisturbStartHour() {
        return nativeGetNoDisturbStartHour();
    }

    public boolean isNoDisturbOn() {
        return nativeIsNoDisturbOn();
    }

    native void nativeFinalize();

    native String nativeGetDisplayNickname();

    native int nativeGetNoDisturbEndHour();

    native int nativeGetNoDisturbStartHour();

    native void nativeInit();

    native void nativeInit(EMAPushConfigs eMAPushConfigs);

    native boolean nativeIsNoDisturbOn();
}
