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
public class MineDemandAdapter extends BaseAdapter {
    private int certAlipay;
    private int certMobile;
    private int certQq;
    private int certRealname;
    private int certWechat;
    private int certZhima;
    private List<DemandDetailBean> data;
    private String headerPic;
    private boolean isHistory = false;
    private LayoutInflater mInflater;
    private String nickName;
    private SubClickListener subClickListener;
    private int zmScore;

    /* loaded from: classes2.dex */
    public interface SubClickListener {
        void itemClicl(int i);

        void toChoiceServer(int i);

        void toEdit(int i);

        void toPay(int i);
    }

    public MineDemandAdapter(List<DemandDetailBean> data, SubClickListener subClickListener) {
        this.headerPic = "";
        this.nickName = "";
        this.data = data;
        this.subClickListener = subClickListener;
        if (this.data == null) {
            this.data = new ArrayList();
        }
        this.zmScore = UserShared.getInstance().getInt(Constant.ZHIMACODE);
        this.certMobile = UserShared.getInstance().getInt(Constant.CERT_MOBILE);
        this.certRealname = UserShared.getInstance().getInt(Constant.CERT_REALNAME);
        this.certZhima = UserShared.getInstance().getInt(Constant.CERT_ZHIMA);
        this.certAlipay = UserShared.getInstance().getInt(Constant.CERT_ALIPAY);
        this.certWechat = UserShared.getInstance().getInt(Constant.CERT_WECHAT);
        this.certQq = UserShared.getInstance().getInt(Constant.CERT_QQ);
        this.headerPic = DemoHelper.getInstance().getCurrentUserPic().getSpath();
        this.nickName = DemoHelper.getInstance().getCurrentUserNick();
    }

