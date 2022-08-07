package com.hy.frame.adapter;

import android.view.View;

/* loaded from: classes2.dex */
public class ViewOnLongClick implements View.OnLongClickListener {
    public static final int LONG_CLICK = 100;
    IAdapterListener listener;
    Object obj;
    int position;

    public ViewOnLongClick(IAdapterListener listener, Object obj, int position) {
        this.obj = obj;
        this.position = position;
        this.listener = listener;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        if (this.listener == null) {
            return false;
        }
        this.listener.onItemClick(100, this.obj, this.position);
        return false;
    }
}
