package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.LocationUtils;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.ui.needs.star.EaseAddressList;
import com.vsf2f.f2f.ui.utils.AmapUtils;
import com.vsf2f.f2f.ui.utils.area.AddressPicker;
import com.vsf2f.f2f.ui.utils.area.AssetsUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes2.dex */
public class ChoiceCityActivity extends BaseActivity implements AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener {
    private ArrayList<AddressPicker.City> allCities = new ArrayList<>();
    private String currentCity;
    private EditText et_key;
    private GeocodeSearch geocoderSearch;
    private double latitude;
    private ListView listView;
    private double longitude;
    private String selectCity;
    private TextView tv_current;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_choice_address;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        this.geocoderSearch = new GeocodeSearch(this.context);
        this.geocoderSearch.setOnGeocodeSearchListener(this);
        this.et_key = (EditText) findViewById(R.id.et_key);
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_header_address, (ViewGroup) null);
        this.tv_current = (TextView) getView(headerView, R.id.tv_current);
        this.currentCity = getBundle().getString(DistrictSearchQuery.KEYWORDS_CITY);
        if (TextUtils.isEmpty(this.currentCity)) {
            AmapUtils.getLocation(this.context, this);
            this.currentCity = "定位中";
        } else {
            LatLng latLng = MyApplication.getCurrentLatlnt();
            this.latitude = latLng.latitude;
            this.longitude = latLng.longitude;
        }
        getAddress();
        this.tv_current.setText(this.currentCity);
        EaseAddressList contactListLayout = (EaseAddressList) getView(R.id.contact_list);
        contactListLayout.init(this.allCities);
        this.listView = contactListLayout.getListView();
        this.listView.setTextFilterEnabled(true);
        this.listView.addHeaderView(headerView);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.demand.ChoiceCityActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= 0) {
                    ChoiceCityActivity.this.selectCity = ChoiceCityActivity.this.currentCity;
                    ChoiceCityActivity.this.aa();
                    return;
                }
                ChoiceCityActivity.this.selectCity = ((AddressPicker.City) ChoiceCityActivity.this.listView.getAdapter().getItem(position)).getAreaName();
                ChoiceCityActivity.this.getLatlon(ChoiceCityActivity.this.selectCity);
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.et_key.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.needs.demand.ChoiceCityActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Filter filter = ((Filterable) ChoiceCityActivity.this.listView.getAdapter()).getFilter();
                if (TextUtils.isEmpty(s.toString().trim())) {
                    filter.filter("");
                } else {
                    filter.filter(s.toString());
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    public void back(View view) {
        finish();
    }

    public void aa() {
        Intent intent = new Intent();
        intent.putExtra(DistrictSearchQuery.KEYWORDS_CITY, this.selectCity + "");
        intent.putExtra("lat", this.latitude);
        intent.putExtra("lng", this.longitude);
        setResult(-1, intent);
        finish();
    }

    private void getAddress() {
        MyLog.e(f.az, Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        this.allCities.clear();
        ArrayList<AddressPicker.Province> data = new ArrayList<>();
        try {
            data.addAll(JSON.parseArray(AssetsUtils.readText(this, "areas.json"), AddressPicker.Province.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int x = 0; x < data.size(); x++) {
            AddressPicker.Province pro = data.get(x);
            ArrayList<AddressPicker.City> cities = pro.getCities();
            if (cities.size() == 2) {
                AddressPicker.City item = new AddressPicker.City();
                item.setAreaId(pro.getAreaId());
                item.setAreaName(pro.getAreaName());
                this.allCities.add(item);
            } else {
                this.allCities.addAll(cities);
            }
        }
        MyLog.e(f.az, Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        sort();
    }

    private void sort() {
        Collections.sort(this.allCities, new Comparator<AddressPicker.City>() { // from class: com.vsf2f.f2f.ui.needs.demand.ChoiceCityActivity.3
            public int compare(AddressPicker.City lhs, AddressPicker.City rhs) {
                if (lhs.getInitialLetter().equals(rhs.getInitialLetter())) {
                    return lhs.getAreaName().compareTo(rhs.getAreaName());
                }
                if ("#".equals(lhs.getInitialLetter())) {
                    return 1;
                }
                if ("#".equals(rhs.getInitialLetter())) {
                    return -1;
                }
                return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());
            }
        });
    }

    public void getLatlon(String city) {
        this.geocoderSearch.getFromLocationNameAsyn(new GeocodeQuery(city, ""));
    }

    @Override // com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
    }

    @Override // com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
        if (rCode != 1000) {
            MyToast.show(getApp(), rCode);
        } else if (result == null || result.getGeocodeAddressList() == null || result.getGeocodeAddressList().size() <= 0) {
            MyToast.show(getApp(), "定位失败");
        } else {
            LatLng latLng = AmapUtils.convertToLatLng(result.getGeocodeAddressList().get(0).getLatLonPoint());
            this.latitude = latLng.latitude;
            this.longitude = latLng.longitude;
            aa();
        }
    }

    @Override // com.amap.api.location.AMapLocationListener
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            MyLocation myLocation = MyApplication.getMyLocation();
            if (myLocation == null || !myLocation.getAddress().equals(aMapLocation.getAddress())) {
                MyApplication.setMyLocation(LocationUtils.locationToAmap(aMapLocation.toString()));
                this.latitude = aMapLocation.getLatitude();
                this.longitude = aMapLocation.getLongitude();
                this.currentCity = aMapLocation.getCity();
                this.tv_current.setText(this.currentCity);
            }
        }
    }
}
