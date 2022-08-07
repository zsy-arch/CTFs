package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.utils.TimeUtil;
import com.em.DemoHelper;
import com.hy.frame.adapter.MyBaseAdapter;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.packet.RedPacketDetailBean;
import java.util.List;

/* loaded from: classes2.dex */
public class RedPacketDetailAdapter extends MyBaseAdapter<RedPacketDetailBean.DetailListBean> {
    private getPacketListener listener;
    private boolean showLucky;

    /* loaded from: classes2.dex */
    public interface getPacketListener {
        void getPacket(String str);
    }

    public RedPacketDetailAdapter(Context context, List<RedPacketDetailBean.DetailListBean> datas, getPacketListener listener) {
        super(context, datas);
        this.listener = listener;
    }

    public void showLucky(boolean showLucky) {
        this.showLucky = showLucky;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View v, ViewGroup group) {
        if (v == null) {
            v = inflate(R.layout.item_detail_redpacket);
            new ItemHolder(v);
        }
        ItemHolder h = (ItemHolder) v.getTag();
        RedPacketDetailBean.DetailListBean item = getItem(position);
        h.name.setText(item.getReceiverNick());
        h.time.setText(TimeUtil.getDateTime(item.getReceiveTime()));
        h.money.setText(String.format("%s 元", HyUtil.formatToMoney(Double.valueOf(item.getAmount()))));
        Glide.with(getContext()).load(item.getReceiverObj().getPicUrl()).error((int) R.mipmap.def_head2).into(h.avatar);
        if (this.showLucky) {
            if (item.getLuckiestFlag() == 1) {
                h.lucky.setVisibility(0);
            } else {
                h.lucky.setVisibility(4);
            }
        }
        if (DemoHelper.getInstance().getCurrentUserName().equals(item.getReceiver())) {
            this.listener.getPacket(HyUtil.formatToMoney(Double.valueOf(item.getAmount())));
        }
        return v;
    }

    /* loaded from: classes2.dex */
    class ItemHolder {
        private ImageView avatar;
        private TextView lucky;
        private TextView money;
        private TextView name;
        private TextView time;

        public ItemHolder(View v) {
            v.setTag(this);
            this.avatar = (ImageView) RedPacketDetailAdapter.this.getView(v, R.id.avatar);
            this.lucky = (TextView) RedPacketDetailAdapter.this.getView(v, R.id.lucky);
            this.money = (TextView) RedPacketDetailAdapter.this.getView(v, R.id.money);
            this.name = (TextView) RedPacketDetailAdapter.this.getView(v, R.id.name);
            this.time = (TextView) RedPacketDetailAdapter.this.getView(v, R.id.time);
        }
    }
}
