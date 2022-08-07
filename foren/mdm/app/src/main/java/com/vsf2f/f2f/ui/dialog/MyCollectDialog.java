package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class MyCollectDialog extends BaseDialog {
    private boolean isShowEdit;
    private boolean isShowShare;

    public MyCollectDialog(Context context, boolean isShowShare, boolean isShowEdit) {
        super(context);
        this.isShowShare = isShowShare;
        this.isShowEdit = isShowEdit;
    }

    public boolean check(boolean showShare, boolean showEdit) {
        return this.isShowShare == showShare && this.isShowEdit == showEdit;
    }

    public boolean checkNo(boolean showShare, boolean showEdit) {
        return !check(showShare, showEdit);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_mycollect;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        int i = 0;
        TextView txtEdit = (TextView) getViewAndClick(R.id.collect_dlg_btnEdit);
        LinearLayout llyShare = (LinearLayout) getView(R.id.collect_dlg_llyShare);
        setOnClickListener(R.id.collect_dlg_btnCancel);
        setOnClickListener(R.id.collect_dlg_btnDelete);
        setOnClickListener(R.id.collect_dlg_btnCircle);
        setOnClickListener(R.id.collect_dlg_btnWx);
        setOnClickListener(R.id.collect_dlg_btnQQfd);
        setOnClickListener(R.id.collect_dlg_btnQzone);
        setOnClickListener(R.id.collect_dlg_btnVsf2f);
        setOnClickListener(R.id.collect_dlg_btnCopylink);
        llyShare.setVisibility(this.isShowShare ? 0 : 8);
        if (!this.isShowEdit) {
            i = 8;
        }
        txtEdit.setVisibility(i);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.collect_dlg_btnCircle /* 2131756298 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 11);
                    return;
                }
                return;
            case R.id.collect_dlg_btnWx /* 2131756299 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 12);
                    return;
                }
                return;
            case R.id.collect_dlg_btnQQfd /* 2131756300 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 13);
                    return;
                }
                return;
            case R.id.collect_dlg_btnQzone /* 2131756301 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 14);
                    return;
                }
                return;
            case R.id.collect_dlg_btnVsf2f /* 2131756302 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 1);
                    return;
                }
                return;
            case R.id.collect_dlg_btnCopylink /* 2131756303 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 2);
                    return;
                }
                return;
            case R.id.collect_dlg_btnDelete /* 2131756304 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 22);
                    return;
                }
                return;
            case R.id.collect_dlg_btnEdit /* 2131756305 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 21);
                    return;
                }
                return;
            case R.id.collect_dlg_btnCancel /* 2131756306 */:
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
