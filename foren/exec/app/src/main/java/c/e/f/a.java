package c.e.f;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import c.a.a.C;

/* loaded from: classes.dex */
public class a implements Spannable {

    /* renamed from: a  reason: collision with root package name */
    public final Spannable f829a;

    /* renamed from: b  reason: collision with root package name */
    public final C0009a f830b;

    /* renamed from: c.e.f.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0009a {

        /* renamed from: a  reason: collision with root package name */
        public final TextPaint f831a;

        /* renamed from: b  reason: collision with root package name */
        public final TextDirectionHeuristic f832b;

        /* renamed from: c  reason: collision with root package name */
        public final int f833c;

        /* renamed from: d  reason: collision with root package name */
        public final int f834d;

        /* renamed from: e  reason: collision with root package name */
        public final PrecomputedText.Params f835e = null;

        public C0009a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            this.f831a = textPaint;
            this.f832b = textDirectionHeuristic;
            this.f833c = i;
            this.f834d = i2;
        }

        public int a() {
            return this.f833c;
        }

        public int b() {
            return this.f834d;
        }

        public TextDirectionHeuristic c() {
            return this.f832b;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0009a)) {
                return false;
            }
            C0009a aVar = (C0009a) obj;
            if (!a(aVar)) {
                return false;
            }
            int i = Build.VERSION.SDK_INT;
            return this.f832b == aVar.f832b;
        }

        public int hashCode() {
            if (Build.VERSION.SDK_INT >= 24) {
                return C.a(Float.valueOf(this.f831a.getTextSize()), Float.valueOf(this.f831a.getTextScaleX()), Float.valueOf(this.f831a.getTextSkewX()), Float.valueOf(this.f831a.getLetterSpacing()), Integer.valueOf(this.f831a.getFlags()), this.f831a.getTextLocales(), this.f831a.getTypeface(), Boolean.valueOf(this.f831a.isElegantTextHeight()), this.f832b, Integer.valueOf(this.f833c), Integer.valueOf(this.f834d));
            }
            return C.a(Float.valueOf(this.f831a.getTextSize()), Float.valueOf(this.f831a.getTextScaleX()), Float.valueOf(this.f831a.getTextSkewX()), Float.valueOf(this.f831a.getLetterSpacing()), Integer.valueOf(this.f831a.getFlags()), this.f831a.getTextLocale(), this.f831a.getTypeface(), Boolean.valueOf(this.f831a.isElegantTextHeight()), this.f832b, Integer.valueOf(this.f833c), Integer.valueOf(this.f834d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            StringBuilder a2 = e.a.a.a.a.a("textSize=");
            a2.append(this.f831a.getTextSize());
            sb.append(a2.toString());
            sb.append(", textScaleX=" + this.f831a.getTextScaleX());
            sb.append(", textSkewX=" + this.f831a.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            StringBuilder a3 = e.a.a.a.a.a(", letterSpacing=");
            a3.append(this.f831a.getLetterSpacing());
            sb.append(a3.toString());
            sb.append(", elegantTextHeight=" + this.f831a.isElegantTextHeight());
            if (Build.VERSION.SDK_INT >= 24) {
                StringBuilder a4 = e.a.a.a.a.a(", textLocale=");
                a4.append(this.f831a.getTextLocales());
                sb.append(a4.toString());
            } else {
                StringBuilder a5 = e.a.a.a.a.a(", textLocale=");
                a5.append(this.f831a.getTextLocale());
                sb.append(a5.toString());
            }
            StringBuilder a6 = e.a.a.a.a.a(", typeface=");
            a6.append(this.f831a.getTypeface());
            sb.append(a6.toString());
            if (Build.VERSION.SDK_INT >= 26) {
                StringBuilder a7 = e.a.a.a.a.a(", variationSettings=");
                a7.append(this.f831a.getFontVariationSettings());
                sb.append(a7.toString());
            }
            StringBuilder a8 = e.a.a.a.a.a(", textDir=");
            a8.append(this.f832b);
            sb.append(a8.toString());
            sb.append(", breakStrategy=" + this.f833c);
            sb.append(", hyphenationFrequency=" + this.f834d);
            sb.append("}");
            return sb.toString();
        }

        public boolean a(C0009a aVar) {
            PrecomputedText.Params params = this.f835e;
            if (params != null) {
                return params.equals(aVar.f835e);
            }
            if ((Build.VERSION.SDK_INT >= 23 && (this.f833c != aVar.f833c || this.f834d != aVar.f834d)) || this.f831a.getTextSize() != aVar.f831a.getTextSize() || this.f831a.getTextScaleX() != aVar.f831a.getTextScaleX() || this.f831a.getTextSkewX() != aVar.f831a.getTextSkewX()) {
                return false;
            }
            int i = Build.VERSION.SDK_INT;
            if (this.f831a.getLetterSpacing() != aVar.f831a.getLetterSpacing() || !TextUtils.equals(this.f831a.getFontFeatureSettings(), aVar.f831a.getFontFeatureSettings()) || this.f831a.getFlags() != aVar.f831a.getFlags()) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                if (!this.f831a.getTextLocales().equals(aVar.f831a.getTextLocales())) {
                    return false;
                }
            } else if (!this.f831a.getTextLocale().equals(aVar.f831a.getTextLocale())) {
                return false;
            }
            return this.f831a.getTypeface() == null ? aVar.f831a.getTypeface() == null : this.f831a.getTypeface().equals(aVar.f831a.getTypeface());
        }

        public C0009a(PrecomputedText.Params params) {
            this.f831a = params.getTextPaint();
            this.f832b = params.getTextDirection();
            this.f833c = params.getBreakStrategy();
            this.f834d = params.getHyphenationFrequency();
        }
    }

    static {
        new Object();
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f829a.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f829a.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f829a.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f829a.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return (T[]) this.f829a.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f829a.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f829a.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj) {
        if (!(obj instanceof MetricAffectingSpan)) {
            this.f829a.removeSpan(obj);
            return;
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
    }

    @Override // android.text.Spannable
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (!(obj instanceof MetricAffectingSpan)) {
            this.f829a.setSpan(obj, i, i2, i3);
            return;
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f829a.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f829a.toString();
    }
}
