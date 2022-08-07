package com.hy.frame.adapter;

import android.content.Context;
import android.view.View;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class MyBaseAdapter<T> extends BaseAdapter<T> {
    private List<T> datas;

    public MyBaseAdapter(Context context, List<T> datas) {
        super(context);
        this.datas = datas;
    }

    public void setList(List<T> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
    }

    public void addList(List<T> datas) {
        this.datas.addAll(datas);
    }

    public void refresh(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.datas == null) {
            return 0;
        }
        return this.datas.size();
    }

    @Override // android.widget.Adapter
    public T getItem(int position) {
        if (this.datas == null) {
            return null;
        }
        return this.datas.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public void setOnClickListener(View v, int position) {
        if (getListener() != null) {
            v.setOnClickListener(new ViewOnClick(getListener(), getItem(position), position));
        }
    }
}
