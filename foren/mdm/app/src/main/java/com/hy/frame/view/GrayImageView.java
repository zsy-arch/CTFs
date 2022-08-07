package com.hy.frame.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes2.dex */
public class GrayImageView extends ImageView {
    private boolean gray;

    public GrayImageView(Context context) {
        super(context);
    }

    public GrayImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GrayImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public GrayImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean isGray() {
        return this.gray;
    }

    public void setGray(boolean gray) {
        this.gray = gray;
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bm) {
        if (bm != null) {
            if (this.gray) {
                Bitmap grayBmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.RGB_565);
                Canvas c = new Canvas(grayBmp);
                Paint paint = new Paint();
                ColorMatrix cm = new ColorMatrix();
                cm.setSaturation(0.0f);
                paint.setColorFilter(new ColorMatrixColorFilter(cm));
                c.drawBitmap(bm, 0.0f, 0.0f, paint);
                super.setImageBitmap(grayBmp);
                return;
            }
            super.setImageBitmap(bm);
        }
    }
}
