package com.vsf2f.f2f.ui.otay;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class BofangView extends View {
    int COMPONENT_HEIGHT;
    int COMPONENT_WIDTH;
    boolean initflag = false;
    int currPicIndex = 0;
    private boolean workFlag = true;
    private int currIndex = 0;
    private Paint paint = new Paint();
    private int[] bitmapId = {R.drawable.icon_otay1, R.drawable.icon_otay3, R.drawable.icon_otay2};
    Bitmap[] bmp = new Bitmap[this.bitmapId.length];

    public BofangView(Context father, AttributeSet as) {
        super(father, as);
        this.paint.setStrokeWidth(1.0f);
        this.paint.setColor(ContextCompat.getColor(father, R.color.red));
        initBitmap();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        BitmapFactory.decodeResource(getResources(), R.drawable.icon_otay1);
        setMeasuredDimension(HyUtil.dip2px(getContext(), 211.0f), HyUtil.dip2px(getContext(), 211.0f));
    }

    public void initBitmap() {
        Resources res = getResources();
        for (int i = 0; i < this.bitmapId.length; i++) {
            this.bmp[i] = BitmapFactory.decodeResource(res, this.bitmapId[i]);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.bmp[this.currIndex];
        int cwidth = bitmap.getWidth();
        int cheight = bitmap.getHeight();
        canvas.drawBitmap(bitmap, (HyUtil.dip2px(getContext(), 211.0f) / 2) - (cwidth / 2), (HyUtil.dip2px(getContext(), 211.0f) / 2) - (cheight / 2), this.paint);
    }

    public void startAnation() {
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new runner(), 0L, 1L, TimeUnit.SECONDS);
    }

    /* loaded from: classes2.dex */
    public class runner implements Runnable {
        public runner() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BofangView.this.currIndex = (BofangView.this.currIndex + 1) % BofangView.this.bitmapId.length;
            BofangView.this.postInvalidate();
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (!this.initflag) {
            this.COMPONENT_WIDTH = getWidth();
            this.COMPONENT_HEIGHT = getHeight();
            this.initflag = true;
        }
    }
}
