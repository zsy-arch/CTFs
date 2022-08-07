package com.vsf2f.f2f.ui.map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.tbt.TrafficFacilityInfo;
import java.util.List;

/* loaded from: classes.dex */
public class NaviBaseActivity extends Activity implements AMapNaviListener, AMapNaviViewListener {
    protected AMapNavi mAMapNavi;
    protected AMapNaviView mAMapNaviView;
    protected List<NaviLatLng> mWayPointList;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        this.mAMapNavi = AMapNavi.getInstance(getApplicationContext());
        this.mAMapNavi.addAMapNaviListener(this);
        this.mAMapNavi.setEmulatorNaviSpeed(75);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mAMapNaviView.onResume();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mAMapNaviView.onPause();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mAMapNaviView.onDestroy();
        this.mAMapNavi.stopNavi();
        this.mAMapNavi.destroy();
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onInitNaviFailure() {
        Toast.makeText(this, "init navi Failed", 0).show();
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onInitNaviSuccess() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onStartNavi(int type) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onTrafficStatusUpdate() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onLocationChange(AMapNaviLocation location) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onGetNavigationText(int type, String text) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onEndEmulatorNavi() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onArriveDestination() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateRouteSuccess() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateRouteFailure(int errorInfo) {
        Log.e(f.aG, "--------------------------------------------");
        Log.i(f.aG, "路线计算失败：错误码=" + errorInfo + ",Error Message= " + ErrorInfo.getError(errorInfo));
        Log.i(f.aG, "错误码详细链接见：http://lbs.amap.com/api/android-navi-sdk/guide/tools/errorcode/");
        Log.e(f.aG, "--------------------------------------------");
        Toast.makeText(this, "errorInfo：" + errorInfo + ",Message：" + ErrorInfo.getError(errorInfo), 1).show();
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onReCalculateRouteForYaw() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onReCalculateRouteForTrafficJam() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onArrivedWayPoint(int wayID) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onGpsOpenStatus(boolean enabled) {
    }

    @Override // com.amap.api.navi.AMapNaviViewListener
    public void onNaviSetting() {
    }

    @Override // com.amap.api.navi.AMapNaviViewListener
    public void onNaviMapMode(int isLock) {
    }

    @Override // com.amap.api.navi.AMapNaviViewListener
    public void onNaviCancel() {
        finish();
    }

    @Override // com.amap.api.navi.AMapNaviViewListener
    public void onNaviTurnClick() {
    }

    @Override // com.amap.api.navi.AMapNaviViewListener
    public void onNextRoadClick() {
    }

    @Override // com.amap.api.navi.AMapNaviViewListener
    public void onScanViewButtonClick() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    @Deprecated
    public void onNaviInfoUpdated(AMapNaviInfo naviInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapCameraInfos) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] amapServiceAreaInfos) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onNaviInfoUpdate(NaviInfo naviinfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void showCross(AMapNaviCross aMapNaviCross) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void hideCross() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void showLaneInfo(AMapLaneInfo[] laneInfos, byte[] laneBackgroundInfo, byte[] laneRecommendedInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void hideLaneInfo() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateMultipleRoutesSuccess(int[] ints) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void notifyParallelRoad(int i) {
        if (i == 0) {
            Toast.makeText(this, "当前在主辅路过渡", 0).show();
            Log.d("wlx", "当前在主辅路过渡");
        } else if (i == 1) {
            Toast.makeText(this, "当前在主路", 0).show();
            Log.d("wlx", "当前在主路");
        } else if (i == 2) {
            Toast.makeText(this, "当前在辅路", 0).show();
            Log.d("wlx", "当前在辅路");
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onPlayRing(int i) {
    }

    @Override // com.amap.api.navi.AMapNaviViewListener
    public void onLockMap(boolean isLock) {
    }

    @Override // com.amap.api.navi.AMapNaviViewListener
    public void onNaviViewLoaded() {
        Log.d("wlx", "导航页面加载成功");
        Log.d("wlx", "请不要使用AMapNaviView.getMap().setOnMapLoadedListener();会overwrite导航SDK内部画线逻辑");
    }

    @Override // com.amap.api.navi.AMapNaviViewListener
    public boolean onNaviBackClick() {
        return false;
    }
}
