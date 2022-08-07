package androidx.appcompat.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import c.a.e.c;
import c.a.f.ha;
import c.a.j;
import c.c.b;
import c.e.h.n;
import com.tencent.smtt.sdk.BuildConfig;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class AppCompatViewInflater {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?>[] f46a = {Context.class, AttributeSet.class};

    /* renamed from: b  reason: collision with root package name */
    public static final int[] f47b = {16843375};

    /* renamed from: c  reason: collision with root package name */
    public static final String[] f48c = {"android.widget.", "android.view.", "android.webkit."};

    /* renamed from: d  reason: collision with root package name */
    public static final Map<String, Constructor<? extends View>> f49d = new b();

    /* renamed from: e  reason: collision with root package name */
    public final Object[] f50e = new Object[2];

    /* loaded from: classes.dex */
    private static class a implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        public final View f51a;

        /* renamed from: b  reason: collision with root package name */
        public final String f52b;

        /* renamed from: c  reason: collision with root package name */
        public Method f53c;

        /* renamed from: d  reason: collision with root package name */
        public Context f54d;

        public a(View view, String str) {
            this.f51a = view;
            this.f52b = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            Method method;
            if (this.f53c == null) {
                Context context = this.f51a.getContext();
                String str2 = this.f52b;
                while (context != null) {
                    try {
                        if (!context.isRestricted() && (method = context.getClass().getMethod(this.f52b, View.class)) != null) {
                            this.f53c = method;
                            this.f54d = context;
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                    context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
                }
                int id = this.f51a.getId();
                if (id == -1) {
                    str = BuildConfig.FLAVOR;
                } else {
                    StringBuilder a2 = e.a.a.a.a.a(" with id '");
                    a2.append(this.f51a.getContext().getResources().getResourceEntryName(id));
                    a2.append("'");
                    str = a2.toString();
                }
                StringBuilder a3 = e.a.a.a.a.a("Could not find method ");
                a3.append(this.f52b);
                a3.append("(View) in a parent or ancestor Context for android:onClick ");
                a3.append("attribute defined on view ");
                a3.append(this.f51a.getClass());
                a3.append(str);
                throw new IllegalStateException(a3.toString());
            }
            try {
                this.f53c.invoke(this.f54d, view);
            } catch (IllegalAccessException e2) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
            } catch (InvocationTargetException e3) {
                throw new IllegalStateException("Could not execute method for android:onClick", e3);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final View a(View view, String str, Context context, AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        char c2;
        View view2;
        c context2 = (!z || view == null) ? context : view.getContext();
        if (z2 || z3) {
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, j.View, 0, 0);
            int resourceId = z2 ? obtainStyledAttributes.getResourceId(j.View_android_theme, 0) : 0;
            if (z3 && resourceId == 0) {
                resourceId = obtainStyledAttributes.getResourceId(j.View_theme, 0);
            }
            obtainStyledAttributes.recycle();
            if (resourceId != 0 && (!(context2 instanceof c) || ((c) context2).f480a != resourceId)) {
                context2 = new c(context2, resourceId);
            }
        }
        if (z4) {
            ha.a(context2);
        }
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    c2 = '\f';
                    break;
                }
                c2 = 65535;
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                view2 = new AppCompatTextView(context2, attributeSet, 16842884);
                a(view2, str);
                break;
            case 1:
                view2 = new AppCompatImageView(context2, attributeSet, 0);
                a(view2, str);
                break;
            case 2:
                view2 = new AppCompatButton(context2, attributeSet, c.a.a.buttonStyle);
                a(view2, str);
                break;
            case 3:
                view2 = new AppCompatEditText(context2, attributeSet, c.a.a.editTextStyle);
                a(view2, str);
                break;
            case 4:
                view2 = new AppCompatSpinner(context2, attributeSet, c.a.a.spinnerStyle);
                a(view2, str);
                break;
            case 5:
                view2 = new AppCompatImageButton(context2, attributeSet, c.a.a.imageButtonStyle);
                a(view2, str);
                break;
            case 6:
                view2 = new AppCompatCheckBox(context2, attributeSet, c.a.a.checkboxStyle);
                a(view2, str);
                break;
            case 7:
                view2 = new AppCompatRadioButton(context2, attributeSet, c.a.a.radioButtonStyle);
                a(view2, str);
                break;
            case '\b':
                view2 = new AppCompatCheckedTextView(context2, attributeSet, 16843720);
                a(view2, str);
                break;
            case '\t':
                view2 = new AppCompatAutoCompleteTextView(context2, attributeSet, c.a.a.autoCompleteTextViewStyle);
                a(view2, str);
                break;
            case '\n':
                view2 = new AppCompatMultiAutoCompleteTextView(context2, attributeSet, c.a.a.autoCompleteTextViewStyle);
                a(view2, str);
                break;
            case 11:
                view2 = new AppCompatRatingBar(context2, attributeSet);
                a(view2, str);
                break;
            case '\f':
                view2 = new AppCompatSeekBar(context2, attributeSet, c.a.a.seekBarStyle);
                a(view2, str);
                break;
            default:
                view2 = null;
                break;
        }
        if (view2 == null && context != context2) {
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                this.f50e[0] = context2;
                this.f50e[1] = attributeSet;
            } catch (Exception unused) {
            } finally {
                Object[] objArr = this.f50e;
                objArr[0] = null;
                objArr[1] = null;
            }
            if (-1 == str.indexOf(46)) {
                for (int i = 0; i < f48c.length; i++) {
                    view2 = a(context2, str, f48c[i]);
                    if (view2 == null) {
                    }
                }
                view2 = null;
            } else {
                View a2 = a(context2, str, null);
                Object[] objArr2 = this.f50e;
                objArr2[0] = null;
                objArr2[1] = null;
                view2 = a2;
            }
        }
        if (view2 != null) {
            Context context3 = view2.getContext();
            if (context3 instanceof ContextWrapper) {
                int i2 = Build.VERSION.SDK_INT;
                if (n.m(view2)) {
                    TypedArray obtainStyledAttributes2 = context3.obtainStyledAttributes(attributeSet, f47b);
                    String string = obtainStyledAttributes2.getString(0);
                    if (string != null) {
                        view2.setOnClickListener(new a(view2, string));
                    }
                    obtainStyledAttributes2.recycle();
                }
            }
        }
        return view2;
    }

    public final void a(View view, String str) {
        if (view == null) {
            throw new IllegalStateException(AppCompatViewInflater.class.getName() + " asked to inflate view for <" + str + ">, but returned null");
        }
    }

    public final View a(Context context, String str, String str2) {
        String str3;
        Constructor<? extends View> constructor = f49d.get(str);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (str2 != null) {
                    str3 = str2 + str;
                } else {
                    str3 = str;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(f46a);
                f49d.put(str, constructor);
            } catch (Exception unused) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.f50e);
    }
}
