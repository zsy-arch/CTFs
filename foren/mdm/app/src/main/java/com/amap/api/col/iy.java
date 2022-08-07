package com.amap.api.col;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

/* compiled from: MapLocationManagerActionHandler.java */
/* loaded from: classes.dex */
public final class iy extends Handler {
    ix a;

    public iy() {
        this.a = null;
    }

    public iy(Looper looper, ix ixVar) {
        super(looper);
        this.a = null;
        this.a = ixVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1001:
                try {
                    this.a.a((Inner_3dMap_locationOption) message.obj);
                    return;
                } catch (Throwable th) {
                    jn.a(th, "ClientActionHandler", "ACTION_SET_OPTION");
                    return;
                }
            case 1002:
                try {
                    this.a.a((Inner_3dMap_locationListener) message.obj);
                    return;
                } catch (Throwable th2) {
                    jn.a(th2, "ClientActionHandler", "ACTION_SET_LISTENER");
                    return;
                }
            case 1003:
                try {
                    this.a.b((Inner_3dMap_locationListener) message.obj);
                    return;
                } catch (Throwable th3) {
                    jn.a(th3, "ClientActionHandler", "ACTION_REMOVE_LISTENER");
                    return;
                }
            case 1004:
                try {
                    this.a.a();
                    return;
                } catch (Throwable th4) {
                    jn.a(th4, "ClientActionHandler", "ACTION_START_LOCATION");
                    return;
                }
            case 1005:
                try {
                    this.a.b();
                    return;
                } catch (Throwable th5) {
                    jn.a(th5, "ClientActionHandler", "ACTION_GET_LOCATION");
                    return;
                }
            case 1006:
                try {
                    this.a.c();
                    return;
                } catch (Throwable th6) {
                    jn.a(th6, "ClientActionHandler", "ACTION_STOP_LOCATION");
                    return;
                }
            case 1007:
                try {
                    this.a.d();
                    return;
                } catch (Throwable th7) {
                    jn.a(th7, "ClientActionHandler", "ACTION_DESTROY");
                    return;
                }
            default:
                return;
        }
    }
}
