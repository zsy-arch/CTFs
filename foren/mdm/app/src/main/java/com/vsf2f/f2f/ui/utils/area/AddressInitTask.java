package com.vsf2f.f2f.ui.utils.area;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.vsf2f.f2f.ui.utils.area.AddressPicker;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class AddressInitTask extends AsyncTask<String, Void, ArrayList<AddressPicker.Province>> {
    private ICallBack callBack;
    private Context context;
    private ProgressDialog dialog;
    private boolean hideCounty;
    private String selectedCity;
    private String selectedCounty;
    private String selectedId;
    private String selectedProvince;

    /* loaded from: classes2.dex */
    public interface ICallBack {
        void setStr(String str, String[] strArr);
    }

    public AddressInitTask(Context context, ICallBack callBack, boolean hideCounty) {
        this.selectedProvince = "";
        this.selectedCity = "";
        this.selectedCounty = "";
        this.selectedId = "";
        this.hideCounty = false;
        this.context = context;
        this.hideCounty = hideCounty;
        this.callBack = callBack;
        this.dialog = ProgressDialog.show(context, null, "正在初始化数据...", true, true);
    }

    public AddressInitTask(Context context, ICallBack callBack) {
        this.selectedProvince = "";
        this.selectedCity = "";
        this.selectedCounty = "";
        this.selectedId = "";
        this.hideCounty = false;
        this.context = context;
        this.callBack = callBack;
        this.dialog = ProgressDialog.show(context, null, "正在初始化数据...", true, true);
    }

    public String[] getDate() {
        return new String[]{this.selectedProvince, this.selectedCity, this.selectedCounty};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArrayList<AddressPicker.Province> doInBackground(String... params) {
        if (params != null) {
            switch (params.length) {
                case 1:
                    this.selectedProvince = params[0];
                    break;
                case 2:
                    this.selectedProvince = params[0];
                    this.selectedCity = params[1];
                    break;
                case 3:
                    this.selectedProvince = params[0];
                    this.selectedCity = params[1];
                    this.selectedCounty = params[2];
                    break;
            }
        }
        ArrayList<AddressPicker.Province> data = new ArrayList<>();
        try {
            data.addAll(JSON.parseArray(AssetsUtils.readText(this.context, "areas.json"), AddressPicker.Province.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPostExecute(ArrayList<AddressPicker.Province> result) {
        this.dialog.dismiss();
        if (result.size() > 0) {
            AddressPicker picker = new AddressPicker(this.context, result);
            picker.setHideCounty(this.hideCounty);
            picker.setSelectedItem(this.selectedProvince, this.selectedCity, this.selectedCounty);
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() { // from class: com.vsf2f.f2f.ui.utils.area.AddressInitTask.1
                @Override // com.vsf2f.f2f.ui.utils.area.AddressPicker.OnAddressPickListener
                public void onAddressPicked(AddressPicker.Province province, AddressPicker.City city, AddressPicker.County county) {
                    if (county != null) {
                        AddressInitTask.this.callBack.setStr(county.getAreaId(), new String[]{province.getAreaName(), city.getAreaName(), county.getAreaName()});
                    }
                }
            });
            picker.show();
            return;
        }
        Toast.makeText(this.context, "数据初始化失败", 0).show();
    }
}
