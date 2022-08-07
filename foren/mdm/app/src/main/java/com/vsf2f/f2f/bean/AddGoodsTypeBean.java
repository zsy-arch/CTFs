package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class AddGoodsTypeBean {
    private List<ProductsListBean> productsList;

    public List<ProductsListBean> getProductsList() {
        return this.productsList;
    }

    public void setProductsList(List<ProductsListBean> productsList) {
        this.productsList = productsList;
    }

    /* loaded from: classes2.dex */
    public static class ProductsListBean {
        private String goodsGuid;
        private int goodsId;
        private int issuer;
        private String marketable;
        private String productName;
        private List<ProductsSpecsVoListBean> productsSpecsVoList;
        private int resRatio;
        private String salesPrice;
        private String status;
        private int store;

        public int getGoodsId() {
            return this.goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsGuid() {
            return this.goodsGuid;
        }

        public void setGoodsGuid(String goodsGuid) {
            this.goodsGuid = goodsGuid;
        }

        public int getIssuer() {
            return this.issuer;
        }

        public void setIssuer(int issuer) {
            this.issuer = issuer;
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

        public int getResRatio() {
            return this.resRatio;
        }

        public void setResRatio(int resRatio) {
            this.resRatio = resRatio;
        }

        public int getStore() {
            return this.store;
        }

        public void setStore(int store) {
            this.store = store;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMarketable() {
            return this.marketable;
        }

        public void setMarketable(String marketable) {
            this.marketable = marketable;
        }

        public List<ProductsSpecsVoListBean> getProductsSpecsVoList() {
            return this.productsSpecsVoList;
        }

        public void setProductsSpecsVoList(List<ProductsSpecsVoListBean> productsSpecsVoList) {
            this.productsSpecsVoList = productsSpecsVoList;
        }

        /* loaded from: classes2.dex */
        public static class ProductsSpecsVoListBean {
            private int proNum;
            private String proPrice;
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

            public int getProNum() {
                return this.proNum;
            }

            public void setProNum(int proNum) {
                this.proNum = proNum;
            }

            public String getProPrice() {
                return this.proPrice;
            }

            public void setProPrice(String proPrice) {
                this.proPrice = proPrice;
            }
        }
    }
}
