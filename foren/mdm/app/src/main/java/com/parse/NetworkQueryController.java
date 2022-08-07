package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NetworkQueryController extends AbstractQueryController {
    private static final String TAG = "NetworkQueryController";
    private final ParseHttpClient restClient;

    public NetworkQueryController(ParseHttpClient restClient) {
        this.restClient = restClient;
    }

    @Override // com.parse.ParseQueryController
    public <T extends ParseObject> Task<List<T>> findAsync(ParseQuery.State<T> state, ParseUser user, Task<Void> cancellationToken) {
        return findAsync(state, user != null ? user.getSessionToken() : null, true, cancellationToken);
    }

    @Override // com.parse.ParseQueryController
    public <T extends ParseObject> Task<Integer> countAsync(ParseQuery.State<T> state, ParseUser user, Task<Void> cancellationToken) {
        return countAsync(state, user != null ? user.getSessionToken() : null, true, cancellationToken);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T extends ParseObject> Task<List<T>> findAsync(final ParseQuery.State<T> state, String sessionToken, boolean shouldRetry, Task<Void> ct) {
        final long queryStart = System.nanoTime();
        final ParseRESTCommand command = ParseRESTQueryCommand.findCommand(state, sessionToken);
        if (shouldRetry) {
            command.enableRetrying();
        }
        final long querySent = System.nanoTime();
        return (Task<List<T>>) command.executeAsync(this.restClient, ct).onSuccess(new Continuation<JSONObject, List<T>>() { // from class: com.parse.NetworkQueryController.1
            @Override // bolts.Continuation
            public List<T> then(Task<JSONObject> task) throws Exception {
                JSONObject json = task.getResult();
                ParseQuery.CachePolicy policy = state.cachePolicy();
                if (!(policy == null || policy == ParseQuery.CachePolicy.IGNORE_CACHE)) {
                    ParseKeyValueCache.saveToKeyValueCache(command.getCacheKey(), json.toString());
                }
                long queryReceived = System.nanoTime();
                List<T> response = NetworkQueryController.this.convertFindResponse(state, task.getResult());
                long objectsParsed = System.nanoTime();
                if (json.has("trace")) {
                    PLog.d("ParseQuery", String.format("Query pre-processing took %f seconds\n%s\nClient side parsing took %f seconds\n", Float.valueOf(((float) (querySent - queryStart)) / 1000000.0f), json.get("trace"), Float.valueOf(((float) (objectsParsed - queryReceived)) / 1000000.0f)));
                }
                return response;
            }
        }, Task.BACKGROUND_EXECUTOR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T extends ParseObject> Task<Integer> countAsync(final ParseQuery.State<T> state, String sessionToken, boolean shouldRetry, Task<Void> ct) {
        final ParseRESTCommand command = ParseRESTQueryCommand.countCommand(state, sessionToken);
        if (shouldRetry) {
            command.enableRetrying();
        }
        return command.executeAsync(this.restClient, ct).onSuccessTask(new Continuation<JSONObject, Task<JSONObject>>() { // from class: com.parse.NetworkQueryController.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<JSONObject> then(Task<JSONObject> task) throws Exception {
                ParseQuery.CachePolicy policy = state.cachePolicy();
                if (!(policy == null || policy == ParseQuery.CachePolicy.IGNORE_CACHE)) {
                    ParseKeyValueCache.saveToKeyValueCache(command.getCacheKey(), task.getResult().toString());
                }
                return task;
            }
        }, Task.BACKGROUND_EXECUTOR).onSuccess(new Continuation<JSONObject, Integer>() { // from class: com.parse.NetworkQueryController.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Integer then(Task<JSONObject> task) throws Exception {
                return Integer.valueOf(task.getResult().optInt("count"));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T extends ParseObject> List<T> convertFindResponse(ParseQuery.State<T> state, JSONObject response) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray results = response.getJSONArray("results");
        if (results == null) {
            PLog.d(TAG, "null results in find response");
        } else {
            String resultClassName = response.optString("className", null);
            if (resultClassName == null) {
                resultClassName = state.className();
            }
            for (int i = 0; i < results.length(); i++) {
                ParseObject fromJSON = ParseObject.fromJSON(results.getJSONObject(i), resultClassName, state.selectedKeys() == null);
                arrayList.add(fromJSON);
                ParseQuery.RelationConstraint relation = (ParseQuery.RelationConstraint) state.constraints().get("$relatedTo");
                if (relation != null) {
                    relation.getRelation().addKnownObject(fromJSON);
                }
            }
        }
        return arrayList;
    }
}
