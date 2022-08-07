package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;

/* loaded from: classes2.dex */
public final class OnSubscribeToMultimap<T, K, V> implements Observable.OnSubscribe<Map<K, Collection<V>>>, Func0<Map<K, Collection<V>>> {
    private final Func1<? super K, ? extends Collection<V>> collectionFactory;
    private final Func1<? super T, ? extends K> keySelector;
    private final Func0<? extends Map<K, Collection<V>>> mapFactory;
    private final Observable<T> source;
    private final Func1<? super T, ? extends V> valueSelector;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeToMultimap(Observable<T> source, Func1<? super T, ? extends K> keySelector, Func1<? super T, ? extends V> valueSelector) {
        this(source, keySelector, valueSelector, null, DefaultMultimapCollectionFactory.instance());
    }

    public OnSubscribeToMultimap(Observable<T> source, Func1<? super T, ? extends K> keySelector, Func1<? super T, ? extends V> valueSelector, Func0<? extends Map<K, Collection<V>>> mapFactory) {
        this(source, keySelector, valueSelector, mapFactory, DefaultMultimapCollectionFactory.instance());
    }

    public OnSubscribeToMultimap(Observable<T> source, Func1<? super T, ? extends K> keySelector, Func1<? super T, ? extends V> valueSelector, Func0<? extends Map<K, Collection<V>>> mapFactory, Func1<? super K, ? extends Collection<V>> collectionFactory) {
        this.source = source;
        this.keySelector = keySelector;
        this.valueSelector = valueSelector;
        if (mapFactory == null) {
            this.mapFactory = this;
        } else {
            this.mapFactory = mapFactory;
        }
        this.collectionFactory = collectionFactory;
    }

    @Override // rx.functions.Func0, java.util.concurrent.Callable
    public Map<K, Collection<V>> call() {
        return new HashMap();
    }

    public void call(Subscriber<? super Map<K, Collection<V>>> subscriber) {
        try {
            new ToMultimapSubscriber(subscriber, (Map) this.mapFactory.call(), this.keySelector, this.valueSelector, this.collectionFactory).subscribeTo(this.source);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            subscriber.onError(ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ToMultimapSubscriber<T, K, V> extends DeferredScalarSubscriberSafe<T, Map<K, Collection<V>>> {
        private final Func1<? super K, ? extends Collection<V>> collectionFactory;
        private final Func1<? super T, ? extends K> keySelector;
        private final Func1<? super T, ? extends V> valueSelector;

        ToMultimapSubscriber(Subscriber<? super Map<K, Collection<V>>> subscriber, Map<K, Collection<V>> map, Func1<? super T, ? extends K> keySelector, Func1<? super T, ? extends V> valueSelector, Func1<? super K, ? extends Collection<V>> collectionFactory) {
            super(subscriber);
            this.value = map;
            this.hasValue = true;
            this.keySelector = keySelector;
            this.valueSelector = valueSelector;
            this.collectionFactory = collectionFactory;
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
                    Object call = this.keySelector.call(t);
                    Object call2 = this.valueSelector.call(t);
                    Collection collection = (Collection) ((Map) this.value).get(call);
                    if (collection == null) {
                        collection = (Collection) this.collectionFactory.call(call);
                        ((Map) this.value).put(call, collection);
                    }
                    collection.add(call2);
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    unsubscribe();
                    onError(ex);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    private static final class DefaultMultimapCollectionFactory<K, V> implements Func1<K, Collection<V>> {
        private static final DefaultMultimapCollectionFactory<Object, Object> INSTANCE = new DefaultMultimapCollectionFactory<>();

        private DefaultMultimapCollectionFactory() {
        }

        static <K, V> DefaultMultimapCollectionFactory<K, V> instance() {
            return (DefaultMultimapCollectionFactory<K, V>) INSTANCE;
        }

        @Override // rx.functions.Func1
        public Collection<V> call(K t1) {
            return new ArrayList();
        }
    }
}
