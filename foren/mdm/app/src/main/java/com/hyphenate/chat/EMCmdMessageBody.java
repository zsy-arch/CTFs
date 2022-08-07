package com.hyphenate.chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.hyphenate.chat.adapter.message.EMACmdMessageBody;
import java.util.Map;

/* loaded from: classes.dex */
public class EMCmdMessageBody extends EMMessageBody implements Parcelable {
    public static final Parcelable.Creator<EMCmdMessageBody> CREATOR = new Parcelable.Creator<EMCmdMessageBody>() { // from class: com.hyphenate.chat.EMCmdMessageBody.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMCmdMessageBody createFromParcel(Parcel parcel) {
            return new EMCmdMessageBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMCmdMessageBody[] newArray(int i) {
            return new EMCmdMessageBody[i];
        }
    };

    private EMCmdMessageBody(Parcel parcel) {
        this.emaObject = new EMACmdMessageBody(parcel.readString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMCmdMessageBody(EMACmdMessageBody eMACmdMessageBody) {
        this.emaObject = eMACmdMessageBody;
    }

    public EMCmdMessageBody(String str) {
        this.emaObject = new EMACmdMessageBody(str);
    }

    @Deprecated
    public EMCmdMessageBody(String str, Map<String, String> map) {
        this.emaObject = new EMACmdMessageBody(str);
        ((EMACmdMessageBody) this.emaObject).setParams(map);
    }

    public String action() {
        return ((EMACmdMessageBody) this.emaObject).action();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Map<String, String> getParams() {
        return ((EMACmdMessageBody) this.emaObject).params();
    }

    public String toString() {
        return "cmd:\"" + ((EMACmdMessageBody) this.emaObject).action() + "\"";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(((EMACmdMessageBody) this.emaObject).action());
    }
}
