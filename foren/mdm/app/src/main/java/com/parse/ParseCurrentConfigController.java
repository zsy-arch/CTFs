package com.parse;

import bolts.Task;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseCurrentConfigController {
    ParseConfig currentConfig;
    private File currentConfigFile;
    private final Object currentConfigMutex = new Object();

    public ParseCurrentConfigController(File currentConfigFile) {
        this.currentConfigFile = currentConfigFile;
    }

    public Task<Void> setCurrentConfigAsync(final ParseConfig config) {
        return Task.call(new Callable<Void>() { // from class: com.parse.ParseCurrentConfigController.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                synchronized (ParseCurrentConfigController.this.currentConfigMutex) {
                    ParseCurrentConfigController.this.currentConfig = config;
                    ParseCurrentConfigController.this.saveToDisk(config);
                }
                return null;
            }
        }, ParseExecutors.io());
    }

    public Task<ParseConfig> getCurrentConfigAsync() {
        return Task.call(new Callable<ParseConfig>() { // from class: com.parse.ParseCurrentConfigController.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public ParseConfig call() throws Exception {
                synchronized (ParseCurrentConfigController.this.currentConfigMutex) {
                    if (ParseCurrentConfigController.this.currentConfig == null) {
                        ParseConfig config = ParseCurrentConfigController.this.getFromDisk();
                        ParseCurrentConfigController parseCurrentConfigController = ParseCurrentConfigController.this;
                        if (config == null) {
                            config = new ParseConfig();
                        }
                        parseCurrentConfigController.currentConfig = config;
                    }
                }
                return ParseCurrentConfigController.this.currentConfig;
            }
        }, ParseExecutors.io());
    }

    ParseConfig getFromDisk() {
        try {
            return ParseConfig.decode(ParseFileUtils.readFileToJSONObject(this.currentConfigFile), ParseDecoder.get());
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    void clearCurrentConfigForTesting() {
        synchronized (this.currentConfigMutex) {
            this.currentConfig = null;
        }
    }

    void saveToDisk(ParseConfig config) {
        JSONObject object = new JSONObject();
        try {
            object.put("params", (JSONObject) NoObjectsEncoder.get().encode(config.getParams()));
            try {
                ParseFileUtils.writeJSONObjectToFile(this.currentConfigFile, object);
            } catch (IOException e) {
            }
        } catch (JSONException e2) {
            throw new RuntimeException("could not serialize config to JSON");
        }
    }
}
