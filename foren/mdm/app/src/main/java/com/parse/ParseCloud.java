package com.parse;

import bolts.Continuation;
import bolts.Task;
import java.util.Map;

/* loaded from: classes2.dex */
public final class ParseCloud {
    static ParseCloudCodeController getCloudCodeController() {
        return ParseCorePlugins.getInstance().getCloudCodeController();
    }

    public static <T> Task<T> callFunctionInBackground(final String name, final Map<String, ?> params) {
        return (Task<T>) ParseUser.getCurrentSessionTokenAsync().onSuccessTask((Continuation<String, Task<T>>) new Continuation<String, Task<T>>() { // from class: com.parse.ParseCloud.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<T> then(Task<String> task) throws Exception {
                return ParseCloud.getCloudCodeController().callFunctionInBackground(name, params, task.getResult());
            }
        });
    }

    public static <T> T callFunction(String name, Map<String, ?> params) throws ParseException {
        return (T) ParseTaskUtils.wait(callFunctionInBackground(name, params));
    }

    public static <T> void callFunctionInBackground(String name, Map<String, ?> params, FunctionCallback<T> callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(callFunctionInBackground(name, params), callback);
    }

    private ParseCloud() {
    }
}
