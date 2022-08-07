package com.hyphenate.chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.hyphenate.chat.adapter.message.EMAFileMessageBody;
import java.io.File;

/* loaded from: classes2.dex */
public class EMNormalFileMessageBody extends EMFileMessageBody implements Parcelable {
    public static final Parcelable.Creator<EMNormalFileMessageBody> CREATOR = new Parcelable.Creator<EMNormalFileMessageBody>() { // from class: com.hyphenate.chat.EMNormalFileMessageBody.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMNormalFileMessageBody createFromParcel(Parcel parcel) {
            return new EMNormalFileMessageBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMNormalFileMessageBody[] newArray(int i) {
            return new EMNormalFileMessageBody[i];
        }
    };

    public EMNormalFileMessageBody() {
        super("");
    }

    private EMNormalFileMessageBody(Parcel parcel) {
        super("");
        ((EMAFileMessageBody) this.emaObject).setDisplayName(parcel.readString());
        ((EMAFileMessageBody) this.emaObject).setLocalPath(parcel.readString());
        ((EMAFileMessageBody) this.emaObject).setRemotePath(parcel.readString());
        ((EMAFileMessageBody) this.emaObject).setFileLength(parcel.readLong());
        ((EMAFileMessageBody) this.emaObject).setSecretKey(parcel.readString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMNormalFileMessageBody(EMAFileMessageBody eMAFileMessageBody) {
        super(eMAFileMessageBody);
    }

    public EMNormalFileMessageBody(File file) {
        super(file.getAbsolutePath());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMNormalFileMessageBody(String str, String str2) {
        super(str);
        super.setRemoteUrl(str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getFileSize() {
        return ((EMAFileMessageBody) this.emaObject).fileLength();
    }

    public String toString() {
        return "normal file:" + ((EMAFileMessageBody) this.emaObject).displayName() + ",localUrl:" + ((EMAFileMessageBody) this.emaObject).getLocalUrl() + ",remoteUrl:" + ((EMAFileMessageBody) this.emaObject).getRemoteUrl() + ",file size:" + ((EMAFileMessageBody) this.emaObject).fileLength();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(((EMAFileMessageBody) this.emaObject).displayName());
        parcel.writeString(((EMAFileMessageBody) this.emaObject).getLocalUrl());
        parcel.writeString(((EMAFileMessageBody) this.emaObject).getRemoteUrl());
        parcel.writeLong(((EMAFileMessageBody) this.emaObject).fileLength());
        parcel.writeString(((EMAFileMessageBody) this.emaObject).getSecret());
    }
}
