package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class ExitGroupDialog extends BaseDialog {
    private boolean isCreator;

    public ExitGroupDialog(Context context, boolean isCreator) {
        super(context);
        this.isCreator = isCreator;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.em_logout_actionsheet;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        Button exitBtn = (Button) getViewAndClick(R.id.btn_exit);
        TextView text = (TextView) getView(R.id.tv_text);
        setOnClickListener(R.id.txt_cancel);
        if (this.isCreator) {
            exitBtn.setText(R.string.dismiss_group);
            text.setText(R.string.dissolution_group_hint);
            return;
        }
        exitBtn.setText(R.string.exit_group);
        text.setText(R.string.exit_group_hint);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.txt_cancel /* 2131756354 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, -1);
                    return;
                }
                return;
            case R.id.btn_exit /* 2131756656 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 1);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
