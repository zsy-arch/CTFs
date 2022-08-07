package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observers.Subscribers;

/* loaded from: classes2.dex */
public final class OnSubscribeUsing<T, Resource> implements Observable.OnSubscribe<T> {
    private final Action1<? super Resource> dispose;
    private final boolean disposeEagerly;
    private final Func1<? super Resource, ? extends Observable<? extends T>> observableFactory;
    private final Func0<Resource> resourceFactory;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeUsing(Func0<Resource> resourceFactory, Func1<? super Resource, ? extends Observable<? extends T>> observableFactory, Action1<? super Resource> dispose, boolean disposeEagerly) {
        this.resourceFactory = resourceFactory;
        this.observableFactory = observableFactory;
        this.dispose = dispose;
        this.disposeEagerly = disposeEagerly;
    }

    public void call(Subscriber<? super T> subscriber) {
        Observable<? extends T> observable;
        try {
            Resource resource = this.resourceFactory.call();
            DisposeAction<Resource> disposeOnceOnly = new DisposeAction<>(this.dispose, resource);
            subscriber.add(disposeOnceOnly);
            Observable<? extends T> source = (Observable) this.observableFactory.call(resource);
            if (this.disposeEagerly) {
                observable = source.doOnTerminate(disposeOnceOnly);
            } else {
                observable = source.doAfterTerminate(disposeOnceOnly);
            }
            observable.unsafeSubscribe(Subscribers.wrap(subscriber));
        } catch (Throwable e) {
            Exceptions.throwOrReport(e, subscriber);
        }
    }

    private Throwable dispose(Action0 disposeOnceOnly) {
        try {
            disposeOnceOnly.call();
            return null;
        } catch (Throwable th) {
            return th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class DisposeAction<Resource> extends AtomicBoolean implements Action0, Subscription {
        private static final long serialVersionUID = 4262875056400218316L;
        private Action1<? super Resource> dispose;
        private Resource resource;

        DisposeAction(Action1<? super Resource> dispose, Resource resource) {
            this.dispose = dispose;
            this.resource = resource;
            lazySet(false);
        }

        /* JADX WARN: Type inference failed for: r2v0, types: [Resource, rx.functions.Action1<? super Resource>] */
        /* JADX WARN: Unknown variable types count: 1 */
        @Override // rx.functions.Action0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void call() {
            /*
                r3 = this;
                r2 = 0
                r0 = 0
                r1 = 1
                boolean r0 = r3.compareAndSet(r0, r1)
                if (r0 == 0) goto L_0x0014
                rx.functions.Action1<? super Resource> r0 = r3.dispose     // Catch: all -> 0x0015
                Resource r1 = r3.resource     // Catch: all -> 0x0015
                r0.call(r1)     // Catch: all -> 0x0015
                r3.resource = r2
                r3.dispose = r2
            L_0x0014:
                return
            L_0x0015:
                r0 = move-exception
                r3.resource = r2
                r3.dispose = r2
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeUsing.DisposeAction.call():void");
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            call();
        }
    }
}
