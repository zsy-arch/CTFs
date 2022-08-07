package com.vsf2f.f2f.ui.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.frame.view.LoadingDialog;
import com.hy.http.AjaxParams;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.PayStyleBean;
import com.vsf2f.f2f.ui.user.change.BindAliActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.user.change.UnBindAccountActivity;
import com.vsf2f.f2f.wxapi.WXEntryActivity;
import java.util.List;

/* loaded from: classes2.dex */
public class MoneyAccountActivity extends BaseActivity {
    private boolean bindAli;
    private boolean bindWx;
    private boolean isTrunWx;
    private KeyValueView kvAliAccount;
    private KeyValueView kvWxAccount;
    private LoadingDialog loadDlg;
    private BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.vsf2f.f2f.ui.user.MoneyAccountActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WXEntryActivity.ACTION_SHARE_SUCCESS)) {
                MoneyAccountActivity.this.bindWeChat(intent.getStringExtra(Constant.FLAG));
            }
        }
    };
    private boolean result;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_money_account;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.capital_account, 0);
        this.kvAliAccount = (KeyValueView) findViewById(R.id.user_data_kvAliAccount);
        this.kvWxAccount = (KeyValueView) findViewById(R.id.user_data_kvWxAccount);
        if (getBundle() != null) {
            this.result = getBundle().getBoolean(Constant.FLAG);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        initBind();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_USER_BANK_ITEMS, ComUtil.getZCApi(this.context, getString(R.string.API_USER_BANK_ITEMS)), new AjaxParams(), PayStyleBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_USER_BANK_ITEMS /* 2131296454 */:
                this.kvWxAccount.setOnClickListener(this);
                this.kvAliAccount.setOnClickListener(this);
                this.bindWx = false;
                this.bindAli = false;
                if (result.getObj() == null) {
                    this.kvWxAccount.setValue(R.string.no_set);
                    this.kvAliAccount.setValue(R.string.no_set);
                    return;
                }
                List<PayStyleBean> datas = (List) result.getObj();
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).getType() == 0) {
                        String nickName = datas.get(i).getBankNumber();
                        KeyValueView keyValueView = this.kvAliAccount;
                        if (TextUtils.isEmpty(nickName)) {
                            nickName = "已绑定";
                        }
                        keyValueView.setValue(nickName);
                        this.bindAli = true;
                    } else if (datas.get(i).getType() == 1) {
                        String nickName2 = datas.get(i).getBankNickName();
                        KeyValueView keyValueView2 = this.kvWxAccount;
                        if (TextUtils.isEmpty(nickName2)) {
                            nickName2 = "已绑定";
                        }
                        keyValueView2.setValue(nickName2);
                        this.bindWx = true;
                    }
                }
                if (!this.bindAli) {
                    this.kvAliAccount.setValue(R.string.no_set);
                }
                if (!this.bindWx) {
                    this.kvWxAccount.setValue(R.string.no_set);
                    return;
                }
                return;
            case R.string.API_USER_BIND_WX /* 2131296458 */:
                requestData();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.isTrunWx) {
            this.isTrunWx = false;
            showLoadDlg(false);
            setResult(-1);
            requestData();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        if (UserShared.getInstance().getInt(com.vsf2f.f2f.ui.utils.Constant.CERT_MOBILE) != 1) {
            new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.MoneyAccountActivity.1
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    if (confirmed) {
                        MoneyAccountActivity.this.startActForResult(BindPhoneActivity.class, 123);
                    }
                }
            }, true).setOkBtn("去绑定").show();
            return;
        }
        switch (v.getId()) {
            case R.id.user_data_kvWxAccount /* 2131755418 */:
                new EaseAlertDialog(this.context, (int) R.string.prompt, this.bindWx ? R.string.already_bind_account : R.string.not_bind_account, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.MoneyAccountActivity.2
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (!confirmed) {
                            return;
                        }
                        if (MoneyAccountActivity.this.bindWx) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("type", "wx");
                            MoneyAccountActivity.this.startActForResult(UnBindAccountActivity.class, bundle2, 999);
                            return;
                        }
                        MoneyAccountActivity.this.WechatLogin();
                    }
                }, true).setOkBtn(this.bindWx ? "去解绑" : "去绑定").show();
                return;
            case R.id.user_data_kvAliAccount /* 2131755419 */:
                new EaseAlertDialog(this.context, (int) R.string.prompt, this.bindAli ? R.string.already_bind_account : R.string.not_bind_account, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.MoneyAccountActivity.3
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (!confirmed) {
                            return;
                        }
                        if (MoneyAccountActivity.this.bindAli) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("type", "ali");
                            MoneyAccountActivity.this.startActForResult(UnBindAccountActivity.class, bundle2, 999);
                            return;
                        }
                        MoneyAccountActivity.this.startActForResult(BindAliActivity.class, 999);
                    }
                }, true).setOkBtn(this.bindAli ? "去解绑" : "去绑定").show();
                return;
            default:
                return;
        }
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

    public void WechatLogin() {
        this.kvWxAccount.setClickable(false);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        if (MyApplication.wxApi.isWXAppInstalled()) {
            MyApplication.wxApi.sendReq(req);
            showLoadDlg(true);
            this.isTrunWx = true;
        } else {
            MyToast.show(this.context, (int) R.string.no_install_wx);
        }
        this.kvWxAccount.setClickable(true);
    }

    public void bindWeChat(String str) {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_BIND_WX, ComUtil.getZCApi(this.context, getString(R.string.API_USER_BIND_WX)), str, params, String.class, false);
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBind() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WXEntryActivity.ACTION_SHARE_SUCCESS);
        filter.setPriority(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
        registerReceiver(this.receiver, filter);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 999 || resultCode != -1) {
            return;
        }
        if (this.result) {
            setResult(-1);
            finish();
            return;
        }
        requestData();
    }

    public void dismiss(View view) {
        getView(R.id.user_data_gray_rlyIKnow).setVisibility(8);
    }
}
