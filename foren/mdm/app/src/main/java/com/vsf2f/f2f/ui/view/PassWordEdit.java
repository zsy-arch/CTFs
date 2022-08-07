package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class PassWordEdit extends AppCompatEditText {
    private int mHeight;
    private Paint mPaint;
    private int mPassWordLength;
    private int mWidth;

    public PassWordEdit(Context context) {
        this(context, null);
    }

    public PassWordEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(getResources().getColor(R.color.golden));
        this.mPaint.setStrokeWidth(2.0f);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new RectF(this.mPaint.getStrokeWidth() + 0.0f, 0.0f, this.mWidth - this.mPaint.getStrokeWidth(), this.mHeight), this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        int width = (int) (this.mWidth / 6.0d);
        for (int i = 1; i <= 5; i++) {
            canvas.drawLine(width * i, 0.0f, width * i, this.mHeight, this.mPaint);
        }
        for (int j = 0; j < this.mPassWordLength; j++) {
            canvas.drawCircle((width / 2) + (width * j), this.mHeight / 2, 5.0f, this.mPaint);
        }
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (text.length() != this.mPassWordLength) {
            this.mPassWordLength = text.length();
        }
        invalidate();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        this.mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(this.mWidth, this.mHeight);
    }
}
