package rx.internal.operators;

import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;

/* loaded from: classes2.dex */
public final class OnSubscribeToMap<T, K, V> implements Observable.OnSubscribe<Map<K, V>>, Func0<Map<K, V>> {
    final Func1<? super T, ? extends K> keySelector;
    final Func0<? extends Map<K, V>> mapFactory;
    final Observable<T> source;
    final Func1<? super T, ? extends V> valueSelector;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeToMap(Observable<T> source, Func1<? super T, ? extends K> keySelector, Func1<? super T, ? extends V> valueSelector) {
        this(source, keySelector, valueSelector, null);
    }

    public OnSubscribeToMap(Observable<T> source, Func1<? super T, ? extends K> keySelector, Func1<? super T, ? extends V> valueSelector, Func0<? extends Map<K, V>> mapFactory) {
        this.source = source;
        this.keySelector = keySelector;
        this.valueSelector = valueSelector;
        if (mapFactory == null) {
            this.mapFactory = this;
        } else {
            this.mapFactory = mapFactory;
        }
    }

    @Override // rx.functions.Func0, java.util.concurrent.Callable
    public Map<K, V> call() {
        return new HashMap();
    }

    public void call(Subscriber<? super Map<K, V>> subscriber) {
        try {
            new ToMapSubscriber(subscriber, (Map) this.mapFactory.call(), this.keySelector, this.valueSelector).subscribeTo(this.source);
        } catch (Throwable ex) {
            Exceptions.throwOrReport(ex, subscriber);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class ToMapSubscriber<T, K, V> extends DeferredScalarSubscriberSafe<T, Map<K, V>> {
        final Func1<? super T, ? extends K> keySelector;
        final Func1<? super T, ? extends V> valueSelector;

        ToMapSubscriber(Subscriber<? super Map<K, V>> actual, Map<K, V> map, Func1<? super T, ? extends K> keySelector, Func1<? super T, ? extends V> valueSelector) {
            super(actual);
            this.value = map;
            this.hasValue = true;
            this.keySelector = keySelector;
            this.valueSelector = valueSelector;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.done) {
                try {
                    ((Map) this.value).put(this.keySelector.call(t), this.valueSelector.call(t));
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    unsubscribe();
                    onError(ex);
                }
            }
        }
    }
}
