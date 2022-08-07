package com.amap.api.navi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amap.api.col.fo;

/* loaded from: classes.dex */
public class OverviewButtonView extends ImageView {
    private boolean isON;
    private Bitmap overviewOffBitmap;
    private Bitmap overviewOnBitmap;

    public OverviewButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public OverviewButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public OverviewButtonView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.overviewOnBitmap = BitmapFactory.decodeResource(fo.a(), 1191313493);
        this.overviewOffBitmap = BitmapFactory.decodeResource(fo.a(), 1191313494);
    }

    public void reDrawBackground(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != null && bitmap2 != null) {
            this.overviewOnBitmap = bitmap;
            this.overviewOffBitmap = bitmap2;
            setChecked(this.isON);
            invalidate();
        }
    }

    public void setChecked(boolean z) {
        this.isON = z;
        if (z) {
            setImageBitmap(this.overviewOnBitmap);
        } else {
            setImageBitmap(this.overviewOffBitmap);
        }
    }

    public void recycleResource() {
        if (this.overviewOffBitmap != null) {
            this.overviewOffBitmap.recycle();
            this.overviewOffBitmap = null;
        }
        if (this.overviewOnBitmap != null) {
            this.overviewOnBitmap.recycle();
            this.overviewOnBitmap = null;
        }
    }
}
