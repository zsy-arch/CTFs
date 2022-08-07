package com.vsf2f.f2f.ui.map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.navi.AMapNaviListener;
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
import com.autonavi.tbt.TrafficFacilityInfo;
import java.util.LinkedList;

/* loaded from: classes2.dex */
public class TTSController implements AMapNaviListener {
    public static TTSController ttsManager;
    private Context mContext;
    private final String appId = "5350db8d";
    private boolean isPlaying = false;
    private LinkedList<String> wordList = new LinkedList<>();
    private final int TTS_PLAY = 1;
    private final int CHECK_TTS_PLAY = 2;
    private Handler handler = new Handler() { // from class: com.vsf2f.f2f.ui.map.TTSController.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                case 2:
                    if (!TTSController.this.isPlaying) {
                        TTSController.this.handler.obtainMessage(1).sendToTarget();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    private TTSController(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private void createSynthesizer() {
    }

    public void init() {
    }

    public static TTSController getInstance(Context context) {
        if (ttsManager == null) {
            ttsManager = new TTSController(context);
        }
        return ttsManager;
    }

    public void stopSpeaking() {
        if (this.wordList != null) {
            this.wordList.clear();
        }
        this.isPlaying = false;
    }

    public void destroy() {
        if (this.wordList != null) {
            this.wordList.clear();
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onArriveDestination() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onArrivedWayPoint(int arg0) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateRouteFailure(int arg0) {
        if (this.wordList != null) {
            this.wordList.addLast("路线规划失败");
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onCalculateRouteSuccess() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onEndEmulatorNavi() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onGetNavigationText(int arg0, String arg1) {
        if (this.wordList != null) {
            this.wordList.addLast(arg1);
        }
        this.handler.obtainMessage(2).sendToTarget();
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onInitNaviFailure() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onInitNaviSuccess() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onLocationChange(AMapNaviLocation arg0) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onReCalculateRouteForTrafficJam() {
        if (this.wordList != null) {
            this.wordList.addLast("前方路线拥堵，路线重新规划");
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onReCalculateRouteForYaw() {
        if (this.wordList != null) {
            this.wordList.addLast("路线重新规划");
        }
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onStartNavi(int arg0) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onTrafficStatusUpdate() {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onGpsOpenStatus(boolean enabled) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onNaviInfoUpdate(NaviInfo naviinfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateCameraInfo(AMapNaviCameraInfo[] infoArray) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] infoArray) {
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
    public void onCalculateMultipleRoutesSuccess(int[] routeIds) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void notifyParallelRoad(int parallelRoadType) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] infos) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
    }

    @Override // com.amap.api.navi.AMapNaviListener
    public void onPlayRing(int type) {
    }
}
