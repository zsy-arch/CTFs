package c.a.b.a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import c.a.a.C;
import c.a.f.C0049q;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<TypedValue> f377a = new ThreadLocal<>();

    /* renamed from: b  reason: collision with root package name */
    public static final WeakHashMap<Context, SparseArray<C0003a>> f378b = new WeakHashMap<>(0);

    /* renamed from: c  reason: collision with root package name */
    public static final Object f379c = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c.a.b.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0003a {

        /* renamed from: a  reason: collision with root package name */
        public final ColorStateList f380a;

        /* renamed from: b  reason: collision with root package name */
        public final Configuration f381b;

        public C0003a(ColorStateList colorStateList, Configuration configuration) {
            this.f380a = colorStateList;
            this.f381b = configuration;
        }
    }

    public static ColorStateList a(Context context, int i) {
        C0003a aVar;
        synchronized (f379c) {
            SparseArray<C0003a> sparseArray = f378b.get(context);
            if (!(sparseArray == null || sparseArray.size() <= 0 || (aVar = sparseArray.get(i)) == null)) {
                if (aVar.f381b.equals(context.getResources().getConfiguration())) {
                    return aVar.f380a;
                }
                sparseArray.remove(i);
            }
            return null;
        }
    }

    public static ColorStateList b(Context context, int i) {
        int next;
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        ColorStateList a2 = a(context, i);
        if (a2 != null) {
            return a2;
        }
        Resources resources = context.getResources();
        TypedValue typedValue = f377a.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            f377a.set(typedValue);
        }
        resources.getValue(i, typedValue, true);
        int i2 = typedValue.type;
        ColorStateList colorStateList = null;
        if (!(i2 >= 28 && i2 <= 31)) {
            Resources resources2 = context.getResources();
            XmlResourceParser xml = resources2.getXml(i);
            try {
                Resources.Theme theme = context.getTheme();
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                while (true) {
                    next = xml.next();
                    if (next == 2 || next == 1) {
                        break;
                    }
                }
                if (next == 2) {
                    colorStateList = C.a(resources2, xml, asAttributeSet, theme);
                } else {
                    throw new XmlPullParserException("No start tag found");
                }
            } catch (Exception unused) {
            }
        }
        if (colorStateList == null) {
            return c.e.b.a.a(context, i);
        }
        a(context, i, colorStateList);
        return colorStateList;
    }

    public static Drawable c(Context context, int i) {
        return C0049q.a().c(context, i);
    }

    public static void a(Context context, int i, ColorStateList colorStateList) {
        synchronized (f379c) {
            SparseArray<C0003a> sparseArray = f378b.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                f378b.put(context, sparseArray);
            }
            sparseArray.append(i, new C0003a(colorStateList, context.getResources().getConfiguration()));
        }
    }
}
