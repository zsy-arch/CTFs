package rx.android.schedulers;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.annotations.Experimental;

/* loaded from: classes2.dex */
public final class AndroidSchedulers {
    private static final AtomicReference<AndroidSchedulers> INSTANCE = new AtomicReference<>();
    private final Scheduler mainThreadScheduler;

    private static AndroidSchedulers getInstance() {
        AndroidSchedulers current;
        do {
            current = INSTANCE.get();
            if (current != null) {
                break;
            }
            current = new AndroidSchedulers();
        } while (!INSTANCE.compareAndSet(null, current));
        return current;
    }

    private AndroidSchedulers() {
        Scheduler main = RxAndroidPlugins.getInstance().getSchedulersHook().getMainThreadScheduler();
        if (main != null) {
            this.mainThreadScheduler = main;
        } else {
            this.mainThreadScheduler = new LooperScheduler(Looper.getMainLooper());
        }
    }

    public static Scheduler mainThread() {
        return getInstance().mainThreadScheduler;
    }

    public static Scheduler from(Looper looper) {
        if (looper != null) {
            return new LooperScheduler(looper);
        }
        throw new NullPointerException("looper == null");
    }

    @Experimental
    public static void reset() {
        INSTANCE.set(null);
    }
}
