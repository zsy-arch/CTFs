package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.hy.frame.adapter.BaseAdapter;
import com.vsf2f.f2f.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class CommentPicAdapter extends BaseAdapter {
    private List<String> imagePaths;
    private AdapterItemClick itemClick;

    /* loaded from: classes2.dex */
    public interface AdapterItemClick {
        void delete(int i);
    }

    public CommentPicAdapter(Context context, List<String> imagePaths, AdapterItemClick itemClick) {
        this(context, imagePaths, 3);
        this.itemClick = itemClick;
    }

    public CommentPicAdapter(Context context, List<String> imagePaths, int clo) {
        super(context);
        this.imagePaths = new ArrayList();
        this.imagePaths = imagePaths;
        int dimensionPixelSize = (getContext().getResources().getDisplayMetrics().widthPixels - (getContext().getResources().getDimensionPixelSize(R.dimen.margin_normal) * (clo + 1))) / (clo + 1);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.imagePaths.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        HoldView holdView;
        if (view == null) {
            holdView = new HoldView();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_comment_pic, viewGroup, false);
            holdView.image = (ImageView) view.findViewById(R.id.image);
            holdView.iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
            view.setTag(holdView);
        } else {
            holdView = (HoldView) view.getTag();
        }
        String path = this.imagePaths.get(i);
        if (!TextUtils.isEmpty(path)) {
            if (path.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                Glide.with(getContext()).load(path).error((int) R.drawable.img_empty).into(holdView.image);
            } else {
                Glide.with(getContext()).load(new File(path)).error((int) R.drawable.img_empty).into(holdView.image);
            }
        }
        holdView.iv_delete.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CommentPicAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                CommentPicAdapter.this.itemClick.delete(i);
            }
        });
        return view;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        ImageView image;
        ImageView iv_delete;

        HoldView() {
        }
    }
}
