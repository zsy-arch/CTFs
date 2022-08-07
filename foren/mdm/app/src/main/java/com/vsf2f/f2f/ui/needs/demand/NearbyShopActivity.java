package com.vsf2f.f2f.ui.needs.demand;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.LocationUtils;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.NearbyShopAdapter;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.bean.NearbyShopBean;
import com.vsf2f.f2f.ui.dialog.ShopDescriptionDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.GatherInUtils;
import com.vsf2f.f2f.ui.utils.ShopAmapUtils;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import com.vsf2f.f2f.ui.view.MyListview;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NearbyShopActivity extends BaseActivity implements AMap.OnMapClickListener, AMap.OnMyLocationChangeListener, AMap.OnMarkerClickListener, AMap.OnCameraChangeListener, NearbyShopAdapter.AdapterItemClick {
    private NearbyShopAdapter adapter;
    private BottomSheetBehavior behavior;
    private Marker clickMarker;
    private MarkerOptions clickMarkeroption;
    private int collectIndex;
    private ShopDescriptionDialog descriptionDialog;
    private double lastDistance;
    private long lastId;
    private String latitude;
    private LinearLayout ll_location;
    private LinearLayout ll_location_map;
    private String longitude;
    private AMap mAMap;
    private TextureMapView mapview;
    private int markerIndex;
    private MyListview mlv_demand;
    private MyLocation myLocation;
    private Bundle savedInstanceState;
    private TextView tv_load_more;
    private int changeCount = 0;
    private List<NearbyShopBean.RowsBean> dataList = new ArrayList();

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
        return R.layout.activity_nearby_shop;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.nearby_task_seller, 0);
        this.mapview = (TextureMapView) getView(R.id.map);
        this.mapview.onCreate(this.savedInstanceState);
        this.ll_location = (LinearLayout) getView(R.id.ll_location);
        this.ll_location_map = (LinearLayout) getView(R.id.ll_location_map);
        this.tv_load_more = (TextView) getViewAndClick(R.id.tv_load_more);
        this.mlv_demand = (MyListview) getView(R.id.mlv_demand);
        if (this.mAMap == null) {
            this.mAMap = this.mapview.getMap();
        }
        Bundle bundle = getBundle();
        this.latitude = bundle.getString("latitude");
        this.longitude = bundle.getString("longitude");
        getNearbyShop(0L, 0.0d);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.descriptionDialog = new ShopDescriptionDialog(this, this);
        this.descriptionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.vsf2f.f2f.ui.needs.demand.NearbyShopActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                if (NearbyShopActivity.this.clickMarker != null) {
                    ShopAmapUtils.getBitmap(NearbyShopActivity.this.markerIndex, false);
                }
                NearbyShopActivity.this.clickMarker = null;
            }
        });
        this.adapter = new NearbyShopAdapter(this.dataList, this);
        this.mlv_demand.setAdapter((ListAdapter) this.adapter);
        initBehavior();
        initMap();
    }

    private void initBehavior() {
        this.behavior = BottomSheetBehavior.from(((CoordinatorLayout) findViewById(R.id.coordinator)).findViewById(R.id.bottom_sheet));
        this.behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.vsf2f.f2f.ui.needs.demand.NearbyShopActivity.2
            @Override // android.support.design.widget.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case 3:
                        NearbyShopActivity.this.ll_location_map.setVisibility(4);
                        return;
                    case 4:
                        NearbyShopActivity.this.ll_location_map.setVisibility(4);
                        NearbyShopActivity.this.ll_location.setVisibility(0);
                        return;
                    case 5:
                        NearbyShopActivity.this.ll_location_map.setVisibility(0);
                        return;
                    default:
                        return;
                }
            }

            @Override // android.support.design.widget.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset > 0.5d) {
                    NearbyShopActivity.this.ll_location.setVisibility(8);
                }
            }
        });
    }

    public void expandDetail(View view) {
        if (this.dataList.size() == 0) {
            MyToast.show(getApplicationContext(), "当前附近没有商家,去别处看看吧");
            return;
        }
        int bstate = this.behavior.getState();
        MyLog.e("bstate", Integer.valueOf(bstate));
        if (bstate != 4 && bstate != 2) {
            this.behavior.setState(4);
        } else if (view != null) {
            this.behavior.setState(5);
        } else {
            this.behavior.setPeekHeight(-1);
        }
    }

    public void location(View view) {
        ShopAmapUtils.setLocationStyle(this, this.mAMap, R.drawable.icon_location, 1);
    }

    public void publish(View view) {
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
            return;
        }
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable(Constant.MY_LOCATION, this.myLocation);
        startAct(DemandPublishActivity.class, bundle1);
    }

    private void initMap() {
        UiSettings uiSettings = this.mAMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setScaleControlsEnabled(false);
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setZoomGesturesEnabled(true);
        this.mAMap.setOnCameraChangeListener(this);
        this.mAMap.setMyLocationEnabled(true);
        this.mAMap.setOnMapClickListener(this);
        this.mAMap.setOnMarkerClickListener(this);
        this.mAMap.setOnMyLocationChangeListener(this);
        this.mAMap.moveCamera(CameraUpdateFactory.zoomBy(6.0f));
        ShopAmapUtils.setLocationStyle(this, this.mAMap, R.drawable.icon_location, 1);
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
            case R.id.tv_load_more /* 2131756062 */:
                getNearbyShop(this.lastId, this.lastDistance);
                return;
            case R.id.ll_root /* 2131756223 */:
                this.descriptionDialog.dismiss();
                NearbyShopBean.RowsBean bean = this.descriptionDialog.getData();
                if (bean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(this.context, "/m/shop/" + bean.getUserName() + ".mobile"));
                    bundle.putString(com.hy.frame.util.Constant.FLAG_TITLE, bean.getStoreName() + "");
                    bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                    startAct(WebKitLocalActivity.class, bundle);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        super.onRightClick();
    }

    @Override // com.amap.api.maps.AMap.OnCameraChangeListener
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    @Override // com.amap.api.maps.AMap.OnCameraChangeListener
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        if (!GatherInUtils.checkInScreen(new LatLng(Double.valueOf(this.latitude).doubleValue(), Double.valueOf(this.longitude).doubleValue()), this.mAMap)) {
            this.dataList.clear();
            this.adapter.setData(this.dataList);
            this.adapter.notifyDataSetChanged();
            ShopAmapUtils.removeAllService();
            if (this.changeCount >= 1) {
                this.latitude = cameraPosition.target.latitude + "";
                this.longitude = cameraPosition.target.longitude + "";
            }
            this.changeCount++;
            getNearbyShop(0L, 0.0d);
        }
    }

    @Override // com.amap.api.maps.AMap.OnMapClickListener
    public void onMapClick(LatLng latLng) {
    }

    @Override // com.amap.api.maps.AMap.OnMarkerClickListener
    public boolean onMarkerClick(Marker marker) {
        if (this.clickMarker == null) {
            this.clickMarker = marker;
            this.clickMarkeroption = marker.getOptions();
            NearbyShopBean.RowsBean easeUser = (NearbyShopBean.RowsBean) marker.getObject();
            if (easeUser != null) {
                this.markerIndex = this.dataList.indexOf(easeUser);
                ShopAmapUtils.getBitmap(this.markerIndex, true);
                this.descriptionDialog.setData(easeUser);
                this.descriptionDialog.show();
            } else {
                this.clickMarker = null;
            }
        } else if (this.clickMarker.getId().equals(marker.getId())) {
            marker.remove();
            marker.setMarkerOptions(this.clickMarkeroption);
            this.clickMarker = null;
        }
        return true;
    }

    @Override // com.amap.api.maps.AMap.OnMyLocationChangeListener
    public void onMyLocationChange(Location location) {
        this.myLocation = LocationUtils.locationToAmap(location.toString());
        this.latitude = this.myLocation.getLatitude();
        this.longitude = this.myLocation.getLongitude();
        MyApplication.setCurrentLatlnt(new LatLng(Double.valueOf(this.latitude).doubleValue(), Double.valueOf(this.longitude).doubleValue()));
    }

    @Override // com.vsf2f.f2f.adapter.NearbyShopAdapter.AdapterItemClick
    public void collect(int position) {
        if (!isLogin()) {
            MyToast.show(getApplicationContext(), "请先登录");
            return;
        }
        this.collectIndex = position;
        NearbyShopBean.RowsBean rowsBean = this.dataList.get(position);
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        params.put("name", rowsBean.getStoreName());
        params.put("href", ("/m/shop/" + rowsBean.getUserName() + ".mobile").trim());
        params.put(f.aY, rowsBean.getLogo().getSpath().trim());
        params.put("type", 1);
        params.put("favType", 1);
        params.put("orderNum", 999);
        params.put("sortName", "");
        params.put("objId", rowsBean.getUserName());
        getClient().post(R.string.API_ADD_FAVARITE, ComUtil.getZCApi(this, getString(R.string.API_ADD_FAVARITE)), params, String.class, false);
    }

    @Override // com.vsf2f.f2f.adapter.NearbyShopAdapter.AdapterItemClick
    public void itemClick(int position) {
        NearbyShopBean.RowsBean easeUser;
        if (!HyUtil.isEmpty(this.dataList) && (easeUser = this.dataList.get(position)) != null) {
            Bundle bundle = new Bundle();
            String url = "/m/shop/" + easeUser.getUserName() + ".mobile";
            if (isLogin()) {
                bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(this.context, url));
            } else {
                bundle.putString(com.hy.frame.util.Constant.FLAG, ComUtil.getZCApi(this.context, url));
            }
            bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
            startAct(WebKitLocalActivity.class, bundle);
        }
    }

    private void getNearbyShop(long lastId, double lastDistance) {
        getClient().showDialogNow();
        AjaxParams params = new AjaxParams();
        getClient().get(R.string.API_NEARBY_SHOP, ComUtil.getZCApi(this.context, getString(R.string.API_NEARBY_SHOP)) + "?lastId=" + lastId + "&lastDistance=" + lastDistance + "&latitude=" + this.latitude + "&longitude=" + this.longitude + "&distance=1000", params, NearbyShopBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (result.getErrorCode() == 0) {
            switch (result.getRequestCode()) {
                case R.string.API_ADD_FAVARITE /* 2131296282 */:
                    MyToast.show(getApplicationContext(), "收藏店铺成功");
                    if (this.dataList.size() > this.collectIndex) {
                        this.dataList.get(this.collectIndex).setCollection(true);
                    }
                    this.adapter.notifyDataSetChanged();
                    updateUI();
                    return;
                case R.string.API_NEARBY_SHOP /* 2131296380 */:
                    NearbyShopBean bean = (NearbyShopBean) result.getObj();
                    this.lastId = bean.getLastId();
                    this.lastDistance = bean.getLastDistance();
                    if (this.lastId == 0) {
                        this.tv_load_more.setText("没有更多了");
                        this.tv_load_more.setClickable(false);
                        this.tv_load_more.setEnabled(false);
                    } else {
                        this.tv_load_more.setVisibility(0);
                        this.tv_load_more.setText("点击加载更多");
                        this.tv_load_more.setClickable(true);
                        this.tv_load_more.setEnabled(true);
                    }
                    if (HyUtil.isEmpty(bean.getRows())) {
                        this.adapter.setData(this.dataList);
                        this.adapter.notifyDataSetChanged();
                    } else {
                        this.dataList.addAll(bean.getRows());
                        this.adapter.setData(this.dataList);
                        this.adapter.notifyDataSetChanged();
                        ShopAmapUtils.addMarksService(this, this.dataList, this.mAMap);
                    }
                    if (this.ll_location_map.getVisibility() == 0) {
                        expandDetail(null);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mapview.onResume();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.clickMarker = null;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
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
        this.descriptionDialog.setOnDismissListener(null);
        this.behavior.setBottomSheetCallback(null);
        ShopAmapUtils.setNull();
        this.mapview = null;
        this.adapter = null;
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (this.descriptionDialog != null && this.descriptionDialog.isShowing()) {
            this.descriptionDialog.dismiss();
        }
        this.changeCount = 0;
    }
}
