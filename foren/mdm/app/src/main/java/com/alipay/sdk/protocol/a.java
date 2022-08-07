package com.alipay.sdk.protocol;

import android.text.TextUtils;

/* loaded from: classes.dex */
public enum a {
    None("none"),
    WapPay("js://wappay"),
    Update("js://update");
    
    private String d;

    a(String str) {
        this.d = str;
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return None;
        }
        a aVar = None;
        a[] values = values();
        for (a aVar2 : values) {
            if (str.startsWith(aVar2.d)) {
                return aVar2;
            }
        }
        return aVar;
    }
}
