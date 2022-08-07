package com.hyphenate.chat.adapter.message;

/* loaded from: classes2.dex */
public class EMAVoiceMessageBody extends EMAFileMessageBody {
    private EMAVoiceMessageBody() {
        super("", 4);
        nativeInit("", 0);
    }

    public EMAVoiceMessageBody(EMAVoiceMessageBody eMAVoiceMessageBody) {
        super("", 4);
        nativeInit(eMAVoiceMessageBody);
    }

    public EMAVoiceMessageBody(String str, int i) {
        super(str, 4);
        nativeInit(str, i);
    }

    public int duration() {
        return nativeDuration();
    }

    @Override // com.hyphenate.chat.adapter.message.EMAFileMessageBody
    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native int nativeDuration();

    @Override // com.hyphenate.chat.adapter.message.EMAFileMessageBody
    native void nativeFinalize();

    native void nativeInit(EMAVoiceMessageBody eMAVoiceMessageBody);

    @Override // com.hyphenate.chat.adapter.message.EMAFileMessageBody
    native void nativeInit(String str, int i);

    native void nativeSetDuration(int i);

    public void setDuration(int i) {
        nativeSetDuration(i);
    }
}
