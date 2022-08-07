package com.ta.utdid2.device;

/* loaded from: classes2.dex */
public class Device {
    private String imei = "";
    private String imsi = "";
    private String deviceId = "";
    private String utdid = "";
    private long mCreateTimestamp = 0;
    private long mCheckSum = 0;

    long getCheckSum() {
        return this.mCheckSum;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCheckSum(long mCheckSum) {
        this.mCheckSum = mCheckSum;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getCreateTimestamp() {
        return this.mCreateTimestamp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCreateTimestamp(long mCreateTimestamp) {
        this.mCreateTimestamp = mCreateTimestamp;
    }

    public String getImei() {
        return this.imei;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return this.imsi;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUtdid() {
        return this.utdid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUtdid(String utdid) {
        this.utdid = utdid;
    }
}
