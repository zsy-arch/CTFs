package com.yolanda.nohttp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.yolanda.nohttp.tools.FileUtil;
import java.io.File;

/* loaded from: classes2.dex */
public class FileBinary implements Binary {
    private static final Object HANDLER_LOCK = new Object();
    private static Handler sProgressHandler;
    private File file;
    private String fileName;
    private int handlerWhat;
    private boolean isCancel;
    private boolean isFinish;
    private OnUploadListener mUploadListener;
    private String mimeType;

    public FileBinary(File file) {
        this(file, file.getName());
    }

    public FileBinary(File file, String fileName) {
        this(file, fileName, null);
    }

    public FileBinary(File file, String fileName, String mimeType) {
        this.isCancel = false;
        this.isFinish = false;
        if (file == null) {
            Logger.w("File == null");
        } else if (!file.exists()) {
            Logger.w("File isn't exists");
        }
        this.file = file;
        this.fileName = fileName;
        this.mimeType = mimeType;
    }

    public void setUploadListener(int what, OnUploadListener mProgressHandler) {
        this.handlerWhat = what;
        this.mUploadListener = mProgressHandler;
    }

    @Override // com.yolanda.nohttp.Binary
    public long getLength() {
        if (this.file == null || !this.file.exists()) {
            return 0L;
        }
        return this.file.length();
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    @Override // com.yolanda.nohttp.Binary
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onWriteBinary(java.io.OutputStream r25) {
        /*
            Method dump skipped, instructions count: 483
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yolanda.nohttp.FileBinary.onWriteBinary(java.io.OutputStream):void");
    }

    @Override // com.yolanda.nohttp.Binary
    public String getFileName() {
        return this.fileName;
    }

    @Override // com.yolanda.nohttp.Binary
    public String getMimeType() {
        if (TextUtils.isEmpty(this.mimeType)) {
            this.mimeType = FileUtil.getMimeTypeByUrl(this.file.getAbsolutePath());
            if (TextUtils.isEmpty(this.mimeType)) {
                this.mimeType = "application/octet-stream";
            }
        }
        return this.mimeType;
    }

    @Override // com.yolanda.nohttp.able.CancelAble
    public void cancel(boolean cancel) {
        this.isCancel = cancel;
    }

    @Override // com.yolanda.nohttp.able.CancelAble
    public boolean isCanceled() {
        return !this.isCancel;
    }

    @Override // com.yolanda.nohttp.able.CancelAble
    public void toggleCancel() {
        this.isCancel = !this.isCancel;
    }

    @Override // com.yolanda.nohttp.able.FinishAble
    public boolean isFinished() {
        return this.isFinish;
    }

    @Override // com.yolanda.nohttp.able.FinishAble
    public void finish(boolean finish) {
        this.isFinish = finish;
    }

    @Override // com.yolanda.nohttp.able.FinishAble
    public void toggleFinish() {
        this.isFinish = !this.isFinish;
    }

    /* loaded from: classes2.dex */
    private class UploadPoster implements Runnable {
        public static final int ON_CANCEL = 1;
        public static final int ON_ERROR = 4;
        public static final int ON_FINISH = 3;
        public static final int ON_PROGRESS = 2;
        public static final int ON_START = 0;
        private int command;
        private Exception exception;
        private final OnUploadListener mOnUploadListener;
        private int progress;
        private final int what;

        public UploadPoster(int what, OnUploadListener onUploadListener) {
            this.what = what;
            this.mOnUploadListener = onUploadListener;
        }

        public void start() {
            this.command = 0;
        }

        public void cancel() {
            this.command = 1;
        }

        public void progress(int progress) {
            this.command = 2;
            this.progress = progress;
        }

        public void finish() {
            this.command = 3;
        }

        public void error(Exception exception) {
            this.command = 4;
            this.exception = exception;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mOnUploadListener == null) {
                return;
            }
            if (this.command == 0) {
                this.mOnUploadListener.onStart(this.what);
            } else if (this.command == 3) {
                this.mOnUploadListener.onFinish(this.what);
            } else if (this.command == 2) {
                this.mOnUploadListener.onProgress(this.what, this.progress);
            } else if (this.command == 1) {
                this.mOnUploadListener.onCancel(this.what);
            } else if (this.command == 4) {
                this.mOnUploadListener.onError(this.what, this.exception);
            }
        }
    }

    private Handler getPosterHandler() {
        synchronized (HANDLER_LOCK) {
            if (sProgressHandler == null) {
                sProgressHandler = new Handler(Looper.getMainLooper());
            }
        }
        return sProgressHandler;
    }
}
