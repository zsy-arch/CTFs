package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.litepal.crud.DataSupport;

/* loaded from: classes2.dex */
public class PicBean extends DataSupport implements Parcelable {
    public static final Parcelable.Creator<PicBean> CREATOR = new Parcelable.Creator<PicBean>() { // from class: com.vsf2f.f2f.bean.PicBean.1
        @Override // android.os.Parcelable.Creator
        public PicBean createFromParcel(Parcel in) {
            return new PicBean(in);
        }

        @Override // android.os.Parcelable.Creator
        public PicBean[] newArray(int size) {
            return new PicBean[size];
        }
    };
    private boolean compress;
    private String id;
    private String mpath;
    private String path;
    private String spath;

    protected PicBean(Parcel in) {
        this.id = in.readString();
        this.path = in.readString();
        this.mpath = in.readString();
        this.spath = in.readString();
        this.compress = in.readByte() != 0;
    }

    public PicBean() {
    }

    public boolean isCompress() {
        return this.compress;
    }

    public void setCompress(boolean compress) {
        this.compress = compress;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String toString() {
        return "PicBean{compress=" + this.compress + ", id='" + this.id + "', path='" + this.path + "', mpath='" + this.mpath + "', spath='" + this.spath + "'}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.path);
        dest.writeString(this.mpath);
        dest.writeString(this.spath);
        dest.writeByte((byte) (this.compress ? 1 : 0));
    }
}
