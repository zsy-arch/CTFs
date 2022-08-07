package com.vsf2f.f2f.bean;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class NearbyPeopleBean implements Serializable {
    private double lastDistance;
    private long lastId;
    private int pageIndex;
    private List<RowsBean> rows;

    public long getLastId() {
        return this.lastId;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }

    public double getLastDistance() {
        return this.lastDistance;
    }

    public void setLastDistance(double lastDistance) {
        this.lastDistance = lastDistance;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<RowsBean> getRows() {
        return this.rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    /* loaded from: classes2.dex */
    public static class RowsBean {
        private int age;
        private int certAlipay;
        private int certMobile;
        private int certQq;
        private int certRealname;
        private int certWechat;
        private int certZhima;
        private String content;
        private String distance;
        private String gender;
        private double lat;
        private double lng;
        private String nickName;
        private String userName;
        private UserPicBean userPic;
        private int zmScore;

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return TextUtils.isEmpty(this.nickName) ? this.userName : this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getGender() {
            return this.gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCertAlipay() {
            return this.certAlipay;
        }

        public void setCertAlipay(int certAlipay) {
            this.certAlipay = certAlipay;
        }

        public int getCertWechat() {
            return this.certWechat;
        }

        public void setCertWechat(int certWechat) {
            this.certWechat = certWechat;
        }

        public int getCertQq() {
            return this.certQq;
        }

        public void setCertQq(int certQq) {
            this.certQq = certQq;
        }

        public int getCertMobile() {
            return this.certMobile;
        }

        public void setCertMobile(int certMobile) {
            this.certMobile = certMobile;
        }

        public int getCertRealname() {
            return this.certRealname;
        }

        public void setCertRealname(int certRealname) {
            this.certRealname = certRealname;
        }

        public int getCertZhima() {
            return this.certZhima;
        }

        public void setCertZhima(int certZhima) {
            this.certZhima = certZhima;
        }

        public int getZmScore() {
            return this.zmScore;
        }

        public void setZmScore(int zmScore) {
            this.zmScore = zmScore;
        }

        public double getLat() {
            return this.lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return this.lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getDistance() {
            return this.distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public UserPicBean getUserPic() {
            if (this.userPic == null) {
                this.userPic = new UserPicBean();
            }
            return this.userPic;
        }

        public void setUserPic(UserPicBean userPic) {
            this.userPic = userPic;
        }

        /* loaded from: classes2.dex */
        public static class UserPicBean {
            private String path;
            private String spath;

            public String getSpath() {
                return this.spath;
            }

            public void setSpath(String spath) {
                this.spath = spath;
            }

            public String getPath() {
                return this.path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }
}
