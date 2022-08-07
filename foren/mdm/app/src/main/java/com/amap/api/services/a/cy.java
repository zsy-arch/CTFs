package com.amap.api.services.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.net.Proxy;

/* compiled from: NetManger.java */
/* loaded from: classes.dex */
public class cy extends ct {
    private static cy a;
    private dh b;
    private Handler c;

    public static cy a(boolean z) {
        return a(z, 5);
    }

    private static synchronized cy a(boolean z, int i) {
        cy cyVar;
        synchronized (cy.class) {
            if (a == null) {
                a = new cy(z, i);
            } else if (z && a.b == null) {
                a.b = dh.a(i);
            }
            cyVar = a;
        }
        return cyVar;
    }

    private cy(boolean z, int i) {
        if (z) {
            try {
                this.b = dh.a(i);
            } catch (Throwable th) {
                bk.b(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.c = new a(Looper.getMainLooper(), null);
        } else {
            this.c = new a();
        }
    }

    /* compiled from: NetManger.java */
    /* renamed from: com.amap.api.services.a.cy$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends di {
        final /* synthetic */ cz a;
        final /* synthetic */ da b;
        final /* synthetic */ cy c;

        @Override // com.amap.api.services.a.di
        public void a() {
            try {
                this.c.a(this.c.b(this.a, false), this.b);
            } catch (av e) {
                this.c.a(e, this.b);
            }
        }
    }

    @Override // com.amap.api.services.a.ct
    public byte[] b(cz czVar) throws av {
        try {
            db a2 = a(czVar, false);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (av e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            bk.a().c(th, "NetManager", "makeSyncPostRequest");
            throw new av("未知的错误");
        }
    }

    public byte[] d(cz czVar) throws av {
        try {
            db b = b(czVar, false);
            if (b != null) {
                return b.a;
            }
            return null;
        } catch (av e) {
            throw e;
        } catch (Throwable th) {
            throw new av("未知的错误");
        }
    }

    public byte[] e(cz czVar) throws av {
        try {
            db b = b(czVar, true);
            if (b != null) {
                return b.a;
            }
            return null;
        } catch (av e) {
            throw e;
        } catch (Throwable th) {
            throw new av("未知的错误");
        }
    }

    public db b(cz czVar, boolean z) throws av {
        Proxy proxy;
        try {
            c(czVar);
            if (czVar.g == null) {
                proxy = null;
            } else {
                proxy = czVar.g;
            }
            return new cw(czVar.e, czVar.f, proxy, z).a(czVar.g(), czVar.c(), czVar.b());
        } catch (av e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new av("未知的错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(av avVar, da daVar) {
        dc dcVar = new dc();
        dcVar.a = avVar;
        dcVar.b = daVar;
        Message obtain = Message.obtain();
        obtain.obj = dcVar;
        obtain.what = 1;
        this.c.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(db dbVar, da daVar) {
        daVar.a(dbVar.b, dbVar.a);
        dc dcVar = new dc();
        dcVar.b = daVar;
        Message obtain = Message.obtain();
        obtain.obj = dcVar;
        obtain.what = 0;
        this.c.sendMessage(obtain);
    }

    /* compiled from: NetManger.java */
    /* loaded from: classes.dex */
    static class a extends Handler {
        /* synthetic */ a(Looper looper, AnonymousClass1 r2) {
            this(looper);
        }

        private a(Looper looper) {
            super(looper);
        }

        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        ((dc) message.obj).b.a();
                        break;
                    case 1:
                        dc dcVar = (dc) message.obj;
                        dcVar.b.a(dcVar.a);
                        break;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
