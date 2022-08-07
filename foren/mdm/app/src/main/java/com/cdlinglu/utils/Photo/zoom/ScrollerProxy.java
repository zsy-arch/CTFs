package com.cdlinglu.utils.Photo.zoom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.widget.OverScroller;
import android.widget.Scroller;

/* loaded from: classes.dex */
public abstract class ScrollerProxy {
    public abstract boolean computeScrollOffset();

    public abstract void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public abstract void forceFinished(boolean z);

    public abstract int getCurrX();

    public abstract int getCurrY();

    public static ScrollerProxy getScroller(Context context) {
        return Build.VERSION.SDK_INT < 9 ? new PreGingerScroller(context) : new GingerScroller(context);
    }

    @TargetApi(9)
    /* loaded from: classes.dex */
    private static class GingerScroller extends ScrollerProxy {
        private OverScroller mScroller;

        public GingerScroller(Context context) {
            this.mScroller = new OverScroller(context);
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public boolean computeScrollOffset() {
            return this.mScroller.computeScrollOffset();
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY) {
            this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY, overX, overY);
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public void forceFinished(boolean finished) {
            this.mScroller.forceFinished(finished);
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public int getCurrX() {
            return this.mScroller.getCurrX();
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public int getCurrY() {
            return this.mScroller.getCurrY();
        }
    }

    /* loaded from: classes.dex */
    private static class PreGingerScroller extends ScrollerProxy {
        private Scroller mScroller;

        public PreGingerScroller(Context context) {
            this.mScroller = new Scroller(context);
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public boolean computeScrollOffset() {
            return this.mScroller.computeScrollOffset();
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY) {
            this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY);
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public void forceFinished(boolean finished) {
            this.mScroller.forceFinished(finished);
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public int getCurrX() {
            return this.mScroller.getCurrX();
        }

        @Override // com.cdlinglu.utils.Photo.zoom.ScrollerProxy
        public int getCurrY() {
            return this.mScroller.getCurrY();
        }
    }
}
