package com.vsf2f.f2f.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.view.MyGridView;
import com.hy.frame.view.RotundityImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandUserInfo;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DemandAdapter extends BaseAdapter {
    private List<DemandDetailBean> data;
    private boolean isService;
    private AdapterItemClick itemClick;

    /* loaded from: classes2.dex */
    public interface AdapterItemClick {
        void itemClick(int i);

        void playVoice(View view, String str);
    }

    public DemandAdapter(List<DemandDetailBean> data, AdapterItemClick itemClick) {
        this.data = data;
        this.itemClick = itemClick;
        if (this.data == null) {
            this.data = new ArrayList();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
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
        HoldView h;
        final DemandDetailBean bean = this.data.get(position);
        if (convertView == null) {
            h = new HoldView();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_demand_item, parent, false);
            h.riv_user = (RotundityImageView) convertView.findViewById(R.id.riv_user);
            h.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_parent);
            h.mgv_pic = (MyGridView) convertView.findViewById(R.id.mgv_pic);
            h.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
            h.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            h.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            h.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            h.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            h.voice = (MusicPlayer) convertView.findViewById(R.id.voicePlayer);
            h.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            h.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
            h.tv_area = (TextView) convertView.findViewById(R.id.tv_area);
            h.tv_servicetype = (TextView) convertView.findViewById(R.id.tv_servicetype);
            h.tv_immediately = (TextView) convertView.findViewById(R.id.tv_immediately);
            h.iv_sex = (ImageView) convertView.findViewById(R.id.iv_sex);
            h.stateView = (IdentyStateView) convertView.findViewById(R.id.identyStateView);
            convertView.setTag(h);
        } else {
            h = (HoldView) convertView.getTag();
        }
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
        h.ll_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandAdapter.this.itemClick.itemClick(position);
            }
        });
        h.voice.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandAdapter.this.itemClick.playVoice(v, bean.getVoiceFullUrl());
            }
        });
        ComUtil.displayHead(parent.getContext(), h.riv_user, userObjBean.getUserPic().getPath());
        h.mgv_pic.setAdapter((ListAdapter) new TaskPicAdapter(parent.getContext(), bean.getImgUrlList().size() > 3 ? bean.getImgUrlList().subList(0, 3) : bean.getImgUrlList(), 3));
        h.mgv_pic.setClickable(false);
        h.mgv_pic.setEnabled(false);
        h.tv_area.setText(bean.getAddress());
        if (isService()) {
            h.tv_immediately.setText("立即预约");
        } else {
            h.tv_immediately.setText("立即抢单");
        }
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        ImageView iv_sex;
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

        HoldView() {
        }
    }
}
