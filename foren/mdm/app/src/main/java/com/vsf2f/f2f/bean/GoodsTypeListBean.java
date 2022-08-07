package com.vsf2f.f2f.bean;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GoodsTypeListBean {
    private String goodsGuid;
    private String goodsId;
    private String guid;
    private String id;
    private String marketable;
    private String productName;
    private List<GoodsTypeVoListBean> productsSpecsVoList;
    private String resPrice;
    private String resRatio;
    private String salesPrice;
    private String store;
    private String unit;

    public String getGoodsGuid() {
        return this.goodsGuid;
    }

    public void setGoodsGuid(String goodsGuid) {
        this.goodsGuid = goodsGuid;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMarketable() {
        return this.marketable;
    }

    public void setMarketable(String marketable) {
        this.marketable = marketable;
    }

    public List<GoodsTypeVoListBean> getProductsSpecsVoList() {
        return this.productsSpecsVoList;
    }

    public void setProductsSpecsVoList(List<GoodsTypeVoListBean> productsSpecsVoList) {
        this.productsSpecsVoList = productsSpecsVoList;
    }

    public String getResPrice() {
        return this.resPrice;
    }

    public void setResPrice(String resPrice) {
        this.resPrice = resPrice;
    }

    public String getResRatio() {
        return this.resRatio;
    }

    public void setResRatio(String resRatio) {
        this.resRatio = resRatio;
    }

    public String getSalesPrice() {
        return this.salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getStore() {
        return this.store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String toString() {
        return "GoodsTypeListBean{goodsGuid='" + this.goodsGuid + "', id=" + this.id + ", guid='" + this.guid + "', goodsId=" + this.goodsId + ", productName='" + this.productName + "', unit='" + this.unit + "', salesPrice=" + this.salesPrice + ", resPrice=" + this.resPrice + ", resRatio=" + this.resRatio + ", store=" + this.store + ", productsSpecsVoList=" + this.productsSpecsVoList + '}';
    }

    public GoodsTypeListBean(String str, String resPrice, String salesPrice, String store, String s) {
        this.resPrice = resPrice;
        this.salesPrice = salesPrice;
        this.store = store;
    }

    public GoodsTypeListBean(String goodsId, String goodsGuid, String productName, String resRatio, String salesPrice, String store, String specValue) {
        this.productsSpecsVoList = new ArrayList();
        this.productsSpecsVoList.add(new GoodsTypeVoListBean(specValue));
        this.salesPrice = salesPrice;
        this.store = store;
        this.goodsId = goodsId;
        this.goodsGuid = goodsGuid;
        this.productName = productName;
        this.resRatio = resRatio;
    }
}
