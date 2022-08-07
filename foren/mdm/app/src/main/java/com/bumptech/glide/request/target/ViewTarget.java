package com.bumptech.glide.request.target;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.bumptech.glide.request.Request;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ViewTarget<T extends View, Z> extends BaseTarget<Z> {
    private static final String TAG = "ViewTarget";
    private static boolean isTagUsedAtLeastOnce = false;
    private static Integer tagId = null;
    private final SizeDeterminer sizeDeterminer;
    protected final T view;

    public static void setTagId(int tagId2) {
        if (tagId != null || isTagUsedAtLeastOnce) {
            throw new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
        }
        tagId = Integer.valueOf(tagId2);
    }

    public ViewTarget(T view) {
        if (view == null) {
            throw new NullPointerException("View must not be null!");
        }
        this.view = view;
        this.sizeDeterminer = new SizeDeterminer(view);
    }

    public T getView() {
        return this.view;
    }

    @Override // com.bumptech.glide.request.target.Target
    public void getSize(SizeReadyCallback cb) {
        this.sizeDeterminer.getSize(cb);
    }

    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
    public void setRequest(Request request) {
        setTag(request);
    }

    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
    public Request getRequest() {
        Object tag = getTag();
        if (tag == null) {
            return null;
        }
        if (tag instanceof Request) {
            return (Request) tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    private void setTag(Object tag) {
        if (tagId == null) {
            isTagUsedAtLeastOnce = true;
            this.view.setTag(tag);
            return;
        }
        this.view.setTag(tagId.intValue(), tag);
    }

    private Object getTag() {
        return tagId == null ? this.view.getTag() : this.view.getTag(tagId.intValue());
    }

    public String toString() {
        return "Target for: " + this.view;
    }

    /* loaded from: classes.dex */
    private static class SizeDeterminer {
        private static final int PENDING_SIZE = 0;
        private final List<SizeReadyCallback> cbs = new ArrayList();
        private Point displayDimens;
        private SizeDeterminerLayoutListener layoutListener;
        private final View view;

        public SizeDeterminer(View view) {
            this.view = view;
        }

        private void notifyCbs(int width, int height) {
            for (SizeReadyCallback cb : this.cbs) {
                cb.onSizeReady(width, height);
            }
            this.cbs.clear();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void checkCurrentDimens() {
            if (!this.cbs.isEmpty()) {
                int currentWidth = getViewWidthOrParam();
                int currentHeight = getViewHeightOrParam();
                if (isSizeValid(currentWidth) && isSizeValid(currentHeight)) {
                    notifyCbs(currentWidth, currentHeight);
                    ViewTreeObserver observer = this.view.getViewTreeObserver();
                    if (observer.isAlive()) {
                        observer.removeOnPreDrawListener(this.layoutListener);
                    }
                    this.layoutListener = null;
                }
            }
        }

        public void getSize(SizeReadyCallback cb) {
            int currentWidth = getViewWidthOrParam();
            int currentHeight = getViewHeightOrParam();
            if (!isSizeValid(currentWidth) || !isSizeValid(currentHeight)) {
                if (!this.cbs.contains(cb)) {
                    this.cbs.add(cb);
                }
                if (this.layoutListener == null) {
                    ViewTreeObserver observer = this.view.getViewTreeObserver();
                    this.layoutListener = new SizeDeterminerLayoutListener(this);
                    observer.addOnPreDrawListener(this.layoutListener);
                    return;
                }
                return;
            }
            cb.onSizeReady(currentWidth, currentHeight);
        }

        private int getViewHeightOrParam() {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (isSizeValid(this.view.getHeight())) {
                return this.view.getHeight();
            }
            if (layoutParams != null) {
                return getSizeForParam(layoutParams.height, true);
            }
            return 0;
        }

        private int getViewWidthOrParam() {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (isSizeValid(this.view.getWidth())) {
                return this.view.getWidth();
            }
            if (layoutParams != null) {
                return getSizeForParam(layoutParams.width, false);
            }
            return 0;
        }

        private int getSizeForParam(int param, boolean isHeight) {
            if (param != -2) {
                return param;
            }
            Point displayDimens = getDisplayDimens();
            return isHeight ? displayDimens.y : displayDimens.x;
        }

        @TargetApi(13)
        private Point getDisplayDimens() {
            if (this.displayDimens != null) {
                return this.displayDimens;
            }
            Display display = ((WindowManager) this.view.getContext().getSystemService("window")).getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= 13) {
                this.displayDimens = new Point();
                display.getSize(this.displayDimens);
            } else {
                this.displayDimens = new Point(display.getWidth(), display.getHeight());
            }
            return this.displayDimens;
        }

        private boolean isSizeValid(int size) {
            return size > 0 || size == -2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class SizeDeterminerLayoutListener implements ViewTreeObserver.OnPreDrawListener {
            private final WeakReference<SizeDeterminer> sizeDeterminerRef;

            public SizeDeterminerLayoutListener(SizeDeterminer sizeDeterminer) {
                this.sizeDeterminerRef = new WeakReference<>(sizeDeterminer);
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (Log.isLoggable(ViewTarget.TAG, 2)) {
                    Log.v(ViewTarget.TAG, "OnGlobalLayoutListener called listener=" + this);
                }
                SizeDeterminer sizeDeterminer = this.sizeDeterminerRef.get();
                if (sizeDeterminer == null) {
                    return true;
                }
                sizeDeterminer.checkCurrentDimens();
                return true;
            }
        }
    }
}
