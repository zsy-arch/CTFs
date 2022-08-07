package com.vsf2f.f2f.ui.needs.demand;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.easeui.widget.EaseAlertDialog;
import com.em.ui.ChatActivity;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.ServerListAdapter;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.OfferList;
import com.vsf2f.f2f.ui.dialog.SureChoiceDialog;

/* loaded from: classes2.dex */
public class ChoiceServerActivity extends BaseActivity implements ServerListAdapter.AdapterClickListener {
    private ServerListAdapter adapter;
    private String demandId;
    private DemandDetailBean detailBean;
    private int funtionType;
    private OfferList offer;
    private String reward;
    private int serviceOrderId;
    private SureChoiceDialog sureChoiceDialog;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_choice_server;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.title_choice_server, 0);
        this.sureChoiceDialog = new SureChoiceDialog(this, this);
        this.detailBean = (DemandDetailBean) getBundle().getSerializable("data");
        if (this.detailBean == null) {
            int moId = getBundle().getInt("id");
            this.detailBean = new DemandDetailBean();
            getDetailInfo(moId);
            return;
        }
        updateUI();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        int status = this.detailBean.getStatus();
        if (status >= 20 && status <= 23) {
            new EaseAlertDialog((Context) this, (String) null, "该需求已选择服务商√", (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.demand.ChoiceServerActivity.1
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    ChoiceServerActivity.this.finish();
                }
            }, false).show();
        } else if (status >= 30) {
            new EaseAlertDialog((Context) this, (String) null, "需求已过期", (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.demand.ChoiceServerActivity.2
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    ChoiceServerActivity.this.finish();
                }
            }, false).show();
        } else {
            this.demandId = this.detailBean.getMoId() + "";
            this.adapter = new ServerListAdapter(this.detailBean.getOfferList(), this.detailBean.getReward(), this);
            ((ListView) getView(R.id.lv_server)).setAdapter((ListAdapter) this.adapter);
        }
    }

    private void getDetailInfo(int moId) {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_DEMAND_DETAIL, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_DETAIL)) + "?moId=" + moId, DemandDetailBean.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure /* 2131756169 */:
                this.sureChoiceDialog.dismiss();
                if (this.funtionType == 0) {
                    ignoreDemand();
                    return;
                } else if (this.funtionType != 1) {
                    return;
                } else {
                    if (this.detailBean.getStatus() == 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.FLAG_TYPE, getClass().getName());
                        bundle.putSerializable("needinfo", this.detailBean);
                        startAct(EntrustActivity.class, bundle);
                        return;
                    }
                    submitOrder();
                    return;
                }
            case R.id.btn_cancel /* 2131756208 */:
                this.sureChoiceDialog.dismiss();
                return;
            default:
                return;
        }
    }

    private void submitOrder() {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("shareId", this.demandId);
        params.put("serviceOrderId", this.serviceOrderId);
        getClient().post(R.string.API_DEMAND_ORDER_SUBMIT, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_ORDER_SUBMIT)), params, String.class);
    }

    private void ignoreDemand() {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("grabOrderId", this.serviceOrderId);
        getClient().post(R.string.API_DEMAND_IGNORESP, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_IGNORESP)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        if (result.getErrorCode() == 0) {
            switch (result.getRequestCode()) {
                case R.string.API_DEMAND_DETAIL /* 2131296324 */:
                    this.detailBean = (DemandDetailBean) result.getObj();
                    updateUI();
                    return;
                case R.string.API_DEMAND_HELP /* 2131296325 */:
                case R.string.API_DEMAND_HOMEPAGE /* 2131296326 */:
                case R.string.API_DEMAND_OFFER /* 2131296328 */:
                default:
                    return;
                case R.string.API_DEMAND_IGNORESP /* 2131296327 */:
                    MyToast.show(getApp(), "忽略成功");
                    this.offer.setStatus(2);
                    this.adapter.setData(this.detailBean.getOfferList());
                    this.adapter.notifyDataSetChanged();
                    return;
                case R.string.API_DEMAND_ORDER_SUBMIT /* 2131296329 */:
                    Bundle bundle = new Bundle();
                    bundle.putInt("title", R.string.title_choice_server);
                    bundle.putInt("msg", R.string.choice_server_success);
                    bundle.putInt(com.vsf2f.f2f.ui.utils.Constant.SUB_MSG, R.string.wait_choice_server);
                    startAct(CommentSuccessActivity.class, bundle);
                    finish();
                    return;
            }
        } else {
            MyToast.show(getApp(), result.getMsg() + "");
        }
    }

    @Override // com.vsf2f.f2f.adapter.ServerListAdapter.AdapterClickListener
    public void clickListener(int funtion, int position) {
        this.offer = this.detailBean.getOfferList().get(position);
        this.serviceOrderId = this.offer.getMoId();
        this.reward = this.offer.getReward();
        switch (funtion) {
            case 0:
                this.funtionType = 0;
                this.sureChoiceDialog.setContent("提示", "确定忽略服务商：" + this.offer.getServiceUserObj().getNickName());
                this.sureChoiceDialog.show();
                return;
            case 1:
                this.funtionType = 1;
                this.sureChoiceDialog.setContent("提示", "确定选择服务商：" + this.offer.getServiceUserObj().getNickName());
                this.sureChoiceDialog.show();
                return;
            case 2:
                this.funtionType = 2;
                Bundle bun = new Bundle();
                bun.putSerializable("username", this.offer.getServiceUserObj().getUserName());
                bun.putBoolean(EaseConstant.BACK_TYPE, false);
                startAct(ChatActivity.class, bun);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.sureChoiceDialog.isShowing()) {
            this.sureChoiceDialog.dismiss();
        }
    }
}
