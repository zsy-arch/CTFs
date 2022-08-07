package com.amap.api.navi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amap.api.col.fo;
import com.amap.api.navi.model.AMapTrafficStatus;
import java.util.List;

/* loaded from: classes.dex */
public class TrafficBarView extends ImageView {
    private RectF colorRectF;
    private Bitmap displayingBitmap;
    private int drawTmcBarBgX;
    private int drawTmcBarBgY;
    private int jamTrafficColor;
    private int left;
    private List<AMapTrafficStatus> mTmcSections;
    private Paint paint;
    private int progressBarHeight;
    private Bitmap rawBitmap;
    private int right;
    private int slowTrafficColor;
    private int smoothTrafficColor;
    private int tmcBarBgWidth;
    private Bitmap tmcBarBitmapLandscape;
    private Bitmap tmcBarBitmapPortrait;
    private int unknownTrafficColor;
    private int veryJamTrafficColor;
    private int tmcBarTopMargin = 30;
    private int totalDis = 0;
    private int tmcBarBgHeight = 0;

    public TrafficBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initResource();
    }

    public TrafficBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initResource();
    }

    public TrafficBarView(Context context) {
        super(context);
        initResource();
    }

    public Bitmap getDisplayingBitmap() {
        return this.displayingBitmap;
    }

    private void initResource() {
        this.rawBitmap = BitmapFactory.decodeResource(fo.a(), 1191313509);
        this.tmcBarBitmapPortrait = this.rawBitmap;
        this.left = (this.tmcBarBitmapPortrait.getWidth() * 20) / 100;
        this.right = (this.tmcBarBitmapPortrait.getWidth() * 80) / 100;
        this.progressBarHeight = (int) (this.tmcBarBitmapPortrait.getHeight() * 0.8d);
        this.tmcBarBgWidth = this.tmcBarBitmapPortrait.getWidth();
        this.tmcBarBgHeight = this.tmcBarBitmapPortrait.getHeight();
        this.paint = new Paint();
        if (Build.VERSION.SDK_INT >= 11) {
            this.tmcBarTopMargin = (Math.abs(this.progressBarHeight - this.tmcBarBitmapPortrait.getHeight()) / 4) - ((int) (this.progressBarHeight * 0.017d));
        } else {
            this.tmcBarTopMargin = (Math.abs(this.progressBarHeight - this.tmcBarBitmapPortrait.getHeight()) / 4) - 3;
        }
        setTmcBarHeightWhenLandscape(0.6666666666666666d);
        this.displayingBitmap = this.tmcBarBitmapPortrait;
        this.colorRectF = new RectF();
        this.unknownTrafficColor = Color.parseColor("#B3CCDD");
        this.smoothTrafficColor = Color.parseColor("#05C300");
        this.slowTrafficColor = Color.parseColor("#FFD615");
        this.jamTrafficColor = Color.argb(255, 255, 93, 91);
        this.veryJamTrafficColor = Color.argb(255, 179, 17, 15);
    }

    public void onConfigurationChanged(boolean z) {
        if (z) {
            this.displayingBitmap = this.tmcBarBitmapLandscape;
        } else {
            this.displayingBitmap = this.tmcBarBitmapPortrait;
        }
        setProgressBarSize(z);
    }

    private void setProgressBarSize(boolean z) {
        this.progressBarHeight = (int) (this.displayingBitmap.getHeight() * 0.8d);
        this.tmcBarBgWidth = this.displayingBitmap.getWidth();
        this.tmcBarBgHeight = this.displayingBitmap.getHeight();
        if (z) {
            this.tmcBarTopMargin = (Math.abs(this.progressBarHeight - this.displayingBitmap.getHeight()) / 4) - ((int) (this.progressBarHeight * 0.017d));
        } else if (Build.VERSION.SDK_INT >= 11) {
            this.tmcBarTopMargin = (Math.abs(this.progressBarHeight - this.displayingBitmap.getHeight()) / 4) - ((int) (this.progressBarHeight * 0.017d));
        } else {
            this.tmcBarTopMargin = (Math.abs(this.progressBarHeight - this.displayingBitmap.getHeight()) / 4) - 4;
        }
    }

    public void update(List<AMapTrafficStatus> list, int i) {
        this.mTmcSections = list;
        this.totalDis = i;
        if (produceFinalBitmap() != null) {
            setImageBitmap(produceFinalBitmap());
        }
    }

    public int getUnknownTrafficColor() {
        return this.unknownTrafficColor;
    }

    public void setUnknownTrafficColor(int i) {
        this.unknownTrafficColor = i;
    }

    public int getSmoothTrafficColor() {
        return this.smoothTrafficColor;
    }

    public void setSmoothTrafficColor(int i) {
        this.smoothTrafficColor = i;
    }

    public int getSlowTrafficColor() {
        return this.slowTrafficColor;
    }

    public void setSlowTrafficColor(int i) {
        this.slowTrafficColor = i;
    }

    public int getJamTrafficColor() {
        return this.jamTrafficColor;
    }

    public void setJamTrafficColor(int i) {
        this.jamTrafficColor = i;
    }

    public int getVeryJamTrafficColor() {
        return this.veryJamTrafficColor;
    }

    public void setVeryJamTrafficColor(int i) {
        this.veryJamTrafficColor = i;
    }

    Bitmap produceFinalBitmap() {
        if (this.mTmcSections == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.displayingBitmap.getWidth(), this.displayingBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        this.paint.setStyle(Paint.Style.FILL);
        float f = this.totalDis;
        for (int i = 0; i < this.mTmcSections.size(); i++) {
            AMapTrafficStatus aMapTrafficStatus = this.mTmcSections.get(i);
            if (aMapTrafficStatus != null) {
                switch (aMapTrafficStatus.getStatus()) {
                    case 0:
                        this.paint.setColor(this.unknownTrafficColor);
                        break;
                    case 1:
                        this.paint.setColor(this.smoothTrafficColor);
                        break;
                    case 2:
                        this.paint.setColor(this.slowTrafficColor);
                        break;
                    case 3:
                        this.paint.setColor(this.jamTrafficColor);
                        break;
                    case 4:
                        this.paint.setColor(this.veryJamTrafficColor);
                        break;
                    default:
                        this.paint.setColor(this.unknownTrafficColor);
                        break;
                }
                if (f - aMapTrafficStatus.getLength() > 0.0f) {
                    this.colorRectF.set(this.left, ((this.progressBarHeight * (f - aMapTrafficStatus.getLength())) / this.totalDis) + this.tmcBarTopMargin, this.right, ((this.progressBarHeight * f) / this.totalDis) + this.tmcBarTopMargin);
                } else {
                    this.colorRectF.set(this.left, this.tmcBarTopMargin, this.right, ((this.progressBarHeight * f) / this.totalDis) + this.tmcBarTopMargin);
                }
                if (i == this.mTmcSections.size() - 1) {
                    this.colorRectF.set(this.left, this.tmcBarTopMargin, this.right, ((this.progressBarHeight * f) / this.totalDis) + this.tmcBarTopMargin);
                }
                canvas.drawRect(this.colorRectF, this.paint);
                f -= aMapTrafficStatus.getLength();
            }
        }
        this.paint.setColor(SupportMenu.CATEGORY_MASK);
        canvas.drawBitmap(this.displayingBitmap, 0.0f, 0.0f, (Paint) null);
        return createBitmap;
    }

    public void setTmcBarPosition(int i, int i2, int i3, int i4, boolean z) {
        setTmcBarHeightWhenLandscape((0.6666666666666666d * i2) / i3);
        setTmcBarHeightWhenPortrait((1.0d * i2) / i3);
        int i5 = (i4 * i2) / i3;
        onConfigurationChanged(z);
        if (z) {
            this.drawTmcBarBgX = Math.abs(i - ((int) (this.tmcBarBgWidth * 1.3d)));
            this.drawTmcBarBgY = ((i2 - (this.tmcBarBgHeight / 2)) * 6) / 10;
            return;
        }
        this.drawTmcBarBgX = Math.abs(i - ((int) (this.tmcBarBgWidth * 1.3d)));
        this.drawTmcBarBgY = (int) ((i2 - (i5 * 1.5d)) - this.tmcBarBgHeight);
    }

    public void setTmcBarHeightWhenLandscape(double d) {
        if (d > 1.0d) {
            d = 1.0d;
        } else if (d < 0.1d) {
            d = 0.1d;
        }
        this.tmcBarBitmapLandscape = Bitmap.createScaledBitmap(this.rawBitmap, this.rawBitmap.getWidth(), (int) (this.rawBitmap.getHeight() * d), true);
    }

    public void setTmcBarHeightWhenPortrait(double d) {
        if (d > 1.0d) {
            d = 1.0d;
        } else if (d < 0.1d) {
            d = 0.1d;
        }
        this.tmcBarBitmapPortrait = Bitmap.createScaledBitmap(this.rawBitmap, this.rawBitmap.getWidth(), (int) (this.rawBitmap.getHeight() * d), true);
        this.displayingBitmap = this.tmcBarBitmapPortrait;
        setProgressBarSize(false);
    }

    public int getTmcBarBgPosX() {
        return this.drawTmcBarBgX;
    }

    public int getTmcBarBgPosY() {
        return this.drawTmcBarBgY;
    }

    public int getTmcBarBgWidth() {
        return this.tmcBarBgWidth;
    }

    public int getTmcBarBgHeight() {
        return this.tmcBarBgHeight;
    }

    public void recycleResource() {
        if (this.displayingBitmap != null) {
            this.displayingBitmap.recycle();
            this.displayingBitmap = null;
        }
        if (this.tmcBarBitmapPortrait != null) {
            this.tmcBarBitmapPortrait.recycle();
            this.tmcBarBitmapPortrait = null;
        }
        if (this.tmcBarBitmapLandscape != null) {
            this.tmcBarBitmapLandscape.recycle();
            this.tmcBarBitmapLandscape = null;
        }
    }
}
