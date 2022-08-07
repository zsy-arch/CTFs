package com.autonavi.amap.mapcore.animation;

import com.amap.api.maps.model.animation.Animation;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GLAnimationSet extends GLAnimation {
    private static final int PROPERTY_CHANGE_BOUNDS_MASK = 128;
    private static final int PROPERTY_DURATION_MASK = 32;
    private static final int PROPERTY_FILL_AFTER_MASK = 1;
    private static final int PROPERTY_FILL_BEFORE_MASK = 2;
    private static final int PROPERTY_MORPH_MATRIX_MASK = 64;
    private static final int PROPERTY_REPEAT_MODE_MASK = 4;
    private static final int PROPERTY_SHARE_INTERPOLATOR_MASK = 16;
    private static final int PROPERTY_START_OFFSET_MASK = 8;
    private boolean mDirty;
    private boolean mHasAlpha;
    private long mLastEnd;
    private long[] mStoredOffsets;
    private int mFlags = 0;
    private ArrayList<GLAnimation> mAnimations = new ArrayList<>();
    private GLTransformation mTempTransformation = new GLTransformation();

    public GLAnimationSet(boolean z) {
        setFlag(16, z);
        init();
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public GLAnimationSet clone() throws CloneNotSupportedException {
        GLAnimationSet gLAnimationSet = (GLAnimationSet) super.clone();
        gLAnimationSet.mTempTransformation = new GLTransformation();
        gLAnimationSet.mAnimations = new ArrayList<>();
        int size = this.mAnimations.size();
        ArrayList<GLAnimation> arrayList = this.mAnimations;
        for (int i = 0; i < size; i++) {
            gLAnimationSet.mAnimations.add(arrayList.get(i).clone());
        }
        return gLAnimationSet;
    }

    private void setFlag(int i, boolean z) {
        if (z) {
            this.mFlags |= i;
        } else {
            this.mFlags &= i ^ (-1);
        }
    }

    private void init() {
        this.mStartTime = 0L;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public void setFillAfter(boolean z) {
        this.mFlags |= 1;
        super.setFillAfter(z);
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public void setFillBefore(boolean z) {
        this.mFlags |= 2;
        super.setFillBefore(z);
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public void setRepeatMode(int i) {
        this.mFlags |= 4;
        super.setRepeatMode(i);
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public void setStartOffset(long j) {
        this.mFlags |= 8;
        super.setStartOffset(j);
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public boolean hasAlpha() {
        if (this.mDirty) {
            this.mHasAlpha = false;
            this.mDirty = false;
            int size = this.mAnimations.size();
            ArrayList<GLAnimation> arrayList = this.mAnimations;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                } else if (arrayList.get(i).hasAlpha()) {
                    this.mHasAlpha = true;
                    break;
                } else {
                    i++;
                }
            }
        }
        return this.mHasAlpha;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public void setDuration(long j) {
        this.mFlags |= 32;
        super.setDuration(j);
        this.mLastEnd = this.mStartOffset + this.mDuration;
    }

    public void addAnimation(Animation animation) {
        boolean z = false;
        this.mAnimations.add(animation.glAnimation);
        if (((this.mFlags & 64) == 0) && animation.glAnimation.willChangeTransformationMatrix()) {
            this.mFlags |= 64;
        }
        if ((this.mFlags & 128) == 0) {
            z = true;
        }
        if (z && animation.glAnimation.willChangeBounds()) {
            this.mFlags |= 128;
        }
        if ((this.mFlags & 32) == 32) {
            this.mLastEnd = this.mStartOffset + this.mDuration;
        } else if (this.mAnimations.size() == 1) {
            this.mDuration = animation.glAnimation.getStartOffset() + animation.glAnimation.getDuration();
            this.mLastEnd = this.mStartOffset + this.mDuration;
        } else {
            this.mLastEnd = Math.max(this.mLastEnd, animation.glAnimation.getStartOffset() + animation.glAnimation.getDuration());
            this.mDuration = this.mLastEnd - this.mStartOffset;
        }
        this.mDirty = true;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public void setStartTime(long j) {
        super.setStartTime(j);
        int size = this.mAnimations.size();
        ArrayList<GLAnimation> arrayList = this.mAnimations;
        for (int i = 0; i < size; i++) {
            arrayList.get(i).setStartTime(j);
        }
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public long getStartTime() {
        long j = Long.MAX_VALUE;
        int size = this.mAnimations.size();
        ArrayList<GLAnimation> arrayList = this.mAnimations;
        for (int i = 0; i < size; i++) {
            j = Math.min(j, arrayList.get(i).getStartTime());
        }
        return j;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public void restrictDuration(long j) {
        super.restrictDuration(j);
        ArrayList<GLAnimation> arrayList = this.mAnimations;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i).restrictDuration(j);
        }
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public long getDuration() {
        ArrayList<GLAnimation> arrayList = this.mAnimations;
        int size = arrayList.size();
        long j = 0;
        if ((this.mFlags & 32) == 32) {
            return this.mDuration;
        }
        for (int i = 0; i < size; i++) {
            j = Math.max(j, arrayList.get(i).getDuration());
        }
        return j;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public long computeDurationHint() {
        long j = 0;
        int size = this.mAnimations.size();
        ArrayList<GLAnimation> arrayList = this.mAnimations;
        for (int i = size - 1; i >= 0; i--) {
            j = arrayList.get(i).computeDurationHint();
            if (j <= j) {
                j = j;
            }
        }
        return j;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public boolean getTransformation(long j, GLTransformation gLTransformation) {
        int size = this.mAnimations.size();
        ArrayList<GLAnimation> arrayList = this.mAnimations;
        GLTransformation gLTransformation2 = this.mTempTransformation;
        gLTransformation.clear();
        boolean z = true;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = size - 1; i >= 0; i--) {
            GLAnimation gLAnimation = arrayList.get(i);
            gLTransformation2.clear();
            z3 = gLAnimation.getTransformation(j, gLTransformation, getScaleFactor()) || z3;
            z2 = z2 || gLAnimation.hasStarted();
            z = gLAnimation.hasEnded() && z;
        }
        if (z2 && !this.mStarted) {
            if (this.mListener != null) {
                this.mListener.onAnimationStart();
            }
            this.mStarted = true;
        }
        if (z != this.mEnded) {
            if (this.mListener != null) {
                this.mListener.onAnimationEnd();
            }
            this.mEnded = z;
        }
        return z3;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public void scaleCurrentDuration(float f) {
        ArrayList<GLAnimation> arrayList = this.mAnimations;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i).scaleCurrentDuration(f);
        }
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public void reset() {
        super.reset();
        restoreChildrenStartOffset();
    }

    void restoreChildrenStartOffset() {
        long[] jArr = this.mStoredOffsets;
        if (jArr != null) {
            ArrayList<GLAnimation> arrayList = this.mAnimations;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                arrayList.get(i).setStartOffset(jArr[i]);
            }
        }
    }

    public List<GLAnimation> getAnimations() {
        return this.mAnimations;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public boolean willChangeTransformationMatrix() {
        return (this.mFlags & 64) == 64;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    public boolean willChangeBounds() {
        return (this.mFlags & 128) == 128;
    }

    public void cleanAnimation() {
        this.mAnimations.clear();
    }
}
