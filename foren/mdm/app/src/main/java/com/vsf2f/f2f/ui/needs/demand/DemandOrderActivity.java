package com.vsf2f.f2f.ui.needs.demand;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.HorizontalTabView;
import com.cdlinglu.common.PullToRefreshView;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.em.ui.ChatActivity;
import com.google.gson.Gson;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.DemandOrderAdapter;
import com.vsf2f.f2f.adapter.OrderApplyAdapter;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.OrderDetailBean;
import com.vsf2f.f2f.bean.OrderListBean;
import com.vsf2f.f2f.ui.dialog.CancelOrderDialog;
import com.vsf2f.f2f.ui.dialog.SureChoiceDialog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DemandOrderActivity extends BaseActivity implements NavGroup.OnCheckedChangeListener, HorizontalTabView.TabClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener, DemandOrderAdapter.ItemClick, IAdapterListener {
    private OrderApplyAdapter applyAdapter;
    private CancelOrderDialog cancelDialog;
    private int doPosition;
    private int function;
    private HorizontalTabView htv_status;
    private LinearLayout ll_pay;
    private PullToRefreshView main_pull_refresh_view;
    private ListView mlv_demand;
    private DemandOrderAdapter orderAdapter;
    private SureChoiceDialog sureChoiceDialog;
    private TextView tv_checkall;
    private List<OrderDetailBean> datas = new ArrayList();
    private int type = 0;
    private int pageNo = 1;
    private int maxPage = 1;
    private int pageSize = 10;
    private int statusType = 0;
    private String[] one = {"全部", "进行中", "待确认", "待评价", "退款中", "已完成"};
    private String[] two = {"全部", "已申请", "进行中", "服务中", "服务结束", "退款中", "已完成"};
    private boolean isCheckAll = false;
    private List<OrderDetailBean> selectedList = new ArrayList();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_demand_manage;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.tab_demand_, 0);
        this.ll_pay = (LinearLayout) getView(R.id.ll_pay);
        this.tv_checkall = (TextView) getViewAndClick(R.id.tv_checkall);
        setOnClickListener(R.id.tv_cacel_order);
        setOnClickListener(R.id.tv_payall);
        NavGroup groupFooter = (NavGroup) getView(R.id.main_groupFooter);
        groupFooter.setOnCheckedChangeListener(this);
        this.htv_status = (HorizontalTabView) getView(R.id.htv_status);
        this.htv_status.setTabArray(this.one);
        this.htv_status.setTabClickListener(this);
        this.main_pull_refresh_view = (PullToRefreshView) getView(R.id.main_pull_refresh_view);
        this.main_pull_refresh_view.setOnHeaderRefreshListener(this);
        this.main_pull_refresh_view.setOnFooterRefreshListener(this);
        this.mlv_demand = (ListView) getView(R.id.mlv_demand);
        if (getBundle().getBoolean("type")) {
            groupFooter.setCheckedChildByPosition(2);
        }
        initDialog();
    }

    public void initDialog() {
        this.sureChoiceDialog = new SureChoiceDialog(this, this);
        this.cancelDialog = new CancelOrderDialog(this, new CancelOrderDialog.CancelOrderImpl() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandOrderActivity.1
            @Override // com.vsf2f.f2f.ui.dialog.CancelOrderDialog.CancelOrderImpl
            public void cancelOrder(String checkReason) {
                DemandOrderActivity.this.closeOrder(checkReason);
            }
        });
        this.cancelDialog.setIsService(false);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.pageNo = 1;
        this.datas.clear();
        getServiceOrDemand();
    }

    private void getServiceOrDemand() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_QUERY_ORDERS, ComUtil.getXDDApi(this.context, getString(R.string.API_QUERY_ORDERS)) + "?orderType=" + this.type + "&statusType=" + this.statusType + "&pageNo=" + this.pageNo + "&pageSize=" + this.pageSize, OrderListBean.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeOrder(String reason) {
        AjaxParams params = new AjaxParams();
        params.put("shareOrderId", this.datas.get(this.doPosition).getMoId());
        params.put("closeReason", reason);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_CLOSE_REWARD, ComUtil.getXDDApi(this.context, getString(R.string.API_CLOSE_REWARD)), params, String.class);
    }

    private void completeOrder() {
        AjaxParams params = new AjaxParams();
        params.put("shareOrderId", this.datas.get(this.doPosition).getMoId());
        params.put("shareId", this.datas.get(this.doPosition).getShareObj().getMoId());
        getClient().setShowDialog(true);
        getClient().post(R.string.API_COMPLETE_ORDER, ComUtil.getXDDApi(this.context, getString(R.string.API_COMPLETE_ORDER)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        this.main_pull_refresh_view.onFooterRefreshComplete();
        this.main_pull_refresh_view.onHeaderRefreshComplete();
        if (result.getErrorCode() == 0) {
            switch (result.getRequestCode()) {
                case R.string.API_CLOSE_REWARD /* 2131296313 */:
                    MyToast.show(getApplicationContext(), "订单取消成功");
                    refresh();
                    return;
                case R.string.API_COMPLETE_ORDER /* 2131296316 */:
                    MyToast.show(getApplicationContext(), "申请成功，等待对方确认");
                    refresh();
                    return;
                case R.string.API_CONFIRM_ORDER /* 2131296317 */:
                    MyToast.show(getApplicationContext(), "确认成功");
                    refresh();
                    return;
                case R.string.API_QUERY_ORDERS /* 2131296394 */:
                    OrderListBean listBean = (OrderListBean) result.getObj();
                    this.maxPage = listBean.getTotalPage();
                    if (this.maxPage == 0) {
                        Toast.makeText(this.context, "没有需求订单", 1).show();
                    } else if (this.pageNo <= this.maxPage) {
                        if (this.pageNo == 1) {
                            this.datas.clear();
                        }
                        this.datas.addAll(listBean.getDatas());
                    } else {
                        Toast.makeText(this.context, "已经到底了", 0).show();
                        return;
                    }
                    updateUI();
                    return;
                default:
                    return;
            }
        } else {
            MyToast.show(getApplicationContext(), result.getMsg());
        }
    }

    private void refresh() {
        this.pageNo = 1;
        this.datas.clear();
        if (this.orderAdapter != null) {
            this.orderAdapter.notifyDataSetChanged();
        }
        getServiceOrDemand();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.main_pull_refresh_view.onFooterRefreshComplete();
        this.main_pull_refresh_view.onHeaderRefreshComplete();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.type == 1 && this.statusType == 1) {
            if (this.applyAdapter == null) {
                this.applyAdapter = new OrderApplyAdapter(this.context, this.datas);
                this.applyAdapter.setListener(this);
            }
            if (this.mlv_demand.getAdapter() != this.applyAdapter) {
                this.mlv_demand.setAdapter((ListAdapter) this.applyAdapter);
            }
            this.applyAdapter.setDatas(this.datas);
            this.applyAdapter.notifyDataSetChanged();
            return;
        }
        if (this.orderAdapter == null) {
            this.orderAdapter = new DemandOrderAdapter(this.context, this.datas, this);
            this.orderAdapter.setListener(this);
            this.orderAdapter.setStatusType(this.statusType);
            this.orderAdapter.setType(this.type);
        } else {
            this.orderAdapter.setDatas(this.datas);
            this.orderAdapter.setStatusType(this.statusType);
            this.orderAdapter.notifyDataSetChanged();
        }
        if (this.mlv_demand.getAdapter() != this.orderAdapter) {
            this.mlv_demand.setAdapter((ListAdapter) this.orderAdapter);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        this.sureChoiceDialog.dismiss();
        switch (v.getId()) {
            case R.id.tv_checkall /* 2131755958 */:
                checkAll();
                return;
            case R.id.tv_cacel_order /* 2131755959 */:
                this.cancelDialog.show();
                return;
            case R.id.btn_sure /* 2131756169 */:
                if (this.function == 2) {
                    getClient().setShowDialog(true);
                    AjaxParams params = new AjaxParams();
                    params.put("shareId", this.datas.get(this.doPosition).getShareObj().getMoId());
                    params.put("shareOrderId", this.datas.get(this.doPosition).getMoId());
                    getClient().post(R.string.API_CONFIRM_ORDER, ComUtil.getXDDApi(this.context, getString(R.string.API_CONFIRM_ORDER)), params, String.class);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void checkAll() {
        this.isCheckAll = !this.isCheckAll;
        for (OrderDetailBean bean : this.datas) {
            bean.setSelectedPay(this.isCheckAll);
        }
        if (this.orderAdapter != null) {
            this.orderAdapter.setDatas(this.datas);
            this.orderAdapter.notifyDataSetChanged();
        }
        this.tv_checkall.setSelected(this.isCheckAll);
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, @IdRes int checkedId) {
        this.pageNo = 1;
        this.maxPage = 1;
        this.statusType = 0;
        if (nav.getId() == R.id.nv_1) {
            this.type = 0;
            this.htv_status.setTabArray(this.one);
            this.htv_status.scrollTo(0, 0);
        } else {
            this.type = 1;
            this.htv_status.setTabArray(this.two);
            this.htv_status.scrollTo(0, 0);
        }
        this.datas.clear();
        if (this.orderAdapter != null) {
            this.orderAdapter.setType(this.type);
            this.orderAdapter.notifyDataSetChanged();
        }
        getServiceOrDemand();
    }

    @Override // com.cdlinglu.common.HorizontalTabView.TabClickListener
    public void tabClick(int index) {
        this.pageNo = 1;
        this.statusType = index;
        this.datas.clear();
        if (this.orderAdapter != null) {
            this.orderAdapter.setStatusType(index);
            this.orderAdapter.notifyDataSetChanged();
        }
        this.isCheckAll = false;
        this.tv_checkall.setSelected(false);
        getServiceOrDemand();
    }

    @Override // com.cdlinglu.common.PullToRefreshView.OnFooterRefreshListener
    public void onFooterRefresh(PullToRefreshView view) {
        this.pageNo++;
        getServiceOrDemand();
    }

    @Override // com.cdlinglu.common.PullToRefreshView.OnHeaderRefreshListener
    public void onHeaderRefresh(PullToRefreshView view) {
        this.pageNo = 1;
        this.datas.clear();
        if (this.orderAdapter != null) {
            this.orderAdapter.notifyDataSetChanged();
        }
        getServiceOrDemand();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.vsf2f.f2f.adapter.DemandOrderAdapter.ItemClick
    public void doFunction(int position, int function) {
        this.function = function;
        this.doPosition = position;
        if (!HyUtil.isEmpty(this.datas) && position >= 0 && position < this.datas.size()) {
            Bundle bundle = new Bundle();
            switch (function) {
                case 0:
                    this.cancelDialog.show();
                    return;
                case 1:
                    bundle.putInt("moId", this.datas.get(position).getMoId());
                    startAct(RefundActivity.class, bundle);
                    return;
                case 2:
                    sureOrder();
                    return;
                case 3:
                    bundle.putInt("type", this.type);
                    bundle.putSerializable("data", this.datas.get(position));
                    startAct(CommentOrderActivity.class, bundle);
                    return;
                case 4:
                    bundle.putInt("id", this.datas.get(position).getMoId());
                    startAct(StartServiceActivity.class, bundle);
                    return;
                case 5:
                    completeOrder();
                    return;
                case 6:
                    bundle.putBoolean("deal", true);
                    break;
                case 7:
                    break;
                case 8:
                case 9:
                default:
                    return;
                case 10:
                    Gson gson = new Gson();
                    OrderDetailBean orderDetail = this.datas.get(position);
                    DemandDetailBean beanTemp = (DemandDetailBean) gson.fromJson(gson.toJson(orderDetail.getShareObj()), (Class<Object>) DemandDetailBean.class);
                    beanTemp.setReward(orderDetail.getAmount() + "");
                    beanTemp.setDemandSnapShotId(orderDetail.getShareSnapshotId());
                    bundle.putSerializable("needinfo", beanTemp);
                    bundle.putString(Constant.FLAG_TYPE, getClass().getName());
                    startAct(EntrustActivity.class, bundle);
                    return;
            }
            bundle.putInt("type", this.type);
            bundle.putSerializable("data", this.datas.get(position));
            startAct(DealDisputeRefundActivity.class, bundle);
        }
    }

    @Override // com.vsf2f.f2f.adapter.DemandOrderAdapter.ItemClick
    public void chat(int position) {
        String username;
        Bundle bundle = new Bundle();
        if (this.type == 0) {
            username = this.datas.get(position).getShareObj().getServiceUserObj().getUserName();
        } else {
            username = this.datas.get(position).getShareObj().getPublishUserObj().getUserName();
        }
        bundle.putString("username", username);
        bundle.putBoolean(EaseConstant.BACK_TYPE, false);
        startAct(ChatActivity.class, bundle);
    }

    @Override // com.vsf2f.f2f.adapter.DemandOrderAdapter.ItemClick
    public void doSelect(OrderDetailBean bean) {
        if (this.selectedList.contains(bean)) {
            this.selectedList.remove(bean);
        } else {
            this.selectedList.add(bean);
        }
        this.isCheckAll = this.selectedList.size() == this.datas.size();
        this.tv_checkall.setSelected(this.isCheckAll);
        if (this.orderAdapter != null) {
            this.orderAdapter.setDatas(this.datas);
            this.orderAdapter.notifyDataSetChanged();
        }
    }

    private void sureOrder() {
        this.sureChoiceDialog.setContent("提示", "是否确认服务：" + this.datas.get(this.doPosition).getMoId());
        this.sureChoiceDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.htv_status.removeAllViews();
        this.htv_status = null;
        this.one = null;
        this.two = null;
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object obj, int position) {
        OrderDetailBean bean = (OrderDetailBean) obj;
        Bundle bundle = new Bundle();
        if (this.type == 1 && this.statusType == 1) {
            bundle.putInt("id", bean.getShareObj().getMoId());
            startAct(DemandInfoActivity.class, bundle);
            return;
        }
        bundle.putInt("id", bean.getMoId());
        startAct(OrderDetailActivity.class, bundle);
    }
}
