package com.parse;

import bolts.Continuation;
import bolts.Task;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CachedCurrentInstallationController implements ParseCurrentInstallationController {
    static final String TAG = "com.parse.CachedCurrentInstallationController";
    ParseInstallation currentInstallation;
    private final InstallationId installationId;
    private final ParseObjectStore<ParseInstallation> store;
    private final Object mutex = new Object();
    private final TaskQueue taskQueue = new TaskQueue();

    public CachedCurrentInstallationController(ParseObjectStore<ParseInstallation> store, InstallationId installationId) {
        this.store = store;
        this.installationId = installationId;
    }

    public Task<Void> setAsync(final ParseInstallation installation) {
        return !isCurrent(installation) ? Task.forResult(null) : this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.CachedCurrentInstallationController.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.CachedCurrentInstallationController.1.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return CachedCurrentInstallationController.this.store.setAsync(installation);
                    }
                }).continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation<Void, Task<Void>>() { // from class: com.parse.CachedCurrentInstallationController.1.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        CachedCurrentInstallationController.this.installationId.set(installation.getInstallationId());
                        return task;
                    }
                }, ParseExecutors.io());
            }
        });
    }

    @Override // com.parse.ParseObjectCurrentController
    public Task<ParseInstallation> getAsync() {
        synchronized (this.mutex) {
            if (this.currentInstallation == null) {
                return this.taskQueue.enqueue(new AnonymousClass2());
            }
            return Task.forResult(this.currentInstallation);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.parse.CachedCurrentInstallationController$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 implements Continuation<Void, Task<ParseInstallation>> {
        AnonymousClass2() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // bolts.Continuation
        public Task<ParseInstallation> then(Task<Void> toAwait) throws Exception {
            return toAwait.continueWithTask(new Continuation<Void, Task<ParseInstallation>>() { // from class: com.parse.CachedCurrentInstallationController.2.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<ParseInstallation> then(Task<Void> task) throws Exception {
                    synchronized (CachedCurrentInstallationController.this.mutex) {
                        if (CachedCurrentInstallationController.this.currentInstallation == null) {
                            return CachedCurrentInstallationController.this.store.getAsync().continueWith(new Continuation<ParseInstallation, ParseInstallation>() { // from class: com.parse.CachedCurrentInstallationController.2.1.1
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // bolts.Continuation
                                public ParseInstallation then(Task<ParseInstallation> task2) throws Exception {
                                    ParseInstallation current = task2.getResult();
                                    if (current == null) {
                                        current = (ParseInstallation) ParseObject.create(ParseInstallation.class);
                                        current.updateDeviceInfo(CachedCurrentInstallationController.this.installationId);
                                    } else {
                                        CachedCurrentInstallationController.this.installationId.set(current.getInstallationId());
                                        PLog.v(CachedCurrentInstallationController.TAG, "Successfully deserialized Installation object");
                                    }
                                    synchronized (CachedCurrentInstallationController.this.mutex) {
                                        CachedCurrentInstallationController.this.currentInstallation = current;
                                    }
                                    return current;
                                }
                            }, ParseExecutors.io());
                        }
                        return Task.forResult(CachedCurrentInstallationController.this.currentInstallation);
                    }
                }
            });
        }
    }

    @Override // com.parse.ParseObjectCurrentController
    public Task<Boolean> existsAsync() {
        synchronized (this.mutex) {
            if (this.currentInstallation == null) {
                return this.taskQueue.enqueue(new Continuation<Void, Task<Boolean>>() { // from class: com.parse.CachedCurrentInstallationController.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Boolean> then(Task<Void> toAwait) throws Exception {
                        return toAwait.continueWithTask(new Continuation<Void, Task<Boolean>>() { // from class: com.parse.CachedCurrentInstallationController.3.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // bolts.Continuation
                            public Task<Boolean> then(Task<Void> task) throws Exception {
                                return CachedCurrentInstallationController.this.store.existsAsync();
                            }
                        });
                    }
                });
            }
            return Task.forResult(true);
        }
    }

    @Override // com.parse.ParseObjectCurrentController
    public void clearFromMemory() {
        synchronized (this.mutex) {
            this.currentInstallation = null;
        }
    }

    @Override // com.parse.ParseObjectCurrentController
    public void clearFromDisk() {
        synchronized (this.mutex) {
            this.currentInstallation = null;
        }
        try {
            this.installationId.clear();
            ParseTaskUtils.wait(this.store.deleteAsync());
        } catch (ParseException e) {
        }
    }

    public boolean isCurrent(ParseInstallation installation) {
        boolean z;
        synchronized (this.mutex) {
            z = this.currentInstallation == installation;
        }
        return z;
    }
}
