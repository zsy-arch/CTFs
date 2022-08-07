package com.vsf2f.f2f.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class OrderTraceBean implements Serializable {
    private String operationTime;
    private String traceType;

    public String getTraceType() {
        return this.traceType;
    }

    public void setTraceType(String traceType) {
        this.traceType = traceType;
    }

    public String getOperationTime() {
        return this.operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }
}
