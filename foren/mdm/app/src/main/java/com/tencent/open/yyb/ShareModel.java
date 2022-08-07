package com.tencent.open.yyb;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class ShareModel implements Parcelable {
    public static final Parcelable.Creator<ShareModel> CREATOR = new Parcelable.Creator<ShareModel>() { // from class: com.tencent.open.yyb.ShareModel.1
        /* renamed from: a */
        public ShareModel createFromParcel(Parcel parcel) {
            ShareModel shareModel = new ShareModel();
            shareModel.a = parcel.readString();
            shareModel.b = parcel.readString();
            shareModel.c = parcel.readString();
            shareModel.d = parcel.readString();
            return shareModel;
        }

        /* renamed from: a */
        public ShareModel[] newArray(int i) {
            return null;
        }
    };
    public String a;
    public String b;
    public String c;
    public String d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }
}
