package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class ThirdRegistDialog extends BaseDialog {
    private EditText editCode;
    private EditText editNick;
    private ThirdRegistListener listener;
    private String nick;

    /* loaded from: classes2.dex */
    public interface ThirdRegistListener {
        void onRegistListener(String str, String str2);
    }

    public ThirdRegistDialog(Context context, String nick, ThirdRegistListener listener) {
        super(context);
        this.nick = nick;
        this.listener = listener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_register_third;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.editNick = (EditText) getView(R.id.regist_editNick);
        this.editCode = (EditText) getView(R.id.regist_editCode);
        setOnClickListener(R.id.regist_btnConfirm);
        setOnClickListener(R.id.regist_imgClose);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        if (this.nick != null) {
            this.editNick.setText(this.nick);
            this.editCode.requestFocus();
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.regist_btnConfirm /* 2131755801 */:
                this.nick = this.editNick.getText().toString().trim();
                if (!ComUtil.checkContent(this.nick, Constant.CONTENTMARTCH)) {
                    MyToast.show(getContext(), (int) R.string.no_martch);
                    return;
                }
                String code = this.editCode.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    code = Constant.COM_SOURCE_USER;
                }
                if (this.listener != null) {
                    this.listener.onRegistListener(this.nick, code);
                    return;
                }
                return;
            case R.id.regist_imgClose /* 2131756334 */:
                dismiss();
                return;
            default:
                return;
        }
    }
}
