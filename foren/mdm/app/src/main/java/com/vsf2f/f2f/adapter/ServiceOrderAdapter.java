package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.util.HyUtil;
import com.hyphenate.util.EMPrivateConstant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandUserInfo;
import com.vsf2f.f2f.bean.OrderDetailBean;
import java.util.List;

/* loaded from: classes2.dex */
public class ServiceOrderAdapter extends BaseAdapter<OrderDetailBean> {
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

    public void setStatusType(int statusType) {
        this.statusType = statusType;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ServiceOrderAdapter(Context context, List<OrderDetailBean> datas, ItemClick itemClick) {
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
        OrderHoldView holdView;
        DemandUserInfo userObjBean;
        String statusStr;
        OrderDetailBean bean = this.datas.get(position);
        if (convertView == null) {
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_service_order, parent, false);
            holdView = new OrderHoldView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (OrderHoldView) convertView.getTag();
        }
        if (this.type == 1) {
            userObjBean = bean.getShareServiceObj().getServiceUserObj();
        } else {
            userObjBean = bean.getShareServiceObj().getPublishUserObj();
        }
        setOnClickListener(holdView.ll_order_item, bean, position);
        holdView.tv_publisher.setText(userObjBean.getNickName() + "");
        holdView.tv_count.setText(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + bean.getBuyNum());
        Glide.with(parent.getContext()).load(userObjBean.getUserPic().getPath() + "").error((int) R.mipmap.img_no_pic).into(holdView.riv_revers);
        if (HyUtil.isNoEmpty(bean.getShareServiceObj().getImgUrlList())) {
            Glide.with(parent.getContext()).load(bean.getShareServiceObj().getImgUrlList().get(0)).error((int) R.mipmap.img_no_pic).into(holdView.iv_icon);
        } else {
            Glide.with(parent.getContext()).load(Integer.valueOf((int) R.mipmap.img_no_pic)).into(holdView.iv_icon);
        }
        holdView.tv_price.setText("" + HyUtil.formatMoney(Double.valueOf(bean.getShareServiceObj().getReward())));
        holdView.tv_amount.setText("" + HyUtil.formatMoney(Double.valueOf(bean.getAmount() + bean.getOtherAmount())));
        holdView.tv_unit.setText("/" + bean.getShareServiceObj().getUnit());
        holdView.tv_title.setText(bean.getShareServiceObj().getTitle() + "");
        holdView.tv_time.setText(bean.getCreateTime() + "");
        holdView.tv_order_no.setText("订单号：" + bean.getMoId());
        holdView.tv_content.setText(bean.getShareServiceObj().getDescription() + "");
        holdView.tv_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 0);
            }
        });
        holdView.tv_return.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 1);
            }
        });
        holdView.tv_sure.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 2);
            }
        });
        holdView.tv_comment.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 3);
            }
        });
        holdView.tv_doservice.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 4);
            }
        });
        holdView.tv_serviced.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 5);
            }
        });
        holdView.tv_deal.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 6);
            }
        });
        holdView.tv_refund.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 7);
            }
        });
        holdView.tv_pay.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.9
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 8);
            }
        });
        holdView.tv_reject.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.10
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 9);
            }
        });
        holdView.tv_accept.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServiceOrderAdapter.11
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServiceOrderAdapter.this.itemClick.doFunction(position, 10);
            }
        });
        switch (bean.getStatus()) {
            case 10:
                statusStr = "进行中";
                if (this.type != 0) {
                    holdView.setStateColor(parent, R.color.demand_order_green);
                    if (bean.getPayStatus() != 0) {
                        holdView.setFunction(0);
                        break;
                    } else {
                        holdView.setFunction(0);
                        break;
                    }
                } else {
                    holdView.setStateColor(parent, R.color.demand_order_red);
                    holdView.setFunction(0, 4);
                    break;
                }
            case 11:
                statusStr = "服务中";
                holdView.setStateColor(parent, R.color.demand_order_green);
                if (this.type != 0) {
                    holdView.setFunction(1);
                    break;
                } else {
                    holdView.setFunction(5);
                    break;
                }
            case 12:
                statusStr = "已关闭";
                holdView.setStateColor(parent, R.color.demand_super);
                if (this.type != 0) {
                    holdView.setFunction(new int[0]);
                    break;
                } else {
                    holdView.setFunction(new int[0]);
                    break;
                }
            case 13:
                statusStr = "待确认";
                holdView.setStateColor(parent, R.color.demand_order_cyan);
                if (this.type != 0) {
                    holdView.setFunction(1, 2);
                    break;
                } else {
                    holdView.setFunction(new int[0]);
                    break;
                }
            case 14:
                statusStr = "已取消";
                holdView.setStateColor(parent, R.color.demand_super);
                holdView.setFunction(new int[0]);
                break;
            case 15:
                holdView.setStateColor(parent, R.color.demand_order_green);
                statusStr = "已申诉";
                holdView.setFunction(new int[0]);
                break;
            case 16:
                holdView.setStateColor(parent, R.color.demand_order_green);
                statusStr = "待接单";
                if (this.type != 0) {
                    if (bean.getPayStatus() != 0) {
                        holdView.setFunction(0);
                        break;
                    } else {
                        statusStr = "待支付";
                        holdView.setFunction(0, 8);
                        break;
                    }
                } else {
                    holdView.setFunction(9, 10);
                    break;
                }
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
                    holdView.setFunction(2, 7);
                    break;
                } else {
                    holdView.setFunction(6);
                    break;
                }
            case 21:
                holdView.setStateColor(parent, R.color.demand_super);
                statusStr = "已退款";
                holdView.setFunction(7);
                break;
            case 22:
                statusStr = "拒绝退款";
                holdView.setStateColor(parent, R.color.demand_super);
                if (this.type != 0) {
                    holdView.setFunction(2, 6);
                    break;
                } else {
                    holdView.setFunction(7);
                    break;
                }
            case 30:
                statusStr = "待评价";
                holdView.setStateColor(parent, R.color.demand_order_gold);
                holdView.setFunction(3);
                break;
            case 31:
                if (this.type != 1) {
                    statusStr = "需求方已评价";
                    holdView.setStateColor(parent, R.color.demand_order_gold);
                    holdView.setFunction(3);
                    break;
                } else {
                    statusStr = "已评价";
                    holdView.setStateColor(parent, R.color.demand_super);
                    holdView.setFunction(new int[0]);
                    break;
                }
            case 32:
                if (this.type != 1) {
                    statusStr = "已评价";
                    holdView.setStateColor(parent, R.color.demand_super);
                    holdView.setFunction(new int[0]);
                    break;
                } else {
                    statusStr = "服务商已评价";
                    holdView.setStateColor(parent, R.color.demand_order_gold);
                    holdView.setFunction(3);
                    break;
                }
            case 33:
                statusStr = "已评价";
                holdView.setStateColor(parent, R.color.demand_super);
                holdView.setFunction(new int[0]);
                break;
        }
        holdView.tv_status.setText(statusStr);
        return convertView;
    }
}
