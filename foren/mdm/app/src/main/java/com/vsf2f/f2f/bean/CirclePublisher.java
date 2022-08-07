package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CirclePublisher implements Parcelable {
    public static final Parcelable.Creator<CirclePublisher> CREATOR = new Parcelable.Creator<CirclePublisher>() { // from class: com.vsf2f.f2f.bean.CirclePublisher.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CirclePublisher createFromParcel(Parcel source) {
            return new CirclePublisher(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CirclePublisher[] newArray(int size) {
            return new CirclePublisher[size];
        }
    };
    private int certAlipay;
    private int certMobile;
    private int certQq;
    private int certRealname;
    private int certShop;
    private int certWechat;
    private int certZhima;
    private String lv;
    private String userName;
    private int zmScore;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLv() {
        return this.lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public int getCertShop() {
        return this.certShop;
    }

    public void setCertShop(int certShop) {
        this.certShop = certShop;
    }

    public int getCertAlipay() {
        return this.certAlipay;
    }

    public void setCertAlipay(int certAlipay) {
        this.certAlipay = certAlipay;
    }

    public int getCertWechat() {
        return this.certWechat;
    }

    public void setCertWechat(int certWechat) {
        this.certWechat = certWechat;
    }

    public int getCertQq() {
        return this.certQq;
    }

    public void setCertQq(int certQq) {
        this.certQq = certQq;
    }

    public int getCertMobile() {
        return this.certMobile;
    }

    public void setCertMobile(int certMobile) {
        this.certMobile = certMobile;
    }

    public int getCertRealname() {
        return this.certRealname;
    }

    public void setCertRealname(int certRealname) {
        this.certRealname = certRealname;
    }

    public int getCertZhima() {
        return this.certZhima;
    }

    public void setCertZhima(int certZhima) {
        this.certZhima = certZhima;
    }

    public int getZmScore() {
        return this.zmScore;
    }

    public void setZmScore(int zmScore) {
        this.zmScore = zmScore;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.lv);
        dest.writeInt(this.certShop);
        dest.writeInt(this.certAlipay);
        dest.writeInt(this.certWechat);
        dest.writeInt(this.certQq);
        dest.writeInt(this.certMobile);
        dest.writeInt(this.certRealname);
        dest.writeInt(this.certZhima);
        dest.writeInt(this.zmScore);
    }

    public CirclePublisher() {
    }

    protected CirclePublisher(Parcel in) {
        this.userName = in.readString();
        this.lv = in.readString();
        this.certShop = in.readInt();
        this.certAlipay = in.readInt();
        this.certWechat = in.readInt();
        this.certQq = in.readInt();
        this.certMobile = in.readInt();
        this.certRealname = in.readInt();
        this.certZhima = in.readInt();
        this.zmScore = in.readInt();
    }
}
