package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class SubscriptionBean {
    private String account;
    private String basePicUrl;
    private String id;
    private String name;
    private int type;
    private String userName;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBasePicUrl() {
        return this.basePicUrl;
    }

    public void setBasePicUrl(String basePicUrl) {
        this.basePicUrl = basePicUrl;
    }
}
