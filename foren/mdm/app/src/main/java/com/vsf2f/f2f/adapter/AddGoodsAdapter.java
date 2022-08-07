package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.hy.frame.adapter.MyBaseAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GoodSingleSpec;
import com.vsf2f.f2f.bean.GoodSingleSpecList;
import java.util.List;

/* loaded from: classes2.dex */
public class AddGoodsAdapter extends MyBaseAdapter<GoodSingleSpec> {
    public AddGoodsAdapter(Context context, List<GoodSingleSpec> datas) {
        super(context, datas);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View v, ViewGroup group) {
        if (v == null) {
            v = inflate(R.layout.item_dlg_editgood);
            new ViewCache(v);
        }
        ViewCache h = (ViewCache) v.getTag();
        final GoodSingleSpec item = getItem(position);
        if (item.getSpecName() != null) {
            h.txtname.setText(item.getSpecName());
        }
        final List<GoodSingleSpecList> values = item.getValues();
        String[] arr = new String[values.size()];
        for (int i = 0; i < values.size(); i++) {
            arr[i] = values.get(i).getSpecValue();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), 17367048, arr);
        adapter.setDropDownViewResource(17367049);
        h.spinner.setAdapter((SpinnerAdapter) adapter);
        h.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.vsf2f.f2f.adapter.AddGoodsAdapter.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position2, long id) {
                item.setListtemp((GoodSingleSpecList) values.get(position2));
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        h.spinner.setVisibility(0);
        return v;
    }

    /* loaded from: classes2.dex */
    class ViewCache {
        private Spinner spinner;
        private TextView txtname;

        public ViewCache(View v) {
            v.setTag(this);
            this.txtname = (TextView) AddGoodsAdapter.this.getView(v, R.id.item_txtname);
            this.spinner = (Spinner) AddGoodsAdapter.this.getView(v, R.id.sp_eidt);
        }
    }
}
