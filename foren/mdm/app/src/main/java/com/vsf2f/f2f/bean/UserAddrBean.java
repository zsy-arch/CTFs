package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class UserAddrBean implements Parcelable {
    public static final Parcelable.Creator<UserAddrBean> CREATOR = new Parcelable.Creator<UserAddrBean>() { // from class: com.vsf2f.f2f.bean.UserAddrBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserAddrBean createFromParcel(Parcel in) {
            return new UserAddrBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserAddrBean[] newArray(int size) {
            return new UserAddrBean[size];
        }
    };
    private String addr;
    private String areaId;
    private String areaName;
    private String email;
    private String id;
    private boolean isCheck;
    private int isDefault;
    private String name;
    private String phone;
    private String tel;

    protected UserAddrBean(Parcel in) {
        this.id = in.readString();
        this.addr = in.readString();
        this.areaId = in.readString();
        this.areaName = in.readString();
        this.isDefault = in.readInt();
        this.name = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.tel = in.readString();
        this.isCheck = in.readByte() != 0;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public int getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.addr);
        dest.writeString(this.areaId);
        dest.writeString(this.areaName);
        dest.writeInt(this.isDefault);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.tel);
        dest.writeByte((byte) (this.isCheck ? 1 : 0));
    }
}
