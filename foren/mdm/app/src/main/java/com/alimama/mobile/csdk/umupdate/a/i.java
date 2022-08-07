package com.alimama.mobile.csdk.umupdate.a;

import android.location.Location;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import com.alimama.mobile.a;
import com.alimama.mobile.csdk.umupdate.b.d;
import com.alimama.mobile.csdk.umupdate.b.e;
import com.alimama.mobile.csdk.umupdate.b.f;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import com.alimama.mobile.csdk.umupdate.models.b;
import com.alimama.mobile.csdk.umupdate.models.c;
import com.hyphenate.util.HanziToPinyin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: UMGetDataTask.java */
/* loaded from: classes.dex */
public class i extends b<Void, Void, Message> {
    static final String e = i.class.getCanonicalName();
    b f;
    private final a.AbstractC0004a g;
    private final int h;
    private final boolean i;

    public i(b bVar, a.AbstractC0004a aVar, int i, boolean z) {
        this.g = aVar;
        this.h = i;
        this.i = z;
        this.f = bVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alimama.mobile.csdk.umupdate.a.b
    public void c() {
        super.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Message a(Void... voidArr) {
        List<Promoter> a;
        boolean isEmpty = TextUtils.isEmpty(this.f.b().sid);
        if (isEmpty) {
            this.f.b().timeline[0] = System.currentTimeMillis();
        }
        int[] iArr = new int[1];
        if (this.h == 1) {
            a = a(TextUtils.isEmpty(this.f.b().sid), true);
            if (a == null || a.size() <= 0) {
                iArr[0] = 0;
                g.c("get data from local-cache.but has no data.", new Object[0]);
            } else {
                iArr[0] = 1;
                g.c("get data from local-cache.", new Object[0]);
            }
        } else {
            a = a(iArr);
            g.c("get data from live.", new Object[0]);
        }
        if (isEmpty) {
            this.f.b().timeline[1] = System.currentTimeMillis();
        }
        Message message = new Message();
        message.obj = a;
        message.arg1 = iArr[0];
        return message;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Message message) {
        int a;
        if (this.g == null) {
            return;
        }
        if (message == null || message.obj == null) {
            this.g.dataReceived(message.arg1, new ArrayList());
            return;
        }
        List<Promoter> list = (List) message.obj;
        if (this.f.b().filterInstalledApp && (a = a(list)) > 0 && this.f.b().newTips > 0) {
            int i = this.f.b().newTips;
            int i2 = this.f.b().newTips - a;
            MMEntity b = this.f.b();
            if (i2 <= 0) {
                i2 = -1;
            }
            b.newTips = i2;
            g.b("new tips has changed " + i + " ===> " + this.f.b().newTips, new Object[0]);
        }
        this.g.dataReceived(message.arg1, list);
    }

    private List<Promoter> a(int[] iArr) {
        boolean z;
        int nextInt = new Random().nextInt(1000);
        Map<String, Object> a = a(this.f.b());
        if (a == null) {
            return null;
        }
        if (a.containsKey(f.o)) {
            z = TextUtils.isEmpty((String) a.get(f.o));
        } else {
            z = true;
        }
        f a2 = new com.alimama.mobile.csdk.umupdate.b.b().a(new e(a));
        if (iArr != null) {
            iArr[0] = a2 == null ? 0 : a2.a;
        }
        if (a2 == null || a2.b == null) {
            return null;
        }
        g.b("   requestLive get resStr: " + a2.b.toString(), new Object[0]);
        try {
            List<Promoter> a3 = a(a2.b);
            if (a3 != null && a3.size() > 0) {
                if (this.i && a2.a == 1) {
                    this.f.a().a(z, a2.b);
                }
                return a3;
            } else if (!z) {
                return null;
            } else {
                this.f.a().a();
                return null;
            }
        } catch (Exception e2) {
            g.d(e, nextInt + "  request from network error:", e2);
            return null;
        }
    }

    private List<Promoter> a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        this.f.b().warp(jSONObject);
        c a = this.f.a();
        if (a.b() != this.f.b().preload) {
            a.a(this.f.b().preload);
        }
        ArrayList arrayList = new ArrayList();
        a(arrayList, Promoter.class, jSONObject, this.f.b().slot_act_params);
        return arrayList;
    }

    private List<Promoter> a(boolean z, boolean z2) {
        try {
            List<Promoter> a = a(this.f.a().a(z, this.f.b().sid_expired, z2));
            if (a != null) {
                if (a.size() > 0) {
                    return a;
                }
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> a(MMEntity mMEntity) {
        HashMap hashMap = new HashMap();
        a b = a.a().b();
        hashMap.put("sdk_version", e.b);
        hashMap.put(f.y, e.a);
        hashMap.put(f.z, e.c);
        String f = TextUtils.isEmpty(e.d) ? b.f("MUNION_CHANNEL") : e.d;
        if (!TextUtils.isEmpty(f)) {
            hashMap.put("channel", f);
        }
        hashMap.put("device_id", b.r());
        hashMap.put("idmd5", j.c(b.r()));
        hashMap.put("device_model", Build.MODEL);
        hashMap.put("os", f.a);
        String p = b.p();
        if (!TextUtils.isEmpty(p)) {
            hashMap.put("mc", p);
        }
        hashMap.put("os_version", Build.VERSION.RELEASE);
        hashMap.put(f.G, b.n());
        hashMap.put("language", b.m());
        hashMap.put("timezone", b.o());
        hashMap.put("resolution", b.t());
        String[] C = b.C();
        hashMap.put("access", C[0]);
        hashMap.put("access_subtype", C[1]);
        hashMap.put("carrier", b.E());
        Location D = b.D();
        if (D != null) {
            hashMap.put("lat", String.valueOf(D.getLatitude()));
            hashMap.put("lng", String.valueOf(D.getLongitude()));
            hashMap.put(f.Q, D.getProvider());
            hashMap.put(f.O, String.valueOf(D.getTime()));
            hashMap.put(f.P, String.valueOf(D.getAccuracy()));
        }
        hashMap.put("cpu", b.F());
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String str = format.split(HanziToPinyin.Token.SEPARATOR)[0];
        String str2 = format.split(HanziToPinyin.Token.SEPARATOR)[1];
        hashMap.put("date", str);
        hashMap.put(f.az, str2);
        hashMap.put(f.R, Build.MANUFACTURER);
        hashMap.put("timezone", b.o());
        hashMap.put(f.bp, b.g());
        hashMap.put(f.bq, b.h());
        hashMap.put(f.br, b.i());
        hashMap.put(f.bs, b.f());
        if (!TextUtils.isEmpty(this.f.b().slotId)) {
            hashMap.put(f.C, this.f.b().slotId);
        } else if (!TextUtils.isEmpty(this.f.b().appkey)) {
            hashMap.put("app_key", this.f.b().appkey);
        } else {
            g.d("Both APPKEY and SLOTID are empty, please specify either one. Request aborted.", new Object[0]);
            return null;
        }
        if (!TextUtils.isEmpty(this.f.b().filterPromoter)) {
            hashMap.put("promoter", this.f.b().filterPromoter);
        }
        hashMap.put(f.bz, Integer.valueOf(this.f.b().layoutType));
        if (!TextUtils.isEmpty(this.f.b().keywords)) {
            hashMap.put(f.aA, j.b(this.f.b().keywords));
        }
        if (!TextUtils.isEmpty(this.f.b().slot_act_params)) {
            String[] split = this.f.b().slot_act_params.split(com.alipay.sdk.sys.a.b);
            try {
                HashMap hashMap2 = new HashMap();
                for (String str3 : split) {
                    String[] split2 = str3.split("=");
                    if (split2.length == 2) {
                        hashMap2.put(split2[0], split2[1]);
                    }
                }
                for (String str4 : hashMap2.keySet()) {
                    hashMap.put(str4, hashMap2.get(str4));
                }
            } catch (Exception e2) {
            }
        }
        if (!TextUtils.isEmpty(this.f.b().urlParams)) {
            hashMap.put(f.X, this.f.b().urlParams);
        }
        if (!TextUtils.isEmpty(this.f.b().tag)) {
            hashMap.put(f.aB, this.f.b().tag);
        }
        if (this.f.b().autofill != 1) {
            hashMap.put(f.aC, Integer.valueOf(this.f.b().autofill));
        }
        if (!TextUtils.isEmpty(this.f.b().sid)) {
            hashMap.put(f.o, this.f.b().sid);
        }
        if (!TextUtils.isEmpty(this.f.b().psid)) {
            hashMap.put(f.ai, this.f.b().psid);
        }
        hashMap.put(f.bN, 1);
        hashMap.put(f.af, this.f.b().resType == null ? "" : this.f.b().resType.toString());
        if (this.f.b().template != null) {
            hashMap.put(f.bg, this.f.b().template.toString() + (TextUtils.isEmpty(this.f.b().templateAttrs) ? "" : "." + this.f.b().templateAttrs));
        }
        if (this.f.b().landing_type > 0) {
            hashMap.put(f.aU, Integer.valueOf(this.f.b().landing_type));
        }
        return hashMap;
    }

    private <T extends Promoter> void a(Collection collection, Class<T> cls, JSONObject jSONObject, String str) {
        g.c("get promoters use class " + cls.toString(), new Object[0]);
        try {
            if (jSONObject.has(f.l)) {
                JSONArray jSONArray = jSONObject.getJSONArray(f.l);
                for (int i = 0; i < jSONArray.length(); i++) {
                    Promoter promoterFromJson = Promoter.getPromoterFromJson((JSONObject) jSONArray.get(i), cls);
                    promoterFromJson.slot_act_pams = str;
                    collection.add(promoterFromJson);
                }
                return;
            }
            g.d("failed requesting", new Object[0]);
        } catch (Exception e2) {
            g.a(e2, "", new Object[0]);
        }
    }

    protected int a(List<Promoter> list) {
        if (list == null) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int size = list.size() - 1; size >= 0; size--) {
            Promoter promoter = list.get(size);
            boolean z = this.f.b().filterInstalledApp;
            if (promoter != null && promoter.filterInstalledApp && z && a.a().b().c(promoter.app_package_name)) {
                g.c(e.e, "Installed: " + list.get(size).title + ". Remove from the list.");
                Promoter remove = list.remove(size);
                arrayList.add(remove);
                if (remove.new_tip == 1) {
                    i++;
                }
            }
            i = i;
        }
        if (arrayList.size() <= 0) {
            return i;
        }
        new d.a(this.f.b()).a(-1).b(-1).c(-1).a((Promoter[]) arrayList.toArray(new Promoter[0])).a().a();
        return i;
    }
}
