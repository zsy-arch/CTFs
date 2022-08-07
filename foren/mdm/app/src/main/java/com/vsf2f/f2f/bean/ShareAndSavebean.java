package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class ShareAndSavebean implements Serializable {
    private List<ShareBean> Circles;
    private List<ShareBean> Href;
    private List<ShareBean> Product;
    private List<ShareBean> Share;
    private List<ShareBean> Shop;

    public List<ShareBean> getCircles() {
        return this.Circles;
    }

    public void setCircles(List<ShareBean> circles) {
        this.Circles = circles;
    }

    public List<ShareBean> getHref() {
        return this.Href;
    }

    public void setHref(List<ShareBean> href) {
        this.Href = href;
    }

    public List<ShareBean> getProduct() {
        return this.Product;
    }

    public void setProduct(List<ShareBean> product) {
        this.Product = product;
    }

    public List<ShareBean> getShare() {
        return this.Share;
    }

    public void setShare(List<ShareBean> share) {
        this.Share = share;
    }

    public List<ShareBean> getShop() {
        return this.Shop;
    }

    public void setShop(List<ShareBean> shop) {
        this.Shop = shop;
    }
}
