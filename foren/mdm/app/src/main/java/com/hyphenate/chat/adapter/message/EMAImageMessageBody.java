package com.hyphenate.chat.adapter.message;

import com.hyphenate.chat.adapter.message.EMAFileMessageBody;

/* loaded from: classes2.dex */
public class EMAImageMessageBody extends EMAFileMessageBody {
    public long fileLength;
    public double height;
    public int thumbnailDownloadStatus;
    public String thumbnailLocalPath;
    public String thumbnailRemotePath;
    public String thumbnailSecretKey;
    public double width;

    private EMAImageMessageBody() {
        super("", 1);
        nativeInit("", "");
    }

    public EMAImageMessageBody(EMAImageMessageBody eMAImageMessageBody) {
        super("", 1);
        nativeInit(eMAImageMessageBody);
    }

    public EMAImageMessageBody(String str, String str2) {
        super(str, 1);
        nativeInit(str, str2);
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

    native void nativeInit(EMAImageMessageBody eMAImageMessageBody);

    native void nativeInit(String str, String str2);

    native int nativeheight();

    native void nativesetSize(int i, int i2);

    native void nativesetThumbnailDisplayName(String str);

    native void nativesetThumbnailDownloadStatus(int i);

    native void nativesetThumbnailFileLength(long j);

    native void nativesetThumbnailLocalPath(String str);

    native void nativesetThumbnailRemotePath(String str);

    native void nativesetThumbnailSecretKey(String str);

    native void nativesetThumbnailSize(int i, int i2);

    native String nativethumbnailDisplayName();

    native int nativethumbnailDownloadStatus();

    native long nativethumbnailFileLength();

    native int nativethumbnailHeight();

    native String nativethumbnailLocalPath();

    native String nativethumbnailRemotePath();

    native String nativethumbnailSecretKey();

    native int nativethumbnailWidth();

    native int nativewidth();

    public void setSize(int i, int i2) {
        nativesetSize(i, i2);
    }

    public void setThumbnailDisplayName(String str) {
        nativesetThumbnailDisplayName(str);
    }

    public void setThumbnailDownloadStatus(EMAFileMessageBody.EMADownloadStatus eMADownloadStatus) {
        nativesetThumbnailDownloadStatus(eMADownloadStatus.ordinal());
    }

    public void setThumbnailFileLength(long j) {
        nativesetThumbnailFileLength(j);
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

    public void setThumbnailSize(int i, int i2) {
        nativesetThumbnailSize(i, i2);
    }

    public String thumbnailDisplayName() {
        return nativethumbnailDisplayName();
    }

    public EMAFileMessageBody.EMADownloadStatus thumbnailDownloadStatus() {
        int nativethumbnailDownloadStatus = nativethumbnailDownloadStatus();
        return nativethumbnailDownloadStatus == EMAFileMessageBody.EMADownloadStatus.DOWNLOADING.ordinal() ? EMAFileMessageBody.EMADownloadStatus.DOWNLOADING : nativethumbnailDownloadStatus == EMAFileMessageBody.EMADownloadStatus.SUCCESSED.ordinal() ? EMAFileMessageBody.EMADownloadStatus.SUCCESSED : nativethumbnailDownloadStatus == EMAFileMessageBody.EMADownloadStatus.FAILED.ordinal() ? EMAFileMessageBody.EMADownloadStatus.FAILED : EMAFileMessageBody.EMADownloadStatus.PENDING;
    }

    public long thumbnailFileLength() {
        return nativethumbnailFileLength();
    }

    int thumbnailHeight() {
        return nativethumbnailHeight();
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

    public int thumbnailWidth() {
        return nativethumbnailWidth();
    }

    public int width() {
        return nativewidth();
    }
}
