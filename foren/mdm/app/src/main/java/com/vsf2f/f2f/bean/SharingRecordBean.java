package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class SharingRecordBean {
    private String batchNo;
    private int recordId;
    private double shareWorth;
    private int status;
    private String statusName;
    private double tradeMoney;
    private int tradeNum;
    private long tradeTime;
    private int type;
    private String typeName;
    private String userName;

    public int getRecordId() {
        return this.recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getBatchNo() {
        return this.batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTradeNum() {
        return this.tradeNum;
    }

    public void setTradeNum(int tradeNum) {
        this.tradeNum = tradeNum;
    }

    public long getTradeTime() {
        return this.tradeTime;
    }

    public void setTradeTime(long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return this.statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getShareWorth() {
        return this.shareWorth;
    }

    public void setShareWorth(double shareWorth) {
        this.shareWorth = shareWorth;
    }

    public double getTradeMoney() {
        return this.tradeMoney;
    }

    public void setTradeMoney(double tradeMoney) {
        this.tradeMoney = tradeMoney;
    }
}
