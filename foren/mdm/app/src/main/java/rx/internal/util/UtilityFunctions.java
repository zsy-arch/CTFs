package rx.internal.util;

import rx.functions.Func1;

/* loaded from: classes2.dex */
public final class UtilityFunctions {
    private UtilityFunctions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    public static <T> Func1<? super T, Boolean> alwaysFalse() {
        return AlwaysFalse.INSTANCE;
    }

    public static <T> Func1<T, T> identity() {
        return Identity.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        public Boolean call(Object o) {
            return true;
        }
    }

    /* loaded from: classes2.dex */
    enum AlwaysFalse implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        public Boolean call(Object o) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum Identity implements Func1<Object, Object> {
        INSTANCE;

        @Override // rx.functions.Func1
        public Object call(Object o) {
            return o;
        }
    }
}
