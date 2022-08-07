package androidx.fragment.app;

import android.animation.Animator;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import c.a.a.C;
import c.c.i;
import c.e.a.d;
import c.i.a.AbstractC0068k;
import c.i.a.AbstractC0069l;
import c.i.a.ActivityC0065h;
import c.i.a.C0061d;
import c.i.a.C0062e;
import c.i.a.C0063f;
import c.i.a.s;
import c.i.a.t;
import c.j.f;
import c.j.h;
import c.j.m;
import c.j.r;
import c.j.s;
import com.tencent.smtt.sdk.TbsListener;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, h, s {

    /* renamed from: a */
    public static final i<String, Class<?>> f244a = new i<>();

    /* renamed from: b */
    public static final Object f245b = new Object();
    public int A;
    public String B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean I;
    public ViewGroup J;
    public View K;
    public View L;
    public boolean M;
    public a O;
    public boolean P;
    public boolean Q;
    public float R;
    public LayoutInflater S;
    public boolean T;
    public c.j.i V;
    public h W;

    /* renamed from: d */
    public Bundle f247d;

    /* renamed from: e */
    public SparseArray<Parcelable> f248e;
    public Boolean f;
    public String h;
    public Bundle i;
    public Fragment j;
    public int l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;
    public c.i.a.s t;
    public AbstractC0068k u;
    public c.i.a.s v;
    public t w;
    public r x;
    public Fragment y;
    public int z;

    /* renamed from: c */
    public int f246c = 0;
    public int g = -1;
    public int k = -1;
    public boolean H = true;
    public boolean N = true;
    public c.j.i U = new c.j.i(this);
    public m<h> X = new m<>();

    /* loaded from: classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0063f();

        /* renamed from: a */
        public final Bundle f249a;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle;
            this.f249a = parcel.readBundle();
            if (classLoader != null && (bundle = this.f249a) != null) {
                bundle.setClassLoader(classLoader);
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.f249a);
        }
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public View f250a;

        /* renamed from: b */
        public Animator f251b;

        /* renamed from: c */
        public int f252c;

        /* renamed from: d */
        public int f253d;

        /* renamed from: e */
        public int f254e;
        public int f;
        public Object h;
        public Object j;
        public Object l;
        public Boolean m;
        public Boolean n;
        public d o;
        public d p;
        public boolean q;
        public c r;
        public boolean s;
        public Object g = null;
        public Object i = null;
        public Object k = null;

        public a() {
            Object obj = Fragment.f245b;
            this.h = obj;
            this.j = obj;
            this.l = obj;
        }
    }

    /* loaded from: classes.dex */
    public static class b extends RuntimeException {
        public b(String str, Exception exc) {
            super(str, exc);
        }
    }

    /* loaded from: classes.dex */
    public interface c {
    }

    @Override // c.j.h
    public f a() {
        return this.U;
    }

    @Override // c.j.s
    public r b() {
        if (g() != null) {
            if (this.x == null) {
                this.x = new r();
            }
            return this.x;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public void c(Bundle bundle) {
        if (this.g >= 0) {
            c.i.a.s sVar = this.t;
            if (sVar == null ? false : sVar.b()) {
                throw new IllegalStateException("Fragment already active and state has been saved");
            }
        }
        this.i = bundle;
    }

    public final a d() {
        if (this.O == null) {
            this.O = new a();
        }
        return this.O;
    }

    public View e() {
        a aVar = this.O;
        if (aVar == null) {
            return null;
        }
        return aVar.f250a;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Animator f() {
        a aVar = this.O;
        if (aVar == null) {
            return null;
        }
        return aVar.f251b;
    }

    public Context g() {
        AbstractC0068k kVar = this.u;
        if (kVar == null) {
            return null;
        }
        return kVar.f971b;
    }

    public Object h() {
        a aVar = this.O;
        if (aVar == null) {
            return null;
        }
        return aVar.g;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void i() {
        a aVar = this.O;
        if (aVar != null) {
            d dVar = aVar.o;
        }
    }

    public Object j() {
        a aVar = this.O;
        if (aVar == null) {
            return null;
        }
        return aVar.i;
    }

    public int k() {
        a aVar = this.O;
        if (aVar == null) {
            return 0;
        }
        return aVar.f253d;
    }

    public int l() {
        a aVar = this.O;
        if (aVar == null) {
            return 0;
        }
        return aVar.f254e;
    }

    public int m() {
        a aVar = this.O;
        if (aVar == null) {
            return 0;
        }
        return aVar.f;
    }

    public Object n() {
        a aVar = this.O;
        if (aVar == null) {
            return null;
        }
        return aVar.k;
    }

    public int o() {
        a aVar = this.O;
        if (aVar == null) {
            return 0;
        }
        return aVar.f252c;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.I = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        AbstractC0068k kVar = this.u;
        (kVar == null ? null : (ActivityC0065h) kVar.f970a).onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.I = true;
    }

    public void p() {
        if (this.u != null) {
            this.v = new c.i.a.s();
            c.i.a.s sVar = this.v;
            AbstractC0068k kVar = this.u;
            C0061d dVar = new C0061d(this);
            if (sVar.q == null) {
                sVar.q = kVar;
                sVar.r = dVar;
                sVar.s = this;
                return;
            }
            throw new IllegalStateException("Already attached");
        }
        throw new IllegalStateException("Fragment has not been attached yet.");
    }

    public boolean q() {
        a aVar = this.O;
        if (aVar == null) {
            return false;
        }
        return aVar.s;
    }

    public final boolean r() {
        return this.s > 0;
    }

    public void s() {
    }

    public AbstractC0069l t() {
        return this.v;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((int) TbsListener.ErrorCode.DOWNLOAD_INTERRUPT);
        C.a((Object) this, sb);
        if (this.g >= 0) {
            sb.append(" #");
            sb.append(this.g);
        }
        if (this.z != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.z));
        }
        if (this.B != null) {
            sb.append(" ");
            sb.append(this.B);
        }
        sb.append('}');
        return sb.toString();
    }

    public void u() {
        this.I = true;
        c.i.a.s sVar = this.v;
        if (sVar != null) {
            sVar.j();
        }
    }

    public static Fragment a(Context context, String str, Bundle bundle) {
        try {
            Class<?> cls = f244a.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                f244a.put(str, cls);
            }
            Fragment fragment = (Fragment) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.c(bundle);
            }
            return fragment;
        } catch (ClassNotFoundException e2) {
            throw new b("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (IllegalAccessException e3) {
            throw new b("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (InstantiationException e4) {
            throw new b("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e4);
        } catch (NoSuchMethodException e5) {
            throw new b("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e5);
        } catch (InvocationTargetException e6) {
            throw new b("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e6);
        }
    }

    public void b(Bundle bundle) {
        Parcelable parcelable;
        if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
            if (this.v == null) {
                p();
            }
            this.v.a(parcelable, this.w);
            this.w = null;
            this.v.h();
        }
    }

    public void c() {
        a aVar = this.O;
        Object obj = null;
        if (aVar != null) {
            aVar.q = false;
            Object obj2 = aVar.r;
            aVar.r = null;
            obj = obj2;
        }
        if (obj != null) {
            s.i iVar = (s.i) obj;
            iVar.f1007c--;
            if (iVar.f1007c == 0) {
                iVar.f1006b.f948a.t();
            }
        }
    }

    public void b(boolean z) {
        c.i.a.s sVar = this.v;
        if (sVar != null) {
            sVar.b(z);
        }
    }

    public static boolean a(Context context, String str) {
        try {
            Class<?> cls = f244a.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                f244a.put(str, cls);
            }
            return Fragment.class.isAssignableFrom(cls);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public void c(boolean z) {
        d().s = z;
    }

    public final void a(int i, Fragment fragment) {
        this.g = i;
        if (fragment != null) {
            this.h = fragment.h + ":" + this.g;
            return;
        }
        StringBuilder a2 = e.a.a.a.a.a("android:fragment:");
        a2.append(this.g);
        this.h = a2.toString();
    }

    public LayoutInflater a(Bundle bundle) {
        AbstractC0068k kVar = this.u;
        if (kVar != null) {
            ActivityC0065h.a aVar = (ActivityC0065h.a) kVar;
            LayoutInflater cloneInContext = ActivityC0065h.this.getLayoutInflater().cloneInContext(ActivityC0065h.this);
            if (this.v == null) {
                p();
                int i = this.f246c;
                if (i >= 4) {
                    this.v.l();
                } else if (i >= 3) {
                    this.v.m();
                } else if (i >= 2) {
                    this.v.g();
                } else if (i >= 1) {
                    this.v.h();
                }
            }
            c.i.a.s sVar = this.v;
            sVar.p();
            C.a(cloneInContext, (LayoutInflater.Factory2) sVar);
            this.S = cloneInContext;
            return this.S;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    public void a(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.I = true;
        AbstractC0068k kVar = this.u;
        if ((kVar == null ? null : kVar.f970a) != null) {
            this.I = false;
            this.I = true;
        }
    }

    public Fragment a(String str) {
        if (str.equals(this.h)) {
            return this;
        }
        c.i.a.s sVar = this.v;
        if (sVar != null) {
            return sVar.a(str);
        }
        return null;
    }

    public void a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        c.i.a.s sVar = this.v;
        if (sVar != null) {
            sVar.q();
        }
        this.r = true;
        this.W = new C0062e(this);
        this.V = null;
        this.K = null;
        if (this.K != null) {
            this.W.a();
            this.X.a((m<h>) this.W);
        } else if (this.V == null) {
            this.W = null;
        } else {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
    }

    public void a(boolean z) {
        c.i.a.s sVar = this.v;
        if (sVar != null) {
            sVar.a(z);
        }
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.C) {
            return false;
        }
        if (this.G && this.H) {
            z = true;
        }
        c.i.a.s sVar = this.v;
        return sVar != null ? z | sVar.a(menu, menuInflater) : z;
    }

    public boolean a(Menu menu) {
        boolean z = false;
        if (this.C) {
            return false;
        }
        if (this.G && this.H) {
            z = true;
        }
        c.i.a.s sVar = this.v;
        return sVar != null ? z | sVar.b(menu) : z;
    }

    public void a(c cVar) {
        d();
        c cVar2 = this.O.r;
        if (cVar != cVar2) {
            if (cVar == null || cVar2 == null) {
                a aVar = this.O;
                if (aVar.q) {
                    aVar.r = cVar;
                }
                if (cVar != null) {
                    ((s.i) cVar).f1007c++;
                    return;
                }
                return;
            }
            throw new IllegalStateException(e.a.a.a.a.a("Trying to set a replacement startPostponedEnterTransition on ", this));
        }
    }

    public void a(int i) {
        if (this.O != null || i != 0) {
            d().f253d = i;
        }
    }

    public void a(View view) {
        d().f250a = view;
    }

    public void a(Animator animator) {
        d().f251b = animator;
    }
}
