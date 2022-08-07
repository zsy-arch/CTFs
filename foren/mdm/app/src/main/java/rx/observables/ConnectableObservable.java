package rx.observables;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.OnSubscribeAutoConnect;
import rx.internal.operators.OnSubscribeRefCount;

/* loaded from: classes2.dex */
public abstract class ConnectableObservable<T> extends Observable<T> {
    public abstract void connect(Action1<? super Subscription> action1);

    public ConnectableObservable(Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
    }

    public final Subscription connect() {
        final Subscription[] out = new Subscription[1];
        connect(new Action1<Subscription>() { // from class: rx.observables.ConnectableObservable.1
            public void call(Subscription t1) {
                out[0] = t1;
            }
        });
        return out[0];
    }

    public Observable<T> refCount() {
        return unsafeCreate(new OnSubscribeRefCount(this));
    }

    public Observable<T> autoConnect() {
        return autoConnect(1);
    }

    public Observable<T> autoConnect(int numberOfSubscribers) {
        return autoConnect(numberOfSubscribers, Actions.empty());
    }

    public Observable<T> autoConnect(int numberOfSubscribers, Action1<? super Subscription> connection) {
        if (numberOfSubscribers > 0) {
            return unsafeCreate(new OnSubscribeAutoConnect(this, numberOfSubscribers, connection));
        }
        connect(connection);
        return this;
    }
}
