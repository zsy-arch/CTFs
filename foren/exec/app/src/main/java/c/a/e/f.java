package c.a.e;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import c.a.e.a.p;
import c.a.e.a.q;
import c.a.f.E;
import c.a.j;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class f extends MenuInflater {

    /* renamed from: a */
    public static final Class<?>[] f494a = {Context.class};

    /* renamed from: b */
    public static final Class<?>[] f495b = f494a;

    /* renamed from: c */
    public final Object[] f496c;

    /* renamed from: d */
    public final Object[] f497d;

    /* renamed from: e */
    public Context f498e;
    public Object f;

    /* loaded from: classes.dex */
    public static class a implements MenuItem.OnMenuItemClickListener {

        /* renamed from: a */
        public static final Class<?>[] f499a = {MenuItem.class};

        /* renamed from: b */
        public Object f500b;

        /* renamed from: c */
        public Method f501c;

        public a(Object obj, String str) {
            this.f500b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f501c = cls.getMethod(str, f499a);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f501c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f501c.invoke(this.f500b, menuItem)).booleanValue();
                }
                this.f501c.invoke(this.f500b, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public f(Context context) {
        super(context);
        this.f498e = context;
        this.f496c = new Object[]{context};
        this.f497d = this.f496c;
    }

    public final void a(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) {
        b bVar = new b(menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("menu")) {
                    eventType = xmlPullParser.next();
                } else {
                    throw new RuntimeException(e.a.a.a.a.a("Expecting menu, got ", name));
                }
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z = false;
        boolean z2 = false;
        String str = null;
        while (!z) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            } else if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z2 && name2.equals(str)) {
                        str = null;
                        z2 = false;
                    } else if (name2.equals("group")) {
                        bVar.f503b = 0;
                        bVar.f504c = 0;
                        bVar.f505d = 0;
                        bVar.f506e = 0;
                        bVar.f = true;
                        bVar.g = true;
                    } else if (name2.equals("item")) {
                        if (!bVar.h) {
                            c.e.h.b bVar2 = bVar.A;
                            if (bVar2 == null || !((q.a) bVar2).f466c.hasSubMenu()) {
                                bVar.h = true;
                                bVar.a(bVar.f502a.add(bVar.f503b, bVar.i, bVar.j, bVar.k));
                            } else {
                                bVar.a();
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z = true;
                    }
                    eventType = xmlPullParser.next();
                }
                eventType = xmlPullParser.next();
            } else {
                if (!z2) {
                    String name3 = xmlPullParser.getName();
                    if (name3.equals("group")) {
                        TypedArray obtainStyledAttributes = f.this.f498e.obtainStyledAttributes(attributeSet, j.MenuGroup);
                        bVar.f503b = obtainStyledAttributes.getResourceId(j.MenuGroup_android_id, 0);
                        bVar.f504c = obtainStyledAttributes.getInt(j.MenuGroup_android_menuCategory, 0);
                        bVar.f505d = obtainStyledAttributes.getInt(j.MenuGroup_android_orderInCategory, 0);
                        bVar.f506e = obtainStyledAttributes.getInt(j.MenuGroup_android_checkableBehavior, 0);
                        bVar.f = obtainStyledAttributes.getBoolean(j.MenuGroup_android_visible, true);
                        bVar.g = obtainStyledAttributes.getBoolean(j.MenuGroup_android_enabled, true);
                        obtainStyledAttributes.recycle();
                    } else if (name3.equals("item")) {
                        TypedArray obtainStyledAttributes2 = f.this.f498e.obtainStyledAttributes(attributeSet, j.MenuItem);
                        bVar.i = obtainStyledAttributes2.getResourceId(j.MenuItem_android_id, 0);
                        bVar.j = (obtainStyledAttributes2.getInt(j.MenuItem_android_menuCategory, bVar.f504c) & (-65536)) | (obtainStyledAttributes2.getInt(j.MenuItem_android_orderInCategory, bVar.f505d) & 65535);
                        bVar.k = obtainStyledAttributes2.getText(j.MenuItem_android_title);
                        bVar.l = obtainStyledAttributes2.getText(j.MenuItem_android_titleCondensed);
                        bVar.m = obtainStyledAttributes2.getResourceId(j.MenuItem_android_icon, 0);
                        String string = obtainStyledAttributes2.getString(j.MenuItem_android_alphabeticShortcut);
                        bVar.n = string == null ? (char) 0 : string.charAt(0);
                        bVar.o = obtainStyledAttributes2.getInt(j.MenuItem_alphabeticModifiers, 4096);
                        String string2 = obtainStyledAttributes2.getString(j.MenuItem_android_numericShortcut);
                        bVar.p = string2 == null ? (char) 0 : string2.charAt(0);
                        bVar.q = obtainStyledAttributes2.getInt(j.MenuItem_numericModifiers, 4096);
                        if (obtainStyledAttributes2.hasValue(j.MenuItem_android_checkable)) {
                            bVar.r = obtainStyledAttributes2.getBoolean(j.MenuItem_android_checkable, false) ? 1 : 0;
                        } else {
                            bVar.r = bVar.f506e;
                        }
                        bVar.s = obtainStyledAttributes2.getBoolean(j.MenuItem_android_checked, false);
                        bVar.t = obtainStyledAttributes2.getBoolean(j.MenuItem_android_visible, bVar.f);
                        bVar.u = obtainStyledAttributes2.getBoolean(j.MenuItem_android_enabled, bVar.g);
                        bVar.v = obtainStyledAttributes2.getInt(j.MenuItem_showAsAction, -1);
                        bVar.z = obtainStyledAttributes2.getString(j.MenuItem_android_onClick);
                        bVar.w = obtainStyledAttributes2.getResourceId(j.MenuItem_actionLayout, 0);
                        bVar.x = obtainStyledAttributes2.getString(j.MenuItem_actionViewClass);
                        bVar.y = obtainStyledAttributes2.getString(j.MenuItem_actionProviderClass);
                        if ((bVar.y != null) && bVar.w == 0 && bVar.x == null) {
                            bVar.A = (c.e.h.b) bVar.a(bVar.y, f495b, f.this.f497d);
                        } else {
                            bVar.A = null;
                        }
                        bVar.B = obtainStyledAttributes2.getText(j.MenuItem_contentDescription);
                        bVar.C = obtainStyledAttributes2.getText(j.MenuItem_tooltipText);
                        if (obtainStyledAttributes2.hasValue(j.MenuItem_iconTintMode)) {
                            bVar.E = E.a(obtainStyledAttributes2.getInt(j.MenuItem_iconTintMode, -1), bVar.E);
                        } else {
                            bVar.E = null;
                        }
                        if (obtainStyledAttributes2.hasValue(j.MenuItem_iconTint)) {
                            bVar.D = obtainStyledAttributes2.getColorStateList(j.MenuItem_iconTint);
                        } else {
                            bVar.D = null;
                        }
                        obtainStyledAttributes2.recycle();
                        bVar.h = false;
                    } else {
                        if (name3.equals("menu")) {
                            a(xmlPullParser, attributeSet, bVar.a());
                        } else {
                            z2 = true;
                            str = name3;
                        }
                        eventType = xmlPullParser.next();
                    }
                }
                eventType = xmlPullParser.next();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.content.res.XmlResourceParser, org.xmlpull.v1.XmlPullParser] */
    @Override // android.view.MenuInflater
    public void inflate(int i, Menu menu) {
        XmlResourceParser xmlResourceParser = menu instanceof c.e.d.a.a;
        if (xmlResourceParser == 0) {
            super.inflate(i, menu);
            return;
        }
        try {
            xmlResourceParser = 0;
            try {
                try {
                    xmlResourceParser = this.f498e.getResources().getLayout(i);
                    a(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
                    xmlResourceParser.close();
                } catch (XmlPullParserException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (IOException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } catch (Throwable th) {
            if (xmlResourceParser != 0) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }

    /* loaded from: classes.dex */
    public class b {
        public c.e.h.b A;
        public CharSequence B;
        public CharSequence C;

        /* renamed from: a */
        public Menu f502a;
        public boolean h;
        public int i;
        public int j;
        public CharSequence k;
        public CharSequence l;
        public int m;
        public char n;
        public int o;
        public char p;
        public int q;
        public int r;
        public boolean s;
        public boolean t;
        public boolean u;
        public int v;
        public int w;
        public String x;
        public String y;
        public String z;
        public ColorStateList D = null;
        public PorterDuff.Mode E = null;

        /* renamed from: b */
        public int f503b = 0;

        /* renamed from: c */
        public int f504c = 0;

        /* renamed from: d */
        public int f505d = 0;

        /* renamed from: e */
        public int f506e = 0;
        public boolean f = true;
        public boolean g = true;

        public b(Menu menu) {
            f.this = r1;
            this.f502a = menu;
        }

        public final void a(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
            int i = this.v;
            if (i >= 0) {
                menuItem.setShowAsAction(i);
            }
            if (this.z != null) {
                if (!f.this.f498e.isRestricted()) {
                    f fVar = f.this;
                    if (fVar.f == null) {
                        fVar.f = fVar.a(fVar.f498e);
                    }
                    menuItem.setOnMenuItemClickListener(new a(fVar.f, this.z));
                } else {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
            }
            boolean z2 = menuItem instanceof p;
            if (z2) {
                p pVar = (p) menuItem;
            }
            if (this.r >= 2) {
                if (z2) {
                    p pVar2 = (p) menuItem;
                    pVar2.y = (pVar2.y & (-5)) | 4;
                } else if (menuItem instanceof q) {
                    q qVar = (q) menuItem;
                    try {
                        if (qVar.f465e == null) {
                            qVar.f465e = ((c.e.d.a.b) qVar.f423a).getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                        }
                        qVar.f465e.invoke(qVar.f423a, true);
                    } catch (Exception unused) {
                    }
                }
            }
            String str = this.x;
            if (str != null) {
                menuItem.setActionView((View) a(str, f.f494a, f.this.f496c));
                z = true;
            }
            int i2 = this.w;
            if (i2 > 0 && !z) {
                menuItem.setActionView(i2);
            }
            c.e.h.b bVar = this.A;
            if (bVar != null && (menuItem instanceof c.e.d.a.b)) {
                ((c.e.d.a.b) menuItem).a(bVar);
            }
            CharSequence charSequence = this.B;
            boolean z3 = menuItem instanceof c.e.d.a.b;
            if (z3) {
                ((c.e.d.a.b) menuItem).mo3setContentDescription(charSequence);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setContentDescription(charSequence);
            }
            CharSequence charSequence2 = this.C;
            if (z3) {
                ((c.e.d.a.b) menuItem).mo4setTooltipText(charSequence2);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setTooltipText(charSequence2);
            }
            char c2 = this.n;
            int i3 = this.o;
            if (z3) {
                ((c.e.d.a.b) menuItem).setAlphabeticShortcut(c2, i3);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setAlphabeticShortcut(c2, i3);
            }
            char c3 = this.p;
            int i4 = this.q;
            if (z3) {
                ((c.e.d.a.b) menuItem).setNumericShortcut(c3, i4);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setNumericShortcut(c3, i4);
            }
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                if (z3) {
                    ((c.e.d.a.b) menuItem).setIconTintMode(mode);
                } else if (Build.VERSION.SDK_INT >= 26) {
                    menuItem.setIconTintMode(mode);
                }
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList == null) {
                return;
            }
            if (z3) {
                ((c.e.d.a.b) menuItem).setIconTintList(colorStateList);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setIconTintList(colorStateList);
            }
        }

        public SubMenu a() {
            this.h = true;
            SubMenu addSubMenu = this.f502a.addSubMenu(this.f503b, this.i, this.j, this.k);
            a(addSubMenu.getItem());
            return addSubMenu;
        }

        public final <T> T a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = f.this.f498e.getClassLoader().loadClass(str).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception unused) {
                String str2 = "Cannot instantiate class: " + str;
                return null;
            }
        }
    }

    public final Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }
}
