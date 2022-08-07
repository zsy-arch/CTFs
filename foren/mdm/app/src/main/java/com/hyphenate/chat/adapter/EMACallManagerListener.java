package com.hyphenate.chat.adapter;

import com.superrtc.sdk.RtcConnection;

/* loaded from: classes2.dex */
public class EMACallManagerListener extends EMABase implements EMACallManagerListenerInterface {
    public EMACallManagerListener() {
        nativeInit();
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native void nativeFinalize();

    native void nativeInit();

    public void onConferenceClosed(EMACallConference eMACallConference) {
    }

    public void onConferenceMemberJoined(EMACallConference eMACallConference, String str) {
    }

    public void onConferenceMemberLeaved(EMACallConference eMACallConference, String str) {
    }

    public void onConferenceMemberPublished(EMACallConference eMACallConference, String str) {
    }

    public void onConferenceMembersUpdated(EMACallConference eMACallConference) {
    }

    @Override // com.hyphenate.chat.adapter.EMACallManagerListenerInterface
    public void onConferenceStreamClosed(EMACallConference eMACallConference, EMACallStream eMACallStream, EMAError eMAError) {
    }

    @Override // com.hyphenate.chat.adapter.EMACallManagerListenerInterface
    public void onConferenceStreamConnected(EMACallConference eMACallConference, EMACallStream eMACallStream) {
    }

    public void onNewRtcConnection(String str, int i, EMACallStream eMACallStream, String str2, RtcConnection.Listener listener, EMACallRtcImpl eMACallRtcImpl) {
    }

    public void onRecvCallAccepted(EMACallSession eMACallSession) {
    }

    public void onRecvCallConnected(EMACallSession eMACallSession) {
    }

    public void onRecvCallEnded(EMACallSession eMACallSession, int i, EMAError eMAError) {
    }

    public void onRecvCallFeatureUnsupported(EMACallSession eMACallSession, EMAError eMAError) {
    }

    public void onRecvCallIncoming(EMACallSession eMACallSession) {
    }

    public void onRecvCallNetworkStatusChanged(EMACallSession eMACallSession, int i) {
    }

    public void onRecvCallStateChanged(EMACallSession eMACallSession, int i) {
    }

    public void onSendPushMessage(String str, String str2) {
    }
}
