package com.vsf2f.f2f.ui.circle;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
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
import com.easeui.domain.EaseUser;
import com.em.ui.ChatActivity;
import com.em.ui.UserProfileActivity;
import com.hy.frame.util.HyUtil;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.ui.dialog.ChatDialog;
import com.vsf2f.f2f.ui.utils.AmapUtils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class NearbyFriendActivity extends BaseActivity implements AMap.OnMapClickListener, AMap.OnMyLocationChangeListener, AMap.OnMarkerClickListener, AMap.OnCameraChangeListener {
    private ChatDialog chatDialog;
    private Marker clickMarker;
    private MarkerOptions clickMarkeroption;
    protected ArrayList<EaseUser> contactList = new ArrayList<>();
    private AMap mAMap;
    private TextureMapView mMapViewFri;
    private int markerIndex;
    private Bundle savedInstanceState;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.savedInstanceState = savedInstanceState;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_nearby_friend;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.nearby_task_friend, 0);
        this.mMapViewFri = (TextureMapView) getView(R.id.fragment_contacts_amap);
        this.mMapViewFri.onCreate(this.savedInstanceState);
        setOnClickListener(R.id.fragment_contacts_btnloc);
        if (this.mAMap == null) {
            this.mAMap = this.mMapViewFri.getMap();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.contactList = getBundle().getParcelableArrayList("list");
        this.chatDialog = new ChatDialog(this.context, this);
        this.chatDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.vsf2f.f2f.ui.circle.NearbyFriendActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                if (NearbyFriendActivity.this.clickMarker != null) {
                    AmapUtils.getFriBitmap(NearbyFriendActivity.this.markerIndex, false);
                }
                MyApplication.setCurrentMapClickUserId("");
                NearbyFriendActivity.this.clickMarker = null;
            }
        });
        initMap();
        AmapUtils.addMarks(this.context, this.contactList, this.mAMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mMapViewFri.onResume();
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

    private void resetMark() {
        AmapUtils.removeAll();
        AmapUtils.addMarks(this.context, this.contactList, this.mAMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mMapViewFri.onPause();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mMapViewFri.onSaveInstanceState(outState);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mMapViewFri.onDestroy();
    }

    public void toChat(boolean chat) {
        String username;
        this.chatDialog.dismiss();
        EaseUser easeUser2 = this.chatDialog.getData();
        if (easeUser2 != null && (username = easeUser2.getUsername()) != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("username", username);
            startAct(chat ? ChatActivity.class : UserProfileActivity.class, bundle);
        }
    }

    private void updateLocation(MyLocation myLocation) {
        AjaxParams params = new AjaxParams();
        params.put("lat", myLocation.getLatitude());
        params.put("lng", myLocation.getLongitude());
        getClient().post(R.string.API_USER_CHANGE_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA)), params, Boolean.class);
    }

    public void location(View view) {
        AmapUtils.setLocationStyle(this, this.mAMap, R.drawable.icon_location, 1);
    }

    private void initMap() {
        if (this.mAMap == null) {
            this.mAMap = this.mMapViewFri.getMap();
            setUiSettings();
        }
        this.mAMap.setMyLocationEnabled(true);
        this.mAMap.setOnCameraChangeListener(this);
        this.mAMap.setOnMapClickListener(this);
        this.mAMap.setOnMarkerClickListener(this);
        this.mAMap.setOnMyLocationChangeListener(this);
        if (HyUtil.isNetworkConnected(this.context)) {
            this.mAMap.moveCamera(CameraUpdateFactory.zoomBy(3.0f));
            AmapUtils.setLocationStyle(this.context, this.mAMap, R.drawable.icon_location, 1);
        }
    }

    private void setUiSettings() {
        UiSettings uiSettings = this.mAMap.getUiSettings();
        uiSettings.setCompassEnabled(false);
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setScaleControlsEnabled(false);
        uiSettings.setMyLocationButtonEnabled(false);
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
            case R.id.fragment_contacts_btnloc /* 2131756065 */:
                AmapUtils.setLocationStyle(this.context, this.mAMap, R.drawable.icon_location, 1);
                return;
            case R.id.chatdlg_llyUser /* 2131756261 */:
                toChat(false);
                return;
            case R.id.chatdlg_llyMsg /* 2131756262 */:
                toChat(true);
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
    }

    @Override // com.amap.api.maps.AMap.OnMapClickListener
    public void onMapClick(LatLng latLng) {
    }

    @Override // com.amap.api.maps.AMap.OnMarkerClickListener
    public boolean onMarkerClick(Marker marker) {
        if (this.clickMarker == null) {
            this.clickMarker = marker;
            EaseUser easeUser = (EaseUser) marker.getObject();
            if (easeUser != null) {
                this.clickMarkeroption = marker.getOptions();
                this.markerIndex = AmapUtils.getFriendList().indexOf(easeUser);
                AmapUtils.getFriBitmap(this.markerIndex, true);
                MyApplication.setCurrentMapClickUserId(easeUser.getUsername());
                this.chatDialog.setData(easeUser);
                this.chatDialog.show();
            }
        } else if (this.clickMarker.getObject().equals(marker.getObject())) {
            marker.setMarkerOptions(this.clickMarkeroption);
            this.clickMarker = null;
        }
        return true;
    }

    @Override // com.amap.api.maps.AMap.OnMyLocationChangeListener
    public void onMyLocationChange(Location location) {
        this.clickMarker = null;
        updateLocation(LocationUtils.locationToAmap(location.toString()));
    }
}
