package com.vsf2f.f2f.ui.pay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.easeui.widget.EaseAlertDialog;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.view.NumKeyView;
import com.vsf2f.f2f.ui.view.NumPwdView;

/* loaded from: classes2.dex */
public class PayPwdFindActivity extends BaseActivity implements Runnable {
    private String code;
    private Handler handler = new Handler();
    private boolean isNextPage;
    private LinearLayout lly_page1;
    private LinearLayout lly_page2;
    private NumKeyView mKeyView;
    private NumPwdView mPwdView;
    private NumPwdView mPwdView2;
    private int timer;
    private TextView tvTimer;
    private UserInfo userInfo;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_pay_pwd_find;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.find_pay_pwd, 0);
        this.lly_page1 = (LinearLayout) findViewById(R.id.find_paypwd_page1);
        this.lly_page2 = (LinearLayout) findViewById(R.id.find_paypwd_page2);
        this.tvTimer = (TextView) getViewAndClick(R.id.find_tvTimer);
        this.mPwdView = (NumPwdView) findViewById(R.id.pwdeditview);
        this.mPwdView2 = (NumPwdView) findViewById(R.id.pwdeditview2);
        this.mKeyView = (NumKeyView) findViewById(R.id.keyboardview);
        this.mKeyView.setOnKeyPressListener(new NumKeyView.OnKeyPressListener() { // from class: com.vsf2f.f2f.ui.pay.PayPwdFindActivity.1
            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onInertKey(String text) {
                if (PayPwdFindActivity.this.isNextPage) {
                    PayPwdFindActivity.this.mPwdView2.onInertKey(text);
                    return;
                }
                PayPwdFindActivity.this.mPwdView.onInertKey(text);
                if (PayPwdFindActivity.this.mPwdView.isFull()) {
                    PayPwdFindActivity.this.code = PayPwdFindActivity.this.mPwdView.getPwd();
                    PayPwdFindActivity.this.checkYZM(PayPwdFindActivity.this.code);
                    PayPwdFindActivity.this.mPwdView.clearPwd();
                }
            }

            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onDeleteKey() {
                if (PayPwdFindActivity.this.isNextPage) {
                    PayPwdFindActivity.this.mPwdView2.onDeleteKey();
                } else {
                    PayPwdFindActivity.this.mPwdView.onDeleteKey();
                }
            }
        });
        setOnClickListener(R.id.find_tvCommit);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (!isLogin()) {
            return;
        }
        if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
            new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.pay.PayPwdFindActivity.2
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    if (confirmed) {
                        PayPwdFindActivity.this.startActForResult(BindPhoneActivity.class, 134);
                    } else {
                        PayPwdFindActivity.this.finish();
                    }
                }
            }, true).show();
        } else {
            initPhone();
        }
    }

    public void initPhone() {
        this.userInfo = getUserInfo();
        if (HyUtil.isNoEmpty(this.userInfo.getPhone())) {
            ((TextView) findViewById(R.id.find_tvPhone)).setText(HyUtil.hideMobile(this.userInfo.getPhone()));
            ((TextView) findViewById(R.id.find_tvPhone2)).setText(HyUtil.hideMobile(this.userInfo.getPhone()));
        }
        requestYZM();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void checkYZM(String code) {
        AjaxParams params = new AjaxParams();
        params.put("phone", this.userInfo.getPhone());
        params.put("flag", "FORGOT_PW");
        params.put("countryCode", this.userInfo.getCountryCode());
        params.put("verificationCode", code);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_CHECK_YZM, ComUtil.getZCApi(this.context, getString(R.string.API_CHECK_YZM)), params, String.class);
    }

    public void requestPwd(String code) {
        String newpwd = this.mPwdView2.getPwd();
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        try {
            params.put("phone", this.userInfo.getPhone());
            params.put("countryCode", this.userInfo.getCountryCode());
            params.put("verificationCode", code);
            params.put("pwdType", "pay");
            params.put(Constant.PASSWORD, MD5.md5Encode(MD5.md5Encode(newpwd)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        getClient().post(R.string.API_USER_FIND_PWD2, ComUtil.getZCApi(this.context, getString(R.string.API_USER_FIND_PWD2, new Object[]{"pay"})), params, String.class);
    }

    private void requestYZM() {
        AjaxParams params = new AjaxParams();
        params.put("phone", this.userInfo.getPhone());
        params.put("flag", "FORGOT_PW");
        params.put("countryCode", this.userInfo.getCountryCode());
        params.put("timestamp", System.currentTimeMillis());
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().setShowDialog(true);
        getClient().post(R.string.API_PHONE_YZM, ComUtil.getZCApi(this.context, getString(R.string.API_PHONE_YZM)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CHECK_YZM /* 2131296306 */:
                this.code = (String) result.getObj();
                changeNextPage(true);
                return;
            case R.string.API_PHONE_YZM /* 2131296388 */:
                start();
                MyToast.show(this, result.getMsg());
                return;
            case R.string.API_USER_FIND_PWD2 /* 2131296466 */:
                MyToast.show(this, "密码找回成功");
                try {
                    UserShared.getInstance().savePaypwdVersion();
                    ComUtil.setPaypwd(this.context, MD5.md5Encode(MD5.md5Encode(this.mPwdView2.getPwd())));
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

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        switch (result.getRequestCode()) {
            case R.string.API_PHONE_YZM /* 2131296388 */:
                update(0);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.isNextPage) {
            changeNextPage(false);
        } else {
            finish();
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.find_tvTimer /* 2131755492 */:
                if (this.timer == 0) {
                    requestYZM();
                    return;
                }
                return;
            case R.id.find_tvCommit /* 2131755497 */:
                requestPwd(this.code);
                return;
            default:
                return;
        }
    }

    public void changeNextPage(boolean toNext) {
        if (toNext) {
            this.lly_page1.setVisibility(8);
            this.lly_page2.setVisibility(0);
            this.lly_page1.setAnimation(AnimationUtils.makeOutAnimation(this, false));
            this.lly_page2.setAnimation(AnimationUtils.makeInAnimation(this, false));
        } else {
            this.lly_page1.setVisibility(0);
            this.lly_page2.setVisibility(8);
            this.lly_page1.setAnimation(AnimationUtils.makeInAnimation(this, true));
            this.lly_page2.setAnimation(AnimationUtils.makeOutAnimation(this, true));
        }
        this.isNextPage = toNext;
    }

    public void start() {
        this.timer = 60;
        this.handler.post(this);
    }

    private void update(int time) {
        if (time == 0) {
            this.tvTimer.setText(Html.fromHtml("收不到验证码？<font color='#3B94F9'>重发短信</font>"));
        } else {
            this.tvTimer.setText(Html.fromHtml("<font color='#3B94F9'>" + time + "s后重发</font>"));
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.timer--;
        update(this.timer);
        if (this.timer > 0) {
            this.handler.postDelayed(this, 1000L);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 134) {
            return;
        }
        if (resultCode == -1) {
            initPhone();
        } else {
            finish();
        }
    }
}
