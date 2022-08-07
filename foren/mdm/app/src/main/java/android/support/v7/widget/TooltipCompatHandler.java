package android.support.v7.widget;

import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static final long HOVER_HIDE_TIMEOUT_MS = 15000;
    private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000;
    private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500;
    private static final String TAG = "TooltipCompatHandler";
    private static TooltipCompatHandler sActiveHandler;
    private final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private boolean mFromTouch;
    private TooltipPopup mPopup;
    private final CharSequence mTooltipText;
    private final Runnable mShowRunnable = new Runnable() { // from class: android.support.v7.widget.TooltipCompatHandler.1
        @Override // java.lang.Runnable
        public void run() {
            TooltipCompatHandler.this.show(false);
        }
    };
    private final Runnable mHideRunnable = new Runnable() { // from class: android.support.v7.widget.TooltipCompatHandler.2
        @Override // java.lang.Runnable
        public void run() {
            TooltipCompatHandler.this.hide();
        }
    };

    public static void setTooltipText(View view, CharSequence tooltipText) {
        if (TextUtils.isEmpty(tooltipText)) {
            if (sActiveHandler != null && sActiveHandler.mAnchor == view) {
                sActiveHandler.hide();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new TooltipCompatHandler(view, tooltipText);
    }

    private TooltipCompatHandler(View anchor, CharSequence tooltipText) {
        this.mAnchor = anchor;
        this.mTooltipText = tooltipText;
        this.mAnchor.setOnLongClickListener(this);
        this.mAnchor.setOnHoverListener(this);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        this.mAnchorX = v.getWidth() / 2;
        this.mAnchorY = v.getHeight() / 2;
        show(true);
        return true;
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View v, MotionEvent event) {
        if (this.mPopup == null || !this.mFromTouch) {
            AccessibilityManager manager = (AccessibilityManager) this.mAnchor.getContext().getSystemService("accessibility");
            if (!manager.isEnabled() || !manager.isTouchExplorationEnabled()) {
                switch (event.getAction()) {
                    case 7:
                        if (this.mAnchor.isEnabled() && this.mPopup == null) {
                            this.mAnchorX = (int) event.getX();
                            this.mAnchorY = (int) event.getY();
                            this.mAnchor.removeCallbacks(this.mShowRunnable);
                            this.mAnchor.postDelayed(this.mShowRunnable, ViewConfiguration.getLongPressTimeout());
                            break;
                        }
                        break;
                    case 10:
                        hide();
                        break;
                }
            }
        }
        return false;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View v) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View v) {
        hide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void show(boolean fromTouch) {
        long timeout;
        if (ViewCompat.isAttachedToWindow(this.mAnchor)) {
            if (sActiveHandler != null) {
                sActiveHandler.hide();
            }
            sActiveHandler = this;
            this.mFromTouch = fromTouch;
            this.mPopup = new TooltipPopup(this.mAnchor.getContext());
            this.mPopup.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
            this.mAnchor.addOnAttachStateChangeListener(this);
            if (this.mFromTouch) {
                timeout = LONG_CLICK_HIDE_TIMEOUT_MS;
            } else if ((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 1) == 1) {
                timeout = HOVER_HIDE_TIMEOUT_SHORT_MS - ViewConfiguration.getLongPressTimeout();
            } else {
                timeout = HOVER_HIDE_TIMEOUT_MS - ViewConfiguration.getLongPressTimeout();
            }
            this.mAnchor.removeCallbacks(this.mHideRunnable);
            this.mAnchor.postDelayed(this.mHideRunnable, timeout);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hide() {
        if (sActiveHandler == this) {
            sActiveHandler = null;
            if (this.mPopup != null) {
                this.mPopup.hide();
                this.mPopup = null;
                this.mAnchor.removeOnAttachStateChangeListener(this);
            } else {
                Log.e(TAG, "sActiveHandler.mPopup == null");
            }
        }
        this.mAnchor.removeCallbacks(this.mShowRunnable);
        this.mAnchor.removeCallbacks(this.mHideRunnable);
    }
}
