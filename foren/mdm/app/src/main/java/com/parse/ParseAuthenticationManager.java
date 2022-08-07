package com.parse;

import bolts.Continuation;
import bolts.Task;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseAuthenticationManager {
    private final ParseCurrentUserController controller;
    private final Object lock = new Object();
    private final Map<String, AuthenticationCallback> callbacks = new HashMap();

    public ParseAuthenticationManager(ParseCurrentUserController controller) {
        this.controller = controller;
    }

    public void register(final String authType, AuthenticationCallback callback) {
        if (authType == null) {
            throw new IllegalArgumentException("Invalid authType: " + ((Object) null));
        }
        synchronized (this.lock) {
            if (this.callbacks.containsKey(authType)) {
                throw new IllegalStateException("Callback already registered for <" + authType + ">: " + this.callbacks.get(authType));
            }
            this.callbacks.put(authType, callback);
        }
        if (!"anonymous".equals(authType)) {
            this.controller.getAsync(false).onSuccessTask(new Continuation<ParseUser, Task<Void>>() { // from class: com.parse.ParseAuthenticationManager.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<ParseUser> task) throws Exception {
                    ParseUser user = task.getResult();
                    if (user != null) {
                        return user.synchronizeAuthDataAsync(authType);
                    }
                    return null;
                }
            });
        }
    }

    public Task<Boolean> restoreAuthenticationAsync(String authType, final Map<String, String> authData) {
        final AuthenticationCallback callback;
        synchronized (this.lock) {
            callback = this.callbacks.get(authType);
        }
        if (callback == null) {
            return Task.forResult(true);
        }
        return Task.call(new Callable<Boolean>() { // from class: com.parse.ParseAuthenticationManager.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(callback.onRestore(authData));
            }
        }, ParseExecutors.io());
    }

    public Task<Void> deauthenticateAsync(String authType) {
        final AuthenticationCallback callback;
        synchronized (this.lock) {
            callback = this.callbacks.get(authType);
        }
        if (callback != null) {
            return Task.call(new Callable<Void>() { // from class: com.parse.ParseAuthenticationManager.3
                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    callback.onRestore(null);
                    return null;
                }
            }, ParseExecutors.io());
        }
        return Task.forResult(null);
    }
}
