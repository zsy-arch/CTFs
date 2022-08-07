package com.hy.frame.view.recycler.xRefreshView;

import android.support.v7.widget.GridLayoutManager;

/* loaded from: classes2.dex */
public class XSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private RecyclerViewHolder adapter;
    private int mSpanSize;

    public XSpanSizeLookup(RecyclerViewHolder adapter, int spanSize) {
        this.mSpanSize = 1;
        this.adapter = adapter;
        this.mSpanSize = spanSize;
    }

    @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
    public int getSpanSize(int position) {
        if (this.adapter.isFooter(position) || this.adapter.isHeader(position)) {
            return this.mSpanSize;
        }
        return 1;
    }
}
