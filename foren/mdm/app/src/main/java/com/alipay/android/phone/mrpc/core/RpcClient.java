package com.alipay.android.phone.mrpc.core;

/* loaded from: classes.dex */
public abstract class RpcClient {
    public abstract <T> T getRpcProxy(Class<T> cls, RpcParams rpcParams);
}
