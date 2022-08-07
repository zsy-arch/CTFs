package c.i.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.fragment.app.BackStackState;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManagerState;
import androidx.fragment.app.FragmentState;
import c.a.a.C;
import c.e.h.n;
import c.i.a.AbstractC0069l;
import c.i.a.C0058a;
import c.j.r;
import com.tencent.smtt.sdk.TbsListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public final class s extends AbstractC0069l implements LayoutInflater.Factory2 {

    /* renamed from: a */
    public static boolean f987a;

    /* renamed from: b */
    public static Field f988b;

    /* renamed from: c */
    public static final Interpolator f989c = new DecelerateInterpolator(2.5f);

    /* renamed from: d */
    public static final Interpolator f990d = new DecelerateInterpolator(1.5f);
    public ArrayList<C0058a> A;
    public ArrayList<Boolean> B;
    public ArrayList<Fragment> C;
    public ArrayList<i> F;
    public t G;

    /* renamed from: e */
    public ArrayList<h> f991e;
    public boolean f;
    public SparseArray<Fragment> i;
    public ArrayList<C0058a> j;
    public ArrayList<Fragment> k;
    public ArrayList<C0058a> l;
    public ArrayList<Integer> m;
    public ArrayList<AbstractC0069l.c> n;
    public AbstractC0068k q;
    public AbstractC0066i r;
    public Fragment s;
    public Fragment t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public String y;
    public boolean z;
    public int g = 0;
    public final ArrayList<Fragment> h = new ArrayList<>();
    public final CopyOnWriteArrayList<f> o = new CopyOnWriteArrayList<>();
    public int p = 0;
    public Bundle D = null;
    public SparseArray<Parcelable> E = null;
    public Runnable H = new RunnableC0070m(this);

    /* loaded from: classes.dex */
    public static class a extends b {

        /* renamed from: b */
        public View f992b;

        public a(View view, Animation.AnimationListener animationListener) {
            super(animationListener);
            this.f992b = view;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (n.o(this.f992b) || Build.VERSION.SDK_INT >= 24) {
                this.f992b.post(new r(this));
            } else {
                this.f992b.setLayerType(0, null);
            }
            Animation.AnimationListener animationListener = this.f993a;
            if (animationListener != null) {
                animationListener.onAnimationEnd(animation);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Animation.AnimationListener {

        /* renamed from: a */
        public final Animation.AnimationListener f993a;

        public b(Animation.AnimationListener animationListener) {
            this.f993a = animationListener;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            Animation.AnimationListener animationListener = this.f993a;
            if (animationListener != null) {
                animationListener.onAnimationRepeat(animation);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            Animation.AnimationListener animationListener = this.f993a;
            if (animationListener != null) {
                animationListener.onAnimationStart(animation);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class d extends AnimatorListenerAdapter {

        /* renamed from: a */
        public View f996a;

        public d(View view) {
            this.f996a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f996a.setLayerType(0, null);
            animator.removeListener(this);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f996a.setLayerType(2, null);
        }
    }

    /* loaded from: classes.dex */
    public static final class f {

        /* renamed from: a */
        public final AbstractC0069l.b f1002a;

        /* renamed from: b */
        public final boolean f1003b;
    }

    /* loaded from: classes.dex */
    public static class g {

        /* renamed from: a */
        public static final int[] f1004a = {16842755, 16842960, 16842961};
    }

    /* loaded from: classes.dex */
    public interface h {
    }

    /* loaded from: classes.dex */
    public static class i implements Fragment.c {

        /* renamed from: a */
        public final boolean f1005a;

        /* renamed from: b */
        public final C0058a f1006b;

        /* renamed from: c */
        public int f1007c;

        public i(C0058a aVar, boolean z) {
            this.f1005a = z;
            this.f1006b = aVar;
        }

        public void a() {
            boolean z = this.f1007c > 0;
            s sVar = this.f1006b.f948a;
            int size = sVar.h.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = sVar.h.get(i);
                fragment.a((Fragment.c) null);
                if (z) {
                    Fragment.a aVar = fragment.O;
                    if (aVar == null ? false : aVar.q) {
                        s sVar2 = fragment.t;
                        if (sVar2 == null || sVar2.q == null) {
                            fragment.d().q = false;
                        } else if (Looper.myLooper() != fragment.t.q.f972c.getLooper()) {
                            fragment.t.q.f972c.postAtFrontOfQueue(new RunnableC0060c(fragment));
                        } else {
                            fragment.c();
                        }
                    }
                }
            }
            C0058a aVar2 = this.f1006b;
            aVar2.f948a.a(aVar2, this.f1005a, !z, true);
        }
    }

    static {
        new AccelerateInterpolator(2.5f);
        new AccelerateInterpolator(1.5f);
    }

    public static void a(t tVar) {
        if (tVar != null) {
            List<Fragment> list = tVar.f1008a;
            if (list != null) {
                for (Fragment fragment : list) {
                    fragment.F = true;
                }
            }
            List<t> list2 = tVar.f1009b;
            if (list2 != null) {
                for (t tVar2 : list2) {
                    a(tVar2);
                }
            }
        }
    }

    public static int d(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    public void b(Fragment fragment) {
        if (f987a) {
            e.a.a.a.a.b("detach: ", fragment);
        }
        if (!fragment.D) {
            fragment.D = true;
            if (fragment.m) {
                if (f987a) {
                    e.a.a.a.a.b("remove from detach: ", fragment);
                }
                synchronized (this.h) {
                    this.h.remove(fragment);
                }
                if (fragment.G && fragment.H) {
                    this.u = true;
                }
                fragment.m = false;
            }
        }
    }

    @Override // c.i.a.AbstractC0069l
    public boolean c() {
        int size;
        AbstractC0069l t;
        e();
        o();
        c(true);
        Fragment fragment = this.t;
        if (fragment != null && (t = fragment.t()) != null && t.c()) {
            return true;
        }
        ArrayList<C0058a> arrayList = this.A;
        ArrayList<Boolean> arrayList2 = this.B;
        ArrayList<C0058a> arrayList3 = this.j;
        boolean z = false;
        if (arrayList3 != null && (size = arrayList3.size() - 1) >= 0) {
            arrayList.add(this.j.remove(size));
            arrayList2.add(true);
            z = true;
        }
        if (z) {
            this.f = true;
            try {
                c(this.A, this.B);
            } finally {
                f();
            }
        }
        n();
        d();
        return z;
    }

    public void d(Fragment fragment) {
        if (fragment.g < 0) {
            int i2 = this.g;
            this.g = i2 + 1;
            fragment.a(i2, this.s);
            if (this.i == null) {
                this.i = new SparseArray<>();
            }
            this.i.put(fragment.g, fragment);
            if (f987a) {
                e.a.a.a.a.b("Allocated fragment index ", fragment);
            }
        }
    }

    public void e(Fragment fragment) {
        Animator animator;
        ViewGroup viewGroup;
        int indexOfChild;
        int indexOfChild2;
        if (fragment != null) {
            int i2 = this.p;
            if (fragment.n) {
                if (fragment.r()) {
                    i2 = Math.min(i2, 1);
                } else {
                    i2 = Math.min(i2, 0);
                }
            }
            a(fragment, i2, fragment.l(), fragment.m(), false);
            View view = fragment.K;
            if (view != null) {
                ViewGroup viewGroup2 = fragment.J;
                Fragment fragment2 = null;
                if (viewGroup2 != null && view != null) {
                    int indexOf = this.h.indexOf(fragment);
                    while (true) {
                        indexOf--;
                        if (indexOf < 0) {
                            break;
                        }
                        Fragment fragment3 = this.h.get(indexOf);
                        if (fragment3.J == viewGroup2 && fragment3.K != null) {
                            fragment2 = fragment3;
                            break;
                        }
                    }
                }
                if (fragment2 != null && (indexOfChild2 = viewGroup.indexOfChild(fragment.K)) < (indexOfChild = (viewGroup = fragment.J).indexOfChild(fragment2.K))) {
                    viewGroup.removeViewAt(indexOfChild2);
                    viewGroup.addView(fragment.K, indexOfChild);
                }
                if (fragment.P && fragment.J != null) {
                    float f2 = fragment.R;
                    if (f2 > 0.0f) {
                        fragment.K.setAlpha(f2);
                    }
                    fragment.R = 0.0f;
                    fragment.P = false;
                    c a2 = a(fragment, fragment.l(), true, fragment.m());
                    if (a2 != null) {
                        a(fragment.K, a2);
                        Animation animation = a2.f994a;
                        if (animation != null) {
                            fragment.K.startAnimation(animation);
                        } else {
                            a2.f995b.setTarget(fragment.K);
                            a2.f995b.start();
                        }
                    }
                }
            }
            if (fragment.Q) {
                if (fragment.K != null) {
                    c a3 = a(fragment, fragment.l(), !fragment.C, fragment.m());
                    if (a3 == null || (animator = a3.f995b) == null) {
                        if (a3 != null) {
                            a(fragment.K, a3);
                            fragment.K.startAnimation(a3.f994a);
                            a3.f994a.start();
                        }
                        fragment.K.setVisibility((!fragment.C || fragment.q()) ? 0 : 8);
                        if (fragment.q()) {
                            fragment.c(false);
                        }
                    } else {
                        animator.setTarget(fragment.K);
                        if (!fragment.C) {
                            fragment.K.setVisibility(0);
                        } else if (fragment.q()) {
                            fragment.c(false);
                        } else {
                            ViewGroup viewGroup3 = fragment.J;
                            View view2 = fragment.K;
                            viewGroup3.startViewTransition(view2);
                            a3.f995b.addListener(new q(this, viewGroup3, view2, fragment));
                        }
                        a(fragment.K, a3);
                        a3.f995b.start();
                    }
                }
                if (fragment.m && fragment.G && fragment.H) {
                    this.u = true;
                }
                fragment.Q = false;
                boolean z = fragment.C;
            }
        }
    }

    public void f(Fragment fragment) {
        if (f987a) {
            String str = "remove: " + fragment + " nesting=" + fragment.s;
        }
        boolean z = !fragment.r();
        if (!fragment.D || z) {
            synchronized (this.h) {
                this.h.remove(fragment);
            }
            if (fragment.G && fragment.H) {
                this.u = true;
            }
            fragment.m = false;
            fragment.n = true;
        }
    }

    public void g(Fragment fragment) {
        if (fragment.L != null) {
            SparseArray<Parcelable> sparseArray = this.E;
            if (sparseArray == null) {
                this.E = new SparseArray<>();
            } else {
                sparseArray.clear();
            }
            fragment.L.saveHierarchyState(this.E);
            if (this.E.size() > 0) {
                fragment.f248e = this.E;
                this.E = null;
            }
        }
    }

    public void h() {
        this.v = false;
        this.w = false;
        a(1);
    }

    public void i(Fragment fragment) {
        if (f987a) {
            e.a.a.a.a.b("show: ", fragment);
        }
        if (fragment.C) {
            fragment.C = false;
            fragment.Q = !fragment.Q;
        }
    }

    public void j() {
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            Fragment fragment = this.h.get(i2);
            if (fragment != null) {
                fragment.u();
            }
        }
    }

    public void k() {
        a(3);
    }

    public void l() {
        this.v = false;
        this.w = false;
        a(4);
    }

    public void m() {
        this.v = false;
        this.w = false;
        a(3);
    }

    public void n() {
        if (this.z) {
            this.z = false;
            u();
        }
    }

    /* JADX WARN: Finally extract failed */
    public boolean o() {
        c(true);
        boolean z = false;
        while (b(this.A, this.B)) {
            this.f = true;
            try {
                c(this.A, this.B);
                f();
                z = true;
            } catch (Throwable th) {
                f();
                throw th;
            }
        }
        if (this.z) {
            this.z = false;
            u();
        }
        d();
        return z;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        Fragment fragment;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.f1004a);
        int i2 = 0;
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.a(this.q.f971b, attributeValue)) {
            return null;
        }
        if (view != null) {
            i2 = view.getId();
        }
        if (i2 == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment b2 = resourceId != -1 ? b(resourceId) : null;
        if (b2 == null && string != null) {
            int size = this.h.size() - 1;
            while (true) {
                if (size >= 0) {
                    fragment = this.h.get(size);
                    if (fragment != null && string.equals(fragment.B)) {
                        break;
                    }
                    size--;
                } else {
                    SparseArray<Fragment> sparseArray = this.i;
                    if (sparseArray != null) {
                        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
                            fragment = this.i.valueAt(size2);
                            if (fragment != null && string.equals(fragment.B)) {
                                break;
                            }
                        }
                    }
                    fragment = null;
                }
            }
        } else {
            fragment = b2;
        }
        if (fragment == null && i2 != -1) {
            fragment = b(i2);
        }
        if (f987a) {
            StringBuilder a2 = e.a.a.a.a.a("onCreateView: id=0x");
            a2.append(Integer.toHexString(resourceId));
            a2.append(" fname=");
            a2.append(attributeValue);
            a2.append(" existing=");
            a2.append(fragment);
            a2.toString();
        }
        if (fragment == null) {
            Fragment a3 = this.r.a(context, attributeValue, null);
            a3.o = true;
            a3.z = resourceId != 0 ? resourceId : i2;
            a3.A = i2;
            a3.B = string;
            a3.p = true;
            a3.t = this;
            AbstractC0068k kVar = this.q;
            a3.u = kVar;
            a3.a(kVar.f971b, attributeSet, a3.f247d);
            a(a3, true);
            fragment = a3;
        } else if (!fragment.p) {
            fragment.p = true;
            AbstractC0068k kVar2 = this.q;
            fragment.u = kVar2;
            if (!fragment.F) {
                fragment.a(kVar2.f971b, attributeSet, fragment.f247d);
            }
        } else {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i2) + " with another fragment for " + attributeValue);
        }
        if (this.p >= 1 || !fragment.o) {
            a(fragment, this.p, 0, 0, false);
        } else {
            a(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.K;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragment.K.getTag() == null) {
                fragment.K.setTag(string);
            }
            return fragment.K;
        }
        throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
    }

    public LayoutInflater.Factory2 p() {
        return this;
    }

    public void q() {
        s sVar;
        this.G = null;
        this.v = false;
        this.w = false;
        int size = this.h.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.h.get(i2);
            if (!(fragment == null || (sVar = fragment.v) == null)) {
                sVar.q();
            }
        }
    }

    public Parcelable r() {
        int i2;
        int i3;
        BackStackState[] backStackStateArr;
        int[] iArr;
        int size;
        Bundle bundle;
        Parcelable r;
        if (this.F != null) {
            while (!this.F.isEmpty()) {
                this.F.remove(0).a();
            }
        }
        SparseArray<Fragment> sparseArray = this.i;
        if (sparseArray == null) {
            i3 = 0;
            i2 = 0;
        } else {
            i2 = sparseArray.size();
            i3 = 0;
        }
        while (true) {
            backStackStateArr = null;
            if (i3 >= i2) {
                break;
            }
            Fragment valueAt = this.i.valueAt(i3);
            if (valueAt != null) {
                if (valueAt.e() != null) {
                    int o = valueAt.o();
                    View e2 = valueAt.e();
                    Animation animation = e2.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        e2.clearAnimation();
                    }
                    valueAt.a((View) null);
                    a(valueAt, o, 0, 0, false);
                } else if (valueAt.f() != null) {
                    valueAt.f().end();
                }
            }
            i3++;
        }
        o();
        this.v = true;
        this.G = null;
        SparseArray<Fragment> sparseArray2 = this.i;
        if (sparseArray2 == null || sparseArray2.size() <= 0) {
            return null;
        }
        int size2 = this.i.size();
        FragmentState[] fragmentStateArr = new FragmentState[size2];
        boolean z = false;
        for (int i4 = 0; i4 < size2; i4++) {
            Fragment valueAt2 = this.i.valueAt(i4);
            if (valueAt2 != null) {
                if (valueAt2.g >= 0) {
                    FragmentState fragmentState = new FragmentState(valueAt2);
                    fragmentStateArr[i4] = fragmentState;
                    if (valueAt2.f246c <= 0 || fragmentState.k != null) {
                        fragmentState.k = valueAt2.f247d;
                    } else {
                        if (this.D == null) {
                            this.D = new Bundle();
                        }
                        Bundle bundle2 = this.D;
                        s sVar = valueAt2.v;
                        if (!(sVar == null || (r = sVar.r()) == null)) {
                            bundle2.putParcelable("android:support:fragments", r);
                        }
                        d(valueAt2, this.D, false);
                        if (!this.D.isEmpty()) {
                            bundle = this.D;
                            this.D = null;
                        } else {
                            bundle = null;
                        }
                        if (valueAt2.K != null) {
                            g(valueAt2);
                        }
                        if (valueAt2.f248e != null) {
                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            bundle.putSparseParcelableArray("android:view_state", valueAt2.f248e);
                        }
                        if (!valueAt2.N) {
                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            bundle.putBoolean("android:user_visible_hint", valueAt2.N);
                        }
                        fragmentState.k = bundle;
                        Fragment fragment = valueAt2.j;
                        if (fragment != null) {
                            if (fragment.g >= 0) {
                                if (fragmentState.k == null) {
                                    fragmentState.k = new Bundle();
                                }
                                Bundle bundle3 = fragmentState.k;
                                Fragment fragment2 = valueAt2.j;
                                int i5 = fragment2.g;
                                if (i5 >= 0) {
                                    bundle3.putInt("android:target_state", i5);
                                    int i6 = valueAt2.l;
                                    if (i6 != 0) {
                                        fragmentState.k.putInt("android:target_req_state", i6);
                                    }
                                } else {
                                    a(new IllegalStateException(e.a.a.a.a.a("Fragment ", fragment2, " is not currently in the FragmentManager")));
                                    throw null;
                                }
                            } else {
                                a(new IllegalStateException("Failure saving state: " + valueAt2 + " has target not in fragment manager: " + valueAt2.j));
                                throw null;
                            }
                        }
                    }
                    if (f987a) {
                        String str = "Saved state of " + valueAt2 + ": " + fragmentState.k;
                    }
                    z = true;
                } else {
                    a(new IllegalStateException("Failure saving state: active " + valueAt2 + " has cleared index: " + valueAt2.g));
                    throw null;
                }
            }
        }
        if (!z) {
            boolean z2 = f987a;
            return null;
        }
        int size3 = this.h.size();
        if (size3 > 0) {
            iArr = new int[size3];
            for (int i7 = 0; i7 < size3; i7++) {
                iArr[i7] = this.h.get(i7).g;
                if (iArr[i7] >= 0) {
                    if (f987a) {
                        String str2 = "saveAllState: adding fragment #" + i7 + ": " + this.h.get(i7);
                    }
                } else {
                    StringBuilder a2 = e.a.a.a.a.a("Failure saving state: active ");
                    a2.append(this.h.get(i7));
                    a2.append(" has cleared index: ");
                    a2.append(iArr[i7]);
                    a(new IllegalStateException(a2.toString()));
                    throw null;
                }
            }
        } else {
            iArr = null;
        }
        ArrayList<C0058a> arrayList = this.j;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i8 = 0; i8 < size; i8++) {
                backStackStateArr[i8] = new BackStackState(this.j.get(i8));
                if (f987a) {
                    String str3 = "saveAllState: adding back stack #" + i8 + ": " + this.j.get(i8);
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f255a = fragmentStateArr;
        fragmentManagerState.f256b = iArr;
        fragmentManagerState.f257c = backStackStateArr;
        Fragment fragment3 = this.t;
        if (fragment3 != null) {
            fragmentManagerState.f258d = fragment3.g;
        }
        fragmentManagerState.f259e = this.g;
        s();
        return fragmentManagerState;
    }

    public void s() {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        t tVar;
        if (this.i != null) {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                Fragment valueAt = this.i.valueAt(i2);
                if (valueAt != null) {
                    if (valueAt.E) {
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        arrayList3.add(valueAt);
                        Fragment fragment = valueAt.j;
                        valueAt.k = fragment != null ? fragment.g : -1;
                        if (f987a) {
                            e.a.a.a.a.b("retainNonConfig: keeping retained ", valueAt);
                        }
                    }
                    s sVar = valueAt.v;
                    if (sVar != null) {
                        sVar.s();
                        tVar = valueAt.v.G;
                    } else {
                        tVar = valueAt.w;
                    }
                    if (arrayList2 == null && tVar != null) {
                        arrayList2 = new ArrayList(this.i.size());
                        for (int i3 = 0; i3 < i2; i3++) {
                            arrayList2.add(null);
                        }
                    }
                    if (arrayList2 != null) {
                        arrayList2.add(tVar);
                    }
                    if (arrayList == null && valueAt.x != null) {
                        arrayList = new ArrayList(this.i.size());
                        for (int i4 = 0; i4 < i2; i4++) {
                            arrayList.add(null);
                        }
                    }
                    if (arrayList != null) {
                        arrayList.add(valueAt.x);
                    }
                }
            }
        } else {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
        }
        if (arrayList3 == null && arrayList2 == null && arrayList == null) {
            this.G = null;
        } else {
            this.G = new t(arrayList3, arrayList2, arrayList);
        }
    }

    public void t() {
        synchronized (this) {
            boolean z = false;
            boolean z2 = this.F != null && !this.F.isEmpty();
            if (this.f991e != null && this.f991e.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.q.f972c.removeCallbacks(this.H);
                this.q.f972c.post(this.H);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((int) TbsListener.ErrorCode.DOWNLOAD_INTERRUPT);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.s;
        if (fragment != null) {
            C.a((Object) fragment, sb);
        } else {
            C.a((Object) this.q, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public void u() {
        if (this.i != null) {
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                Fragment valueAt = this.i.valueAt(i2);
                if (valueAt != null && valueAt.M) {
                    if (this.f) {
                        this.z = true;
                    } else {
                        valueAt.M = false;
                        a(valueAt, this.p, 0, 0, false);
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a */
        public final Animation f994a;

        /* renamed from: b */
        public final Animator f995b;

        public c(Animation animation) {
            this.f994a = animation;
            this.f995b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public c(Animator animator) {
            this.f994a = null;
            this.f995b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    public void h(Fragment fragment) {
        if (fragment == null || (this.i.get(fragment.g) == fragment && (fragment.u == null || fragment.t == this))) {
            this.t = fragment;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void i() {
        this.x = true;
        o();
        a(0);
        this.q = null;
        this.r = null;
        this.s = null;
    }

    /* loaded from: classes.dex */
    public static class e extends AnimationSet implements Runnable {

        /* renamed from: a */
        public final ViewGroup f997a;

        /* renamed from: b */
        public final View f998b;

        /* renamed from: c */
        public boolean f999c;

        /* renamed from: d */
        public boolean f1000d;

        /* renamed from: e */
        public boolean f1001e = true;

        public e(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f997a = viewGroup;
            this.f998b = view;
            addAnimation(animation);
            this.f997a.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.f1001e = true;
            if (this.f999c) {
                return !this.f1000d;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f999c = true;
                M.a(this.f997a, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f999c || !this.f1001e) {
                this.f997a.endViewTransition(this.f998b);
                this.f1000d = true;
                return;
            }
            this.f1001e = false;
            this.f997a.post(this);
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f) {
            this.f1001e = true;
            if (this.f999c) {
                return !this.f1000d;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.f999c = true;
                M.a(this.f997a, this);
            }
            return true;
        }
    }

    public static boolean a(Animator animator) {
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            for (PropertyValuesHolder propertyValuesHolder : ((ValueAnimator) animator).getValues()) {
                if ("alpha".equals(propertyValuesHolder.getPropertyName())) {
                    return true;
                }
            }
        } else if (animator instanceof AnimatorSet) {
            ArrayList<Animator> childAnimations = ((AnimatorSet) animator).getChildAnimations();
            for (int i2 = 0; i2 < childAnimations.size(); i2++) {
                if (a(childAnimations.get(i2))) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void d() {
        SparseArray<Fragment> sparseArray = this.i;
        if (sparseArray != null) {
            for (int size = sparseArray.size() - 1; size >= 0; size--) {
                if (this.i.valueAt(size) == null) {
                    SparseArray<Fragment> sparseArray2 = this.i;
                    sparseArray2.delete(sparseArray2.keyAt(size));
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(androidx.fragment.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.h(r3, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r3.next()
            c.i.a.s$f r0 = (c.i.a.s.f) r0
            if (r4 == 0) goto L_0x0027
            boolean r1 = r0.f1003b
            if (r1 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r0.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.h(androidx.fragment.app.Fragment, boolean):void");
    }

    public void g() {
        this.v = false;
        this.w = false;
        a(2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(androidx.fragment.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.d(r3, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r3.next()
            c.i.a.s$f r0 = (c.i.a.s.f) r0
            if (r4 == 0) goto L_0x0027
            boolean r1 = r0.f1003b
            if (r1 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r0.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.d(androidx.fragment.app.Fragment, boolean):void");
    }

    public final void f() {
        this.f = false;
        this.B.clear();
        this.A.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g(androidx.fragment.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.g(r3, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r3.next()
            c.i.a.s$f r0 = (c.i.a.s.f) r0
            if (r4 == 0) goto L_0x0027
            boolean r1 = r0.f1003b
            if (r1 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r0.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.g(androidx.fragment.app.Fragment, boolean):void");
    }

    public Fragment b(int i2) {
        for (int size = this.h.size() - 1; size >= 0; size--) {
            Fragment fragment = this.h.get(size);
            if (fragment != null && fragment.z == i2) {
                return fragment;
            }
        }
        SparseArray<Fragment> sparseArray = this.i;
        if (sparseArray == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            Fragment valueAt = this.i.valueAt(size2);
            if (valueAt != null && valueAt.z == i2) {
                return valueAt;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(androidx.fragment.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.f(r3, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r3.next()
            c.i.a.s$f r0 = (c.i.a.s.f) r0
            if (r4 == 0) goto L_0x0027
            boolean r1 = r0.f1003b
            if (r1 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r0.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.f(androidx.fragment.app.Fragment, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0042, code lost:
        if (r0 == false) goto L_0x0045;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.view.View r5, c.i.a.s.c r6) {
        /*
            if (r5 == 0) goto L_0x006a
            if (r6 != 0) goto L_0x0005
            goto L_0x006a
        L_0x0005:
            int r0 = android.os.Build.VERSION.SDK_INT
            int r0 = r5.getLayerType()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0045
            boolean r0 = c.e.h.n.n(r5)
            if (r0 == 0) goto L_0x0045
            android.view.animation.Animation r0 = r6.f994a
            boolean r3 = r0 instanceof android.view.animation.AlphaAnimation
            if (r3 == 0) goto L_0x001c
            goto L_0x0035
        L_0x001c:
            boolean r3 = r0 instanceof android.view.animation.AnimationSet
            if (r3 == 0) goto L_0x003c
            android.view.animation.AnimationSet r0 = (android.view.animation.AnimationSet) r0
            java.util.List r0 = r0.getAnimations()
            r3 = 0
        L_0x0027:
            int r4 = r0.size()
            if (r3 >= r4) goto L_0x003a
            java.lang.Object r4 = r0.get(r3)
            boolean r4 = r4 instanceof android.view.animation.AlphaAnimation
            if (r4 == 0) goto L_0x0037
        L_0x0035:
            r0 = 1
            goto L_0x0042
        L_0x0037:
            int r3 = r3 + 1
            goto L_0x0027
        L_0x003a:
            r0 = 0
            goto L_0x0042
        L_0x003c:
            android.animation.Animator r0 = r6.f995b
            boolean r0 = a(r0)
        L_0x0042:
            if (r0 == 0) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            r1 = 0
        L_0x0046:
            if (r1 == 0) goto L_0x006a
            android.animation.Animator r0 = r6.f995b
            if (r0 == 0) goto L_0x0055
            c.i.a.s$d r6 = new c.i.a.s$d
            r6.<init>(r5)
            r0.addListener(r6)
            goto L_0x006a
        L_0x0055:
            android.view.animation.Animation r0 = r6.f994a
            android.view.animation.Animation$AnimationListener r0 = a(r0)
            r1 = 2
            r2 = 0
            r5.setLayerType(r1, r2)
            android.view.animation.Animation r6 = r6.f994a
            c.i.a.s$a r1 = new c.i.a.s$a
            r1.<init>(r5, r0)
            r6.setAnimationListener(r1)
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.a(android.view.View, c.i.a.s$c):void");
    }

    public void c(Fragment fragment) {
        if (f987a) {
            e.a.a.a.a.b("hide: ", fragment);
        }
        if (!fragment.C) {
            fragment.C = true;
            fragment.Q = true ^ fragment.Q;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(androidx.fragment.app.Fragment r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.d(r3, r4, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002b
            java.lang.Object r4 = r3.next()
            c.i.a.s$f r4 = (c.i.a.s.f) r4
            if (r5 == 0) goto L_0x0027
            boolean r0 = r4.f1003b
            if (r0 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r4.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.d(androidx.fragment.app.Fragment, android.os.Bundle, boolean):void");
    }

    @Override // c.i.a.AbstractC0069l
    public boolean b() {
        return this.v || this.w;
    }

    public final boolean b(ArrayList<C0058a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            if (!(this.f991e == null || this.f991e.size() == 0)) {
                int size = this.f991e.size();
                boolean z = false;
                for (int i2 = 0; i2 < size; i2++) {
                    ((C0058a) this.f991e.get(i2)).a(arrayList, arrayList2);
                    z |= true;
                }
                this.f991e.clear();
                this.q.f972c.removeCallbacks(this.H);
                return z;
            }
            return false;
        }
    }

    public void c(int i2) {
        synchronized (this) {
            this.l.set(i2, null);
            if (this.m == null) {
                this.m = new ArrayList<>();
            }
            if (f987a) {
                String str = "Freeing back stack index " + i2;
            }
            this.m.add(Integer.valueOf(i2));
        }
    }

    public final void c(boolean z) {
        if (this.f) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.q == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        } else if (Looper.myLooper() == this.q.f972c.getLooper()) {
            if (!z) {
                e();
            }
            if (this.A == null) {
                this.A = new ArrayList<>();
                this.B = new ArrayList<>();
            }
            this.f = true;
            try {
                a((ArrayList<C0058a>) null, (ArrayList<Boolean>) null);
            } finally {
                this.f = false;
            }
        } else {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
    }

    public final void a(RuntimeException runtimeException) {
        runtimeException.getMessage();
        PrintWriter printWriter = new PrintWriter(new c.e.g.a("FragmentManager"));
        AbstractC0068k kVar = this.q;
        try {
            if (kVar != null) {
                ActivityC0065h.this.dump("  ", null, printWriter, new String[0]);
            } else {
                a("  ", (FileDescriptor) null, printWriter, new String[0]);
            }
        } catch (Exception unused) {
        }
        throw runtimeException;
    }

    public void b(boolean z) {
        for (int size = this.h.size() - 1; size >= 0; size--) {
            Fragment fragment = this.h.get(size);
            if (fragment != null) {
                fragment.b(z);
            }
        }
    }

    public boolean b(Menu menu) {
        if (this.p < 1) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            Fragment fragment = this.h.get(i2);
            if (fragment != null && fragment.a(menu)) {
                z = true;
            }
        }
        return z;
    }

    @Override // c.i.a.AbstractC0069l
    public List<Fragment> a() {
        List<Fragment> list;
        if (this.h.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.h) {
            list = (List) this.h.clone();
        }
        return list;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0033 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0034 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean b(android.view.MenuItem r6) {
        /*
            r5 = this;
            int r0 = r5.p
            r1 = 0
            r2 = 1
            if (r0 >= r2) goto L_0x0007
            return r1
        L_0x0007:
            r0 = 0
        L_0x0008:
            java.util.ArrayList<androidx.fragment.app.Fragment> r3 = r5.h
            int r3 = r3.size()
            if (r0 >= r3) goto L_0x0037
            java.util.ArrayList<androidx.fragment.app.Fragment> r3 = r5.h
            java.lang.Object r3 = r3.get(r0)
            androidx.fragment.app.Fragment r3 = (androidx.fragment.app.Fragment) r3
            if (r3 == 0) goto L_0x0034
            boolean r4 = r3.C
            if (r4 != 0) goto L_0x0030
            boolean r4 = r3.G
            if (r4 == 0) goto L_0x0024
            boolean r4 = r3.H
        L_0x0024:
            c.i.a.s r3 = r3.v
            if (r3 == 0) goto L_0x0030
            boolean r3 = r3.b(r6)
            if (r3 == 0) goto L_0x0030
            r3 = 1
            goto L_0x0031
        L_0x0030:
            r3 = 0
        L_0x0031:
            if (r3 == 0) goto L_0x0034
            return r2
        L_0x0034:
            int r0 = r0 + 1
            goto L_0x0008
        L_0x0037:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.b(android.view.MenuItem):boolean");
    }

    @Override // c.i.a.AbstractC0069l
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        String a2 = e.a.a.a.a.a(str, "    ");
        SparseArray<Fragment> sparseArray = this.i;
        if (sparseArray != null && (size5 = sparseArray.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i2 = 0; i2 < size5; i2++) {
                Fragment valueAt = this.i.valueAt(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(valueAt);
                if (valueAt != null) {
                    printWriter.print(a2);
                    printWriter.print("mFragmentId=#");
                    printWriter.print(Integer.toHexString(valueAt.z));
                    printWriter.print(" mContainerId=#");
                    printWriter.print(Integer.toHexString(valueAt.A));
                    printWriter.print(" mTag=");
                    printWriter.println(valueAt.B);
                    printWriter.print(a2);
                    printWriter.print("mState=");
                    printWriter.print(valueAt.f246c);
                    printWriter.print(" mIndex=");
                    printWriter.print(valueAt.g);
                    printWriter.print(" mWho=");
                    printWriter.print(valueAt.h);
                    printWriter.print(" mBackStackNesting=");
                    printWriter.println(valueAt.s);
                    printWriter.print(a2);
                    printWriter.print("mAdded=");
                    printWriter.print(valueAt.m);
                    printWriter.print(" mRemoving=");
                    printWriter.print(valueAt.n);
                    printWriter.print(" mFromLayout=");
                    printWriter.print(valueAt.o);
                    printWriter.print(" mInLayout=");
                    printWriter.println(valueAt.p);
                    printWriter.print(a2);
                    printWriter.print("mHidden=");
                    printWriter.print(valueAt.C);
                    printWriter.print(" mDetached=");
                    printWriter.print(valueAt.D);
                    printWriter.print(" mMenuVisible=");
                    printWriter.print(valueAt.H);
                    printWriter.print(" mHasMenu=");
                    printWriter.println(valueAt.G);
                    printWriter.print(a2);
                    printWriter.print("mRetainInstance=");
                    printWriter.print(valueAt.E);
                    printWriter.print(" mRetaining=");
                    printWriter.print(valueAt.F);
                    printWriter.print(" mUserVisibleHint=");
                    printWriter.println(valueAt.N);
                    if (valueAt.t != null) {
                        printWriter.print(a2);
                        printWriter.print("mFragmentManager=");
                        printWriter.println(valueAt.t);
                    }
                    if (valueAt.u != null) {
                        printWriter.print(a2);
                        printWriter.print("mHost=");
                        printWriter.println(valueAt.u);
                    }
                    if (valueAt.y != null) {
                        printWriter.print(a2);
                        printWriter.print("mParentFragment=");
                        printWriter.println(valueAt.y);
                    }
                    if (valueAt.i != null) {
                        printWriter.print(a2);
                        printWriter.print("mArguments=");
                        printWriter.println(valueAt.i);
                    }
                    if (valueAt.f247d != null) {
                        printWriter.print(a2);
                        printWriter.print("mSavedFragmentState=");
                        printWriter.println(valueAt.f247d);
                    }
                    if (valueAt.f248e != null) {
                        printWriter.print(a2);
                        printWriter.print("mSavedViewState=");
                        printWriter.println(valueAt.f248e);
                    }
                    if (valueAt.j != null) {
                        printWriter.print(a2);
                        printWriter.print("mTarget=");
                        printWriter.print(valueAt.j);
                        printWriter.print(" mTargetRequestCode=");
                        printWriter.println(valueAt.l);
                    }
                    if (valueAt.k() != 0) {
                        printWriter.print(a2);
                        printWriter.print("mNextAnim=");
                        printWriter.println(valueAt.k());
                    }
                    if (valueAt.J != null) {
                        printWriter.print(a2);
                        printWriter.print("mContainer=");
                        printWriter.println(valueAt.J);
                    }
                    if (valueAt.K != null) {
                        printWriter.print(a2);
                        printWriter.print("mView=");
                        printWriter.println(valueAt.K);
                    }
                    if (valueAt.L != null) {
                        printWriter.print(a2);
                        printWriter.print("mInnerView=");
                        printWriter.println(valueAt.K);
                    }
                    if (valueAt.e() != null) {
                        printWriter.print(a2);
                        printWriter.print("mAnimatingAway=");
                        printWriter.println(valueAt.e());
                        printWriter.print(a2);
                        printWriter.print("mStateAfterAnimating=");
                        printWriter.println(valueAt.o());
                    }
                    if (valueAt.g() != null) {
                        c.k.a.a.a(valueAt).a(a2, fileDescriptor, printWriter, strArr);
                    }
                    if (valueAt.v != null) {
                        printWriter.print(a2);
                        printWriter.println("Child " + valueAt.v + ":");
                        valueAt.v.a(e.a.a.a.a.a(a2, "  "), fileDescriptor, printWriter, strArr);
                    }
                }
            }
        }
        int size6 = this.h.size();
        if (size6 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i3 = 0; i3 < size6; i3++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(this.h.get(i3).toString());
            }
        }
        ArrayList<Fragment> arrayList = this.k;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i4 = 0; i4 < size4; i4++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(this.k.get(i4).toString());
            }
        }
        ArrayList<C0058a> arrayList2 = this.j;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i5 = 0; i5 < size3; i5++) {
                C0058a aVar = this.j.get(i5);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i5);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.a(a2, printWriter, true);
            }
        }
        synchronized (this) {
            if (this.l != null && (size2 = this.l.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i6 = 0; i6 < size2; i6++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i6);
                    printWriter.print(": ");
                    printWriter.println((C0058a) this.l.get(i6));
                }
            }
            if (this.m != null && this.m.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.m.toArray()));
            }
        }
        ArrayList<h> arrayList3 = this.f991e;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i7 = 0; i7 < size; i7++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println((h) this.f991e.get(i7));
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.q);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.r);
        if (this.s != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.s);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.p);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.v);
        printWriter.print(" mStopped=");
        printWriter.print(this.w);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.x);
        if (this.u) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.u);
        }
        if (this.y != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.y);
        }
    }

    public final void c(ArrayList<C0058a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            a(arrayList, arrayList2);
            int size = arrayList.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                if (!arrayList.get(i2).s) {
                    if (i3 != i2) {
                        a(arrayList, arrayList2, i3, i2);
                    }
                    i3 = i2 + 1;
                    if (arrayList2.get(i2).booleanValue()) {
                        while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).s) {
                            i3++;
                        }
                    }
                    a(arrayList, arrayList2, i2, i3);
                    i2 = i3 - 1;
                }
                i2++;
            }
            if (i3 != size) {
                a(arrayList, arrayList2, i3, size);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(androidx.fragment.app.Fragment r3, android.content.Context r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.b(r3, r4, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002b
            java.lang.Object r4 = r3.next()
            c.i.a.s$f r4 = (c.i.a.s.f) r4
            if (r5 == 0) goto L_0x0027
            boolean r0 = r4.f1003b
            if (r0 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r4.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.b(androidx.fragment.app.Fragment, android.content.Context, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(androidx.fragment.app.Fragment r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.b(r3, r4, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002b
            java.lang.Object r4 = r3.next()
            c.i.a.s$f r4 = (c.i.a.s.f) r4
            if (r5 == 0) goto L_0x0027
            boolean r0 = r4.f1003b
            if (r0 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r4.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.b(androidx.fragment.app.Fragment, android.os.Bundle, boolean):void");
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(androidx.fragment.app.Fragment r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.c(r3, r4, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002b
            java.lang.Object r4 = r3.next()
            c.i.a.s$f r4 = (c.i.a.s.f) r4
            if (r5 == 0) goto L_0x0027
            boolean r0 = r4.f1003b
            if (r0 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r4.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.c(androidx.fragment.app.Fragment, android.os.Bundle, boolean):void");
    }

    public final void e() {
        if (b()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.y != null) {
            StringBuilder a2 = e.a.a.a.a.a("Can not perform this action inside of ");
            a2.append(this.y);
            throw new IllegalStateException(a2.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(androidx.fragment.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.b(r3, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r3.next()
            c.i.a.s$f r0 = (c.i.a.s.f) r0
            if (r4 == 0) goto L_0x0027
            boolean r1 = r0.f1003b
            if (r1 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r0.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.b(androidx.fragment.app.Fragment, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(androidx.fragment.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.c(r3, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r3.next()
            c.i.a.s$f r0 = (c.i.a.s.f) r0
            if (r4 == 0) goto L_0x0027
            boolean r1 = r0.f1003b
            if (r1 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r0.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.c(androidx.fragment.app.Fragment, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(androidx.fragment.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.e(r3, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r3.next()
            c.i.a.s$f r0 = (c.i.a.s.f) r0
            if (r4 == 0) goto L_0x0027
            boolean r1 = r0.f1003b
            if (r1 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r0.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.e(androidx.fragment.app.Fragment, boolean):void");
    }

    public static c a(float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(f989c);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(f990d);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new c(animationSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c.i.a.s.c a(androidx.fragment.app.Fragment r7, int r8, boolean r9, int r10) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.a(androidx.fragment.app.Fragment, int, boolean, int):c.i.a.s$c");
    }

    public static Animation.AnimationListener a(Animation animation) {
        try {
            if (f988b == null) {
                f988b = Animation.class.getDeclaredField("mListener");
                f988b.setAccessible(true);
            }
            return (Animation.AnimationListener) f988b.get(animation);
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0074, code lost:
        if (r0 != 3) goto L_0x068c;
     */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:372:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(androidx.fragment.app.Fragment r17, int r18, int r19, int r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 1723
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.a(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    public void a(int i2, boolean z) {
        AbstractC0068k kVar;
        if (this.q == null && i2 != 0) {
            throw new IllegalStateException("No activity");
        } else if (z || i2 != this.p) {
            this.p = i2;
            if (this.i != null) {
                int size = this.h.size();
                for (int i3 = 0; i3 < size; i3++) {
                    e(this.h.get(i3));
                }
                int size2 = this.i.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    Fragment valueAt = this.i.valueAt(i4);
                    if (valueAt != null && ((valueAt.n || valueAt.D) && !valueAt.P)) {
                        e(valueAt);
                    }
                }
                u();
                if (this.u && (kVar = this.q) != null && this.p == 4) {
                    ActivityC0065h.this.g();
                    this.u = false;
                }
            }
        }
    }

    public void a(Fragment fragment, boolean z) {
        if (f987a) {
            e.a.a.a.a.b("add: ", fragment);
        }
        d(fragment);
        if (fragment.D) {
            return;
        }
        if (!this.h.contains(fragment)) {
            synchronized (this.h) {
                this.h.add(fragment);
            }
            fragment.m = true;
            fragment.n = false;
            if (fragment.K == null) {
                fragment.Q = false;
            }
            if (fragment.G && fragment.H) {
                this.u = true;
            }
            if (z) {
                a(fragment, this.p, 0, 0, false);
                return;
            }
            return;
        }
        throw new IllegalStateException(e.a.a.a.a.a("Fragment already added: ", fragment));
    }

    public void a(Fragment fragment) {
        if (f987a) {
            e.a.a.a.a.b("attach: ", fragment);
        }
        if (fragment.D) {
            fragment.D = false;
            if (fragment.m) {
                return;
            }
            if (!this.h.contains(fragment)) {
                if (f987a) {
                    e.a.a.a.a.b("add from attach: ", fragment);
                }
                synchronized (this.h) {
                    this.h.add(fragment);
                }
                fragment.m = true;
                if (fragment.G && fragment.H) {
                    this.u = true;
                    return;
                }
                return;
            }
            throw new IllegalStateException(e.a.a.a.a.a("Fragment already added: ", fragment));
        }
    }

    public Fragment a(String str) {
        Fragment a2;
        SparseArray<Fragment> sparseArray = this.i;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            Fragment valueAt = this.i.valueAt(size);
            if (!(valueAt == null || (a2 = valueAt.a(str)) == null)) {
                return a2;
            }
        }
        return null;
    }

    public void a(int i2, C0058a aVar) {
        synchronized (this) {
            if (this.l == null) {
                this.l = new ArrayList<>();
            }
            int size = this.l.size();
            if (i2 < size) {
                if (f987a) {
                    String str = "Setting back stack index " + i2 + " to " + aVar;
                }
                this.l.set(i2, aVar);
            } else {
                while (size < i2) {
                    this.l.add(null);
                    if (this.m == null) {
                        this.m = new ArrayList<>();
                    }
                    if (f987a) {
                        String str2 = "Adding available back stack index " + size;
                    }
                    this.m.add(Integer.valueOf(size));
                    size++;
                }
                if (f987a) {
                    String str3 = "Adding back stack index " + i2 + " with " + aVar;
                }
                this.l.add(aVar);
            }
        }
    }

    public final void a(ArrayList<C0058a> arrayList, ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList<i> arrayList3 = this.F;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            i iVar = this.F.get(i2);
            if (arrayList == null || iVar.f1005a || (indexOf2 = arrayList.indexOf(iVar.f1006b)) == -1 || !arrayList2.get(indexOf2).booleanValue()) {
                if ((iVar.f1007c == 0) || (arrayList != null && iVar.f1006b.a(arrayList, 0, arrayList.size()))) {
                    this.F.remove(i2);
                    i2--;
                    size--;
                    if (arrayList == null || iVar.f1005a || (indexOf = arrayList.indexOf(iVar.f1006b)) == -1 || !arrayList2.get(indexOf).booleanValue()) {
                        iVar.a();
                    } else {
                        C0058a aVar = iVar.f1006b;
                        aVar.f948a.a(aVar, iVar.f1005a, false, false);
                    }
                }
            } else {
                C0058a aVar2 = iVar.f1006b;
                aVar2.f948a.a(aVar2, iVar.f1005a, false, false);
            }
            i2++;
        }
    }

    public final void a(ArrayList<C0058a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        boolean z;
        int i8;
        ArrayList<C0058a> arrayList3 = arrayList;
        ArrayList<Boolean> arrayList4 = arrayList2;
        boolean z2 = arrayList3.get(i2).s;
        ArrayList<Fragment> arrayList5 = this.C;
        if (arrayList5 == null) {
            this.C = new ArrayList<>();
        } else {
            arrayList5.clear();
        }
        this.C.addAll(this.h);
        Fragment fragment = this.t;
        int i9 = i2;
        boolean z3 = false;
        while (true) {
            int i10 = 1;
            if (i9 < i3) {
                C0058a aVar = arrayList3.get(i9);
                int i11 = 3;
                if (!arrayList4.get(i9).booleanValue()) {
                    ArrayList<Fragment> arrayList6 = this.C;
                    Fragment fragment2 = fragment;
                    int i12 = 0;
                    while (i12 < aVar.f949b.size()) {
                        C0058a.C0015a aVar2 = aVar.f949b.get(i12);
                        int i13 = aVar2.f953a;
                        if (i13 != i10) {
                            if (i13 != 2) {
                                if (i13 == i11 || i13 == 6) {
                                    arrayList6.remove(aVar2.f954b);
                                    Fragment fragment3 = aVar2.f954b;
                                    if (fragment3 == fragment2) {
                                        aVar.f949b.add(i12, new C0058a.C0015a(9, fragment3));
                                        i12++;
                                        fragment2 = null;
                                    }
                                } else if (i13 != 7) {
                                    if (i13 == 8) {
                                        aVar.f949b.add(i12, new C0058a.C0015a(9, fragment2));
                                        i12++;
                                        fragment2 = aVar2.f954b;
                                    }
                                }
                                i8 = 1;
                            } else {
                                Fragment fragment4 = aVar2.f954b;
                                int i14 = fragment4.A;
                                int i15 = i12;
                                Fragment fragment5 = fragment2;
                                boolean z4 = false;
                                for (int size = arrayList6.size() - 1; size >= 0; size--) {
                                    Fragment fragment6 = arrayList6.get(size);
                                    if (fragment6.A != i14) {
                                        i14 = i14;
                                    } else if (fragment6 == fragment4) {
                                        i14 = i14;
                                        z4 = true;
                                    } else {
                                        if (fragment6 == fragment5) {
                                            i14 = i14;
                                            aVar.f949b.add(i15, new C0058a.C0015a(9, fragment6));
                                            i15++;
                                            fragment5 = null;
                                        } else {
                                            i14 = i14;
                                        }
                                        C0058a.C0015a aVar3 = new C0058a.C0015a(3, fragment6);
                                        aVar3.f955c = aVar2.f955c;
                                        aVar3.f957e = aVar2.f957e;
                                        aVar3.f956d = aVar2.f956d;
                                        aVar3.f = aVar2.f;
                                        aVar.f949b.add(i15, aVar3);
                                        arrayList6.remove(fragment6);
                                        i15++;
                                    }
                                }
                                if (z4) {
                                    aVar.f949b.remove(i15);
                                    i12 = i15 - 1;
                                    i8 = 1;
                                } else {
                                    i8 = 1;
                                    aVar2.f953a = 1;
                                    arrayList6.add(fragment4);
                                    i12 = i15;
                                }
                                fragment2 = fragment5;
                            }
                            i12 += i8;
                            i11 = 3;
                            i10 = 1;
                        }
                        i8 = 1;
                        arrayList6.add(aVar2.f954b);
                        i12 += i8;
                        i11 = 3;
                        i10 = 1;
                    }
                    fragment = fragment2;
                } else {
                    ArrayList<Fragment> arrayList7 = this.C;
                    Fragment fragment7 = fragment;
                    for (int i16 = 0; i16 < aVar.f949b.size(); i16++) {
                        C0058a.C0015a aVar4 = aVar.f949b.get(i16);
                        int i17 = aVar4.f953a;
                        if (i17 != 1) {
                            if (i17 != 3) {
                                switch (i17) {
                                    case 8:
                                        fragment7 = null;
                                        break;
                                    case 9:
                                        fragment7 = aVar4.f954b;
                                        break;
                                }
                            }
                            arrayList7.add(aVar4.f954b);
                        }
                        arrayList7.remove(aVar4.f954b);
                    }
                    fragment = fragment7;
                }
                z3 = z3 || aVar.i;
                i9++;
                arrayList3 = arrayList;
                arrayList4 = arrayList2;
            } else {
                this.C.clear();
                if (!z2) {
                    C.a(this, arrayList, arrayList2, i2, i3, false);
                }
                int i18 = i2;
                while (i18 < i3) {
                    C0058a aVar5 = arrayList.get(i18);
                    if (arrayList2.get(i18).booleanValue()) {
                        aVar5.a(-1);
                        aVar5.a(i18 == i3 + (-1));
                    } else {
                        aVar5.a(1);
                        aVar5.a();
                    }
                    i18++;
                }
                if (z2) {
                    c.c.d<Fragment> dVar = new c.c.d<>();
                    a(dVar);
                    i5 = i2;
                    int i19 = i3;
                    for (int i20 = i3 - 1; i20 >= i5; i20--) {
                        C0058a aVar6 = arrayList.get(i20);
                        boolean booleanValue = arrayList2.get(i20).booleanValue();
                        int i21 = 0;
                        while (true) {
                            if (i21 >= aVar6.f949b.size()) {
                                z = false;
                            } else if (C0058a.a(aVar6.f949b.get(i21))) {
                                z = true;
                            } else {
                                i21++;
                            }
                        }
                        if (z && !aVar6.a(arrayList, i20 + 1, i3)) {
                            if (this.F == null) {
                                this.F = new ArrayList<>();
                            }
                            i iVar = new i(aVar6, booleanValue);
                            this.F.add(iVar);
                            for (int i22 = 0; i22 < aVar6.f949b.size(); i22++) {
                                C0058a.C0015a aVar7 = aVar6.f949b.get(i22);
                                if (C0058a.a(aVar7)) {
                                    aVar7.f954b.a(iVar);
                                }
                            }
                            if (booleanValue) {
                                aVar6.a();
                            } else {
                                aVar6.a(false);
                            }
                            i19--;
                            if (i20 != i19) {
                                arrayList.remove(i20);
                                arrayList.add(i19, aVar6);
                            }
                            a(dVar);
                        }
                    }
                    i4 = 0;
                    int size2 = dVar.size();
                    for (int i23 = 0; i23 < size2; i23++) {
                        Fragment fragment8 = (Fragment) dVar.h[i23];
                        if (!fragment8.m) {
                            View view = fragment8.K;
                            fragment8.R = view.getAlpha();
                            view.setAlpha(0.0f);
                        }
                    }
                    i6 = i19;
                } else {
                    i5 = i2;
                    i4 = 0;
                    i6 = i3;
                }
                if (i6 != i5 && z2) {
                    C.a(this, arrayList, arrayList2, i2, i6, true);
                    a(this.p, true);
                }
                while (i5 < i3) {
                    C0058a aVar8 = arrayList.get(i5);
                    if (arrayList2.get(i5).booleanValue() && (i7 = aVar8.l) >= 0) {
                        c(i7);
                        aVar8.l = -1;
                    }
                    ArrayList<Runnable> arrayList8 = aVar8.t;
                    if (arrayList8 != null) {
                        int size3 = arrayList8.size();
                        for (int i24 = 0; i24 < size3; i24++) {
                            aVar8.t.get(i24).run();
                        }
                        aVar8.t = null;
                    }
                    i5++;
                }
                if (z3 && this.n != null) {
                    while (i4 < this.n.size()) {
                        this.n.get(i4).onBackStackChanged();
                        i4++;
                    }
                    return;
                }
                return;
            }
        }
    }

    public void a(C0058a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.a(z3);
        } else {
            aVar.a();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            C.a(this, (ArrayList<C0058a>) arrayList, (ArrayList<Boolean>) arrayList2, 0, 1, true);
        }
        if (z3) {
            a(this.p, true);
        }
        SparseArray<Fragment> sparseArray = this.i;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                Fragment valueAt = this.i.valueAt(i2);
                if (valueAt != null && valueAt.K != null && valueAt.P && aVar.b(valueAt.A)) {
                    float f2 = valueAt.R;
                    if (f2 > 0.0f) {
                        valueAt.K.setAlpha(f2);
                    }
                    if (z3) {
                        valueAt.R = 0.0f;
                    } else {
                        valueAt.R = -1.0f;
                        valueAt.P = false;
                    }
                }
            }
        }
    }

    public final void a(c.c.d<Fragment> dVar) {
        int i2 = this.p;
        if (i2 >= 1) {
            int min = Math.min(i2, 3);
            int size = this.h.size();
            for (int i3 = 0; i3 < size; i3++) {
                Fragment fragment = this.h.get(i3);
                if (fragment.f246c < min) {
                    a(fragment, min, fragment.k(), fragment.l(), false);
                    if (fragment.K != null && !fragment.C && fragment.P) {
                        dVar.add(fragment);
                    }
                }
            }
        }
    }

    public void a(Parcelable parcelable, t tVar) {
        List<r> list;
        List<t> list2;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.f255a != null) {
                if (tVar != null) {
                    List<Fragment> list3 = tVar.f1008a;
                    list2 = tVar.f1009b;
                    list = tVar.f1010c;
                    int size = list3 != null ? list3.size() : 0;
                    for (int i2 = 0; i2 < size; i2++) {
                        Fragment fragment = list3.get(i2);
                        if (f987a) {
                            e.a.a.a.a.b("restoreAllState: re-attaching retained ", fragment);
                        }
                        int i3 = 0;
                        while (true) {
                            FragmentState[] fragmentStateArr = fragmentManagerState.f255a;
                            if (i3 >= fragmentStateArr.length || fragmentStateArr[i3].f261b == fragment.g) {
                                break;
                            }
                            i3++;
                        }
                        FragmentState[] fragmentStateArr2 = fragmentManagerState.f255a;
                        if (i3 != fragmentStateArr2.length) {
                            FragmentState fragmentState = fragmentStateArr2[i3];
                            fragmentState.l = fragment;
                            fragment.f248e = null;
                            fragment.s = 0;
                            fragment.p = false;
                            fragment.m = false;
                            fragment.j = null;
                            Bundle bundle = fragmentState.k;
                            if (bundle != null) {
                                bundle.setClassLoader(this.q.f971b.getClassLoader());
                                fragment.f248e = fragmentState.k.getSparseParcelableArray("android:view_state");
                                fragment.f247d = fragmentState.k;
                            }
                        } else {
                            StringBuilder a2 = e.a.a.a.a.a("Could not find active fragment with index ");
                            a2.append(fragment.g);
                            a(new IllegalStateException(a2.toString()));
                            throw null;
                        }
                    }
                } else {
                    list2 = null;
                    list = null;
                }
                this.i = new SparseArray<>(fragmentManagerState.f255a.length);
                int i4 = 0;
                while (true) {
                    FragmentState[] fragmentStateArr3 = fragmentManagerState.f255a;
                    if (i4 >= fragmentStateArr3.length) {
                        break;
                    }
                    FragmentState fragmentState2 = fragmentStateArr3[i4];
                    if (fragmentState2 != null) {
                        Fragment a3 = fragmentState2.a(this.q, this.r, this.s, (list2 == null || i4 >= list2.size()) ? null : list2.get(i4), (list == null || i4 >= list.size()) ? null : list.get(i4));
                        if (f987a) {
                            String str = "restoreAllState: active #" + i4 + ": " + a3;
                        }
                        this.i.put(a3.g, a3);
                        fragmentState2.l = null;
                    }
                    i4++;
                }
                if (tVar != null) {
                    List<Fragment> list4 = tVar.f1008a;
                    int size2 = list4 != null ? list4.size() : 0;
                    for (int i5 = 0; i5 < size2; i5++) {
                        Fragment fragment2 = list4.get(i5);
                        int i6 = fragment2.k;
                        if (i6 >= 0) {
                            fragment2.j = this.i.get(i6);
                            if (fragment2.j == null) {
                                String str2 = "Re-attaching retained fragment " + fragment2 + " target no longer exists: " + fragment2.k;
                            }
                        }
                    }
                }
                this.h.clear();
                if (fragmentManagerState.f256b != null) {
                    int i7 = 0;
                    while (true) {
                        int[] iArr = fragmentManagerState.f256b;
                        if (i7 >= iArr.length) {
                            break;
                        }
                        Fragment fragment3 = this.i.get(iArr[i7]);
                        if (fragment3 != null) {
                            fragment3.m = true;
                            if (f987a) {
                                String str3 = "restoreAllState: added #" + i7 + ": " + fragment3;
                            }
                            if (!this.h.contains(fragment3)) {
                                synchronized (this.h) {
                                    this.h.add(fragment3);
                                }
                                i7++;
                            } else {
                                throw new IllegalStateException("Already added!");
                            }
                        } else {
                            StringBuilder a4 = e.a.a.a.a.a("No instantiated fragment for index #");
                            a4.append(fragmentManagerState.f256b[i7]);
                            a(new IllegalStateException(a4.toString()));
                            throw null;
                        }
                    }
                }
                BackStackState[] backStackStateArr = fragmentManagerState.f257c;
                if (backStackStateArr != null) {
                    this.j = new ArrayList<>(backStackStateArr.length);
                    int i8 = 0;
                    while (true) {
                        BackStackState[] backStackStateArr2 = fragmentManagerState.f257c;
                        if (i8 >= backStackStateArr2.length) {
                            break;
                        }
                        C0058a a5 = backStackStateArr2[i8].a(this);
                        if (f987a) {
                            String str4 = "restoreAllState: back stack #" + i8 + " (index " + a5.l + "): " + a5;
                            PrintWriter printWriter = new PrintWriter(new c.e.g.a("FragmentManager"));
                            a5.a("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.j.add(a5);
                        int i9 = a5.l;
                        if (i9 >= 0) {
                            a(i9, a5);
                        }
                        i8++;
                    }
                } else {
                    this.j = null;
                }
                int i10 = fragmentManagerState.f258d;
                if (i10 >= 0) {
                    this.t = this.i.get(i10);
                }
                this.g = fragmentManagerState.f259e;
            }
        }
    }

    public void a(AbstractC0068k kVar, AbstractC0066i iVar, Fragment fragment) {
        if (this.q == null) {
            this.q = kVar;
            this.r = iVar;
            this.s = fragment;
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    /* JADX WARN: Finally extract failed */
    public final void a(int i2) {
        try {
            this.f = true;
            a(i2, false);
            this.f = false;
            o();
        } catch (Throwable th) {
            this.f = false;
            throw th;
        }
    }

    public void a(boolean z) {
        for (int size = this.h.size() - 1; size >= 0; size--) {
            Fragment fragment = this.h.get(size);
            if (fragment != null) {
                fragment.a(z);
            }
        }
    }

    public void a(Configuration configuration) {
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            Fragment fragment = this.h.get(i2);
            if (fragment != null) {
                fragment.I = true;
                s sVar = fragment.v;
                if (sVar != null) {
                    sVar.a(configuration);
                }
            }
        }
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        if (this.p < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            Fragment fragment = this.h.get(i2);
            if (fragment != null && fragment.a(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.k != null) {
            for (int i3 = 0; i3 < this.k.size(); i3++) {
                Fragment fragment2 = this.k.get(i3);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.s();
                }
            }
        }
        this.k = arrayList;
        return z;
    }

    public boolean a(MenuItem menuItem) {
        s sVar;
        if (this.p < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            Fragment fragment = this.h.get(i2);
            if (fragment != null) {
                if (!fragment.C && (sVar = fragment.v) != null && sVar.a(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void a(Menu menu) {
        if (this.p >= 1) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                Fragment fragment = this.h.get(i2);
                if (fragment != null && !fragment.C) {
                    if (fragment.G) {
                        boolean z = fragment.H;
                    }
                    s sVar = fragment.v;
                    if (sVar != null) {
                        sVar.a(menu);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(androidx.fragment.app.Fragment r3, android.content.Context r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.a(r3, r4, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002b
            java.lang.Object r4 = r3.next()
            c.i.a.s$f r4 = (c.i.a.s.f) r4
            if (r5 == 0) goto L_0x0027
            boolean r0 = r4.f1003b
            if (r0 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r4.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.a(androidx.fragment.app.Fragment, android.content.Context, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(androidx.fragment.app.Fragment r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.a(r3, r4, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002b
            java.lang.Object r4 = r3.next()
            c.i.a.s$f r4 = (c.i.a.s.f) r4
            if (r5 == 0) goto L_0x0027
            boolean r0 = r4.f1003b
            if (r0 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r4.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.a(androidx.fragment.app.Fragment, android.os.Bundle, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(androidx.fragment.app.Fragment r3, android.view.View r4, android.os.Bundle r5, boolean r6) {
        /*
            r2 = this;
            androidx.fragment.app.Fragment r0 = r2.s
            if (r0 == 0) goto L_0x000e
            c.i.a.s r0 = r0.t
            boolean r1 = r0 instanceof c.i.a.s
            if (r1 == 0) goto L_0x000e
            r1 = 1
            r0.a(r3, r4, r5, r1)
        L_0x000e:
            java.util.concurrent.CopyOnWriteArrayList<c.i.a.s$f> r3 = r2.o
            java.util.Iterator r3 = r3.iterator()
        L_0x0014:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002b
            java.lang.Object r4 = r3.next()
            c.i.a.s$f r4 = (c.i.a.s.f) r4
            if (r6 == 0) goto L_0x0027
            boolean r5 = r4.f1003b
            if (r5 != 0) goto L_0x0027
            goto L_0x0014
        L_0x0027:
            c.i.a.l$b r3 = r4.f1002a
            r3 = 0
            throw r3
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.i.a.s.a(androidx.fragment.app.Fragment, android.view.View, android.os.Bundle, boolean):void");
    }
}
