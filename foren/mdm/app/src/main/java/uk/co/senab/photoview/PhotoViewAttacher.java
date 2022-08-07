package uk.co.senab.photoview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import com.hy.frame.util.MyLog;
import java.lang.ref.WeakReference;
import uk.co.senab.photoview.gestures.OnGestureListener;
import uk.co.senab.photoview.gestures.VersionedGestureDetector;
import uk.co.senab.photoview.scrollerproxy.ScrollerProxy;

/* loaded from: classes.dex */
public class PhotoViewAttacher implements IPhotoView, View.OnTouchListener, OnGestureListener, ViewTreeObserver.OnGlobalLayoutListener {
    static final int EDGE_BOTH = 2;
    static final int EDGE_LEFT = 0;
    static final int EDGE_NONE = -1;
    static final int EDGE_RIGHT = 1;
    int ZOOM_DURATION;
    private boolean mAllowParentInterceptOnEdge;
    private final Matrix mBaseMatrix;
    private boolean mBlockParentIntercept;
    private FlingRunnable mCurrentFlingRunnable;
    private final RectF mDisplayRect;
    private final Matrix mDrawMatrix;
    private GestureDetector mGestureDetector;
    private WeakReference<ImageView> mImageView;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mIvTop;
    private View.OnLongClickListener mLongClickListener;
    private OnMatrixChangedListener mMatrixChangeListener;
    private final float[] mMatrixValues;
    private float mMaxScale;
    private float mMidScale;
    private float mMinScale;
    private OnPhotoTapListener mPhotoTapListener;
    private OnScaleChangeListener mScaleChangeListener;
    private uk.co.senab.photoview.gestures.GestureDetector mScaleDragDetector;
    private ImageView.ScaleType mScaleType;
    private int mScrollEdge;
    private final Matrix mSuppMatrix;
    private OnViewTapListener mViewTapListener;
    private boolean mZoomEnabled;
    private static final String LOG_TAG = "PhotoViewAttacher";
    private static final boolean DEBUG = Log.isLoggable(LOG_TAG, 3);
    static final Interpolator sInterpolator = new AccelerateDecelerateInterpolator();

    /* loaded from: classes2.dex */
    public interface OnMatrixChangedListener {
        void onMatrixChanged(RectF rectF);
    }

    /* loaded from: classes2.dex */
    public interface OnPhotoTapListener {
        void onPhotoTap(View view, float f, float f2);
    }

    /* loaded from: classes2.dex */
    public interface OnScaleChangeListener {
        void onScaleChange(float f, float f2, float f3);
    }

    /* loaded from: classes2.dex */
    public interface OnViewTapListener {
        void onViewTap(View view, float f, float f2);
    }

