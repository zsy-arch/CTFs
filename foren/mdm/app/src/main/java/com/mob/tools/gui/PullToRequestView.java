package com.mob.tools.gui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/* loaded from: classes2.dex */
public class PullToRequestView extends RelativeLayout {
    private static final long MIN_REF_TIME = 1000;
    private PullToRequestAdatper adapter;
    private View bodyView;
    private float downY;
    private int footerHeight;
    private View footerView;
    private int headerHeight;
    private View headerView;
    private long pullTime;
    private boolean pullingDownLock;
    private boolean pullingUpLock;
    private int state;
    private Runnable stopAct;
    private int top;

    public PullToRequestView(Context context) {
        super(context);
        init();
    }

    public PullToRequestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRequestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private boolean canPullDown() {
        return !this.pullingDownLock && this.adapter.isPullDownReady();
    }

    private boolean canPullUp() {
        return !this.pullingUpLock && this.adapter.isPullUpReady();
    }

    private MotionEvent getCancelEvent(MotionEvent ev) {
        return MotionEvent.obtain(ev.getDownTime(), ev.getEventTime(), 3, ev.getX(), ev.getY(), ev.getMetaState());
    }

    private void init() {
        this.stopAct = new Runnable() { // from class: com.mob.tools.gui.PullToRequestView.1
            @Override // java.lang.Runnable
            public void run() {
                PullToRequestView.this.reversePulling();
            }
        };
    }

    private void performFresh() {
        this.pullTime = System.currentTimeMillis();
        this.state = 1;
        if (this.adapter != null) {
            this.adapter.onRefresh();
        }
    }

    private void performRequestNext() {
        this.pullTime = System.currentTimeMillis();
        this.state = -1;
        if (this.adapter != null) {
            this.adapter.onRequestNext();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reversePulling() {
        this.top = 0;
        scrollTo(0, 0);
        this.state = 0;
        if (this.adapter != null) {
            this.adapter.onReversed();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.downY = ev.getY();
                break;
            case 1:
            case 3:
                switch (this.state) {
                    case -1:
                        this.top = this.footerHeight;
                        scrollTo(0, -this.top);
                        break;
                    case 0:
                        if (this.top <= this.headerHeight) {
                            if (this.top >= (-this.footerHeight)) {
                                if (this.top != 0) {
                                    scrollTo(0, 0);
                                    if (this.adapter != null) {
                                        if (this.top > 0) {
                                            this.adapter.onPullDown(0);
                                        } else {
                                            this.adapter.onPullUp(0);
                                        }
                                    }
                                    this.top = 0;
                                    break;
                                }
                            } else {
                                this.top = -this.footerHeight;
                                scrollTo(0, -this.top);
                                if (this.adapter != null) {
                                    this.adapter.onPullUp(100);
                                }
                                performRequestNext();
                                ev = getCancelEvent(ev);
                                break;
                            }
                        } else {
                            this.top = this.headerHeight;
                            scrollTo(0, -this.top);
                            if (this.adapter != null) {
                                this.adapter.onPullDown(100);
                            }
                            performFresh();
                            ev = getCancelEvent(ev);
                            break;
                        }
                        break;
                    case 1:
                        this.top = this.headerHeight;
                        scrollTo(0, -this.top);
                        break;
                }
            case 2:
                float curY = ev.getY();
                boolean canPullDown = canPullDown();
                boolean canPullUp = canPullUp();
                if (canPullDown || canPullUp) {
                    this.top = (int) (this.top + ((curY - this.downY) / 2.0f));
                    if (this.top > 0 && canPullDown) {
                        scrollTo(0, -this.top);
                        if (this.state == 0 && this.adapter != null) {
                            this.adapter.onPullDown((this.top * 100) / this.headerHeight);
                        }
                        ev = getCancelEvent(ev);
                    } else if (this.top >= 0 || !canPullUp) {
                        this.top = 0;
                        scrollTo(0, 0);
                    } else {
                        scrollTo(0, -this.top);
                        if (this.state == 0 && this.adapter != null) {
                            this.adapter.onPullUp(((-this.top) * 100) / this.footerHeight);
                        }
                        ev = getCancelEvent(ev);
                    }
                }
                this.downY = curY;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void lockPullingDown() {
        this.pullingDownLock = true;
    }

    public void lockPullingUp() {
        this.pullingUpLock = true;
    }

    public void performPullingDown(boolean request) {
        this.top = this.headerHeight;
        scrollTo(0, -this.top);
        if (request) {
            performFresh();
        }
    }

    public void performPullingUp(boolean request) {
        this.top = -this.footerHeight;
        scrollTo(0, -this.top);
        if (request) {
            performRequestNext();
        }
    }

    public void releasePullingDownLock() {
        this.pullingDownLock = false;
    }

    public void releasePullingUpLock() {
        this.pullingUpLock = false;
    }

    public void setAdapter(PullToRequestAdatper adapter) {
        this.adapter = adapter;
        removeAllViews();
        this.bodyView = (View) adapter.getBodyView();
        RelativeLayout.LayoutParams lpBody = new RelativeLayout.LayoutParams(-1, -1);
        lpBody.addRule(9);
        lpBody.addRule(11);
        lpBody.addRule(10);
        addView(this.bodyView, lpBody);
        this.headerView = adapter.getHeaderView();
        this.headerView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.headerView.measure(0, 0);
        this.headerHeight = this.headerView.getMeasuredHeight();
        RelativeLayout.LayoutParams lpHead = new RelativeLayout.LayoutParams(-2, this.headerHeight);
        lpHead.addRule(9);
        lpHead.addRule(11);
        lpHead.addRule(10);
        lpHead.topMargin = -this.headerHeight;
        addView(this.headerView, lpHead);
        this.footerView = adapter.getFooterView();
        this.footerView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.footerView.measure(0, 0);
        this.footerHeight = this.footerView.getMeasuredHeight();
        RelativeLayout.LayoutParams lpFooter = new RelativeLayout.LayoutParams(-2, this.headerHeight);
        lpFooter.addRule(9);
        lpFooter.addRule(11);
        lpFooter.addRule(12);
        lpFooter.bottomMargin = -this.headerHeight;
        addView(this.footerView, lpFooter);
    }

    public void stopPulling() {
        long delta = System.currentTimeMillis() - this.pullTime;
        if (delta < MIN_REF_TIME) {
            postDelayed(this.stopAct, MIN_REF_TIME - delta);
        } else {
            post(this.stopAct);
        }
    }
}
