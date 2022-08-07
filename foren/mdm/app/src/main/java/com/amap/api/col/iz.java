package com.amap.api.col;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.autonavi.amap.mapcore.Inner_3dMap_location;

/* compiled from: MapLocationManagerResultHandler.java */
/* loaded from: classes.dex */
public final class iz extends Handler {
    ix a;

    public iz(Looper looper, ix ixVar) {
        super(looper);
        this.a = null;
        this.a = ixVar;
    }

    public iz(ix ixVar) {
        this.a = null;
        this.a = ixVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                try {
                    if (this.a != null) {
                        this.a.a((Inner_3dMap_location) message.obj);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    jn.a(th, "ClientResultHandler", "RESULT_LOCATION_FINISH");
                    return;
                }
            default:
                return;
        }
    }
}
