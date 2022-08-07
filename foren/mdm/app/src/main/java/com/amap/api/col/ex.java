package com.amap.api.col;

import android.location.Location;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.model.AMapNaviGuide;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.NaviInfo;
import java.util.List;

/* compiled from: ICommon.java */
/* loaded from: classes.dex */
public interface ex {
    void a();

    void a(int i, double d, double d2);

    void a(int i, Location location);

    void a(AMapNaviListener aMapNaviListener);

    boolean a(int i);

    void b();

    void b(int i);

    void b(AMapNaviListener aMapNaviListener);

    int c(int i);

    NaviInfo c();

    void d(int i);

    void h();

    void i();

    void j();

    AMapNaviPath k();

    List<AMapNaviGuide> l();
}
