package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;
import rx.internal.producers.SingleProducer;
import rx.subjects.SubjectSubscriptionManager;

/* loaded from: classes2.dex */
public final class AsyncSubject<T> extends Subject<T, T> {
    volatile Object lastValue;
    final SubjectSubscriptionManager<T> state;

    public static <T> AsyncSubject<T> create() {
        final SubjectSubscriptionManager<T> state = new SubjectSubscriptionManager<>();
        state.onTerminated = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() { // from class: rx.subjects.AsyncSubject.1
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object x0) {
                call((SubjectSubscriptionManager.SubjectObserver) ((SubjectSubscriptionManager.SubjectObserver) x0));
            }

            public void call(SubjectSubscriptionManager.SubjectObserver<T> o) {
                Object v = SubjectSubscriptionManager.this.getLatest();
                if (v == null || NotificationLite.isCompleted(v)) {
                    o.onCompleted();
                } else if (NotificationLite.isError(v)) {
                    o.onError(NotificationLite.getError(v));
                } else {
                    o.actual.setProducer(new SingleProducer(o.actual, NotificationLite.getValue(v)));
                }
            }
        };
        return new AsyncSubject<>(state, state);
    }

    protected AsyncSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> state) {
        super(onSubscribe);
        this.state = state;
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.state.active) {
            Object last = this.lastValue;
            if (last == null) {
                last = NotificationLite.completed();
            }
            SubjectSubscriptionManager.SubjectObserver<T>[] arr$ = this.state.terminate(last);
            for (SubjectSubscriptionManager.SubjectObserver<T> bo : arr$) {
                if (last == NotificationLite.completed()) {
                    bo.onCompleted();
                } else {
                    bo.actual.setProducer(new SingleProducer(bo.actual, NotificationLite.getValue(last)));
                }
            }
        }
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        if (this.state.active) {
            List<Throwable> errors = null;
            for (SubjectSubscriptionManager.SubjectObserver<T> bo : this.state.terminate(NotificationLite.error(e))) {
                try {
                    bo.onError(e);
                } catch (Throwable e2) {
                    if (errors == null) {
                        errors = new ArrayList<>();
                    }
                    errors.add(e2);
                }
            }
            Exceptions.throwIfAny(errors);
        }
    }

    @Override // rx.Observer
    public void onNext(T v) {
        this.lastValue = NotificationLite.next(v);
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    public boolean hasValue() {
        return !NotificationLite.isError(this.state.getLatest()) && NotificationLite.isNext(this.lastValue);
    }

    public boolean hasThrowable() {
        return NotificationLite.isError(this.state.getLatest());
    }

    public boolean hasCompleted() {
        Object o = this.state.getLatest();
        return o != null && !NotificationLite.isError(o);
    }

    public T getValue() {
        Object v = this.lastValue;
        if (NotificationLite.isError(this.state.getLatest()) || !NotificationLite.isNext(v)) {
            return null;
        }
        return (T) NotificationLite.getValue(v);
    }

    public Throwable getThrowable() {
        Object o = this.state.getLatest();
        if (NotificationLite.isError(o)) {
            return NotificationLite.getError(o);
        }
        return null;
    }
}
