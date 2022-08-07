package com.mob.tools.gui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/* loaded from: classes2.dex */
public abstract class PullToRequestListAdapter extends PullToRequestBaseListAdapter {
    private boolean fling;
    private OnListStopScrollListener osListener;
    private boolean pullUpReady;
    private ScrollableListView listView = onNewListView(getContext());
    private PullToRequestBaseAdapter adapter = new PullToRequestBaseAdapter(this);

    public PullToRequestListAdapter(PullToRequestView view) {
        super(view);
        this.listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.mob.tools.gui.PullToRequestListAdapter.1
            private int firstVisibleItem;
            private int visibleItemCount;

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView view2, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.firstVisibleItem = firstVisibleItem;
                this.visibleItemCount = visibleItemCount;
                View v = view2.getChildAt(visibleItemCount - 1);
                PullToRequestListAdapter.this.pullUpReady = firstVisibleItem + visibleItemCount == totalItemCount && v != null && v.getBottom() <= view2.getBottom();
                PullToRequestListAdapter.this.onScroll(PullToRequestListAdapter.this.listView, firstVisibleItem, visibleItemCount, totalItemCount);
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView view2, int scrollState) {
                PullToRequestListAdapter.this.fling = scrollState == 2;
                if (scrollState != 0) {
                    return;
                }
                if (PullToRequestListAdapter.this.osListener != null) {
                    PullToRequestListAdapter.this.osListener.onListStopScrolling(this.firstVisibleItem, this.visibleItemCount);
                } else if (PullToRequestListAdapter.this.adapter != null) {
                    PullToRequestListAdapter.this.adapter.notifyDataSetChanged();
                }
            }
        });
        this.listView.setAdapter((ListAdapter) this.adapter);
    }

    @Override // com.mob.tools.gui.PullToRequestAdatper
    public Scrollable getBodyView() {
        return this.listView;
    }

    public ListView getListView() {
        return this.listView;
    }

    @Override // com.mob.tools.gui.PullToRequestBaseListAdapter
    public boolean isFling() {
        return this.fling;
    }

    @Override // com.mob.tools.gui.PullToRequestAdatper
    public boolean isPullDownReady() {
        return this.listView.isReadyToPull();
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

    protected ScrollableListView onNewListView(Context context) {
        return new ScrollableListView(context);
    }

    @Override // com.mob.tools.gui.PullToRequestBaseListAdapter
    public void onScroll(Scrollable scrollable, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    public void setDivider(Drawable divider) {
        this.listView.setDivider(divider);
    }

    public void setDividerHeight(int height) {
        this.listView.setDividerHeight(height);
    }
}
