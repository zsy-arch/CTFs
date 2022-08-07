package com.lidroid.xutils.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.task.Priority;

/* loaded from: classes2.dex */
public class BitmapDisplayConfig {
    private Animation animation;
    private BitmapFactory bitmapFactory;
    private BitmapSize bitmapMaxSize;
    private Drawable loadFailedDrawable;
    private Drawable loadingDrawable;
    private Priority priority;
    private boolean autoRotation = false;
    private boolean showOriginal = false;
    private Bitmap.Config bitmapConfig = Bitmap.Config.RGB_565;

    public BitmapSize getBitmapMaxSize() {
        return this.bitmapMaxSize == null ? BitmapSize.ZERO : this.bitmapMaxSize;
    }

    public void setBitmapMaxSize(BitmapSize bitmapMaxSize) {
        this.bitmapMaxSize = bitmapMaxSize;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Drawable getLoadingDrawable() {
        return this.loadingDrawable;
    }

    public void setLoadingDrawable(Drawable loadingDrawable) {
        this.loadingDrawable = loadingDrawable;
    }

    public Drawable getLoadFailedDrawable() {
        return this.loadFailedDrawable;
    }

    public void setLoadFailedDrawable(Drawable loadFailedDrawable) {
        this.loadFailedDrawable = loadFailedDrawable;
    }

    public boolean isAutoRotation() {
        return this.autoRotation;
    }

    public void setAutoRotation(boolean autoRotation) {
        this.autoRotation = autoRotation;
    }

    public boolean isShowOriginal() {
        return this.showOriginal;
    }

    public void setShowOriginal(boolean showOriginal) {
        this.showOriginal = showOriginal;
    }

    public Bitmap.Config getBitmapConfig() {
        return this.bitmapConfig;
    }

    public void setBitmapConfig(Bitmap.Config bitmapConfig) {
        this.bitmapConfig = bitmapConfig;
    }

    public BitmapFactory getBitmapFactory() {
        return this.bitmapFactory;
    }

    public void setBitmapFactory(BitmapFactory bitmapFactory) {
        this.bitmapFactory = bitmapFactory;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String toString() {
        return (isShowOriginal() ? "" : this.bitmapMaxSize.toString()) + (this.bitmapFactory == null ? "" : this.bitmapFactory.getClass().getName());
    }

    public BitmapDisplayConfig cloneNew() {
        BitmapDisplayConfig config = new BitmapDisplayConfig();
        config.bitmapMaxSize = this.bitmapMaxSize;
        config.animation = this.animation;
        config.loadingDrawable = this.loadingDrawable;
        config.loadFailedDrawable = this.loadFailedDrawable;
        config.autoRotation = this.autoRotation;
        config.showOriginal = this.showOriginal;
        config.bitmapConfig = this.bitmapConfig;
        config.bitmapFactory = this.bitmapFactory;
        config.priority = this.priority;
        return config;
    }
}
