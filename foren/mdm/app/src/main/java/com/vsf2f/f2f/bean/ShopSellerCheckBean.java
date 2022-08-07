package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ShopSellerCheckBean implements Parcelable {
    public static final Parcelable.Creator<ShopSellerCheckBean> CREATOR = new Parcelable.Creator<ShopSellerCheckBean>() { // from class: com.vsf2f.f2f.bean.ShopSellerCheckBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopSellerCheckBean createFromParcel(Parcel in) {
            return new ShopSellerCheckBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopSellerCheckBean[] newArray(int size) {
            return new ShopSellerCheckBean[size];
        }
    };
    private boolean check;
    private String isVerify;
    private String maxAdvert;
    private String maxChildrenMenus;
    private String maxGoods;
    private String maxParentMenus;
    private String maxSellType;
    private String maxShareHref;
    private String maxShopNav;

    protected ShopSellerCheckBean(Parcel in) {
        this.isVerify = in.readString();
        this.maxSellType = in.readString();
        this.maxGoods = in.readString();
        this.maxShopNav = in.readString();
        this.maxParentMenus = in.readString();
        this.maxChildrenMenus = in.readString();
        this.maxAdvert = in.readString();
        this.maxShareHref = in.readString();
    }

    public boolean getCheck() {
        return this.check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getIsVerify() {
        return this.isVerify;
    }

    public void setIsVerify(String isVerify) {
        this.isVerify = isVerify;
    }

    public String getMaxAdvert() {
        return this.maxAdvert;
    }

    public void setMaxAdvert(String maxAdvert) {
        this.maxAdvert = maxAdvert;
    }

    public String getMaxChildrenMenus() {
        return this.maxChildrenMenus;
    }

    public void setMaxChildrenMenus(String maxChildrenMenus) {
        this.maxChildrenMenus = maxChildrenMenus;
    }

    public String getMaxGoods() {
        return this.maxGoods;
    }

    public void setMaxGoods(String maxGoods) {
        this.maxGoods = maxGoods;
    }

    public String getMaxParentMenus() {
        return this.maxParentMenus;
    }

    public void setMaxParentMenus(String maxParentMenus) {
        this.maxParentMenus = maxParentMenus;
    }

    public String getMaxSellType() {
        return this.maxSellType;
    }

    public void setMaxSellType(String maxSellType) {
        this.maxSellType = maxSellType;
    }

    public String getMaxShareHref() {
        return this.maxShareHref;
    }

    public void setMaxShareHref(String maxShareHref) {
        this.maxShareHref = maxShareHref;
    }

    public String getMaxShopNav() {
        return this.maxShopNav;
    }

    public void setMaxShopNav(String maxShopNav) {
        this.maxShopNav = maxShopNav;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.isVerify);
        dest.writeString(this.maxSellType);
        dest.writeString(this.maxGoods);
        dest.writeString(this.maxShopNav);
        dest.writeString(this.maxParentMenus);
        dest.writeString(this.maxChildrenMenus);
        dest.writeString(this.maxAdvert);
        dest.writeString(this.maxShareHref);
    }

    public String toString() {
        return "ShopSellerCheckBean{check='" + this.check + "', isVerify='" + this.isVerify + "', maxSellType='" + this.maxSellType + "', maxGoods='" + this.maxGoods + "', maxShopNav='" + this.maxShopNav + "', maxParentMenus='" + this.maxParentMenus + "', maxChildrenMenus='" + this.maxChildrenMenus + "', maxAdvert='" + this.maxAdvert + "', maxShareHref='" + this.maxShareHref + "'}";
    }
}
