package com.hyphenate.media;

import android.content.Context;
import android.util.AttributeSet;
import com.superrtc.sdk.VideoView;
import com.superrtc.sdk.VideoViewRenderer;

/* loaded from: classes2.dex */
public class EMLocalSurfaceView extends VideoView {
    VideoViewRenderer renderer;

    public EMLocalSurfaceView(Context context) {
        super(context);
        this.renderer = new VideoViewRenderer(this, "local");
    }

    public EMLocalSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.renderer = new VideoViewRenderer(this, "oppositeView:");
    }

    public EMLocalSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.renderer = new VideoViewRenderer(this, "oppositeView:");
    }

    public VideoViewRenderer getRenderer() {
        return this.renderer;
    }

    public void release() {
        this.renderer = null;
    }
}
