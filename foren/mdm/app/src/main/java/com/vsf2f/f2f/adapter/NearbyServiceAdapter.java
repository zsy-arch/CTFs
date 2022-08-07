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
import com.cdlinglu.server.audio.MusicPlayer;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import java.util.List;

/* loaded from: classes2.dex */
public class NearbyServiceAdapter extends BaseAdapter {
    private List<DemandDetailBean> data;
    private AdapterItemClick itemClick;

    /* loaded from: classes2.dex */
    public interface AdapterItemClick {
        void ask(DemandDetailBean demandDetailBean);

        void getOrder(DemandDetailBean demandDetailBean);

        void itemClick(int i);

        void playVoice(View view, String str);
    }

    public NearbyServiceAdapter(List<DemandDetailBean> data, AdapterItemClick itemClick) {
        this.data = data;
        this.itemClick = itemClick;
    }

    public List<DemandDetailBean> getData() {
        return this.data;
    }

    public void setData(List<DemandDetailBean> data) {
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
        final DemandDetailBean bean = (DemandDetailBean) getItem(position);
        if (convertView == null) {
            holdView = new HoldView();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_service, parent, false);
            holdView.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_parent);
            holdView.tv_ask = (TextView) convertView.findViewById(R.id.tv_ask);
            holdView.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holdView.tv_zhima = (TextView) convertView.findViewById(R.id.tv_zhima);
            holdView.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holdView.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holdView.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
            holdView.voicePlayer = (MusicPlayer) convertView.findViewById(R.id.voicePlayer);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holdView.tv_applyed = (TextView) convertView.findViewById(R.id.tv_applyed);
            holdView.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.tv_name.setText(bean.getPublishUserObj().getNickName());
        holdView.tv_title.setText(bean.getTitle());
        holdView.tv_content.setText(bean.getDescription());
        holdView.tv_price.setText("￥" + bean.getReward());
        holdView.tv_unit.setText(" /" + bean.getUnit());
        holdView.tv_zhima.setText(bean.getPublishUserObj().getZmScore() + "分");
        if (TextUtils.isEmpty(bean.getVoiceFullUrl())) {
            holdView.voicePlayer.setVisibility(4);
            holdView.voicePlayer.setDuration("0");
        } else {
            holdView.voicePlayer.setDuration(bean.getVoiceDuration());
            holdView.voicePlayer.setVisibility(0);
        }
        holdView.ll_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.NearbyServiceAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NearbyServiceAdapter.this.itemClick.itemClick(position);
            }
        });
        holdView.tv_applyed.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.NearbyServiceAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NearbyServiceAdapter.this.itemClick.getOrder(bean);
            }
        });
        holdView.voicePlayer.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.NearbyServiceAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NearbyServiceAdapter.this.itemClick.playVoice(v, bean.getVoiceFullUrl());
            }
        });
        holdView.tv_ask.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.NearbyServiceAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NearbyServiceAdapter.this.itemClick.ask(bean);
            }
        });
        if (bean.getImgUrlList().size() == 0) {
            Glide.with(parent.getContext()).load(Integer.valueOf((int) R.mipmap.img_no_pic)).into(holdView.iv_icon);
        } else {
            Glide.with(parent.getContext()).load(bean.getImgUrlList().get(0)).error((int) R.mipmap.img_no_pic).into(holdView.iv_icon);
        }
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        ImageView iv_icon;
        LinearLayout ll_parent;
        TextView tv_applyed;
        TextView tv_ask;
        TextView tv_content;
        TextView tv_name;
        TextView tv_price;
        TextView tv_title;
        TextView tv_unit;
        TextView tv_zhima;
        MusicPlayer voicePlayer;

        HoldView() {
        }
    }
}
