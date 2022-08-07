package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserAddrBean;
import java.util.List;

/* loaded from: classes2.dex */
public class UserAddrAdapter extends BaseRecyclerAdapter<UserAddrBean> {
    public UserAddrAdapter(Context context, List<UserAddrBean> datas, IAdapterListener listener) {
        super(context, R.layout.item_user_address_list, datas);
        setListener(listener);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new BaseHolder(itemView);
    }

    public void initItemView(BaseHolder itemHolder, UserAddrBean entity, int position) {
        itemHolder.setText(R.id.user_addr_i_txtName, entity.getName());
        itemHolder.setText(R.id.user_addr_i_txtPhone, entity.getPhone());
        if (!(entity.getAddr() == null && entity.getAreaName() == null)) {
            itemHolder.setText(R.id.user_addr_i_txtAddress, entity.getAreaName() + HanziToPinyin.Token.SEPARATOR + entity.getAddr());
        }
        itemHolder.setText(R.id.user_addr_i_txtDef, R.string.set_default);
        itemHolder.setTextColor(R.id.user_addr_i_txtDef, entity.getIsDefault() == 1 ? SupportMenu.CATEGORY_MASK : -7829368);
        itemHolder.setViewSelected(R.id.user_addr_i_imgDef, entity.getIsDefault() == 1);
        setOnClickListener(itemHolder.getView(R.id.user_addr_i_llyDef), entity, position);
        setOnClickListener(itemHolder.getView(R.id.user_addr_i_llyEdit), entity, position);
        setOnClickListener(itemHolder.getView(R.id.user_addr_i_llyDel), entity, position);
    }
}
