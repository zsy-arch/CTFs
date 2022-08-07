package com.tencent.qqconnect.dataprovider.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class TextAndMediaPath implements Parcelable {
    public static final Parcelable.Creator<TextAndMediaPath> CREATOR = new Parcelable.Creator<TextAndMediaPath>() { // from class: com.tencent.qqconnect.dataprovider.datatype.TextAndMediaPath.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextAndMediaPath createFromParcel(Parcel parcel) {
            return new TextAndMediaPath(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextAndMediaPath[] newArray(int i) {
            return new TextAndMediaPath[i];
        }
    };
    private String a;
    private String b;

    public TextAndMediaPath(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String getText() {
        return this.a;
    }

    public String getMediaPath() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }

    private TextAndMediaPath(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }
}
