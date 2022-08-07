package com.vsf2f.f2f.ui.user.pwd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.TimerButton;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.CountryDialog;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class FindPwdActivity extends BaseActivity {
    private TimerButton btnGetCode;
    private EditText editcode;
    private EditText editensurepwd;
    private EditText editnewpwd;
    private EditText editphone;
    private ImageButton imbPwdShow1;
    private ImageButton imbPwdShow2;
    private boolean isCurrent;
    private String strCountryCode = "86";
    private TextView txtCountryCode;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_find_pwd;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.find_login_pwd, 0);
        setOnClickListener(R.id.find_pwd_btnConfirm);
        setOnClickListener(R.id.find_pwd_imgPhone);
        this.editphone = (EditText) getView(R.id.find_pwd_edtPhone);
        this.editcode = (EditText) getView(R.id.find_pwd_edtCode);
        this.editnewpwd = (EditText) getView(R.id.find_pwd_edtNewPwd);
        this.editensurepwd = (EditText) getView(R.id.find_pwd_edtAffirmPwd);
        this.btnGetCode = (TimerButton) getViewAndClick(R.id.find_pwd_btnGetCode);
        this.txtCountryCode = (TextView) getViewAndClick(R.id.find_pwd_txtPhone);
        this.imbPwdShow1 = (ImageButton) getViewAndClick(R.id.findpwd_imbPwdShow1);
        this.imbPwdShow2 = (ImageButton) getViewAndClick(R.id.findpwd_imbPwdShow2);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (isLogin()) {
            if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.pwd.FindPwdActivity.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            FindPwdActivity.this.startActForResult(BindPhoneActivity.class, 0);
                        }
                        FindPwdActivity.this.finish();
                    }
                }, true).show();
            } else if (HyUtil.isNoEmpty(getUserInfo().getPhone())) {
                this.editphone.setText(getUserInfo().getPhone());
                this.editphone.setEnabled(false);
            }
        }
        String code = AppShare.get(this.context).getString(Constant.COUNTRYCODE);
        if (!TextUtils.isEmpty(code)) {
            this.txtCountryCode.setText(code);
            this.strCountryCode = code;
        }
    }

    private void requestYZM() {
        String phone = this.editphone.getText().toString();
        if (HyUtil.isEmpty(phone)) {
            MyToast.show(this, (int) R.string.toast_enter_phone_number);
            return;
        }
        if (isLogin() && phone.equals(getUserInfo().getPhone())) {
            this.isCurrent = true;
        }
        this.btnGetCode.prepare();
        AjaxParams params = new AjaxParams();
        params.put("phone", phone);
        params.put("flag", "FORGOT_PW");
        params.put("countryCode", this.strCountryCode);
        params.put("timestamp", System.currentTimeMillis());
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().setShowDialog(true);
        getClient().post(R.string.API_PHONE_YZM, ComUtil.getZCApi(this.context, getString(R.string.API_PHONE_YZM)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_PHONE_YZM /* 2131296388 */:
                this.btnGetCode.start(60);
                MyToast.show(this, result.getMsg());
                return;
            case R.string.API_USER_FIND_PWD /* 2131296465 */:
                MyToast.show(this, "密码找回成功");
                if (this.isCurrent) {
                    AppShare.get(this.context).putString(Constant.PASSWORD, null);
                    DemoHelper.getInstance().logout(null);
                    startAct(LoginActivity.class);
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
                if (result.getObj() != null) {
                    this.btnGetCode.end();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        String phone = this.editphone.getText().toString();
        if (HyUtil.isEmpty(phone)) {
            MyToast.show(this, (int) R.string.toast_enter_phone_number);
            return;
        }
        String code = this.editcode.getText().toString();
        if (HyUtil.isEmpty(code)) {
            MyToast.show(this, "请输入验证码");
            return;
        }
        String newpwd = this.editnewpwd.getText().toString();
        if (HyUtil.isEmpty(newpwd)) {
            MyToast.show(this, "请输入新密码");
            return;
        }
        String oldpwd = this.editensurepwd.getText().toString();
        if (HyUtil.isEmpty(oldpwd)) {
            MyToast.show(this, "请确认密码");
        } else if (!newpwd.equals(oldpwd)) {
            MyToast.show(this, "请输入相同的密码");
        } else if (newpwd.length() < 6 || newpwd.length() > 20) {
            MyToast.show(this, "密码长度为6到20位");
        } else {
            getClient().setShowDialog(true);
            AjaxParams params = new AjaxParams();
            params.put("phone", phone);
            params.put("countryCode", this.strCountryCode);
            params.put("verificationCode", code);
            try {
                params.put(Constant.PASSWORD, MD5.md5Encode(MD5.md5Encode(newpwd)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            params.put("pwdType", "login");
            getClient().post(R.string.API_USER_FIND_PWD, ComUtil.getZCApi(this.context, getString(R.string.API_USER_FIND_PWD, new Object[]{"login"})), params, String.class);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.find_pwd_imgPhone /* 2131755756 */:
            case R.id.find_pwd_txtPhone /* 2131755757 */:
                showCountryDialog();
                return;
            case R.id.find_pwd_edtPhone /* 2131755758 */:
            case R.id.find_pwd_edtCode /* 2131755760 */:
            case R.id.find_pwd_edtNewPwd /* 2131755762 */:
            case R.id.find_pwd_edtAffirmPwd /* 2131755764 */:
            default:
                return;
            case R.id.findpwd_imbClearEdit /* 2131755759 */:
                this.editphone.setText("");
                this.editphone.requestFocus();
                return;
            case R.id.find_pwd_btnGetCode /* 2131755761 */:
                requestYZM();
                return;
            case R.id.findpwd_imbPwdShow1 /* 2131755763 */:
                if (this.imbPwdShow1.isSelected()) {
                    this.imbPwdShow1.setSelected(false);
                    this.editnewpwd.setInputType(129);
                } else {
                    this.imbPwdShow1.setSelected(true);
                    this.editnewpwd.setInputType(144);
                }
                this.editnewpwd.setSelection(this.editnewpwd.length());
                return;
            case R.id.findpwd_imbPwdShow2 /* 2131755765 */:
                if (this.imbPwdShow2.isSelected()) {
                    this.imbPwdShow2.setSelected(false);
                    this.editensurepwd.setInputType(129);
                } else {
                    this.imbPwdShow2.setSelected(true);
                    this.editensurepwd.setInputType(144);
                }
                this.editensurepwd.setSelection(this.editensurepwd.length());
                return;
            case R.id.find_pwd_btnConfirm /* 2131755766 */:
                requestData();
                return;
        }
    }

    private void showCountryDialog() {
        new CountryDialog(this.context, new CountryDialog.ConfirmDlgListener() { // from class: com.vsf2f.f2f.ui.user.pwd.FindPwdActivity.2
            @Override // com.vsf2f.f2f.ui.dialog.CountryDialog.ConfirmDlgListener
            public void onCountrySelect(String string) {
                FindPwdActivity.this.txtCountryCode.setText(string);
                FindPwdActivity.this.strCountryCode = string;
            }
        }).show();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 0) {
            initData();
        }
    }
}
