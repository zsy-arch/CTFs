package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class BitmapImageViewTarget extends ImageViewTarget<Bitmap> {
    public BitmapImageViewTarget(ImageView view) {
        super(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setResource(Bitmap resource) {
        ((ImageView) this.view).setImageBitmap(resource);
    }
}
