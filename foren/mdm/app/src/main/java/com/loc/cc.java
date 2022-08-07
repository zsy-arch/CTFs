package com.loc;

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
import java.util.ArrayList;
import java.util.List;

/* compiled from: CgiManager.java */
@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public final class cc {
    private Context b;
    private TelephonyManager h;
    private ca i;
    private Object j;
    private volatile CellLocation l;
    private int c = 0;
    private ArrayList<cb> d = new ArrayList<>();
    private String e = null;
    private ArrayList<cb> f = new ArrayList<>();
    private int g = -113;
    private long k = 0;
    private int m = 0;
    volatile boolean a = false;
    private String n = null;

    public cc(Context context) {
        this.h = null;
        this.i = null;
        this.b = context;
        if (this.h == null) {
            this.h = (TelephonyManager) cx.a(this.b, "phone");
        }
        this.i = new ca();
    }

    private CellLocation a(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Object a = cs.a(obj, str, objArr);
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
        CdmaCellLocation cdmaCellLocation;
        GsmCellLocation gsmCellLocation;
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList<cb> arrayList = this.f;
        ca caVar = this.i;
        cb cbVar = null;
        int size = list.size();
        if (size != 0) {
            if (arrayList != null) {
                arrayList.clear();
            }
            for (int i = 0; i < size; i++) {
                CellInfo cellInfo = list.get(i);
                if (cellInfo != null) {
                    try {
                        boolean isRegistered = cellInfo.isRegistered();
                        if (cellInfo instanceof CellInfoCdma) {
                            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                            CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
                            if (cellIdentity != null && cellIdentity.getSystemId() > 0 && cellIdentity.getNetworkId() >= 0 && cellIdentity.getBasestationId() >= 0) {
                                cbVar = a(cellInfoCdma, isRegistered);
                                cbVar.l = (short) Math.min(65535L, caVar.a(cbVar));
                                arrayList.add(cbVar);
                            }
                        } else if (cellInfo instanceof CellInfoGsm) {
                            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                            CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                            if (cellIdentity2 != null && a(cellIdentity2.getLac()) && b(cellIdentity2.getCid())) {
                                CellIdentityGsm cellIdentity3 = cellInfoGsm.getCellIdentity();
                                cbVar = a(1, isRegistered, cellIdentity3.getMcc(), cellIdentity3.getMnc(), cellIdentity3.getLac(), cellIdentity3.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
                                cbVar.l = (short) Math.min(65535L, caVar.a(cbVar));
                                arrayList.add(cbVar);
                            }
                        } else if (cellInfo instanceof CellInfoWcdma) {
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                            CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                            if (cellIdentity4 != null && a(cellIdentity4.getLac()) && b(cellIdentity4.getCid())) {
                                CellIdentityWcdma cellIdentity5 = cellInfoWcdma.getCellIdentity();
                                cbVar = a(4, isRegistered, cellIdentity5.getMcc(), cellIdentity5.getMnc(), cellIdentity5.getLac(), cellIdentity5.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
                                cbVar.l = (short) Math.min(65535L, caVar.a(cbVar));
                                arrayList.add(cbVar);
                            }
                        } else if (cellInfo instanceof CellInfoLte) {
                            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                            CellIdentityLte cellIdentity6 = cellInfoLte.getCellIdentity();
                            if (cellIdentity6 != null && a(cellIdentity6.getTac()) && b(cellIdentity6.getCi())) {
                                CellIdentityLte cellIdentity7 = cellInfoLte.getCellIdentity();
                                cbVar = a(3, isRegistered, cellIdentity7.getMcc(), cellIdentity7.getMnc(), cellIdentity7.getTac(), cellIdentity7.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
                                try {
                                    cbVar.l = (short) Math.min(65535L, caVar.a(cbVar));
                                    arrayList.add(cbVar);
                                } catch (Throwable th) {
                                    cbVar = cbVar;
                                }
                            }
                        } else {
                            cbVar = cbVar;
                        }
                    } catch (Throwable th2) {
                    }
                }
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            cdmaCellLocation = null;
            gsmCellLocation = null;
        } else {
            this.c |= 4;
            caVar.a(arrayList);
            cb cbVar2 = arrayList.get(arrayList.size() - 1);
            if (cbVar2 == null || cbVar2.k != 2) {
                gsmCellLocation = new GsmCellLocation();
                gsmCellLocation.setLacAndCid(cbVar.c, cbVar.d);
                cdmaCellLocation = null;
            } else {
                CdmaCellLocation cdmaCellLocation2 = new CdmaCellLocation();
                cdmaCellLocation2.setCellLocationData(cbVar2.i, cbVar2.e, cbVar2.f, cbVar2.g, cbVar2.h);
                cdmaCellLocation = cdmaCellLocation2;
                gsmCellLocation = null;
            }
        }
        return cdmaCellLocation != null ? cdmaCellLocation : gsmCellLocation;
    }

    private static cb a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        cb cbVar = new cb(i, z);
        cbVar.a = i2;
        cbVar.b = i3;
        cbVar.c = i4;
        cbVar.d = i5;
        cbVar.j = i6;
        return cbVar;
    }

    @SuppressLint({"NewApi"})
    private cb a(CellInfoCdma cellInfoCdma, boolean z) {
        int i;
        int i2;
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        String[] a = cx.a(this.h);
        try {
            i = Integer.parseInt(a[0]);
            try {
                i2 = Integer.parseInt(a[1]);
            } catch (Throwable th) {
                i2 = 0;
                cb a2 = a(2, z, i, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
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
        cb a22 = a(2, z, i, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
        a22.g = cellIdentity.getSystemId();
        a22.h = cellIdentity.getNetworkId();
        a22.i = cellIdentity.getBasestationId();
        a22.e = cellIdentity.getLatitude();
        a22.f = cellIdentity.getLongitude();
        return a22;
    }

    private cb a(NeighboringCellInfo neighboringCellInfo) {
        try {
            cb cbVar = new cb(1, false);
            String[] a = cx.a(this.h);
            cbVar.a = Integer.parseInt(a[0]);
            cbVar.b = Integer.parseInt(a[1]);
            cbVar.c = cs.b(neighboringCellInfo, "getLac", new Object[0]);
            cbVar.d = neighboringCellInfo.getCid();
            cbVar.j = cx.a(neighboringCellInfo.getRssi());
            return cbVar;
        } catch (Throwable th) {
            f.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    private void a(CellLocation cellLocation, boolean z) {
        List<NeighboringCellInfo> neighboringCellInfo;
        cb a;
        if (!(cellLocation == null || this.h == null)) {
            this.d.clear();
            if (a(cellLocation)) {
                this.c = 1;
                ArrayList<cb> arrayList = this.d;
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                cb cbVar = new cb(1, true);
                String[] a2 = cx.a(this.h);
                cbVar.a = Integer.parseInt(a2[0]);
                cbVar.b = Integer.parseInt(a2[1]);
                cbVar.c = gsmCellLocation.getLac();
                cbVar.d = gsmCellLocation.getCid();
                cbVar.j = this.g;
                arrayList.add(cbVar);
                if (!(z || (neighboringCellInfo = this.h.getNeighboringCellInfo()) == null || neighboringCellInfo.isEmpty())) {
                    for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                        if (neighboringCellInfo2 != null && a(neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid()) && (a = a(neighboringCellInfo2)) != null && !this.d.contains(a)) {
                            this.d.add(a);
                        }
                    }
                }
            }
        }
    }

    private static boolean a(int i) {
        return (i == -1 || i == 0 || i > 65535) ? false : true;
    }

    private static boolean a(int i, int i2) {
        return (i == -1 || i == 0 || i > 65535 || i2 == -1 || i2 == 0 || i2 == 65535 || i2 >= 268435455) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        if (com.loc.cs.b(r6, "getBaseStationId", new java.lang.Object[0]) < 0) goto L_0x004f;
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
            boolean r2 = r5.a
            android.content.Context r3 = r5.b
            int r2 = com.loc.cx.a(r2, r6)
            switch(r2) {
                case 1: goto L_0x0016;
                case 2: goto L_0x002e;
                default: goto L_0x0010;
            }
        L_0x0010:
            if (r0 != 0) goto L_0x0014
            r5.c = r1
        L_0x0014:
            r1 = r0
            goto L_0x0003
        L_0x0016:
            android.telephony.gsm.GsmCellLocation r6 = (android.telephony.gsm.GsmCellLocation) r6     // Catch: Throwable -> 0x0025
            int r2 = r6.getLac()     // Catch: Throwable -> 0x0025
            int r3 = r6.getCid()     // Catch: Throwable -> 0x0025
            boolean r0 = a(r2, r3)     // Catch: Throwable -> 0x0025
            goto L_0x0010
        L_0x0025:
            r2 = move-exception
            java.lang.String r3 = "CgiManager"
            java.lang.String r4 = "cgiUseful Cgi.iGsmT"
            com.loc.f.a(r2, r3, r4)
            goto L_0x0010
        L_0x002e:
            java.lang.String r2 = "getSystemId"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: Throwable -> 0x0051
            int r2 = com.loc.cs.b(r6, r2, r3)     // Catch: Throwable -> 0x0051
            if (r2 <= 0) goto L_0x004f
            java.lang.String r2 = "getNetworkId"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: Throwable -> 0x0051
            int r2 = com.loc.cs.b(r6, r2, r3)     // Catch: Throwable -> 0x0051
            if (r2 < 0) goto L_0x004f
            java.lang.String r2 = "getBaseStationId"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: Throwable -> 0x0051
            int r2 = com.loc.cs.b(r6, r2, r3)     // Catch: Throwable -> 0x0051
            if (r2 >= 0) goto L_0x0010
        L_0x004f:
            r0 = r1
            goto L_0x0010
        L_0x0051:
            r2 = move-exception
            java.lang.String r3 = "CgiManager"
            java.lang.String r4 = "cgiUseful Cgi.iCdmaT"
            com.loc.f.a(r2, r3, r4)
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cc.a(android.telephony.CellLocation):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a0, code lost:
        if (r0 != false) goto L_0x0041;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void b(boolean r8, boolean r9) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cc.b(boolean, boolean):void");
    }

    private static boolean b(int i) {
        return (i == -1 || i == 0 || i == 65535 || i >= 268435455) ? false : true;
    }

    @SuppressLint({"NewApi"})
    private CellLocation n() {
        CellLocation cellLocation = null;
        TelephonyManager telephonyManager = this.h;
        if (telephonyManager == null) {
            return null;
        }
        CellLocation f = f();
        if (cx.c() >= 18) {
            try {
                cellLocation = a(telephonyManager.getAllCellInfo());
            } catch (SecurityException e) {
                this.n = e.getMessage();
            }
        }
        if (a(f)) {
            return f;
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

    private CellLocation o() {
        Object cast;
        CellLocation cellLocation = null;
        Object obj = this.j;
        if (obj != null) {
            try {
                Class<?> p = p();
                if (p.isInstance(obj) && (cellLocation = a((cast = p.cast(obj)), "getCellLocation", new Object[0])) == null && (cellLocation = a(cast, "getCellLocation", 1)) == null && (cellLocation = a(cast, "getCellLocationGemini", 1)) == null) {
                    cellLocation = a(cast, "getAllCellInfo", 1);
                    if (cellLocation != null) {
                    }
                }
            } catch (Throwable th) {
                f.a(th, "CgiManager", "getSim2Cgi");
            }
        }
        return cellLocation;
    }

    private Class<?> p() {
        String str;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        switch (this.m) {
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
            f.a(th, "CgiManager", "getSim2TmClass");
            return null;
        }
    }

    private int q() {
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            this.m = 1;
        } catch (Throwable th) {
        }
        if (this.m == 0) {
            try {
                Class.forName("android.telephony.TelephonyManager2");
                this.m = 2;
            } catch (Throwable th2) {
            }
        }
        return this.m;
    }

    public final void a() {
        if (this.h != null) {
            try {
                boolean z = this.a;
                CellLocation cellLocation = this.h.getCellLocation();
                Context context = this.b;
                this.c = cx.a(z, cellLocation);
            } catch (SecurityException e) {
                this.n = e.getMessage();
            } catch (Throwable th) {
                this.n = null;
                f.a(th, "CgiManager", "CgiManager");
                this.c = 0;
            }
            try {
                this.m = q();
                switch (this.m) {
                    case 1:
                        this.j = cx.a(this.b, "phone_msim");
                        break;
                    case 2:
                        this.j = cx.a(this.b, "phone2");
                        break;
                    default:
                        this.j = cx.a(this.b, "phone2");
                        break;
                }
            } catch (Throwable th2) {
            }
        }
    }

    public final void a(boolean z, boolean z2) {
        boolean z3 = false;
        z3 = true;
        if (!z) {
            try {
                if (cx.b() - this.k >= 10000) {
                }
            } catch (SecurityException e) {
                this.n = e.getMessage();
                return;
            } catch (Throwable th) {
                f.a(th, "CgiManager", "refresh");
                return;
            }
        }
        if (z3) {
            b(z, z2);
            this.k = cx.b();
        }
    }

    public final ArrayList<cb> b() {
        return this.d;
    }

    public final cb c() {
        ArrayList<cb> arrayList = this.d;
        if (arrayList.size() > 0) {
            return arrayList.get(0);
        }
        return null;
    }

    public final int d() {
        return this.c;
    }

    public final int e() {
        return this.c & 3;
    }

    public final CellLocation f() {
        if (this.h != null) {
            try {
                CellLocation cellLocation = this.h.getCellLocation();
                this.n = null;
                if (a(cellLocation)) {
                    this.l = cellLocation;
                    return cellLocation;
                }
            } catch (SecurityException e) {
                this.n = e.getMessage();
            } catch (Throwable th) {
                this.n = null;
                f.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    public final TelephonyManager g() {
        return this.h;
    }

    public final void h() {
        this.n = null;
        this.l = null;
        this.c = 0;
        this.d.clear();
    }

    public final void i() {
        this.i.a();
        this.d.clear();
        this.g = -113;
        this.k = 0L;
        this.h = null;
        this.j = null;
    }

    public final void j() {
        switch (this.c) {
            case 1:
                if (this.d.isEmpty()) {
                    this.c = 0;
                    return;
                }
                return;
            case 2:
                if (this.d.isEmpty()) {
                    this.c = 0;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final String k() {
        return this.n;
    }

    public final String l() {
        return this.e;
    }

    public final ArrayList<cb> m() {
        return this.f;
    }
}
