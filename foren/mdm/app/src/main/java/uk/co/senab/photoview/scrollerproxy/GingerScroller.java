package uk.co.senab.photoview.scrollerproxy;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.OverScroller;

@TargetApi(9)
/* loaded from: classes2.dex */
public class GingerScroller extends ScrollerProxy {
    private boolean mFirstScroll = false;
    protected final OverScroller mScroller;

    public GingerScroller(Context context) {
        this.mScroller = new OverScroller(context);
    }

    @Override // uk.co.senab.photoview.scrollerproxy.ScrollerProxy
    public boolean computeScrollOffset() {
        if (this.mFirstScroll) {
            this.mScroller.computeScrollOffset();
            this.mFirstScroll = false;
        }
        return this.mScroller.computeScrollOffset();
    }

    @Override // uk.co.senab.photoview.scrollerproxy.ScrollerProxy
    public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY) {
        this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY, overX, overY);
    }

    @Override // uk.co.senab.photoview.scrollerproxy.ScrollerProxy
    public void forceFinished(boolean finished) {
        this.mScroller.forceFinished(finished);
    }

    @Override // uk.co.senab.photoview.scrollerproxy.ScrollerProxy
    public boolean isFinished() {
        return this.mScroller.isFinished();
    }

    @Override // uk.co.senab.photoview.scrollerproxy.ScrollerProxy
    public int getCurrX() {
        return this.mScroller.getCurrX();
    }

    @Override // uk.co.senab.photoview.scrollerproxy.ScrollerProxy
    public int getCurrY() {
        return this.mScroller.getCurrY();
    }
}
