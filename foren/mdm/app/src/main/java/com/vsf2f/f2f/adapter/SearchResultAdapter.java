package com.vsf2f.f2f.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.services.core.PoiItem;
import com.vsf2f.f2f.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SearchResultAdapter extends BaseAdapter {
    private ArrayList<PoiItem> data;
    private LayoutInflater mInflater;
    private int selectedPosition = 0;

    public int getSelectedPosition() {
        return this.selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public SearchResultAdapter(ArrayList<PoiItem> data) {
        this.data = data;
        if (data == null) {
            this.data = new ArrayList<>();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.data.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        PoiItem item = (PoiItem) getItem(position);
        if (convertView == null) {
            holdView = new HoldView();
            this.mInflater = LayoutInflater.from(parent.getContext());
            convertView = this.mInflater.inflate(R.layout.layout_item_search_result, parent, false);
            holdView.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
            holdView.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holdView.iv_state = (ImageView) convertView.findViewById(R.id.iv_state);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        String address = item.getCityName() + item.getAdName() + item.getBusinessArea() + item.getSnippet();
        if (item.getLatLonPoint() == null) {
            address = "";
        }
        if (position == this.selectedPosition) {
            holdView.iv_state.setImageResource(R.drawable.icon_address_checked);
        } else {
            holdView.iv_state.setImageResource(R.drawable.icon_address_null);
        }
        holdView.tv_name.setText(item.getTitle() + "");
        holdView.tv_location.setText(address + "");
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class HoldView {
        ImageView iv_state;
        TextView tv_location;
        TextView tv_name;

        HoldView() {
        }
    }
}
