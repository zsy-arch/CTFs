package com.superrtc.sdk;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/* loaded from: classes2.dex */
public class VideoView extends GLSurfaceView {
    private EMCallViewScaleMode aspectMode = EMCallViewScaleMode.EMCallViewScaleModeAspectFit;

    /* loaded from: classes2.dex */
    public enum EMCallViewScaleMode {
        EMCallViewScaleModeAspectFit,
        EMCallViewScaleModeAspectFill
    }

    public synchronized EMCallViewScaleMode getScaleMode() {
        return this.aspectMode;
    }

    public synchronized void setScaleMode(EMCallViewScaleMode mode) {
        if (this.aspectMode != mode) {
            this.aspectMode = mode;
        }
    }

    public VideoView(Context context) {
        super(context);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
    }
}
