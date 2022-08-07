package com.vsf2f.f2f.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.alipay.sdk.util.j;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.CacheUtil;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.easeui.widget.EaseSwitchButton;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.AppVersion;
import com.vsf2f.f2f.bean.IdentifyBean;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.identify.BindZhiMaActivity;
import com.vsf2f.f2f.ui.identify.IdentityComActivity;
import com.vsf2f.f2f.ui.identify.IdentyIdCardActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.user.pwd.ResetPwdActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.upload.UpAppUtils;
import java.util.List;

/* loaded from: classes2.dex */
public class SettingsActivity extends BaseActivity {
    private static final int IDENTIFY_CODE = 788;
    private CacheUtil cacheUtil;
    private KeyValueView kvCheckVersion;
    private KeyValueView kvClearCache;
    private KeyValueView kvIdentCom;
    private KeyValueView kvIdentReal;
    private KeyValueView kvIdentZM;
    private EaseSwitchButton sbtn_isVisible;
    private int visible;
    private IdentifyBean zhimaIdentify;
    private int realType = -1;
    private int zhimaType = -1;
    private int comType = -1;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_settings;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeader(R.string.setting_set, 0);
        setOnClickListener(R.id.logout_btnLogout);
        setOnClickListener(R.id.settings_kvLocation);
        setOnClickListener(R.id.settings_kvResetPwd);
        setOnClickListener(R.id.settings_kvMsgNotify);
        setOnClickListener(R.id.settings_kvAboutFace);
        setOnClickListener(R.id.settings_kvBlackList);
        setOnClickListener(R.id.settings_kvProfile);
        setOnClickListener(R.id.settings_kvAddress);
        this.kvClearCache = (KeyValueView) getViewAndClick(R.id.settings_kvClearCache);
        this.kvCheckVersion = (KeyValueView) getViewAndClick(R.id.settings_kvCheckVersion);
        this.sbtn_isVisible = (EaseSwitchButton) getView(R.id.sbtn_isVisible);
        checkSwitch();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.kvCheckVersion.setValue("V " + ComUtil.getVersion(this.context));
        this.cacheUtil = new CacheUtil(this.context);
        this.kvClearCache.setValue(this.cacheUtil.getCacheSize());
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        queryIdentifyState();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    private void queryIdentifyState() {
        getClient().get(R.string.QUERY_CERTIFY_STATUS, ComUtil.getXDDApi(this.context, getString(R.string.QUERY_CERTIFY_STATUS)) + "?userName=" + DemoHelper.getInstance().getCurrentUserName(), null, IdentifyBean.class, true);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        boolean z = true;
        int i = 1;
        switch (view.getId()) {
            case R.id.settings_kvLocation /* 2131755810 */:
                if (!this.sbtn_isVisible.change()) {
                    i = 0;
                }
                this.visible = i;
                switchVisible();
                return;
            case R.id.sbtn_isVisible /* 2131755811 */:
            case R.id.settings_llCheckVersion /* 2131755820 */:
            default:
                return;
            case R.id.settings_kvMsgNotify /* 2131755812 */:
                startAct(SettingsNotifyActivity.class);
                return;
            case R.id.settings_kvProfile /* 2131755813 */:
                startAct(UserDataActivity.class);
                return;
            case R.id.settings_kvIdentReal /* 2131755814 */:
                Bundle bundle = new Bundle();
                bundle.putInt("type", this.realType);
                startActForResult(IdentyIdCardActivity.class, bundle, IDENTIFY_CODE);
                return;
            case R.id.settings_kvIdentZM /* 2131755815 */:
                if (this.realType == 3) {
                    MyToast.show(getApplicationContext(), "请先进行实名认证");
                    return;
                } else if (this.realType == 2) {
                    MyToast.show(getApplicationContext(), "实名认证未通过");
                    return;
                } else if (this.zhimaType == 2 || this.zhimaType == 3) {
                    Bundle bundle2 = new Bundle();
                    if (this.zhimaIdentify.getNeedPay() != 1) {
                        z = false;
                    }
                    bundle2.putBoolean("needPay", z);
                    bundle2.putSerializable(j.c, this.zhimaIdentify);
                    startActForResult(BindZhiMaActivity.class, bundle2, IDENTIFY_CODE);
                    return;
                } else if (this.zhimaType == 0) {
                    MyToast.show(getApplicationContext(), "芝麻认证正在认证中");
                    return;
                } else if (this.zhimaType == 1) {
                    MyToast.show(getApplicationContext(), "芝麻认证已通过");
                    return;
                } else {
                    return;
                }
            case R.id.settings_kvIdentCom /* 2131755816 */:
                if (UserShared.getInstance().getIsVerifyState(this.context)) {
                    Bundle bundle3 = new Bundle();
                    switch (this.comType) {
                        case 0:
                            MyToast.show(this.context, "企业认证申请审核中");
                            return;
                        case 1:
                            MyToast.show(this.context, "企业认证已认证通过");
                            return;
                        case 2:
                        case 4:
                            bundle3.putBoolean("has", true);
                            startActForResult(IdentityComActivity.class, bundle3, IDENTIFY_CODE);
                            return;
                        case 3:
                            startActForResult(IdentityComActivity.class, IDENTIFY_CODE);
                            return;
                        default:
                            return;
                    }
                } else {
                    return;
                }
            case R.id.settings_kvResetPwd /* 2131755817 */:
                startAct(ResetPwdActivity.class);
                return;
            case R.id.settings_kvAddress /* 2131755818 */:
                startAct(UserAddrActivity.class);
                return;
            case R.id.settings_kvBlackList /* 2131755819 */:
                startAct(BlacklistActivity.class);
                return;
            case R.id.settings_kvCheckVersion /* 2131755821 */:
                checkVersion();
                return;
            case R.id.settings_kvClearCache /* 2131755822 */:
                new EaseAlertDialog((Context) this, (int) R.string.prompt, (int) R.string.confirm_to_clear, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.SettingsActivity.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle4) {
                        if (confirmed) {
                            int files = SettingsActivity.this.cacheUtil.clearAppCache();
                            SettingsActivity.this.cacheUtil.clearWebCache();
                            if (files > 0) {
                                MyToast.show(SettingsActivity.this.context, "清除缓存文件成功");
                            } else {
                                MyToast.show(SettingsActivity.this.context, "没有缓存文件了");
                            }
                            SettingsActivity.this.kvClearCache.setValue(SettingsActivity.this.cacheUtil.getCacheSize());
                        }
                    }
                }, true).show();
                return;
            case R.id.settings_kvAboutFace /* 2131755823 */:
                startAct(AboutUsActivity.class);
                return;
            case R.id.logout_btnLogout /* 2131755824 */:
                logout();
                return;
        }
    }

    private void logout() {
        getClient().setShowDialog(R.string.are_logged_out);
        getClient().get(R.string.API_USER_LOGOUT, ComUtil.getZCApi(this.context, getString(R.string.API_USER_LOGOUT)));
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CHECK_VERSION /* 2131296305 */:
                AppVersion appVersion = (AppVersion) result.getObj();
                if (appVersion != null) {
                    new UpAppUtils(this.context, appVersion, true);
                    return;
                }
                return;
            case R.string.API_USER_CHANGE_DATA /* 2131296461 */:
                if (result.getErrorCode() == 0) {
                    UserInfo info = getUserInfo();
                    info.setVisible(this.visible);
                    DemoHelper.getInstance().saveCurrentUserInfo(info);
                    MyToast.show(this.context, "位置共享" + (this.visible == 0 ? "关闭" : "开启"));
                    return;
                }
                checkSwitch();
                return;
            case R.string.API_USER_LOGOUT /* 2131296469 */:
                DemoHelper.getInstance().logout(null);
                startActClear(LoginActivity.class);
                return;
            case R.string.QUERY_CERTIFY_STATUS /* 2131296507 */:
                this.kvIdentReal = (KeyValueView) getViewAndClick(R.id.settings_kvIdentReal);
                this.kvIdentCom = (KeyValueView) getViewAndClick(R.id.settings_kvIdentCom);
                this.kvIdentZM = (KeyValueView) getViewAndClick(R.id.settings_kvIdentZM);
                List<IdentifyBean> beanList = (List) result.getObj();
                if (beanList != null) {
                    for (int i = 0; i < beanList.size(); i++) {
                        IdentifyBean bean = beanList.get(i);
                        switch (bean.getType()) {
                            case 0:
                                this.realType = bean.getStatus();
                                UserShared.getInstance().save(Constant.CERT_REALNAME, this.realType);
                                this.kvIdentReal.setValue(bean.getStatusStr());
                                break;
                            case 1:
                                this.zhimaIdentify = bean;
                                this.zhimaType = bean.getStatus();
                                UserShared.getInstance().save(Constant.CERT_ZHIMA, this.zhimaType);
                                this.kvIdentZM.setValue(bean.getStatusStr());
                                break;
                            case 2:
                                this.comType = bean.getStatus();
                                UserShared.getInstance().save(Constant.CERT_COM, this.comType);
                                if (bean.getStatus() != 0 || bean.getNeedPay() != 1) {
                                    this.kvIdentCom.setValue(bean.getStatusStr());
                                    break;
                                } else {
                                    this.kvIdentCom.setValue("待支付");
                                    this.comType = 4;
                                    break;
                                }
                                break;
                        }
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_USER_CHANGE_DATA /* 2131296461 */:
                checkSwitch();
                return;
            case R.string.API_USER_LOGOUT /* 2131296469 */:
                DemoHelper.getInstance().logout(null);
                startActClear(LoginActivity.class);
                return;
            default:
                return;
        }
    }

    private void checkSwitch() {
        if (getUserInfo().getVisible() == 0) {
            this.sbtn_isVisible.closeSwitch();
        } else {
            this.sbtn_isVisible.openSwitch();
        }
    }

    private void checkVersion() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_CHECK_VERSION, ComUtil.getZCApi(this.context, getString(R.string.API_CHECK_VERSION)), AppVersion.class);
    }

    private void switchVisible() {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("id", getUserInfo().getId());
        params.put("visible", this.visible);
        getClient().post(R.string.API_USER_CHANGE_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA)), params, Boolean.class, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == IDENTIFY_CODE) {
            queryIdentifyState();
        }
    }
}
