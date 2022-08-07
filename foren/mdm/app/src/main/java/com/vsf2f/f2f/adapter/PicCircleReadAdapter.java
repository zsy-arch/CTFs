package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.adapter.MyBaseAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.CircleReadPicListBean;
import java.util.List;

/* loaded from: classes2.dex */
public class PicCircleReadAdapter extends MyBaseAdapter<CircleReadPicListBean> {
    private LinearLayout.LayoutParams params;

    public PicCircleReadAdapter(Context context, List<CircleReadPicListBean> datas) {
        this(context, datas, 3);
    }

    public PicCircleReadAdapter(Context context, List<CircleReadPicListBean> datas, int numColumns) {
        this(context, datas, numColumns, context.getResources().getDisplayMetrics().widthPixels);
    }

    public PicCircleReadAdapter(Context context, List<CircleReadPicListBean> datas, int numColumns, int gridWidth) {
        super(context, datas);
        int width = (gridWidth - ((numColumns - 1) * 3)) / numColumns;
        this.params = new LinearLayout.LayoutParams(width, width);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View v, ViewGroup group) {
        ViewCache c;
        if (v == null) {
            v = inflate(R.layout.item_circlepic);
            c = new ViewCache(v);
            v.setTag(c);
        } else {
            c = (ViewCache) v.getTag();
        }
        CircleReadPicListBean item = getItem(position);
        if (item.getSpath() == null) {
            c.imgPic.setBackgroundResource(R.drawable.icon_empty_pic);
        } else {
            ComUtil.display(getContext(), c.imgPic, item.getSpath(), R.drawable.icon_empty_pic);
        }
        c.imgPic.setLayoutParams(this.params);
        setOnClickListener(c.imgPic, position);
        return v;
    }

    /* loaded from: classes2.dex */
    static class ViewCache {
        private ImageView imgPic;

        public ViewCache(View v) {
            v.setTag(this);
            this.imgPic = (ImageView) v.findViewById(R.id.imgcirclepic);
        }
    }
}
