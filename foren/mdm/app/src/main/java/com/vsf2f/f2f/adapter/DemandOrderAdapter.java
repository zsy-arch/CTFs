package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.OrderDetailBean;
import com.vsf2f.f2f.bean.result.CertUserInfoBean;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.List;

/* loaded from: classes2.dex */
public class DemandOrderAdapter extends BaseAdapter<OrderDetailBean> {
    private List<OrderDetailBean> datas;
    private ItemClick itemClick;
    private LayoutInflater mInflater;
    private int statusType;
    private int type;

    /* loaded from: classes2.dex */
    public interface ItemClick {
        void chat(int i);

        void doFunction(int i, int i2);

        void doSelect(OrderDetailBean orderDetailBean);
    }

    public int getStatusType() {
        return this.statusType;
    }

    public void setStatusType(int statusType) {
        this.statusType = statusType;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DemandOrderAdapter(Context context, List<OrderDetailBean> datas, ItemClick itemClick) {
        super(context);
        this.datas = datas;
        this.itemClick = itemClick;
    }

    public List<OrderDetailBean> getDatas() {
        return this.datas;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        CertUserInfoBean userObjBean;
        String statusStr;
        OrderDetailBean bean = this.datas.get(position);
        if (convertView == null) {
            holdView = new HoldView();
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_demand_order, parent, false);
            holdView.ll_order_item = (LinearLayout) convertView.findViewById(R.id.ll_order_item);
            holdView.tv_doservice = (TextView) convertView.findViewById(R.id.tv_doservice);
            holdView.tv_publisher = (TextView) convertView.findViewById(R.id.tv_publisher);
            holdView.tv_order_no = (TextView) convertView.findViewById(R.id.tv_order_no);
            holdView.tv_serviced = (TextView) convertView.findViewById(R.id.tv_serviced);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holdView.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);
            holdView.tv_cancel = (TextView) convertView.findViewById(R.id.tv_cancel);
            holdView.tv_return = (TextView) convertView.findViewById(R.id.tv_return);
            holdView.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holdView.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holdView.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            holdView.tv_sure = (TextView) convertView.findViewById(R.id.tv_sure);
            holdView.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holdView.tv_deal = (TextView) convertView.findViewById(R.id.tv_deal);
            holdView.tv_chat = (TextView) convertView.findViewById(R.id.tv_chat);
            holdView.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
            holdView.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            holdView.tv_refund = (TextView) convertView.findViewById(R.id.tv_refund);
            holdView.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holdView.stateView = (IdentyStateView) convertView.findViewById(R.id.identyStateView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        if (this.type == 0) {
            userObjBean = bean.getShareObj().getServiceUserObj();
        } else {
            userObjBean = bean.getShareObj().getPublishUserObj();
        }
        setOnClickListener(holdView.ll_order_item, bean, position);
        holdView.tv_publisher.setText(userObjBean.getNickName() + "");
        holdView.stateView.setStatus(userObjBean.getCertMobile(), userObjBean.getCertRealname(), userObjBean.getCertZhima(), userObjBean.getCertAlipay(), userObjBean.getCertWechat(), userObjBean.getCertQq());
        if (HyUtil.isNoEmpty(bean.getShareObj().getImgUrlList())) {
            Glide.with(parent.getContext()).load(bean.getShareObj().getImgUrlList().get(0)).error((int) R.mipmap.img_no_pic).into(holdView.iv_icon);
        } else {
            Glide.with(parent.getContext()).load(Integer.valueOf((int) R.mipmap.img_no_pic)).into(holdView.iv_icon);
        }
        holdView.tv_price.setText("￥ " + HyUtil.formatMoney(Double.valueOf(bean.getShareObj().getReward() + bean.getOtherAmount())));
        holdView.tv_unit.setText("/" + bean.getShareObj().getUnit());
        holdView.tv_title.setText(bean.getShareObj().getTitle() + "");
        holdView.tv_time.setText(bean.getCreateTime() + "");
        holdView.tv_order_no.setText("订单号：" + bean.getMoId());
        holdView.tv_content.setText(bean.getShareObj().getDescription() + "");
        holdView.tv_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandOrderAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandOrderAdapter.this.itemClick.doFunction(position, 0);
            }
        });
        holdView.tv_return.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandOrderAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandOrderAdapter.this.itemClick.doFunction(position, 1);
            }
        });
        holdView.tv_sure.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandOrderAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandOrderAdapter.this.itemClick.doFunction(position, 2);
            }
        });
        holdView.tv_comment.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandOrderAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandOrderAdapter.this.itemClick.doFunction(position, 3);
            }
        });
        holdView.tv_doservice.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandOrderAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandOrderAdapter.this.itemClick.doFunction(position, 4);
            }
        });
        holdView.tv_serviced.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandOrderAdapter.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandOrderAdapter.this.itemClick.doFunction(position, 5);
            }
        });
        holdView.tv_deal.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandOrderAdapter.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandOrderAdapter.this.itemClick.doFunction(position, 6);
            }
        });
        holdView.tv_refund.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandOrderAdapter.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandOrderAdapter.this.itemClick.doFunction(position, 7);
            }
        });
        holdView.tv_chat.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandOrderAdapter.9
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DemandOrderAdapter.this.itemClick.chat(position);
            }
        });
        holdView.tv_state.setText("");
        switch (bean.getStatus()) {
            case 10:
                if (this.type != 0) {
                    statusStr = "进行中";
                    holdView.setStateColor(parent, R.color.demand_order_red);
                    holdView.setFunction(0, 8, 8, 8, 0, 8, 8, 8);
                    break;
                } else {
                    statusStr = "待服务";
                    holdView.setStateColor(parent, R.color.demand_order_green);
                    holdView.setFunction(0, 8, 8, 8, 8, 8, 8, 8);
                    break;
                }
            case 11:
                statusStr = "服务中";
                holdView.setStateColor(parent, R.color.demand_order_green);
                if (this.type == 0) {
                    if (bean.getShareObj().getPayType() != 0) {
                        holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 8);
                        break;
                    } else {
                        holdView.setFunction(8, 0, 8, 8, 8, 8, 8, 8);
                        break;
                    }
                } else {
                    holdView.setFunction(8, 8, 8, 8, 8, 0, 8, 8);
                    break;
                }
            case 12:
                statusStr = "服务结束";
                holdView.setStateColor(parent, R.color.demand_super);
                holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 8);
                break;
            case 13:
                statusStr = "待确认";
                holdView.setStateColor(parent, R.color.demand_order_cyan);
                if (this.type == 0) {
                    if (bean.getShareObj().getPayType() != 0) {
                        holdView.setFunction(8, 8, 0, 8, 8, 8, 8, 8);
                        break;
                    } else {
                        holdView.setFunction(8, 0, 0, 8, 8, 8, 8, 8);
                        break;
                    }
                } else {
                    holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 8);
                    break;
                }
            case 14:
                statusStr = "已取消";
                holdView.setStateColor(parent, R.color.demand_super);
                holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 8);
                break;
            case 15:
                holdView.setStateColor(parent, R.color.demand_order_green);
                statusStr = "已申诉";
                holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 8);
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            default:
                statusStr = "未知状态";
                break;
            case 20:
                statusStr = "退款中";
                holdView.setStateColor(parent, R.color.demand_order_gold);
                if (this.type != 0) {
                    holdView.setFunction(8, 8, 8, 8, 8, 8, 0, 8);
                    break;
                } else {
                    holdView.setFunction(8, 8, 0, 8, 8, 8, 8, 0);
                    break;
                }
            case 21:
                holdView.setStateColor(parent, R.color.demand_super);
                statusStr = "已退款";
                holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 0);
                break;
            case 22:
                statusStr = "拒绝退款";
                holdView.setStateColor(parent, R.color.demand_super);
                if (this.type != 0) {
                    holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 0);
                    break;
                } else {
                    holdView.setFunction(8, 8, 0, 8, 8, 8, 0, 8);
                    break;
                }
            case 30:
                statusStr = "待评价";
                holdView.setStateColor(parent, R.color.demand_order_gold);
                if (this.type != 0) {
                    holdView.setFunction(8, 8, 8, 0, 8, 8, 8, 8);
                    break;
                } else {
                    holdView.setFunction(8, 8, 8, 0, 8, 8, 8, 8);
                    break;
                }
            case 31:
                if (this.type != 0) {
                    statusStr = "需求方已评价";
                    holdView.setStateColor(parent, R.color.demand_order_gold);
                    holdView.setFunction(8, 8, 8, 0, 8, 8, 8, 8);
                    break;
                } else {
                    statusStr = "已评价";
                    holdView.setStateColor(parent, R.color.demand_super);
                    holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 8);
                    break;
                }
            case 32:
                if (this.type != 0) {
                    statusStr = "已评价";
                    holdView.setStateColor(parent, R.color.demand_super);
                    holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 8);
                    break;
                } else {
                    statusStr = "服务商已评价";
                    holdView.setStateColor(parent, R.color.demand_order_gold);
                    holdView.setFunction(8, 8, 8, 0, 8, 8, 8, 8);
                    break;
                }
            case 33:
                statusStr = "已评价";
                holdView.setStateColor(parent, R.color.demand_super);
                holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 8);
                break;
        }
        if (this.type == 1 && this.statusType == 1) {
            statusStr = "已申请";
            holdView.setStateColor(parent, R.color.demand_order_green);
            holdView.setFunction(8, 8, 8, 8, 8, 8, 8, 8);
        }
        holdView.tv_status.setText(statusStr);
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        ImageView iv_icon;
        ImageView iv_select;
        LinearLayout ll_order_item;
        IdentyStateView stateView;
        TextView tv_cancel;
        TextView tv_chat;
        TextView tv_comment;
        TextView tv_content;
        TextView tv_deal;
        TextView tv_doservice;
        TextView tv_order_no;
        TextView tv_price;
        TextView tv_publisher;
        TextView tv_refund;
        TextView tv_return;
        TextView tv_serviced;
        TextView tv_state;
        TextView tv_status;
        TextView tv_sure;
        TextView tv_time;
        TextView tv_title;
        TextView tv_unit;

        HoldView() {
        }

        void setFunction(int cancel, int returnMon, int sure, int comment, int doserv, int serviced, int deal, int refund) {
            this.tv_cancel.setVisibility(cancel);
            this.tv_return.setVisibility(returnMon);
            this.tv_sure.setVisibility(sure);
            this.tv_comment.setVisibility(comment);
            this.tv_doservice.setVisibility(doserv);
            this.tv_serviced.setVisibility(serviced);
            this.tv_deal.setVisibility(deal);
            this.tv_refund.setVisibility(refund);
            this.tv_status.setVisibility(0);
        }

        void setStateColor(ViewGroup parent, @ColorRes int rid) {
            this.tv_status.setTextColor(parent.getResources().getColor(rid));
        }
    }
}
