package c.a.f;

import android.view.View;
import androidx.appcompat.widget.ActionMenuPresenter;
import c.a.e.a.y;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.g  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0039g extends H {
    public final /* synthetic */ ActionMenuPresenter.d j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0039g(ActionMenuPresenter.d dVar, View view, ActionMenuPresenter actionMenuPresenter) {
        super(view);
        this.j = dVar;
    }

    @Override // c.a.f.H
    public y b() {
        ActionMenuPresenter.e eVar = ActionMenuPresenter.this.x;
        if (eVar == null) {
            return null;
        }
        return eVar.b();
    }

    @Override // c.a.f.H
    public boolean c() {
        ActionMenuPresenter.this.e();
        return true;
    }

    @Override // c.a.f.H
    public boolean d() {
        ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
        if (actionMenuPresenter.z != null) {
            return false;
        }
        actionMenuPresenter.c();
        return true;
    }
}
