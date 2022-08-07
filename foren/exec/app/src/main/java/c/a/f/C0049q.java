package c.a.f;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import c.a.a.C;
import c.c.f;
import c.c.g;
import c.c.j;
import c.m.a.a.i;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: c.a.f.q */
/* loaded from: classes.dex */
public final class C0049q {

    /* renamed from: b */
    public static C0049q f624b;
    public WeakHashMap<Context, j<ColorStateList>> j;
    public c.c.b<String, d> k;
    public j<String> l;
    public final WeakHashMap<Context, f<WeakReference<Drawable.ConstantState>>> m = new WeakHashMap<>(0);
    public TypedValue n;
    public boolean o;

    /* renamed from: a */
    public static final PorterDuff.Mode f623a = PorterDuff.Mode.SRC_IN;

    /* renamed from: c */
    public static final c f625c = new c(6);

    /* renamed from: d */
    public static final int[] f626d = {c.a.e.abc_textfield_search_default_mtrl_alpha, c.a.e.abc_textfield_default_mtrl_alpha, c.a.e.abc_ab_share_pack_mtrl_alpha};

    /* renamed from: e */
    public static final int[] f627e = {c.a.e.abc_ic_commit_search_api_mtrl_alpha, c.a.e.abc_seekbar_tick_mark_material, c.a.e.abc_ic_menu_share_mtrl_alpha, c.a.e.abc_ic_menu_copy_mtrl_am_alpha, c.a.e.abc_ic_menu_cut_mtrl_alpha, c.a.e.abc_ic_menu_selectall_mtrl_alpha, c.a.e.abc_ic_menu_paste_mtrl_am_alpha};
    public static final int[] f = {c.a.e.abc_textfield_activated_mtrl_alpha, c.a.e.abc_textfield_search_activated_mtrl_alpha, c.a.e.abc_cab_background_top_mtrl_alpha, c.a.e.abc_text_cursor_material, c.a.e.abc_text_select_handle_left_mtrl_dark, c.a.e.abc_text_select_handle_middle_mtrl_dark, c.a.e.abc_text_select_handle_right_mtrl_dark, c.a.e.abc_text_select_handle_left_mtrl_light, c.a.e.abc_text_select_handle_middle_mtrl_light, c.a.e.abc_text_select_handle_right_mtrl_light};
    public static final int[] g = {c.a.e.abc_popup_background_mtrl_mult, c.a.e.abc_cab_background_internal_bg, c.a.e.abc_menu_hardkey_panel_mtrl_mult};
    public static final int[] h = {c.a.e.abc_tab_indicator_material, c.a.e.abc_textfield_search_material};
    public static final int[] i = {c.a.e.abc_btn_check_material, c.a.e.abc_btn_radio_material};

