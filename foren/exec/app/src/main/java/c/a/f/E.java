package c.a.f;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import c.a.c.a.e;
import c.e.c.a.b;
import c.e.c.a.c;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class E {

    /* renamed from: a */
    public static final Rect f526a = new Rect();

    /* renamed from: b */
    public static Class<?> f527b;

    static {
        int i = Build.VERSION.SDK_INT;
        try {
            f527b = Class.forName("android.graphics.Insets");
        } catch (ClassNotFoundException unused) {
        }
    }

    public static boolean a(Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        if (drawable instanceof DrawableContainer) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (!(constantState instanceof DrawableContainer.DrawableContainerState)) {
                return true;
            }
            for (Drawable drawable2 : ((DrawableContainer.DrawableContainerState) constantState).getChildren()) {
                if (!a(drawable2)) {
                    return false;
                }
            }
            return true;
        } else if (drawable instanceof b) {
            return a(((c) drawable).g);
        } else {
            if (drawable instanceof e) {
                return a(((e) drawable).f401a);
            }
            if (drawable instanceof ScaleDrawable) {
                return a(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
    }

    public static void b(Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            int[] state = drawable.getState();
            if (state == null || state.length == 0) {
                drawable.setState(fa.f596e);
            } else {
                drawable.setState(fa.f);
            }
            drawable.setState(state);
        }
    }

    public static Rect c(Drawable drawable) {
        if (f527b != null) {
            try {
                if (drawable instanceof b) {
                    drawable = ((c) drawable).g;
                }
                Object invoke = drawable.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(drawable, new Object[0]);
                if (invoke != null) {
                    Rect rect = new Rect();
                    Field[] fields = f527b.getFields();
                    for (Field field : fields) {
                        String name = field.getName();
                        char c2 = 65535;
                        switch (name.hashCode()) {
                            case -1383228885:
                                if (name.equals("bottom")) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                            case 115029:
                                if (name.equals("top")) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                            case 3317767:
                                if (name.equals("left")) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                            case 108511772:
                                if (name.equals("right")) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                        }
                        if (c2 == 0) {
                            rect.left = field.getInt(invoke);
                        } else if (c2 == 1) {
                            rect.top = field.getInt(invoke);
                        } else if (c2 == 2) {
                            rect.right = field.getInt(invoke);
                        } else if (c2 == 3) {
                            rect.bottom = field.getInt(invoke);
                        }
                    }
                    return rect;
                }
            } catch (Exception unused) {
            }
        }
        return f526a;
    }

    public static PorterDuff.Mode a(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
