package c.a.a;

import androidx.appcompat.app.AppCompatDelegateImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class q implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppCompatDelegateImpl f368a;

    public q(AppCompatDelegateImpl appCompatDelegateImpl) {
        this.f368a = appCompatDelegateImpl;
    }

    @Override // java.lang.Runnable
    public void run() {
        AppCompatDelegateImpl appCompatDelegateImpl = this.f368a;
        if ((appCompatDelegateImpl.P & 1) != 0) {
            appCompatDelegateImpl.c(0);
        }
        AppCompatDelegateImpl appCompatDelegateImpl2 = this.f368a;
        if ((appCompatDelegateImpl2.P & 4096) != 0) {
            appCompatDelegateImpl2.c(108);
        }
        AppCompatDelegateImpl appCompatDelegateImpl3 = this.f368a;
        appCompatDelegateImpl3.O = false;
        appCompatDelegateImpl3.P = 0;
    }
}
