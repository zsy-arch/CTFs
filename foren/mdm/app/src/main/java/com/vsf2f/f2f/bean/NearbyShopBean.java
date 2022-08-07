package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NearbyShopBean implements Serializable {
    private double lastDistance;
    private int lastId;
    private int pageIndex;
    private List<RowsBean> rows;

    public double getLastDistance() {
        return this.lastDistance;
    }

    public void setLastDistance(double lastDistance) {
        this.lastDistance = lastDistance;
    }

    public int getLastId() {
        return this.lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<RowsBean> getRows() {
        if (this.rows == null) {
            this.rows = new ArrayList();
        }
        return this.rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    /* loaded from: classes2.dex */
    public static class RowsBean implements Serializable {
        private boolean collection;
        private int customerId;
        private double distance;
        private int goodsCount;
        private int id;
        private double lat;
        private double lng;
        private LogoBean logo;
        private int pictureId;
        private int salesCount;
        private String storeName;
        private String userName;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCustomerId() {
            return this.customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getPictureId() {
            return this.pictureId;
        }

        public void setPictureId(int pictureId) {
            this.pictureId = pictureId;
        }

        public String getStoreName() {
            return this.storeName;
        }

        public boolean isCollection() {
            return this.collection;
        }

        public void setCollection(boolean collection) {
            this.collection = collection;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getSalesCount() {
            return this.salesCount;
        }

        public void setSalesCount(int salesCount) {
            this.salesCount = salesCount;
        }

        public int getGoodsCount() {
            return this.goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public double getLng() {
            return this.lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return this.lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getDistance() {
            return this.distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public LogoBean getLogo() {
            if (this.logo == null) {
                this.logo = new LogoBean();
            }
            return this.logo;
        }

        public void setLogo(LogoBean logo) {
            this.logo = logo;
        }

        /* loaded from: classes2.dex */
        public static class LogoBean implements Serializable {
            private int id;
            private String path;
            private String spath;

            public int getId() {
                return this.id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPath() {
                return this.path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getSpath() {
                return this.spath;
            }

            public void setSpath(String spath) {
                this.spath = spath;
            }
        }
    }
}
