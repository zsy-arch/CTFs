package rx;

import rx.internal.util.SubscriptionList;

/* loaded from: classes2.dex */
public abstract class Subscriber<T> implements Observer<T>, Subscription {
    private static final long NOT_SET = Long.MIN_VALUE;
    private Producer producer;
    private long requested;
    private final Subscriber<?> subscriber;
    private final SubscriptionList subscriptions;

    public Subscriber() {
        this(null, false);
    }

    public Subscriber(Subscriber<?> subscriber) {
        this(subscriber, true);
    }

    public Subscriber(Subscriber<?> subscriber, boolean shareSubscriptions) {
        this.requested = NOT_SET;
        this.subscriber = subscriber;
        this.subscriptions = (!shareSubscriptions || subscriber == null) ? new SubscriptionList() : subscriber.subscriptions;
    }

    public final void add(Subscription s) {
        this.subscriptions.add(s);
    }

    @Override // rx.Subscription
    public final void unsubscribe() {
        this.subscriptions.unsubscribe();
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.subscriptions.isUnsubscribed();
    }

    public void onStart() {
    }

    public final void request(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + n);
        }
        synchronized (this) {
            if (this.producer != null) {
                this.producer.request(n);
                return;
            }
            addToRequested(n);
        }
    }

    private void addToRequested(long n) {
        if (this.requested == NOT_SET) {
            this.requested = n;
            return;
        }
        long total = this.requested + n;
        if (total < 0) {
            this.requested = Long.MAX_VALUE;
        } else {
            this.requested = total;
        }
    }

    public void setProducer(Producer p) {
        long toRequest;
        boolean passToSubscriber = false;
        synchronized (this) {
            toRequest = this.requested;
            this.producer = p;
            if (this.subscriber != null && toRequest == NOT_SET) {
                passToSubscriber = true;
            }
        }
        if (passToSubscriber) {
            this.subscriber.setProducer(this.producer);
        } else if (toRequest == NOT_SET) {
            this.producer.request(Long.MAX_VALUE);
        } else {
            this.producer.request(toRequest);
        }
    }
}
