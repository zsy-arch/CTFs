package com.hy.frame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes2.dex */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private Context context;
    private IAdapterListener listener;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    protected void setContext(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return this.context;
    }

    protected IAdapterListener getListener() {
        return this.listener;
    }

    public void setListener(IAdapterListener listener) {
        this.listener = listener;
    }

    protected View inflate(int resId) {
        return LayoutInflater.from(this.context).inflate(resId, (ViewGroup) null);
    }

    public void setOnClickListener(View v, T t, int position) {
        if (getListener() != null) {
            v.setOnClickListener(new ViewOnClick(getListener(), t, position));
        }
    }

    public void setOnLongClickListener(View v, T t, int position) {
        if (getListener() != null) {
            v.setOnLongClickListener(new ViewOnLongClick(getListener(), t, position));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <V extends View> V getView(View v, int resId) {
        return (V) v.findViewById(resId);
    }
}
