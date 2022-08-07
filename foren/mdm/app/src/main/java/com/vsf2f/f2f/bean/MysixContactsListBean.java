package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class MysixContactsListBean implements Parcelable {
    public static final Parcelable.Creator<MysixContactsListBean> CREATOR = new Parcelable.Creator<MysixContactsListBean>() { // from class: com.vsf2f.f2f.bean.MysixContactsListBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MysixContactsListBean createFromParcel(Parcel in) {
            return new MysixContactsListBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MysixContactsListBean[] newArray(int size) {
            return new MysixContactsListBean[size];
        }
    };
    private String allUnderCount;
    private String investorMoney;
    private String investorMoneyStr;
    private String level;
    private String profitMoney;
    private String profitMoneyStr;

    protected MysixContactsListBean(Parcel in) {
        this.level = in.readString();
        this.investorMoney = in.readString();
        this.investorMoneyStr = in.readString();
        this.profitMoney = in.readString();
        this.profitMoneyStr = in.readString();
        this.allUnderCount = in.readString();
    }

    public String getAllUnderCount() {
        return this.allUnderCount;
    }

    public void setAllUnderCount(String allUnderCount) {
        this.allUnderCount = allUnderCount;
    }

    public String getInvestorMoney() {
        return this.investorMoney;
    }

    public void setInvestorMoney(String investorMoney) {
        this.investorMoney = investorMoney;
    }

    public String getInvestorMoneyStr() {
        return this.investorMoneyStr;
    }

    public void setInvestorMoneyStr(String investorMoneyStr) {
        this.investorMoneyStr = investorMoneyStr;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProfitMoney() {
        return this.profitMoney;
    }

    public void setProfitMoney(String profitMoney) {
        this.profitMoney = profitMoney;
    }

    public String getProfitMoneyStr() {
        return this.profitMoneyStr;
    }

    public void setProfitMoneyStr(String profitMoneyStr) {
        this.profitMoneyStr = profitMoneyStr;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.level);
        dest.writeString(this.investorMoney);
        dest.writeString(this.investorMoneyStr);
        dest.writeString(this.profitMoney);
        dest.writeString(this.profitMoneyStr);
        dest.writeString(this.allUnderCount);
    }
}
