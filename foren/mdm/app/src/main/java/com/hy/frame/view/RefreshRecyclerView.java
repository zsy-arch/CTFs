package com.hy.frame.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.hy.frame.R;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.DividerItemDecoration;
import com.hy.frame.view.recycler.xRefreshView.XRefreshView;

/* loaded from: classes2.dex */
public class RefreshRecyclerView extends LinearLayout {
    private RecyclerView recyclerView;
    private XRefreshView xrv_pull;

    public RefreshRecyclerView(Context context) {
        super(context);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_refresh_recycler, this);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.xrv_pull = (XRefreshView) findViewById(R.id.xrv_pull);
        this.xrv_pull.setPullRefreshEnable(true);
        this.xrv_pull.setPullLoadEnable(true);
        this.xrv_pull.setAutoRefresh(false);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (this.recyclerView != null) {
            this.recyclerView.setAdapter(adapter);
        }
    }

    public void setHorizontal() {
        if (this.recyclerView != null) {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        }
    }

    public void addItemDecoration() {
        if (this.recyclerView != null) {
            this.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        }
    }

    public void setRefreshEnabled(boolean b) {
        if (this.xrv_pull != null) {
            this.xrv_pull.setPullRefreshEnable(b);
        }
    }

    public void setLoadMoreEnabled(boolean b) {
        if (this.xrv_pull != null) {
            this.xrv_pull.setPullLoadEnable(b);
        }
    }

    public void setOnRefreshListener(XRefreshViewListener listener) {
        this.xrv_pull.setXRefreshViewListener(listener);
    }

    public void setRefreshComplete() {
        if (this.xrv_pull != null) {
            this.xrv_pull.stopRefresh();
        }
    }

    public void setRefreshFail() {
        if (this.xrv_pull != null) {
            this.xrv_pull.stopRefresh(false);
        }
    }

    public void setLoadMoreComplete() {
        if (this.xrv_pull != null) {
            this.xrv_pull.stopLoadMore();
        }
    }

    public void setLoadMoreComplete(boolean hasMore) {
        if (this.xrv_pull == null) {
            return;
        }
        if (hasMore) {
            this.xrv_pull.stopLoadMore();
        } else {
            this.xrv_pull.setLoadComplete(true);
        }
    }
}
