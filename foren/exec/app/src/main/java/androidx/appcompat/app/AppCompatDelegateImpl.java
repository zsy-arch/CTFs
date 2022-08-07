package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import c.a.a.AbstractC0020a;
import c.a.a.E;
import c.a.a.I;
import c.a.a.n;
import c.a.a.o;
import c.a.a.p;
import c.a.a.q;
import c.a.a.t;
import c.a.a.x;
import c.a.a.z;
import c.a.e.a;
import c.a.e.a.l;
import c.a.e.a.v;
import c.a.e.e;
import c.a.e.i;
import c.a.f.C;
import c.a.f.ka;
import c.a.f.wa;
import c.a.f.xa;
import c.a.g;
import c.a.j;
import c.e.h.r;
import java.lang.reflect.Method;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class AppCompatDelegateImpl extends o implements l.a, LayoutInflater.Factory2 {

    /* renamed from: b */
    public static final boolean f24b = false;

    /* renamed from: c */
    public static final int[] f25c = {16842836};

    /* renamed from: d */
    public static boolean f26d;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public PanelFeatureState[] H;
    public PanelFeatureState I;
    public boolean J;
    public boolean K;
    public boolean M;
    public d N;
    public boolean O;
    public int P;
    public boolean R;
    public Rect S;
    public Rect T;
    public AppCompatViewInflater U;

    /* renamed from: e */
    public final Context f27e;
    public final Window f;
    public final Window.Callback g;
    public final Window.Callback h;
    public final n i;
    public AbstractC0020a j;
    public MenuInflater k;
    public CharSequence l;
    public C m;
    public a n;
    public f o;
    public c.a.e.a p;
    public ActionBarContextView q;
    public PopupWindow r;
    public Runnable s;
    public boolean v;
    public ViewGroup w;
    public TextView x;
    public View y;
    public boolean z;
    public r t = null;
    public boolean u = true;
    public int L = -100;
    public final Runnable Q = new q(this);

    /* loaded from: classes.dex */
    public class b implements a.AbstractC0005a {

        /* renamed from: a */
        public a.AbstractC0005a f37a;

        public b(a.AbstractC0005a aVar) {
            AppCompatDelegateImpl.this = r1;
            this.f37a = aVar;
        }

        @Override // c.a.e.a.AbstractC0005a
        public boolean a(c.a.e.a aVar, Menu menu) {
            return this.f37a.a(aVar, menu);
        }

        @Override // c.a.e.a.AbstractC0005a
        public boolean b(c.a.e.a aVar, Menu menu) {
            return this.f37a.b(aVar, menu);
        }

        @Override // c.a.e.a.AbstractC0005a
        public boolean a(c.a.e.a aVar, MenuItem menuItem) {
            return this.f37a.a(aVar, menuItem);
        }

        @Override // c.a.e.a.AbstractC0005a
        public void a(c.a.e.a aVar) {
            this.f37a.a(aVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.r != null) {
                appCompatDelegateImpl.f.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.s);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.q != null) {
                appCompatDelegateImpl2.d();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                r a2 = c.e.h.n.a(appCompatDelegateImpl3.q);
                a2.a(0.0f);
                appCompatDelegateImpl3.t = a2;
                AppCompatDelegateImpl.this.t.a(new x(this));
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            n nVar = appCompatDelegateImpl4.i;
            if (nVar != null) {
                nVar.b(appCompatDelegateImpl4.p);
            }
            AppCompatDelegateImpl.this.p = null;
        }
    }

    /* loaded from: classes.dex */
    public final class d {

        /* renamed from: a */
        public E f40a;

        /* renamed from: b */
        public boolean f41b;

        /* renamed from: c */
        public BroadcastReceiver f42c;

        /* renamed from: d */
        public IntentFilter f43d;

        public d(E e2) {
            AppCompatDelegateImpl.this = r1;
            this.f40a = e2;
            this.f41b = e2.a();
        }

        public void a() {
            BroadcastReceiver broadcastReceiver = this.f42c;
            if (broadcastReceiver != null) {
                AppCompatDelegateImpl.this.f27e.unregisterReceiver(broadcastReceiver);
                this.f42c = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public class e extends ContentFrameLayout {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(context);
            AppCompatDelegateImpl.this = r1;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                    AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                    appCompatDelegateImpl.a(appCompatDelegateImpl.a(0, true), true);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(c.a.b.a.a.c(getContext(), i));
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (f24b && !f26d) {
            Thread.setDefaultUncaughtExceptionHandler(new p(Thread.getDefaultUncaughtExceptionHandler()));
            f26d = true;
        }
    }

    public AppCompatDelegateImpl(Context context, Window window, n nVar) {
        this.f27e = context;
        this.f = window;
        this.i = nVar;
        this.g = this.f.getCallback();
        Window.Callback callback = this.g;
        if (!(callback instanceof c)) {
            this.h = new c(callback);
            this.f.setCallback(this.h);
            ka a2 = ka.a(context, (AttributeSet) null, f25c);
            Drawable c2 = a2.c(0);
            if (c2 != null) {
                this.f.setBackgroundDrawable(c2);
            }
            a2.f605b.recycle();
            return;
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    @Override // c.a.a.o
    public void a(Bundle bundle) {
        Window.Callback callback = this.g;
        if (callback instanceof Activity) {
            String str = null;
            try {
                Activity activity = (Activity) callback;
                try {
                    str = c.a.a.C.b(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e2) {
                    throw new IllegalArgumentException(e2);
                }
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                AbstractC0020a aVar = this.j;
                if (aVar == null) {
                    this.R = true;
                } else {
                    aVar.b(true);
                }
            }
        }
        if (bundle != null && this.L == -100) {
            this.L = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    @Override // c.a.a.o
    public void b(int i) {
        f();
        ViewGroup viewGroup = (ViewGroup) this.w.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f27e).inflate(i, viewGroup);
        this.g.onContentChanged();
    }

    @Override // c.a.a.o
    public void c() {
        i();
        AbstractC0020a aVar = this.j;
        d(0);
    }

    public void d() {
        r rVar = this.t;
        if (rVar != null) {
            rVar.a();
        }
    }

    public void e(int i) {
        if (i == 108) {
            i();
            AbstractC0020a aVar = this.j;
            if (aVar != null) {
                aVar.a(true);
            }
        }
    }

    public void f(int i) {
        if (i == 108) {
            i();
            AbstractC0020a aVar = this.j;
            if (aVar != null) {
                aVar.a(false);
            }
        } else if (i == 0) {
            PanelFeatureState a2 = a(i, true);
            if (a2.o) {
                a(a2, false);
            }
        }
    }

    public final Context g() {
        i();
        AbstractC0020a aVar = this.j;
        Context c2 = aVar != null ? aVar.c() : null;
        return c2 == null ? this.f27e : c2;
    }

    public final Window.Callback h() {
        return this.f.getCallback();
    }

    public final void i() {
        f();
        if (this.B && this.j == null) {
            Window.Callback callback = this.g;
            if (callback instanceof Activity) {
                this.j = new I((Activity) callback, this.C);
            } else if (callback instanceof Dialog) {
                this.j = new I((Dialog) callback);
            }
            AbstractC0020a aVar = this.j;
            if (aVar != null) {
                aVar.b(this.R);
            }
        }
    }

    public final boolean j() {
        ViewGroup viewGroup;
        return this.v && (viewGroup = this.w) != null && c.e.h.n.p(viewGroup);
    }

    public final void k() {
        if (this.v) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006b, code lost:
        if (((org.xmlpull.v1.XmlPullParser) r15).getDepth() > 1) goto L_0x007c;
     */
    @Override // android.view.LayoutInflater.Factory2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View onCreateView(android.view.View r12, java.lang.String r13, android.content.Context r14, android.util.AttributeSet r15) {
        /*
            r11 = this;
            androidx.appcompat.app.AppCompatViewInflater r0 = r11.U
            r1 = 0
            if (r0 != 0) goto L_0x005b
            android.content.Context r0 = r11.f27e
            int[] r2 = c.a.j.AppCompatTheme
            android.content.res.TypedArray r0 = r0.obtainStyledAttributes(r2)
            int r2 = c.a.j.AppCompatTheme_viewInflaterClass
            java.lang.String r0 = r0.getString(r2)
            if (r0 == 0) goto L_0x0054
            java.lang.Class<androidx.appcompat.app.AppCompatViewInflater> r2 = androidx.appcompat.app.AppCompatViewInflater.class
            java.lang.String r2 = r2.getName()
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0022
            goto L_0x0054
        L_0x0022:
            java.lang.Class r2 = java.lang.Class.forName(r0)     // Catch: Throwable -> 0x0037
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch: Throwable -> 0x0037
            java.lang.reflect.Constructor r2 = r2.getDeclaredConstructor(r3)     // Catch: Throwable -> 0x0037
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: Throwable -> 0x0037
            java.lang.Object r2 = r2.newInstance(r3)     // Catch: Throwable -> 0x0037
            androidx.appcompat.app.AppCompatViewInflater r2 = (androidx.appcompat.app.AppCompatViewInflater) r2     // Catch: Throwable -> 0x0037
            r11.U = r2     // Catch: Throwable -> 0x0037
            goto L_0x005b
        L_0x0037:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to instantiate custom view inflater "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = ". Falling back to default."
            r2.append(r0)
            r2.toString()
            androidx.appcompat.app.AppCompatViewInflater r0 = new androidx.appcompat.app.AppCompatViewInflater
            r0.<init>()
            r11.U = r0
            goto L_0x005b
        L_0x0054:
            androidx.appcompat.app.AppCompatViewInflater r0 = new androidx.appcompat.app.AppCompatViewInflater
            r0.<init>()
            r11.U = r0
        L_0x005b:
            boolean r0 = androidx.appcompat.app.AppCompatDelegateImpl.f24b
            if (r0 == 0) goto L_0x0095
            boolean r0 = r15 instanceof org.xmlpull.v1.XmlPullParser
            r2 = 1
            if (r0 == 0) goto L_0x006e
            r0 = r15
            org.xmlpull.v1.XmlPullParser r0 = (org.xmlpull.v1.XmlPullParser) r0
            int r0 = r0.getDepth()
            if (r0 <= r2) goto L_0x0093
            goto L_0x007c
        L_0x006e:
            r0 = r12
            android.view.ViewParent r0 = (android.view.ViewParent) r0
            if (r0 != 0) goto L_0x0074
            goto L_0x0093
        L_0x0074:
            android.view.Window r3 = r11.f
            android.view.View r3 = r3.getDecorView()
        L_0x007a:
            if (r0 != 0) goto L_0x007e
        L_0x007c:
            r1 = 1
            goto L_0x0093
        L_0x007e:
            if (r0 == r3) goto L_0x0093
            boolean r4 = r0 instanceof android.view.View
            if (r4 == 0) goto L_0x0093
            r4 = r0
            android.view.View r4 = (android.view.View) r4
            boolean r4 = c.e.h.n.o(r4)
            if (r4 == 0) goto L_0x008e
            goto L_0x0093
        L_0x008e:
            android.view.ViewParent r0 = r0.getParent()
            goto L_0x007a
        L_0x0093:
            r7 = r1
            goto L_0x0096
        L_0x0095:
            r7 = 0
        L_0x0096:
            androidx.appcompat.app.AppCompatViewInflater r2 = r11.U
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.f24b
            r9 = 1
            c.a.f.wa.a()
            r10 = 0
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            android.view.View r12 = r2.a(r3, r4, r5, r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.onCreateView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    /* loaded from: classes.dex */
    public final class a implements v.a {
        public a() {
            AppCompatDelegateImpl.this = r1;
        }

        @Override // c.a.e.a.v.a
        public boolean a(l lVar) {
            Window.Callback h = AppCompatDelegateImpl.this.h();
            if (h == null) {
                return true;
            }
            h.onMenuOpened(108, lVar);
            return true;
        }

        @Override // c.a.e.a.v.a
        public void a(l lVar, boolean z) {
            AppCompatDelegateImpl.this.b(lVar);
        }
    }

    public final void d(int i) {
        this.P = (1 << i) | this.P;
        if (!this.O) {
            c.e.h.n.a(this.f.getDecorView(), this.Q);
            this.O = true;
        }
    }

    public void c(int i) {
        PanelFeatureState a2 = a(i, true);
        if (a2.j != null) {
            Bundle bundle = new Bundle();
            a2.j.b(bundle);
            if (bundle.size() > 0) {
                a2.s = bundle;
            }
            a2.j.i();
            l lVar = a2.j;
            c.a.e.a.p pVar = lVar.y;
            if (pVar != null) {
                lVar.a(pVar);
            }
            lVar.g.clear();
            lVar.b(true);
        }
        a2.r = true;
        a2.q = true;
        if ((i == 108 || i == 0) && this.m != null) {
            PanelFeatureState a3 = a(0, false);
            a3.m = false;
            b(a3, null);
        }
    }

    public final void e() {
        if (this.N == null) {
            Context context = this.f27e;
            if (E.f324a == null) {
                Context applicationContext = context.getApplicationContext();
                E.f324a = new E(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.N = new d(E.f324a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends i {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Window.Callback callback) {
            super(callback);
            AppCompatDelegateImpl.this = r1;
        }

        public final ActionMode a(ActionMode.Callback callback) {
            e.a aVar = new e.a(AppCompatDelegateImpl.this.f27e, callback);
            c.a.e.a a2 = AppCompatDelegateImpl.this.a(aVar);
            if (a2 != null) {
                return aVar.b(a2);
            }
            return null;
        }

        @Override // android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || this.f515a.dispatchKeyEvent(keyEvent);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0049, code lost:
            if (r6 != false) goto L_0x001d;
         */
        /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
        @Override // android.view.Window.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean dispatchKeyShortcutEvent(android.view.KeyEvent r6) {
            /*
                r5 = this;
                android.view.Window$Callback r0 = r5.f515a
                boolean r0 = r0.dispatchKeyShortcutEvent(r6)
                r1 = 0
                r2 = 1
                if (r0 != 0) goto L_0x004f
                androidx.appcompat.app.AppCompatDelegateImpl r0 = androidx.appcompat.app.AppCompatDelegateImpl.this
                int r3 = r6.getKeyCode()
                r0.i()
                c.a.a.a r4 = r0.j
                if (r4 == 0) goto L_0x001f
                boolean r3 = r4.a(r3, r6)
                if (r3 == 0) goto L_0x001f
            L_0x001d:
                r6 = 1
                goto L_0x004d
            L_0x001f:
                androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r3 = r0.I
                if (r3 == 0) goto L_0x0034
                int r4 = r6.getKeyCode()
                boolean r3 = r0.a(r3, r4, r6, r2)
                if (r3 == 0) goto L_0x0034
                androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r6 = r0.I
                if (r6 == 0) goto L_0x001d
                r6.n = r2
                goto L_0x001d
            L_0x0034:
                androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r3 = r0.I
                if (r3 != 0) goto L_0x004c
                androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r3 = r0.a(r1, r2)
                r0.b(r3, r6)
                int r4 = r6.getKeyCode()
                boolean r6 = r0.a(r3, r4, r6, r2)
                r3.m = r1
                if (r6 == 0) goto L_0x004c
                goto L_0x001d
            L_0x004c:
                r6 = 0
            L_0x004d:
                if (r6 == 0) goto L_0x0050
            L_0x004f:
                r1 = 1
            L_0x0050:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.c.dispatchKeyShortcutEvent(android.view.KeyEvent):boolean");
        }

        @Override // android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof l)) {
                return this.f515a.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            this.f515a.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.e(i);
            return true;
        }

        @Override // android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            this.f515a.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.f(i);
        }

        @Override // android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            l lVar = menu instanceof l ? (l) menu : null;
            if (i == 0 && lVar == null) {
                return false;
            }
            if (lVar != null) {
                lVar.A = true;
            }
            boolean onPreparePanel = this.f515a.onPreparePanel(i, view, menu);
            if (lVar != null) {
                lVar.A = false;
            }
            return onPreparePanel;
        }

        @Override // android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            l lVar = AppCompatDelegateImpl.this.a(0, true).j;
            if (lVar != null) {
                this.f515a.onProvideKeyboardShortcuts(list, lVar, i);
            } else {
                this.f515a.onProvideKeyboardShortcuts(list, menu, i);
            }
        }

        @Override // android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            if (AppCompatDelegateImpl.this.u) {
                return a(callback);
            }
            return this.f515a.onWindowStartingActionMode(callback);
        }

        @Override // android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (!AppCompatDelegateImpl.this.u || i != 0) {
                return this.f515a.onWindowStartingActionMode(callback, i);
            }
            return a(callback);
        }
    }

    /* loaded from: classes.dex */
    public final class f implements v.a {
        public f() {
            AppCompatDelegateImpl.this = r1;
        }

        @Override // c.a.e.a.v.a
        public void a(l lVar, boolean z) {
            l c2 = lVar.c();
            boolean z2 = c2 != lVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                lVar = c2;
            }
            PanelFeatureState a2 = appCompatDelegateImpl.a((Menu) lVar);
            if (a2 == null) {
                return;
            }
            if (z2) {
                AppCompatDelegateImpl.this.a(a2.f28a, a2, c2);
                AppCompatDelegateImpl.this.a(a2, true);
                return;
            }
            AppCompatDelegateImpl.this.a(a2, z);
        }

        @Override // c.a.e.a.v.a
        public boolean a(l lVar) {
            Window.Callback h;
            if (lVar != null) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.B || (h = appCompatDelegateImpl.h()) == null || AppCompatDelegateImpl.this.K) {
                return true;
            }
            h.onMenuOpened(108, lVar);
            return true;
        }
    }

    public int g(int i) {
        boolean z;
        boolean z2;
        ActionBarContextView actionBarContextView = this.q;
        int i2 = 0;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.q.getLayoutParams();
            z = true;
            if (this.q.isShown()) {
                if (this.S == null) {
                    this.S = new Rect();
                    this.T = new Rect();
                }
                Rect rect = this.S;
                Rect rect2 = this.T;
                rect.set(0, i, 0, 0);
                ViewGroup viewGroup = this.w;
                Method method = xa.f662a;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect, rect2);
                    } catch (Exception unused) {
                    }
                }
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    View view = this.y;
                    if (view == null) {
                        this.y = new View(this.f27e);
                        this.y.setBackgroundColor(this.f27e.getResources().getColor(c.a.c.abc_input_method_navigation_guard));
                        this.w.addView(this.y, -1, new ViewGroup.LayoutParams(-1, i));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            this.y.setLayoutParams(layoutParams);
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (this.y == null) {
                    z = false;
                }
                if (!this.D && z) {
                    i = 0;
                }
            } else {
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = false;
            }
            if (z2) {
                this.q.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.y;
        if (view2 != null) {
            if (!z) {
                i2 = 8;
            }
            view2.setVisibility(i2);
        }
        return i;
    }

    @Override // c.a.a.o
    public void b() {
        LayoutInflater from = LayoutInflater.from(this.f27e);
        if (from.getFactory() == null) {
            from.setFactory2(this);
            int i = Build.VERSION.SDK_INT;
            return;
        }
        boolean z = from.getFactory2() instanceof AppCompatDelegateImpl;
    }

    public final void f() {
        ViewGroup viewGroup;
        CharSequence charSequence;
        Context context;
        if (!this.v) {
            TypedArray obtainStyledAttributes = this.f27e.obtainStyledAttributes(j.AppCompatTheme);
            if (obtainStyledAttributes.hasValue(j.AppCompatTheme_windowActionBar)) {
                if (obtainStyledAttributes.getBoolean(j.AppCompatTheme_windowNoTitle, false)) {
                    a(1);
                } else if (obtainStyledAttributes.getBoolean(j.AppCompatTheme_windowActionBar, false)) {
                    a(108);
                }
                if (obtainStyledAttributes.getBoolean(j.AppCompatTheme_windowActionBarOverlay, false)) {
                    a(109);
                }
                if (obtainStyledAttributes.getBoolean(j.AppCompatTheme_windowActionModeOverlay, false)) {
                    a(10);
                }
                this.E = obtainStyledAttributes.getBoolean(j.AppCompatTheme_android_windowIsFloating, false);
                obtainStyledAttributes.recycle();
                this.f.getDecorView();
                LayoutInflater from = LayoutInflater.from(this.f27e);
                if (this.F) {
                    if (this.D) {
                        viewGroup = (ViewGroup) from.inflate(g.abc_screen_simple_overlay_action_mode, (ViewGroup) null);
                    } else {
                        viewGroup = (ViewGroup) from.inflate(g.abc_screen_simple, (ViewGroup) null);
                    }
                    int i = Build.VERSION.SDK_INT;
                    c.e.h.n.a(viewGroup, new c.a.a.r(this));
                } else if (this.E) {
                    viewGroup = (ViewGroup) from.inflate(g.abc_dialog_title_material, (ViewGroup) null);
                    this.C = false;
                    this.B = false;
                } else if (this.B) {
                    TypedValue typedValue = new TypedValue();
                    this.f27e.getTheme().resolveAttribute(c.a.a.actionBarTheme, typedValue, true);
                    int i2 = typedValue.resourceId;
                    if (i2 != 0) {
                        context = new c.a.e.c(this.f27e, i2);
                    } else {
                        context = this.f27e;
                    }
                    viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(g.abc_screen_toolbar, (ViewGroup) null);
                    this.m = (C) viewGroup.findViewById(c.a.f.decor_content_parent);
                    this.m.setWindowCallback(h());
                    if (this.C) {
                        this.m.a(109);
                    }
                    if (this.z) {
                        this.m.a(2);
                    }
                    if (this.A) {
                        this.m.a(5);
                    }
                } else {
                    viewGroup = null;
                }
                if (viewGroup != null) {
                    if (this.m == null) {
                        this.x = (TextView) viewGroup.findViewById(c.a.f.title);
                    }
                    xa.b(viewGroup);
                    ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(c.a.f.action_bar_activity_content);
                    ViewGroup viewGroup2 = (ViewGroup) this.f.findViewById(16908290);
                    if (viewGroup2 != null) {
                        while (viewGroup2.getChildCount() > 0) {
                            View childAt = viewGroup2.getChildAt(0);
                            viewGroup2.removeViewAt(0);
                            contentFrameLayout.addView(childAt);
                        }
                        viewGroup2.setId(-1);
                        contentFrameLayout.setId(16908290);
                        if (viewGroup2 instanceof FrameLayout) {
                            ((FrameLayout) viewGroup2).setForeground(null);
                        }
                    }
                    this.f.setContentView(viewGroup);
                    contentFrameLayout.setAttachListener(new t(this));
                    this.w = viewGroup;
                    Window.Callback callback = this.g;
                    if (callback instanceof Activity) {
                        charSequence = ((Activity) callback).getTitle();
                    } else {
                        charSequence = this.l;
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        C c2 = this.m;
                        if (c2 != null) {
                            c2.setWindowTitle(charSequence);
                        } else {
                            AbstractC0020a aVar = this.j;
                            if (aVar != null) {
                                aVar.a(charSequence);
                            } else {
                                TextView textView = this.x;
                                if (textView != null) {
                                    textView.setText(charSequence);
                                }
                            }
                        }
                    }
                    ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.w.findViewById(16908290);
                    View decorView = this.f.getDecorView();
                    contentFrameLayout2.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
                    TypedArray obtainStyledAttributes2 = this.f27e.obtainStyledAttributes(j.AppCompatTheme);
                    obtainStyledAttributes2.getValue(j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout2.getMinWidthMajor());
                    obtainStyledAttributes2.getValue(j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout2.getMinWidthMinor());
                    if (obtainStyledAttributes2.hasValue(j.AppCompatTheme_windowFixedWidthMajor)) {
                        obtainStyledAttributes2.getValue(j.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout2.getFixedWidthMajor());
                    }
                    if (obtainStyledAttributes2.hasValue(j.AppCompatTheme_windowFixedWidthMinor)) {
                        obtainStyledAttributes2.getValue(j.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout2.getFixedWidthMinor());
                    }
                    if (obtainStyledAttributes2.hasValue(j.AppCompatTheme_windowFixedHeightMajor)) {
                        obtainStyledAttributes2.getValue(j.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout2.getFixedHeightMajor());
                    }
                    if (obtainStyledAttributes2.hasValue(j.AppCompatTheme_windowFixedHeightMinor)) {
                        obtainStyledAttributes2.getValue(j.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout2.getFixedHeightMinor());
                    }
                    obtainStyledAttributes2.recycle();
                    contentFrameLayout2.requestLayout();
                    ViewGroup viewGroup3 = this.w;
                    this.v = true;
                    PanelFeatureState a2 = a(0, false);
                    if (!this.K && a2.j == null) {
                        d(108);
                        return;
                    }
                    return;
                }
                StringBuilder a3 = e.a.a.a.a.a("AppCompat does not support the current theme features: { windowActionBar: ");
                a3.append(this.B);
                a3.append(", windowActionBarOverlay: ");
                a3.append(this.C);
                a3.append(", android:windowIsFloating: ");
                a3.append(this.E);
                a3.append(", windowActionModeOverlay: ");
                a3.append(this.D);
                a3.append(", windowNoTitle: ");
                a3.append(this.F);
                a3.append(" }");
                throw new IllegalArgumentException(a3.toString());
            }
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
    }

    @Override // c.a.a.o
    public void a(View view) {
        f();
        ViewGroup viewGroup = (ViewGroup) this.w.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.g.onContentChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00bc A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState r11, android.view.KeyEvent r12) {
        /*
            Method dump skipped, instructions count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.b(androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):boolean");
    }

    @Override // c.a.a.o
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        f();
        ViewGroup viewGroup = (ViewGroup) this.w.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.g.onContentChanged();
    }

    /* loaded from: classes.dex */
    public static final class PanelFeatureState {

        /* renamed from: a */
        public int f28a;

        /* renamed from: b */
        public int f29b;

        /* renamed from: c */
        public int f30c;

        /* renamed from: d */
        public int f31d;

        /* renamed from: e */
        public int f32e;
        public int f;
        public ViewGroup g;
        public View h;
        public View i;
        public l j;
        public c.a.e.a.j k;
        public Context l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public boolean q = false;
        public boolean r;
        public Bundle s;

        /* loaded from: classes.dex */
        public static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new z();

            /* renamed from: a */
            public int f33a;

            /* renamed from: b */
            public boolean f34b;

            /* renamed from: c */
            public Bundle f35c;

            public static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f33a = parcel.readInt();
                boolean z = true;
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.f34b = z;
                if (savedState.f34b) {
                    savedState.f35c = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f33a);
                parcel.writeInt(this.f34b ? 1 : 0);
                if (this.f34b) {
                    parcel.writeBundle(this.f35c);
                }
            }
        }

        public PanelFeatureState(int i) {
            this.f28a = i;
        }

        public void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(c.a.a.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                newTheme.applyStyle(i, true);
            }
            newTheme.resolveAttribute(c.a.a.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                newTheme.applyStyle(i2, true);
            } else {
                newTheme.applyStyle(c.a.i.Theme_AppCompat_CompactMenu, true);
            }
            c.a.e.c cVar = new c.a.e.c(context, 0);
            cVar.getTheme().setTo(newTheme);
            this.l = cVar;
            TypedArray obtainStyledAttributes = cVar.obtainStyledAttributes(j.AppCompatTheme);
            this.f29b = obtainStyledAttributes.getResourceId(j.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(j.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        public void a(l lVar) {
            c.a.e.a.j jVar;
            l lVar2 = this.j;
            if (lVar != lVar2) {
                if (lVar2 != null) {
                    lVar2.a(this.k);
                }
                this.j = lVar;
                if (lVar != null && (jVar = this.k) != null) {
                    lVar.a(jVar, lVar.f451b);
                }
            }
        }
    }

    @Override // c.a.a.o
    public boolean a(int i) {
        if (i == 8) {
            i = 108;
        } else if (i == 9) {
            i = 109;
        }
        if (this.F && i == 108) {
            return false;
        }
        if (this.B && i == 1) {
            this.B = false;
        }
        if (i == 1) {
            k();
            this.F = true;
            return true;
        } else if (i == 2) {
            k();
            this.z = true;
            return true;
        } else if (i == 5) {
            k();
            this.A = true;
            return true;
        } else if (i == 10) {
            k();
            this.D = true;
            return true;
        } else if (i == 108) {
            k();
            this.B = true;
            return true;
        } else if (i != 109) {
            return this.f.requestFeature(i);
        } else {
            k();
            this.C = true;
            return true;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        boolean z = false;
        if (this.U == null) {
            String string = this.f27e.obtainStyledAttributes(j.AppCompatTheme).getString(j.AppCompatTheme_viewInflaterClass);
            if (string == null || AppCompatViewInflater.class.getName().equals(string)) {
                this.U = new AppCompatViewInflater();
            } else {
                try {
                    this.U = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable unused) {
                    String str2 = "Failed to instantiate custom view inflater " + string + ". Falling back to default.";
                    this.U = new AppCompatViewInflater();
                }
            }
        }
        if (!f24b) {
            z = false;
        } else if ((attributeSet instanceof XmlPullParser) && ((XmlPullParser) attributeSet).getDepth() > 1) {
            z = true;
        }
        AppCompatViewInflater appCompatViewInflater = this.U;
        boolean z2 = f24b;
        wa.a();
        return appCompatViewInflater.a(null, str, context, attributeSet, z, z2, true, false);
    }

    @Override // c.a.a.o
    public final void a(CharSequence charSequence) {
        this.l = charSequence;
        C c2 = this.m;
        if (c2 != null) {
            c2.setWindowTitle(charSequence);
            return;
        }
        AbstractC0020a aVar = this.j;
        if (aVar != null) {
            aVar.a(charSequence);
            return;
        }
        TextView textView = this.x;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // c.a.e.a.l.a
    public boolean a(l lVar, MenuItem menuItem) {
        PanelFeatureState a2;
        Window.Callback h = h();
        if (h == null || this.K || (a2 = a((Menu) lVar.c())) == null) {
            return false;
        }
        return h.onMenuItemSelected(a2.f28a, menuItem);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c.a.e.a a(c.a.e.a.AbstractC0005a r8) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.a(c.a.e.a$a):c.a.e.a");
    }

    public void b(l lVar) {
        if (!this.G) {
            this.G = true;
            this.m.b();
            Window.Callback h = h();
            if (h != null && !this.K) {
                h.onPanelClosed(108, lVar);
            }
            this.G = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.a(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x0104, code lost:
        if (r14.h != null) goto L_0x0106;
     */
    /* JADX WARN: Removed duplicated region for block: B:73:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState r14, android.view.KeyEvent r15) {
        /*
            Method dump skipped, instructions count: 389
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.a(androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):void");
    }

    @Override // c.a.e.a.l.a
    public void a(l lVar) {
        C c2 = this.m;
        if (c2 == null || !c2.d() || (ViewConfiguration.get(this.f27e).hasPermanentMenuKey() && !this.m.e())) {
            PanelFeatureState a2 = a(0, true);
            a2.q = true;
            a(a2, false);
            a(a2, (KeyEvent) null);
            return;
        }
        Window.Callback h = h();
        if (this.m.a()) {
            this.m.f();
            if (!this.K) {
                h.onPanelClosed(108, a(0, true).j);
            }
        } else if (h != null && !this.K) {
            if (this.O && (this.P & 1) != 0) {
                this.f.getDecorView().removeCallbacks(this.Q);
                this.Q.run();
            }
            PanelFeatureState a3 = a(0, true);
            l lVar2 = a3.j;
            if (lVar2 != null && !a3.r && h.onPreparePanel(0, a3.i, lVar2)) {
                h.onMenuOpened(108, a3.j);
                this.m.g();
            }
        }
    }

    public void a(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        C c2;
        if (!z || panelFeatureState.f28a != 0 || (c2 = this.m) == null || !c2.a()) {
            WindowManager windowManager = (WindowManager) this.f27e.getSystemService("window");
            if (!(windowManager == null || !panelFeatureState.o || (viewGroup = panelFeatureState.g) == null)) {
                windowManager.removeView(viewGroup);
                if (z) {
                    a(panelFeatureState.f28a, panelFeatureState, null);
                }
            }
            panelFeatureState.m = false;
            panelFeatureState.n = false;
            panelFeatureState.o = false;
            panelFeatureState.h = null;
            panelFeatureState.q = true;
            if (this.I == panelFeatureState) {
                this.I = null;
                return;
            }
            return;
        }
        b(panelFeatureState.j);
    }

    public void a(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.H;
                if (i < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.K) {
            this.g.onPanelClosed(i, menu);
        }
    }

    public PanelFeatureState a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.H;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i = 0; i < length; i++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    public PanelFeatureState a(int i, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.H;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.H = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i);
        panelFeatureStateArr[i] = panelFeatureState2;
        return panelFeatureState2;
    }

    public final boolean a(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        l lVar;
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || b(panelFeatureState, keyEvent)) && (lVar = panelFeatureState.j) != null) {
            z = lVar.performShortcut(i, keyEvent, i2);
        }
        if (z && (i2 & 1) == 0 && this.m == null) {
            a(panelFeatureState, true);
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x007c, code lost:
        if ((r2.getPackageManager().getActivityInfo(new android.content.ComponentName(r11.f27e, r11.f27e.getClass()), 0).configChanges & 512) == 0) goto L_0x007e;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0143  */
    @Override // c.a.a.o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a() {
        /*
            Method dump skipped, instructions count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.a():boolean");
    }
}
