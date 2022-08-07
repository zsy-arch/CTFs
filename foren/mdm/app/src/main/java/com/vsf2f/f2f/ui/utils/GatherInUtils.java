package com.vsf2f.f2f.ui.utils;

import android.graphics.Point;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.cdlinglu.common.MyApplication;
import com.easeui.domain.EaseUser;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GatherInUtils {
    private static final int column = 12;
    private static List<EaseUser>[][] gather = null;
    private static final int line = 8;
    private static final int iwidth = MyApplication.getWidthCm() / 8;
    private static final int iheight = MyApplication.getHeightCm() / 12;

    public static synchronized List<EaseUser> gather(List<EaseUser> data, AMap aMap) {
        List<EaseUser> result;
        LatLng latlng;
        synchronized (GatherInUtils.class) {
            gather = (List[][]) Array.newInstance(List.class, 8, 12);
            result = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if (!(data.get(i).getVisible() == 0 || (latlng = data.get(i).getLatLng()) == null)) {
                    Point mPoint = aMap.getProjection().toScreenLocation(latlng);
                    if (checkInScreen(mPoint)) {
                        int pointX = mPoint.x / iwidth;
                        int pointY = mPoint.y / iheight;
                        if (gather[pointX][pointY] == null) {
                            gather[pointX][pointY] = new ArrayList();
                        }
                        gather[pointX][pointY].add(data.get(i));
                    }
                }
            }
            for (int i2 = 0; i2 < 8; i2++) {
                for (int j = 0; j < 12; j++) {
                    if (gather[i2][j] != null && gather[i2][j].size() > 0) {
                        result.add(gather[i2][j].get(0));
                    }
                }
            }
        }
        return result;
    }

    private static boolean checkInScreen(Point mPoint) {
        return mPoint.x >= 0 && mPoint.x <= MyApplication.getWidthCm() && mPoint.y >= 0 && mPoint.y <= MyApplication.getHeightCm();
    }

    public static boolean checkInScreen(LatLng latlng, AMap aMap) {
        Point mPoint = aMap.getProjection().toScreenLocation(latlng);
        return mPoint.x >= 0 && mPoint.x <= MyApplication.getWidthCm() && mPoint.y >= 0 && mPoint.y <= MyApplication.getHeightCm();
    }
}
