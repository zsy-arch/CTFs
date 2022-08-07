package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseFile;
import com.parse.ParseRESTFileCommand;
import com.parse.http.ParseHttpRequest;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseFileController {
    private ParseHttpClient awsClient;
    private final File cachePath;
    private final Object lock = new Object();
    private final ParseHttpClient restClient;

    public ParseFileController(ParseHttpClient restClient, File cachePath) {
        this.restClient = restClient;
        this.cachePath = cachePath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseHttpClient awsClient() {
        ParseHttpClient parseHttpClient;
        synchronized (this.lock) {
            if (this.awsClient == null) {
                this.awsClient = ParsePlugins.get().newHttpClient();
            }
            parseHttpClient = this.awsClient;
        }
        return parseHttpClient;
    }

    ParseFileController awsClient(ParseHttpClient awsClient) {
        synchronized (this.lock) {
            this.awsClient = awsClient;
        }
        return this;
    }

    public File getCacheFile(ParseFile.State state) {
        return new File(this.cachePath, state.name());
    }

    File getTempFile(ParseFile.State state) {
        if (state.url() == null) {
            return null;
        }
        return new File(this.cachePath, state.url() + ".tmp");
    }

    public boolean isDataAvailable(ParseFile.State state) {
        return getCacheFile(state).exists();
    }

    public void clearCache() {
        File[] files = this.cachePath.listFiles();
        if (files != null) {
            for (File file : files) {
                ParseFileUtils.deleteQuietly(file);
            }
        }
    }

    public Task<ParseFile.State> saveAsync(final ParseFile.State state, final byte[] data, String sessionToken, ProgressCallback uploadProgressCallback, Task<Void> cancellationToken) {
        if (state.url() != null) {
            return Task.forResult(state);
        }
        if (cancellationToken != null && cancellationToken.isCancelled()) {
            return Task.cancelled();
        }
        ParseRESTCommand command = new ParseRESTFileCommand.Builder().fileName(state.name()).data(data).contentType(state.mimeType()).sessionToken(sessionToken).build();
        command.enableRetrying();
        return command.executeAsync(this.restClient, uploadProgressCallback, (ProgressCallback) null, cancellationToken).onSuccess(new Continuation<JSONObject, ParseFile.State>() { // from class: com.parse.ParseFileController.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseFile.State then(Task<JSONObject> task) throws Exception {
                JSONObject result = task.getResult();
                ParseFile.State newState = new ParseFile.State.Builder(state).name(result.getString("name")).url(result.getString("url")).build();
                try {
                    ParseFileUtils.writeByteArrayToFile(ParseFileController.this.getCacheFile(newState), data);
                } catch (IOException e) {
                }
                return newState;
            }
        }, ParseExecutors.io());
    }

    public Task<ParseFile.State> saveAsync(final ParseFile.State state, final File file, String sessionToken, ProgressCallback uploadProgressCallback, Task<Void> cancellationToken) {
        if (state.url() != null) {
            return Task.forResult(state);
        }
        if (cancellationToken != null && cancellationToken.isCancelled()) {
            return Task.cancelled();
        }
        ParseRESTCommand command = new ParseRESTFileCommand.Builder().fileName(state.name()).file(file).contentType(state.mimeType()).sessionToken(sessionToken).build();
        command.enableRetrying();
        return command.executeAsync(this.restClient, uploadProgressCallback, (ProgressCallback) null, cancellationToken).onSuccess(new Continuation<JSONObject, ParseFile.State>() { // from class: com.parse.ParseFileController.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseFile.State then(Task<JSONObject> task) throws Exception {
                JSONObject result = task.getResult();
                ParseFile.State newState = new ParseFile.State.Builder(state).name(result.getString("name")).url(result.getString("url")).build();
                try {
                    ParseFileUtils.copyFile(file, ParseFileController.this.getCacheFile(newState));
                } catch (IOException e) {
                }
                return newState;
            }
        }, ParseExecutors.io());
    }

    public Task<File> fetchAsync(final ParseFile.State state, String sessionToken, final ProgressCallback downloadProgressCallback, final Task<Void> cancellationToken) {
        if (cancellationToken != null && cancellationToken.isCancelled()) {
            return Task.cancelled();
        }
        final File cacheFile = getCacheFile(state);
        return Task.call(new Callable<Boolean>() { // from class: com.parse.ParseFileController.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(cacheFile.exists());
            }
        }, ParseExecutors.io()).continueWithTask(new Continuation<Boolean, Task<File>>() { // from class: com.parse.ParseFileController.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<File> then(Task<Boolean> task) throws Exception {
                if (task.getResult().booleanValue()) {
                    return Task.forResult(cacheFile);
                }
                if (cancellationToken != null && cancellationToken.isCancelled()) {
                    return Task.cancelled();
                }
                final File tempFile = ParseFileController.this.getTempFile(state);
                return new ParseAWSRequest(ParseHttpRequest.Method.GET, state.url(), tempFile).executeAsync(ParseFileController.this.awsClient(), (ProgressCallback) null, downloadProgressCallback, cancellationToken).continueWithTask(new Continuation<Void, Task<File>>() { // from class: com.parse.ParseFileController.3.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<File> then(Task<Void> task2) throws Exception {
                        if (cancellationToken != null && cancellationToken.isCancelled()) {
                            throw new CancellationException();
                        } else if (task2.isFaulted()) {
                            ParseFileUtils.deleteQuietly(tempFile);
                            return task2.cast();
                        } else {
                            ParseFileUtils.deleteQuietly(cacheFile);
                            ParseFileUtils.moveFile(tempFile, cacheFile);
                            return Task.forResult(cacheFile);
                        }
                    }
                }, ParseExecutors.io());
            }
        });
    }
}
