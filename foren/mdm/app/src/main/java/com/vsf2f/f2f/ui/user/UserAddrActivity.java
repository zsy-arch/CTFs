package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.UserAddrAdapter;
import com.vsf2f.f2f.bean.UserAddrBean;
import java.util.List;

/* loaded from: classes2.dex */
public class UserAddrActivity extends BaseActivity implements IAdapterListener, XRefreshViewListener {
    private UserAddrAdapter adapter;
    private List<UserAddrBean> datas;
    private RefreshRecyclerView recyclerView;
    private int selectIndex;
    private TextView txtNoArea;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_address;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.address_ship, 0);
        setOnClickListener(R.id.user_addr_btnAdd);
        this.recyclerView = (RefreshRecyclerView) getView(R.id.user_addr_recyclerView);
        this.recyclerView.setOnRefreshListener(this);
        this.txtNoArea = (TextView) getView(R.id.addt_txtNoArea);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_ADDR_CHECK, ComUtil.getZCApi(this.context, getString(R.string.API_ADDR_CHECK)), null, UserAddrBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_ADDR_CHECK /* 2131296280 */:
                this.recyclerView.setRefreshComplete();
                this.recyclerView.setLoadMoreComplete();
                this.datas = (List) result.getObj();
                break;
            case R.string.API_CHANGE_ADDR /* 2131296298 */:
                MyToast.show(this, (int) R.string.setting_success);
                for (int i = 0; i < this.datas.size(); i++) {
                    if (i == this.selectIndex) {
                        this.datas.get(i).setIsDefault(1);
                    } else {
                        this.datas.get(i).setIsDefault(0);
                    }
                }
                this.adapter.notifyDataSetChanged();
                break;
            case R.string.API_DELETE_ADDR /* 2131296319 */:
                MyToast.show(this, (int) R.string.delete_success);
                requestData();
                break;
        }
        updateUI();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        switch (result.getRequestCode()) {
            case R.string.API_ADDR_CHECK /* 2131296280 */:
                this.recyclerView.setRefreshComplete();
                this.recyclerView.setLoadMoreComplete();
                this.datas = (List) result.getObj();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (HyUtil.isEmpty(this.datas)) {
            this.recyclerView.setVisibility(8);
            this.txtNoArea.setVisibility(0);
            return;
        }
        this.recyclerView.setVisibility(0);
        this.txtNoArea.setVisibility(8);
        showCView();
        if (this.adapter == null) {
            this.adapter = new UserAddrAdapter(this, this.datas, this);
            this.recyclerView.setAdapter(this.adapter);
            return;
        }
        this.adapter.setDatas(this.datas);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.user_addr_btnAdd /* 2131755709 */:
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constant.FLAG, false);
                startActForResult(UserAddrAddActivity.class, bundle, 999);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object o, int position) {
        switch (id) {
            case R.id.user_addr_i_llyDef /* 2131756910 */:
                try {
                    if (this.datas.get(position).getIsDefault() != 1) {
                        this.selectIndex = position;
                        defaultRequest(this.datas.get(position).getId());
                        return;
                    }
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
            case R.id.user_addr_i_imgDef /* 2131756911 */:
            case R.id.user_addr_i_txtDef /* 2131756912 */:
            default:
                return;
            case R.id.user_addr_i_llyEdit /* 2131756913 */:
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constant.FLAG, true);
                bundle.putParcelable(Constant.FLAG2, this.datas.get(position));
                startActForResult(UserAddrAddActivity.class, bundle, 999);
                return;
            case R.id.user_addr_i_llyDel /* 2131756914 */:
                deleteRequest(position);
                return;
        }
    }

    private void defaultRequest(String id) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("isDefault", 1);
        getClient().post(R.string.API_CHANGE_ADDR, ComUtil.getZCApi(this.context, getString(R.string.API_CHANGE_ADDR)), params, String.class, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == -1) {
            requestData();
        }
    }

    private void deleteRequest(int position) {
        UserAddrBean addr;
        if (!HyUtil.isEmpty(this.datas) && (addr = this.datas.get(position)) != null) {
            getClient().post(R.string.API_DELETE_ADDR, ComUtil.getZCApi(this.context, getString(R.string.API_DELETE_ADDR, new Object[]{addr.getId()})));
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
