package c.e.b.a;

import android.graphics.Typeface;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Typeface f761a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ j f762b;

    public h(j jVar, Typeface typeface) {
        this.f762b = jVar;
        this.f761a = typeface;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f762b.a(this.f761a);
    }
}
