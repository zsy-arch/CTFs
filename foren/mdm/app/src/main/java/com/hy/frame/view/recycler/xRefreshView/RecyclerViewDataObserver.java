package com.hy.frame.view.recycler.xRefreshView;

import android.support.v7.widget.RecyclerView;

/* loaded from: classes2.dex */
public class RecyclerViewDataObserver extends RecyclerView.AdapterDataObserver {
    private boolean hasData = true;
    private RecyclerViewHolder mAdapter;
    private boolean mAttached;
    private XRefreshView xRefreshView;

    public void setData(RecyclerViewHolder adapter, XRefreshView xRefreshView) {
        this.mAdapter = adapter;
        this.xRefreshView = xRefreshView;
    }

    private void enableEmptyView(boolean enable) {
        if (this.xRefreshView != null) {
            this.xRefreshView.enableEmptyView(enable);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
    public void onChanged() {
        if (this.mAdapter != null) {
            if (this.mAdapter.isEmpty()) {
                if (this.hasData) {
                    enableEmptyView(true);
                    this.hasData = false;
                }
            } else if (!this.hasData) {
                enableEmptyView(false);
                this.hasData = true;
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        onChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeChanged(int positionStart, int itemCount) {
        onChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeInserted(int positionStart, int itemCount) {
        onChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        onChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        onChanged();
    }

    public void attach() {
        this.mAttached = true;
    }

    public boolean hasAttached() {
        return this.mAttached;
    }
}
