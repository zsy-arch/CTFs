package c.i.a;

import android.graphics.Rect;
import android.transition.Transition;

/* loaded from: classes.dex */
class G extends Transition.EpicenterCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Rect f935a;

    public G(H h, Rect rect) {
        this.f935a = rect;
    }

    @Override // android.transition.Transition.EpicenterCallback
    public Rect onGetEpicenter(Transition transition) {
        Rect rect = this.f935a;
        if (rect == null || rect.isEmpty()) {
            return null;
        }
        return this.f935a;
    }
}
