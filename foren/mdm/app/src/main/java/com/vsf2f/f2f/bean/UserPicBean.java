package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class UserPicBean implements Parcelable {
    public static final Parcelable.Creator<UserPicBean> CREATOR = new Parcelable.Creator<UserPicBean>() { // from class: com.vsf2f.f2f.bean.UserPicBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserPicBean createFromParcel(Parcel in) {
            return new UserPicBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserPicBean[] newArray(int size) {
            return new UserPicBean[size];
        }
    };
    private String autopath;
    private String bucket;
    private String compress;
    private String etag;
    private String id;
    private String path;
    private String spath;
    private String userName;

    public UserPicBean() {
    }

    public UserPicBean(String path) {
        this.path = path;
        this.spath = path;
        this.autopath = path;
    }

    protected UserPicBean(Parcel in) {
        this.id = in.readString();
        this.userName = in.readString();
        this.path = in.readString();
        this.bucket = in.readString();
        this.etag = in.readString();
        this.autopath = in.readString();
        this.compress = in.readString();
        this.spath = in.readString();
    }

    public String getAutopath() {
        return this.autopath;
    }

    public void setAutopath(String autopath) {
        this.autopath = autopath;
    }

    public String getBucket() {
        return this.bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getCompress() {
        return this.compress;
    }

    public void setCompress(String compress) {
        this.compress = compress;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
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

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userName);
        dest.writeString(this.path);
        dest.writeString(this.bucket);
        dest.writeString(this.etag);
        dest.writeString(this.autopath);
        dest.writeString(this.compress);
        dest.writeString(this.spath);
    }

    public String toString() {
        return "UserPicBean{autopath='" + this.autopath + "', id='" + this.id + "', userName='" + this.userName + "', path='" + this.path + "', bucket='" + this.bucket + "', etag='" + this.etag + "', compress='" + this.compress + "', spath='" + this.spath + "'}";
    }
}
