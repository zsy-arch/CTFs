package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.hy.frame.adapter.BaseAdapter;
import com.vsf2f.f2f.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class DemandPicAdapter extends BaseAdapter {
    private List<String> imagePaths;
    private AbsListView.LayoutParams params;

    public DemandPicAdapter(Context context, List<String> imagePaths) {
        this(context, imagePaths, 4);
    }

    public DemandPicAdapter(Context context, List<String> imagePaths, int clo) {
        super(context);
        this.imagePaths = new ArrayList();
        this.imagePaths = imagePaths;
        int inWidth = (getContext().getResources().getDisplayMetrics().widthPixels - (getContext().getResources().getDimensionPixelSize(R.dimen.margin_normal) * (clo - 1))) / clo;
        this.params = new AbsListView.LayoutParams(inWidth, inWidth);
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = inflate(R.layout.item_imgview_alone);
        ImageView imageView = (ImageView) view2.findViewById(R.id.image);
        imageView.setLayoutParams(this.params);
        String path = getItem(i);
        if (!TextUtils.isEmpty(path)) {
            if (path.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                Glide.with(getContext()).load(path).error((int) R.drawable.img_empty).centerCrop().crossFade().into(imageView);
            } else {
                Glide.with(getContext()).load(new File(path)).error((int) R.drawable.img_empty).centerCrop().crossFade().into(imageView);
            }
        }
        return view2;
    }
}
