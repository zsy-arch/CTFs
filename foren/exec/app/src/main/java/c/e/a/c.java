package c.e.a;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import c.c.e;
import c.e.h.c;
import c.e.h.n;
import c.j.f;
import c.j.h;
import c.j.i;
import c.j.o;

/* loaded from: classes.dex */
public class c extends Activity implements h, c.a {

    /* renamed from: a  reason: collision with root package name */
    public i f743a = new i(this);

    public c() {
        int[] iArr = e.f695a;
        Object[] objArr = e.f697c;
    }

    @Override // c.e.h.c.a
    public boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !n.b(decorView, keyEvent)) {
            return c.e.h.c.a(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !n.b(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        o.a(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        this.f743a.a(f.b.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
