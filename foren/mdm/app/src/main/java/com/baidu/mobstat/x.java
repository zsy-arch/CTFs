package com.baidu.mobstat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class x extends Enum<x> {
    public static final x a = new y("AP_LIST", 0, 0);
    public static final x b = new z("APP_LIST", 1, 1);
    public static final x c = new aa("APP_TRACE", 2, 2);
    public static final x d = new ab("APP_CHANGE", 3, 3);
    public static final x e = new ac("APP_APK", 4, 4);
    private static final /* synthetic */ x[] g = {a, b, c, d, e};
    private int f;

    private x(String str, int i, int i2) {
        super(str, i);
        this.f = i2;
    }

    public /* synthetic */ x(String str, int i, int i2, y yVar) {
        this(str, i, i2);
    }

    private int a(List<String> list, ArrayList<Long> arrayList, ArrayList<v> arrayList2, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        int c2 = c();
        int i5 = i2;
        while (c2 > 0) {
            int i6 = c2 < i5 ? c2 : i5;
            ArrayList<v> a2 = a(i6, i4);
            if (i4 == 0 && a2.size() != 0) {
                arrayList2.add(a2.get(0));
            }
            Iterator<v> it = a2.iterator();
            while (it.hasNext()) {
                v next = it.next();
                long a3 = next.a();
                String b2 = next.b();
                int length = b2.length();
                if (i3 + length > i) {
                    break;
                }
                arrayList.add(Long.valueOf(a3));
                list.add(b2);
                i3 += length;
            }
            c2 -= i6;
            i4 += i6;
            i5 = i6;
        }
        return i3;
    }

    private int c() {
        w wVar = null;
        try {
            try {
                wVar = a();
            } catch (Exception e2) {
                bb.b(e2);
                if (wVar != null) {
                    wVar.close();
                }
            }
            if (wVar.a()) {
                int b2 = wVar.b();
            }
            if (wVar != null) {
                wVar.close();
            }
            return 0;
        } finally {
            if (wVar != null) {
                wVar.close();
            }
        }
    }

    public static x valueOf(String str) {
        return (x) Enum.valueOf(x.class, str);
    }

    public static x[] values() {
        return (x[]) g.clone();
    }

    public synchronized int a(ArrayList<Long> arrayList) {
        int i = 0;
        synchronized (this) {
            if (arrayList != null) {
                if (arrayList.size() != 0) {
                    w wVar = null;
                    try {
                        wVar = a();
                    } catch (Exception e2) {
                        bb.b(e2);
                        if (wVar != null) {
                            wVar.close();
                        }
                    }
                    if (wVar.a()) {
                        int size = arrayList.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            if (wVar.b(arrayList.get(i2).longValue())) {
                                i++;
                            } else if (wVar != null) {
                                wVar.close();
                            }
                        }
                        if (wVar != null) {
                            wVar.close();
                            i = i;
                        } else {
                            i = i;
                        }
                    } else if (wVar != null) {
                        wVar.close();
                    }
                }
            }
        }
        return i;
    }

    public synchronized long a(long j, String str) {
        long j2;
        j2 = -1;
        w wVar = null;
        try {
            wVar = a();
            if (wVar.a()) {
                j2 = wVar.a(String.valueOf(j), str);
                if (wVar != null) {
                    wVar.close();
                }
            } else if (wVar != null) {
                wVar.close();
            }
        } catch (Exception e2) {
            bb.b(e2);
            if (wVar != null) {
                wVar.close();
            }
        }
        return j2;
    }

    public abstract w a();

    public synchronized ArrayList<v> a(int i, int i2) {
        ArrayList<v> arrayList;
        arrayList = new ArrayList<>();
        w wVar = null;
        try {
            wVar = a();
            if (wVar.a()) {
                arrayList = wVar.a(i, i2);
                if (wVar != null) {
                    wVar.close();
                }
            } else if (wVar != null) {
                wVar.close();
            }
        } catch (Exception e2) {
            bb.b(e2);
            if (wVar != null) {
                wVar.close();
            }
        }
        return arrayList;
    }

    public synchronized List<String> a(int i) {
        List<String> arrayList;
        arrayList = new ArrayList<>();
        ArrayList<Long> arrayList2 = new ArrayList<>();
        ArrayList<v> arrayList3 = new ArrayList<>();
        a(arrayList, arrayList2, arrayList3, i, 500);
        if (arrayList3.size() != 0 && arrayList.size() == 0 && arrayList2.size() == 0) {
            v vVar = arrayList3.get(0);
            long a2 = vVar.a();
            String b2 = vVar.b();
            arrayList2.add(Long.valueOf(a2));
            arrayList.add(b2);
        }
        int a3 = a(arrayList2);
        if (a3 != arrayList.size()) {
            arrayList = arrayList.subList(0, a3);
        }
        return arrayList;
    }

    public synchronized boolean b() {
        return c() == 0;
    }

    public synchronized boolean b(int i) {
        return c() >= i;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.f);
    }
}
