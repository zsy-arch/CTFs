package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class ShareCodeDialog extends BaseDialog {
    public ShareCodeDialog(Context context) {
        super(context);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_share_qrcode;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.share_btnCancel);
        setOnClickListener(R.id.share_btnSave);
        setOnClickListener(R.id.share_btnCopy);
        setOnClickListener(R.id.share_btnCircle);
        setOnClickListener(R.id.share_btnWx);
        setOnClickListener(R.id.share_btnQQfd);
        setOnClickListener(R.id.share_btnQzone);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.share_btnCancel /* 2131756361 */:
                return;
            default:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, v.getId());
                    return;
                }
                return;
        }
    }
}
