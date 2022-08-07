package rx.subscriptions;

import com.http.config.URLConfig;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;

/* loaded from: classes2.dex */
public final class RefCountSubscription implements Subscription {
    static final State EMPTY_STATE = new State(false, 0);
    private final Subscription actual;
    final AtomicReference<State> state = new AtomicReference<>(EMPTY_STATE);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class State {
        final int children;
        final boolean isUnsubscribed;

        State(boolean u2, int c) {
            this.isUnsubscribed = u2;
            this.children = c;
        }

        State addChild() {
            return new State(this.isUnsubscribed, this.children + 1);
        }

        State removeChild() {
            return new State(this.isUnsubscribed, this.children - 1);
        }

        State unsubscribe() {
            return new State(true, this.children);
        }
    }

    public RefCountSubscription(Subscription s) {
        if (s == null) {
            throw new IllegalArgumentException(URLConfig.baidu_url);
        }
        this.actual = s;
    }

    public Subscription get() {
        State oldState;
        AtomicReference<State> localState = this.state;
        do {
            oldState = localState.get();
            if (oldState.isUnsubscribed) {
                return Subscriptions.unsubscribed();
            }
        } while (!localState.compareAndSet(oldState, oldState.addChild()));
        return new InnerSubscription(this);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.state.get().isUnsubscribed;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        State oldState;
        State newState;
        AtomicReference<State> localState = this.state;
        do {
            oldState = localState.get();
            if (!oldState.isUnsubscribed) {
                newState = oldState.unsubscribe();
            } else {
                return;
            }
        } while (!localState.compareAndSet(oldState, newState));
        unsubscribeActualIfApplicable(newState);
    }

    private void unsubscribeActualIfApplicable(State state) {
        if (state.isUnsubscribed && state.children == 0) {
            this.actual.unsubscribe();
        }
    }

    void unsubscribeAChild() {
        State oldState;
        State newState;
        AtomicReference<State> localState = this.state;
        do {
            oldState = localState.get();
            newState = oldState.removeChild();
        } while (!localState.compareAndSet(oldState, newState));
        unsubscribeActualIfApplicable(newState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class InnerSubscription extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 7005765588239987643L;
        final RefCountSubscription parent;

        public InnerSubscription(RefCountSubscription parent) {
            this.parent = parent;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(0, 1)) {
                this.parent.unsubscribeAChild();
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() != 0;
        }
    }
}
