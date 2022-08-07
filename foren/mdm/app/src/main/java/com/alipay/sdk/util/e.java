package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.l;

/* loaded from: classes.dex */
public class e {
    public static final String b = "failed";
    public Activity a;
    private IAlixPay c;
    private boolean e;
    private a f;
    private final Object d = IAlixPay.class;
    private ServiceConnection g = new f(this);
    private IRemoteServiceCallback h = new g(this);

    /* loaded from: classes.dex */
    public interface a {
        void a();
    }

    public e(Activity activity, a aVar) {
        this.a = activity;
        this.f = aVar;
    }

    public final String a(String str) {
        l.a a2;
        try {
            a2 = l.a(this.a);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(c.b, c.C, th);
        }
        if (a2.a()) {
            return b;
        }
        if (a2 != null && a2.b > 78) {
            String a3 = l.a();
            Intent intent = new Intent();
            intent.setClassName(a3, "com.alipay.android.app.TransProcessPayActivity");
            this.a.startActivity(intent);
            Thread.sleep(200L);
        }
        return b(str);
    }

    private void a(l.a aVar) throws InterruptedException {
        if (aVar != null && aVar.b > 78) {
            String a2 = l.a();
            Intent intent = new Intent();
            intent.setClassName(a2, "com.alipay.android.app.TransProcessPayActivity");
            this.a.startActivity(intent);
            Thread.sleep(200L);
        }
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.alipay.android.app.IAlixPay, android.content.ServiceConnection, com.alipay.sdk.util.e$a, com.alipay.android.app.IRemoteServiceCallback] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.e.b(java.lang.String):java.lang.String");
    }

    private void a() {
        this.a = null;
    }
}
