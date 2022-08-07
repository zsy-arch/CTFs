package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShopMainDetailBean;
import java.util.List;

/* loaded from: classes2.dex */
public class ShopBusinessAdapter extends BaseRecyclerAdapter<ShopMainDetailBean> {
    public ShopBusinessAdapter(Context context, List<ShopMainDetailBean> datas) {
        super(context, R.layout.item_shop_detail, datas);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, ShopMainDetailBean item, int position) {
        ItemHolder h = (ItemHolder) holder;
        h.txtitem.setVisibility(item.getName() == null ? 8 : 0);
        h.txtitem.setText(item.getName());
        h.imgcheck.setSelected(item.isCheck());
        setOnClickListener(h.itemView, item, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private TextView txtitem = (TextView) getView(R.id.txt_detail_item);
        private ImageView imgcheck = (ImageView) getView(R.id.img_detail_check);

        public ItemHolder(View v) {
            super(v);
        }
    }
}
