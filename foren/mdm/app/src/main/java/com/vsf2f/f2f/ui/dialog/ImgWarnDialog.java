package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;

/* loaded from: classes2.dex */
public class ImgWarnDialog extends BaseDialog {
    private String btn;
    private boolean finish;
    private int img;
    private String title;

    public ImgWarnDialog(Context context, @DrawableRes int img, String title, String btn) {
        super(context);
        this.img = img;
        this.btn = btn;
        this.title = title;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseDialog, android.app.Dialog
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_img_warn;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy((ScreenUtils.widthPixels(getContext()) * 4) / 5, 0.0f, 17, R.style.AnimHead);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        ((ImageView) findViewById(R.id.iv_top)).setImageResource(this.img);
        ((TextView) findViewById(R.id.tv_title)).setText(this.title);
        ((TextView) getViewAndClick(R.id.tv_sure)).setText(this.btn);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    public ImgWarnDialog setDisListener(@Nullable DialogInterface.OnDismissListener listener) {
        super.setOnDismissListener(listener);
        return this;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sure /* 2131756083 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 0);
                }
                dismiss();
                return;
            default:
                return;
        }
    }
}
