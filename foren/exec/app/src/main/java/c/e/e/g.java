package c.e.e;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
class g implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ k f813a;

    public g(k kVar) {
        this.f813a = kVar;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            this.f813a.a();
            return true;
        } else if (i != 1) {
            return true;
        } else {
            this.f813a.a((Runnable) message.obj);
            return true;
        }
    }
}
