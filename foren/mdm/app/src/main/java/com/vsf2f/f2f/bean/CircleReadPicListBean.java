package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CircleReadPicListBean implements Parcelable {
    public static final Parcelable.Creator<CircleReadPicListBean> CREATOR = new Parcelable.Creator<CircleReadPicListBean>() { // from class: com.vsf2f.f2f.bean.CircleReadPicListBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleReadPicListBean createFromParcel(Parcel in) {
            return new CircleReadPicListBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleReadPicListBean[] newArray(int size) {
            return new CircleReadPicListBean[size];
        }
    };
    private String autopath;
    private String bpath;
    private boolean compress;
    private int id;
    private String mpath;
    private String path;
    private String remark;
    private String spath;

    public CircleReadPicListBean() {
    }

    protected CircleReadPicListBean(Parcel in) {
        this.id = in.readInt();
        this.remark = in.readString();
        this.path = in.readString();
        this.mpath = in.readString();
        this.spath = in.readString();
        this.autopath = in.readString();
        this.compress = in.readByte() != 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMpath() {
        return this.mpath;
    }

    public void setMpath(String mpath) {
        this.mpath = mpath;
    }

    public String getSpath() {
        return this.spath;
    }

    public void setSpath(String spath) {
        this.spath = spath;
    }

    public String getBpath() {
        return this.bpath;
    }

    public void setBpath(String bpath) {
        this.bpath = bpath;
    }

    public String getAutopath() {
        return this.autopath;
    }

    public void setAutopath(String autopath) {
        this.autopath = autopath;
    }

    public boolean isCompress() {
        return this.compress;
    }

    public void setCompress(boolean compress) {
        this.compress = compress;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.remark);
        dest.writeString(this.path);
        dest.writeString(this.mpath);
        dest.writeString(this.spath);
        dest.writeString(this.autopath);
        dest.writeByte((byte) (this.compress ? 1 : 0));
    }

    public String toString() {
        return "CircleReadPicListBean{autopath='" + this.autopath + "', id=" + this.id + ", remark='" + this.remark + "', path='" + this.path + "', mpath='" + this.mpath + "', spath='" + this.spath + "', compress=" + this.compress + '}';
    }
}