    public void setHistory(boolean history) {
        this.isHistory = history;
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
        DemandDetailBean bean = this.data.get(position);
        if (convertView == null) {
            holdView = new HoldView();
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_mine_demand, parent, false);
            holdView.riv_user = (RotundityImageView) convertView.findViewById(R.id.riv_user);
            holdView.mgv_pic = (MyGridView) convertView.findViewById(R.id.mgv_pic);
            holdView.iv_edit = (ImageView) convertView.findViewById(R.id.iv_edit);
            holdView.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holdView.tv_server = (TextView) convertView.findViewById(R.id.tv_server);
            holdView.ll_choice = (LinearLayout) convertView.findViewById(R.id.ll_choice);
            holdView.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_parent);
            holdView.iv_go = (ImageView) convertView.findViewById(R.id.iv_go);
            holdView.tv_zhima = (TextView) convertView.findViewById(R.id.tv_zhima);
            holdView.tv_browser = (TextView) convertView.findViewById(R.id.tv_browser);
            holdView.tv_collect = (TextView) convertView.findViewById(R.id.tv_collect);
            holdView.tv_right = (TextView) convertView.findViewById(R.id.tv_right);
            holdView.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holdView.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holdView.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
            holdView.tv_tab = (TextView) convertView.findViewById(R.id.tv_tab);
            holdView.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holdView.stateView = (IdentyStateView) convertView.findViewById(R.id.identyStateView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.mgv_pic.setAdapter((ListAdapter) new TaskPicAdapter(parent.getContext(), bean.getImgUrlList()));
        holdView.mgv_pic.setClickable(false);
        holdView.mgv_pic.setEnabled(false);
        holdView.tv_browser.setText(bean.getViewCount() + "");
        holdView.tv_collect.setText(bean.getCollectCount() + "");
        holdView.tv_name.setText(this.nickName);
        ComUtil.displayHead(parent.getContext(), holdView.riv_user, this.headerPic);
        holdView.stateView.setStatus(this.certMobile, this.certRealname, this.certZhima, this.certAlipay, this.certWechat, this.certQq);
        holdView.tv_zhima.setText(this.zmScore + "分");
        if (TextUtils.isEmpty(bean.getDescription())) {
            holdView.tv_content.setText("");
        } else {
            holdView.tv_content.setText(bean.getDescription());
        }
        holdView.tv_title.setText(bean.getTitle());
        if (this.isHistory) {
            holdView.iv_edit.setVisibility(4);
        } else {
            holdView.iv_edit.setVisibility(0);
        }
        holdView.tv_right.setText("");
        holdView.tv_server.setText("");
        holdView.tv_price.setText("￥" + bean.getReward());
        holdView.tv_unit.setText(" /" + bean.getUnit());
        holdView.tv_tab.setTextColor(parent.getResources().getColor(R.color.demand_super));
        holdView.ll_choice.setVisibility(0);
        holdView.tv_status.setClickable(false);
        holdView.tv_status.setEnabled(false);
        switch (bean.getStatus()) {
            case 10:
                holdView.tv_status.setText("待支付");
                holdView.tv_status.setEnabled(true);
                holdView.tv_status.setClickable(true);
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.demand_nopay));
                holdView.ll_choice.setVisibility(8);
                break;
            case 11:
                if (bean.getPayType() == 1) {
                    holdView.tv_status.setText("线下支付");
                } else {
                    holdView.tv_status.setText("已支付");
                }
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.golden_fac));
                holdView.iv_go.setVisibility(0);
                holdView.tv_tab.setText("待选择服务商");
                holdView.tv_tab.setTextColor(parent.getResources().getColor(R.color.btn_green));
                holdView.tv_right.setText(bean.getOfferList().size() + "");
                break;
            case 20:
                holdView.tv_status.setText("待服务");
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.demand_published));
                holdView.ll_choice.setEnabled(false);
                holdView.iv_go.setVisibility(8);
                holdView.iv_edit.setVisibility(4);
                holdView.tv_tab.setText("服务商");
                holdView.tv_server.setText(bean.getServiceProviderObj().getNickName());
                break;
            case 21:
                holdView.tv_status.setText("服务中");
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.demand_servicing));
                holdView.ll_choice.setEnabled(false);
                holdView.iv_go.setVisibility(8);
                holdView.iv_edit.setVisibility(4);
                holdView.tv_tab.setText("服务商");
                holdView.tv_server.setText(bean.getServiceProviderObj().getNickName());
                break;
            case 22:
                holdView.tv_status.setText("退款中");
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.black_deep));
                holdView.ll_choice.setEnabled(false);
                holdView.iv_go.setVisibility(8);
                holdView.iv_edit.setVisibility(4);
                holdView.tv_tab.setText("服务商");
                holdView.tv_server.setText(bean.getServiceProviderObj().getNickName());
                break;
            case 23:
                holdView.tv_status.setText("已完成");
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.demand_over));
                holdView.ll_choice.setEnabled(false);
                holdView.iv_go.setVisibility(8);
                holdView.iv_edit.setVisibility(4);
                holdView.tv_tab.setText("服务商");
                holdView.tv_server.setText(bean.getServiceProviderObj().getNickName());
                break;
            case 30:
                holdView.tv_status.setText("已取消");
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.demand_sub));
                holdView.ll_choice.setVisibility(8);
                break;
            case 31:
                holdView.tv_status.setText("已过期");
                holdView.iv_edit.setVisibility(4);
                holdView.iv_go.setVisibility(8);
                holdView.ll_choice.setEnabled(false);
                holdView.tv_right.setText(bean.getServiceProviderObj().getNickName());
                break;
            default:
                holdView.iv_edit.setVisibility(4);
                holdView.iv_go.setVisibility(8);
                holdView.tv_status.setClickable(false);
                holdView.ll_choice.setEnabled(false);
                break;
        }
        holdView.ll_parent.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MineDemandAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MineDemandAdapter.this.subClickListener.itemClicl(position);
            }
        });
        holdView.tv_status.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MineDemandAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MineDemandAdapter.this.subClickListener.toPay(position);
            }
        });
        holdView.ll_choice.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MineDemandAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MineDemandAdapter.this.subClickListener.toChoiceServer(position);
            }
        });
        holdView.iv_edit.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MineDemandAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MineDemandAdapter.this.subClickListener.toEdit(position);
            }
        });
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        ImageView iv_edit;
        ImageView iv_go;
        LinearLayout ll_choice;
        LinearLayout ll_parent;
        MyGridView mgv_pic;
        RotundityImageView riv_user;
        IdentyStateView stateView;
        TextView tv_browser;
        TextView tv_collect;
        TextView tv_content;
        TextView tv_name;
        TextView tv_price;
        TextView tv_right;
        TextView tv_server;
        TextView tv_status;
        TextView tv_tab;
        TextView tv_title;
        TextView tv_unit;
        TextView tv_zhima;

        HoldView() {
        }
    }
}
