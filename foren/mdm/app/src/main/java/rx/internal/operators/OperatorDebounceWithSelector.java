package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.operators.OperatorDebounceWithTime;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes2.dex */
public final class OperatorDebounceWithSelector<T, U> implements Observable.Operator<T, T> {
    final Func1<? super T, ? extends Observable<U>> selector;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorDebounceWithSelector(Func1<? super T, ? extends Observable<U>> selector) {
        this.selector = selector;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        SerializedSubscriber<T> s = new SerializedSubscriber<>(child);
        SerialSubscription serial = new SerialSubscription();
        child.add(serial);
        return new AnonymousClass1(child, s, serial);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorDebounceWithSelector$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends Subscriber<T> {
        final /* synthetic */ SerializedSubscriber val$s;
        final /* synthetic */ SerialSubscription val$serial;
        final OperatorDebounceWithTime.DebounceState<T> state = new OperatorDebounceWithTime.DebounceState<>();
        final Subscriber<?> self = this;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Subscriber subscriber, SerializedSubscriber serializedSubscriber, SerialSubscription serialSubscription) {
            super(subscriber);
            this.val$s = serializedSubscriber;
            this.val$serial = serialSubscription;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Observable<U> debouncer = (Observable) OperatorDebounceWithSelector.this.selector.call(t);
                final int index = this.state.next(t);
                Subscriber<U> debounceSubscriber = new Subscriber<U>() { // from class: rx.internal.operators.OperatorDebounceWithSelector.1.1
                    @Override // rx.Observer
                    public void onNext(U t2) {
                        onCompleted();
                    }

                    @Override // rx.Observer
                    public void onError(Throwable e) {
                        AnonymousClass1.this.self.onError(e);
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        AnonymousClass1.this.state.emit(index, AnonymousClass1.this.val$s, AnonymousClass1.this.self);
                        unsubscribe();
                    }
                };
                this.val$serial.set(debounceSubscriber);
                debouncer.unsafeSubscribe(debounceSubscriber);
            } catch (Throwable e) {
                Exceptions.throwOrReport(e, this);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.val$s.onError(e);
            unsubscribe();
            this.state.clear();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.state.emitAndComplete(this.val$s, this);
        }
    }
}
