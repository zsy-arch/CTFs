package com.vsf2f.f2f.ui.shop;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.ShopBusinessAdapter;
import com.vsf2f.f2f.bean.ShopMainDetailBean;
import java.util.List;

/* loaded from: classes2.dex */
public class ShopBusinessActivity extends BaseActivity implements IAdapterListener, XRefreshViewListener {
    private ShopBusinessAdapter adapter;
    private List<ShopMainDetailBean> datas;
    private String mainsaleID;
    private RefreshRecyclerView recyclerView;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_shop_business;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.shop_main_sale, R.string.ensure);
        this.recyclerView = (RefreshRecyclerView) getView(R.id.shop_business_refreshView);
        this.recyclerView.setOnRefreshListener(this);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        if (HyUtil.isNoEmpty(this.datas)) {
            StringBuilder name = new StringBuilder();
            StringBuilder id = new StringBuilder();
            boolean first = true;
            for (ShopMainDetailBean item : this.datas) {
                if (item.isCheck()) {
                    if (first) {
                        name.append(item.getName());
                        id.append(item.getId());
                        first = false;
                    } else {
                        name.append(" ，" + item.getName());
                        id.append("," + item.getId());
                    }
                }
            }
            if (TextUtils.isEmpty(name.toString())) {
                MyToast.show(this, getString(R.string.toast_select_main_sale));
                return;
            }
            Intent intent = new Intent();
            intent.putExtra(Constant.FLAG, name.toString());
            intent.putExtra(Constant.FLAG2, id.toString());
            setResult(-1, intent);
            finish();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.mainsaleID = getBundle().getString(Constant.FLAG);
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_SHOP_DETAIL_SIMPLE, ComUtil.getZCApi(this.context, getString(R.string.API_SHOP_DETAIL_SIMPLE)), null, ShopMainDetailBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        this.recyclerView.setLoadMoreComplete();
        this.recyclerView.setRefreshComplete();
        this.datas = (List) result.getObj();
        updateUI();
        updateData();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.recyclerView.setRefreshComplete();
        this.recyclerView.setLoadMoreComplete();
    }

    private void updateData() {
        String[] arr = this.mainsaleID.split(",");
        for (int i = 0; i < this.datas.size(); i++) {
            for (String str : arr) {
                if (this.datas.get(i).getId().equals(str)) {
                    this.datas.get(i).setCheck(true);
                }
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.adapter == null) {
            this.adapter = new ShopBusinessAdapter(this.context, this.datas);
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
    public void onItemClick(int id, Object obj, int position) {
        ShopMainDetailBean item = this.datas.get(position);
        switch (id) {
            case -1:
                item.setCheck(!item.isCheck());
                this.datas.set(position, item);
                updateUI();
                return;
            default:
                return;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
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

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        finish();
    }
}
