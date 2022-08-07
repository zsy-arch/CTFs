package org.greenrobot.eventbus;

import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class BackgroundPoster implements Runnable {
    private final EventBus eventBus;
    private volatile boolean executorRunning;
    private final PendingPostQueue queue = new PendingPostQueue();

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackgroundPoster(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void enqueue(Subscription subscription, Object event) {
        PendingPost pendingPost = PendingPost.obtainPendingPost(subscription, event);
        synchronized (this) {
            this.queue.enqueue(pendingPost);
            if (!this.executorRunning) {
                this.executorRunning = true;
                this.eventBus.getExecutorService().execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            try {
                PendingPost pendingPost = this.queue.poll(1000);
                if (pendingPost == null) {
                    synchronized (this) {
                        pendingPost = this.queue.poll();
                        if (pendingPost == null) {
                            this.executorRunning = false;
                            return;
                        }
                    }
                }
                this.eventBus.invokeSubscriber(pendingPost);
            } catch (InterruptedException e) {
                Log.w("Event", Thread.currentThread().getName() + " was interruppted", e);
                return;
            } finally {
                this.executorRunning = false;
            }
        }
    }
}
