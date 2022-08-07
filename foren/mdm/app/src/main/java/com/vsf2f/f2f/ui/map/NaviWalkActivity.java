package com.vsf2f.f2f.ui.map;

import android.os.Bundle;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewOptions;
import com.amap.api.navi.model.NaviLatLng;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NaviWalkActivity extends NaviBaseActivity {
    private NaviLatLng endLatLng;
    private NaviLatLng locLatLng;
    private int type;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vsf2f.f2f.ui.map.NaviBaseActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_navi_basic);
        this.mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        this.mAMapNaviView.onCreate(savedInstanceState);
        this.mAMapNaviView.setAMapNaviViewListener(this);
        this.type = getIntent().getIntExtra("navi_type", 0);
        this.locLatLng = new NaviLatLng(getIntent().getDoubleExtra("loc_latitude", 0.0d), getIntent().getDoubleExtra("loc_longitude", 0.0d));
        this.endLatLng = new NaviLatLng(getIntent().getDoubleExtra("end_latitude", 0.0d), getIntent().getDoubleExtra("end_longitude", 0.0d));
        if (this.type == 1) {
            AMapNaviViewOptions options = new AMapNaviViewOptions();
            options.setTilt(0);
            this.mAMapNaviView.setViewOptions(options);
            this.mAMapNaviView.setNaviMode(0);
            return;
        }
        this.mAMapNaviView.setNaviMode(1);
    }

    @Override // com.vsf2f.f2f.ui.map.NaviBaseActivity, com.amap.api.navi.AMapNaviListener
    public void onInitNaviSuccess() {
        super.onInitNaviSuccess();
        if (this.type == 1) {
            int strategy = 0;
            try {
                strategy = this.mAMapNavi.strategyConvert(true, false, false, false, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<NaviLatLng> sList = new ArrayList<>();
            List<NaviLatLng> eList = new ArrayList<>();
            sList.add(this.locLatLng);
            eList.add(this.endLatLng);
            this.mAMapNavi.calculateDriveRoute(sList, eList, this.mWayPointList, strategy);
            return;
        }
        this.mAMapNavi.calculateWalkRoute(this.locLatLng, this.endLatLng);
    }

    @Override // com.vsf2f.f2f.ui.map.NaviBaseActivity, com.amap.api.navi.AMapNaviListener
    public void onCalculateRouteSuccess() {
        super.onCalculateRouteSuccess();
        this.mAMapNavi.startNavi(1);
    }
}
