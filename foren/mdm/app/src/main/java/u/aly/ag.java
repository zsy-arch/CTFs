package u.aly;

import com.umeng.analytics.AnalyticsConfig;
import java.lang.Thread;

/* compiled from: CrashHandler.java */
/* loaded from: classes2.dex */
public class ag implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler a;
    private ao b;

    public ag() {
        if (Thread.getDefaultUncaughtExceptionHandler() != this) {
            this.a = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void a(ao aoVar) {
        this.b = aoVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        a(th);
        if (this.a != null && this.a != Thread.getDefaultUncaughtExceptionHandler()) {
            this.a.uncaughtException(thread, th);
        }
    }

    private void a(Throwable th) {
        if (AnalyticsConfig.CATCH_EXCEPTION) {
            this.b.a(th);
        } else {
            this.b.a(null);
        }
    }
}
