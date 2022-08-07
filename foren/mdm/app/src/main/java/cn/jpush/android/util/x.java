package cn.jpush.android.util;

/* loaded from: classes.dex */
final class x implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ JRecorder b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(JRecorder jRecorder, int i) {
        this.b = jRecorder;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JRecorder.a(this.b, this.a);
    }
}
