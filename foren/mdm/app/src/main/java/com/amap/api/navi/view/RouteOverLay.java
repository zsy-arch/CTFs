package com.amap.api.navi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.internal.view.SupportMenu;
import com.amap.api.col.fn;
import com.amap.api.col.fo;
import com.amap.api.col.gr;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.NavigateArrow;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.navi.AMapNaviException;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.model.RouteOverlayOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* loaded from: classes.dex */
public class RouteOverLay {
    private AMap aMap;
    private Bitmap endBitmap;
    private BitmapDescriptor endBitmapDescriptor;
    private Marker endMarker;
    private Context mContext;
    private Polyline mDefaultPolyline;
    private List<LatLng> mLatLngsOfPath;
    private float mWidth;
    private Bitmap startBitmap;
    private BitmapDescriptor startBitmapDescriptor;
    private Marker startMarker;
    private Bitmap wayBitmap;
    private List<Marker> wayMarkers;
    private BitmapDescriptor wayPointBitmapDescriptor;
    private BitmapDescriptor arrowOnRoute = null;
    private BitmapDescriptor normalRoute = null;
    private BitmapDescriptor unknownTraffic = null;
    private BitmapDescriptor smoothTraffic = null;
    private BitmapDescriptor slowTraffic = null;
    private BitmapDescriptor jamTraffic = null;
    private BitmapDescriptor veryJamTraffic = null;
    private List<Polyline> mTrafficColorfulPolylines = new ArrayList();
    private RouteOverlayOptions mRouteOverlayOptions = null;
    private AMapNaviPath mAMapNaviPath = null;
    private Polyline guideLink = null;
    private List<Circle> gpsCircles = null;
    private boolean emulateGPSLocationVisibility = true;
    private NavigateArrow naviArrow = null;
    private boolean isTrafficLine = true;
    private List<Polyline> mCustomPolylines = new ArrayList();
    private int arrowColor = Color.parseColor("#4DF6CC");

    public RouteOverLay(AMap aMap, AMapNaviPath aMapNaviPath, Context context) {
        this.mWidth = 40.0f;
        this.mContext = context;
        this.mWidth = fn.a(context, 22);
        init(aMap, aMapNaviPath);
    }

    public float getWidth() {
        return this.mWidth;
    }

    public void setWidth(float f) throws AMapNaviException {
        if (f > 0.0f) {
            this.mWidth = f;
        }
    }

    public RouteOverlayOptions getRouteOverlayOptions() {
        return this.mRouteOverlayOptions;
    }

    public void setRouteOverlayOptions(RouteOverlayOptions routeOverlayOptions) {
        this.mRouteOverlayOptions = routeOverlayOptions;
        if (!(routeOverlayOptions == null || routeOverlayOptions.getNormalRoute() == null)) {
            this.normalRoute = BitmapDescriptorFactory.fromBitmap(routeOverlayOptions.getNormalRoute());
        }
        if (!(routeOverlayOptions == null || routeOverlayOptions.getArrowOnTrafficRoute() == null)) {
            this.arrowOnRoute = BitmapDescriptorFactory.fromBitmap(routeOverlayOptions.getArrowOnTrafficRoute());
        }
        if (!(routeOverlayOptions == null || routeOverlayOptions.getUnknownTraffic() == null)) {
            this.unknownTraffic = BitmapDescriptorFactory.fromBitmap(routeOverlayOptions.getUnknownTraffic());
        }
        if (!(routeOverlayOptions == null || routeOverlayOptions.getSmoothTraffic() == null)) {
            this.smoothTraffic = BitmapDescriptorFactory.fromBitmap(routeOverlayOptions.getSmoothTraffic());
        }
        if (!(routeOverlayOptions == null || routeOverlayOptions.getSlowTraffic() == null)) {
            this.slowTraffic = BitmapDescriptorFactory.fromBitmap(routeOverlayOptions.getSlowTraffic());
        }
        if (!(routeOverlayOptions == null || routeOverlayOptions.getJamTraffic() == null)) {
            this.jamTraffic = BitmapDescriptorFactory.fromBitmap(routeOverlayOptions.getJamTraffic());
        }
        if (!(routeOverlayOptions == null || routeOverlayOptions.getVeryJamTraffic() == null)) {
            this.veryJamTraffic = BitmapDescriptorFactory.fromBitmap(routeOverlayOptions.getVeryJamTraffic());
        }
        if (routeOverlayOptions != null && routeOverlayOptions.getLineWidth() > 0.0f) {
            this.mWidth = routeOverlayOptions.getLineWidth();
        }
        if (routeOverlayOptions != null && routeOverlayOptions.getArrowColor() != this.arrowColor) {
            this.arrowColor = routeOverlayOptions.getArrowColor();
        }
    }

