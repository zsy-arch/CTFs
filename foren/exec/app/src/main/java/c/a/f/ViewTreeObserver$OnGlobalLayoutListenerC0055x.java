package c.a.f;

import android.view.ViewTreeObserver;
import androidx.appcompat.widget.AppCompatSpinner;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.x  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class ViewTreeObserver$OnGlobalLayoutListenerC0055x implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppCompatSpinner.b f661a;

    public ViewTreeObserver$OnGlobalLayoutListenerC0055x(AppCompatSpinner.b bVar) {
        this.f661a = bVar;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        AppCompatSpinner.b bVar = this.f661a;
        if (!bVar.b(AppCompatSpinner.this)) {
            this.f661a.dismiss();
            return;
        }
        this.f661a.l();
        ViewTreeObserver$OnGlobalLayoutListenerC0055x.super.c();
    }
}
