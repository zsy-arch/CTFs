package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.social.e;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;
import u.aly.ad;
import u.aly.af;
import u.aly.ag;
import u.aly.ah;
import u.aly.ao;
import u.aly.aq;
import u.aly.as;
import u.aly.au;
import u.aly.aw;
import u.aly.bj;
import u.aly.bl;
import u.aly.bn;
import u.aly.bo;
import u.aly.bp;
import u.aly.br;
import u.aly.f;
import u.aly.m;

/* compiled from: InternalAgent.java */
/* loaded from: classes2.dex */
public class b implements ao {
    private static final String j = "sp";
    private bn b;
    private Context a = null;
    private ag c = new ag();
    private au d = new au();
    private as e = new as();
    private ah f = null;
    private af g = null;
    private ad h = null;
    private m i = null;
    private boolean k = false;
    private boolean l = false;
    private JSONObject m = null;
    private boolean n = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b() {
        this.c.a(this);
    }

    private void g(Context context) {
        if (context != null) {
            try {
                if (Build.VERSION.SDK_INT > 13 && !this.n && (context instanceof Activity)) {
                    this.h = new ad((Activity) context);
                    this.n = true;
                }
                if (!this.k) {
                    this.a = context.getApplicationContext();
                    this.f = new ah(this.a);
                    this.g = af.b(this.a);
                    this.k = true;
                    if (this.i == null) {
                        this.i = m.a(this.a);
                    }
                    if (!this.l) {
                        bp.b(new br() { // from class: com.umeng.analytics.b.1
                            @Override // u.aly.br
                            public void a() {
                                b.this.i.a(new f() { // from class: com.umeng.analytics.b.1.1
                                    @Override // u.aly.f, u.aly.g
                                    public void a(Object obj, boolean z) {
                                        b.this.l = true;
                                    }
                                });
                            }
                        });
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.d.a(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.d.b(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(bn bnVar) {
        this.b = bnVar;
    }

    public void a(Context context, int i) {
        AnalyticsConfig.a(context, i);
    }

    public void a(String str, String str2) {
        AnalyticsConfig.mWrapperType = str;
        AnalyticsConfig.mWrapperVersion = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(final Context context) {
        if (context == null) {
            bo.e("unexpected null context in onResume");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.d.a(context.getClass().getName());
        }
        try {
            if (!this.k || !this.n) {
                g(context);
            }
            bp.a(new br() { // from class: com.umeng.analytics.b.2
                @Override // u.aly.br
                public void a() {
                    b.this.h(context.getApplicationContext());
                }
            });
        } catch (Exception e) {
            bo.e("Exception occurred in Mobclick.onResume(). ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(final Context context) {
        if (context == null) {
            bo.e("unexpected null context in onPause");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.d.b(context.getClass().getName());
        }
        try {
            if (!this.k || !this.n) {
                g(context);
            }
            bp.a(new br() { // from class: com.umeng.analytics.b.3
                @Override // u.aly.br
                public void a() {
                    b.this.i(context.getApplicationContext());
                    b.this.i.d();
                }
            });
        } catch (Exception e) {
            bo.e("Exception occurred in Mobclick.onRause(). ", e);
        }
    }

    public as a() {
        return this.e;
    }

    public void a(Context context, String str, HashMap<String, Object> hashMap) {
        try {
            if (!this.k || !this.n) {
                g(context);
            }
            this.f.b(str, hashMap);
        } catch (Exception e) {
            bo.e(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (context == null) {
                bo.e("unexpected null context in reportError");
                return;
            }
            try {
                if (!this.k || !this.n) {
                    g(context);
                }
                aw.i iVar = new aw.i();
                iVar.a = System.currentTimeMillis();
                iVar.b = 2L;
                iVar.c = str;
                this.g.a(iVar);
            } catch (Exception e) {
                bo.e(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, Throwable th) {
        if (context != null && th != null) {
            try {
                a(context, bj.a(th));
            } catch (Exception e) {
                bo.e(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(Context context) {
        this.e.c(context);
        if (this.b != null) {
            this.b.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(Context context) {
        this.e.d(context);
        this.d.a(context);
        this.h.a(context);
        if (this.b != null) {
            this.b.b();
        }
        this.g.b();
    }

    void c(Context context) {
        try {
            if (!this.k || !this.n) {
                g(context);
            }
            this.g.a();
        } catch (Exception e) {
            bo.e(e);
        }
    }

    public void a(Context context, List<String> list, int i, String str) {
        try {
            if (!this.k || !this.n) {
                g(context);
            }
            this.f.a(list, i, str);
        } catch (Exception e) {
            bo.e(e);
        }
    }

    public void a(Context context, String str, String str2, long j2, int i) {
        try {
            if (!this.k || !this.n) {
                g(context);
            }
            this.f.a(str, str2, j2, i);
        } catch (Exception e) {
            bo.e(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, String str, Map<String, Object> map, long j2) {
        try {
            if (!this.k || !this.n) {
                g(context);
            }
            this.f.a(str, map, j2);
        } catch (Exception e) {
            bo.e(e);
        }
    }

    public void a(Context context, String str, Map<String, Object> map) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(Context context) {
        try {
            this.h.b(context);
            this.d.a();
            i(context);
            aq.a(context).edit().commit();
            this.i.b();
            bp.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // u.aly.ao
    public void a(Throwable th) {
        try {
            this.d.a();
            if (this.a != null) {
                if (!(th == null || this.g == null)) {
                    aw.i iVar = new aw.i();
                    iVar.a = System.currentTimeMillis();
                    iVar.b = 1L;
                    iVar.c = bj.a(th);
                    this.g.a(iVar);
                }
                this.h.b(this.a);
                this.i.c();
                i(this.a);
                aq.a(this.a).edit().commit();
            }
            bp.a();
        } catch (Exception e) {
            bo.e("Exception in onAppCrash", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(final String str, final String str2) {
        try {
            bp.a(new br() { // from class: com.umeng.analytics.b.4
                @Override // u.aly.br
                public void a() {
                    String[] a = c.a(b.this.a);
                    if (a == null || !str.equals(a[0]) || !str2.equals(a[1])) {
                        boolean e = b.this.a().e(b.this.a);
                        af.b(b.this.a).c();
                        if (e) {
                            b.this.a().f(b.this.a);
                        }
                        c.a(b.this.a, str, str2);
                    }
                }
            });
        } catch (Exception e) {
            bo.e(" Excepthon  in  onProfileSignIn", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        try {
            bp.a(new br() { // from class: com.umeng.analytics.b.5
                @Override // u.aly.br
                public void a() {
                    String[] a = c.a(b.this.a);
                    if (a != null && !TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
                        boolean e = b.this.a().e(b.this.a);
                        af.b(b.this.a).c();
                        if (e) {
                            b.this.a().f(b.this.a);
                        }
                        c.b(b.this.a);
                    }
                }
            });
        } catch (Exception e) {
            bo.e(" Excepthon  in  onProfileSignOff", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        AnalyticsConfig.CATCH_EXCEPTION = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(GL10 gl10) {
        String[] a = bl.a(gl10);
        if (a.length == 2) {
            AnalyticsConfig.GPU_VENDER = a[0];
            AnalyticsConfig.GPU_RENDERER = a[1];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        AnalyticsConfig.ACTIVITY_DURATION_OPEN = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(boolean z) {
        a.e = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(boolean z) {
        bo.a = z;
        e.v = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(boolean z) {
        AnalyticsConfig.a(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(double d, double d2) {
        if (AnalyticsConfig.a == null) {
            AnalyticsConfig.a = new double[2];
        }
        AnalyticsConfig.a[0] = d;
        AnalyticsConfig.a[1] = d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        AnalyticsConfig.sLatentWindow = ((int) j2) * 1000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, MobclickAgent.EScenarioType eScenarioType) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        if (eScenarioType != null) {
            a(context, eScenarioType.toValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Context context, String str) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        AnalyticsConfig.b(context, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(MobclickAgent.UMAnalyticsConfig uMAnalyticsConfig) {
        if (uMAnalyticsConfig.mContext != null) {
            this.a = uMAnalyticsConfig.mContext.getApplicationContext();
        }
        if (!TextUtils.isEmpty(uMAnalyticsConfig.mAppkey)) {
            AnalyticsConfig.a(uMAnalyticsConfig.mContext, uMAnalyticsConfig.mAppkey);
            if (!TextUtils.isEmpty(uMAnalyticsConfig.mChannelId)) {
                AnalyticsConfig.a(uMAnalyticsConfig.mChannelId);
            }
            AnalyticsConfig.CATCH_EXCEPTION = uMAnalyticsConfig.mIsCrashEnable;
            a(this.a, uMAnalyticsConfig.mType);
            return;
        }
        bo.e("the appkey is null!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(long j2) {
        AnalyticsConfig.kContinueSessionMillis = j2;
    }

    public void a(Context context, String str, Object obj) {
    }

    public void c(Context context, String str) {
    }

    public Object d(Context context, String str) {
        return null;
    }

    public String e(Context context) {
        return null;
    }

    private JSONObject j(Context context) {
        try {
            String string = aq.a(context).getString(j, null);
            if (!TextUtils.isEmpty(string)) {
                return new JSONObject(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void f(Context context) {
    }

    public void a(Context context, List<String> list) {
        try {
            if (!this.k || !this.n) {
                g(context);
            }
            this.f.a(context, list);
        } catch (Exception e) {
            bo.e(e);
        }
    }
}
