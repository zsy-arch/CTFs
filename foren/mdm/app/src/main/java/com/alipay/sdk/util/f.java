package com.alipay.sdk.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alipay.android.app.IAlixPay;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f implements ServiceConnection {
    final /* synthetic */ e a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(e eVar) {
        this.a = eVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.c = null;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Object obj;
        Object obj2;
        obj = this.a.d;
        synchronized (obj) {
            this.a.c = IAlixPay.Stub.asInterface(iBinder);
            obj2 = this.a.d;
            obj2.notify();
        }
    }
}
