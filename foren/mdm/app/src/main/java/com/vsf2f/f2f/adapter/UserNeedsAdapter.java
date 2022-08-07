package com.vsf2f.f2f.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.utils.LocationUtils;
import com.cdlinglu.utils.TimeUtil;
import com.hy.frame.util.Constant;
import com.hy.frame.view.MyGridView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import java.util.List;

/* loaded from: classes2.dex */
public class UserNeedsAdapter extends BaseAdapter {
    private List<DemandDetailBean> data;
    private boolean isService;
    private LayoutInflater mInflater;

    /* loaded from: classes2.dex */
    public interface SubClickListener {
        void itemClicl(int i);

        void toChoiceServer(int i);

        void toEdit(int i);

        void toPay(int i);
    }

    public void setService(boolean service) {
        this.isService = service;
    }

    public UserNeedsAdapter(List<DemandDetailBean> data) {
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
    public View getView(int position, View convertView, final ViewGroup parent) {
        HoldView holdView;
        final DemandDetailBean bean = this.data.get(position);
        if (convertView == null) {
            holdView = new HoldView();
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_user_needs, parent, false);
            holdView.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_parent);
            holdView.mgv_pic = (MyGridView) convertView.findViewById(R.id.user_needs_mgv);
            holdView.voice = (MusicPlayer) convertView.findViewById(R.id.user_needs_play);
            holdView.tv_title = (TextView) convertView.findViewById(R.id.user_needs_title);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.user_needs_detail);
            holdView.tv_price = (TextView) convertView.findViewById(R.id.user_needs_price);
            holdView.tv_unit = (TextView) convertView.findViewById(R.id.user_needs_unit);
            holdView.tv_mode = (TextView) convertView.findViewById(R.id.user_needs_mode);
            holdView.tv_btn = (TextView) convertView.findViewById(R.id.user_needs_btn);
            holdView.tv_time = (TextView) convertView.findViewById(R.id.user_needs_time);
            holdView.tv_distance = (TextView) convertView.findViewById(R.id.user_needs_distance);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.tv_title.setText(bean.getTitle());
        holdView.tv_content.setText(bean.getDescription());
        holdView.tv_price.setText(bean.getReward() + "元");
        holdView.tv_unit.setText("/" + bean.getUnit());
        holdView.tv_mode.setText(bean.getServiceModeStr() + "：");
        holdView.tv_time.setText(TimeUtil.formatDisplayTime(bean.getPublishTime(), "yyyy-MM-dd HH:mm:ss"));
        holdView.tv_distance.setText(LocationUtils.getDistance(bean.getLat(), bean.getLng()));
        if (!"0".equals(bean.getVoiceDuration())) {
            holdView.voice.setDuration(bean.getVoiceDuration() + "");
            holdView.voice.setVisibility(0);
        } else {
            holdView.voice.setVisibility(8);
        }
        if (this.isService) {
            holdView.tv_btn.setText("我要约他");
        } else {
            holdView.tv_btn.setText("我帮助他");
        }
        if (bean.getImgUrlList().size() > 0) {
            List<String> pics = bean.getImgUrlList();
            if (pics.size() > 4) {
                pics = bean.getImgUrlList().subList(0, 4);
            }
            holdView.mgv_pic.setAdapter((ListAdapter) new PicUrlPathAdapter(parent.getContext(), pics, 4, R.dimen.margin_normal, R.dimen.padding_normal));
            holdView.mgv_pic.setClickable(false);
            holdView.mgv_pic.setEnabled(false);
        }
        holdView.ll_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.UserNeedsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("id", bean.getMoId());
                intent.putExtra(Constant.BUNDLE, bundle);
                if (UserNeedsAdapter.this.isService) {
                    intent.setClass(parent.getContext(), ServiceInfoActivity.class);
                } else {
                    intent.setClass(parent.getContext(), DemandInfoActivity.class);
                }
                parent.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        LinearLayout ll_parent;
        MyGridView mgv_pic;
        TextView tv_btn;
        TextView tv_content;
        TextView tv_distance;
        TextView tv_mode;
        TextView tv_price;
        TextView tv_time;
        TextView tv_title;
        TextView tv_unit;
        MusicPlayer voice;

        HoldView() {
        }
    }
}
