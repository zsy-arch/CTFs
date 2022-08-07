package c.a.f;

import android.graphics.Typeface;
import android.widget.TextView;
import c.e.b.a.j;
import java.lang.ref.WeakReference;

/* renamed from: c.a.f.z  reason: case insensitive filesystem */
/* loaded from: classes.dex */
class C0057z extends j {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ WeakReference f665a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ A f666b;

    public C0057z(A a2, WeakReference weakReference) {
        this.f666b = a2;
        this.f665a = weakReference;
    }

    @Override // c.e.b.a.j
    public void a(int i) {
    }

    @Override // c.e.b.a.j
    public void a(Typeface typeface) {
        A a2 = this.f666b;
        WeakReference weakReference = this.f665a;
        if (a2.k) {
            a2.j = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, a2.i);
            }
        }
    }
}
