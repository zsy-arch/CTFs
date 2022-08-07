package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class GoodSingleSpecList {
    private String guid;
    private String specAlias;
    private String specGuid;
    private String specName;
    private String specValue;

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSpecAlias() {
        return this.specAlias;
    }

    public void setSpecAlias(String specAlias) {
        this.specAlias = specAlias;
    }

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

    public String toString() {
        return "GoodSingleSpecList{guid='" + this.guid + "', specValue='" + this.specValue + "', specAlias='" + this.specAlias + "', specGuid='" + this.specGuid + "', specName='" + this.specName + "'}";
    }
}
