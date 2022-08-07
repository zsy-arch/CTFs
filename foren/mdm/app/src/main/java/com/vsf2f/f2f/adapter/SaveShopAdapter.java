package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShareBean;
import java.util.List;

/* loaded from: classes2.dex */
public class SaveShopAdapter extends BaseRecyclerAdapter<ShareBean> {
    private AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-1, -2);

    public SaveShopAdapter(Context context, List<ShareBean> datas) {
        super(context, R.layout.item_saveshop, datas);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, ShareBean item, int position) {
        ItemHolder h = (ItemHolder) holder;
        if (item.getShopInfo().getLogo() == null) {
            h.imgshop.setImageResource(R.drawable.img_empty);
        } else {
            ComUtil.displayImage(getContext(), h.imgshop, item.getShopInfo().getLogo().getSpath());
        }
        if (item.getShopInfo().getStoreName() == null) {
            h.txtshopname.setText("");
        } else {
            h.txtshopname.setText(item.getShopInfo().getStoreName());
        }
        setOnClickListener(h.itemView, item, position);
        setOnClickListener(h.imgbottom, item, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private TextView txtshopname = (TextView) getView(R.id.save_txtshopname);
        private ImageView imgshop = (ImageView) getView(R.id.save_imgshopurl);
        private ImageButton imgbottom = (ImageButton) getView(R.id.save_imgShopbottom);

        public ItemHolder(View v) {
            super(v);
            v.setLayoutParams(SaveShopAdapter.this.layoutParams);
        }
    }
}
