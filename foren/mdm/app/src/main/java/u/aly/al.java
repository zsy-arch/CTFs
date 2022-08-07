package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import com.umeng.analytics.c;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MemoCache.java */
/* loaded from: classes2.dex */
public class al {
    private List<aj> a = new ArrayList();
    private Context b;

    public al(Context context) {
        this.b = null;
        this.b = context;
    }

    public Context a() {
        return this.b;
    }

    protected boolean a(int i) {
        return true;
    }

    public synchronized int b() {
        int size;
        size = this.a.size();
        if (aw.c != 0) {
            size++;
        }
        return size;
    }

    public synchronized void a(aj ajVar) {
        this.a.add(ajVar);
    }

    public void a(aw awVar) {
        if (as.g(this.b) != null) {
            b(awVar);
            c(awVar);
        }
    }

    private void c(aw awVar) {
        synchronized (this) {
            for (aj ajVar : this.a) {
                ajVar.a(awVar);
            }
            SharedPreferences a = aq.a(this.b);
            if (a != null) {
                String string = a.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    awVar.b.j = string;
                }
                this.a.clear();
                if (aw.c != 0) {
                    awVar.b.d.a = aw.c;
                }
                m.a(this.b).a(awVar);
                String[] a2 = c.a(this.b);
                if (a2 != null && !TextUtils.isEmpty(a2[0]) && !TextUtils.isEmpty(a2[1])) {
                    awVar.b.g.a = a2[0];
                    awVar.b.g.b = a2[1];
                }
                ax.a(this.b).a(awVar);
            }
        }
    }

    void b(aw awVar) {
        bd b;
        awVar.a.a = AnalyticsConfig.getAppkey(this.b);
        awVar.a.b = AnalyticsConfig.getChannel(this.b);
        awVar.a.c = bm.a(AnalyticsConfig.getSecretKey(this.b));
        awVar.a.m = AnalyticsConfig.getVerticalType(this.b);
        awVar.a.l = AnalyticsConfig.getSDKVersion(this.b);
        awVar.a.e = bl.t(this.b);
        SharedPreferences a = aq.a(this.b);
        a.edit();
        int parseInt = Integer.parseInt(bl.a(this.b));
        String b2 = bl.b(this.b);
        if (a == null) {
            awVar.a.h = parseInt;
            awVar.a.d = b2;
        } else {
            awVar.a.h = a.getInt(a.y, 0);
            awVar.a.d = a.getString(a.z, "");
        }
        awVar.a.f = bl.u(this.b);
        awVar.a.g = bl.w(this.b);
        if (!(AnalyticsConfig.mWrapperType == null || AnalyticsConfig.mWrapperVersion == null)) {
            awVar.a.i = AnalyticsConfig.mWrapperType;
            awVar.a.j = AnalyticsConfig.mWrapperVersion;
        }
        awVar.a.y = bl.c(this.b);
        awVar.a.s = bl.d(this.b);
        awVar.a.x = bl.q(this.b);
        awVar.a.G = bl.x(this.b);
        awVar.a.H = bl.y(this.b);
        int[] r = bl.r(this.b);
        if (r != null) {
            awVar.a.w = r[1] + "*" + r[0];
        }
        if (AnalyticsConfig.GPU_RENDERER == null || AnalyticsConfig.GPU_VENDER != null) {
        }
        String[] j = bl.j(this.b);
        if ("Wi-Fi".equals(j[0])) {
            awVar.a.M = "wifi";
        } else if ("2G/3G".equals(j[0])) {
            awVar.a.M = "2G/3G";
        } else {
            awVar.a.M = "unknow";
        }
        if (!"".equals(j[1])) {
            awVar.a.N = j[1];
        }
        String e = bl.e(this.b);
        if (!TextUtils.isEmpty(e)) {
            awVar.a.O = e;
        }
        awVar.a.L = bl.h(this.b);
        String[] o = bl.o(this.b);
        awVar.a.K = o[0];
        awVar.a.J = o[1];
        awVar.a.I = bl.m(this.b);
        at.a(this.b, awVar);
        try {
            b = v.a(this.b).b();
        } catch (Exception e2) {
        }
        if (b != null) {
            byte[] a2 = new cf().a(b);
            awVar.a.T = Base64.encodeToString(a2, 0);
            try {
                be a3 = x.a(this.b).a();
                if (a3 == null) {
                    bo.e("trans the imprint is null");
                } else {
                    byte[] a4 = new cf().a(a3);
                    awVar.a.S = Base64.encodeToString(a4, 0);
                }
            } catch (Exception e3) {
            }
        }
    }
}
