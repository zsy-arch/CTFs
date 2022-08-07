package com.hy.frame.view.recycler.xRefreshView;

/* loaded from: classes2.dex */
public class XRefreshHolder {
    public int mOffsetY;

    public void move(int deltaY) {
        this.mOffsetY += deltaY;
    }

    public boolean hasHeaderPullDown() {
        return this.mOffsetY > 0;
    }

    public boolean hasFooterPullUp() {
        return this.mOffsetY < 0;
    }

    public boolean isOverHeader(int deltaY) {
        return this.mOffsetY < (-deltaY);
    }
}
