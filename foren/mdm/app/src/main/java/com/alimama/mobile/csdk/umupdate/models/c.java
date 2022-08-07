package com.alimama.mobile.csdk.umupdate.models;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alimama.mobile.a;
import com.alimama.mobile.csdk.umupdate.a.g;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import org.json.JSONObject;

/* compiled from: FileCache.java */
/* loaded from: classes.dex */
public class c {
    public static final int a = 0;
    public static final int b = 1;
    private static final String j = "EXCHANGE_PRELOAD_ADS";
    protected String c;
    private final String f = "PROMOTERS_FIRST_PAGE_";
    private final String g = "PROMOTERS_NEXT_PAGE_";
    private String h = "";
    private String i = "";
    protected String d = "";
    protected String e = "";

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(MMEntity mMEntity) {
        this.c = "EXCHANGE_PRELOAD_ADS_" + mMEntity.keywords + "_" + mMEntity.autofill;
        if (!TextUtils.isEmpty(this.e) && !TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.i)) {
            return;
        }
        if (TextUtils.isEmpty(mMEntity.slotId)) {
            String str = mMEntity.appkey;
            if (TextUtils.isEmpty(str)) {
                g.d("No found Slot_id or Appkey!!!!!", new Object[0]);
                return;
            }
            this.e = "PRELOAD_KEY_" + str;
            this.h = "PROMOTERS_FIRST_PAGE_" + str;
            this.d = "PROMOTERS_NEXT_PAGE_" + str;
            this.i = "PRELOAD_UPDATE_DATE_" + str;
            return;
        }
        String str2 = mMEntity.slotId;
        this.e = "PRELOAD_KEY_" + str2;
        this.h = "PROMOTERS_FIRST_PAGE_" + str2;
        this.d = "PROMOTERS_NEXT_PAGE_" + str2;
        this.i = "PRELOAD_UPDATE_DATE_" + str2;
    }

    public void a() {
        g.c("remove cache....[" + this.c + "]", new Object[0]);
        SharedPreferences sharedPreferences = a.a().c().getSharedPreferences(this.c, 0);
        synchronized (sharedPreferences) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(this.h);
            edit.remove(this.d);
            edit.remove(this.i);
            edit.commit();
        }
    }

    public int b() {
        return a.a().c().getSharedPreferences(this.c, 0).getInt(this.e, 0);
    }

    public void a(int i) {
        SharedPreferences.Editor edit = a.a().c().getSharedPreferences(this.c, 0).edit();
        edit.putInt(this.e, i);
        edit.commit();
        g.b("Save the " + this.e + "   " + i, new Object[0]);
    }

    public JSONObject a(boolean z, long j2, boolean z2) {
        String str;
        boolean z3 = false;
        try {
            SharedPreferences sharedPreferences = a.a().c().getSharedPreferences(this.c, 0);
            if (z) {
                g.b("Request data from first-cache..", new Object[0]);
                if ((System.currentTimeMillis() - Long.valueOf(sharedPreferences.getLong(this.i, 0L)).longValue()) / 1000 > (TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC * j2) - 3600) {
                    z3 = true;
                }
                if (z3) {
                    a();
                    g.e("Cache data is inactivation...", new Object[0]);
                    return null;
                }
                str = this.h;
            } else {
                g.b("Request data from second-cache..", new Object[0]);
                str = this.d;
            }
            String string = sharedPreferences.getString(str, null);
            if (string == null) {
                return null;
            }
            if (z2) {
                synchronized (sharedPreferences) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.remove(str);
                    edit.commit();
                    g.c("destroy the used cache data.", new Object[0]);
                }
            }
            return new JSONObject(string);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.d;
    }

    public boolean a(boolean z, JSONObject jSONObject) {
        SharedPreferences sharedPreferences = a.a().c().getSharedPreferences(this.c, 0);
        if (jSONObject == null) {
            return false;
        }
        g.c("save json to cache....", new Object[0]);
        synchronized (sharedPreferences) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putLong(this.i, System.currentTimeMillis());
            if (z) {
                edit.putString(this.h, jSONObject.toString());
            } else {
                edit.putString(this.d, jSONObject.toString());
            }
            edit.commit();
        }
        return true;
    }
}
