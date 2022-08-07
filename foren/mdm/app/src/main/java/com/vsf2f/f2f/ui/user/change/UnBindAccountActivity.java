package com.vsf2f.f2f.ui.user.change;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.view.NumKeyView;
import com.vsf2f.f2f.ui.view.NumPwdView;

/* loaded from: classes2.dex */
public class UnBindAccountActivity extends BaseActivity implements Runnable {
    private Handler handler = new Handler();
    private NumPwdView mPwdView;
    private int timer;
    private TextView tvTimer;
    private String type;
    private UserInfo userInfo;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_bind_account_cancel;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.bind_account_cancel, 0);
        this.tvTimer = (TextView) getViewAndClick(R.id.unbind_tvTimer);
        this.mPwdView = (NumPwdView) findViewById(R.id.pwdeditview);
        ((NumKeyView) findViewById(R.id.keyboardview)).setOnKeyPressListener(new NumKeyView.OnKeyPressListener() { // from class: com.vsf2f.f2f.ui.user.change.UnBindAccountActivity.1
            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onInertKey(String text) {
                UnBindAccountActivity.this.mPwdView.onInertKey(text);
                if (UnBindAccountActivity.this.mPwdView.isFull()) {
                    UnBindAccountActivity.this.requestCode(UnBindAccountActivity.this.mPwdView.getPwd());
                    UnBindAccountActivity.this.mPwdView.clearPwd();
                }
            }

            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onDeleteKey() {
                UnBindAccountActivity.this.mPwdView.onDeleteKey();
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (isLogin()) {
            if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.change.UnBindAccountActivity.2
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            UnBindAccountActivity.this.startActForResult(BindPhoneActivity.class, 0);
                        }
                        UnBindAccountActivity.this.finish();
                    }
                }, true).show();
            } else {
                this.userInfo = getUserInfo();
                if (HyUtil.isNoEmpty(this.userInfo.getPhone())) {
                    ((TextView) findViewById(R.id.unbind_tvPhone)).setText(this.userInfo.getPhone());
                }
                requestYZM();
            }
        }
        this.type = getBundle().getString("type");
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestCode(String code) {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("phone", this.userInfo.getPhone());
        params.put("countryCode", this.userInfo.getCountryCode());
        params.put("verificationCode", code);
        getClient().post(R.string.API_USER_BIND_CANCEL, ComUtil.getZCApi(this.context, getString(R.string.API_USER_BIND_CANCEL, new Object[]{this.type})), params, String.class);
    }

    private void requestYZM() {
        AjaxParams params = new AjaxParams();
        params.put("phone", this.userInfo.getPhone());
        params.put("flag", "RELIEVE_ACCOUNT");
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
            case R.string.API_PHONE_YZM /* 2131296388 */:
                start();
                MyToast.show(this, result.getMsg());
                return;
            case R.string.API_USER_BIND_CANCEL /* 2131296457 */:
                MyToast.show(this, "解绑成功");
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

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.unbind_tvTimer /* 2131755169 */:
                if (this.timer == 0) {
                    requestYZM();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void start() {
        this.timer = 60;
        this.handler.post(this);
    }

    private void update(int time) {
        if (time == 0) {
            this.tvTimer.setText("收不到验证码？重发短信");
        } else {
            this.tvTimer.setText(time + "s后重发");
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
}
