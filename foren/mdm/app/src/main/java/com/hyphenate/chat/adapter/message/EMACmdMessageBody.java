package com.hyphenate.chat.adapter.message;

import java.util.Map;

/* loaded from: classes2.dex */
public class EMACmdMessageBody extends EMAMessageBody {
    private EMACmdMessageBody() {
        nativeInit("");
    }

    public EMACmdMessageBody(EMACmdMessageBody eMACmdMessageBody) {
        nativeInit(eMACmdMessageBody);
    }

    public EMACmdMessageBody(String str) {
        nativeInit(str);
    }

    public String action() {
        return nativeAction();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native String nativeAction();

    native void nativeFinalize();

    native void nativeInit(EMACmdMessageBody eMACmdMessageBody);

    native void nativeInit(String str);

    native Map<String, String> nativeParams();

    native void nativeSetAction(String str);

    native void nativeSetParams(Map<String, String> map);

    public Map<String, String> params() {
        return nativeParams();
    }

    public void setAction(String str) {
        nativeSetAction(str);
    }

    public void setParams(Map<String, String> map) {
        nativeSetParams(map);
    }
}
