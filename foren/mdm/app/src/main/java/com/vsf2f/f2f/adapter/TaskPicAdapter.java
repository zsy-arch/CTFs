package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cdlinglu.utils.ComUtil;
import com.vsf2f.f2f.R;
import java.util.List;

/* loaded from: classes2.dex */
public class TaskPicAdapter extends BaseAdapter {
    private List<String> imagePath;
    private LinearLayout.LayoutParams params;

    public TaskPicAdapter(Context context, List<String> imagePath) {
        this.imagePath = imagePath;
        int width = context.getResources().getDimensionPixelSize(R.dimen.margin_big);
        this.params = new LinearLayout.LayoutParams(width, width);
    }

    public TaskPicAdapter(Context context, List<String> imagePath, int clo) {
        this.imagePath = imagePath;
        int inWidth = ((context.getResources().getDisplayMetrics().widthPixels - (context.getResources().getDimensionPixelSize(R.dimen.margin_normal) * (clo + 1))) / clo) - 5;
        this.params = new LinearLayout.LayoutParams(inWidth, inWidth);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.imagePath.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.imagePath.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        if (convertView == null) {
            holdView = new HoldView();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imgview_lly, parent, false);
            holdView.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        String path = (String) getItem(position);
        if (TextUtils.isEmpty(path)) {
            holdView.imageView.setBackgroundResource(R.drawable.icon_empty_pic);
        } else {
            ComUtil.display(parent.getContext(), holdView.imageView, path, R.drawable.icon_empty_pic);
        }
        holdView.imageView.setLayoutParams(this.params);
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        ImageView imageView;

        HoldView() {
        }
    }
}
