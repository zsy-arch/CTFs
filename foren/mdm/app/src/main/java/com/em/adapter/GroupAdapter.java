package com.em.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.easeui.utils.EaseUserUtils;
import com.hy.frame.view.RoundImageView;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupBean;
import java.util.List;

/* loaded from: classes.dex */
public class GroupAdapter extends BaseRecyclerAdapter<GroupBean> {
    public GroupAdapter(Context context, List<GroupBean> groups) {
        super(context, R.layout.em_row_group, groups);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, GroupBean group, int position) {
        ItemHolder h = (ItemHolder) holder;
        EaseUserUtils.setGroupName(group, h.name);
        EaseUserUtils.setGroupPic(group, h.head);
        setOnClickListener(h.ll_parent, group, position);
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
