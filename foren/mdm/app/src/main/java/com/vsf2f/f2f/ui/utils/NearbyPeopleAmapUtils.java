package com.vsf2f.f2f.ui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.MyApplication;
import com.hy.frame.view.RotundityImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.FriendsListBean;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* loaded from: classes2.dex */
public class NearbyPeopleAmapUtils {
    public static final String ADDRESS = "cus_address";
    public static final String END_LAT = "end_latitude";
    public static final String END_LNG = "end_longitude";
    public static final String LOC_LAT = "loc_latitude";
    public static final String LOC_LNG = "loc_longitude";
    private static AMap aMap;
    private static int clickTaskId;
    private static boolean isScale;
    private static Context serviceContext;
    private static final int FILL_COLOR = Color.argb(0, 0, 0, 180);
    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static Map<String, Marker> markerMap = new HashMap();
    private static Map<String, Marker> markerMapService = new HashMap();
    private static Handler handlerService = new Handler() { // from class: com.vsf2f.f2f.ui.utils.NearbyPeopleAmapUtils.3
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null) {
                NearbyPeopleAmapUtils.paintServiceMarks((Bitmap) msg.obj, msg.arg1);
            }
        }
    };
    private static final List<FriendsListBean.RowsBean> serviceMapList = new ArrayList();

    public static void setLocationStyle(Context context, AMap mAMap, int rId, int styleId) {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        if (rId != 0) {
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(context.getResources(), rId)));
            myLocationStyle.showMyLocation(true);
            myLocationStyle.strokeColor(STROKE_COLOR);
            myLocationStyle.strokeWidth(0.0f);
            myLocationStyle.radiusFillColor(FILL_COLOR);
        }
        myLocationStyle.myLocationType(styleId);
        myLocationStyle.interval(2000L);
        mAMap.setMyLocationStyle(myLocationStyle);
    }

    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    private static boolean checkInScreen(LatLng latLng, AMap aMap2) {
        Point mPoint = aMap2.getProjection().toScreenLocation(latLng);
        return mPoint.x >= 0 && mPoint.x <= MyApplication.getWidthCm() && mPoint.y >= 0 && mPoint.y <= MyApplication.getHeightCm();
    }

    public static void addMarksService(Context context, List<FriendsListBean.RowsBean> serviceList, AMap serviceMap) {
        synchronized (serviceMapList) {
            serviceMapList.clear();
            serviceMapList.addAll(serviceList);
            serviceContext = context;
            aMap = serviceMap;
            isScale = false;
            for (final int i = 0; i < serviceMapList.size(); i++) {
                ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.NearbyPeopleAmapUtils.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NearbyPeopleAmapUtils.getBitmap(i);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getBitmap(int i) {
        Bitmap bitmap;
        if (i < serviceMapList.size()) {
            try {
                bitmap = null;
                try {
                    try {
                        try {
                            bitmap = Glide.with(serviceContext).load(serviceMapList.get(i).getUserPic().getSpath()).asBitmap().into(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
                            if (bitmap == null) {
                                bitmap = BitmapFactory.decodeResource(serviceContext.getResources(), R.mipmap.def_head);
                            }
                            Message msg = new Message();
                            msg.obj = bitmap;
                            msg.arg1 = i;
                            if (handlerService != null) {
                                handlerService.sendMessage(msg);
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                            if (0 == 0) {
                                bitmap = BitmapFactory.decodeResource(serviceContext.getResources(), R.mipmap.def_head);
                            }
                            Message msg2 = new Message();
                            msg2.obj = bitmap;
                            msg2.arg1 = i;
                            if (handlerService != null) {
                                handlerService.sendMessage(msg2);
                            }
                        }
                    } catch (IndexOutOfBoundsException e2) {
                        e2.printStackTrace();
                        if (0 == 0) {
                            bitmap = BitmapFactory.decodeResource(serviceContext.getResources(), R.mipmap.def_head);
                        }
                        Message msg3 = new Message();
                        msg3.obj = bitmap;
                        msg3.arg1 = i;
                        if (handlerService != null) {
                            handlerService.sendMessage(msg3);
                        }
                    }
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                    if (0 == 0) {
                        bitmap = BitmapFactory.decodeResource(serviceContext.getResources(), R.mipmap.def_head);
                    }
                    Message msg4 = new Message();
                    msg4.obj = bitmap;
                    msg4.arg1 = i;
                    if (handlerService != null) {
                        handlerService.sendMessage(msg4);
                    }
                }
            } catch (Throwable th) {
                if (bitmap == null) {
                    bitmap = BitmapFactory.decodeResource(serviceContext.getResources(), R.mipmap.def_head);
                }
                Message msg5 = new Message();
                msg5.obj = bitmap;
                msg5.arg1 = i;
                if (handlerService != null) {
                    handlerService.sendMessage(msg5);
                }
                throw th;
            }
        }
    }

    public static void setNull() {
        serviceContext = null;
        markerMapService.clear();
        markerMap.clear();
    }

    public static void getBitmap(final int i, boolean isClick) {
        isScale = isClick;
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.NearbyPeopleAmapUtils.2
            @Override // java.lang.Runnable
            public void run() {
                if (NearbyPeopleAmapUtils.serviceMapList.size() > i && i > 0) {
                    int unused = NearbyPeopleAmapUtils.clickTaskId = ((FriendsListBean.RowsBean) NearbyPeopleAmapUtils.serviceMapList.get(i)).getId();
                    NearbyPeopleAmapUtils.getBitmap(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void paintServiceMarks(Bitmap bmap, int i) {
        View view;
        if (i < serviceMapList.size() && i >= 0) {
            FriendsListBean.RowsBean detailBean = serviceMapList.get(i);
            LatLng latlng = new LatLng(detailBean.getLat(), detailBean.getLng());
            if (!isScale || clickTaskId != serviceMapList.get(i).getId()) {
                view = LayoutInflater.from(serviceContext).inflate(R.layout.layout_demand_mark, (ViewGroup) null);
            } else {
                view = LayoutInflater.from(serviceContext).inflate(R.layout.layout_demand_mark_big, (ViewGroup) null);
            }
            RelativeLayout rl_headerm = (RelativeLayout) view.findViewById(R.id.iv_headerm);
            RotundityImageView iv_userheadm = (RotundityImageView) view.findViewById(R.id.iv_userheadm);
            if (bmap == null) {
                bmap = BitmapFactory.decodeResource(serviceContext.getResources(), R.mipmap.def_head);
            }
            rl_headerm.setVisibility(0);
            iv_userheadm.setImageBitmap(bmap);
            MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(new WeakReference<>(view)))).position(latlng).draggable(false);
            if (!isScale || clickTaskId != serviceMapList.get(i).getId()) {
                if (markerMapService.containsKey(detailBean.getId() + "")) {
                    markerMapService.get(detailBean.getId() + "").remove();
                    markerMapService.remove(detailBean.getId() + "");
                }
                Marker marker = aMap.addMarker(markerOption);
                marker.setObject(detailBean);
                markerMapService.put(detailBean.getId() + "", marker);
                return;
            }
            Marker marker2 = markerMapService.get(detailBean.getId() + "");
            markerMapService.remove(marker2);
            marker2.remove();
            Marker marker3 = aMap.addMarker(markerOption);
            marker3.setObject(detailBean);
            markerMapService.put(detailBean.getId() + "", marker3);
        }
    }

    private static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void removeMarkByUserId(String userId) {
        markerMap.get(userId).remove();
    }

    public static void removeAll() {
        markerMap.clear();
    }

    public static void removeAllService() {
        markerMapService.clear();
        if (aMap != null) {
            aMap.clear();
        }
    }

    public static void addMarks(List<LatLng> latLngList, AMap aMap2, View view) {
        for (int i = 0; i < latLngList.size(); i++) {
            aMap2.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromView(view)).position(latLngList.get(i)).draggable(true));
        }
    }

    public static Bitmap convertViewToBitmap(WeakReference<View> view) {
        View v = view.get();
        if (v == null) {
            return null;
        }
        v.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.buildDrawingCache();
        return v.getDrawingCache();
    }

    public static void addMarks(LatLng latLng, AMap aMap2) {
        aMap2.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.touxiang_dingwei)).position(latLng).draggable(false));
    }
}
