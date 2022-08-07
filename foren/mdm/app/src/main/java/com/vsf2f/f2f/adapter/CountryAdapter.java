package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class CountryAdapter extends ArrayAdapter<Map<String, String>> {
    private Context context;
    private List<Map<String, String>> data;
    private LayoutInflater inflater;
    private int res;

    public CountryAdapter(Context context, int res, List<Map<String, String>> data) {
        super(context, res, data);
        this.data = new ArrayList();
        this.context = context;
        this.data = data;
        this.res = res;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == 0) {
            if (convertView == null) {
                convertView = this.inflater.inflate(this.res, (ViewGroup) null);
            }
            if (getCount() > 0) {
                ((TextView) convertView.findViewById(R.id.country_i_txtCountryName)).setText(this.data.get(position).get("cn"));
            }
        }
        return convertView;
    }
}
