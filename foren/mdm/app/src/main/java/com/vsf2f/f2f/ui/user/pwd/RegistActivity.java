package com.vsf2f.f2f.ui.user.pwd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.TimerButton;
import com.hy.http.AjaxParams;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.dialog.CountryDialog;
import com.vsf2f.f2f.ui.qrcode.QrcodeActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.captcha.Captcha;
import com.vsf2f.f2f.ui.utils.captcha.CaptchaListener;

/* loaded from: classes2.dex */
public class RegistActivity extends BaseActivity {
    private TextView agree;
    private AppShare appShare;
    private TimerButton btnGetCode;
    private Button btnLogin;
    private String countryCode;
    private EditText editInvite;
    private EditText editNick;
    private EditText editPhone;
    private EditText editPwd;
    private EditText editPwdffirm;
    private EditText editcode;
    private String guid;
    private ImageButton imbClearEdit;
    private ImageButton imbPwdShow;
    private ImageButton imbPwdShow2;
    private boolean isNextPage;
    private String lastPhone;
    private LinearLayout lly_headPage;
    private LinearLayout lly_nextPage;
    private Captcha mCaptcha = null;
    private UserLoginTask mLoginTask = null;
    CaptchaListener myCaptchaListener = new CaptchaListener() { // from class: com.vsf2f.f2f.ui.user.pwd.RegistActivity.4
        @Override // com.vsf2f.f2f.ui.utils.captcha.CaptchaListener
        public void onValidate(String result, String validate, String message) {
            if (validate.length() > 0) {
                MyLog.e("验证成功，validate = " + validate);
                RegistActivity.this.requestYZM();
                return;
            }
            MyLog.e("验证失败：result = " + result + ", validate = " + validate + ", message = " + message);
        }

        @Override // com.vsf2f.f2f.ui.utils.captcha.CaptchaListener
        public void closeWindow() {
            MyLog.e("关闭页面");
        }

        @Override // com.vsf2f.f2f.ui.utils.captcha.CaptchaListener
        public void onError(String errormsg) {
            MyLog.e("错误信息：" + errormsg);
        }

        @Override // com.vsf2f.f2f.ui.utils.captcha.CaptchaListener
        public void onCancel() {
            MyLog.e("取消线程");
            if (RegistActivity.this.mLoginTask != null && RegistActivity.this.mLoginTask.getStatus() == AsyncTask.Status.RUNNING) {
                MyLog.d("stop mLoginTask");
                RegistActivity.this.mLoginTask.cancel(true);
            }
        }

        @Override // com.vsf2f.f2f.ui.utils.captcha.CaptchaListener
        public void onReady(boolean ret) {
            if (ret) {
                MyLog.e("验证码sdk加载成功");
            }
        }
    };
    private String nick;
    private String phone;
    private TextView txtCountryCode;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_register;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        String guid;
        initHeaderBack(R.string.register, 0);
        this.editNick = (EditText) getView(R.id.regist_editNick);
        this.editPhone = (EditText) getView(R.id.regist_editPhone);
        this.editInvite = (EditText) getView(R.id.regist_editInvite);
        this.txtCountryCode = (TextView) getViewAndClick(R.id.regist_txtCountryCode);
        this.imbClearEdit = (ImageButton) getViewAndClick(R.id.regist_imbClearEdit);
        this.lly_headPage = (LinearLayout) getView(R.id.lly_headPage);
        this.lly_nextPage = (LinearLayout) getView(R.id.lly_nextPage);
        this.editPwdffirm = (EditText) getView(R.id.regist_editPwdffirm);
        this.btnGetCode = (TimerButton) getViewAndClick(R.id.regist_btnGetCode);
        this.editcode = (EditText) getView(R.id.regist_editCode);
        this.editPwd = (EditText) getView(R.id.regist_editPwd);
        this.agree = (TextView) getViewAndClick(R.id.regist_agree);
        this.agree.setSelected(true);
        this.imbPwdShow = (ImageButton) getViewAndClick(R.id.regist_imbPwdShow);
        this.imbPwdShow2 = (ImageButton) getViewAndClick(R.id.regist_imbPwdShow2);
        this.btnLogin = (Button) getViewAndClick(R.id.login_btnLogin);
        setOnClickListener(R.id.login_user);
        this.appShare = AppShare.get(this.context);
        setOnClickListener(R.id.regist_imgPhone);
        setOnClickListener(R.id.regist_btnConfirm);
        setOnClickListener(R.id.regist_imbQrcode);
        setOnClickListener(R.id.regist_tvClose);
        if (!(getBundle() == null || getBundle().getString(Constant.USER_GUID) == null || (guid = getBundle().getString(Constant.USER_GUID)) == null)) {
            this.editInvite.setText(guid);
            this.editInvite.setEnabled(false);
        }
        ImageView imageAdv = (ImageView) findViewById(R.id.regist_adv);
        String url = DemoHelper.getInstance().readConfig().getJoin_ad_url();
        if (TextUtils.isEmpty(url)) {
            url = getString(R.string.URL_REGIST_ADV);
        }
        ComUtil.display(this.context, imageAdv, url, R.drawable.ic_load_more);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.editNick.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.pwd.RegistActivity.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                RegistActivity.this.imbClearEdit.setVisibility(s.length() > 0 ? 0 : 4);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        boolean can = true;
        switch (v.getId()) {
            case R.id.regist_btnGetCode /* 2131755186 */:
                requestYZM();
                return;
            case R.id.login_btnLogin /* 2131755707 */:
                try {
                    register();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.regist_imbClearEdit /* 2131755794 */:
                if (this.editNick != null) {
                    this.editNick.getText().clear();
                    this.editNick.requestFocus();
                    return;
                }
                return;
            case R.id.regist_imgPhone /* 2131755795 */:
            case R.id.regist_txtCountryCode /* 2131755797 */:
                showCountryDialog();
                return;
            case R.id.regist_imbQrcode /* 2131755800 */:
                Bundle bundle = new Bundle();
                bundle.putBoolean("getcode", true);
                startActForResult(QrcodeActivity.class, bundle, 222);
                return;
            case R.id.regist_btnConfirm /* 2131755801 */:
                nextStep();
                return;
            case R.id.regist_tvClose /* 2131755802 */:
                finish();
                return;
            case R.id.regist_imbPwdShow /* 2131755805 */:
                if (this.imbPwdShow.isSelected()) {
                    this.editPwd.setInputType(129);
                    this.imbPwdShow.setSelected(false);
                } else {
                    this.editPwd.setInputType(144);
                    this.imbPwdShow.setSelected(true);
                }
                this.editPwd.setSelection(this.editPwd.length());
                return;
            case R.id.regist_imbPwdShow2 /* 2131755806 */:
                if (this.imbPwdShow2.isSelected()) {
                    this.editPwdffirm.setInputType(129);
                    this.imbPwdShow2.setSelected(false);
                } else {
                    this.editPwdffirm.setInputType(144);
                    this.imbPwdShow2.setSelected(true);
                }
                this.editPwdffirm.setSelection(this.editPwdffirm.length());
                return;
            case R.id.regist_agree /* 2131755807 */:
                if (this.agree.isSelected()) {
                    can = false;
                }
                this.agree.setSelected(can);
                this.btnLogin.setEnabled(can);
                return;
            case R.id.regist_agreement /* 2131755808 */:
            default:
                return;
        }
    }

