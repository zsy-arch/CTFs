package com.vsf2f.f2f.ui.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.EditText;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.PayStyleBean;
import com.vsf2f.f2f.ui.dialog.PayReadyDialog;
import com.vsf2f.f2f.ui.view.NumKeyView;
import com.vsf2f.f2f.wxapi.WXEntryActivity;

/* loaded from: classes2.dex */
public class MoneyRecordActivity extends BaseActivity {
    private EditText et_money;
    private NumKeyView mKeyView;
    private BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.vsf2f.f2f.ui.user.MoneyRecordActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (WXEntryActivity.ACTION_SHARE_SUCCESS.equals(intent.getAction())) {
                MoneyRecordActivity.this.bindWeChat(intent.getStringExtra(Constant.FLAG));
            }
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_money_record;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.mdm_redpacket, 0);
        this.et_money = (EditText) getViewAndClick(R.id.recharge_tvMoney);
        this.et_money.requestFocus();
        setOnClickListener(R.id.recharge_tvRecharge);
        this.mKeyView = (NumKeyView) findViewById(R.id.keyboardview);
        this.mKeyView.setOnKeyPressListener(new NumKeyView.OnKeyPressListener() { // from class: com.vsf2f.f2f.ui.user.MoneyRecordActivity.1
            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onInertKey(String text) {
                MoneyRecordActivity.this.et_money.append(text);
            }

            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onDeleteKey() {
                int last = MoneyRecordActivity.this.et_money.getText().length();
                if (last > 0) {
                    MoneyRecordActivity.this.et_money.getText().delete(last - 1, last);
                }
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        initBind();
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
                if (result.getObj() == null) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getClient().dialogDismiss();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.recharge_tvRecharge /* 2131755422 */:
                new PayReadyDialog(this.context, this.et_money.getText().toString()).setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.user.MoneyRecordActivity.2
                    @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                    public void onDlgConfirm(BaseDialog dlg, int flag) {
                        if (flag == 1) {
                        }
                    }
                }).show();
                return;
            default:
                return;
        }
    }

    private void WechatLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        if (MyApplication.wxApi.isWXAppInstalled()) {
            MyApplication.wxApi.sendReq(req);
            getClient().showDialogNow();
            return;
        }
        MyToast.show(this.context, (int) R.string.no_install_wx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindWeChat(String str) {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_BIND_WX, ComUtil.getZCApi(this.context, getString(R.string.API_USER_BIND_WX)), str, params, String.class, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == -1) {
            requestData();
        }
    }
}
