package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class ErrorBean {
    private String error;
    private String status;
    private String success;

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String toString() {
        return "ErrorBean{error='" + this.error + "', status='" + this.status + "', success='" + this.success + "'}";
    }
}
