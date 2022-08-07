package com.yolanda.nohttp;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes2.dex */
public class RequestQueue {
    private RequestDispatcher[] mDispatchers;
    private final ImplRestParser mImplRestParser;
    private final LinkedBlockingQueue<HttpRequest<?>> mRequestQueue = new LinkedBlockingQueue<>();

    public RequestQueue(ImplRestParser implRestParser, int threadPoolSize) {
        this.mImplRestParser = implRestParser;
        this.mDispatchers = new RequestDispatcher[threadPoolSize];
    }

    public void start() {
        stop();
        for (int i = 0; i < this.mDispatchers.length; i++) {
            RequestDispatcher networkDispatcher = new RequestDispatcher(this.mRequestQueue, this.mImplRestParser);
            this.mDispatchers[i] = networkDispatcher;
            networkDispatcher.start();
        }
    }

    public <T> void add(int what, Request<T> request, OnResponseListener<T> responseListener) {
        if (request.isQueue()) {
            Logger.w("This request has been in the queue");
            return;
        }
        request.queue(true);
        try {
            request.start(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.cancel(false);
        request.finish(false);
        this.mRequestQueue.add(new HttpRequest<>(what, request, responseListener));
    }

    public void stop() {
        for (int i = 0; i < this.mDispatchers.length; i++) {
            if (this.mDispatchers[i] != null) {
                this.mDispatchers[i].quit();
            }
        }
    }

    public void cancelBySign(Object sign) {
        synchronized (this.mRequestQueue) {
            Iterator<HttpRequest<?>> it = this.mRequestQueue.iterator();
            while (it.hasNext()) {
                it.next().request.cancelBySign(sign);
            }
        }
    }

    public void cancelAll() {
        synchronized (this.mRequestQueue) {
            Iterator<HttpRequest<?>> it = this.mRequestQueue.iterator();
            while (it.hasNext()) {
                it.next().request.cancel(true);
            }
        }
    }
}
