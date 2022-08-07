package org.greenrobot.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class HandlerPoster extends Handler {
    private final EventBus eventBus;
    private boolean handlerActive;
    private final int maxMillisInsideHandleMessage;
    private final PendingPostQueue queue = new PendingPostQueue();

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerPoster(EventBus eventBus, Looper looper, int maxMillisInsideHandleMessage) {
        super(looper);
        this.eventBus = eventBus;
        this.maxMillisInsideHandleMessage = maxMillisInsideHandleMessage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueue(Subscription subscription, Object event) {
        PendingPost pendingPost = PendingPost.obtainPendingPost(subscription, event);
        synchronized (this) {
            this.queue.enqueue(pendingPost);
            if (!this.handlerActive) {
                this.handlerActive = true;
                if (!sendMessage(obtainMessage())) {
                    throw new EventBusException("Could not send handler message");
                }
            }
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        boolean rescheduled = false;
        try {
            long started = SystemClock.uptimeMillis();
            do {
                PendingPost pendingPost = this.queue.poll();
                if (pendingPost == null) {
                    synchronized (this) {
                        pendingPost = this.queue.poll();
                        if (pendingPost == null) {
                            this.handlerActive = false;
                            return;
                        }
                    }
                }
                this.eventBus.invokeSubscriber(pendingPost);
            } while (SystemClock.uptimeMillis() - started < this.maxMillisInsideHandleMessage);
            if (!sendMessage(obtainMessage())) {
                throw new EventBusException("Could not send handler message");
            }
            rescheduled = true;
        } finally {
            this.handlerActive = rescheduled;
        }
    }
}
