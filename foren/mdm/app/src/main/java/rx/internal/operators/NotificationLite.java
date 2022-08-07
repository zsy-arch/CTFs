package rx.internal.operators;

import java.io.Serializable;
import rx.Observer;

/* loaded from: classes2.dex */
public final class NotificationLite {
    private static final Object ON_COMPLETED_SENTINEL = new Serializable() { // from class: rx.internal.operators.NotificationLite.1
        private static final long serialVersionUID = 1;

        public String toString() {
            return "Notification=>Completed";
        }
    };
    private static final Object ON_NEXT_NULL_SENTINEL = new Serializable() { // from class: rx.internal.operators.NotificationLite.2
        private static final long serialVersionUID = 2;

        public String toString() {
            return "Notification=>NULL";
        }
    };

    private NotificationLite() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class OnErrorSentinel implements Serializable {
        private static final long serialVersionUID = 3;
        final Throwable e;

        public OnErrorSentinel(Throwable e) {
            this.e = e;
        }

        public String toString() {
            return "Notification=>Error:" + this.e;
        }
    }

    public static <T> Object next(T t) {
        if (t == null) {
            return ON_NEXT_NULL_SENTINEL;
        }
        return t;
    }

    public static Object completed() {
        return ON_COMPLETED_SENTINEL;
    }

    public static Object error(Throwable e) {
        return new OnErrorSentinel(e);
    }

    public static <T> boolean accept(Observer<? super T> o, Object n) {
        if (n == ON_COMPLETED_SENTINEL) {
            o.onCompleted();
            return true;
        } else if (n == ON_NEXT_NULL_SENTINEL) {
            o.onNext(null);
            return false;
        } else if (n == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (n.getClass() == OnErrorSentinel.class) {
            o.onError(((OnErrorSentinel) n).e);
            return true;
        } else {
            o.onNext(n);
            return false;
        }
    }

    public static boolean isCompleted(Object n) {
        return n == ON_COMPLETED_SENTINEL;
    }

    public static boolean isError(Object n) {
        return n instanceof OnErrorSentinel;
    }

    public static boolean isNull(Object n) {
        return n == ON_NEXT_NULL_SENTINEL;
    }

    public static boolean isNext(Object n) {
        return n != null && !isError(n) && !isCompleted(n);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T getValue(Object n) {
        if (n == ON_NEXT_NULL_SENTINEL) {
            return null;
        }
        return n;
    }

    public static Throwable getError(Object n) {
        return ((OnErrorSentinel) n).e;
    }
}
