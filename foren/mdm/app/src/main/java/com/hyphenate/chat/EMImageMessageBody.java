package com.hyphenate.chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.adapter.message.EMAFileMessageBody;
import com.hyphenate.chat.adapter.message.EMAImageMessageBody;
import java.io.File;

/* loaded from: classes2.dex */
public class EMImageMessageBody extends EMFileMessageBody implements Parcelable {
    public static final Parcelable.Creator<EMImageMessageBody> CREATOR = new Parcelable.Creator<EMImageMessageBody>() { // from class: com.hyphenate.chat.EMImageMessageBody.1
        @Override // android.os.Parcelable.Creator
        public EMImageMessageBody createFromParcel(Parcel parcel) {
            return new EMImageMessageBody(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public EMImageMessageBody[] newArray(int i) {
            return new EMImageMessageBody[i];
        }
    };
    private boolean sendOriginalImage;

    private EMImageMessageBody(Parcel parcel) {
        super("", 1);
        this.sendOriginalImage = false;
        ((EMAImageMessageBody) this.emaObject).setDisplayName(parcel.readString());
        ((EMAImageMessageBody) this.emaObject).setLocalPath(parcel.readString());
        ((EMAImageMessageBody) this.emaObject).setRemotePath(parcel.readString());
        ((EMAImageMessageBody) this.emaObject).setThumbnailRemotePath(parcel.readString());
        ((EMAImageMessageBody) this.emaObject).setSize(parcel.readInt(), parcel.readInt());
    }

    public EMImageMessageBody(EMAImageMessageBody eMAImageMessageBody) {
        super(eMAImageMessageBody);
        this.sendOriginalImage = false;
    }

    public EMImageMessageBody(File file) {
        super(file.getAbsolutePath(), 1);
        this.sendOriginalImage = false;
    }

    public EMImageMessageBody(File file, File file2) {
        super(file.getAbsolutePath(), 1);
        this.sendOriginalImage = false;
        ((EMAImageMessageBody) this.emaObject).setThumbnailLocalPath(file2.getAbsolutePath());
    }

    public EMImageMessageBody(String str, String str2, String str3) {
        super("", 1);
        this.sendOriginalImage = false;
        this.emaObject = new EMAImageMessageBody("", "");
        ((EMAImageMessageBody) this.emaObject).setDisplayName(str);
        ((EMAImageMessageBody) this.emaObject).setRemotePath(str2);
        ((EMAImageMessageBody) this.emaObject).setThumbnailRemotePath(str3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.hyphenate.chat.EMFileMessageBody
    public String getFileName() {
        return ((EMAImageMessageBody) this.emaObject).displayName();
    }

    public int getHeight() {
        return ((EMAImageMessageBody) this.emaObject).height();
    }

    public String getThumbnailSecret() {
        return ((EMAImageMessageBody) this.emaObject).thumbnailSecretKey();
    }

    public String getThumbnailUrl() {
        return ((EMAImageMessageBody) this.emaObject).thumbnailRemotePath();
    }

    public int getWidth() {
        return ((EMAImageMessageBody) this.emaObject).width();
    }

    public boolean isSendOriginalImage() {
        return this.sendOriginalImage;
    }

    public void setSendOriginalImage(boolean z) {
        this.sendOriginalImage = z;
    }

    public void setSize(int i, int i2) {
        ((EMAImageMessageBody) this.emaObject).setSize(i, i2);
    }

    void setThumbnailDownloadStatus(EMFileMessageBody.EMDownloadStatus eMDownloadStatus) {
        ((EMAImageMessageBody) this.emaObject).setThumbnailDownloadStatus(EMAFileMessageBody.EMADownloadStatus.valueOf(eMDownloadStatus.name()));
    }

    public void setThumbnailLocalPath(String str) {
        ((EMAImageMessageBody) this.emaObject).setThumbnailLocalPath(str);
    }

    public void setThumbnailSecret(String str) {
        ((EMAImageMessageBody) this.emaObject).setThumbnailSecretKey(str);
    }

    public void setThumbnailUrl(String str) {
        ((EMAImageMessageBody) this.emaObject).setThumbnailRemotePath(str);
    }

    public EMFileMessageBody.EMDownloadStatus thumbnailDownloadStatus() {
        switch (((EMAImageMessageBody) this.emaObject).thumbnailDownloadStatus()) {
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

    public String thumbnailLocalPath() {
        return ((EMAImageMessageBody) this.emaObject).thumbnailLocalPath();
    }

    public String toString() {
        return "image:" + ((EMAImageMessageBody) this.emaObject).displayName() + ",localurl:" + ((EMAImageMessageBody) this.emaObject).getLocalUrl() + ",remoteurl:" + ((EMAImageMessageBody) this.emaObject).getRemoteUrl() + ",thumbnial:" + ((EMAImageMessageBody) this.emaObject).thumbnailRemotePath();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(((EMAImageMessageBody) this.emaObject).displayName());
        parcel.writeString(((EMAImageMessageBody) this.emaObject).getLocalUrl());
        parcel.writeString(((EMAImageMessageBody) this.emaObject).getRemoteUrl());
        parcel.writeString(((EMAImageMessageBody) this.emaObject).thumbnailRemotePath());
        parcel.writeInt(((EMAImageMessageBody) this.emaObject).width());
        parcel.writeInt(((EMAImageMessageBody) this.emaObject).height());
    }
}
