package com.vsf2f.f2f.ui.user.pwd;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationManagerCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.sys.a;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.IDUtil;
import com.cdlinglu.utils.MD5;
import com.easeui.EaseConstant;
import com.em.DemoHelper;
import com.em.db.DemoDBManager;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.LoadingDialog;
import com.hy.http.AjaxParams;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.open.GameAppOperation;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ThirdPartyBean;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.dialog.CountryDialog;
import com.vsf2f.f2f.ui.dialog.ThirdRegistDialog;
import com.vsf2f.f2f.ui.utils.Base64Helper;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.ali.AuthResult;
import com.vsf2f.f2f.wxapi.WXEntryActivity;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LoginActivity extends BaseActivity {
    private static final int SDK_AUTH_FLAG = 1;
    private String QQopenid;
    private AppShare appShare;
    private ThirdRegistDialog editGoodDialog;
    private EditText editPassword;
    private EditText editUsername;
    private ImageButton imbClearEdit;
    private ImageButton imbPwdShow;
    private LoadingDialog loadDlg;
    private IUiListener loginListener;
    private Tencent mTencent;
    private String pwdMD5;
    private ThirdPartyBean thirdParty;
    private TextView txtCountryCode;
    private IUiListener userInfoListener;
    private String username;
    private String strCountryCode = "86";
    private BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), WXEntryActivity.ACTION_SHARE_SUCCESS) && intent.getExtras() != null) {
                try {
                    JSONObject jsonObject = new JSONObject(intent.getStringExtra(Constant.FLAG));
                    LoginActivity.this.thirdParty = new ThirdPartyBean();
                    LoginActivity.this.thirdParty.setOpenId(jsonObject.optString(GameAppOperation.GAME_UNION_ID));
                    LoginActivity.this.thirdParty.setPicUrl(jsonObject.optString("headimgurl"));
                    LoginActivity.this.thirdParty.setNickName(jsonObject.optString(EaseConstant.EXTRA_NICK_NAME));
                    LoginActivity.this.thirdParty.setPartyType(2);
                    LoginActivity.this.loginThird(LoginActivity.this.thirdParty);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @SuppressLint({"HandlerLeak"})
    private Handler mHandler = new Handler() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.9
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    AuthResult authResult = new AuthResult((Map) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        String authcode = authResult.getAuthCode();
                        String userid = authResult.getUserId();
                        authResult.getAlipayOpenId();
                        LoginActivity.this.thirdParty = new ThirdPartyBean();
                        LoginActivity.this.thirdParty.setPartyType(4);
                        LoginActivity.this.thirdParty.setUserId(userid);
                        LoginActivity.this.thirdParty.setOpenId(userid);
                        LoginActivity.this.thirdParty.setAuthCode(authcode);
                        LoginActivity.this.loginThird(LoginActivity.this.thirdParty);
                        return;
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        Toast.makeText(LoginActivity.this.context, LoginActivity.this.getString(R.string.be_canceled), 0).show();
                        return;
                    } else {
                        Toast.makeText(LoginActivity.this.context, "授权失败", 0).show();
                        return;
                    }
                default:
                    return;
            }
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_login2;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        this.editPassword = (EditText) getView(R.id.login_editPwd);
        this.editUsername = (EditText) getView(R.id.login_editUserName);
        this.txtCountryCode = (TextView) getView(R.id.login_txtCountryCode);
        this.imbClearEdit = (ImageButton) getViewAndClick(R.id.login_imbClearEdit);
        this.imbPwdShow = (ImageButton) getViewAndClick(R.id.login_imbPwdShow);
        setOnClickListener(R.id.login_txtCountryCode);
        setOnClickListener(R.id.login_imgPhone);
        setOnClickListener(R.id.login_btnLogin);
        setOnClickListener(R.id.login_txtResetPwd);
        setOnClickListener(R.id.login_txtRegist);
        setOnClickListener(R.id.login_ibnWechat);
        setOnClickListener(R.id.login_ibnQQ);
        setOnClickListener(R.id.login_ibnALI);
        setOnClickListener(R.id.login_ibnTwitter);
        setOnClickListener(R.id.login_ibnFacebook);
        getView(R.id.login_testRegist).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                return false;
            }
        });
        this.appShare = AppShare.get(this.context);
        initLogin();
    }

    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {
                prestr.append(key).append("=").append(value);
            } else {
                prestr.append(key).append("=").append(value).append(a.b);
            }
        }
        return prestr.toString();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.username = this.appShare.getString("username");
        if (!TextUtils.isEmpty(this.username)) {
            this.imbClearEdit.setVisibility(0);
            this.editUsername.setText(this.username);
            this.pwdMD5 = this.appShare.getString(com.vsf2f.f2f.ui.utils.Constant.PASSWORD);
            if (!TextUtils.isEmpty(this.pwdMD5)) {
                this.editPassword.setText("123456");
            }
            this.editPassword.requestFocus();
            String code = this.appShare.getString(com.vsf2f.f2f.ui.utils.Constant.COUNTRYCODE);
            if (!TextUtils.isEmpty(code)) {
                this.txtCountryCode.setText(code);
                this.strCountryCode = code;
            }
        }
        this.editUsername.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.2
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LoginActivity.this.imbClearEdit.setVisibility(s.length() > 0 ? 0 : 4);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.editPassword.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.3
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (LoginActivity.this.pwdMD5 != null) {
                    LoginActivity.this.pwdMD5 = null;
                    LoginActivity.this.editPassword.setText("");
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    private void showLoadDlg(boolean isShow) {
        if (this.loadDlg == null) {
            if (isShow) {
                this.loadDlg = new LoadingDialog(this.context);
            } else {
                return;
            }
        }
        if (isShow) {
            this.loadDlg.show();
        } else {
            this.loadDlg.dismiss();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.login_btnLogin /* 2131755707 */:
                try {
                    requestLogin();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.login_imgPhone /* 2131755767 */:
            case R.id.login_txtCountryCode /* 2131755768 */:
                showCountryDialog();
                return;
            case R.id.login_imbClearEdit /* 2131755770 */:
                if (this.editUsername != null) {
                    this.editUsername.getText().clear();
                    this.editUsername.requestFocus();
                }
                if (this.editPassword != null) {
                    this.editPassword.getText().clear();
                    return;
                }
                return;
            case R.id.login_imbPwdShow /* 2131755772 */:
                if (this.pwdMD5 != null) {
                    this.pwdMD5 = null;
                    this.editPassword.setText("");
                }
                if (this.imbPwdShow.isSelected()) {
                    this.editPassword.setInputType(129);
                    this.imbPwdShow.setSelected(false);
                } else {
                    this.editPassword.setInputType(144);
                    this.imbPwdShow.setSelected(true);
                }
                this.editPassword.setSelection(this.editPassword.length());
                return;
            case R.id.login_txtRegist /* 2131755773 */:
                startAct(RegistActivity.class);
                return;
            case R.id.login_txtResetPwd /* 2131755774 */:
                Bundle bundle = new Bundle();
                bundle.putString(Constant.FLAG, com.vsf2f.f2f.ui.utils.Constant.CHANGE_LOGIN_TYPE);
                startActForResult(FindPwdActivity.class, bundle, 999);
                return;
            case R.id.login_ibnWechat /* 2131755776 */:
                loginWx();
                return;
            case R.id.login_ibnQQ /* 2131755777 */:
                loginQQ();
                return;
            case R.id.login_ibnALI /* 2131755778 */:
                loginAli();
                return;
            case R.id.login_ibnTwitter /* 2131755779 */:
                MyToast.show(this, "暂不支持");
                return;
            case R.id.login_ibnFacebook /* 2131755780 */:
                MyToast.show(this, "暂不支持");
                return;
            default:
                return;
        }
    }

    private void initLogin() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WXEntryActivity.ACTION_SHARE_SUCCESS);
        filter.setPriority(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
        registerReceiver(this.receiver, filter);
    }

    private void showCountryDialog() {
        new CountryDialog(this.context, new CountryDialog.ConfirmDlgListener() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.4
            @Override // com.vsf2f.f2f.ui.dialog.CountryDialog.ConfirmDlgListener
            public void onCountrySelect(String string) {
                LoginActivity.this.txtCountryCode.setText(string);
                LoginActivity.this.strCountryCode = string;
            }
        }).show();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_ALI_THREE_LOGIN /* 2131296290 */:
                final String orderInfo = "";
                try {
                    orderInfo = Base64Helper.decode((String) result.getObj(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.6
                    @Override // java.lang.Runnable
                    public void run() {
                        Map<String, String> result2 = new AuthTask(LoginActivity.this).authV2(orderInfo, true);
                        Message msg = new Message();
                        msg.obj = result2;
                        msg.what = 1;
                        LoginActivity.this.mHandler.sendMessage(msg);
                    }
                });
                return;
            case R.string.API_USER_LOGIN /* 2131296468 */:
                afterLogin((UserInfo) result.getObj());
                return;
            case R.string.API_USER_THIRD_FINISH /* 2131296475 */:
                this.editGoodDialog.dismiss();
                afterLogin((UserInfo) result.getObj());
                return;
            case R.string.API_USER_THIRD_LOGIN /* 2131296476 */:
                UserInfo userInfo = (UserInfo) result.getObj();
                MyLog.e("OauthResult = " + userInfo.getOauthResult());
                if ("UnBind".equals(userInfo.getOauthResult())) {
                    getClient().setShowDialog(false);
                    this.editGoodDialog = new ThirdRegistDialog(this.context, this.thirdParty.getNickName(), new ThirdRegistDialog.ThirdRegistListener() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.5
                        @Override // com.vsf2f.f2f.ui.dialog.ThirdRegistDialog.ThirdRegistListener
                        public void onRegistListener(String nick, String code) {
                            LoginActivity.this.requestAfterThird(nick, code);
                        }
                    });
                    this.editGoodDialog.show();
                    return;
                }
                afterLogin(userInfo);
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.pwdMD5 = null;
    }

    public void requestAfterThird(String nick, String code) {
        AjaxParams params = new AjaxParams();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nickName", ComUtil.UTF(nick));
            if (TextUtils.isEmpty(code)) {
                code = com.vsf2f.f2f.ui.utils.Constant.COM_SOURCE_USER;
            }
            jsonObject.put("guid", ComUtil.UTF(code));
            jsonObject.put("thirdParty", this.thirdParty.toJson());
            getClient().setShowDialog(R.string.is_logging);
            getClient().post(R.string.API_USER_THIRD_FINISH, ComUtil.getZCApi(this.context, getString(R.string.API_USER_THIRD_FINISH)), jsonObject.toString(), params, UserInfo.class, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestLogin() throws Exception {
        this.username = this.editUsername.getText().toString().trim();
        if (HyUtil.isEmpty(this.username)) {
            MyToast.show(this.context, ((Object) this.editUsername.getHint()) + getString(R.string.empty_not));
            return;
        }
        if (HyUtil.isChinese(this.username)) {
            MyToast.show(this.context, ((Object) this.editUsername.getHint()) + getString(R.string.name_rule));
        }
        if (this.username.length() > 20) {
            MyToast.show(this.context, getString(R.string.name_hint_prompt));
            return;
        }
        if (this.pwdMD5 == null) {
            String pwd = this.editPassword.getText().toString().trim();
            if (HyUtil.isEmpty(pwd)) {
                MyToast.show(this.context, this.editPassword.getHint().toString());
                return;
            } else if (pwd.length() < 6) {
                MyToast.show(this.context, getString(R.string.pwd_hint_prompt));
                return;
            } else {
                this.pwdMD5 = MD5.md5Encode(MD5.md5Encode(pwd));
            }
        }
        AjaxParams params = new AjaxParams();
        params.put("countryCode", this.strCountryCode);
        params.put("userName", this.username);
        params.put(com.vsf2f.f2f.ui.utils.Constant.PASSWORD, this.pwdMD5);
        params.put("timestamp", System.currentTimeMillis());
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().setShowDialog(R.string.is_logging);
        getClient().post(R.string.API_USER_LOGIN, ComUtil.getZCApi(this.context, getString(R.string.API_USER_LOGIN)), params, UserInfo.class);
    }

    private void afterLogin(UserInfo info) {
        boolean z;
        int i = 1;
        DemoDBManager.getInstance().closeDB();
        DemoHelper.getInstance().saveCurrentUserInfo(info);
        this.appShare.putString(Constant.USER_TOKEN, info.getAccessToken());
        this.appShare.putString("username", info.getUserName());
        this.appShare.putString(com.vsf2f.f2f.ui.utils.Constant.PASSWORD, info.getPassword());
        this.appShare.putString(com.vsf2f.f2f.ui.utils.Constant.COUNTRYCODE, info.getCountryCode());
        this.appShare.putString("avatar", info.getUserPic().getSpath());
        if (this.appShare.getBoolean("tree")) {
            AppShare appShare = this.appShare;
            if (!info.getUserExtendInfo().isOpenManor()) {
                z = true;
            } else {
                z = false;
            }
            appShare.putBoolean("tree", z);
        }
        DemoHelper.getInstance().setCurrentNickName(info.getNickName());
        EMClient.getInstance().login(info.getUserName(), info.getPassword(), new EMCallBack() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.7
            @Override // com.hyphenate.EMCallBack
            public void onSuccess() {
                MyLog.e(Integer.valueOf((int) R.string.em_login_success));
                EMClient.getInstance().chatManager().loadAllConversations();
            }

            @Override // com.hyphenate.EMCallBack
            public void onError(int i2, String s) {
                MyLog.e(LoginActivity.this.getString(R.string.em_login_failed) + ":" + s);
            }

            @Override // com.hyphenate.EMCallBack
            public void onProgress(int i2, String s) {
            }
        });
        DemoHelper instance = DemoHelper.getInstance();
        if (info.getSeller() == null) {
            i = -1;
        }
        instance.setOpenStore(i);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(com.vsf2f.f2f.ui.utils.Constant.FLAG_ACT, com.vsf2f.f2f.ui.utils.Constant.ACT_LOGIN);
        startActClear(intent, MainActivity.class);
        finish();
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        showLoadDlg(false);
        super.onResume();
    }

    public void loginThird(ThirdPartyBean thirdParty) {
        AjaxParams params = new AjaxParams();
        if (thirdParty != null) {
            MyLog.e(thirdParty.toString());
            getClient().setShowDialog(true);
            params.put("openId", thirdParty.getOpenId());
            params.put("userId", thirdParty.getUserId());
            params.put("partyType", thirdParty.getPartyType());
            params.put("picUrl", ComUtil.UTF(thirdParty.getPicUrl()));
            params.put("nickName", ComUtil.UTF(thirdParty.getNickName()));
            params.put("authCode", ComUtil.UTF(thirdParty.getAuthCode()));
            getClient().post(R.string.API_USER_THIRD_LOGIN, ComUtil.getZCApi(this.context, getString(R.string.API_USER_THIRD_LOGIN)), params, UserInfo.class);
        }
    }

    private void loginWx() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        if (MyApplication.wxApi.isWXAppInstalled()) {
            MyApplication.wxApi.sendReq(req);
            showLoadDlg(true);
            return;
        }
        MyToast.show(this.context, (int) R.string.no_install_wx);
    }

    private void loginAli() {
        getClient().get(R.string.API_ALI_THREE_LOGIN, ComUtil.getZCApi(this.context, getString(R.string.API_ALI_THREE_LOGIN)), new AjaxParams(), String.class);
    }

    public void loginQQ() {
        try {
            this.mTencent = Tencent.createInstance(IDUtil.QQ_ID, this);
            if (this.mTencent == null || !this.mTencent.isSupportSSOLogin(this)) {
                MyToast.show(this.context, (int) R.string.no_install_qq);
            } else {
                initQqListener();
                showLoadDlg(true);
                this.mTencent.login(this, com.vsf2f.f2f.ui.utils.Constant.ALL, this.loginListener);
            }
        } catch (Exception e) {
            showLoadDlg(false);
        }
    }

    private void initQqListener() {
        this.loginListener = new IUiListener() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.10
            @Override // com.tencent.tauth.IUiListener
            public void onComplete(Object o) {
                Toast.makeText(LoginActivity.this.context, "授权成功", 0).show();
                JSONObject object = (JSONObject) o;
                try {
                    LoginActivity.this.QQopenid = object.getString("openid");
                    String accessToken = object.getString(Constants.PARAM_ACCESS_TOKEN);
                    String expires = object.getString(Constants.PARAM_EXPIRES_IN);
                    LoginActivity.this.mTencent.setOpenId(LoginActivity.this.QQopenid);
                    LoginActivity.this.mTencent.setAccessToken(accessToken, expires);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.tencent.tauth.IUiListener
            public void onError(UiError uiError) {
                Toast.makeText(LoginActivity.this.context, "授权失败", 0).show();
                Log.e("LoginError:", uiError.toString());
            }

            @Override // com.tencent.tauth.IUiListener
            public void onCancel() {
                Toast.makeText(LoginActivity.this.context, LoginActivity.this.getString(R.string.be_canceled), 0).show();
            }
        };
        this.userInfoListener = new IUiListener() { // from class: com.vsf2f.f2f.ui.user.pwd.LoginActivity.11
            @Override // com.tencent.tauth.IUiListener
            public void onComplete(Object o) {
                if (o != null) {
                    try {
                        JSONObject qq = (JSONObject) o;
                        String nickName = qq.getString(EaseConstant.EXTRA_NICK_NAME);
                        String figureurl = qq.getString("figureurl");
                        LoginActivity.this.thirdParty = new ThirdPartyBean();
                        LoginActivity.this.thirdParty.setPartyType(1);
                        LoginActivity.this.thirdParty.setOpenId(LoginActivity.this.QQopenid);
                        LoginActivity.this.thirdParty.setNickName(nickName);
                        LoginActivity.this.thirdParty.setPicUrl(figureurl);
                        LoginActivity.this.loginThird(LoginActivity.this.thirdParty);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.tencent.tauth.IUiListener
            public void onError(UiError uiError) {
            }

            @Override // com.tencent.tauth.IUiListener
            public void onCancel() {
            }
        };
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        startAct(MainActivity.class);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.REQUEST_LOGIN /* 11101 */:
                Tencent.onActivityResultData(requestCode, resultCode, data, this.loginListener);
                if (resultCode == -1 && this.mTencent != null) {
                    new com.tencent.connect.UserInfo(this, this.mTencent.getQQToken()).getUserInfo(this.userInfoListener);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
