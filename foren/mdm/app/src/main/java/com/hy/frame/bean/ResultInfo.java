package com.hy.frame.bean;

/* loaded from: classes2.dex */
public class ResultInfo {
    public static final int CODE_ERROR_DECODE = -252;
    public static final int CODE_ERROR_DEFAULT = -250;
    public static final int CODE_ERROR_NET = -251;
    private String code;
    private String error;
    private int errorCode;
    private String json;
    private String msg;
    private Object obj;
    private String objStr;
    private int qid;
    private int requestCode;

    public int getRequestCode() {
        return this.requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getQid() {
        return this.qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public <T> T getObj() {
        return (T) this.obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getObjStr() {
        return this.objStr;
    }

    public void setObjStr(String objStr) {
        this.objStr = objStr;
    }

    public String getError() {
        return this.error == null ? "" : this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
