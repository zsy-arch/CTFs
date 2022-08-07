package com.amap.api.col;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import com.amap.api.col.ct;
import com.amap.api.col.du;
import java.lang.ref.WeakReference;

/* compiled from: ImageWorker.java */
/* loaded from: classes.dex */
public abstract class dx {
    private du a;
    private du.a b;
    protected Resources d;
    private boolean e = false;
    protected boolean c = false;
    private final Object f = new Object();

    protected abstract Bitmap a(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public dx(Context context) {
        this.d = context.getResources();
    }

    public void a(boolean z, ct.b bVar) {
        if (bVar != null) {
            Bitmap bitmap = null;
            try {
                if (this.a != null) {
                    bitmap = this.a.a(bVar.a + "-" + bVar.b + "-" + bVar.c);
                }
                if (bitmap != null) {
                    bVar.a(bitmap);
                    return;
                }
                a aVar = new a(bVar);
                bVar.j = aVar;
                aVar.a(db.c, Boolean.valueOf(z));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(du.a aVar) {
        this.b = aVar;
        this.a = du.a(this.b);
        new b().c(1);
    }

    protected du a() {
        return this.a;
    }

    public static void a(ct.b bVar) {
        a c = c(bVar);
        if (c != null) {
            c.a(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static a c(ct.b bVar) {
        if (bVar != null) {
            return bVar.j;
        }
        return null;
    }

    /* compiled from: ImageWorker.java */
    /* loaded from: classes.dex */
    public class a extends db<Boolean, Void, Bitmap> {
        private final WeakReference<ct.b> e;

        public a(ct.b bVar) {
            this.e = new WeakReference<>(bVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Bitmap a(Boolean... boolArr) {
            Bitmap bitmap;
            try {
                boolean booleanValue = boolArr[0].booleanValue();
                ct.b bVar = this.e.get();
                if (bVar == null) {
                    return null;
                }
                String str = bVar.a + "-" + bVar.b + "-" + bVar.c;
                synchronized (dx.this.f) {
                    while (dx.this.c && !d()) {
                        dx.this.f.wait();
                    }
                }
                if (dx.this.a == null || d() || e() == null || dx.this.e) {
                    bitmap = null;
                } else {
                    bitmap = dx.this.a.b(str);
                }
                Bitmap a = (!booleanValue || bitmap != null || d() || e() == null || dx.this.e) ? bitmap : dx.this.a((Object) bVar);
                if (a == null || dx.this.a == null) {
                    return a;
                }
                dx.this.a.a(str, a);
                return a;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void a(Bitmap bitmap) {
            try {
                if (d() || dx.this.e) {
                    bitmap = null;
                }
                ct.b e = e();
                if (bitmap != null && !bitmap.isRecycled() && e != null) {
                    e.a(bitmap);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void b(Bitmap bitmap) {
            super.b((a) bitmap);
            synchronized (dx.this.f) {
                dx.this.f.notifyAll();
            }
        }

        private ct.b e() {
            ct.b bVar = this.e.get();
            if (this == dx.c(bVar)) {
                return bVar;
            }
            return null;
        }
    }

    public void a(boolean z) {
        synchronized (this.f) {
            this.c = z;
            if (!this.c) {
                this.f.notifyAll();
            }
        }
    }

    /* compiled from: ImageWorker.java */
    /* loaded from: classes.dex */
    protected class b extends db<Object, Void, Void> {
        protected b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: d */
        public Void a(Object... objArr) {
            try {
                switch (((Integer) objArr[0]).intValue()) {
                    case 0:
                        dx.this.c();
                        break;
                    case 1:
                        dx.this.b();
                        break;
                    case 2:
                        dx.this.d();
                        break;
                    case 3:
                        dx.this.e();
                        break;
                }
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    protected void b() {
        if (this.a != null) {
            this.a.a();
        }
    }

    protected void c() {
        if (this.a != null) {
            this.a.b();
        }
    }

    protected void d() {
        if (this.a != null) {
            this.a.c();
        }
    }

    protected void e() {
        if (this.a != null) {
            this.a.d();
            this.a = null;
        }
    }

    public void f() {
        new b().c(0);
    }

    public void g() {
        new b().c(3);
    }
}
