package com.hyphenate.chat.adapter.message;

/* loaded from: classes2.dex */
public class EMATextMessageBody extends EMAMessageBody {
    private EMATextMessageBody() {
        nativeInit("");
    }

    public EMATextMessageBody(EMATextMessageBody eMATextMessageBody) {
        nativeInit(eMATextMessageBody);
    }

    public EMATextMessageBody(String str) {
        nativeInit(str);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native void nativeFinalize();

    native void nativeInit(EMATextMessageBody eMATextMessageBody);

    native void nativeInit(String str);

    native String nativeText();

    public String text() {
        return nativeText();
    }
}
