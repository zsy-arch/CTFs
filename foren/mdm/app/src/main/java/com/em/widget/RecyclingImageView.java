package com.em.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.em.video.util.RecyclingBitmapDrawable;

/* loaded from: classes.dex */
public class RecyclingImageView extends ImageView {
    public RecyclingImageView(Context context) {
        super(context);
    }

    public RecyclingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        setImageDrawable(null);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        Drawable previousDrawable = getDrawable();
        super.setImageDrawable(drawable);
        notifyDrawable(drawable, true);
        notifyDrawable(previousDrawable, false);
    }

    private static void notifyDrawable(Drawable drawable, boolean isDisplayed) {
        if (drawable instanceof RecyclingBitmapDrawable) {
            ((RecyclingBitmapDrawable) drawable).setIsDisplayed(isDisplayed);
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int z = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < z; i++) {
                notifyDrawable(layerDrawable.getDrawable(i), isDisplayed);
            }
        }
    }
}
