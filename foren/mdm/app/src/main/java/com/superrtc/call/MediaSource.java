package com.superrtc.call;

/* loaded from: classes2.dex */
public class MediaSource {
    final long nativeSource;

    /* loaded from: classes2.dex */
    public enum State {
        INITIALIZING,
        LIVE,
        ENDED,
        MUTED
    }

    private static native void free(long j);

    private static native State nativeState(long j);

    public MediaSource(long nativeSource) {
        this.nativeSource = nativeSource;
    }

    public State state() {
        return nativeState(this.nativeSource);
    }

    public void dispose() {
        free(this.nativeSource);
    }
}
