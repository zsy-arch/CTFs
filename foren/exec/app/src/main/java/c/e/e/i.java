package c.e.e;

import android.os.Handler;
import c.e.e.k;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class i implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ Callable f816a;

    /* renamed from: b */
    public final /* synthetic */ Handler f817b;

    /* renamed from: c */
    public final /* synthetic */ k.a f818c;

    public i(k kVar, Callable callable, Handler handler, k.a aVar) {
        this.f816a = callable;
        this.f817b = handler;
        this.f818c = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        try {
            obj = this.f816a.call();
        } catch (Exception unused) {
            obj = null;
        }
        this.f817b.post(new h(this, obj));
    }
}
