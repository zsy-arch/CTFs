package com.hyphenate.chat.adapter;

import com.superrtc.sdk.RtcConnection;

/* loaded from: classes2.dex */
public class EMACallRtcListenerDelegate extends EMABase implements RtcConnection.Listener {
    RtcConnection.RtcStatistics states;

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public synchronized RtcConnection.RtcStatistics getStatistics() {
        return this.states;
    }

    native void nativeFinalize();

    native void nativeOnCandidateCompleted();

    native void nativeOnClosed();

    native void nativeOnConnected();

    native void nativeOnConnectionSetup();

    native void nativeOnDisconnected();

    native void nativeOnError();

    native void nativeOnLocalCandidate(String str);

    native void nativeOnLocalSdp(String str);

    native void nativeOnStats(EMAChannelStatistics eMAChannelStatistics);

    @Override // com.superrtc.sdk.RtcConnection.Listener
    public void onCandidateCompleted() {
        nativeOnCandidateCompleted();
    }

    @Override // com.superrtc.sdk.RtcConnection.Listener
    public void onClosed() {
        nativeOnClosed();
    }

    @Override // com.superrtc.sdk.RtcConnection.Listener
    public void onConnected() {
        nativeOnConnected();
    }

    @Override // com.superrtc.sdk.RtcConnection.Listener
    public void onConnectionsetup() {
        nativeOnConnectionSetup();
    }

    @Override // com.superrtc.sdk.RtcConnection.Listener
    public void onDisconnected() {
        nativeOnDisconnected();
    }

    @Override // com.superrtc.sdk.RtcConnection.Listener
    public void onError(String str) {
        nativeOnError();
    }

    @Override // com.superrtc.sdk.RtcConnection.Listener
    public void onLocalCandidate(String str) {
        nativeOnLocalCandidate(str);
    }

    @Override // com.superrtc.sdk.RtcConnection.Listener
    public void onLocalSdp(String str) {
        nativeOnLocalSdp(str);
    }

    @Override // com.superrtc.sdk.RtcConnection.Listener
    public void onStats(RtcConnection.RtcStatistics rtcStatistics) {
        synchronized (this) {
            this.states = rtcStatistics;
        }
        EMAChannelStatistics eMAChannelStatistics = new EMAChannelStatistics();
        eMAChannelStatistics.setConnType(this.states.connectionType);
        eMAChannelStatistics.setLocalVideoRtt(this.states.localVideoRtt);
        eMAChannelStatistics.setRemoteVideoWidth(this.states.remoteWidth);
        eMAChannelStatistics.setRemoteVideoHeight(this.states.remoteHeight);
        eMAChannelStatistics.setLocalVideoFps(this.states.localEncodedFps);
        eMAChannelStatistics.setRemoteVideoFps(this.states.remoteFps);
        eMAChannelStatistics.setLocalVideoPacketsLost(this.states.localVideoPacketsLost);
        eMAChannelStatistics.setRemoteVideoPacketsLost(this.states.remoteVideoPacketsLost);
        eMAChannelStatistics.setLocalVideoActualBps(this.states.localVideoActualBps);
        eMAChannelStatistics.setRemoteAudioBps(this.states.remoteAudioBps);
        eMAChannelStatistics.setRemoteVideoBps(this.states.remoteVideoBps);
        nativeOnStats(eMAChannelStatistics);
    }
}
