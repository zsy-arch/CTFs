package com.hy.frame.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.R;

/* loaded from: classes2.dex */
public class KeyValueView extends LinearLayout {
    private ImageView imgLeft;
    private ImageView imgRight;
    private TextView txtKey;
    private TextView txtValue;

    public KeyValueView(Context context) {
        this(context, null);
    }

    public KeyValueView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    @TargetApi(11)
    public KeyValueView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public KeyValueView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.KeyValueView, defStyleAttr, defStyleRes);
        if (a != null) {
            Drawable drawRight = a.getDrawable(R.styleable.KeyValueView_kvDrawRight);
            Drawable drawLeft = a.getDrawable(R.styleable.KeyValueView_kvDrawLeft);
            CharSequence key = a.getText(R.styleable.KeyValueView_kvKey);
            ColorStateList keyColor = a.getColorStateList(R.styleable.KeyValueView_kvKeyColor);
            float keySize = a.getDimension(R.styleable.KeyValueView_kvKeySize, 0.0f);
            CharSequence keySign = a.getText(R.styleable.KeyValueView_kvKeySign);
            CharSequence value = a.getText(R.styleable.KeyValueView_kvValue);
            ColorStateList valueColor = a.getColorStateList(R.styleable.KeyValueView_kvValueColor);
            float valueSize = a.getDimension(R.styleable.KeyValueView_kvValueSize, 0.0f);
            CharSequence valueHint = a.getText(R.styleable.KeyValueView_kvValueHint);
            ColorStateList valueHintColor = a.getColorStateList(R.styleable.KeyValueView_kvValueHintColor);
            int valuePadding = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValuePadding, 0);
            int valuePaddingLeft = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValuePaddingLeft, 0);
            int valuePaddingRight = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValuePaddingRight, 0);
            int valuePaddingTop = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValuePaddingTop, 0);
            int valuePaddingBottom = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValuePaddingBottom, 0);
            int valueMargin = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValueMargin, 0);
            int valueMarginLeft = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValueMarginLeft, 0);
            int valueMarginRight = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValueMarginRight, 0);
            int valueMarginTop = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValueMarginTop, 0);
            int valueMarginBottom = a.getDimensionPixelSize(R.styleable.KeyValueView_kvValueMarginBottom, 0);
            int drawRightWidth = a.getDimensionPixelSize(R.styleable.KeyValueView_kvDrawRightWidth, 0);
            boolean valueRight = a.getBoolean(R.styleable.KeyValueView_kvValueRight, false);
            a.recycle();
            if (drawLeft != null) {
                this.imgLeft = new ImageView(context);
                this.imgLeft.setImageDrawable(drawLeft);
                this.imgLeft.setPadding(0, 0, 10, 0);
                addView(this.imgLeft, new LinearLayout.LayoutParams(-2, -2));
            }
            this.txtKey = new TextView(context);
            if (key != null) {
                this.txtKey.setText(key);
            }
            if (keyColor != null) {
                this.txtKey.setTextColor(keyColor);
            }
            if (keySize > 0.0f) {
                this.txtKey.setTextSize(floatToSpDimension(context, keySize));
            }
            if (keySign != null) {
                this.txtKey.append(keySign);
            }
            this.txtKey.setSingleLine(true);
            this.txtKey.setGravity(48);
            addView(this.txtKey, new LinearLayout.LayoutParams(-2, -2));
            if (valuePaddingLeft == 0) {
                valuePaddingLeft = valuePadding;
            }
            if (valuePaddingRight == 0) {
                valuePaddingRight = valuePadding;
            }
            if (valuePaddingTop == 0) {
                valuePaddingTop = valuePadding;
            }
            if (valuePaddingBottom == 0) {
                valuePaddingBottom = valuePadding;
            }
            if (valueMarginLeft == 0) {
                valueMarginLeft = valueMargin;
            }
            if (valueMarginRight == 0) {
                valueMarginRight = valueMargin;
            }
            if (valueMarginTop == 0) {
                valueMarginTop = valueMargin;
            }
            if (valueMarginBottom == 0) {
                valueMarginBottom = valueMargin;
            }
            LinearLayout.LayoutParams valueLp = new LinearLayout.LayoutParams(-1, -2);
            valueLp.setMargins(valueMarginLeft, valueMarginTop, valueMarginRight, valueMarginBottom);
            if (drawRight != null) {
                valueLp.width = 0;
                valueLp.weight = 1.0f;
            }
            this.txtValue = new TextView(context);
            if (value != null) {
                this.txtValue.setText(value);
            }
            if (valueColor != null) {
                this.txtValue.setTextColor(valueColor);
            }
            if (valueSize > 0.0f) {
                this.txtValue.setTextSize(floatToSpDimension(context, valueSize));
            }
            if (valueHint != null) {
                this.txtValue.setHint(valueHint);
            }
            if (valueHintColor != null) {
                this.txtValue.setHintTextColor(valueHintColor);
            }
            this.txtValue.setPadding(valuePaddingLeft, valuePaddingTop, valuePaddingRight, valuePaddingBottom);
            this.txtValue.setSingleLine(true);
            this.txtValue.setEllipsize(TextUtils.TruncateAt.END);
            if (valueRight) {
                this.txtValue.setGravity(21);
            } else {
                this.txtValue.setGravity(16);
            }
            addView(this.txtValue, valueLp);
            if (drawRight != null) {
                this.imgRight = new ImageView(context);
                this.imgRight.setImageDrawable(drawRight);
                if (drawRightWidth > 0) {
                    this.imgRight.setScaleType(ImageView.ScaleType.FIT_XY);
                    addView(this.imgRight, new LinearLayout.LayoutParams(drawRightWidth, drawRightWidth));
                    return;
                }
                addView(this.imgRight, new LinearLayout.LayoutParams(-2, -2));
            }
        }
    }

    private float floatToSpDimension(Context context, float value) {
        return value / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public void setValue(@StringRes int resId) {
        setValue(getResources().getString(resId));
    }

    public void setValue(CharSequence sequence) {
        if (this.txtValue != null) {
            this.txtValue.setText(sequence);
        }
    }

    public CharSequence getValue() {
        if (this.txtValue != null) {
            return this.txtValue.getText();
        }
        return null;
    }

    public ImageView getImgRight() {
        return this.imgRight;
    }

    public TextView getTxtKey() {
        return this.txtKey;
    }

    public TextView getTxtValue() {
        return this.txtValue;
    }
}
