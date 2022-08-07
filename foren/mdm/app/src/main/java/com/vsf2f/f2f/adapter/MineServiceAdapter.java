package com.vsf2f.f2f.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.view.MyGridView;
import com.hy.frame.view.RotundityImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MineServiceAdapter extends BaseAdapter {
    private int certAlipay;
    private int certMobile;
    private int certQq;
    private int certRealname;
    private int certWechat;
    private int certZhima;
    private List<DemandDetailBean> data;
    private String headerPic;
    private boolean isUnsold = false;
    private LayoutInflater mInflater;
    private String nickName;
    private SubClickListener subClickListener;
    private int zmScore;

    /* loaded from: classes2.dex */
    public interface SubClickListener {
        void itemClicl(int i);

        void playVoice(int i, MusicPlayer musicPlayer);

        void toChange(int i);

        void toEdit(int i);
    }

    public MineServiceAdapter(List<DemandDetailBean> data, SubClickListener subClickListener) {
        this.headerPic = "";
        this.nickName = "";
        this.data = data;
        this.subClickListener = subClickListener;
        if (this.data == null) {
            this.data = new ArrayList();
        }
        this.zmScore = UserShared.getInstance().getInt(Constant.ZHIMACODE);
        this.certZhima = UserShared.getInstance().getInt(Constant.CERT_ZHIMA);
        this.certRealname = UserShared.getInstance().getInt(Constant.CERT_REALNAME);
        this.certAlipay = UserShared.getInstance().getInt(Constant.CERT_ALIPAY);
        this.certWechat = UserShared.getInstance().getInt(Constant.CERT_WECHAT);
        this.certMobile = UserShared.getInstance().getInt(Constant.CERT_MOBILE);
        this.certQq = UserShared.getInstance().getInt(Constant.CERT_QQ);
        this.headerPic = DemoHelper.getInstance().getCurrentUserPic().getSpath();
        this.nickName = DemoHelper.getInstance().getCurrentUserNick();
    }

    public void setUnsold(boolean isUnsold) {
        this.isUnsold = isUnsold;
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
        final HoldView holdView;
        DemandDetailBean bean = this.data.get(position);
        if (convertView == null) {
            holdView = new HoldView();
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_mine_service, parent, false);
            holdView.riv_user = (RotundityImageView) convertView.findViewById(R.id.riv_user);
            holdView.mp_player = (MusicPlayer) convertView.findViewById(R.id.mp_player);
            holdView.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_parent);
            holdView.mgv_pic = (MyGridView) convertView.findViewById(R.id.mgv_pic);
            holdView.tv_edit = (TextView) convertView.findViewById(R.id.tv_edit);
            holdView.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holdView.tv_zhima = (TextView) convertView.findViewById(R.id.tv_zhima);
            holdView.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holdView.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holdView.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
            holdView.tv_change = (TextView) convertView.findViewById(R.id.tv_change);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holdView.stateView = (IdentyStateView) convertView.findViewById(R.id.identyStateView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        if (this.isUnsold) {
            holdView.tv_change.setText(R.string.up_service);
        } else {
            holdView.tv_change.setText(R.string.down_service);
        }
        holdView.mgv_pic.setAdapter((ListAdapter) new TaskPicAdapter(parent.getContext(), bean.getImgUrlList()));
        holdView.mgv_pic.setClickable(false);
        holdView.mgv_pic.setEnabled(false);
        ComUtil.displayHead(parent.getContext(), holdView.riv_user, this.headerPic);
        holdView.tv_zhima.setText(this.zmScore + "分");
        holdView.stateView.setStatus(this.certMobile, this.certRealname, this.certZhima, this.certAlipay, this.certWechat, this.certQq);
        holdView.tv_name.setText(this.nickName);
        holdView.tv_title.setText(bean.getTitle());
        holdView.tv_content.setText(bean.getDescription());
        holdView.tv_price.setText("￥" + bean.getReward());
        holdView.tv_unit.setText(" /" + bean.getUnit());
        if (TextUtils.isEmpty(bean.getVoiceFullUrl())) {
            holdView.mp_player.setVisibility(4);
        } else {
            holdView.mp_player.setDuration(bean.getVoiceDuration());
            holdView.mp_player.setVisibility(0);
        }
        holdView.mp_player.setDuration(bean.getVoiceDuration());
        holdView.mp_player.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MineServiceAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MineServiceAdapter.this.subClickListener.playVoice(position, holdView.mp_player);
            }
        });
        holdView.ll_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MineServiceAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MineServiceAdapter.this.subClickListener.itemClicl(position);
            }
        });
        holdView.tv_change.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MineServiceAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MineServiceAdapter.this.subClickListener.toChange(position);
            }
        });
        holdView.tv_edit.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MineServiceAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MineServiceAdapter.this.subClickListener.toEdit(position);
            }
        });
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        LinearLayout ll_parent;
        MyGridView mgv_pic;
        MusicPlayer mp_player;
        RotundityImageView riv_user;
        IdentyStateView stateView;
        TextView tv_change;
        TextView tv_content;
        TextView tv_edit;
        TextView tv_name;
        TextView tv_price;
        TextView tv_title;
        TextView tv_unit;
        TextView tv_zhima;

        HoldView() {
        }
    }
}
