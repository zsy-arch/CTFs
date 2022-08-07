package android.support.graphics.drawable;

import android.animation.TypeEvaluator;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class ArgbEvaluator implements TypeEvaluator {
    private static final ArgbEvaluator sInstance = new ArgbEvaluator();

    public static ArgbEvaluator getInstance() {
        return sInstance;
    }

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = ((Integer) startValue).intValue();
        float startA = ((startInt >> 24) & 255) / 255.0f;
        int endInt = ((Integer) endValue).intValue();
        float startR = (float) Math.pow(((startInt >> 16) & 255) / 255.0f, 2.2d);
        float startG = (float) Math.pow(((startInt >> 8) & 255) / 255.0f, 2.2d);
        float startB = (float) Math.pow((startInt & 255) / 255.0f, 2.2d);
        float endR = (float) Math.pow(((endInt >> 16) & 255) / 255.0f, 2.2d);
        float endG = (float) Math.pow(((endInt >> 8) & 255) / 255.0f, 2.2d);
        return Integer.valueOf((Math.round((startA + (((((endInt >> 24) & 255) / 255.0f) - startA) * fraction)) * 255.0f) << 24) | (Math.round(((float) Math.pow(startR + ((endR - startR) * fraction), 0.45454545454545453d)) * 255.0f) << 16) | (Math.round(((float) Math.pow(startG + ((endG - startG) * fraction), 0.45454545454545453d)) * 255.0f) << 8) | Math.round(((float) Math.pow(startB + ((((float) Math.pow((endInt & 255) / 255.0f, 2.2d)) - startB) * fraction), 0.45454545454545453d)) * 255.0f));
    }
}
