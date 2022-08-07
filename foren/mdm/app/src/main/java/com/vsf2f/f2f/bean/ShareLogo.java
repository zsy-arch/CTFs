package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ShareLogo implements Parcelable {
    public static final Parcelable.Creator<ShareLogo> CREATOR = new Parcelable.Creator<ShareLogo>() { // from class: com.vsf2f.f2f.bean.ShareLogo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareLogo createFromParcel(Parcel in) {
            return new ShareLogo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareLogo[] newArray(int size) {
            return new ShareLogo[size];
        }
    };
    private String id;
    private String path;
    private String spath;

    protected ShareLogo(Parcel in) {
        this.id = in.readString();
        this.path = in.readString();
        this.spath = in.readString();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.path);
        dest.writeString(this.spath);
    }
}
