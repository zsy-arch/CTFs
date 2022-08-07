package com.amap.api.navi;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.amap.api.col.es;
import com.amap.api.col.fn;
import com.amap.api.col.fo;
import com.amap.api.col.hm;
import com.amap.api.maps.AMap;
import com.amap.api.navi.view.DirectionView;
import com.amap.api.navi.view.DriveWayView;
import com.amap.api.navi.view.NextTurnTipView;
import com.amap.api.navi.view.OverviewButtonView;
import com.amap.api.navi.view.TrafficBarView;
import com.amap.api.navi.view.TrafficButtonView;
import com.amap.api.navi.view.ZoomButtonView;
import com.amap.api.navi.view.ZoomInIntersectionView;

/* loaded from: classes.dex */
public class AMapNaviView extends FrameLayout {
    public static final int CAR_UP_MODE = 0;
    public static final int NORTH_UP_MODE = 1;
    private IAMapNaviView core;

    public AMapNaviView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(null);
    }

    public AMapNaviView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(null);
    }

    public AMapNaviView(Context context) {
        super(context);
        init(null);
    }

    public AMapNaviView(Context context, AMapNaviViewOptions aMapNaviViewOptions) {
        super(context);
        init(aMapNaviViewOptions);
    }

    public double getAnchorX() {
        return this.core.getAnchorX();
    }

    public double getAnchorY() {
        return this.core.getAnchorY();
    }

    public int getLockZoom() {
        return this.core.getLockZoom();
    }

    public void setLockZoom(int i) {
        this.core.setLockZoom(i);
    }

    public int getLockTilt() {
        return this.core.getLockTilt();
    }

    public void setLockTilt(int i) {
        this.core.setLockTilt(i);
    }

    public int getNaviMode() {
        return this.core.getNaviMode();
    }

    public void setNaviMode(int i) {
        this.core.setNaviMode(i);
    }

    public boolean isAutoChangeZoom() {
        return this.core.isAutoChangeZoom();
    }

    public AMapNaviViewOptions getViewOptions() {
        return this.core.getViewOptions();
    }

    public void setViewOptions(AMapNaviViewOptions aMapNaviViewOptions) {
        this.core.setViewOptions(aMapNaviViewOptions);
    }

    public AMap getMap() {
        return this.core.getMap();
    }

    private void init(AMapNaviViewOptions aMapNaviViewOptions) {
        try {
            fo.a(getContext().getApplicationContext());
            this.core = (IAMapNaviView) hm.a(getContext(), fn.a(), "com.amap.api.navi.wrapper.AMapNaviViewWrapper", es.class, new Class[]{AMapNaviView.class, AMapNaviViewOptions.class}, new Object[]{this, aMapNaviViewOptions});
        } catch (Throwable th) {
            fn.a(th);
            this.core = new es(this, aMapNaviViewOptions);
        }
        this.core.init();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.core.onConfigurationChanged(configuration);
    }

    public final void onCreate(Bundle bundle) {
        this.core.onCreate(bundle);
    }

    public final void onResume() {
        this.core.onResume();
    }

    public final void onPause() {
        this.core.onPause();
    }

    public final void onDestroy() {
        this.core.onDestroy();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.core.onSaveInstanceState(bundle);
    }

    public void displayOverview() {
        this.core.displayOverview();
    }

    public void openNorthMode() {
        this.core.openNorthMode();
    }

    public void recoverLockMode() {
        this.core.recoverLockMode();
    }

    public boolean isTrafficLine() {
        return this.core.isTrafficLine();
    }

    public void setTrafficLine(boolean z) {
        this.core.setTrafficLine(z);
    }

    public void setAMapNaviViewListener(AMapNaviViewListener aMapNaviViewListener) {
        this.core.setAMapNaviViewListener(aMapNaviViewListener);
    }

    public boolean isShowRoadEnlarge() {
        return this.core.isShowRoadEnlarge();
    }

    public boolean isOrientationLandscape() {
        return this.core.isOrientationLandscape();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.core.layout(z, i, i2, i3, i4);
    }

    public DriveWayView getLazyDriveWayView() {
        return this.core.getLazyDriveWayView();
    }

    public void setLazyDriveWayView(DriveWayView driveWayView) {
        this.core.setLazyDriveWayView(driveWayView);
    }

    public ZoomInIntersectionView getLazyZoomInIntersectionView() {
        return this.core.getLazyZoomInIntersectionView();
    }

    public void setLazyZoomInIntersectionView(ZoomInIntersectionView zoomInIntersectionView) {
        this.core.setLazyZoomInIntersectionView(zoomInIntersectionView);
    }

    public TrafficBarView getLazyTrafficBarView() {
        return this.core.getLazyTrafficBarView();
    }

    public void setLazyTrafficBarView(TrafficBarView trafficBarView) {
        this.core.setLazyTrafficBarView(trafficBarView);
    }

    public DirectionView getLazyDirectionView() {
        return this.core.getLazyDirectionView();
    }

    public void setLazyDirectionView(DirectionView directionView) {
        this.core.setLazyDirectionView(directionView);
    }

    public TrafficButtonView getLazyTrafficButtonView() {
        return this.core.getLazyTrafficButtonView();
    }

    public void setLazyTrafficButtonView(TrafficButtonView trafficButtonView) {
        this.core.setLazyTrafficButtonView(trafficButtonView);
    }

    public NextTurnTipView getLazyNextTurnTipView() {
        return this.core.getLazyNextTurnTipView();
    }

    public void setLazyNextTurnTipView(NextTurnTipView nextTurnTipView) {
        this.core.setLazyNextTurnTipView(nextTurnTipView);
    }

    public void zoomIn() {
        this.core.zoomIn();
    }

    public void zoomOut() {
        this.core.zoomOut();
    }

    public void setLazyZoomButtonView(ZoomButtonView zoomButtonView) {
        this.core.setLazyZoomButtonView(zoomButtonView);
    }

    public void setLazyOverviewButtonView(OverviewButtonView overviewButtonView) {
        this.core.setLazyOverviewButtonView(overviewButtonView);
    }

    public boolean isRouteOverviewNow() {
        return this.core.isRouteOverviewNow();
    }
}
