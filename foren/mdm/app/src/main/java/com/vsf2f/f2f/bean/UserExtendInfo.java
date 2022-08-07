package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class UserExtendInfo implements Parcelable {
    public static final Parcelable.Creator<UserExtendInfo> CREATOR = new Parcelable.Creator<UserExtendInfo>() { // from class: com.vsf2f.f2f.bean.UserExtendInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserExtendInfo createFromParcel(Parcel source) {
            return new UserExtendInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserExtendInfo[] newArray(int size) {
            return new UserExtendInfo[size];
        }
    };
    private int certCompany;
    private int isProxy;
    private int isShareholders;
    private boolean openManor;
    private int openShop;
    private int paypwdVersion;
    private int shareMoney;
    private int shareMoneyEnable;
    private int shareMoneyFrozen;

    public int getIsProxy() {
        return this.isProxy;
    }

    public void setIsProxy(int isProxy) {
        this.isProxy = isProxy;
    }

    public int getIsShareholders() {
        return this.isShareholders;
    }

    public void setIsShareholders(int isShareholders) {
        this.isShareholders = isShareholders;
    }

    public int getOpenShop() {
        return this.openShop;
    }

    public void setOpenShop(int openShop) {
        this.openShop = openShop;
    }

    public boolean isOpenManor() {
        return this.openManor;
    }

    public void setOpenManor(boolean openManor) {
        this.openManor = openManor;
    }

    public int getCertCompany() {
        return this.certCompany;
    }

    public void setCertCompany(int certCompany) {
        this.certCompany = certCompany;
    }

    public int getShareMoney() {
        return this.shareMoney;
    }

    public void setShareMoney(int shareMoney) {
        this.shareMoney = shareMoney;
    }

    public int getShareMoneyEnable() {
        return this.shareMoneyEnable;
    }

    public void setShareMoneyEnable(int shareMoneyEnable) {
        this.shareMoneyEnable = shareMoneyEnable;
    }

    public void setShareMoneyFrozen(int shareMoneyFrozen) {
        this.shareMoneyFrozen = shareMoneyFrozen;
    }

    public int getShareMoneyFrozen() {
        return this.shareMoneyFrozen;
    }

    public int getShareMoneyFrozenThaw() {
        return (this.shareMoney - this.shareMoneyEnable) - this.shareMoneyFrozen;
    }

    public UserExtendInfo() {
    }

    public int getPaypwdVersion() {
        return this.paypwdVersion;
    }

    public void setPaypwdVersion(int paypwdVersion) {
        this.paypwdVersion = paypwdVersion;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.isProxy);
        dest.writeInt(this.isShareholders);
        dest.writeInt(this.openShop);
        dest.writeByte(this.openManor ? (byte) 1 : (byte) 0);
        dest.writeInt(this.paypwdVersion);
        dest.writeInt(this.certCompany);
        dest.writeInt(this.shareMoney);
        dest.writeInt(this.shareMoneyEnable);
        dest.writeInt(this.shareMoneyFrozen);
    }

    protected UserExtendInfo(Parcel in) {
        this.isProxy = in.readInt();
        this.isShareholders = in.readInt();
        this.openShop = in.readInt();
        this.openManor = in.readByte() != 0;
        this.paypwdVersion = in.readInt();
        this.certCompany = in.readInt();
        this.shareMoney = in.readInt();
        this.shareMoneyEnable = in.readInt();
        this.shareMoneyFrozen = in.readInt();
    }
}
