package com.amap.api.navi;

/* loaded from: classes.dex */
public class AMapNaviException extends Exception {
    public static final String CALCULATE_ROUTE_FAILURE = "算路失败";
    public static final String ERROR_UNKNOWN = "未知的错误";
    public static final String ILLEGAL_ARGUMENT = "非法参数";
    private String mDetailMessage;

    public AMapNaviException(String str) {
        super(str);
        this.mDetailMessage = "未知的错误";
        this.mDetailMessage = str;
    }

    public AMapNaviException() {
        this.mDetailMessage = "未知的错误";
    }

    public String getErrorMessage() {
        return this.mDetailMessage;
    }
}
