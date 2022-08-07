package com.hy.frame.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.R;
import com.hy.frame.util.HyUtil;

/* loaded from: classes2.dex */
public class NavView extends FrameLayout implements Checkable {
    private TintImageView icoKey;
    private LinearLayout llyContainer;
    private boolean mChecked;
    private TextView txtKey;

    /* loaded from: classes2.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(NavView navView, boolean z);
    }

    public NavView(Context context) {
        this(context, null);
    }

    public NavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NavView, 0, 0);
        if (a != null) {
            Drawable draw = a.getDrawable(R.styleable.NavView_navDraw);
            Drawable drawLeft = a.getDrawable(R.styleable.NavView_navDrawLeft);
            Drawable drawRight = a.getDrawable(R.styleable.NavView_navDrawRight);
            CharSequence key = a.getText(R.styleable.NavView_navText);
            CharSequence hint = a.getText(R.styleable.NavView_navHint);
            ColorStateList textColor = a.getColorStateList(R.styleable.NavView_navTextColor);
            ColorStateList drawTint = a.getColorStateList(R.styleable.NavView_navDrawTint);
            float textSize = a.getDimension(R.styleable.NavView_navTextSize, 0.0f);
            boolean checked = a.getBoolean(R.styleable.NavView_navChecked, false);
            boolean horizontal = a.getBoolean(R.styleable.NavView_navHorizontal, false);
            boolean textRight = a.getBoolean(R.styleable.NavView_navTextRight, false);
            boolean center = a.getBoolean(R.styleable.NavView_navCenter, false);
            int padding = a.getDimensionPixelSize(R.styleable.NavView_navPadding, 0);
            int paddingL = a.getDimensionPixelSize(R.styleable.NavView_navPaddingL, padding);
            int paddingT = a.getDimensionPixelSize(R.styleable.NavView_navPaddingT, padding);
            int drawWidth = a.getDimensionPixelSize(R.styleable.NavView_navDrawWidth, 0);
            a.getDimensionPixelSize(R.styleable.NavView_navDrawHeight, 0);
            int drawPadding = a.getDimensionPixelSize(R.styleable.NavView_navDrawPadding, 0);
            int drawRightWidth = a.getDimensionPixelSize(R.styleable.NavView_navDrawRightWidth, 0);
            a.recycle();
            this.llyContainer = new LinearLayout(context);
            this.llyContainer.setOrientation(horizontal ? 0 : 1);
            this.llyContainer.setGravity(17);
            if (!horizontal || paddingL != 0) {
                setPadding(0, 0, 0, 0);
                this.llyContainer.setPadding(paddingL, paddingT, padding, padding);
            }
            this.icoKey = new TintImageView(context);
            if (draw != null) {
                this.icoKey.setImageDrawable(draw);
            }
            if (drawTint != null) {
                this.icoKey.setColorFilter(drawTint);
            }
            if (drawWidth > 0) {
                this.llyContainer.addView(this.icoKey, new LinearLayout.LayoutParams(drawWidth, drawWidth));
            } else {
                this.llyContainer.addView(this.icoKey, new LinearLayout.LayoutParams(-2, -2));
            }
            this.txtKey = new TextView(context);
            if (key != null) {
                this.txtKey.setText(key);
            }
            if (textColor != null) {
                this.txtKey.setTextColor(textColor);
            }
            if (textSize > 0.0f) {
                this.txtKey.setTextSize(HyUtil.floatToSpDimension(textSize, context));
            }
            if (horizontal && drawLeft != null) {
                ImageView imgLeft = new ImageView(getContext());
                FrameLayout.LayoutParams rllp = new FrameLayout.LayoutParams(-2, -2);
                if (drawRightWidth > 0) {
                    imgLeft.setScaleType(ImageView.ScaleType.FIT_XY);
                    rllp.width = drawRightWidth;
                    rllp.height = drawRightWidth;
                }
                imgLeft.setImageDrawable(drawLeft);
                rllp.gravity = 19;
                addView(imgLeft, rllp);
            }
            if (!horizontal || !textRight) {
                LinearLayout.LayoutParams txtLlp = new LinearLayout.LayoutParams(-2, -2);
                if (horizontal) {
                    txtLlp.leftMargin = drawPadding;
                } else {
                    txtLlp.topMargin = drawPadding;
                }
                this.txtKey.setGravity(17);
                this.llyContainer.addView(this.txtKey, txtLlp);
            } else {
                FrameLayout.LayoutParams txtFlp = new FrameLayout.LayoutParams(-2, -2);
                txtFlp.gravity = 21;
                addView(this.txtKey, txtFlp);
            }
            if (hint != null) {
                TextView txtHint = new TextView(context);
                txtHint.setText(hint);
                txtHint.setPadding(0, 0, 55, 0);
                txtHint.setTextSize(11.0f);
                txtHint.setTextColor(getResources().getColor(R.color.txt_gray_b));
                FrameLayout.LayoutParams txtFlp2 = new FrameLayout.LayoutParams(-2, -2);
                txtFlp2.gravity = 21;
                addView(txtHint, txtFlp2);
            }
            FrameLayout.LayoutParams clp = new FrameLayout.LayoutParams(horizontal ? -2 : -1, horizontal ? -1 : -1);
            if (center) {
                clp.width = -2;
                clp.height = -2;
                clp.gravity = 17;
            }
            addView(this.llyContainer, clp);
            if (horizontal && drawRight != null) {
                ImageView imgRight = new ImageView(getContext());
                FrameLayout.LayoutParams rllp2 = new FrameLayout.LayoutParams(-2, -2);
                if (drawRightWidth > 0) {
                    imgRight.setScaleType(ImageView.ScaleType.FIT_XY);
                    rllp2.width = drawRightWidth;
                    rllp2.height = drawRightWidth;
                }
                imgRight.setImageDrawable(drawRight);
                rllp2.gravity = 21;
                addView(imgRight, rllp2);
            }
            setChecked(checked);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean checked) {
        if (this.mChecked != checked) {
            this.mChecked = checked;
            setSelected(checked);
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.mChecked);
    }

    public void setText(CharSequence text) {
        this.txtKey.setText(text);
    }

    public void setDraw(int resId) {
        this.icoKey.setImageResource(resId);
    }

    public void setText(@StringRes int resId) {
        setText(getContext().getResources().getText(resId));
    }

    public void setImageResource(@DrawableRes int resId) {
        this.icoKey.setImageResource(resId);
    }

    public TintImageView getIcoKey() {
        return this.icoKey;
    }

    public TextView getTxtKey() {
        return this.txtKey;
    }

    public LinearLayout getLlyContainer() {
        return this.llyContainer;
    }
}
