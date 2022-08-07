package com.tencent.qqconnect.dataprovider.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class TextOnly implements Parcelable {
    public static final Parcelable.Creator<TextOnly> CREATOR = new Parcelable.Creator<TextOnly>() { // from class: com.tencent.qqconnect.dataprovider.datatype.TextOnly.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextOnly createFromParcel(Parcel parcel) {
            return new TextOnly(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextOnly[] newArray(int i) {
            return new TextOnly[i];
        }
    };
    private String a;

    public TextOnly(String str) {
        this.a = str;
    }

    public String getText() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }

    private TextOnly(Parcel parcel) {
        this.a = parcel.readString();
    }
}
