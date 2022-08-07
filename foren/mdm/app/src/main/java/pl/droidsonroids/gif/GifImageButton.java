package pl.droidsonroids.gif;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageButton;
import pl.droidsonroids.gif.GifViewUtils;

/* loaded from: classes2.dex */
public class GifImageButton extends ImageButton {
    private boolean mFreezesAnimation;

    public GifImageButton(Context context) {
        super(context);
    }

    public GifImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInit(GifViewUtils.initImageView(this, attrs, 0, 0));
    }

    public GifImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        postInit(GifViewUtils.initImageView(this, attrs, defStyle, 0));
    }

    @TargetApi(21)
    public GifImageButton(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
        postInit(GifViewUtils.initImageView(this, attrs, defStyle, defStyleRes));
    }

    private void postInit(GifViewUtils.InitResult result) {
        this.mFreezesAnimation = result.mFreezesAnimation;
        if (result.mSourceResId > 0) {
            super.setImageResource(result.mSourceResId);
        }
        if (result.mBackgroundResId > 0) {
            super.setBackgroundResource(result.mBackgroundResId);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        if (!GifViewUtils.setGifImageUri(this, uri)) {
            super.setImageURI(uri);
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int resId) {
        if (!GifViewUtils.setResource(this, true, resId)) {
            super.setImageResource(resId);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int resId) {
        if (!GifViewUtils.setResource(this, false, resId)) {
            super.setBackgroundResource(resId);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        return new GifViewSavedState(super.onSaveInstanceState(), this.mFreezesAnimation ? getDrawable() : null, this.mFreezesAnimation ? getBackground() : null);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        GifViewSavedState ss = (GifViewSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        ss.restoreState(getDrawable(), 0);
        ss.restoreState(getBackground(), 1);
    }

    public void setFreezesAnimation(boolean freezesAnimation) {
        this.mFreezesAnimation = freezesAnimation;
    }
}
