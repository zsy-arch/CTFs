package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class UpAppDialog extends BaseDialog {
    private boolean canCancel;
    private String content;
    private String version;

    public UpAppDialog(Context context, String version, String content, boolean canCancel) {
        super(context);
        this.version = version;
        this.content = content;
        this.canCancel = canCancel;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_upapp;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(0.8f, -2.0f, 17);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        TextView _txtTitle = (TextView) getView(R.id.upapp_txtTitle);
        TextView _txtContent = (TextView) getView(R.id.upapp_txtContent);
        _txtContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        setOnClickListener(R.id.upapp_btnConfirm);
        TextView _btnCancel = (TextView) getViewAndClick(R.id.upapp_btnCancel);
        if (!this.canCancel) {
            _btnCancel.setVisibility(8);
        }
        _txtTitle.setText("V" + this.version);
        _txtContent.setText(this.content);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.upapp_btnConfirm /* 2131756386 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
