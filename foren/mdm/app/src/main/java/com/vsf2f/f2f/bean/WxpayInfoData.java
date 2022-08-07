package com.vsf2f.f2f.bean;

import com.cdlinglu.utils.pay.WxPayInfo;

/* loaded from: classes2.dex */
public class WxpayInfoData {
    private int code;
    private WxPayInfo data;
    private String msg;

    public WxPayInfo getData() {
        return this.data;
    }

    public void setData(WxPayInfo data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
