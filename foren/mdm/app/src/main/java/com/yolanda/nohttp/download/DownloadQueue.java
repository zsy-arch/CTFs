package com.yolanda.nohttp.download;

import com.yolanda.nohttp.Logger;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes2.dex */
public class DownloadQueue {
    private DownloadDispatcher[] mDispatchers;
    private final LinkedBlockingQueue<NetworkDownloadRequest> mDownloadQueue = new LinkedBlockingQueue<>();
    private final Downloader mDownloader;

    public DownloadQueue(Downloader downloader, int threadPoolSize) {
        this.mDownloader = downloader;
        this.mDispatchers = new DownloadDispatcher[threadPoolSize];
    }

    public void start() {
        stop();
        for (int i = 0; i < this.mDispatchers.length; i++) {
            DownloadDispatcher networkDispatcher = new DownloadDispatcher(this.mDownloadQueue, this.mDownloader);
            this.mDispatchers[i] = networkDispatcher;
            networkDispatcher.start();
        }
    }

    public void add(int what, DownloadRequest downloadRequest, DownloadListener downloadListener) {
        if (downloadRequest.isQueue()) {
            Logger.w("This request has been in the queue");
            return;
        }
        downloadRequest.queue(true);
        downloadRequest.start(false);
        downloadRequest.cancel(false);
        downloadRequest.finish(false);
        this.mDownloadQueue.add(new NetworkDownloadRequest(what, downloadRequest, downloadListener));
    }

    public void stop() {
        DownloadDispatcher[] downloadDispatcherArr = this.mDispatchers;
        for (DownloadDispatcher mDispatcher : downloadDispatcherArr) {
            if (mDispatcher != null) {
                mDispatcher.quit();
            }
        }
    }

    public void cancelBySign(Object sign) {
        synchronized (this.mDownloadQueue) {
            Iterator<NetworkDownloadRequest> it = this.mDownloadQueue.iterator();
            while (it.hasNext()) {
                it.next().downloadRequest.cancelBySign(sign);
            }
        }
    }

    public void cancelAll() {
        synchronized (this.mDownloadQueue) {
            Iterator<NetworkDownloadRequest> it = this.mDownloadQueue.iterator();
            while (it.hasNext()) {
                it.next().downloadRequest.cancel(true);
            }
        }
    }
}