    public void changeNextPage(boolean toNext) {
        if (toNext) {
            this.lly_headPage.setVisibility(8);
            this.lly_nextPage.setVisibility(0);
            this.lly_headPage.setAnimation(AnimationUtils.makeOutAnimation(this, false));
            this.lly_nextPage.setAnimation(AnimationUtils.makeInAnimation(this, false));
        } else {
            this.lly_headPage.setVisibility(0);
            this.lly_nextPage.setVisibility(8);
            this.lly_headPage.setAnimation(AnimationUtils.makeInAnimation(this, true));
            this.lly_nextPage.setAnimation(AnimationUtils.makeOutAnimation(this, true));
        }
        this.isNextPage = toNext;
    }

    private void nextStep() {
        this.phone = this.editPhone.getText().toString().trim();
        if (HyUtil.isEmpty(this.phone)) {
            MyToast.show(this, (int) R.string.toast_enter_phone_number);
            return;
        }
        this.guid = this.editInvite.getText().toString().trim();
        if (TextUtils.isEmpty(this.guid)) {
            this.guid = Constant.COM_SOURCE_USER;
        }
        this.nick = this.editNick.getText().toString().trim();
        if (!ComUtil.checkContent(this.nick, Constant.CONTENTMARTCH)) {
            MyToast.show(this, (int) R.string.no_martch);
            return;
        }
        if (!this.phone.equals(this.lastPhone)) {
            requestYZM();
        }
        changeNextPage(true);
        this.lastPhone = this.phone;
    }

    public void requestYZM() {
        this.btnGetCode.prepare();
        AjaxParams params = new AjaxParams();
        params.put("phone", this.phone);
        params.put("flag", "REG");
        params.put("countryCode", this.countryCode);
        params.put("timestamp", System.currentTimeMillis());
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().post(R.string.API_PHONE_YZM, ComUtil.getZCApi(this.context, getString(R.string.API_PHONE_YZM)), params);
    }

