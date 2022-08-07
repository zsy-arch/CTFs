package com.vsf2f.f2f.ui.pay;

import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.view.NumKeyView;
import com.vsf2f.f2f.ui.view.NumPwdView;

/* loaded from: classes2.dex */
public class PayPwdResetActivity extends BaseActivity {
    private NumPwdView mPwdView;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_pay_pwd_reset;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.pay_pwd_set, 0);
        this.mPwdView = (NumPwdView) findViewById(R.id.pwdeditview);
        ((NumKeyView) findViewById(R.id.keyboardview)).setOnKeyPressListener(new NumKeyView.OnKeyPressListener() { // from class: com.vsf2f.f2f.ui.pay.PayPwdResetActivity.1
            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onInertKey(String text) {
                PayPwdResetActivity.this.mPwdView.onInertKey(text);
            }

            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onDeleteKey() {
                PayPwdResetActivity.this.mPwdView.onDeleteKey();
            }
        });
        setOnClickListener(R.id.find_tvCommit);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    public void initPhone() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestPwd(String newPwd) {
        AjaxParams params = new AjaxParams();
        params.put("id", DemoHelper.getInstance().getCurrentUserId());
        params.put("pwdType", "Pay");
        params.put("paypwdVersion", "2");
        try {
            params.put(Constant.PASSWORD, MD5.md5Encode(MD5.md5Encode("123456")));
            params.put("newPassword", MD5.md5Encode(MD5.md5Encode(newPwd)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_CHANGE_PWD, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_PWD)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_USER_CHANGE_PWD /* 2131296463 */:
                MyToast.show(this, "密码设置成功");
                try {
                    UserShared.getInstance().savePaypwdVersion();
                    ComUtil.setPaypwd(this.context, MD5.md5Encode(MD5.md5Encode(this.mPwdView.getPwd())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setResult(-1);
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.find_tvCommit /* 2131755497 */:
                requestPwd(this.mPwdView.getPwd());
                return;
            default:
                return;
        }
    }
}
