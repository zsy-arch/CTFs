package com.autonavi.ae.gmap.gloverlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.TypedValue;
import com.autonavi.ae.gmap.gloverlay.GLCrossVector;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.tencent.smtt.sdk.TbsListener;

/* loaded from: classes.dex */
public class CrossVectorOverlay extends BaseMapOverlay<GLCrossVector, Object> {
    int mIndex = 0;
    GLCrossVector.AVectorCrossAttr attr = null;

    public CrossVectorOverlay(int i, Context context, IAMap iAMap) {
        super(i, context, iAMap);
    }

    @Override // com.autonavi.ae.gmap.gloverlay.BaseMapOverlay
    protected void iniGLOverlay() {
        this.mGLOverlay = new GLCrossVector(this.mEngineID, this.mMapView, hashCode());
    }

    @Override // com.autonavi.ae.gmap.gloverlay.BaseMapOverlay
    public void addItem(Object obj) {
    }

    @Override // com.autonavi.ae.gmap.gloverlay.BaseMapOverlay
    public void resumeMarker(Bitmap bitmap) {
        AddOverlayTexture(bitmap, 12345, 4);
        ((GLCrossVector) this.mGLOverlay).setArrowResId(false, 12345);
        ((GLCrossVector) this.mGLOverlay).setCarResId(12345);
    }

    public void AddOverlayTexture(Bitmap bitmap, int i, int i2) {
        GLTextureProperty gLTextureProperty = new GLTextureProperty();
        gLTextureProperty.mId = i;
        gLTextureProperty.mAnchor = i2;
        gLTextureProperty.mBitmap = bitmap;
        gLTextureProperty.mXRatio = 0.0f;
        gLTextureProperty.mYRatio = 0.0f;
        gLTextureProperty.isGenMimps = true;
        this.mMapView.addOverlayTexture(this.mEngineID, gLTextureProperty);
    }

    public int dipToPixel(Context context, int i) {
        if (context == null) {
            return i;
        }
        try {
            return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
        } catch (Exception e) {
            return i;
        }
    }

    public void setAttribute(GLCrossVector.AVectorCrossAttr aVectorCrossAttr) {
        this.attr = aVectorCrossAttr;
    }

    public void setData(byte[] bArr) {
        if (this.attr == null) {
            GLCrossVector.AVectorCrossAttr aVectorCrossAttr = new GLCrossVector.AVectorCrossAttr();
            aVectorCrossAttr.nCenterX = 0;
            aVectorCrossAttr.nCenterY = 0;
            aVectorCrossAttr.stRectMax = new Rect();
            aVectorCrossAttr.stRectMin = new Rect();
            aVectorCrossAttr.nAngle = 0;
            aVectorCrossAttr.stAreaRect = new Rect(0, 0, this.mMapView.getMapWidth(), (this.mMapView.getMapHeight() * 4) / 11);
            aVectorCrossAttr.stAreaColor = Color.argb((int) TbsListener.ErrorCode.INCR_UPDATE_FAIL, 95, 95, 95);
            aVectorCrossAttr.fImportBorderWidth = dipToPixel(this.mContext, 22);
            aVectorCrossAttr.stImportBorderColor = Color.argb(255, 255, 255, 255);
            aVectorCrossAttr.fUnImportBorderWidth = aVectorCrossAttr.fImportBorderWidth;
            aVectorCrossAttr.stUnImportBorderColor = aVectorCrossAttr.stImportBorderColor;
            aVectorCrossAttr.fArrowBorderWidth = dipToPixel(this.mContext, 22);
            aVectorCrossAttr.stArrowBorderColor = Color.argb(0, 0, 50, 20);
            aVectorCrossAttr.fImportLineWidth = dipToPixel(this.mContext, 18);
            aVectorCrossAttr.stImportLineColor = Color.argb(255, 150, 170, 200);
            aVectorCrossAttr.fUnImportLineWidth = aVectorCrossAttr.fImportLineWidth;
            aVectorCrossAttr.stUnImportLineColor = aVectorCrossAttr.stImportLineColor;
            aVectorCrossAttr.fDashLineWidth = dipToPixel(this.mContext, 2);
            aVectorCrossAttr.stDashLineColor = aVectorCrossAttr.stUnImportBorderColor;
            aVectorCrossAttr.fArrowLineWidth = dipToPixel(this.mContext, 18);
            aVectorCrossAttr.stArrowLineColor = Color.argb(255, 255, 253, 65);
            aVectorCrossAttr.dayMode = false;
        }
        if (bArr != null && this.attr != null) {
            ((GLCrossVector) this.mGLOverlay).addVectorItem(this.attr, bArr, bArr.length);
            ((GLCrossVector) this.mGLOverlay).setVisible(true);
        }
    }

    public void remove() {
        setVisible(false);
        releaseInstance();
    }
}
