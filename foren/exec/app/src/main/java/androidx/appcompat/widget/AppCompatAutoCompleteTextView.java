package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import c.a.a;
import c.a.a.C;
import c.a.f.A;
import c.a.f.C0047o;
import c.a.f.ha;
import c.a.f.ka;
import c.e.h.l;

/* loaded from: classes.dex */
public class AppCompatAutoCompleteTextView extends AutoCompleteTextView implements l {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f97a = {16843126};

    /* renamed from: b  reason: collision with root package name */
    public final C0047o f98b;

    /* renamed from: c  reason: collision with root package name */
    public final A f99c;

    public AppCompatAutoCompleteTextView(Context context) {
        this(context, null, a.autoCompleteTextViewStyle);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0047o oVar = this.f98b;
        if (oVar != null) {
            oVar.a();
        }
        A a2 = this.f99c;
        if (a2 != null) {
            a2.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0047o oVar = this.f98b;
        if (oVar != null) {
            return oVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0047o oVar = this.f98b;
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
        C0047o oVar = this.f98b;
        if (oVar != null) {
            oVar.f613c = -1;
            oVar.a((ColorStateList) null);
            oVar.a();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0047o oVar = this.f98b;
        if (oVar != null) {
            oVar.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C.a((TextView) this, callback));
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(c.a.b.a.a.c(getContext(), i));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0047o oVar = this.f98b;
        if (oVar != null) {
            oVar.b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0047o oVar = this.f98b;
        if (oVar != null) {
            oVar.a(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        A a2 = this.f99c;
        if (a2 != null) {
            a2.a(context, i);
        }
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.autoCompleteTextViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ha.a(context);
        ka a2 = ka.a(getContext(), attributeSet, f97a, i, 0);
        if (a2.f(0)) {
            setDropDownBackgroundDrawable(a2.b(0));
        }
        a2.f605b.recycle();
        this.f98b = new C0047o(this);
        this.f98b.a(attributeSet, i);
        this.f99c = new A(this);
        this.f99c.a(attributeSet, i);
        this.f99c.a();
    }
}
