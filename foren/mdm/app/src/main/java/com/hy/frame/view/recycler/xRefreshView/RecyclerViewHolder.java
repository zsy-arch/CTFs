package com.hy.frame.view.recycler.xRefreshView;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.alimama.mobile.csdk.umupdate.a.f;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class RecyclerViewHolder<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private XRefreshView mParent;
    protected View customLoadMoreView = null;
    protected View customHeaderView = null;
    private boolean isFooterEnable = true;
    private boolean removeFooter = false;
    private final RecyclerViewDataObserver observer = new RecyclerViewDataObserver();

    public abstract int getAdapterItemCount();

    public abstract VH getViewHolder(View view);

    public abstract void onBindViewHolder(VH vh, int i, boolean z);

    public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i, boolean z);

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        showFooter(this.customLoadMoreView, false);
        if (viewType == -1) {
            Utils.removeViewFromParent(this.customLoadMoreView);
            return getViewHolder(this.customLoadMoreView);
        } else if (viewType != -3) {
            return onCreateViewHolder(parent, viewType, true);
        } else {
            Utils.removeViewFromParent(this.customHeaderView);
            return getViewHolder(this.customHeaderView);
        }
    }

    private void showFooter(View footerview, boolean show) {
        if (this.isFooterEnable && footerview != null && (footerview instanceof IFooterCallBack)) {
            IFooterCallBack footerCallBack = (IFooterCallBack) footerview;
            if (show) {
                if (!footerCallBack.isShowing()) {
                    footerCallBack.show(show);
                }
            } else if (getAdapterItemCount() == 0 && footerCallBack.isShowing()) {
                footerCallBack.show(false);
            } else if (getAdapterItemCount() != 0 && !footerCallBack.isShowing()) {
                footerCallBack.show(true);
            }
        }
    }

    public void addFooterView() {
        LogUtils.d("test addFooterView");
        if (this.removeFooter) {
            notifyItemInserted(getItemCount());
            this.removeFooter = false;
            showFooter(this.customLoadMoreView, true);
        }
    }

    public boolean isFooterShowing() {
        return !this.removeFooter;
    }

    public void removeFooterView() {
        LogUtils.d("test removeFooterView");
        if (!this.removeFooter) {
            notifyItemRemoved(getItemCount() - 1);
            this.removeFooter = true;
        }
    }

    public boolean isEmpty() {
        return getAdapterItemCount() == 0;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(VH holder, int position) {
        int start = getStart();
        if (!isHeader(position) && !isFooter(position)) {
            onBindViewHolder((RecyclerViewHolder<VH>) holder, position - start, true);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && (lp instanceof StaggeredGridLayoutManager.LayoutParams)) {
            ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(isFooter(position) || isHeader(position));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        ViewParent parent = recyclerView.getParent();
        if (parent != null && (parent instanceof XRefreshView)) {
            this.mParent = (XRefreshView) recyclerView.getParent();
            if (this.mParent != null && !this.observer.hasAttached()) {
                this.observer.setData(this, this.mParent);
                this.observer.attach();
                registerAdapterDataObserver(this.observer);
            }
        }
    }

    public void setCustomLoadMoreView(View footerView) {
        if (footerView instanceof IFooterCallBack) {
            this.customLoadMoreView = footerView;
            Utils.removeViewFromParent(this.customLoadMoreView);
            if (!(this.mParent == null || this.mParent.getContentView() == null)) {
                this.mParent.getContentView().initFooterCallBack(this, this.mParent);
            }
            showFooter(this.customLoadMoreView, false);
            notifyDataSetChanged();
            return;
        }
        throw new RuntimeException("footerView must be implementes IFooterCallBack!");
    }

    public void setHeaderView(View headerView, RecyclerView recyclerView) {
        if (recyclerView != null) {
            Utils.removeViewFromParent(headerView);
            this.customHeaderView = headerView;
            notifyDataSetChanged();
        }
    }

    public View setHeaderView(@LayoutRes int id, RecyclerView recyclerView) {
        if (recyclerView == null) {
            return null;
        }
        Context context = recyclerView.getContext();
        if (!context.getResources().getResourceTypeName(id).contains(f.bt)) {
            throw new RuntimeException(context.getResources().getResourceName(id) + " is a illegal layoutid , please check your layout id first !");
        }
        this.customHeaderView = LayoutInflater.from(context).inflate(id, (ViewGroup) new FrameLayout(recyclerView.getContext()), false);
        notifyDataSetChanged();
        return this.customHeaderView;
    }

    public boolean isFooter(int position) {
        return this.customLoadMoreView != null && position >= getAdapterItemCount() + getStart();
    }

    public boolean isHeader(int position) {
        return getStart() > 0 && position == 0;
    }

    public View getCustomLoadMoreView() {
        return this.customLoadMoreView;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemViewType(int position) {
        if (isHeader(position)) {
            return -3;
        }
        if (isFooter(position)) {
            return -1;
        }
        if (getStart() > 0) {
            position--;
        }
        return getAdapterItemViewType(position);
    }

    public int getAdapterItemViewType(int position) {
        return -4;
    }

    public int getStart() {
        return this.customHeaderView == null ? 0 : 1;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        int count = getAdapterItemCount() + getStart();
        if (this.customLoadMoreView == null || this.removeFooter) {
            return count;
        }
        return count + 1;
    }

    public void swapPositions(List<?> list, int from, int to) {
        Collections.swap(list, from, to);
    }

    public void insideEnableFooter(boolean enable) {
        this.isFooterEnable = enable;
    }

    public <T> void insert(List<T> list, T object, int position) {
        list.add(position, object);
        notifyItemInserted(getStart() + position);
    }

    public void remove(List<?> list, int position) {
        if (list.size() > 0) {
            notifyItemRemoved(getStart() + position);
        }
    }

    public void clear(List<?> list) {
        int start = getStart();
        int size = list.size();
        list.clear();
        notifyItemRangeRemoved(start, size);
    }

    /* loaded from: classes2.dex */
    protected class VIEW_TYPES {
        public static final int FOOTER = -1;
        public static final int HEADER = -3;
        public static final int NORMAL = -4;

        protected VIEW_TYPES() {
            RecyclerViewHolder.this = this$0;
        }
    }
}
