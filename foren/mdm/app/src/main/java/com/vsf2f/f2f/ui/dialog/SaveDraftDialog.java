package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class SaveDraftDialog extends BaseDialog {
    public SaveDraftDialog(Context context, BaseDialog.IConfirmListener listener) {
        super(context);
        setListener(listener);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_save_draft;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.draft_btnNot);
        setOnClickListener(R.id.draft_btnSave);
        setOnClickListener(R.id.draft_btnCancel);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.draft_btnSave /* 2131756338 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 1);
                    return;
                }
                return;
            case R.id.draft_btnNot /* 2131756339 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, -1);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
