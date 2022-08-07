package uk.co.senab.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

/* loaded from: classes.dex */
public class PhotoView extends ImageView implements IPhotoView {
    private PhotoViewAttacher mAttacher;
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
        init();
    }

    protected void init() {
        if (this.mAttacher == null || this.mAttacher.getImageView() == null) {
            this.mAttacher = new PhotoViewAttacher(this);
        }
        if (this.mPendingScaleType != null) {
            setScaleType(this.mPendingScaleType);
            this.mPendingScaleType = null;
        }
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setPhotoViewRotation(float rotationDegree) {
        this.mAttacher.setRotationTo(rotationDegree);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setRotationTo(float rotationDegree) {
        this.mAttacher.setRotationTo(rotationDegree);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setRotationBy(float rotationDegree) {
        this.mAttacher.setRotationBy(rotationDegree);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public boolean canZoom() {
        return this.mAttacher.canZoom();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public RectF getDisplayRect() {
        return this.mAttacher.getDisplayRect();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public Matrix getDisplayMatrix() {
        return this.mAttacher.getDisplayMatrix();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public boolean setDisplayMatrix(Matrix finalRectangle) {
        return this.mAttacher.setDisplayMatrix(finalRectangle);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public float getMinimumScale() {
        return this.mAttacher.getMinimumScale();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public float getMediumScale() {
        return this.mAttacher.getMediumScale();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public float getMaximumScale() {
        return this.mAttacher.getMaximumScale();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public float getScale() {
        return this.mAttacher.getScale();
    }

    @Override // android.widget.ImageView, uk.co.senab.photoview.IPhotoView
    public ImageView.ScaleType getScaleType() {
        return this.mAttacher.getScaleType();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setAllowParentInterceptOnEdge(boolean allow) {
        this.mAttacher.setAllowParentInterceptOnEdge(allow);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public void setMinScale(float minScale) {
        setMinimumScale(minScale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setMinimumScale(float minimumScale) {
        this.mAttacher.setMinimumScale(minimumScale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public void setMidScale(float midScale) {
        setMediumScale(midScale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setMediumScale(float mediumScale) {
        this.mAttacher.setMediumScale(mediumScale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public void setMaxScale(float maxScale) {
        setMaximumScale(maxScale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setMaximumScale(float maximumScale) {
        this.mAttacher.setMaximumScale(maximumScale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        this.mAttacher.setScaleLevels(minimumScale, mediumScale, maximumScale);
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

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener listener) {
        this.mAttacher.setOnMatrixChangeListener(listener);
    }

    @Override // android.view.View, uk.co.senab.photoview.IPhotoView
    public void setOnLongClickListener(View.OnLongClickListener l) {
        this.mAttacher.setOnLongClickListener(l);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener listener) {
        this.mAttacher.setOnPhotoTapListener(listener);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public PhotoViewAttacher.OnPhotoTapListener getOnPhotoTapListener() {
        return this.mAttacher.getOnPhotoTapListener();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener listener) {
        this.mAttacher.setOnViewTapListener(listener);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public PhotoViewAttacher.OnViewTapListener getOnViewTapListener() {
        return this.mAttacher.getOnViewTapListener();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setScale(float scale) {
        this.mAttacher.setScale(scale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setScale(float scale, boolean animate) {
        this.mAttacher.setScale(scale, animate);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        this.mAttacher.setScale(scale, focalX, focalY, animate);
    }

    @Override // android.widget.ImageView, uk.co.senab.photoview.IPhotoView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (this.mAttacher != null) {
            this.mAttacher.setScaleType(scaleType);
        } else {
            this.mPendingScaleType = scaleType;
        }
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setZoomable(boolean zoomable) {
        this.mAttacher.setZoomable(zoomable);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public Bitmap getVisibleRectangleBitmap() {
        return this.mAttacher.getVisibleRectangleBitmap();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setZoomTransitionDuration(int milliseconds) {
        this.mAttacher.setZoomTransitionDuration(milliseconds);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public IPhotoView getIPhotoViewImplementation() {
        return this.mAttacher;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener newOnDoubleTapListener) {
        this.mAttacher.setOnDoubleTapListener(newOnDoubleTapListener);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnScaleChangeListener(PhotoViewAttacher.OnScaleChangeListener onScaleChangeListener) {
        this.mAttacher.setOnScaleChangeListener(onScaleChangeListener);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        this.mAttacher.cleanup();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        init();
        super.onAttachedToWindow();
    }
}
