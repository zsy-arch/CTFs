package com.amap.api.services.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.amap.api.services.a.ax;

/* compiled from: ManifestConfig.java */
/* loaded from: classes.dex */
public class o {
    public static be a;
    private static o b;
    private static Context c;
    private a d;
    private HandlerThread e = new HandlerThread("manifestThread") { // from class: com.amap.api.services.a.o.1
        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("ManifestConfigThread");
            Message message = new Message();
            try {
                ax.a a2 = ax.a(o.c, h.a(false), "11K;001", null);
                if (!(a2 == null || a2.p == null)) {
                    message.obj = new p(a2.p.b, a2.p.a);
                }
                if (!(a2 == null || a2.q == null)) {
                    ax.a.d dVar = a2.q;
                    if (dVar != null) {
                        String str = dVar.b;
                        String str2 = dVar.a;
                        String str3 = dVar.c;
                        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
                            new cd(o.c, null, h.a(false)).a();
                        } else {
                            new cd(o.c, new ce(str2, str, str3), h.a(false)).a();
                        }
                    } else {
                        new cd(o.c, null, h.a(false)).a();
                    }
                }
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                message.what = 3;
                if (o.this.d != null) {
                    o.this.d.sendMessage(message);
                }
            }
        }
    };

    private o(Context context) {
        c = context;
        a = h.a(false);
        try {
            this.d = new a(Looper.getMainLooper());
            this.e.start();
        } catch (Throwable th) {
            i.a(th, "ManifestConfig", "ManifestConfig");
        }
    }

    public static o a(Context context) {
        if (b == null) {
            b = new o(context);
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ManifestConfig.java */
    /* loaded from: classes.dex */
    public class a extends Handler {
        String a = "handleMessage";

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Looper looper) {
            super(looper);
            o.this = r2;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message != null) {
                switch (message.what) {
                    case 3:
                        try {
                            p pVar = (p) message.obj;
                            if (pVar == null) {
                                pVar = new p(false, false);
                            }
                            bk.a(o.c, h.a(pVar.a()));
                            o.a = h.a(pVar.a());
                            return;
                        } catch (Throwable th) {
                            i.a(th, "ManifestConfig", this.a);
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }
}
