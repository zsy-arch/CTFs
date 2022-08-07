package c.e.h;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class n {

    /* renamed from: a */
    public static WeakHashMap<View, r> f862a = null;

    /* renamed from: b */
    public static ThreadLocal<Rect> f863b;

    /* loaded from: classes.dex */
    public interface a {
        boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent);
    }

    static {
        new AtomicInteger(1);
    }

    public static void a(View view, a aVar) {
        view.setAccessibilityDelegate(aVar == null ? null : aVar.f842b);
    }

    public static ColorStateList b(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getBackgroundTintList();
    }

    public static PorterDuff.Mode c(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getBackgroundTintMode();
    }

    public static float d(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getElevation();
    }

    public static void e(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        view.setImportantForAccessibility(i);
    }

    public static int f(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getImportantForAccessibility();
    }

    public static int g(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getLayoutDirection();
    }

    public static int h(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getMinimumHeight();
    }

    public static ViewParent i(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getParentForAccessibility();
    }

    public static String j(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getTransitionName();
    }

    public static int k(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getWindowSystemUiVisibility();
    }

    public static float l(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getZ();
    }

    public static boolean m(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasOnClickListeners();
    }

    public static boolean n(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasOverlappingRendering();
    }

    public static boolean o(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isAttachedToWindow();
    }

    public static boolean p(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isLaidOut();
    }

    public static void q(View view) {
        int i = Build.VERSION.SDK_INT;
        view.postInvalidateOnAnimation();
    }

    public static void r(View view) {
        int i = Build.VERSION.SDK_INT;
        view.requestApplyInsets();
    }

    public static void s(View view) {
        int i = Build.VERSION.SDK_INT;
        view.stopNestedScroll();
    }

    public static void t(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a */
        public static final ArrayList<WeakReference<View>> f864a = new ArrayList<>();

        /* renamed from: b */
        public WeakHashMap<View, Boolean> f865b = null;

        /* renamed from: c */
        public SparseArray<WeakReference<View>> f866c = null;

        /* renamed from: d */
        public WeakReference<KeyEvent> f867d = null;

        public final SparseArray<WeakReference<View>> a() {
            if (this.f866c == null) {
                this.f866c = new SparseArray<>();
            }
            return this.f866c;
        }

        public final View b(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.f865b;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View b2 = b(viewGroup.getChildAt(childCount), keyEvent);
                        if (b2 != null) {
                            return b2;
                        }
                    }
                }
                if (c(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        public final boolean c(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(c.e.b.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((a) arrayList.get(size)).onUnhandledKeyEvent(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        public static b a(View view) {
            b bVar = (b) view.getTag(c.e.b.tag_unhandled_key_event_manager);
            if (bVar != null) {
                return bVar;
            }
            b bVar2 = new b();
            view.setTag(c.e.b.tag_unhandled_key_event_manager, bVar2);
            return bVar2;
        }

        public boolean a(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                b();
            }
            View b2 = b(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (b2 != null && !KeyEvent.isModifierKey(keyCode)) {
                    if (this.f866c == null) {
                        this.f866c = new SparseArray<>();
                    }
                    this.f866c.put(keyCode, new WeakReference<>(b2));
                }
            }
            return b2 != null;
        }

        public final void b() {
            WeakHashMap<View, Boolean> weakHashMap = this.f865b;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (!f864a.isEmpty()) {
                synchronized (f864a) {
                    if (this.f865b == null) {
                        this.f865b = new WeakHashMap<>();
                    }
                    for (int size = f864a.size() - 1; size >= 0; size--) {
                        View view = f864a.get(size).get();
                        if (view == null) {
                            f864a.remove(size);
                        } else {
                            this.f865b.put(view, Boolean.TRUE);
                            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                                this.f865b.put((View) parent, Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }
    }

    public static Rect a() {
        if (f863b == null) {
            f863b = new ThreadLocal<>();
        }
        Rect rect = f863b.get();
        if (rect == null) {
            rect = new Rect();
            f863b.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static void b(View view, int i) {
        view.offsetTopAndBottom(i);
        if (view.getVisibility() == 0) {
            float translationY = view.getTranslationY();
            view.setTranslationY(1.0f + translationY);
            view.setTranslationY(translationY);
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                t((View) parent);
            }
        }
    }

    public static void c(View view, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetLeftAndRight(i);
            return;
        }
        Rect a2 = a();
        boolean z = false;
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            a2.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !a2.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        a(view, i);
        if (z && a2.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(a2);
        }
    }

    public static void d(View view, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetTopAndBottom(i);
            return;
        }
        Rect a2 = a();
        boolean z = false;
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            a2.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !a2.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        b(view, i);
        if (z && a2.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(a2);
        }
    }

    public static boolean e(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getFitsSystemWindows();
    }

    public static void a(View view, Runnable runnable) {
        int i = Build.VERSION.SDK_INT;
        view.postOnAnimation(runnable);
    }

    public static void a(View view, Runnable runnable, long j) {
        int i = Build.VERSION.SDK_INT;
        view.postOnAnimationDelayed(runnable, j);
    }

    public static boolean b(View view, KeyEvent keyEvent) {
        int indexOfKey;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        b a2 = b.a(view);
        WeakReference<KeyEvent> weakReference = a2.f867d;
        if (weakReference != null && weakReference.get() == keyEvent) {
            return false;
        }
        a2.f867d = new WeakReference<>(keyEvent);
        WeakReference<View> weakReference2 = null;
        SparseArray<WeakReference<View>> a3 = a2.a();
        if (keyEvent.getAction() == 1 && (indexOfKey = a3.indexOfKey(keyEvent.getKeyCode())) >= 0) {
            weakReference2 = a3.valueAt(indexOfKey);
            a3.removeAt(indexOfKey);
        }
        if (weakReference2 == null) {
            weakReference2 = a3.get(keyEvent.getKeyCode());
        }
        if (weakReference2 == null) {
            return false;
        }
        View view2 = weakReference2.get();
        if (view2 != null && o(view2)) {
            a2.c(view2, keyEvent);
        }
        return true;
    }

    public static void a(View view, Paint paint) {
        int i = Build.VERSION.SDK_INT;
        view.setLayerPaint(paint);
    }

    public static r a(View view) {
        if (f862a == null) {
            f862a = new WeakHashMap<>();
        }
        r rVar = f862a.get(view);
        if (rVar != null) {
            return rVar;
        }
        r rVar2 = new r(view);
        f862a.put(view, rVar2);
        return rVar2;
    }

    public static void a(View view, float f) {
        int i = Build.VERSION.SDK_INT;
        view.setElevation(f);
    }

    public static void a(View view, String str) {
        int i = Build.VERSION.SDK_INT;
        view.setTransitionName(str);
    }

    public static void a(View view, j jVar) {
        int i = Build.VERSION.SDK_INT;
        if (jVar == null) {
            view.setOnApplyWindowInsetsListener(null);
        } else {
            view.setOnApplyWindowInsetsListener(new m(jVar));
        }
    }

    public static v a(View view, v vVar) {
        int i = Build.VERSION.SDK_INT;
        WindowInsets windowInsets = (WindowInsets) (vVar == null ? null : vVar.f876a);
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        if (onApplyWindowInsets != windowInsets) {
            windowInsets = new WindowInsets(onApplyWindowInsets);
        }
        if (windowInsets == null) {
            return null;
        }
        return new v(windowInsets);
    }

    public static void a(View view, Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        view.setBackground(drawable);
    }

    public static void a(View view, ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        view.setBackgroundTintList(colorStateList);
        if (Build.VERSION.SDK_INT == 21) {
            Drawable background = view.getBackground();
            boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background != null && z) {
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
            }
        }
    }

    public static void a(View view, PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        view.setBackgroundTintMode(mode);
        if (Build.VERSION.SDK_INT == 21) {
            Drawable background = view.getBackground();
            boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background != null && z) {
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
            }
        }
    }

    public static void a(View view, int i) {
        view.offsetLeftAndRight(i);
        if (view.getVisibility() == 0) {
            float translationY = view.getTranslationY();
            view.setTranslationY(1.0f + translationY);
            view.setTranslationY(translationY);
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                t((View) parent);
            }
        }
    }

    public static void a(View view, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i, i2);
        }
    }

    public static boolean a(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return b.a(view).a(view, keyEvent);
    }
}
