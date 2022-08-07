package com.hyphenate.chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.hyphenate.chat.adapter.message.EMATextMessageBody;

/* loaded from: classes2.dex */
public class EMTextMessageBody extends EMMessageBody implements Parcelable {
    public static final Parcelable.Creator<EMTextMessageBody> CREATOR = new Parcelable.Creator<EMTextMessageBody>() { // from class: com.hyphenate.chat.EMTextMessageBody.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMTextMessageBody createFromParcel(Parcel parcel) {
            return new EMTextMessageBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMTextMessageBody[] newArray(int i) {
            return new EMTextMessageBody[i];
        }
    };

    private EMTextMessageBody(Parcel parcel) {
        this.emaObject = new EMATextMessageBody(parcel.readString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMTextMessageBody(EMATextMessageBody eMATextMessageBody) {
        this.emaObject = eMATextMessageBody;
    }

    public EMTextMessageBody(String str) {
        this.emaObject = new EMATextMessageBody(str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getMessage() {
        return ((EMATextMessageBody) this.emaObject).text();
    }

    public String toString() {
        return "txt:\"" + ((EMATextMessageBody) this.emaObject).text() + "\"";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(((EMATextMessageBody) this.emaObject).text());
    }
}
