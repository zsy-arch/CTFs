package com.alipay.sdk.packet.impl;

import com.alipay.sdk.packet.d;
import com.tencent.open.utils.SystemUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b extends d {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alipay.sdk.packet.d
    public final String b() {
        return SystemUtils.QQ_VERSION_NAME_5_0_0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alipay.sdk.packet.d
    public final JSONObject a() throws JSONException {
        return d.a("sdkConfig", "obtain");
    }
}
