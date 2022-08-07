package c.e.h;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class m implements View.OnApplyWindowInsetsListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ j f861a;

    public m(j jVar) {
        this.f861a = jVar;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        Object obj = null;
        v a2 = this.f861a.a(view, windowInsets == null ? null : new v(windowInsets));
        if (a2 != null) {
            obj = a2.f876a;
        }
        return (WindowInsets) obj;
    }
}
