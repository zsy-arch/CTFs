package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Future;

/* loaded from: classes.dex */
public interface Transport {
    Future<Response> execute(Request request);
}
