package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class UserProfileBean implements Serializable {
    private CustomerBean customer;
    private FriendBean friend;
    private List<UserPicBean> photoAlbum;
    private int points;

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public CustomerBean getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public FriendBean getFriend() {
        return this.friend;
    }

    public void setFriend(FriendBean friend) {
        this.friend = friend;
    }

    public List<UserPicBean> getPhotoAlbum() {
        return this.photoAlbum;
    }

    public void setPhotoAlbum(List<UserPicBean> photoAlbum) {
        this.photoAlbum = photoAlbum;
    }

    /* loaded from: classes2.dex */
    public static class CustomerBean {
        private int age;
        private int attachmentId;
        private int certAlipay;
        private int certMobile;
        private int certQq;
        private int certRealname;
        private int certWechat;
        private int certZhima;
        private String content;
        private String countryCode;
        private String gender;
        private String lat;
        private String lng;
        private String lv;
        private String nickName;
        private String phone;
        private SellerBean seller;
        private String userName;
        private UserPicBean userPic;
        private int visible;
        private int zmScore;

        public int getAttachmentId() {
            return this.attachmentId;
        }

        public void setAttachmentId(int attachmentId) {
            this.attachmentId = attachmentId;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getLv() {
            return this.lv;
        }

        public void setLv(String lv) {
            this.lv = lv;
        }

        public String getCountryCode() {
            return this.countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public UserPicBean getUserPic() {
            if (this.userPic == null) {
                this.userPic = new UserPicBean();
            }
            return this.userPic;
        }

        public void setUserPic(UserPicBean userPic) {
            this.userPic = userPic;
        }

        public SellerBean getSeller() {
            return this.seller;
        }

        public String getSellerName() {
            if (this.seller == null) {
                return null;
            }
            return this.seller.getStoreName();
        }

        public void setSeller(SellerBean seller) {
            this.seller = seller;
        }

        public String getLng() {
            return this.lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return this.lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public int getVisible() {
            return this.visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        /* loaded from: classes2.dex */
        public static class SellerBean {
            private int id;
            private String storeName;

            public int getId() {
                return this.id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStoreName() {
                return this.storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class FriendBean {
        private String friendsNickname;
        private String friendsStatus;

        public String getFriendsNickname() {
            return this.friendsNickname;
        }

        public void setFriendsNickname(String friendsNickname) {
            this.friendsNickname = friendsNickname;
        }

        public String getFriendsStatus() {
            return this.friendsStatus;
        }

        public void setFriendsStatus(String friendsStatus) {
            this.friendsStatus = friendsStatus;
        }
    }

    /* loaded from: classes2.dex */
    public static class UserPicBean {
        private String path;
        private String spath;

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
