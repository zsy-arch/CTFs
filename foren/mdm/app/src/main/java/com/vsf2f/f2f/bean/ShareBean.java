package com.vsf2f.f2f.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class ShareBean implements Serializable {
    private int favType;
    private ShareGoodsInfo goodsInfo;
    private String href;
    private String icon;
    private String id;
    private ShareCircleInfo msgInfo;
    private String name;
    private String objId;
    private int orderNum;
    private ShareThirdBean shareThird;
    private ShareShopInfo shopInfo;
    private String sortName;
    private int status;
    private int type;
    private String userName;

    public ShareBean() {
    }

    public ShareBean(String name, String url) {
        this.name = name;
        this.href = url;
    }

    public String getObjId() {
        return this.objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public int getFavType() {
        return this.favType;
    }

    public void setFavType(int favType) {
        this.favType = favType;
    }

    public ShareGoodsInfo getGoodsInfo() {
        return this.goodsInfo;
    }

    public void setGoodsInfo(ShareGoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShareCircleInfo getMsgInfo() {
        return this.msgInfo;
    }

    public void setMsgInfo(ShareCircleInfo msgInfo) {
        this.msgInfo = msgInfo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public ShareThirdBean getShareThird() {
        return this.shareThird;
    }

    public void setShareThird(ShareThirdBean shareThird) {
        this.shareThird = shareThird;
    }

    public ShareShopInfo getShopInfo() {
        if (this.shopInfo == null) {
            this.shopInfo = new ShareShopInfo();
        }
        return this.shopInfo;
    }

    public void setShopInfo(ShareShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public String getSortName() {
        return this.sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
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

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return "ShareBean{favType=" + this.favType + ", id='" + this.id + "', userName='" + this.userName + "', name='" + this.name + "', sortName='" + this.sortName + "', href='" + this.href + "', icon='" + this.icon + "', status=" + this.status + ", type=" + this.type + ", orderNum=" + this.orderNum + ", shareThird=" + this.shareThird + ", shopInfo=" + this.shopInfo + ", goodsInfo=" + this.goodsInfo + ", msgInfo=" + this.msgInfo + '}';
    }
}
