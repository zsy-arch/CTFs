package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

/* loaded from: classes.dex */
public class BitmapPoolAdapter implements BitmapPool {
    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public int getMaxSize() {
        return 0;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void setSizeMultiplier(float sizeMultiplier) {
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public boolean put(Bitmap bitmap) {
        return false;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public Bitmap get(int width, int height, Bitmap.Config config) {
        return null;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public Bitmap getDirty(int width, int height, Bitmap.Config config) {
        return null;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void clearMemory() {
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void trimMemory(int level) {
    }
}
