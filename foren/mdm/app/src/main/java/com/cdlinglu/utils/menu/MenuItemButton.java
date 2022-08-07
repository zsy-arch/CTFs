package com.cdlinglu.utils.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.DrawableRes;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.services.core.AMapException;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MenuItemButton {
    private boolean animated;
    private MenuAnimationHandler animationHandler;
    private int endAngle;
    private View mainActionView;
    private boolean open = false;
    private OrientationEventListener orientationListener;
    private FrameLayout overlayContainer;
    private int radius;
    private int startAngle;
    private MenuStateChangeListener stateChangeListener;
    private List<Item> subActionItems;
    private boolean systemOverlay;

    /* loaded from: classes.dex */
    public interface MenuStateChangeListener {
        void onMenuClosed(MenuItemButton menuItemButton);

        void onMenuOpened(MenuItemButton menuItemButton);
    }

    /* loaded from: classes.dex */
    public interface SateliteClickedListener {
        void eventClick(int i);
    }

    public MenuItemButton(View mainActionView, int startAngle, int endAngle, int radius, List<Item> subActionItems, MenuAnimationHandler animationHandler, boolean animated, MenuStateChangeListener stateChangeListener, boolean systemOverlay) {
        this.mainActionView = mainActionView;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.radius = radius;
        this.subActionItems = subActionItems;
        this.animationHandler = animationHandler;
        this.animated = animated;
        this.systemOverlay = systemOverlay;
        this.stateChangeListener = stateChangeListener;
        this.mainActionView.setClickable(true);
        this.mainActionView.setOnClickListener(new ActionViewClickListener());
        if (animationHandler != null) {
            animationHandler.setMenu(this);
        }
        if (systemOverlay) {
            this.overlayContainer = new FrameLayout(mainActionView.getContext());
        } else {
            this.overlayContainer = null;
        }
        for (Item item : subActionItems) {
            if (item.width == 0 || item.height == 0) {
                if (systemOverlay) {
                    throw new RuntimeException("Sub action views cannot be added without definite width and height.");
                }
                addViewToCurrentContainer(item.view);
                item.view.setAlpha(0.0f);
                item.view.post(new ItemViewQueueListener(item));
            }
        }
        if (systemOverlay) {
            this.orientationListener = new OrientationEventListener(mainActionView.getContext(), 2) { // from class: com.cdlinglu.utils.menu.MenuItemButton.1
                private int lastState = -1;

                @Override // android.view.OrientationEventListener
                public void onOrientationChanged(int orientation) {
                    Display display = MenuItemButton.this.getWindowManager().getDefaultDisplay();
                    if (display.getRotation() != this.lastState) {
                        this.lastState = display.getRotation();
                        if (MenuItemButton.this.isOpen()) {
                            MenuItemButton.this.close(false);
                        }
                    }
                }
            };
            this.orientationListener.enable();
        }
    }

    public void open(boolean animated) {
        Point center = calculateItemPositions();
        WindowManager.LayoutParams overlayParams = null;
        if (this.systemOverlay) {
            attachOverlayContainer();
            overlayParams = (WindowManager.LayoutParams) this.overlayContainer.getLayoutParams();
        }
        if (!animated || this.animationHandler == null) {
            for (int i = 0; i < this.subActionItems.size(); i++) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(this.subActionItems.get(i).width, this.subActionItems.get(i).height, 51);
                if (this.systemOverlay) {
                    params.setMargins(this.subActionItems.get(i).x - overlayParams.x, this.subActionItems.get(i).y - overlayParams.y, 0, 0);
                    this.subActionItems.get(i).view.setLayoutParams(params);
                } else {
                    params.setMargins(this.subActionItems.get(i).x, this.subActionItems.get(i).y, 0, 0);
                    this.subActionItems.get(i).view.setLayoutParams(params);
                }
                addViewToCurrentContainer(this.subActionItems.get(i).view, params);
            }
        } else if (!this.animationHandler.isAnimating()) {
            for (int i2 = 0; i2 < this.subActionItems.size(); i2++) {
                if (this.subActionItems.get(i2).view.getParent() != null) {
                    throw new RuntimeException("All of the sub action items have to be independent from a parent.");
                }
                FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(this.subActionItems.get(i2).width, this.subActionItems.get(i2).height, 51);
                if (this.systemOverlay) {
                    params2.setMargins((center.x - overlayParams.x) - (this.subActionItems.get(i2).width / 2), (center.y - overlayParams.y) - (this.subActionItems.get(i2).height / 2), 0, 0);
                } else {
                    params2.setMargins(center.x - (this.subActionItems.get(i2).width / 2), center.y - (this.subActionItems.get(i2).height / 2), 0, 0);
                }
                addViewToCurrentContainer(this.subActionItems.get(i2).view, params2);
            }
            this.animationHandler.animateMenuOpening(center);
        } else {
            return;
        }
        this.open = true;
        if (this.stateChangeListener != null) {
            this.stateChangeListener.onMenuOpened(this);
        }
    }

    public void close(boolean animated) {
        if (!animated || this.animationHandler == null) {
            for (int i = 0; i < this.subActionItems.size(); i++) {
                removeViewFromCurrentContainer(this.subActionItems.get(i).view);
            }
            detachOverlayContainer();
        } else if (!this.animationHandler.isAnimating()) {
            this.animationHandler.animateMenuClosing(getActionViewCenter());
        } else {
            return;
        }
        this.open = false;
        if (this.stateChangeListener != null) {
            this.stateChangeListener.onMenuClosed(this);
        }
    }

    public void toggle(boolean animated) {
        if (this.open) {
            close(animated);
        } else {
            open(animated);
        }
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isSystemOverlay() {
        return this.systemOverlay;
    }

    public FrameLayout getOverlayContainer() {
        return this.overlayContainer;
    }

    public void updateItemPositions() {
        if (isOpen()) {
            calculateItemPositions();
            for (int i = 0; i < this.subActionItems.size(); i++) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(this.subActionItems.get(i).width, this.subActionItems.get(i).height, 51);
                params.setMargins(this.subActionItems.get(i).x, this.subActionItems.get(i).y, 0, 0);
                this.subActionItems.get(i).view.setLayoutParams(params);
            }
        }
    }

    private Point getActionViewCoordinates() {
        int[] coords = new int[2];
        this.mainActionView.getLocationOnScreen(coords);
        if (this.systemOverlay) {
            coords[1] = coords[1] - getStatusBarHeight();
        } else {
            Rect activityFrame = new Rect();
            getActivityContentView().getWindowVisibleDisplayFrame(activityFrame);
            coords[0] = coords[0] - (getScreenSize().x - getActivityContentView().getMeasuredWidth());
            coords[1] = coords[1] - ((activityFrame.height() + activityFrame.top) - getActivityContentView().getMeasuredHeight());
        }
        return new Point(coords[0], coords[1]);
    }

    public Point getActionViewCenter() {
        Point point = getActionViewCoordinates();
        point.x += this.mainActionView.getMeasuredWidth() / 2;
        point.y += this.mainActionView.getMeasuredHeight() / 2;
        return point;
    }

    private Point calculateItemPositions() {
        Point center = getActionViewCenter();
        RectF area = new RectF(center.x - this.radius, center.y - this.radius, center.x + this.radius, center.y + this.radius);
        Path orbit = new Path();
        orbit.addArc(area, this.startAngle, this.endAngle - this.startAngle);
        PathMeasure measure = new PathMeasure(orbit, false);
        int divisor = (Math.abs(this.endAngle - this.startAngle) >= 360 || this.subActionItems.size() <= 1) ? this.subActionItems.size() : this.subActionItems.size() - 1;
        for (int i = 0; i < this.subActionItems.size(); i++) {
            float[] coords = {0.0f, 0.0f};
            measure.getPosTan((i * measure.getLength()) / divisor, coords, null);
            this.subActionItems.get(i).x = ((int) coords[0]) - (this.subActionItems.get(i).width / 2);
            this.subActionItems.get(i).y = ((int) coords[1]) - (this.subActionItems.get(i).height / 2);
        }
        return center;
    }

    public int getRadius() {
        return this.radius;
    }

    public List<Item> getSubActionItems() {
        return this.subActionItems;
    }

    public View getActivityContentView() {
        try {
            return ((Activity) this.mainActionView.getContext()).getWindow().getDecorView().findViewById(16908290);
        } catch (ClassCastException e) {
            throw new ClassCastException("Please provide an Activity context for this FloatingActionMenu.");
        }
    }

    public WindowManager getWindowManager() {
        return (WindowManager) this.mainActionView.getContext().getSystemService("window");
    }

    private void addViewToCurrentContainer(View view, ViewGroup.LayoutParams layoutParams) {
        if (this.systemOverlay) {
            this.overlayContainer.addView(view, layoutParams);
            return;
        }
        try {
            if (layoutParams != null) {
                ((ViewGroup) getActivityContentView()).addView(view, (FrameLayout.LayoutParams) layoutParams);
            } else {
                ((ViewGroup) getActivityContentView()).addView(view);
            }
        } catch (ClassCastException e) {
            throw new ClassCastException("layoutParams must be an instance of FrameLayout.LayoutParams.");
        }
    }

    public void attachOverlayContainer() {
        try {
            WindowManager.LayoutParams overlayParams = calculateOverlayContainerParams();
            this.overlayContainer.setLayoutParams(overlayParams);
            if (this.overlayContainer.getParent() == null) {
                getWindowManager().addView(this.overlayContainer, overlayParams);
            }
            getWindowManager().updateViewLayout(this.mainActionView, this.mainActionView.getLayoutParams());
        } catch (SecurityException e) {
            throw new SecurityException("Your application must have SYSTEM_ALERT_WINDOW permission to create a system window.");
        }
    }

    private WindowManager.LayoutParams calculateOverlayContainerParams() {
        WindowManager.LayoutParams overlayParams = getDefaultSystemWindowParams();
        int left = 9999;
        int right = 0;
        int top = 9999;
        int bottom = 0;
        for (int i = 0; i < this.subActionItems.size(); i++) {
            int lm = this.subActionItems.get(i).x;
            int tm = this.subActionItems.get(i).y;
            if (lm < left) {
                left = lm;
            }
            if (tm < top) {
                top = tm;
            }
            if (this.subActionItems.get(i).width + lm > right) {
                right = lm + this.subActionItems.get(i).width;
            }
            if (this.subActionItems.get(i).height + tm > bottom) {
                bottom = tm + this.subActionItems.get(i).height;
            }
        }
        overlayParams.width = right - left;
        overlayParams.height = bottom - top;
        overlayParams.x = left;
        overlayParams.y = top;
        overlayParams.gravity = 51;
        return overlayParams;
    }

    public void detachOverlayContainer() {
        getWindowManager().removeView(this.overlayContainer);
    }

    public int getStatusBarHeight() {
        int resourceId = this.mainActionView.getContext().getResources().getIdentifier("status_bar_height", "dimen", f.a);
        if (resourceId > 0) {
            return this.mainActionView.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public void addViewToCurrentContainer(View view) {
        addViewToCurrentContainer(view, null);
    }

    public void removeViewFromCurrentContainer(View view) {
        if (this.systemOverlay) {
            this.overlayContainer.removeView(view);
        } else {
            ((ViewGroup) getActivityContentView()).removeView(view);
        }
    }

    private Point getScreenSize() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return size;
    }

    public void setStateChangeListener(MenuStateChangeListener listener) {
        this.stateChangeListener = listener;
    }

    /* loaded from: classes.dex */
    public class ActionViewClickListener implements View.OnClickListener {
        public ActionViewClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MenuItemButton.this.toggle(MenuItemButton.this.animated);
        }
    }

    /* loaded from: classes.dex */
    private class ItemViewQueueListener implements Runnable {
        private static final int MAX_TRIES = 10;
        private Item item;
        private int tries = 0;

        public ItemViewQueueListener(Item item) {
            this.item = item;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.item.view.getMeasuredWidth() != 0 || this.tries >= 10) {
                this.item.width = this.item.view.getMeasuredWidth();
                this.item.height = this.item.view.getMeasuredHeight();
                this.item.view.setAlpha(this.item.alpha);
                MenuItemButton.this.removeViewFromCurrentContainer(this.item.view);
                return;
            }
            this.item.view.post(this);
        }
    }

    /* loaded from: classes.dex */
    public static class Item {
        public float alpha;
        public int height;
        public View view;
        public int width;
        public int x = 0;
        public int y = 0;

        public Item(View view, int width, int height) {
            this.view = view;
            this.width = width;
            this.height = height;
            this.alpha = view.getAlpha();
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private View actionView;
        private boolean animated;
        private MenuAnimationHandler animationHandler;
        private int endAngle;
        private SateliteClickedListener itemClickedListener;
        private ViewGroup.LayoutParams layout;
        private int radius;
        private int startAngle;
        private MenuStateChangeListener stateChangeListener;
        private List<Item> subActionItems;
        private boolean systemOverlay;

        public Builder(Context context, boolean systemOverlay) {
            this.layout = new ViewGroup.LayoutParams(-2, -2);
            this.subActionItems = new ArrayList();
            this.radius = context.getResources().getDimensionPixelSize(R.dimen.action_menu_radius);
            this.startAngle = 180;
            this.endAngle = 270;
            this.animationHandler = new DefaultAnimationHandler();
            this.animated = true;
            this.systemOverlay = systemOverlay;
        }

        public Builder(Context context, SateliteClickedListener itemClickedListener) {
            this(context, false);
            setOnItemClickedListener(itemClickedListener);
        }

        public Builder setStartAngle(int startAngle) {
            this.startAngle = startAngle;
            return this;
        }

        public Builder setEndAngle(int endAngle) {
            this.endAngle = endAngle;
            return this;
        }

        public Builder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public void setOnItemClickedListener(SateliteClickedListener itemClickedListener) {
            this.itemClickedListener = itemClickedListener;
        }

        public Builder addItem(Context context, @DrawableRes int rid, final int tag) {
            ImageView rlIcon = new ImageView(context);
            rlIcon.setLayoutParams(MenuItemButton.getDefaultSystemWindowParams());
            rlIcon.setImageResource(rid);
            rlIcon.setOnClickListener(new View.OnClickListener() { // from class: com.cdlinglu.utils.menu.MenuItemButton.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Builder.this.itemClickedListener.eventClick(tag);
                }
            });
            addSubActionView(rlIcon);
            return this;
        }

        public Builder addSubActionView(View subActionView, int width, int height) {
            this.subActionItems.add(new Item(subActionView, width, height));
            return this;
        }

        public Builder addSubActionView(View subActionView) {
            if (!this.systemOverlay) {
                return addSubActionView(subActionView, 0, 0);
            }
            throw new RuntimeException("Sub action views cannot be added without definite width and height. Please use other methods named addSubActionView");
        }

        public Builder addSubActionView(int resId, Context context) {
            View view = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(resId, (ViewGroup) null, false);
            view.measure(0, 0);
            return addSubActionView(view, view.getMeasuredWidth(), view.getMeasuredHeight());
        }

        public Builder setAnimationHandler(MenuAnimationHandler animationHandler) {
            this.animationHandler = animationHandler;
            return this;
        }

        public Builder enableAnimations() {
            this.animated = true;
            return this;
        }

        public Builder disableAnimations() {
            this.animated = false;
            return this;
        }

        public Builder setStateChangeListener(MenuStateChangeListener listener) {
            this.stateChangeListener = listener;
            return this;
        }

        public Builder setSystemOverlay(boolean systemOverlay) {
            this.systemOverlay = systemOverlay;
            return this;
        }

        public Builder attachTo(View actionView) {
            this.actionView = actionView;
            return this;
        }

        public MenuItemButton build() {
            return new MenuItemButton(this.actionView, this.startAngle, this.endAngle, this.radius, this.subActionItems, this.animationHandler, this.animated, this.stateChangeListener, this.systemOverlay);
        }
    }

    public static WindowManager.LayoutParams getDefaultSystemWindowParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(-2, -2, AMapException.CODE_AMAP_SERVICE_MAINTENANCE, 40, -3);
        params.format = 1;
        params.gravity = 51;
        return params;
    }
}
