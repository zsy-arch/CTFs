package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ShopFinanceBean implements Parcelable {
    public static final Parcelable.Creator<ShopFinanceBean> CREATOR = new Parcelable.Creator<ShopFinanceBean>() { // from class: com.vsf2f.f2f.bean.ShopFinanceBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopFinanceBean createFromParcel(Parcel in) {
            return new ShopFinanceBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopFinanceBean[] newArray(int size) {
            return new ShopFinanceBean[size];
        }
    };
    private String cash;
    private String confirmNum;
    private String downNum;
    private String notConfirm;
    private String notPicking;
    private String notShipped;
    private String refundNum;
    private String salesNum;
    private String scNum;
    private String shoppingProfit;
    private String tradingNum;
    private String unpayOrder;
    private String upNum;

    protected ShopFinanceBean(Parcel in) {
        this.cash = in.readString();
        this.salesNum = in.readString();
        this.tradingNum = in.readString();
        this.shoppingProfit = in.readString();
        this.unpayOrder = in.readString();
        this.notShipped = in.readString();
        this.notPicking = in.readString();
        this.refundNum = in.readString();
        this.notConfirm = in.readString();
        this.confirmNum = in.readString();
        this.upNum = in.readString();
        this.downNum = in.readString();
        this.scNum = in.readString();
    }

    public String getCash() {
        return this.cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getConfirmNum() {
        return this.confirmNum;
    }

    public void setConfirmNum(String confirmNum) {
        this.confirmNum = confirmNum;
    }

    public String getDownNum() {
        return this.downNum;
    }

    public void setDownNum(String downNum) {
        this.downNum = downNum;
    }

    public String getNotConfirm() {
        return this.notConfirm;
    }

    public void setNotConfirm(String notConfirm) {
        this.notConfirm = notConfirm;
    }

    public String getNotPicking() {
        return this.notPicking;
    }

    public void setNotPicking(String notPicking) {
        this.notPicking = notPicking;
    }

    public String getNotShipped() {
        return this.notShipped;
    }

    public void setNotShipped(String notShipped) {
        this.notShipped = notShipped;
    }

    public String getRefundNum() {
        return this.refundNum;
    }

    public void setRefundNum(String refundNum) {
        this.refundNum = refundNum;
    }

    public String getSalesNum() {
        return this.salesNum;
    }

    public void setSalesNum(String salesNum) {
        this.salesNum = salesNum;
    }

    public String getScNum() {
        return this.scNum;
    }

    public void setScNum(String scNum) {
        this.scNum = scNum;
    }

    public String getShoppingProfit() {
        return this.shoppingProfit;
    }

    public void setShoppingProfit(String shoppingProfit) {
        this.shoppingProfit = shoppingProfit;
    }

    public String getTradingNum() {
        return this.tradingNum;
    }

    public void setTradingNum(String tradingNum) {
        this.tradingNum = tradingNum;
    }

    public String getUnpayOrder() {
        return this.unpayOrder;
    }

    public void setUnpayOrder(String unpayOrder) {
        this.unpayOrder = unpayOrder;
    }

    public String getUpNum() {
        return this.upNum;
    }

    public void setUpNum(String upNum) {
        this.upNum = upNum;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cash);
        dest.writeString(this.salesNum);
        dest.writeString(this.tradingNum);
        dest.writeString(this.shoppingProfit);
        dest.writeString(this.unpayOrder);
        dest.writeString(this.notShipped);
        dest.writeString(this.notPicking);
        dest.writeString(this.refundNum);
        dest.writeString(this.notConfirm);
        dest.writeString(this.confirmNum);
        dest.writeString(this.upNum);
        dest.writeString(this.downNum);
        dest.writeString(this.scNum);
    }

    public String toString() {
        return "ShopFinanceBean{cash='" + this.cash + "', salesNum='" + this.salesNum + "', tradingNum='" + this.tradingNum + "', shoppingProfit='" + this.shoppingProfit + "', unpayOrder='" + this.unpayOrder + "', notShipped='" + this.notShipped + "', notPicking='" + this.notPicking + "', refundNum='" + this.refundNum + "', notConfirm='" + this.notConfirm + "', confirmNum='" + this.confirmNum + "', upNum='" + this.upNum + "', downNum='" + this.downNum + "', scNum='" + this.scNum + "'}";
    }
}
