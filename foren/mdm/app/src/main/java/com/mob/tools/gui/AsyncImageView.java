package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mob.tools.MobLog;
import com.mob.tools.gui.BitmapProcessor;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.UIHandler;
import java.util.Random;

/* loaded from: classes2.dex */
public class AsyncImageView extends ImageView implements BitmapProcessor.BitmapCallback, Handler.Callback {
    private static final int MSG_IMG_GOT = 1;
    private static final Random rnd = new Random();
    private Bitmap defaultBm;
    private int defaultRes;
    private Path path;
    private float[] rect;
    private boolean scaleToCrop;
    private String url;

    public AsyncImageView(Context context) {
        super(context);
        init(context);
    }

    public AsyncImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AsyncImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private int[] getSize() {
        ViewGroup.LayoutParams lp;
        int width = getWidth();
        int height = getHeight();
        if ((width == 0 || height == 0) && (lp = getLayoutParams()) != null) {
            width = lp.width;
            height = lp.height;
        }
        if (width == 0 || height == 0) {
            measure(0, 0);
            width = getMeasuredWidth();
            height = getMeasuredHeight();
        }
        return new int[]{width, height};
    }

    private Bitmap goCrop(Bitmap bm) {
        float width = bm.getWidth();
        float height = bm.getHeight();
        if (width == 0.0f || height == 0.0f) {
            return bm;
        }
        int[] size = getSize();
        if (size[0] == 0 || size[1] == 0) {
            return bm;
        }
        float respHeight = (size[1] * width) / size[0];
        if (respHeight == height) {
            return bm;
        }
        int[] rect = new int[4];
        if (respHeight < height) {
            rect[1] = (int) ((height - respHeight) / 2.0f);
            rect[3] = rect[1];
        } else {
            rect[0] = (int) ((width - ((size[0] * height) / size[1])) / 2.0f);
            rect[2] = rect[0];
        }
        try {
            bm = BitmapHelper.cropBitmap(bm, rect[0], rect[1], rect[2], rect[3]);
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
        }
        return bm;
    }

    private void init(Context context) {
        if (isInEditMode()) {
            setBackgroundColor(-16777216);
        } else {
            BitmapProcessor.prepare(context);
        }
    }

    public void execute(String url, int defaultRes) {
        this.url = url;
        this.defaultRes = defaultRes;
        if (TextUtils.isEmpty(url)) {
            setImageResource(defaultRes);
            return;
        }
        Bitmap bm = BitmapProcessor.getBitmapFromCache(url);
        if (bm == null || bm.isRecycled()) {
            if (defaultRes > 0) {
                setImageResource(defaultRes);
            }
            BitmapProcessor.process(url, this);
            return;
        }
        setBitmap(bm);
    }

    public void execute(String url, Bitmap defaultBm) {
        this.url = url;
        this.defaultBm = defaultBm;
        if (TextUtils.isEmpty(url)) {
            setImageBitmap(defaultBm);
            return;
        }
        Bitmap bm = BitmapProcessor.getBitmapFromCache(url);
        if (bm == null || bm.isRecycled()) {
            if (defaultBm != null && !defaultBm.isRecycled()) {
                setImageBitmap(defaultBm);
            }
            BitmapProcessor.process(url, this);
            return;
        }
        setBitmap(bm);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        if (msg.what == 1) {
            Object url = ((Object[]) msg.obj)[0];
            Object bm = ((Object[]) msg.obj)[1];
            if (bm != null && url != null && url.equals(this.url)) {
                setImageBitmap((Bitmap) bm);
            } else if (this.defaultBm == null || this.defaultBm.isRecycled()) {
                setImageResource(this.defaultRes);
            } else {
                setImageBitmap(this.defaultBm);
            }
        }
        return false;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.rect != null) {
            if (this.path == null) {
                int width = getWidth();
                int height = getHeight();
                this.path = new Path();
                this.path.addRoundRect(new RectF(0.0f, 0.0f, width, height), this.rect, Path.Direction.CW);
            }
            canvas.clipPath(this.path);
        }
        super.onDraw(canvas);
    }

    @Override // com.mob.tools.gui.BitmapProcessor.BitmapCallback
    public void onImageGot(String url, Bitmap bm) {
        Bitmap shownImage = null;
        if (url != null && url.trim().length() > 0 && url.equals(this.url)) {
            shownImage = bm;
        }
        if (shownImage != null && this.scaleToCrop) {
            shownImage = goCrop(shownImage);
        }
        Message msg = new Message();
        msg.what = 1;
        msg.obj = new Object[]{url, shownImage};
        UIHandler.sendMessageDelayed(msg, rnd.nextInt(300), this);
    }

    public void setBitmap(Bitmap bm) {
        if (this.scaleToCrop) {
            bm = goCrop(bm);
        }
        setImageBitmap(bm);
    }

    public void setRound(float radius) {
        setRound(radius, radius, radius, radius);
    }

    public void setRound(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        this.rect = new float[]{leftTop, leftTop, rightTop, rightTop, rightBottom, rightBottom, leftBottom, leftBottom};
    }

    public void setScaleToCropCenter(boolean scaleToCrop) {
        this.scaleToCrop = scaleToCrop;
    }
}
