package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observers.SerializedObserver;
import rx.observers.SerializedSubscriber;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.RefCountSubscription;

/* loaded from: classes2.dex */
public final class OnSubscribeGroupJoin<T1, T2, D1, D2, R> implements Observable.OnSubscribe<R> {
    final Observable<T1> left;
    final Func1<? super T1, ? extends Observable<D1>> leftDuration;
    final Func2<? super T1, ? super Observable<T2>, ? extends R> resultSelector;
    final Observable<T2> right;
    final Func1<? super T2, ? extends Observable<D2>> rightDuration;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeGroupJoin(Observable<T1> left, Observable<T2> right, Func1<? super T1, ? extends Observable<D1>> leftDuration, Func1<? super T2, ? extends Observable<D2>> rightDuration, Func2<? super T1, ? super Observable<T2>, ? extends R> resultSelector) {
        this.left = left;
        this.right = right;
        this.leftDuration = leftDuration;
        this.rightDuration = rightDuration;
        this.resultSelector = resultSelector;
    }

    public void call(Subscriber<? super R> child) {
        OnSubscribeGroupJoin<T1, T2, D1, D2, R>.ResultManager ro = new ResultManager(new SerializedSubscriber(child));
        child.add(ro);
        ro.init();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class ResultManager extends HashMap<Integer, Observer<T2>> implements Subscription {
        private static final long serialVersionUID = -3035156013812425335L;
        boolean leftDone;
        int leftIds;
        boolean rightDone;
        int rightIds;
        final Subscriber<? super R> subscriber;
        final Map<Integer, T2> rightMap = new HashMap();
        final CompositeSubscription group = new CompositeSubscription();
        final RefCountSubscription cancel = new RefCountSubscription(this.group);

        public ResultManager(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
        }

        public void init() {
            LeftObserver leftObserver = new LeftObserver();
            RightObserver rightObserver = new RightObserver();
            this.group.add(leftObserver);
            this.group.add(rightObserver);
            OnSubscribeGroupJoin.this.left.unsafeSubscribe(leftObserver);
            OnSubscribeGroupJoin.this.right.unsafeSubscribe(rightObserver);
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.cancel.unsubscribe();
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancel.isUnsubscribed();
        }

        Map<Integer, Observer<T2>> leftMap() {
            return this;
        }

        void errorAll(Throwable e) {
            List<Observer<T2>> list;
            synchronized (this) {
                list = new ArrayList<>(leftMap().values());
                leftMap().clear();
                this.rightMap.clear();
            }
            for (Observer<T2> o : list) {
                o.onError(e);
            }
            this.subscriber.onError(e);
            this.cancel.unsubscribe();
        }

        void errorMain(Throwable e) {
            synchronized (this) {
                leftMap().clear();
                this.rightMap.clear();
            }
            this.subscriber.onError(e);
            this.cancel.unsubscribe();
        }

        void complete(List<Observer<T2>> list) {
            if (list != null) {
                for (Observer<T2> o : list) {
                    o.onCompleted();
                }
                this.subscriber.onCompleted();
                this.cancel.unsubscribe();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public final class LeftObserver extends Subscriber<T1> {
            LeftObserver() {
            }

            @Override // rx.Observer
            public void onNext(T1 args) {
                int id;
                List<T2> rightMapValues;
                try {
                    Subject<T2, T2> subj = PublishSubject.create();
                    SerializedObserver serializedObserver = new SerializedObserver(subj);
                    synchronized (ResultManager.this) {
                        ResultManager resultManager = ResultManager.this;
                        id = resultManager.leftIds;
                        resultManager.leftIds = id + 1;
                        ResultManager.this.leftMap().put(Integer.valueOf(id), serializedObserver);
                    }
                    Observable<T2> window = Observable.unsafeCreate(new WindowObservableFunc(subj, ResultManager.this.cancel));
                    Subscriber<? super D1> leftDurationObserver = new LeftDurationObserver(id);
                    ResultManager.this.group.add(leftDurationObserver);
                    ((Observable) OnSubscribeGroupJoin.this.leftDuration.call(args)).unsafeSubscribe(leftDurationObserver);
                    Object call = OnSubscribeGroupJoin.this.resultSelector.call(args, window);
                    synchronized (ResultManager.this) {
                        rightMapValues = new ArrayList<>((Collection<? extends T2>) ResultManager.this.rightMap.values());
                    }
                    ResultManager.this.subscriber.onNext(call);
                    for (T2 t2 : rightMapValues) {
                        serializedObserver.onNext(t2);
                    }
                } catch (Throwable t) {
                    Exceptions.throwOrReport(t, this);
                }
            }

            @Override // rx.Observer
            public void onCompleted() {
                Throwable th;
                ArrayList arrayList = null;
                try {
                    synchronized (ResultManager.this) {
                        ResultManager.this.leftDone = true;
                        if (ResultManager.this.rightDone) {
                            ArrayList arrayList2 = new ArrayList(ResultManager.this.leftMap().values());
                            try {
                                ResultManager.this.leftMap().clear();
                                ResultManager.this.rightMap.clear();
                                arrayList = arrayList2;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                        ResultManager.this.complete(arrayList);
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                ResultManager.this.errorAll(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public final class RightObserver extends Subscriber<T2> {
            RightObserver() {
            }

            @Override // rx.Observer
            public void onNext(T2 args) {
                int id;
                List<Observer<T2>> list;
                try {
                    synchronized (ResultManager.this) {
                        ResultManager resultManager = ResultManager.this;
                        id = resultManager.rightIds;
                        resultManager.rightIds = id + 1;
                        ResultManager.this.rightMap.put(Integer.valueOf(id), args);
                    }
                    Subscriber<? super D2> rightDurationObserver = new RightDurationObserver(id);
                    ResultManager.this.group.add(rightDurationObserver);
                    ((Observable) OnSubscribeGroupJoin.this.rightDuration.call(args)).unsafeSubscribe(rightDurationObserver);
                    synchronized (ResultManager.this) {
                        list = new ArrayList<>(ResultManager.this.leftMap().values());
                    }
                    for (Observer<T2> o : list) {
                        o.onNext(args);
                    }
                } catch (Throwable t) {
                    Exceptions.throwOrReport(t, this);
                }
            }

            @Override // rx.Observer
            public void onCompleted() {
                Throwable th;
                ArrayList arrayList = null;
                try {
                    synchronized (ResultManager.this) {
                        ResultManager.this.rightDone = true;
                        if (ResultManager.this.leftDone) {
                            ArrayList arrayList2 = new ArrayList(ResultManager.this.leftMap().values());
                            try {
                                ResultManager.this.leftMap().clear();
                                ResultManager.this.rightMap.clear();
                                arrayList = arrayList2;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                        ResultManager.this.complete(arrayList);
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                ResultManager.this.errorAll(e);
            }
        }

        /* loaded from: classes2.dex */
        final class LeftDurationObserver extends Subscriber<D1> {
            final int id;
            boolean once = true;

            public LeftDurationObserver(int id) {
                this.id = id;
            }

            @Override // rx.Observer
            public void onCompleted() {
                Observer<T2> gr;
                if (this.once) {
                    this.once = false;
                    synchronized (ResultManager.this) {
                        gr = ResultManager.this.leftMap().remove(Integer.valueOf(this.id));
                    }
                    if (gr != null) {
                        gr.onCompleted();
                    }
                    ResultManager.this.group.remove(this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                ResultManager.this.errorMain(e);
            }

            @Override // rx.Observer
            public void onNext(D1 args) {
                onCompleted();
            }
        }

        /* loaded from: classes2.dex */
        final class RightDurationObserver extends Subscriber<D2> {
            final int id;
            boolean once = true;

            public RightDurationObserver(int id) {
                this.id = id;
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (this.once) {
                    this.once = false;
                    synchronized (ResultManager.this) {
                        ResultManager.this.rightMap.remove(Integer.valueOf(this.id));
                    }
                    ResultManager.this.group.remove(this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                ResultManager.this.errorMain(e);
            }

            @Override // rx.Observer
            public void onNext(D2 args) {
                onCompleted();
            }
        }
    }

    /* loaded from: classes2.dex */
    static final class WindowObservableFunc<T> implements Observable.OnSubscribe<T> {
        final RefCountSubscription refCount;
        final Observable<T> underlying;

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object x0) {
            call((Subscriber) ((Subscriber) x0));
        }

        public WindowObservableFunc(Observable<T> underlying, RefCountSubscription refCount) {
            this.refCount = refCount;
            this.underlying = underlying;
        }

        public void call(Subscriber<? super T> t1) {
            Subscription ref = this.refCount.get();
            WindowObservableFunc<T>.WindowSubscriber wo = new WindowSubscriber(t1, ref);
            wo.add(ref);
            this.underlying.unsafeSubscribe(wo);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public final class WindowSubscriber extends Subscriber<T> {
            private final Subscription ref;
            final Subscriber<? super T> subscriber;

            public WindowSubscriber(Subscriber<? super T> subscriber, Subscription ref) {
                super(subscriber);
                this.subscriber = subscriber;
                this.ref = ref;
            }

            @Override // rx.Observer
            public void onNext(T args) {
                this.subscriber.onNext(args);
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                this.subscriber.onError(e);
                this.ref.unsubscribe();
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.subscriber.onCompleted();
                this.ref.unsubscribe();
            }
        }
    }
}
