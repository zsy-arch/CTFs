package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/* compiled from: Cache.java */
/* loaded from: classes2.dex */
public final class cf {
    private static cf b = null;
    Hashtable<String, ArrayList<a>> a = new Hashtable<>();
    private long c = 0;
    private volatile boolean d = false;

    /* compiled from: Cache.java */
    /* loaded from: classes2.dex */
    public static class a {
        private AMapLocationServer a = null;
        private String b = null;

        protected a() {
        }

        public final AMapLocationServer a() {
            return this.a;
        }

        public final void a(AMapLocationServer aMapLocationServer) {
            this.a = aMapLocationServer;
        }

        public final void a(String str) {
            if (TextUtils.isEmpty(str)) {
                this.b = null;
            } else {
                this.b = str.replace("##", "#");
            }
        }

        public final String b() {
            return this.b;
        }
    }

    private cf() {
    }

    private synchronized a a(StringBuilder sb, String str) {
        a aVar;
        boolean z;
        if (this.a.isEmpty() || TextUtils.isEmpty(sb)) {
            aVar = null;
        } else if (!this.a.containsKey(str)) {
            aVar = null;
        } else {
            aVar = null;
            Hashtable hashtable = new Hashtable();
            Hashtable hashtable2 = new Hashtable();
            Hashtable hashtable3 = new Hashtable();
            ArrayList<a> arrayList = this.a.get(str);
            int size = arrayList.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                a aVar2 = arrayList.get(size);
                if (!TextUtils.isEmpty(aVar2.b())) {
                    boolean z2 = false;
                    String b2 = aVar2.b();
                    if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(sb)) {
                        z = false;
                    } else if (!b2.contains(",access") || sb.indexOf(",access") == -1) {
                        z = false;
                    } else {
                        String[] split = b2.split(",access");
                        String substring = split[0].contains("#") ? split[0].substring(split[0].lastIndexOf("#") + 1) : split[0];
                        z = TextUtils.isEmpty(substring) ? false : sb.toString().contains(substring + ",access");
                    }
                    if (z) {
                        z2 = true;
                        if (a(aVar2.b(), sb.toString())) {
                            aVar = aVar2;
                            break;
                        }
                    }
                    a(aVar2.b(), hashtable);
                    a(sb.toString(), hashtable2);
                    hashtable3.clear();
                    for (String str2 : hashtable.keySet()) {
                        hashtable3.put(str2, "");
                    }
                    for (String str3 : hashtable2.keySet()) {
                        hashtable3.put(str3, "");
                    }
                    Set keySet = hashtable3.keySet();
                    double[] dArr = new double[keySet.size()];
                    double[] dArr2 = new double[keySet.size()];
                    int i = 0;
                    Iterator it = keySet.iterator();
                    while (it != null && it.hasNext()) {
                        String str4 = (String) it.next();
                        dArr[i] = hashtable.containsKey(str4) ? 1.0d : 0.0d;
                        dArr2[i] = hashtable2.containsKey(str4) ? 1.0d : 0.0d;
                        i++;
                    }
                    keySet.clear();
                    double[] a2 = a(dArr, dArr2);
                    if (a2[0] < 0.800000011920929d) {
                        if (a2[1] < 0.618d) {
                            if (z2 && a2[0] >= 0.618d) {
                                aVar = aVar2;
                                break;
                            }
                        } else {
                            aVar = aVar2;
                            break;
                        }
                    } else {
                        aVar = aVar2;
                        break;
                    }
                }
                size--;
            }
            hashtable.clear();
            hashtable2.clear();
            hashtable3.clear();
        }
        return aVar;
    }

    public static synchronized cf a() {
        cf cfVar;
        synchronized (cf.class) {
            if (b == null) {
                b = new cf();
            }
            cfVar = b;
        }
        return cfVar;
    }

    private static void a(String str, Hashtable<String, String> hashtable) {
        if (!TextUtils.isEmpty(str)) {
            hashtable.clear();
            String[] split = str.split("#");
            for (String str2 : split) {
                if (!TextUtils.isEmpty(str2) && !str2.contains("|")) {
                    hashtable.put(str2, "");
                }
            }
        }
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String[] split = str.split("#");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(",nb") || split[i].contains(",access")) {
                arrayList.add(split[i]);
            }
        }
        String[] split2 = str2.toString().split("#");
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < split2.length; i4++) {
            if (split2[i4].contains(",nb") || split2[i4].contains(",access")) {
                i3++;
                if (arrayList.contains(split2[i4])) {
                    i2++;
                }
            }
        }
        return ((double) (i2 * 2)) >= ((double) (arrayList.size() + i3)) * 0.618d;
    }

    private static double[] a(double[] dArr, double[] dArr2) {
        double[] dArr3 = new double[3];
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < dArr.length; i3++) {
            d2 += dArr[i3] * dArr[i3];
            d3 += dArr2[i3] * dArr2[i3];
            d += dArr[i3] * dArr2[i3];
            if (dArr2[i3] == 1.0d) {
                i++;
                if (dArr[i3] == 1.0d) {
                    i2++;
                }
            }
        }
        dArr3[0] = d / (Math.sqrt(d3) * Math.sqrt(d2));
        dArr3[1] = (1.0d * i2) / i;
        dArr3[2] = i2;
        for (int i4 = 0; i4 < dArr3.length - 1; i4++) {
            if (dArr3[i4] > 1.0d) {
                dArr3[i4] = 1.0d;
            }
        }
        return dArr3;
    }

    private boolean c() {
        long b2 = cx.b() - this.c;
        if (this.c == 0) {
            return false;
        }
        return this.a.size() > 360 || b2 > 36000000;
    }

    private void d() {
        this.c = 0L;
        if (!this.a.isEmpty()) {
            this.a.clear();
        }
        this.d = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
        if (com.loc.cq.b(r0.getTime()) != false) goto L_0x0015;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003d A[Catch: all -> 0x0094, TryCatch #0 {, blocks: (B:4:0x0002, B:7:0x000c, B:13:0x0017, B:15:0x001d, B:16:0x0022, B:19:0x002c, B:21:0x0034, B:25:0x003d, B:27:0x0047, B:30:0x0063, B:32:0x006b, B:35:0x0073, B:37:0x007b, B:39:0x0083), top: B:45:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized com.autonavi.aps.amapapi.model.AMapLocationServer a(java.lang.String r5, java.lang.StringBuilder r6, boolean r7) {
        /*
            r4 = this;
            r1 = 0
            monitor-enter(r4)
            java.lang.String r0 = "gps"
            boolean r0 = r5.contains(r0)     // Catch: all -> 0x0094
            if (r0 != 0) goto L_0x0014
            if (r7 == 0) goto L_0x0014
            boolean r0 = com.loc.cq.t()     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x0014
            if (r6 != 0) goto L_0x0017
        L_0x0014:
            r0 = r1
        L_0x0015:
            monitor-exit(r4)
            return r0
        L_0x0017:
            boolean r0 = r4.c()     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x0022
            r4.d()     // Catch: all -> 0x0094
            r0 = r1
            goto L_0x0015
        L_0x0022:
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.cf$a>> r0 = r4.a     // Catch: all -> 0x0094
            boolean r0 = r0.isEmpty()     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x002c
            r0 = r1
            goto L_0x0015
        L_0x002c:
            java.lang.String r0 = "cgiwifi"
            boolean r0 = r5.contains(r0)     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x0063
            com.loc.cf$a r0 = r4.a(r6, r5)     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x0099
            r2 = r0
        L_0x003b:
            if (r2 == 0) goto L_0x0061
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r2.a()     // Catch: all -> 0x0094
            boolean r0 = com.loc.cx.a(r0)     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x0061
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r2.a()     // Catch: all -> 0x0094
            java.lang.String r3 = "mem"
            r0.d(r3)     // Catch: all -> 0x0094
            java.lang.String r2 = r2.b()     // Catch: all -> 0x0094
            r0.f(r2)     // Catch: all -> 0x0094
            long r2 = r0.getTime()     // Catch: all -> 0x0094
            boolean r2 = com.loc.cq.b(r2)     // Catch: all -> 0x0094
            if (r2 != 0) goto L_0x0015
        L_0x0061:
            r0 = r1
            goto L_0x0015
        L_0x0063:
            java.lang.String r0 = "wifi"
            boolean r0 = r5.contains(r0)     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x0073
            com.loc.cf$a r0 = r4.a(r6, r5)     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x0099
            r2 = r0
            goto L_0x003b
        L_0x0073:
            java.lang.String r0 = "cgi"
            boolean r0 = r5.contains(r0)     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x0097
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.cf$a>> r0 = r4.a     // Catch: all -> 0x0094
            boolean r0 = r0.containsKey(r5)     // Catch: all -> 0x0094
            if (r0 == 0) goto L_0x0097
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.cf$a>> r0 = r4.a     // Catch: all -> 0x0094
            java.lang.Object r0 = r0.get(r5)     // Catch: all -> 0x0094
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch: all -> 0x0094
            r2 = 0
            java.lang.Object r0 = r0.get(r2)     // Catch: all -> 0x0094
            com.loc.cf$a r0 = (com.loc.cf.a) r0     // Catch: all -> 0x0094
            r2 = r0
            goto L_0x003b
        L_0x0094:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0097:
            r2 = r1
            goto L_0x003b
        L_0x0099:
            r2 = r0
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cf.a(java.lang.String, java.lang.StringBuilder, boolean):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    public final synchronized String a(String str, StringBuilder sb, Context context) {
        return ch.a().a(str, sb, context);
    }

    public final synchronized void a(Context context, String str) {
        if (!this.d) {
            d();
            ch.a().a(context, str);
            if (str == null) {
                this.d = true;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a0, code lost:
        if (r0 < 8) goto L_0x00a2;
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010b A[Catch: all -> 0x012b, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0009, B:12:0x0014, B:15:0x001e, B:18:0x0028, B:20:0x0034, B:22:0x0040, B:24:0x004c, B:26:0x0052, B:27:0x0055, B:29:0x0061, B:30:0x0069, B:32:0x0071, B:34:0x0077, B:36:0x0081, B:38:0x008f, B:40:0x0099, B:41:0x009b, B:44:0x00a2, B:46:0x00aa, B:48:0x00b4, B:50:0x00c6, B:51:0x00d1, B:53:0x00dc, B:55:0x00eb, B:58:0x0100, B:60:0x010b, B:62:0x0118, B:65:0x012e, B:68:0x013a, B:70:0x0142, B:72:0x014b, B:75:0x0159, B:76:0x015e), top: B:77:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0118 A[Catch: Throwable -> 0x0121, all -> 0x012b, TRY_ENTER, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0009, B:12:0x0014, B:15:0x001e, B:18:0x0028, B:20:0x0034, B:22:0x0040, B:24:0x004c, B:26:0x0052, B:27:0x0055, B:29:0x0061, B:30:0x0069, B:32:0x0071, B:34:0x0077, B:36:0x0081, B:38:0x008f, B:40:0x0099, B:41:0x009b, B:44:0x00a2, B:46:0x00aa, B:48:0x00b4, B:50:0x00c6, B:51:0x00d1, B:53:0x00dc, B:55:0x00eb, B:58:0x0100, B:60:0x010b, B:62:0x0118, B:65:0x012e, B:68:0x013a, B:70:0x0142, B:72:0x014b, B:75:0x0159, B:76:0x015e), top: B:77:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0159 A[Catch: all -> 0x012b, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0009, B:12:0x0014, B:15:0x001e, B:18:0x0028, B:20:0x0034, B:22:0x0040, B:24:0x004c, B:26:0x0052, B:27:0x0055, B:29:0x0061, B:30:0x0069, B:32:0x0071, B:34:0x0077, B:36:0x0081, B:38:0x008f, B:40:0x0099, B:41:0x009b, B:44:0x00a2, B:46:0x00aa, B:48:0x00b4, B:50:0x00c6, B:51:0x00d1, B:53:0x00dc, B:55:0x00eb, B:58:0x0100, B:60:0x010b, B:62:0x0118, B:65:0x012e, B:68:0x013a, B:70:0x0142, B:72:0x014b, B:75:0x0159, B:76:0x015e), top: B:77:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x015e A[Catch: all -> 0x012b, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0009, B:12:0x0014, B:15:0x001e, B:18:0x0028, B:20:0x0034, B:22:0x0040, B:24:0x004c, B:26:0x0052, B:27:0x0055, B:29:0x0061, B:30:0x0069, B:32:0x0071, B:34:0x0077, B:36:0x0081, B:38:0x008f, B:40:0x0099, B:41:0x009b, B:44:0x00a2, B:46:0x00aa, B:48:0x00b4, B:50:0x00c6, B:51:0x00d1, B:53:0x00dc, B:55:0x00eb, B:58:0x0100, B:60:0x010b, B:62:0x0118, B:65:0x012e, B:68:0x013a, B:70:0x0142, B:72:0x014b, B:75:0x0159, B:76:0x015e), top: B:77:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void a(java.lang.String r7, java.lang.StringBuilder r8, com.autonavi.aps.amapapi.model.AMapLocationServer r9, android.content.Context r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cf.a(java.lang.String, java.lang.StringBuilder, com.autonavi.aps.amapapi.model.AMapLocationServer, android.content.Context, boolean):void");
    }

    public final synchronized boolean b() {
        return this.d;
    }
}
