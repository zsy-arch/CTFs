package com.tencent.map.b;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public final class d {
    private Context a = null;
    private TelephonyManager b = null;
    private a c = null;
    private c d = null;
    private b e = null;
    private boolean f = false;
    private List<NeighboringCellInfo> g = new LinkedList();
    private byte[] h = new byte[0];
    private byte[] i = new byte[0];
    private boolean j = false;

    /* loaded from: classes2.dex */
    public class a extends PhoneStateListener {
        private int a;
        private int b;
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = -1;
        private int g = Integer.MAX_VALUE;
        private int h = Integer.MAX_VALUE;
        private Method i = null;
        private Method j = null;
        private Method k = null;
        private Method l = null;
        private Method m = null;

        public a(int i, int i2) {
            this.a = 0;
            this.b = 0;
            this.b = i;
            this.a = i2;
        }

        /* JADX WARN: Removed duplicated region for block: B:47:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0039  */
        @Override // android.telephony.PhoneStateListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onCellLocationChanged(android.telephony.CellLocation r12) {
            /*
                Method dump skipped, instructions count: 372
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.d.a.onCellLocationChanged(android.telephony.CellLocation):void");
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthChanged(int i) {
            if (this.a == 1) {
                d.c(d.this);
            }
            if (Math.abs(i - ((this.f + 113) / 2)) <= 3) {
                return;
            }
            if (this.f == -1) {
                this.f = (i << 1) - 113;
                return;
            }
            this.f = (i << 1) - 113;
            d.this.e = new b(d.this, this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            if (d.this.d != null) {
                d.this.d.a(d.this.e);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Cloneable {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;

        public b(d dVar, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = Integer.MAX_VALUE;
            this.h = Integer.MAX_VALUE;
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
            this.g = i7;
            this.h = i8;
        }

        public final Object clone() {
            try {
                return (b) super.clone();
            } catch (Exception e) {
                return null;
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface c {
        void a(b bVar);
    }

    private int a(int i) {
        int i2;
        String networkOperator = this.b.getNetworkOperator();
        if (networkOperator != null && networkOperator.length() >= 3) {
            try {
                i2 = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
            } catch (Exception e) {
            }
            if (i == 2 || i2 != -1) {
                return i2;
            }
            return 0;
        }
        i2 = -1;
        if (i == 2) {
        }
        return i2;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.tencent.map.b.d$1] */
    static /* synthetic */ void c(d dVar) {
        if (!dVar.j) {
            dVar.j = true;
            new Thread() { // from class: com.tencent.map.b.d.1
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    if (d.this.b != null) {
                        List neighboringCellInfo = d.this.b.getNeighboringCellInfo();
                        synchronized (d.this.i) {
                            if (neighboringCellInfo != null) {
                                d.this.g.clear();
                                d.this.g.addAll(neighboringCellInfo);
                            }
                        }
                    }
                    d.this.j = false;
                }
            }.start();
        }
    }

    public final void a() {
        synchronized (this.h) {
            if (this.f) {
                if (!(this.b == null || this.c == null)) {
                    try {
                        this.b.listen(this.c, 0);
                    } catch (Exception e) {
                        this.f = false;
                    }
                }
                this.f = false;
            }
        }
    }

    public final boolean a(Context context, c cVar) {
        synchronized (this.h) {
            if (this.f) {
                return true;
            }
            if (context == null || cVar == null) {
                return false;
            }
            this.a = context;
            this.d = cVar;
            try {
                this.b = (TelephonyManager) this.a.getSystemService("phone");
                if (this.b == null) {
                    return false;
                }
                int phoneType = this.b.getPhoneType();
                this.c = new a(a(phoneType), phoneType);
                if (this.c == null) {
                    return false;
                }
                this.b.listen(this.c, 18);
                this.f = true;
                return this.f;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public final List<NeighboringCellInfo> b() {
        LinkedList linkedList = null;
        synchronized (this.i) {
            if (this.g != null) {
                linkedList = new LinkedList();
                linkedList.addAll(this.g);
            }
        }
        return linkedList;
    }
}
