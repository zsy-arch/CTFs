package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class UserShareBean implements Parcelable {
    public static final Parcelable.Creator<UserShareBean> CREATOR = new Parcelable.Creator<UserShareBean>() { // from class: com.vsf2f.f2f.bean.UserShareBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserShareBean createFromParcel(Parcel source) {
            return new UserShareBean(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserShareBean[] newArray(int size) {
            return new UserShareBean[size];
        }
    };
    private String shareAdvert;
    private String shareHref;
    private String shareIcon;
    private String shareName;
    private String shareType;
    private String userName;

    public UserShareBean() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShareType() {
        return this.shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getShareName() {
        return this.shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public String getShareIcon() {
        return this.shareIcon;
    }

    public void setShareIcon(String shareIcon) {
        this.shareIcon = shareIcon;
    }

    public String getShareHref() {
        return this.shareHref;
    }

    public void setShareHref(String shareHref) {
        this.shareHref = shareHref;
    }

    public String getShareAdvert() {
        return this.shareAdvert;
    }

    public void setShareAdvert(String shareAdvert) {
        this.shareAdvert = shareAdvert;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.shareType);
        dest.writeString(this.shareName);
        dest.writeString(this.shareIcon);
        dest.writeString(this.shareHref);
        dest.writeString(this.shareAdvert);
    }

    protected UserShareBean(Parcel in) {
        this.userName = in.readString();
        this.shareType = in.readString();
        this.shareName = in.readString();
        this.shareIcon = in.readString();
        this.shareHref = in.readString();
        this.shareAdvert = in.readString();
    }
}
