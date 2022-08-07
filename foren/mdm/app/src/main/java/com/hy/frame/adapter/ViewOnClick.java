package com.hy.frame.adapter;

import android.view.View;

/* loaded from: classes2.dex */
public class ViewOnClick implements View.OnClickListener {
    IAdapterListener listener;
    Object obj;
    int position;

    public ViewOnClick(IAdapterListener listener, Object obj, int position) {
        this.obj = obj;
        this.position = position;
        this.listener = listener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (this.listener != null) {
            this.listener.onItemClick(v.getId(), this.obj, this.position);
        }
    }
}
