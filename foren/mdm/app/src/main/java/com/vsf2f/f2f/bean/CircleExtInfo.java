package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CircleExtInfo implements Parcelable {
    public static final Parcelable.Creator<CircleExtInfo> CREATOR = new Parcelable.Creator<CircleExtInfo>() { // from class: com.vsf2f.f2f.bean.CircleExtInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleExtInfo createFromParcel(Parcel source) {
            return new CircleExtInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleExtInfo[] newArray(int size) {
            return new CircleExtInfo[size];
        }
    };
    private List<adPicBean> adPic;
    private String advType;
    private String bizId;
    private String collection;
    private String content;
    private String goodsName;
    private String guid;
    private String inSale;
    private String logo;
    private List<String> pic;
    private String picUrl;
    private String price;
    private String sales;
    private String storeName;
    private String title;
    private String type;
    private String typeName;
    private String unit;
    private String userName;
    private String videoUrl;

    public CircleExtInfo() {
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public List<String> getPic() {
        return this.pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBizId() {
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSales() {
        return this.sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getInSale() {
        return this.inSale;
    }

    public void setInSale(String inSale) {
        this.inSale = inSale;
    }

    public String getCollection() {
        return this.collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getAdvType() {
        return this.advType;
    }

    public void setAdvType(String advType) {
        this.advType = advType;
    }

    public List<adPicBean> getAdPic() {
        return this.adPic;
    }

    public void setAdPic(List<adPicBean> adPic) {
        this.adPic = adPic;
    }

    /* loaded from: classes2.dex */
    public class adPicBean {
        private double height;
        private String path;
        private double width;

        public adPicBean() {
        }

        public double getRatio() {
            return this.height / this.width;
        }

        public double getHeight() {
            return this.height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getWidth() {
            return this.width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public String getPath() {
            return this.path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.price);
        dest.writeString(this.content);
        dest.writeString(this.userName);
        dest.writeStringList(this.pic);
        dest.writeString(this.goodsName);
        dest.writeString(this.guid);
        dest.writeString(this.unit);
        dest.writeString(this.bizId);
        dest.writeString(this.title);
        dest.writeString(this.typeName);
        dest.writeString(this.videoUrl);
        dest.writeString(this.picUrl);
        dest.writeString(this.logo);
        dest.writeString(this.sales);
        dest.writeString(this.inSale);
        dest.writeString(this.collection);
        dest.writeString(this.storeName);
        dest.writeString(this.advType);
        dest.writeList(this.adPic);
    }

    protected CircleExtInfo(Parcel in) {
        this.type = in.readString();
        this.price = in.readString();
        this.content = in.readString();
        this.userName = in.readString();
        this.pic = in.createStringArrayList();
        this.goodsName = in.readString();
        this.guid = in.readString();
        this.unit = in.readString();
        this.bizId = in.readString();
        this.title = in.readString();
        this.typeName = in.readString();
        this.videoUrl = in.readString();
        this.picUrl = in.readString();
        this.logo = in.readString();
        this.sales = in.readString();
        this.inSale = in.readString();
        this.collection = in.readString();
        this.storeName = in.readString();
        this.advType = in.readString();
        this.adPic = new ArrayList();
        in.readList(this.adPic, adPicBean.class.getClassLoader());
    }
}
