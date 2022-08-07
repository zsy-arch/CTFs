package com.vsf2f.f2f.ui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.TranslateAnimation;
import com.amap.api.services.core.LatLonPoint;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.MyApplication;
import com.easeui.domain.EaseUser;
import com.hy.frame.view.RotundityImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* loaded from: classes2.dex */
public class AmapUtils {
    public static final String ADDRESS = "cus_address";
    public static final String END_LAT = "end_latitude";
    public static final String END_LNG = "end_longitude";
    public static final String LOC_LAT = "loc_latitude";
    public static final String LOC_LNG = "loc_longitude";
    private static AMap aMap;
    private static int clickTaskId;
    private static Context friendContext;
    private static AMap friendMap;
    private static boolean isScale;
    private static boolean isScaleFri;
    private static Context serviceContext;
    private static final int FILL_COLOR = Color.argb(0, 0, 0, 180);
    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static Map<String, Marker> markerMap = new HashMap();
    private static Map<String, Marker> markerMapService = new HashMap();
    private static final List<EaseUser> friendList = new ArrayList();
    private static Handler handler = new Handler() { // from class: com.vsf2f.f2f.ui.utils.AmapUtils.3
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.obj != null) {
                AmapUtils.paintMarks((EaseUser) msg.obj);
            }
        }
    };
    private static Handler handlerService = new Handler() { // from class: com.vsf2f.f2f.ui.utils.AmapUtils.6
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null) {
                AmapUtils.paintServiceMarks((Bitmap) msg.obj, msg.arg1);
            }
        }
    };
    private static final List<DemandDetailBean> serviceMapList = new ArrayList();

    public static void getLocation(Context context, AMapLocationListener listener) {
        AMapLocationClient mlocationClient = new AMapLocationClient(context);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(listener);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000L);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
    }

    public static void setLocationStyle(Context context, AMap mAMap, int rId, int styleId) {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        if (rId != 0) {
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(context.getResources(), rId)));
            myLocationStyle.strokeColor(STROKE_COLOR);
            myLocationStyle.strokeWidth(0.0f);
            myLocationStyle.radiusFillColor(FILL_COLOR);
        }
        myLocationStyle.myLocationType(styleId);
        myLocationStyle.interval(2000L);
        mAMap.setMyLocationStyle(myLocationStyle);
    }

    public static List<EaseUser> getFriendList() {
        return friendList;
    }

    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    public static synchronized void addMarks(Context context, final List<EaseUser> latLngList, AMap aMap2) {
        synchronized (AmapUtils.class) {
            synchronized (friendList) {
                friendContext = context;
                friendMap = aMap2;
                friendList.clear();
                ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.AmapUtils.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AmapUtils.friendList.addAll(latLngList);
                        for (int i = 0; i < AmapUtils.friendList.size(); i++) {
                            AmapUtils.getFriBitmap((EaseUser) ((EaseUser) AmapUtils.friendList.get(i)).clone());
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getFriBitmap(EaseUser easeUser) {
        Bitmap bitmap;
        if (easeUser != null) {
            try {
                bitmap = null;
                try {
                    try {
                        bitmap = Glide.with(friendContext).load(easeUser.getAvatar()).asBitmap().into(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
                        if (bitmap == null) {
                            bitmap = BitmapFactory.decodeResource(friendContext.getResources(), R.mipmap.def_head);
                        }
                        easeUser.setHeaderBitmap(bitmap);
                        Message msg = new Message();
                        msg.obj = easeUser;
                        handler.sendMessage(msg);
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                        if (0 == 0) {
                            bitmap = BitmapFactory.decodeResource(friendContext.getResources(), R.mipmap.def_head);
                        }
                        easeUser.setHeaderBitmap(bitmap);
                        Message msg2 = new Message();
                        msg2.obj = easeUser;
                        handler.sendMessage(msg2);
                    }
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    if (0 == 0) {
                        bitmap = BitmapFactory.decodeResource(friendContext.getResources(), R.mipmap.def_head);
                    }
                    easeUser.setHeaderBitmap(bitmap);
                    Message msg3 = new Message();
                    msg3.obj = easeUser;
                    handler.sendMessage(msg3);
                } catch (ExecutionException e3) {
                    e3.printStackTrace();
                    if (0 == 0) {
                        bitmap = BitmapFactory.decodeResource(friendContext.getResources(), R.mipmap.def_head);
                    }
                    easeUser.setHeaderBitmap(bitmap);
                    Message msg4 = new Message();
                    msg4.obj = easeUser;
                    handler.sendMessage(msg4);
                }
            } catch (Throwable th) {
                if (bitmap == null) {
                    bitmap = BitmapFactory.decodeResource(friendContext.getResources(), R.mipmap.def_head);
                }
                easeUser.setHeaderBitmap(bitmap);
                Message msg5 = new Message();
                msg5.obj = easeUser;
                handler.sendMessage(msg5);
                throw th;
            }
        }
    }

    public static void getFriBitmap(final int i, boolean isClick) {
        isScaleFri = isClick;
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.AmapUtils.2
            @Override // java.lang.Runnable
            public void run() {
                AmapUtils.getFriBitmap((EaseUser) ((EaseUser) AmapUtils.friendList.get(i)).clone());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void paintMarks(EaseUser easeUser) {
        View view;
        synchronized (AmapUtils.class) {
            LatLng latlng = easeUser.getLatLng();
            if (isScaleFri) {
                view = LayoutInflater.from(friendContext).inflate(R.layout.layout_user_mark_big, (ViewGroup) null);
            } else {
                view = LayoutInflater.from(friendContext).inflate(R.layout.layout_user_mark, (ViewGroup) null);
            }
            RelativeLayout rl_headerw = (RelativeLayout) view.findViewById(R.id.iv_headerw);
            RotundityImageView iv_userheadw = (RotundityImageView) view.findViewById(R.id.iv_userheadw);
            RelativeLayout rl_headerm = (RelativeLayout) view.findViewById(R.id.iv_headerm);
            RotundityImageView iv_userheadm = (RotundityImageView) view.findViewById(R.id.iv_userheadm);
            Bitmap bmap = easeUser.getHeaderBitmap();
            if (bmap == null) {
                bmap = BitmapFactory.decodeResource(friendContext.getResources(), R.mipmap.def_head);
            }
            if ("1".equals(Integer.valueOf(easeUser.getGender()))) {
                rl_headerw.setVisibility(8);
                rl_headerm.setVisibility(0);
                iv_userheadm.setImageBitmap(bmap);
            } else {
                rl_headerw.setVisibility(0);
                rl_headerm.setVisibility(8);
                iv_userheadw.setImageBitmap(bmap);
            }
            MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(new WeakReference<>(view)))).position(latlng).draggable(false);
            if (isScaleFri) {
                Marker marker = markerMap.get(easeUser.getUsername());
                if (marker != null) {
                    markerMap.remove(marker);
                    marker.remove();
                }
                Marker marker2 = friendMap.addMarker(markerOption);
                marker2.setToTop();
                marker2.setObject(easeUser);
                markerMap.put(easeUser.getUsername(), marker2);
            } else {
                if (markerMap.containsKey(easeUser.getUsername())) {
                    markerMap.get(easeUser.getUsername()).remove();
                    markerMap.remove(easeUser.getUsername());
                }
                Marker marker3 = friendMap.addMarker(markerOption);
                marker3.setToTop();
                marker3.setObject(easeUser);
                markerMap.put(easeUser.getUsername(), marker3);
            }
        }
    }

    private static boolean checkInScreen(LatLng latLng, AMap aMap2) {
        Point mPoint = aMap2.getProjection().toScreenLocation(latLng);
        return mPoint.x >= 0 && mPoint.x <= MyApplication.getWidthCm() && mPoint.y >= 0 && mPoint.y <= MyApplication.getHeightCm();
    }

    public static void addMarksService(Context context, List<DemandDetailBean> serviceList, AMap serviceMap) {
        serviceMapList.clear();
        serviceMapList.addAll(serviceList);
        serviceContext = context;
        aMap = serviceMap;
        isScale = false;
        for (final int i = 0; i < serviceMapList.size(); i++) {
            ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.AmapUtils.4
                @Override // java.lang.Runnable
                public void run() {
                    AmapUtils.getBitmap(i);
                }
            });
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
                        bitmap = Glide.with(serviceContext).load(serviceMapList.get(i).getPublishUserObj().getUserPic().getSpath()).asBitmap().into(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
                        if (bitmap == null) {
                            bitmap = BitmapFactory.decodeResource(serviceContext.getResources(), R.mipmap.def_head);
                        }
                        Message msg = new Message();
                        msg.obj = bitmap;
                        msg.arg1 = i;
                        if (handlerService != null) {
                            handlerService.sendMessage(msg);
                        }
                    } catch (IndexOutOfBoundsException e) {
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
                } catch (InterruptedException e2) {
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
                } catch (ExecutionException e3) {
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
        friendContext = null;
        friendList.clear();
        friendMap.clear();
    }

    public static void getBitmap(final int i, boolean isClick) {
        isScale = isClick;
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.AmapUtils.5
            @Override // java.lang.Runnable
            public void run() {
                if (AmapUtils.serviceMapList.size() > i && i > 0) {
                    int unused = AmapUtils.clickTaskId = ((DemandDetailBean) AmapUtils.serviceMapList.get(i)).getMoId();
                    AmapUtils.getBitmap(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void paintServiceMarks(Bitmap bmap, int i) {
        View view;
        if (i < serviceMapList.size() && i >= 0) {
            DemandDetailBean detailBean = serviceMapList.get(i);
            LatLng latlng = new LatLng(detailBean.getLat(), detailBean.getLng());
            if (!isScale || clickTaskId != serviceMapList.get(i).getMoId()) {
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
            if (!isScale || clickTaskId != serviceMapList.get(i).getMoId()) {
                if (markerMapService.containsKey(detailBean.getMoId() + "")) {
                    markerMapService.get(detailBean.getMoId() + "").remove();
                    markerMapService.remove(detailBean.getMoId() + "");
                }
                Marker marker = aMap.addMarker(markerOption);
                marker.setObject(detailBean);
                markerMapService.put(detailBean.getMoId() + "", marker);
                return;
            }
            Marker marker2 = markerMapService.get(detailBean.getMoId() + "");
            markerMapService.remove(marker2);
            marker2.remove();
            Marker marker3 = aMap.addMarker(markerOption);
            marker3.setObject(detailBean);
            markerMapService.put(detailBean.getMoId() + "", marker3);
        }
    }

    public static void removeServiceMarker(String id) {
        if (markerMapService != null) {
            markerMapService.remove(id);
        }
    }

    public static void startJumpAnimation(String userName, int pageType) {
        AMap jumpAmap;
        Context jumpContext;
        Marker screenMarker;
        if (pageType == 0) {
            jumpAmap = friendMap;
            jumpContext = friendContext;
            screenMarker = markerMap.get(userName);
        } else {
            jumpAmap = aMap;
            jumpContext = serviceContext;
            screenMarker = markerMapService.get(userName);
        }
        if (jumpAmap != null) {
            if (screenMarker != null) {
                Point point = jumpAmap.getProjection().toScreenLocation(screenMarker.getPosition());
                point.y -= dip2px(jumpContext, 125.0f);
                Animation animation = new TranslateAnimation(jumpAmap.getProjection().fromScreenLocation(point));
                animation.setInterpolator(new Interpolator() { // from class: com.vsf2f.f2f.ui.utils.AmapUtils.7
                    @Override // android.animation.TimeInterpolator
                    public float getInterpolation(float input) {
                        return ((double) input) <= 0.5d ? (float) (0.5d - ((2.0d * (0.5d - input)) * (0.5d - input))) : (float) (0.5d - Math.sqrt((input - 0.5f) * (1.5f - input)));
                    }
                });
                animation.setDuration(600L);
                screenMarker.setAnimation(animation);
                screenMarker.startAnimation();
                return;
            }
            Log.e("ama", "screenMarker is null");
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
