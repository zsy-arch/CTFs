package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class MultipleFindBean {
    private int pageIndex;
    private int results;
    private List<RowsBean> rows;
    private int totalPage;

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getResults() {
        return this.results;
    }

    public void setResults(int results) {
        this.results = results;
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
        private boolean check;
        private int customersId;
        private int friendsCount;
        private int id;
        private String nickName;
        private String phone;
        private int pictureId;
        private int secondFriendsCount;
        private String userName;
        private UserPicBean userPic;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCustomersId() {
            return this.customersId;
        }

        public void setCustomersId(int customersId) {
            this.customersId = customersId;
        }

        public int getPictureId() {
            return this.pictureId;
        }

        public void setPictureId(int pictureId) {
            this.pictureId = pictureId;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public UserPicBean getUserPic() {
            return this.userPic;
        }

        public void setUserPic(UserPicBean userPic) {
            this.userPic = userPic;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getFriendsCount() {
            return this.friendsCount;
        }

        public void setFriendsCount(int friendsCount) {
            this.friendsCount = friendsCount;
        }

        public int getSecondFriendsCount() {
            return this.secondFriendsCount;
        }

        public void setSecondFriendsCount(int secondFriendsCount) {
            this.secondFriendsCount = secondFriendsCount;
        }

        public boolean isCheck() {
            return this.check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }
    }
}
