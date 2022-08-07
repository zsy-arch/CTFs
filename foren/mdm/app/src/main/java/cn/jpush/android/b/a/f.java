package cn.jpush.android.b.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import cn.jpush.android.api.n;
import cn.jpush.android.data.c;
import cn.jpush.android.data.i;
import cn.jpush.android.data.s;
import cn.jpush.android.e;
import cn.jpush.android.helpers.h;
import cn.jpush.android.helpers.k;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.ui.PopWinActivity;
import cn.jpush.android.ui.PushActivity;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import com.amap.api.services.core.AMapException;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class f {
    private static final String[] z;
    private final WeakReference<Activity> a;
    private final c b;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r5 > r6) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a9;
            case 12: goto L_0x00b4;
            case 13: goto L_0x00bf;
            case 14: goto L_0x00ca;
            case 15: goto L_0x00d5;
            case 16: goto L_0x00e0;
            case 17: goto L_0x00eb;
            case 18: goto L_0x00f6;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "<zP\u0004|e6G/\u007f\u007f)FV";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "G?ALpq6O\u000ers1\u0019\u000f\u007fy9HL>0;@\u0018z\u007f4j\b)";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "D2FLrs.J\u001azd#\u0003\u0002r}?\u0003\u0005`03M\u001ar|3G@3W3U\t3e*\rB";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "D2FLrs.J\u001azd#\u0003\u0002r}?\u0003\u0005`04V\u0000\u007f05QLv}*W\u0015?0\u001dJ\u001av0/SB=";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "q9W\u0005ey.Z\"r}?\u0003Q3";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "s4\r\u0006ce)KBr~>Q\u0003zttb/GY\fj8JO\nb>R]";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "G?A:zu-k\t\u007f`?Q";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "E4K\r}t6FLz~.F\u0002g0`\u0003\u000f}>0S\u0019`xtB\u0002wb5J\b=y4W\t}dtb/GY\u0015m3RS\u000ej:ZD\u0003|#CU\u0014g)W";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "s4\r\u0006ce)KBr~>Q\u0003zttJ\u0002gu4WBRS\u000ej#]O\bj/[@\u000fp$LS\u001bo QQ\u0019h";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "<zV\u001e\u007f*";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "G?ALpq6O\u000ers1\u0019\u000fau;W\t@x5Q\u0018pe.\u0003A3~;N\t)";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "y9L\u0002ZtzP\u0004|e6GLquzJ\u0002g0w\u0003";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a9, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "G?ALpq6O\u000ers1\u0019\u001f{\u007f-w\u0003rc.\u0003A3";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b4, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "G?ALpq6O\u000ers1\u0019\tku9V\u0018v])D!vc)B\u000bv0w\u0003";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00bf, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "Y4U\r\u007fy>\u0003\rpd3L\u0002ZtzE\u001e|}zt\tq0w\u0003";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ca, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "Y4U\r\u007fy>\u0003\u0001vc)B\u000bvD#S\t3v5QLw\u007f-M\u0000|q>\u0003A3";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d5, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "G?ALpq6O\u000ers1\u0019\b|g4O\u0003rtz\u000eL";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e0, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "E4K\r}t6FLz~.F\u0002g0`\u0003";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00eb, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "s4\r\u0006ce)KBr~>Q\u0003zttf4GB\u001b";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f6, code lost:
        r3[r2] = r1;
        cn.jpush.android.b.a.f.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00fa, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00fb, code lost:
        r9 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ff, code lost:
        r9 = 'Z';
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0103, code lost:
        r9 = '#';
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0107, code lost:
        r9 = 'l';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x00fb;
            case 1: goto L_0x00ff;
            case 2: goto L_0x0103;
            case 3: goto L_0x0107;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.f.<clinit>():void");
    }

    public f(Context context, c cVar) {
        this.a = new WeakReference<>((Activity) context);
        this.b = cVar;
    }

    private void g(String str) {
        int i = AMapException.CODE_AMAP_ENGINE_RESPONSE_ERROR;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            new StringBuilder(z[15]).append(str);
            ac.e();
        }
        k.a(this.b.c, i, this.a.get());
    }

    public final void a() {
        if (this.a.get() != null) {
            ac.b();
            this.a.get().finish();
        }
    }

    public final void a(String str) {
        Activity activity = this.a.get();
        if (activity != null) {
            b.b(activity, z[9], str);
        }
    }

    public final void a(String str, String str2) {
        new StringBuilder(z[5]).append(str);
        ac.b();
        if (ao.a(str)) {
            ac.e(z[7], z[4]);
        }
        Activity activity = this.a.get();
        if (activity != null) {
            try {
                Class<?> cls = Class.forName(str);
                if (cls != null) {
                    Intent intent = new Intent(activity, cls);
                    intent.putExtra(z[6], str2);
                    intent.setFlags(268435456);
                    activity.startActivity(intent);
                }
            } catch (Exception e) {
                ac.e(z[7], z[3]);
            }
        }
    }

    public final void a(String str, String str2, String str3) {
        int i = 0;
        try {
            i = Integer.parseInt(str3);
        } catch (Exception e) {
            new StringBuilder(z[12]).append(str3);
            ac.b();
        }
        if (this.a.get() != null) {
            new StringBuilder(z[11]).append(str).append(z[10]).append(str2);
            ac.b();
            b.a(this.a.get(), str, str2, n.a(i));
        }
    }

    public final void b() {
        if (this.a.get() != null && (this.a.get() instanceof PushActivity)) {
            ((PushActivity) this.a.get()).a();
        }
    }

    public final void b(String str) {
        Activity activity = this.a.get();
        if (activity != null) {
            try {
                b.h(activity, str);
                activity.finish();
            } catch (Exception e) {
                ac.e(z[7], z[8]);
            }
        }
    }

    public final void b(String str, String str2) {
        Activity activity = this.a.get();
        if (activity != null) {
            try {
                Intent intent = new Intent(str);
                intent.addCategory(activity.getPackageName());
                intent.putExtra(z[19], str2);
                intent.setFlags(268435456);
                activity.startActivity(intent);
            } catch (Exception e) {
                ac.e(z[7], z[18] + str);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            r1 = 0
            java.lang.ref.WeakReference<android.app.Activity> r0 = r5.a
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L_0x005c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String[] r2 = cn.jpush.android.b.a.f.z
            r3 = 2
            r2 = r2[r3]
            r0.<init>(r2)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String[] r2 = cn.jpush.android.b.a.f.z
            r3 = 1
            r2 = r2[r3]
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String[] r2 = cn.jpush.android.b.a.f.z
            r2 = r2[r1]
            java.lang.StringBuilder r0 = r0.append(r2)
            r0.append(r8)
            cn.jpush.android.util.ac.b()
            r5.g(r6)
            boolean r0 = java.lang.Boolean.parseBoolean(r7)     // Catch: Exception -> 0x005d
            boolean r2 = java.lang.Boolean.parseBoolean(r8)     // Catch: Exception -> 0x0062
            r4 = r2
            r2 = r0
            r0 = r4
        L_0x0040:
            if (r0 == 0) goto L_0x004f
            java.lang.ref.WeakReference<android.app.Activity> r0 = r5.a
            java.lang.Object r0 = r0.get()
            android.content.Context r0 = (android.content.Context) r0
            cn.jpush.android.data.c r3 = r5.b
            cn.jpush.android.api.n.a(r0, r3, r1)
        L_0x004f:
            if (r2 == 0) goto L_0x005c
            java.lang.ref.WeakReference<android.app.Activity> r0 = r5.a
            java.lang.Object r0 = r0.get()
            android.app.Activity r0 = (android.app.Activity) r0
            r0.finish()
        L_0x005c:
            return
        L_0x005d:
            r0 = move-exception
            r0 = r1
        L_0x005f:
            r2 = r0
            r0 = r1
            goto L_0x0040
        L_0x0062:
            r2 = move-exception
            goto L_0x005f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.f.b(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public final void c(String str) {
        if (this.a.get() != null) {
            new StringBuilder(z[17]).append(str);
            ac.b();
            Activity activity = this.a.get();
            c cVar = this.b;
            if (cVar.a()) {
                i iVar = (i) cVar;
                if (TextUtils.isEmpty(iVar.K)) {
                    iVar.K = str;
                }
                if (!TextUtils.isEmpty(iVar.P)) {
                    b.e(activity, iVar.P);
                    n.a(activity, iVar, 0);
                    n.a(activity, iVar, 1);
                    return;
                }
            } else if (cVar.b()) {
                s sVar = (s) cVar;
                if (TextUtils.isEmpty(sVar.E)) {
                    sVar.E = str;
                }
                if (!TextUtils.isEmpty(sVar.I)) {
                    activity.startActivity(b.a((Context) activity, cVar, false));
                    return;
                }
            } else {
                new StringBuilder(z[16]).append(cVar.o);
                ac.d();
                return;
            }
            ServiceInterface.a(activity, cVar);
        }
    }

    public final void c(String str, String str2) {
        if (this.a.get() != null) {
            g(str);
            c(str2);
            n.a(this.a.get(), this.b, 0);
            this.a.get().finish();
        }
    }

    public final void d(String str) {
        if (this.a.get() != null) {
            new StringBuilder(z[13]).append(str);
            ac.b();
            Toast.makeText(this.a.get(), str, 0).show();
        }
    }

    public final void e(String str) {
        if (e.a) {
            new StringBuilder(z[14]).append(str);
            ac.b();
            if (this.a.get() != null) {
                h.a(this.a.get(), str);
            }
        }
    }

    public final void f(String str) {
        if (this.a.get() != null && (this.a.get() instanceof PopWinActivity)) {
            ((PopWinActivity) this.a.get()).a(str);
        }
    }
}
