package com.cdlinglu.utils.Photo.zoom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class PhotoViewAttacher implements IPhotoView, View.OnTouchListener, VersionedGestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, ViewTreeObserver.OnGlobalLayoutListener {
    public static final float DEFAULT_MAX_SCALE = 3.0f;
    public static final float DEFAULT_MID_SCALE = 1.75f;
    public static final float DEFAULT_MIN_SCALE = 1.0f;
    static final int EDGE_BOTH = 2;
    static final int EDGE_LEFT = 0;
    static final int EDGE_NONE = -1;
    static final int EDGE_RIGHT = 1;
    private FlingRunnable mCurrentFlingRunnable;
    private GestureDetector mGestureDetector;
    private WeakReference<ImageView> mImageView;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mIvTop;
    private View.OnLongClickListener mLongClickListener;
    private OnMatrixChangedListener mMatrixChangeListener;
    private OnPhotoTapListener mPhotoTapListener;
    private VersionedGestureDetector mScaleDragDetector;
    private OnViewTapListener mViewTapListener;
    private ViewTreeObserver mViewTreeObserver;
    private boolean mZoomEnabled;
    static final String LOG_TAG = "PhotoViewAttacher";
    static final boolean DEBUG = Log.isLoggable(LOG_TAG, 3);
    private float mMinScale = 1.0f;
    private float mMidScale = 1.75f;
    private float mMaxScale = 3.0f;
    private boolean mAllowParentInterceptOnEdge = true;
    private final Matrix mBaseMatrix = new Matrix();
    private final Matrix mDrawMatrix = new Matrix();
    private final Matrix mSuppMatrix = new Matrix();
    private final RectF mDisplayRect = new RectF();
    private final float[] mMatrixValues = new float[9];
    private int mScrollEdge = 2;
    private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;

    /* loaded from: classes.dex */
    public interface OnMatrixChangedListener {
        void onMatrixChanged(RectF rectF);
    }

    /* loaded from: classes.dex */
    public interface OnPhotoTapListener {
        void onPhotoTap(View view, float f, float f2);
    }

    /* loaded from: classes.dex */
    public interface OnViewTapListener {
        void onViewTap(View view, float f, float f2);
    }

    private static void checkZoomLevels(float minZoom, float midZoom, float maxZoom) {
        if (minZoom >= midZoom) {
            throw new IllegalArgumentException("MinZoom should be less than MidZoom");
        } else if (midZoom >= maxZoom) {
            throw new IllegalArgumentException("MidZoom should be less than MaxZoom");
        }
    }

    private static boolean hasDrawable(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private static boolean isSupportedScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()]) {
            case 1:
                throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
            default:
                return true;
        }
    }

    /* renamed from: com.cdlinglu.utils.Photo.zoom.PhotoViewAttacher$2 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private static void setImageViewScaleTypeMatrix(ImageView imageView) {
        if (imageView != null && !(imageView instanceof PhotoView)) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        }
    }

    public PhotoViewAttacher(ImageView imageView) {
        this.mImageView = new WeakReference<>(imageView);
        imageView.setOnTouchListener(this);
        this.mViewTreeObserver = imageView.getViewTreeObserver();
        this.mViewTreeObserver.addOnGlobalLayoutListener(this);
        setImageViewScaleTypeMatrix(imageView);
        if (!imageView.isInEditMode()) {
            this.mScaleDragDetector = VersionedGestureDetector.newInstance(imageView.getContext(), this);
            this.mGestureDetector = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.cdlinglu.utils.Photo.zoom.PhotoViewAttacher.1
                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public void onLongPress(MotionEvent e) {
                    if (PhotoViewAttacher.this.mLongClickListener != null) {
                        PhotoViewAttacher.this.mLongClickListener.onLongClick((View) PhotoViewAttacher.this.mImageView.get());
                    }
                }
            });
            this.mGestureDetector.setOnDoubleTapListener(this);
            setZoomable(true);
        }
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final boolean canZoom() {
        return this.mZoomEnabled;
    }

    @SuppressLint({"NewApi"})
    public final void cleanup() {
        if (Build.VERSION.SDK_INT >= 16) {
            if (this.mImageView != null) {
                this.mImageView.get().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            if (this.mViewTreeObserver != null && this.mViewTreeObserver.isAlive()) {
                this.mViewTreeObserver.removeOnGlobalLayoutListener(this);
                this.mViewTreeObserver = null;
                this.mMatrixChangeListener = null;
                this.mPhotoTapListener = null;
                this.mViewTapListener = null;
                this.mImageView = null;
                return;
            }
            return;
        }
        if (this.mImageView != null) {
            this.mImageView.get().getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        if (this.mViewTreeObserver != null && this.mViewTreeObserver.isAlive()) {
            this.mViewTreeObserver.removeGlobalOnLayoutListener(this);
            this.mViewTreeObserver = null;
            this.mMatrixChangeListener = null;
            this.mPhotoTapListener = null;
            this.mViewTapListener = null;
            this.mImageView = null;
        }
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final RectF getDisplayRect() {
        checkMatrixBounds();
        return getDisplayRect(getDisplayMatrix());
    }

    public final ImageView getImageView() {
        ImageView imageView = null;
        if (this.mImageView != null) {
            imageView = this.mImageView.get();
        }
        if (imageView != null) {
            return imageView;
        }
        cleanup();
        throw new IllegalStateException("ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public float getMinScale() {
        return this.mMinScale;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public float getMidScale() {
        return this.mMidScale;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public float getMaxScale() {
        return this.mMaxScale;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final float getScale() {
        return getValue(this.mSuppMatrix, 0);
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTap(MotionEvent ev) {
        try {
            float scale = getScale();
            float x = ev.getX();
            float y = ev.getY();
            if (scale < this.mMidScale) {
                zoomTo(this.mMidScale, x, y);
            } else if (scale < this.mMidScale || scale >= this.mMaxScale) {
                zoomTo(this.mMinScale, x, y);
            } else {
                zoomTo(this.mMaxScale, x, y);
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.OnGestureListener
    public final void onDrag(float dx, float dy) {
        if (DEBUG) {
            Log.d(LOG_TAG, String.format("onDrag: dx: %.2f. dy: %.2f", Float.valueOf(dx), Float.valueOf(dy)));
        }
        ImageView imageView = getImageView();
        if (imageView != null && hasDrawable(imageView)) {
            this.mSuppMatrix.postTranslate(dx, dy);
            checkAndDisplayMatrix();
            if (this.mAllowParentInterceptOnEdge && !this.mScaleDragDetector.isScaling()) {
                if (this.mScrollEdge == 2 || ((this.mScrollEdge == 0 && dx >= 1.0f) || (this.mScrollEdge == 1 && dx <= -1.0f))) {
                    imageView.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
        }
    }

    @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.OnGestureListener
    public final void onFling(float startX, float startY, float velocityX, float velocityY) {
        if (DEBUG) {
            Log.d(LOG_TAG, "onFling. sX: " + startX + " sY: " + startY + " Vx: " + velocityX + " Vy: " + velocityY);
        }
        ImageView imageView = getImageView();
        if (hasDrawable(imageView)) {
            this.mCurrentFlingRunnable = new FlingRunnable(imageView.getContext());
            this.mCurrentFlingRunnable.fling(imageView.getWidth(), imageView.getHeight(), (int) velocityX, (int) velocityY);
            imageView.post(this.mCurrentFlingRunnable);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        ImageView imageView = getImageView();
        if (imageView != null && this.mZoomEnabled) {
            int top = imageView.getTop();
            int right = imageView.getRight();
            int bottom = imageView.getBottom();
            int left = imageView.getLeft();
            if (top != this.mIvTop || bottom != this.mIvBottom || left != this.mIvLeft || right != this.mIvRight) {
                updateBaseMatrix(imageView.getDrawable());
                this.mIvTop = top;
                this.mIvRight = right;
                this.mIvBottom = bottom;
                this.mIvLeft = left;
            }
        }
    }

    @Override // com.cdlinglu.utils.Photo.zoom.VersionedGestureDetector.OnGestureListener
    public final void onScale(float scaleFactor, float focusX, float focusY) {
        if (DEBUG) {
            Log.d(LOG_TAG, String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", Float.valueOf(scaleFactor), Float.valueOf(focusX), Float.valueOf(focusY)));
        }
        if (!hasDrawable(getImageView())) {
            return;
        }
        if (getScale() < this.mMaxScale || scaleFactor < 1.0f) {
            this.mSuppMatrix.postScale(scaleFactor, scaleFactor, focusX, focusY);
            checkAndDisplayMatrix();
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onSingleTapConfirmed(MotionEvent e) {
        RectF displayRect;
        ImageView imageView = getImageView();
        if (imageView != null) {
            if (!(this.mPhotoTapListener == null || (displayRect = getDisplayRect()) == null)) {
                float x = e.getX();
                float y = e.getY();
                if (displayRect.contains(x, y)) {
                    this.mPhotoTapListener.onPhotoTap(imageView, (x - displayRect.left) / displayRect.width(), (y - displayRect.top) / displayRect.height());
                    return true;
                }
            }
            if (this.mViewTapListener != null) {
                this.mViewTapListener.onViewTap(imageView, e.getX(), e.getY());
            }
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View v, MotionEvent ev) {
        RectF rect;
        boolean handled = false;
        if (!this.mZoomEnabled) {
            return false;
        }
        switch (ev.getAction()) {
            case 0:
                v.getParent().requestDisallowInterceptTouchEvent(true);
                cancelFling();
                break;
            case 1:
            case 3:
                if (getScale() < this.mMinScale && (rect = getDisplayRect()) != null) {
                    v.post(new AnimatedZoomRunnable(getScale(), this.mMinScale, rect.centerX(), rect.centerY()));
                    handled = true;
                    break;
                }
                break;
        }
        if (this.mGestureDetector != null && this.mGestureDetector.onTouchEvent(ev)) {
            handled = true;
        }
        if (this.mScaleDragDetector == null || !this.mScaleDragDetector.onTouchEvent(ev)) {
            return handled;
        }
        return true;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setAllowParentInterceptOnEdge(boolean allow) {
        this.mAllowParentInterceptOnEdge = allow;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setMinScale(float minScale) {
        checkZoomLevels(minScale, this.mMidScale, this.mMaxScale);
        this.mMinScale = minScale;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setMidScale(float midScale) {
        checkZoomLevels(this.mMinScale, midScale, this.mMaxScale);
        this.mMidScale = midScale;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public void setMaxScale(float maxScale) {
        checkZoomLevels(this.mMinScale, this.mMidScale, maxScale);
        this.mMaxScale = maxScale;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final void setOnLongClickListener(View.OnLongClickListener listener) {
        this.mLongClickListener = listener;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final void setOnMatrixChangeListener(OnMatrixChangedListener listener) {
        this.mMatrixChangeListener = listener;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final void setOnPhotoTapListener(OnPhotoTapListener listener) {
        this.mPhotoTapListener = listener;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final void setOnViewTapListener(OnViewTapListener listener) {
        this.mViewTapListener = listener;
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final void setScaleType(ImageView.ScaleType scaleType) {
        if (isSupportedScaleType(scaleType) && scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            update();
        }
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final void setZoomable(boolean zoomable) {
        this.mZoomEnabled = zoomable;
        update();
    }

    public final void update() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (this.mZoomEnabled) {
            setImageViewScaleTypeMatrix(imageView);
            updateBaseMatrix(imageView.getDrawable());
            return;
        }
        resetMatrix();
    }

    @Override // com.cdlinglu.utils.Photo.zoom.IPhotoView
    public final void zoomTo(float scale, float focalX, float focalY) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            imageView.post(new AnimatedZoomRunnable(getScale(), scale, focalX, focalY));
        }
    }

    protected Matrix getDisplayMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    private void cancelFling() {
        if (this.mCurrentFlingRunnable != null) {
            this.mCurrentFlingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    public void checkAndDisplayMatrix() {
        checkMatrixBounds();
        setImageViewMatrix(getDisplayMatrix());
    }

    private void checkImageViewScaleType() {
        ImageView imageView = getImageView();
        if (imageView != null && !(imageView instanceof PhotoView) && imageView.getScaleType() != ImageView.ScaleType.MATRIX) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private void checkMatrixBounds() {
        RectF rect;
        ImageView imageView = getImageView();
        if (imageView != null && (rect = getDisplayRect(getDisplayMatrix())) != null) {
            float height = rect.height();
            float width = rect.width();
            float deltaX = 0.0f;
            float deltaY = 0.0f;
            int viewHeight = imageView.getHeight();
            if (height <= viewHeight) {
                switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                    case 2:
                        deltaY = -rect.top;
                        break;
                    case 3:
                        deltaY = (viewHeight - height) - rect.top;
                        break;
                    default:
                        deltaY = ((viewHeight - height) / 2.0f) - rect.top;
                        break;
                }
            } else if (rect.top > 0.0f) {
                deltaY = -rect.top;
            } else if (rect.bottom < viewHeight) {
                deltaY = viewHeight - rect.bottom;
            }
            int viewWidth = imageView.getWidth();
            if (width <= viewWidth) {
                switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                    case 2:
                        deltaX = -rect.left;
                        break;
                    case 3:
                        deltaX = (viewWidth - width) - rect.left;
                        break;
                    default:
                        deltaX = ((viewWidth - width) / 2.0f) - rect.left;
                        break;
                }
                this.mScrollEdge = 2;
            } else if (rect.left > 0.0f) {
                this.mScrollEdge = 0;
                deltaX = -rect.left;
            } else if (rect.right < viewWidth) {
                deltaX = viewWidth - rect.right;
                this.mScrollEdge = 1;
            } else {
                this.mScrollEdge = -1;
            }
            this.mSuppMatrix.postTranslate(deltaX, deltaY);
        }
    }

    private RectF getDisplayRect(Matrix matrix) {
        Drawable d;
        ImageView imageView = getImageView();
        if (imageView == null || (d = imageView.getDrawable()) == null) {
            return null;
        }
        this.mDisplayRect.set(0.0f, 0.0f, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        matrix.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
    }

    private float getValue(Matrix matrix, int whichValue) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[whichValue];
    }

    private void resetMatrix() {
        this.mSuppMatrix.reset();
        setImageViewMatrix(getDisplayMatrix());
        checkMatrixBounds();
    }

    public void setImageViewMatrix(Matrix matrix) {
        RectF displayRect;
        ImageView imageView = getImageView();
        if (imageView != null) {
            checkImageViewScaleType();
            imageView.setImageMatrix(matrix);
            if (this.mMatrixChangeListener != null && (displayRect = getDisplayRect(matrix)) != null) {
                this.mMatrixChangeListener.onMatrixChanged(displayRect);
            }
        }
    }

    private void updateBaseMatrix(Drawable d) {
        ImageView imageView = getImageView();
        if (imageView != null && d != null) {
            float viewWidth = imageView.getWidth();
            float viewHeight = imageView.getHeight();
            int drawableWidth = d.getIntrinsicWidth();
            int drawableHeight = d.getIntrinsicHeight();
            this.mBaseMatrix.reset();
            float widthScale = viewWidth / drawableWidth;
            float heightScale = viewHeight / drawableHeight;
            if (this.mScaleType != ImageView.ScaleType.CENTER) {
                if (this.mScaleType != ImageView.ScaleType.CENTER_CROP) {
                    if (this.mScaleType != ImageView.ScaleType.CENTER_INSIDE) {
                        RectF mTempSrc = new RectF(0.0f, 0.0f, drawableWidth, drawableHeight);
                        RectF mTempDst = new RectF(0.0f, 0.0f, viewWidth, viewHeight);
                        switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                            case 2:
                                this.mBaseMatrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.START);
                                break;
                            case 3:
                                this.mBaseMatrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.END);
                                break;
                            case 4:
                                this.mBaseMatrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.CENTER);
                                break;
                            case 5:
                                this.mBaseMatrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.FILL);
                                break;
                        }
                    } else {
                        float scale = Math.min(1.0f, Math.min(widthScale, heightScale));
                        this.mBaseMatrix.postScale(scale, scale);
                        this.mBaseMatrix.postTranslate((viewWidth - (drawableWidth * scale)) / 2.0f, (viewHeight - (drawableHeight * scale)) / 2.0f);
                    }
                } else {
                    float scale2 = Math.max(widthScale, heightScale);
                    this.mBaseMatrix.postScale(scale2, scale2);
                    this.mBaseMatrix.postTranslate((viewWidth - (drawableWidth * scale2)) / 2.0f, (viewHeight - (drawableHeight * scale2)) / 2.0f);
                }
            } else {
                this.mBaseMatrix.postTranslate((viewWidth - drawableWidth) / 2.0f, (viewHeight - drawableHeight) / 2.0f);
            }
            resetMatrix();
        }
    }

    /* loaded from: classes.dex */
    public class AnimatedZoomRunnable implements Runnable {
        static final float ANIMATION_SCALE_PER_ITERATION_IN = 1.07f;
        static final float ANIMATION_SCALE_PER_ITERATION_OUT = 0.93f;
        private final float mDeltaScale;
        private final float mFocalX;
        private final float mFocalY;
        private final float mTargetZoom;

        public AnimatedZoomRunnable(float currentZoom, float targetZoom, float focalX, float focalY) {
            PhotoViewAttacher.this = r2;
            this.mTargetZoom = targetZoom;
            this.mFocalX = focalX;
            this.mFocalY = focalY;
            if (currentZoom < targetZoom) {
                this.mDeltaScale = ANIMATION_SCALE_PER_ITERATION_IN;
            } else {
                this.mDeltaScale = ANIMATION_SCALE_PER_ITERATION_OUT;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView != null) {
                PhotoViewAttacher.this.mSuppMatrix.postScale(this.mDeltaScale, this.mDeltaScale, this.mFocalX, this.mFocalY);
                PhotoViewAttacher.this.checkAndDisplayMatrix();
                float currentScale = PhotoViewAttacher.this.getScale();
                if ((this.mDeltaScale <= 1.0f || currentScale >= this.mTargetZoom) && (this.mDeltaScale >= 1.0f || this.mTargetZoom >= currentScale)) {
                    float delta = this.mTargetZoom / currentScale;
                    PhotoViewAttacher.this.mSuppMatrix.postScale(delta, delta, this.mFocalX, this.mFocalY);
                    PhotoViewAttacher.this.checkAndDisplayMatrix();
                    return;
                }
                Compat.postOnAnimation(imageView, this);
            }
        }
    }

    /* loaded from: classes.dex */
    public class FlingRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final ScrollerProxy mScroller;

        public FlingRunnable(Context context) {
            PhotoViewAttacher.this = r2;
            this.mScroller = ScrollerProxy.getScroller(context);
        }

        public void cancelFling() {
            if (PhotoViewAttacher.DEBUG) {
                Log.d(PhotoViewAttacher.LOG_TAG, "Cancel Fling");
            }
            this.mScroller.forceFinished(true);
        }

        public void fling(int viewWidth, int viewHeight, int velocityX, int velocityY) {
            int maxX;
            int minX;
            int maxY;
            int minY;
            RectF rect = PhotoViewAttacher.this.getDisplayRect();
            if (rect != null) {
                int startX = Math.round(-rect.left);
                if (viewWidth < rect.width()) {
                    minX = 0;
                    maxX = Math.round(rect.width() - viewWidth);
                } else {
                    maxX = startX;
                    minX = startX;
                }
                int startY = Math.round(-rect.top);
                if (viewHeight < rect.height()) {
                    minY = 0;
                    maxY = Math.round(rect.height() - viewHeight);
                } else {
                    maxY = startY;
                    minY = startY;
                }
                this.mCurrentX = startX;
                this.mCurrentY = startY;
                if (PhotoViewAttacher.DEBUG) {
                    Log.d(PhotoViewAttacher.LOG_TAG, "fling. StartX:" + startX + " StartY:" + startY + " MaxX:" + maxX + " MaxY:" + maxY);
                }
                if (startX != maxX || startY != maxY) {
                    this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY, 0, 0);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView != null && this.mScroller.computeScrollOffset()) {
                int newX = this.mScroller.getCurrX();
                int newY = this.mScroller.getCurrY();
                if (PhotoViewAttacher.DEBUG) {
                    Log.d(PhotoViewAttacher.LOG_TAG, "fling run(). CurrentX:" + this.mCurrentX + " CurrentY:" + this.mCurrentY + " NewX:" + newX + " NewY:" + newY);
                }
                PhotoViewAttacher.this.mSuppMatrix.postTranslate(this.mCurrentX - newX, this.mCurrentY - newY);
                PhotoViewAttacher.this.setImageViewMatrix(PhotoViewAttacher.this.getDisplayMatrix());
                this.mCurrentX = newX;
                this.mCurrentY = newY;
                Compat.postOnAnimation(imageView, this);
            }
        }
    }
}
