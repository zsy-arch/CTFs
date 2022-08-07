package org.greenrobot.eventbus;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class AsyncPoster implements Runnable {
    private final EventBus eventBus;
    private final PendingPostQueue queue = new PendingPostQueue();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsyncPoster(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void enqueue(Subscription subscription, Object event) {
        this.queue.enqueue(PendingPost.obtainPendingPost(subscription, event));
        this.eventBus.getExecutorService().execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        PendingPost pendingPost = this.queue.poll();
        if (pendingPost == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.eventBus.invokeSubscriber(pendingPost);
    }
}
