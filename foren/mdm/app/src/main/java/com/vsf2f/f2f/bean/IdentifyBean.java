package com.vsf2f.f2f.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class IdentifyBean implements Serializable {
    private int amount;
    private String bizId;
    private int needPay;
    private String returnUrl;
    private int status;
    private String statusStr;
    private int type;

    public String getStatusStr() {
        this.statusStr = "";
        switch (this.status) {
            case 0:
                this.statusStr = "审核中";
                break;
            case 1:
                this.statusStr = "已认证";
                break;
            case 2:
                this.statusStr = "认证失败";
                break;
            case 3:
                this.statusStr = "未认证";
                break;
        }
        return this.statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNeedPay() {
        return this.needPay;
    }

    public void setNeedPay(int needPay) {
        this.needPay = needPay;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBizId() {
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getReturnUrl() {
        return this.returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
