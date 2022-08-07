package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ProductBean implements Parcelable {
    public static final Parcelable.Creator<ProductBean> CREATOR = new Parcelable.Creator<ProductBean>() { // from class: com.vsf2f.f2f.bean.ProductBean.1
        @Override // android.os.Parcelable.Creator
        public ProductBean createFromParcel(Parcel in) {
            return new ProductBean(in);
        }

        @Override // android.os.Parcelable.Creator
        public ProductBean[] newArray(int size) {
            return new ProductBean[size];
        }
    };
    private String attachPictureIds;
    private int commentCount;
    private long createdAt;
    private String createdAtStr;
    private String defaultFreight;
    private int defaultImageId;
    private long downTime;
    private String downTimeStr;
    private String explains;
    private int favoritesCount;
    private int goodsCategoryId;
    private String goodsName;
    private int goodsTypeId;
    private String guid;
    private int id;
    private boolean isCheck;
    private int isRecom;
    private int issuer;
    private int limitBuyType;
    private String marketable;
    private String noSales;
    private String onSpec;
    private PicBean picture;
    private String readUrl;
    private double resPrice;
    private int resRatio;
    private int salesCount;
    private double salesPrice;
    private ShareThirdBean shareThird;
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

    public ProductBean() {
    }

    protected ProductBean(Parcel in) {
        this.id = in.readInt();
        this.guid = in.readString();
        this.issuer = in.readInt();
        this.shopMenusId = in.readString();
        this.goodsName = in.readString();
        this.goodsTypeId = in.readInt();
        this.goodsCategoryId = in.readInt();
        this.store = in.readInt();
        this.resRatio = in.readInt();
        this.resPrice = in.readDouble();
        this.usableCoupon = in.readInt();
        this.usableQuantity = in.readInt();
        this.limitBuyType = in.readInt();
        this.defaultFreight = in.readString();
        this.defaultImageId = in.readInt();
        this.explains = in.readString();
        this.onSpec = in.readString();
        this.noSales = in.readString();
        this.status = in.readString();
        this.marketable = in.readString();
        this.isRecom = in.readInt();
        this.commentCount = in.readInt();
        this.favoritesCount = in.readInt();
        this.viewCount = in.readInt();
        this.salesCount = in.readInt();
        this.uptime = in.readLong();
        this.createdAt = in.readLong();
        this.downTime = in.readLong();
        this.updatedAt = in.readLong();
        this.readUrl = in.readString();
        this.uptimeStr = in.readString();
        this.downTimeStr = in.readString();
        this.createdAtStr = in.readString();
        this.updatedAtStr = in.readString();
        this.attachPictureIds = in.readString();
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
        dest.writeInt(this.resRatio);
        dest.writeDouble(this.resPrice);
        dest.writeInt(this.usableCoupon);
        dest.writeInt(this.usableQuantity);
        dest.writeInt(this.limitBuyType);
        dest.writeString(this.defaultFreight);
        dest.writeInt(this.defaultImageId);
        dest.writeString(this.explains);
        dest.writeString(this.onSpec);
        dest.writeString(this.noSales);
        dest.writeString(this.status);
        dest.writeString(this.marketable);
        dest.writeInt(this.isRecom);
        dest.writeInt(this.commentCount);
        dest.writeInt(this.favoritesCount);
        dest.writeInt(this.viewCount);
        dest.writeInt(this.salesCount);
        dest.writeLong(this.uptime);
        dest.writeLong(this.createdAt);
        dest.writeLong(this.downTime);
        dest.writeLong(this.updatedAt);
        dest.writeString(this.readUrl);
        dest.writeString(this.uptimeStr);
        dest.writeString(this.downTimeStr);
        dest.writeString(this.createdAtStr);
        dest.writeString(this.updatedAtStr);
        dest.writeString(this.attachPictureIds);
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

    public double getSalesPrice() {
        return this.salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
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

    public void setDefaultFreight(String defaultFreight) {
        this.defaultFreight = defaultFreight;
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

    public void setCommentCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public int getFavoritesCount() {
        return this.favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
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

    public long getDownTime() {
        return this.downTime;
    }

    public void setDownTime(long downTime) {
        this.downTime = downTime;
    }

    public String getReadUrl() {
        return this.readUrl;
    }

    public void setReadUrl(String readUrl) {
        this.readUrl = readUrl;
    }

    public String getDownTimeStr() {
        return this.downTimeStr;
    }

    public void setDownTimeStr(String downTimeStr) {
        this.downTimeStr = downTimeStr;
    }

    public PicBean getPicture() {
        if (this.picture == null) {
            this.picture = new PicBean();
        }
        return this.picture;
    }

    public void setPicture(PicBean picture) {
        this.picture = picture;
    }

    public ShareThirdBean getShareThird() {
        return this.shareThird;
    }

    public void setShareThird(ShareThirdBean shareThird) {
        this.shareThird = shareThird;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean check) {
        this.isCheck = check;
    }

    public String toString() {
        return "ProductBean{id=" + this.id + ", guid='" + this.guid + "', issuer=" + this.issuer + ", shopMenusId='" + this.shopMenusId + "', goodsName='" + this.goodsName + "', goodsTypeId=" + this.goodsTypeId + ", goodsCategoryId=" + this.goodsCategoryId + ", store=" + this.store + ", salesPrice=" + this.salesPrice + ", resPrice=" + this.resPrice + ", usableCoupon=" + this.usableCoupon + ", usableQuantity=" + this.usableQuantity + ", limitBuyType=" + this.limitBuyType + ", defaultFreight=" + this.defaultFreight + ", defaultImageId=" + this.defaultImageId + ", explains='" + this.explains + "', onSpec='" + this.onSpec + "', noSales='" + this.noSales + "', status='" + this.status + "', marketable='" + this.marketable + "', isRecom=" + this.isRecom + ", commentCount=" + this.commentCount + ", viewCount=" + this.viewCount + ", salesCount=" + this.salesCount + ", uptime=" + this.uptime + ", createdAt=" + this.createdAt + ", downTime=" + this.downTime + ", updatedAt=" + this.updatedAt + ", readUrl='" + this.readUrl + "', uptimeStr='" + this.uptimeStr + "', downTimeStr='" + this.downTimeStr + "', createdAtStr='" + this.createdAtStr + "', updatedAtStr='" + this.updatedAtStr + "', attachPictureIds='" + this.attachPictureIds + "', shareThird=" + this.shareThird + ", picture=" + this.picture + ", isCheck=" + this.isCheck + '}';
    }
}
