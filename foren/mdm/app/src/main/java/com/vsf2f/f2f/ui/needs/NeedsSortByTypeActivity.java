package com.vsf2f.f2f.ui.needs;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.server.audio.VoicePlayer;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.DemandMainRecycleAdapter;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandServiceBean;
import com.vsf2f.f2f.ui.dialog.DemandBrushDialog;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NeedsSortByTypeActivity extends BaseActivity implements NavGroup.OnCheckedChangeListener, XRefreshViewListener, DemandMainRecycleAdapter.AdapterItemClick, DemandBrushDialog.BrushType {
    private DemandMainRecycleAdapter adapter;
    private DemandBrushDialog demandBrushDialog;
    private Boolean isService;
    private String latitude;
    private String longitude;
    private RefreshRecyclerView mlv_demand;
    private NavView nv_servicetype;
    private NavView nv_smart;
    private int orderType;
    private int serviceType;
    private int shareTypeIndex;
    private NavGroup sub_groupFooter;
    private String typeId;
    private int page = 1;
    private List<DemandDetailBean> dataList = new ArrayList();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_demand_sort_by_type;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        String typeName;
        initHeaderBack(R.string.title, 0);
        this.typeId = getBundle().getString("id");
        this.isService = Boolean.valueOf(getBundle().getBoolean("isService"));
        this.shareTypeIndex = getBundle().getInt("shareTypeIndex", 1);
        String typeName2 = getBundle().getString("name");
        if (this.isService.booleanValue()) {
            typeName = "服务/共享-" + typeName2;
        } else {
            typeName = "需求-" + typeName2;
        }
        setTitle(typeName);
        this.latitude = getBundle().getString("latitude");
        this.longitude = getBundle().getString("longitude");
        this.sub_groupFooter = (NavGroup) getView(R.id.sub_groupFooter);
        this.sub_groupFooter.setCanClickCurrent();
        this.sub_groupFooter.setOnCheckedChangeListener(this);
        this.demandBrushDialog = new DemandBrushDialog(this, this);
        this.nv_smart = (NavView) getView(R.id.nv_smart);
        this.nv_servicetype = (NavView) getView(R.id.nv_servicetype);
        this.mlv_demand = (RefreshRecyclerView) getView(R.id.mlv_demand);
        this.mlv_demand.setOnRefreshListener(this);
        this.adapter = new DemandMainRecycleAdapter(this.dataList, this);
        this.mlv_demand.setAdapter(this.adapter);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.page = 1;
        getDemandOrService(0, 2);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.nv_smart /* 2131755983 */:
                this.demandBrushDialog.setBrushType(0);
                break;
            case R.id.nv_servicetype /* 2131755984 */:
                this.demandBrushDialog.setBrushType(1);
                break;
        }
        this.demandBrushDialog.showAsDropDown(nav);
    }

    private void getDemandOrService(int orderBy, int serviceMode) {
        String argument;
        this.orderType = orderBy;
        this.serviceType = serviceMode;
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        if (this.isService.booleanValue()) {
            argument = ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_HOMEPAGE));
        } else {
            argument = ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_HOMEPAGE));
        }
        getClient().get(R.string.API_DEMAND_HOMEPAGE, argument + "?pageNo=" + this.page + "&pageSize=30&lat=" + this.latitude + "&lng=" + this.longitude + "&shareType=" + this.typeId + "&orderBy=" + orderBy + "&serviceMode=" + serviceMode + "&shareTypeIndex=" + this.shareTypeIndex, params, DemandServiceBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (result.getErrorCode() == 0) {
            switch (result.getRequestCode()) {
                case R.string.API_DEMAND_HOMEPAGE /* 2131296326 */:
                    this.mlv_demand.setRefreshComplete();
                    this.mlv_demand.setLoadMoreComplete();
                    DemandServiceBean bean = (DemandServiceBean) result.getObj();
                    if (this.page != 1 || !HyUtil.isEmpty(bean.getDatas())) {
                        if (this.page == 1) {
                            this.dataList.clear();
                        }
                        this.dataList.addAll(bean.getDatas());
                        this.adapter.setData(this.dataList);
                        this.adapter.notifyDataSetChanged();
                        return;
                    }
                    this.dataList.clear();
                    this.adapter.setData(this.dataList);
                    this.adapter.notifyDataSetChanged();
                    if (this.isService.booleanValue()) {
                        showNoData("未找到相关服务/共享", R.drawable.icon_empty_service);
                        return;
                    } else {
                        showNoData("未找到相关需求", R.drawable.icon_empty_demand);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    @Override // com.vsf2f.f2f.adapter.DemandMainRecycleAdapter.AdapterItemClick
    public void playVoice(View v, String url) {
        new VoicePlayer(this, url, (MusicPlayer) v);
    }

    @Override // com.vsf2f.f2f.adapter.DemandMainRecycleAdapter.AdapterItemClick
    public void itemClick(int position) {
        DemandDetailBean bean = this.dataList.get(position);
        if (bean != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("id", bean.getMoId());
            if (this.isService.booleanValue()) {
                startAct(ServiceInfoActivity.class, bundle2);
            } else {
                startAct(DemandInfoActivity.class, bundle2);
            }
        }
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        this.page = 1;
        getDemandOrService(this.orderType, this.serviceType);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
        this.page++;
        getDemandOrService(this.orderType, this.serviceType);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }

    @Override // com.vsf2f.f2f.ui.dialog.DemandBrushDialog.BrushType
    public void brushTypeCallBack(int orderType, int serviceMode, @StringRes int orderRes, @StringRes int serviceRes) {
        this.page = 1;
        this.nv_smart.setText(orderRes);
        this.nv_servicetype.setText(serviceRes);
        getDemandOrService(orderType, serviceMode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        VoicePlayer.stopVoice();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        VoicePlayer.stopVoice();
        super.onDestroy();
    }
}
