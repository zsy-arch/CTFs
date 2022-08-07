package com.vsf2f.f2f.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class ReportAdapter extends BaseAdapter {
    private String[] datas;
    private LayoutInflater mInflater;

    public ReportAdapter(String[] datas) {
        this.datas = datas;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.datas.length;
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.datas[position];
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        if (convertView == null) {
            holdView = new HoldView();
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_report, parent, false);
            holdView.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        holdView.tv_title.setText(this.datas[position]);
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        TextView tv_title;

        HoldView() {
        }
    }
}
