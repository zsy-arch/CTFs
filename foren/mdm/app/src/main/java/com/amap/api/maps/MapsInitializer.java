package com.amap.api.maps;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.col.g;
import com.amap.api.col.gc;
import com.amap.api.col.o;
import com.autonavi.ae.gmap.GLMapEngine;

/* loaded from: classes.dex */
public final class MapsInitializer {
    public static String sdcardDir = "";
    private static boolean a = true;

    public static void initialize(Context context) throws RemoteException {
        if (context != null) {
            o.a = context.getApplicationContext();
        } else {
            Log.w("MapsInitializer", "the context is null");
        }
    }

    public static void setNetWorkEnable(boolean z) {
        a = z;
    }

    public static boolean getNetWorkEnable() {
        return a;
    }

    public static void setApiKey(String str) {
        if (str != null && str.trim().length() > 0) {
            gc.a(str);
        }
    }

    public static String getVersion() {
        return "5.2.1";
    }

    public static void loadWorldGridMap(boolean z) {
        g.c = z ? 0 : 1;
    }

    public static void setBuildingHeight(int i) {
        GLMapEngine.BUILDINGHEIGHT = i;
    }
}
