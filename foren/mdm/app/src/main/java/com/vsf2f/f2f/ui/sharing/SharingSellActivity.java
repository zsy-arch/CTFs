package com.vsf2f.f2f.ui.sharing;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.ImgWarnDialog;
import com.vsf2f.f2f.ui.dialog.PayPwdDialog;
import com.vsf2f.f2f.ui.dialog.WarnDialog;
import com.vsf2f.f2f.ui.user.UserVipActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.FingerUtil;

/* loaded from: classes2.dex */
public class SharingSellActivity extends BaseActivity {
    private TextView btnSell;
    private EditText etNum;
    private EditText etPrice;
    private double maxworth;
    private double minworth;
    private PayPwdDialog pwdDialog;
    private int sharingEnable;
    private TextView tvTotal;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_sharing_sell;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.sharing_title_sell, R.drawable.icon_help_white);
        this.btnSell = (TextView) getViewAndClick(R.id.sharing_btnSell);
        this.tvTotal = (TextView) findViewById(R.id.sharing_sell_tvTotal);
        this.etNum = (EditText) findViewById(R.id.sharing_sell_etNum);
        this.etNum.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.sharing.SharingSellActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String num = charSequence.toString();
                String price = SharingSellActivity.this.etPrice.getText().toString();
                if (TextUtils.isEmpty(price) || TextUtils.isEmpty(charSequence)) {
                    SharingSellActivity.this.btnSell.setEnabled(false);
                    SharingSellActivity.this.tvTotal.setText("0");
                    return;
                }
                try {
                    SharingSellActivity.this.tvTotal.setText(HyUtil.formatToMoney(Double.valueOf(Double.parseDouble(price) * Double.parseDouble(num))));
                    SharingSellActivity.this.btnSell.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.etPrice = (EditText) findViewById(R.id.sharing_sell_etPrice);
        this.etPrice.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.sharing.SharingSellActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String price = charSequence.toString();
                String num = SharingSellActivity.this.etNum.getText().toString();
                if (TextUtils.isEmpty(price) || TextUtils.isEmpty(charSequence)) {
                    SharingSellActivity.this.btnSell.setEnabled(false);
                    SharingSellActivity.this.tvTotal.setText("0");
                    return;
                }
                try {
                    SharingSellActivity.this.tvTotal.setText(HyUtil.formatToMoney(Double.valueOf(Double.parseDouble(price) * Double.parseDouble(num))));
                    SharingSellActivity.this.btnSell.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.sharingEnable = UserShared.getInstance().readShareMoney().getShareMoneyEnable();
        double worth = getBundle().getDouble("worth");
        this.minworth = getBundle().getDouble("minWorth");
        this.maxworth = getBundle().getDouble("maxWorth");
        ((TextView) findViewById(R.id.sharing_sell_tvWorth)).setText(HyUtil.formatToMoney(Double.valueOf(worth)));
        this.etPrice.setHint(this.minworth + "~" + this.maxworth);
        ((TextView) findViewById(R.id.sharing_sell_tvEnable)).setText(this.sharingEnable + "");
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_SHARING_SELL /* 2131296429 */:
                this.pwdDialog.saveDismiss();
                setResult(-1);
                FingerUtil.showOpenFinger(this.context, new FingerUtil.overListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingSellActivity.3
                    @Override // com.vsf2f.f2f.ui.utils.FingerUtil.overListener
                    public void over() {
                        new ImgWarnDialog(SharingSellActivity.this.context, R.drawable.icon_ok, SharingSellActivity.this.getString(R.string.already_request_sell), "我知道了").setDisListener(new DialogInterface.OnDismissListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingSellActivity.3.1
                            @Override // android.content.DialogInterface.OnDismissListener
                            public void onDismiss(DialogInterface dialogInterface) {
                                SharingSellActivity.this.finish();
                            }
                        }).show();
                    }
                });
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestSell(String num, String price, String pwdMd5) {
        AjaxParams params = new AjaxParams();
        params.put("num", Integer.parseInt(num) + "");
        params.put(f.aS, Double.parseDouble(price) + "");
        params.put("userName", DemoHelper.getInstance().getCurrentUserName());
        params.put("payPwd", pwdMd5);
        params.put("timestamp", System.currentTimeMillis() + "");
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().setShowDialog(true);
        getClient().post(R.string.API_SHARING_SELL, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_SELL)), params, String.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.sharing_btnSell /* 2131755641 */:
                if (getUserInfo().getLv() == 0) {
                    showWarnDlg();
                    return;
                }
                final String numStr = this.etNum.getText().toString();
                if (!TextUtils.isEmpty(numStr)) {
                    try {
                        int num = Integer.parseInt(numStr);
                        if (num == 0 || num % 100 != 0) {
                            MyToast.show(this.context, "请输入整百的数量");
                        } else if (num > this.sharingEnable) {
                            MyToast.show(this.context, "可用共享宝数量不足");
                        } else {
                            final String price = this.etPrice.getText().toString();
                            if (TextUtils.isEmpty(price)) {
                                MyToast.show(this.context, "价格不能为0");
                            } else {
                                try {
                                    double price2 = Double.parseDouble(price);
                                    if (price2 == 0.0d) {
                                        MyToast.show(this.context, "出让价格不能为0");
                                    } else if (price2 < this.minworth) {
                                        MyToast.show(this.context, "出让价格不能低于参考价");
                                    } else {
                                        this.pwdDialog = new PayPwdDialog(this.context, new PayPwdDialog.OutPwdListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingSellActivity.4
                                            @Override // com.vsf2f.f2f.ui.dialog.PayPwdDialog.OutPwdListener
                                            public void outPwd(String password) {
                                                SharingSellActivity.this.requestSell(numStr, price, password);
                                            }
                                        });
                                        this.pwdDialog.show();
                                    }
                                } catch (Exception e) {
                                    MyToast.show(this.context, "出让价格不规范");
                                }
                            }
                        }
                        return;
                    } catch (Exception e2) {
                        MyToast.show(this.context, "数量不规范");
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void showWarnDlg() {
        WarnDialog warnDialog = new WarnDialog(this.context, getString(R.string.not_open_pms_prompt3), getString(R.string.open_vip_now), true, true, false);
        warnDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingSellActivity.5
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                switch (flag) {
                    case 0:
                        SharingSellActivity.this.startAct(UserVipActivity.class);
                        return;
                    default:
                        return;
                }
            }
        });
        warnDialog.show();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG_TITLE, getString(R.string.sharing_title_sell));
        bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getString(R.string.URL_HELP_COMMON_CODE, new Object[]{"gxb_apply_sell"})));
        bundle.putBoolean(Constant.FLAG2, false);
        startAct(WebKitLocalActivity.class, bundle);
    }
}
