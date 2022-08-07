package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.hy.frame.adapter.BaseAdapter;
import com.vsf2f.f2f.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class PicUrlPathAdapter extends BaseAdapter {
    private List<String> imagePaths;
    private LinearLayout.LayoutParams params;

    public PicUrlPathAdapter(Context context, List<String> imagePaths) {
        this(context, imagePaths, 3, R.dimen.margin_normal, R.dimen.padding_normal);
    }

    public PicUrlPathAdapter(Context context, List<String> imagePaths, int numColumns, int gridWidth) {
        super(context);
        this.imagePaths = new ArrayList();
        this.imagePaths = imagePaths;
        int width = (gridWidth - ((numColumns - 1) * 3)) / numColumns;
        this.params = new LinearLayout.LayoutParams(width, width);
    }

    public PicUrlPathAdapter(Context context, List<String> imagePaths, int columns, @DimenRes int padding, @DimenRes int spacing) {
        super(context);
        this.imagePaths = new ArrayList();
        this.imagePaths = imagePaths;
        int length = ((getContext().getResources().getDisplayMetrics().widthPixels - getContext().getResources().getDimensionPixelSize(padding)) - (getContext().getResources().getDimensionPixelSize(spacing) * (columns - 1))) / columns;
        this.params = new LinearLayout.LayoutParams(length, length);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.imagePaths == null) {
            return 0;
        }
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
        View view2 = inflate(R.layout.item_circlepic);
        ImageView imageView = (ImageView) view2.findViewById(R.id.imgcirclepic);
        imageView.setLayoutParams(this.params);
        if (getItem(i).startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            Glide.with(getContext()).load(getItem(i)).error((int) R.drawable.img_empty).centerCrop().crossFade().into(imageView);
        } else {
            Glide.with(getContext()).load(new File(getItem(i))).error((int) R.drawable.img_empty).centerCrop().crossFade().into(imageView);
        }
        return view2;
    }
}
