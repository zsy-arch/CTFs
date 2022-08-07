package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class GroupInfoBean {
    private int bizId;
    private ConfigBean config;
    private String content;
    private int creatorId;
    private String creatorName;
    private List<GroupMembersBean> friends;
    private String groupName;
    private GroupPicBean groupPic;
    private int groupType;
    private String imGroupid;
    private int membersNum;
    private int pictureId;
    private SelfConfigBean selfConfig;

    public int getBizId() {
        return this.bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }

    public String getImGroupid() {
        return this.imGroupid;
    }

    public void setImGroupid(String imGroupid) {
        this.imGroupid = imGroupid;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupType() {
        return this.groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public int getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public int getMembersNum() {
        return this.membersNum;
    }

    public void setMembersNum(int membersNum) {
        this.membersNum = membersNum;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ConfigBean getConfig() {
        if (this.config == null) {
            this.config = new ConfigBean();
        }
        return this.config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    public SelfConfigBean getSelfConfig() {
        return this.selfConfig;
    }

    public void setSelfConfig(SelfConfigBean selfConfig) {
        this.selfConfig = selfConfig;
    }

    public GroupPicBean getGroupPic() {
        if (this.groupPic == null) {
            this.groupPic = new GroupPicBean();
        }
        return this.groupPic;
    }

    public void setGroupPic(GroupPicBean groupPic) {
        this.groupPic = groupPic;
    }

    public List<GroupMembersBean> getFriends() {
        return this.friends;
    }

    public void setFriends(List<GroupMembersBean> friends) {
        this.friends = friends;
    }

    /* loaded from: classes2.dex */
    public static class ConfigBean {
        private int approval;
        private int bizId;
        private int groupLimit;
        private int invita;
        private int isOpen;
        private int limit;

        public int getBizId() {
            return this.bizId;
        }

        public void setBizId(int bizId) {
            this.bizId = bizId;
        }

        public int getGroupLimit() {
            return this.groupLimit;
        }

        public void setGroupLimit(int groupLimit) {
            this.groupLimit = groupLimit;
        }

        public int getIsOpen() {
            return this.isOpen;
        }

        public void setIsOpen(int isOpen) {
            this.isOpen = isOpen;
        }

        public int getApproval() {
            return this.approval;
        }

        public void setApproval(int approval) {
            this.approval = approval;
        }

        public int getInvita() {
            return this.invita;
        }

        public void setInvita(int invita) {
            this.invita = invita;
        }

        public int getLimit() {
            return this.limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }
    }

    /* loaded from: classes2.dex */
    public static class SelfConfigBean {
        private int bizId;
        private String isNormal;
        private String isOwner;
        private String isShared;
        private String msgConfig;
        private String nickName;
        private String status;

        public int getBizId() {
            return this.bizId;
        }

        public void setBizId(int bizId) {
            this.bizId = bizId;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getIsOwner() {
            return this.isOwner;
        }

        public void setIsOwner(String isOwner) {
            this.isOwner = isOwner;
        }

        public String getIsShared() {
            return this.isShared;
        }

        public void setIsShared(String isShared) {
            this.isShared = isShared;
        }

        public String getIsNormal() {
            return this.isNormal;
        }

        public void setIsNormal(String isNormal) {
            this.isNormal = isNormal;
        }

        public String getMsgConfig() {
            return this.msgConfig;
        }

        public void setMsgConfig(String msgConfig) {
            this.msgConfig = msgConfig;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    /* loaded from: classes2.dex */
    public static class GroupPicBean {
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
