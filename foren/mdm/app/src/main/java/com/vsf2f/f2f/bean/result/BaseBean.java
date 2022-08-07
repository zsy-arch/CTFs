package com.vsf2f.f2f.bean.result;

/* loaded from: classes2.dex */
public class BaseBean<T> {
    private String message;
    private T obj;
    private String ret;

    public String getRet() {
        return this.ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return this.obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
