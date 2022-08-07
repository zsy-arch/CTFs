package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;

/* loaded from: classes2.dex */
public class OnSubscribeSingle<T> implements Single.OnSubscribe<T> {
    private final Observable<T> observable;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((SingleSubscriber) ((SingleSubscriber) x0));
    }

    public OnSubscribeSingle(Observable<T> observable) {
        this.observable = observable;
    }

    public void call(final SingleSubscriber<? super T> child) {
        Subscriber<T> parent = new Subscriber<T>() { // from class: rx.internal.operators.OnSubscribeSingle.1
            private T emission;
            private boolean emittedTooMany;
            private boolean itemEmitted;

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void onStart() {
                request(2L);
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (!this.emittedTooMany) {
                    if (this.itemEmitted) {
                        child.onSuccess(this.emission);
                    } else {
                        child.onError(new NoSuchElementException("Observable emitted no items"));
                    }
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                child.onError(e);
                unsubscribe();
            }

            @Override // rx.Observer
            public void onNext(T t) {
                if (this.itemEmitted) {
                    this.emittedTooMany = true;
                    child.onError(new IllegalArgumentException("Observable emitted too many elements"));
                    unsubscribe();
                    return;
                }
                this.itemEmitted = true;
                this.emission = t;
            }
        };
        child.add(parent);
        this.observable.unsafeSubscribe(parent);
    }

    public static <T> OnSubscribeSingle<T> create(Observable<T> observable) {
        return new OnSubscribeSingle<>(observable);
    }
}
