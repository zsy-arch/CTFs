package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class ImgViewDialog extends BaseDialog {
    private int gravity;
    private int resId;
    private int top = 0;
    private int left = 0;
    private int right = 0;
    private int bottom = 0;

    public ImgViewDialog(Context context, @DrawableRes int resId, int gravity) {
        super(context);
        this.resId = resId;
        this.gravity = gravity;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_imgview;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-2.0f, -2.0f, this.gravity);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    public ImgViewDialog setPadding(int left, int top, int right, int bottom) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        return this;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        if (this.gravity != 17) {
            ((FrameLayout) findViewById(R.id.parent)).setPadding(this.left, this.top, this.right, this.bottom);
        }
        ((ImageView) getViewAndClick(R.id.imgview)).setImageResource(this.resId);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
    }
}
