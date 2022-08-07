package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class GetSpecValueBean {
    private String guid;
    private int id;
    private int orderNum;
    private String showType;
    private String specAlias;
    private String specName;
    private String type;
    private List<ValuesBean> values;

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

    public String getSpecName() {
        return this.specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecAlias() {
        return this.specAlias;
    }

    public void setSpecAlias(String specAlias) {
        this.specAlias = specAlias;
    }

    public String getShowType() {
        return this.showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public List<ValuesBean> getValues() {
        return this.values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    /* loaded from: classes2.dex */
    public static class ValuesBean {
        private String guid;
        private int id;
        private String specAlias;
        private String specValue;

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

        public String getSpecValue() {
            return this.specValue;
        }

        public void setSpecValue(String specValue) {
            this.specValue = specValue;
        }

        public String getSpecAlias() {
            return this.specAlias;
        }

        public void setSpecAlias(String specAlias) {
            this.specAlias = specAlias;
        }
    }
}
