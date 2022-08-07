package com.hyphenate.chat;

import com.hyphenate.chat.adapter.message.EMAFileMessageBody;
import com.hyphenate.chat.adapter.message.EMAImageMessageBody;
import com.hyphenate.chat.adapter.message.EMAVideoMessageBody;
import com.hyphenate.chat.adapter.message.EMAVoiceMessageBody;

/* loaded from: classes2.dex */
public abstract class EMFileMessageBody extends EMMessageBody {

    /* loaded from: classes2.dex */
    public enum EMDownloadStatus {
        DOWNLOADING,
        SUCCESSED,
        FAILED,
        PENDING
    }

    public EMFileMessageBody(EMAFileMessageBody eMAFileMessageBody) {
        this.emaObject = eMAFileMessageBody;
    }

    public EMFileMessageBody(String str) {
        this.emaObject = new EMAFileMessageBody(str);
    }

    public EMFileMessageBody(String str, int i) {
        switch (i) {
            case 1:
                this.emaObject = new EMAImageMessageBody(str, "");
                return;
            case 2:
                this.emaObject = new EMAVideoMessageBody(str, "");
                return;
            case 3:
            default:
                return;
            case 4:
                this.emaObject = new EMAVoiceMessageBody(str, 0);
                return;
            case 5:
                this.emaObject = new EMAFileMessageBody(str, i);
                return;
        }
    }

    public String displayName() {
        return ((EMAFileMessageBody) this.emaObject).displayName();
    }

    public EMDownloadStatus downloadStatus() {
        switch (((EMAFileMessageBody) this.emaObject).downloadStatus()) {
            case DOWNLOADING:
                return EMDownloadStatus.DOWNLOADING;
            case SUCCESSED:
                return EMDownloadStatus.SUCCESSED;
            case FAILED:
                return EMDownloadStatus.FAILED;
            case PENDING:
                return EMDownloadStatus.PENDING;
            default:
                return EMDownloadStatus.SUCCESSED;
        }
    }

    public String getFileName() {
        return ((EMAFileMessageBody) this.emaObject).displayName();
    }

    public String getLocalUrl() {
        return ((EMAFileMessageBody) this.emaObject).getLocalUrl();
    }

    public String getRemoteUrl() {
        return ((EMAFileMessageBody) this.emaObject).getRemoteUrl();
    }

    public String getSecret() {
        return ((EMAFileMessageBody) this.emaObject).getSecret();
    }

    void setDownloadStatus(EMDownloadStatus eMDownloadStatus) {
        ((EMAFileMessageBody) this.emaObject).setDownloadStatus(EMAFileMessageBody.EMADownloadStatus.valueOf(eMDownloadStatus.name()));
    }

    void setFileLength(long j) {
        ((EMAFileMessageBody) this.emaObject).setFileLength(j);
    }

    public void setFileName(String str) {
        ((EMAFileMessageBody) this.emaObject).setDisplayName(str);
    }

    public void setLocalUrl(String str) {
        ((EMAFileMessageBody) this.emaObject).setLocalPath(str);
    }

    public void setRemoteUrl(String str) {
        ((EMAFileMessageBody) this.emaObject).setRemotePath(str);
    }

    public void setSecret(String str) {
        ((EMAFileMessageBody) this.emaObject).setSecretKey(str);
    }
}
