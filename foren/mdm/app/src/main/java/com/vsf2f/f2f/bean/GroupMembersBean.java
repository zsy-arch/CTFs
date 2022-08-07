package com.vsf2f.f2f.bean;

import com.easeui.domain.EaseUser;

/* loaded from: classes2.dex */
public class GroupMembersBean {
    private String customersId;
    private String isOwner;
    private String nickName;
    private String pictureId;
    private String userName;
    private UserPicBean userPic;

    public GroupMembersBean() {
    }

    public GroupMembersBean(String username) {
        this.userName = username;
    }

    public GroupMembersBean(EaseUser user) {
        this.userName = user.getUsername();
        this.nickName = user.getNickname();
        this.userPic = new UserPicBean(user.getAvatar());
    }

    public String getCustomersId() {
        return this.customersId;
    }

    public void setCustomersId(String customersId) {
        this.customersId = customersId;
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

    public String getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getIsOwner() {
        return this.isOwner;
    }

    public void setIsOwner(String isOwner) {
        this.isOwner = isOwner;
    }

    public UserPicBean getUserPic() {
        return this.userPic;
    }

    public void setUserPic(UserPicBean userPic) {
        this.userPic = userPic;
    }
}
