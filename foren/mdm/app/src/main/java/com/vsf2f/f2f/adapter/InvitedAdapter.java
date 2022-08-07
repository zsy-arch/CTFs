package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.MyContactsRowsBean;
import java.util.List;

/* loaded from: classes2.dex */
public class InvitedAdapter extends BaseRecyclerAdapter<MyContactsRowsBean> {
    private AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-1, -2);

    public InvitedAdapter(Context context, List<MyContactsRowsBean> datas) {
        super(context, R.layout.item_level, datas);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, MyContactsRowsBean item, int position) {
        ItemHolder h = (ItemHolder) holder;
        if (item.getUserPic() == null) {
            h.imgavarta.setImageResource(R.mipmap.def_head);
        } else {
            ComUtil.displayHead(getContext(), h.imgavarta, item.getUserPic().getSpath());
        }
        if (item.getName() == null) {
            h.llReal.setVisibility(8);
        } else {
            h.llReal.setVisibility(0);
            h.txtRealName.setText(item.getName());
        }
        h.txtNickName.setText(item.getNickName());
        h.txtUserName.setText(item.getUserName());
        h.txtlevelPeople.setText(item.getDirectlyUnderCount() + " 人");
        setOnClickListener(h.itemView, item, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private ImageView imgavarta = (ImageView) getView(R.id.contact_imgHead);
        private TextView txtNickName = (TextView) getView(R.id.contact_txtNickName);
        private TextView txtUserName = (TextView) getView(R.id.contact_txtUserName);
        private TextView txtRealName = (TextView) getView(R.id.contact_txtRealName);
        private TextView txtlevelPeople = (TextView) getView(R.id.contact_txtPeople);
        private LinearLayout llReal = (LinearLayout) getView(R.id.contact_llReal);

        public ItemHolder(View v) {
            super(v);
            v.setLayoutParams(InvitedAdapter.this.layoutParams);
        }
    }
}
