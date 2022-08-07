package com.amap.api.navi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amap.api.col.fo;

/* loaded from: classes.dex */
public class DirectionView extends ImageView {
    private final int height;
    private Bitmap mDirectionBitmap;
    private int mLastAngle;
    private final PaintFlagsDrawFilter mPaintFlagsDrawFilter;
    private final int width;

    public DirectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDirectionBitmap = BitmapFactory.decodeResource(fo.a(), 1191313497);
        setImageBitmap(this.mDirectionBitmap);
        this.mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0, 3);
        this.width = this.mDirectionBitmap.getWidth();
        this.height = this.mDirectionBitmap.getHeight();
    }

    public DirectionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DirectionView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        getImageMatrix().setRotate(this.mLastAngle, this.width / 2.0f, this.height / 2.0f);
        canvas.setDrawFilter(this.mPaintFlagsDrawFilter);
        super.onDraw(canvas);
    }

    public void setRotate(float f) {
        if (this.mLastAngle != ((int) f)) {
            this.mLastAngle = (int) f;
            invalidate();
        }
    }

    public void recycleResource() {
        if (this.mDirectionBitmap != null) {
            this.mDirectionBitmap.recycle();
            this.mDirectionBitmap = null;
        }
    }

    public Bitmap getDirectionBitmap() {
        return this.mDirectionBitmap;
    }

    public void setDirectionBitmap(Bitmap bitmap) {
        this.mDirectionBitmap = bitmap;
        setImageBitmap(this.mDirectionBitmap);
    }
}
