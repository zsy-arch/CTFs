package com.vsf2f.f2f.bean;

import android.text.TextUtils;
import com.vsf2f.f2f.bean.DemandTypesBean;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class OrderDetailBean implements Serializable {
    private static DecimalFormat df = new DecimalFormat("0.00");
    private double amount;
    private String bookMessage;
    private int buyNum;
    private String contactPhone;
    private String contactUser;
    private String createTime;
    private String creator;
    private int grabOrderId;
    private int moId;
    private double otherAmount;
    private int payStatus;
    private List<RefundInfoBean> refundRecordList;
    private boolean selectedPay;
    private String serviceAddress;
    private int serviceOrderId;
    private String serviceUserName;
    private ShareObjBean shareObj;
    private ShareObjBean shareServiceObj;
    private int shareServiceSnapshotId;
    private int shareSnapshotId;
    private int status;
    private List<OrderTraceBean> traceList;
    private String validCode;

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactUser() {
        if (TextUtils.isEmpty(this.contactUser)) {
            this.contactUser = "匿名";
        }
        return this.contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public int getBuyNum() {
        return this.buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public ShareObjBean getShareServiceObj() {
        return this.shareServiceObj;
    }

    public void setShareServiceObj(ShareObjBean shareServiceObj) {
        this.shareServiceObj = shareServiceObj;
    }

    public int getShareServiceSnapshotId() {
        return this.shareServiceSnapshotId;
    }

    public void setShareServiceSnapshotId(int shareServiceSnapshotId) {
        this.shareServiceSnapshotId = shareServiceSnapshotId;
    }

    public int getPayStatus() {
        return this.payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public List<OrderTraceBean> getTraceList() {
        if (this.traceList == null) {
            this.traceList = new ArrayList();
        }
        return this.traceList;
    }

    public void setTraceList(List<OrderTraceBean> traceList) {
        this.traceList = traceList;
    }

    public boolean isSelectedPay() {
        return this.selectedPay;
    }

    public void setSelectedPay(boolean selectedPay) {
        this.selectedPay = selectedPay;
    }

    public double getAmount() {
        return Double.valueOf(df.format(this.amount)).doubleValue();
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getGrabOrderId() {
        return this.grabOrderId;
    }

    public void setGrabOrderId(int grabOrderId) {
        this.grabOrderId = grabOrderId;
    }

    public int getMoId() {
        return this.moId;
    }

    public void setMoId(int moId) {
        this.moId = moId;
    }

    public double getOtherAmount() {
        return Double.valueOf(df.format(this.otherAmount)).doubleValue();
    }

    public void setOtherAmount(double otherAmount) {
        this.otherAmount = otherAmount;
    }

    public int getServiceOrderId() {
        return this.serviceOrderId;
    }

    public void setServiceOrderId(int serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getServiceUserName() {
        return this.serviceUserName;
    }

    public void setServiceUserName(String serviceUserName) {
        this.serviceUserName = serviceUserName;
    }

    public ShareObjBean getShareObj() {
        if (this.shareObj == null) {
            this.shareObj = new ShareObjBean();
        }
        return this.shareObj;
    }

    public void setShareObj(ShareObjBean shareObj) {
        this.shareObj = shareObj;
    }

    public int getShareSnapshotId() {
        return this.shareSnapshotId;
    }

    public void setShareSnapshotId(int shareSnapshotId) {
        this.shareSnapshotId = shareSnapshotId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getValidCode() {
        return this.validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getBookMessage() {
        return this.bookMessage;
    }

    public void setBookMessage(String bookMessage) {
        this.bookMessage = bookMessage;
    }

    public String getServiceAddress() {
        return this.serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public List<RefundInfoBean> getRefundRecordList() {
        if (this.refundRecordList == null) {
            this.refundRecordList = new ArrayList();
        }
        return this.refundRecordList;
    }

    public void setRefundRecordList(List<RefundInfoBean> refundRecordList) {
        this.refundRecordList = refundRecordList;
    }

    /* loaded from: classes2.dex */
    public static class ShareObjBean implements Serializable {
        private String address;
        private String area;
        private String areaCode;
        private String cityCode;
        private String contactPhone;
        private String contactUser;
        private double deposit;
        private String description;
        private String expireTime;
        private List<String> imgUrlList;
        private double lat;
        private double lng;
        private int moId;
        private List<String> ossImgList;
        private int payType;
        private String picUrls;
        private String provinceCode;
        private String publishTime;
        private String publishUser;
        private DemandUserInfo publishUserObj;
        private double reward;
        private int serviceMode;
        private DemandUserInfo serviceUserObj;
        private int shareMoId;
        private int shareProductType;
        private int shareServiceId;
        private int shareTypeId;
        private DemandTypesBean.ChildsBean shareTypeObj;
        private String snapshotVersion;
        private int status;
        private String title;
        private String unit;
        private String voiceDuration;
        private String voiceUrl;

        public DemandTypesBean.ChildsBean getShareTypeObj() {
            if (this.shareTypeObj == null) {
                this.shareTypeObj = null;
            }
            return this.shareTypeObj;
        }

        public void setShareTypeObj(DemandTypesBean.ChildsBean shareTypeObj) {
            this.shareTypeObj = shareTypeObj;
        }

        public DemandUserInfo getPublishUserObj() {
            if (this.publishUserObj == null) {
                this.publishUserObj = new DemandUserInfo();
            }
            return this.publishUserObj;
        }

        public void setPublishUserObj(DemandUserInfo publishUserObj) {
            this.publishUserObj = publishUserObj;
        }

        public DemandUserInfo getServiceUserObj() {
            if (this.serviceUserObj == null) {
                this.serviceUserObj = new DemandUserInfo();
            }
            return this.serviceUserObj;
        }

        public void setServiceUserObj(DemandUserInfo serviceUserObj) {
            this.serviceUserObj = serviceUserObj;
        }

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return this.area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAreaCode() {
            return this.areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getCityCode() {
            return this.cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
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

        public double getDeposit() {
            return this.deposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getExpireTime() {
            return this.expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
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

        public int getMoId() {
            return this.moId;
        }

        public void setMoId(int moId) {
            this.moId = moId;
        }

        public int getPayType() {
            return this.payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getPicUrls() {
            return this.picUrls;
        }

        public void setPicUrls(String picUrls) {
            this.picUrls = picUrls;
        }

        public String getProvinceCode() {
            return this.provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getPublishTime() {
            return this.publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getPublishUser() {
            return this.publishUser;
        }

        public void setPublishUser(String publishUser) {
            this.publishUser = publishUser;
        }

        public double getReward() {
            return this.reward;
        }

        public void setReward(double reward) {
            this.reward = reward;
        }

        public String getServiceModeStr() {
            return this.serviceMode == 0 ? "线上服务" : "线下服务";
        }

        public int getServiceMode() {
            return this.serviceMode;
        }

        public void setServiceMode(int serviceMode) {
            this.serviceMode = serviceMode;
        }

        public int getShareMoId() {
            return this.shareMoId;
        }

        public void setShareMoId(int shareMoId) {
            this.shareMoId = shareMoId;
        }

        public int getShareServiceId() {
            return this.shareServiceId;
        }

        public void setShareServiceId(int shareServiceId) {
            this.shareServiceId = shareServiceId;
        }

        public int getShareProductType() {
            return this.shareProductType;
        }

        public void setShareProductType(int shareProductType) {
            this.shareProductType = shareProductType;
        }

        public int getShareTypeId() {
            return this.shareTypeId;
        }

        public void setShareTypeId(int shareTypeId) {
            this.shareTypeId = shareTypeId;
        }

        public String getSnapshotVersion() {
            return this.snapshotVersion;
        }

        public void setSnapshotVersion(String snapshotVersion) {
            this.snapshotVersion = snapshotVersion;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUnit() {
            if (TextUtils.isEmpty(this.unit)) {
                this.unit = "tim";
            }
            return this.unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getVoiceDuration() {
            return this.voiceDuration;
        }

        public void setVoiceDuration(String voiceDuration) {
            this.voiceDuration = voiceDuration;
        }

        public String getVoiceUrl() {
            return this.voiceUrl;
        }

        public void setVoiceUrl(String voiceUrl) {
            this.voiceUrl = voiceUrl;
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
    }
}
