package com.hyphenate.chat.adapter.message;

/* loaded from: classes2.dex */
public class EMALocationMessageBody extends EMAMessageBody {
    public String address;
    public double latitude;
    public double longitude;

    private EMALocationMessageBody() {
        nativeInit(0.0d, 0.0d, "");
    }

    public EMALocationMessageBody(double d, double d2, String str) {
        nativeInit(d, d2, str);
    }

    public EMALocationMessageBody(EMALocationMessageBody eMALocationMessageBody) {
        nativeInit(eMALocationMessageBody);
    }

    public String address() {
        return nativeaddress();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public double latitude() {
        return nativelatitude();
    }

    public double longitude() {
        return nativelongitude();
    }

    native void nativeFinalize();

    native void nativeInit(double d, double d2, String str);

    native void nativeInit(EMALocationMessageBody eMALocationMessageBody);

    native String nativeaddress();

    native double nativelatitude();

    native double nativelongitude();

    native void nativesetAddress(String str);

    native void nativesetLatitude(double d);

    native void nativesetLongitude(double d);

    public void setAddress(String str) {
        nativesetAddress(str);
    }

    public void setLatitude(double d) {
        nativesetLatitude(d);
    }

    public void setLongitude(double d) {
        nativesetLongitude(d);
    }
}
