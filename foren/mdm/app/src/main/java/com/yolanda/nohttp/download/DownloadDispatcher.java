package com.yolanda.nohttp.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.yolanda.nohttp.Headers;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class DownloadDispatcher extends Thread {
    private static final Object HANDLER_LOCK = new Object();
    private static Handler sDownloadHandler;
    private final BlockingQueue<NetworkDownloadRequest> mDownloadQueue;
    private final Downloader mDownloader;
    private volatile boolean mQuit = false;

    public DownloadDispatcher(BlockingQueue<NetworkDownloadRequest> downloadQueue, Downloader downloader) {
        this.mDownloadQueue = downloadQueue;
        this.mDownloader = downloader;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                final NetworkDownloadRequest request = this.mDownloadQueue.take();
                if (!request.downloadRequest.isCanceled()) {
                    request.downloadRequest.start(true);
                    this.mDownloader.download(request.what, request.downloadRequest, new DownloadListener() { // from class: com.yolanda.nohttp.download.DownloadDispatcher.1
                        @Override // com.yolanda.nohttp.download.DownloadListener
                        public void onStart(int what, boolean isResume, long beforeLength, Headers headers, long allCount) {
                            ThreadPoster threadPoster = new ThreadPoster(request.what, request.downloadListener);
                            threadPoster.onStart(isResume, beforeLength, headers, allCount);
                            DownloadDispatcher.this.getPosterHandler().post(threadPoster);
                        }

                        @Override // com.yolanda.nohttp.download.DownloadListener
                        public void onDownloadError(int what, Exception exception) {
                            ThreadPoster threadPoster = new ThreadPoster(request.what, request.downloadListener);
                            threadPoster.onError(exception);
                            DownloadDispatcher.this.getPosterHandler().post(threadPoster);
                        }

                        @Override // com.yolanda.nohttp.download.DownloadListener
                        public void onProgress(int what, int progress, long fileCount) {
                            ThreadPoster threadPoster = new ThreadPoster(request.what, request.downloadListener);
                            threadPoster.onProgress(progress, fileCount);
                            DownloadDispatcher.this.getPosterHandler().post(threadPoster);
                        }

                        @Override // com.yolanda.nohttp.download.DownloadListener
                        public void onFinish(int what, String filePath) {
                            ThreadPoster threadPoster = new ThreadPoster(request.what, request.downloadListener);
                            threadPoster.onFinish(filePath);
                            DownloadDispatcher.this.getPosterHandler().post(threadPoster);
                        }

                        @Override // com.yolanda.nohttp.download.DownloadListener
                        public void onCancel(int what) {
                            ThreadPoster threadPoster = new ThreadPoster(request.what, request.downloadListener);
                            threadPoster.onCancel();
                            DownloadDispatcher.this.getPosterHandler().post(threadPoster);
                        }
                    });
                    request.downloadRequest.finish(true);
                    request.downloadRequest.queue(false);
                }
            } catch (InterruptedException e) {
                if (this.mQuit) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Handler getPosterHandler() {
        synchronized (HANDLER_LOCK) {
            if (sDownloadHandler == null) {
                sDownloadHandler = new Handler(Looper.getMainLooper());
            }
        }
        return sDownloadHandler;
    }

    /* loaded from: classes2.dex */
    private class ThreadPoster implements Runnable {
        public static final int COMMAND_CANCEL = 4;
        public static final int COMMAND_ERROR = 2;
        public static final int COMMAND_FINISH = 3;
        public static final int COMMAND_PROGRESS = 1;
        public static final int COMMAND_START = 0;
        private long allCount;
        private long beforeLength;
        private int command;
        private final DownloadListener downloadListener;
        private Exception exception;
        private long fileCount;
        private String filePath;
        private boolean isResume;
        private int progress;
        private Headers responseHeaders;
        private final int what;

        public ThreadPoster(int what, DownloadListener downloadListener) {
            this.what = what;
            this.downloadListener = downloadListener;
        }

        public void onStart(boolean isResume, long beforeLength, Headers responseHeaders, long allCount) {
            this.command = 0;
            this.isResume = isResume;
            this.beforeLength = beforeLength;
            this.responseHeaders = responseHeaders;
            this.allCount = allCount;
        }

        public void onProgress(int progress, long fileCount) {
            this.command = 1;
            this.progress = progress;
            this.fileCount = fileCount;
        }

        public void onError(Exception exception) {
            this.command = 2;
            this.exception = exception;
        }

        public void onCancel() {
            this.command = 4;
        }

        public void onFinish(String filePath) {
            this.command = 3;
            this.filePath = filePath;
        }

        @Override // java.lang.Runnable
        public void run() {
            switch (this.command) {
                case 0:
                    this.downloadListener.onStart(this.what, this.isResume, this.beforeLength, this.responseHeaders, this.allCount);
                    return;
                case 1:
                    this.downloadListener.onProgress(this.what, this.progress, this.fileCount);
                    return;
                case 2:
                    this.downloadListener.onDownloadError(this.what, this.exception);
                    return;
                case 3:
                    this.downloadListener.onFinish(this.what, this.filePath);
                    return;
                case 4:
                    this.downloadListener.onCancel(this.what);
                    return;
                default:
                    return;
            }
        }
    }
}
