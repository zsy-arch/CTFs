package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ProductBean;
import java.util.List;

/* loaded from: classes2.dex */
public class GoodesAdapter extends BaseRecyclerAdapter<ProductBean> {
    private boolean isSoldTab;

    public GoodesAdapter(Context context, List<ProductBean> datas, boolean isSoldTab) {
        super(context, R.layout.item_goods_manage, datas);
        this.isSoldTab = isSoldTab;
    }

    public void setStats(List<ProductBean> datas, boolean isSoldTab) {
        this.datas = datas;
        this.isSoldTab = isSoldTab;
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, ProductBean entity, int position) {
        ItemHolder holdView = (ItemHolder) holder;
        holdView.imgGoods.setImageResource(R.mipmap.def_head);
        holdView.txtTitle.setText(entity.getGoodsName());
        holdView.txtSales.setText("" + entity.getSalesCount());
        holdView.txtFavorites.setText("" + entity.getFavoritesCount());
        holdView.txtStore.setText("" + entity.getStore());
        holdView.txtPrice.setText(HyUtil.removeNumberZero("" + entity.getSalesPrice()));
        holdView.imgCheck.setSelected(entity.isCheck());
        holdView.txtDiscount.setText((!this.isSoldTab || entity.getUsableCoupon() <= 0) ? "" : "可用" + entity.getUsableCoupon() + "元抵用券");
        if (this.isSoldTab) {
            if (entity.getIsRecom() == 1) {
                holdView.txtRecom.setVisibility(0);
                holdView.txtRecommend.setText("不推荐");
            } else {
                holdView.txtRecom.setVisibility(8);
                holdView.txtRecommend.setText("推荐");
            }
            holdView.txtChange.setText("下架");
            holdView.txtRecommend.setVisibility(0);
            holdView.txtShare.setVisibility(0);
            holdView.txtLook.setVisibility(0);
        } else {
            holdView.txtRecom.setVisibility(8);
            holdView.txtChange.setText("恢复上架");
            holdView.txtRecommend.setVisibility(8);
            holdView.txtShare.setVisibility(8);
            holdView.txtLook.setVisibility(8);
        }
        if (entity.getPicture() == null) {
            holdView.imgGoods.setImageResource(R.drawable.img_empty);
        } else {
            ComUtil.displayImage(this.context, holdView.imgGoods, entity.getPicture().getSpath());
        }
        setOnClickListener(holdView.imgCheck, entity, position);
        setOnClickListener(holdView.txtLook, entity, position);
        setOnClickListener(holdView.txtEdit, entity, position);
        setOnClickListener(holdView.txtChange, entity, position);
        setOnClickListener(holdView.txtRecommend, entity, position);
        setOnClickListener(holdView.txtDelete, entity, position);
        setOnClickListener(holdView.txtShare, entity, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private ImageView imgGoods = (ImageView) getView(R.id.goods_manage_i_imgPic);
        private ImageView imgCheck = (ImageView) getView(R.id.goods_manage_i_imgCheck);
        private TextView txtTitle = (TextView) getView(R.id.goods_manage_i_txtTitle);
        private TextView txtPrice = (TextView) getView(R.id.goods_manage_i_txtPrice);
        private TextView txtRecom = (TextView) getView(R.id.goods_manage_i_txtRecom);
        private TextView txtSales = (TextView) getView(R.id.goods_manage_i_txtSales);
        private TextView txtStore = (TextView) getView(R.id.goods_manage_i_txtStore);
        private TextView txtDiscount = (TextView) getView(R.id.goods_manage_i_txtDiscount);
        private TextView txtFavorites = (TextView) getView(R.id.goods_manage_i_txtFavorites);
        private TextView txtLook = (TextView) getView(R.id.goods_manage_i_txtLook);
        private TextView txtEdit = (TextView) getView(R.id.goods_manage_i_txtEdit);
        private TextView txtChange = (TextView) getView(R.id.goods_manage_i_txtChange);
        private TextView txtRecommend = (TextView) getView(R.id.goods_manage_i_txtRecommend);
        private TextView txtDelete = (TextView) getView(R.id.goods_manage_i_txtDelete);
        private TextView txtShare = (TextView) getView(R.id.goods_manage_i_txtShare);

        public ItemHolder(View v) {
            super(v);
        }
    }
}
