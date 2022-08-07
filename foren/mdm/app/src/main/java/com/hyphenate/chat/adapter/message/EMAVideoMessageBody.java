package com.hyphenate.chat.adapter.message;

import com.hyphenate.chat.adapter.message.EMAFileMessageBody;

/* loaded from: classes2.dex */
public class EMAVideoMessageBody extends EMAFileMessageBody {
    private EMAVideoMessageBody() {
        super("", 2);
        nativeInit("", "");
    }

    public EMAVideoMessageBody(EMAVideoMessageBody eMAVideoMessageBody) {
        super("", 2);
        nativeInit(eMAVideoMessageBody);
    }

    public EMAVideoMessageBody(String str, String str2) {
        super(str, 2);
        nativeInit(str, str2);
    }

    public int duration() {
        return nativeduration();
    }

    @Override // com.hyphenate.chat.adapter.message.EMAFileMessageBody
    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public int height() {
        return nativeheight();
    }

    @Override // com.hyphenate.chat.adapter.message.EMAFileMessageBody
    native void nativeFinalize();

    native void nativeInit(EMAVideoMessageBody eMAVideoMessageBody);

    native void nativeInit(String str, String str2);

    native int nativeduration();

    native int nativeheight();

    native void nativesetDuration(int i);

    native void nativesetSize(int i, int i2);

    native void nativesetThumbnailDownloadStatus(int i);

    native void nativesetThumbnailLocalPath(String str);

    native void nativesetThumbnailRemotePath(String str);

    native void nativesetThumbnailSecretKey(String str);

    native int nativethumbnailDownloadStatus();

    native String nativethumbnailLocalPath();

    native String nativethumbnailRemotePath();

    native String nativethumbnailSecretKey();

    native int nativewidth();

    public void setDuration(int i) {
        nativesetDuration(i);
    }

    public void setSize(int i, int i2) {
        nativesetSize(i, i2);
    }

    public void setThumbnailDownloadStatus(EMAFileMessageBody.EMADownloadStatus eMADownloadStatus) {
        nativesetThumbnailDownloadStatus(eMADownloadStatus.ordinal());
    }

    public void setThumbnailLocalPath(String str) {
        nativesetThumbnailLocalPath(str);
    }

    public void setThumbnailRemotePath(String str) {
        nativesetThumbnailRemotePath(str);
    }

    public void setThumbnailSecretKey(String str) {
        nativesetThumbnailSecretKey(str);
    }

    public EMAFileMessageBody.EMADownloadStatus thumbnailDownloadStatus() {
        int nativethumbnailDownloadStatus = nativethumbnailDownloadStatus();
        return nativethumbnailDownloadStatus == EMAFileMessageBody.EMADownloadStatus.DOWNLOADING.ordinal() ? EMAFileMessageBody.EMADownloadStatus.DOWNLOADING : nativethumbnailDownloadStatus == EMAFileMessageBody.EMADownloadStatus.SUCCESSED.ordinal() ? EMAFileMessageBody.EMADownloadStatus.SUCCESSED : nativethumbnailDownloadStatus == EMAFileMessageBody.EMADownloadStatus.FAILED.ordinal() ? EMAFileMessageBody.EMADownloadStatus.FAILED : EMAFileMessageBody.EMADownloadStatus.PENDING;
    }

    public String thumbnailLocalPath() {
        return nativethumbnailLocalPath();
    }

    public String thumbnailRemotePath() {
        return nativethumbnailRemotePath();
    }

    public String thumbnailSecretKey() {
        return nativethumbnailSecretKey();
    }

    public int width() {
        return nativewidth();
    }
}
