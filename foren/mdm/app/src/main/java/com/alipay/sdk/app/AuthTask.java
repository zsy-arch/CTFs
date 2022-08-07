package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.data.c;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.l;
import com.alipay.sdk.widget.a;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AuthTask {
    static final Object a = e.class;
    private static final int b = 73;
    private Activity c;
    private a d;

    public AuthTask(Activity activity) {
        this.c = activity;
        b a2 = b.a();
        Activity activity2 = this.c;
        c.a();
        a2.a(activity2);
        com.alipay.sdk.app.statistic.a.a(activity);
        this.d = new a(activity, a.c);
    }

    private e.a a() {
        return new a(this);
    }

    private void b() {
        if (this.d != null) {
            this.d.a();
        }
    }

    public void c() {
        if (this.d != null) {
            this.d.b();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        return j.a(auth(str, z));
    }

    /* JADX WARN: Finally extract failed */
    public synchronized String auth(String str, boolean z) {
        String str2;
        Activity activity;
        String a2;
        if (z) {
            b();
        }
        b a3 = b.a();
        Activity activity2 = this.c;
        c.a();
        a3.a(activity2);
        String a4 = h.a();
        try {
            activity = this.c;
            a2 = new com.alipay.sdk.sys.a(this.c).a(str);
        } catch (Exception e) {
            com.alipay.sdk.data.a.b().a(this.c);
            c();
            com.alipay.sdk.app.statistic.a.a(this.c, str);
            str2 = a4;
        } catch (Throwable th) {
            com.alipay.sdk.data.a.b().a(this.c);
            c();
            com.alipay.sdk.app.statistic.a.a(this.c, str);
            throw th;
        }
        if (a(activity)) {
            str2 = new e(activity, new a(this)).a(a2);
            if (!TextUtils.equals(str2, e.b)) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = h.a();
                }
                com.alipay.sdk.data.a.b().a(this.c);
                c();
                com.alipay.sdk.app.statistic.a.a(this.c, str);
            }
        }
        str2 = b(activity, a2);
        com.alipay.sdk.data.a.b().a(this.c);
        c();
        com.alipay.sdk.app.statistic.a.a(this.c, str);
        return str2;
    }

    private String a(Activity activity, String str) {
        String a2 = new com.alipay.sdk.sys.a(this.c).a(str);
        if (!a(activity)) {
            return b(activity, a2);
        }
        String a3 = new e(activity, new a(this)).a(a2);
        if (TextUtils.equals(a3, e.b)) {
            return b(activity, a2);
        }
        if (TextUtils.isEmpty(a3)) {
            return h.a();
        }
        return a3;
    }

    private String b(Activity activity, String str) {
        i a2;
        b();
        try {
            try {
                List<com.alipay.sdk.protocol.b> a3 = com.alipay.sdk.protocol.b.a(new com.alipay.sdk.packet.impl.a().a(activity, str).a().optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
                c();
                for (int i = 0; i < a3.size(); i++) {
                    if (a3.get(i).a == com.alipay.sdk.protocol.a.WapPay) {
                        return a(a3.get(i));
                    }
                }
                c();
                a2 = null;
            } catch (IOException e) {
                a2 = i.a(i.NETWORK_ERROR.h);
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, e);
            }
            if (a2 == null) {
                a2 = i.a(i.FAILED.h);
            }
            return h.a(a2.h, a2.i, "");
        } finally {
            c();
        }
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] strArr = bVar.b;
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        Intent intent = new Intent(this.c, H5AuthActivity.class);
        intent.putExtras(bundle);
        this.c.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                return h.a();
            }
        }
        String str = h.a;
        if (TextUtils.isEmpty(str)) {
            return h.a();
        }
        return str;
    }

    private static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(l.a(), 128);
            if (packageInfo == null) {
                return false;
            }
            if (packageInfo.versionCode >= 73) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
