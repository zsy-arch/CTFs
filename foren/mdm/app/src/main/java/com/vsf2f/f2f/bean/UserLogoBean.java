package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class UserLogoBean implements Parcelable {
    public static final Parcelable.Creator<UserLogoBean> CREATOR = new Parcelable.Creator<UserLogoBean>() { // from class: com.vsf2f.f2f.bean.UserLogoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserLogoBean createFromParcel(Parcel in) {
            return new UserLogoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserLogoBean[] newArray(int size) {
            return new UserLogoBean[size];
        }
    };
    private String path;
    private String spath;

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSpath() {
        return this.spath;
    }

    public void setSpath(String spath) {
        this.spath = spath;
    }

    protected UserLogoBean(Parcel in) {
        this.path = in.readString();
        this.spath = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.spath);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
