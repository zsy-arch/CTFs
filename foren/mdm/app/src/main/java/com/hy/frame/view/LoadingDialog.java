package com.hy.frame.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.hy.frame.R;
import com.hy.frame.common.BaseDialog;

/* loaded from: classes2.dex */
public class LoadingDialog extends BaseDialog {
    private String loadMsg;
    private TextView txtLoadMsg;

    public LoadingDialog(Context context) {
        this(context, null);
    }

    public LoadingDialog(Context context, String loadMsg) {
        super(context);
        this.loadMsg = loadMsg;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_loading;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-2.0f, -2.0f, 17);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.txtLoadMsg = (TextView) getView(R.id.loading_txtLoadMsg);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        if (this.loadMsg != null) {
            this.txtLoadMsg.setText(this.loadMsg);
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
    }

    @Override // android.app.Dialog
    public void show() {
        if (this.txtLoadMsg != null) {
            if (this.loadMsg != null) {
                this.txtLoadMsg.setText(this.loadMsg);
                this.loadMsg = null;
            } else {
                this.txtLoadMsg.setText(R.string.loading);
            }
        }
        super.show();
    }

    public void setLoadMsg(String loadMsg) {
        this.loadMsg = loadMsg;
    }
}
