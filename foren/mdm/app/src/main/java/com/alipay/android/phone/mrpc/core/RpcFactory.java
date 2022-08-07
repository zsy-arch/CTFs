package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

/* loaded from: classes.dex */
public class RpcFactory {
    private Config mConfig;
    private RpcInvoker mRpcInvoker = new RpcInvoker(this);

    public RpcFactory(Config config) {
        this.mConfig = config;
    }

    public Config getConfig() {
        return this.mConfig;
    }

    public <T> T getRpcProxy(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new RpcInvocationHandler(this.mConfig, cls, this.mRpcInvoker));
    }
}
