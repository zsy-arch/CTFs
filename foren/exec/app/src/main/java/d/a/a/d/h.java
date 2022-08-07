package d.a.a.d;

import android.view.animation.Animation;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h implements Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ j f1640a;

    public h(j jVar) {
        this.f1640a = jVar;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        j jVar = this.f1640a;
        jVar.f.removeView(jVar.f1646e);
        j jVar2 = this.f1640a;
        jVar2.i = false;
        jVar2.j = false;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
        this.f1640a.i = true;
    }
}
