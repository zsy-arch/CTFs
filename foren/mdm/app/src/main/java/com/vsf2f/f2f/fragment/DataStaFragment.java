package com.vsf2f.f2f.fragment;

import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseFragment;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShopFinanceBean;

/* loaded from: classes2.dex */
public class DataStaFragment extends BaseFragment {
    private int time;
    private TextView txt_havereceive;
    private TextView txt_notpay;
    private TextView txt_notpick;
    private TextView txt_notreceive;
    private TextView txt_notsend;
    private TextView txt_onfund;
    private TextView txt_onsaling;
    private TextView txt_savenumber;
    private TextView txt_undershelf;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_shop_data_fragment;
    }

    public void setTime(int days) {
        this.time = days;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        this.txt_notpay = (TextView) getView(R.id.txt_notpay);
        this.txt_onfund = (TextView) getView(R.id.txt_onfund);
        this.txt_notsend = (TextView) getView(R.id.txt_notsend);
        this.txt_notpick = (TextView) getView(R.id.txt_notpick);
        this.txt_onsaling = (TextView) getView(R.id.txt_onsaling);
        this.txt_undershelf = (TextView) getView(R.id.txt_undershelf);
        this.txt_savenumber = (TextView) getView(R.id.txt_savenumber);
        this.txt_notreceive = (TextView) getView(R.id.txt_notreceive);
        this.txt_havereceive = (TextView) getView(R.id.txt_havereceive);
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        AjaxParams params = new AjaxParams();
        getClient().get(R.string.API_SHOP_SELLER_ALL, ComUtil.getZCApi(this.context, getString(R.string.API_SHOP_SELLER_ALL) + "?time=" + this.time), params, ShopFinanceBean.class, false);
    }

    @Override // com.cdlinglu.common.BaseFragment, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        ShopFinanceBean financeBean = (ShopFinanceBean) result.getObj();
        if (financeBean != null) {
            updateUI(financeBean);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    public void updateUI(ShopFinanceBean financeBean) {
        this.txt_onsaling.setText(financeBean.getUpNum() != null ? financeBean.getUpNum() : "0");
        this.txt_undershelf.setText(financeBean.getDownNum() != null ? financeBean.getDownNum() : "0");
        this.txt_savenumber.setText(financeBean.getScNum() != null ? financeBean.getScNum() : "0");
        this.txt_notpay.setText(financeBean.getUnpayOrder() != null ? financeBean.getUnpayOrder() : "0");
        this.txt_notsend.setText(financeBean.getNotShipped() != null ? financeBean.getNotShipped() : "0");
        this.txt_notpick.setText(financeBean.getNotPicking() != null ? financeBean.getNotPicking() : "0");
        this.txt_onfund.setText(financeBean.getRefundNum() != null ? financeBean.getRefundNum() : "0");
        this.txt_notreceive.setText(financeBean.getNotConfirm() != null ? financeBean.getNotConfirm() : "0");
        this.txt_havereceive.setText(financeBean.getConfirmNum() != null ? financeBean.getConfirmNum() : "0");
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
    }

    @Override // com.hy.frame.common.IFragmentListener
    public void sendMsg(int i, Object o) {
    }
}
