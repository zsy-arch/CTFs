package com.vsf2f.f2f.ui.user;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.ui.UserProfileActivity;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.InvitedAdapter;
import com.vsf2f.f2f.bean.MyContactsBean;
import com.vsf2f.f2f.bean.MyContactsRowsBean;
import java.util.List;

/* loaded from: classes2.dex */
public class InvitedActivity extends BaseActivity implements XRefreshViewListener, IAdapterListener {
    private InvitedAdapter adapter;
    private List<MyContactsRowsBean> datas;
    private RefreshRecyclerView refreshView;
    private int PAGE_SIZE = 20;
    private int PAGE_INDEX = 1;
    private int Level = 1;
    private boolean fresh = true;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circles_fragment;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.me_invited, 0);
        this.refreshView = (RefreshRecyclerView) getView(R.id.circles_recyclerView);
        this.refreshView.setOnRefreshListener(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData(this.PAGE_INDEX);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestData(int page) {
        getClient().setShowDialog(this.fresh);
        getClient().get(R.string.API_SIX_DETAIL_INFORMATION, ComUtil.getZCApi(this.context, getString(R.string.API_SIX_DETAIL_INFORMATION, new Object[]{Integer.valueOf(this.Level)}) + "?limit=" + this.PAGE_SIZE + "&page=" + page), null, MyContactsBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        boolean z = true;
        this.refreshView.setRefreshComplete();
        this.refreshView.setLoadMoreComplete();
        MyContactsBean bean = (MyContactsBean) result.getObj();
        List<MyContactsRowsBean> beans = bean.getRows();
        try {
            if (this.PAGE_INDEX <= 1) {
                if (HyUtil.isEmpty(bean.getRows())) {
                    showNoData();
                    return;
                }
                this.datas = beans;
            } else if (beans != null) {
                this.datas.addAll(beans);
            }
            RefreshRecyclerView refreshRecyclerView = this.refreshView;
            if (this.PAGE_INDEX >= Integer.parseInt(bean.getTotalPage())) {
                z = false;
            }
            refreshRecyclerView.setLoadMoreEnabled(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateUI();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.refreshView.setRefreshComplete();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (HyUtil.isEmpty(this.datas)) {
            showNoData();
            return;
        }
        showCView();
        if (this.adapter == null) {
            this.adapter = new InvitedAdapter(this.context, this.datas);
            this.adapter.setListener(this);
            this.refreshView.setAdapter(this.adapter);
            return;
        }
        this.adapter.setDatas(this.datas);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object o, int i) {
        switch (id) {
            case -1:
                Bundle bundle = new Bundle();
                bundle.putString("username", ((MyContactsRowsBean) o).getUserName());
                startAct(UserProfileActivity.class, bundle);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        this.fresh = true;
        this.PAGE_INDEX = 1;
        requestData(this.PAGE_INDEX);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
        getClient().setShowDialog(false);
        this.PAGE_INDEX++;
        this.fresh = false;
        requestData(this.PAGE_INDEX);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }
}
