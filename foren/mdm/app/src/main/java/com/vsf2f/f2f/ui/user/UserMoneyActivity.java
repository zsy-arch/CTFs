package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserFundCenterBean;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.web.WebUtils;

/* loaded from: classes2.dex */
public class UserMoneyActivity extends BaseActivity {
    private final int ACTIVATE_PAY = 111;
    private final int WITHDRAWAL_ACT = 121;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_money;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.money_title, 0);
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
            finish();
            return;
        }
        setOnClickListener(R.id.money_tvDetail);
        setOnClickListener(R.id.money_tvRecharge);
        setOnClickListener(R.id.money_tvWithdrawal);
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
                    ((TextView) findViewById(R.id.money_tvBalance)).setText(HyUtil.formatToMoney(centerBean.getAccount().getCash()));
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
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.money_tvRecharge /* 2131755782 */:
                bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, getString(R.string.URL_MONEY_RECHARGE)));
                startAct(WebKitLocalActivity.class, bundle);
                return;
            case R.id.money_tvWithdrawal /* 2131755783 */:
                if (UserShared.getInstance().getInt(com.vsf2f.f2f.ui.utils.Constant.CERT_MOBILE) != 1) {
                    new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.UserMoneyActivity.1
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle2) {
                            if (confirmed) {
                                UserMoneyActivity.this.startAct(BindPhoneActivity.class);
                            }
                        }
                    }, true).show();
                    return;
                } else {
                    startActForResult(WithdrawalActivity.class, bundle, 121);
                    return;
                }
            case R.id.money_tvDetail /* 2131755784 */:
                bundle.putBoolean(Constant.FLAG2, false);
                bundle.putString(Constant.FLAG_TITLE, getString(R.string.cash_detail));
                bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, getString(R.string.WITHDRAWAL_HISTORY)));
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
