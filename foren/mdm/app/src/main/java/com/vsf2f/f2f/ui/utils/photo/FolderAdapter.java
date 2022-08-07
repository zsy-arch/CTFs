package com.vsf2f.f2f.ui.utils.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.vsf2f.f2f.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class FolderAdapter extends BaseAdapter {
    private Context mContext;
    private int mImageSize;
    private LayoutInflater mInflater;
    private List<Folder> mFolders = new ArrayList();
    private int lastSelected = 0;

    public FolderAdapter(Context context) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mImageSize = this.mContext.getResources().getDimensionPixelOffset(R.dimen.folder_cover_size);
    }

    public void setData(List<Folder> folders) {
        if (folders == null || folders.size() <= 0) {
            this.mFolders.clear();
        } else {
            this.mFolders = folders;
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mFolders.size() + 1;
    }

    @Override // android.widget.Adapter
    public Folder getItem(int i) {
        if (i == 0) {
            return null;
        }
        return this.mFolders.get(i - 1);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.item_folder, viewGroup, false);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (holder != null) {
            if (i == 0) {
                holder.name.setText(this.mContext.getResources().getString(R.string.all_image));
                holder.size.setText(getTotalImageSize() + "张");
                if (this.mFolders.size() > 0) {
                    Glide.with(this.mContext).load(new File(this.mFolders.get(0).cover.path)).error((int) R.drawable.img_empty).dontAnimate().into(holder.cover);
                }
            } else {
                holder.bindData(getItem(i));
            }
            if (this.lastSelected == i) {
                holder.indicator.setVisibility(0);
            } else {
                holder.indicator.setVisibility(4);
            }
        }
        return view;
    }

    private int getTotalImageSize() {
        int result = 0;
        if (this.mFolders != null && this.mFolders.size() > 0) {
            for (Folder f : this.mFolders) {
                result += f.images.size();
            }
        }
        return result;
    }

    public void setSelectIndex(int i) {
        if (this.lastSelected != i) {
            this.lastSelected = i;
            notifyDataSetChanged();
        }
    }

    public int getSelectIndex() {
        return this.lastSelected;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        ImageView cover;
        ImageView indicator;
        TextView name;
        TextView size;

        ViewHolder(View view) {
            this.cover = (ImageView) view.findViewById(R.id.cover);
            this.name = (TextView) view.findViewById(R.id.name);
            this.size = (TextView) view.findViewById(R.id.size);
            this.indicator = (ImageView) view.findViewById(R.id.indicator);
            view.setTag(this);
        }

        void bindData(Folder data) {
            this.name.setText(data.name);
            this.size.setText(data.images.size() + "张");
            Glide.with(FolderAdapter.this.mContext).load(new File(data.cover.path)).error((int) R.drawable.img_empty).dontAnimate().into(this.cover);
        }
    }
}
