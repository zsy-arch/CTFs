package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GetSpecBean;
import com.vsf2f.f2f.ui.utils.listener.SelectDialogListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SelectSpecAdapter extends BaseAdapter {
    private Context context;
    private SelectDialogListener listener;
    private List<GetSpecBean> mySpec;
    private List<GetSpecBean> selected = new ArrayList();

    public SelectSpecAdapter(final Context context, Button saveView, List<GetSpecBean> mySpec, final SelectDialogListener listener) {
        this.context = context;
        this.mySpec = mySpec;
        this.listener = listener;
        saveView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.SelectSpecAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (SelectSpecAdapter.this.selected.size() == 0) {
                    MyToast.show(context, "请选择商品规格");
                } else {
                    listener.saveClick(SelectSpecAdapter.this.selected);
                }
            }
        });
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mySpec.size();
    }

    @Override // android.widget.Adapter
    public GetSpecBean getItem(int position) {
        return this.mySpec.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewHolder holder = new ViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_select_spec, (ViewGroup) null);
            holder.specView = (TextView) convertView.findViewById(R.id.tv_spec_item);
            holder.selectedView = (TextView) convertView.findViewById(R.id.tv_selected_item);
            holder.addView = (TextView) convertView.findViewById(R.id.tv_add_item);
            holder.containerView = (LinearLayout) convertView.findViewById(R.id.ll_item_container);
            convertView.setTag(holder);
        }
        final ViewHolder holder2 = (ViewHolder) convertView.getTag();
        final GetSpecBean bean = getItem(position);
        holder2.specView.setText(bean.getSpecName());
        convertView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.SelectSpecAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (holder2.selectedView.getVisibility() == 8) {
                    holder2.selectedView.setVisibility(0);
                    holder2.containerView.setBackgroundResource(R.color.item_selected);
                    SelectSpecAdapter.this.selected.add(bean);
                } else if (holder2.selectedView.getVisibility() == 0) {
                    holder2.selectedView.setVisibility(8);
                    holder2.containerView.setBackgroundResource(R.color.classify_bg);
                    SelectSpecAdapter.this.selected.remove(bean);
                }
            }
        });
        holder2.addView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.SelectSpecAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SelectSpecAdapter.this.listener.addClick(bean.getSpecName(), bean.getGuid());
            }
        });
        return convertView;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        TextView addView;
        LinearLayout containerView;
        TextView selectedView;
        TextView specView;

        ViewHolder() {
        }
    }
}
