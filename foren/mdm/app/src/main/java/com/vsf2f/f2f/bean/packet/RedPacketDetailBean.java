package com.vsf2f.f2f.bean.packet;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class RedPacketDetailBean implements Serializable {
    private long createTime;
    private String description;
    private List<DetailListBean> detailList;
    private long expireTime;
    private long id;
    private int module;
    private long receiveGroup;
    private String receiver;
    private double singleAmount;
    private int status;
    private double surplusAmount;
    private int surplusNum;
    private double totalAmount;
    private int totalNum;
    private int type;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getModule() {
        return this.module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public long getReceiveGroup() {
        return this.receiveGroup;
    }

    public void setReceiveGroup(long receiveGroup) {
        this.receiveGroup = receiveGroup;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSingleAmount() {
        return this.singleAmount;
    }

    public void setSingleAmount(double singleAmount) {
        this.singleAmount = singleAmount;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getSurplusAmount() {
        return this.surplusAmount;
    }

    public void setSurplusAmount(double surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getSurplusNum() {
        return this.surplusNum;
    }

    public void setSurplusNum(int surplusNum) {
        this.surplusNum = surplusNum;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public List<DetailListBean> getDetailList() {
        return this.detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    /* loaded from: classes2.dex */
    public static class DetailListBean {
        private double amount;
        private int luckiestFlag;
        private long receiveGroup;
        private long receiveTime;
        private String receiver;
        private ReceiverObjBean receiverObj;

        public double getAmount() {
            return this.amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getReceiver() {
            return this.receiver;
        }

        public String getReceiverNick() {
            return (this.receiverObj == null || TextUtils.isEmpty(this.receiverObj.getNickName())) ? this.receiver : this.receiverObj.getNickName();
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public ReceiverObjBean getReceiverObj() {
            if (this.receiverObj == null) {
                this.receiverObj = new ReceiverObjBean();
            }
            return this.receiverObj;
        }

        public void setReceiverObj(ReceiverObjBean receiverObj) {
            this.receiverObj = receiverObj;
        }

        public long getReceiveGroup() {
            return this.receiveGroup;
        }

        public void setReceiveGroup(long receiveGroup) {
            this.receiveGroup = receiveGroup;
        }

        public long getReceiveTime() {
            return this.receiveTime;
        }

        public void setReceiveTime(long receiveTime) {
            this.receiveTime = receiveTime;
        }

        public int getLuckiestFlag() {
            return this.luckiestFlag;
        }

        public void setLuckiestFlag(int luckiestFlag) {
            this.luckiestFlag = luckiestFlag;
        }

        /* loaded from: classes2.dex */
        public static class ReceiverObjBean {
            private String name;
            private String nickName;
            private String picUrl;

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

            public String getPicUrl() {
                return this.picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }
        }
    }
}
