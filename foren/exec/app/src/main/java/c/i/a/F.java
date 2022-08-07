package c.i.a;

import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

/* loaded from: classes.dex */
class F implements Transition.TransitionListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Object f930a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ArrayList f931b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f932c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ ArrayList f933d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Object f934e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ H g;

    public F(H h, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.g = h;
        this.f930a = obj;
        this.f931b = arrayList;
        this.f932c = obj2;
        this.f933d = arrayList2;
        this.f934e = obj3;
        this.f = arrayList3;
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionEnd(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionResume(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionStart(Transition transition) {
        Object obj = this.f930a;
        if (obj != null) {
            this.g.a(obj, this.f931b, (ArrayList<View>) null);
        }
        Object obj2 = this.f932c;
        if (obj2 != null) {
            this.g.a(obj2, this.f933d, (ArrayList<View>) null);
        }
        Object obj3 = this.f934e;
        if (obj3 != null) {
            this.g.a(obj3, this.f, (ArrayList<View>) null);
        }
    }
}
