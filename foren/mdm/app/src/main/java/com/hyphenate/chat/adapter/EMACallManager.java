package com.hyphenate.chat.adapter;

import com.hyphenate.chat.adapter.EMACallSession;
import java.util.List;

/* loaded from: classes2.dex */
public class EMACallManager extends EMABase {
    public void addListener(EMACallManagerListener eMACallManagerListener) {
        nativeAddListener(eMACallManagerListener);
    }

    public void answerCall(String str, EMAError eMAError) {
        nativeAnswerCall(str, eMAError);
    }

    public void asyncPublishConferenceStream(String str, EMAError eMAError) {
        nativeAsyncPublishConferenceStream(str, eMAError);
    }

    public void asyncSubscribeConferenceStream(String str, EMACallStream eMACallStream, EMAError eMAError) {
        nativeAsyncSubscribeConferenceStream(str, eMACallStream, eMAError);
    }

    public native boolean capturePicture(String str);

    void clearListeners() {
        nativeClearListeners();
    }

    public EMACallConference createAndJoinConference(int i, String str, EMAError eMAError) {
        return nativeCreateAndJoinConference(i, str, eMAError);
    }

    public EMACallConference createConference(int i, String str, EMAError eMAError) {
        return nativeCreateConference(i, str, eMAError);
    }

    public void deleteConference(String str, EMAError eMAError) {
        nativeDeleteConference(str, eMAError);
    }

    public void deleteConference(String str, String str2, EMAError eMAError) {
        nativeDeleteConference(str, str2, eMAError);
    }

    public void endCall(String str, EMACallSession.EndReason endReason) {
        nativeEndCall(str, endReason.ordinal());
    }

    public List<String> getConferenceMembersFromServer(String str, EMAError eMAError) {
        return nativeGetConferenceMembersFromServer(str, eMAError);
    }

    public boolean getIsSendPushIfOffline() {
        return nativeGetIsSendPushIfOffline();
    }

    public long getVideoKbps() {
        return nativeGetVideoKbps();
    }

    public long getVideoResolutionHeight() {
        return nativeGetVideoResolutionHeight();
    }

    public long getVideoResolutionWidth() {
        return nativeGetVideoResolutionWidth();
    }

    public EMACallConference joinConference(String str, String str2, EMAError eMAError) {
        return nativeJoinConference(str, str2, eMAError);
    }

    public void leaveConference(String str, EMAError eMAError) {
        nativeLeaveConference(str, eMAError);
    }

    public EMACallSession makeCall(String str, EMACallSession.Type type, EMAError eMAError) {
        return nativeMakeCall(str, type.ordinal(), "", eMAError);
    }

    public EMACallSession makeCall(String str, EMACallSession.Type type, String str2, EMAError eMAError) {
        return nativeMakeCall(str, type.ordinal(), str2, eMAError);
    }

    native void nativeAddListener(EMACallManagerListener eMACallManagerListener);

    native void nativeAnswerCall(String str, EMAError eMAError);

    native void nativeAsyncPublishConferenceStream(String str, EMAError eMAError);

    native void nativeAsyncSubscribeConferenceStream(String str, EMACallStream eMACallStream, EMAError eMAError);

    native void nativeClearListeners();

    native EMACallConference nativeCreateAndJoinConference(int i, String str, EMAError eMAError);

    native EMACallConference nativeCreateConference(int i, String str, EMAError eMAError);

    native void nativeDeleteConference(String str, EMAError eMAError);

    native void nativeDeleteConference(String str, String str2, EMAError eMAError);

    native void nativeEndCall(String str, int i);

    native List<String> nativeGetConferenceMembersFromServer(String str, EMAError eMAError);

    native boolean nativeGetIsSendPushIfOffline();

    native long nativeGetVideoKbps();

    native long nativeGetVideoResolutionHeight();

    native long nativeGetVideoResolutionWidth();

    native EMACallConference nativeJoinConference(String str, String str2, EMAError eMAError);

    native void nativeLeaveConference(String str, EMAError eMAError);

    native EMACallSession nativeMakeCall(String str, int i, String str2, EMAError eMAError);

    native void nativeRemoveListener(EMACallManagerListener eMACallManagerListener);

    native void nativeSetIsSendPushIfOffline(boolean z);

    native void nativeSetVideoKbps(long j);

    native void nativeSetVideoResolution(long j, long j2);

    native void nativeUpdateCall(String str, int i, EMAError eMAError);

    native void nativeUpdateSessionInfo(String str, int i);

    void removeListener(EMACallManagerListener eMACallManagerListener) {
        nativeRemoveListener(eMACallManagerListener);
    }

    public void setIsSendPushIfOffline(boolean z) {
        nativeSetIsSendPushIfOffline(z);
    }

    public void setVideoKbps(long j) {
        nativeSetVideoKbps(j);
    }

    public void setVideoResolution(long j, long j2) {
        nativeSetVideoResolution(j, j2);
    }

    public native void startRecordVideo(String str);

    public native String stopRecordVideo();

    public void updateCall(String str, EMACallSession.StreamControlType streamControlType, EMAError eMAError) {
        nativeUpdateCall(str, streamControlType.ordinal(), eMAError);
    }

    public void updateSessionInfo(String str, int i) {
        nativeUpdateSessionInfo(str, i);
    }
}
