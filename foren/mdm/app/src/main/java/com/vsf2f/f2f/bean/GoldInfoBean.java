package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class GoldInfoBean {
    private CofferInfoBean cofferInfo;
    private List<RecordsBean> records;

    public CofferInfoBean getCofferInfo() {
        return this.cofferInfo;
    }

    public void setCofferInfo(CofferInfoBean cofferInfo) {
        this.cofferInfo = cofferInfo;
    }

    public List<RecordsBean> getRecords() {
        return this.records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    /* loaded from: classes2.dex */
    public static class CofferInfoBean {
        private long createTime;
        private long id;
        private boolean isNewRecord;
        private String num;
        private int priority;
        private int status;
        private int type;
        private long updateTime;
        private String userName;

        public long getId() {
            return this.id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return this.isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNum() {
            return this.num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public int getPriority() {
            return this.priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public long getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    /* loaded from: classes2.dex */
    public static class RecordsBean {
        private String bizId;
        private int bizType;
        private String bizTypeStr;
        private long createTime;
        private String createTimeStr;
        private int goldType;
        private long id;
        private boolean isNewRecord;
        private int moduleType;
        private String moduleTypeStr;
        private String num;
        private String remarks;
        private String userName;

        public long getId() {
            return this.id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return this.isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getRemarks() {
            return this.remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getModuleType() {
            return this.moduleType;
        }

        public void setModuleType(int moduleType) {
            this.moduleType = moduleType;
        }

        public int getBizType() {
            return this.bizType;
        }

        public void setBizType(int bizType) {
            this.bizType = bizType;
        }

        public int getGoldType() {
            return this.goldType;
        }

        public void setGoldType(int goldType) {
            this.goldType = goldType;
        }

        public String getNum() {
            return this.num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public long getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getModuleTypeStr() {
            return this.moduleTypeStr;
        }

        public void setModuleTypeStr(String moduleTypeStr) {
            this.moduleTypeStr = moduleTypeStr;
        }

        public String getBizTypeStr() {
            return this.bizTypeStr;
        }

        public void setBizTypeStr(String bizTypeStr) {
            this.bizTypeStr = bizTypeStr;
        }

        public String getCreateTimeStr() {
            return this.createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }

        public String getBizId() {
            return this.bizId;
        }

        public void setBizId(String bizId) {
            this.bizId = bizId;
        }
    }
}
