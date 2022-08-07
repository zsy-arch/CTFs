package com.em.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hy.frame.view.RoundImageView;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.SubscriptionBean;
import java.util.List;

/* loaded from: classes.dex */
public class SubscriptionAdapter extends BaseRecyclerAdapter<SubscriptionBean> {
    public SubscriptionAdapter(Context context, List<SubscriptionBean> groups) {
        super(context, R.layout.em_row_group, groups);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, SubscriptionBean item, int position) {
        ItemHolder h = (ItemHolder) holder;
        h.name.setText(item.getName());
        if (item.getBasePicUrl() != null) {
            String groupPic = item.getBasePicUrl();
            if (!TextUtils.isEmpty(groupPic)) {
                Glide.with(this.context).load(groupPic).error((int) R.drawable.em_group_icon).into(h.head);
            }
        }
        setOnClickListener(h.ll_parent, item, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ItemHolder extends BaseHolder {
        private LinearLayout ll_parent = (LinearLayout) getView(R.id.ll_parent);
        private RoundImageView head = (RoundImageView) getView(R.id.avatar);
        private TextView name = (TextView) getView(R.id.name);

        public ItemHolder(View v) {
            super(v);
        }
    }
}
