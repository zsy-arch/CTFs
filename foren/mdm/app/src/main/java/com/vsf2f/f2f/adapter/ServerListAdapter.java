package com.vsf2f.f2f.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hy.frame.view.RotundityImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.OfferList;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ServerListAdapter extends BaseAdapter {
    private AdapterClickListener adapterClickListener;
    private List<OfferList> data;
    private LayoutInflater mInflater;
    private String price;

    /* loaded from: classes2.dex */
    public interface AdapterClickListener {
        void clickListener(int i, int i2);
    }

    public ServerListAdapter(List<OfferList> data, String price, AdapterClickListener adapterClickListener) {
        this.price = "";
        this.adapterClickListener = adapterClickListener;
        this.data = data;
        this.price = price;
        if (this.data == null) {
            this.data = new ArrayList();
        }
    }

    public void setData(List<OfferList> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        OfferList bean = this.data.get(position);
        if (convertView == null) {
            holdView = new HoldView();
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_server, parent, false);
            holdView.riv_user = (RotundityImageView) convertView.findViewById(R.id.riv_user);
            holdView.stateView = (IdentyStateView) convertView.findViewById(R.id.identyStateView);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holdView.tv_ignore = (TextView) convertView.findViewById(R.id.tv_ignore);
            holdView.tv_agree = (TextView) convertView.findViewById(R.id.tv_agree);
            holdView.tv_zhima = (TextView) convertView.findViewById(R.id.tv_zhima);
            holdView.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holdView.tv_user = (TextView) convertView.findViewById(R.id.tv_user);
            holdView.tv_chat = (TextView) convertView.findViewById(R.id.tv_chat);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        OfferList.ServiceUserObjBean userObjBean = bean.getServiceUserObj();
        holdView.tv_zhima.setText(userObjBean.getZmScore() + "");
        holdView.stateView.setStatus(userObjBean.getCertMobile(), userObjBean.getCertRealname(), userObjBean.getCertZhima(), userObjBean.getCertAlipay(), userObjBean.getCertWechat(), userObjBean.getCertQq());
        Glide.with(parent.getContext()).load(userObjBean.getUserPic().getPath()).error((int) R.mipmap.def_head).into(holdView.riv_user);
        holdView.tv_price.setText("￥ " + this.price + "元");
        holdView.tv_user.setText(userObjBean.getNickName() + "");
        if (bean.getStatus() == 2) {
            holdView.tv_agree.setSelected(true);
            holdView.tv_ignore.setSelected(true);
            holdView.tv_ignore.setEnabled(false);
            holdView.tv_agree.setEnabled(false);
            holdView.tv_ignore.setText("已忽略");
        } else if (bean.getStatus() == 0) {
            holdView.tv_agree.setSelected(false);
            holdView.tv_ignore.setSelected(false);
            holdView.tv_agree.setEnabled(true);
            holdView.tv_ignore.setEnabled(true);
            holdView.tv_ignore.setText("忽略");
        } else if (bean.getStatus() == 1) {
            holdView.tv_agree.setSelected(true);
            holdView.tv_ignore.setSelected(true);
            holdView.tv_ignore.setEnabled(false);
            holdView.tv_agree.setEnabled(false);
            holdView.tv_ignore.setText("已作废");
        }
        holdView.tv_content.setText(bean.getMsg());
        holdView.tv_price.setVisibility(0);
        holdView.tv_ignore.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServerListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServerListAdapter.this.adapterClickListener.clickListener(0, position);
            }
        });
        holdView.tv_agree.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServerListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServerListAdapter.this.adapterClickListener.clickListener(1, position);
            }
        });
        holdView.tv_chat.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.ServerListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ServerListAdapter.this.adapterClickListener.clickListener(2, position);
            }
        });
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        RotundityImageView riv_user;
        IdentyStateView stateView;
        TextView tv_agree;
        TextView tv_chat;
        TextView tv_content;
        TextView tv_ignore;
        TextView tv_price;
        TextView tv_user;
        TextView tv_zhima;

        HoldView() {
        }
    }
}
