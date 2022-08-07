package com.vsf2f.f2f.bean;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class MyContactsRowsBean implements Serializable {
    private int allUnderCount;
    private String appendMoneyStr;
    private String appendProfitMoneyStr;
    private String cjProfitMoneyStr;
    private int directlyUnderCount;
    private int investorId;
    private String investorMoneyStr;
    private boolean leaf;
    private String name;
    private String nickName;
    private String parameter;
    private String phone;
    private int pictureId;
    private String profitMoneyStr;
    private String userName;
    private UserPicBean userPic;

    public int getInvestorId() {
        return this.investorId;
    }

    public void setInvestorId(int investorId) {
        this.investorId = investorId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public boolean isLeaf() {
        return this.leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int getDirectlyUnderCount() {
        return this.directlyUnderCount;
    }

    public void setDirectlyUnderCount(int directlyUnderCount) {
        this.directlyUnderCount = directlyUnderCount;
    }

    public int getAllUnderCount() {
        return this.allUnderCount;
    }

    public void setAllUnderCount(int allUnderCount) {
        this.allUnderCount = allUnderCount;
    }

    public String getInvestorMoneyStr() {
        return this.investorMoneyStr;
    }

    public void setInvestorMoneyStr(String investorMoneyStr) {
        this.investorMoneyStr = investorMoneyStr;
    }

    public String getProfitMoneyStr() {
        return this.profitMoneyStr;
    }

    public void setProfitMoneyStr(String profitMoneyStr) {
        this.profitMoneyStr = profitMoneyStr;
    }

    public String getAppendMoneyStr() {
        return this.appendMoneyStr;
    }

    public void setAppendMoneyStr(String appendMoneyStr) {
        this.appendMoneyStr = appendMoneyStr;
    }

    public String getCjProfitMoneyStr() {
        return this.cjProfitMoneyStr;
    }

    public void setCjProfitMoneyStr(String cjProfitMoneyStr) {
        this.cjProfitMoneyStr = cjProfitMoneyStr;
    }

    public String getAppendProfitMoneyStr() {
        return this.appendProfitMoneyStr;
    }

    public void setAppendProfitMoneyStr(String appendProfitMoneyStr) {
        this.appendProfitMoneyStr = appendProfitMoneyStr;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public UserPicBean getUserPic() {
        return this.userPic;
    }

    public void setUserPic(UserPicBean userPic) {
        this.userPic = userPic;
    }

    /* loaded from: classes2.dex */
    public static class UserPicBean {
        private String bucket;
        private int id;
        private String path;
        private String spath;
        private String userName;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public void setSpath(String path) {
            this.spath = path;
        }

        public String getBucket() {
            return this.bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }
    }
}
