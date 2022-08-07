package com.vsf2f.f2f.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.utils.LocationUtils;
import com.hy.frame.view.RoundImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.FriendsListBean;
import java.util.List;

/* loaded from: classes2.dex */
public class NearbyPeopleAdapter extends BaseAdapter {
    private List<FriendsListBean.RowsBean> data;
    private AdapterItemClick itemClick;

    /* loaded from: classes2.dex */
    public interface AdapterItemClick {
        void itemClick(int i);
    }

    public NearbyPeopleAdapter(List<FriendsListBean.RowsBean> data, AdapterItemClick itemClick) {
        this.data = data;
        this.itemClick = itemClick;
    }

    public List<FriendsListBean.RowsBean> getData() {
        return this.data;
    }

    public void setData(List<FriendsListBean.RowsBean> data) {
        this.data = data;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        FriendsListBean.RowsBean bean = (FriendsListBean.RowsBean) getItem(position);
        if (convertView == null) {
            holdView = new HoldView();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_people, parent, false);
            holdView.riv_user = (RoundImageView) convertView.findViewById(R.id.riv_user);
            holdView.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_parent);
            holdView.iv_sex = (ImageView) convertView.findViewById(R.id.iv_sex);
            holdView.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
            holdView.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holdView.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        if (bean.getGender() != -1) {
            holdView.iv_sex.setVisibility(0);
            holdView.iv_sex.setSelected(bean.getGender() == 1);
        } else {
            holdView.iv_sex.setVisibility(4);
        }
        if (bean.getAge() != 0) {
            holdView.tv_age.setVisibility(0);
            holdView.tv_age.setText(bean.getAge() + "岁");
        } else {
            holdView.tv_age.setVisibility(8);
        }
        holdView.tv_name.setText(bean.getNickName());
        if (TextUtils.isEmpty(bean.getContent())) {
            holdView.tv_content.setVisibility(4);
        } else {
            holdView.tv_content.setVisibility(0);
            holdView.tv_content.setText(bean.getContent());
        }
        holdView.tv_distance.setText(LocationUtils.getDistanceStr(bean.getLat(), bean.getLng()));
        holdView.ll_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.NearbyPeopleAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NearbyPeopleAdapter.this.itemClick.itemClick(position);
            }
        });
        Glide.with(parent.getContext()).load(bean.getUserPic().getSpath()).error((int) R.mipmap.def_head).into(holdView.riv_user);
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        ImageView iv_sex;
        LinearLayout ll_parent;
        RoundImageView riv_user;
        TextView tv_age;
        TextView tv_content;
        TextView tv_distance;
        TextView tv_name;

        HoldView() {
        }
    }
}
