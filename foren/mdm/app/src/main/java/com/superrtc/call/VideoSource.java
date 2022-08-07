package com.superrtc.call;

/* loaded from: classes2.dex */
public class VideoSource extends MediaSource {
    private static native void restart(long j);

    private static native void stop(long j);

    public VideoSource(long nativeSource) {
        super(nativeSource);
    }

    public void stop() {
        stop(this.nativeSource);
    }

    public void restart() {
        restart(this.nativeSource);
    }

    @Override // com.superrtc.call.MediaSource
    public void dispose() {
        super.dispose();
    }
}
