package c.h.a;

import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DrawerLayout.e f908a;

    public c(DrawerLayout.e eVar) {
        this.f908a = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        View view;
        DrawerLayout.e eVar = this.f908a;
        int i2 = eVar.f236b.p;
        int i3 = 0;
        boolean z = eVar.f235a == 3;
        if (z) {
            view = DrawerLayout.this.a(3);
            if (view != null) {
                i3 = -view.getWidth();
            }
            i = i3 + i2;
        } else {
            view = DrawerLayout.this.a(5);
            i = DrawerLayout.this.getWidth() - i2;
        }
        if (view == null) {
            return;
        }
        if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && DrawerLayout.this.d(view) == 0) {
            eVar.f236b.b(view, i, view.getTop());
            ((DrawerLayout.d) view.getLayoutParams()).f233c = true;
            DrawerLayout.this.invalidate();
            eVar.a();
            DrawerLayout.this.a();
        }
    }
}
