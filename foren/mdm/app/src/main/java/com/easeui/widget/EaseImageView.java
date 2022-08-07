package com.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseImageView extends ImageView {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;
    private int borderColor;
    private int borderWidth;
    private int height;
    private int pressAlpha;
    private int pressColor;
    private Paint pressPaint;
    private int radius;
    private int shapeType;
    private int width;

    public EaseImageView(Context context) {
        super(context);
        init(context, null);
    }

    public EaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EaseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.borderWidth = 0;
        this.borderColor = -570425345;
        this.pressAlpha = 66;
        this.pressColor = 1107296256;
        this.radius = 16;
        this.shapeType = 0;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.EaseImageView);
            this.borderColor = array.getColor(0, this.borderColor);
            this.borderWidth = array.getDimensionPixelOffset(1, this.borderWidth);
            this.pressAlpha = array.getInteger(2, this.pressAlpha);
            this.pressColor = array.getColor(3, this.pressColor);
            this.radius = array.getDimensionPixelOffset(4, this.radius);
            this.shapeType = array.getInteger(5, this.shapeType);
            array.recycle();
        }
        this.pressPaint = new Paint();
        this.pressPaint.setAntiAlias(true);
        this.pressPaint.setStyle(Paint.Style.FILL);
        this.pressPaint.setColor(this.pressColor);
        this.pressPaint.setAlpha(0);
        this.pressPaint.setFlags(1);
        setClickable(true);
        setDrawingCacheEnabled(true);
        setWillNotDraw(false);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.shapeType == 0) {
            super.onDraw(canvas);
            return;
        }
        Drawable drawable = getDrawable();
        if (drawable != null && getWidth() != 0 && getHeight() != 0) {
            drawDrawable(canvas, getBitmapFromDrawable(drawable));
            drawPress(canvas);
            drawBorder(canvas);
        }
    }

    private void drawDrawable(Canvas canvas, Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setAntiAlias(true);
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        canvas.saveLayer(0.0f, 0.0f, this.width, this.height, null, 31);
        if (this.shapeType == 1) {
            canvas.drawCircle(this.width / 2, this.height / 2, (this.width / 2) - 1, paint);
        } else if (this.shapeType == 2) {
            canvas.drawRoundRect(new RectF(1.0f, 1.0f, getWidth() - 1, getHeight() - 1), this.radius + 1, this.radius + 1, paint);
        }
        paint.setXfermode(xfermode);
        Matrix matrix = new Matrix();
        matrix.postScale(getWidth() / bitmap.getWidth(), getHeight() / bitmap.getHeight());
        canvas.drawBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true), 0.0f, 0.0f, paint);
        canvas.restore();
    }

    private void drawPress(Canvas canvas) {
        if (this.shapeType == 1) {
            canvas.drawCircle(this.width / 2, this.height / 2, (this.width / 2) - 1, this.pressPaint);
        } else if (this.shapeType == 2) {
            canvas.drawRoundRect(new RectF(1.0f, 1.0f, this.width - 1, this.height - 1), this.radius + 1, this.radius + 1, this.pressPaint);
        }
    }

    private void drawBorder(Canvas canvas) {
        if (this.borderWidth > 0) {
            Paint paint = new Paint();
            paint.setStrokeWidth(this.borderWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(this.borderColor);
            paint.setAntiAlias(true);
            if (this.shapeType == 1) {
                canvas.drawCircle(this.width / 2, this.height / 2, (this.width - this.borderWidth) / 2, paint);
            } else if (this.shapeType == 2) {
                canvas.drawRoundRect(new RectF(this.borderWidth / 2, this.borderWidth / 2, getWidth() - (this.borderWidth / 2), getHeight() - (this.borderWidth / 2)), this.radius, this.radius, paint);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.pressPaint.setAlpha(this.pressAlpha);
                invalidate();
                break;
            case 1:
                this.pressPaint.setAlpha(0);
                invalidate();
                break;
            case 2:
                break;
            default:
                this.pressPaint.setAlpha(0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), BITMAP_CONFIG);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        invalidate();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setPressAlpha(int pressAlpha) {
        this.pressAlpha = pressAlpha;
    }

    public void setPressColor(int pressColor) {
        this.pressColor = pressColor;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
        invalidate();
    }
}
