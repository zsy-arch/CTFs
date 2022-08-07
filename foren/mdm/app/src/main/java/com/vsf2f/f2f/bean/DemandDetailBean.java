package com.vsf2f.f2f.bean;

import android.text.TextUtils;
import com.cdlinglu.utils.LocationUtils;
import com.vsf2f.f2f.bean.DemandTypesBean;
import com.vsf2f.f2f.bean.OfferList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DemandDetailBean implements Serializable {
    private String address;
    private String area;
    private String areaStr;
    private String batchNo;
    private int bizId;
    private int bizType;
    private int collectCount;
    private String contactPhone;
    private String contactUser;
    private String deposit;
    private String description;
    private String distanceStr;
    private String expireTime;
    private List<OfferList> grabOrderList;
    private List<String> imgUrlList;
    private int inventory;
    private double lat;
    private double lng;
    private String localDistance;
    private int moId;
    private int offerFlag;
    private String orderId;
    private List<String> ossImgList;
    private double payAmount;
    private int payType;
    private String picUrls;
    private String provinceCode;
    private String publishTime;
    private String publishUser;
    private DemandUserInfo publishUserObj;
    private String reward;
    private int serviceMode;
    private OfferList.ServiceUserObjBean serviceUserObj;
    private int shareProductType;
    private int shareServiceSnapshotId;
    private int shareSnapshotId;
    private int shareTypeId;
    private DemandTypesBean.ChildsBean shareTypeObj;
    private String snapshotVersion;
    private String sourceType;
    private int status;
    private String title;
    private String typeName;
    private String unit;
    private int viewCount;
    private String voiceDuration;
    private String voiceFullUrl;
    private String voiceUrl;

    public int getBizId() {
        return this.bizId;
    }

    public void setBizId(int bizId) {
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

    public String getBatchNo() {
        return this.batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getOfferFlag() {
        return this.offerFlag;
    }

    public void setOfferFlag(int offerFlag) {
        this.offerFlag = offerFlag;
    }

    public int getInventory() {
        return this.inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getVoiceFullUrl() {
        return this.voiceFullUrl;
    }

    public void setVoiceFullUrl(String voiceFullUrl) {
        this.voiceFullUrl = voiceFullUrl;
    }

    public OfferList.ServiceUserObjBean getServiceProviderObj() {
        if (this.serviceUserObj == null) {
            this.serviceUserObj = new OfferList.ServiceUserObjBean();
        }
        return this.serviceUserObj;
    }

    public void setServiceUserObj(OfferList.ServiceUserObjBean serviceProviderObj) {
        this.serviceUserObj = serviceProviderObj;
    }

    public List<OfferList> getOfferList() {
        if (this.grabOrderList == null) {
            this.grabOrderList = new ArrayList();
        }
        return this.grabOrderList;
    }

    public void setOfferList(List<OfferList> offerList) {
        this.grabOrderList = offerList;
    }

    public String getVoiceUrl() {
        if (this.voiceUrl == null) {
            this.voiceUrl = "";
        }
        return this.voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public DemandUserInfo getPublishUserObj() {
        if (this.publishUserObj == null) {
            this.publishUserObj = new DemandUserInfo();
        }
        return this.publishUserObj;
    }

    public String getPublishUser() {
        if (TextUtils.isEmpty(this.publishUser)) {
            this.publishUser = "";
        }
        return this.publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    public void setPublishUserObj(DemandUserInfo publishUserObj) {
        this.publishUserObj = publishUserObj;
    }

    public int getStatus() {
        return this.status;
    }

    public String getReward() {
        return this.reward;
    }

    public String getLocalDistanceStr() {
        if (TextUtils.isEmpty(this.localDistance)) {
            this.localDistance = LocationUtils.getDistance(getLat(), getLng());
        }
        return this.localDistance;
    }

    public void setDistanceStr(String distanceStr) {
        this.distanceStr = distanceStr;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getImgUrlList() {
        if (this.imgUrlList == null) {
            this.imgUrlList = new ArrayList();
        }
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

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public String getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getDemandSnapShotId() {
        return this.shareSnapshotId;
    }

    public void setDemandSnapShotId(int shareSnapshotId) {
        this.shareSnapshotId = shareSnapshotId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMoId() {
        return this.moId;
    }

    public void setMoId(int id) {
        this.moId = id;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getServiceMode() {
        return this.serviceMode;
    }

    public String getServiceModeStr() {
        return this.serviceMode == 0 ? "线上服务" : "线下服务";
    }

    public void setServiceMode(int serviceMode) {
        this.serviceMode = serviceMode;
    }

    public DemandTypesBean.ChildsBean getDemandTypeObj() {
        return this.shareTypeObj;
    }

    public void setDemandTypeObj(DemandTypesBean.ChildsBean demandTypeObj) {
        this.shareTypeObj = demandTypeObj;
    }

    public int getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCollectCount() {
        return this.collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public String getUnit() {
        if (TextUtils.isEmpty(this.unit)) {
            this.unit = "次";
        }
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaStr() {
        if (TextUtils.isEmpty(this.areaStr)) {
            if ("2".equals(this.area)) {
                this.areaStr = "全国";
            } else if ("0".equals(this.area)) {
                this.areaStr = "全区";
            } else {
                this.areaStr = "全市";
            }
        }
        return this.areaStr;
    }

    public String getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getPayType() {
        return this.payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getDeposit() {
        return this.deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistanceStr() {
        return this.distanceStr;
    }

    public int getShareTypeId() {
        return this.shareTypeId;
    }

    public void setShareTypeId(int shareTypeId) {
        this.shareTypeId = shareTypeId;
    }

    public int getShareSnapshotId() {
        return this.shareSnapshotId;
    }

    public void setShareSnapshotId(int shareSnapshotId) {
        this.shareSnapshotId = shareSnapshotId;
    }

    public int getShareProductType() {
        return this.shareProductType;
    }

    public void setShareProductType(int shareProductType) {
        this.shareProductType = shareProductType;
    }

    public String getVoiceDuration() {
        if (TextUtils.isEmpty(this.voiceDuration)) {
            this.voiceDuration = "0";
        }
        return this.voiceDuration;
    }

    public void setVoiceDuration(String voiceDuration) {
        this.voiceDuration = voiceDuration;
    }

    public DemandTypesBean.ChildsBean getShareTypeObj() {
        if (this.shareTypeObj == null) {
            this.shareTypeObj = new DemandTypesBean.ChildsBean();
        }
        return this.shareTypeObj;
    }

    public void setShareTypeObj(DemandTypesBean.ChildsBean shareTypeObj) {
        this.shareTypeObj = shareTypeObj;
    }

    public String getTypeName() {
        if (TextUtils.isEmpty(this.typeName) && this.shareTypeObj != null) {
            this.typeName = this.shareTypeObj.getName();
        }
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPicUrls() {
        if (this.picUrls == null) {
            this.picUrls = "";
        }
        return this.picUrls;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getSnapshotVersion() {
        return this.snapshotVersion;
    }

    public void setSnapshotVersion(String snapshotVersion) {
        this.snapshotVersion = snapshotVersion;
    }

    public int getShareServiceSnapshotId() {
        return this.shareServiceSnapshotId;
    }

    public void setShareServiceSnapshotId(int shareServiceSnapshotId) {
        this.shareServiceSnapshotId = shareServiceSnapshotId;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactUser() {
        return this.contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
