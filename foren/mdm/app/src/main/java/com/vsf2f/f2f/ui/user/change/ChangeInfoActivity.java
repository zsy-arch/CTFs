package com.vsf2f.f2f.ui.user.change;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.FlagUtil;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.TimerButton;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.dialog.CountryDialog;

/* loaded from: classes2.dex */
public class ChangeInfoActivity extends BaseActivity {
    private TimerButton btnGetCode;
    private UserInfo detail;
    private EditText editText;
    private EditText editcode;
    private String flag;
    private LinearLayout ll_code;
    private String strCountryCode = "86";
    private TextView txtPrompt;
    private TextView txtTitle;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_change_phone;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        if (getBundle() == null) {
            finish();
        }
        this.detail = (UserInfo) getBundle().getParcelable(Constant.FLAG);
        this.flag = getBundle().getString(Constant.FLAG2);
        if (this.detail == null) {
            this.detail = getUserInfo();
        }
        this.txtTitle = (TextView) getViewAndClick(R.id.change_txtTitle);
        this.txtPrompt = (TextView) getViewAndClick(R.id.change_txtPrompt);
        this.editText = (EditText) getView(R.id.change_editText);
        this.editcode = (EditText) getView(R.id.regist_editCode);
        this.ll_code = (LinearLayout) getView(R.id.ll_code);
        this.btnGetCode = (TimerButton) getViewAndClick(R.id.regist_btnGetCode);
        updateUI();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        super.onRightClick();
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        AjaxParams params = new AjaxParams();
        params.put("id", this.detail.getId());
        String text = this.editText.getText().toString().trim();
        String str = this.flag;
        char c = 65535;
        switch (str.hashCode()) {
            case -2131598352:
                if (str.equals(FlagUtil.CHANGE_AGE)) {
                    c = 3;
                    break;
                }
                break;
            case -2105021411:
                if (str.equals(FlagUtil.CHANGE_NICKNAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1128733158:
                if (str.equals(FlagUtil.CHANGE_IDCARD)) {
                    c = 0;
                    break;
                }
                break;
            case 236910309:
                if (str.equals(FlagUtil.CHANGE_EAMIL)) {
                    c = 4;
                    break;
                }
                break;
            case 1259870424:
                if (str.equals(FlagUtil.CHANGE_REALNAME)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                params.put("idcard", text);
                break;
            case 1:
                if (!HyUtil.isEmpty(text)) {
                    params.put("name", text);
                    break;
                } else {
                    MyToast.show(this, (int) R.string.real_name_hint);
                    return;
                }
            case 2:
                if (!HyUtil.isEmpty(text)) {
                    if (text.length() <= 10) {
                        if (!text.equals(this.detail.getNickName())) {
                            if (ComUtil.checkContent(text, com.vsf2f.f2f.ui.utils.Constant.CONTENTMARTCH)) {
                                params.put("nickName", text);
                                break;
                            } else {
                                MyToast.show(this, "不能输入特殊字符");
                                return;
                            }
                        } else {
                            onLeftClick();
                            return;
                        }
                    } else {
                        MyToast.show(this, "昵称过长");
                        return;
                    }
                } else {
                    MyToast.show(this, (int) R.string.nick_empty);
                    return;
                }
            case 3:
                if (!HyUtil.isEmpty(text)) {
                    if (!text.equals(this.detail.getAge())) {
                        if (text.length() <= 3) {
                            params.put("age", text);
                            break;
                        } else {
                            MyToast.show(this, "已超出合适的年龄范围");
                            return;
                        }
                    } else {
                        onLeftClick();
                        return;
                    }
                } else {
                    MyToast.show(this, (int) R.string.age_hint);
                    return;
                }
            case 4:
                if (!HyUtil.isEmpty(text)) {
                    if (HyUtil.isEmail(text)) {
                        if (!text.equals(this.detail.getEmail())) {
                            params.put(NotificationCompat.CATEGORY_EMAIL, text);
                            break;
                        } else {
                            onLeftClick();
                            return;
                        }
                    } else {
                        MyToast.show(this, (int) R.string.email_error);
                        return;
                    }
                } else {
                    MyToast.show(this, (int) R.string.email_hint);
                    return;
                }
        }
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_CHANGE_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA)), params, String.class);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        char c;
        String editStr = this.editText.getText().toString();
        String str = this.flag;
        switch (str.hashCode()) {
            case -2131598352:
                if (str.equals(FlagUtil.CHANGE_AGE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -2105021411:
                if (str.equals(FlagUtil.CHANGE_NICKNAME)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1128733158:
                if (str.equals(FlagUtil.CHANGE_IDCARD)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 236910309:
                if (str.equals(FlagUtil.CHANGE_EAMIL)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1259870424:
                if (str.equals(FlagUtil.CHANGE_REALNAME)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                UserShared.getInstance().save("idcard", editStr);
                break;
            case 1:
                MainActivity.getInstance().refreshMine();
                UserShared.getInstance().save("name", editStr);
                break;
            case 2:
                MainActivity.getInstance().refreshMine();
                DemoHelper.getInstance().setCurrentNickName(editStr);
                break;
            case 3:
                UserShared.getInstance().save("age", editStr);
                break;
            case 4:
                UserShared.getInstance().save(NotificationCompat.CATEGORY_EMAIL, editStr);
                break;
        }
        MyToast.show(getApplicationContext(), getString(R.string.toast_change_success));
        setResult(-1);
        finish();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        String str = this.flag;
        char c = 65535;
        switch (str.hashCode()) {
            case -2131598352:
                if (str.equals(FlagUtil.CHANGE_AGE)) {
                    c = 3;
                    break;
                }
                break;
            case -2105021411:
                if (str.equals(FlagUtil.CHANGE_NICKNAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1128733158:
                if (str.equals(FlagUtil.CHANGE_IDCARD)) {
                    c = 0;
                    break;
                }
                break;
            case 236910309:
                if (str.equals(FlagUtil.CHANGE_EAMIL)) {
                    c = 4;
                    break;
                }
                break;
            case 1259870424:
                if (str.equals(FlagUtil.CHANGE_REALNAME)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                initHeaderBackTxt(R.string.ID_card_enter, R.string.ensure);
                ((LinearLayout) getView(R.id.change_llyIDCard)).setVisibility(0);
                this.editText = (EditText) getView(R.id.change_editIDCard);
                this.txtPrompt.setText(R.string.ID_card_prompt);
                break;
            case 1:
                initHeaderBackTxt(R.string.real_change, R.string.ensure);
                this.txtPrompt.setText(R.string.ali_bind_prompt);
                this.txtTitle.setText(R.string.real_name);
                this.editText.setHint(R.string.real_name_hint);
                break;
            case 2:
                initHeaderBackTxt(R.string.nick_change, R.string.ensure);
                this.txtTitle.setText(R.string.nick);
                this.editText.setHint(R.string.nick_hint);
                if (this.detail.getNickName() != null) {
                    this.editText.setText(this.detail.getNickName());
                }
                this.txtPrompt.setText("10字以内");
                break;
            case 3:
                initHeaderBackTxt(R.string.age_change, R.string.ensure);
                this.txtTitle.setText(R.string.age_change);
                this.editText.setHint(R.string.age_hint);
                if (this.detail.getAge() != null) {
                    this.editText.setText(this.detail.getAge());
                }
                this.editText.setInputType(2);
                break;
            case 4:
                initHeaderBackTxt(R.string.email_change, R.string.ensure);
                ((LinearLayout) getView(R.id.change_llyEmail)).setVisibility(0);
                this.editText = (EditText) getView(R.id.change_editEmail);
                this.editText.setHint(R.string.email_hint);
                if (this.detail.getEmail() != null) {
                    this.editText.setText(this.detail.getEmail());
                    break;
                }
                break;
        }
        this.editText.requestFocus();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.change_txtTitle /* 2131755182 */:
                showCountryDialog();
                return;
            case R.id.regist_btnGetCode /* 2131755186 */:
                requestYZM();
                return;
            default:
                return;
        }
    }

    private void requestYZM() {
        this.btnGetCode.prepare();
        String text = this.editText.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            MyToast.show(getApplicationContext(), "手机号不能为空");
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("phone", text);
        params.put("flag", "CERT");
        params.put("countryCode", "86");
        params.put("timestamp", System.currentTimeMillis());
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().post(R.string.API_PHONE_YZM, ComUtil.getZCApi(this.context, getString(R.string.API_PHONE_YZM)), params);
    }

    private void showCountryDialog() {
        new CountryDialog(this.context, new CountryDialog.ConfirmDlgListener() { // from class: com.vsf2f.f2f.ui.user.change.ChangeInfoActivity.1
            @Override // com.vsf2f.f2f.ui.dialog.CountryDialog.ConfirmDlgListener
            public void onCountrySelect(String string) {
                ChangeInfoActivity.this.txtTitle.setText(string);
                ChangeInfoActivity.this.strCountryCode = string;
            }
        }).show();
    }
}
