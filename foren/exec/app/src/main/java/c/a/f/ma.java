package c.a.f;

import androidx.appcompat.widget.Toolbar;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ma implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Toolbar f608a;

    public ma(Toolbar toolbar) {
        this.f608a = toolbar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f608a.o();
    }
}
