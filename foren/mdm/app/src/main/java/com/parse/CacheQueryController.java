package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseQuery;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CacheQueryController extends AbstractQueryController {
    private final NetworkQueryController networkController;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface CommandDelegate<T> {
        Task<T> runFromCacheAsync();

        Task<T> runOnNetworkAsync(boolean z);
    }

    public CacheQueryController(NetworkQueryController network) {
        this.networkController = network;
    }

    @Override // com.parse.ParseQueryController
    public <T extends ParseObject> Task<List<T>> findAsync(final ParseQuery.State<T> state, ParseUser user, final Task<Void> cancellationToken) {
        final String sessionToken = user != null ? user.getSessionToken() : null;
        return runCommandWithPolicyAsync(new CommandDelegate<List<T>>() { // from class: com.parse.CacheQueryController.1
            @Override // com.parse.CacheQueryController.CommandDelegate
            public Task<List<T>> runOnNetworkAsync(boolean retry) {
                return CacheQueryController.this.networkController.findAsync(state, sessionToken, retry, cancellationToken);
            }

            @Override // com.parse.CacheQueryController.CommandDelegate
            public Task<List<T>> runFromCacheAsync() {
                return CacheQueryController.this.findFromCacheAsync(state, sessionToken);
            }
        }, state.cachePolicy());
    }

    @Override // com.parse.ParseQueryController
    public <T extends ParseObject> Task<Integer> countAsync(final ParseQuery.State<T> state, ParseUser user, final Task<Void> cancellationToken) {
        final String sessionToken = user != null ? user.getSessionToken() : null;
        return runCommandWithPolicyAsync(new CommandDelegate<Integer>() { // from class: com.parse.CacheQueryController.2
            @Override // com.parse.CacheQueryController.CommandDelegate
            public Task<Integer> runOnNetworkAsync(boolean retry) {
                return CacheQueryController.this.networkController.countAsync(state, sessionToken, retry, cancellationToken);
            }

            @Override // com.parse.CacheQueryController.CommandDelegate
            public Task<Integer> runFromCacheAsync() {
                return CacheQueryController.this.countFromCacheAsync(state, sessionToken);
            }
        }, state.cachePolicy());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends ParseObject> Task<List<T>> findFromCacheAsync(final ParseQuery.State<T> state, String sessionToken) {
        final String cacheKey = ParseRESTQueryCommand.findCommand(state, sessionToken).getCacheKey();
        return Task.call(new Callable<List<T>>() { // from class: com.parse.CacheQueryController.3
            @Override // java.util.concurrent.Callable
            public List<T> call() throws Exception {
                JSONObject cached = ParseKeyValueCache.jsonFromKeyValueCache(cacheKey, state.maxCacheAge());
                if (cached == null) {
                    throw new ParseException(120, "results not cached");
                }
                try {
                    return CacheQueryController.this.networkController.convertFindResponse(state, cached);
                } catch (JSONException e) {
                    throw new ParseException(120, "the cache contains corrupted json");
                }
            }
        }, Task.BACKGROUND_EXECUTOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends ParseObject> Task<Integer> countFromCacheAsync(final ParseQuery.State<T> state, String sessionToken) {
        final String cacheKey = ParseRESTQueryCommand.countCommand(state, sessionToken).getCacheKey();
        return Task.call(new Callable<Integer>() { // from class: com.parse.CacheQueryController.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                JSONObject cached = ParseKeyValueCache.jsonFromKeyValueCache(cacheKey, state.maxCacheAge());
                if (cached == null) {
                    throw new ParseException(120, "results not cached");
                }
                try {
                    return Integer.valueOf(cached.getInt("count"));
                } catch (JSONException e) {
                    throw new ParseException(120, "the cache contains corrupted json");
                }
            }
        }, Task.BACKGROUND_EXECUTOR);
    }

    private <TResult> Task<TResult> runCommandWithPolicyAsync(final CommandDelegate<TResult> c, ParseQuery.CachePolicy policy) {
        switch (policy) {
            case IGNORE_CACHE:
            case NETWORK_ONLY:
                return c.runOnNetworkAsync(true);
            case CACHE_ONLY:
                return c.runFromCacheAsync();
            case CACHE_ELSE_NETWORK:
                return (Task<TResult>) c.runFromCacheAsync().continueWithTask((Continuation<TResult, Task<TResult>>) new Continuation<TResult, Task<TResult>>() { // from class: com.parse.CacheQueryController.5
                    @Override // bolts.Continuation
                    public Task<TResult> then(Task<TResult> task) throws Exception {
                        if (task.getError() instanceof ParseException) {
                            return c.runOnNetworkAsync(true);
                        }
                        return task;
                    }
                });
            case NETWORK_ELSE_CACHE:
                return (Task<TResult>) c.runOnNetworkAsync(false).continueWithTask((Continuation<TResult, Task<TResult>>) new Continuation<TResult, Task<TResult>>() { // from class: com.parse.CacheQueryController.6
                    @Override // bolts.Continuation
                    public Task<TResult> then(Task<TResult> task) throws Exception {
                        Exception error = task.getError();
                        if (!(error instanceof ParseException) || ((ParseException) error).getCode() != 100) {
                            return task;
                        }
                        return c.runFromCacheAsync();
                    }
                });
            case CACHE_THEN_NETWORK:
                throw new RuntimeException("You cannot use the cache policy CACHE_THEN_NETWORK with find()");
            default:
                throw new RuntimeException("Unknown cache policy: " + policy);
        }
    }
}
