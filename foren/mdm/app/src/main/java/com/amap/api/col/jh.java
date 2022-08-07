package com.amap.api.col;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CgiManager.java */
@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public final class jh {
    static CellLocation e;
    TelephonyManager c;
    private Context i;
    private jf m;
    private Object n;
    static ArrayList<jg> b = new ArrayList<>();
    private static ArrayList<jg> k = new ArrayList<>();
    static long d = 0;
    int a = 0;
    private String j = null;
    private int l = -113;
    private int o = 0;
    String f = null;
    boolean g = false;
    StringBuilder h = null;

    public jh(Context context) {
        this.c = null;
        this.m = null;
        this.i = context;
        if (this.c == null) {
            this.c = (TelephonyManager) jq.a(this.i, "phone");
        }
        i();
        this.m = new jf();
    }

    private CellLocation a(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Object a = jo.a(obj, str, objArr);
            CellLocation cellLocation = a != null ? (CellLocation) a : null;
            if (a(cellLocation)) {
                return cellLocation;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private CellLocation a(List<CellInfo> list) {
        jg jgVar;
        CdmaCellLocation cdmaCellLocation;
        GsmCellLocation gsmCellLocation;
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList<jg> arrayList = k;
        jf jfVar = this.m;
        int size = list.size();
        if (size != 0) {
            if (arrayList != null) {
                arrayList.clear();
            }
            jgVar = null;
            for (int i = 0; i < size; i++) {
                CellInfo cellInfo = list.get(i);
                if (cellInfo != null) {
                    try {
                        boolean isRegistered = cellInfo.isRegistered();
                        if (cellInfo instanceof CellInfoCdma) {
                            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                            if (a(cellInfoCdma.getCellIdentity())) {
                                jgVar = a(cellInfoCdma, isRegistered);
                                jgVar.l = (short) Math.min(65535L, jfVar.a(jgVar));
                                arrayList.add(jgVar);
                            }
                        } else if (cellInfo instanceof CellInfoGsm) {
                            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                            if (a(cellInfoGsm.getCellIdentity())) {
                                jgVar = a(cellInfoGsm, isRegistered);
                                jgVar.l = (short) Math.min(65535L, jfVar.a(jgVar));
                                arrayList.add(jgVar);
                            }
                        } else if (cellInfo instanceof CellInfoWcdma) {
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                            if (a(cellInfoWcdma.getCellIdentity())) {
                                jgVar = a(cellInfoWcdma, isRegistered);
                                jgVar.l = (short) Math.min(65535L, jfVar.a(jgVar));
                                arrayList.add(jgVar);
                            }
                        } else if (cellInfo instanceof CellInfoLte) {
                            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                            if (a(cellInfoLte.getCellIdentity())) {
                                jgVar = a(cellInfoLte, isRegistered);
                                try {
                                    jgVar.l = (short) Math.min(65535L, jfVar.a(jgVar));
                                    arrayList.add(jgVar);
                                } catch (Throwable th) {
                                    jgVar = jgVar;
                                }
                            }
                        } else {
                            jgVar = jgVar;
                        }
                    } catch (Throwable th2) {
                    }
                }
            }
        } else {
            jgVar = null;
        }
        if (arrayList == null || arrayList.size() <= 0) {
            cdmaCellLocation = null;
            gsmCellLocation = null;
        } else {
            this.a |= 4;
            jfVar.a(arrayList);
            jg jgVar2 = arrayList.get(arrayList.size() - 1);
            if (jgVar2 == null || jgVar2.k != 2) {
                gsmCellLocation = new GsmCellLocation();
                gsmCellLocation.setLacAndCid(jgVar.c, jgVar.d);
                cdmaCellLocation = null;
            } else {
                CdmaCellLocation cdmaCellLocation2 = new CdmaCellLocation();
                cdmaCellLocation2.setCellLocationData(jgVar2.i, jgVar2.e, jgVar2.f, jgVar2.g, jgVar2.h);
                cdmaCellLocation = cdmaCellLocation2;
                gsmCellLocation = null;
            }
        }
        return cdmaCellLocation != null ? cdmaCellLocation : gsmCellLocation;
    }

    private static jg a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        jg jgVar = new jg(i, z);
        jgVar.a = i2;
        jgVar.b = i3;
        jgVar.c = i4;
        jgVar.d = i5;
        jgVar.j = i6;
        return jgVar;
    }

    @SuppressLint({"NewApi"})
    private jg a(CellInfoCdma cellInfoCdma, boolean z) {
        int i;
        int i2;
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        String[] a = jq.a(this.c);
        try {
            i = Integer.parseInt(a[0]);
            try {
                i2 = Integer.parseInt(a[1]);
            } catch (Throwable th) {
                i2 = 0;
                jg a2 = a(2, z, i, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
                a2.g = cellIdentity.getSystemId();
                a2.h = cellIdentity.getNetworkId();
                a2.i = cellIdentity.getBasestationId();
                a2.e = cellIdentity.getLatitude();
                a2.f = cellIdentity.getLongitude();
                return a2;
            }
        } catch (Throwable th2) {
            i = 0;
        }
        jg a22 = a(2, z, i, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
        a22.g = cellIdentity.getSystemId();
        a22.h = cellIdentity.getNetworkId();
        a22.i = cellIdentity.getBasestationId();
        a22.e = cellIdentity.getLatitude();
        a22.f = cellIdentity.getLongitude();
        return a22;
    }

    @SuppressLint({"NewApi"})
    private static jg a(CellInfoGsm cellInfoGsm, boolean z) {
        CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
        return a(1, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
    }

    @SuppressLint({"NewApi"})
    private static jg a(CellInfoLte cellInfoLte, boolean z) {
        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
        return a(3, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getTac(), cellIdentity.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
    }

    @SuppressLint({"NewApi"})
    private static jg a(CellInfoWcdma cellInfoWcdma, boolean z) {
        CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
        return a(4, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
    }

    private static jg a(NeighboringCellInfo neighboringCellInfo, String[] strArr) {
        try {
            jg jgVar = new jg(1, false);
            jgVar.a = Integer.parseInt(strArr[0]);
            jgVar.b = Integer.parseInt(strArr[1]);
            jgVar.c = jo.b(neighboringCellInfo, "getLac", new Object[0]);
            jgVar.d = neighboringCellInfo.getCid();
            jgVar.j = jq.a(neighboringCellInfo.getRssi());
            return jgVar;
        } catch (Throwable th) {
            jn.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    public static ArrayList<jg> a() {
        return b;
    }

    private void a(CellLocation cellLocation, String[] strArr) {
        jg a;
        if (!(cellLocation == null || this.c == null)) {
            b.clear();
            if (a(cellLocation)) {
                this.a = 1;
                b.add(c(cellLocation, strArr));
                List<NeighboringCellInfo> neighboringCellInfo = this.c.getNeighboringCellInfo();
                if (!(neighboringCellInfo == null || neighboringCellInfo.isEmpty())) {
                    for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                        if (neighboringCellInfo2 != null && a(neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid()) && (a = a(neighboringCellInfo2, strArr)) != null && !b.contains(a)) {
                            b.add(a);
                        }
                    }
                }
            }
        }
    }

    public static boolean a(int i) {
        return i > 0 && i <= 15;
    }

    private static boolean a(int i, int i2) {
        return (i == -1 || i == 0 || i > 65535 || i2 == -1 || i2 == 0 || i2 == 65535 || i2 >= 268435455) ? false : true;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityCdma cellIdentityCdma) {
        return cellIdentityCdma != null && cellIdentityCdma.getSystemId() > 0 && cellIdentityCdma.getNetworkId() >= 0 && cellIdentityCdma.getBasestationId() >= 0;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityGsm cellIdentityGsm) {
        return cellIdentityGsm != null && b(cellIdentityGsm.getLac()) && c(cellIdentityGsm.getCid());
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityLte cellIdentityLte) {
        return cellIdentityLte != null && b(cellIdentityLte.getTac()) && c(cellIdentityLte.getCi());
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityWcdma cellIdentityWcdma) {
        return cellIdentityWcdma != null && b(cellIdentityWcdma.getLac()) && c(cellIdentityWcdma.getCid());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        if (com.amap.api.col.jo.b(r6, "getBaseStationId", new java.lang.Object[0]) < 0) goto L_0x004d;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(android.telephony.CellLocation r6) {
        /*
            r5 = this;
            r1 = 0
            if (r6 != 0) goto L_0x0004
        L_0x0003:
            return r1
        L_0x0004:
            r0 = 1
            android.content.Context r2 = r5.i
            int r2 = r5.b(r6)
            switch(r2) {
                case 1: goto L_0x0014;
                case 2: goto L_0x002c;
                default: goto L_0x000e;
            }
        L_0x000e:
            if (r0 != 0) goto L_0x0012
            r5.a = r1
        L_0x0012:
            r1 = r0
            goto L_0x0003
        L_0x0014:
            android.telephony.gsm.GsmCellLocation r6 = (android.telephony.gsm.GsmCellLocation) r6     // Catch: Throwable -> 0x0023
            int r2 = r6.getLac()     // Catch: Throwable -> 0x0023
            int r3 = r6.getCid()     // Catch: Throwable -> 0x0023
            boolean r0 = a(r2, r3)     // Catch: Throwable -> 0x0023
            goto L_0x000e
        L_0x0023:
            r2 = move-exception
            java.lang.String r3 = "CgiManager"
            java.lang.String r4 = "cgiUseful Cgi.iGsmT"
            com.amap.api.col.jn.a(r2, r3, r4)
            goto L_0x000e
        L_0x002c:
            java.lang.String r2 = "getSystemId"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: Throwable -> 0x004f
            int r2 = com.amap.api.col.jo.b(r6, r2, r3)     // Catch: Throwable -> 0x004f
            if (r2 <= 0) goto L_0x004d
            java.lang.String r2 = "getNetworkId"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: Throwable -> 0x004f
            int r2 = com.amap.api.col.jo.b(r6, r2, r3)     // Catch: Throwable -> 0x004f
            if (r2 < 0) goto L_0x004d
            java.lang.String r2 = "getBaseStationId"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: Throwable -> 0x004f
            int r2 = com.amap.api.col.jo.b(r6, r2, r3)     // Catch: Throwable -> 0x004f
            if (r2 >= 0) goto L_0x000e
        L_0x004d:
            r0 = r1
            goto L_0x000e
        L_0x004f:
            r2 = move-exception
            java.lang.String r3 = "CgiManager"
            java.lang.String r4 = "cgiUseful Cgi.iCdmaT"
            com.amap.api.col.jn.a(r2, r3, r4)
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.jh.a(android.telephony.CellLocation):boolean");
    }

    private int b(CellLocation cellLocation) {
        if (this.g || cellLocation == null) {
            return 0;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            jn.a(th, "Utils", "getCellLocT");
            return 0;
        }
    }

    public static ArrayList<jg> b() {
        return k;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(android.telephony.CellLocation r6, java.lang.String[] r7) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.jh.b(android.telephony.CellLocation, java.lang.String[]):void");
    }

    private static boolean b(int i) {
        return (i == -1 || i == 0 || i > 65535) ? false : true;
    }

    private jg c(CellLocation cellLocation, String[] strArr) {
        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
        jg jgVar = new jg(1, true);
        jgVar.a = Integer.parseInt(strArr[0]);
        jgVar.b = Integer.parseInt(strArr[1]);
        jgVar.c = gsmCellLocation.getLac();
        jgVar.d = gsmCellLocation.getCid();
        jgVar.j = this.l;
        return jgVar;
    }

    private static boolean c(int i) {
        return (i == -1 || i == 0 || i == 65535 || i >= 268435455) ? false : true;
    }

    private void i() {
        if (this.c != null) {
            try {
                CellLocation cellLocation = this.c.getCellLocation();
                Context context = this.i;
                this.a = b(cellLocation);
            } catch (SecurityException e2) {
                this.f = e2.getMessage();
            } catch (Throwable th) {
                this.f = null;
                jn.a(th, "CgiManager", "CgiManager");
                this.a = 0;
            }
            try {
                this.o = s();
                switch (this.o) {
                    case 1:
                        this.n = jq.a(this.i, "phone_msim");
                        break;
                    case 2:
                        this.n = jq.a(this.i, "phone2");
                        break;
                    default:
                        this.n = jq.a(this.i, "phone2");
                        break;
                }
            } catch (Throwable th2) {
            }
        }
    }

    private CellLocation j() {
        if (this.c != null) {
            try {
                CellLocation cellLocation = this.c.getCellLocation();
                this.f = null;
                if (a(cellLocation)) {
                    e = cellLocation;
                    return cellLocation;
                }
            } catch (SecurityException e2) {
                this.f = e2.getMessage();
            } catch (Throwable th) {
                this.f = null;
                jn.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    private boolean k() {
        return !this.g && jq.b() - d >= 10000;
    }

    private void l() {
        r();
    }

    private void m() {
        switch (this.a) {
            case 1:
                if (b.isEmpty()) {
                    this.a = 0;
                    return;
                }
                return;
            case 2:
                if (b.isEmpty()) {
                    this.a = 0;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void n() {
        if (!this.g && this.c != null) {
            CellLocation o = o();
            if (!a(o)) {
                o = p();
            }
            if (a(o)) {
                e = o;
            }
        }
        if (a(e)) {
            String[] a = jq.a(this.c);
            CellLocation cellLocation = e;
            Context context = this.i;
            switch (b(cellLocation)) {
                case 1:
                    a(e, a);
                    break;
                case 2:
                    b(e, a);
                    break;
            }
        }
        if (this.c != null) {
            this.j = this.c.getNetworkOperator();
            if (!TextUtils.isEmpty(this.j)) {
                this.a |= 8;
            }
        }
    }

    @SuppressLint({"NewApi"})
    private CellLocation o() {
        CellLocation cellLocation = null;
        TelephonyManager telephonyManager = this.c;
        if (telephonyManager == null) {
            return null;
        }
        CellLocation j = j();
        if (a(j)) {
            return j;
        }
        if (jq.c() >= 18) {
            try {
                cellLocation = a(telephonyManager.getAllCellInfo());
            } catch (SecurityException e2) {
                this.f = e2.getMessage();
            }
        }
        if (cellLocation != null) {
            return cellLocation;
        }
        CellLocation a = a(telephonyManager, "getCellLocationExt", 1);
        if (a != null) {
            return a;
        }
        CellLocation a2 = a(telephonyManager, "getCellLocationGemini", 1);
        if (a2 != null) {
        }
        return a2;
    }

    private CellLocation p() {
        Object cast;
        CellLocation cellLocation = null;
        Object obj = this.n;
        if (obj != null) {
            try {
                Class<?> q = q();
                if (q.isInstance(obj) && (cellLocation = a((cast = q.cast(obj)), "getCellLocation", new Object[0])) == null && (cellLocation = a(cast, "getCellLocation", 1)) == null && (cellLocation = a(cast, "getCellLocationGemini", 1)) == null) {
                    cellLocation = a(cast, "getAllCellInfo", 1);
                    if (cellLocation != null) {
                    }
                }
            } catch (Throwable th) {
                jn.a(th, "CgiManager", "getSim2Cgi");
            }
        }
        return cellLocation;
    }

    private Class<?> q() {
        String str;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        switch (this.o) {
            case 0:
                str = "android.telephony.TelephonyManager";
                break;
            case 1:
                str = "android.telephony.MSimTelephonyManager";
                break;
            case 2:
                str = "android.telephony.TelephonyManager2";
                break;
            default:
                str = null;
                break;
        }
        try {
            return systemClassLoader.loadClass(str);
        } catch (Throwable th) {
            jn.a(th, "CgiManager", "getSim2TmClass");
            return null;
        }
    }

    private void r() {
        this.f = null;
        e = null;
        this.a = 0;
        b.clear();
    }

    private int s() {
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            this.o = 1;
        } catch (Throwable th) {
        }
        if (this.o == 0) {
            try {
                Class.forName("android.telephony.TelephonyManager2");
                this.o = 2;
            } catch (Throwable th2) {
            }
        }
        return this.o;
    }

    public final int c() {
        return this.a;
    }

    public final int d() {
        return this.a & 3;
    }

    public final TelephonyManager e() {
        return this.c;
    }

    public final void f() {
        try {
            this.g = jq.a(this.i);
            if (k()) {
                n();
                d = jq.b();
            }
            if (this.g) {
                l();
            } else {
                m();
            }
        } catch (SecurityException e2) {
            this.f = e2.getMessage();
        } catch (Throwable th) {
            jn.a(th, "CgiManager", "refresh");
        }
    }

    public final void g() {
        this.m.a();
        this.l = -113;
        this.c = null;
        this.n = null;
    }

    public final String h() {
        return this.j;
    }
}
