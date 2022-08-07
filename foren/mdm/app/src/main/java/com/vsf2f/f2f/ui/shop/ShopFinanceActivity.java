package com.vsf2f.f2f.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShopFinanceBean;
import com.vsf2f.f2f.ui.user.WithdrawalActivity;

/* loaded from: classes2.dex */
public class ShopFinanceActivity extends BaseActivity {
    private ShopFinanceBean financeBean;
    private TextView txtbanalce;
    private TextView txtrebatesale;
    private TextView txttotalsale;
    private TextView txttranslate;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_shop_finance;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.finance_title, 0);
        this.txttranslate = (TextView) getView(R.id.txt_translateing);
        this.txttotalsale = (TextView) getView(R.id.txt_totalsale);
        this.txtrebatesale = (TextView) getView(R.id.txt_rebatesale);
        this.txtbanalce = (TextView) getView(R.id.user_fnc_txtBalance);
        setOnClickListener(R.id.user_fnc_btnWithdraw);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_SHOP_SELLER_SALE, ComUtil.getZCApi(this.context, getString(R.string.API_SHOP_SELLER_SALE)), ShopFinanceBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        this.financeBean = (ShopFinanceBean) result.getObj();
        updateUI();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.financeBean != null) {
            this.txtbanalce.setText(HyUtil.removeNumberZero(this.financeBean.getCash()) + getString(R.string.money));
            this.txttotalsale.setText(HyUtil.removeNumberZero(this.financeBean.getSalesNum()) + getString(R.string.money));
            this.txttranslate.setText(HyUtil.removeNumberZero(this.financeBean.getTradingNum()) + getString(R.string.money));
            this.txtrebatesale.setText(HyUtil.removeNumberZero(this.financeBean.getShoppingProfit()) + getString(R.string.money));
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.user_fnc_btnWithdraw /* 2131755663 */:
                startActForResult(WithdrawalActivity.class, new Bundle(), 999);
                return;
            default:
                return;
        }
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
