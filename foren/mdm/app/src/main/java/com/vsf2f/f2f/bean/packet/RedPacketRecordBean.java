package com.vsf2f.f2f.bean.packet;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class RedPacketRecordBean implements Serializable {
    private ConditionBean condition;
    private RecordCountBean recordCount;
    private List<RowsBean> rows;

    public RecordCountBean getRecordCount() {
        return this.recordCount;
    }

    public void setRecordCount(RecordCountBean recordCount) {
        this.recordCount = recordCount;
    }

    public ConditionBean getCondition() {
        if (this.condition == null) {
            this.condition = new ConditionBean();
        }
        return this.condition;
    }

    public void setCondition(ConditionBean condition) {
        this.condition = condition;
    }

    public List<RowsBean> getRows() {
        return this.rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    /* loaded from: classes2.dex */
    public static class RecordCountBean {
        private int luckNum;
        private double totalAmount;
        private int totalNum;

        public double getTotalAmount() {
            return this.totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public int getTotalNum() {
            return this.totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getLuckNum() {
            return this.luckNum;
        }

        public void setLuckNum(int luckNum) {
            this.luckNum = luckNum;
        }
    }

    /* loaded from: classes2.dex */
    public static class ConditionBean {
        private long lastId;

        public long getLastId() {
            if (this.lastId == 0) {
                return -1L;
            }
            return this.lastId;
        }

        public void setLastId(int lastId) {
            this.lastId = lastId;
        }
    }

    /* loaded from: classes2.dex */
    public static class RowsBean {
        private double amount;
        private long createTime;
        private String createTimeStr;
        private long id;
        private int luckiestFlag;
        private long redPacketDetailId;
        private long redPacketId;
        private int redPacketModule;
        private int redPacketType;
        private String sourceUserName;
        private String takeBizId;
        private int type;
        private UserBean user;
        private String userName;
        private int year;

        public long getId() {
            return this.id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getSendNick() {
            String nick = getUser().getNickName();
            if (TextUtils.isEmpty(nick)) {
                return this.sourceUserName;
            }
            return nick;
        }

        public String getReceiveNick() {
            String nick = getUser().getNickName();
            if (TextUtils.isEmpty(nick)) {
                return this.takeBizId;
            }
            return nick;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getYear() {
            return this.year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public long getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getRedPacketId() {
            return this.redPacketId;
        }

        public void setRedPacketId(int redPacketId) {
            this.redPacketId = redPacketId;
        }

        public long getRedPacketDetailId() {
            return this.redPacketDetailId;
        }

        public void setRedPacketDetailId(int redPacketDetailId) {
            this.redPacketDetailId = redPacketDetailId;
        }

        public int getRedPacketType() {
            return this.redPacketType;
        }

        public void setRedPacketType(int redPacketType) {
            this.redPacketType = redPacketType;
        }

        public int getRedPacketModule() {
            return this.redPacketModule;
        }

        public void setRedPacketModule(int redPacketModule) {
            this.redPacketModule = redPacketModule;
        }

        public String getSourceUserName() {
            return this.sourceUserName;
        }

        public void setSourceUserName(String sourceUserName) {
            this.sourceUserName = sourceUserName;
        }

        public String getTakeBizId() {
            return this.takeBizId;
        }

        public void setTakeBizId(String takeBizId) {
            this.takeBizId = takeBizId;
        }

        public double getAmount() {
            return this.amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getLuckiestFlag() {
            return this.luckiestFlag;
        }

        public void setLuckiestFlag(int luckiestFlag) {
            this.luckiestFlag = luckiestFlag;
        }

        public String getCreateTimeStr() {
            return this.createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }

        public UserBean getUser() {
            if (this.user == null) {
                this.user = new UserBean();
            }
            return this.user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        /* loaded from: classes2.dex */
        public static class UserBean {
            private String name;
            private String nickName;
            private String picUrl;

            public String getPicUrl() {
                return this.picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getNickName() {
                return this.nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
