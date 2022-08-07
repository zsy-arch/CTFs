package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class IsLoginBean implements Parcelable {
    public static final Parcelable.Creator<IsLoginBean> CREATOR = new Parcelable.Creator<IsLoginBean>() { // from class: com.vsf2f.f2f.bean.IsLoginBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IsLoginBean createFromParcel(Parcel in) {
            return new IsLoginBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IsLoginBean[] newArray(int size) {
            return new IsLoginBean[size];
        }
    };
    private String error;
    private String status;
    private String success;

    protected IsLoginBean(Parcel in) {
        this.error = in.readString();
        this.status = in.readString();
        this.success = in.readString();
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error);
        dest.writeString(this.status);
        dest.writeString(this.success);
    }
}
