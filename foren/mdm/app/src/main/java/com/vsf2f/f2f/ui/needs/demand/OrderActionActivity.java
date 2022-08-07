package com.vsf2f.f2f.ui.needs.demand;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.OrderTraceAdapter;
import com.vsf2f.f2f.bean.OrderDetailBean;

/* loaded from: classes2.dex */
public class OrderActionActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_order_action;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.order_action, 0);
        OrderDetailBean orderDetailBean = (OrderDetailBean) getBundle().getSerializable("data");
        boolean isService = getBundle().getBoolean("isService");
        ((ListView) getView(R.id.mlv_trace)).setAdapter((ListAdapter) new OrderTraceAdapter(orderDetailBean.getTraceList()));
        TextView txtTitle = (TextView) getView(R.id.order_needs_title);
        TextView keyTitle = (TextView) getView(R.id.order_needs_titleKey);
        if (isService) {
            keyTitle.setText("服务名称：");
            txtTitle.setText(orderDetailBean.getShareServiceObj().getTitle());
        } else {
            keyTitle.setText("需求名称：");
            txtTitle.setText(orderDetailBean.getShareObj().getTitle());
        }
        ((TextView) getView(R.id.order_needs_no)).setText(orderDetailBean.getMoId() + "");
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
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
}
