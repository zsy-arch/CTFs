package com.vsf2f.f2f.ui.otay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class TramsImageView extends ImageView {
    private Paint paint = new Paint();

    public TramsImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.paint.setColor(ContextCompat.getColor(context, R.color.red));
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
