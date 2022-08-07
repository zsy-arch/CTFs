package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.util.ImageUtil;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class PublishGridAdapter extends BaseAdapter {
    private List<String> imagePaths;
    private int length = (getContext().getResources().getDisplayMetrics().widthPixels - (getContext().getResources().getDimensionPixelSize(R.dimen.margin_default) * 2)) / 3;

    public PublishGridAdapter(Context context, List<String> imagePaths) {
        super(context);
        this.imagePaths = new ArrayList();
        this.imagePaths = imagePaths;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int total = this.imagePaths.size();
        if (total < 9) {
            return total + 1;
        }
        return total;
    }

    @Override // android.widget.Adapter
    public String getItem(int i) {
        return this.imagePaths.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_imageview, viewGroup, false);
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(this.length, this.length);
            holder.imgAdd = (ImageView) getView(view, R.id.image);
            holder.imgAdd.setLayoutParams(layoutParams);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (i != this.imagePaths.size() || this.imagePaths.size() >= 9) {
            String item = getItem(i);
            if (item.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                ComUtil.displayImage(getContext(), holder.imgAdd, item);
            } else {
                holder.imgAdd.setImageBitmap(ImageUtil.getScaledBitmap(item, this.length));
                holder.imgAdd.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        } else {
            holder.imgAdd.setImageResource(R.drawable.icon_addpic_focused);
            holder.imgAdd.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        return view;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        private ImageView imgAdd;

        ViewHolder() {
        }
    }
}
