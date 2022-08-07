package c.a.f;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class B {

    /* renamed from: a  reason: collision with root package name */
    public static final RectF f521a = new RectF();

    /* renamed from: b  reason: collision with root package name */
    public static ConcurrentHashMap<String, Method> f522b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    public int f523c = 0;

    /* renamed from: d  reason: collision with root package name */
    public boolean f524d = false;

    /* renamed from: e  reason: collision with root package name */
    public float f525e = -1.0f;
    public float f = -1.0f;
    public float g = -1.0f;
    public int[] h = new int[0];
    public boolean i = false;
    public TextPaint j;
    public final TextView k;
    public final Context l;

    public B(TextView textView) {
        this.k = textView;
        this.l = this.k.getContext();
    }

    public final int[] a(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i)) < 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public int b() {
        return Math.round(this.g);
    }

    public int c() {
        return Math.round(this.f);
    }

    public int d() {
        return Math.round(this.f525e);
    }

    public int[] e() {
        return this.h;
    }

    public int f() {
        return this.f523c;
    }

    public final boolean g() {
        if (!i() || this.f523c != 1) {
            this.f524d = false;
        } else {
            if (!this.i || this.h.length == 0) {
                float round = Math.round(this.f);
                int i = 1;
                while (Math.round(this.f525e + round) <= Math.round(this.g)) {
                    i++;
                    round += this.f525e;
                }
                int[] iArr = new int[i];
                float f = this.f;
                for (int i2 = 0; i2 < i; i2++) {
                    iArr[i2] = Math.round(f);
                    f += this.f525e;
                }
                this.h = a(iArr);
            }
            this.f524d = true;
        }
        return this.f524d;
    }

    public final boolean h() {
        int length = this.h.length;
        this.i = length > 0;
        if (this.i) {
            this.f523c = 1;
            int[] iArr = this.h;
            this.f = iArr[0];
            this.g = iArr[length - 1];
            this.f525e = -1.0f;
        }
        return this.i;
    }

    public final boolean i() {
        return !(this.k instanceof AppCompatEditText);
    }

    public final void a(float f, float f2, float f3) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f + "px) is less or equal to (0px)");
        } else if (f2 <= f) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f2 + "px) is less or equal to minimum auto-size text size (" + f + "px)");
        } else if (f3 > 0.0f) {
            this.f523c = 1;
            this.f = f;
            this.g = f2;
            this.f525e = f3;
            this.i = false;
        } else {
            throw new IllegalArgumentException("The auto-size step granularity (" + f3 + "px) is less or equal to (0px)");
        }
    }

    public void a(int i, float f) {
        Resources resources;
        Context context = this.l;
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        float applyDimension = TypedValue.applyDimension(i, f, resources.getDisplayMetrics());
        if (applyDimension != this.k.getPaint().getTextSize()) {
            this.k.getPaint().setTextSize(applyDimension);
            int i2 = Build.VERSION.SDK_INT;
            boolean isInLayout = this.k.isInLayout();
            if (this.k.getLayout() != null) {
                this.f524d = false;
                try {
                    Method a2 = a("nullLayouts");
                    if (a2 != null) {
                        a2.invoke(this.k, new Object[0]);
                    }
                } catch (Exception unused) {
                }
                if (!isInLayout) {
                    this.k.requestLayout();
                } else {
                    this.k.forceLayout();
                }
                this.k.invalidate();
            }
        }
    }

    public final int a(RectF rectF) {
        StaticLayout staticLayout;
        int i;
        CharSequence transformation;
        int length = this.h.length;
        if (length != 0) {
            int i2 = length - 1;
            int i3 = 0;
            int i4 = 1;
            int i5 = 0;
            while (i4 <= i2) {
                int i6 = (i4 + i2) / 2;
                int i7 = this.h[i6];
                CharSequence text = this.k.getText();
                TransformationMethod transformationMethod = this.k.getTransformationMethod();
                if (!(transformationMethod == null || (transformation = transformationMethod.getTransformation(text, this.k)) == null)) {
                    text = transformation;
                }
                int i8 = Build.VERSION.SDK_INT;
                int maxLines = this.k.getMaxLines();
                TextPaint textPaint = this.j;
                if (textPaint == null) {
                    this.j = new TextPaint();
                } else {
                    textPaint.reset();
                }
                this.j.set(this.k.getPaint());
                this.j.setTextSize(i7);
                Layout.Alignment alignment = (Layout.Alignment) a((Object) this.k, "getLayoutAlignment", (String) Layout.Alignment.ALIGN_NORMAL);
                if (Build.VERSION.SDK_INT >= 23) {
                    staticLayout = StaticLayout.Builder.obtain(text, i3, text.length(), this.j, Math.round(rectF.right)).setAlignment(alignment).setLineSpacing(this.k.getLineSpacingExtra(), this.k.getLineSpacingMultiplier()).setIncludePad(this.k.getIncludeFontPadding()).setBreakStrategy(this.k.getBreakStrategy()).setHyphenationFrequency(this.k.getHyphenationFrequency()).setMaxLines(maxLines == -1 ? Integer.MAX_VALUE : maxLines).setTextDirection((TextDirectionHeuristic) a((Object) this.k, "getTextDirectionHeuristic", (String) TextDirectionHeuristics.FIRSTSTRONG_LTR)).build();
                    i = -1;
                } else {
                    int round = Math.round(rectF.right);
                    int i9 = Build.VERSION.SDK_INT;
                    i = -1;
                    staticLayout = new StaticLayout(text, this.j, round, alignment, this.k.getLineSpacingMultiplier(), this.k.getLineSpacingExtra(), this.k.getIncludeFontPadding());
                }
                if ((maxLines == i || (staticLayout.getLineCount() <= maxLines && staticLayout.getLineEnd(staticLayout.getLineCount() - 1) == text.length())) && ((float) staticLayout.getHeight()) <= rectF.bottom) {
                    i4 = i6 + 1;
                    i5 = i4;
                } else {
                    i5 = i6 - 1;
                    i2 = i5;
                }
                i3 = 0;
            }
            return this.h[i5];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    public final <T> T a(Object obj, String str, T t) {
        try {
            return (T) a(str).invoke(obj, new Object[0]);
        } catch (Exception unused) {
            String str2 = "Failed to invoke TextView#" + str + "() method";
            return t;
        }
    }

    public final Method a(String str) {
        try {
            Method method = f522b.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                f522b.put(str, method);
            }
            return method;
        } catch (Exception unused) {
            String str2 = "Failed to retrieve TextView#" + str + "() method";
            return null;
        }
    }

    public void a() {
        if (i() && this.f523c != 0) {
            if (this.f524d) {
                if (this.k.getMeasuredHeight() > 0 && this.k.getMeasuredWidth() > 0) {
                    int measuredWidth = ((Boolean) a((Object) this.k, "getHorizontallyScrolling", (String) false)).booleanValue() ? 1048576 : (this.k.getMeasuredWidth() - this.k.getTotalPaddingLeft()) - this.k.getTotalPaddingRight();
                    int height = (this.k.getHeight() - this.k.getCompoundPaddingBottom()) - this.k.getCompoundPaddingTop();
                    if (measuredWidth > 0 && height > 0) {
                        synchronized (f521a) {
                            f521a.setEmpty();
                            f521a.right = measuredWidth;
                            f521a.bottom = height;
                            float a2 = a(f521a);
                            if (a2 != this.k.getTextSize()) {
                                a(0, a2);
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.f524d = true;
        }
    }
}
