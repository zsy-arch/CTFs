package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.adapter.IAdapterListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.MultipleFindBean;
import com.vsf2f.f2f.bean.UserPicBean;
import java.util.List;

/* loaded from: classes2.dex */
public class AddContactAdapter extends BaseAdapter<MultipleFindBean.RowsBean> {
    private List<MultipleFindBean.RowsBean> data;

    public AddContactAdapter(Context context, List<MultipleFindBean.RowsBean> data, IAdapterListener listener) {
        super(context);
        this.data = data;
        setListener(listener);
    }

    public void setData(List<MultipleFindBean.RowsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.data.size();
    }

    @Override // android.widget.Adapter
    public MultipleFindBean.RowsBean getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewHolder holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_addcontact, (ViewGroup) null);
            holder.llyUser = (RelativeLayout) convertView.findViewById(R.id.item_add_rlyUser);
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.item_add_imgAvatar);
            holder.nameView = (TextView) convertView.findViewById(R.id.item_add_txtName);
            holder.addView = (Button) convertView.findViewById(R.id.item_add_btnAdd);
            convertView.setTag(holder);
        }
        ViewHolder holder2 = (ViewHolder) convertView.getTag();
        MultipleFindBean.RowsBean item = getItem(position);
        String nickName = item.getNickName();
        String userName = item.getUserName();
        UserPicBean userPic = item.getUserPic();
        if (!TextUtils.isEmpty(nickName)) {
            holder2.nameView.setText(nickName);
        } else {
            holder2.nameView.setText(userName);
        }
        if (userPic != null) {
            String spath = userPic.getSpath();
            String path = userPic.getPath();
            if (!TextUtils.isEmpty(spath)) {
                Glide.with(getContext()).load(spath).error((int) R.mipmap.def_head).into(holder2.imgAvatar);
            } else if (!TextUtils.isEmpty(path)) {
                Glide.with(getContext()).load(path).error((int) R.mipmap.def_head).into(holder2.imgAvatar);
            } else {
                holder2.imgAvatar.setImageResource(R.mipmap.def_head);
            }
        } else {
            holder2.imgAvatar.setImageResource(R.mipmap.def_head);
        }
        if (item.isCheck()) {
            holder2.addView.setText("已申请");
            holder2.addView.setOnClickListener(null);
        } else {
            holder2.addView.setText("添加");
            setOnClickListener(holder2.addView, item, position);
        }
        setOnClickListener(holder2.llyUser, item, position);
        return convertView;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        Button addView;
        ImageView imgAvatar;
        RelativeLayout llyUser;
        TextView nameView;

        ViewHolder() {
        }
    }
}
