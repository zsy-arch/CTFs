package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.view.MyGridView;
import com.hy.frame.view.RotundityImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandUserInfo;
import com.vsf2f.f2f.bean.OrderDetailBean;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.List;

/* loaded from: classes2.dex */
public class OrderApplyAdapter extends BaseAdapter<OrderDetailBean> {
    private List<OrderDetailBean> datas;
    private LayoutInflater mInflater;

    public OrderApplyAdapter(Context context, List<OrderDetailBean> data) {
        super(context);
        this.datas = data;
    }

    public void setDatas(List<OrderDetailBean> datas) {
        this.datas = datas;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.datas == null) {
            return 0;
        }
        return this.datas.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.datas.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        OrderDetailBean orderBean = this.datas.get(position);
        if (convertView == null) {
            holdView = new HoldView();
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_order_apply, parent, false);
            holdView.ll_order_apply = (LinearLayout) convertView.findViewById(R.id.ll_order_apply);
            holdView.riv_user = (RotundityImageView) convertView.findViewById(R.id.riv_user);
            holdView.mgv_pic = (MyGridView) convertView.findViewById(R.id.mgv_pic);
            holdView.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holdView.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holdView.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holdView.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
            holdView.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            holdView.tv_zhima = (TextView) convertView.findViewById(R.id.tv_zhima);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holdView.stateView = (IdentyStateView) convertView.findViewById(R.id.identyStateView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        setOnClickListener(holdView.ll_order_apply, orderBean, position);
        OrderDetailBean.ShareObjBean bean = orderBean.getShareObj();
        DemandUserInfo userObjBean = bean.getPublishUserObj();
        holdView.stateView.setStatus(userObjBean.getCertMobile(), userObjBean.getCertRealname(), userObjBean.getCertZhima(), userObjBean.getCertAlipay(), userObjBean.getCertWechat(), userObjBean.getCertQq());
        holdView.tv_zhima.setText(userObjBean.getZmScore() + "");
        holdView.mgv_pic.setAdapter((ListAdapter) new PicUrlPathAdapter(parent.getContext(), bean.getImgUrlList(), 4, R.dimen.lv_height, R.dimen.spacing));
        holdView.mgv_pic.setClickable(false);
        holdView.mgv_pic.setEnabled(false);
        holdView.mgv_pic.setPressed(false);
        Glide.with(parent.getContext()).load(userObjBean.getUserPic().getPath()).error((int) R.mipmap.def_head).into(holdView.riv_user);
        holdView.tv_name.setText(bean.getPublishUserObj().getNickName());
        if (TextUtils.isEmpty(bean.getDescription())) {
            holdView.tv_content.setText("");
        } else {
            holdView.tv_content.setText(bean.getDescription());
        }
        holdView.tv_title.setText(bean.getTitle());
        holdView.tv_price.setText("￥" + bean.getReward());
        holdView.tv_unit.setText("/" + bean.getUnit());
        switch (orderBean.getStatus()) {
            case 0:
                holdView.tv_status.setText("等待");
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.demand_order_gold));
                break;
            case 1:
                holdView.tv_status.setText("作废");
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.gray_a));
                break;
            case 2:
                holdView.tv_status.setText("拒绝");
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.demand_order_gold));
                break;
            case 3:
                holdView.tv_status.setText("成交");
                holdView.tv_status.setTextColor(parent.getResources().getColor(R.color.demand_order_green));
                break;
            default:
                holdView.tv_status.setText("取消");
                break;
        }
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        LinearLayout ll_order_apply;
        MyGridView mgv_pic;
        RotundityImageView riv_user;
        IdentyStateView stateView;
        TextView tv_content;
        TextView tv_name;
        TextView tv_price;
        TextView tv_status;
        TextView tv_title;
        TextView tv_unit;
        TextView tv_zhima;

        HoldView() {
        }
    }
}
