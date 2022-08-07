package com.vsf2f.f2f.ui.user.pwd;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.em.utils.UserShared;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.pay.PayPwdEditActivity;
import com.vsf2f.f2f.ui.pay.PayPwdResetActivity;

/* loaded from: classes2.dex */
public class ResetPwdActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_pwd;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        setOnClickListener(R.id.pwd_kvCashReset);
        setOnClickListener(R.id.pwd_kvLoginPwdReset);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        initHeaderBack(R.string.account_safe, 0);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.pwd_kvLoginPwdReset /* 2131755785 */:
                startAct(ChangePwdActivity.class);
                return;
            case R.id.pwd_kvCashReset /* 2131755786 */:
                int pwdType = UserShared.getInstance().readExtInfo().getPaypwdVersion();
                if (pwdType == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.FLAG, com.vsf2f.f2f.ui.utils.Constant.CHANGE_PAY_TYPE);
                    startAct(ChangePwdActivity.class, bundle);
                    return;
                } else if (pwdType == 1) {
                    startAct(PayPwdResetActivity.class);
                    return;
                } else if (pwdType == 2) {
                    startAct(PayPwdEditActivity.class);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }
}
