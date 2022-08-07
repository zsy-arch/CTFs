package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class SharingBuyOrderBean {
    private String bizId;
    private String nonceStr;
    private String payUrl;
    private String preId;
    private String sign;
    private String subject;
    private String timestamp;
    private String totalFee;
    private String userName;

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNonceStr() {
        return this.nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPayUrl() {
        return this.payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getBizId() {
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getPreId() {
        return this.preId;
    }

    public void setPreId(String preId) {
        this.preId = preId;
    }
}
