package c.i.a;

import androidx.fragment.app.Fragment;
import c.i.a.AbstractC0069l;
import c.i.a.s;
import com.tencent.smtt.sdk.TbsListener;
import e.a.a.a.a;
import java.io.PrintWriter;
import java.util.ArrayList;

/* renamed from: c.i.a.a  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0058a extends x implements AbstractC0069l.a, s.h {

    /* renamed from: a  reason: collision with root package name */
    public final s f948a;

    /* renamed from: c  reason: collision with root package name */
    public int f950c;

    /* renamed from: d  reason: collision with root package name */
    public int f951d;

    /* renamed from: e  reason: collision with root package name */
    public int f952e;
    public int f;
    public int g;
    public int h;
    public boolean i;
    public String j;
    public boolean k;
    public int m;
    public CharSequence n;
    public int o;
    public CharSequence p;
    public ArrayList<String> q;
    public ArrayList<String> r;
    public ArrayList<Runnable> t;

    /* renamed from: b  reason: collision with root package name */
    public ArrayList<C0015a> f949b = new ArrayList<>();
    public int l = -1;
    public boolean s = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c.i.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0015a {

        /* renamed from: a  reason: collision with root package name */
        public int f953a;

        /* renamed from: b  reason: collision with root package name */
        public Fragment f954b;

        /* renamed from: c  reason: collision with root package name */
        public int f955c;

        /* renamed from: d  reason: collision with root package name */
        public int f956d;

        /* renamed from: e  reason: collision with root package name */
        public int f957e;
        public int f;

        public C0015a() {
        }

        public C0015a(int i, Fragment fragment) {
            this.f953a = i;
            this.f954b = fragment;
        }
    }

    public C0058a(s sVar) {
        this.f948a = sVar;
    }

    public void a(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.j);
            printWriter.print(" mIndex=");
            printWriter.print(this.l);
            printWriter.print(" mCommitted=");
            printWriter.println(this.k);
            if (this.g != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.g));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.h));
            }
            if (!(this.f950c == 0 && this.f951d == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f950c));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f951d));
            }
            if (!(this.f952e == 0 && this.f == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f952e));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f));
            }
            if (!(this.m == 0 && this.n == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.m));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.n);
            }
            if (!(this.o == 0 && this.p == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.o));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.p);
            }
        }
        if (!this.f949b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            String str3 = str + "    ";
            int size = this.f949b.size();
            for (int i = 0; i < size; i++) {
                C0015a aVar = this.f949b.get(i);
                switch (aVar.f953a) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case 8:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    default:
                        StringBuilder a2 = a.a("cmd=");
                        a2.append(aVar.f953a);
                        str2 = a2.toString();
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(aVar.f954b);
                if (z) {
                    if (!(aVar.f955c == 0 && aVar.f956d == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.f955c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.f956d));
                    }
                    if (aVar.f957e != 0 || aVar.f != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.f957e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.f));
                    }
                }
            }
        }
    }

    public boolean b(int i) {
        int size = this.f949b.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f949b.get(i2).f954b;
            int i3 = fragment != null ? fragment.A : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((int) TbsListener.ErrorCode.DOWNLOAD_INTERRUPT);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.l >= 0) {
            sb.append(" #");
            sb.append(this.l);
        }
        if (this.j != null) {
            sb.append(" ");
            sb.append(this.j);
        }
        sb.append("}");
        return sb.toString();
    }

    public void a(int i) {
        if (this.i) {
            if (s.f987a) {
                String str = "Bump nesting in " + this + " by " + i;
            }
            int size = this.f949b.size();
            for (int i2 = 0; i2 < size; i2++) {
                C0015a aVar = this.f949b.get(i2);
                Fragment fragment = aVar.f954b;
                if (fragment != null) {
                    fragment.s += i;
                    if (s.f987a) {
                        StringBuilder a2 = a.a("Bump nesting of ");
                        a2.append(aVar.f954b);
                        a2.append(" to ");
                        a2.append(aVar.f954b.s);
                        a2.toString();
                    }
                }
            }
        }
    }

    public boolean a(ArrayList<C0058a> arrayList, ArrayList<Boolean> arrayList2) {
        if (s.f987a) {
            a.b("Run: ", this);
        }
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.i) {
            return true;
        }
        s sVar = this.f948a;
        if (sVar.j == null) {
            sVar.j = new ArrayList<>();
        }
        sVar.j.add(this);
        return true;
    }

    public boolean a(ArrayList<C0058a> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.f949b.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f949b.get(i4).f954b;
            int i5 = fragment != null ? fragment.A : 0;
            if (!(i5 == 0 || i5 == i3)) {
                for (int i6 = i; i6 < i2; i6++) {
                    C0058a aVar = arrayList.get(i6);
                    int size2 = aVar.f949b.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = aVar.f949b.get(i7).f954b;
                        if ((fragment2 != null ? fragment2.A : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }

    public void a() {
        int size = this.f949b.size();
        for (int i = 0; i < size; i++) {
            C0015a aVar = this.f949b.get(i);
            Fragment fragment = aVar.f954b;
            if (fragment != null) {
                int i2 = this.g;
                int i3 = this.h;
                if (!(fragment.O == null && i2 == 0 && i3 == 0)) {
                    fragment.d();
                    Fragment.a aVar2 = fragment.O;
                    aVar2.f254e = i2;
                    aVar2.f = i3;
                }
            }
            switch (aVar.f953a) {
                case 1:
                    fragment.a(aVar.f955c);
                    this.f948a.a(fragment, false);
                    break;
                case 2:
                default:
                    StringBuilder a2 = a.a("Unknown cmd: ");
                    a2.append(aVar.f953a);
                    throw new IllegalArgumentException(a2.toString());
                case 3:
                    fragment.a(aVar.f956d);
                    this.f948a.f(fragment);
                    break;
                case 4:
                    fragment.a(aVar.f956d);
                    this.f948a.c(fragment);
                    break;
                case 5:
                    fragment.a(aVar.f955c);
                    this.f948a.i(fragment);
                    break;
                case 6:
                    fragment.a(aVar.f956d);
                    this.f948a.b(fragment);
                    break;
                case 7:
                    fragment.a(aVar.f955c);
                    this.f948a.a(fragment);
                    break;
                case 8:
                    this.f948a.h(fragment);
                    break;
                case 9:
                    this.f948a.h(null);
                    break;
            }
            if (!(this.s || aVar.f953a == 1 || fragment == null)) {
                this.f948a.e(fragment);
            }
        }
        if (!this.s) {
            s sVar = this.f948a;
            sVar.a(sVar.p, true);
        }
    }

    public void a(boolean z) {
        for (int size = this.f949b.size() - 1; size >= 0; size--) {
            C0015a aVar = this.f949b.get(size);
            Fragment fragment = aVar.f954b;
            if (fragment != null) {
                int d2 = s.d(this.g);
                int i = this.h;
                if (!(fragment.O == null && d2 == 0 && i == 0)) {
                    fragment.d();
                    Fragment.a aVar2 = fragment.O;
                    aVar2.f254e = d2;
                    aVar2.f = i;
                }
            }
            switch (aVar.f953a) {
                case 1:
                    fragment.a(aVar.f);
                    this.f948a.f(fragment);
                    break;
                case 2:
                default:
                    StringBuilder a2 = a.a("Unknown cmd: ");
                    a2.append(aVar.f953a);
                    throw new IllegalArgumentException(a2.toString());
                case 3:
                    fragment.a(aVar.f957e);
                    this.f948a.a(fragment, false);
                    break;
                case 4:
                    fragment.a(aVar.f957e);
                    this.f948a.i(fragment);
                    break;
                case 5:
                    fragment.a(aVar.f);
                    this.f948a.c(fragment);
                    break;
                case 6:
                    fragment.a(aVar.f957e);
                    this.f948a.a(fragment);
                    break;
                case 7:
                    fragment.a(aVar.f);
                    this.f948a.b(fragment);
                    break;
                case 8:
                    this.f948a.h(null);
                    break;
                case 9:
                    this.f948a.h(fragment);
                    break;
            }
            if (!(this.s || aVar.f953a == 3 || fragment == null)) {
                this.f948a.e(fragment);
            }
        }
        if (!this.s && z) {
            s sVar = this.f948a;
            sVar.a(sVar.p, true);
        }
    }

    public static boolean a(C0015a aVar) {
        Fragment fragment = aVar.f954b;
        if (fragment == null || !fragment.m || fragment.K == null || fragment.D || fragment.C) {
            return false;
        }
        Fragment.a aVar2 = fragment.O;
        return aVar2 == null ? false : aVar2.q;
    }
}
