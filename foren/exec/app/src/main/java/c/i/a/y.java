package c.i.a;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class y implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ArrayList f1011a;

    public y(ArrayList arrayList) {
        this.f1011a = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        C.a(this.f1011a, 4);
    }
}
