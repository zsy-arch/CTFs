package c.a.e.a;

import android.widget.PopupWindow;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class t implements PopupWindow.OnDismissListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ u f473a;

    public t(u uVar) {
        this.f473a = uVar;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.f473a.d();
    }
}
