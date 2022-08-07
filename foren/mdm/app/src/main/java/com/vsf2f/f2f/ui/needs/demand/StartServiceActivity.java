package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.qrcode.QrcodeActivity;

/* loaded from: classes2.dex */
public class StartServiceActivity extends BaseActivity {
    private EditText et_code;
    private boolean isService;
    private int orderId;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_start_service;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.tab_doservice, 0);
        this.et_code = (EditText) getView(R.id.et_code);
        setOnClickListener(R.id.btn_ensure);
        setOnClickListener(R.id.imb_qrcode);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.orderId = getBundle().getInt("id");
        getBundle(getBundle());
    }

    public void getBundle(Bundle bundle) {
        if (this.orderId == 0) {
            this.orderId = bundle.getInt("id");
        }
        this.isService = bundle.getBoolean("isService");
        String code = bundle.getString("code");
        if (!TextUtils.isEmpty(code)) {
            this.et_code.setText(code);
            startService();
        }
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
            case R.id.btn_ensure /* 2131755180 */:
                startService();
                return;
            case R.id.imb_qrcode /* 2131755557 */:
                startAct(QrcodeActivity.class);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
    }

    private void startService() {
        String code = this.et_code.getText().toString();
        if (TextUtils.isEmpty(code)) {
            MyToast.show(getApp(), "请输入验证码");
            return;
        }
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("validCode", code);
        if (!this.isService) {
            params.put("shareOrderId", this.orderId);
            getClient().post(R.string.API_CHECKVALID_CODE, ComUtil.getXDDApi(this.context, getString(R.string.API_CHECKVALID_CODE)), params, Boolean.class);
            return;
        }
        params.put("shareServiceOrderId", this.orderId);
        getClient().post(R.string.API_SERVICE_CHECKVALIDCODE, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_CHECKVALIDCODE)), params, Boolean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        MyToast.show(getApp(), "开始服务成功");
        finish();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getBundle(intent.getExtras().getBundle(Constant.BUNDLE));
    }
}
