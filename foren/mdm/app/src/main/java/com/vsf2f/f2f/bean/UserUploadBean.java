package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class UserUploadBean implements Parcelable {
    public static final Parcelable.Creator<UserUploadBean> CREATOR = new Parcelable.Creator<UserUploadBean>() { // from class: com.vsf2f.f2f.bean.UserUploadBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserUploadBean createFromParcel(Parcel in) {
            return new UserUploadBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserUploadBean[] newArray(int size) {
            return new UserUploadBean[size];
        }
    };
    private String AccessKeyId;
    private String AccessKeySecret;
    private String Expiration;
    private String SecurityToken;

    protected UserUploadBean(Parcel in) {
        this.Expiration = in.readString();
        this.AccessKeyId = in.readString();
        this.AccessKeySecret = in.readString();
        this.SecurityToken = in.readString();
    }

    public String getAccessKeyId() {
        return this.AccessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.AccessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return this.AccessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.AccessKeySecret = accessKeySecret;
    }

    public String getExpiration() {
        return this.Expiration;
    }

    public void setExpiration(String expiration) {
        this.Expiration = expiration;
    }

    public String getSecurityToken() {
        return this.SecurityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.SecurityToken = securityToken;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Expiration);
        dest.writeString(this.AccessKeyId);
        dest.writeString(this.AccessKeySecret);
        dest.writeString(this.SecurityToken);
    }
}
