package com.cdlinglu.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

/* loaded from: classes.dex */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected static Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected final int mItemLayoutId;

    public abstract void convert(ViewHolder viewHolder, T t);

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public void refresh(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mDatas.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.mDatas.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, this.mItemLayoutId, position);
    }

    /* loaded from: classes.dex */
    public static class ViewHolder {
        private View mConvertView;
        private OnClickListener mOnItemChangedListener;
        private int mPosition;
        private final SparseArray<View> mViews = new SparseArray<>();

        /* loaded from: classes.dex */
        public interface OnClickListener {
            void onClick(View view);
        }

        private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
            this.mPosition = position;
            this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            this.mConvertView.setTag(this);
        }

        public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
            return convertView == null ? new ViewHolder(context, parent, layoutId, position) : (ViewHolder) convertView.getTag();
        }

        public View getConvertView() {
            return this.mConvertView;
        }

        public View getView(int viewId) {
            View view = this.mViews.get(viewId);
            if (view != null) {
                return view;
            }
            View view2 = this.mConvertView.findViewById(viewId);
            this.mViews.put(viewId, view2);
            return view2;
        }

        public ViewHolder setText(int viewId, String text) {
            ((TextView) getView(viewId)).setText(text);
            return this;
        }

        public ViewHolder setImageResource(int viewId, int drawableId) {
            ((ImageView) getView(viewId)).setImageResource(drawableId);
            return this;
        }

        public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
            ((ImageView) getView(viewId)).setImageBitmap(bm);
            return this;
        }

        public ViewHolder setImageByUrl(int viewId, String url) {
            Glide.with(CommonAdapter.mContext).load(url).into((ImageView) getView(viewId));
            return this;
        }

        protected ViewHolder setOnClick(int viewId, View.OnClickListener onClickListener) {
            getView(viewId).setOnClickListener(onClickListener);
            return this;
        }

        public void setOnClickListener(OnClickListener listener) {
            this.mOnItemChangedListener = listener;
        }

        public int getPosition() {
            return this.mPosition;
        }
    }
}
