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
import com.em.ui.UserProfileActivity;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.NearbyPeopleAdapter;
import com.vsf2f.f2f.bean.FriendsListBean;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;
import com.vsf2f.f2f.ui.dialog.NearbyPeopleDialog;
import com.vsf2f.f2f.ui.dialog.ScreenDialog;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.GatherInUtils;
import com.vsf2f.f2f.ui.utils.NearbyPeopleAmapUtils;
import com.vsf2f.f2f.ui.view.MyListview;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NearbyPeopleActivity extends BaseActivity implements AMap.OnMapClickListener, AMap.OnMyLocationChangeListener, AMap.OnMarkerClickListener, AMap.OnCameraChangeListener, NearbyPeopleAdapter.AdapterItemClick, BasePopupMenu.PopupListener {
    private NearbyPeopleAdapter adapter;
    private BottomSheetBehavior behavior;
    private Marker clickMarker;
    private MarkerOptions clickMarkeroption;
    private NearbyPeopleDialog descriptionDialog;
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
    private TextView title;
    private TextView tv_load_more;
    private int sex = -1;
    private int changeCount = 0;
    private String normalTitle = "附近的人";
    private List<FriendsListBean.RowsBean> dataList = new ArrayList();

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.savedInstanceState = savedInstanceState;
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.clickMarker = null;
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mapview.onSaveInstanceState(outState);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_nearby_people;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.nearby_task_people, R.string.nav_select);
        this.mapview = (TextureMapView) getView(R.id.map);
        this.mapview.onCreate(this.savedInstanceState);
        this.title = (TextView) getView(R.id.title);
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
        getNearbyPeople(0L, 0.0d);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.descriptionDialog = new NearbyPeopleDialog(this, this);
        this.descriptionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.vsf2f.f2f.ui.needs.demand.NearbyPeopleActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                if (NearbyPeopleActivity.this.clickMarker != null) {
                    NearbyPeopleAmapUtils.getBitmap(NearbyPeopleActivity.this.markerIndex, false);
                }
                NearbyPeopleActivity.this.clickMarker = null;
            }
        });
        this.adapter = new NearbyPeopleAdapter(this.dataList, this);
        this.mlv_demand.setAdapter((ListAdapter) this.adapter);
        initBehavior();
        initMap();
    }

    private void initBehavior() {
        this.behavior = BottomSheetBehavior.from(((CoordinatorLayout) findViewById(R.id.coordinator)).findViewById(R.id.bottom_sheet));
        this.behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.vsf2f.f2f.ui.needs.demand.NearbyPeopleActivity.2
            @Override // android.support.design.widget.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case 3:
                        NearbyPeopleActivity.this.ll_location_map.setVisibility(4);
                        return;
                    case 4:
                        NearbyPeopleActivity.this.ll_location_map.setVisibility(4);
                        NearbyPeopleActivity.this.ll_location.setVisibility(0);
                        return;
                    case 5:
                        NearbyPeopleActivity.this.ll_location_map.setVisibility(0);
                        return;
                    default:
                        return;
                }
            }

            @Override // android.support.design.widget.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset > 0.5d) {
                    NearbyPeopleActivity.this.ll_location.setVisibility(8);
                }
            }
        });
    }

    public void expandDetail(View view) {
        if (this.dataList.size() == 0) {
            MyToast.show(getApplicationContext(), "当前附近没有其他用户,去别处看看吧");
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
        NearbyPeopleAmapUtils.setLocationStyle(this, this.mAMap, R.drawable.icon_location, 1);
    }

    public void publish(View view) {
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
        } else if (UserShared.getInstance().getIsVerifyState(this.context)) {
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable(Constant.MY_LOCATION, this.myLocation);
            startAct(DemandPublishActivity.class, bundle1);
        }
    }

    private void initMap() {
        UiSettings uiSettings = this.mAMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setScaleControlsEnabled(false);
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setZoomGesturesEnabled(true);
        this.mAMap.setMyLocationEnabled(true);
        this.mAMap.setOnMapClickListener(this);
        this.mAMap.setOnMarkerClickListener(this);
        this.mAMap.setOnCameraChangeListener(this);
        this.mAMap.setOnMyLocationChangeListener(this);
        this.mAMap.moveCamera(CameraUpdateFactory.zoomBy(6.0f));
        NearbyPeopleAmapUtils.setLocationStyle(this, this.mAMap, R.drawable.icon_location, 1);
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

    @Override // com.hy.frame.common.BaseActivity, android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_load_more /* 2131756062 */:
                getNearbyPeople(this.lastId, this.lastDistance);
                return;
            case R.id.ll_root /* 2131756223 */:
                this.descriptionDialog.dismiss();
                FriendsListBean.RowsBean bean = this.descriptionDialog.getData();
                if (bean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("username", bean.getUserName());
                    startAct(UserProfileActivity.class, bundle);
                    return;
                }
                return;
            case R.id.head_vLeft /* 2131756685 */:
                onLeftClick();
                return;
            case R.id.head_vRight /* 2131756686 */:
                new ScreenDialog(this.context, this).showAsDropDown(v);
                return;
            default:
                return;
        }
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
            NearbyPeopleAmapUtils.removeAllService();
            if (this.changeCount >= 1) {
                this.latitude = cameraPosition.target.latitude + "";
                this.longitude = cameraPosition.target.longitude + "";
            }
            this.changeCount++;
            getNearbyPeople(0L, 0.0d);
        }
    }

    @Override // com.amap.api.maps.AMap.OnMapClickListener
    public void onMapClick(LatLng latLng) {
    }

    private void getNearbyPeople(long lastId, double lastDistance) {
        getClient().showDialogNow();
        AjaxParams params = new AjaxParams();
        String url = ComUtil.getF2FApi(this.context, getString(R.string.API_NEARBY_PEOPLE)) + "?latitude=" + this.latitude + "&longitude=" + this.longitude + "&distance=1000&pageSize=20&lastId=" + lastId + "&lastDistance=" + lastDistance;
        if (this.sex >= 0) {
            url = url + "&gender=" + this.sex;
        }
        getClient().get(R.string.API_NEARBY_PEOPLE, url, params, FriendsListBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (result.getErrorCode() == 0) {
            switch (result.getRequestCode()) {
                case R.string.API_NEARBY_PEOPLE /* 2131296379 */:
                    FriendsListBean listBean = (FriendsListBean) result.getObj();
                    this.lastId = listBean.getLastId();
                    this.lastDistance = listBean.getLastDistance();
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
                    this.title.setText(this.normalTitle);
                    if (HyUtil.isEmpty(listBean.getRows())) {
                        this.adapter.setData(this.dataList);
                        this.adapter.notifyDataSetChanged();
                        return;
                    }
                    this.dataList.addAll(listBean.getRows());
                    this.adapter.setData(this.dataList);
                    this.adapter.notifyDataSetChanged();
                    NearbyPeopleAmapUtils.addMarksService(this, this.dataList, this.mAMap);
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

    @Override // com.amap.api.maps.AMap.OnMarkerClickListener
    public boolean onMarkerClick(Marker marker) {
        if (this.clickMarker == null) {
            this.clickMarker = marker;
            this.clickMarkeroption = marker.getOptions();
            FriendsListBean.RowsBean easeUser = (FriendsListBean.RowsBean) marker.getObject();
            if (easeUser != null) {
                this.markerIndex = this.dataList.indexOf(easeUser);
                NearbyPeopleAmapUtils.getBitmap(this.markerIndex, true);
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
        MyApplication.setMyLocation(this.myLocation);
    }

    @Override // com.vsf2f.f2f.adapter.NearbyPeopleAdapter.AdapterItemClick
    public void itemClick(int position) {
        FriendsListBean.RowsBean easeUser;
        if (!isLogin()) {
            MyToast.show(getApplicationContext(), "请先登录");
        } else if (!HyUtil.isEmpty(this.dataList) && (easeUser = this.dataList.get(position)) != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("username", easeUser.getUserName());
            startAct(UserProfileActivity.class, bundle);
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mapview.onResume();
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
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (this.descriptionDialog != null && this.descriptionDialog.isShowing()) {
            this.descriptionDialog.dismiss();
        }
        this.changeCount = 0;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu.PopupListener
    public void onClickPopup(View v) {
        switch (v.getId()) {
            case R.id.txt_all /* 2131756341 */:
                onScreen(-1);
                return;
            case R.id.txt_woman /* 2131756342 */:
                onScreen(0);
                return;
            case R.id.txt_man /* 2131756343 */:
                onScreen(1);
                return;
            default:
                return;
        }
    }

    public void onScreen(int flag) {
        this.dataList.clear();
        this.adapter.setData(this.dataList);
        this.adapter.notifyDataSetChanged();
        NearbyPeopleAmapUtils.removeAllService();
        switch (flag) {
            case -1:
                this.normalTitle = "附近的人";
                break;
            case 0:
                this.normalTitle = "附近的美女";
                break;
            case 1:
                this.normalTitle = "附近的帅哥";
                break;
        }
        this.sex = flag;
        getNearbyPeople(0L, 0.0d);
    }
}
