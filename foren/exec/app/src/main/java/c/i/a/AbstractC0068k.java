package c.i.a;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import c.a.a.C;

/* renamed from: c.i.a.k */
/* loaded from: classes.dex */
public abstract class AbstractC0068k<E> extends AbstractC0066i {

    /* renamed from: a */
    public final Activity f970a;

    /* renamed from: b */
    public final Context f971b;

    /* renamed from: c */
    public final Handler f972c;

    /* renamed from: d */
    public final s f973d = new s();

    public AbstractC0068k(ActivityC0065h hVar) {
        Handler handler = hVar.f962b;
        this.f970a = hVar;
        C.a(hVar, (Object) "context == null");
        this.f971b = hVar;
        C.a(handler, "handler == null");
        this.f972c = handler;
    }
}
