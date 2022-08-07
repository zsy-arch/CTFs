package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class StatisOrderBean implements Parcelable {
    public static final Parcelable.Creator<StatisOrderBean> CREATOR = new Parcelable.Creator<StatisOrderBean>() { // from class: com.vsf2f.f2f.bean.StatisOrderBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatisOrderBean createFromParcel(Parcel in) {
            return new StatisOrderBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatisOrderBean[] newArray(int size) {
            return new StatisOrderBean[size];
        }
    };
    private int all;
    private int applyReturn;
    private int cancelOrder;
    private int confirmGoods;
    private int delete;
    private int endOrder;
    private int pay;
    private int prepareGoods;
    private int returnGoods;
    private int sentGoods;
    private int unPay;

    protected StatisOrderBean(Parcel in) {
        this.all = in.readInt();
        this.unPay = in.readInt();
        this.pay = in.readInt();
        this.prepareGoods = in.readInt();
        this.sentGoods = in.readInt();
        this.returnGoods = in.readInt();
        this.confirmGoods = in.readInt();
        this.cancelOrder = in.readInt();
        this.endOrder = in.readInt();
        this.delete = in.readInt();
        this.applyReturn = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.all);
        dest.writeInt(this.unPay);
        dest.writeInt(this.pay);
        dest.writeInt(this.prepareGoods);
        dest.writeInt(this.sentGoods);
        dest.writeInt(this.returnGoods);
        dest.writeInt(this.confirmGoods);
        dest.writeInt(this.cancelOrder);
        dest.writeInt(this.endOrder);
        dest.writeInt(this.delete);
        dest.writeInt(this.applyReturn);
    }

    public int getAll() {
        return this.all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getUnPay() {
        return this.unPay;
    }

    public void setUnPay(int unPay) {
        this.unPay = unPay;
    }

    public int getPay() {
        return this.pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getPrepareGoods() {
        return this.prepareGoods;
    }

    public void setPrepareGoods(int prepareGoods) {
        this.prepareGoods = prepareGoods;
    }

    public int getSentGoods() {
        return this.sentGoods;
    }

    public void setSentGoods(int sentGoods) {
        this.sentGoods = sentGoods;
    }

    public int getReturnGoods() {
        return this.returnGoods;
    }

    public void setReturnGoods(int returnGoods) {
        this.returnGoods = returnGoods;
    }

    public int getConfirmGoods() {
        return this.confirmGoods;
    }

    public void setConfirmGoods(int confirmGoods) {
        this.confirmGoods = confirmGoods;
    }

    public int getCancelOrder() {
        return this.cancelOrder;
    }

    public void setCancelOrder(int cancelOrder) {
        this.cancelOrder = cancelOrder;
    }

    public int getEndOrder() {
        return this.endOrder;
    }

    public void setEndOrder(int endOrder) {
        this.endOrder = endOrder;
    }

    public int getDelete() {
        return this.delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public int getApplyReturn() {
        return this.applyReturn;
    }

    public void setApplyReturn(int applyReturn) {
        this.applyReturn = applyReturn;
    }
}
