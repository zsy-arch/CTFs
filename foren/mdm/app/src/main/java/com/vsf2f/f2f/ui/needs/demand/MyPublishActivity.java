package com.vsf2f.f2f.ui.needs.demand;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.common.PullToRefreshView;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.server.audio.VoicePlayer;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.MineDemandAdapter;
import com.vsf2f.f2f.adapter.MineServiceAdapter;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandServiceBean;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MyPublishActivity extends BaseActivity implements NavGroup.OnCheckedChangeListener, PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener, MineDemandAdapter.SubClickListener, MineServiceAdapter.SubClickListener {
    private int changeIndex;
    private MineDemandAdapter demAdapter;
    private boolean isService;
    private ListView lv_demand;
    private NavView nv_sub1;
    private NavView nv_sub2;
    private PullToRefreshView pull;
    private MineServiceAdapter serAdapter;
    private NavGroup tab_cus_his;
    private NavGroup tab_dem_ser;
    private int pageNoD = 1;
    private int pageNoS = 1;
    private int pageSize = 20;
    private boolean isHistory = false;
    private List<DemandDetailBean> demandList = new ArrayList();
    private List<DemandDetailBean> serviceList = new ArrayList();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_my_publish;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.title_launch, 0);
        this.lv_demand = (ListView) getView(R.id.lv_demand);
        this.nv_sub1 = (NavView) getView(R.id.nv_sub1);
        this.nv_sub2 = (NavView) getView(R.id.nv_sub2);
        this.pull = (PullToRefreshView) getView(R.id.pull);
        this.pull.setOnFooterRefreshListener(this);
        this.pull.setOnHeaderRefreshListener(this);
        this.tab_dem_ser = (NavGroup) getView(R.id.tab_dem_ser);
        this.tab_dem_ser.setOnCheckedChangeListener(this);
        this.tab_cus_his = (NavGroup) getView(R.id.tab_cus_his);
        this.tab_cus_his.setOnCheckedChangeListener(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.isService = getBundle().getBoolean("isService");
        this.isHistory = getBundle().getBoolean("isHistory");
        if (this.isService) {
            this.tab_dem_ser.setCheckedChildByPosition(2);
        }
        if (this.isHistory) {
            this.tab_cus_his.setCheckedChildByPosition(1);
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.pageNoD = 1;
        this.pageNoS = 1;
        this.demandList.clear();
        this.serviceList.clear();
        getDemandOrService();
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
        switch (nav.getId()) {
            case R.id.nv_1 /* 2131755719 */:
                this.isService = false;
                this.nv_sub1.setText("当前需求");
                this.nv_sub2.setText("历史需求");
                refreshAdapter();
                break;
            case R.id.nv_2 /* 2131755720 */:
                this.isService = true;
                this.nv_sub1.setText("上架中");
                this.nv_sub2.setText("已下架");
                refreshAdapter();
                break;
            case R.id.nv_sub1 /* 2131756053 */:
                changSecondTab(false);
                break;
            case R.id.nv_sub2 /* 2131756054 */:
                changSecondTab(true);
                break;
        }
        this.pageNoD = 1;
        this.pageNoS = 1;
        getDemandOrService();
    }

    private void changSecondTab(boolean history) {
        this.isHistory = history;
        if (this.isService) {
            this.serviceList.clear();
        } else {
            this.demandList.clear();
        }
        refreshAdapter();
    }

    public void refreshAdapter() {
        if (!this.isService) {
            if (this.demAdapter == null) {
                this.demAdapter = new MineDemandAdapter(this.demandList, this);
            }
            if (this.lv_demand.getAdapter() != this.demAdapter) {
                this.lv_demand.setAdapter((ListAdapter) this.demAdapter);
            }
            this.demAdapter.setHistory(this.isHistory);
            this.demAdapter.notifyDataSetChanged();
            return;
        }
        if (this.serAdapter == null) {
            this.serAdapter = new MineServiceAdapter(this.serviceList, this);
        }
        if (this.lv_demand.getAdapter() != this.serAdapter) {
            this.lv_demand.setAdapter((ListAdapter) this.serAdapter);
        }
        this.serAdapter.setUnsold(this.isHistory);
        this.serAdapter.notifyDataSetChanged();
    }

    @Override // com.cdlinglu.common.PullToRefreshView.OnFooterRefreshListener
    public void onFooterRefresh(PullToRefreshView view) {
        if (this.isService) {
            this.pageNoS++;
        } else {
            this.pageNoD++;
        }
        getDemandOrService();
    }

    @Override // com.cdlinglu.common.PullToRefreshView.OnHeaderRefreshListener
    public void onHeaderRefresh(PullToRefreshView view) {
        if (this.isService) {
            this.pageNoS = 1;
        } else {
            this.pageNoD = 1;
        }
        getDemandOrService();
    }

    private void getDemandOrService() {
        getClient().setShowDialog(true);
        if (this.isService) {
            getClient().get(R.string.API_MYSERVICE_QUERY, ComUtil.getXDDApi(this.context, getString(R.string.API_MYSERVICE_QUERY) + "?pageNo=" + this.pageNoS + "&pageSize=" + this.pageSize + "&status=" + ((cuOrhistory() + 1) % 2)), DemandServiceBean.class);
            return;
        }
        getClient().get(R.string.API_MYDEMAND_QUERY, ComUtil.getXDDApi(this.context, getString(R.string.API_MYDEMAND_QUERY) + "?pageNo=" + this.pageNoD + "&pageSize=" + this.pageSize + "&type=" + cuOrhistory()), DemandServiceBean.class);
    }

    private void changeSoldStatus(int type) {
        int moid = this.serviceList.get(this.changeIndex).getMoId();
        AjaxParams params = new AjaxParams();
        params.put("shareServiceId", moid);
        params.put("type", type);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_MYSERVICE_UPDATESTATUS, ComUtil.getXDDApi(this.context, getString(R.string.API_MYSERVICE_UPDATESTATUS)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.pull.onFooterRefreshComplete();
        this.pull.onHeaderRefreshComplete();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        this.pull.onFooterRefreshComplete();
        this.pull.onHeaderRefreshComplete();
        switch (result.getRequestCode()) {
            case R.string.API_MYDEMAND_QUERY /* 2131296373 */:
                DemandServiceBean bean2 = (DemandServiceBean) result.getObj();
                int maxPage2 = bean2.getTotalPage();
                if (maxPage2 == 0) {
                    this.serviceList.clear();
                } else if (this.pageNoD <= maxPage2) {
                    if (this.pageNoD == 1) {
                        this.demandList.clear();
                    }
                    this.demandList.addAll(bean2.getDatas());
                } else {
                    Toast.makeText(MyApplication.getInstance(), "已经到底了", 0).show();
                    return;
                }
                refreshAdapter();
                return;
            case R.string.API_MYSELF_CIRCLES_MESSAGE /* 2131296374 */:
            default:
                return;
            case R.string.API_MYSERVICE_QUERY /* 2131296375 */:
                DemandServiceBean bean = (DemandServiceBean) result.getObj();
                int maxPage = bean.getTotalPage();
                if (maxPage == 0) {
                    this.serviceList.clear();
                } else if (this.pageNoS <= maxPage) {
                    if (this.pageNoS == 1) {
                        this.serviceList.clear();
                    }
                    this.serviceList.addAll(bean.getDatas());
                } else {
                    Toast.makeText(MyApplication.getInstance(), "已经到底了", 0).show();
                    return;
                }
                refreshAdapter();
                return;
            case R.string.API_MYSERVICE_UPDATESTATUS /* 2131296376 */:
                this.serviceList.remove(this.changeIndex);
                refreshAdapter();
                return;
        }
    }

    @Override // com.vsf2f.f2f.adapter.MineDemandAdapter.SubClickListener
    public void toChoiceServer(int postion) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", this.demandList.get(postion));
        startAct(ChoiceServerActivity.class, bundle);
    }

    @Override // com.vsf2f.f2f.adapter.MineServiceAdapter.SubClickListener
    public void toChange(int postion) {
        this.changeIndex = postion;
        if (this.isHistory) {
            changeSoldStatus(1);
        } else {
            changeSoldStatus(0);
        }
    }

    @Override // com.vsf2f.f2f.adapter.MineDemandAdapter.SubClickListener, com.vsf2f.f2f.adapter.MineServiceAdapter.SubClickListener
    public void toEdit(int postion) {
        Bundle bundle = new Bundle();
        if (this.isService) {
            bundle.putInt("moId", this.serviceList.get(postion).getMoId());
            startAct(ServiceModifyActivity.class, bundle);
            return;
        }
        bundle.putInt("moId", this.demandList.get(postion).getMoId());
        startAct(DemandModifyActivity.class, bundle);
    }

    @Override // com.vsf2f.f2f.adapter.MineDemandAdapter.SubClickListener
    public void toPay(int postion) {
        DemandDetailBean bean = this.demandList.get(postion);
        Bundle bundle = new Bundle();
        if (bean.getBizType() == 5) {
            bean.setBizId(bean.getMoId());
        }
        bundle.putInt(Constant.FLAG_TITLE, R.string.bail_demand);
        bundle.putSerializable("needinfo", bean);
        startAct(EntrustActivity.class, bundle);
    }

    @Override // com.vsf2f.f2f.adapter.MineDemandAdapter.SubClickListener, com.vsf2f.f2f.adapter.MineServiceAdapter.SubClickListener
    public void itemClicl(int postion) {
        Bundle bundle = new Bundle();
        if (this.isService) {
            bundle.putInt("isHistory", cuOrhistory());
            bundle.putInt("id", this.serviceList.get(postion).getMoId());
            startAct(ServiceInfoActivity.class, bundle);
            return;
        }
        bundle.putInt("isHistory", cuOrhistory());
        bundle.putInt("id", this.demandList.get(postion).getMoId());
        startAct(DemandInfoActivity.class, bundle);
    }

    @Override // com.vsf2f.f2f.adapter.MineServiceAdapter.SubClickListener
    public void playVoice(int postion, MusicPlayer _voice) {
        String url;
        if (this.isService) {
            url = this.serviceList.get(postion).getVoiceFullUrl();
        } else {
            url = this.demandList.get(postion).getVoiceFullUrl();
        }
        new VoicePlayer(this, url, _voice).onClick(_voice);
    }

    private int cuOrhistory() {
        return this.isHistory ? 1 : 0;
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
        super.onDestroy();
        VoicePlayer.stopVoice();
    }
}
