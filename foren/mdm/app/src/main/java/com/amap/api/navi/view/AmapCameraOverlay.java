package com.amap.api.navi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.internal.view.SupportMenu;
import android.widget.TextView;
import com.amap.api.col.fn;
import com.amap.api.col.fo;
import com.amap.api.col.fr;
import com.amap.api.col.gr;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AmapCameraOverlay {
    private BitmapDescriptor mBusLeftIcon;
    private BitmapDescriptor mBusRightIcon;
    private BitmapDescriptor mCameraIcon;
    private BitmapDescriptor mCameraLeftIcon;
    private BitmapDescriptor mCameraRightIcon;
    private Context mContext;
    private BitmapDescriptor mRedLeftIcon;
    private BitmapDescriptor mRedRightIcon;
    private BitmapDescriptor mYingjiLeftIcon;
    private BitmapDescriptor mYingjiRightIcon;
    private Map<String, List<Marker>> markerMap = new HashMap();
    private boolean mLastFlag = false;

    public AmapCameraOverlay(Context context) {
        this.mCameraIcon = null;
        this.mBusLeftIcon = null;
        this.mBusRightIcon = null;
        this.mCameraRightIcon = null;
        this.mCameraLeftIcon = null;
        this.mYingjiRightIcon = null;
        this.mYingjiLeftIcon = null;
        this.mRedRightIcon = null;
        this.mRedLeftIcon = null;
        try {
            this.mCameraIcon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313411));
            this.mBusLeftIcon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313416));
            this.mBusRightIcon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313417));
            this.mCameraLeftIcon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313418));
            this.mCameraRightIcon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313419));
            this.mYingjiLeftIcon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313420));
            this.mYingjiRightIcon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313421));
            this.mRedLeftIcon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313422));
            this.mRedRightIcon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313423));
            this.mContext = context;
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "CameraOverlay", "CameraOverlay()");
        }
    }

    public void draw(AMap aMap, AMapNaviCameraInfo aMapNaviCameraInfo, int i) {
        boolean z;
        float f;
        float f2;
        boolean z2 = true;
        if (aMap != null) {
            float f3 = 26.0f;
            if (i > 75 && i < 135) {
                try {
                    f3 = (float) (((i * 1000) / 3600) * 1.35d);
                } catch (Throwable th) {
                    fn.a(th);
                    gr.b(th, "CameraOverlay", "draw(AMap aMap, LatLng latLng)");
                    return;
                }
            }
            String str = aMapNaviCameraInfo.getX() + "-" + aMapNaviCameraInfo.getCameraType() + "-" + aMapNaviCameraInfo.getY();
            if (this.markerMap.keySet().contains(str)) {
                fr.a("CameraOverlay", "key 包含在 map 中,距离摄像头:" + aMapNaviCameraInfo.getCameraDistance() + "米，flag=" + f3);
                if (aMapNaviCameraInfo.getCameraDistance() < f3) {
                    removeAllCamera();
                    if (this.mLastFlag) {
                        z2 = false;
                    }
                    this.mLastFlag = z2;
                }
            } else if (aMapNaviCameraInfo.getCameraDistance() >= f3) {
                if (this.mLastFlag) {
                    this.mLastFlag = false;
                    z = false;
                } else {
                    this.mLastFlag = true;
                    z = true;
                }
                ArrayList arrayList = new ArrayList();
                Marker addMarker = aMap.addMarker(new MarkerOptions().position(new LatLng(aMapNaviCameraInfo.getY(), aMapNaviCameraInfo.getX())).anchor(0.5f, 0.5f).icon(this.mCameraIcon));
                addMarker.setVisible(true);
                arrayList.add(addMarker);
                int cameraType = aMapNaviCameraInfo.getCameraType();
                if (z) {
                    f = 1.0f;
                    f2 = 0.7f;
                } else {
                    f = 0.0f;
                    f2 = 0.7f;
                }
                fr.b("CameraOverlay", "key 不包含在 map 中,摄像头类型=" + cameraType + ",距离:" + aMapNaviCameraInfo.getCameraDistance());
                switch (cameraType) {
                    case 0:
                        TextView textView = new TextView(this.mContext);
                        textView.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView.setGravity(49);
                        int cameraSpeed = aMapNaviCameraInfo.getCameraSpeed();
                        if (cameraSpeed > 99) {
                            textView.setTextSize(20.0f);
                            textView.setPadding(0, 16, 0, 0);
                        } else {
                            textView.setTextSize(24.0f);
                            textView.setPadding(0, 12, 0, 0);
                        }
                        textView.setText(String.valueOf(cameraSpeed));
                        Bitmap decodeResource = BitmapFactory.decodeResource(fo.a(), z ? 1191313424 : 1191313425);
                        if (Build.VERSION.SDK_INT >= 17) {
                            textView.setBackground(new BitmapDrawable(fo.a(), decodeResource));
                        } else {
                            textView.setBackgroundDrawable(new BitmapDrawable(fo.a(), decodeResource));
                        }
                        Marker addMarker2 = aMap.addMarker(new MarkerOptions().position(new LatLng(aMapNaviCameraInfo.getY(), aMapNaviCameraInfo.getX())).anchor(f, f2).icon(BitmapDescriptorFactory.fromView(textView)));
                        addMarker2.setVisible(true);
                        arrayList.add(addMarker2);
                        break;
                    case 1:
                    case 3:
                        Marker addMarker3 = aMap.addMarker(new MarkerOptions().position(new LatLng(aMapNaviCameraInfo.getY(), aMapNaviCameraInfo.getX())).anchor(f, f2).icon(z ? this.mCameraLeftIcon : this.mCameraRightIcon));
                        addMarker3.setVisible(true);
                        arrayList.add(addMarker3);
                        break;
                    case 2:
                        Marker addMarker4 = aMap.addMarker(new MarkerOptions().position(new LatLng(aMapNaviCameraInfo.getY(), aMapNaviCameraInfo.getX())).anchor(f, f2).icon(z ? this.mRedLeftIcon : this.mRedRightIcon));
                        addMarker4.setVisible(true);
                        arrayList.add(addMarker4);
                        break;
                    case 4:
                        Marker addMarker5 = aMap.addMarker(new MarkerOptions().position(new LatLng(aMapNaviCameraInfo.getY(), aMapNaviCameraInfo.getX())).anchor(f, f2).icon(z ? this.mBusLeftIcon : this.mBusRightIcon));
                        addMarker5.setVisible(true);
                        arrayList.add(addMarker5);
                        break;
                    case 5:
                        Marker addMarker6 = aMap.addMarker(new MarkerOptions().position(new LatLng(aMapNaviCameraInfo.getY(), aMapNaviCameraInfo.getX())).anchor(f, f2).icon(z ? this.mYingjiLeftIcon : this.mYingjiRightIcon));
                        addMarker6.setVisible(true);
                        arrayList.add(addMarker6);
                        break;
                }
                this.markerMap.put(str, arrayList);
            }
        }
    }

    public void removeAllCamera() {
        if (this.markerMap != null) {
            for (String str : this.markerMap.keySet()) {
                List<Marker> list = this.markerMap.get(str);
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).remove();
                }
                list.clear();
            }
            this.markerMap.clear();
        }
    }

    public void destroy() {
        try {
            if (this.markerMap != null) {
                removeAllCamera();
                this.markerMap = null;
            }
            if (this.mCameraIcon != null) {
                this.mCameraIcon.recycle();
            }
            if (this.mBusLeftIcon != null) {
                this.mBusLeftIcon.recycle();
                this.mBusLeftIcon = null;
            }
            if (this.mBusRightIcon != null) {
                this.mBusRightIcon.recycle();
                this.mBusRightIcon = null;
            }
            if (this.mCameraRightIcon != null) {
                this.mCameraRightIcon.recycle();
                this.mCameraRightIcon = null;
            }
            if (this.mCameraLeftIcon != null) {
                this.mCameraLeftIcon.recycle();
                this.mCameraLeftIcon = null;
            }
            if (this.mYingjiRightIcon != null) {
                this.mYingjiRightIcon.recycle();
                this.mYingjiRightIcon = null;
            }
            if (this.mYingjiLeftIcon != null) {
                this.mYingjiLeftIcon.recycle();
                this.mYingjiLeftIcon = null;
            }
            if (this.mRedRightIcon != null) {
                this.mRedRightIcon.recycle();
                this.mRedRightIcon = null;
            }
            if (this.mRedLeftIcon != null) {
                this.mRedLeftIcon.recycle();
                this.mRedLeftIcon = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCameraBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            this.mCameraIcon = BitmapDescriptorFactory.fromBitmap(bitmap);
        }
    }

    public void setBusBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != null) {
            this.mBusLeftIcon = BitmapDescriptorFactory.fromBitmap(bitmap);
        }
        if (bitmap2 != null) {
            this.mBusRightIcon = BitmapDescriptorFactory.fromBitmap(bitmap2);
        }
    }

    public void setCameraMoniterBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != null) {
            this.mCameraLeftIcon = BitmapDescriptorFactory.fromBitmap(bitmap);
        }
        if (bitmap2 != null) {
            this.mCameraRightIcon = BitmapDescriptorFactory.fromBitmap(bitmap2);
        }
    }

    public void setEmergencyBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != null) {
            this.mYingjiLeftIcon = BitmapDescriptorFactory.fromBitmap(bitmap);
        }
        if (bitmap2 != null) {
            this.mYingjiRightIcon = BitmapDescriptorFactory.fromBitmap(bitmap2);
        }
    }

    public void setRedLightBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != null) {
            this.mRedLeftIcon = BitmapDescriptorFactory.fromBitmap(bitmap);
        }
        if (bitmap2 != null) {
            this.mRedRightIcon = BitmapDescriptorFactory.fromBitmap(bitmap2);
        }
    }
}
