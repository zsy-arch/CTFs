package c.a.f;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import androidx.appcompat.widget.AppCompatSpinner;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.y  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0056y implements PopupWindow.OnDismissListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f663a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AppCompatSpinner.b f664b;

    public C0056y(AppCompatSpinner.b bVar, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        this.f664b = bVar;
        this.f663a = onGlobalLayoutListener;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f663a);
        }
    }
}
