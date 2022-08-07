package com.amap.api.col;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.amap.api.col.gb;
import com.amap.api.maps.MapsInitializer;
import org.json.JSONObject;

/* compiled from: AuthTask.java */
/* loaded from: classes.dex */
public class f extends Thread {
    private Context a;
    private k b;

    public f(Context context, k kVar) {
        this.a = context;
        this.b = kVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                gb.a a = gb.a(this.a, dt.e(), "002" + h.b + "11K" + h.b + "001", null);
                if (gb.a != 1) {
                    Message obtainMessage = this.b.getMainHandler().obtainMessage();
                    obtainMessage.what = 2;
                    if (a.a != null) {
                        obtainMessage.obj = a.a;
                    }
                    this.b.getMainHandler().sendMessage(obtainMessage);
                }
                if (a != null) {
                    if (a.f21u != null) {
                        dt.e().a(a.f21u.a);
                    }
                    if (a.w != null) {
                        new gi(this.a, "3dmap", a.w.a, a.w.b).a();
                    }
                }
                a(a);
                if (!(a == null || a.v == null)) {
                    gb.a.d dVar = a.v;
                    if (dVar != null) {
                        String str = dVar.b;
                        String str2 = dVar.a;
                        String str3 = dVar.c;
                        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
                            new hk(this.a, null, dt.e()).a();
                        } else {
                            new hk(this.a, new hl(str2, str, str3), dt.e()).a();
                        }
                    } else {
                        new hk(this.a, null, dt.e()).a();
                    }
                }
                gr.a(this.a, dt.e());
                interrupt();
                this.b.setRunLowFrame(false);
            }
        } catch (Throwable th) {
            interrupt();
            gr.b(th, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th.printStackTrace();
        }
    }

    private void a(gb.a aVar) {
        int i = 500;
        int i2 = 30;
        try {
            gb.a.C0014a aVar2 = aVar.f21u;
            if (aVar2 != null) {
                dr.a(this.a, "maploc", "ue", Boolean.valueOf(aVar2.a));
                JSONObject jSONObject = aVar2.c;
                int optInt = jSONObject.optInt("fn", 1000);
                int optInt2 = jSONObject.optInt("mpn", 0);
                if (optInt2 <= 500) {
                    i = optInt2;
                }
                if (i >= 30) {
                    i2 = i;
                }
                il.a(optInt, gb.a(jSONObject.optString("igu"), false));
                dr.a(this.a, "maploc", "opn", Integer.valueOf(i2));
            }
        } catch (Throwable th) {
            gr.b(th, "AuthUtil", "loadConfigData_uploadException");
        }
    }
}
