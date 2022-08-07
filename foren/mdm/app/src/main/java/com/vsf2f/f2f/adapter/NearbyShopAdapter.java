package com.vsf2f.f2f.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.utils.LocationUtils;
import com.hy.frame.view.RotundityImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.NearbyShopBean;
import java.util.List;

/* loaded from: classes2.dex */
public class NearbyShopAdapter extends BaseAdapter {
    private List<NearbyShopBean.RowsBean> data;
    private AdapterItemClick itemClick;

    /* loaded from: classes2.dex */
    public interface AdapterItemClick {
        void collect(int i);

        void itemClick(int i);
    }

    public NearbyShopAdapter(List<NearbyShopBean.RowsBean> data, AdapterItemClick itemClick) {
        this.data = data;
        this.itemClick = itemClick;
    }

    public List<NearbyShopBean.RowsBean> getData() {
        return this.data;
    }

    public void setData(List<NearbyShopBean.RowsBean> data) {
        this.data = data;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        NearbyShopBean.RowsBean bean = (NearbyShopBean.RowsBean) getItem(position);
        if (convertView == null) {
            holdView = new HoldView();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_shop, parent, false);
            holdView.riv_user = (RotundityImageView) convertView.findViewById(R.id.riv_user);
            holdView.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_parent);
            holdView.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holdView.tv_sell = (TextView) convertView.findViewById(R.id.tv_sell);
            holdView.tv_sum = (TextView) convertView.findViewById(R.id.tv_sum);
            holdView.tv_collect = (TextView) convertView.findViewById(R.id.tv_collect);
            holdView.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.tv_name.setText(bean.getStoreName());
        holdView.tv_sell.setText("售出：" + bean.getSalesCount() + "件");
        holdView.tv_sum.setText("共：" + bean.getGoodsCount() + "件宝贝");
        holdView.tv_distance.setText(LocationUtils.getDistance(bean.getLat(), bean.getLng()));
        holdView.ll_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.NearbyShopAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NearbyShopAdapter.this.itemClick.itemClick(position);
            }
        });
        holdView.tv_collect.setSelected(bean.isCollection());
        holdView.tv_collect.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.NearbyShopAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NearbyShopAdapter.this.itemClick.collect(position);
            }
        });
        Glide.with(parent.getContext()).load(bean.getLogo().getSpath()).error((int) R.mipmap.def_head).into(holdView.riv_user);
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        LinearLayout ll_parent;
        RotundityImageView riv_user;
        TextView tv_collect;
        TextView tv_distance;
        TextView tv_name;
        TextView tv_sell;
        TextView tv_sum;

        HoldView() {
        }
    }
}
