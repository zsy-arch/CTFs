package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ProductCategory;
import com.vsf2f.f2f.ui.utils.listener.GoodsClassifyListener;
import java.util.List;

/* loaded from: classes2.dex */
public class GoodsClassifyAdapter extends BaseAdapter {
    private Context context;
    private List<ProductCategory> data;
    private GoodsClassifyListener listener;

    public GoodsClassifyAdapter(Context context, List<ProductCategory> data, GoodsClassifyListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    @Override // android.widget.Adapter
    public ProductCategory getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewHolder viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_goods_classify, (ViewGroup) null);
            viewHolder.nameView = (TextView) convertView.findViewById(R.id.tv_name_item_goods_classify);
            viewHolder.nextView = (ImageView) convertView.findViewById(R.id.iv_next_item_goods_classify);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder2 = (ViewHolder) convertView.getTag();
        ProductCategory product = getItem(position);
        final int underCount = product.getUnderCount();
        viewHolder2.nameView.setText(product.getName());
        if (underCount == 0) {
            viewHolder2.nextView.setVisibility(8);
        } else {
            viewHolder2.nextView.setVisibility(0);
        }
        convertView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.GoodsClassifyAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                GoodsClassifyAdapter.this.listener.onClick(position, underCount);
            }
        });
        return convertView;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        TextView nameView;
        ImageView nextView;

        ViewHolder() {
        }
    }
}
