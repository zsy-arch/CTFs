package com.vsf2f.f2f.bean;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class ApplyPayInfoBean implements Serializable {
    private String batchNo;
    private String bizId;
    private int bizType;
    private int f2fPay;
    private int moId;
    private int needPay;
    private double payAmount;
    private String payTitle;

    public int getMoId() {
        return this.moId;
    }

    public void setMoId(int moId) {
        this.moId = moId;
    }

    public String getBizId() {
        if (TextUtils.isEmpty(this.bizId)) {
            this.bizId = this.moId + "";
        }
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public int getBizType() {
        return this.bizType;
    }

    public void setBizType(int bizType) {
        this.bizType = bizType;
    }

    public double getPayAmount() {
        return this.payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public int getNeedPay() {
        return this.needPay;
    }

    public int getF2fPay() {
        return this.f2fPay;
    }

    public void setNeedPay(int needPay) {
        this.needPay = needPay;
    }

    public void setF2fPay(int f2fPay) {
        this.f2fPay = f2fPay;
    }

    public String getBatchNo() {
        return this.batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPayTitle() {
        return this.payTitle;
    }

    public void setPayTitle(String payTitle) {
        this.payTitle = payTitle;
    }
}
