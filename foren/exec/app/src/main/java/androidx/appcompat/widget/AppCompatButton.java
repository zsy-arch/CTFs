package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;
import c.a.a;
import c.a.a.C;
import c.a.f.A;
import c.a.f.C0047o;
import c.a.f.ha;
import c.e.h.l;
import c.e.i.b;

/* loaded from: classes.dex */
public class AppCompatButton extends Button implements l, b {

    /* renamed from: a  reason: collision with root package name */
    public final C0047o f100a;

    /* renamed from: b  reason: collision with root package name */
    public final A f101b;

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.buttonStyle);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0047o oVar = this.f100a;
        if (oVar != null) {
            oVar.a();
        }
        A a2 = this.f101b;
        if (a2 != null) {
            a2.a();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (b.f888a) {
            return super.getAutoSizeMaxTextSize();
        }
        A a2 = this.f101b;
        if (a2 != null) {
            return a2.h.b();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (b.f888a) {
            return super.getAutoSizeMinTextSize();
        }
        A a2 = this.f101b;
        if (a2 != null) {
            return a2.h.c();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (b.f888a) {
            return super.getAutoSizeStepGranularity();
        }
        A a2 = this.f101b;
        if (a2 != null) {
            return a2.h.d();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (b.f888a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        A a2 = this.f101b;
        if (a2 != null) {
            return a2.h.e();
        }
        return new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (b.f888a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        A a2 = this.f101b;
        if (a2 != null) {
            return a2.h.f();
        }
        return 0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0047o oVar = this.f100a;
        if (oVar != null) {
            return oVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0047o oVar = this.f100a;
        if (oVar != null) {
            return oVar.c();
        }
        return null;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        A a2 = this.f101b;
        if (a2 != null) {
            a2.a(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        A a2 = this.f101b;
        if (a2 != null && !b.f888a && a2.b()) {
            this.f101b.h.a();
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (b.f888a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        A a2 = this.f101b;
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
        A a2 = this.f101b;
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
        A a2 = this.f101b;
        if (a2 != null) {
            a2.a(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0047o oVar = this.f100a;
        if (oVar != null) {
            oVar.f613c = -1;
            oVar.a((ColorStateList) null);
            oVar.a();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0047o oVar = this.f100a;
        if (oVar != null) {
            oVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C.a((TextView) this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        A a2 = this.f101b;
        if (a2 != null) {
            a2.f516a.setAllCaps(z);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0047o oVar = this.f100a;
        if (oVar != null) {
            oVar.b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0047o oVar = this.f100a;
        if (oVar != null) {
            oVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        A a2 = this.f101b;
        if (a2 != null) {
            a2.a(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (b.f888a) {
            super.setTextSize(i, f);
            return;
        }
        A a2 = this.f101b;
        if (a2 != null) {
            a2.a(i, f);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ha.a(context);
        this.f100a = new C0047o(this);
        this.f100a.a(attributeSet, i);
        this.f101b = new A(this);
        this.f101b.a(attributeSet, i);
        this.f101b.a();
    }
}
