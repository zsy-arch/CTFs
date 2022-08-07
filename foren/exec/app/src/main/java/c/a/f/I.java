package c.a.f;

import android.view.View;
import androidx.appcompat.widget.ListPopupWindow;

/* loaded from: classes.dex */
public class I implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ListPopupWindow f542a;

    public I(ListPopupWindow listPopupWindow) {
        this.f542a = listPopupWindow;
    }

    @Override // java.lang.Runnable
    public void run() {
        View e2 = this.f542a.e();
        if (e2 != null && e2.getWindowToken() != null) {
            this.f542a.c();
        }
    }
}
