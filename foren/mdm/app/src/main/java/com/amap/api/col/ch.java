package com.amap.api.col;

import android.graphics.Rect;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IOverlayImage;

/* compiled from: IOverlayImageDelegate.java */
/* loaded from: classes.dex */
public interface ch extends IOverlayImage {
    void a(k kVar);

    void a(k kVar, float[] fArr, int i, float f);

    void b(boolean z);

    IMarkerAction getIMarkerAction();

    Rect h();

    boolean i();

    boolean isInfoWindowShown();

    boolean j();

    int k();

    boolean l();
}
