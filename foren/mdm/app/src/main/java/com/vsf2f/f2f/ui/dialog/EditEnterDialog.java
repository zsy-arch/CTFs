package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class EditEnterDialog extends BaseDialog {
    private EditText edit;
    private String hint;
    private setOnEditSendClickListener listener;

    /* loaded from: classes2.dex */
    public interface setOnEditSendClickListener {
        void onEditSendClickListener(String str);
    }

    public EditEnterDialog(Context context, setOnEditSendClickListener listener) {
        this(context, "请输入内容", listener);
    }

    public EditEnterDialog(Context context, String hint, setOnEditSendClickListener listener) {
        super(context);
        this.hint = hint;
        this.listener = listener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_edit;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        getWindow().setSoftInputMode(21);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.edit = (EditText) getView(R.id.dlg_editContent);
        this.edit.setHint(this.hint);
        setOnClickListener(R.id.dlg_btnOk);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        this.edit.requestFocus();
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.dlg_btnOk /* 2131756235 */:
                String s = this.edit.getText().toString().trim();
                if (TextUtils.isEmpty(s)) {
                    MyToast.show(getContext(), "请输入内容");
                    return;
                }
                view.setEnabled(false);
                this.listener.onEditSendClickListener(s);
                dismiss();
                return;
            default:
                return;
        }
    }
}
