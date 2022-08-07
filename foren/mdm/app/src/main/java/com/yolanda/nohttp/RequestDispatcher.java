package com.yolanda.nohttp;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes2.dex */
public class RequestDispatcher extends Thread {
    private static final Object HANDLER_LOCK = new Object();
    private static Handler sRequestHandler;
    private final ImplRestParser mImplRestParser;
    private final BlockingQueue<HttpRequest<?>> mRequestQueue;
    private volatile boolean mRunning = true;

    public RequestDispatcher(BlockingQueue<HttpRequest<?>> requestQueue, ImplRestParser implRestParser) {
        this.mRequestQueue = requestQueue;
        this.mImplRestParser = implRestParser;
    }

    public void quit() {
        this.mRunning = false;
        interrupt();
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        while (this.mRunning) {
            try {
                HttpRequest<?> request = this.mRequestQueue.take();
                if (request.request.isCanceled()) {
                    Logger.d(request.request.url() + " is canceled.");
                } else {
                    try {
                        request.request.start(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ThreadPoster startThread = new ThreadPoster(request.what, request.responseListener);
                    startThread.onStart();
                    getPosterHandler().post(startThread);
                    Response<?> response = this.mImplRestParser.parserRequest(request.request);
                    ThreadPoster finishThread = new ThreadPoster(request.what, request.responseListener);
                    finishThread.onFinished();
                    getPosterHandler().post(finishThread);
                    request.request.finish(true);
                    request.request.queue(false);
                    if (request.request.isCanceled()) {
                        Logger.d(request.request.url() + " finish, but it's canceled.");
                    } else {
                        ThreadPoster responseThread = new ThreadPoster(request.what, request.responseListener);
                        responseThread.onResponse(response);
                        getPosterHandler().post(responseThread);
                    }
                }
            } catch (InterruptedException e2) {
                if (!this.mRunning) {
                    return;
                }
            }
        }
    }

    private Handler getPosterHandler() {
        synchronized (HANDLER_LOCK) {
            if (sRequestHandler == null) {
                sRequestHandler = new Handler(Looper.getMainLooper());
            }
        }
        return sRequestHandler;
    }

    /* loaded from: classes2.dex */
    private class ThreadPoster implements Runnable {
        public static final int COMMAND_FINISH = 2;
        public static final int COMMAND_RESPONSE = 1;
        public static final int COMMAND_START = 0;
        private int command;
        private Response response;
        private final OnResponseListener responseListener;
        private final int what;

        public ThreadPoster(int what, OnResponseListener<?> responseListener) {
            this.what = what;
            this.responseListener = responseListener;
        }

        public void onStart() {
            this.command = 0;
        }

        public void onResponse(Response response) {
            this.command = 1;
            this.response = response;
        }

        public void onFinished() {
            this.command = 2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.responseListener == null) {
                return;
            }
            if (this.command == 0) {
                this.responseListener.onStart(this.what);
            } else if (this.command == 2) {
                this.responseListener.onFinish(this.what);
            } else if (this.command != 1) {
            } else {
                if (this.response == null) {
                    this.responseListener.onFailed(this.what, null, null, new Exception("Unknown abnormal."), 0, 0L);
                } else if (this.response.isSucceed()) {
                    this.responseListener.onSucceed(this.what, this.response);
                } else {
                    Headers headers = this.response.getHeaders();
                    this.responseListener.onFailed(this.what, this.response.url(), this.response.getTag(), this.response.getException(), headers == null ? -1 : headers.getResponseCode(), this.response.getNetworkMillis());
                }
            }
        }
    }
}
