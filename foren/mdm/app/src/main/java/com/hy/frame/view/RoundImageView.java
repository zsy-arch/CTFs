package com.hy.frame.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.hy.frame.R;

/* loaded from: classes2.dex */
public class RoundImageView extends ImageView {
    private final Paint maskPaint;
    private float radius;
    private final RectF roundRect;
    private final Paint zonePaint;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.radius = 15.0f;
        this.roundRect = new RectF();
        this.maskPaint = new Paint();
        this.zonePaint = new Paint();
        init(context, attrs);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        canvas.saveLayer(this.roundRect, this.zonePaint, 31);
        canvas.drawRoundRect(this.roundRect, this.radius, this.radius, this.zonePaint);
        canvas.saveLayer(this.roundRect, this.maskPaint, 31);
        super.draw(canvas);
        canvas.restore();
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundImageView, 0, 0);
        this.radius = a.getDimensionPixelSize(R.styleable.RoundImageView_rivRadius, 0);
        if (this.radius == 0.0f) {
            this.radius = getResources().getDimensionPixelSize(R.dimen.btn_radius);
        }
        a.recycle();
        this.maskPaint.setAntiAlias(true);
        this.maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.zonePaint.setAntiAlias(true);
        this.zonePaint.setColor(-1);
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.roundRect.set(0.0f, 0.0f, getWidth(), getHeight());
    }

    public void setRectAdius(float radius) {
        this.radius = radius;
        invalidate();
    }
}
