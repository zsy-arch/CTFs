package com.autonavi.amap.mapcore;

import android.graphics.Rect;

/* loaded from: classes.dex */
public class Rectangle {
    public float bottom;
    public FPoint[] clipMapRect;
    public IPoint[] clipRect;
    public float left;
    public Rect rect;
    public float right;
    public float top;

    public Rectangle() {
        this.rect = new Rect();
        this.clipRect = null;
        this.clipMapRect = null;
    }

    public Rectangle(Rect rect, int i, int i2) {
        this.rect = new Rect();
        this.clipRect = null;
        this.clipMapRect = null;
        this.rect = rect;
        if (rect != null) {
            updateRect(rect, i, i2);
            updateClipRect();
            updateClipMapRect(rect.centerX(), rect.centerY());
        }
    }

    public void updateRect(Rect rect, int i, int i2) {
        if (rect != null) {
            this.rect = rect;
            this.rect.inset((-rect.width()) / 8, (-rect.height()) / 8);
            updateClipRect();
            updateClipMapRect(i, i2);
        }
    }

    private void updateClipRect() {
        if (this.clipRect == null) {
            this.clipRect = new IPoint[4];
            this.clipRect[0] = IPoint.obtain(0, 0);
            this.clipRect[1] = IPoint.obtain(0, 0);
            this.clipRect[2] = IPoint.obtain(0, 0);
            this.clipRect[3] = IPoint.obtain(0, 0);
        }
        if (this.rect != null) {
            this.clipRect[0].x = this.rect.left;
            this.clipRect[0].y = this.rect.top;
            this.clipRect[1].x = this.rect.right;
            this.clipRect[1].y = this.rect.top;
            this.clipRect[2].x = this.rect.right;
            this.clipRect[2].y = this.rect.bottom;
            this.clipRect[3].x = this.rect.left;
            this.clipRect[3].y = this.rect.bottom;
        }
    }

    private void updateClipMapRect(int i, int i2) {
        if (this.clipMapRect == null) {
            this.clipMapRect = new FPoint[4];
            this.clipMapRect[0] = FPoint.obtain(0.0f, 0.0f);
            this.clipMapRect[1] = FPoint.obtain(0.0f, 0.0f);
            this.clipMapRect[2] = FPoint.obtain(0.0f, 0.0f);
            this.clipMapRect[3] = FPoint.obtain(0.0f, 0.0f);
        }
        if (this.rect != null) {
            this.clipMapRect[0].x = this.rect.left - i;
            this.clipMapRect[0].y = this.rect.top - i2;
            this.clipMapRect[1].x = this.rect.right - i;
            this.clipMapRect[1].y = this.rect.top - i2;
            this.clipMapRect[2].x = this.rect.right - i;
            this.clipMapRect[2].y = this.rect.bottom - i2;
            this.clipMapRect[3].x = this.rect.left - i;
            this.clipMapRect[3].y = this.rect.bottom - i2;
        }
    }

    public Rect getRect() {
        return this.rect;
    }

    public IPoint[] getClipRect() {
        return this.clipRect;
    }

    public FPoint[] getClipMapRect() {
        return this.clipMapRect;
    }

    public boolean contains(int i, int i2) {
        if (this.rect != null) {
            return this.rect.contains(i, i2);
        }
        return false;
    }

    public boolean contains(IPoint iPoint) {
        if (iPoint == null) {
            return false;
        }
        return contains(iPoint.x, iPoint.y);
    }

    public boolean contains(Rect rect) {
        if (rect == null) {
            return false;
        }
        return this.rect.contains(rect);
    }

    public boolean isOverlap(Rect rect) {
        if (this.rect == null || rect == null || this.rect.left + this.rect.width() <= rect.left || rect.left + rect.width() <= this.rect.left || this.rect.top + this.rect.height() <= rect.top || rect.top + rect.height() <= this.rect.top) {
            return false;
        }
        return true;
    }

    public Rectangle(float f, float f2, float f3, float f4) {
        this.rect = new Rect();
        this.clipRect = null;
        this.clipMapRect = null;
        if (f < f2 && f4 > f3) {
            this.left = f;
            this.right = f2;
            this.top = f4;
            this.bottom = f3;
        }
    }
}
