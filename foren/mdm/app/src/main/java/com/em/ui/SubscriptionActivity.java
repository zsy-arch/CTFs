package com.em.ui;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.em.adapter.SubscriptionAdapter;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.SubscriptionBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SubscriptionActivity extends BaseActivity implements XRefreshViewListener, IAdapterListener {
    public static SubscriptionActivity instance;
    private SubscriptionAdapter adapter;
    protected List<SubscriptionBean> subList = new ArrayList();
    private RefreshRecyclerView subscription_list;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_fragment_subscription;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        instance = this;
        initHeaderBack(R.string.subscription_list, 0);
        this.subscription_list = (RefreshRecyclerView) getView(R.id.subscription_list);
        this.subscription_list.setOnRefreshListener(this);
        this.subscription_list.setLoadMoreEnabled(false);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        getGroups();
    }

    public void getGroups() {
        getClient().get(R.string.API_SUBSCRIPTION_LIST, ComUtil.getZCApi(this.context, getString(R.string.API_SUBSCRIPTION_LIST)), null, SubscriptionBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_SUBSCRIPTION_LIST /* 2131296442 */:
                this.subscription_list.setRefreshComplete();
                this.subList.clear();
                setGroupList((List) result.getObj());
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.subscription_list.setRefreshFail();
    }

    public void setGroupList(List<SubscriptionBean> groupslist) {
        this.subList.clear();
        this.subList.addAll(groupslist);
        updateUI();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.adapter == null) {
            this.adapter = new SubscriptionAdapter(this.context, this.subList);
            this.adapter.setListener(this);
            this.subscription_list.setAdapter(this.adapter);
            return;
        }
        this.adapter.setDatas(this.subList);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.group_add /* 2131756648 */:
                startAct(NewGroupActivity.class);
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        getGroups();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object obj, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("username", ((SubscriptionBean) obj).getAccount());
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, 4);
        startAct(ChatActivity.class, bundle);
    }
}
