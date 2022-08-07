package com.vsf2f.f2f.ui.pay;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.user.MoneyAccountActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.web.WebUtils;

/* loaded from: classes2.dex */
public class PayCenterActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_pay_center;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.tab_pay_center, 0);
        setOnClickListener(R.id.pay_center_kvRecord);
        setOnClickListener(R.id.pay_center_kvManager);
        setOnClickListener(R.id.pay_center_kvWithManager);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
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
            case R.id.pay_center_kvRecord /* 2131755487 */:
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constant.FLAG2, false);
                bundle.putString(Constant.FLAG_TITLE, getString(R.string.cash_detail));
                bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, getString(R.string.WITHDRAWAL_HISTORY)));
                startAct(WebKitLocalActivity.class, bundle);
                return;
            case R.id.pay_center_kvManager /* 2131755488 */:
                startAct(PaySettingActivity.class);
                return;
            case R.id.pay_center_kvWithManager /* 2131755489 */:
                startAct(MoneyAccountActivity.class);
                return;
            default:
                return;
        }
    }
}
