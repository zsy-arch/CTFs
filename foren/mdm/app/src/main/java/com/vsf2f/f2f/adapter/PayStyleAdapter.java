package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.adapter.MyBaseAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.PayStyleBean;
import java.util.List;

/* loaded from: classes2.dex */
public class PayStyleAdapter extends MyBaseAdapter<PayStyleBean> {
    private int clickStatus = -1;

    public void setSeclection(int position) {
        this.clickStatus = position;
        notifyDataSetChanged();
    }

    public PayStyleAdapter(Context context, List<PayStyleBean> datas) {
        super(context, datas);
    }

    @Override // com.hy.frame.adapter.MyBaseAdapter, android.widget.Adapter
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override // android.widget.Adapter
    public View getView(int position, View v, ViewGroup group) {
        if (v == null) {
            v = inflate(R.layout.item_pay_style);
            new ViewCache(v);
        }
        ViewCache h = (ViewCache) v.getTag();
        PayStyleBean item = getItem(position);
        if (item.getType() == 0) {
            h.imgavrta.setImageResource(R.mipmap.ico_login_alipay);
            h.payname.setText("支付宝支付");
            h.payAccount.setText("支付宝账号");
        } else if (item.getType() == 1) {
            h.imgavrta.setImageResource(R.mipmap.ico_login_wechat);
            h.payname.setText("微信支付");
            h.payAccount.setText("微信账号");
        }
        if (item.getBankNumber() == null) {
        }
        h.txtAccount.setText(item.getBankNumber());
        if (this.clickStatus == position) {
            h.check.setImageResource(R.drawable.icon_radio_red);
        } else {
            h.check.setImageResource(R.drawable.icon_radio_empty);
        }
        setOnClickListener(h.lly_withdraw, position);
        return v;
    }

    /* loaded from: classes2.dex */
    class ViewCache {
        private ImageView check;
        private ImageView imgavrta;
        private LinearLayout lly_withdraw;
        private TextView payAccount;
        private TextView payname;
        private TextView txtAccount;

        public ViewCache(View v) {
            v.setTag(this);
            this.lly_withdraw = (LinearLayout) PayStyleAdapter.this.getView(v, R.id.withdrawal_llyWx);
            this.imgavrta = (ImageView) PayStyleAdapter.this.getView(v, R.id.img_payivarta);
            this.payname = (TextView) PayStyleAdapter.this.getView(v, R.id.txt_payname);
            this.payAccount = (TextView) PayStyleAdapter.this.getView(v, R.id.txt_payaccount);
            this.txtAccount = (TextView) PayStyleAdapter.this.getView(v, R.id.withdrawal_txtAccount);
            this.check = (ImageView) PayStyleAdapter.this.getView(v, R.id.withdrawal_cbtnW);
        }
    }
}
