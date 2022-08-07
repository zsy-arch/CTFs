package com.parse;

import bolts.Continuation;
import bolts.Task;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CachedCurrentUserController implements ParseCurrentUserController {
    ParseUser currentUser;
    private final ParseObjectStore<ParseUser> store;
    private final Object mutex = new Object();
    private final TaskQueue taskQueue = new TaskQueue();
    boolean currentUserMatchesDisk = false;

    public CachedCurrentUserController(ParseObjectStore<ParseUser> store) {
        this.store = store;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.parse.CachedCurrentUserController$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 implements Continuation<Void, Task<Void>> {
        final /* synthetic */ ParseUser val$user;

        AnonymousClass1(ParseUser parseUser) {
            this.val$user = parseUser;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // bolts.Continuation
        public Task<Void> then(Task<Void> toAwait) throws Exception {
            return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.CachedCurrentUserController.1.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    ParseUser oldCurrentUser;
                    synchronized (CachedCurrentUserController.this.mutex) {
                        oldCurrentUser = CachedCurrentUserController.this.currentUser;
                    }
                    if (oldCurrentUser == null || oldCurrentUser == AnonymousClass1.this.val$user) {
                        return task;
                    }
                    return oldCurrentUser.logOutAsync(false).continueWith(new Continuation<Void, Void>() { // from class: com.parse.CachedCurrentUserController.1.3.1
                        @Override // bolts.Continuation
                        public Void then(Task<Void> task2) throws Exception {
                            return null;
                        }
                    });
                }
            }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.CachedCurrentUserController.1.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    AnonymousClass1.this.val$user.setIsCurrentUser(true);
                    return AnonymousClass1.this.val$user.synchronizeAllAuthDataAsync();
                }
            }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.CachedCurrentUserController.1.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    return CachedCurrentUserController.this.store.setAsync(AnonymousClass1.this.val$user).continueWith(new Continuation<Void, Void>() { // from class: com.parse.CachedCurrentUserController.1.1.1
                        @Override // bolts.Continuation
                        public Void then(Task<Void> task2) throws Exception {
                            synchronized (CachedCurrentUserController.this.mutex) {
                                CachedCurrentUserController.this.currentUserMatchesDisk = !task2.isFaulted();
                                CachedCurrentUserController.this.currentUser = AnonymousClass1.this.val$user;
                            }
                            return null;
                        }
                    });
                }
            });
        }
    }

    public Task<Void> setAsync(ParseUser user) {
        return this.taskQueue.enqueue(new AnonymousClass1(user));
    }

    @Override // com.parse.ParseCurrentUserController
    public Task<Void> setIfNeededAsync(ParseUser user) {
        synchronized (this.mutex) {
            if (user.isCurrentUser() && !this.currentUserMatchesDisk) {
                return setAsync(user);
            }
            return Task.forResult(null);
        }
    }

    @Override // com.parse.ParseObjectCurrentController
    public Task<ParseUser> getAsync() {
        return getAsync(ParseUser.isAutomaticUserEnabled());
    }

    @Override // com.parse.ParseObjectCurrentController
    public Task<Boolean> existsAsync() {
        synchronized (this.mutex) {
            if (this.currentUser == null) {
                return this.taskQueue.enqueue(new Continuation<Void, Task<Boolean>>() { // from class: com.parse.CachedCurrentUserController.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Boolean> then(Task<Void> toAwait) throws Exception {
                        return toAwait.continueWithTask(new Continuation<Void, Task<Boolean>>() { // from class: com.parse.CachedCurrentUserController.2.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // bolts.Continuation
                            public Task<Boolean> then(Task<Void> task) throws Exception {
                                return CachedCurrentUserController.this.store.existsAsync();
                            }
                        });
                    }
                });
            }
            return Task.forResult(true);
        }
    }

    public boolean isCurrent(ParseUser user) {
        boolean z;
        synchronized (this.mutex) {
            z = this.currentUser == user;
        }
        return z;
    }

    @Override // com.parse.ParseObjectCurrentController
    public void clearFromMemory() {
        synchronized (this.mutex) {
            this.currentUser = null;
            this.currentUserMatchesDisk = false;
        }
    }

    @Override // com.parse.ParseObjectCurrentController
    public void clearFromDisk() {
        synchronized (this.mutex) {
            this.currentUser = null;
            this.currentUserMatchesDisk = false;
        }
        try {
            ParseTaskUtils.wait(this.store.deleteAsync());
        } catch (ParseException e) {
        }
    }

    @Override // com.parse.ParseCurrentUserController
    public Task<String> getCurrentSessionTokenAsync() {
        return getAsync(false).onSuccess(new Continuation<ParseUser, String>() { // from class: com.parse.CachedCurrentUserController.3
            @Override // bolts.Continuation
            public String then(Task<ParseUser> task) throws Exception {
                ParseUser user = task.getResult();
                if (user != null) {
                    return user.getSessionToken();
                }
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.parse.CachedCurrentUserController$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass4 implements Continuation<Void, Task<Void>> {
        AnonymousClass4() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // bolts.Continuation
        public Task<Void> then(Task<Void> toAwait) throws Exception {
            final Task<ParseUser> userTask = CachedCurrentUserController.this.getAsync(false);
            return Task.whenAll(Arrays.asList(userTask, toAwait)).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.CachedCurrentUserController.4.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    return Task.whenAll(Arrays.asList(userTask.onSuccessTask(new Continuation<ParseUser, Task<Void>>() { // from class: com.parse.CachedCurrentUserController.4.1.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // bolts.Continuation
                        public Task<Void> then(Task<ParseUser> task2) throws Exception {
                            ParseUser user = task2.getResult();
                            return user == null ? task2.cast() : user.logOutAsync();
                        }
                    }), CachedCurrentUserController.this.store.deleteAsync().continueWith(new Continuation<Void, Void>() { // from class: com.parse.CachedCurrentUserController.4.1.2
                        @Override // bolts.Continuation
                        public Void then(Task<Void> task2) throws Exception {
                            boolean deleted = !task2.isFaulted();
                            synchronized (CachedCurrentUserController.this.mutex) {
                                CachedCurrentUserController.this.currentUserMatchesDisk = deleted;
                                CachedCurrentUserController.this.currentUser = null;
                            }
                            return null;
                        }
                    })));
                }
            });
        }
    }

    @Override // com.parse.ParseCurrentUserController
    public Task<Void> logOutAsync() {
        return this.taskQueue.enqueue(new AnonymousClass4());
    }

    @Override // com.parse.ParseCurrentUserController
    public Task<ParseUser> getAsync(boolean shouldAutoCreateUser) {
        synchronized (this.mutex) {
            if (this.currentUser == null) {
                return this.taskQueue.enqueue(new AnonymousClass5(shouldAutoCreateUser));
            }
            return Task.forResult(this.currentUser);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.parse.CachedCurrentUserController$5  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass5 implements Continuation<Void, Task<ParseUser>> {
        final /* synthetic */ boolean val$shouldAutoCreateUser;

        AnonymousClass5(boolean z) {
            this.val$shouldAutoCreateUser = z;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // bolts.Continuation
        public Task<ParseUser> then(Task<Void> toAwait) throws Exception {
            return toAwait.continueWithTask(new Continuation<Void, Task<ParseUser>>() { // from class: com.parse.CachedCurrentUserController.5.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<ParseUser> then(Task<Void> ignored) throws Exception {
                    ParseUser current;
                    boolean matchesDisk;
                    synchronized (CachedCurrentUserController.this.mutex) {
                        current = CachedCurrentUserController.this.currentUser;
                        matchesDisk = CachedCurrentUserController.this.currentUserMatchesDisk;
                    }
                    if (current != null) {
                        return Task.forResult(current);
                    }
                    if (!matchesDisk) {
                        return CachedCurrentUserController.this.store.getAsync().continueWith(new Continuation<ParseUser, ParseUser>() { // from class: com.parse.CachedCurrentUserController.5.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // bolts.Continuation
                            public ParseUser then(Task<ParseUser> task) throws Exception {
                                boolean matchesDisk2 = true;
                                ParseUser current2 = task.getResult();
                                if (task.isFaulted()) {
                                    matchesDisk2 = false;
                                }
                                synchronized (CachedCurrentUserController.this.mutex) {
                                    CachedCurrentUserController.this.currentUser = current2;
                                    CachedCurrentUserController.this.currentUserMatchesDisk = matchesDisk2;
                                }
                                if (current2 != null) {
                                    synchronized (current2.mutex) {
                                        current2.setIsCurrentUser(true);
                                    }
                                    return current2;
                                } else if (AnonymousClass5.this.val$shouldAutoCreateUser) {
                                    return CachedCurrentUserController.this.lazyLogIn();
                                } else {
                                    return null;
                                }
                            }
                        });
                    }
                    if (AnonymousClass5.this.val$shouldAutoCreateUser) {
                        return Task.forResult(CachedCurrentUserController.this.lazyLogIn());
                    }
                    return null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ParseUser lazyLogIn() {
        return lazyLogIn("anonymous", ParseAnonymousUtils.getAuthData());
    }

    ParseUser lazyLogIn(String authType, Map<String, String> authData) {
        ParseUser user = (ParseUser) ParseObject.create(ParseUser.class);
        synchronized (user.mutex) {
            user.setIsCurrentUser(true);
            user.putAuthData(authType, authData);
        }
        synchronized (this.mutex) {
            this.currentUserMatchesDisk = false;
            this.currentUser = user;
        }
        return user;
    }
}
