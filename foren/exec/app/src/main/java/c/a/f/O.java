package c.a.f;

import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class O implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View f549a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ P f550b;

    public O(P p, View view) {
        this.f550b = p;
        this.f549a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f550b.smoothScrollTo(this.f549a.getLeft() - ((this.f550b.getWidth() - this.f549a.getWidth()) / 2), 0);
        this.f550b.f551a = null;
    }
}
