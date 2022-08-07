package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class MoreOperateDialog extends BaseDialog {
    private boolean btnReport;
    private boolean btnShare;
    private boolean btnShare2;

    public MoreOperateDialog(Context context) {
        super(context);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_more;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.more_btnCancel);
        if (this.btnShare) {
            getViewAndClick(R.id.more_btnShare).setVisibility(0);
        }
        if (this.btnShare2) {
            getViewAndClick(R.id.more_btnShare2).setVisibility(0);
        }
        if (this.btnReport) {
            getViewAndClick(R.id.more_btnReport).setVisibility(0);
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    public void showBtnReport() {
        this.btnReport = true;
    }

    public void showBtnShare() {
        this.btnShare = true;
    }

    public void showBtnShare2() {
        this.btnShare2 = true;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        if (v.getId() != R.id.more_btnCancel && getListener() != null) {
            getListener().onDlgConfirm(this, v.getId());
        }
    }
}
