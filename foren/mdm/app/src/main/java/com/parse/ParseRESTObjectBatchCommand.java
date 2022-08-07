package com.parse;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import com.umeng.analytics.a;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes2.dex */
class ParseRESTObjectBatchCommand extends ParseRESTCommand {
    public static final int COMMAND_OBJECT_BATCH_MAX_SIZE = 50;
    private static final String KEY_RESULTS = "results";

    public static List<Task<JSONObject>> executeBatch(ParseHttpClient client, List<ParseRESTObjectCommand> commands, String sessionToken) {
        final int batchSize = commands.size();
        List<Task<JSONObject>> tasks = new ArrayList<>(batchSize);
        if (batchSize == 1) {
            tasks.add(commands.get(0).executeAsync(client));
        } else if (batchSize > 50) {
            List<List<ParseRESTObjectCommand>> batches = Lists.partition(commands, 50);
            int size = batches.size();
            for (int i = 0; i < size; i++) {
                tasks.addAll(executeBatch(client, batches.get(i), sessionToken));
            }
        } else {
            final List<TaskCompletionSource<JSONObject>> tcss = new ArrayList<>(batchSize);
            for (int i2 = 0; i2 < batchSize; i2++) {
                TaskCompletionSource<JSONObject> tcs = new TaskCompletionSource<>();
                tcss.add(tcs);
                tasks.add(tcs.getTask());
            }
            JSONObject parameters = new JSONObject();
            JSONArray requests = new JSONArray();
            try {
                for (ParseRESTObjectCommand command : commands) {
                    JSONObject requestParameters = new JSONObject();
                    requestParameters.put("method", command.method.toString());
                    requestParameters.put("path", new URL(server, command.httpPath).getPath());
                    JSONObject body = command.jsonParameters;
                    if (body != null) {
                        requestParameters.put(a.w, body);
                    }
                    requests.put(requestParameters);
                }
                parameters.put("requests", requests);
                new ParseRESTObjectBatchCommand("batch", ParseHttpRequest.Method.POST, parameters, sessionToken).executeAsync(client).continueWith(new Continuation<JSONObject, Void>() { // from class: com.parse.ParseRESTObjectBatchCommand.1
                    @Override // bolts.Continuation
                    public Void then(Task<JSONObject> task) throws Exception {
                        if (task.isFaulted() || task.isCancelled()) {
                            for (int i3 = 0; i3 < batchSize; i3++) {
                                TaskCompletionSource<JSONObject> tcs2 = (TaskCompletionSource) tcss.get(i3);
                                if (task.isFaulted()) {
                                    tcs2.setError(task.getError());
                                } else {
                                    tcs2.setCancelled();
                                }
                            }
                        }
                        JSONArray results = task.getResult().getJSONArray(ParseRESTObjectBatchCommand.KEY_RESULTS);
                        int resultLength = results.length();
                        if (resultLength != batchSize) {
                            for (int i4 = 0; i4 < batchSize; i4++) {
                                ((TaskCompletionSource) tcss.get(i4)).setError(new IllegalStateException("Batch command result count expected: " + batchSize + " but was: " + resultLength));
                            }
                        }
                        for (int i5 = 0; i5 < batchSize; i5++) {
                            JSONObject result = results.getJSONObject(i5);
                            TaskCompletionSource<JSONObject> tcs3 = (TaskCompletionSource) tcss.get(i5);
                            if (result.has("success")) {
                                tcs3.setResult(result.getJSONObject("success"));
                            } else if (result.has(av.aG)) {
                                JSONObject error = result.getJSONObject(av.aG);
                                tcs3.setError(new ParseException(error.getInt("code"), error.getString(av.aG)));
                            }
                        }
                        return null;
                    }
                });
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }
        return tasks;
    }

    private ParseRESTObjectBatchCommand(String httpPath, ParseHttpRequest.Method httpMethod, JSONObject parameters, String sessionToken) {
        super(httpPath, httpMethod, parameters, sessionToken);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.parse.ParseRESTCommand, com.parse.ParseRequest
    public Task<JSONObject> onResponseAsync(ParseHttpResponse response, ProgressCallback downloadProgressCallback) {
        InputStream responseStream;
        try {
            responseStream = null;
            responseStream = response.getContent();
            try {
                JSONArray results = new JSONArray(new String(ParseIOUtils.toByteArray(responseStream)));
                JSONObject json = new JSONObject();
                json.put(KEY_RESULTS, results);
                return Task.forResult(json);
            } catch (JSONException e) {
                return Task.forError(newTemporaryException("bad json response", e));
            }
        } catch (IOException e2) {
            return Task.forError(e2);
        } finally {
            ParseIOUtils.closeQuietly(responseStream);
        }
    }
}
