package com.cdlinglu.utils.menu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Point;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import com.cdlinglu.utils.menu.MenuAnimationHandler;
import com.cdlinglu.utils.menu.MenuItemButton;

/* loaded from: classes.dex */
public class DefaultAnimationHandler extends MenuAnimationHandler {
    protected static final int DURATION = 500;
    protected static final int LAG_BETWEEN_ITEMS = 20;
    private boolean animating;

    public DefaultAnimationHandler() {
        setAnimating(false);
    }

    @Override // com.cdlinglu.utils.menu.MenuAnimationHandler
    public void animateMenuOpening(Point center) {
        super.animateMenuOpening(center);
        setAnimating(true);
        Animator lastAnimation = null;
        for (int i = 0; i < this.menu.getSubActionItems().size(); i++) {
            this.menu.getSubActionItems().get(i).view.setScaleX(0.0f);
            this.menu.getSubActionItems().get(i).view.setScaleY(0.0f);
            this.menu.getSubActionItems().get(i).view.setAlpha(0.0f);
            ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(this.menu.getSubActionItems().get(i).view, PropertyValuesHolder.ofFloat(View.TRANSLATION_X, (this.menu.getSubActionItems().get(i).width / 2) + (this.menu.getSubActionItems().get(i).x - center.x)), PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, (this.menu.getSubActionItems().get(i).height / 2) + (this.menu.getSubActionItems().get(i).y - center.y)), PropertyValuesHolder.ofFloat(View.ROTATION, 720.0f), PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f), PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f), PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f));
            animation.setDuration(500L);
            animation.setInterpolator(new OvershootInterpolator(0.9f));
            animation.addListener(new SubActionItemAnimationListener(this.menu.getSubActionItems().get(i), MenuAnimationHandler.ActionType.OPENING));
            if (i == 0) {
                lastAnimation = animation;
            }
            animation.setStartDelay((this.menu.getSubActionItems().size() - i) * 20);
            animation.start();
        }
        if (lastAnimation != null) {
            lastAnimation.addListener(new MenuAnimationHandler.LastAnimationListener());
        }
    }

    @Override // com.cdlinglu.utils.menu.MenuAnimationHandler
    public void animateMenuClosing(Point center) {
        super.animateMenuOpening(center);
        setAnimating(true);
        Animator lastAnimation = null;
        for (int i = 0; i < this.menu.getSubActionItems().size(); i++) {
            ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(this.menu.getSubActionItems().get(i).view, PropertyValuesHolder.ofFloat(View.TRANSLATION_X, -((this.menu.getSubActionItems().get(i).width / 2) + (this.menu.getSubActionItems().get(i).x - center.x))), PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -((this.menu.getSubActionItems().get(i).height / 2) + (this.menu.getSubActionItems().get(i).y - center.y))), PropertyValuesHolder.ofFloat(View.ROTATION, -720.0f), PropertyValuesHolder.ofFloat(View.SCALE_X, 0.0f), PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.0f), PropertyValuesHolder.ofFloat(View.ALPHA, 0.0f));
            animation.setDuration(500L);
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation.addListener(new SubActionItemAnimationListener(this.menu.getSubActionItems().get(i), MenuAnimationHandler.ActionType.CLOSING));
            if (i == 0) {
                lastAnimation = animation;
            }
            animation.setStartDelay((this.menu.getSubActionItems().size() - i) * 20);
            animation.start();
        }
        if (lastAnimation != null) {
            lastAnimation.addListener(new MenuAnimationHandler.LastAnimationListener());
        }
    }

    @Override // com.cdlinglu.utils.menu.MenuAnimationHandler
    public boolean isAnimating() {
        return this.animating;
    }

    @Override // com.cdlinglu.utils.menu.MenuAnimationHandler
    protected void setAnimating(boolean animating) {
        this.animating = animating;
    }

    /* loaded from: classes.dex */
    protected class SubActionItemAnimationListener implements Animator.AnimatorListener {
        private MenuAnimationHandler.ActionType actionType;
        private MenuItemButton.Item subActionItem;

        public SubActionItemAnimationListener(MenuItemButton.Item subActionItem, MenuAnimationHandler.ActionType actionType) {
            DefaultAnimationHandler.this = this$0;
            this.subActionItem = subActionItem;
            this.actionType = actionType;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            DefaultAnimationHandler.this.restoreSubActionViewAfterAnimation(this.subActionItem, this.actionType);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
            DefaultAnimationHandler.this.restoreSubActionViewAfterAnimation(this.subActionItem, this.actionType);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animation) {
        }
    }
}
