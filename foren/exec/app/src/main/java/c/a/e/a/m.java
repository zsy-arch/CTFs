package c.a.e.a;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import c.a.a.l;
import c.a.e.a.v;

/* loaded from: classes.dex */
public class m implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, v.a {

    /* renamed from: a  reason: collision with root package name */
    public l f455a;

    /* renamed from: b  reason: collision with root package name */
    public l f456b;

    /* renamed from: c  reason: collision with root package name */
    public j f457c;

    /* renamed from: d  reason: collision with root package name */
    public v.a f458d;

    public m(l lVar) {
        this.f455a = lVar;
    }

    @Override // c.a.e.a.v.a
    public void a(l lVar, boolean z) {
        l lVar2;
        if ((z || lVar == this.f455a) && (lVar2 = this.f456b) != null) {
            lVar2.dismiss();
        }
        v.a aVar = this.f458d;
        if (aVar != null) {
            aVar.a(lVar, z);
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.f455a.a((p) this.f457c.b().getItem(i), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        j jVar = this.f457c;
        l lVar = this.f455a;
        v.a aVar = jVar.h;
        if (aVar != null) {
            aVar.a(lVar, true);
        }
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f456b.getWindow();
                if (!(window2 == null || (decorView2 = window2.getDecorView()) == null || (keyDispatcherState2 = decorView2.getKeyDispatcherState()) == null)) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f456b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f455a.a(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f455a.performShortcut(i, keyEvent, 0);
    }

    @Override // c.a.e.a.v.a
    public boolean a(l lVar) {
        v.a aVar = this.f458d;
        if (aVar != null) {
            return aVar.a(lVar);
        }
        return false;
    }
}
