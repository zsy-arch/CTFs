package com.vsf2f.f2f.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.hy.frame.util.HyUtil;

/* loaded from: classes2.dex */
public abstract class BasePopupMenu extends PopupWindow implements View.OnClickListener {
    private PopupListener listener;
    private Context mContext;
    private View parent;
    private int width;

    /* loaded from: classes2.dex */
    public interface PopupListener {
        void onClickPopup(View view);
    }

    protected abstract int initLayoutId();

    protected abstract void initView();

    protected abstract int initWidth();

    public BasePopupMenu(Context context) {
        super(context);
        this.mContext = context;
        this.parent = View.inflate(context, initLayoutId(), null);
        setContentView(this.parent);
        setBackgroundDrawable(new BitmapDrawable());
        setBackgroundAlpha(0.5f);
        this.width = HyUtil.dip2px(context, initWidth());
        setWidth(this.width);
        setHeight(-2);
        setFocusable(true);
        setOutsideTouchable(true);
        setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.vsf2f.f2f.ui.dialog.BasePopupMenu.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                BasePopupMenu.this.setBackgroundAlpha(1.0f);
            }
        });
        initView();
    }

    protected Context getContext() {
        return this.mContext;
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 19) {
            super.showAsDropDown(anchor, 0, 0, 8388661);
        } else {
            super.showAsDropDown(anchor, anchor.getWidth() - getWidth(), 0);
        }
    }

    public void showAsDrawTop(View anchor) {
        if (Build.VERSION.SDK_INT >= 19) {
            super.showAsDropDown(anchor, 0, 0, 8388661);
        } else {
            super.showAsDropDown(anchor, 0, 0);
        }
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) this.mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) this.mContext).getWindow().setAttributes(lp);
    }

    protected <T> T getView(int resId) {
        return (T) this.parent.findViewById(resId);
    }

    protected <T extends View> T getViewAndClick(@IdRes int id) {
        T v = (T) ((View) getView(id));
        v.setOnClickListener(this);
        return v;
    }

    protected void setOnClickListener(int resId) {
        this.parent.findViewById(resId).setOnClickListener(this);
    }

    public PopupListener getListener() {
        return this.listener;
    }

    public void setListener(PopupListener listener) {
        this.listener = listener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
        if (getListener() != null) {
            getListener().onClickPopup(view);
        }
    }
}
