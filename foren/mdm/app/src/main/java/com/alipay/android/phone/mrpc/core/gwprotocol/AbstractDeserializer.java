package com.alipay.android.phone.mrpc.core.gwprotocol;

import java.lang.reflect.Type;

/* loaded from: classes.dex */
public abstract class AbstractDeserializer implements Deserializer {
    protected byte[] mData;
    protected Type mType;

    public AbstractDeserializer(Type type, byte[] bArr) {
        this.mType = type;
        this.mData = bArr;
    }
}
