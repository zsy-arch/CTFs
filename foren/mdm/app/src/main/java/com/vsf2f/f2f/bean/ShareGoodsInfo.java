package com.vsf2f.f2f.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class ShareGoodsInfo implements Serializable {
    private String goodsName;
    private ShareLogo goodsPicture;
    private String guid;
    private String salesPrice;

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public ShareLogo getGoodsPicture() {
        return this.goodsPicture;
    }

    public void setGoodsPicture(ShareLogo goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSalesPrice() {
        return this.salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }
}
