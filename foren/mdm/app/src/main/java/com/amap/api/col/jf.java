package com.amap.api.col;

import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: CellAgeEstimator.java */
/* loaded from: classes.dex */
public final class jf {
    private HashMap<Long, jg> a = new HashMap<>();
    private long b = 0;

    private static long a(int i, int i2) {
        return ((i & 65535) << 32) | (i2 & 65535);
    }

    public final long a(jg jgVar) {
        long a;
        if (jgVar == null || !jgVar.o) {
            return 0L;
        }
        HashMap<Long, jg> hashMap = this.a;
        switch (jgVar.k) {
            case 1:
            case 3:
            case 4:
                a = a(jgVar.a(), jgVar.b());
                break;
            case 2:
                a = a(jgVar.c(), jgVar.d());
                break;
            default:
                a = 0;
                break;
        }
        jg jgVar2 = hashMap.get(Long.valueOf(a));
        if (jgVar2 == null) {
            jgVar.m = jq.b();
            hashMap.put(Long.valueOf(a), jgVar);
            return 0L;
        } else if (jgVar2.e() != jgVar.e()) {
            jgVar.m = jq.b();
            hashMap.put(Long.valueOf(a), jgVar);
            return 0L;
        } else {
            jgVar.m = jgVar2.m;
            hashMap.put(Long.valueOf(a), jgVar);
            return (jq.b() - jgVar2.m) / 1000;
        }
    }

    public final void a() {
        this.a.clear();
        this.b = 0L;
    }

    public final void a(ArrayList<? extends jg> arrayList) {
        long j = 0;
        if (arrayList != null) {
            long b = jq.b();
            if (this.b <= 0 || b - this.b >= 60000) {
                HashMap<Long, jg> hashMap = this.a;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    jg jgVar = (jg) arrayList.get(i);
                    if (jgVar.o) {
                        switch (jgVar.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(jgVar.c, jgVar.d);
                                break;
                            case 2:
                                j = a(jgVar.h, jgVar.i);
                                break;
                        }
                        jg jgVar2 = hashMap.get(Long.valueOf(j));
                        if (jgVar2 != null) {
                            if (jgVar2.e() == jgVar.e()) {
                                jgVar.m = jgVar2.m;
                            } else {
                                jgVar.m = b;
                            }
                        }
                    }
                }
                hashMap.clear();
                int size2 = arrayList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    jg jgVar3 = (jg) arrayList.get(i2);
                    if (jgVar3.o) {
                        switch (jgVar3.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(jgVar3.a(), jgVar3.b());
                                break;
                            case 2:
                                j = a(jgVar3.c(), jgVar3.d());
                                break;
                        }
                        hashMap.put(Long.valueOf(j), jgVar3);
                    }
                }
                this.b = b;
            }
        }
    }
}