    private static void checkZoomLevels(float minZoom, float midZoom, float maxZoom) {
        if (minZoom >= midZoom) {
            throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
        } else if (midZoom >= maxZoom) {
            throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
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

    /* renamed from: uk.co.senab.photoview.PhotoViewAttacher$2 */
    /* loaded from: classes2.dex */
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
        if (imageView != null && !(imageView instanceof IPhotoView) && !ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        }
    }

    public PhotoViewAttacher(ImageView imageView) {
        this(imageView, true);
    }

    public PhotoViewAttacher(ImageView imageView, boolean zoomable) {
        this.ZOOM_DURATION = 200;
        this.mMinScale = 1.0f;
        this.mMidScale = 1.75f;
        this.mMaxScale = 3.0f;
        this.mAllowParentInterceptOnEdge = true;
        this.mBlockParentIntercept = false;
        this.mBaseMatrix = new Matrix();
        this.mDrawMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayRect = new RectF();
        this.mMatrixValues = new float[9];
        this.mScrollEdge = 2;
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mImageView = new WeakReference<>(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver observer = imageView.getViewTreeObserver();
        if (observer != null) {
            observer.addOnGlobalLayoutListener(this);
        }
        setImageViewScaleTypeMatrix(imageView);
        if (!imageView.isInEditMode()) {
            this.mScaleDragDetector = VersionedGestureDetector.newInstance(imageView.getContext(), this);
            this.mGestureDetector = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: uk.co.senab.photoview.PhotoViewAttacher.1
                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public void onLongPress(MotionEvent e) {
                    if (PhotoViewAttacher.this.mLongClickListener != null) {
                        PhotoViewAttacher.this.mLongClickListener.onLongClick(PhotoViewAttacher.this.getImageView());
                    }
                }
            });
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
            setZoomable(zoomable);
        }
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener newOnDoubleTapListener) {
        if (newOnDoubleTapListener != null) {
            this.mGestureDetector.setOnDoubleTapListener(newOnDoubleTapListener);
        } else {
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
        }
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnScaleChangeListener(OnScaleChangeListener onScaleChangeListener) {
        this.mScaleChangeListener = onScaleChangeListener;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public boolean canZoom() {
        return this.mZoomEnabled;
    }

    public void cleanup() {
        if (this.mImageView != null) {
            ImageView imageView = this.mImageView.get();
            if (imageView != null) {
                ViewTreeObserver observer = imageView.getViewTreeObserver();
                if (observer != null && observer.isAlive()) {
                    observer.removeGlobalOnLayoutListener(this);
                }
                imageView.setOnTouchListener(null);
                cancelFling();
            }
            if (this.mGestureDetector != null) {
                this.mGestureDetector.setOnDoubleTapListener(null);
            }
            this.mMatrixChangeListener = null;
            this.mPhotoTapListener = null;
            this.mViewTapListener = null;
            this.mImageView = null;
        }
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public RectF getDisplayRect() {
        checkMatrixBounds();
        return getDisplayRect(getDrawMatrix());
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public boolean setDisplayMatrix(Matrix finalMatrix) {
        if (finalMatrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        ImageView imageView = getImageView();
        if (imageView == null || imageView.getDrawable() == null) {
            return false;
        }
        this.mSuppMatrix.set(finalMatrix);
        setImageViewMatrix(getDrawMatrix());
        checkMatrixBounds();
        return true;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setPhotoViewRotation(float degrees) {
        this.mSuppMatrix.setRotate(degrees % 360.0f);
        checkAndDisplayMatrix();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setRotationTo(float degrees) {
        this.mSuppMatrix.setRotate(degrees % 360.0f);
        checkAndDisplayMatrix();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setRotationBy(float degrees) {
        this.mSuppMatrix.postRotate(degrees % 360.0f);
        checkAndDisplayMatrix();
    }

    public ImageView getImageView() {
        ImageView imageView = null;
        if (this.mImageView != null) {
            imageView = this.mImageView.get();
        }
        if (imageView == null) {
            cleanup();
            MyLog.i(LOG_TAG, "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
        }
        return imageView;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public float getMinimumScale() {
        return this.mMinScale;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public float getMediumScale() {
        return this.mMidScale;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public float getMaximumScale() {
        return this.mMaxScale;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public float getScale() {
        return (float) Math.sqrt(((float) Math.pow(getValue(this.mSuppMatrix, 0), 2.0d)) + ((float) Math.pow(getValue(this.mSuppMatrix, 3), 2.0d)));
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // uk.co.senab.photoview.gestures.OnGestureListener
    public void onDrag(float dx, float dy) {
        if (!this.mScaleDragDetector.isScaling()) {
            if (DEBUG) {
                MyLog.d(LOG_TAG, String.format("onDrag: dx: %.2f. dy: %.2f", Float.valueOf(dx), Float.valueOf(dy)));
            }
            ImageView imageView = getImageView();
            this.mSuppMatrix.postTranslate(dx, dy);
            checkAndDisplayMatrix();
            ViewParent parent = imageView.getParent();
            if (!this.mAllowParentInterceptOnEdge || this.mScaleDragDetector.isScaling() || this.mBlockParentIntercept) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            } else if ((this.mScrollEdge == 2 || ((this.mScrollEdge == 0 && dx >= 1.0f) || (this.mScrollEdge == 1 && dx <= -1.0f))) && parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    @Override // uk.co.senab.photoview.gestures.OnGestureListener
    public void onFling(float startX, float startY, float velocityX, float velocityY) {
        if (DEBUG) {
            MyLog.d(LOG_TAG, "onFling. sX: " + startX + " sY: " + startY + " Vx: " + velocityX + " Vy: " + velocityY);
        }
        ImageView imageView = getImageView();
        this.mCurrentFlingRunnable = new FlingRunnable(imageView.getContext());
        this.mCurrentFlingRunnable.fling(getImageViewWidth(imageView), getImageViewHeight(imageView), (int) velocityX, (int) velocityY);
        imageView.post(this.mCurrentFlingRunnable);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (this.mZoomEnabled) {
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
                return;
            }
            return;
        }
        updateBaseMatrix(imageView.getDrawable());
    }

    @Override // uk.co.senab.photoview.gestures.OnGestureListener
    public void onScale(float scaleFactor, float focusX, float focusY) {
        if (DEBUG) {
            MyLog.d(LOG_TAG, String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", Float.valueOf(scaleFactor), Float.valueOf(focusX), Float.valueOf(focusY)));
        }
        if (getScale() < this.mMaxScale || scaleFactor < 1.0f) {
            if (this.mScaleChangeListener != null) {
                this.mScaleChangeListener.onScaleChange(scaleFactor, focusX, focusY);
            }
            this.mSuppMatrix.postScale(scaleFactor, scaleFactor, focusX, focusY);
            checkAndDisplayMatrix();
        }
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View v, MotionEvent ev) {
        RectF rect;
        boolean handled = false;
        if (!this.mZoomEnabled || !hasDrawable((ImageView) v)) {
            return false;
        }
        ViewParent parent = v.getParent();
        switch (ev.getAction()) {
            case 0:
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                } else {
                    MyLog.i(LOG_TAG, "onTouch getParent() returned null");
                }
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
        if (this.mScaleDragDetector != null) {
            boolean wasScaling = this.mScaleDragDetector.isScaling();
            boolean wasDragging = this.mScaleDragDetector.isDragging();
            handled = this.mScaleDragDetector.onTouchEvent(ev);
            this.mBlockParentIntercept = (!wasScaling && !this.mScaleDragDetector.isScaling()) && (!wasDragging && !this.mScaleDragDetector.isDragging());
        }
        if (this.mGestureDetector == null || !this.mGestureDetector.onTouchEvent(ev)) {
            return handled;
        }
        return true;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setAllowParentInterceptOnEdge(boolean allow) {
        this.mAllowParentInterceptOnEdge = allow;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public void setMinScale(float minScale) {
        setMinimumScale(minScale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setMinimumScale(float minimumScale) {
        checkZoomLevels(minimumScale, this.mMidScale, this.mMaxScale);
        this.mMinScale = minimumScale;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public void setMidScale(float midScale) {
        setMediumScale(midScale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setMediumScale(float mediumScale) {
        checkZoomLevels(this.mMinScale, mediumScale, this.mMaxScale);
        this.mMidScale = mediumScale;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    @Deprecated
    public void setMaxScale(float maxScale) {
        setMaximumScale(maxScale);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setMaximumScale(float maximumScale) {
        checkZoomLevels(this.mMinScale, this.mMidScale, maximumScale);
        this.mMaxScale = maximumScale;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        checkZoomLevels(minimumScale, mediumScale, maximumScale);
        this.mMinScale = minimumScale;
        this.mMidScale = mediumScale;
        this.mMaxScale = maximumScale;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnLongClickListener(View.OnLongClickListener listener) {
        this.mLongClickListener = listener;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnMatrixChangeListener(OnMatrixChangedListener listener) {
        this.mMatrixChangeListener = listener;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnPhotoTapListener(OnPhotoTapListener listener) {
        this.mPhotoTapListener = listener;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public OnPhotoTapListener getOnPhotoTapListener() {
        return this.mPhotoTapListener;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setOnViewTapListener(OnViewTapListener listener) {
        this.mViewTapListener = listener;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public OnViewTapListener getOnViewTapListener() {
        return this.mViewTapListener;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setScale(float scale) {
        setScale(scale, false);
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setScale(float scale, boolean animate) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            setScale(scale, imageView.getRight() / 2, imageView.getBottom() / 2, animate);
        }
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (scale < this.mMinScale || scale > this.mMaxScale) {
            MyLog.i(LOG_TAG, "Scale must be within the range of minScale and maxScale");
        } else if (animate) {
            imageView.post(new AnimatedZoomRunnable(getScale(), scale, focalX, focalY));
        } else {
            this.mSuppMatrix.setScale(scale, scale, focalX, focalY);
            checkAndDisplayMatrix();
        }
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (isSupportedScaleType(scaleType) && scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            update();
        }
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setZoomable(boolean zoomable) {
        this.mZoomEnabled = zoomable;
        update();
    }

    public void update() {
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

    @Override // uk.co.senab.photoview.IPhotoView
    public Matrix getDisplayMatrix() {
        return new Matrix(getDrawMatrix());
    }

    public Matrix getDrawMatrix() {
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

    private void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private void checkImageViewScaleType() {
        ImageView imageView = getImageView();
        if (imageView != null && !(imageView instanceof IPhotoView) && !ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private boolean checkMatrixBounds() {
        RectF rect;
        ImageView imageView = getImageView();
        if (imageView == null || (rect = getDisplayRect(getDrawMatrix())) == null) {
            return false;
        }
        float height = rect.height();
        float width = rect.width();
        float deltaX = 0.0f;
        float deltaY = 0.0f;
        int viewHeight = getImageViewHeight(imageView);
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
        int viewWidth = getImageViewWidth(imageView);
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
        return true;
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

    @Override // uk.co.senab.photoview.IPhotoView
    public Bitmap getVisibleRectangleBitmap() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return null;
        }
        return imageView.getDrawingCache();
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public void setZoomTransitionDuration(int milliseconds) {
        if (milliseconds < 0) {
            milliseconds = 200;
        }
        this.ZOOM_DURATION = milliseconds;
    }

    @Override // uk.co.senab.photoview.IPhotoView
    public IPhotoView getIPhotoViewImplementation() {
        return this;
    }

    private float getValue(Matrix matrix, int whichValue) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[whichValue];
    }

    private void resetMatrix() {
        this.mSuppMatrix.reset();
        setImageViewMatrix(getDrawMatrix());
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
            float viewWidth = getImageViewWidth(imageView);
            float viewHeight = getImageViewHeight(imageView);
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

    private int getImageViewWidth(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private int getImageViewHeight(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    /* loaded from: classes2.dex */
    public class AnimatedZoomRunnable implements Runnable {
        private final float mFocalX;
        private final float mFocalY;
        private final long mStartTime = System.currentTimeMillis();
        private final float mZoomEnd;
        private final float mZoomStart;

        public AnimatedZoomRunnable(float currentZoom, float targetZoom, float focalX, float focalY) {
            PhotoViewAttacher.this = r3;
            this.mFocalX = focalX;
            this.mFocalY = focalY;
            this.mZoomStart = currentZoom;
            this.mZoomEnd = targetZoom;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView != null) {
                float t = interpolate();
                PhotoViewAttacher.this.onScale((this.mZoomStart + ((this.mZoomEnd - this.mZoomStart) * t)) / PhotoViewAttacher.this.getScale(), this.mFocalX, this.mFocalY);
                if (t < 1.0f) {
                    Compat.postOnAnimation(imageView, this);
                }
            }
        }

        private float interpolate() {
            return PhotoViewAttacher.sInterpolator.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.mStartTime)) * 1.0f) / PhotoViewAttacher.this.ZOOM_DURATION));
        }
    }

    /* loaded from: classes2.dex */
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
                MyLog.d(PhotoViewAttacher.LOG_TAG, "Cancel Fling");
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
                    MyLog.d(PhotoViewAttacher.LOG_TAG, "fling. StartX:" + startX + " StartY:" + startY + " MaxX:" + maxX + " MaxY:" + maxY);
                }
                if (startX != maxX || startY != maxY) {
                    this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY, 0, 0);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageView;
            if (!this.mScroller.isFinished() && (imageView = PhotoViewAttacher.this.getImageView()) != null && this.mScroller.computeScrollOffset()) {
                int newX = this.mScroller.getCurrX();
                int newY = this.mScroller.getCurrY();
                if (PhotoViewAttacher.DEBUG) {
                    MyLog.d(PhotoViewAttacher.LOG_TAG, "fling run(). CurrentX:" + this.mCurrentX + " CurrentY:" + this.mCurrentY + " NewX:" + newX + " NewY:" + newY);
                }
                PhotoViewAttacher.this.mSuppMatrix.postTranslate(this.mCurrentX - newX, this.mCurrentY - newY);
                PhotoViewAttacher.this.setImageViewMatrix(PhotoViewAttacher.this.getDrawMatrix());
                this.mCurrentX = newX;
                this.mCurrentY = newY;
                Compat.postOnAnimation(imageView, this);
            }
        }
    }
}
