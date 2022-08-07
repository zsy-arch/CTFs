package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.CountryAdapter;
import com.vsf2f.f2f.ui.utils.area.AssetsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CountryDialog extends BaseDialog {
    private static List<Map<String, String>> dataList;
    private Context context;
    private ConfirmDlgListener listener;
    private ListView lvCountry;

    /* loaded from: classes2.dex */
    public interface ConfirmDlgListener {
        void onCountrySelect(String str);
    }

    public CountryDialog(Context context, ConfirmDlgListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_country;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.lvCountry = (ListView) findViewById(R.id.country_lv);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        dataList = setData(AssetsUtils.readJson(this.context, "CountryList.json"));
        this.lvCountry.setAdapter((ListAdapter) new CountryAdapter(this.context, R.layout.item_dlg_country, dataList));
        this.lvCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.dialog.CountryDialog.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CountryDialog.this.listener.onCountrySelect((String) ((Map) CountryDialog.dataList.get(position)).get("code"));
                CountryDialog.this.dismiss();
            }
        });
    }

    public static List<Map<String, String>> setData(String str) {
        dataList = new ArrayList();
        try {
            JSONArray array = new JSONArray(str);
            int len = array.length();
            for (int i = 0; i < len; i++) {
                JSONObject object = array.getJSONObject(i);
                Map<String, String> map = new HashMap<>();
                map.put("en", object.getString("en"));
                map.put("cn", object.getString("cn"));
                map.put("encode", object.getString("encode"));
                map.put("code", object.getString("code"));
                dataList.add(map);
            }
            return dataList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        v.getId();
    }
}
