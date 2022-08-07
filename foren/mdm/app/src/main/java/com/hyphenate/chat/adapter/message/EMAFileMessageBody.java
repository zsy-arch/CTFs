package com.hyphenate.chat.adapter.message;

import com.hyphenate.chat.adapter.EMACallback;

/* loaded from: classes2.dex */
public class EMAFileMessageBody extends EMAMessageBody {
    public static final int EMDownloadStatus_DOWNLOADING = 0;
    public static final int EMDownloadStatus_FAILED = 2;
    public static final int EMDownloadStatus_PENDING = 3;
    public static final int EMDownloadStatus_SUCCESSED = 1;

    /* loaded from: classes2.dex */
    public enum EMADownloadStatus {
        DOWNLOADING,
        SUCCESSED,
        FAILED,
        PENDING
    }

    private EMAFileMessageBody() {
        this("", 5);
    }

    public EMAFileMessageBody(EMAFileMessageBody eMAFileMessageBody) {
        nativeInit(eMAFileMessageBody);
    }

    public EMAFileMessageBody(String str) {
        this(str, 5);
    }

    public EMAFileMessageBody(String str, int i) {
        if (i == 5) {
            nativeInit(str, i);
        }
        this.type = i;
    }

    public String displayName() {
        return nativedisplayName();
    }

    public EMADownloadStatus downloadStatus() {
        int nativedownloadStatus = nativedownloadStatus();
        return nativedownloadStatus == EMADownloadStatus.DOWNLOADING.ordinal() ? EMADownloadStatus.DOWNLOADING : nativedownloadStatus == EMADownloadStatus.SUCCESSED.ordinal() ? EMADownloadStatus.SUCCESSED : nativedownloadStatus == EMADownloadStatus.FAILED.ordinal() ? EMADownloadStatus.FAILED : EMADownloadStatus.PENDING;
    }

    public long fileLength() {
        return nativefileLength();
    }

    public void finalize() throws Throwable {
        if (this.type == 5) {
            nativeFinalize();
        }
        super.finalize();
    }

    public String getLocalUrl() {
        return nativelocalPath();
    }

    public String getRemoteUrl() {
        return nativeremotePath();
    }

    public String getSecret() {
        return nativesecretKey();
    }

    native void nativeFinalize();

    native void nativeInit(EMAFileMessageBody eMAFileMessageBody);

    native void nativeInit(String str, int i);

    native void nativeSetDownloadCallback(EMACallback eMACallback);

    native String nativedisplayName();

    native int nativedownloadStatus();

    native long nativefileLength();

    native String nativelocalPath();

    native String nativeremotePath();

    native String nativesecretKey();

    native void nativesetDisplayName(String str);

    native void nativesetDownloadStatus(int i);

    native void nativesetFileLength(long j);

    native void nativesetLocalPath(String str);

    native void nativesetRemotePath(String str);

    native void nativesetSecretKey(String str);

    public void setDisplayName(String str) {
        nativesetDisplayName(str);
    }

    public void setDownloadCallback(EMACallback eMACallback) {
        nativeSetDownloadCallback(eMACallback);
    }

    public void setDownloadStatus(EMADownloadStatus eMADownloadStatus) {
        nativesetDownloadStatus(eMADownloadStatus.ordinal());
    }

    public void setFileLength(long j) {
        nativesetFileLength(j);
    }

    public void setLocalPath(String str) {
        nativesetLocalPath(str);
    }

    public void setRemotePath(String str) {
        nativesetRemotePath(str);
    }

    public void setSecretKey(String str) {
        nativesetSecretKey(str);
    }
}
