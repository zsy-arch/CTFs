package com.parse;

import bolts.Continuation;
import bolts.Task;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TaskQueue {
    private final Lock lock = new ReentrantLock();
    private Task<Void> tail;

    private Task<Void> getTaskToAwait() {
        this.lock.lock();
        try {
            return (this.tail != null ? this.tail : Task.forResult(null)).continueWith(new Continuation<Void, Void>() { // from class: com.parse.TaskQueue.1
                @Override // bolts.Continuation
                public Void then(Task<Void> task) throws Exception {
                    return null;
                }
            });
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> Task<T> enqueue(Continuation<Void, Task<T>> taskStart) {
        this.lock.lock();
        try {
            Task<Void> oldTail = this.tail != null ? this.tail : Task.forResult(null);
            try {
                Task<T> task = taskStart.then(getTaskToAwait());
                this.tail = Task.whenAll(Arrays.asList(oldTail, task));
                return task;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Continuation<T, Task<T>> waitFor(final Task<Void> toAwait) {
        return new Continuation<T, Task<T>>() { // from class: com.parse.TaskQueue.2
            @Override // bolts.Continuation
            public Task<T> then(final Task<T> task) throws Exception {
                return Task.this.continueWithTask(new Continuation<Void, Task<T>>() { // from class: com.parse.TaskQueue.2.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<T> then(Task<Void> ignored) throws Exception {
                        return task;
                    }
                });
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Lock getLock() {
        return this.lock;
    }

    void waitUntilFinished() throws InterruptedException {
        this.lock.lock();
        try {
            if (this.tail != null) {
                this.tail.waitForCompletion();
            }
        } finally {
            this.lock.unlock();
        }
    }
}
