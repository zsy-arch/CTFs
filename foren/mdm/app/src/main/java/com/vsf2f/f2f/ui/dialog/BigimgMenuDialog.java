package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class BigimgMenuDialog extends BaseDialog {
    private ConfirmDlgListener listener;

    /* loaded from: classes2.dex */
    public interface ConfirmDlgListener {
        void onDlgCancelClick(BigimgMenuDialog bigimgMenuDialog);

        void onDlgSavePhotoClick(BigimgMenuDialog bigimgMenuDialog);
    }

    public BigimgMenuDialog(Context context) {
        super(context);
    }

    public void init(ConfirmDlgListener listener) {
        this.listener = listener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_pic_save;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.save_btnPhoto);
        setOnClickListener(R.id.set_btnCancel);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.save_btnPhoto /* 2131756322 */:
                if (this.listener != null) {
                    this.listener.onDlgSavePhotoClick(this);
                    return;
                }
                return;
            case R.id.set_btnCancel /* 2131756323 */:
                if (this.listener != null) {
                    this.listener.onDlgCancelClick(this);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
