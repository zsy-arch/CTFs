package rx.internal.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes2.dex */
public final class OnSubscribeJoin<TLeft, TRight, TLeftDuration, TRightDuration, R> implements Observable.OnSubscribe<R> {
    final Observable<TLeft> left;
    final Func1<TLeft, Observable<TLeftDuration>> leftDurationSelector;
    final Func2<TLeft, TRight, R> resultSelector;
    final Observable<TRight> right;
    final Func1<TRight, Observable<TRightDuration>> rightDurationSelector;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeJoin(Observable<TLeft> left, Observable<TRight> right, Func1<TLeft, Observable<TLeftDuration>> leftDurationSelector, Func1<TRight, Observable<TRightDuration>> rightDurationSelector, Func2<TLeft, TRight, R> resultSelector) {
        this.left = left;
        this.right = right;
        this.leftDurationSelector = leftDurationSelector;
        this.rightDurationSelector = rightDurationSelector;
        this.resultSelector = resultSelector;
    }

    public void call(Subscriber<? super R> t1) {
        new ResultSink(new SerializedSubscriber(t1)).run();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class ResultSink extends HashMap<Integer, TLeft> {
        private static final long serialVersionUID = 3491669543549085380L;
        boolean leftDone;
        int leftId;
        boolean rightDone;
        int rightId;
        final Subscriber<? super R> subscriber;
        final CompositeSubscription group = new CompositeSubscription();
        final Map<Integer, TRight> rightMap = new HashMap();

        public ResultSink(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
        }

        HashMap<Integer, TLeft> leftMap() {
            return this;
        }

        public void run() {
            this.subscriber.add(this.group);
            LeftSubscriber leftSubscriber = new LeftSubscriber();
            RightSubscriber rightSubscriber = new RightSubscriber();
            this.group.add(leftSubscriber);
            this.group.add(rightSubscriber);
            OnSubscribeJoin.this.left.unsafeSubscribe(leftSubscriber);
            OnSubscribeJoin.this.right.unsafeSubscribe(rightSubscriber);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public final class LeftSubscriber extends Subscriber<TLeft> {
            LeftSubscriber() {
            }

            protected void expire(int id, Subscription resource) {
                boolean complete = false;
                synchronized (ResultSink.this) {
                    if (ResultSink.this.leftMap().remove(Integer.valueOf(id)) != null && ResultSink.this.leftMap().isEmpty() && ResultSink.this.leftDone) {
                        complete = true;
                    }
                }
                if (complete) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.group.remove(resource);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.Observer
            public void onNext(TLeft args) {
                int id;
                int highRightId;
                synchronized (ResultSink.this) {
                    ResultSink resultSink = ResultSink.this;
                    id = resultSink.leftId;
                    resultSink.leftId = id + 1;
                    ResultSink.this.leftMap().put(Integer.valueOf(id), args);
                    highRightId = ResultSink.this.rightId;
                }
                try {
                    LeftDurationSubscriber leftDurationSubscriber = new LeftDurationSubscriber(id);
                    ResultSink.this.group.add(leftDurationSubscriber);
                    OnSubscribeJoin.this.leftDurationSelector.call(args).unsafeSubscribe(leftDurationSubscriber);
                    List<TRight> rightValues = new ArrayList<>();
                    synchronized (ResultSink.this) {
                        for (Map.Entry<Integer, TRight> entry : ResultSink.this.rightMap.entrySet()) {
                            if (entry.getKey().intValue() < highRightId) {
                                rightValues.add(entry.getValue());
                            }
                        }
                    }
                    for (TRight r : rightValues) {
                        ResultSink.this.subscriber.onNext(OnSubscribeJoin.this.resultSelector.call(args, r));
                    }
                } catch (Throwable t) {
                    Exceptions.throwOrReport(t, this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                ResultSink.this.subscriber.onError(e);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // rx.Observer
            public void onCompleted() {
                boolean complete = false;
                synchronized (ResultSink.this) {
                    ResultSink.this.leftDone = true;
                    if (ResultSink.this.rightDone || ResultSink.this.leftMap().isEmpty()) {
                        complete = true;
                    }
                }
                if (complete) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.group.remove(this);
            }

            /* loaded from: classes2.dex */
            final class LeftDurationSubscriber extends Subscriber<TLeftDuration> {
                final int id;
                boolean once = true;

                public LeftDurationSubscriber(int id) {
                    this.id = id;
                }

                @Override // rx.Observer
                public void onNext(TLeftDuration args) {
                    onCompleted();
                }

                @Override // rx.Observer
                public void onError(Throwable e) {
                    LeftSubscriber.this.onError(e);
                }

                @Override // rx.Observer
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        LeftSubscriber.this.expire(this.id, this);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public final class RightSubscriber extends Subscriber<TRight> {
            RightSubscriber() {
            }

            void expire(int id, Subscription resource) {
                boolean complete = false;
                synchronized (ResultSink.this) {
                    if (ResultSink.this.rightMap.remove(Integer.valueOf(id)) != null && ResultSink.this.rightMap.isEmpty() && ResultSink.this.rightDone) {
                        complete = true;
                    }
                }
                if (complete) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.group.remove(resource);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.Observer
            public void onNext(TRight args) {
                int id;
                int highLeftId;
                synchronized (ResultSink.this) {
                    ResultSink resultSink = ResultSink.this;
                    id = resultSink.rightId;
                    resultSink.rightId = id + 1;
                    ResultSink.this.rightMap.put(Integer.valueOf(id), args);
                    highLeftId = ResultSink.this.leftId;
                }
                ResultSink.this.group.add(new SerialSubscription());
                try {
                    RightDurationSubscriber rightDurationSubscriber = new RightDurationSubscriber(id);
                    ResultSink.this.group.add(rightDurationSubscriber);
                    OnSubscribeJoin.this.rightDurationSelector.call(args).unsafeSubscribe(rightDurationSubscriber);
                    List<TLeft> leftValues = new ArrayList<>();
                    synchronized (ResultSink.this) {
                        for (Map.Entry<Integer, TLeft> entry : ResultSink.this.leftMap().entrySet()) {
                            if (entry.getKey().intValue() < highLeftId) {
                                leftValues.add(entry.getValue());
                            }
                        }
                    }
                    for (TLeft lv : leftValues) {
                        ResultSink.this.subscriber.onNext(OnSubscribeJoin.this.resultSelector.call(lv, args));
                    }
                } catch (Throwable t) {
                    Exceptions.throwOrReport(t, this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                ResultSink.this.subscriber.onError(e);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // rx.Observer
            public void onCompleted() {
                boolean complete = false;
                synchronized (ResultSink.this) {
                    ResultSink.this.rightDone = true;
                    if (ResultSink.this.leftDone || ResultSink.this.rightMap.isEmpty()) {
                        complete = true;
                    }
                }
                if (complete) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.group.remove(this);
            }

            /* loaded from: classes2.dex */
            final class RightDurationSubscriber extends Subscriber<TRightDuration> {
                final int id;
                boolean once = true;

                public RightDurationSubscriber(int id) {
                    this.id = id;
                }

                @Override // rx.Observer
                public void onNext(TRightDuration args) {
                    onCompleted();
                }

                @Override // rx.Observer
                public void onError(Throwable e) {
                    RightSubscriber.this.onError(e);
                }

                @Override // rx.Observer
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        RightSubscriber.this.expire(this.id, this);
                    }
                }
            }
        }
    }
}
