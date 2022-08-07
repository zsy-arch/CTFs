package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class SharingBuyDialog extends BaseDialog {
    private double money;
    private int num;
    private double price;

    public SharingBuyDialog(Context context, double money, double price, int num) {
        super(context);
        this.money = money;
        this.price = price;
        this.num = num;
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_sharing_buy;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(0.8f, -2.0f, 17);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        ((TextView) findViewById(R.id.sharing_buy_money)).setText(this.money + "");
        ((TextView) findViewById(R.id.sharing_buy_price)).setText(this.price + "");
        ((TextView) findViewById(R.id.sharing_buy_num)).setText(this.num + "");
        setOnClickListener(R.id.sharing_buy_btnConfirm);
        setOnClickListener(R.id.sharing_buy_btnClose);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.sharing_buy_btnClose /* 2131756366 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, -1);
                    return;
                }
                return;
            case R.id.sharing_buy_btnConfirm /* 2131756367 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
