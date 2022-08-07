package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.widget.TextView;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GoodsTypeListBean;
import com.vsf2f.f2f.bean.GoodsTypeVoListBean;
import java.util.List;

/* loaded from: classes2.dex */
public class GoodsTypeAdapter extends BaseRecyclerAdapter<GoodsTypeListBean> {
    public GoodsTypeAdapter(Context context, List<GoodsTypeListBean> datas) {
        super(context, R.layout.item_good_edit_type, datas);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, GoodsTypeListBean item, int position) {
        ItemHolder h = (ItemHolder) holder;
        if (item.getProductsSpecsVoList() == null) {
            h.txtSpeci.setText("—— ——");
        } else {
            StringBuilder speci = new StringBuilder();
            for (GoodsTypeVoListBean list : item.getProductsSpecsVoList()) {
                speci.append(list.getSpecValue());
                speci.append(",");
            }
            h.txtSpeci.setText(speci.substring(0, speci.length() - 1));
        }
        h.txtprice.setText(item.getSalesPrice() == null ? "--" : item.getSalesPrice() + "");
        h.txtnum.setText(item.getStore() == null ? "--" : item.getStore() + "");
        h.txtnone.setText(item.getResRatio() == null ? "--" : item.getResRatio() + "%");
        if (item.getMarketable().equals("1")) {
            h.txtdown.setText("下架");
        } else {
            h.txtdown.setText("上架");
            h.txtdown.setTextColor(SupportMenu.CATEGORY_MASK);
        }
        setOnClickListener(h.txtedit, item, position);
        setOnClickListener(h.txtdown, item, position);
        setOnClickListener(h.txtdelete, item, position);
        setOnClickListener(h.itemView, item, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private TextView txtSpeci = (TextView) getView(R.id.txt_typerule);
        private TextView txtprice = (TextView) getView(R.id.txt_typeprice);
        private TextView txtnum = (TextView) getView(R.id.txt_typenum);
        private TextView txtnone = (TextView) getView(R.id.txt_typenone);
        private TextView txtedit = (TextView) getView(R.id.txt_edit);
        private TextView txtdown = (TextView) getView(R.id.txt_down);
        private TextView txtdelete = (TextView) getView(R.id.txt_delete);

        public ItemHolder(View v) {
            super(v);
        }
    }
}
