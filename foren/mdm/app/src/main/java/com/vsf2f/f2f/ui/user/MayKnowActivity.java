package com.vsf2f.f2f.ui.user;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.ui.UserProfileActivity;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.InvitedAdapter;
import com.vsf2f.f2f.bean.MyContactsBean;
import com.vsf2f.f2f.bean.MyContactsRowsBean;
import com.vsf2f.f2f.ui.dialog.WarnDialog;
import java.util.List;

/* loaded from: classes2.dex */
public class MayKnowActivity extends BaseActivity implements XRefreshViewListener, IAdapterListener {
    private InvitedAdapter adapter;
    private List<MyContactsRowsBean> datas;
    private RefreshRecyclerView refreshView;
    private int PAGE_SIZE = 20;
    private int PAGE_INDEX = 1;
    private int Level = 2;
    private boolean fresh = true;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circles_fragment;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.may_know, 0);
        this.refreshView = (RefreshRecyclerView) getView(R.id.circles_recyclerView);
        this.refreshView.setOnRefreshListener(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (getUserInfo().getLv() == 0) {
            showWarnDlg();
        } else {
            requestData(this.PAGE_INDEX);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestData(int page) {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(this.fresh);
        getClient().get(R.string.API_SIX_DETAIL_INFORMATION, ComUtil.getZCApi(this.context, getString(R.string.API_SIX_DETAIL_INFORMATION, new Object[]{Integer.valueOf(this.Level)}) + "?limit=20") + "&page=" + page, params, MyContactsBean.class, false);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        boolean z = true;
        switch (result.getRequestCode()) {
            case R.string.API_SIX_DETAIL_INFORMATION /* 2131296441 */:
                this.refreshView.setRefreshComplete();
                this.refreshView.setLoadMoreComplete();
                List<MyContactsRowsBean> rows = ((MyContactsBean) result.getObj()).getRows();
                if (this.PAGE_INDEX <= 1) {
                    if (HyUtil.isEmpty(rows)) {
                        showNoData();
                        return;
                    }
                    this.datas = rows;
                } else if (rows != null) {
                    this.datas.addAll(rows);
                }
                RefreshRecyclerView refreshRecyclerView = this.refreshView;
                if (!HyUtil.isNoEmpty(this.datas) || this.datas.size() % this.PAGE_SIZE != 0) {
                    z = false;
                }
                refreshRecyclerView.setLoadMoreEnabled(z);
                updateUI();
                return;
            default:
                return;
        }
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

    public void showWarnDlg() {
        WarnDialog warnDialog = new WarnDialog(this.context, getString(R.string.not_open_pms_prompt), getString(R.string.open_vip_now), true, true, false);
        warnDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.user.MayKnowActivity.1
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                switch (flag) {
                    case -1:
                        MayKnowActivity.this.finish();
                        return;
                    case 0:
                        MayKnowActivity.this.startAct(UserVipActivity.class);
                        MayKnowActivity.this.finish();
                        return;
                    default:
                        return;
                }
            }
        });
        warnDialog.show();
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
