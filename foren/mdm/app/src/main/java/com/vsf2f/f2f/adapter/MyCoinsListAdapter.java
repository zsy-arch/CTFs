package com.vsf2f.f2f.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GoldInfoBean;
import java.util.List;

/* loaded from: classes2.dex */
public class MyCoinsListAdapter extends BaseAdapter {
    private List<GoldInfoBean.RecordsBean> data;

    public MyCoinsListAdapter(List<GoldInfoBean.RecordsBean> data) {
        this.data = data;
    }

    public List<GoldInfoBean.RecordsBean> getData() {
        return this.data;
    }

    public void setData(List<GoldInfoBean.RecordsBean> data) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        String num;
        GoldInfoBean.RecordsBean bean = (GoldInfoBean.RecordsBean) getItem(position);
        if (convertView == null) {
            holdView = new HoldView();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coins_info, parent, false);
            holdView.tv_title = (TextView) convertView.findViewById(R.id.item_coins_title);
            holdView.tv_time = (TextView) convertView.findViewById(R.id.item_coins_time);
            holdView.tv_num = (TextView) convertView.findViewById(R.id.item_coins_num);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.tv_title.setText(bean.getRemarks());
        holdView.tv_time.setText(bean.getCreateTimeStr());
        String num2 = bean.getNum();
        if (num2.startsWith("-")) {
            num = num2.replace("-", "- ");
        } else {
            num = "+ " + num2;
        }
        holdView.tv_num.setText(num);
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        TextView tv_num;
        TextView tv_time;
        TextView tv_title;

        HoldView() {
        }
    }
}
