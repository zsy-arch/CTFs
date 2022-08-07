package com.vsf2f.f2f.ui.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/* loaded from: classes2.dex */
public interface Synthesizer {
    boolean asyncLoadImageList();

    void drawDrawable(Canvas canvas);

    Bitmap synthesizeImageList();
}
