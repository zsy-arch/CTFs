package com.amap.api.navi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amap.api.col.fo;

/* loaded from: classes.dex */
public class TrafficButtonView extends ImageView {
    private boolean mIsTrafficOpen;
    private Bitmap trafficCloseBitmap;
    private Bitmap trafficOpenBitmap;

    public TrafficButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initBackground();
    }

    public TrafficButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initBackground();
    }

    public TrafficButtonView(Context context) {
        super(context);
        initBackground();
    }

    private void initBackground() {
        this.trafficOpenBitmap = BitmapFactory.decodeResource(fo.a(), 1191313492);
        this.trafficCloseBitmap = BitmapFactory.decodeResource(fo.a(), 1191313491);
        setIsTrafficOpen(true);
    }

    public void reDrawBackground(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != null && bitmap2 != null) {
            this.trafficOpenBitmap = bitmap;
            this.trafficCloseBitmap = bitmap2;
            setIsTrafficOpen(this.mIsTrafficOpen);
        }
    }

    public boolean getIsTrafficOpen() {
        return this.mIsTrafficOpen;
    }

    public void setIsTrafficOpen(boolean z) {
        this.mIsTrafficOpen = z;
        if (this.mIsTrafficOpen) {
            setImageBitmap(this.trafficOpenBitmap);
        } else {
            setImageBitmap(this.trafficCloseBitmap);
        }
    }

    public void recycleResource() {
        if (this.trafficCloseBitmap != null) {
            this.trafficCloseBitmap.recycle();
            this.trafficCloseBitmap = null;
        }
        if (this.trafficOpenBitmap != null) {
            this.trafficOpenBitmap.recycle();
            this.trafficOpenBitmap = null;
        }
    }
}
