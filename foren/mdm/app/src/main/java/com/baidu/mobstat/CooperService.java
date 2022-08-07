package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CooperService extends bf implements ICooperService {
    private static CooperService a;
    private bo b = new bo();

    public static synchronized CooperService a() {
        CooperService cooperService;
        synchronized (CooperService.class) {
            if (a == null) {
                a = new CooperService();
            }
            cooperService = a;
        }
        return cooperService;
    }

    private static String a(Context context) {
        String i = cu.i(context);
        return !TextUtils.isEmpty(i) ? i.replaceAll(":", "") : i;
    }

    private static String a(String str, Context context) {
        if (str == null) {
            return null;
        }
        return str.equals("000000000000000") ? a(context) : str;
    }

    private static String b(Context context) {
        String j = cu.j(context);
        return !TextUtils.isEmpty(j) ? j.replaceAll(":", "") : j;
    }

    private String c(Context context) {
        try {
            cr.a("----------getAppChannel");
            if (this.b.m == null || this.b.m.equals("")) {
                boolean g = be.a().g(context);
                cr.a("----------setChannelWithCode=" + g);
                if (g) {
                    this.b.m = be.a().f(context);
                    cr.a("----------mHeadObject.channel=" + this.b.m);
                }
                if (!g || this.b.m == null || this.b.m.equals("")) {
                    this.b.m = cu.a(context, "BaiduMobAd_CHANNEL");
                }
            }
        } catch (Exception e) {
            cr.a(e);
        }
        return this.b.m;
    }

    @Override // com.baidu.mobstat.ICooperService
    public boolean checkCellLocationSetting(Context context) {
        return "true".equalsIgnoreCase(cu.a(context, "BaiduMobAd_CELL_LOCATION"));
    }

    @Override // com.baidu.mobstat.ICooperService
    public boolean checkGPSLocationSetting(Context context) {
        return "true".equals(cu.a(context, "BaiduMobAd_GPS_LOCATION"));
    }

    @Override // com.baidu.mobstat.ICooperService
    public boolean checkWifiLocationSetting(Context context) {
        return "true".equalsIgnoreCase(cu.a(context, "BaiduMobAd_WIFI_LOCATION"));
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getAppChannel(Context context) {
        return c(context);
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getAppKey(Context context) {
        if (this.b.e == null) {
            this.b.e = cu.a(context, "BaiduMobAd_STAT_ID");
        }
        return this.b.e;
    }

    @Override // com.baidu.mobstat.ICooperService
    public int getAppVersionCode(Context context) {
        if (this.b.h == -1) {
            this.b.h = cu.e(context);
        }
        return this.b.h;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(this.b.i)) {
            this.b.i = cu.f(context);
        }
        return this.b.i;
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ boolean getBoolean(Context context, String str, boolean z) {
        return super.getBoolean(context, str, z);
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getCUID(Context context, boolean z) {
        if (this.b.g == null) {
            this.b.g = be.a().e(context);
            if (this.b.g == null || "".equalsIgnoreCase(this.b.g)) {
                try {
                    this.b.g = cw.a(context);
                    Matcher matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(this.b.g);
                    this.b.g = matcher.replaceAll("");
                    this.b.g = getSecretValue(this.b.g);
                    be.a().b(context, this.b.g);
                } catch (Exception e) {
                    cr.c(e.getMessage());
                }
            }
        }
        if (z) {
            return this.b.g;
        }
        try {
            String str = this.b.g;
            if (!TextUtils.isEmpty(str)) {
                return new String(ck.b(1, cm.a(str.getBytes())));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    @Override // com.baidu.mobstat.ICooperService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getDeviceId(android.telephony.TelephonyManager r6, android.content.Context r7) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.CooperService.getDeviceId(android.telephony.TelephonyManager, android.content.Context):java.lang.String");
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ Float getFloatt(Context context, String str, int i) {
        return super.getFloatt(context, str, i);
    }

    public bo getHeadObject() {
        return this.b;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getHost() {
        return "https://hmma.baidu.com/app.gif";
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ int getInt(Context context, String str, int i) {
        return super.getInt(context, str, i);
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getLinkedWay(Context context) {
        if (TextUtils.isEmpty(this.b.s)) {
            this.b.s = cu.n(context);
        }
        return this.b.s;
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ long getLong(Context context, String str, long j) {
        return super.getLong(context, str, j);
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getMTJSDKVersion() {
        return Build.SDK_VERSION;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getMacID(Context context) {
        if (this.b.t == null || "".equals(this.b.t)) {
            String h = be.a().h(context);
            if (h == null) {
                String a2 = a(context);
                if (a2 != null) {
                    this.b.t = getSecretValue(a2);
                    cr.a("加密=mHeadObject.mHeadObject.macAddr=" + this.b.t);
                    if (!"".equals(this.b.t)) {
                        be.a().d(context, this.b.t);
                    }
                }
            } else {
                this.b.t = h;
            }
        }
        return this.b.t;
    }

    public String getMacIDForTv(Context context) {
        if (this.b.f28u == null || "".equals(this.b.f28u)) {
            String j = be.a().j(context);
            if (j == null) {
                String a2 = cu.a();
                if (a2 == null || a2.equals("")) {
                    a2 = cu.c(1, context);
                }
                if (a2 != null) {
                    this.b.f28u = getSecretValue(a2);
                    cr.a("加密=macAddr=" + this.b.f28u);
                    if (!"".equals(this.b.t)) {
                        be.a().e(context, this.b.f28u);
                    }
                }
            } else {
                this.b.f28u = j;
            }
        }
        return this.b.f28u;
    }

    public String getManufacturer() {
        if (TextUtils.isEmpty(this.b.p)) {
            this.b.p = Build.MANUFACTURER;
        }
        return this.b.p;
    }

    public String getOSSysVersion() {
        if (TextUtils.isEmpty(this.b.d)) {
            this.b.d = Build.VERSION.RELEASE;
        }
        return this.b.d;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getOSVersion() {
        if (TextUtils.isEmpty(this.b.c)) {
            this.b.c = Integer.toString(Build.VERSION.SDK_INT);
        }
        return this.b.c;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getOperator(TelephonyManager telephonyManager) {
        if (TextUtils.isEmpty(this.b.n)) {
            this.b.n = telephonyManager.getNetworkOperator();
        }
        return this.b.n;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getPhoneModel() {
        if (TextUtils.isEmpty(this.b.o)) {
            this.b.o = Build.MODEL;
        }
        return this.b.o;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getSecretValue(String str) {
        return ck.c(1, str.getBytes());
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ boolean getSharedBoolean(Context context, String str, boolean z) {
        return super.getSharedBoolean(context, str, z);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ int getSharedInt(Context context, String str, int i) {
        return super.getSharedInt(context, str, i);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ long getSharedLong(Context context, String str, long j) {
        return super.getSharedLong(context, str, j);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ String getSharedString(Context context, String str, String str2) {
        return super.getSharedString(context, str, str2);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ String getString(Context context, String str, String str2) {
        return super.getString(context, str, str2);
    }

    @Override // com.baidu.mobstat.ICooperService
    public int getTagValue() {
        return 1;
    }

    @Override // com.baidu.mobstat.ICooperService
    public void installHeader(Context context, JSONObject jSONObject) {
        this.b.a(context, jSONObject);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void putBoolean(Context context, String str, boolean z) {
        super.putBoolean(context, str, z);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void putFloat(Context context, String str, Float f) {
        super.putFloat(context, str, f);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void putInt(Context context, String str, int i) {
        super.putInt(context, str, i);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void putLong(Context context, String str, long j) {
        super.putLong(context, str, j);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void putSharedBoolean(Context context, String str, boolean z) {
        super.putSharedBoolean(context, str, z);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void putSharedInt(Context context, String str, int i) {
        super.putSharedInt(context, str, i);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void putSharedLong(Context context, String str, long j) {
        super.putSharedLong(context, str, j);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void putSharedString(Context context, String str, String str2) {
        super.putSharedString(context, str, str2);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void putString(Context context, String str, String str2) {
        super.putString(context, str, str2);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void removeShare(Context context, String str) {
        super.removeShare(context, str);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ void removeString(Context context, String str) {
        super.removeString(context, str);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ boolean updateShareBoolean(Intent intent, Activity activity, String str) {
        return super.updateShareBoolean(intent, activity, str);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ boolean updateShareBoolean(Intent intent, Activity activity, String str, boolean z) {
        return super.updateShareBoolean(intent, activity, str, z);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ boolean updateShareInt(Intent intent, Activity activity, String str, int i) {
        return super.updateShareInt(intent, activity, str, i);
    }

    @Override // com.baidu.mobstat.bf
    public /* bridge */ /* synthetic */ boolean updateShareString(Intent intent, Activity activity, String str) {
        return super.updateShareString(intent, activity, str);
    }
}
