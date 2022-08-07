package com.vsf2f.f2f.ui.user.pwd;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class RegistTestActivity extends BaseActivity {
    private AppShare AppShare;
    private EditText editAccount;
    private EditText editCode;
    private EditText editPwd;
    private EditText editPwdffirm;
    private TextView tv_code;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_test_register;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.regist_test, 0);
        this.editAccount = (EditText) getView(R.id.regist_editAccount);
        this.editCode = (EditText) getView(R.id.regist_editCode);
        this.editPwd = (EditText) getView(R.id.regist_editPwd);
        this.editPwdffirm = (EditText) getView(R.id.regist_editPwdffirm);
        this.tv_code = (TextView) getView(R.id.tv_code);
        setOnClickListener(R.id.login_btnLogin);
        setOnClickListener(R.id.login_user);
        AppShare appShare = this.AppShare;
        this.AppShare = AppShare.get(this.context);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.tv_code.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.vsf2f.f2f.ui.user.pwd.RegistTestActivity.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                RegistTestActivity.this.editCode.setVisibility(0);
                return true;
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
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.login_user /* 2131755706 */:
            default:
                return;
            case R.id.login_btnLogin /* 2131755707 */:
                try {
                    regist();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
        }
    }

    private void regist() throws Exception {
        String account = this.editAccount.getText().toString().trim();
        if (account.trim().length() == 0) {
            MyToast.show(this, "请输入账号");
            return;
        }
        String pwd = this.editPwd.getText().toString();
        if (pwd.trim().length() == 0) {
            MyToast.show(this, "请输入密码");
            return;
        }
        String guid = this.editCode.getText().toString().trim();
        if (TextUtils.isEmpty(guid)) {
            guid = "13794478697";
        }
        String confirmPwd = this.editPwdffirm.getText().toString().trim();
        if (confirmPwd.trim().length() == 0) {
            MyToast.show(this, "请确认密码");
        } else if (!pwd.trim().equals(confirmPwd.trim())) {
            MyToast.show(this, "密码输入不一致，请重新输入");
        } else {
            AjaxParams params = new AjaxParams();
            params.put("userName", account);
            params.put("phone", account);
            params.put(Constant.PASSWORD, MD5.md5Encode(MD5.md5Encode(pwd)));
            params.put("guid", guid);
            params.put("timestamp", System.currentTimeMillis());
            params.put("nonceStr", ComUtil.getRandomString(32));
            params.put("sign", ComUtil.encryptParam(params));
            getClient().setShowDialog(true);
            getClient().post(R.string.API_TEST_REGIST, ComUtil.getZCApi(this.context, getString(R.string.API_TEST_REGIST)), params, UserInfo.class);
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_TEST_REGIST /* 2131296444 */:
                UserInfo info = (UserInfo) result.getObj();
                this.AppShare.putString(com.hy.frame.util.Constant.USER_TOKEN, info.getAccessToken());
                this.AppShare.putString("username", info.getUserName());
                this.AppShare.putString(Constant.PASSWORD, info.getPassword());
                startAct(LoginActivity.class);
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
            String obj = result.getObj().toString();
            char c = 65535;
            switch (obj.hashCode()) {
                case -1885142534:
                    if (obj.equals("InvalidVFTCode")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1295344731:
                    if (obj.equals("UnMatchVFTCode")) {
                        c = 4;
                        break;
                    }
                    break;
                case -720715546:
                    if (obj.equals("phone_exist")) {
                        c = 0;
                        break;
                    }
                    break;
                case -501961764:
                    if (obj.equals("NotGuid")) {
                        c = 2;
                        break;
                    }
                    break;
                case 50983271:
                    if (obj.equals("UnMatchPassWord")) {
                        c = 6;
                        break;
                    }
                    break;
                case 58953525:
                    if (obj.equals("UnMatchGuid")) {
                        c = 1;
                        break;
                    }
                    break;
                case 195029406:
                    if (obj.equals("NotVFTCode")) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                    startAct(RegistActivity.class);
                    finish();
                    return;
                default:
                    return;
            }
        }
    }
}
