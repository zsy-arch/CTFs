package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserFundCenterBean;
import com.vsf2f.f2f.fragment.Needs2Activity;
import com.vsf2f.f2f.fragment.NeedsActivity;
import com.vsf2f.f2f.ui.otay.ManorTreeDialog;
import com.vsf2f.f2f.ui.pay.PayCenterActivity;
import com.vsf2f.f2f.ui.sharing.SharingMainActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.GameUtil;

/* loaded from: classes2.dex */
public class UserWalletActivity extends BaseActivity {
    private final int WITHDRAWAL_ACT = 121;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_wallet;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.wallet_title, R.drawable.icon_wallet_more);
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
            finish();
            return;
        }
        setOnClickListener(R.id.wallet_llMoney);
        setOnClickListener(R.id.wallet_navShares);
        setOnClickListener(R.id.wallet_navCoins);
        setOnClickListener(R.id.wallet_navBeans);
        setOnClickListener(R.id.wallet_navPoints);
        setOnClickListener(R.id.wallet_navClouds);
        setOnClickListener(R.id.wallet_navTrade);
        setOnClickListener(R.id.wallet_navHelp);
        setOnClickListener(R.id.wallet_navService);
        setOnClickListener(R.id.wallet_navManor);
        setOnClickListener(R.id.wallet_navShopping);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_USER_FUNDS, ComUtil.getZCApi(this.context, getString(R.string.API_USER_FUNDS)), UserFundCenterBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_USER_FUNDS /* 2131296467 */:
                UserFundCenterBean centerBean = (UserFundCenterBean) result.getObj();
                if (centerBean != null) {
                    ((TextView) findViewById(R.id.wallet_tvBalance)).setText("￥" + HyUtil.formatToMoney(centerBean.getAccount().getCash()));
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        startAct(PayCenterActivity.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        if (UserShared.getInstance().getIsVerifyState(this.context)) {
            Bundle bundle = new Bundle();
            switch (view.getId()) {
                case R.id.wallet_llMoney /* 2131755837 */:
                    startAct(UserMoneyActivity.class);
                    return;
                case R.id.wallet_tvBalance /* 2131755838 */:
                default:
                    return;
                case R.id.wallet_navShares /* 2131755839 */:
                    startAct(SharingMainActivity.class);
                    return;
                case R.id.wallet_navCoins /* 2131755840 */:
                    if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                        new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.UserWalletActivity.1
                            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                            public void onResult(boolean confirmed, Bundle bundle2) {
                                if (confirmed) {
                                    UserWalletActivity.this.startActForResult(BindPhoneActivity.class, 123);
                                }
                            }
                        }, true).show();
                        return;
                    } else if (UserShared.getInstance().isOpenManor()) {
                        startActForResult(MyCoinsActivity.class, 121);
                        return;
                    } else {
                        new ManorTreeDialog(this.context).show();
                        return;
                    }
                case R.id.wallet_navBeans /* 2131755841 */:
                    if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                        new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.UserWalletActivity.2
                            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                            public void onResult(boolean confirmed, Bundle bundle2) {
                                if (confirmed) {
                                    UserWalletActivity.this.startActForResult(BindPhoneActivity.class, 123);
                                }
                            }
                        }, true).show();
                        return;
                    } else if (UserShared.getInstance().isOpenManor()) {
                        startActForResult(MyBeansActivity.class, 121);
                        return;
                    } else {
                        new ManorTreeDialog(this.context).show();
                        return;
                    }
                case R.id.wallet_navPoints /* 2131755842 */:
                    String url2 = DemoHelper.getInstance().readConfig().getYlh_url();
                    if (url2 == null) {
                        url2 = "http://www.yunlianhui.cn/";
                    }
                    bundle.putString(com.hy.frame.util.Constant.FLAG, url2);
                    bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                    bundle.putString(com.hy.frame.util.Constant.FLAG_TITLE, "乐返中心");
                    startAct(WebKitLocalActivity.class, bundle);
                    return;
                case R.id.wallet_navClouds /* 2131755843 */:
                    bundle.putString(com.hy.frame.util.Constant.FLAG, "http://hb.piduhui.com/index.php?m=Mobile&c=Index&a=index&shareUserId=");
                    startAct(WebKitLocalActivity.class, bundle);
                    return;
                case R.id.wallet_navTrade /* 2131755844 */:
                    bundle.putString(com.hy.frame.util.Constant.FLAG, "http://shop.qqjfjys.top/");
                    startAct(WebKitLocalActivity.class, bundle);
                    return;
                case R.id.wallet_navHelp /* 2131755845 */:
                    startAct(NeedsActivity.class);
                    return;
                case R.id.wallet_navService /* 2131755846 */:
                    startAct(Needs2Activity.class);
                    return;
                case R.id.wallet_navManor /* 2131755847 */:
                    if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                        new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.UserWalletActivity.3
                            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                            public void onResult(boolean confirmed, Bundle bundle2) {
                                if (confirmed) {
                                    UserWalletActivity.this.startAct(BindPhoneActivity.class);
                                }
                            }
                        }, true).show();
                        return;
                    } else if (UserShared.getInstance().isOpenManor()) {
                        GameUtil.startGame(this.context);
                        return;
                    } else {
                        new ManorTreeDialog(this.context).show();
                        return;
                    }
                case R.id.wallet_navShopping /* 2131755848 */:
                    String url = DemoHelper.getInstance().readConfig().getWap_mall_url();
                    if (TextUtils.isEmpty(url)) {
                        url = "https://wap.vjkeji.com/mall/index.html";
                    }
                    bundle.putString(com.hy.frame.util.Constant.FLAG, url);
                    bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                    startAct(WebKitLocalActivity.class, bundle);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 121:
                    requestData();
                    return;
                default:
                    return;
            }
        }
    }
}
