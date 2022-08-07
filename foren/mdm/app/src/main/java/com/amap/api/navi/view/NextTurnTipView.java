package com.amap.api.navi.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amap.api.col.fo;

/* loaded from: classes.dex */
public class NextTurnTipView extends ImageView {
    private int[] customIconTypeDrawables;
    private Resources customRes;
    private Bitmap nextTurnBitmap;
    private long mLastIconType = -1;
    private int[] defaultIconTypes = {1191313412, 1191313412, 1191313526, 1191313527, 1191313528, 1191313529, 1191313530, 1191313531, 1191313532, 1191313533, 1191313516, 1191313517, 1191313518, 1191313519, 1191313520, 1191313521, 1191313522, 1191313523, 1191313524, 1191313525};

    public Resources getCustomResources() {
        return this.customRes;
    }

    public int[] getCustomIconTypeDrawables() {
        return this.customIconTypeDrawables;
    }

    public void setCustomIconTypes(Resources resources, int[] iArr) {
        this.customRes = resources;
        this.customIconTypeDrawables = new int[iArr.length + 2];
        for (int i = 0; i < iArr.length; i++) {
            this.customIconTypeDrawables[i + 2] = iArr[i];
        }
    }

    public NextTurnTipView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public NextTurnTipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NextTurnTipView(Context context) {
        super(context);
    }

    public void recycleResource() {
        if (this.nextTurnBitmap != null) {
            this.nextTurnBitmap.recycle();
            this.nextTurnBitmap = null;
        }
    }

    public void setIconType(int i) {
        if (i <= 19) {
            try {
                if (this.mLastIconType != i) {
                    recycleResource();
                    if (this.customIconTypeDrawables == null || this.customRes == null) {
                        this.nextTurnBitmap = BitmapFactory.decodeResource(fo.a(), this.defaultIconTypes[i]);
                    } else {
                        this.nextTurnBitmap = BitmapFactory.decodeResource(this.customRes, this.customIconTypeDrawables[i]);
                    }
                    setImageBitmap(this.nextTurnBitmap);
                    this.mLastIconType = i;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
