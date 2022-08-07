package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class GoodsTypeVoListBean {
    private String productGuid;
    private String specGuid;
    private String specName;
    private String specValue;
    private String specValueGuid;

    public GoodsTypeVoListBean() {
    }

    public GoodsTypeVoListBean(String specValue) {
        this.specGuid = "VSF2F0COMMON0SPEC0GUID0FOR0MODEL";
        this.specName = "型号";
        this.specValue = specValue;
    }

    public String getProductGuid() {
        return this.productGuid;
    }

    public void setProductGuid(String productGuid) {
        this.productGuid = productGuid;
    }

    public String getSpecGuid() {
        return this.specGuid;
    }

    public void setSpecGuid(String specGuid) {
        this.specGuid = specGuid;
    }

    public String getSpecValueGuid() {
        return this.specValueGuid;
    }

    public void setSpecValueGuid(String specValueGuid) {
        this.specValueGuid = specValueGuid;
    }

    public String getSpecName() {
        return this.specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecValue() {
        return this.specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }

    public String toString() {
        return "GoodsTypeVoListBean{productGuid='" + this.productGuid + "', specGuid='" + this.specGuid + "', specValueGuid='" + this.specValueGuid + "', specName='" + this.specName + "', specValue='" + this.specValue + "'}";
    }
}
