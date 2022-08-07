package com.vsf2f.f2f.bean;

import com.vsf2f.f2f.bean.result.CertUserInfoBean;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class RefundInfoBean implements Serializable {
    private String creator;
    private CertUserInfoBean creatorObj;
    private String description;
    private String imgList;
    private List<String> imgUrlList;
    private int moId;
    private String opTypeStr;
    private String operationTime;
    private String operationType;
    private List<String> ossImgList;
    private int shareOrderId;

    public int getMoId() {
        return this.moId;
    }

    public void setMoId(int moId) {
        this.moId = moId;
    }

    public int getShareOrderId() {
        return this.shareOrderId;
    }

    public void setShareOrderId(int shareOrderId) {
        this.shareOrderId = shareOrderId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationTime() {
        return this.operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOpTypeStr() {
        return this.opTypeStr;
    }

    public void setOpTypeStr(String opTypeStr) {
        this.opTypeStr = opTypeStr;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public CertUserInfoBean getCreatorObj() {
        return this.creatorObj;
    }

    public void setCreatorObj(CertUserInfoBean creatorObj) {
        this.creatorObj = creatorObj;
    }

    public String getImgList() {
        return this.imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public List<String> getImgUrlList() {
        return this.imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public List<String> getOssImgList() {
        return this.ossImgList;
    }

    public void setOssImgList(List<String> ossImgList) {
        this.ossImgList = ossImgList;
    }
}
