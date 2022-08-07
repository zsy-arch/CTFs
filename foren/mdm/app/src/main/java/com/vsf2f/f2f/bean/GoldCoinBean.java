package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class GoldCoinBean {
    private int count;
    private int firstResult;
    private List<ListBean> list;
    private int maxResults;
    private int pageNo;
    private int pageSize;

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFirstResult() {
        return this.firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public List<ListBean> getList() {
        return this.list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    /* loaded from: classes2.dex */
    public static class ListBean {
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
        private int priority;
        private String remarks;
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

        public int getModuleType() {
            return this.moduleType;
        }

        public void setModuleType(int moduleType) {
            this.moduleType = moduleType;
        }

        public boolean isNewRecord() {
            return this.isNewRecord;
        }

        public void setNewRecord(boolean newRecord) {
            this.isNewRecord = newRecord;
        }

        public String getRemarks() {
            return this.remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getBizTypeStr() {
            return this.bizTypeStr;
        }

        public void setBizTypeStr(String bizTypeStr) {
            this.bizTypeStr = bizTypeStr;
        }

        public String getModuleTypeStr() {
            return this.moduleTypeStr;
        }

        public void setModuleTypeStr(String moduleTypeStr) {
            this.moduleTypeStr = moduleTypeStr;
        }

        public String getCreateTimeStr() {
            return this.createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }
    }
}
