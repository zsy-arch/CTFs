package com.vsf2f.f2f.bean;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class FriendsListBean implements Serializable {
    private List<RowsBean> Black;
    private List<RowsBean> Delete;
    private long LastTime;
    private int NextPage;
    private List<RowsBean> Normal;
    private int TotalPage;
    private double lastDistance;
    private long lastId;
    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return this.rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

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

    public List<RowsBean> getNormal() {
        return this.Normal;
    }

    public void setNormal(List<RowsBean> normal) {
        this.Normal = normal;
    }

    public List<RowsBean> getBlack() {
        return this.Black;
    }

    public void setBlack(List<RowsBean> black) {
        this.Black = black;
    }

    public List<RowsBean> getDelete() {
        return this.Delete;
    }

    public void setDelete(List<RowsBean> delete) {
        this.Delete = delete;
    }

    public int getNextPage() {
        return this.NextPage;
    }

    public void setNextPage(int nextPage) {
        this.NextPage = nextPage;
    }

    public int getTotalPage() {
        return this.TotalPage;
    }

    public void setTotalPage(int totalPage) {
        this.TotalPage = totalPage;
    }

    public long getLastTime() {
        return this.LastTime;
    }

    public void setLastTime(long lastTime) {
        this.LastTime = lastTime;
    }

    /* loaded from: classes2.dex */
    public static class RowsBean implements Serializable {
        private int age;
        private int certAlipay;
        private int certMobile;
        private int certQq;
        private int certRealname;
        private int certWechat;
        private int certZhima;
        private String content;
        private int friendsCount;
        private int friendsId;
        private String friendsNickname;
        private String friendsUser;
        private int gender;
        private int id;
        private long lastTime;
        private double lat;
        private double lng;
        private String nickName;
        private int status;
        private String userName;
        private UserPicBean userPic;
        private int visible;
        private int zmScore;

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getVisible() {
            return this.visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public long getLastTime() {
            return this.lastTime;
        }

        public void setLastTime(long lastTime) {
            this.lastTime = lastTime;
        }

        public String getNickName() {
            if (TextUtils.isEmpty(this.nickName)) {
                this.nickName = this.userName;
            }
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getFriendsId() {
            return this.friendsId;
        }

        public void setFriendsId(int friendsId) {
            this.friendsId = friendsId;
        }

        public String getFriendsUser() {
            return this.friendsUser;
        }

        public void setFriendsUser(String friendsUser) {
            this.friendsUser = friendsUser;
        }

        public int getGender() {
            return this.gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
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

        public String getFriendsNickname() {
            return this.friendsNickname;
        }

        public void setFriendsNickname(String friendsNickname) {
            this.friendsNickname = friendsNickname;
        }

        public int getFriendsCount() {
            return this.friendsCount;
        }

        public void setFriendsCount(int friendsCount) {
            this.friendsCount = friendsCount;
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
    }
}
