package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
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
public class SaveUrlAdapter extends BaseRecyclerAdapter<ShareBean> {
    public SaveUrlAdapter(Context context, List<ShareBean> datas) {
        super(context, R.layout.item_saveurl, datas);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, ShareBean item, int position) {
        ItemHolder h = (ItemHolder) holder;
        h.txtName.setText(item.getName() == null ? "" : item.getName());
        h.txtUrl.setText(item.getHref() == null ? "" : item.getHref());
        if (item.getIcon() == null) {
            h.imgUrl.setImageResource(R.drawable.img_empty);
        } else {
            ComUtil.displayImage(getContext(), h.imgUrl, item.getIcon());
        }
        setOnClickListener(h.itemView, item, position);
        setOnClickListener(h.imgbottom, item, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private TextView txtName = (TextView) getView(R.id.save_txtName);
        private TextView txtUrl = (TextView) getView(R.id.save_txtUrl);
        private ImageView imgUrl = (ImageView) getView(R.id.save_imgUrl);
        private ImageButton imgbottom = (ImageButton) getView(R.id.save_imgbottom);

        public ItemHolder(View v) {
            super(v);
        }
    }
}
