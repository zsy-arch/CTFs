package com.superrtc.call;

/* loaded from: classes2.dex */
public class RtpReceiver {
    private MediaStreamTrack cachedTrack;
    final long nativeRtpReceiver;

    private static native void free(long j);

    private static native long nativeGetTrack(long j);

    private static native String nativeId(long j);

    public RtpReceiver(long nativeRtpReceiver) {
        this.nativeRtpReceiver = nativeRtpReceiver;
        this.cachedTrack = new MediaStreamTrack(nativeGetTrack(nativeRtpReceiver));
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }

    public String id() {
        return nativeId(this.nativeRtpReceiver);
    }

    public void dispose() {
        this.cachedTrack.dispose();
        free(this.nativeRtpReceiver);
    }
}
