package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.alipay.sdk.util.j;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseCloudCodeController {
    final ParseHttpClient restClient;

    public ParseCloudCodeController(ParseHttpClient restClient) {
        this.restClient = restClient;
    }

    public <T> Task<T> callFunctionInBackground(String name, Map<String, ?> params, String sessionToken) {
        return (Task<T>) ParseRESTCloudCommand.callFunctionCommand(name, params, sessionToken).executeAsync(this.restClient).onSuccess((Continuation<JSONObject, T>) new Continuation<JSONObject, T>() { // from class: com.parse.ParseCloudCodeController.1
            /* JADX WARN: Type inference failed for: r0v0, types: [T, java.lang.Object] */
            @Override // bolts.Continuation
            public T then(Task<JSONObject> task) throws Exception {
                return ParseCloudCodeController.this.convertCloudResponse(task.getResult());
            }
        });
    }

    Object convertCloudResponse(Object result) {
        if (result instanceof JSONObject) {
            result = ((JSONObject) result).opt(j.c);
        }
        Object finalResult = ParseDecoder.get().decode(result);
        return finalResult != null ? finalResult : result;
    }
}
