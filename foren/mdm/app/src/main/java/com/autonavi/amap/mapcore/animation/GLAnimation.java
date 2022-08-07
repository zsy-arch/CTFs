package com.autonavi.amap.mapcore.animation;

import android.content.Context;
import android.graphics.RectF;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import com.amap.api.maps.model.animation.Animation;

/* loaded from: classes.dex */
public class GLAnimation implements Cloneable {
    public static final int ABSOLUTE = 0;
    public static final int INFINITE = -1;
    public static final int RELATIVE_TO_PARENT = 2;
    public static final int RELATIVE_TO_SELF = 1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    public static final int START_ON_FIRST_FRAME = -1;
    public static final int ZORDER_BOTTOM = -1;
    public static final int ZORDER_NORMAL = 0;
    public static final int ZORDER_TOP = 1;
    private int mBackgroundColor;
    Interpolator mInterpolator;
    Animation.AnimationListener mListener;
    private Handler mListenerHandler;
    private Runnable mOnEnd;
    private Runnable mOnRepeat;
    private Runnable mOnStart;
    long mStartOffset;
    private int mZAdjustment;
    boolean mEnded = false;
    boolean mStarted = false;
    boolean mCycleFlip = false;
    boolean mInitialized = false;
    boolean mFillBefore = true;
    boolean mFillAfter = false;
    boolean mFillEnabled = false;
    long mStartTime = -1;
    long mDuration = 500;
    int mRepeatCount = 0;
    int mRepeated = 0;
    int mRepeatMode = 1;
    private float mScaleFactor = 1.0f;
    private boolean mDetachWallpaper = false;
    private boolean mMore = true;
    private boolean mOneMoreTime = true;
    RectF mPreviousRegion = new RectF();
    RectF mRegion = new RectF();
    GLTransformation mTransformation = new GLTransformation();
    GLTransformation mPreviousTransformation = new GLTransformation();

    public GLAnimation() {
        ensureInterpolator();
    }

    public GLAnimation clone() throws CloneNotSupportedException {
        GLAnimation gLAnimation = (GLAnimation) super.clone();
        gLAnimation.mPreviousRegion = new RectF();
        gLAnimation.mRegion = new RectF();
        gLAnimation.mTransformation = new GLTransformation();
        gLAnimation.mPreviousTransformation = new GLTransformation();
        return gLAnimation;
    }

    public void reset() {
        this.mPreviousRegion.setEmpty();
        this.mPreviousTransformation.clear();
        this.mInitialized = false;
        this.mCycleFlip = false;
        this.mRepeated = 0;
        this.mMore = true;
        this.mOneMoreTime = true;
        this.mListenerHandler = null;
    }

    public void cancel() {
        if (this.mStarted && !this.mEnded) {
            fireAnimationEnd();
            this.mEnded = true;
        }
        this.mStartTime = Long.MIN_VALUE;
        this.mOneMoreTime = false;
        this.mMore = false;
    }

    public void detach() {
        if (this.mStarted && !this.mEnded) {
            this.mEnded = true;
            fireAnimationEnd();
        }
    }

    public boolean isInitialized() {
        return this.mInitialized;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        reset();
        this.mInitialized = true;
    }

    public void setListenerHandler(Handler handler) {
        if (this.mListenerHandler == null) {
            this.mOnStart = new Runnable() { // from class: com.autonavi.amap.mapcore.animation.GLAnimation.1
                @Override // java.lang.Runnable
                public void run() {
                    if (GLAnimation.this.mListener != null) {
                        GLAnimation.this.mListener.onAnimationStart();
                    }
                }
            };
            this.mOnEnd = new Runnable() { // from class: com.autonavi.amap.mapcore.animation.GLAnimation.2
                @Override // java.lang.Runnable
                public void run() {
                    if (GLAnimation.this.mListener != null) {
                        GLAnimation.this.mListener.onAnimationEnd();
                    }
                }
            };
        }
        this.mListenerHandler = handler;
    }

