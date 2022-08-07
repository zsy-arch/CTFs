package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.StatService;
import com.http.config.URLConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DataCore {
    private static JSONObject a = new JSONObject();
    private static DataCore b = new DataCore();
    private int c = 0;
    private int d = 0;
    private JSONArray e = new JSONArray();
    private JSONArray f = new JSONArray();
    private JSONArray g = new JSONArray();
    private boolean h = false;
    private StatService.WearListener i;

    private DataCore() {
    }

    private void a(Context context) {
        cr.a("clear cache log" + this.d);
        this.d = 0;
        a(false);
        synchronized (a) {
            a = new JSONObject();
        }
        installHeader(context);
        synchronized (this.f) {
            this.f = new JSONArray();
        }
        synchronized (this.e) {
            this.e = new JSONArray();
        }
        synchronized (this.g) {
            this.g = new JSONArray();
        }
        flush(context);
        ca.a().c();
        bs.a().c(context);
    }

    private void a(boolean z) {
        this.h = z;
    }

    private boolean a() {
        return this.h;
    }

    public static DataCore instance() {
        return b;
    }

    public String constructLogWithEmptyBody(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        bo headObject = CooperService.a().getHeadObject();
        if (TextUtils.isEmpty(headObject.e)) {
            headObject.a(context, jSONObject2);
        } else {
            headObject.b(context, jSONObject2);
        }
        JSONArray jSONArray = new JSONArray();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject2.put("t", currentTimeMillis);
            jSONObject2.put("ss", currentTimeMillis);
            jSONObject2.put("wl2", jSONArray);
            jSONObject2.put("k", str);
            jSONObject.put("he", jSONObject2);
            try {
                jSONObject.put("pr", jSONArray);
                try {
                    jSONObject.put("ev", jSONArray);
                    try {
                        jSONObject.put("ex", jSONArray);
                        return jSONObject.toString();
                    } catch (JSONException e) {
                        cr.a(e);
                        return null;
                    }
                } catch (JSONException e2) {
                    cr.a(e2);
                    return null;
                }
            } catch (JSONException e3) {
                cr.a(e3);
                return null;
            }
        } catch (Exception e4) {
            cr.a(e4);
            return null;
        }
    }

    public void flush(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            synchronized (this.e) {
                jSONObject.put("pr", new JSONArray(this.e.toString()));
            }
            synchronized (this.f) {
                jSONObject.put("ev", new JSONArray(this.f.toString()));
            }
            synchronized (a) {
                jSONObject.put("he", new JSONObject(a.toString()));
            }
        } catch (Exception e) {
            cr.a("flushLogWithoutHeader() construct cache error");
        }
        String jSONObject2 = jSONObject.toString();
        if (a()) {
            cr.a("cache.json exceed 204800B,stop flush.");
            return;
        }
        int length = jSONObject2.getBytes().length;
        if (length >= 204800) {
            a(true);
            return;
        }
        this.c = length;
        cr.a("flush:cacheFileSize is:" + this.c);
        cl.a(context, cu.p(context) + "__local_stat_cache.json", jSONObject2, false);
        synchronized (this.g) {
            String jSONArray = this.g.toString();
            cr.a("flush wifi data: " + jSONArray);
            cl.a(context, "__local_ap_info_cache.json", jSONArray, false);
        }
    }

    public String getAppKey(Context context) {
        return CooperService.a().getAppKey(context);
    }

    public synchronized void installHeader(Context context) {
        synchronized (a) {
            CooperService.a().getHeadObject().a(context, a);
        }
    }

    public boolean isPartEmpty() {
        boolean z;
        synchronized (this.e) {
            z = this.e.length() == 0;
        }
        return z;
    }

    public void loadLastSession(Context context) {
        cr.a("LoadLastSession()");
        if (context != null) {
            String str = cu.p(context) + "__local_last_session.json";
            if (cl.c(context, str)) {
                String a2 = cl.a(context, str);
                if ("".equals(a2)) {
                    cr.a("loadLastSession(): last_session.json file not found.");
                    return;
                }
                cl.a(context, str, new JSONObject().toString(), false);
                putSession(a2);
                flush(context);
            }
        }
    }

    public void loadStatData(Context context) {
        if (context != null) {
            String str = cu.p(context) + "__local_stat_cache.json";
            if (cl.c(context, str)) {
                String a2 = cl.a(context, str);
                if (a2.equals("")) {
                    cr.a("stat_cache file not found.");
                    return;
                }
                cr.a("loadStatData, ");
                try {
                    this.c = a2.getBytes().length;
                    cr.a("load Stat Data:cacheFileSize is:" + this.c);
                    JSONObject jSONObject = new JSONObject(a2);
                    cr.a("Load cache:" + a2);
                    long currentTimeMillis = System.currentTimeMillis();
                    JSONArray jSONArray = jSONObject.getJSONArray("pr");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (currentTimeMillis - jSONObject2.getLong(URLConfig.baidu_url) <= 604800000) {
                            putSession(jSONObject2, true);
                        }
                    }
                    JSONArray jSONArray2 = jSONObject.getJSONArray("ev");
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                        if (currentTimeMillis - jSONObject3.getLong("t") <= 604800000) {
                            putEvent(jSONObject3, true);
                        }
                    }
                    if (!isPartEmpty()) {
                        try {
                            JSONObject jSONObject4 = jSONObject.getJSONObject("he");
                            synchronized (a) {
                                a = jSONObject4;
                            }
                        } catch (JSONException e) {
                            cr.a(e);
                        }
                    }
                } catch (JSONException e2) {
                    cr.a("Load stat data error:" + e2);
                }
            }
        }
    }

    public void loadWifiData(Context context) {
        JSONArray jSONArray;
        if (context != null && cl.c(context, "__local_ap_info_cache.json")) {
            try {
                JSONArray jSONArray2 = new JSONArray(cl.a(context, "__local_ap_info_cache.json"));
                int length = jSONArray2.length();
                if (length >= 10) {
                    jSONArray = new JSONArray();
                    for (int i = length - 10; i < length; i++) {
                        jSONArray.put(jSONArray2.get(i));
                    }
                } else {
                    jSONArray = jSONArray2;
                }
                String e = cu.e(1, context);
                if (!TextUtils.isEmpty(e)) {
                    jSONArray.put(e);
                }
                synchronized (this.g) {
                    this.g = jSONArray;
                    cr.a("wifiPart: " + this.g.toString());
                }
            } catch (JSONException e2) {
                cr.b(e2);
            }
        }
    }

    public void putEvent(String str, String str2, int i, long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("i", str);
            jSONObject.put("l", str2);
            jSONObject.put("c", i);
            jSONObject.put("t", j);
            jSONObject.put("d", j2);
            putEvent(jSONObject, false);
            cr.a("put event:" + jSONObject.toString());
        } catch (JSONException e) {
            cr.a(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0079, code lost:
        if (r3.equals("") != false) goto L_0x007b;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void putEvent(org.json.JSONObject r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 443
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.putEvent(org.json.JSONObject, boolean):void");
    }

    public void putSession(String str) {
        if (!str.equals("{}") && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                putSession(jSONObject, false);
                cr.a("Load last session:" + jSONObject);
            } catch (JSONException e) {
                cr.a("putSession()" + e);
            }
        }
    }

    public void putSession(JSONObject jSONObject, boolean z) {
        if (jSONObject != null && !z) {
            int length = jSONObject.toString().getBytes().length;
            cr.a("putSession:addSize is:" + length);
            if (length + this.c > 204800) {
                cr.a("putSession: size is full!");
                return;
            }
        }
        synchronized (this.e) {
            try {
                this.e.put(this.e.length(), jSONObject);
            } catch (JSONException e) {
                cr.a(e);
            }
        }
    }

    public void sendLogData(Context context) {
        cr.a("sendLogData() begin.");
        bo headObject = CooperService.a().getHeadObject();
        if (headObject != null && TextUtils.isEmpty(headObject.e)) {
            synchronized (a) {
                headObject.a(context, a);
                cr.a("constructHeader() begin." + a + a.length());
            }
            if (TextUtils.isEmpty(headObject.e)) {
                cr.c("不能在manifest.xml中找到APP Key||can't find app key in manifest.xml.");
                return;
            }
        }
        JSONObject jSONObject = new JSONObject();
        synchronized (a) {
            try {
                a.put("t", System.currentTimeMillis());
                a.put("ss", ca.a().d());
                synchronized (this.g) {
                    a.put("wl2", this.g);
                }
                jSONObject.put("he", a);
                synchronized (this.e) {
                    try {
                        jSONObject.put("pr", this.e);
                        synchronized (this.f) {
                            try {
                                jSONObject.put("ev", this.f);
                                try {
                                    jSONObject.put("ex", new JSONArray());
                                    String jSONObject2 = jSONObject.toString();
                                    cr.a("---Send Data Is:" + jSONObject2);
                                    sendLogData(context, jSONObject2);
                                    a(context);
                                } catch (JSONException e) {
                                    cr.a(e);
                                }
                            } catch (JSONException e2) {
                                cr.a(e2);
                            }
                        }
                    } catch (JSONException e3) {
                        cr.a(e3);
                    }
                }
            } catch (Exception e4) {
                cr.a(e4);
            }
        }
    }

    public void sendLogData(Context context, String str) {
        if (this.i == null || !this.i.onSendLogData(str)) {
            bs.a().a(context, str);
        } else {
            cr.a("log data has been passed to app level");
        }
    }

    public void setAppChannel(Context context, String str, boolean z) {
        if (str == null || str.equals("")) {
            cr.c("设置的渠道不能为空或者为null || The channel that you have been set is null or empty, please check it.");
        }
        CooperService.a().getHeadObject().m = str;
        if (z && str != null && !str.equals("")) {
            be.a().c(context, str);
            be.a().c(context, true);
        }
        if (!z) {
            be.a().c(context, "");
            be.a().c(context, false);
        }
    }

    public void setAppChannel(String str) {
        if (str == null || str.equals("")) {
            cr.c("设置的渠道不能为空或者为null || The channel that you have been set is null or empty, please check it.");
        }
        CooperService.a().getHeadObject().m = str;
    }

    public void setAppKey(String str) {
        CooperService.a().getHeadObject().e = str;
    }
}
