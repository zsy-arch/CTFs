package com.amap.api.services.a;

import com.amap.api.services.a.be;
import com.amap.api.services.core.ServiceSettings;
import com.tencent.open.utils.SystemUtils;

/* compiled from: ConfigableConst.java */
/* loaded from: classes.dex */
public class h {
    public static final String[] a = {"com.amap.api.services", "com.amap.api.search.admic"};

    public static String a() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://restapi.amap.com/v3" : "https://restapi.amap.com/v3";
    }

    public static String b() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://yuntuapi.amap.com" : "https://yuntuapi.amap.com";
    }

    public static String c() {
        return ServiceSettings.getInstance().getLanguage();
    }

    public static be a(boolean z) {
        try {
            return new be.a("sea", SystemUtils.QQ_VERSION_NAME_5_0_0, "AMAP SDK Android Search 5.0.0").a(a).a(z).a(SystemUtils.QQ_VERSION_NAME_5_0_0).a();
        } catch (av e) {
            i.a(e, "ConfigableConst", "getSDKInfo");
            return null;
        }
    }

    public static be b(boolean z) {
        try {
            return new be.a("cloud", SystemUtils.QQ_VERSION_NAME_5_0_0, "AMAP SDK Android Search 5.0.0").a(a).a(z).a();
        } catch (av e) {
            i.a(e, "ConfigableConst", "getCloudSDKInfo");
            return null;
        }
    }

    public static String d() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://m5.amap.com/ws/mapapi/shortaddress/transform" : "https://m5.amap.com/ws/mapapi/shortaddress/transform";
    }
}
