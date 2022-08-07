package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.VipUpdateBean;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.pay.PayActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class UserVipActivity extends BaseActivity {
    private Button btnUp;
    private int cusLevel;
    private LinearLayout llyCanUp;
    private String money;
    private TextView txtLevel;
    private TextView txtUplevel;
    private int upLevel;
    private WebView vip_tab;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_vip;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.open_pms, 0);
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
            finish();
            return;
        }
        setOnClickListener(R.id.user_vip_navHelp);
        this.txtLevel = (TextView) getView(R.id.user_vip_txtLevel);
        this.txtUplevel = (TextView) getView(R.id.user_vip_txtUpLevel);
        this.llyCanUp = (LinearLayout) getView(R.id.user_vip_llyCanUp);
        this.vip_tab = (WebView) getView(R.id.user_vip_tab);
        this.btnUp = (Button) getViewAndClick(R.id.user_vip_btnUp);
        this.cusLevel = getUserInfo().getLv();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
            new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.UserVipActivity.1
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    if (confirmed) {
                        UserVipActivity.this.startActForResult(BindPhoneActivity.class, 123);
                    }
                    UserVipActivity.this.finish();
                }
            }, true).show();
            return;
        }
        requestData();
        initSetting();
        if (this.cusLevel > 1) {
            showCrown();
        }
        String url = DemoHelper.getInstance().readConfig().getPermissions_contrast();
        if (TextUtils.isEmpty(url)) {
            url = "http://advert.vjkeji.com/000_advert/admin/pc_ad.png?x-oss-process=style/nostyle";
        }
        this.vip_tab.loadUrl(url);
    }

    private void showCrown() {
        this.txtLevel.setText(R.string.vip_crown_member);
        this.llyCanUp.setVisibility(0);
        this.txtUplevel.setText("您已经是共享会员");
        this.btnUp.setVisibility(8);
    }

    private void initSetting() {
        this.vip_tab.removeJavascriptInterface("searchBoxJavaBridge_");
        this.vip_tab.removeJavascriptInterface("accessibility");
        this.vip_tab.removeJavascriptInterface("accessibilityTraversal");
        WebSettings settings = this.vip_tab.getSettings();
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setSavePassword(true);
        settings.setCacheMode(-1);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setGeolocationDatabasePath(getApp().getDir("database", 0).getPath());
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(false);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        settings.setUserAgentString(settings.getUserAgentString() + " vsf2fcompany/f2f/" + ComUtil.getVersion(this));
        this.vip_tab.setInitialScale(10);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_VIP_LEVEL, ComUtil.getZCApi(this.context, getString(R.string.API_VIP_LEVEL)), VipUpdateBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        VipUpdateBean updateBean = (VipUpdateBean) result.getObj();
        this.money = updateBean.getExp();
        this.upLevel = updateBean.getLv();
        if (this.cusLevel == 0) {
            this.btnUp.setVisibility(0);
            this.llyCanUp.setVisibility(0);
            this.txtUplevel.setText(updateBean.getName() + "会员:" + this.money + getString(R.string.vip_onelife));
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        this.btnUp.setVisibility(8);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.user_vip_navHelp /* 2131755835 */:
                Bundle bundle2 = new Bundle();
                bundle2.putString(com.hy.frame.util.Constant.FLAG_TITLE, "协议书");
                bundle2.putString(com.hy.frame.util.Constant.FLAG, getString(R.string.API_HOST) + getString(R.string.HELP_VIP_URL));
                bundle2.putBoolean(com.hy.frame.util.Constant.FLAG2, false);
                startAct(WebKitLocalActivity.class, bundle2);
                return;
            case R.id.user_vip_btnUp /* 2131755836 */:
                if (UserShared.getInstance().getIsVerifyState(this.context)) {
                    Bundle bundle = new Bundle();
                    bundle.putString(com.hy.frame.util.Constant.FLAG, this.money);
                    bundle.putString(com.hy.frame.util.Constant.FLAG_TITLE, getString(R.string.pay_vip));
                    bundle.putInt(com.hy.frame.util.Constant.FLAG_TYPE, 1);
                    startActForResult(PayActivity.class, bundle, 999);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 999) {
            int level = this.upLevel;
            showCrown();
            UserShared.getInstance().setOpenVip(level);
            MainActivity.getInstance().refreshMine();
            setResult(-1);
        } else if (requestCode == 123) {
            requestData();
        }
    }
}
