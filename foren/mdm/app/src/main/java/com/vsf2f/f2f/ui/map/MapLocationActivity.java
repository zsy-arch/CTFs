package com.vsf2f.f2f.ui.map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.LocationUtils;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.MyToast;
import com.tencent.smtt.sdk.TbsReaderView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.MapChoiceDialog;
import com.vsf2f.f2f.ui.utils.AmapUtils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class MapLocationActivity extends BaseActivity implements AMap.OnMyLocationChangeListener {
    private MapChoiceDialog choiceDialog;
    private String cus_address;
    private LatLng endLatLng;
    private ImageView img_toGps;
    private LatLng locLatLng;
    private AMap mAMap;
    private TextureMapView mMapView;
    private TextView txt_address;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_location_map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHeaderBackTxt(R.string.location_message, 0);
        this.mMapView = (TextureMapView) getView(R.id.bmapView);
        this.mMapView.onCreate(savedInstanceState);
        this.txt_address = (TextView) getView(R.id.map_txtAddress);
        this.img_toGps = (ImageView) getViewAndClick(R.id.map_imgToGps);
        if (getIntent().hasExtra("end_latitude")) {
            this.endLatLng = new LatLng(getIntent().getDoubleExtra("end_latitude", 0.0d), getIntent().getDoubleExtra("end_longitude", 0.0d));
        } else {
            this.endLatLng = new LatLng(0.0d, 0.0d);
            setHeaderRightTxt(R.string.button_send);
        }
        initMap();
        setTxtAddress(getIntent().getStringExtra("cus_address"));
    }

    public void setTxtAddress(String strAddress) {
        if (!TextUtils.isEmpty(strAddress)) {
            this.txt_address.setVisibility(0);
            this.txt_address.setText(strAddress);
            return;
        }
        this.txt_address.setVisibility(8);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
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
            case R.id.map_imgToGps /* 2131755366 */:
                showChoiceDialog();
                return;
            default:
                return;
        }
    }

    private void showChoiceDialog() {
        if (this.choiceDialog == null) {
            this.choiceDialog = new MapChoiceDialog(this);
            this.choiceDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.map.MapLocationActivity.1
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    switch (flag) {
                        case 11:
                            MapLocationActivity.this.toMyGps(0);
                            return;
                        case 12:
                            MapLocationActivity.this.toMyGps(1);
                            return;
                        case 21:
                            ChoiceMapUtils.toMap(MapLocationActivity.this, MapLocationActivity.this.locLatLng, MapLocationActivity.this.endLatLng, MapLocationActivity.this.cus_address, 0);
                            return;
                        case 22:
                            ChoiceMapUtils.toMap(MapLocationActivity.this, MapLocationActivity.this.locLatLng, MapLocationActivity.this.endLatLng, MapLocationActivity.this.cus_address, 1);
                            return;
                        default:
                            return;
                    }
                }
            });
        }
        this.choiceDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toMyGps(int type) {
        if (this.locLatLng == null || this.locLatLng.latitude == 0.0d) {
            MyToast.show(this.context, "定位当前位置失败");
            return;
        }
        Intent intent = new Intent(this.context, NaviWalkActivity.class);
        intent.putExtra("navi_type", type);
        intent.putExtra("loc_latitude", this.locLatLng.latitude);
        intent.putExtra("loc_longitude", this.locLatLng.longitude);
        intent.putExtra("end_latitude", this.endLatLng.latitude);
        intent.putExtra("end_longitude", this.endLatLng.longitude);
        startActivity(intent);
    }

    private void initMap() {
        if (this.mAMap == null) {
            this.mAMap = this.mMapView.getMap();
            this.mAMap.getUiSettings().setRotateGesturesEnabled(false);
            this.mAMap.getUiSettings().setScaleControlsEnabled(false);
            this.mAMap.getUiSettings().setZoomControlsEnabled(false);
        }
        if (this.endLatLng.latitude == 0.0d) {
            this.mAMap.moveCamera(CameraUpdateFactory.zoomBy(4.0f));
            AmapUtils.setLocationStyle(this.context, this.mAMap, 0, 1);
            this.mAMap.setOnMyLocationChangeListener(this);
            this.mAMap.setMyLocationEnabled(true);
            this.img_toGps.setVisibility(8);
            AmapUtils.setLocationStyle(this, this.mAMap, R.drawable.ease_icon_marka, 1);
            return;
        }
        AmapUtils.setLocationStyle(this.context, this.mAMap, 0, 0);
        this.mAMap.setOnMyLocationChangeListener(this);
        this.mAMap.setMyLocationEnabled(true);
        this.mAMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() { // from class: com.vsf2f.f2f.ui.map.MapLocationActivity.2
            @Override // com.amap.api.maps.AMap.OnMapLoadedListener
            public void onMapLoaded() {
                MapLocationActivity.this.changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(MapLocationActivity.this.endLatLng, 15.0f, 20.0f, 0.0f)));
                MapLocationActivity.this.mAMap.clear();
                MapLocationActivity.this.mAMap.addMarker(new MarkerOptions().position(MapLocationActivity.this.endLatLng).icon(BitmapDescriptorFactory.defaultMarker(240.0f)));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeCamera(CameraUpdate update) {
        this.mAMap.moveCamera(update);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mMapView.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mMapView.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mMapView.onDestroy();
    }

    @Override // com.amap.api.maps.AMap.OnMyLocationChangeListener
    public void onMyLocationChange(Location location) {
        this.locLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        this.cus_address = LocationUtils.locationToAmap(location.toString()).getAddress();
        setTxtAddress(this.cus_address);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        if (this.locLatLng == null) {
            MyToast.show(this.context, "定位当前位置失败");
        } else {
            getImgBitmap();
        }
    }

    public void getImgBitmap() {
        this.mMapView.getMap().getMapScreenShot(new AMap.OnMapScreenShotListener() { // from class: com.vsf2f.f2f.ui.map.MapLocationActivity.3
            @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
            public void onMapScreenShot(Bitmap bitmap) {
                int w = bitmap.getWidth();
                int height = (w * 57) / 100;
                Bitmap result = Bitmap.createBitmap(bitmap, 0, (bitmap.getHeight() - height) / 2, w, height);
                String filePath = FolderUtil.getCachePathCrop() + "/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png";
                try {
                    FileOutputStream fos2 = new FileOutputStream(filePath);
                    result.compress(Bitmap.CompressFormat.PNG, 100, fos2);
                    try {
                        fos2.flush();
                        fos2.close();
                        bitmap.recycle();
                        result.recycle();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
                Intent intent = MapLocationActivity.this.getIntent();
                intent.putExtra("loc_latitude", MapLocationActivity.this.locLatLng.latitude);
                intent.putExtra("loc_longitude", MapLocationActivity.this.locLatLng.longitude);
                intent.putExtra("cus_address", MapLocationActivity.this.cus_address);
                intent.putExtra(TbsReaderView.KEY_FILE_PATH, filePath);
                intent.putExtra("zoomLevel", "13");
                intent.putExtra("scale", "2");
                MapLocationActivity.this.setResult(-1, intent);
                MapLocationActivity.this.finish();
            }

            @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
            public void onMapScreenShot(Bitmap bitmap, int status) {
            }
        });
    }
}
