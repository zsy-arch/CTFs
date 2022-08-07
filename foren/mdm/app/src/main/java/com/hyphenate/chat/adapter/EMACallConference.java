package com.hyphenate.chat.adapter;

import com.hyphenate.chat.adapter.EMACallSession;
import java.util.List;

/* loaded from: classes2.dex */
public class EMACallConference extends EMABase {
    public EMACallConference() {
        nativeInit();
    }

    public EMACallConference(EMACallConference eMACallConference) {
        nativeInit(eMACallConference);
    }

    public void close() {
        nativeClose();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public String getCallId() {
        return nativeGetCallId();
    }

    public String getLocalName() {
        return nativeGetLocalName();
    }

    public List<EMACallStream> getSubscribableStreams() {
        return nativeGetSubscribableStreams();
    }

    public List<EMACallStream> getSubscribedStreams() {
        return nativeGetSubscribedStreams();
    }

    public EMACallSession.Type getType() {
        int nativeGetType = nativeGetType();
        return nativeGetType == EMACallSession.Type.VIDEO.ordinal() ? EMACallSession.Type.VIDEO : nativeGetType == EMACallSession.Type.VOICE.ordinal() ? EMACallSession.Type.VOICE : EMACallSession.Type.VOICE;
    }

    public void leave() {
        nativeLeave();
    }

    native void nativeClose();

    native void nativeFinalize();

    native String nativeGetCallId();

    native String nativeGetLocalName();

    native List<EMACallStream> nativeGetSubscribableStreams();

    native List<EMACallStream> nativeGetSubscribedStreams();

    native int nativeGetType();

    native void nativeInit();

    native void nativeInit(EMACallConference eMACallConference);

    native void nativeLeave();
}
