package rx;

import rx.exceptions.MissingBackpressureException;

/* loaded from: classes2.dex */
public final class BackpressureOverflow {
    public static final Strategy ON_OVERFLOW_ERROR = Error.INSTANCE;
    public static final Strategy ON_OVERFLOW_DEFAULT = ON_OVERFLOW_ERROR;
    public static final Strategy ON_OVERFLOW_DROP_OLDEST = DropOldest.INSTANCE;
    public static final Strategy ON_OVERFLOW_DROP_LATEST = DropLatest.INSTANCE;

    /* loaded from: classes2.dex */
    public interface Strategy {
        boolean mayAttemptDrop() throws MissingBackpressureException;
    }

    private BackpressureOverflow() {
        throw new IllegalStateException("No instances!");
    }

    /* loaded from: classes2.dex */
    static final class DropOldest implements Strategy {
        static final DropOldest INSTANCE = new DropOldest();

        private DropOldest() {
        }

        @Override // rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() {
            return true;
        }
    }

    /* loaded from: classes2.dex */
    static final class DropLatest implements Strategy {
        static final DropLatest INSTANCE = new DropLatest();

        private DropLatest() {
        }

        @Override // rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() {
            return false;
        }
    }

    /* loaded from: classes2.dex */
    static final class Error implements Strategy {
        static final Error INSTANCE = new Error();

        private Error() {
        }

        @Override // rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() throws MissingBackpressureException {
            throw new MissingBackpressureException("Overflowed buffer");
        }
    }
}