    private void register() throws Exception {
        String code = this.editcode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            MyToast.show(this, "请输入验证码");
            return;
        }
        String pwd = this.editPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            MyToast.show(this, "请输入密码");
            return;
        }
        String repwd = this.editPwdffirm.getText().toString().trim();
        if (TextUtils.isEmpty(repwd)) {
            MyToast.show(this, "请确认密码");
        } else if (!pwd.equals(repwd)) {
            MyToast.show(this, "两次密码输入不一致，请重新输入");
        } else if (pwd.length() < 6 || pwd.length() > 20) {
            MyToast.show(this, "密码长度为6到20位");
        } else {
            AjaxParams params = new AjaxParams();
            params.put("nickName", ComUtil.UTF(this.nick));
            params.put("userName", this.phone);
            params.put("phone", this.phone);
            params.put(Constant.PASSWORD, MD5.md5Encode(MD5.md5Encode(pwd)));
            params.put("guid", this.guid);
            params.put("verificationCode", code);
            params.put("countryCode", this.countryCode);
            getClient().setShowDialog(true);
            getClient().post(R.string.API_USER_REGISTER, ComUtil.getZCApi(this.context, getString(R.string.API_USER_REGISTER)), params, UserInfo.class);
        }
    }

    private void showCountryDialog() {
        new CountryDialog(this.context, new CountryDialog.ConfirmDlgListener() { // from class: com.vsf2f.f2f.ui.user.pwd.RegistActivity.2
            @Override // com.vsf2f.f2f.ui.dialog.CountryDialog.ConfirmDlgListener
            public void onCountrySelect(String string) {
                RegistActivity.this.txtCountryCode.setText(string);
                RegistActivity.this.countryCode = string;
            }
        }).show();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_PHONE_YZM /* 2131296388 */:
                this.btnGetCode.start(60);
                MyToast.show(this.context, result.getMsg());
                return;
            case R.string.API_USER_REGISTER /* 2131296472 */:
                this.btnGetCode.end();
                UserInfo info = (UserInfo) result.getObj();
                DemoHelper.getInstance().saveCurrentUserInfo(info);
                this.appShare.putString(Constant.COUNTRYCODE, info.getCountryCode());
                this.appShare.putString(com.hy.frame.util.Constant.USER_TOKEN, info.getAccessToken());
                this.appShare.putString("username", info.getUserName());
                this.appShare.putString(Constant.PASSWORD, info.getPassword());
                this.appShare.putString("avatar", "");
                EMClient.getInstance().login(info.getUserName(), info.getPassword(), new EMCallBack() { // from class: com.vsf2f.f2f.ui.user.pwd.RegistActivity.3
                    @Override // com.hyphenate.EMCallBack
                    public void onSuccess() {
                        MyLog.e(Integer.valueOf((int) R.string.em_login_success));
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onError(int i, String s) {
                        MyLog.e(Integer.valueOf((int) R.string.em_login_failed));
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onProgress(int i, String s) {
                    }
                });
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Constant.FLAG_ACT, Constant.ACT_REGIST);
                startActivity(intent);
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        if (result.getObj() != null) {
            switch (result.getRequestCode()) {
                case R.string.API_PHONE_YZM /* 2131296388 */:
                    this.btnGetCode.end();
                    onBackPressed();
                    return;
                case R.string.API_USER_REGISTER /* 2131296472 */:
                    String obj = result.getObj().toString();
                    char c = 65535;
                    switch (obj.hashCode()) {
                        case -501961764:
                            if (obj.equals("NotGuid")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 58953525:
                            if (obj.equals("UnMatchGuid")) {
                                c = 0;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                        case 1:
                            this.btnGetCode.end();
                            onBackPressed();
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
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

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String guid;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222 && resultCode == -1 && data.getExtras().getString(Constant.USER_GUID) != null && (guid = data.getExtras().getString(Constant.USER_GUID)) != null) {
            this.editInvite.setText(guid);
            this.editInvite.setEnabled(false);
        }
    }

    /* loaded from: classes2.dex */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        UserLoginTask() {
            RegistActivity.this = this$0;
        }

        public Boolean doInBackground(Void... params) {
            return Boolean.valueOf(RegistActivity.this.mCaptcha.checkParams());
        }

        public void onPostExecute(Boolean success) {
            if (success.booleanValue()) {
                RegistActivity.this.mCaptcha.Validate();
            } else {
                MyLog.e("验证码SDK参数设置错误,请检查配置");
            }
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            RegistActivity.this.mLoginTask = null;
        }
    }
}
