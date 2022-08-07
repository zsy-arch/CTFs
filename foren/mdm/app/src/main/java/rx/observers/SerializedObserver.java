package rx.observers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.internal.operators.NotificationLite;

/* loaded from: classes2.dex */
public class SerializedObserver<T> implements Observer<T> {
    private final Observer<? super T> actual;
    private boolean emitting;
    private FastList queue;
    private volatile boolean terminated;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class FastList {
        Object[] array;
        int size;

        FastList() {
        }

        public void add(Object o) {
            int s = this.size;
            Object[] a = this.array;
            if (a == null) {
                a = new Object[16];
                this.array = a;
            } else if (s == a.length) {
                Object[] array2 = new Object[(s >> 2) + s];
                System.arraycopy(a, 0, array2, 0, s);
                a = array2;
                this.array = a;
            }
            a[s] = o;
            this.size = s + 1;
        }
    }

    public SerializedObserver(Observer<? super T> s) {
        this.actual = s;
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0031, code lost:
        continue;
     */
    @Override // rx.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onNext(T r9) {
        /*
            r8 = this;
            r7 = 1
            boolean r6 = r8.terminated
            if (r6 == 0) goto L_0x0006
        L_0x0005:
            return
        L_0x0006:
            monitor-enter(r8)
            boolean r6 = r8.terminated     // Catch: all -> 0x000d
            if (r6 == 0) goto L_0x0010
            monitor-exit(r8)     // Catch: all -> 0x000d
            goto L_0x0005
        L_0x000d:
            r6 = move-exception
            monitor-exit(r8)     // Catch: all -> 0x000d
            throw r6
        L_0x0010:
            boolean r6 = r8.emitting     // Catch: all -> 0x000d
            if (r6 == 0) goto L_0x0028
            rx.observers.SerializedObserver$FastList r4 = r8.queue     // Catch: all -> 0x000d
            if (r4 != 0) goto L_0x001f
            rx.observers.SerializedObserver$FastList r4 = new rx.observers.SerializedObserver$FastList     // Catch: all -> 0x000d
            r4.<init>()     // Catch: all -> 0x000d
            r8.queue = r4     // Catch: all -> 0x000d
        L_0x001f:
            java.lang.Object r6 = rx.internal.operators.NotificationLite.next(r9)     // Catch: all -> 0x000d
            r4.add(r6)     // Catch: all -> 0x000d
            monitor-exit(r8)     // Catch: all -> 0x000d
            goto L_0x0005
        L_0x0028:
            r6 = 1
            r8.emitting = r6     // Catch: all -> 0x000d
            monitor-exit(r8)     // Catch: all -> 0x000d
            rx.Observer<? super T> r6 = r8.actual     // Catch: Throwable -> 0x003e
            r6.onNext(r9)     // Catch: Throwable -> 0x003e
        L_0x0031:
            monitor-enter(r8)
            rx.observers.SerializedObserver$FastList r4 = r8.queue     // Catch: all -> 0x003b
            if (r4 != 0) goto L_0x0047
            r6 = 0
            r8.emitting = r6     // Catch: all -> 0x003b
            monitor-exit(r8)     // Catch: all -> 0x003b
            goto L_0x0005
        L_0x003b:
            r6 = move-exception
            monitor-exit(r8)     // Catch: all -> 0x003b
            throw r6
        L_0x003e:
            r1 = move-exception
            r8.terminated = r7
            rx.Observer<? super T> r6 = r8.actual
            rx.exceptions.Exceptions.throwOrReport(r1, r6, r9)
            goto L_0x0005
        L_0x0047:
            r6 = 0
            r8.queue = r6     // Catch: all -> 0x003b
            monitor-exit(r8)     // Catch: all -> 0x003b
            java.lang.Object[] r0 = r4.array
            int r3 = r0.length
            r2 = 0
        L_0x004f:
            if (r2 >= r3) goto L_0x0031
            r5 = r0[r2]
            if (r5 == 0) goto L_0x0031
            rx.Observer<? super T> r6 = r8.actual     // Catch: Throwable -> 0x0061
            boolean r6 = rx.internal.operators.NotificationLite.accept(r6, r5)     // Catch: Throwable -> 0x0061
            if (r6 == 0) goto L_0x0071
            r6 = 1
            r8.terminated = r6     // Catch: Throwable -> 0x0061
            goto L_0x0005
        L_0x0061:
            r1 = move-exception
            r8.terminated = r7
            rx.exceptions.Exceptions.throwIfFatal(r1)
            rx.Observer<? super T> r6 = r8.actual
            java.lang.Throwable r7 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r9)
            r6.onError(r7)
            goto L_0x0005
        L_0x0071:
            int r2 = r2 + 1
            goto L_0x004f
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.observers.SerializedObserver.onNext(java.lang.Object):void");
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        Exceptions.throwIfFatal(e);
        if (!this.terminated) {
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    if (this.emitting) {
                        FastList list = this.queue;
                        if (list == null) {
                            list = new FastList();
                            this.queue = list;
                        }
                        list.add(NotificationLite.error(e));
                        return;
                    }
                    this.emitting = true;
                    this.actual.onError(e);
                }
            }
        }
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (!this.terminated) {
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    if (this.emitting) {
                        FastList list = this.queue;
                        if (list == null) {
                            list = new FastList();
                            this.queue = list;
                        }
                        list.add(NotificationLite.completed());
                        return;
                    }
                    this.emitting = true;
                    this.actual.onCompleted();
                }
            }
        }
    }
}
