package com.cdlinglu.utils.pay;

import com.umeng.update.a;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WxPayInfo {
    private String appid;
    private String noncestr;
    private String package1;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;

    public WxPayInfo(JSONObject payInfo) {
        this.appid = payInfo.optString("appid");
        this.noncestr = payInfo.optString("noncestr");
        this.partnerid = payInfo.optString("partnerid");
        this.package1 = payInfo.optString(a.d);
        this.prepayid = payInfo.optString("prepayid");
        this.timestamp = payInfo.optString("timestamp");
        this.sign = payInfo.optString("sign");
    }

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return this.partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return this.prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return this.noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPackage() {
        return this.package1;
    }

    public void setPackage(String package1) {
        this.package1 = package1;
    }

    public String toString() {
        return "WxPayInfo{appid='" + this.appid + "', partnerid='" + this.partnerid + "', prepayid='" + this.prepayid + "', noncestr='" + this.noncestr + "', timestamp='" + this.timestamp + "', sign='" + this.sign + "'}";
    }
}
