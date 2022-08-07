package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alimama.mobile.csdk.umupdate.a.f;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class HttpManager implements Transport {
    private static final int CORE_SIZE = 10;
    private static final int KEEP_ALIVE_TIME = 3;
    private static final int POOL_SIZE = 11;
    private static final int QUEUE_SIZE = 20;
    public static final String TAG = "HttpManager";
    private long mAllConnectTimes;
    private long mAllDataSize;
    private long mAllSocketTimes;
    Context mContext;
    private AndroidHttpClient mHttpClient;
    private ThreadPoolExecutor mParallelExecutor;
    private int mRequestTimes;
    private static HttpManager HTTP_MANAGER = null;
    private static final ThreadFactory THREADFACTORY = new ThreadFactory() { // from class: com.alipay.android.phone.mrpc.core.HttpManager.2
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #" + this.mCount.getAndIncrement());
            thread.setPriority(4);
            return thread;
        }
    };

    public HttpManager(Context context) {
        this.mContext = context;
        init();
    }

    public static final HttpManager getInstance(Context context) {
        return HTTP_MANAGER != null ? HTTP_MANAGER : syncCreateInstance(context);
    }

    private void init() {
        this.mHttpClient = AndroidHttpClient.newInstance(f.a);
        this.mParallelExecutor = new ThreadPoolExecutor(10, 11, 3L, TimeUnit.SECONDS, new ArrayBlockingQueue(20), THREADFACTORY, new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            this.mParallelExecutor.allowCoreThreadTimeOut(true);
        } catch (Exception e) {
        }
        CookieSyncManager.createInstance(this.mContext);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    private FutureTask<Response> makeTask(final HttpWorker httpWorker) {
        return new FutureTask<Response>(httpWorker) { // from class: com.alipay.android.phone.mrpc.core.HttpManager.1
            @Override // java.util.concurrent.FutureTask
            protected void done() {
                HttpUrlRequest request = httpWorker.getRequest();
                TransportCallback callback = request.getCallback();
                if (callback == null) {
                    super.done();
                    return;
                }
                try {
                    Response response = get();
                    if (isCancelled() || request.isCanceled()) {
                        request.cancel();
                        if (!isCancelled() || !isDone()) {
                            cancel(false);
                        }
                        callback.onCancelled(request);
                    } else if (response != null) {
                        callback.onPostExecute(request, response);
                    }
                } catch (InterruptedException e) {
                    callback.onFailed(request, 7, String.valueOf(e));
                } catch (CancellationException e2) {
                    request.cancel();
                    callback.onCancelled(request);
                } catch (ExecutionException e3) {
                    if (e3.getCause() == null || !(e3.getCause() instanceof HttpException)) {
                        callback.onFailed(request, 6, String.valueOf(e3));
                        return;
                    }
                    HttpException httpException = (HttpException) e3.getCause();
                    callback.onFailed(request, httpException.getCode(), httpException.getMsg());
                } catch (Throwable th) {
                    throw new RuntimeException("An error occured while executing http request", th);
                }
            }
        };
    }

    private static final synchronized HttpManager syncCreateInstance(Context context) {
        HttpManager httpManager;
        synchronized (HttpManager.class) {
            if (HTTP_MANAGER != null) {
                httpManager = HTTP_MANAGER;
            } else {
                httpManager = new HttpManager(context);
                HTTP_MANAGER = httpManager;
            }
        }
        return httpManager;
    }

    public void addConnectTime(long j) {
        this.mAllConnectTimes += j;
        this.mRequestTimes++;
    }

    public void addDataSize(long j) {
        this.mAllDataSize += j;
    }

    public void addSocketTime(long j) {
        this.mAllSocketTimes += j;
    }

    public void close() {
        if (this.mParallelExecutor != null) {
            this.mParallelExecutor.shutdown();
            this.mParallelExecutor = null;
        }
        if (this.mHttpClient != null) {
            this.mHttpClient.close();
        }
        this.mHttpClient = null;
    }

    public String dumpPerf() {
        return String.format(TAG + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times", Integer.valueOf(this.mParallelExecutor.getActiveCount()), Long.valueOf(this.mParallelExecutor.getCompletedTaskCount()), Long.valueOf(this.mParallelExecutor.getTaskCount()), Long.valueOf(getAverageSpeed()), Long.valueOf(getAverageConnectTime()), Long.valueOf(this.mAllDataSize), Long.valueOf(this.mAllConnectTimes), Long.valueOf(this.mAllSocketTimes), Integer.valueOf(this.mRequestTimes));
    }

    @Override // com.alipay.android.phone.mrpc.core.Transport
    public Future<Response> execute(Request request) {
        if (!(request instanceof HttpUrlRequest)) {
            throw new RuntimeException("request send error.");
        }
        if (MiscUtils.isDebugger(this.mContext)) {
            dumpPerf();
        }
        FutureTask<Response> makeTask = makeTask(generateWorker((HttpUrlRequest) request));
        this.mParallelExecutor.execute(makeTask);
        return makeTask;
    }

    protected HttpWorker generateWorker(HttpUrlRequest httpUrlRequest) {
        return new HttpWorker(this, httpUrlRequest);
    }

    public long getAverageConnectTime() {
        if (this.mRequestTimes == 0) {
            return 0L;
        }
        return this.mAllConnectTimes / this.mRequestTimes;
    }

    public long getAverageSpeed() {
        if (this.mAllSocketTimes == 0) {
            return 0L;
        }
        return ((this.mAllDataSize * 1000) / this.mAllSocketTimes) >> 10;
    }

    public AndroidHttpClient getHttpClient() {
        return this.mHttpClient;
    }
}
