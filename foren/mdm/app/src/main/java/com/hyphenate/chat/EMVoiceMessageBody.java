package com.hyphenate.chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.hyphenate.chat.adapter.message.EMAVoiceMessageBody;
import com.hyphenate.util.EMLog;
import java.io.File;

/* loaded from: classes2.dex */
public class EMVoiceMessageBody extends EMFileMessageBody implements Parcelable {
    public static final Parcelable.Creator<EMVoiceMessageBody> CREATOR = new Parcelable.Creator<EMVoiceMessageBody>() { // from class: com.hyphenate.chat.EMVoiceMessageBody.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMVoiceMessageBody createFromParcel(Parcel parcel) {
            return new EMVoiceMessageBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMVoiceMessageBody[] newArray(int i) {
            return new EMVoiceMessageBody[i];
        }
    };

    private EMVoiceMessageBody(Parcel parcel) {
        super("", 4);
        ((EMAVoiceMessageBody) this.emaObject).setDisplayName(parcel.readString());
        ((EMAVoiceMessageBody) this.emaObject).setLocalPath(parcel.readString());
        ((EMAVoiceMessageBody) this.emaObject).setRemotePath(parcel.readString());
        ((EMAVoiceMessageBody) this.emaObject).setDuration(parcel.readInt());
    }

    public EMVoiceMessageBody(EMAVoiceMessageBody eMAVoiceMessageBody) {
        super(eMAVoiceMessageBody);
    }

    public EMVoiceMessageBody(File file, int i) {
        super(file.getAbsolutePath(), 4);
        ((EMAVoiceMessageBody) this.emaObject).setDuration(i);
        EMLog.d("voicemsg", "create voice, message body for:" + file.getAbsolutePath());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMVoiceMessageBody(String str, String str2, int i) {
        super(str, 4);
        ((EMAVoiceMessageBody) this.emaObject).setLocalPath(str);
        ((EMAVoiceMessageBody) this.emaObject).setRemotePath(str2);
        ((EMAVoiceMessageBody) this.emaObject).setDuration(i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getLength() {
        return ((EMAVoiceMessageBody) this.emaObject).duration();
    }

    public String toString() {
        return "voice:" + ((EMAVoiceMessageBody) this.emaObject).displayName() + ",localurl:" + ((EMAVoiceMessageBody) this.emaObject).getLocalUrl() + ",remoteurl:" + ((EMAVoiceMessageBody) this.emaObject).getRemoteUrl() + ",length:" + ((EMAVoiceMessageBody) this.emaObject).duration();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(((EMAVoiceMessageBody) this.emaObject).displayName());
        parcel.writeString(((EMAVoiceMessageBody) this.emaObject).getLocalUrl());
        parcel.writeString(((EMAVoiceMessageBody) this.emaObject).getRemoteUrl());
        parcel.writeInt(((EMAVoiceMessageBody) this.emaObject).duration());
    }
}
