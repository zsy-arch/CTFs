package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class GoodSingleSpec {
    private String guid;
    private GoodSingleSpecList listtemp;
    private String showType;
    private String specName;
    private String type;
    private List<GoodSingleSpecList> values;

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public GoodSingleSpecList getListtemp() {
        return this.listtemp;
    }

    public void setListtemp(GoodSingleSpecList listtemp) {
        this.listtemp = listtemp;
    }

    public String getShowType() {
        return this.showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getSpecName() {
        return this.specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GoodSingleSpecList> getValues() {
        return this.values;
    }

    public void setValues(List<GoodSingleSpecList> values) {
        this.values = values;
    }

    public String toString() {
        return "GoodSingleSpec{guid='" + this.guid + "', specName='" + this.specName + "', type='" + this.type + "', showType='" + this.showType + "', values=" + this.values + ", listtemp=" + this.listtemp + '}';
    }

    public GoodSingleSpec(String specName) {
        this.specName = specName;
    }
}
