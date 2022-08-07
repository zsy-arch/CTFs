package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public class EMAGroupSetting extends EMABase {
    public static final int EMAGroupStyle_PRIVATE_MEMBER_INVITE = 1;
    public static final int EMAGroupStyle_PRIVATE_OWNER_INVITE = 0;
    public static final int EMAGroupStyle_PUBLIC_ANONYMOUS = 4;
    public static final int EMAGroupStyle_PUBLIC_JOIN_APPROVAL = 2;
    public static final int EMAGroupStyle_PUBLIC_JOIN_OPEN = 3;

    public EMAGroupSetting() {
        nativeInit(0, 200);
    }

    public EMAGroupSetting(int i, int i2) {
        nativeInit(i, i2);
    }

    public EMAGroupSetting(EMAGroupSetting eMAGroupSetting) {
        nativeInit(eMAGroupSetting);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public int maxUserCount() {
        return nativeMaxUserCount();
    }

    native void nativeFinalize();

    native void nativeInit(int i, int i2);

    native void nativeInit(EMAGroupSetting eMAGroupSetting);

    native int nativeMaxUserCount();

    native void nativeSetMaxUserCount(int i);

    native void nativeSetStyle(int i);

    native int nativeStyle();

    public void setMaxuserCount(int i) {
        nativeSetMaxUserCount(i);
    }

    public void setStyle(int i) {
        nativeSetStyle(i);
    }

    public int style() {
        return nativeStyle();
    }
}
