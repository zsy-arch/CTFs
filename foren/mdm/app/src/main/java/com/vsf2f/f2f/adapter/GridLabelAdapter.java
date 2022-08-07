package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hy.frame.adapter.MyBaseAdapter;
import com.vsf2f.f2f.R;
import java.util.List;

/* loaded from: classes2.dex */
public class GridLabelAdapter extends MyBaseAdapter<String> {
    private int clickStatus = 0;

    public void setSeclection(int position) {
        this.clickStatus = position;
    }

    public GridLabelAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View v, ViewGroup group) {
        if (v == null) {
            v = inflate(R.layout.item_circles_list_grid2);
            new ViewCache(v);
        }
        ViewCache c = (ViewCache) v.getTag();
        c.txtLable.setText(getItem(position));
        setOnClickListener(c.txtLable, position);
        c.txtLable.setSelected(this.clickStatus == position);
        return v;
    }

    /* loaded from: classes2.dex */
    class ViewCache {
        TextView txtLable;

        public ViewCache(View v) {
            v.setTag(this);
            this.txtLable = (TextView) GridLabelAdapter.this.getView(v, R.id.circles_gdv_i_txtLabel);
        }
    }
}
