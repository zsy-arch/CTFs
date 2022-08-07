package com.vsf2f.f2f.ui.utils.web;

import com.alipay.sdk.sys.a;

/* loaded from: classes2.dex */
public class UrlUtils {
    public static String getValueByName(String url, String name) {
        String[] keyValue = url.substring(url.indexOf("?") + 1).split(a.b);
        for (String str : keyValue) {
            if (str.contains(name)) {
                return str.replace(name + "=", "");
            }
        }
        return "";
    }
}
