package com.vsf2f.f2f.ui.user.change;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.TimerButton;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.CountryDialog;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class BindPhoneActivity extends BaseActivity {
    private TimerButton btnGetCode;
    private String countryCode = "86";
    private EditText editCode;
    private EditText editPhone;
    private String phone;
    private TextView txtCountryCode;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_bind_phone;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.bind_phone, R.string.ensure);
        this.editPhone = (EditText) getView(R.id.bind_editPhone);
        this.editCode = (EditText) getView(R.id.bind_editCode);
        this.btnGetCode = (TimerButton) getViewAndClick(R.id.bind_btnGetCode);
        this.txtCountryCode = (TextView) getViewAndClick(R.id.bind_txtCountryCode);
        setOnClickListener(R.id.bind_imgPhone);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    private void requestYZM() {
        this.phone = this.editPhone.getText().toString().trim();
        if (HyUtil.isEmpty(this.phone)) {
            MyToast.show(this, (int) R.string.toast_enter_phone_number);
            return;
        }
        this.btnGetCode.prepare();
        AjaxParams params = new AjaxParams();
        params.put("phone", this.phone);
        params.put("flag", "CERT");
        params.put("countryCode", this.countryCode);
        params.put("timestamp", System.currentTimeMillis());
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().post(R.string.API_PHONE_YZM, ComUtil.getZCApi(this.context, getString(R.string.API_PHONE_YZM)), params);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        this.phone = this.editPhone.getText().toString().trim();
        if (HyUtil.isEmpty(this.phone)) {
            MyToast.show(this, (int) R.string.phone_hint);
            return;
        }
        String code = this.editCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            MyToast.show(this, (int) R.string.code_hint);
            return;
        }
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("phone", this.phone);
        params.put("countryCode", this.countryCode);
        params.put("verificationCode", code);
        params.put("timestamp", System.currentTimeMillis());
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().post(R.string.API_USER_CERT_MOBILE, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CERT_MOBILE)), params, String.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bind_imgPhone /* 2131755173 */:
            case R.id.bind_txtCountryCode /* 2131755175 */:
                showCountryDialog();
                return;
            case R.id.bind_btnGetCode /* 2131755177 */:
                requestYZM();
                return;
            case R.id.login_user /* 2131755706 */:
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_PHONE_YZM /* 2131296388 */:
                if (result.getErrorCode() == 0) {
                    this.btnGetCode.start(60);
                }
                MyToast.show(this.context, result.getMsg());
                return;
            case R.string.API_USER_CERT_MOBILE /* 2131296460 */:
                MyToast.show(this.context, "绑定手机成功！");
                UserShared.getInstance().save("phone", this.phone);
                UserShared.getInstance().save(Constant.CERT_MOBILE, 1);
                AppShare.get(this.context).putString(Constant.COUNTRYCODE, this.countryCode);
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
    }

    private void showCountryDialog() {
        new CountryDialog(this.context, new CountryDialog.ConfirmDlgListener() { // from class: com.vsf2f.f2f.ui.user.change.BindPhoneActivity.1
            @Override // com.vsf2f.f2f.ui.dialog.CountryDialog.ConfirmDlgListener
            public void onCountrySelect(String string) {
                BindPhoneActivity.this.txtCountryCode.setText(string);
                BindPhoneActivity.this.countryCode = string;
            }
        }).show();
    }
}
