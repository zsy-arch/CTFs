package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class AddSpecBean {
    private String goodsGuid;
    private String goodsId;
    private String guid;
    private String productName;
    private String ratioPosition;
    private String resPrice;
    private String resRatio;
    private String salesPrice;
    private List<SpecListsBean> specLists;
    private String store;
    private String unit;

    public String getRatioPosition() {
        return this.ratioPosition;
    }

    public void setRatioPosition(String ratioPosition) {
        this.ratioPosition = ratioPosition;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsGuid() {
        return this.goodsGuid;
    }

    public void setGoodsGuid(String goodsGuid) {
        this.goodsGuid = goodsGuid;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSalesPrice() {
        return this.salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getResRatio() {
        return this.resRatio;
    }

    public void setResRatio(String resRatio) {
        this.resRatio = resRatio;
    }

    public String getResPrice() {
        return this.resPrice;
    }

    public void setResPrice(String resPrice) {
        this.resPrice = resPrice;
    }

    public String getStore() {
        return this.store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public List<SpecListsBean> getSpecLists() {
        return this.specLists;
    }

    public void setSpecLists(List<SpecListsBean> specLists) {
        this.specLists = specLists;
    }

    /* loaded from: classes2.dex */
    public static class SpecListsBean {
        private String guid;
        private String specName;
        private List<ValuesBean> values;

        public String toString() {
            return "SpecListsBean{guid='" + this.guid + "', specName='" + this.specName + "', values=" + this.values + '}';
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

        public List<ValuesBean> getValues() {
            return this.values;
        }

        public void setValues(List<ValuesBean> values) {
            this.values = values;
        }

        /* loaded from: classes2.dex */
        public static class ValuesBean {
            private String guid;
            private String specValue;

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

            public String toString() {
                return "ValuesBean{guid='" + this.guid + "', specValue='" + this.specValue + "'}";
            }
        }
    }

    public String toString() {
        return "AddSpecBean{goodsId='" + this.goodsId + "', goodsGuid='" + this.goodsGuid + "', guid='" + this.guid + "', productName='" + this.productName + "', salesPrice='" + this.salesPrice + "', unit='" + this.unit + "', resRatio=" + this.resRatio + ", resPrice=" + this.resPrice + ", store='" + this.store + "', ratioPosition='" + this.ratioPosition + "', specLists=" + this.specLists + '}';
    }
}
