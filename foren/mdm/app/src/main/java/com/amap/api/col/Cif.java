package com.amap.api.col;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.net.Proxy;

/* compiled from: NetManger.java */
/* renamed from: com.amap.api.col.if  reason: invalid class name */
/* loaded from: classes.dex */
public class Cif extends ia {
    private static Cif a;
    private ip b;
    private Handler c;

    public static Cif b() {
        return a(true, 5);
    }

    public static Cif a(boolean z) {
        return a(z, 5);
    }

    private static synchronized Cif a(boolean z, int i) {
        Cif ifVar;
        synchronized (Cif.class) {
            if (a == null) {
                a = new Cif(z, i);
            } else if (z && a.b == null) {
                a.b = ip.a(i);
            }
            ifVar = a;
        }
        return ifVar;
    }

    private Cif(boolean z, int i) {
        if (z) {
            try {
                this.b = ip.a(i);
            } catch (Throwable th) {
                gr.b(th, "NetManger", "NetManger1");
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
    /* renamed from: com.amap.api.col.if$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends iq {
        final /* synthetic */ ig a;
        final /* synthetic */ ih b;
        final /* synthetic */ Cif c;

        @Override // com.amap.api.col.iq
        public void a() {
            try {
                this.c.a(this.c.b(this.a, false), this.b);
            } catch (fz e) {
                this.c.a(e, this.b);
            }
        }
    }

    @Override // com.amap.api.col.ia
    public byte[] b(ig igVar) throws fz {
        try {
            ii a2 = a(igVar, false);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (fz e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            gr.a().c(th, "NetManager", "makeSyncPostRequest");
            throw new fz("未知的错误");
        }
    }

    public byte[] e(ig igVar) throws fz {
        try {
            ii b = b(igVar, false);
            if (b != null) {
                return b.a;
            }
            return null;
        } catch (fz e) {
            throw e;
        } catch (Throwable th) {
            throw new fz("未知的错误");
        }
    }

    public ii b(ig igVar, boolean z) throws fz {
        Proxy proxy;
        try {
            d(igVar);
            if (igVar.h == null) {
                proxy = null;
            } else {
                proxy = igVar.h;
            }
            return new id(igVar.f, igVar.g, proxy, z).a(igVar.c(), igVar.a(), igVar.b());
        } catch (fz e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new fz("未知的错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(fz fzVar, ih ihVar) {
        ij ijVar = new ij();
        ijVar.a = fzVar;
        ijVar.b = ihVar;
        Message obtain = Message.obtain();
        obtain.obj = ijVar;
        obtain.what = 1;
        this.c.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ii iiVar, ih ihVar) {
        ihVar.a(iiVar.b, iiVar.a);
        ij ijVar = new ij();
        ijVar.b = ihVar;
        Message obtain = Message.obtain();
        obtain.obj = ijVar;
        obtain.what = 0;
        this.c.sendMessage(obtain);
    }

    /* compiled from: NetManger.java */
    /* renamed from: com.amap.api.col.if$a */
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
                        ((ij) message.obj).b.a();
                        break;
                    case 1:
                        ij ijVar = (ij) message.obj;
                        ijVar.b.a(ijVar.a);
                        break;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
