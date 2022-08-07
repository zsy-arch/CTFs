package com.amap.api.services.a;

import android.content.Context;
import com.alipay.sdk.sys.a;
import com.tencent.open.utils.SystemUtils;
import com.yolanda.nohttp.Headers;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BasicLBSRestHandler.java */
/* loaded from: classes.dex */
public abstract class b<T, V> extends a<T, V> {
    protected abstract String e();

    public b(Context context, T t) {
        super(context, t);
    }

    @Override // com.amap.api.services.a.cz
    public byte[] f() {
        try {
            String e = e();
            String d = d(e);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(e);
            String a = az.a();
            stringBuffer.append("&ts=" + a);
            stringBuffer.append("&scode=" + az.a(this.d, a, d));
            return stringBuffer.toString().getBytes("utf-8");
        } catch (Throwable th) {
            i.a(th, "ProtocalHandler", "getEntity");
            return null;
        }
    }

    @Override // com.amap.api.services.a.a, com.amap.api.services.a.cz
    public Map<String, String> b() {
        return null;
    }

    @Override // com.amap.api.services.a.a, com.amap.api.services.a.cz
    public Map<String, String> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        hashMap.put("User-Agent", "AMAP SDK Android Search 5.0.0");
        hashMap.put("X-INFO", az.a(this.d, o.a, null, false));
        hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", SystemUtils.QQ_VERSION_NAME_5_0_0, "sea"));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    @Override // com.amap.api.services.a.a
    protected V d() {
        return null;
    }

    private String d(String str) {
        String[] split = str.split(a.b);
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(c(str2));
            stringBuffer.append(a.b);
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.length() > 1 ? (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1) : str;
    }

    protected String b(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            i.a(e, "ProtocalHandler", "strEncoderUnsupportedEncodingException");
            return "";
        } catch (Exception e2) {
            i.a(e2, "ProtocalHandler", "strEncoderException");
            return "";
        }
    }

    protected String c(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            i.a(e, "ProtocalHandler", "strReEncoder");
            return "";
        } catch (Exception e2) {
            i.a(e2, "ProtocalHandler", "strReEncoderException");
            return "";
        }
    }
}
