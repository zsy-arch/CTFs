package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.LocationUtils;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.SearchResultAdapter;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.ui.utils.AmapUtils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ChoiceLocationActivity extends BaseActivity implements AMap.OnMapClickListener, AMap.OnMyLocationChangeListener, AMap.OnCameraChangeListener, PoiSearch.OnPoiSearchListener, TabLayout.OnTabSelectedListener {
    private SearchResultAdapter adapter;
    private LatLng cameraLatlng;
    private PoiItem choicePoiItem;
    private String city;
    private ImageView currentView;
    private TabLayout locationTab;
    private ListView lv_detail;
    private AMap mAMap;
    private TextureMapView mapview;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private Bundle savedInstanceState;
    private String[] arr = {"全部", "写字楼", "小区", "学校"};
    private String keyWord = "";
    private LatLonPoint latLng = new LatLonPoint(0.0d, 0.0d);
    private boolean isItemClick = false;
    private boolean isAbroad = false;
    private int requestCode = 1001;
    private ArrayList<PoiItem> result = new ArrayList<>();

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.savedInstanceState = savedInstanceState;
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mapview.onSaveInstanceState(outState);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_choice_location;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.attach_location, R.string.dl_ok);
        addHeaderRight(R.drawable.icon_easou, R.id.icon_search_location);
        this.locationTab = (TabLayout) getView(R.id.tab_layout);
        this.mapview = (TextureMapView) findViewById(R.id.map);
        this.lv_detail = (ListView) getView(R.id.lv_detail);
        this.mapview.onCreate(this.savedInstanceState);
        if (this.mAMap == null) {
            this.mAMap = this.mapview.getMap();
            this.mAMap.setOnMapClickListener(this);
            UiSettings uiSettings = this.mAMap.getUiSettings();
            uiSettings.setMyLocationButtonEnabled(true);
            uiSettings.setRotateGesturesEnabled(false);
        }
        this.mAMap.moveCamera(CameraUpdateFactory.zoomBy(3.0f));
        this.mAMap.setOnCameraChangeListener(this);
        this.mAMap.setMyLocationEnabled(true);
        this.mAMap.setOnMyLocationChangeListener(this);
        if (HyUtil.isNetworkConnected(getApp())) {
            AmapUtils.setLocationStyle(this, this.mAMap, R.drawable.icon_location, 1);
            this.lv_detail.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.demand.ChoiceLocationActivity.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (((PoiItem) ChoiceLocationActivity.this.result.get(0)).getLatLonPoint() != null) {
                        ChoiceLocationActivity.this.isItemClick = true;
                        if (ChoiceLocationActivity.this.currentView != null) {
                            ChoiceLocationActivity.this.currentView.setImageResource(R.drawable.icon_address_null);
                        }
                        ChoiceLocationActivity.this.adapter.setSelectedPosition(position);
                        ChoiceLocationActivity.this.choicePoiItem = (PoiItem) ChoiceLocationActivity.this.result.get(position);
                        ChoiceLocationActivity.this.currentView = (ImageView) view.findViewById(R.id.iv_state);
                        ChoiceLocationActivity.this.currentView.setImageResource(R.drawable.icon_address_checked);
                        ChoiceLocationActivity.this.mAMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(ChoiceLocationActivity.this.choicePoiItem.getLatLonPoint().getLatitude(), ChoiceLocationActivity.this.choicePoiItem.getLatLonPoint().getLongitude())));
                    }
                }
            });
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        for (int i = 0; i < this.arr.length; i++) {
            TabLayout.Tab tab1 = this.locationTab.newTab().setText(this.arr[i]);
            tab1.setTag(Integer.valueOf(i));
            this.locationTab.addTab(tab1);
        }
        this.locationTab.setOnTabSelectedListener(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.icon_search_location /* 2131755014 */:
                startActForResult(SearchEditActivity.class, this.requestCode);
                return;
            default:
                return;
        }
    }

    @Override // com.amap.api.maps.AMap.OnMapClickListener
    public void onMapClick(LatLng latLng) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        finish();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isAbroad", this.isAbroad);
        bundle.putParcelable("key", this.choicePoiItem);
        Intent intent = new Intent();
        intent.putExtra("bund", bundle);
        setResult(-1, intent);
        finish();
    }

    @Override // com.amap.api.maps.AMap.OnMyLocationChangeListener
    public void onMyLocationChange(Location location) {
        MyLocation myLocation = LocationUtils.locationToAmap(location.toString());
        this.latLng = new LatLonPoint(Double.valueOf(myLocation.getLatitude()).doubleValue(), Double.valueOf(myLocation.getLongitude()).doubleValue());
        doSearchQuery();
    }

    protected void doSearchQuery() {
        if (!HyUtil.isNetworkConnected(getApp())) {
            MyToast.show(getApp(), (int) R.string.network_error);
            return;
        }
        this.query = new PoiSearch.Query(this.keyWord, "", "");
        this.query.setPageSize(30);
        if (this.latLng != null) {
            this.poiSearch = new PoiSearch(this, this.query);
            this.poiSearch.setOnPoiSearchListener(this);
            this.poiSearch.setBound(new PoiSearch.SearchBound(this.latLng, 5000, true));
            this.poiSearch.searchPOIAsyn();
        }
    }

    @Override // com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener
    public void onPoiSearched(PoiResult poiResult, int i) {
        this.result.clear();
        this.result.addAll(poiResult.getPois());
        if (this.result.size() == 0) {
            PoiItem poiItem = new PoiItem("", null, "未找到相关数据", "");
            poiItem.setProvinceName("");
            this.result.add(poiItem);
        }
        if (this.adapter == null) {
            this.adapter = new SearchResultAdapter(this.result);
            this.lv_detail.setAdapter((ListAdapter) this.adapter);
        } else {
            this.adapter.notifyDataSetChanged();
        }
        this.lv_detail.post(new Runnable() { // from class: com.vsf2f.f2f.ui.needs.demand.ChoiceLocationActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (ChoiceLocationActivity.this.lv_detail.getChildAt(0) != null && ((PoiItem) ChoiceLocationActivity.this.result.get(0)).getLatLonPoint() != null) {
                    if (ChoiceLocationActivity.this.currentView != null) {
                        ChoiceLocationActivity.this.currentView.setImageResource(R.drawable.icon_address_null);
                    }
                    ChoiceLocationActivity.this.choicePoiItem = (PoiItem) ChoiceLocationActivity.this.result.get(0);
                    ChoiceLocationActivity.this.adapter.setSelectedPosition(0);
                    ChoiceLocationActivity.this.currentView = (ImageView) ChoiceLocationActivity.this.lv_detail.getChildAt(0).findViewById(R.id.iv_state);
                    ChoiceLocationActivity.this.currentView.setImageResource(R.drawable.icon_address_checked);
                }
            }
        });
    }

    @Override // com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
    public void onTabSelected(TabLayout.Tab tab) {
        int index = ((Integer) tab.getTag()).intValue();
        this.keyWord = this.arr[index];
        if (index == 0) {
            this.keyWord = "";
        }
        doSearchQuery();
    }

    @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override // com.amap.api.maps.AMap.OnCameraChangeListener
    public void onCameraChange(CameraPosition cameraPosition) {
        this.cameraLatlng = cameraPosition.target;
        this.isAbroad = cameraPosition.isAbroad;
    }

    @Override // com.amap.api.maps.AMap.OnCameraChangeListener
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        if (this.isItemClick) {
            this.isItemClick = false;
            return;
        }
        this.cameraLatlng = cameraPosition.target;
        this.latLng.setLatitude(this.cameraLatlng.latitude);
        this.latLng.setLongitude(this.cameraLatlng.longitude);
        doSearchQuery();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        PoiItem poiItem;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == this.requestCode && data.getBundleExtra("bund") != null && (poiItem = (PoiItem) data.getBundleExtra("bund").getParcelable("key")) != null) {
            this.latLng = poiItem.getLatLonPoint();
            this.mAMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(this.latLng.getLatitude(), this.latLng.getLongitude())));
            doSearchQuery();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mapview.onResume();
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mapview.onPause();
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mapview.onDestroy();
    }
}
