package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class AccountBean {
    private String avatar;
    private String name;
    private String pwd;

    public AccountBean() {
    }

    public AccountBean(String name, String pwd, String avatar) {
        this.name = name;
        this.pwd = pwd;
        this.avatar = avatar;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String toString() {
        return "AccountBean{name='" + this.name + "', pwd='" + this.pwd + "', avatar='" + this.avatar + "'}";
    }
}
