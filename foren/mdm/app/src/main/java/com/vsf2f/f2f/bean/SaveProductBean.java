package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class SaveProductBean implements Parcelable {
    public static final Parcelable.Creator<SaveProductBean> CREATOR = new Parcelable.Creator<SaveProductBean>() { // from class: com.vsf2f.f2f.bean.SaveProductBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SaveProductBean createFromParcel(Parcel in) {
            return new SaveProductBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SaveProductBean[] newArray(int size) {
            return new SaveProductBean[size];
        }
    };
    private String attachPictureIds;
    private int commentCount;
    private long createdAt;
    private String createdAtStr;
    private double defaultFreight;
    private int defaultImageId;
    private String explains;
    private int goodsCategoryId;
    private String goodsName;
    private int goodsTypeId;
    private String guid;
    private int id;
    private int isRecom;
    private int issuer;
    private int limitBuyType;
    private String marketable;
    private String noSales;
    private String onSpec;
    private double resPrice;
    private int resRatio;
    private int salesCount;
    private String salesPrice;
    private String shopMenusId;
    private String status;
    private int store;
    private long updatedAt;
    private String updatedAtStr;
    private long uptime;
    private String uptimeStr;
    private int usableCoupon;
    private int usableQuantity;
    private int viewCount;

    public SaveProductBean() {
    }

    protected SaveProductBean(Parcel in) {
        this.id = in.readInt();
        this.guid = in.readString();
        this.issuer = in.readInt();
        this.shopMenusId = in.readString();
        this.goodsName = in.readString();
        this.goodsTypeId = in.readInt();
        this.goodsCategoryId = in.readInt();
        this.store = in.readInt();
        this.salesPrice = in.readString();
        this.resPrice = in.readDouble();
        this.resRatio = in.readInt();
        this.usableCoupon = in.readInt();
        this.usableQuantity = in.readInt();
        this.limitBuyType = in.readInt();
        this.defaultFreight = in.readDouble();
        this.defaultImageId = in.readInt();
        this.explains = in.readString();
        this.onSpec = in.readString();
        this.noSales = in.readString();
        this.status = in.readString();
        this.marketable = in.readString();
        this.isRecom = in.readInt();
        this.commentCount = in.readInt();
        this.viewCount = in.readInt();
        this.salesCount = in.readInt();
        this.uptime = in.readLong();
        this.createdAt = in.readLong();
        this.updatedAt = in.readLong();
        this.uptimeStr = in.readString();
        this.createdAtStr = in.readString();
        this.updatedAtStr = in.readString();
        this.attachPictureIds = in.readString();
    }

    public String toString() {
        return "SaveProductBean{id=" + this.id + ", guid='" + this.guid + "', issuer=" + this.issuer + ", shopMenusId='" + this.shopMenusId + "', goodsName='" + this.goodsName + "', goodsTypeId=" + this.goodsTypeId + ", goodsCategoryId=" + this.goodsCategoryId + ", store=" + this.store + ", salesPrice=" + this.salesPrice + ", resPrice=" + this.resPrice + ", resRatio=" + this.resRatio + ", usableCoupon=" + this.usableCoupon + ", usableQuantity=" + this.usableQuantity + ", limitBuyType=" + this.limitBuyType + ", defaultFreight=" + this.defaultFreight + ", defaultImageId=" + this.defaultImageId + ", explains='" + this.explains + "', onSpec='" + this.onSpec + "', noSales='" + this.noSales + "', status='" + this.status + "', marketable='" + this.marketable + "', isRecom=" + this.isRecom + ", commentCount=" + this.commentCount + ", viewCount=" + this.viewCount + ", salesCount=" + this.salesCount + ", uptime=" + this.uptime + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", uptimeStr='" + this.uptimeStr + "', createdAtStr='" + this.createdAtStr + "', updatedAtStr='" + this.updatedAtStr + "', attachPictureIds='" + this.attachPictureIds + "'}";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getIssuer() {
        return this.issuer;
    }

    public void setIssuer(int issuer) {
        this.issuer = issuer;
    }

    public String getShopMenusId() {
        return this.shopMenusId;
    }

    public void setShopMenusId(String shopMenusId) {
        this.shopMenusId = shopMenusId;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsTypeId() {
        return this.goodsTypeId;
    }

    public void setGoodsTypeId(int goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public int getGoodsCategoryId() {
        return this.goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public int getStore() {
        return this.store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public String getSalesPrice() {
        return this.salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getResPrice() {
        return this.resPrice;
    }

    public void setResPrice(double resPrice) {
        this.resPrice = resPrice;
    }

    public int getResRatio() {
        return this.resRatio;
    }

    public void setResRatio(int resRatio) {
        this.resRatio = resRatio;
    }

    public int getUsableCoupon() {
        return this.usableCoupon;
    }

    public void setUsableCoupon(int usableCoupon) {
        this.usableCoupon = usableCoupon;
    }

    public int getUsableQuantity() {
        return this.usableQuantity;
    }

    public void setUsableQuantity(int usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public int getLimitBuyType() {
        return this.limitBuyType;
    }

    public void setLimitBuyType(int limitBuyType) {
        this.limitBuyType = limitBuyType;
    }

    public double getDefaultFreight() {
        return this.defaultFreight;
    }

    public void setDefaultFreight(double defaultFreight) {
        this.defaultFreight = defaultFreight;
    }

    public int getDefaultImageId() {
        return this.defaultImageId;
    }

    public void setDefaultImageId(int defaultImageId) {
        this.defaultImageId = defaultImageId;
    }

    public String getExplains() {
        return this.explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getOnSpec() {
        return this.onSpec;
    }

    public void setOnSpec(String onSpec) {
        this.onSpec = onSpec;
    }

    public String getNoSales() {
        return this.noSales;
    }

    public void setNoSales(String noSales) {
        this.noSales = noSales;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMarketable() {
        return this.marketable;
    }

    public void setMarketable(String marketable) {
        this.marketable = marketable;
    }

    public int getIsRecom() {
        return this.isRecom;
    }

    public void setIsRecom(int isRecom) {
        this.isRecom = isRecom;
    }

    public int getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getSalesCount() {
        return this.salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public long getUptime() {
        return this.uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUptimeStr() {
        return this.uptimeStr;
    }

    public void setUptimeStr(String uptimeStr) {
        this.uptimeStr = uptimeStr;
    }

    public String getCreatedAtStr() {
        return this.createdAtStr;
    }

    public void setCreatedAtStr(String createdAtStr) {
        this.createdAtStr = createdAtStr;
    }

    public String getUpdatedAtStr() {
        return this.updatedAtStr;
    }

    public void setUpdatedAtStr(String updatedAtStr) {
        this.updatedAtStr = updatedAtStr;
    }

    public String getAttachPictureIds() {
        return this.attachPictureIds;
    }

    public void setAttachPictureIds(String attachPictureIds) {
        this.attachPictureIds = attachPictureIds;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.guid);
        dest.writeInt(this.issuer);
        dest.writeString(this.shopMenusId);
        dest.writeString(this.goodsName);
        dest.writeInt(this.goodsTypeId);
        dest.writeInt(this.goodsCategoryId);
        dest.writeInt(this.store);
        dest.writeString(this.salesPrice);
        dest.writeDouble(this.resPrice);
        dest.writeInt(this.resRatio);
        dest.writeInt(this.usableCoupon);
        dest.writeInt(this.usableQuantity);
        dest.writeInt(this.limitBuyType);
        dest.writeDouble(this.defaultFreight);
        dest.writeInt(this.defaultImageId);
        dest.writeString(this.explains);
        dest.writeString(this.onSpec);
        dest.writeString(this.noSales);
        dest.writeString(this.status);
        dest.writeString(this.marketable);
        dest.writeInt(this.isRecom);
        dest.writeInt(this.commentCount);
        dest.writeInt(this.viewCount);
        dest.writeInt(this.salesCount);
        dest.writeLong(this.uptime);
        dest.writeLong(this.createdAt);
        dest.writeLong(this.updatedAt);
        dest.writeString(this.uptimeStr);
        dest.writeString(this.createdAtStr);
        dest.writeString(this.updatedAtStr);
        dest.writeString(this.attachPictureIds);
    }
}
