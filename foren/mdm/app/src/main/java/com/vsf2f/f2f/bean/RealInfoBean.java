package com.vsf2f.f2f.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class RealInfoBean implements Serializable {
    private int baseCertifyStatus;
    private String baseCertifyTime;
    private String certificate;
    private String certifyAvatar;
    private String handTakePositiveImg;
    private String idCardNumber;
    private String idCardOppositeImg;
    private String idCardPositiveImg;
    private String idCardValidTime;
    private int payStatus;
    private String realName;
    private String userId;
    private int zhimaCertifyStatus;
    private String zhimaCertifyTime;

    public String getCertifyAvatar() {
        return this.certifyAvatar;
    }

    public void setCertifyAvatar(String certifyAvatar) {
        this.certifyAvatar = certifyAvatar;
    }

    public int getBaseCertifyStatus() {
        return this.baseCertifyStatus;
    }

    public void setBaseCertifyStatus(int baseCertifyStatus) {
        this.baseCertifyStatus = baseCertifyStatus;
    }

    public String getBaseCertifyTime() {
        return this.baseCertifyTime;
    }

    public void setBaseCertifyTime(String baseCertifyTime) {
        this.baseCertifyTime = baseCertifyTime;
    }

    public String getCertificate() {
        return this.certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getHandTakePositiveImg() {
        return this.handTakePositiveImg;
    }

    public void setHandTakePositiveImg(String handTakePositiveImg) {
        this.handTakePositiveImg = handTakePositiveImg;
    }

    public String getIdCardNumber() {
        return this.idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardOppositeImg() {
        return this.idCardOppositeImg;
    }

    public void setIdCardOppositeImg(String idCardOppositeImg) {
        this.idCardOppositeImg = idCardOppositeImg;
    }

    public String getIdCardPositiveImg() {
        return this.idCardPositiveImg;
    }

    public void setIdCardPositiveImg(String idCardPositiveImg) {
        this.idCardPositiveImg = idCardPositiveImg;
    }

    public String getIdCardValidTime() {
        return this.idCardValidTime;
    }

    public void setIdCardValidTime(String idCardValidTime) {
        this.idCardValidTime = idCardValidTime;
    }

    public int getPayStatus() {
        return this.payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getZhimaCertifyStatus() {
        return this.zhimaCertifyStatus;
    }

    public void setZhimaCertifyStatus(int zhimaCertifyStatus) {
        this.zhimaCertifyStatus = zhimaCertifyStatus;
    }

    public String getZhimaCertifyTime() {
        return this.zhimaCertifyTime;
    }

    public void setZhimaCertifyTime(String zhimaCertifyTime) {
        this.zhimaCertifyTime = zhimaCertifyTime;
    }
}