    public void setInterpolator(Context context, int i) {
        setInterpolator(AnimationUtils.loadInterpolator(context, i));
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public void setStartOffset(long j) {
        this.mStartOffset = j;
    }

    public void setDuration(long j) {
        if (j < 0) {
            j = 0;
        }
        this.mDuration = j;
    }

    public void restrictDuration(long j) {
        if (this.mStartOffset > j) {
            this.mStartOffset = j;
            this.mDuration = 0L;
            this.mRepeatCount = 0;
            return;
        }
        long j2 = this.mDuration + this.mStartOffset;
        if (j2 > j) {
            this.mDuration = j - this.mStartOffset;
            j2 = j;
        }
        if (this.mDuration <= 0) {
            this.mDuration = 0L;
            this.mRepeatCount = 0;
        } else if (this.mRepeatCount < 0 || this.mRepeatCount > j || this.mRepeatCount * j2 > j) {
            this.mRepeatCount = ((int) (j / j2)) - 1;
            if (this.mRepeatCount < 0) {
                this.mRepeatCount = 0;
            }
        }
    }

    public void scaleCurrentDuration(float f) {
        this.mDuration = ((float) this.mDuration) * f;
        this.mStartOffset = ((float) this.mStartOffset) * f;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
        this.mEnded = false;
        this.mStarted = false;
        this.mCycleFlip = false;
        this.mRepeated = 0;
        this.mMore = true;
    }

    public void start() {
        setStartTime(-1L);
    }

    public void startNow() {
        setStartTime(AnimationUtils.currentAnimationTimeMillis());
    }

    public void setRepeatMode(int i) {
        this.mRepeatMode = i;
    }

    public void setRepeatCount(int i) {
        if (i < 0) {
            i = -1;
        }
        this.mRepeatCount = i;
    }

    public boolean isFillEnabled() {
        return this.mFillEnabled;
    }

    public void setFillEnabled(boolean z) {
        this.mFillEnabled = z;
    }

    public void setFillBefore(boolean z) {
        this.mFillBefore = z;
    }

    public void setFillAfter(boolean z) {
        this.mFillAfter = z;
    }

    public void setZAdjustment(int i) {
        this.mZAdjustment = i;
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
    }

    protected float getScaleFactor() {
        return this.mScaleFactor;
    }

    public void setDetachWallpaper(boolean z) {
        this.mDetachWallpaper = z;
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public long getStartOffset() {
        return this.mStartOffset;
    }

    public int getRepeatMode() {
        return this.mRepeatMode;
    }

    public int getRepeatCount() {
        return this.mRepeatCount;
    }

    public boolean getFillBefore() {
        return this.mFillBefore;
    }

    public boolean getFillAfter() {
        return this.mFillAfter;
    }

    public int getZAdjustment() {
        return this.mZAdjustment;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public boolean getDetachWallpaper() {
        return this.mDetachWallpaper;
    }

    public boolean willChangeTransformationMatrix() {
        return true;
    }

    public boolean willChangeBounds() {
        return true;
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.mListener = animationListener;
    }

    protected void ensureInterpolator() {
        if (this.mInterpolator == null) {
            this.mInterpolator = new AccelerateDecelerateInterpolator();
        }
    }

    public long computeDurationHint() {
        return (getStartOffset() + getDuration()) * (getRepeatCount() + 1);
    }

    public boolean getTransformation(long j, GLTransformation gLTransformation) {
        float f;
        if (this.mStartTime == -1) {
            this.mStartTime = j;
        }
        long startOffset = getStartOffset();
        long j2 = this.mDuration;
        if (j2 != 0) {
            f = ((float) (j - (startOffset + this.mStartTime))) / ((float) j2);
        } else {
            f = j < this.mStartTime ? 0.0f : 1.0f;
        }
        boolean z = f >= 1.0f;
        this.mMore = !z;
        float max = !this.mFillEnabled ? Math.max(Math.min(f, 1.0f), 0.0f) : f;
        if ((max >= 0.0f || this.mFillBefore) && (max <= 1.0f || this.mFillAfter)) {
            if (!this.mStarted) {
                fireAnimationStart();
                this.mStarted = true;
            }
            if (this.mFillEnabled) {
                max = Math.max(Math.min(max, 1.0f), 0.0f);
            }
            if (this.mCycleFlip) {
                max = 1.0f - max;
            }
            applyTransformation(this.mInterpolator.getInterpolation(max), gLTransformation);
        }
        if (z) {
            if (this.mRepeatCount != this.mRepeated) {
                if (this.mRepeatCount > 0) {
                    this.mRepeated++;
                }
                if (this.mRepeatMode == 2) {
                    this.mCycleFlip = !this.mCycleFlip;
                }
                this.mStartTime = -1L;
                this.mMore = true;
                fireAnimationRepeat();
            } else if (!this.mEnded) {
                this.mEnded = true;
                fireAnimationEnd();
            }
        }
        if (this.mMore || !this.mOneMoreTime) {
            return this.mMore;
        }
        this.mOneMoreTime = false;
        return true;
    }

    private void fireAnimationStart() {
        if (this.mListener == null) {
            return;
        }
        if (this.mListenerHandler == null) {
            this.mListener.onAnimationStart();
        } else {
            this.mListenerHandler.postAtFrontOfQueue(this.mOnStart);
        }
    }

    private void fireAnimationRepeat() {
    }

    private void fireAnimationEnd() {
        if (this.mListener == null) {
            return;
        }
        if (this.mListenerHandler == null) {
            this.mListener.onAnimationEnd();
        } else {
            this.mListenerHandler.postAtFrontOfQueue(this.mOnEnd);
        }
    }

    public boolean getTransformation(long j, GLTransformation gLTransformation, float f) {
        this.mScaleFactor = f;
        return getTransformation(j, gLTransformation);
    }

    public boolean hasStarted() {
        return this.mStarted;
    }

    public boolean hasEnded() {
        return this.mEnded;
    }

    protected void applyTransformation(float f, GLTransformation gLTransformation) {
    }

    protected float resolveSize(int i, float f, int i2, int i3) {
        switch (i) {
            case 0:
            default:
                return f;
            case 1:
                return f * i2;
            case 2:
                return f * i3;
        }
    }

    public void getInvalidateRegion(int i, int i2, int i3, int i4, RectF rectF, Transformation transformation) {
        RectF rectF2 = this.mRegion;
        RectF rectF3 = this.mPreviousRegion;
        rectF.set(i, i2, i3, i4);
        transformation.getMatrix().mapRect(rectF);
        rectF.inset(-1.0f, -1.0f);
        rectF2.set(rectF);
        rectF.union(rectF3);
        rectF3.set(rectF2);
    }

    public void initializeInvalidateRegion(int i, int i2, int i3, int i4) {
        RectF rectF = this.mPreviousRegion;
        rectF.set(i, i2, i3, i4);
        rectF.inset(-1.0f, -1.0f);
        if (this.mFillBefore) {
            applyTransformation(this.mInterpolator.getInterpolation(0.0f), this.mPreviousTransformation);
        }
    }

    public boolean hasAlpha() {
        return false;
    }
}
