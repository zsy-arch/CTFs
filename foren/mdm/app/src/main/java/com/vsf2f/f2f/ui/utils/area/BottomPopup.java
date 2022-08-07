package com.vsf2f.f2f.ui.utils.area;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.CallSuper;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.hy.frame.util.MyLog;
import com.vsf2f.f2f.ui.other.Popup;

/* loaded from: classes2.dex */
public abstract class BottomPopup<V extends View> implements DialogInterface.OnKeyListener {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;
    protected Context context;
    private Popup popup;
    protected int screenHeightPixels;
    protected int screenWidthPixels;
    private int width = 0;
    private int height = 0;
    private boolean isFillScreen = false;
    private boolean isHalfScreen = false;
    private boolean isPrepared = false;

    protected abstract V makeContentView();

    public BottomPopup(Context context) {
        this.context = context;
        DisplayMetrics displayMetrics = ScreenUtils.displayMetrics(context);
        this.screenWidthPixels = displayMetrics.widthPixels;
        this.screenHeightPixels = displayMetrics.heightPixels;
        this.popup = new Popup(context);
        this.popup.setOnKeyListener(this);
    }

    private void onShowPrepare() {
        if (!this.isPrepared) {
            setContentViewBefore();
            V view = makeContentView();
            this.popup.setContentView(view);
            setContentViewAfter(view);
            MyLog.d("do something before popup show");
            if (this.width == 0 && this.height == 0) {
                this.width = this.screenWidthPixels;
                if (this.isFillScreen) {
                    this.height = -1;
                } else if (this.isHalfScreen) {
                    this.height = this.screenHeightPixels / 2;
                } else {
                    this.height = -2;
                }
            }
            this.popup.setSize(this.width, this.height);
            this.isPrepared = true;
        }
    }

    public void setFillScreen(boolean fillScreen) {
        this.isFillScreen = fillScreen;
    }

    public void setHalfScreen(boolean halfScreen) {
        this.isHalfScreen = halfScreen;
    }

    protected void setContentViewBefore() {
    }

    protected void setContentViewAfter(V contentView) {
    }

    public void setAnimationStyle(@StyleRes int animRes) {
        this.popup.setAnimationStyle(animRes);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.popup.setOnDismissListener(onDismissListener);
        MyLog.d("popup setOnDismissListener");
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isShowing() {
        return this.popup.isShowing();
    }

    @CallSuper
    public void show() {
        onShowPrepare();
        this.popup.show();
        MyLog.d("popup show");
    }

    public void dismiss() {
        this.popup.dismiss();
        MyLog.d("popup dismiss");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            return onKeyDown(keyCode, event);
        }
        return false;
    }

    public Window getWindow() {
        return this.popup.getWindow();
    }

    public ViewGroup getRootView() {
        return this.popup.getRootView();
    }
}
