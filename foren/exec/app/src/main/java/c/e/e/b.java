package c.e.e;

import android.content.Context;
import android.graphics.Typeface;
import c.e.e.f;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b implements Callable<f.c> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f792a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ a f793b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f794c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f795d;

    public b(Context context, a aVar, int i, String str) {
        this.f792a = context;
        this.f793b = aVar;
        this.f794c = i;
        this.f795d = str;
    }

    @Override // java.util.concurrent.Callable
    public f.c call() {
        f.c a2 = f.a(this.f792a, this.f793b, this.f794c);
        Typeface typeface = a2.f811a;
        if (typeface != null) {
            f.f799a.a(this.f795d, typeface);
        }
        return a2;
    }
}
