package com.cdlinglu.utils.menu;

import android.animation.Animator;
import android.graphics.Point;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.cdlinglu.utils.menu.MenuItemButton;

/* loaded from: classes.dex */
public abstract class MenuAnimationHandler {
    protected MenuItemButton menu;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public enum ActionType {
        OPENING,
        CLOSING
    }

    public abstract boolean isAnimating();

    protected abstract void setAnimating(boolean z);

    public void setMenu(MenuItemButton menu) {
        this.menu = menu;
    }

    public void animateMenuOpening(Point center) {
        if (this.menu == null) {
            throw new NullPointerException("MenuAnimationHandler cannot animate without a valid FloatingActionMenu.");
        }
    }

    public void animateMenuClosing(Point center) {
        if (this.menu == null) {
            throw new NullPointerException("MenuAnimationHandler cannot animate without a valid FloatingActionMenu.");
        }
    }

    protected void restoreSubActionViewAfterAnimation(MenuItemButton.Item subActionItem, ActionType actionType) {
        ViewGroup.LayoutParams params = subActionItem.view.getLayoutParams();
        subActionItem.view.setTranslationX(0.0f);
        subActionItem.view.setTranslationY(0.0f);
        subActionItem.view.setRotation(0.0f);
        subActionItem.view.setScaleX(1.0f);
        subActionItem.view.setScaleY(1.0f);
        subActionItem.view.setAlpha(1.0f);
        if (actionType == ActionType.OPENING) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) params;
            if (this.menu.isSystemOverlay()) {
                WindowManager.LayoutParams overlayParams = (WindowManager.LayoutParams) this.menu.getOverlayContainer().getLayoutParams();
                lp.setMargins(subActionItem.x - overlayParams.x, subActionItem.y - overlayParams.y, 0, 0);
            } else {
                lp.setMargins(subActionItem.x, subActionItem.y, 0, 0);
            }
            subActionItem.view.setLayoutParams(lp);
        } else if (actionType == ActionType.CLOSING) {
            Point center = this.menu.getActionViewCenter();
            FrameLayout.LayoutParams lp2 = (FrameLayout.LayoutParams) params;
            if (this.menu.isSystemOverlay()) {
                WindowManager.LayoutParams overlayParams2 = (WindowManager.LayoutParams) this.menu.getOverlayContainer().getLayoutParams();
                lp2.setMargins((center.x - overlayParams2.x) - (subActionItem.width / 2), (center.y - overlayParams2.y) - (subActionItem.height / 2), 0, 0);
            } else {
                lp2.setMargins(center.x - (subActionItem.width / 2), center.y - (subActionItem.height / 2), 0, 0);
            }
            subActionItem.view.setLayoutParams(lp2);
            this.menu.removeViewFromCurrentContainer(subActionItem.view);
            if (this.menu.isSystemOverlay() && this.menu.getOverlayContainer().getChildCount() == 0) {
                this.menu.detachOverlayContainer();
            }
        }
    }

    /* loaded from: classes.dex */
    public class LastAnimationListener implements Animator.AnimatorListener {
        public LastAnimationListener() {
            MenuAnimationHandler.this = this$0;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
            MenuAnimationHandler.this.setAnimating(true);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            MenuAnimationHandler.this.setAnimating(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
            MenuAnimationHandler.this.setAnimating(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animation) {
            MenuAnimationHandler.this.setAnimating(true);
        }
    }
}
