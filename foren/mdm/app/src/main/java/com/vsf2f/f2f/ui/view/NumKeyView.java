package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import com.vsf2f.f2f.R;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class NumKeyView extends KeyboardView implements KeyboardView.OnKeyboardActionListener {
    private int KEYCODE_EMPTY = -10;
    private int mBgColor;
    private Drawable mDeleteDrawable;
    private OnKeyPressListener mOnkeyPressListener;
    private int mTvColor;

    /* loaded from: classes2.dex */
    public interface OnKeyPressListener {
        void onDeleteKey();

        void onInertKey(String str);
    }

    public NumKeyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public NumKeyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumKeyView);
        this.mTvColor = ta.getColor(0, -12303292);
        this.mBgColor = ta.getColor(1, -1);
        this.mDeleteDrawable = ta.getDrawable(2);
        ta.recycle();
        setKeyboard(new Keyboard(context, R.xml.num_keyboard));
        setEnabled(true);
        setPreviewEnabled(false);
        setOnKeyboardActionListener(this);
    }

    private void drawKeyBackground(Drawable drawable, Canvas canvas, Keyboard.Key key) {
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            drawable.setState(drawableState);
        }
        drawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        drawable.draw(canvas);
    }

    private void drawKeyBackGround(Canvas canvas, Keyboard.Key key) {
        ColorDrawable colordrawable = new ColorDrawable(this.mBgColor);
        colordrawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        colordrawable.draw(canvas);
    }

    private void drawkeyDelete(Canvas canvas, Keyboard.Key key) {
        this.mDeleteDrawable.getIntrinsicWidth();
        this.mDeleteDrawable.getIntrinsicHeight();
        int drawWidth = key.width / 4;
        int widthInterval = (key.width - drawWidth) / 2;
        int heightInterval = (key.height - drawWidth) / 2;
        this.mDeleteDrawable.setBounds(key.x + widthInterval, key.y + heightInterval, key.x + widthInterval + drawWidth, key.y + heightInterval + drawWidth);
        this.mDeleteDrawable.draw(canvas);
    }

    private void drawText(Canvas canvas, Keyboard.Key key) {
        Rect bounds = new Rect();
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setTextSize(40.0f);
        paint.setColor(this.mTvColor);
        if (key.label != null) {
            if (key.label.toString().length() <= 1 || key.codes.length >= 2) {
                int keyTextSize = 0;
                try {
                    Field field = KeyboardView.class.getDeclaredField("mLabelTextSize");
                    field.setAccessible(true);
                    keyTextSize = ((Integer) field.get(this)).intValue();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e2) {
                    e2.printStackTrace();
                }
                paint.setTextSize(keyTextSize);
                paint.setTypeface(Typeface.DEFAULT);
            } else {
                int labelTextSize = 0;
                try {
                    Field field2 = KeyboardView.class.getDeclaredField("mLabelTextSize");
                    field2.setAccessible(true);
                    labelTextSize = ((Integer) field2.get(this)).intValue();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                } catch (NoSuchFieldException e4) {
                    e4.printStackTrace();
                }
                paint.setTextSize(labelTextSize);
                paint.setTypeface(Typeface.DEFAULT_BOLD);
            }
            paint.getTextBounds(key.label.toString(), 0, key.label.toString().length(), bounds);
            canvas.drawText(key.label.toString(), key.x + (key.width / 2), key.y + (key.height / 2) + (bounds.height() / 2), paint);
        } else if (key.icon != null) {
            key.icon.setBounds(key.x + ((key.width - key.icon.getIntrinsicWidth()) / 2), key.y + ((key.height - key.icon.getIntrinsicHeight()) / 2), key.x + ((key.width - key.icon.getIntrinsicWidth()) / 2) + key.icon.getIntrinsicWidth(), key.y + ((key.height - key.icon.getIntrinsicHeight()) / 2) + key.icon.getIntrinsicHeight());
            key.icon.draw(canvas);
        }
    }

    public void setOnKeyPressListener(OnKeyPressListener li) {
        this.mOnkeyPressListener = li;
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onKey(int i, int[] ints) {
        if (i == -5 && this.mOnkeyPressListener != null) {
            this.mOnkeyPressListener.onDeleteKey();
        } else if (i != this.KEYCODE_EMPTY) {
            this.mOnkeyPressListener.onInertKey(Character.toString((char) i));
        }
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onPress(int i) {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onRelease(int i) {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onText(CharSequence charSequence) {
    }

    @Override // android.inputmethodservice.KeyboardView, android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeRight() {
        super.swipeRight();
    }

    @Override // android.inputmethodservice.KeyboardView, android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeDown() {
        super.swipeDown();
    }

    @Override // android.inputmethodservice.KeyboardView, android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeLeft() {
        super.swipeLeft();
    }

    @Override // android.inputmethodservice.KeyboardView, android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeUp() {
        super.swipeUp();
    }
}
