package d.a.a.d;

import bccsejw.sxexrix.zaswnwt.widget.PromptView;
import d.a.a.c;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class k implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f1647a;

    public k(PromptView promptView, g gVar) {
        this.f1647a = gVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        g gVar = this.f1647a;
        ((c) gVar.h).a(gVar);
    }
}
