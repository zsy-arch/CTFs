package com.hy.frame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes2.dex */
public class MyLetterView extends View {
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private String[] letters = {"@", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    int choose = -1;
    private Paint paint = new Paint();
    boolean showBkg = false;

    /* loaded from: classes2.dex */
    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String str, float f, float f2);

        void onTouchingLetterEnd();
    }

    public MyLetterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyLetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLetterView(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.showBkg) {
            canvas.drawColor(Color.parseColor("#40000000"));
        }
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / this.letters.length;
        for (int i = 0; i < this.letters.length; i++) {
            this.paint.setTextSize(18.0f);
            this.paint.setColor(-16777216);
            this.paint.setAntiAlias(true);
            if (i == this.choose) {
                this.paint.setColor(Color.parseColor("#3399ff"));
                this.paint.setFakeBoldText(true);
            }
            canvas.drawText(this.letters[i], (width / 2) - (this.paint.measureText(this.letters[i]) / 2.0f), (singleHeight * i) + singleHeight, this.paint);
            this.paint.reset();
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        float x = event.getX();
        int oldChoose = this.choose;
        OnTouchingLetterChangedListener listener = this.onTouchingLetterChangedListener;
        int c = (int) ((y / getHeight()) * this.letters.length);
        switch (action) {
            case 0:
                this.showBkg = true;
                if (oldChoose != c && listener != null && c > 0 && c < this.letters.length) {
                    listener.onTouchingLetterChanged(this.letters[c], y, x);
                    this.choose = c;
                    invalidate();
                    break;
                }
                break;
            case 1:
                this.showBkg = false;
                this.choose = -1;
                if (listener != null) {
                    listener.onTouchingLetterEnd();
                }
                invalidate();
                break;
            case 2:
                if (oldChoose != c && listener != null && c > 0 && c < this.letters.length) {
                    listener.onTouchingLetterChanged(this.letters[c], y, x);
                    this.choose = c;
                    invalidate();
                    break;
                }
                break;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
}
