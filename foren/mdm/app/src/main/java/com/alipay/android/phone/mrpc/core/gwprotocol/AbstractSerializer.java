package com.alipay.android.phone.mrpc.core.gwprotocol;

/* loaded from: classes.dex */
public abstract class AbstractSerializer implements Serializer {
    protected String mOperationType;
    protected Object mParams;

    public AbstractSerializer(String str, Object obj) {
        this.mOperationType = str;
        this.mParams = obj;
    }
}
