package com.hyphenate.media;

import android.content.Context;
import android.util.AttributeSet;
import com.superrtc.sdk.VideoView;
import com.superrtc.sdk.VideoViewRenderer;

/* loaded from: classes2.dex */
public class EMOppositeSurfaceView extends VideoView {
    VideoViewRenderer renderer = new VideoViewRenderer(this, "oppositeView:");

    public EMOppositeSurfaceView(Context context) {
        super(context);
    }

    public EMOppositeSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EMOppositeSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
    }

    public VideoViewRenderer getRenderer() {
        return this.renderer;
    }

    public void release() {
        this.renderer = null;
    }
}
