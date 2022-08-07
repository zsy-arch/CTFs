package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.cdlinglu.utils.ComUtil;
import com.em.utils.UserShared;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity;
import com.vsf2f.f2f.ui.needs.service.ServicePublishActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;

/* loaded from: classes2.dex */
public class DiscoverDialog extends BaseDialog {
    public DiscoverDialog(Context context) {
        super(context);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_discover_add;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-2.0f, -2.0f, 8388693, R.style.AnimRight);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.add_demand);
        setOnClickListener(R.id.add_service);
        setOnClickListener(R.id.ll_parent);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        if (ComUtil.isNoLogin(getContext())) {
            MyToast.show(getContext(), (int) R.string.login_hint);
            getContext().startActivity(new Intent(getContext(), LoginActivity.class));
        } else if (UserShared.getInstance().getIsVerifyState(getContext())) {
            switch (v.getId()) {
                case R.id.add_demand /* 2131756232 */:
                    getContext().startActivity(new Intent(getContext(), DemandPublishActivity.class));
                    return;
                case R.id.add_service /* 2131756233 */:
                    getContext().startActivity(new Intent(getContext(), ServicePublishActivity.class));
                    return;
                default:
                    return;
            }
        }
    }
}
