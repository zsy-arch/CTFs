package com.vsf2f.f2f.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class UserSimpleBean implements Serializable {
    private String name;
    private String nickName;
    private String phone;
    private String userName;
    private UserPicBean userPic;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
