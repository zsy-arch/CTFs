package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ProductBaseBean implements Parcelable {
    public static final Parcelable.Creator<ProductBaseBean> CREATOR = new Parcelable.Creator<ProductBaseBean>() { // from class: com.vsf2f.f2f.bean.ProductBaseBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProductBaseBean createFromParcel(Parcel source) {
            return new ProductBaseBean(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProductBaseBean[] newArray(int size) {
            return new ProductBaseBean[size];
        }
    };
    private List<AttachPictureListBean> attachPictureList;
    private double defaultFreight;
    private int defaultImageId;
    private String explains;
    private int goodsCategoryId;
    private String goodsCategoryName;
    private String goodsName;
    private GoodsPictureBean goodsPicture;
    private String goodsTypeGuid;
    private int goodsTypeId;
    private String guid;
    private int id;
    private int issuer;
    private String onSpec;
    private List<GoodsTypeListBean> productsList;
    private String readUrl;
    private double resPrice;
    private int resRatio;
    private double salesPrice;
    private String shopMenusId;
    private String shopMenusName;
    private int store;
    private int usableCoupon;

    public int getUsableCoupon() {
        return this.usableCoupon;
    }

    public void setUsableCoupon(int usableCoupon) {
        this.usableCoupon = usableCoupon;
    }

    public String getOnSpec() {
        return this.onSpec == null ? "" : this.onSpec;
    }

    public void setOnSpec(String onSpec) {
        this.onSpec = onSpec;
    }

    public List<GoodsTypeListBean> getProductsList() {
        return this.productsList;
    }

    public void setProductsList(List<GoodsTypeListBean> productsList) {
        this.productsList = productsList;
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

    public int getDefaultImageId() {
        return this.defaultImageId;
    }

    public void setDefaultImageId(int defaultImageId) {
        this.defaultImageId = defaultImageId;
    }

    public String getShopMenusId() {
        return this.shopMenusId;
    }

    public void setShopMenusId(String shopMenusId) {
        this.shopMenusId = shopMenusId;
    }

    public String getShopMenusName() {
        return this.shopMenusName;
    }

    public void setShopMenusName(String shopMenusName) {
        this.shopMenusName = shopMenusName;
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

    public String getGoodsTypeGuid() {
        return this.goodsTypeGuid;
    }

    public void setGoodsTypeGuid(String goodsTypeGuid) {
        this.goodsTypeGuid = goodsTypeGuid;
    }

    public int getGoodsCategoryId() {
        return this.goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getGoodsCategoryName() {
        return this.goodsCategoryName;
    }

    public void setGoodsCategoryName(String goodsCategoryName) {
        this.goodsCategoryName = goodsCategoryName;
    }

    public double getDefaultFreight() {
        return this.defaultFreight;
    }

    public void setDefaultFreight(double defaultFreight) {
        this.defaultFreight = defaultFreight;
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

    public String getExplains() {
        return this.explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public GoodsPictureBean getGoodsPicture() {
        return this.goodsPicture;
    }

    public void setGoodsPicture(GoodsPictureBean goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    public String getReadUrl() {
        return this.readUrl;
    }

    public void setReadUrl(String readUrl) {
        this.readUrl = readUrl;
    }

    public List<AttachPictureListBean> getAttachPictureList() {
        return this.attachPictureList;
    }

    public void setAttachPictureList(List<AttachPictureListBean> attachPictureList) {
        this.attachPictureList = attachPictureList;
    }

    /* loaded from: classes2.dex */
    public static class GoodsPictureBean implements Parcelable {
        public static final Parcelable.Creator<GoodsPictureBean> CREATOR = new Parcelable.Creator<GoodsPictureBean>() { // from class: com.vsf2f.f2f.bean.ProductBaseBean.GoodsPictureBean.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GoodsPictureBean createFromParcel(Parcel in) {
                return new GoodsPictureBean(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GoodsPictureBean[] newArray(int size) {
                return new GoodsPictureBean[size];
            }
        };
        private String bucket;
        private String etag;
        private int height;
        private int id;
        private String path;
        private String userName;

        protected GoodsPictureBean(Parcel in) {
            this.id = in.readInt();
            this.userName = in.readString();
            this.path = in.readString();
            this.bucket = in.readString();
            this.etag = in.readString();
            this.height = in.readInt();
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPath() {
            return this.path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getBucket() {
            return this.bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getEtag() {
            return this.etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.userName);
            dest.writeString(this.path);
            dest.writeString(this.bucket);
            dest.writeString(this.etag);
            dest.writeInt(this.height);
        }
    }

    /* loaded from: classes2.dex */
    public static class AttachPictureListBean {
        private int id;
        private PicturesBean pictures;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public PicturesBean getPictures() {
            return this.pictures;
        }

        public void setPictures(PicturesBean pictures) {
            this.pictures = pictures;
        }

        /* loaded from: classes2.dex */
        public static class PicturesBean {
            private int height;
            private int id;
            private String path;
            private String spath;

            public int getId() {
                return this.id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPath() {
                return this.path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getHeight() {
                return this.height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getSpath() {
                return this.spath;
            }

            public void setSpath(String spath) {
                this.spath = spath;
            }
        }
    }

    public ProductBaseBean() {
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
        dest.writeInt(this.defaultImageId);
        dest.writeString(this.shopMenusId);
        dest.writeString(this.shopMenusName);
        dest.writeString(this.goodsName);
        dest.writeInt(this.goodsTypeId);
        dest.writeString(this.goodsTypeGuid);
        dest.writeInt(this.goodsCategoryId);
        dest.writeString(this.goodsCategoryName);
        dest.writeDouble(this.defaultFreight);
        dest.writeInt(this.store);
        dest.writeDouble(this.salesPrice);
        dest.writeDouble(this.resPrice);
        dest.writeInt(this.resRatio);
        dest.writeString(this.explains);
        dest.writeString(this.onSpec);
        dest.writeInt(this.usableCoupon);
        dest.writeParcelable(this.goodsPicture, flags);
        dest.writeString(this.readUrl);
        dest.writeList(this.productsList);
        dest.writeList(this.attachPictureList);
    }

    protected ProductBaseBean(Parcel in) {
        this.id = in.readInt();
        this.guid = in.readString();
        this.issuer = in.readInt();
        this.defaultImageId = in.readInt();
        this.shopMenusId = in.readString();
        this.shopMenusName = in.readString();
        this.goodsName = in.readString();
        this.goodsTypeId = in.readInt();
        this.goodsTypeGuid = in.readString();
        this.goodsCategoryId = in.readInt();
        this.goodsCategoryName = in.readString();
        this.defaultFreight = in.readDouble();
        this.store = in.readInt();
        this.salesPrice = in.readDouble();
        this.resPrice = in.readDouble();
        this.resRatio = in.readInt();
        this.explains = in.readString();
        this.onSpec = in.readString();
        this.usableCoupon = in.readInt();
        this.goodsPicture = (GoodsPictureBean) in.readParcelable(GoodsPictureBean.class.getClassLoader());
        this.readUrl = in.readString();
        this.productsList = new ArrayList();
        in.readList(this.productsList, GoodsTypeListBean.class.getClassLoader());
        this.attachPictureList = new ArrayList();
        in.readList(this.attachPictureList, AttachPictureListBean.class.getClassLoader());
    }
}
