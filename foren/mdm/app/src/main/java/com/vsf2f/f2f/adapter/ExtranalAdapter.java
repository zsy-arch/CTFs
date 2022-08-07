package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ExtranalBean;
import java.util.List;

/* loaded from: classes2.dex */
public class ExtranalAdapter extends BaseRecyclerAdapter<ExtranalBean> {
    public ExtranalAdapter(Context context, List<ExtranalBean> datas) {
        super(context, R.layout.item_extranal, datas);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, ExtranalBean extranalBean, int position) {
        ItemHolder h = (ItemHolder) holder;
        if (extranalBean.getRecomPic() == null) {
            h.imgavarta.setImageResource(R.mipmap.def_head);
        } else {
            ComUtil.displayImage(this.context, h.imgavarta, extranalBean.getRecomPic().getSpath());
        }
        h.txtextranalname.setText(extranalBean.getRecomName() != null ? extranalBean.getRecomName() : "");
        h.txtwgdMoney.setText(extranalBean.getWgdMoney() != null ? extranalBean.getWgdMoney() : "");
        h.txtwgdprofit.setText(extranalBean.getProfitMoney() != null ? extranalBean.getProfitMoney() : "");
        h.txtwgdTime.setText(extranalBean.getFromTimeStr() != null ? extranalBean.getFromTimeStr() : "");
        h.txtrecommandname.setText(extranalBean.getRecomUserName() != null ? extranalBean.getRecomUserName() : "");
        setOnClickListener(h.btnextranalwgd, extranalBean, position);
        setOnClickListener(h.itemView, extranalBean, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private ImageView imgavarta = (ImageView) getView(R.id.img_extranal_avarta);
        private TextView txtextranalname = (TextView) getView(R.id.txt_extranal_name);
        private TextView txtrecommandname = (TextView) getView(R.id.txt_extranal_recommand);
        private TextView txtwgdMoney = (TextView) getView(R.id.txt_wgdMoney);
        private TextView txtwgdprofit = (TextView) getView(R.id.txt_wgdProfit);
        private TextView txtwgdTime = (TextView) getView(R.id.txt_wgdTime);
        private Button btnextranalwgd = (Button) getView(R.id.btn_extranalwgd);

        public ItemHolder(View v) {
            super(v);
        }
    }
}
