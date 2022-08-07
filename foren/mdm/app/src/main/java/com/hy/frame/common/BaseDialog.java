package com.hy.frame.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.hy.frame.R;
import com.hy.frame.util.HyUtil;

/* loaded from: classes2.dex */
public abstract class BaseDialog extends Dialog implements View.OnClickListener {
    private IConfirmListener listener;
    private Object tag;

    /* loaded from: classes2.dex */
    public interface IConfirmListener {
        void onDlgConfirm(BaseDialog baseDialog, int i);
    }

    protected abstract void initData();

    protected abstract int initLayoutId();

    protected abstract void initView();

    protected abstract void initWindow();

    protected abstract void onViewClick(View view);

    public BaseDialog(Context context) {
        super(context, R.style.DialogTheme);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog setListener(IConfirmListener listener) {
        this.listener = listener;
        return this;
    }

    public IConfirmListener getListener() {
        return this.listener;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        initWindow();
        initView();
        initData();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (!HyUtil.isFastClick()) {
            onViewClick(v);
        }
    }

    protected void windowDeploy(float width, float height, int gravity) {
        windowDeploy(width, height, gravity, 0);
    }

    protected void windowDeploy(float width, float height, int gravity, int style) {
        Window window = getWindow();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        if (width == 0.0f) {
            params.width = -2;
        } else if (width <= 0.0f || width > 1.0f) {
            params.width = (int) width;
        } else {
            params.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * width);
        }
        if (height == 0.0f) {
            params.height = -2;
        } else if (height <= 0.0f || height > 1.0f) {
            params.height = (int) height;
        } else {
            params.height = (int) (getContext().getResources().getDisplayMetrics().heightPixels * height);
        }
        window.setAttributes(params);
        getWindow().setGravity(gravity);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        if (style != 0) {
            window.setWindowAnimations(style);
        }
    }

    protected <T> T getView(int resId) {
        return (T) findViewById(resId);
    }

    protected <T extends View> T getViewAndClick(@IdRes int id) {
        T v = (T) ((View) getView(id));
        v.setOnClickListener(this);
        return v;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !event.isTracking() || event.isCanceled()) {
            return false;
        }
        onBackPressed();
        return true;
    }

    protected void setOnClickListener(int resId) {
        findViewById(resId).setOnClickListener(this);
    }

    public Object getTag() {
        return this.tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