    public AMapNaviPath getAMapNaviPath() {
        return this.mAMapNaviPath;
    }

    public void setAMapNaviPath(AMapNaviPath aMapNaviPath) {
        this.mAMapNaviPath = aMapNaviPath;
    }

    @Deprecated
    public void setRouteInfo(AMapNaviPath aMapNaviPath) {
        this.mAMapNaviPath = aMapNaviPath;
    }

    private void init(AMap aMap, AMapNaviPath aMapNaviPath) {
        try {
            this.aMap = aMap;
            this.mAMapNaviPath = aMapNaviPath;
            this.normalRoute = BitmapDescriptorFactory.fromAsset("custtexture.png");
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "RouteOverLay", "init(AMap amap, AMapNaviPath aMapNaviPath)");
        }
        this.arrowOnRoute = BitmapDescriptorFactory.fromAsset("custtexture_aolr.png");
        this.smoothTraffic = BitmapDescriptorFactory.fromAsset("custtexture_green.png");
        this.unknownTraffic = BitmapDescriptorFactory.fromAsset("custtexture_no.png");
        this.slowTraffic = BitmapDescriptorFactory.fromAsset("custtexture_slow.png");
        this.jamTraffic = BitmapDescriptorFactory.fromAsset("custtexture_bad.png");
        this.veryJamTraffic = BitmapDescriptorFactory.fromAsset("custtexture_grayred.png");
    }

    public void addToMap() {
        List<NaviLatLng> list;
        LatLng latLng;
        LatLng latLng2;
        Marker addMarker;
        try {
            if (this.aMap != null) {
                if (this.mDefaultPolyline != null) {
                    this.mDefaultPolyline.remove();
                    this.mDefaultPolyline = null;
                }
                if (!(this.mWidth == 0.0f || this.mAMapNaviPath == null)) {
                    if (this.naviArrow != null) {
                        this.naviArrow.setVisible(false);
                    }
                    List<NaviLatLng> coordList = this.mAMapNaviPath.getCoordList();
                    if (coordList != null) {
                        this.mLatLngsOfPath = new ArrayList(coordList.size());
                        for (NaviLatLng naviLatLng : coordList) {
                            this.mLatLngsOfPath.add(new LatLng(naviLatLng.getLatitude(), naviLatLng.getLongitude(), false));
                        }
                        if (this.mLatLngsOfPath.size() != 0) {
                            clearTrafficLineAndInvisibleOriginalLine();
                            this.mDefaultPolyline = this.aMap.addPolyline(new PolylineOptions().addAll(this.mLatLngsOfPath).setCustomTexture(this.normalRoute).width(this.mWidth - 5.0f));
                            this.mDefaultPolyline.setVisible(true);
                            if (this.mAMapNaviPath.getStartPoint() == null || this.mAMapNaviPath.getEndPoint() == null) {
                                list = null;
                                latLng = null;
                                latLng2 = null;
                            } else {
                                LatLng latLng3 = new LatLng(this.mAMapNaviPath.getStartPoint().getLatitude(), this.mAMapNaviPath.getStartPoint().getLongitude());
                                LatLng latLng4 = new LatLng(this.mAMapNaviPath.getEndPoint().getLatitude(), this.mAMapNaviPath.getEndPoint().getLongitude());
                                list = this.mAMapNaviPath.getWayPoint();
                                latLng = latLng4;
                                latLng2 = latLng3;
                            }
                            if (this.startMarker != null) {
                                this.startMarker.remove();
                                this.startMarker = null;
                            }
                            if (this.endMarker != null) {
                                this.endMarker.remove();
                                this.endMarker = null;
                            }
                            if (this.wayMarkers != null && this.wayMarkers.size() > 0) {
                                for (int i = 0; i < this.wayMarkers.size(); i++) {
                                    Marker marker = this.wayMarkers.get(i);
                                    if (marker != null) {
                                        marker.remove();
                                    }
                                }
                            }
                            if (this.startBitmap == null) {
                                this.startMarker = this.aMap.addMarker(new MarkerOptions().position(latLng2).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313534))));
                            } else if (this.startBitmapDescriptor != null) {
                                this.startMarker = this.aMap.addMarker(new MarkerOptions().position(latLng2).icon(this.startBitmapDescriptor));
                            }
                            if (list != null && list.size() > 0) {
                                int size = list.size();
                                if (this.wayMarkers == null) {
                                    this.wayMarkers = new ArrayList(size);
                                }
                                for (NaviLatLng naviLatLng2 : list) {
                                    LatLng latLng5 = new LatLng(naviLatLng2.getLatitude(), naviLatLng2.getLongitude());
                                    if (this.wayBitmap == null) {
                                        addMarker = this.aMap.addMarker(new MarkerOptions().position(latLng5).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313542))));
                                    } else {
                                        addMarker = this.wayPointBitmapDescriptor != null ? this.aMap.addMarker(new MarkerOptions().position(latLng5).icon(this.wayPointBitmapDescriptor)) : null;
                                    }
                                    this.wayMarkers.add(addMarker);
                                }
                            }
                            if (this.endBitmap == null) {
                                this.endMarker = this.aMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313426))));
                            } else if (this.endBitmapDescriptor != null) {
                                this.endMarker = this.aMap.addMarker(new MarkerOptions().position(latLng).icon(this.endBitmapDescriptor));
                            }
                            if (this.isTrafficLine) {
                                setTrafficLine(Boolean.valueOf(this.isTrafficLine));
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "RouteOverLay", "addToMap()");
        }
    }

    public void drawGuideLink(LatLng latLng, LatLng latLng2, boolean z) {
        if (z) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(latLng);
            arrayList.add(latLng2);
            if (this.guideLink == null) {
                this.guideLink = this.aMap.addPolyline(new PolylineOptions().addAll(arrayList).width(this.mWidth / 3.0f).setDottedLine(true));
            } else {
                this.guideLink.setPoints(arrayList);
            }
            this.guideLink.setVisible(true);
        } else if (this.guideLink != null) {
            this.guideLink.setVisible(false);
        }
    }

    public void drawEmulateGPSLocation(Vector<String> vector) {
        try {
            if (this.gpsCircles == null) {
                this.gpsCircles = new ArrayList(vector.size());
            } else {
                for (Circle circle : this.gpsCircles) {
                    circle.remove();
                }
                this.gpsCircles.clear();
            }
            Iterator<String> it = vector.iterator();
            while (it.hasNext()) {
                String[] split = it.next().split(",");
                if (split != null && split.length >= 11) {
                    this.gpsCircles.add(this.aMap.addCircle(new CircleOptions().center(new LatLng(Double.parseDouble(split[0]), Double.parseDouble(split[1]))).radius(1.5d).strokeWidth(0.0f).fillColor(SupportMenu.CATEGORY_MASK)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            gr.b(e, "RouteOverLay", "drawEmulateGPSLocation(Vector<String> gpsData)");
        }
    }

    public void setEmulateGPSLocationVisible() {
        if (this.gpsCircles != null) {
            this.emulateGPSLocationVisibility = !this.emulateGPSLocationVisibility;
            for (Circle circle : this.gpsCircles) {
                circle.setVisible(this.emulateGPSLocationVisibility);
            }
        }
    }

    public void setStartPointBitmap(Bitmap bitmap) {
        this.startBitmap = bitmap;
        if (this.startBitmap != null) {
            this.startBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(this.startBitmap);
        }
    }

    public void setWayPointBitmap(Bitmap bitmap) {
        this.wayBitmap = bitmap;
        if (this.wayBitmap != null) {
            this.wayPointBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(this.wayBitmap);
        }
    }

    public void setEndPointBitmap(Bitmap bitmap) {
        this.endBitmap = bitmap;
        if (this.endBitmap != null) {
            this.endBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(this.endBitmap);
        }
    }

    public void removeFromMap() {
        try {
            if (this.mDefaultPolyline != null) {
                this.mDefaultPolyline.setVisible(false);
            }
            if (this.startMarker != null) {
                this.startMarker.setVisible(false);
            }
            if (this.wayMarkers != null) {
                for (Marker marker : this.wayMarkers) {
                    marker.setVisible(false);
                }
            }
            if (this.endMarker != null) {
                this.endMarker.setVisible(false);
            }
            if (this.naviArrow != null) {
                this.naviArrow.remove();
            }
            if (this.guideLink != null) {
                this.guideLink.setVisible(false);
            }
            if (this.gpsCircles != null) {
                for (Circle circle : this.gpsCircles) {
                    circle.setVisible(false);
                }
            }
            clearTrafficLineAndInvisibleOriginalLine();
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "RouteOverLay", "removeFromMap()");
        }
    }

    private void clearTrafficLineAndInvisibleOriginalLine() {
        if (this.mTrafficColorfulPolylines.size() > 0) {
            for (int i = 0; i < this.mTrafficColorfulPolylines.size(); i++) {
                if (this.mTrafficColorfulPolylines.get(i) != null) {
                    this.mTrafficColorfulPolylines.get(i).remove();
                }
            }
        }
        this.mTrafficColorfulPolylines.clear();
        if (this.mDefaultPolyline != null) {
            this.mDefaultPolyline.setVisible(false);
        }
        if (this.mCustomPolylines.size() > 0) {
            for (int i2 = 0; i2 < this.mCustomPolylines.size(); i2++) {
                if (this.mCustomPolylines.get(i2) != null) {
                    this.mCustomPolylines.get(i2).setVisible(false);
                }
            }
        }
    }

    private void colorWayUpdate(List<AMapTrafficStatus> list) {
        LatLng latLng;
        int i;
        Polyline addPolyline;
        if (!(this.aMap == null || this.mLatLngsOfPath == null || this.mLatLngsOfPath.size() <= 0 || list == null || list.size() <= 0)) {
            clearTrafficLineAndInvisibleOriginalLine();
            int i2 = 0;
            LatLng latLng2 = this.mLatLngsOfPath.get(0);
            double d = 0.0d;
            ArrayList arrayList = new ArrayList();
            Polyline polyline = null;
            for (int i3 = 0; i3 < this.mLatLngsOfPath.size() && i2 < list.size(); i3 = i + 1) {
                AMapTrafficStatus aMapTrafficStatus = list.get(i2);
                LatLng latLng3 = this.mLatLngsOfPath.get(i3);
                NaviLatLng naviLatLng = new NaviLatLng(latLng2.latitude, latLng2.longitude);
                NaviLatLng naviLatLng2 = new NaviLatLng(latLng3.latitude, latLng3.longitude);
                double a = fn.a(naviLatLng, naviLatLng2);
                d += a;
                if (d > aMapTrafficStatus.getLength() + 1) {
                    NaviLatLng a2 = fn.a(naviLatLng, naviLatLng2, a - (d - aMapTrafficStatus.getLength()));
                    LatLng latLng4 = new LatLng(a2.getLatitude(), a2.getLongitude());
                    arrayList.add(latLng4);
                    i = i3 - 1;
                    latLng = latLng4;
                } else {
                    arrayList.add(latLng3);
                    latLng = latLng3;
                    i = i3;
                }
                if (d >= aMapTrafficStatus.getLength() || i == this.mLatLngsOfPath.size() - 1) {
                    if (i2 == list.size() - 1 && i < this.mLatLngsOfPath.size() - 1) {
                        int i4 = i + 1;
                        while (i4 < this.mLatLngsOfPath.size()) {
                            arrayList.add(this.mLatLngsOfPath.get(i4));
                            i4++;
                        }
                        i = i4;
                    }
                    int i5 = i2 + 1;
                    switch (aMapTrafficStatus.getStatus()) {
                        case 0:
                            addPolyline = this.aMap.addPolyline(new PolylineOptions().addAll(arrayList).width(this.mWidth).setCustomTexture(this.unknownTraffic));
                            break;
                        case 1:
                            addPolyline = this.aMap.addPolyline(new PolylineOptions().addAll(arrayList).width(this.mWidth).setCustomTexture(this.smoothTraffic));
                            break;
                        case 2:
                            addPolyline = this.aMap.addPolyline(new PolylineOptions().addAll(arrayList).width(this.mWidth).setCustomTexture(this.slowTraffic));
                            break;
                        case 3:
                            addPolyline = this.aMap.addPolyline(new PolylineOptions().addAll(arrayList).width(this.mWidth).setCustomTexture(this.jamTraffic));
                            break;
                        case 4:
                            addPolyline = this.aMap.addPolyline(new PolylineOptions().addAll(arrayList).width(this.mWidth).setCustomTexture(this.veryJamTraffic));
                            break;
                        default:
                            addPolyline = polyline;
                            break;
                    }
                    this.mTrafficColorfulPolylines.add(addPolyline);
                    arrayList.clear();
                    arrayList.add(latLng);
                    i2 = i5;
                    d = 0.0d;
                    polyline = addPolyline;
                } else {
                    i = i;
                    i2 = i2;
                }
                latLng2 = latLng;
            }
            this.mTrafficColorfulPolylines.add(this.aMap.addPolyline(new PolylineOptions().addAll(this.mLatLngsOfPath).width(this.mWidth).setCustomTexture(this.arrowOnRoute)));
        }
    }

    public void zoomToSpan() {
        zoomToSpan(100);
    }

    public void zoomToSpan(int i) {
        try {
            if (this.mAMapNaviPath != null) {
                this.aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(this.mAMapNaviPath.getBoundsForPath(), i), 1000L, null);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "RouteOverLay", "zoomToSpan()");
        }
    }

    public void zoomToSpan(int i, AMapNaviPath aMapNaviPath) {
        if (aMapNaviPath != null) {
            try {
                this.aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(aMapNaviPath.getBoundsForPath(), i), 1000L, null);
            } catch (Throwable th) {
                fn.a(th);
                gr.b(th, "RouteOverLay", "zoomToSpan()");
            }
        }
    }

    public void destroy() {
        try {
            if (this.mDefaultPolyline != null) {
                this.mDefaultPolyline.remove();
            }
            this.mAMapNaviPath = null;
            if (this.arrowOnRoute != null) {
                this.arrowOnRoute.recycle();
            }
            if (this.smoothTraffic != null) {
                this.smoothTraffic.recycle();
            }
            if (this.unknownTraffic != null) {
                this.unknownTraffic.recycle();
            }
            if (this.slowTraffic != null) {
                this.slowTraffic.recycle();
            }
            if (this.jamTraffic != null) {
                this.jamTraffic.recycle();
            }
            if (this.veryJamTraffic != null) {
                this.veryJamTraffic.recycle();
            }
            if (this.startBitmap != null) {
                this.startBitmap.recycle();
            }
            if (this.endBitmap != null) {
                this.endBitmap.recycle();
            }
            if (this.wayBitmap != null) {
                this.wayBitmap.recycle();
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "RouteOverLay", "destroy()");
        }
    }

    public void drawArrow(List<NaviLatLng> list) {
        try {
            if (list == null) {
                this.naviArrow.setVisible(false);
                return;
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (NaviLatLng naviLatLng : list) {
                arrayList.add(new LatLng(naviLatLng.getLatitude(), naviLatLng.getLongitude(), false));
            }
            if (this.naviArrow == null) {
                this.naviArrow = this.aMap.addNavigateArrow(new NavigateArrowOptions().addAll(arrayList).topColor(this.arrowColor).width(this.mWidth * 0.4f));
            } else {
                this.naviArrow.setPoints(arrayList);
            }
            this.naviArrow.setZIndex(1.0f);
            this.naviArrow.setVisible(true);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "RouteOverLay", "drawArrow(List<NaviLatLng> list) ");
        }
    }

    public List<NaviLatLng> getArrowPoints(int i) {
        int i2 = 0;
        if (this.mAMapNaviPath == null) {
            return null;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
            gr.b(e, "RouteOverLay", "getArrowPoints(int roadIndex)");
        }
        if (i >= this.mAMapNaviPath.getStepsCount()) {
            return null;
        }
        List<NaviLatLng> coordList = this.mAMapNaviPath.getCoordList();
        int size = coordList.size();
        int endIndex = this.mAMapNaviPath.getSteps().get(i).getEndIndex();
        NaviLatLng naviLatLng = coordList.get(endIndex);
        Vector vector = new Vector();
        int i3 = endIndex - 1;
        int i4 = 0;
        NaviLatLng naviLatLng2 = naviLatLng;
        while (true) {
            if (i3 < 0) {
                break;
            }
            NaviLatLng naviLatLng3 = coordList.get(i3);
            int a = fn.a(naviLatLng2, naviLatLng3);
            i4 += a;
            if (i4 >= 50) {
                vector.add(fn.a(naviLatLng2, naviLatLng3, (50 + a) - i4));
                break;
            }
            vector.add(naviLatLng3);
            i3--;
            naviLatLng2 = naviLatLng3;
        }
        Collections.reverse(vector);
        vector.add(naviLatLng);
        int i5 = endIndex + 1;
        NaviLatLng naviLatLng4 = naviLatLng;
        while (true) {
            if (i5 >= size) {
                break;
            }
            NaviLatLng naviLatLng5 = coordList.get(i5);
            int a2 = fn.a(naviLatLng4, naviLatLng5);
            i2 += a2;
            if (i2 >= 50) {
                vector.add(fn.a(naviLatLng4, naviLatLng5, (50 + a2) - i2));
                break;
            }
            vector.add(naviLatLng5);
            i5++;
            naviLatLng4 = naviLatLng5;
        }
        if (vector.size() > 2) {
            return vector;
        }
        return null;
    }

    public boolean isTrafficLine() {
        return this.isTrafficLine;
    }

    public void setTrafficLine(Boolean bool) {
        try {
            if (this.mContext != null) {
                this.isTrafficLine = bool.booleanValue();
                List<AMapTrafficStatus> list = null;
                clearTrafficLineAndInvisibleOriginalLine();
                if (this.isTrafficLine) {
                    if (this.mAMapNaviPath != null) {
                        list = this.mAMapNaviPath.getTrafficStatuses();
                    }
                    if (list == null || list.size() == 0) {
                        NoTrafficStatusDisplay();
                    } else {
                        colorWayUpdate(list);
                    }
                } else {
                    NoTrafficStatusDisplay();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "RouteOverLay", "setTrafficLine(Boolean enabled)");
        }
    }

    private void NoTrafficStatusDisplay() {
        if (this.mDefaultPolyline != null) {
            this.mDefaultPolyline.setVisible(true);
        }
        if (this.mCustomPolylines.size() > 0) {
            for (int i = 0; i < this.mCustomPolylines.size(); i++) {
                if (this.mCustomPolylines.get(i) != null) {
                    this.mCustomPolylines.get(i).setVisible(true);
                }
            }
        }
    }

    private void addToMap(int[] iArr, int[] iArr2, BitmapDescriptor[] bitmapDescriptorArr) {
        int length;
        LatLng latLng;
        LatLng latLng2;
        List<NaviLatLng> list;
        Polyline polyline;
        try {
            if (this.aMap != null) {
                if (this.mDefaultPolyline != null) {
                    this.mDefaultPolyline.remove();
                    this.mDefaultPolyline = null;
                }
                if (!(this.mWidth == 0.0f || this.mAMapNaviPath == null || this.normalRoute == null)) {
                    if (this.naviArrow != null) {
                        this.naviArrow.setVisible(false);
                    }
                    List<NaviLatLng> coordList = this.mAMapNaviPath.getCoordList();
                    if (coordList != null) {
                        clearTrafficLineAndInvisibleOriginalLine();
                        this.mLatLngsOfPath = new ArrayList(coordList.size());
                        ArrayList arrayList = new ArrayList();
                        if (iArr == null) {
                            length = bitmapDescriptorArr.length;
                        } else {
                            length = iArr.length;
                        }
                        int i = 0;
                        for (int i2 = 0; i2 < length; i2++) {
                            if (iArr2 == null || i2 >= iArr2.length || iArr2[i2] > 0) {
                                arrayList.clear();
                                int i3 = i;
                                while (i3 < coordList.size()) {
                                    NaviLatLng naviLatLng = coordList.get(i3);
                                    LatLng latLng3 = new LatLng(naviLatLng.getLatitude(), naviLatLng.getLongitude(), false);
                                    this.mLatLngsOfPath.add(latLng3);
                                    arrayList.add(latLng3);
                                    if (iArr2 != null && i2 < iArr2.length && i3 == iArr2[i2]) {
                                        break;
                                    }
                                    i3++;
                                }
                                if (bitmapDescriptorArr == null || bitmapDescriptorArr.length == 0) {
                                    polyline = this.aMap.addPolyline(new PolylineOptions().addAll(arrayList).color(iArr[i2]).width(this.mWidth));
                                } else {
                                    polyline = this.aMap.addPolyline(new PolylineOptions().addAll(arrayList).setCustomTexture(bitmapDescriptorArr[i2]).width(this.mWidth));
                                }
                                polyline.setVisible(true);
                                this.mCustomPolylines.add(polyline);
                                i = i3;
                            }
                        }
                        this.mCustomPolylines.add(this.aMap.addPolyline(new PolylineOptions().addAll(this.mLatLngsOfPath).width(this.mWidth).setCustomTexture(this.arrowOnRoute)));
                        if (this.mAMapNaviPath.getStartPoint() == null || this.mAMapNaviPath.getEndPoint() == null) {
                            latLng = null;
                            latLng2 = null;
                            list = null;
                        } else {
                            LatLng latLng4 = new LatLng(this.mAMapNaviPath.getStartPoint().getLatitude(), this.mAMapNaviPath.getStartPoint().getLongitude());
                            LatLng latLng5 = new LatLng(this.mAMapNaviPath.getEndPoint().getLatitude(), this.mAMapNaviPath.getEndPoint().getLongitude());
                            list = this.mAMapNaviPath.getWayPoint();
                            latLng = latLng5;
                            latLng2 = latLng4;
                        }
                        if (this.startMarker != null) {
                            this.startMarker.remove();
                            this.startMarker = null;
                        }
                        if (this.endMarker != null) {
                            this.endMarker.remove();
                            this.endMarker = null;
                        }
                        if (this.wayMarkers != null && this.wayMarkers.size() > 0) {
                            for (int i4 = 0; i4 < this.wayMarkers.size(); i4++) {
                                Marker marker = this.wayMarkers.get(i4);
                                if (marker != null) {
                                    marker.remove();
                                }
                            }
                        }
                        if (this.startBitmap == null) {
                            this.startMarker = this.aMap.addMarker(new MarkerOptions().position(latLng2).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313534))));
                        } else if (this.startBitmapDescriptor != null) {
                            this.startMarker = this.aMap.addMarker(new MarkerOptions().position(latLng2).icon(this.startBitmapDescriptor));
                        }
                        if (list != null && list.size() > 0) {
                            int size = list.size();
                            if (this.wayMarkers == null) {
                                this.wayMarkers = new ArrayList(size);
                            }
                            for (NaviLatLng naviLatLng2 : list) {
                                LatLng latLng6 = new LatLng(naviLatLng2.getLatitude(), naviLatLng2.getLongitude());
                                Marker marker2 = null;
                                if (this.wayBitmap == null) {
                                    marker2 = this.aMap.addMarker(new MarkerOptions().position(latLng6).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313542))));
                                } else if (this.wayPointBitmapDescriptor != null) {
                                    marker2 = this.aMap.addMarker(new MarkerOptions().position(latLng6).icon(this.wayPointBitmapDescriptor));
                                }
                                this.wayMarkers.add(marker2);
                            }
                        }
                        if (this.endBitmap == null) {
                            this.endMarker = this.aMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313426))));
                        } else if (this.endBitmapDescriptor != null) {
                            this.endMarker = this.aMap.addMarker(new MarkerOptions().position(latLng).icon(this.endBitmapDescriptor));
                        }
                        if (this.isTrafficLine) {
                            setTrafficLine(Boolean.valueOf(this.isTrafficLine));
                        }
                    }
                }
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "RouteOverLay", "addToMap(int[] color, int[] index, BitmapDescriptor[] resourceArray)");
        }
    }

    public void addToMap(int[] iArr, int[] iArr2) {
        if (iArr != null && iArr.length != 0) {
            addToMap(iArr, iArr2, null);
        }
    }

    public void addToMap(BitmapDescriptor[] bitmapDescriptorArr, int[] iArr) {
        if (bitmapDescriptorArr != null && bitmapDescriptorArr.length != 0) {
            addToMap(null, iArr, bitmapDescriptorArr);
        }
    }

    public void setTransparency(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        if (this.mDefaultPolyline != null) {
            this.mDefaultPolyline.setTransparency(f);
        }
        for (Polyline polyline : this.mTrafficColorfulPolylines) {
            polyline.setTransparency(f);
        }
    }

    public void setZindex(int i) {
        try {
            if (this.mTrafficColorfulPolylines != null) {
                for (int i2 = 0; i2 < this.mTrafficColorfulPolylines.size(); i2++) {
                    this.mTrafficColorfulPolylines.get(i2).setZIndex(i);
                }
            }
            if (this.mDefaultPolyline != null) {
                this.mDefaultPolyline.setZIndex(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
