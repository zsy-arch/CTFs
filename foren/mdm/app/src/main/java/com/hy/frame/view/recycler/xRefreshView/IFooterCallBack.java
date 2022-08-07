package com.hy.frame.view.recycler.xRefreshView;

/* loaded from: classes2.dex */
public interface IFooterCallBack {
    void callWhenNotAutoLoadMore(XRefreshView xRefreshView);

    int getFooterHeight();

    boolean isShowing();

    void onReleaseToLoadMore();

    void onStateComplete();

    void onStateFinish(boolean z);

    void onStateReady();

    void onStateRefreshing();

    void show(boolean z);
}
