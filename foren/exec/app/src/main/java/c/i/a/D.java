package c.i.a;

import android.graphics.Rect;
import android.transition.Transition;

/* loaded from: classes.dex */
class D extends Transition.EpicenterCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Rect f927a;

    public D(H h, Rect rect) {
        this.f927a = rect;
    }

    @Override // android.transition.Transition.EpicenterCallback
    public Rect onGetEpicenter(Transition transition) {
        return this.f927a;
    }
}
