package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.NavView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.PayStyleBean;
import com.vsf2f.f2f.bean.UserBalanceBean;
import com.vsf2f.f2f.ui.dialog.PayPwdDialog;
import com.vsf2f.f2f.ui.dialog.PayTypeDialog;
import com.vsf2f.f2f.ui.dialog.WarnDialog;
import com.vsf2f.f2f.ui.pay.PayScucessActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WithdrawalActivity extends BaseActivity {
    private int AliId;
    private int WxId;
    private UserBalanceBean balanceBean;
    private Button btn_commit;
    private List<PayStyleBean> datas;
    private EditText editAmount;
    private int level;
    private String md5pwd;
    private NavView nvType;
    private PayPwdDialog payPwdDialog;
    private TextView txtBalance;
    private int type;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_withdrawal;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.withdrawal, 0);
        this.nvType = (NavView) getViewAndClick(R.id.pay_nvType);
        this.txtBalance = (TextView) getView(R.id.withdrawal_txtBalance);
        this.editAmount = (EditText) getView(R.id.withdrawal_editAmount);
        this.btn_commit = (Button) getViewAndClick(R.id.withdrawal_btnCommit);
        setOnClickListener(R.id.withdrawal_txtAll);
        setOnClickListener(R.id.txt_pswreset);
        this.datas = new ArrayList();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData();
        requestAccountData();
        this.level = getUserInfo().getLv();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_USER_BALANCE, ComUtil.getZCApi(this.context, getString(R.string.API_USER_BALANCE)), UserBalanceBean.class);
    }

    public void requestAccountData() {
        getClient().get(R.string.API_USER_BANK_ITEMS, ComUtil.getZCApi(this.context, getString(R.string.API_USER_BANK_ITEMS)), null, PayStyleBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_USER_BALANCE /* 2131296453 */:
                this.balanceBean = (UserBalanceBean) result.getObj();
                if (this.balanceBean == null) {
                    this.txtBalance.setText("0");
                    return;
                } else {
                    updateUI();
                    return;
                }
            case R.string.API_USER_BANK_ITEMS /* 2131296454 */:
                if (result.getObj() != null) {
                    this.datas = (List) result.getObj();
                    this.btn_commit.setEnabled(true);
                    update();
                    return;
                }
                return;
            case R.string.API_USER_DRAWING /* 2131296464 */:
                this.payPwdDialog.saveDismiss();
                ComUtil.setPaypwd(this.context, this.md5pwd);
                Toast.makeText(this, getString(R.string.apply_success), 0).show();
                Bundle bundle = new Bundle();
                bundle.putString("money", this.editAmount.getText().toString().trim());
                startAct(PayScucessActivity.class, bundle);
                setResult(-1);
                finish();
                return;
            default:
                return;
        }
    }

    private void update() {
        for (int i = 0; i < this.datas.size(); i++) {
            if (this.datas.get(i).getType() == 0) {
                this.AliId = this.datas.get(i).getId();
            }
            if (this.datas.get(i).getType() == 1) {
                this.WxId = this.datas.get(i).getId();
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        this.txtBalance.setText(this.balanceBean.getCash() != null ? HyUtil.formatToMoney(this.balanceBean.getCash()) : "0");
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.pay_nvType /* 2131755855 */:
                PayTypeDialog payTypeDialog = new PayTypeDialog(this.context, this.type);
                payTypeDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.user.WithdrawalActivity.1
                    @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                    public void onDlgConfirm(BaseDialog dlg, int flag) {
                        WithdrawalActivity.this.type = flag;
                        if (WithdrawalActivity.this.type == 0) {
                            WithdrawalActivity.this.nvType.setText("支付宝");
                            WithdrawalActivity.this.nvType.setDraw(R.drawable.icon_pay_ali2);
                            if (WithdrawalActivity.this.AliId == 0) {
                                WithdrawalActivity.this.toBindPhone();
                            }
                        } else if (WithdrawalActivity.this.type == 1) {
                            WithdrawalActivity.this.nvType.setText("微信");
                            WithdrawalActivity.this.nvType.setDraw(R.drawable.icon_pay_wx2);
                            if (WithdrawalActivity.this.WxId == 0) {
                                WithdrawalActivity.this.toBindPhone();
                            }
                        }
                    }
                });
                payTypeDialog.show();
                return;
            case R.id.withdrawal_editAmount /* 2131755856 */:
            case R.id.withdrawal_txtBalance /* 2131755857 */:
            case R.id.txt_pswreset /* 2131755859 */:
            default:
                return;
            case R.id.withdrawal_txtAll /* 2131755858 */:
                if (this.balanceBean != null) {
                    String money = this.balanceBean.getCash();
                    if (money.contains(".")) {
                        money = money.substring(0, money.indexOf("."));
                    }
                    this.editAmount.setText(money);
                    this.editAmount.setSelection(money.length());
                    return;
                }
                return;
            case R.id.withdrawal_btnCommit /* 2131755860 */:
                int id = 0;
                if (this.type == 0) {
                    id = this.AliId;
                } else if (this.type == 1) {
                    id = this.WxId;
                }
                if (id == 0) {
                    toBindPhone();
                    return;
                }
                String moneyStr = this.editAmount.getText().toString().trim();
                if (TextUtils.isEmpty(moneyStr)) {
                    MyToast.show(this.context, (int) R.string.toast_input_money);
                    return;
                } else if (Integer.parseInt(moneyStr) < 1) {
                    MyToast.show(this.context, (int) R.string.toast_input_zero_money);
                    return;
                } else if (Double.parseDouble(this.txtBalance.getText().toString().trim()) < Integer.parseInt(moneyStr)) {
                    MyToast.show(this.context, (int) R.string.toast_input_no_money);
                    return;
                } else if (this.level != 0 || Integer.parseInt(moneyStr) >= 10) {
                    pay(id, moneyStr);
                    return;
                } else {
                    showDialog(getString(R.string.not_open_vip_prompt2));
                    return;
                }
        }
    }

    public void toBindPhone() {
        new EaseAlertDialog(this.context, R.string.not_bind_account, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.WithdrawalActivity.2
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putBoolean(Constant.FLAG, true);
                    WithdrawalActivity.this.startActForResult(MoneyAccountActivity.class, bundle1, 222);
                }
            }
        }, true).show();
    }

    public void pay(final int id, final String money) {
        this.payPwdDialog = new PayPwdDialog(this.context, new PayPwdDialog.OutPwdListener() { // from class: com.vsf2f.f2f.ui.user.WithdrawalActivity.3
            @Override // com.vsf2f.f2f.ui.dialog.PayPwdDialog.OutPwdListener
            public void outPwd(String password) {
                WithdrawalActivity.this.md5pwd = password;
                WithdrawalActivity.this.payRequest(id, password, money);
            }
        });
        this.payPwdDialog.show();
    }

    private void showDialog(String str) {
        WarnDialog warnDialog = new WarnDialog(this.context, str, getString(R.string.open_vip_now), true);
        warnDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.user.WithdrawalActivity.4
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                switch (flag) {
                    case 0:
                        WithdrawalActivity.this.startActForResult(UserVipActivity.class, 122);
                        return;
                    default:
                        return;
                }
            }
        });
        warnDialog.show();
    }

    public void payRequest(int id, String md5pwd, String money) {
        AjaxParams params = new AjaxParams();
        params.put("bankId", id);
        params.put("batchFee", money);
        try {
            params.put("payPassword", md5pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_DRAWING, ComUtil.getZCApi(this.context, getString(R.string.API_USER_DRAWING)), params, Integer.class);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 122) {
            this.level = 4;
        } else if (requestCode == 222) {
            requestAccountData();
        }
    }
}
