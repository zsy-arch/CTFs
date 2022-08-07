package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.tbs.video.interfaces.IUserStateChangedListener;
import com.tencent.tbs.video.interfaces.a;

/* loaded from: classes.dex */
public class p {

    /* renamed from: e  reason: collision with root package name */
    public static p f1389e;

    /* renamed from: a  reason: collision with root package name */
    public C0072r f1390a;

    /* renamed from: b  reason: collision with root package name */
    public Context f1391b;

    /* renamed from: c  reason: collision with root package name */
    public a f1392c;

    /* renamed from: d  reason: collision with root package name */
    public IUserStateChangedListener f1393d;

    public p(Context context) {
        this.f1390a = null;
        this.f1391b = context.getApplicationContext();
        this.f1390a = new C0072r(this.f1391b);
    }

    public static synchronized p a(Context context) {
        p pVar;
        synchronized (p.class) {
            if (f1389e == null) {
                f1389e = new p(context);
            }
            pVar = f1389e;
        }
        return pVar;
    }

    public void a(int i, int i2, Intent intent) {
        a aVar = this.f1392c;
        if (aVar != null) {
            aVar.a(i, i2, intent);
        }
    }

    public void a(Activity activity, int i) {
        this.f1390a.a(activity, i);
    }

    public boolean a() {
        this.f1390a.a();
        return this.f1390a.b();
    }

    public boolean a(String str, Bundle bundle, a aVar) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("videoUrl", str);
        }
        if (aVar != null) {
            this.f1390a.a();
            if (!this.f1390a.b()) {
                return false;
            }
            this.f1392c = aVar;
            this.f1393d = new IUserStateChangedListener() { // from class: com.tencent.smtt.sdk.p.1
                @Override // com.tencent.tbs.video.interfaces.IUserStateChangedListener
                public void onUserStateChanged() {
                    p.this.f1390a.c();
                }
            };
            this.f1392c.a(this.f1393d);
            bundle.putInt("callMode", 3);
        } else {
            bundle.putInt("callMode", 1);
        }
        this.f1390a.a(bundle, aVar == null ? null : this);
        return true;
    }
}
