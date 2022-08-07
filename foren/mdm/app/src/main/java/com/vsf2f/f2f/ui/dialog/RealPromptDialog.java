package com.vsf2f.f2f.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.identify.IdentifyActivity;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;

/* loaded from: classes2.dex */
public class RealPromptDialog extends BaseDialog {
    private Activity activity;
    private String content;
    private String title;
    private boolean toStay;
    private TextView tv_msg;
    private TextView tv_title;

    public RealPromptDialog(Context context, String content) {
        this(context, "", content);
    }

    public RealPromptDialog(Context context, String title, String content) {
        super(context);
        this.toStay = true;
        this.title = title;
        this.content = content;
    }

    public RealPromptDialog notToStay(Activity activity) {
        this.activity = activity;
        this.toStay = false;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseDialog, android.app.Dialog
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_real_prompt;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.tv_msg.setText(this.content);
        if (!TextUtils.isEmpty(this.title)) {
            this.tv_title.setText(this.title);
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy((ScreenUtils.widthPixels(getContext()) * 4) / 5, 0.0f, 17, R.style.AnimHead);
        setCancelable(this.toStay);
        setCanceledOnTouchOutside(this.toStay);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.tv_msg = (TextView) findViewById(R.id.tv_msg);
        setOnClickListener(R.id.ll_parent);
        setOnClickListener(R.id.ll_dlg);
        setOnClickListener(R.id.btn_sure);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        if (!this.toStay && this.activity != null) {
            this.activity.finish();
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure /* 2131756169 */:
                dismiss();
                getContext().startActivity(new Intent(getContext(), IdentifyActivity.class));
                return;
            case R.id.ll_parent /* 2131756231 */:
                dismiss();
                return;
            case R.id.ll_dlg /* 2131756257 */:
            default:
                return;
        }
    }
}
