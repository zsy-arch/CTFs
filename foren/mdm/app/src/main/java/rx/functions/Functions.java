package rx.functions;

/* loaded from: classes2.dex */
public final class Functions {
    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <R> FuncN<R> fromFunc(final Func0<? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.1
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 0) {
                    return Func0.this.call();
                }
                throw new IllegalArgumentException("Func0 expecting 0 arguments.");
            }
        };
    }

    public static <T0, R> FuncN<R> fromFunc(final Func1<? super T0, ? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.2
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 1) {
                    return Func1.this.call(args[0]);
                }
                throw new IllegalArgumentException("Func1 expecting 1 argument.");
            }
        };
    }

    public static <T0, T1, R> FuncN<R> fromFunc(final Func2<? super T0, ? super T1, ? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.3
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 2) {
                    return Func2.this.call(args[0], args[1]);
                }
                throw new IllegalArgumentException("Func2 expecting 2 arguments.");
            }
        };
    }

    public static <T0, T1, T2, R> FuncN<R> fromFunc(final Func3<? super T0, ? super T1, ? super T2, ? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.4
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 3) {
                    return Func3.this.call(args[0], args[1], args[2]);
                }
                throw new IllegalArgumentException("Func3 expecting 3 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, R> FuncN<R> fromFunc(final Func4<? super T0, ? super T1, ? super T2, ? super T3, ? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.5
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 4) {
                    return Func4.this.call(args[0], args[1], args[2], args[3]);
                }
                throw new IllegalArgumentException("Func4 expecting 4 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, R> FuncN<R> fromFunc(final Func5<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.6
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 5) {
                    return Func5.this.call(args[0], args[1], args[2], args[3], args[4]);
                }
                throw new IllegalArgumentException("Func5 expecting 5 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, T5, R> FuncN<R> fromFunc(final Func6<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.7
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 6) {
                    return Func6.this.call(args[0], args[1], args[2], args[3], args[4], args[5]);
                }
                throw new IllegalArgumentException("Func6 expecting 6 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, R> FuncN<R> fromFunc(final Func7<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.8
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 7) {
                    return Func7.this.call(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
                }
                throw new IllegalArgumentException("Func7 expecting 7 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, R> FuncN<R> fromFunc(final Func8<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.9
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 8) {
                    return Func8.this.call(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
                }
                throw new IllegalArgumentException("Func8 expecting 8 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, R> FuncN<R> fromFunc(final Func9<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> f) {
        return new FuncN<R>() { // from class: rx.functions.Functions.10
            /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
            @Override // rx.functions.FuncN
            public R call(Object... args) {
                if (args.length == 9) {
                    return Func9.this.call(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
                }
                throw new IllegalArgumentException("Func9 expecting 9 arguments.");
            }
        };
    }

    public static FuncN<Void> fromAction(final Action0 f) {
        return new FuncN<Void>() { // from class: rx.functions.Functions.11
            @Override // rx.functions.FuncN
            public Void call(Object... args) {
                if (args.length != 0) {
                    throw new IllegalArgumentException("Action0 expecting 0 arguments.");
                }
                Action0.this.call();
                return null;
            }
        };
    }

    public static <T0> FuncN<Void> fromAction(final Action1<? super T0> f) {
        return new FuncN<Void>() { // from class: rx.functions.Functions.12
            @Override // rx.functions.FuncN
            public Void call(Object... args) {
                if (args.length != 1) {
                    throw new IllegalArgumentException("Action1 expecting 1 argument.");
                }
                Action1.this.call(args[0]);
                return null;
            }
        };
    }

    public static <T0, T1> FuncN<Void> fromAction(final Action2<? super T0, ? super T1> f) {
        return new FuncN<Void>() { // from class: rx.functions.Functions.13
            @Override // rx.functions.FuncN
            public Void call(Object... args) {
                if (args.length != 2) {
                    throw new IllegalArgumentException("Action3 expecting 2 arguments.");
                }
                Action2.this.call(args[0], args[1]);
                return null;
            }
        };
    }

    public static <T0, T1, T2> FuncN<Void> fromAction(final Action3<? super T0, ? super T1, ? super T2> f) {
        return new FuncN<Void>() { // from class: rx.functions.Functions.14
            @Override // rx.functions.FuncN
            public Void call(Object... args) {
                if (args.length != 3) {
                    throw new IllegalArgumentException("Action3 expecting 3 arguments.");
                }
                Action3.this.call(args[0], args[1], args[2]);
                return null;
            }
        };
    }
}
