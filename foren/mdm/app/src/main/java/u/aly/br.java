package u.aly;

/* compiled from: SafeRunnable.java */
/* loaded from: classes2.dex */
public abstract class br implements Runnable {
    public abstract void a();

    @Override // java.lang.Runnable
    public void run() {
        try {
            a();
        } catch (Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }
}
