package com.cdlinglu.utils.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.amap.api.services.core.AMapException;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class MenuMainButton extends FrameLayout {
    public static final int POSITION_BOTTOM_CENTER = 5;
    public static final int POSITION_BOTTOM_LEFT = 6;
    public static final int POSITION_BOTTOM_RIGHT = 4;
    public static final int POSITION_LEFT_CENTER = 7;
    public static final int POSITION_RIGHT_CENTER = 3;
    public static final int POSITION_TOP_CENTER = 1;
    public static final int POSITION_TOP_LEFT = 8;
    public static final int POSITION_TOP_RIGHT = 2;
    public static final int THEME_DARK = 1;
    public static final int THEME_LIGHT = 0;
    private View contentView;
    private boolean systemOverlay;

    public MenuMainButton(Context context, ViewGroup.LayoutParams layoutParams, int theme, Drawable backgroundDrawable, int position, View contentView, FrameLayout.LayoutParams contentParams, boolean systemOverlay) {
        super(context);
        this.systemOverlay = systemOverlay;
        if (systemOverlay || (context instanceof Activity)) {
            setPosition(position, layoutParams);
            if (contentView != null) {
                setContentView(contentView, contentParams);
            }
            setClickable(true);
            attach(layoutParams);
            return;
        }
        throw new RuntimeException("Given context must be an instance of Activity, since this FAB is not a systemOverlay.");
    }

    public void setPosition(int position, ViewGroup.LayoutParams layoutParams) {
        int gravity;
        boolean setDefaultMargin = false;
        switch (position) {
            case 1:
                gravity = 49;
                break;
            case 2:
                gravity = 53;
                break;
            case 3:
                gravity = 21;
                break;
            case 4:
            default:
                setDefaultMargin = true;
                gravity = 85;
                break;
            case 5:
                gravity = 81;
                break;
            case 6:
                gravity = 83;
                break;
            case 7:
                gravity = 19;
                break;
            case 8:
                gravity = 51;
                break;
        }
        if (!this.systemOverlay) {
            try {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) layoutParams;
                lp.gravity = gravity;
                setLayoutParams(lp);
            } catch (ClassCastException e) {
                throw new ClassCastException("layoutParams must be an instance of FrameLayout.LayoutParams, since this FAB is not a systemOverlay");
            }
        } else {
            try {
                WindowManager.LayoutParams lp2 = (WindowManager.LayoutParams) layoutParams;
                lp2.gravity = gravity;
                if (setDefaultMargin) {
                    int margin = getContext().getResources().getDimensionPixelSize(R.dimen.action_button_margin);
                    lp2.x = margin;
                    lp2.y = margin;
                }
                setLayoutParams(lp2);
            } catch (ClassCastException e2) {
                throw new ClassCastException("layoutParams must be an instance of WindowManager.LayoutParams, since this FAB is a systemOverlay");
            }
        }
    }

    public void setContentView(View contentView, FrameLayout.LayoutParams contentParams) {
        FrameLayout.LayoutParams params;
        this.contentView = contentView;
        if (contentParams == null) {
            params = new FrameLayout.LayoutParams(-2, -2, 17);
            int margin = getResources().getDimensionPixelSize(R.dimen.action_button_content_margin);
            params.setMargins(margin, margin, margin, margin);
        } else {
            params = contentParams;
        }
        params.gravity = 17;
        contentView.setClickable(false);
        addView(contentView, params);
    }

    public void attach(ViewGroup.LayoutParams layoutParams) {
        if (this.systemOverlay) {
            try {
                getWindowManager().addView(this, layoutParams);
            } catch (SecurityException e) {
                throw new SecurityException("Your application must have SYSTEM_ALERT_WINDOW permission to create a system window.");
            }
        } else {
            ((ViewGroup) getActivityContentView()).addView(this, layoutParams);
        }
    }

    public void detach() {
        if (this.systemOverlay) {
            getWindowManager().removeView(this);
        } else {
            ((ViewGroup) getActivityContentView()).removeView(this);
        }
    }

    public View getActivityContentView() {
        try {
            return ((Activity) getContext()).getWindow().getDecorView().findViewById(16908290);
        } catch (ClassCastException e) {
            throw new ClassCastException("Please provide an Activity context for this FloatingActionButton.");
        }
    }

    public WindowManager getWindowManager() {
        return (WindowManager) getContext().getSystemService("window");
    }

    private void setBackgroundResource(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private Drawable backgroundDrawable;
        private FrameLayout.LayoutParams contentParams;
        private View contentView;
        private Context context;
        private ViewGroup.LayoutParams layoutParams;
        private int position;
        private boolean systemOverlay;
        private int theme;

        public Builder(Context context) {
            this.context = context;
            int size = context.getResources().getDimensionPixelSize(R.dimen.action_button_size);
            int margin = context.getResources().getDimensionPixelSize(R.dimen.action_button_margin);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(size, size, 85);
            layoutParams.setMargins(margin, margin, margin, size);
            setLayoutParams(layoutParams);
            setTheme(0);
            setPosition(4);
            setSystemOverlay(false);
        }

        public Builder setLayoutParams(ViewGroup.LayoutParams params) {
            this.layoutParams = params;
            return this;
        }

        public Builder setTheme(int theme) {
            this.theme = theme;
            return this;
        }

        public Builder setBackgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder setBackgroundDrawable(int drawableId) {
            return setBackgroundDrawable(this.context.getResources().getDrawable(drawableId));
        }

        public Builder setPosition(int position) {
            this.position = position;
            return this;
        }

        public Builder setContentView(View contentView) {
            return setContentView(contentView, null);
        }

        public Builder setContentView(View contentView, FrameLayout.LayoutParams contentParams) {
            this.contentView = contentView;
            this.contentParams = contentParams;
            return this;
        }

        public Builder setSystemOverlay(boolean systemOverlay) {
            this.systemOverlay = systemOverlay;
            return this;
        }

        public MenuMainButton build() {
            return new MenuMainButton(this.context, this.layoutParams, this.theme, this.backgroundDrawable, this.position, this.contentView, this.contentParams, this.systemOverlay);
        }

        public static WindowManager.LayoutParams getDefaultSystemWindowParams(Context context) {
            int size = context.getResources().getDimensionPixelSize(R.dimen.action_button_size);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(size, size, AMapException.CODE_AMAP_ENGINE_TABLEID_NOT_EXIST, 40, -3);
            params.format = 1;
            params.gravity = 51;
            return params;
        }
    }
}
