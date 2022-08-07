package com.vsf2f.f2f.ui.user;

import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.ExtranalAdapter;
import com.vsf2f.f2f.bean.ExtranalBean;
import java.util.List;

/* loaded from: classes2.dex */
public class PlugActivity extends BaseActivity implements IAdapterListener, XRefreshViewListener {
    private ExtranalAdapter adapter;
    private List<ExtranalBean> datas;
    private RefreshRecyclerView recyclerView;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_plug;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.external_profit_title, 0);
        this.recyclerView = (RefreshRecyclerView) getView(R.id.recycler_refreshView);
        this.recyclerView.setOnRefreshListener(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_MY_WGD, ComUtil.getZCApi(this.context, getString(R.string.API_MY_WGD)), null, ExtranalBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        this.recyclerView.setRefreshComplete();
        switch (result.getRequestCode()) {
            case R.string.API_MY_WGD /* 2131296378 */:
                this.datas = (List) result.getObj();
                updateUI();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (HyUtil.isEmpty(this.datas)) {
            showNoData();
            return;
        }
        showCView();
        if (this.adapter == null) {
            this.adapter = new ExtranalAdapter(this.context, this.datas);
            this.adapter.setListener(this);
            this.recyclerView.setAdapter(this.adapter);
            return;
        }
        this.adapter.setDatas(this.datas);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int i, Object o, int i1) {
        switch (i) {
            case R.id.btn_extranalwgd /* 2131756801 */:
                startAct(UserVipActivity.class);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        requestData();
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
}
