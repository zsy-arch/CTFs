package com.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseSwitchButton extends FrameLayout {
    private ImageView closeImage;
    private ImageView openImage;

    public EaseSwitchButton(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseSwitchButton(Context context) {
        this(context, null);
    }

    public EaseSwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseSwitchButton);
        Drawable openDrawable = ta.getDrawable(0);
        Drawable closeDrawable = ta.getDrawable(1);
        int switchStatus = ta.getInt(2, 0);
        ta.recycle();
        LayoutInflater.from(context).inflate(R.layout.ease_widget_switch_button, this);
        this.openImage = (ImageView) findViewById(R.id.iv_switch_open);
        this.closeImage = (ImageView) findViewById(R.id.iv_switch_close);
        if (openDrawable != null) {
            this.openImage.setImageDrawable(openDrawable);
        }
        if (closeDrawable != null) {
            this.closeImage.setImageDrawable(closeDrawable);
        }
        if (switchStatus == 0) {
            openSwitch();
        } else {
            closeSwitch();
        }
    }

    public boolean isSwitchOpen() {
        return this.openImage.getVisibility() == 0;
    }

    public void openSwitch() {
        this.openImage.setVisibility(0);
        this.closeImage.setVisibility(4);
    }

    public void closeSwitch() {
        this.openImage.setVisibility(4);
        this.closeImage.setVisibility(0);
    }

    public boolean change() {
        if (isSwitchOpen()) {
            closeSwitch();
            return false;
        }
        openSwitch();
        return true;
    }
}
