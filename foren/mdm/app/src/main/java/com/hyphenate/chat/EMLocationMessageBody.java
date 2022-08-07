package com.hyphenate.chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.hyphenate.chat.adapter.message.EMALocationMessageBody;

/* loaded from: classes2.dex */
public class EMLocationMessageBody extends EMMessageBody implements Parcelable {
    public static final Parcelable.Creator<EMLocationMessageBody> CREATOR = new Parcelable.Creator<EMLocationMessageBody>() { // from class: com.hyphenate.chat.EMLocationMessageBody.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMLocationMessageBody createFromParcel(Parcel parcel) {
            return new EMLocationMessageBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMLocationMessageBody[] newArray(int i) {
            return new EMLocationMessageBody[i];
        }
    };

    private EMLocationMessageBody(Parcel parcel) {
        this.emaObject = new EMALocationMessageBody(0.0d, 0.0d, "");
        String readString = parcel.readString();
        double readDouble = parcel.readDouble();
        double readDouble2 = parcel.readDouble();
        ((EMALocationMessageBody) this.emaObject).setAddress(readString);
        ((EMALocationMessageBody) this.emaObject).setLatitude(readDouble);
        ((EMALocationMessageBody) this.emaObject).setLongitude(readDouble2);
    }

    public EMLocationMessageBody(EMALocationMessageBody eMALocationMessageBody) {
        this.emaObject = eMALocationMessageBody;
    }

    public EMLocationMessageBody(String str, double d, double d2) {
        this.emaObject = new EMALocationMessageBody(d, d2, str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return ((EMALocationMessageBody) this.emaObject).address();
    }

    public double getLatitude() {
        return ((EMALocationMessageBody) this.emaObject).latitude();
    }

    public double getLongitude() {
        return ((EMALocationMessageBody) this.emaObject).longitude();
    }

    public String toString() {
        return "location:" + ((EMALocationMessageBody) this.emaObject).address() + ",lat:" + ((EMALocationMessageBody) this.emaObject).latitude() + ",lng:" + ((EMALocationMessageBody) this.emaObject).longitude();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(((EMALocationMessageBody) this.emaObject).address());
        parcel.writeDouble(((EMALocationMessageBody) this.emaObject).latitude());
        parcel.writeDouble(((EMALocationMessageBody) this.emaObject).longitude());
    }
}