    /* renamed from: c.a.f.q$a */
    /* loaded from: classes.dex */
    public static class a implements d {
        @Override // c.a.f.C0049q.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return c.a.c.a.b.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    /* renamed from: c.a.f.q$b */
    /* loaded from: classes.dex */
    public static class b implements d {
        @Override // c.a.f.C0049q.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                Resources resources = context.getResources();
                c.m.a.a.d dVar = new c.m.a.a.d(context, null, null);
                dVar.inflate(resources, xmlPullParser, attributeSet, theme);
                return dVar;
            } catch (Exception unused) {
                return null;
            }
        }
    }

    /* renamed from: c.a.f.q$d */
    /* loaded from: classes.dex */
    public interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* renamed from: c.a.f.q$e */
    /* loaded from: classes.dex */
    public static class e implements d {
        @Override // c.a.f.C0049q.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return i.createFromXmlInner(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public static synchronized C0049q a() {
        C0049q qVar;
        synchronized (C0049q.class) {
            if (f624b == null) {
                f624b = new C0049q();
                C0049q qVar2 = f624b;
                if (Build.VERSION.SDK_INT < 24) {
                    qVar2.a("vector", new e());
                    qVar2.a("animated-vector", new b());
                    qVar2.a("animated-selector", new a());
                }
            }
            qVar = f624b;
        }
        return qVar;
    }

    public synchronized void b(Context context) {
        f<WeakReference<Drawable.ConstantState>> fVar = this.m.get(context);
        if (fVar != null) {
            int i2 = fVar.f702e;
            Object[] objArr = fVar.f701d;
            for (int i3 = 0; i3 < i2; i3++) {
                objArr[i3] = null;
            }
            fVar.f702e = 0;
            fVar.f699b = false;
        }
    }

    public synchronized Drawable c(Context context, int i2) {
        return a(context, i2, false);
    }

    public synchronized ColorStateList d(Context context, int i2) {
        ColorStateList colorStateList;
        j<ColorStateList> jVar;
        WeakHashMap<Context, j<ColorStateList>> weakHashMap = this.j;
        colorStateList = null;
        if (!(weakHashMap == null || (jVar = weakHashMap.get(context)) == null)) {
            colorStateList = jVar.b(i2, null);
        }
        if (colorStateList == null) {
            if (i2 == c.a.e.abc_edit_text_material) {
                colorStateList = c.a.b.a.a.b(context, c.a.c.abc_tint_edittext);
            } else if (i2 == c.a.e.abc_switch_track_mtrl_alpha) {
                colorStateList = c.a.b.a.a.b(context, c.a.c.abc_tint_switch_track);
            } else if (i2 == c.a.e.abc_switch_thumb_material) {
                colorStateList = a(context);
            } else if (i2 == c.a.e.abc_btn_default_mtrl_shape) {
                colorStateList = a(context, fa.b(context, c.a.a.colorButtonNormal));
            } else if (i2 == c.a.e.abc_btn_borderless_material) {
                colorStateList = a(context, 0);
            } else if (i2 == c.a.e.abc_btn_colored_material) {
                colorStateList = a(context, fa.b(context, c.a.a.colorAccent));
            } else {
                if (!(i2 == c.a.e.abc_spinner_mtrl_am_alpha || i2 == c.a.e.abc_spinner_textfield_background_material)) {
                    if (a(f627e, i2)) {
                        colorStateList = fa.c(context, c.a.a.colorControlNormal);
                    } else if (a(h, i2)) {
                        colorStateList = c.a.b.a.a.b(context, c.a.c.abc_tint_default);
                    } else if (a(i, i2)) {
                        colorStateList = c.a.b.a.a.b(context, c.a.c.abc_tint_btn_checkable);
                    } else if (i2 == c.a.e.abc_seekbar_thumb_material) {
                        colorStateList = c.a.b.a.a.b(context, c.a.c.abc_tint_seek_thumb);
                    }
                }
                colorStateList = c.a.b.a.a.b(context, c.a.c.abc_tint_spinner);
            }
            if (colorStateList != null) {
                if (this.j == null) {
                    this.j = new WeakHashMap<>();
                }
                j<ColorStateList> jVar2 = this.j.get(context);
                if (jVar2 == null) {
                    jVar2 = new j<>(10);
                    this.j.put(context, jVar2);
                }
                jVar2.a(i2, colorStateList);
            }
        }
        return colorStateList;
    }

    public final Drawable e(Context context, int i2) {
        int next;
        c.c.b<String, d> bVar = this.k;
        if (bVar == null || bVar.isEmpty()) {
            return null;
        }
        j<String> jVar = this.l;
        if (jVar != null) {
            String b2 = jVar.b(i2, null);
            if ("appcompat_skip_skip".equals(b2) || (b2 != null && this.k.get(b2) == null)) {
                return null;
            }
        } else {
            this.l = new j<>(10);
        }
        if (this.n == null) {
            this.n = new TypedValue();
        }
        TypedValue typedValue = this.n;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long j = (typedValue.assetCookie << 32) | typedValue.data;
        Drawable a2 = a(context, j);
        if (a2 != null) {
            return a2;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                while (true) {
                    next = xml.next();
                    if (next == 2 || next == 1) {
                        break;
                    }
                }
                if (next == 2) {
                    String name = xml.getName();
                    this.l.a(i2, name);
                    d dVar = this.k.get(name);
                    if (dVar != null) {
                        a2 = dVar.a(context, xml, asAttributeSet, context.getTheme());
                    }
                    if (a2 != null) {
                        a2.setChangingConfigurations(typedValue.changingConfigurations);
                        a(context, j, a2);
                    }
                } else {
                    throw new XmlPullParserException("No start tag found");
                }
            } catch (Exception unused) {
            }
        }
        if (a2 == null) {
            this.l.a(i2, "appcompat_skip_skip");
        }
        return a2;
    }

    /* renamed from: c.a.f.q$c */
    /* loaded from: classes.dex */
    public static class c extends g<Integer, PorterDuffColorFilter> {
        public c(int i) {
            super(i);
        }

        public PorterDuffColorFilter a(int i, PorterDuff.Mode mode) {
            return a((c) Integer.valueOf(mode.hashCode() + ((i + 31) * 31)));
        }

        public PorterDuffColorFilter a(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return a((c) Integer.valueOf(mode.hashCode() + ((i + 31) * 31)), (Integer) porterDuffColorFilter);
        }
    }

    public final Drawable b(Context context, int i2) {
        if (this.n == null) {
            this.n = new TypedValue();
        }
        TypedValue typedValue = this.n;
        context.getResources().getValue(i2, typedValue, true);
        long j = (typedValue.assetCookie << 32) | typedValue.data;
        Drawable a2 = a(context, j);
        if (a2 != null) {
            return a2;
        }
        if (i2 == c.a.e.abc_cab_background_top_material) {
            a2 = new LayerDrawable(new Drawable[]{c(context, c.a.e.abc_cab_background_internal_bg), c(context, c.a.e.abc_cab_background_top_mtrl_alpha)});
        }
        if (a2 != null) {
            a2.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, j, a2);
        }
        return a2;
    }

    public final Drawable a(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList d2 = d(context, i2);
        PorterDuff.Mode mode = null;
        if (d2 != null) {
            if (E.a(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable b2 = C.b(drawable);
            int i3 = Build.VERSION.SDK_INT;
            b2.setTintList(d2);
            if (i2 == c.a.e.abc_switch_thumb_material) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (mode == null) {
                return b2;
            }
            int i4 = Build.VERSION.SDK_INT;
            b2.setTintMode(mode);
            return b2;
        } else if (i2 == c.a.e.abc_seekbar_track_material) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            a(layerDrawable.findDrawableByLayerId(16908288), fa.b(context, c.a.a.colorControlNormal), f623a);
            a(layerDrawable.findDrawableByLayerId(16908303), fa.b(context, c.a.a.colorControlNormal), f623a);
            a(layerDrawable.findDrawableByLayerId(16908301), fa.b(context, c.a.a.colorControlActivated), f623a);
            return drawable;
        } else if (i2 == c.a.e.abc_ratingbar_material || i2 == c.a.e.abc_ratingbar_indicator_material || i2 == c.a.e.abc_ratingbar_small_material) {
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            a(layerDrawable2.findDrawableByLayerId(16908288), fa.a(context, c.a.a.colorControlNormal), f623a);
            a(layerDrawable2.findDrawableByLayerId(16908303), fa.b(context, c.a.a.colorControlActivated), f623a);
            a(layerDrawable2.findDrawableByLayerId(16908301), fa.b(context, c.a.a.colorControlActivated), f623a);
            return drawable;
        } else if (a(context, i2, drawable) || !z) {
            return drawable;
        } else {
            return null;
        }
    }

    public final synchronized Drawable a(Context context, long j) {
        Object[] objArr;
        Object obj;
        f<WeakReference<Drawable.ConstantState>> fVar = this.m.get(context);
        if (fVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> b2 = fVar.b(j, null);
        if (b2 != null) {
            Drawable.ConstantState constantState = b2.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            int a2 = c.c.e.a(fVar.f700c, fVar.f702e, j);
            if (a2 >= 0 && (objArr = fVar.f701d)[a2] != (obj = f.f698a)) {
                objArr[a2] = obj;
                fVar.f699b = true;
            }
        }
        return null;
    }

    public final synchronized boolean a(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        f<WeakReference<Drawable.ConstantState>> fVar = this.m.get(context);
        if (fVar == null) {
            fVar = new f<>();
            this.m.put(context, fVar);
        }
        fVar.c(j, new WeakReference<>(constantState));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0061 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Context r6, int r7, android.graphics.drawable.Drawable r8) {
        /*
            android.graphics.PorterDuff$Mode r0 = c.a.f.C0049q.f623a
            int[] r1 = c.a.f.C0049q.f626d
            boolean r1 = a(r1, r7)
            r2 = 16842801(0x1010031, float:2.3693695E-38)
            r3 = -1
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L_0x0015
            int r2 = c.a.a.colorControlNormal
        L_0x0012:
            r7 = 1
            r1 = -1
            goto L_0x0044
        L_0x0015:
            int[] r1 = c.a.f.C0049q.f
            boolean r1 = a(r1, r7)
            if (r1 == 0) goto L_0x0020
            int r2 = c.a.a.colorControlActivated
            goto L_0x0012
        L_0x0020:
            int[] r1 = c.a.f.C0049q.g
            boolean r1 = a(r1, r7)
            if (r1 == 0) goto L_0x002b
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L_0x0012
        L_0x002b:
            int r1 = c.a.e.abc_list_divider_mtrl_alpha
            if (r7 != r1) goto L_0x003c
            r2 = 16842800(0x1010030, float:2.3693693E-38)
            r7 = 1109603123(0x42233333, float:40.8)
            int r7 = java.lang.Math.round(r7)
            r1 = r7
            r7 = 1
            goto L_0x0044
        L_0x003c:
            int r1 = c.a.e.abc_dialog_material_background
            if (r7 != r1) goto L_0x0041
            goto L_0x0012
        L_0x0041:
            r7 = 0
            r1 = -1
            r2 = 0
        L_0x0044:
            if (r7 == 0) goto L_0x0061
            boolean r7 = c.a.f.E.a(r8)
            if (r7 == 0) goto L_0x0050
            android.graphics.drawable.Drawable r8 = r8.mutate()
        L_0x0050:
            int r6 = c.a.f.fa.b(r6, r2)
            android.graphics.PorterDuffColorFilter r6 = a(r6, r0)
            r8.setColorFilter(r6)
            if (r1 == r3) goto L_0x0060
            r8.setAlpha(r1)
        L_0x0060:
            return r5
        L_0x0061:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.f.C0049q.a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    public final void a(String str, d dVar) {
        if (this.k == null) {
            this.k = new c.c.b<>();
        }
        this.k.put(str, dVar);
    }

    public static boolean a(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public final ColorStateList a(Context context, int i2) {
        int b2 = fa.b(context, c.a.a.colorControlHighlight);
        return new ColorStateList(new int[][]{fa.f593b, fa.f595d, fa.f594c, fa.f}, new int[]{fa.a(context, c.a.a.colorButtonNormal), c.e.c.a.a(b2, i2), c.e.c.a.a(b2, i2), i2});
    }

    public final ColorStateList a(Context context) {
        int[][] iArr = new int[3];
        int[] iArr2 = new int[3];
        ColorStateList c2 = fa.c(context, c.a.a.colorSwitchThumbNormal);
        if (c2 == null || !c2.isStateful()) {
            iArr[0] = fa.f593b;
            iArr2[0] = fa.a(context, c.a.a.colorSwitchThumbNormal);
            iArr[1] = fa.f596e;
            iArr2[1] = fa.b(context, c.a.a.colorControlActivated);
            iArr[2] = fa.f;
            iArr2[2] = fa.b(context, c.a.a.colorSwitchThumbNormal);
        } else {
            iArr[0] = fa.f593b;
            iArr2[0] = c2.getColorForState(iArr[0], 0);
            iArr[1] = fa.f596e;
            iArr2[1] = fa.b(context, c.a.a.colorControlActivated);
            iArr[2] = fa.f;
            iArr2[2] = c2.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    public static void a(Drawable drawable, ia iaVar, int[] iArr) {
        if (!E.a(drawable) || drawable.mutate() == drawable) {
            if (iaVar.f601d || iaVar.f600c) {
                PorterDuffColorFilter porterDuffColorFilter = null;
                ColorStateList colorStateList = iaVar.f601d ? iaVar.f598a : null;
                PorterDuff.Mode mode = iaVar.f600c ? iaVar.f599b : f623a;
                if (!(colorStateList == null || mode == null)) {
                    porterDuffColorFilter = a(colorStateList.getColorForState(iArr, 0), mode);
                }
                drawable.setColorFilter(porterDuffColorFilter);
            } else {
                drawable.clearColorFilter();
            }
            if (Build.VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }

    public static synchronized PorterDuffColorFilter a(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter a2;
        synchronized (C0049q.class) {
            a2 = f625c.a(i2, mode);
            if (a2 == null) {
                a2 = new PorterDuffColorFilter(i2, mode);
                f625c.a(i2, mode, a2);
            }
        }
        return a2;
    }

    public static void a(Drawable drawable, int i2, PorterDuff.Mode mode) {
        if (E.a(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = f623a;
        }
        drawable.setColorFilter(a(i2, mode));
    }

    public synchronized Drawable a(Context context, int i2, boolean z) {
        Drawable e2;
        if (!this.o) {
            boolean z2 = true;
            this.o = true;
            Drawable c2 = c(context, c.a.e.abc_vector_test);
            if (c2 != null) {
                if (!(c2 instanceof i) && !"android.graphics.drawable.VectorDrawable".equals(c2.getClass().getName())) {
                    z2 = false;
                }
            }
            this.o = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
        e2 = e(context, i2);
        if (e2 == null) {
            e2 = b(context, i2);
        }
        if (e2 == null) {
            e2 = c.e.b.a.b(context, i2);
        }
        if (e2 != null) {
            e2 = a(context, i2, z, e2);
        }
        if (e2 != null) {
            E.b(e2);
        }
        return e2;
    }
}
