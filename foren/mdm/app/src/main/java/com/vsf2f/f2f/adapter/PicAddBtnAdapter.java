package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.support.annotation.DimenRes;
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
public class PicAddBtnAdapter extends BaseAdapter {
    private List<String> imagePaths;
    private int inWidth;
    private int totalNum;

    public PicAddBtnAdapter(Context context, List<String> imagePaths) {
        this(context, imagePaths, R.dimen.margin_normal);
    }

    public PicAddBtnAdapter(Context context, List<String> imagePaths, @DimenRes int padding) {
        super(context);
        this.totalNum = 9;
        this.imagePaths = new ArrayList();
        this.imagePaths = imagePaths;
        this.inWidth = ((getContext().getResources().getDisplayMetrics().widthPixels - (getContext().getResources().getDimensionPixelSize(padding) * 2)) - 6) / 3;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int total = this.imagePaths.size();
        if (total < this.totalNum) {
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
        View view2 = inflate(R.layout.item_imgview_alone);
        ImageView imageView = (ImageView) view2.findViewById(R.id.image);
        if (i == this.imagePaths.size() && this.imagePaths.size() < this.totalNum) {
            Glide.with(getContext()).load(Integer.valueOf((int) R.drawable.icon_addpic_focused)).override(this.inWidth, this.inWidth).centerCrop().crossFade().into(imageView);
        } else if (getItem(i).startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            Glide.with(getContext()).load(getItem(i)).override(this.inWidth, this.inWidth).error((int) R.drawable.img_empty).centerCrop().crossFade().into(imageView);
        } else {
            Glide.with(getContext()).load(new File(getItem(i))).override(this.inWidth, this.inWidth).error((int) R.drawable.img_empty).centerCrop().crossFade().into(imageView);
        }
        return view2;
    }
}
