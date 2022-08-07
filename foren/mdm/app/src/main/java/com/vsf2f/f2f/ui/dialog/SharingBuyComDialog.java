package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;

/* loaded from: classes2.dex */
public class SharingBuyComDialog extends BaseDialog {
    private TextView agree;
    private TextView buyCom;
    private EditText etNum;
    private EditText etPrice;
    private BuyComImpl listener;
    private int maxBuyNum;
    private double maxWorth;
    private int minBuyNum;
    private double minWorth;
    private TextView tvTotal;
    private double worth;

    /* loaded from: classes2.dex */
    public interface BuyComImpl {
        void buyCom(String str, String str2);
    }

    public SharingBuyComDialog(Context context, Double worth, Double minWorth, Double maxWorth, int minBuyNum, int maxBuyNum, BuyComImpl listener) {
        super(context);
        this.worth = worth.doubleValue();
        this.minWorth = minWorth.doubleValue();
        this.maxWorth = maxWorth.doubleValue();
        this.minBuyNum = minBuyNum;
        this.maxBuyNum = maxBuyNum;
        this.listener = listener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_sharing_buy_com;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(0.8f, -2.0f, 17);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.iv_close);
        this.agree = (TextView) getViewAndClick(R.id.regist_agree);
        this.agree.setSelected(true);
        this.buyCom = (TextView) getViewAndClick(R.id.sharing_buy_tvBuyCom);
        ((TextView) findViewById(R.id.sharing_buy_tvPrice)).setText(String.format("(当前参考价值)%s", HyUtil.formatToMoney(Double.valueOf(this.worth))));
        this.tvTotal = (TextView) findViewById(R.id.sharing_buy_tvTotal);
        this.etPrice = (EditText) findViewById(R.id.sharing_buy_etPrice);
        this.etPrice.setHint(String.format("请输入出价(%s~%s)", Double.valueOf(this.minWorth), Double.valueOf(this.maxWorth)));
        this.etNum = (EditText) findViewById(R.id.sharing_buy_etNum);
        this.etNum.setHint(String.format("申请区间%d~%d个", Integer.valueOf(this.minBuyNum), Integer.valueOf(this.maxBuyNum)));
        this.etNum.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.dialog.SharingBuyComDialog.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharingBuyComDialog.this.showTotal(charSequence.toString(), SharingBuyComDialog.this.etPrice.getText().toString());
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.etPrice.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.dialog.SharingBuyComDialog.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String price = charSequence.toString();
                SharingBuyComDialog.this.showTotal(SharingBuyComDialog.this.etNum.getText().toString(), price);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTotal(String num, String price) {
        if (TextUtils.isEmpty(num) || TextUtils.isEmpty(price)) {
            this.buyCom.setBackgroundResource(R.drawable.btn_red10_selector2);
            this.tvTotal.setText("0");
            return;
        }
        try {
            this.tvTotal.setText(HyUtil.formatToMoney(Double.valueOf(Double.parseDouble(price) * Double.parseDouble(num))));
            this.buyCom.setBackgroundResource(R.drawable.btn_red10_selector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        double price;
        int num;
        switch (v.getId()) {
            case R.id.regist_agree /* 2131755807 */:
                this.agree.setSelected(!this.agree.isSelected());
                return;
            case R.id.regist_agreement /* 2131755808 */:
                Bundle bundle = new Bundle();
                bundle.putString(Constant.FLAG, ComUtil.getZCApi(getContext(), getContext().getString(R.string.URL_HELP_COMMON_CODE, "gxb_apply_sell")));
                bundle.putBoolean(Constant.FLAG2, false);
                Intent intent = new Intent(getContext(), WebKitLocalActivity.class);
                intent.putExtra(Constant.BUNDLE, bundle);
                getContext().startActivity(intent);
                return;
            case R.id.iv_close /* 2131756368 */:
                dismiss();
                return;
            case R.id.sharing_buy_tvBuyCom /* 2131756373 */:
                if (!this.agree.isSelected()) {
                    MyToast.show(getContext(), "请同意收购协议");
                    return;
                } else if (this.worth == 0.0d) {
                    MyToast.show(getContext(), "获取当前价值失败");
                    return;
                } else {
                    String numStr = this.etNum.getText().toString().trim();
                    if (TextUtils.isEmpty(numStr)) {
                        MyToast.show(getContext(), "请输入收购数量");
                        return;
                    }
                    try {
                        num = Integer.parseInt(numStr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (num == 0 || num % 100 != 0) {
                        MyToast.show(getContext(), "请输入整百的数量");
                    } else {
                        if (num < this.minBuyNum) {
                            MyToast.show(getContext(), "收购数量不能少于" + this.minBuyNum + "个");
                        }
                        String priceStr = this.etPrice.getText().toString().trim();
                        if (TextUtils.isEmpty(priceStr)) {
                            MyToast.show(getContext(), "请输入收购价格");
                        } else {
                            try {
                                price = Double.parseDouble(priceStr);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            if (price < this.minWorth) {
                                MyToast.show(getContext(), "收购价格不能低于" + this.minWorth);
                            } else {
                                if (price > this.maxWorth) {
                                    MyToast.show(getContext(), "收购价格不能高于" + this.maxWorth);
                                }
                                this.listener.buyCom(numStr, numStr);
                            }
                        }
                    }
                    return;
                }
            default:
                return;
        }
    }
}
