package com.hy.frame.adapter;

import android.content.Context;

/* loaded from: classes2.dex */
public abstract class MyArrayAdapter<T> extends BaseAdapter {
    private T[] datas;

    public MyArrayAdapter(Context context, T[] datas) {
        super(context);
        this.datas = datas;
    }

    public void setList(T[] datas) {
        this.datas = datas;
    }

    public void refresh(T[] datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.datas == null) {
            return 0;
        }
        return this.datas.length;
    }

    @Override // android.widget.Adapter
    public T getItem(int position) {
        return this.datas[position];
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }
}
