package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class GoodsSpecBean {
    private String model;
    private String price;
    private String stock;

    public GoodsSpecBean() {
    }

    public GoodsSpecBean(String model, String price, String stock) {
        this.model = model;
        this.price = price;
        this.stock = stock;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return this.stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
