package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.hy.frame.bean.ResultInfo;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class MyGoldBeansActivity extends BaseActivity {
    private final int ACTIVATE_PAY = 111;
    private final int WITHDRAWAL_ACT = 121;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_my_coins;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.mygoldcoin_title, 0);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        result.getRequestCode();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        result.getRequestCode();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        new Bundle();
        view.getId();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 111:
                    initData();
                    return;
                case 121:
                    requestData();
                    return;
                default:
                    return;
            }
        }
    }
}
