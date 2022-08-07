package com.vsf2f.f2f.ui.needs.star;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.vsf2f.f2f.R;
import java.math.BigDecimal;

/* loaded from: classes2.dex */
public class StarLinearLayout extends LinearLayout {
    private boolean mClickable;
    private OnRatingChangeListener onRatingChangeListener;
    private int starCount;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private Drawable starHalfDrawable;
    private float starImageSize;
    private float starPadding;
    private double starStep;
    private StepSize stepSize;

    /* loaded from: classes2.dex */
    public interface OnRatingChangeListener {
        void onRatingChange(double d);
    }

    public void setStarHalfDrawable(Drawable starHalfDrawable) {
        this.starHalfDrawable = starHalfDrawable;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    @Override // android.view.View
    public void setClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }

    public void setStepSize(StepSize stepSize) {
        this.stepSize = stepSize;
    }

    public StarLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(0);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        this.starImageSize = mTypedArray.getDimension(0, 20.0f);
        this.starPadding = mTypedArray.getDimension(1, 10.0f);
        this.starStep = mTypedArray.getFloat(7, 1.0f);
        this.stepSize = StepSize.fromStep(mTypedArray.getInt(8, 1));
        this.starCount = mTypedArray.getInteger(2, 5);
        this.starEmptyDrawable = mTypedArray.getDrawable(3);
        this.starFillDrawable = mTypedArray.getDrawable(4);
        this.starHalfDrawable = mTypedArray.getDrawable(5);
        this.mClickable = mTypedArray.getBoolean(6, true);
        mTypedArray.recycle();
        for (int i = 0; i < this.starCount; i++) {
            addView(createStar(i));
        }
        setStar(this.starStep);
    }

    private ImageView createStar(final int pos) {
        final ImageView imageView = getStarImageView();
        imageView.setImageDrawable(this.starEmptyDrawable);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.needs.star.StarLinearLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (StarLinearLayout.this.mClickable) {
                    int fint = (int) StarLinearLayout.this.starStep;
                    if (new BigDecimal(Double.toString(StarLinearLayout.this.starStep)).subtract(new BigDecimal(Integer.toString(fint))).floatValue() == 0.0f) {
                        fint--;
                    }
                    if (pos > fint) {
                        StarLinearLayout.this.setStar(pos + 1);
                    } else if (pos != fint) {
                        StarLinearLayout.this.setStar(StarLinearLayout.this.indexOfChild(v) + 1.0f);
                    } else if (StarLinearLayout.this.stepSize == StepSize.Full) {
                    } else {
                        if (imageView.getDrawable().getCurrent().getConstantState().equals(StarLinearLayout.this.starHalfDrawable.getConstantState())) {
                            StarLinearLayout.this.setStar(StarLinearLayout.this.indexOfChild(v) + 1);
                        } else {
                            StarLinearLayout.this.setStar(StarLinearLayout.this.indexOfChild(v) + 0.5f);
                        }
                    }
                }
            }
        });
        return imageView;
    }

    private ImageView getStarImageView() {
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(Math.round(this.starImageSize), Math.round(this.starImageSize));
        layout.setMargins(0, 0, Math.round(this.starPadding), 0);
        imageView.setLayoutParams(layout);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(this.starEmptyDrawable);
        imageView.setMinimumWidth(10);
        imageView.setMaxHeight(10);
        return imageView;
    }

    public void setStar(double rating) {
        if (this.onRatingChangeListener != null) {
            this.onRatingChangeListener.onRatingChange(rating);
        }
        this.starStep = rating;
        int fint = (int) rating;
        float fPoint = new BigDecimal(Double.toString(rating)).subtract(new BigDecimal(Integer.toString(fint))).floatValue();
        for (int i = 0; i < fint; i++) {
            ((ImageView) getChildAt(i)).setImageDrawable(this.starFillDrawable);
        }
        for (int i2 = fint; i2 < this.starCount; i2++) {
            ((ImageView) getChildAt(i2)).setImageDrawable(this.starEmptyDrawable);
        }
        if (fPoint > 0.0f) {
            ((ImageView) getChildAt(fint)).setImageDrawable(this.starHalfDrawable);
        }
    }

    /* loaded from: classes2.dex */
    public enum StepSize {
        Half(0),
        Full(1);
        
        int step;

        StepSize(int step) {
            this.step = step;
        }

        public static StepSize fromStep(int step) {
            StepSize[] values = values();
            for (StepSize f : values) {
                if (f.step == step) {
                    return f;
                }
            }
            throw new IllegalArgumentException();
        }
    }
}
