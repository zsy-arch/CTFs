package com.vsf2f.f2f.ui.user.pwd;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.pay.PayPwdFindActivity;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class ChangePwdActivity extends BaseActivity {
    private EditText editAgainPwd;
    private EditText editNewPwd;
    private EditText editOldPwd;
    private TextView longtxtpwd;
    private String type;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_pwd_update;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        setOnClickListener(R.id.pwd_update_txtForget);
        this.editOldPwd = (EditText) getView(R.id.pwd_update_editOldPwd);
        this.editNewPwd = (EditText) getView(R.id.pwd_update_editNewPwd);
        this.editAgainPwd = (EditText) getView(R.id.pwd_update_editAgainPwd);
        this.longtxtpwd = (TextView) getView(R.id.txt_longtxtpwd);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.type = Constant.CHANGE_LOGIN_TYPE;
        if (getBundle() != null) {
            this.type = getBundle().getString(com.hy.frame.util.Constant.FLAG, Constant.CHANGE_LOGIN_TYPE);
        }
        if (Constant.CHANGE_LOGIN_TYPE.equals(this.type)) {
            initHeaderBackTxt(R.string.user_reset_login_pwd, R.string.confirm);
            this.longtxtpwd.setVisibility(4);
        } else if (this.type.equals(Constant.CHANGE_PAY_TYPE)) {
            initHeaderBackTxt(R.string.user_cash_pwd, R.string.confirm);
            this.longtxtpwd.setVisibility(0);
        }
    }

    public void getData() {
        String oldPwd = this.editOldPwd.getText().toString();
        if (HyUtil.isEmpty(oldPwd)) {
            MyToast.show(this.context, this.editOldPwd.getHint().toString());
        } else if (oldPwd.length() < 6) {
            MyToast.show(this.context, getString(R.string.pwd_hint_prompt));
        } else {
            String newPwd = this.editNewPwd.getText().toString();
            if (oldPwd.equals(newPwd)) {
                MyToast.show(this.context, (int) R.string.pwd_the_same);
            } else if (HyUtil.isEmpty(newPwd)) {
                MyToast.show(this.context, this.editNewPwd.getHint().toString());
            } else if (newPwd.length() < 6 || newPwd.length() > 20) {
                MyToast.show(this, "密码长度为6到20位");
            } else if (HyUtil.isChinese(newPwd)) {
                MyToast.show(this.context, getString(R.string.pwd_chinese));
            } else {
                String againPwd = this.editAgainPwd.getText().toString();
                if (HyUtil.isEmpty(againPwd)) {
                    MyToast.show(this.context, this.editAgainPwd.getHint().toString());
                } else if (!TextUtils.equals(againPwd, newPwd)) {
                    MyToast.show(this.context, (int) R.string.pwd_not_match);
                } else {
                    AjaxParams params = new AjaxParams();
                    params.put("id", DemoHelper.getInstance().getCurrentUserId());
                    if (this.type.equals(Constant.CHANGE_LOGIN_TYPE)) {
                        params.put("pwdType", "Login");
                    } else if (this.type.equals(Constant.CHANGE_PAY_TYPE)) {
                        if (newPwd.length() != 6) {
                            MyToast.show(this, "请输入6位数字新密码");
                            return;
                        } else {
                            params.put("pwdType", "Pay");
                            params.put("paypwdVersion", "2");
                        }
                    }
                    try {
                        params.put(Constant.PASSWORD, MD5.md5Encode(MD5.md5Encode(oldPwd)));
                        params.put("newPassword", MD5.md5Encode(MD5.md5Encode(newPwd)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    getClient().setShowDialog(true);
                    getClient().post(R.string.API_USER_CHANGE_PWD, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_PWD)), params, String.class);
                }
            }
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
            case R.id.pwd_update_txtForget /* 2131755791 */:
                if (this.type.equals(Constant.CHANGE_LOGIN_TYPE)) {
                    startActForResult(FindPwdActivity.class, 999);
                    return;
                } else if (this.type.equals(Constant.CHANGE_PAY_TYPE)) {
                    startActForResult(PayPwdFindActivity.class, 999);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        getData();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        MyToast.show(this.context, "修改密码成功");
        if (this.type.equals(Constant.CHANGE_LOGIN_TYPE)) {
            if (isLogin()) {
                AppShare.get(this.context).putString(Constant.PASSWORD, null);
                DemoHelper.getInstance().logout(null);
            }
            DemoHelper.getInstance().logout(null);
            startActClear(LoginActivity.class);
        } else if (this.type.equals(Constant.CHANGE_PAY_TYPE)) {
            UserShared.getInstance().savePaypwdVersion();
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 999) {
            finish();
        }
    }
}
