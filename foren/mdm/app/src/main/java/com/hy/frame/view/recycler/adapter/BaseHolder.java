package com.hy.frame.view.recycler.adapter;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class BaseHolder extends RecyclerView.ViewHolder {
    private View convertView;
    private SparseArray<View> views = new SparseArray<>();

    public BaseHolder(View itemView) {
        super(itemView);
        this.convertView = itemView;
    }

    public <T extends View> T getView(int viewId) {
        T t = (T) this.views.get(viewId);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.convertView.findViewById(viewId);
        this.views.put(viewId, t2);
        return t2;
    }

    public BaseHolder setText(int viewId, @StringRes int resid) {
        ((TextView) getView(viewId)).setText(resid);
        return this;
    }

    public BaseHolder setText(int viewId, String text) {
        ((TextView) getView(viewId)).setText(text);
        return this;
    }

    public String getText(int viewId) {
        return ((TextView) getView(viewId)).getText().toString();
    }

    public BaseHolder setTextColor(int viewId, int color) {
        ((TextView) getView(viewId)).setTextColor(color);
        return this;
    }

    public BaseHolder setImageResource(int viewId, int resId) {
        ((ImageView) getView(viewId)).setImageResource(resId);
        return this;
    }

    public BaseHolder setViewVisiable(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    public BaseHolder setViewSelected(int viewId, boolean selected) {
        getView(viewId).setSelected(selected);
        return this;
    }

    public BaseHolder setViewBackgroundResource(int viewId, int resId) {
        getView(viewId).setBackgroundResource(resId);
        return this;
    }

    public BaseHolder setOnclickListener(int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
        return this;
    }
}
