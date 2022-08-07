package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class GroupBean {
    private String bizId;
    private String groupHead;
    private String groupId;
    private String groupName;
    private String groupPicture;

    public GroupBean() {
    }

    public GroupBean(String groupId) {
        this.groupId = groupId;
    }

    public GroupBean(GroupInfoBean groupInfo) {
        if (groupInfo != null) {
            this.bizId = groupInfo.getBizId() + "";
            this.groupId = groupInfo.getImGroupid();
            this.groupName = groupInfo.getGroupName();
            if (groupInfo.getGroupPic() != null) {
                this.groupPicture = groupInfo.getGroupPic().getSpath();
            }
        }
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupHead() {
        return this.groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead;
    }

    public String getBizId() {
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getGroupPicture() {
        return this.groupPicture;
    }

    public void setGroupPicture(String groupPicture) {
        this.groupPicture = groupPicture;
    }

    public String toString() {
        return "GroupBean{bizId='" + this.bizId + "',groupId='" + this.groupId + "', groupName='" + this.groupName + "', groupPicture='" + this.groupPicture + "'}";
    }
}
