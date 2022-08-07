package c.a.f;

import android.view.View;
import androidx.appcompat.widget.ActivityChooserView;
import c.a.e.a.y;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.m  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0045m extends H {
    public final /* synthetic */ ActivityChooserView j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0045m(ActivityChooserView activityChooserView, View view) {
        super(view);
        this.j = activityChooserView;
    }

    @Override // c.a.f.H
    public y b() {
        return this.j.getListPopupWindow();
    }

    @Override // c.a.f.H
    public boolean c() {
        this.j.c();
        return true;
    }

    @Override // c.a.f.H
    public boolean d() {
        this.j.a();
        return true;
    }
}
