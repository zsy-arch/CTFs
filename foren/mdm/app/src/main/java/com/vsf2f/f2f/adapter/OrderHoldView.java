package com.vsf2f.f2f.adapter;

import android.support.annotation.ColorRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class OrderHoldView {
    List<TextView> function = new ArrayList();
    ImageView iv_icon;
    LinearLayout ll_order_item;
    ImageView riv_revers;
    TextView tv_accept;
    TextView tv_amount;
    TextView tv_cancel;
    TextView tv_comment;
    TextView tv_content;
    TextView tv_count;
    TextView tv_deal;
    TextView tv_doservice;
    TextView tv_order_no;
    TextView tv_pay;
    TextView tv_price;
    TextView tv_publisher;
    TextView tv_refund;
    TextView tv_reject;
    TextView tv_return;
    TextView tv_serviced;
    TextView tv_status;
    TextView tv_sure;
    TextView tv_time;
    TextView tv_title;
    TextView tv_unit;

    public OrderHoldView(View convertView) {
        this.ll_order_item = (LinearLayout) convertView.findViewById(R.id.ll_order_item);
        this.riv_revers = (ImageView) convertView.findViewById(R.id.riv_revers);
        this.tv_publisher = (TextView) convertView.findViewById(R.id.tv_publisher);
        this.tv_doservice = (TextView) convertView.findViewById(R.id.tv_doservice);
        this.tv_order_no = (TextView) convertView.findViewById(R.id.tv_order_no);
        this.tv_serviced = (TextView) convertView.findViewById(R.id.tv_serviced);
        this.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
        this.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);
        this.tv_cancel = (TextView) convertView.findViewById(R.id.tv_cancel);
        this.tv_return = (TextView) convertView.findViewById(R.id.tv_return);
        this.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
        this.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        this.tv_sure = (TextView) convertView.findViewById(R.id.tv_sure);
        this.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        this.tv_deal = (TextView) convertView.findViewById(R.id.tv_deal);
        this.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
        this.tv_count = (TextView) convertView.findViewById(R.id.tv_count);
        this.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
        this.tv_refund = (TextView) convertView.findViewById(R.id.tv_refund);
        this.tv_reject = (TextView) convertView.findViewById(R.id.tv_reject);
        this.tv_accept = (TextView) convertView.findViewById(R.id.tv_accept);
        this.tv_pay = (TextView) convertView.findViewById(R.id.tv_pay);
        this.tv_amount = (TextView) convertView.findViewById(R.id.tv_amount);
        this.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
        this.function.clear();
        this.function.add(this.tv_cancel);
        this.function.add(this.tv_return);
        this.function.add(this.tv_sure);
        this.function.add(this.tv_comment);
        this.function.add(this.tv_doservice);
        this.function.add(this.tv_serviced);
        this.function.add(this.tv_deal);
        this.function.add(this.tv_refund);
        this.function.add(this.tv_pay);
        this.function.add(this.tv_reject);
        this.function.add(this.tv_accept);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFunction(int... indexs) {
        hideFunction();
        if (indexs != null) {
            for (int index : indexs) {
                if (index < this.function.size()) {
                    this.function.get(index).setVisibility(0);
                }
            }
        }
    }

    void hideFunction() {
        this.tv_cancel.setVisibility(8);
        this.tv_return.setVisibility(8);
        this.tv_sure.setVisibility(8);
        this.tv_comment.setVisibility(8);
        this.tv_serviced.setVisibility(8);
        this.tv_doservice.setVisibility(8);
        this.tv_deal.setVisibility(8);
        this.tv_refund.setVisibility(8);
        this.tv_reject.setVisibility(8);
        this.tv_accept.setVisibility(8);
        this.tv_pay.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStateColor(ViewGroup parent, @ColorRes int rid) {
        this.tv_status.setTextColor(parent.getResources().getColor(rid));
    }
}
