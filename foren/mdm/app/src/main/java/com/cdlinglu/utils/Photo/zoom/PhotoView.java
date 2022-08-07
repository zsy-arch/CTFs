package com.cdlinglu.utils.Photo.zoom;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.cdlinglu.utils.Photo.zoom.PhotoViewAttacher;

/* loaded from: classes.dex */
public class PhotoView extends ImageView implements IPhotoView {
    private final PhotoViewAttacher mAttacher;
    private ImageView.ScaleType mPendingScaleType;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public PhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        this.mAttacher = new PhotoViewAttacher(this);
        if (this.mPendingScaleType != null) {
            setScaleType(this.mPendingScaleType);
            this.mPendingScaleType = null;
        }
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public boolean canZoom() {
        return this.mAttacher.canZoom();
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public RectF getDisplayRect() {
        return this.mAttacher.getDisplayRect();
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public float getMinScale() {
        return this.mAttacher.getMinScale();
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public float getMidScale() {
        return this.mAttacher.getMidScale();
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public float getMaxScale() {
        return this.mAttacher.getMaxScale();
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public float getScale() {
        return this.mAttacher.getScale();
    }

    @Override // android.widget.ImageView, com.cdlinglu.utils.Photo.zoom.IPhotoView
    public ImageView.ScaleType getScaleType() {
        return this.mAttacher.getScaleType();
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setAllowParentInterceptOnEdge(boolean allow) {
        this.mAttacher.setAllowParentInterceptOnEdge(allow);
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setMinScale(float minScale) {
        this.mAttacher.setMinScale(minScale);
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setMidScale(float midScale) {
        this.mAttacher.setMidScale(midScale);
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setMaxScale(float maxScale) {
        this.mAttacher.setMaxScale(maxScale);
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener listener) {
        this.mAttacher.setOnMatrixChangeListener(listener);
    }

    @Override // android.view.View, com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setOnLongClickListener(View.OnLongClickListener l) {
        this.mAttacher.setOnLongClickListener(l);
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener listener) {
        this.mAttacher.setOnPhotoTapListener(listener);
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener listener) {
        this.mAttacher.setOnViewTapListener(listener);
    }

    @Override // android.widget.ImageView, com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (this.mAttacher != null) {
            this.mAttacher.setScaleType(scaleType);
        } else {
            this.mPendingScaleType = scaleType;
        }
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setZoomable(boolean zoomable) {
        this.mAttacher.setZoomable(zoomable);
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void zoomTo(float scale, float focalX, float focalY) {
        this.mAttacher.zoomTo(scale, focalX, focalY);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        this.mAttacher.cleanup();
        super.onDetachedFromWindow();
    }
}
