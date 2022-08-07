package com.parse;

import android.os.Build;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class ParseRequest<Response> {
    protected static final int DEFAULT_MAX_RETRIES = 4;
    private static final long KEEP_ALIVE_TIME = 1;
    private static final int MAX_QUEUE_SIZE = 128;
    private int maxRetries;
    ParseHttpRequest.Method method;
    String url;
    private static final ThreadFactory sThreadFactory = new ThreadFactory() { // from class: com.parse.ParseRequest.1
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            return new Thread(r, "ParseRequest.NETWORK_EXECUTOR-thread-" + this.mCount.getAndIncrement());
        }
    };
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = (CPU_COUNT * 2) + 1;
    private static final int MAX_POOL_SIZE = ((CPU_COUNT * 2) * 2) + 1;
    static final ExecutorService NETWORK_EXECUTOR = newThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(128), sThreadFactory);
    static final long DEFAULT_INITIAL_RETRY_DELAY = 1000;
    private static long defaultInitialRetryDelay = DEFAULT_INITIAL_RETRY_DELAY;

    protected abstract Task<Response> onResponseAsync(ParseHttpResponse parseHttpResponse, ProgressCallback progressCallback);

    private static ThreadPoolExecutor newThreadPoolExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue, threadFactory);
        if (Build.VERSION.SDK_INT >= 9) {
            executor.allowCoreThreadTimeOut(true);
        }
        return executor;
    }

    public static void setDefaultInitialRetryDelay(long delay) {
        defaultInitialRetryDelay = delay;
    }

    public static long getDefaultInitialRetryDelay() {
        return defaultInitialRetryDelay;
    }

    public ParseRequest(String url) {
        this(ParseHttpRequest.Method.GET, url);
    }

    public ParseRequest(ParseHttpRequest.Method method, String url) {
        this.maxRetries = 4;
        this.method = method;
        this.url = url;
    }

    public void setMaxRetries(int max) {
        this.maxRetries = max;
    }

    protected ParseHttpBody newBody(ProgressCallback uploadProgressCallback) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ParseHttpRequest newRequest(ParseHttpRequest.Method method, String url, ProgressCallback uploadProgressCallback) {
        ParseHttpRequest.Builder requestBuilder = new ParseHttpRequest.Builder().setMethod(method).setUrl(url);
        switch (method) {
            case GET:
            case DELETE:
                break;
            default:
                throw new IllegalStateException("Invalid method " + method);
            case POST:
            case PUT:
                requestBuilder.setBody(newBody(uploadProgressCallback));
                break;
        }
        return requestBuilder.build();
    }

    private Task<Response> sendOneRequestAsync(final ParseHttpClient client, final ParseHttpRequest request, final ProgressCallback downloadProgressCallback) {
        return Task.forResult(null).onSuccessTask(new Continuation<Void, Task<Response>>() { // from class: com.parse.ParseRequest.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Response> then(Task<Void> task) throws Exception {
                return ParseRequest.this.onResponseAsync(client.execute(request), downloadProgressCallback);
            }
        }, NETWORK_EXECUTOR).continueWithTask(new Continuation<Response, Task<Response>>() { // from class: com.parse.ParseRequest.2
            @Override // bolts.Continuation
            public Task<Response> then(Task<Response> task) throws Exception {
                if (!task.isFaulted()) {
                    return task;
                }
                Exception error = task.getError();
                if (error instanceof IOException) {
                    return Task.forError(ParseRequest.this.newTemporaryException("i/o failure", error));
                }
                return task;
            }
        }, Task.BACKGROUND_EXECUTOR);
    }

    public Task<Response> executeAsync(ParseHttpClient client) {
        return executeAsync(client, (ProgressCallback) null, (ProgressCallback) null, (Task<Void>) null);
    }

    public Task<Response> executeAsync(ParseHttpClient client, Task<Void> cancellationToken) {
        return executeAsync(client, (ProgressCallback) null, (ProgressCallback) null, cancellationToken);
    }

    public Task<Response> executeAsync(ParseHttpClient client, ProgressCallback uploadProgressCallback, ProgressCallback downloadProgressCallback) {
        return executeAsync(client, uploadProgressCallback, downloadProgressCallback, (Task<Void>) null);
    }

    public Task<Response> executeAsync(ParseHttpClient client, ProgressCallback uploadProgressCallback, ProgressCallback downloadProgressCallback, Task<Void> cancellationToken) {
        return executeAsync(client, newRequest(this.method, this.url, uploadProgressCallback), downloadProgressCallback, cancellationToken);
    }

    private Task<Response> executeAsync(ParseHttpClient client, ParseHttpRequest request, ProgressCallback downloadProgressCallback, Task<Void> cancellationToken) {
        return executeAsync(client, request, 0, defaultInitialRetryDelay + ((long) (defaultInitialRetryDelay * Math.random())), downloadProgressCallback, cancellationToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Response> executeAsync(final ParseHttpClient client, final ParseHttpRequest request, final int attemptsMade, final long delay, final ProgressCallback downloadProgressCallback, final Task<Void> cancellationToken) {
        return (cancellationToken == null || !cancellationToken.isCancelled()) ? (Task<Response>) sendOneRequestAsync(client, request, downloadProgressCallback).continueWithTask((Continuation<Response, Task<Response>>) new Continuation<Response, Task<Response>>() { // from class: com.parse.ParseRequest.4
            @Override // bolts.Continuation
            public Task<Response> then(Task<Response> task) throws Exception {
                Exception e = task.getError();
                if (!task.isFaulted() || !(e instanceof ParseException)) {
                    return task;
                }
                if (cancellationToken != null && cancellationToken.isCancelled()) {
                    return Task.cancelled();
                }
                if (((e instanceof ParseRequestException) && ((ParseRequestException) e).isPermanentFailure) || attemptsMade >= ParseRequest.this.maxRetries) {
                    return task;
                }
                PLog.i("com.parse.ParseRequest", "Request failed. Waiting " + delay + " milliseconds before attempt #" + (attemptsMade + 1));
                final TaskCompletionSource<Response> retryTask = new TaskCompletionSource<>();
                ParseExecutors.scheduled().schedule(new Runnable() { // from class: com.parse.ParseRequest.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ParseRequest.this.executeAsync(client, request, attemptsMade + 1, delay * 2, downloadProgressCallback, cancellationToken).continueWithTask(new Continuation<Response, Task<Void>>() { // from class: com.parse.ParseRequest.4.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // bolts.Continuation
                            public Task<Void> then(Task<Response> task2) throws Exception {
                                if (task2.isCancelled()) {
                                    retryTask.setCancelled();
                                    return null;
                                } else if (task2.isFaulted()) {
                                    retryTask.setError(task2.getError());
                                    return null;
                                } else {
                                    retryTask.setResult(task2.getResult());
                                    return null;
                                }
                            }
                        });
                    }
                }, delay, TimeUnit.MILLISECONDS);
                return retryTask.getTask();
            }
        }) : Task.cancelled();
    }

    protected ParseException newPermanentException(int code, String message) {
        ParseRequestException e = new ParseRequestException(code, message);
        e.isPermanentFailure = true;
        return e;
    }

    protected ParseException newTemporaryException(int code, String message) {
        ParseRequestException e = new ParseRequestException(code, message);
        e.isPermanentFailure = false;
        return e;
    }

    protected ParseException newTemporaryException(String message, Throwable t) {
        ParseRequestException e = new ParseRequestException(100, message, t);
        e.isPermanentFailure = false;
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ParseRequestException extends ParseException {
        boolean isPermanentFailure = false;

        public ParseRequestException(int theCode, String theMessage) {
            super(theCode, theMessage);
        }

        public ParseRequestException(int theCode, String message, Throwable cause) {
            super(theCode, message, cause);
        }
    }
}
