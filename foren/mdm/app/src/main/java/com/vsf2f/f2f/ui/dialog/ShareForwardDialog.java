package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class ShareForwardDialog extends BaseDialog {
    public ShareForwardDialog(Context context) {
        super(context);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_share_forward;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.txt_cancel);
        setOnClickListener(R.id.share_txtCircle);
        setOnClickListener(R.id.share_txtWeChat);
        setOnClickListener(R.id.share_txtQQfd);
        setOnClickListener(R.id.share_txtQzone);
        setOnClickListener(R.id.share_txtVsf2f);
        setOnClickListener(R.id.share_txtCopylink);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.txt_cancle /* 2131756250 */:
            default:
                return;
            case R.id.share_txtCircle /* 2131756348 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 11);
                    return;
                }
                return;
            case R.id.share_txtWeChat /* 2131756349 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 12);
                    return;
                }
                return;
            case R.id.share_txtQQfd /* 2131756350 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 13);
                    return;
                }
                return;
            case R.id.share_txtQzone /* 2131756351 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 14);
                    return;
                }
                return;
            case R.id.share_txtVsf2f /* 2131756352 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 1);
                    return;
                }
                return;
            case R.id.share_txtCopylink /* 2131756353 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 2);
                    return;
                }
                return;
        }
    }
}
