package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;
import c.a.a;
import c.a.a.C;
import c.a.f.A;
import c.a.f.C0047o;
import c.a.f.ha;
import c.e.h.l;

/* loaded from: classes.dex */
public class AppCompatEditText extends EditText implements l {

    /* renamed from: a  reason: collision with root package name */
    public final C0047o f106a;

    /* renamed from: b  reason: collision with root package name */
    public final A f107b;

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.editTextStyle);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0047o oVar = this.f106a;
        if (oVar != null) {
            oVar.a();
        }
        A a2 = this.f107b;
        if (a2 != null) {
            a2.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0047o oVar = this.f106a;
        if (oVar != null) {
            return oVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0047o oVar = this.f106a;
        if (oVar != null) {
            return oVar.c();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C.a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0047o oVar = this.f106a;
        if (oVar != null) {
            oVar.f613c = -1;
            oVar.a((ColorStateList) null);
            oVar.a();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0047o oVar = this.f106a;
        if (oVar != null) {
            oVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C.a((TextView) this, callback));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0047o oVar = this.f106a;
        if (oVar != null) {
            oVar.b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0047o oVar = this.f106a;
        if (oVar != null) {
            oVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        A a2 = this.f107b;
        if (a2 != null) {
            a2.a(context, i);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ha.a(context);
        this.f106a = new C0047o(this);
        this.f106a.a(attributeSet, i);
        this.f107b = new A(this);
        this.f107b.a(attributeSet, i);
        this.f107b.a();
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        if (Build.VERSION.SDK_INT >= 28) {
            return super.getText();
        }
        return super.getEditableText();
    }
}
