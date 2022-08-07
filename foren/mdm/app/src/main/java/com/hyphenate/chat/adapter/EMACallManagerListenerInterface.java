package com.hyphenate.chat.adapter;

import com.superrtc.sdk.RtcConnection;

/* loaded from: classes2.dex */
public interface EMACallManagerListenerInterface {
    void onConferenceClosed(EMACallConference eMACallConference);

    void onConferenceMemberJoined(EMACallConference eMACallConference, String str);

    void onConferenceMemberLeaved(EMACallConference eMACallConference, String str);

    void onConferenceMemberPublished(EMACallConference eMACallConference, String str);

    void onConferenceMembersUpdated(EMACallConference eMACallConference);

    void onConferenceStreamClosed(EMACallConference eMACallConference, EMACallStream eMACallStream, EMAError eMAError);

    void onConferenceStreamConnected(EMACallConference eMACallConference, EMACallStream eMACallStream);

    void onNewRtcConnection(String str, int i, EMACallStream eMACallStream, String str2, RtcConnection.Listener listener, EMACallRtcImpl eMACallRtcImpl);

    void onRecvCallAccepted(EMACallSession eMACallSession);

    void onRecvCallConnected(EMACallSession eMACallSession);

    void onRecvCallEnded(EMACallSession eMACallSession, int i, EMAError eMAError);

    void onRecvCallFeatureUnsupported(EMACallSession eMACallSession, EMAError eMAError);

    void onRecvCallIncoming(EMACallSession eMACallSession);

    void onRecvCallNetworkStatusChanged(EMACallSession eMACallSession, int i);

    void onRecvCallStateChanged(EMACallSession eMACallSession, int i);

    void onSendPushMessage(String str, String str2);
}
