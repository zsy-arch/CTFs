package com.vsf2f.f2f.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class ShareShopInfo implements Serializable {
    private ShareLogo logo;
    private String storeName;
    private String userName;

    public ShareLogo getLogo() {
        return this.logo;
    }

    public void setLogo(ShareLogo logo) {
        this.logo = logo;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
