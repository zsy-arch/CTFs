package c.i.a;

import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import androidx.fragment.app.Fragment;
import c.c.b;
import c.c.h;
import c.e.a.d;
import c.e.h.n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class C {

    /* renamed from: a */
    public static final int[] f919a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8};

    /* renamed from: b */
    public static final L f920b = new H();

    /* renamed from: c */
    public static final L f921c;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public Fragment f922a;

        /* renamed from: b */
        public boolean f923b;

        /* renamed from: c */
        public C0058a f924c;

        /* renamed from: d */
        public Fragment f925d;

        /* renamed from: e */
        public boolean f926e;
        public C0058a f;
    }

    static {
        L l;
        int i = Build.VERSION.SDK_INT;
        try {
            l = (L) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            l = null;
        }
        f921c = l;
    }

    /* JADX WARN: Removed duplicated region for block: B:154:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0454 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0223  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(c.i.a.s r39, java.util.ArrayList<c.i.a.C0058a> r40, java.util.ArrayList<java.lang.Boolean> r41, int r42, int r43, boolean r44) {
        /*
            Method dump skipped, instructions count: 1129
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.C.a(c.i.a.s, java.util.ArrayList, java.util.ArrayList, int, int, boolean):void");
    }

    public static b<String, View> b(L l, b<String, String> bVar, Object obj, a aVar) {
        ArrayList<String> arrayList;
        if (bVar.isEmpty() || obj == null) {
            bVar.clear();
            return null;
        }
        Fragment fragment = aVar.f925d;
        b<String, View> bVar2 = new b<>();
        l.a((Map<String, View>) bVar2, fragment.K);
        C0058a aVar2 = aVar.f;
        if (aVar.f926e) {
            fragment.i();
            arrayList = aVar2.r;
        } else {
            Fragment.a aVar3 = fragment.O;
            if (aVar3 != null) {
                d dVar = aVar3.p;
            }
            arrayList = aVar2.q;
        }
        h.a((Map) bVar2, (Collection<?>) arrayList);
        h<String, View> b2 = bVar2.b();
        if (b2.f709b == null) {
            b2.f709b = new h.c();
        }
        h.a((Map) bVar, (Collection<?>) b2.f709b);
        return bVar2;
    }

    public static Object b(L l, Fragment fragment, boolean z) {
        Object obj = null;
        if (fragment == null) {
            return null;
        }
        if (z) {
            Fragment.a aVar = fragment.O;
            if (aVar != null) {
                Object obj2 = aVar.h;
                obj = obj2 == Fragment.f245b ? fragment.h() : obj2;
            }
        } else {
            obj = fragment.j();
        }
        return l.b(obj);
    }

    public static L a(Fragment fragment, Fragment fragment2) {
        Object obj;
        Object obj2;
        Object obj3;
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object j = fragment.j();
            if (j != null) {
                arrayList.add(j);
            }
            Fragment.a aVar = fragment.O;
            if (aVar == null) {
                obj2 = null;
            } else {
                obj2 = aVar.h;
                if (obj2 == Fragment.f245b) {
                    obj2 = fragment.h();
                }
            }
            if (obj2 != null) {
                arrayList.add(obj2);
            }
            Fragment.a aVar2 = fragment.O;
            if (aVar2 == null) {
                obj3 = null;
            } else {
                Object obj4 = aVar2.l;
                obj3 = obj4 == Fragment.f245b ? fragment.n() : obj4;
            }
            if (obj3 != null) {
                arrayList.add(obj3);
            }
        }
        if (fragment2 != null) {
            Object h = fragment2.h();
            if (h != null) {
                arrayList.add(h);
            }
            Fragment.a aVar3 = fragment2.O;
            if (aVar3 == null) {
                obj = null;
            } else {
                obj = aVar3.j;
                if (obj == Fragment.f245b) {
                    obj = fragment2.j();
                }
            }
            if (obj != null) {
                arrayList.add(obj);
            }
            Object n = fragment2.n();
            if (n != null) {
                arrayList.add(n);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        L l = f920b;
        if (l != null && a(l, arrayList)) {
            return f920b;
        }
        L l2 = f921c;
        if (l2 != null && a(l2, arrayList)) {
            return f921c;
        }
        if (f920b == null && f921c == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static boolean a(L l, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!l.a(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void a(ArrayList<View> arrayList, b<String, View> bVar, Collection<String> collection) {
        for (int i = bVar.g - 1; i >= 0; i--) {
            View d2 = bVar.d(i);
            if (collection.contains(n.j(d2))) {
                arrayList.add(d2);
            }
        }
    }

    public static b<String, View> a(L l, b<String, String> bVar, Object obj, a aVar) {
        ArrayList<String> arrayList;
        Fragment fragment = aVar.f922a;
        View view = fragment.K;
        if (bVar.isEmpty() || obj == null || view == null) {
            bVar.clear();
            return null;
        }
        b<String, View> bVar2 = new b<>();
        l.a((Map<String, View>) bVar2, view);
        C0058a aVar2 = aVar.f924c;
        if (aVar.f923b) {
            Fragment.a aVar3 = fragment.O;
            if (aVar3 != null) {
                d dVar = aVar3.p;
            }
            arrayList = aVar2.q;
        } else {
            fragment.i();
            arrayList = aVar2.r;
        }
        if (arrayList != null) {
            h.a((Map) bVar2, (Collection<?>) arrayList);
            h.a((Map) bVar2, (Collection<?>) bVar.values());
        }
        int i = bVar.g;
        while (true) {
            i--;
            if (i < 0) {
                return bVar2;
            }
            if (!bVar2.containsKey(bVar.d(i))) {
                bVar.c(i);
            }
        }
    }

    public static View a(b<String, View> bVar, a aVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        String str;
        C0058a aVar2 = aVar.f924c;
        if (obj == null || bVar == null || (arrayList = aVar2.q) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = aVar2.q.get(0);
        } else {
            str = aVar2.r.get(0);
        }
        return bVar.get(str);
    }

    public static void a(L l, Object obj, Object obj2, b<String, View> bVar, boolean z, C0058a aVar) {
        String str;
        ArrayList<String> arrayList = aVar.q;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (z) {
                str = aVar.r.get(0);
            } else {
                str = aVar.q.get(0);
            }
            View view = bVar.get(str);
            l.b(obj, view);
            if (obj2 != null) {
                l.b(obj2, view);
            }
        }
    }

    public static void a(Fragment fragment, Fragment fragment2, boolean z, b<String, View> bVar, boolean z2) {
        if (z) {
            fragment2.i();
        } else {
            fragment.i();
        }
    }

    public static ArrayList<View> a(L l, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.K;
        if (view2 != null) {
            l.a(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        l.a(obj, arrayList2);
        return arrayList2;
    }

    public static void a(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).setVisibility(i);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0038, code lost:
        if (r6.m != false) goto L_0x008a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0076, code lost:
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0088, code lost:
        if (r6.C == false) goto L_0x008a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x008a, code lost:
        r12 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ae A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00d4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00ec A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(c.i.a.C0058a r11, c.i.a.C0058a.C0015a r12, android.util.SparseArray<c.i.a.C.a> r13, boolean r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.C.a(c.i.a.a, c.i.a.a$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static Object a(L l, Fragment fragment, boolean z) {
        Object obj = null;
        if (fragment == null) {
            return null;
        }
        if (z) {
            Fragment.a aVar = fragment.O;
            if (aVar != null) {
                Object obj2 = aVar.j;
                obj = obj2 == Fragment.f245b ? fragment.j() : obj2;
            }
        } else {
            obj = fragment.h();
        }
        return l.b(obj);
    }

    public static Object a(L l, Fragment fragment, Fragment fragment2, boolean z) {
        Object obj;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        if (z) {
            Fragment.a aVar = fragment2.O;
            if (aVar == null) {
                obj = null;
            } else {
                obj = aVar.l;
                if (obj == Fragment.f245b) {
                    obj = fragment2.n();
                }
            }
        } else {
            obj = fragment.n();
        }
        Object b2 = l.b(obj);
        H h = (H) l;
        if (b2 == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition((Transition) b2);
        return transitionSet;
    }

    public static Object a(L l, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        Boolean bool;
        Boolean bool2;
        boolean z2 = true;
        if (!(obj == null || obj2 == null || fragment == null)) {
            if (z) {
                Fragment.a aVar = fragment.O;
                if (!(aVar == null || (bool2 = aVar.m) == null)) {
                    z2 = bool2.booleanValue();
                }
            } else {
                Fragment.a aVar2 = fragment.O;
                if (!(aVar2 == null || (bool = aVar2.n) == null)) {
                    z2 = bool.booleanValue();
                }
            }
        }
        if (z2) {
            return l.b(obj2, obj, obj3);
        }
        return l.a(obj2, obj, obj3);
    }
}
