package com.lidroid.xutils.bitmap.callback;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class DefaultBitmapLoadCallBack<T extends View> extends BitmapLoadCallBack<T> {
    @Override // com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack
    public void onLoadCompleted(T container, String uri, Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
        setBitmap(container, bitmap);
        Animation animation = config.getAnimation();
        if (animation != null) {
            animationDisplay(container, animation);
        }
    }

    @Override // com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack
    public void onLoadFailed(T container, String uri, Drawable drawable) {
        setDrawable(container, drawable);
    }

    private void animationDisplay(T container, Animation animation) {
        try {
            Method cloneMethod = Animation.class.getDeclaredMethod("clone", new Class[0]);
            cloneMethod.setAccessible(true);
            container.startAnimation((Animation) cloneMethod.invoke(animation, new Object[0]));
        } catch (Throwable th) {
            container.startAnimation(animation);
        }
    }
}
