package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class SharingBean {
    private String growNum;
    private List<GrowRecordBean> growRecord;
    private int maxBuyNum;
    private double maxWorth;
    private int minBuyNum;
    private double minWorth;
    private int shareMoney;
    private int shareMoneyDisable;
    private int shareMoneyEnable;
    private int shareMoneyFrozen;
    private int shareMoneyUnFrozen;
    private List<SharingRecordBean> tradeRecord;
    private double worth;

    public double getWorth() {
        return this.worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public double getMinWorth() {
        return this.minWorth;
    }

    public void setMinWorth(double minWorth) {
        this.minWorth = minWorth;
    }

    public double getMaxWorth() {
        return this.maxWorth;
    }

    public void setMaxWorth(double maxWorth) {
        this.maxWorth = maxWorth;
    }

    public String getGrowNum() {
        return this.growNum;
    }

    public void setGrowNum(String growNum) {
        this.growNum = growNum;
    }

    public int getMinBuyNum() {
        return this.minBuyNum;
    }

    public void setMinBuyNum(int minBuyNum) {
        this.minBuyNum = minBuyNum;
    }

    public int getMaxBuyNum() {
        return this.maxBuyNum;
    }

    public void setMaxBuyNum(int maxBuyNum) {
        this.maxBuyNum = maxBuyNum;
    }

    public int getShareMoney() {
        return this.shareMoney;
    }

    public void setShareMoney(int shareMoney) {
        this.shareMoney = shareMoney;
    }

    public int getShareMoneyEnable() {
        return this.shareMoneyEnable;
    }

    public void setShareMoneyEnable(int shareMoneyEnable) {
        this.shareMoneyEnable = shareMoneyEnable;
    }

    public int getShareMoneyFrozen() {
        return this.shareMoneyFrozen;
    }

    public void setShareMoneyFrozen(int shareMoneyFrozen) {
        this.shareMoneyFrozen = shareMoneyFrozen;
    }

    public List<GrowRecordBean> getGrowRecord() {
        return this.growRecord;
    }

    public void setGrowRecord(List<GrowRecordBean> growRecord) {
        this.growRecord = growRecord;
    }

    public List<SharingRecordBean> getTradeRecord() {
        return this.tradeRecord;
    }

    public void setTradeRecord(List<SharingRecordBean> tradeRecord) {
        this.tradeRecord = tradeRecord;
    }

    public int getShareMoneyDisable() {
        return this.shareMoneyDisable;
    }

    public void setShareMoneyDisable(int shareMoneyDisable) {
        this.shareMoneyDisable = shareMoneyDisable;
    }

    public int getShareMoneyUnFrozen() {
        return this.shareMoneyUnFrozen;
    }

    public void setShareMoneyUnFrozen(int shareMoneyUnFrozen) {
        this.shareMoneyUnFrozen = shareMoneyUnFrozen;
    }

    /* loaded from: classes2.dex */
    public static class GrowRecordBean {
        private String todayNum;
        private String typeName;
        private String yesterdayNum;

        public String getTypeName() {
            return this.typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getYesterdayNum() {
            return this.yesterdayNum;
        }

        public void setYesterdayNum(String yesterdayNum) {
            this.yesterdayNum = yesterdayNum;
        }

        public String getTodayNum() {
            return this.todayNum;
        }

        public void setTodayNum(String todayNum) {
            this.todayNum = todayNum;
        }
    }
}
