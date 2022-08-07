package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.DealRefundAdapter;
import com.vsf2f.f2f.bean.OrderDetailBean;
import com.vsf2f.f2f.ui.view.MyListview;

/* loaded from: classes2.dex */
public class DealDisputeRefundActivity extends BaseActivity {
    private KeyValueView _kvCreateName;
    private KeyValueView _kvCreateTime;
    private KeyValueView _kvDemandType;
    private KeyValueView _kvPrice;
    private LinearLayout _llyDemand;
    private LinearLayout _llyService;
    private MyListview _mlvRecord;
    private boolean isService;
    private OrderDetailBean orderDetailBean;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_deal_dispute_refund;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this._llyService = (LinearLayout) getView(R.id.refund_llyService);
        this._llyDemand = (LinearLayout) getView(R.id.refund_llyDemand);
        this._mlvRecord = (MyListview) getView(R.id.refund_mlvRecord);
        this._kvCreateName = (KeyValueView) getView(R.id.refund_kvCreateName);
        this._kvCreateTime = (KeyValueView) getView(R.id.refund_kvCreateTime);
        this._kvPrice = (KeyValueView) getView(R.id.refund_kvPrice);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.isService = getBundle().getBoolean("isService");
        this.orderDetailBean = (OrderDetailBean) getBundle().getSerializable("data");
        if (getBundle().getBoolean("deal", false)) {
            initHeaderBackTxt(R.string.deal_title, 0);
            int type = getBundle().getInt("type", 0);
            if (this.isService) {
                type = (type + 1) % 2;
            }
            if (type == 0) {
                this._llyService.setVisibility(8);
                this._llyDemand.setVisibility(0);
                setOnClickListener(R.id.refund_over);
                setOnClickListener(R.id.refund_retry);
                setOnClickListener(R.id.refund_official);
                return;
            }
            this._llyService.setVisibility(0);
            this._llyDemand.setVisibility(8);
            setOnClickListener(R.id.refund_agree);
            setOnClickListener(R.id.refund_refuse);
            return;
        }
        initHeaderBackTxt(R.string.refund_info, 0);
        this._llyService.setVisibility(8);
        this._llyDemand.setVisibility(8);
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        getDetail();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.orderDetailBean != null) {
            this._kvCreateName.setValue(this.orderDetailBean.getShareObj().getPublishUser() + "");
            this._kvCreateTime.setValue(this.orderDetailBean.getShareObj().getPublishTime() + "");
            this._kvPrice.setValue("￥" + this.orderDetailBean.getAmount());
            if (HyUtil.isNoEmpty(this.orderDetailBean.getRefundRecordList())) {
                try {
                    int size = this.orderDetailBean.getRefundRecordList().size();
                    this._kvCreateName.setValue(this.orderDetailBean.getRefundRecordList().get(size - 1).getCreatorObj().getNickName());
                    this._kvCreateTime.setValue(this.orderDetailBean.getRefundRecordList().get(size - 1).getOperationTime());
                } catch (Exception e) {
                    HyUtil.printException(e);
                }
                this._mlvRecord.setAdapter((ListAdapter) new DealRefundAdapter(this.context, this.orderDetailBean.getRefundRecordList()));
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.refund_agree /* 2131755914 */:
                new EaseAlertDialog(this, R.string.sure_agree_refund, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DealDisputeRefundActivity.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            DealDisputeRefundActivity.this.agree();
                        }
                    }
                }, true).show();
                return;
            case R.id.refund_refuse /* 2131755915 */:
                refuse();
                return;
            case R.id.refund_llyDemand /* 2131755916 */:
            default:
                return;
            case R.id.refund_over /* 2131755917 */:
                new EaseAlertDialog(this, R.string.sure_over_order, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DealDisputeRefundActivity.2
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            DealDisputeRefundActivity.this.sureFinished();
                        }
                    }
                }, true).show();
                return;
            case R.id.refund_retry /* 2131755918 */:
                retry();
                return;
            case R.id.refund_official /* 2131755919 */:
                applyPlate();
                return;
        }
    }

    private void getDetail() {
        getClient().setShowDialog(true);
        if (!this.isService) {
            getClient().get(R.string.API_ORDER_DETAIL, ComUtil.getXDDApi(this.context, getString(R.string.API_ORDER_DETAIL)) + "?shareOrderId=" + this.orderDetailBean.getMoId(), OrderDetailBean.class);
            return;
        }
        getClient().get(R.string.API_ORDER_SERVICE, ComUtil.getXDDApi(this.context, getString(R.string.API_ORDER_SERVICE)) + "?shareServiceOrderId=" + this.orderDetailBean.getMoId(), OrderDetailBean.class);
    }

    private void retry() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isService", this.isService);
        bundle.putInt("type", 1);
        bundle.putInt("moId", this.orderDetailBean.getMoId());
        startActForResult(RefundActivity.class, bundle, 111);
    }

    private void applyPlate() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isService", this.isService);
        bundle.putInt("type", 3);
        bundle.putInt("moId", this.orderDetailBean.getMoId());
        startActForResult(RefundActivity.class, bundle, 111);
    }

    private void refuse() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", this.orderDetailBean.getMoId());
        bundle.putBoolean("isService", this.isService);
        bundle.putString("amount", this.orderDetailBean.getAmount() + "");
        startActForResult(RefuseRefundActivity.class, bundle, 222);
    }

    public void agree() {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        if (!this.isService) {
            params.put("shareOrderId", this.orderDetailBean.getMoId());
            getClient().post(R.string.API_AGREE_REFUND, ComUtil.getXDDApi(this.context, getString(R.string.API_AGREE_REFUND)), params, String.class);
            return;
        }
        params.put("shareServiceOrderId", this.orderDetailBean.getMoId());
        getClient().post(R.string.API_AGREE_REFUND, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_AGREEREFUND)), params, String.class);
    }

    public void sureFinished() {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        if (!this.isService) {
            params.put("shareId", this.orderDetailBean.getShareObj().getMoId());
            params.put("shareOrderId", this.orderDetailBean.getMoId());
            getClient().post(R.string.API_CONFIRM_ORDER, ComUtil.getXDDApi(this.context, getString(R.string.API_CONFIRM_ORDER)), params, String.class);
            return;
        }
        params.put("shareServiceOrderId", this.orderDetailBean.getMoId());
        getClient().post(R.string.API_SERVICE_CONFIRMORDER, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_CONFIRMORDER)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_AGREE_REFUND /* 2131296289 */:
            case R.string.API_SERVICE_AGREEREFUND /* 2131296401 */:
                MyToast.show(getApplicationContext(), "已同意退款");
                finish();
                return;
            case R.string.API_CONFIRM_ORDER /* 2131296317 */:
            case R.string.API_SERVICE_CONFIRMORDER /* 2131296411 */:
                MyToast.show(getApplicationContext(), "确认服务完成成功");
                finish();
                return;
            case R.string.API_ORDER_DETAIL /* 2131296386 */:
            case R.string.API_ORDER_SERVICE /* 2131296387 */:
                this.orderDetailBean = (OrderDetailBean) result.getObj();
                updateUI();
                return;
            default:
                return;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 111) {
            this._llyDemand.setVisibility(8);
        } else if (requestCode == 222) {
            this._llyService.setVisibility(8);
        }
    }
}
