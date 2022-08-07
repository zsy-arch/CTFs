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
public class SaveSaleAdapter extends BaseRecyclerAdapter<ShareBean> {
    private AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-1, -2);

    public SaveSaleAdapter(Context context, List<ShareBean> datas) {
        super(context, R.layout.item_savesale, datas);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, ShareBean item, int position) {
        ItemHolder h = (ItemHolder) holder;
        if (item.getGoodsInfo() == null) {
            h.imgCover.setImageResource(R.drawable.img_empty);
            h.txtsalename.setText("名称");
            h.txtsaleprice.setText("￥0.00元");
            return;
        }
        if (item.getGoodsInfo().getGoodsPicture() == null) {
            h.imgCover.setImageResource(R.mipmap.def_head);
        } else {
            ComUtil.displayImage(getContext(), h.imgCover, item.getGoodsInfo().getGoodsPicture().getSpath());
        }
        if (item.getGoodsInfo().getGoodsName() == null) {
            h.txtsalename.setText("请设置店铺名称");
        }
        h.txtsalename.setText(item.getGoodsInfo().getGoodsName());
        if (item.getGoodsInfo().getSalesPrice() == null) {
            h.txtsaleprice.setText("￥0.00元");
        }
        h.txtsaleprice.setText("￥" + item.getGoodsInfo().getSalesPrice() + "元");
        setOnClickListener(h.itemView, item, position);
        setOnClickListener(h.imgbottom, item, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private ImageView imgCover = (ImageView) getView(R.id.save_imgsaleurl);
        private ImageButton imgbottom = (ImageButton) getView(R.id.save_imgSalebottom);
        private TextView txtsalename = (TextView) getView(R.id.save_txtsalename);
        private TextView txtsaleprice = (TextView) getView(R.id.save_txtsaleprice);

        public ItemHolder(View v) {
            super(v);
            v.setLayoutParams(SaveSaleAdapter.this.layoutParams);
        }
    }
}
