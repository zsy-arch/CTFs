package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class UserFundCenterBean {
    private Account Account;
    private LevelCount LevelCount;

    public Account getAccount() {
        return this.Account;
    }

    public void setAccount(Account account) {
        this.Account = account;
    }

    public LevelCount getLevelCount() {
        return this.LevelCount;
    }

    public void setLevelCount(LevelCount levelCount) {
        this.LevelCount = levelCount;
    }

    /* loaded from: classes2.dex */
    public class Account {
        private String allProfit;
        private String cash;
        private String frozenFund;
        private String goldBeanNum;
        private String goldNum;
        private String investmentCash;
        private String investmentProfit;
        private String maxWgdMoney;
        private String maxWgdProfit;
        private String minWgdMoney;
        private String points;
        private String recomProfit;
        private String registeredProfit;
        private String salesNum;
        private String shoppingProfit;
        private String sumCoupon;
        private String sumWgdMoney;
        private String usableCoupon;

        public Account() {
        }

        public String getFrozenFund() {
            return this.frozenFund;
        }

        public void setFrozenFund(String frozenFund) {
            this.frozenFund = frozenFund;
        }

        public String getUsableCoupon() {
            return this.usableCoupon;
        }

        public void setUsableCoupon(String usableCoupon) {
            this.usableCoupon = usableCoupon;
        }

        public String getSumCoupon() {
            return this.sumCoupon;
        }

        public void setSumCoupon(String sumCoupon) {
            this.sumCoupon = sumCoupon;
        }

        public String getCash() {
            return this.cash;
        }

        public void setCash(String cash) {
            this.cash = cash;
        }

        public String getInvestmentCash() {
            return this.investmentCash;
        }

        public void setInvestmentCash(String investmentCash) {
            this.investmentCash = investmentCash;
        }

        public String getRegisteredProfit() {
            return this.registeredProfit;
        }

        public void setRegisteredProfit(String registeredProfit) {
            this.registeredProfit = registeredProfit;
        }

        public String getInvestmentProfit() {
            return this.investmentProfit;
        }

        public void setInvestmentProfit(String investmentProfit) {
            this.investmentProfit = investmentProfit;
        }

        public String getRecomProfit() {
            return this.recomProfit;
        }

        public void setRecomProfit(String recomProfit) {
            this.recomProfit = recomProfit;
        }

        public String getSalesNum() {
            return this.salesNum;
        }

        public void setSalesNum(String salesNum) {
            this.salesNum = salesNum;
        }

        public String getShoppingProfit() {
            return this.shoppingProfit;
        }

        public void setShoppingProfit(String shoppingProfit) {
            this.shoppingProfit = shoppingProfit;
        }

        public String getAllProfit() {
            return this.allProfit;
        }

        public void setAllProfit(String allProfit) {
            this.allProfit = allProfit;
        }

        public String getSumWgdMoney() {
            return this.sumWgdMoney;
        }

        public void setSumWgdMoney(String sumWgdMoney) {
            this.sumWgdMoney = sumWgdMoney;
        }

        public String getMinWgdMoney() {
            return this.minWgdMoney;
        }

        public void setMinWgdMoney(String minWgdMoney) {
            this.minWgdMoney = minWgdMoney;
        }

        public String getMaxWgdMoney() {
            return this.maxWgdMoney;
        }

        public void setMaxWgdMoney(String maxWgdMoney) {
            this.maxWgdMoney = maxWgdMoney;
        }

        public String getMaxWgdProfit() {
            return this.maxWgdProfit;
        }

        public void setMaxWgdProfit(String maxWgdProfit) {
            this.maxWgdProfit = maxWgdProfit;
        }

        public String getPoints() {
            return this.points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getGoldNum() {
            return this.goldNum == null ? "0" : this.goldNum;
        }

        public void setGoldNum(String goldNum) {
            this.goldNum = goldNum;
        }

        public String getGoldBeanNum() {
            return this.goldBeanNum == null ? "0" : this.goldBeanNum;
        }

        public void setGoldBeanNum(String goldBeanNum) {
            this.goldBeanNum = goldBeanNum;
        }

        public String toString() {
            return "UserFundAccountbean{allProfit=" + this.allProfit + ", usableCoupon=" + this.usableCoupon + ", sumCoupon=" + this.sumCoupon + ", cash=" + this.cash + ", investmentCash=" + this.investmentCash + ", registeredProfit=" + this.registeredProfit + ", investmentProfit=" + this.investmentProfit + ", recomProfit=" + this.recomProfit + ", salesNum=" + this.salesNum + ", shoppingProfit=" + this.shoppingProfit + ", sumWgdMoney=" + this.sumWgdMoney + ", minWgdMoney=" + this.minWgdMoney + ", maxWgdMoney=" + this.maxWgdMoney + ", maxWgdProfit=" + this.maxWgdProfit + '}';
        }
    }

    /* loaded from: classes2.dex */
    class LevelCount {
        private String First;
        private String Second;

        LevelCount() {
        }

        public String getSecond() {
            return this.Second;
        }

        public void setSecond(String Second) {
            this.Second = Second;
        }

        public String getFirst() {
            return this.First;
        }

        public void setFirst(String First) {
            this.First = First;
        }
    }
}
