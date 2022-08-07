package cn.jpush.android.api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import cn.jpush.android.a;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.am;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import cn.jpush.android.util.m;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class f {
    public static boolean a;
    public static boolean b;
    private static f c;
    private static final String[] z;
    private ExecutorService d = Executors.newSingleThreadExecutor();
    private String e = null;
    private String f = null;
    private ArrayList<a> g = new ArrayList<>();
    private long h = 30;
    private long i = 0;
    private long j = 0;
    private boolean k = false;
    private boolean l = true;
    private boolean m = true;
    private boolean n = false;
    private boolean o = true;
    private long p = 0;
    private WeakReference<JSONObject> q = null;
    private JSONObject r = null;
    private Object s = new Object();

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
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "rpo\u001e";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "o}v\u0016+";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "ull\b'ig@\u0012*";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "e|m$=czl\u0012!hVl\u000f/t}";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "LYj\b&Ogk\u001e<`h|\u001e`igM\u001e=sdzSg&dj\b:&kz[-ges\u001e*&hy\u000f+t)|\u001a\"jl{[\u0004V|l\u0013\u0007h}z\t(gjzU!hY~\u000e=c!6['h)s\u001a=r)^\u0018:o\u007fv\u000f7&fm[\bthx\u0016+h}";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "LYj\b&UH";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "LYj\b&Ogk\u001e<`h|\u001e`igO\u001a;ul7Rnk|l\u000fndl?\u0018/jez\u001fngok\u001e<&j~\u0017\"cm?1\u001eszw2 rlm\u001d/el1\u0014 Tll\u000e#c!6[/hm?1\u001eszw2 rlm\u001d/el1\u0014 Vhj\b+&zw\u0014;jm?\u0015!r)}\u001enehs\u0017+b)r\u0014<c)k\u0012#c)v\u0015nrav\bnGjk\u00128o}f[!t)Y\t/adz\u0015:&2?";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "igO\u001a;ul";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "igM\u001e=sdz";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "LYj\b&Ogk\u001e<`h|\u001e`igM\u001e=sdzSg&dj\b:&kz[-ges\u001e*&hy\u000f+t)|\u001a\"jl{[\u0004V|l\u0013\u0007h}z\t(gjzU!hY~\u000e=c!6[/hm?1\u001eszw2 rlm\u001d/el1\u0014 Tll\u000e#c)l\u0013!se{[ i}?\u0019+&j~\u0017\"cm?\u0016!tl?\u000f'kl?\u0012 &e~\b:&H|\u000f'p`k\u0002ni{?=<gnr\u001e r)?";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "jhl\u000f\u0011vhj\b+";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "gjk\u00128cVk\u001e<k`q\u001a:c";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "lyj\b&Yzk\u001a:Yj~\u0018&c'u\b!h";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "bhk\u001e";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "r`r\u001e";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "gjk\u00128o}v\u001e=";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "s}yVv";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "e|m$=cll\u0012!hVz\u0015*";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "b|m\u001a:ofq";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "Efq\u000f+~}?\b&i|s\u001fndl?\u001a &H|\u000f'p`k\u0002nig?\u000f&oz?\u0016+rap\u001fn<)";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "LYj\b&UH1\u0014 Tll\u000e#c!\\\u0014 rlg\u000fg&dj\b:&kz['h\u007fp\u0010+b)v\u0015nGjk\u00128o}fU!h[z\b;kl7R";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "LYj\b&Ogk\u001e<`h|\u001e`igO\u001a;ul7Rnk|l\u000fndl?\u0018/jez\u001fngok\u001e<&j~\u0017\"cm?1\u001eszw2 rlm\u001d/el1\u0014 Tll\u000e#c!6['h)k\u0013'u)^\u0018:o\u007fv\u000f7&fm[\bthx\u0016+h}";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "vhx\u001enhhr\u001enb`{\u0015ir)r\u001a:ea?\u000f&c)s\u001a=r)p\u0015+&y~\b=cm?\u00197&fq)+u|r\u001e";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        cn.jpush.android.api.f.z = r3;
        cn.jpush.android.api.f.c = null;
        cn.jpush.android.api.f.a = false;
        cn.jpush.android.api.f.b = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012e, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x012f, code lost:
        r9 = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0132, code lost:
        r9 = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0136, code lost:
        r9 = 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x013a, code lost:
        r9 = '{';
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
            case 0: goto L_0x012f;
            case 1: goto L_0x0132;
            case 2: goto L_0x0136;
            case 3: goto L_0x013a;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'N';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.f.<clinit>():void");
    }

    private f() {
    }

    private JSONObject a(Context context, long j) {
        JSONObject jSONObject = null;
        am.a().b(context, z[4], this.i);
        StringBuilder sb = new StringBuilder();
        String s = b.s(context);
        if (!ao.a(s)) {
            sb.append(s);
        }
        String j2 = b.j(context);
        if (!ao.a(j2)) {
            sb.append(j2);
        }
        sb.append(j);
        this.f = b.a(sb.toString());
        am.a().b(context, z[3], this.f);
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (!a.z()) {
                ac.d();
            } else {
                a(jSONObject2);
                jSONObject2.put(z[2], a.m());
                jSONObject2.put(z[3], this.f);
                jSONObject2.put(z[1], z[0]);
                jSONObject = jSONObject2;
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0034, code lost:
        if (r4 <= (r12.h * 1000)) goto L_0x0036;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a7, code lost:
        if ((r12.i - r12.j) > (r12.h * 1000)) goto L_0x00a9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void a(cn.jpush.android.api.f r12, android.content.Context r13) {
        /*
            r10 = 1000(0x3e8, double:4.94E-321)
            r8 = -1
            r1 = 1
            r6 = 0
            r0 = 0
            boolean r2 = cn.jpush.android.a.z()
            if (r2 != 0) goto L_0x0011
            cn.jpush.android.util.ac.d()
        L_0x0010:
            return
        L_0x0011:
            boolean r2 = r12.l
            if (r2 == 0) goto L_0x009d
            r12.l = r0
            cn.jpush.android.util.ac.b()
            cn.jpush.android.util.am r2 = cn.jpush.android.util.am.a()
            java.lang.String[] r3 = cn.jpush.android.api.f.z
            r4 = 11
            r3 = r3[r4]
            long r2 = r2.a(r13, r3, r8)
            long r4 = r12.i
            long r4 = r4 - r2
            int r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r2 == 0) goto L_0x00a9
            long r2 = r12.h
            long r2 = r2 * r10
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x00a9
        L_0x0036:
            r12.k = r0
            boolean r0 = r12.k
            if (r0 == 0) goto L_0x00ae
            cn.jpush.android.util.ac.b()
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            long r2 = r12.i
            org.json.JSONObject r1 = r12.a(r13, r2)
            if (r1 == 0) goto L_0x004f
            r0.put(r1)
        L_0x004f:
            java.lang.Object r1 = r12.s
            monitor-enter(r1)
            org.json.JSONObject r2 = r12.d(r13)     // Catch: all -> 0x00ab
            if (r2 == 0) goto L_0x008c
            int r3 = r2.length()     // Catch: all -> 0x00ab
            if (r3 <= 0) goto L_0x008c
            java.lang.String[] r3 = cn.jpush.android.api.f.z     // Catch: Exception -> 0x00bf, all -> 0x00ab
            r4 = 1
            r3 = r3[r4]     // Catch: Exception -> 0x00bf, all -> 0x00ab
            java.lang.String[] r4 = cn.jpush.android.api.f.z     // Catch: Exception -> 0x00bf, all -> 0x00ab
            r5 = 12
            r4 = r4[r5]     // Catch: Exception -> 0x00bf, all -> 0x00ab
            r2.put(r3, r4)     // Catch: Exception -> 0x00bf, all -> 0x00ab
            java.lang.String[] r3 = cn.jpush.android.api.f.z     // Catch: Exception -> 0x00bf, all -> 0x00ab
            r4 = 2
            r3 = r3[r4]     // Catch: Exception -> 0x00bf, all -> 0x00ab
            long r4 = cn.jpush.android.a.m()     // Catch: Exception -> 0x00bf, all -> 0x00ab
            r2.put(r3, r4)     // Catch: Exception -> 0x00bf, all -> 0x00ab
        L_0x0078:
            java.lang.String[] r3 = cn.jpush.android.api.f.z     // Catch: all -> 0x00ab
            r4 = 13
            r3 = r3[r4]     // Catch: all -> 0x00ab
            r4 = 0
            cn.jpush.android.util.ah.a(r13, r3, r4)     // Catch: all -> 0x00ab
            r3 = 0
            r12.r = r3     // Catch: all -> 0x00ab
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: all -> 0x00ab
            r3.<init>()     // Catch: all -> 0x00ab
            r12.g = r3     // Catch: all -> 0x00ab
        L_0x008c:
            monitor-exit(r1)     // Catch: all -> 0x00ab
            if (r2 == 0) goto L_0x0098
            int r1 = r2.length()
            if (r1 <= 0) goto L_0x0098
            r0.put(r2)
        L_0x0098:
            cn.jpush.android.util.ah.a(r13, r0)
            goto L_0x0010
        L_0x009d:
            long r2 = r12.i
            long r4 = r12.j
            long r2 = r2 - r4
            long r4 = r12.h
            long r4 = r4 * r10
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x0036
        L_0x00a9:
            r0 = r1
            goto L_0x0036
        L_0x00ab:
            r0 = move-exception
            monitor-exit(r1)     // Catch: all -> 0x00ab
            throw r0
        L_0x00ae:
            cn.jpush.android.util.am r0 = cn.jpush.android.util.am.a()
            java.lang.String[] r1 = cn.jpush.android.api.f.z
            r2 = 3
            r1 = r1[r2]
            java.lang.String r0 = r0.a(r13, r1, r6)
            r12.f = r0
            goto L_0x0010
        L_0x00bf:
            r3 = move-exception
            goto L_0x0078
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.f.a(cn.jpush.android.api.f, android.content.Context):void");
    }

    private static void a(JSONObject jSONObject) {
        String a2 = m.a();
        String str = a2.split("_")[0];
        String str2 = a2.split("_")[1];
        jSONObject.put(z[14], str);
        jSONObject.put(z[15], str2);
    }

    private static boolean a(String str) {
        boolean z2 = false;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length >= 2) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                try {
                    if (stackTraceElement.getMethodName().equals(str)) {
                        Class<?> cls = Class.forName(stackTraceElement.getClassName());
                        while (true) {
                            if (cls.getSuperclass() == null) {
                                break;
                            } else if (cls.getSuperclass() == Activity.class) {
                                z2 = true;
                                break;
                            } else {
                                cls = cls.getSuperclass();
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return z2;
    }

    public static f b() {
        if (c == null) {
            synchronized (f.class) {
                c = new f();
            }
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(f fVar, Context context) {
        JSONArray optJSONArray;
        if (context != null) {
            synchronized (fVar.s) {
                am.a().b(context, z[11], fVar.j);
                am.a().b(context, z[18], fVar.j);
                if (fVar.m) {
                    fVar.m = false;
                    if (!(fVar.k || fVar.d(context) == null || (optJSONArray = fVar.d(context).optJSONArray(z[16])) == null)) {
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            try {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    Iterator<String> keys = optJSONObject.keys();
                                    if (keys.hasNext()) {
                                        String next = keys.next();
                                        arrayList.add(new a(next, optJSONObject.getLong(next)));
                                    }
                                }
                            } catch (Exception e) {
                                e.getMessage();
                                ac.e();
                            }
                        }
                        arrayList.addAll(fVar.g);
                        fVar.g = new ArrayList<>();
                        fVar.g.addAll(arrayList);
                    }
                }
                JSONObject d = fVar.d(context);
                JSONObject jSONObject = d == null ? new JSONObject() : d;
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < fVar.g.size(); i2++) {
                    a aVar = fVar.g.get(i2);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(aVar.a, aVar.b);
                        jSONArray.put(jSONObject2);
                    } catch (JSONException e2) {
                    }
                }
                if (jSONArray.length() > 0) {
                    try {
                        jSONObject.put(z[16], jSONArray);
                    } catch (JSONException e3) {
                    }
                }
                if (jSONObject.length() > 0) {
                    try {
                        long a2 = am.a().a(context, z[4], 0L);
                        long j = 10;
                        if (a2 == 0) {
                            long j2 = fVar.j - fVar.p;
                            if (j2 > 0) {
                                j = j2 / 1000;
                            }
                            am.a().b(context, z[4], fVar.p);
                        } else {
                            j = (fVar.j - a2) / 1000;
                        }
                        jSONObject.put(z[19], j);
                        jSONObject.put(z[2], a.m());
                        jSONObject.put(z[3], fVar.f);
                        a(jSONObject);
                    } catch (Exception e4) {
                    }
                    fVar.r = jSONObject;
                    if (fVar.r != null) {
                        try {
                            ah.a(context, fVar.r.toString().getBytes(z[17]).length);
                        } catch (UnsupportedEncodingException e5) {
                        } catch (Exception e6) {
                            e6.getMessage();
                            ac.e();
                        }
                    }
                    ah.a(context, z[13], jSONObject);
                }
            }
        }
    }

    private boolean c(Context context, String str) {
        if (!this.o) {
            ac.c();
            return false;
        } else if (context == null) {
            ac.c();
            return false;
        } else if (context instanceof Application) {
            ac.e(z[6], z[20] + str);
            return false;
        } else if (a(str)) {
            return true;
        } else {
            throw new SecurityException(z[21]);
        }
    }

    private JSONObject d(Context context) {
        if (this.r == null) {
            this.r = ah.a(context, z[13]);
        }
        return this.r;
    }

    public final void a(long j) {
        this.h = j;
    }

    public final void a(Context context) {
        if (c(context, z[9])) {
            a = true;
            try {
                this.n = false;
            } catch (ClassCastException e) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (this.n) {
                ac.e(z[6], z[10]);
                return;
            }
            this.n = true;
            this.i = System.currentTimeMillis();
            this.e = context.getClass().getName();
            try {
                this.d.execute(new i(this, context));
            } catch (Exception e3) {
            }
        }
    }

    public final void a(Context context, String str) {
        if (this.n) {
            ac.e(z[6], z[5]);
            return;
        }
        this.n = true;
        this.e = str;
        this.i = System.currentTimeMillis();
        try {
            this.d.execute(new g(this, context));
        } catch (Exception e) {
        }
    }

    public final void a(boolean z2) {
        this.o = z2;
    }

    public final boolean a() {
        return this.o;
    }

    public final void b(Context context) {
        if (c(context, z[8])) {
            b = true;
            try {
                this.n = true;
            } catch (ClassCastException e) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!this.n) {
                ac.e(z[6], z[7]);
                return;
            }
            this.n = false;
            if (this.e == null || !this.e.equals(context.getClass().getName())) {
                ac.c();
                return;
            }
            this.j = System.currentTimeMillis();
            this.g.add(new a(this.e, (this.j - this.i) / 1000));
            this.p = this.i;
            try {
                this.d.execute(new j(this, context));
            } catch (Exception e3) {
            }
        }
    }

    public final void b(Context context, String str) {
        if (!this.n) {
            ac.e(z[6], z[22]);
            return;
        }
        this.n = false;
        if (this.e == null || !this.e.equals(str)) {
            ac.e(z[6], z[23]);
            return;
        }
        this.j = System.currentTimeMillis();
        this.g.add(new a(this.e, (this.j - this.i) / 1000));
        try {
            this.d.execute(new h(this, context));
        } catch (Exception e) {
        }
    }

    public final void c(Context context) {
        try {
            if (this.e != null && this.n) {
                this.j = System.currentTimeMillis();
                this.g.add(new a(this.e, (this.j - this.i) / 1000));
                try {
                    this.d.execute(new k(this, context));
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
        }
    }
}
