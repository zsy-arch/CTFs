package com.http.bean;

/* loaded from: classes2.dex */
public class BaseEntity<T> {
    private static int SUCCESS_CODE = 42440;
    private int code;
    private T data;
    private String msg;

    public boolean isSuccess() {
        return getCode() == SUCCESS_CODE;
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

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
