package com.superrtc.sdk;

import com.superrtc.call.VideoRendererGui2;

/* loaded from: classes2.dex */
public class VideoViewRenderer {
    private static final String TAG = "VR";
    private String name;
    final VideoRendererGui2 rendererGui;
    private boolean viewReady = true;

    public VideoViewRenderer(VideoView surface, String name_) {
        log("VideoViewRenderer");
        this.name = name_;
        this.rendererGui = new VideoRendererGui2(surface, null);
        if (this.name == null) {
            this.name = "";
        }
        this.rendererGui.setReadyCallback(new Runnable() { // from class: com.superrtc.sdk.VideoViewRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                    VideoViewRenderer.this.log("view ready");
                    VideoViewRenderer.this.viewReady = true;
                }
            }
        });
    }

    public synchronized boolean isViewReady() {
        return this.viewReady;
    }

    public boolean getRenderEnabled() {
        return this.rendererGui.getRenderEnable();
    }

    public void setRenderEnable(boolean enabled) {
        this.rendererGui.setRenderEnable(enabled);
    }

    public void dispose() {
        this.rendererGui.dispose();
    }

    public VideoRendererGui2 getGuiImpl() {
        return this.rendererGui;
    }

    public void log(String msg) {
        ALog.i(TAG, "<D><" + this.name + "> " + msg);
    }
}
