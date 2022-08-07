package com.hyphenate.chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.adapter.message.EMAFileMessageBody;
import com.hyphenate.chat.adapter.message.EMAVideoMessageBody;
import com.hyphenate.util.EMLog;

/* loaded from: classes2.dex */
public class EMVideoMessageBody extends EMFileMessageBody implements Parcelable {
    public static final Parcelable.Creator<EMVideoMessageBody> CREATOR = new Parcelable.Creator<EMVideoMessageBody>() { // from class: com.hyphenate.chat.EMVideoMessageBody.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMVideoMessageBody createFromParcel(Parcel parcel) {
            return new EMVideoMessageBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMVideoMessageBody[] newArray(int i) {
            return new EMVideoMessageBody[i];
        }
    };

    public EMVideoMessageBody() {
        super("", 2);
    }

    private EMVideoMessageBody(Parcel parcel) {
        super("", 2);
        ((EMAVideoMessageBody) this.emaObject).setDisplayName(parcel.readString());
        ((EMAVideoMessageBody) this.emaObject).setLocalPath(parcel.readString());
        ((EMAVideoMessageBody) this.emaObject).setRemotePath(parcel.readString());
        ((EMAVideoMessageBody) this.emaObject).setThumbnailRemotePath(parcel.readString());
        ((EMAVideoMessageBody) this.emaObject).setThumbnailLocalPath(parcel.readString());
        ((EMAVideoMessageBody) this.emaObject).setDuration(parcel.readInt());
        ((EMAVideoMessageBody) this.emaObject).setFileLength(parcel.readLong());
    }

    public EMVideoMessageBody(EMAVideoMessageBody eMAVideoMessageBody) {
        super(eMAVideoMessageBody);
    }

    public EMVideoMessageBody(String str, String str2, int i, long j) {
        super(str, 2);
        ((EMAVideoMessageBody) this.emaObject).setThumbnailLocalPath(str2);
        ((EMAVideoMessageBody) this.emaObject).setDuration(i);
        ((EMAVideoMessageBody) this.emaObject).setFileLength(j);
        EMLog.d("videomsg", "create video,message body for:" + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMVideoMessageBody(String str, String str2, String str3, int i) {
        super(str, 2);
        ((EMAVideoMessageBody) this.emaObject).setThumbnailLocalPath(str3);
        ((EMAVideoMessageBody) this.emaObject).setLocalPath(str);
        ((EMAVideoMessageBody) this.emaObject).setRemotePath(str2);
        ((EMAVideoMessageBody) this.emaObject).setThumbnailRemotePath(str3);
        ((EMAVideoMessageBody) this.emaObject).setFileLength(i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getDuration() {
        return ((EMAVideoMessageBody) this.emaObject).duration();
    }

    public String getLocalThumb() {
        return ((EMAVideoMessageBody) this.emaObject).thumbnailLocalPath();
    }

    public String getThumbnailSecret() {
        return ((EMAVideoMessageBody) this.emaObject).thumbnailSecretKey();
    }

    public String getThumbnailUrl() {
        return ((EMAVideoMessageBody) this.emaObject).thumbnailRemotePath();
    }

    public long getVideoFileLength() {
        return ((EMAVideoMessageBody) this.emaObject).fileLength();
    }

    public void setLocalThumb(String str) {
        ((EMAVideoMessageBody) this.emaObject).setThumbnailLocalPath(str);
    }

    void setThumbnailDownloadStatus(EMFileMessageBody.EMDownloadStatus eMDownloadStatus) {
        ((EMAVideoMessageBody) this.emaObject).setThumbnailDownloadStatus(EMAFileMessageBody.EMADownloadStatus.valueOf(eMDownloadStatus.name()));
    }

    public void setThumbnailSecret(String str) {
        ((EMAVideoMessageBody) this.emaObject).setThumbnailSecretKey(str);
    }

    public void setThumbnailUrl(String str) {
        ((EMAVideoMessageBody) this.emaObject).setThumbnailRemotePath(str);
    }

    public void setVideoFileLength(long j) {
        ((EMAVideoMessageBody) this.emaObject).setFileLength(j);
    }

    public EMFileMessageBody.EMDownloadStatus thumbnailDownloadStatus() {
        switch (((EMAVideoMessageBody) this.emaObject).thumbnailDownloadStatus()) {
            case DOWNLOADING:
                return EMFileMessageBody.EMDownloadStatus.DOWNLOADING;
            case SUCCESSED:
                return EMFileMessageBody.EMDownloadStatus.SUCCESSED;
            case FAILED:
                return EMFileMessageBody.EMDownloadStatus.FAILED;
            case PENDING:
                return EMFileMessageBody.EMDownloadStatus.PENDING;
            default:
                return EMFileMessageBody.EMDownloadStatus.SUCCESSED;
        }
    }

    public String toString() {
        return "video:" + ((EMAVideoMessageBody) this.emaObject).displayName() + ",localUrl:" + ((EMAVideoMessageBody) this.emaObject).getLocalUrl() + ",remoteUrl:" + ((EMAVideoMessageBody) this.emaObject).getRemoteUrl() + ",thumbnailUrl:" + ((EMAVideoMessageBody) this.emaObject).thumbnailLocalPath() + ",length:" + ((EMAVideoMessageBody) this.emaObject).fileLength();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(((EMAVideoMessageBody) this.emaObject).displayName());
        parcel.writeString(((EMAVideoMessageBody) this.emaObject).getLocalUrl());
        parcel.writeString(((EMAVideoMessageBody) this.emaObject).getRemoteUrl());
        parcel.writeString(((EMAVideoMessageBody) this.emaObject).thumbnailLocalPath());
        parcel.writeString(((EMAVideoMessageBody) this.emaObject).thumbnailLocalPath());
        parcel.writeInt(((EMAVideoMessageBody) this.emaObject).duration());
        parcel.writeLong(((EMAVideoMessageBody) this.emaObject).fileLength());
    }
}
