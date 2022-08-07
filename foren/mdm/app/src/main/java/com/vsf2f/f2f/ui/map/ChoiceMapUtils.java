package com.vsf2f.f2f.ui.map;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amap.api.maps.model.LatLng;
import com.hy.frame.util.MyLog;
import java.net.URISyntaxException;

/* loaded from: classes2.dex */
public class ChoiceMapUtils {
    public static void toMap(Context context, LatLng myLatLng, LatLng toLatLng, String address, int mapType) {
        Intent intent = new Intent();
        if (mapType == 0) {
            try {
                intent = Intent.parseUri("intent://map/direction?origin=latlng:" + myLatLng.latitude + "," + myLatLng.longitude + "|name:&destination=latlng:" + toLatLng.latitude + "," + toLatLng.longitude + "|name:" + address + "&mode=driving&src=Name|AppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end", 0);
            } catch (URISyntaxException e) {
                MyLog.d("URISyntaxException : " + e.getMessage());
                e.printStackTrace();
            }
            context.startActivity(intent);
        } else if (mapType == 1) {
            intent.setData(Uri.parse("androidamap://route?sourceApplication=softname&slat=" + myLatLng.latitude + "&slon=" + myLatLng.longitude + "&dlat=" + toLatLng.latitude + "&dlon=" + toLatLng.longitude + "&dname=" + address + "&dev=0&m=0&t=2"));
            context.startActivity(intent);
        }
    }
}
