package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* loaded from: classes.dex */
public interface Config {
    Context getApplicationContext();

    RpcParams getRpcParams();

    Transport getTransport();

    String getUrl();

    boolean isGzip();
}
