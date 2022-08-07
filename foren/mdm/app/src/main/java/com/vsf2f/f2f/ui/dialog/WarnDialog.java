package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class WarnDialog extends BaseDialog {
    private Button btnConfirm;
    private String confirm;
    private String content;
    private ImageView imgClose;
    private boolean showCancel;
    private TextView txtContent;

    public WarnDialog(Context context, String content) {
        this(context, content, null, false);
    }

    public WarnDialog(Context context, String content, String confirm, boolean showCancel) {
        this(context, content, confirm, showCancel, false, false);
    }

    public WarnDialog(Context context, String content, String confirm, boolean showCancel, boolean cancelable, boolean onTouchOutside) {
        super(context);
        this.content = content;
        this.confirm = confirm;
        this.showCancel = showCancel;
        setCancelable(cancelable);
        setCanceledOnTouchOutside(onTouchOutside);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_warn;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(0.8f, -2.0f, 17);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.imgClose = (ImageView) getViewAndClick(R.id.warn_imgClose);
        this.btnConfirm = (Button) getViewAndClick(R.id.warn_btnConfirm);
        this.txtContent = (TextView) getView(R.id.warn_txtContent);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        if (HyUtil.isNoEmpty(this.content)) {
            this.txtContent.setText(this.content);
        }
        if (HyUtil.isNoEmpty(this.confirm)) {
            this.btnConfirm.setText(this.confirm);
        }
        this.imgClose.setVisibility(this.showCancel ? 0 : 8);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.warn_txtContent /* 2131756362 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 1);
                    return;
                }
                return;
            case R.id.warn_imgClose /* 2131756376 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, -1);
                    return;
                }
                return;
            case R.id.warn_btnConfirm /* 2131756377 */:
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
