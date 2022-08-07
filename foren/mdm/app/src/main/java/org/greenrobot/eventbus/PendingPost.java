package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class PendingPost {
    private static final List<PendingPost> pendingPostPool = new ArrayList();
    Object event;
    PendingPost next;
    Subscription subscription;

    private PendingPost(Object event, Subscription subscription) {
        this.event = event;
        this.subscription = subscription;
    }

    public static PendingPost obtainPendingPost(Subscription subscription, Object event) {
        synchronized (pendingPostPool) {
            int size = pendingPostPool.size();
            if (size <= 0) {
                return new PendingPost(event, subscription);
            }
            PendingPost pendingPost = pendingPostPool.remove(size - 1);
            pendingPost.event = event;
            pendingPost.subscription = subscription;
            pendingPost.next = null;
            return pendingPost;
        }
    }

    public static void releasePendingPost(PendingPost pendingPost) {
        pendingPost.event = null;
        pendingPost.subscription = null;
        pendingPost.next = null;
        synchronized (pendingPostPool) {
            if (pendingPostPool.size() < 10000) {
                pendingPostPool.add(pendingPost);
            }
        }
    }
}
