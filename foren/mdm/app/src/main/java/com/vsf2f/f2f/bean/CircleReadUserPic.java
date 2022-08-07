package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CircleReadUserPic implements Parcelable {
    public static final Parcelable.Creator<CircleReadUserPic> CREATOR = new Parcelable.Creator<CircleReadUserPic>() { // from class: com.vsf2f.f2f.bean.CircleReadUserPic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleReadUserPic createFromParcel(Parcel in) {
            return new CircleReadUserPic(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleReadUserPic[] newArray(int size) {
            return new CircleReadUserPic[size];
        }
    };
    private boolean compress;
    private String mpath;
    private String path;
    private String spath;

    public CircleReadUserPic() {
    }

    protected CircleReadUserPic(Parcel in) {
        this.path = in.readString();
        this.mpath = in.readString();
        this.spath = in.readString();
        this.compress = in.readByte() != 0;
    }

    public boolean isCompress() {
        return this.compress;
    }

    public void setCompress(boolean compress) {
        this.compress = compress;
    }

    public String getMpath() {
        return this.mpath;
    }

    public void setMpath(String mpath) {
        this.mpath = mpath;
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
        dest.writeString(this.path);
        dest.writeString(this.mpath);
        dest.writeString(this.spath);
        dest.writeByte((byte) (this.compress ? 1 : 0));
    }

    public String toString() {
        return "CircleReadUserPic{compress=" + this.compress + ", path='" + this.path + "', mpath='" + this.mpath + "', spath='" + this.spath + "'}";
    }
}
