package rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
public final class SequentialSubscription extends AtomicReference<Subscription> implements Subscription {
    private static final long serialVersionUID = 995205034283130269L;

    public SequentialSubscription() {
    }

    public SequentialSubscription(Subscription initial) {
        lazySet(initial);
    }

    public Subscription current() {
        Subscription current = (Subscription) super.get();
        if (current == Unsubscribed.INSTANCE) {
            return Subscriptions.unsubscribed();
        }
        return current;
    }

    public boolean update(Subscription next) {
        Subscription current;
        do {
            current = get();
            if (current == Unsubscribed.INSTANCE) {
                if (next != null) {
                    next.unsubscribe();
                }
                return false;
            }
        } while (!compareAndSet(current, next));
        if (current != null) {
            current.unsubscribe();
        }
        return true;
    }

    public boolean replace(Subscription next) {
        Subscription current;
        do {
            current = get();
            if (current == Unsubscribed.INSTANCE) {
                if (next != null) {
                    next.unsubscribe();
                }
                return false;
            }
        } while (!compareAndSet(current, next));
        return true;
    }

    public boolean updateWeak(Subscription next) {
        Subscription current = get();
        if (current == Unsubscribed.INSTANCE) {
            if (next != null) {
                next.unsubscribe();
            }
            return false;
        } else if (compareAndSet(current, next)) {
            return true;
        } else {
            Subscription current2 = get();
            if (next != null) {
                next.unsubscribe();
            }
            return current2 == Unsubscribed.INSTANCE;
        }
    }

    public boolean replaceWeak(Subscription next) {
        Subscription current = get();
        if (current != Unsubscribed.INSTANCE) {
            if (!compareAndSet(current, next) && get() == Unsubscribed.INSTANCE) {
                if (next == null) {
                    return false;
                }
                next.unsubscribe();
                return false;
            }
            return true;
        } else if (next == null) {
            return false;
        } else {
            next.unsubscribe();
            return false;
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        Subscription current;
        if (get() != Unsubscribed.INSTANCE && (current = getAndSet(Unsubscribed.INSTANCE)) != null && current != Unsubscribed.INSTANCE) {
            current.unsubscribe();
        }
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return get() == Unsubscribed.INSTANCE;
    }
}
