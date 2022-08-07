package com.vsf2f.f2f.ui.needs.demand;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
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
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.server.audio.VoicePlayer;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.LocationUtils;
import com.easeui.EaseConstant;
import com.em.DemoHelper;
import com.em.ui.ChatActivity;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.tencent.open.SocialConstants;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.NearbyDemandAdapter;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandServiceBean;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.ui.dialog.DemandDescriptionDialog;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.AmapUtils;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.GatherInUtils;
import com.vsf2f.f2f.ui.view.MyListview;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class NearbyDemandActivity extends BaseActivity implements AMap.OnMapClickListener, AMap.OnMyLocationChangeListener, AMap.OnMarkerClickListener, AMap.OnCameraChangeListener, NearbyDemandAdapter.AdapterItemClick {
    private NearbyDemandAdapter adapter;
    private BottomSheetBehavior behavior;
    private Marker clickMarker;
    private MarkerOptions clickMarkeroption;
    private int currentZoom;
    private DemandDescriptionDialog descriptionDialog;
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
    private int pageNo = 1;
    private List<DemandDetailBean> dataList = new ArrayList();
    private int changeCount = 0;

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

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.clickMarker = null;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_nearby_demand;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.nearby_task_demand, 0);
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
        getNearbyDemand();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.descriptionDialog = new DemandDescriptionDialog(this, this);
        this.descriptionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.vsf2f.f2f.ui.needs.demand.NearbyDemandActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                if (NearbyDemandActivity.this.clickMarker != null) {
                    AmapUtils.getBitmap(NearbyDemandActivity.this.markerIndex, false);
                }
                NearbyDemandActivity.this.clickMarker = null;
            }
        });
        this.adapter = new NearbyDemandAdapter(this.dataList, this);
        this.mlv_demand.setAdapter((ListAdapter) this.adapter);
        initBehavior();
        initMap();
    }

    private void initBehavior() {
        this.behavior = BottomSheetBehavior.from(((CoordinatorLayout) findViewById(R.id.coordinator)).findViewById(R.id.bottom_sheet));
        this.behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.vsf2f.f2f.ui.needs.demand.NearbyDemandActivity.2
            @Override // android.support.design.widget.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case 3:
                        NearbyDemandActivity.this.ll_location_map.setVisibility(4);
                        return;
                    case 4:
                        NearbyDemandActivity.this.ll_location_map.setVisibility(4);
                        NearbyDemandActivity.this.ll_location.setVisibility(0);
                        return;
                    case 5:
                        NearbyDemandActivity.this.ll_location_map.setVisibility(0);
                        return;
                    default:
                        return;
                }
            }

            @Override // android.support.design.widget.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset > 0.5d) {
                    NearbyDemandActivity.this.ll_location.setVisibility(8);
                }
            }
        });
    }

    public void expandDetail(View view) {
        if (this.dataList.size() == 0) {
            MyToast.show(getApplicationContext(), "当前附近没有需求,稍后再试");
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
        AmapUtils.setLocationStyle(this, this.mAMap, R.drawable.icon_location, 1);
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
        this.mAMap.setOnCameraChangeListener(this);
        this.mAMap.setMyLocationEnabled(true);
        this.mAMap.setOnMapClickListener(this);
        this.mAMap.setOnMarkerClickListener(this);
        this.mAMap.setOnMyLocationChangeListener(this);
        this.mAMap.moveCamera(CameraUpdateFactory.zoomBy(6.0f));
        AmapUtils.setLocationStyle(this, this.mAMap, R.drawable.icon_location, 1);
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
                this.pageNo++;
                getNearbyDemand();
                return;
            case R.id.ll_root /* 2131756223 */:
                this.descriptionDialog.dismiss();
                DemandDetailBean bean = this.descriptionDialog.getData();
                if (bean != null) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("id", bean.getMoId());
                    startAct(DemandInfoActivity.class, bundle2);
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
            AmapUtils.removeAllService();
            this.currentZoom = (int) cameraPosition.zoom;
            if (this.changeCount >= 1) {
                this.latitude = cameraPosition.target.latitude + "";
                this.longitude = cameraPosition.target.longitude + "";
            }
            this.changeCount++;
            this.pageNo = 1;
            getNearbyDemand();
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
            DemandDetailBean easeUser = (DemandDetailBean) marker.getObject();
            if (easeUser != null) {
                this.markerIndex = this.dataList.indexOf(easeUser);
                AmapUtils.getBitmap(this.markerIndex, true);
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

    @Override // com.vsf2f.f2f.adapter.NearbyDemandAdapter.AdapterItemClick
    public void playVoice(View v, String url) {
        new VoicePlayer(this, url, (MusicPlayer) v).onClick(v);
    }

    @Override // com.vsf2f.f2f.adapter.NearbyDemandAdapter.AdapterItemClick
    public void ask(DemandDetailBean detailBean) {
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
        } else if (detailBean != null) {
            Map<String, String> map = new HashMap<>();
            map.put("type", "demand");
            map.put("moId", detailBean.getMoId() + "");
            map.put("title", detailBean.getTitle() + "");
            map.put(SocialConstants.PARAM_APP_DESC, detailBean.getDescription() + "");
            map.put("reward", detailBean.getReward() + "");
            map.put("unit", detailBean.getUnit() + "");
            map.put("serviceMode", detailBean.getServiceMode() + "");
            map.put("address", detailBean.getAddress() + "");
            map.put("voiceUrl", detailBean.getVoiceFullUrl() + "");
            map.put("voiceDuration", detailBean.getVoiceDuration() + "");
            map.put("publishUser", detailBean.getPublishUser());
            JSONObject json = new JSONObject(map);
            Bundle bundle = new Bundle();
            bundle.putString(MessageEncoder.ATTR_EXT, json.toString());
            bundle.putString("username", detailBean.getPublishUser());
            bundle.putBoolean(EaseConstant.BACK_TYPE, false);
            startAct(ChatActivity.class, bundle);
        }
    }

    @Override // com.vsf2f.f2f.adapter.NearbyDemandAdapter.AdapterItemClick
    public void getOrder(DemandDetailBean bean) {
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
        } else if (bean != null) {
            String cusname = DemoHelper.getInstance().getCurrentUserName();
            String demandname = bean.getPublishUserObj().getUserName();
            if (!TextUtils.isEmpty(demandname)) {
                Bundle bundle = new Bundle();
                if (cusname.equals(demandname)) {
                    MyToast.show(getApp(), "不能接自己的需求");
                } else if (bean.getStatus() == 10) {
                    MyToast.show(getApp(), "此需求方尚未支付定金");
                } else {
                    bundle.putInt("id", bean.getMoId());
                    startAct(DemandInfoActivity.class, bundle);
                }
            }
        }
    }

    @Override // com.vsf2f.f2f.adapter.NearbyDemandAdapter.AdapterItemClick
    public void itemClick(int position) {
        DemandDetailBean bean;
        if (!HyUtil.isEmpty(this.dataList) && (bean = this.dataList.get(position)) != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("id", bean.getMoId());
            startAct(DemandInfoActivity.class, bundle);
        }
    }

    private void getNearbyDemand() {
        getClient().showDialogNow();
        AjaxParams params = new AjaxParams();
        getClient().get(R.string.API_DEMAND_HOMEPAGE, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_HOMEPAGE)) + "?pageNo=" + this.pageNo + "&pageSize=20&lat=" + this.latitude + "&lng=" + this.longitude + "&zoom=" + this.currentZoom + "&orderBy=2", params, DemandServiceBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (result.getErrorCode() == 0) {
            switch (result.getRequestCode()) {
                case R.string.API_DEMAND_HOMEPAGE /* 2131296326 */:
                    DemandServiceBean bean = (DemandServiceBean) result.getObj();
                    if (HyUtil.isEmpty(bean.getDatas())) {
                        this.adapter.setData(this.dataList);
                        this.adapter.notifyDataSetChanged();
                        if (this.pageNo == 1) {
                            this.tv_load_more.setVisibility(8);
                        } else {
                            this.tv_load_more.setText("没有更多了");
                            this.tv_load_more.setClickable(false);
                            this.tv_load_more.setEnabled(false);
                        }
                    } else {
                        this.dataList.addAll(bean.getDatas());
                        this.adapter.setData(this.dataList);
                        this.adapter.notifyDataSetChanged();
                        AmapUtils.addMarksService(this, this.dataList, this.mAMap);
                        this.tv_load_more.setVisibility(0);
                        this.tv_load_more.setText("点击加载更多");
                        this.tv_load_more.setClickable(true);
                        this.tv_load_more.setEnabled(true);
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

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
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
        VoicePlayer.stopVoice();
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        VoicePlayer.stopVoice();
        this.mapview.onDestroy();
        super.onDestroy();
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
