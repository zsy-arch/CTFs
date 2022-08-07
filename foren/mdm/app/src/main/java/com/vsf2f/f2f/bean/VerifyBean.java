package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.lidroid.xutils.db.annotation.Id;

/* loaded from: classes.dex */
public class VerifyBean implements Parcelable {
    public static final Parcelable.Creator<VerifyBean> CREATOR = new Parcelable.Creator<VerifyBean>() { // from class: com.vsf2f.f2f.bean.VerifyBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VerifyBean createFromParcel(Parcel in) {
            return new VerifyBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VerifyBean[] newArray(int size) {
            return new VerifyBean[size];
        }
    };
    private String failed;
    @Id(column = "dbId")
    private int isVerify;

    public VerifyBean() {
    }

    protected VerifyBean(Parcel in) {
        this.isVerify = in.readInt();
        this.failed = in.readString();
    }

    public int getIsVerify() {
        return this.isVerify;
    }

    public void setIsVerify(int isVerify) {
        this.isVerify = isVerify;
    }

    public String getFailed() {
        return this.failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.isVerify);
        dest.writeString(this.failed);
    }

    public String toString() {
        return "VerifyBean{, isVerify='" + this.isVerify + "', failed='" + this.failed + "'}";
    }
}
