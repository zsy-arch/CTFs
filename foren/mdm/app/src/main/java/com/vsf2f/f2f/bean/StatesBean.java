package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class StatesBean implements Parcelable {
    public static final Parcelable.Creator<StatesBean> CREATOR = new Parcelable.Creator<StatesBean>() { // from class: com.vsf2f.f2f.bean.StatesBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatesBean createFromParcel(Parcel in) {
            return new StatesBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatesBean[] newArray(int size) {
            return new StatesBean[size];
        }
    };
    private String failed;
    private String seccuse;

    public StatesBean() {
    }

    protected StatesBean(Parcel in) {
        this.seccuse = in.readString();
        this.failed = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.seccuse);
        dest.writeString(this.failed);
    }

    public String getSeccuse() {
        return this.seccuse;
    }

    public void setSeccuse(String seccuse) {
        this.seccuse = seccuse;
    }

    public String getFailed() {
        return this.failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }
}
