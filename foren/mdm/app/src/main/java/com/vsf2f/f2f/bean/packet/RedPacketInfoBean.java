package com.vsf2f.f2f.bean.packet;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class RedPacketInfoBean implements Serializable {
    public String RedpacketKeyRedpacketGreeting;
    public String createTime;
    public int groupMemberCount;
    public String headURL;
    public boolean received;
    public String redPacketId;
    public String redPacketType;
    public String singleAmount;
    public String toGroupId;
    public String toUserId;
    public String totalAmount;
    public String userName;
    public String type = "redPacket";
    public int moneyType = 0;
    public int module = 0;
    public int totalNum = 1;

    public RedPacketInfoBean() {
    }

    public RedPacketInfoBean(String userId) {
        this.toUserId = userId;
        this.toGroupId = userId;
    }

    public String getRedPacketId() {
        return this.redPacketId;
    }

    public void setRedPacketId(String redPacketId) {
        this.redPacketId = redPacketId;
    }

    public String getToUserId() {
        return this.toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToGroupId() {
        return this.toGroupId;
    }

    public void setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
    }

    public String getHeadURL() {
        return this.headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRedpacketKeyRedpacketGreeting() {
        return this.RedpacketKeyRedpacketGreeting;
    }

    public void setRedpacketKeyRedpacketGreeting(String redpacketKeyRedpacketGreeting) {
        this.RedpacketKeyRedpacketGreeting = redpacketKeyRedpacketGreeting;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMoneyType() {
        return this.moneyType;
    }

    public void setMoneyType(int moneyType) {
        this.moneyType = moneyType;
    }

    public int getModule() {
        return this.module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public String getSingleAmount() {
        return this.singleAmount;
    }

    public void setSingleAmount(String singleAmount) {
        this.singleAmount = singleAmount;
    }

    public String getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public boolean isReceived() {
        return this.received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRedPacketType() {
        return this.redPacketType;
    }

    public void setRedPacketType(String redPacketType) {
        this.redPacketType = redPacketType;
    }

    public int getGroupMemberCount() {
        return this.groupMemberCount;
    }

    public void setGroupMemberCount(int groupMemberCount) {
        this.groupMemberCount = groupMemberCount;
    }
}
