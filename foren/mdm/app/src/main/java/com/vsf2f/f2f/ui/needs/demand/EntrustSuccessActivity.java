package com.vsf2f.f2f.ui.needs.demand;

import android.view.View;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.BaseActivity;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class EntrustSuccessActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_entrust_success;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.pay_success, 0);
        String price = getBundle().getString(f.aS);
        String time = getBundle().getString(f.az);
        String typeStr = "";
        switch (getBundle().getInt("type")) {
            case 0:
                typeStr = "支付宝支付";
                break;
            case 1:
                typeStr = "微信支付";
                break;
            case 2:
                typeStr = "其他支付";
                break;
        }
        ((TextView) getView(R.id.EntrustSuccess_txtPrice)).setText(price);
        ((TextView) getView(R.id.EntrustSuccess_txtType)).setText(typeStr);
        ((TextView) getView(R.id.EntrustSuccess_txtTime)).setText(time);
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
    }
}
