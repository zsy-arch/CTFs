package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cdlinglu.common.CommonAdapter;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.adapter.MyBaseAdapter;
import com.hy.frame.view.MyGridView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandTypesBean;
import java.util.List;

/* loaded from: classes2.dex */
public class DemandTypeAdapter extends MyBaseAdapter<DemandTypesBean.ChildsBean> {
    private AdapterItemClick itemClick;
    private AbsListView.LayoutParams wrapParams = new AbsListView.LayoutParams(-1, -2);

    /* loaded from: classes2.dex */
    public interface AdapterItemClick {
        void selectType(DemandTypesBean.ChildsBean childsBean);
    }

    public DemandTypeAdapter(Context context, List<DemandTypesBean.ChildsBean> datas, AdapterItemClick itemClick) {
        super(context, datas);
        this.itemClick = itemClick;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder h;
        if (convertView == null) {
            convertView = inflate(R.layout.layout_item_demand_type);
            h = new ViewHolder();
            h.name = (TextView) convertView.findViewById(R.id.name);
            h.logo = (ImageView) convertView.findViewById(R.id.logo);
            h.grid = (MyGridView) convertView.findViewById(R.id.grid);
            convertView.setTag(h);
        } else {
            h = (ViewHolder) convertView.getTag();
        }
        convertView.setLayoutParams(this.wrapParams);
        DemandTypesBean.ChildsBean type = getItem(position);
        h.name.setText(type.getName() + "分类");
        h.logo.setImageResource(ComUtil.getImageResourceId("needstype_" + type.getId()));
        h.grid.setAdapter((ListAdapter) new CommonAdapter<DemandTypesBean.ChildsBean>(getContext(), type.getChilds(), R.layout.item_demand_type_grid) { // from class: com.vsf2f.f2f.adapter.DemandTypeAdapter.1
            public void convert(CommonAdapter.ViewHolder helper, final DemandTypesBean.ChildsBean item) {
                TextView txt = (TextView) helper.getView(R.id.item_demand_type_grid_txt);
                txt.setText(item.getName());
                txt.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.DemandTypeAdapter.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        DemandTypeAdapter.this.itemClick.selectType(item);
                    }
                });
            }
        });
        return convertView;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        private MyGridView grid;
        public ImageView logo;
        public TextView name;

        ViewHolder() {
        }
    }
}
