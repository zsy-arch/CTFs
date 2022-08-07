package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import c.a.a.C;
import c.a.f.A;
import c.a.f.C0047o;
import c.a.f.ha;
import c.e.f.a;
import c.e.h.l;
import c.e.i.b;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: classes.dex */
public class AppCompatTextView extends TextView implements l, b {

    /* renamed from: a  reason: collision with root package name */
    public final C0047o f126a;

    /* renamed from: b  reason: collision with root package name */
    public final A f127b;

    /* renamed from: c  reason: collision with root package name */
    public Future<a> f128c;

    public AppCompatTextView(Context context) {
        this(context, null, 16842884);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0047o oVar = this.f126a;
        if (oVar != null) {
            oVar.a();
        }
        A a2 = this.f127b;
        if (a2 != null) {
            a2.a();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (b.f888a) {
            return super.getAutoSizeMaxTextSize();
        }
        A a2 = this.f127b;
        if (a2 != null) {
            return Math.round(a2.h.g);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (b.f888a) {
            return super.getAutoSizeMinTextSize();
        }
        A a2 = this.f127b;
        if (a2 != null) {
            return Math.round(a2.h.f);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (b.f888a) {
            return super.getAutoSizeStepGranularity();
        }
        A a2 = this.f127b;
        if (a2 != null) {
            return Math.round(a2.h.f525e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (b.f888a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        A a2 = this.f127b;
        if (a2 != null) {
            return a2.h.h;
        }
        return new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (b.f888a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        A a2 = this.f127b;
        if (a2 != null) {
            return a2.h.f523c;
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0047o oVar = this.f126a;
        if (oVar != null) {
            return oVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0047o oVar = this.f126a;
        if (oVar != null) {
            return oVar.c();
        }
        return null;
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        Future<a> future = this.f128c;
        if (future != null) {
            try {
                this.f128c = null;
                C.a((TextView) this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        return super.getText();
    }

    public a.C0009a getTextMetricsParamsCompat() {
        return C.a((TextView) this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C.a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        A a2 = this.f127b;
        if (a2 != null) {
            a2.a(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        Future<a> future = this.f128c;
        if (future != null) {
            try {
                this.f128c = null;
                C.a((TextView) this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        A a2 = this.f127b;
        if (a2 != null && !b.f888a && a2.b()) {
            this.f127b.h.a();
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (b.f888a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        A a2 = this.f127b;
        if (a2 != null) {
            a2.a(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (b.f888a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        A a2 = this.f127b;
        if (a2 != null) {
            a2.a(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (b.f888a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        A a2 = this.f127b;
        if (a2 != null) {
            a2.a(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0047o oVar = this.f126a;
        if (oVar != null) {
            oVar.f613c = -1;
            oVar.a((ColorStateList) null);
            oVar.a();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0047o oVar = this.f126a;
        if (oVar != null) {
            oVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C.a((TextView) this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            C.a(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            C.b(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        C.c(this, i);
    }

    public void setPrecomputedText(a aVar) {
        C.a((TextView) this, aVar);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0047o oVar = this.f126a;
        if (oVar != null) {
            oVar.b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0047o oVar = this.f126a;
        if (oVar != null) {
            oVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        A a2 = this.f127b;
        if (a2 != null) {
            a2.a(context, i);
        }
    }

    public void setTextFuture(Future<a> future) {
        this.f128c = future;
        requestLayout();
    }

    public void setTextMetricsParamsCompat(a.C0009a aVar) {
        int i = Build.VERSION.SDK_INT;
        TextDirectionHeuristic c2 = aVar.c();
        int i2 = 1;
        if (!(c2 == TextDirectionHeuristics.FIRSTSTRONG_RTL || c2 == TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            if (c2 == TextDirectionHeuristics.ANYRTL_LTR) {
                i2 = 2;
            } else if (c2 == TextDirectionHeuristics.LTR) {
                i2 = 3;
            } else if (c2 == TextDirectionHeuristics.RTL) {
                i2 = 4;
            } else if (c2 == TextDirectionHeuristics.LOCALE) {
                i2 = 5;
            } else if (c2 == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
                i2 = 6;
            } else if (c2 == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
                i2 = 7;
            }
        }
        setTextDirection(i2);
        if (Build.VERSION.SDK_INT < 23) {
            float textScaleX = aVar.f831a.getTextScaleX();
            getPaint().set(aVar.f831a);
            if (textScaleX == getTextScaleX()) {
                setTextScaleX((textScaleX / 2.0f) + 1.0f);
            }
            setTextScaleX(textScaleX);
            return;
        }
        getPaint().set(aVar.f831a);
        setBreakStrategy(aVar.a());
        setHyphenationFrequency(aVar.b());
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (b.f888a) {
            super.setTextSize(i, f);
            return;
        }
        A a2 = this.f127b;
        if (a2 != null) {
            a2.a(i, f);
        }
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ha.a(context);
        this.f126a = new C0047o(this);
        this.f126a.a(attributeSet, i);
        this.f127b = new A(this);
        this.f127b.a(attributeSet, i);
        this.f127b.a();
    }
}
