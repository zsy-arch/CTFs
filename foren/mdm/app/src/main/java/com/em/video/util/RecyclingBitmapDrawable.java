package com.em.video.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* loaded from: classes.dex */
public class RecyclingBitmapDrawable extends BitmapDrawable {
    static final String TAG = "CountingBitmapDrawable";
    private int mCacheRefCount = 0;
    private int mDisplayRefCount = 0;
    private boolean mHasBeenDisplayed;

    public RecyclingBitmapDrawable(Resources res, Bitmap bitmap) {
        super(res, bitmap);
    }

    public void setIsDisplayed(boolean isDisplayed) {
        synchronized (this) {
            if (isDisplayed) {
                this.mDisplayRefCount++;
                this.mHasBeenDisplayed = true;
            } else {
                this.mDisplayRefCount--;
            }
        }
        checkState();
    }

    public void setIsCached(boolean isCached) {
        synchronized (this) {
            if (isCached) {
                this.mCacheRefCount++;
            } else {
                this.mCacheRefCount--;
            }
        }
        checkState();
    }

    private synchronized void checkState() {
        if (this.mCacheRefCount <= 0 && this.mDisplayRefCount <= 0 && this.mHasBeenDisplayed && hasValidBitmap()) {
            getBitmap().recycle();
        }
    }

    private synchronized boolean hasValidBitmap() {
        boolean z;
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                z = true;
            }
        }
        z = false;
        return z;
    }
}
