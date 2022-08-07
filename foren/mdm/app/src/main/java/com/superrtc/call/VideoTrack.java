package com.superrtc.call;

import java.util.LinkedList;

/* loaded from: classes2.dex */
public class VideoTrack extends MediaStreamTrack {
    private final LinkedList<VideoRenderer> renderers = new LinkedList<>();

    private static native void free(long j);

    private static native void nativeAddRenderer(long j, long j2);

    private static native void nativeRemoveRenderer(long j, long j2);

    public VideoTrack(long nativeTrack) {
        super(nativeTrack);
    }

    public void addRenderer(VideoRenderer renderer) {
        this.renderers.add(renderer);
        nativeAddRenderer(this.nativeTrack, renderer.nativeVideoRenderer);
    }

    public void removeRenderer(VideoRenderer renderer) {
        if (this.renderers.remove(renderer)) {
            nativeRemoveRenderer(this.nativeTrack, renderer.nativeVideoRenderer);
            renderer.dispose();
        }
    }

    @Override // com.superrtc.call.MediaStreamTrack
    public void dispose() {
        while (!this.renderers.isEmpty()) {
            removeRenderer(this.renderers.getFirst());
        }
        super.dispose();
    }
}
