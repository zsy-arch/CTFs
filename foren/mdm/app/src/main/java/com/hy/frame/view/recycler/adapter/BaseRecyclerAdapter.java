package com.hy.frame.view.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.adapter.ViewOnClick;
import com.hy.frame.adapter.ViewOnLongClick;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseHolder> {
    protected Context context;
    protected List<T> datas;
    protected LayoutInflater inflater;
    protected int layoutId;
    protected IAdapterListener listener;
    protected BaseHolder vhItem;
    protected boolean showEmptyView = false;
    protected boolean isShowEmptyView = false;
    protected boolean isShowEndView = false;
    protected boolean isAddShowEndViewData = false;

    public abstract void initItemView(BaseHolder baseHolder, T t, int i);

    public abstract BaseHolder setItemViewHolder(View view);

    public BaseRecyclerAdapter(Context context, int layoutId, List<T> datas) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.datas = datas;
        if (this.datas == null) {
            this.datas = new ArrayList();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(this.layoutId, parent, false);
        this.vhItem = setItemViewHolder(itemView);
        return this.vhItem == null ? new BaseHolder(itemView) : this.vhItem;
    }

    public void onBindViewHolder(BaseHolder holder, int position) {
        if (this.vhItem != null && holder.getClass() == this.vhItem.getClass()) {
            initItemView(holder, this.datas.get(position), position);
            setOnClickListener(holder.itemView, this.datas.get(position), position);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.datas.size();
    }

    protected <V extends View> V getView(View v, int resId) {
        return (V) v.findViewById(resId);
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public Context getContext() {
        return this.context;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        if (this.datas == null) {
            this.datas = new ArrayList();
            this.isShowEmptyView = true;
        } else {
            this.isShowEmptyView = false;
        }
        notifyDataSetChanged();
    }

    public void insertDataAtTop(T newData) {
        if (this.datas != null) {
            this.datas.add(0, newData);
        } else {
            this.datas = new ArrayList();
            this.datas.add(newData);
        }
        notifyDataSetChanged();
    }

    public void addDatas(List<T> newDatas) {
        if (this.datas == null) {
            this.datas = new ArrayList();
        }
        int last = this.datas.size();
        this.datas.addAll(newDatas);
        notifyItemInserted(last + 1);
    }

    public void addData(T newData) {
        if (this.datas == null) {
            this.datas = new ArrayList();
        }
        this.datas.add(newData);
        notifyDataSetChanged();
    }

    public void addData(T newData, int index) {
        if (this.datas == null) {
            this.datas = new ArrayList();
        }
        this.datas.add(index, newData);
        notifyDataSetChanged();
    }

    public void setShowEmptyView(boolean showEmptyView) {
        this.showEmptyView = showEmptyView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setIsShowEmptyView(boolean show) {
        if (this.showEmptyView) {
            this.isShowEmptyView = show;
            if (this.isShowEmptyView) {
                this.datas.clear();
                insertDataAtTop(new Object());
            }
        }
    }

    public boolean isShowEndView() {
        return this.isShowEndView;
    }

    public void setShowEndView(boolean showEndView) {
        this.isShowEndView = showEndView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addEndViewData() {
        if (!this.isAddShowEndViewData && this.isShowEndView) {
            this.isAddShowEndViewData = true;
            if (this.datas == null) {
                this.datas = new ArrayList();
            }
            this.datas.add(new Object());
        }
    }

    public void removeEndViewData() {
        if (this.isAddShowEndViewData && this.isShowEndView) {
            this.isAddShowEndViewData = false;
            if (this.datas != null && this.datas.size() > 0) {
                this.datas.remove(this.datas.size() - 1);
            }
        }
    }

    public void removeItem(int position) {
        if (this.datas != null && this.datas.size() > position) {
            this.datas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clearDatas() {
        this.datas.clear();
        notifyDataSetChanged();
    }

    protected IAdapterListener getListener() {
        return this.listener;
    }

    public void setListener(IAdapterListener listener) {
        this.listener = listener;
    }

    public void setOnClickListener(View v, T t, int position) {
        if (getListener() != null) {
            v.setOnClickListener(new ViewOnClick(getListener(), t, position));
        }
    }

    public void setOnLongClickListener(View v, T t, int position) {
        if (getListener() != null) {
            v.setOnLongClickListener(new ViewOnLongClick(getListener(), t, position));
        }
    }
}
