package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class GoodSingleSpecListTemp {
    private String specGuid;
    private String specName;
    private String specValue;
    private String specValueGuid;

    public String getSpecGuid() {
        return this.specGuid;
    }

    public void setSpecGuid(String specGuid) {
        this.specGuid = specGuid;
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

    public String getSpecValueGuid() {
        return this.specValueGuid;
    }

    public void setSpecValueGuid(String specValueGuid) {
        this.specValueGuid = specValueGuid;
    }

    public String toString() {
        return "{\"specGuid\":\"" + this.specGuid + "\",\"specName\":\"" + this.specName + "\",\"specValue\":\"" + this.specValue + "\",\"specValueGuid\":\"" + this.specValueGuid + "\"}";
    }
}
