package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ShareUrlSearchHandler.java */
/* loaded from: classes.dex */
public class ad extends a<String, String> {
    private String h;

    public ad(Context context, String str) {
        super(context, str);
        this.h = str;
    }

    @Override // com.amap.api.services.a.a, com.amap.api.services.a.cz
    public Map<String, String> b() {
        StringBuilder sb = new StringBuilder();
        sb.append("channel=open_api").append("&flag=1").append("&address=" + URLEncoder.encode(this.h));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("open_api").append("1").append(this.h).append("@").append("8UbJH6N2szojnTHONAWzB6K7N1kaj7Y0iUMarxac");
        sb.append("&sign=").append(bc.b(stringBuffer.toString()).toUpperCase(Locale.US));
        sb.append("&output=json");
        byte[] bArr = null;
        try {
            bArr = ai.a(sb.toString().getBytes("utf-8"), "Yaynpa84IKOfasFx".substring(0, 16).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            i.a(e, "ShareUrlSearchHandler", "getParams");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("ent", "2");
        hashMap.put("in", bb.a(bArr));
        hashMap.put("keyt", "openapi");
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public String a(String str) throws AMapException {
        JSONObject jSONObject;
        String a;
        try {
            jSONObject = new JSONObject(str);
            a = n.a(jSONObject, "code");
        } catch (JSONException e) {
            i.a(e, "ShareUrlSearchHandler", "paseJSON");
        }
        if ("1".equals(a)) {
            return n.a(jSONObject, "transfer_url");
        }
        if ("0".equals(a)) {
            throw new AMapException(AMapException.AMAP_SERVICE_UNKNOWN_ERROR);
        } else if ("2".equals(a)) {
            throw new AMapException(AMapException.AMAP_SHARE_FAILURE);
        } else if ("3".equals(a)) {
            throw new AMapException(AMapException.AMAP_SERVICE_INVALID_PARAMS);
        } else if ("4".equals(a)) {
            throw new AMapException(AMapException.AMAP_SIGNATURE_ERROR);
        } else {
            if ("5".equals(a)) {
                throw new AMapException(AMapException.AMAP_SHARE_LICENSE_IS_EXPIRED);
            }
            return null;
        }
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.d();
    }

    @Override // com.amap.api.services.a.a
    protected byte[] a(int i, cy cyVar, cz czVar) throws av {
        if (i == 1) {
            return cyVar.d(czVar);
        }
        if (i == 2) {
            return cyVar.e(czVar);
        }
        return null;
    }
}
