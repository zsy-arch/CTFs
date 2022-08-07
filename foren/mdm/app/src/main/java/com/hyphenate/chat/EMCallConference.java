package com.hyphenate.chat;

import com.hyphenate.chat.EMCallSession;
import com.hyphenate.chat.adapter.EMACallConference;
import com.hyphenate.chat.adapter.EMACallSession;
import com.hyphenate.chat.adapter.EMACallStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
class EMCallConference extends EMBase<EMACallConference> {
    EMACallConference emaObject;

    EMCallConference() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMCallConference(EMACallConference eMACallConference) {
        this.emaObject = eMACallConference;
    }

    public void close() {
        this.emaObject.close();
    }

    public String getCallId() {
        return this.emaObject.getCallId();
    }

    public String getLocalName() {
        return this.emaObject.getLocalName();
    }

    public List<EMCallStream> getSubscribableStreams() {
        List<EMACallStream> subscribableStreams = this.emaObject.getSubscribableStreams();
        ArrayList arrayList = new ArrayList();
        for (EMACallStream eMACallStream : subscribableStreams) {
            arrayList.add(new EMCallStream(eMACallStream));
        }
        return arrayList;
    }

    public List<EMCallStream> getSubscribedStreams() {
        List<EMACallStream> subscribedStreams = this.emaObject.getSubscribedStreams();
        ArrayList arrayList = new ArrayList();
        for (EMACallStream eMACallStream : subscribedStreams) {
            arrayList.add(new EMCallStream(eMACallStream));
        }
        return arrayList;
    }

    public EMCallSession.Type getType() {
        EMACallSession.Type type = this.emaObject.getType();
        return type == EMACallSession.Type.VIDEO ? EMCallSession.Type.VIDEO : type == EMACallSession.Type.VOICE ? EMCallSession.Type.VOICE : EMCallSession.Type.VOICE;
    }

    public void leave() {
        this.emaObject.leave();
    }
}
