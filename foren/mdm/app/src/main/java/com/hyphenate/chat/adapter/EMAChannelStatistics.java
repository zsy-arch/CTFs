package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public class EMAChannelStatistics extends EMABase {
    /* JADX INFO: Access modifiers changed from: package-private */
    public EMAChannelStatistics() {
        nativeInit();
    }

    EMAChannelStatistics(EMAChannelStatistics eMAChannelStatistics) {
        nativeInit(eMAChannelStatistics);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    native void nativeFinalize();

    native void nativeInit();

    native void nativeInit(EMAChannelStatistics eMAChannelStatistics);

    native void nativeSetConnType(String str);

    native void nativeSetLocalVideoActualBps(int i);

    native void nativeSetLocalVideoFps(int i);

    native void nativeSetLocalVideoPacketsLost(int i);

    native void nativeSetLocalVideoRtt(int i);

    native void nativeSetRemoteAudioBps(int i);

    native void nativeSetRemoteVideoBps(int i);

    native void nativeSetRemoteVideoFps(int i);

    native void nativeSetRemoteVideoHeight(int i);

    native void nativeSetRemoteVideoPacketsLost(int i);

    native void nativeSetRemoteVideoWidth(int i);

    native void nativeSetRtcReport(String str);

    public void setConnType(String str) {
        nativeSetConnType(str);
    }

    public void setLocalVideoActualBps(int i) {
        nativeSetLocalVideoActualBps(i);
    }

    public void setLocalVideoFps(int i) {
        nativeSetLocalVideoFps(i);
    }

    public void setLocalVideoPacketsLost(int i) {
        nativeSetLocalVideoPacketsLost(i);
    }

    public void setLocalVideoRtt(int i) {
        nativeSetLocalVideoRtt(i);
    }

    public void setRemoteAudioBps(int i) {
        nativeSetRemoteAudioBps(i);
    }

    public void setRemoteVideoBps(int i) {
        nativeSetRemoteVideoBps(i);
    }

    public void setRemoteVideoFps(int i) {
        nativeSetRemoteVideoFps(i);
    }

    public void setRemoteVideoHeight(int i) {
        nativeSetRemoteVideoHeight(i);
    }

    public void setRemoteVideoPacketsLost(int i) {
        nativeSetRemoteVideoPacketsLost(i);
    }

    public void setRemoteVideoWidth(int i) {
        nativeSetRemoteVideoWidth(i);
    }

    public void setRtcReport(String str) {
        nativeSetRtcReport(str);
    }
}
