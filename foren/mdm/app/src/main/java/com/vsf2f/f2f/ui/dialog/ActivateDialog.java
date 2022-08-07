package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.identify.IdentifyActivity;
import com.vsf2f.f2f.ui.pay.PayActivity;

/* loaded from: classes2.dex */
public class ActivateDialog extends BaseDialog {
    public ActivateDialog(Context context) {
        super(context);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_activate;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(0.8f, -2.0f, 17);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.active_real);
        setOnClickListener(R.id.active_ali);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.active_real /* 2131756201 */:
                getContext().startActivity(new Intent(getContext(), IdentifyActivity.class));
                return;
            case R.id.active_ali /* 2131756202 */:
                Bundle bundle = new Bundle();
                bundle.putString(Constant.FLAG, "0.01");
                bundle.putString(Constant.FLAG_TITLE, getContext().getString(R.string.active_recharge));
                bundle.putInt(Constant.FLAG_TYPE, 2);
                Intent intent = new Intent(getContext(), PayActivity.class);
                intent.putExtra(Constant.BUNDLE, bundle);
                getContext().startActivity(intent);
                return;
            default:
                return;
        }
    }
}
