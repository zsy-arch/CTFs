package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes2.dex */
public class ShopInfoBean implements Parcelable {
    public static final Parcelable.Creator<ShopInfoBean> CREATOR = new Parcelable.Creator<ShopInfoBean>() { // from class: com.vsf2f.f2f.bean.ShopInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopInfoBean createFromParcel(Parcel in) {
            return new ShopInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopInfoBean[] newArray(int size) {
            return new ShopInfoBean[size];
        }
    };
    private String address;
    private String advertWapId;
    private List<PicBean> advertWapList;
    private String areaId;
    private String areaName;
    private String description;
    private String id;
    private String isVerify;
    private String latitude;
    private PicBean logo;
    private String longitude;
    private String maxAdvert;
    private String maxChildrenMenus;
    private String maxGoods;
    private String maxParentMenus;
    private String maxSellType;
    private String maxShareHref;
    private String maxShopNav;
    private String phone;
    private String pictureId;
    private String sellGoodsType;
    private String sellGoodsTypeName;
    private String storeName;

    protected ShopInfoBean(Parcel in) {
        this.id = in.readString();
        this.storeName = in.readString();
        this.advertWapId = in.readString();
        this.pictureId = in.readString();
        this.phone = in.readString();
        this.areaId = in.readString();
        this.areaName = in.readString();
        this.address = in.readString();
        this.longitude = in.readString();
        this.latitude = in.readString();
        this.sellGoodsType = in.readString();
        this.sellGoodsTypeName = in.readString();
        this.description = in.readString();
        this.isVerify = in.readString();
        this.maxSellType = in.readString();
        this.maxGoods = in.readString();
        this.maxShopNav = in.readString();
        this.maxParentMenus = in.readString();
        this.maxChildrenMenus = in.readString();
        this.maxAdvert = in.readString();
        this.maxShareHref = in.readString();
        this.logo = (PicBean) in.readParcelable(PicBean.class.getClassLoader());
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdvertWapId() {
        return this.advertWapId;
    }

    public void setAdvertWapId(String advertWapId) {
        this.advertWapId = advertWapId;
    }

    public List<PicBean> getAdvertWapList() {
        return this.advertWapList;
    }

    public void setAdvertWapList(List<PicBean> advertWapList) {
        this.advertWapList = advertWapList;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsVerify() {
        return this.isVerify;
    }

    public void setIsVerify(String isVerify) {
        this.isVerify = isVerify;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public PicBean getLogo() {
        if (this.logo == null) {
            this.logo = new PicBean();
        }
        return this.logo;
    }

    public void setLogo(PicBean logo) {
        this.logo = logo;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getSellGoodsType() {
        return this.sellGoodsType;
    }

    public void setSellGoodsType(String sellGoodsType) {
        this.sellGoodsType = sellGoodsType;
    }

    public String getSellGoodsTypeName() {
        return this.sellGoodsTypeName;
    }

    public void setSellGoodsTypeName(String sellGoodsTypeName) {
        this.sellGoodsTypeName = sellGoodsTypeName;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.storeName);
        dest.writeString(this.advertWapId);
        dest.writeString(this.pictureId);
        dest.writeString(this.phone);
        dest.writeString(this.areaId);
        dest.writeString(this.areaName);
        dest.writeString(this.address);
        dest.writeString(this.longitude);
        dest.writeString(this.latitude);
        dest.writeString(this.sellGoodsType);
        dest.writeString(this.sellGoodsTypeName);
        dest.writeString(this.description);
        dest.writeString(this.isVerify);
        dest.writeString(this.maxSellType);
        dest.writeString(this.maxGoods);
        dest.writeString(this.maxShopNav);
        dest.writeString(this.maxParentMenus);
        dest.writeString(this.maxChildrenMenus);
        dest.writeString(this.maxAdvert);
        dest.writeString(this.maxShareHref);
    }
}
