package com.mob.tools.gui;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;

/* loaded from: classes2.dex */
public abstract class PullToRequestGridAdapter extends PullToRequestBaseListAdapter {
    private boolean fling;
    private OnListStopScrollListener osListener;
    private boolean pullUpReady;
    private ScrollableGridView gridView = onNewGridView(getContext());
    private PullToRequestBaseAdapter adapter = new PullToRequestBaseAdapter(this);

    public PullToRequestGridAdapter(PullToRequestView view) {
        super(view);
        this.gridView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.mob.tools.gui.PullToRequestGridAdapter.1
            private int firstVisibleItem;
            private int visibleItemCount;

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView view2, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.firstVisibleItem = firstVisibleItem;
                this.visibleItemCount = visibleItemCount;
                View v = view2.getChildAt(visibleItemCount - 1);
                PullToRequestGridAdapter.this.pullUpReady = firstVisibleItem + visibleItemCount == totalItemCount && v != null && v.getBottom() <= view2.getBottom();
                PullToRequestGridAdapter.this.onScroll(PullToRequestGridAdapter.this.gridView, firstVisibleItem, visibleItemCount, totalItemCount);
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView view2, int scrollState) {
                PullToRequestGridAdapter.this.fling = scrollState == 2;
                if (scrollState != 0) {
                    return;
                }
                if (PullToRequestGridAdapter.this.osListener != null) {
                    PullToRequestGridAdapter.this.osListener.onListStopScrolling(this.firstVisibleItem, this.visibleItemCount);
                } else if (PullToRequestGridAdapter.this.adapter != null) {
                    PullToRequestGridAdapter.this.adapter.notifyDataSetChanged();
                }
            }
        });
        this.gridView.setAdapter((ListAdapter) this.adapter);
    }

    @Override // com.mob.tools.gui.PullToRequestAdatper
    public Scrollable getBodyView() {
        return this.gridView;
    }

    public GridView getGridView() {
        return this.gridView;
    }

    @Override // com.mob.tools.gui.PullToRequestBaseListAdapter
    public boolean isFling() {
        return this.fling;
    }

    @Override // com.mob.tools.gui.PullToRequestAdatper
    public boolean isPullDownReady() {
        return this.gridView.isReadyToPull();
    }

    @Override // com.mob.tools.gui.PullToRequestAdatper
    public boolean isPullUpReady() {
        return this.pullUpReady;
    }

    @Override // com.mob.tools.gui.PullToRequestAdatper
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.adapter.notifyDataSetChanged();
    }

    protected ScrollableGridView onNewGridView(Context context) {
        return new ScrollableGridView(context);
    }

    @Override // com.mob.tools.gui.PullToRequestBaseListAdapter
    public void onScroll(Scrollable scrollable, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    public void setColumnWidth(int columnWidth) {
        this.gridView.setColumnWidth(columnWidth);
    }

    public void setHorizontalSpacing(int horizontalSpacing) {
        this.gridView.setHorizontalSpacing(horizontalSpacing);
    }

    public void setNumColumns(int numColumns) {
        this.gridView.setNumColumns(numColumns);
    }

    public void setStretchMode(int stretchMode) {
        this.gridView.setStretchMode(stretchMode);
    }

    public void setVerticalSpacing(int verticalSpacing) {
        this.gridView.setVerticalSpacing(verticalSpacing);
    }
}
