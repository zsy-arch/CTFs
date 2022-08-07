package com.vsf2f.f2f.ui.pay;

import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.user.WithdrawalActivity;
import com.vsf2f.f2f.ui.utils.FingerUtil;

/* loaded from: classes2.dex */
public class PayScucessActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_pay_scucess;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.pay_success, 0);
        setOnClickListener(R.id.btn_finish);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        ((TextView) findViewById(R.id.pay_scucess_money)).setText(getBundle().getString("money"));
        getApp().removeFinish(WithdrawalActivity.class);
        FingerUtil.showOpenFinger(this.context, null);
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
            case R.id.btn_finish /* 2131755568 */:
                finish();
                return;
            default:
                return;
        }
    }
}
