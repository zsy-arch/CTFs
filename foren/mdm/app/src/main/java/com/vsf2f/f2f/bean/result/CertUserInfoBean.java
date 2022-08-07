package com.vsf2f.f2f.bean.result;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class CertUserInfoBean implements Serializable {
    private int certAlipay;
    private int certMobile;
    private int certQq;
    private int certRealname;
    private int certWechat;
    private int certZhima;
    private int gender;
    private String name;
    private String nickName;
    private String phone;
    private String userName;
    private int zmScore;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        if (!TextUtils.isEmpty(this.nickName)) {
            return this.nickName;
        }
        if (TextUtils.isEmpty(this.name)) {
            return this.userName + "";
        }
        return this.name + "";
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCertAlipay() {
        return this.certAlipay;
    }

    public void setCertAlipay(int certAlipay) {
        this.certAlipay = certAlipay;
    }

    public int getCertQq() {
        return this.certQq;
    }

    public void setCertQq(int certQq) {
        this.certQq = certQq;
    }

    public int getCertWechat() {
        return this.certWechat;
    }

    public void setCertWechat(int certWechat) {
        this.certWechat = certWechat;
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
}
