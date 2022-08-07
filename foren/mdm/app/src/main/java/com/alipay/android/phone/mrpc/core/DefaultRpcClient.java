package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* loaded from: classes.dex */
public class DefaultRpcClient extends RpcClient {
    private Context mContext;

    public DefaultRpcClient(Context context) {
        this.mContext = context;
    }

    private Config createConfig(final RpcParams rpcParams) {
        return new Config() { // from class: com.alipay.android.phone.mrpc.core.DefaultRpcClient.1
            @Override // com.alipay.android.phone.mrpc.core.Config
            public Context getApplicationContext() {
                return DefaultRpcClient.this.mContext.getApplicationContext();
            }

            @Override // com.alipay.android.phone.mrpc.core.Config
            public RpcParams getRpcParams() {
                return rpcParams;
            }

            @Override // com.alipay.android.phone.mrpc.core.Config
            public Transport getTransport() {
                return HttpManager.getInstance(getApplicationContext());
            }

            @Override // com.alipay.android.phone.mrpc.core.Config
            public String getUrl() {
                return rpcParams.getGwUrl();
            }

            @Override // com.alipay.android.phone.mrpc.core.Config
            public boolean isGzip() {
                return rpcParams.isGzip();
            }
        };
    }

    @Override // com.alipay.android.phone.mrpc.core.RpcClient
    public <T> T getRpcProxy(Class<T> cls, RpcParams rpcParams) {
        return (T) new RpcFactory(createConfig(rpcParams)).getRpcProxy(cls);
    }
}
