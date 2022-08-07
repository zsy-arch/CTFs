package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.server.audio.MusicPlayer;
import com.hy.frame.view.MyGridView;
import com.hy.frame.view.RotundityImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandUserInfo;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.List;

/* loaded from: classes2.dex */
public class DemandMainRecycleAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<DemandDetailBean> data;
    private boolean isService;
    private AdapterItemClick itemClick;

    /* loaded from: classes2.dex */
    public interface AdapterItemClick {
        void itemClick(int i);

        void playVoice(View view, String str);
    }

    public DemandMainRecycleAdapter(List<DemandDetailBean> data, AdapterItemClick itemClick) {
        this.data = data;
        this.itemClick = itemClick;
    }

    public List<DemandDetailBean> getData() {
        return this.data;
    }

    public void setData(List<DemandDetailBean> data) {
        this.data = data;
    }

    public boolean isService() {
        return this.isService;
    }

    public void setService(boolean service) {
        this.isService = service;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new HoldView(LayoutInflater.from(this.context).inflate(R.layout.layout_demand_item, parent, false));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HoldView h = (HoldView) holder;
        final DemandDetailBean bean = this.data.get(position);
        h.tv_name.setText(bean.getPublishUserObj().getNickName());
        h.tv_title.setText(bean.getTitle());
        h.tv_time.setText(bean.getPublishTime());
        h.tv_content.setText(bean.getDescription());
        h.tv_price.setText("￥" + bean.getReward() + "元");
        h.tv_unit.setText("/" + bean.getUnit());
        DemandUserInfo userObjBean = bean.getPublishUserObj();
        h.stateView.setStatus(userObjBean.getCertMobile(), userObjBean.getCertRealname(), userObjBean.getCertZhima(), userObjBean.getCertAlipay(), userObjBean.getCertWechat(), userObjBean.getCertQq());
        if (userObjBean.getGender() != -1) {
            h.iv_sex.setVisibility(0);
            h.iv_sex.setSelected(userObjBean.getGender() == 1);
        } else {
            h.iv_sex.setVisibility(8);
        }
        h.tv_location.setText(bean.getLocalDistanceStr());
        h.tv_servicetype.setText(bean.getServiceModeStr());
        if (TextUtils.isEmpty(bean.getVoiceFullUrl()) || TextUtils.isEmpty(bean.getVoiceDuration()) || "0".equals(bean.getVoiceDuration())) {
            h.voice.setVisibility(4);
        } else {
            h.voice.setDuration(bean.getVoiceDuration());
            h.voice.setVisibility(0);
        }
        h.ll_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandMainRecycleAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandMainRecycleAdapter.this.itemClick.itemClick(position);
            }
        });
        h.voice.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandMainRecycleAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandMainRecycleAdapter.this.itemClick.playVoice(v, bean.getVoiceFullUrl());
            }
        });
        Glide.with(this.context).load(userObjBean.getUserPic().getPath()).error((int) R.mipmap.def_head).into(h.riv_user);
        h.mgv_pic.setAdapter((ListAdapter) new TaskPicAdapter(this.context, bean.getImgUrlList().size() > 3 ? bean.getImgUrlList().subList(0, 3) : bean.getImgUrlList(), 3));
        h.mgv_pic.setClickable(false);
        h.mgv_pic.setEnabled(false);
        h.tv_area.setText(bean.getAddress());
        if (isService()) {
            h.tv_immediately.setText("立即预约");
        } else {
            h.tv_immediately.setText("立即抢单");
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    /* loaded from: classes2.dex */
    static class HoldView extends RecyclerView.ViewHolder {
        ImageView iv_sex;
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-1, -2);
        LinearLayout ll_parent;
        MyGridView mgv_pic;
        RotundityImageView riv_user;
        IdentyStateView stateView;
        TextView tv_area;
        TextView tv_content;
        TextView tv_immediately;
        TextView tv_location;
        TextView tv_name;
        TextView tv_price;
        TextView tv_servicetype;
        TextView tv_time;
        TextView tv_title;
        TextView tv_unit;
        MusicPlayer voice;

        public HoldView(View convertView) {
            super(convertView);
            convertView.setLayoutParams(this.layoutParams);
            this.riv_user = (RotundityImageView) convertView.findViewById(R.id.riv_user);
            this.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_parent);
            this.mgv_pic = (MyGridView) convertView.findViewById(R.id.mgv_pic);
            this.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
            this.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            this.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            this.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            this.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            this.voice = (MusicPlayer) convertView.findViewById(R.id.voicePlayer);
            this.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            this.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
            this.tv_area = (TextView) convertView.findViewById(R.id.tv_area);
            this.tv_servicetype = (TextView) convertView.findViewById(R.id.tv_servicetype);
            this.tv_immediately = (TextView) convertView.findViewById(R.id.tv_immediately);
            this.iv_sex = (ImageView) convertView.findViewById(R.id.iv_sex);
            this.stateView = (IdentyStateView) convertView.findViewById(R.id.identyStateView);
        }
    }
}
