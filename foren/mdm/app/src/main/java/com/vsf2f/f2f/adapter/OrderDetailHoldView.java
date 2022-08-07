package com.vsf2f.f2f.adapter;

import android.view.View;
import android.widget.TextView;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class OrderDetailHoldView {
    List<TextView> function = new ArrayList();
    View ll_operation;
    TextView tv_accept;
    TextView tv_cancel;
    TextView tv_comment;
    TextView tv_deal;
    TextView tv_doservice;
    TextView tv_pay;
    TextView tv_refund;
    TextView tv_reject;
    TextView tv_return;
    TextView tv_serviced;
    TextView tv_sure;

    public OrderDetailHoldView(View convertView, View.OnClickListener listener) {
        this.ll_operation = convertView;
        this.tv_doservice = (TextView) convertView.findViewById(R.id.tv_doservice);
        this.tv_serviced = (TextView) convertView.findViewById(R.id.tv_serviced);
        this.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);
        this.tv_cancel = (TextView) convertView.findViewById(R.id.tv_cancel);
        this.tv_return = (TextView) convertView.findViewById(R.id.tv_return);
        this.tv_sure = (TextView) convertView.findViewById(R.id.tv_sure);
        this.tv_deal = (TextView) convertView.findViewById(R.id.tv_deal);
        this.tv_refund = (TextView) convertView.findViewById(R.id.tv_refund);
        this.tv_reject = (TextView) convertView.findViewById(R.id.tv_reject);
        this.tv_accept = (TextView) convertView.findViewById(R.id.tv_accept);
        this.tv_pay = (TextView) convertView.findViewById(R.id.tv_pay);
        this.tv_doservice.setOnClickListener(listener);
        this.tv_serviced.setOnClickListener(listener);
        this.tv_comment.setOnClickListener(listener);
        this.tv_cancel.setOnClickListener(listener);
        this.tv_return.setOnClickListener(listener);
        this.tv_sure.setOnClickListener(listener);
        this.tv_deal.setOnClickListener(listener);
        this.tv_refund.setOnClickListener(listener);
        this.tv_reject.setOnClickListener(listener);
        this.tv_accept.setOnClickListener(listener);
        this.tv_pay.setOnClickListener(listener);
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

    public void setFunction(int... indexs) {
        hideFunction();
        if (!(indexs == null || indexs.length == 0)) {
            this.ll_operation.setVisibility(0);
            for (int index : indexs) {
                if (index < this.function.size()) {
                    this.function.get(index).setVisibility(0);
                }
            }
        }
    }

    public void hideFunction() {
        this.tv_cancel.setVisibility(8);
        this.tv_return.setVisibility(8);
        this.tv_sure.setVisibility(8);
        this.tv_comment.setVisibility(8);
        this.tv_doservice.setVisibility(8);
        this.tv_serviced.setVisibility(8);
        this.tv_deal.setVisibility(8);
        this.tv_refund.setVisibility(8);
        this.tv_reject.setVisibility(8);
        this.tv_accept.setVisibility(8);
        this.tv_pay.setVisibility(8);
    }
}
