package com.parse;

import bolts.Continuation;
import bolts.Task;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ParseConfigController {
    private ParseCurrentConfigController currentConfigController;
    private final ParseHttpClient restClient;

    public ParseConfigController(ParseHttpClient restClient, ParseCurrentConfigController currentConfigController) {
        this.restClient = restClient;
        this.currentConfigController = currentConfigController;
    }

    public ParseCurrentConfigController getCurrentConfigController() {
        return this.currentConfigController;
    }

    public Task<ParseConfig> getAsync(String sessionToken) {
        ParseRESTCommand command = ParseRESTConfigCommand.fetchConfigCommand(sessionToken);
        command.enableRetrying();
        return command.executeAsync(this.restClient).onSuccessTask(new Continuation<JSONObject, Task<ParseConfig>>() { // from class: com.parse.ParseConfigController.1
            @Override // bolts.Continuation
            public Task<ParseConfig> then(Task<JSONObject> task) throws Exception {
                final ParseConfig config = ParseConfig.decode(task.getResult(), ParseDecoder.get());
                return ParseConfigController.this.currentConfigController.setCurrentConfigAsync(config).continueWith(new Continuation<Void, ParseConfig>() { // from class: com.parse.ParseConfigController.1.1
                    @Override // bolts.Continuation
                    public ParseConfig then(Task<Void> task2) throws Exception {
                        return config;
                    }
                });
            }
        });
    }
}
