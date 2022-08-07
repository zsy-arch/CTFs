package com.vsf2f.f2f.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.OrderTraceBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class OrderTraceAdapter extends BaseAdapter {
    private List<OrderTraceBean> traceBeanList;

    public OrderTraceAdapter(List<OrderTraceBean> traceBeanList) {
        if (traceBeanList == null) {
            this.traceBeanList = new ArrayList();
        } else {
            this.traceBeanList = traceBeanList;
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.traceBeanList == null) {
            return 0;
        }
        return this.traceBeanList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.traceBeanList.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        HoldView holdView;
        OrderTraceBean bean = (OrderTraceBean) getItem(i);
        if (view == null) {
            holdView = new HoldView();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_order_trace, viewGroup, false);
            holdView.iv_flag = (ImageView) view.findViewById(R.id.iv_flag);
            holdView.tv_state = (TextView) view.findViewById(R.id.tv_state);
            holdView.tv_time = (TextView) view.findViewById(R.id.tv_time);
            view.setTag(holdView);
        } else {
            holdView = (HoldView) view.getTag();
        }
        if (i == getCount() - 1) {
            holdView.iv_flag.setImageResource(R.drawable.icon_order_action_item2);
        } else {
            holdView.iv_flag.setImageResource(R.drawable.icon_order_action_item);
        }
        holdView.tv_state.setText(bean.getTraceType() + "");
        holdView.tv_time.setText(bean.getOperationTime() + "");
        return view;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        ImageView iv_flag;
        TextView tv_state;
        TextView tv_time;

        HoldView() {
        }
    }
}
