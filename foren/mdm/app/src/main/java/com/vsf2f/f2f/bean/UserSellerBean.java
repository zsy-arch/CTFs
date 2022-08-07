package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class UserSellerBean implements Parcelable {
    public static final Parcelable.Creator<UserSellerBean> CREATOR = new Parcelable.Creator<UserSellerBean>() { // from class: com.vsf2f.f2f.bean.UserSellerBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserSellerBean createFromParcel(Parcel in) {
            return new UserSellerBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserSellerBean[] newArray(int size) {
            return new UserSellerBean[size];
        }
    };
    private String address;
    private String areaId;
    private String description;
    private String latitude;
    private UserLogoBean logo;
    private String longitude;
    private String phone;
    private String storeName;

    protected UserSellerBean(Parcel in) {
        this.storeName = in.readString();
        this.description = in.readString();
        this.phone = in.readString();
        this.areaId = in.readString();
        this.address = in.readString();
        this.longitude = in.readString();
        this.latitude = in.readString();
        this.logo = (UserLogoBean) in.readParcelable(UserLogoBean.class.getClassLoader());
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserLogoBean getLogo() {
        return this.logo;
    }

    public void setLogo(UserLogoBean logo) {
        this.logo = logo;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.storeName);
        dest.writeString(this.description);
        dest.writeString(this.phone);
        dest.writeString(this.areaId);
        dest.writeString(this.address);
        dest.writeString(this.longitude);
        dest.writeString(this.latitude);
        dest.writeParcelable(this.logo, flags);
    }

    public String toString() {
        return "UserSellerBean{address='" + this.address + "', storeName='" + this.storeName + "', description='" + this.description + "', phone='" + this.phone + "', areaId='" + this.areaId + "', longitude='" + this.longitude + "', latitude='" + this.latitude + "', logo=" + this.logo + '}';
    }

    public UserSellerBean() {
    }
}
