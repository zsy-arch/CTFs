package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserFundCenterBean;
import com.vsf2f.f2f.ui.otay.ManorTreeDialog;
import com.vsf2f.f2f.ui.packet.RedPacketRecordActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.web.WebUtils;

/* loaded from: classes2.dex */
public class UserFinanceActivity extends BaseActivity {
    private final int ACTIVATE_PAY = 111;
    private final int WITHDRAWAL_ACT = 121;
    private UserFundCenterBean.Account accountbean;
    private TextView txtBalance;
    private TextView txtBonusPlatform;
    private TextView txtBonusSales;
    private TextView txtCoupon;
    private TextView txtFrozen;
    private TextView txtGoldbean;
    private TextView txtGoldcoin;
    private TextView txtLevel;
    private TextView txtProfitExternal;
    private TextView txtProfitShare;
    private TextView txtSumCoupon;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_finance;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.finance_title, R.string.details_withdrawal);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
        }
        this.txtLevel = (TextView) getView(R.id.user_fnc_txtLevel);
        this.txtCoupon = (TextView) getView(R.id.user_fnc_txtCoupon);
        this.txtFrozen = (TextView) getView(R.id.user_fnc_txtFrozen);
        this.txtBalance = (TextView) getView(R.id.user_fnc_txtBalance);
        this.txtGoldcoin = (TextView) getView(R.id.user_fnc_txtGoldcoin);
        this.txtGoldbean = (TextView) getView(R.id.user_fnc_txtGoldbean);
        this.txtSumCoupon = (TextView) getView(R.id.user_fnc_txtSumCoupon);
        this.txtBonusSales = (TextView) getView(R.id.user_fnc_txtBonusSales);
        this.txtProfitShare = (TextView) getView(R.id.user_fnc_txtProfitShare);
        this.txtBonusPlatform = (TextView) getView(R.id.user_fnc_txtBonusPlatform);
        this.txtProfitExternal = (TextView) getView(R.id.user_fnc_txtProfitExternal);
        setOnClickListener(R.id.user_fnc_btnRedRecord);
        setOnClickListener(R.id.user_fnc_btnWithdraw);
        setOnClickListener(R.id.user_fnc_btnRecharge);
        setOnClickListener(R.id.user_fnc_llyProfitExternal);
        setOnClickListener(R.id.user_fnc_llyBonusPlatform);
        setOnClickListener(R.id.user_fnc_llyProfitShare);
        setOnClickListener(R.id.user_fnc_llyBonusSales);
        setOnClickListener(R.id.user_fnc_llyGoldbean);
        setOnClickListener(R.id.user_fnc_llyGoldcoin);
        setOnClickListener(R.id.user_fnc_llyCoupon);
        setOnClickListener(R.id.user_fnc_llyLevel);
        ((TextView) getView(R.id.tv_remind)).setText(Html.fromHtml(getString(R.string.finance_prompt)));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        requestData();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_USER_FUNDS /* 2131296467 */:
                UserFundCenterBean centerBean = (UserFundCenterBean) result.getObj();
                if (centerBean != null) {
                    this.accountbean = centerBean.getAccount();
                    updateUI();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_USER_FUNDS, ComUtil.getZCApi(this.context, getString(R.string.API_USER_FUNDS)), UserFundCenterBean.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (getUserInfo().getLv() == 4) {
            this.txtLevel.setText(R.string.vip_crown_member);
        } else {
            this.txtLevel.setText(R.string.vip_normal);
        }
        if (this.accountbean != null) {
            this.txtBalance.setText(HyUtil.formatToMoney(this.accountbean.getCash()));
            this.txtFrozen.setText(HyUtil.removeNumberZero(this.accountbean.getFrozenFund()));
            this.txtGoldcoin.setText(String.format("%s 个", this.accountbean.getGoldNum()));
            this.txtGoldbean.setText(String.format("%s 个", this.accountbean.getGoldBeanNum()));
            this.txtProfitShare.setText(HyUtil.formatToMoney(this.accountbean.getRecomProfit()));
            this.txtBonusPlatform.setText(HyUtil.formatToMoney(this.accountbean.getSalesNum()));
            this.txtBonusSales.setText(HyUtil.formatToMoney(this.accountbean.getShoppingProfit()));
            this.txtSumCoupon.setText(String.format("%s 元", this.accountbean.getSumCoupon()));
            this.txtCoupon.setText(String.format("%s元 ", this.accountbean.getUsableCoupon()));
            this.txtProfitExternal.setText(String.format("%s 分", this.accountbean.getPoints()));
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        super.onRightClick();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.FLAG2, false);
        bundle.putString(Constant.FLAG_TITLE, getString(R.string.cash_detail));
        bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, getString(R.string.WITHDRAWAL_HISTORY)));
        startAct(WebKitLocalActivity.class, bundle);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.user_fnc_llyLevel /* 2131755431 */:
                startAct(UserVipActivity.class);
                return;
            case R.id.user_fnc_btnWithdraw /* 2131755663 */:
                if (UserShared.getInstance().getInt(com.vsf2f.f2f.ui.utils.Constant.CERT_MOBILE) != 1) {
                    new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.UserFinanceActivity.1
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle2) {
                            if (confirmed) {
                                UserFinanceActivity.this.startAct(BindPhoneActivity.class);
                            }
                        }
                    }, true).show();
                    return;
                } else {
                    startActForResult(WithdrawalActivity.class, bundle, 121);
                    return;
                }
            case R.id.user_fnc_btnRecharge /* 2131755737 */:
                bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, getString(R.string.URL_MONEY_RECHARGE)));
                startAct(WebKitLocalActivity.class, bundle);
                return;
            case R.id.user_fnc_btnRedRecord /* 2131755738 */:
                startAct(RedPacketRecordActivity.class);
                return;
            case R.id.user_fnc_llyGoldbean /* 2131755746 */:
                if (this.accountbean == null) {
                    return;
                }
                if (UserShared.getInstance().isOpenManor()) {
                    startActForResult(MyBeansActivity.class, 121);
                    return;
                } else {
                    new ManorTreeDialog(this.context).show();
                    return;
                }
            case R.id.user_fnc_llyGoldcoin /* 2131755748 */:
                if (this.accountbean == null) {
                    return;
                }
                if (UserShared.getInstance().isOpenManor()) {
                    startActForResult(MyCoinsActivity.class, 121);
                    return;
                } else {
                    new ManorTreeDialog(this.context).show();
                    return;
                }
            case R.id.user_fnc_llyProfitExternal /* 2131755750 */:
                bundle.putString(Constant.FLAG_TITLE, "积分返还记录");
                bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, getString(R.string.URL_ACCOUNT_POINTS)));
                startAct(WebKitLocalActivity.class, bundle);
                return;
            case R.id.user_fnc_llyCoupon /* 2131755752 */:
                String url = DemoHelper.getInstance().readConfig().getShop();
                if (url == null) {
                    url = "/m/shop/18944761788.mobile";
                }
                bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, url));
                bundle.putString(Constant.FLAG_TITLE, com.vsf2f.f2f.ui.utils.Constant.FLAG_AUTO_TITLE);
                startAct(WebKitLocalActivity.class, bundle);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 111:
                    initData();
                    return;
                case 121:
                    requestData();
                    return;
                default:
                    return;
            }
        }
    }
}
