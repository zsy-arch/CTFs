package c.i.a;

import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

/* loaded from: classes.dex */
class E implements Transition.TransitionListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View f928a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ArrayList f929b;

    public E(H h, View view, ArrayList arrayList) {
        this.f928a = view;
        this.f929b = arrayList;
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
        this.f928a.setVisibility(8);
        int size = this.f929b.size();
        for (int i = 0; i < size; i++) {
            ((View) this.f929b.get(i)).setVisibility(0);
        }
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionResume(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public void onTransitionStart(Transition transition) {
    }
}
