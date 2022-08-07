package com.loc;

import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: CellAgeEstimator.java */
/* loaded from: classes2.dex */
public final class ca {
    private HashMap<Long, cb> a = new HashMap<>();
    private long b = 0;

    private static long a(int i, int i2) {
        return ((i & 65535) << 32) | (i2 & 65535);
    }

    public final long a(cb cbVar) {
        long a;
        if (cbVar == null || !cbVar.o) {
            return 0L;
        }
        HashMap<Long, cb> hashMap = this.a;
        switch (cbVar.k) {
            case 1:
            case 3:
            case 4:
                a = a(cbVar.a(), cbVar.b());
                break;
            case 2:
                a = a(cbVar.c(), cbVar.d());
                break;
            default:
                a = 0;
                break;
        }
        cb cbVar2 = hashMap.get(Long.valueOf(a));
        if (cbVar2 == null) {
            cbVar.m = cx.b();
            hashMap.put(Long.valueOf(a), cbVar);
            return 0L;
        } else if (cbVar2.e() != cbVar.e()) {
            cbVar.m = cx.b();
            hashMap.put(Long.valueOf(a), cbVar);
            return 0L;
        } else {
            cbVar.m = cbVar2.m;
            hashMap.put(Long.valueOf(a), cbVar);
            return (cx.b() - cbVar2.m) / 1000;
        }
    }

    public final void a() {
        this.a.clear();
        this.b = 0L;
    }

    public final void a(ArrayList<? extends cb> arrayList) {
        long j = 0;
        if (arrayList != null) {
            long b = cx.b();
            if (this.b <= 0 || b - this.b >= 60000) {
                HashMap<Long, cb> hashMap = this.a;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    cb cbVar = (cb) arrayList.get(i);
                    if (cbVar.o) {
                        switch (cbVar.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(cbVar.c, cbVar.d);
                                break;
                            case 2:
                                j = a(cbVar.h, cbVar.i);
                                break;
                        }
                        cb cbVar2 = hashMap.get(Long.valueOf(j));
                        if (cbVar2 != null) {
                            if (cbVar2.e() == cbVar.e()) {
                                cbVar.m = cbVar2.m;
                            } else {
                                cbVar.m = b;
                            }
                        }
                    }
                }
                hashMap.clear();
                int size2 = arrayList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    cb cbVar3 = (cb) arrayList.get(i2);
                    if (cbVar3.o) {
                        switch (cbVar3.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(cbVar3.a(), cbVar3.b());
                                break;
                            case 2:
                                j = a(cbVar3.c(), cbVar3.d());
                                break;
                        }
                        hashMap.put(Long.valueOf(j), cbVar3);
                    }
                }
                this.b = b;
            }
        }
    }
}
