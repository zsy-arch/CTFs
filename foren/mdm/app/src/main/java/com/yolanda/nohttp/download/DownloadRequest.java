package com.yolanda.nohttp.download;

import com.yolanda.nohttp.Request;

/* loaded from: classes2.dex */
public interface DownloadRequest extends Request<Void> {
    public static final int STATUS_FINISH = 2;
    public static final int STATUS_RESTART = 0;
    public static final int STATUS_RESUME = 1;

    int checkBeforeStatus();

    String getFileDir();

    String getFileName();

    boolean isDeleteOld();

    boolean isRange();
}
