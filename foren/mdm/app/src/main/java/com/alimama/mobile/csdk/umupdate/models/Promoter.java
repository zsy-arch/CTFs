package com.alimama.mobile.csdk.umupdate.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.alimama.mobile.csdk.umupdate.a.e;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alimama.mobile.csdk.umupdate.a.g;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Promoter implements Parcelable {
    public static final int LANDING_TYPE_ALIP4P = 5;
    public static final int LANDING_TYPE_BROWSER = 3;
    public static final int LANDING_TYPE_DIRECT_DOWNLOAD = 1;
    public static final int LANDING_TYPE_POPUP = 0;
    public static final int LANDING_TYPE_UMENGAPP = 6;
    public static final int LANDING_TYPE_WAP_WEBVIEW = 4;
    public static final int LANDING_TYPE_WEBVIEW = 2;
    public static final int REPORT_CLICK_TO_LAUNCH = 4;
    public static final int REPORT_CLICK_TO_LAUNCH_DETAIL_PAGE = 5;
    public static final int REPORT_CLICK_TO_PROMOTE = 2;
    public static final int REPORT_DOWNLOAD = 1;
    public static final int REPORT_DOWNLOAD_CLICK = 3;
    public static final int REPORT_DOWNLOAD_CLICK_DIRECT = 7;
    public static final int REPORT_DOWNLOAD_FAILED = -2;
    public static final int REPORT_ENTRANCE_CLICK = 15;
    public static final int REPORT_ENTRANCE_IMPRESSION = 14;
    public static final int REPORT_FEED_NOMATCH = -4;
    public static final int REPORT_FEED_UNIMPRESSION = -3;
    public static final int REPORT_FILTERED = -1;
    public static final int REPORT_IMPRESSION = 0;
    public String ad_words;
    public int anim_in;
    public String app_package_name;
    public int app_version_code;
    public String app_version_name;
    public double bid;
    public int category;
    public int content_type;
    public String description;
    public int display_type;
    public boolean filterInstalledApp;
    public String icon;
    public int image_type;
    public String img;
    public String[] imgs;
    public String landing_size;
    public int landing_type;
    public int new_tip;
    public String price;
    public String prom_act_pams;
    public String promoter;
    public String promoterPrice;
    public String provider;
    public int sell;
    public long size;
    public String slot_act_pams;
    public String text_color;
    public String text_font;
    public String text_size;
    public String title;
    public String url;
    public String url_in_app;
    private static final String a = Promoter.class.getSimpleName();
    public static final Parcelable.Creator<Promoter> CREATOR = new Parcelable.Creator<Promoter>() { // from class: com.alimama.mobile.csdk.umupdate.models.Promoter.1
        /* renamed from: a */
        public Promoter createFromParcel(Parcel parcel) {
            return new Promoter(parcel);
        }

        /* renamed from: a */
        public Promoter[] newArray(int i) {
            return new Promoter[i];
        }
    };

    /* loaded from: classes.dex */
    public interface a<T extends Promoter> {
        Collection<T> a();

        String b();

        Class<T> c();
    }

    /* loaded from: classes.dex */
    public enum b {
        TEL {
            @Override // java.lang.Enum
            public String toString() {
                return "telephone";
            }
        },
        CALLBACK {
            @Override // java.lang.Enum
            public String toString() {
                return "callback";
            }
        };

        public static String[] a() {
            b[] values = values();
            String[] strArr = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                strArr[i] = values[i].toString();
            }
            return strArr;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int length;
        String[] strArr;
        parcel.writeString(this.promoter);
        parcel.writeInt(this.category);
        parcel.writeInt(this.content_type);
        parcel.writeInt(this.display_type);
        parcel.writeString(this.img);
        parcel.writeInt(this.image_type);
        parcel.writeInt(this.anim_in);
        parcel.writeInt(this.landing_type);
        parcel.writeString(this.text_font);
        parcel.writeString(this.text_size);
        parcel.writeString(this.text_color);
        parcel.writeString(this.title);
        parcel.writeString(this.provider);
        parcel.writeString(this.ad_words);
        parcel.writeString(this.description);
        parcel.writeString(this.icon);
        parcel.writeString(this.url);
        parcel.writeInt(this.app_version_code);
        parcel.writeString(this.url_in_app);
        parcel.writeLong(this.size);
        parcel.writeString(this.app_package_name);
        parcel.writeString(this.app_version_name);
        parcel.writeInt(this.new_tip);
        parcel.writeInt(this.filterInstalledApp ? 1 : 0);
        if (this.imgs == null) {
            length = 0;
        } else {
            length = this.imgs.length;
        }
        parcel.writeInt(length);
        if (this.imgs == null) {
            strArr = new String[0];
        } else {
            strArr = this.imgs;
        }
        parcel.writeStringArray(strArr);
        parcel.writeString(this.prom_act_pams);
        parcel.writeString(this.price);
        parcel.writeString(this.promoterPrice);
        parcel.writeInt(this.sell);
        parcel.writeString(this.landing_size);
    }

    protected Promoter(Parcel parcel) {
        boolean z = false;
        this.app_package_name = "";
        this.new_tip = 0;
        this.prom_act_pams = "";
        this.slot_act_pams = "";
        this.filterInstalledApp = false;
        if (parcel != null) {
            this.promoter = parcel.readString();
            this.category = parcel.readInt();
            this.content_type = parcel.readInt();
            this.display_type = parcel.readInt();
            this.img = parcel.readString();
            this.image_type = parcel.readInt();
            this.anim_in = parcel.readInt();
            this.landing_type = parcel.readInt();
            this.text_font = parcel.readString();
            this.text_size = parcel.readString();
            this.text_color = parcel.readString();
            this.title = parcel.readString();
            this.provider = parcel.readString();
            this.ad_words = parcel.readString();
            this.description = parcel.readString();
            this.icon = parcel.readString();
            this.url = parcel.readString();
            this.app_version_code = parcel.readInt();
            this.url_in_app = parcel.readString();
            this.size = parcel.readLong();
            this.app_package_name = parcel.readString();
            this.app_version_name = parcel.readString();
            this.new_tip = parcel.readInt();
            this.filterInstalledApp = parcel.readInt() != 0 ? true : z;
            int readInt = parcel.readInt();
            String[] strArr = new String[readInt];
            parcel.readStringArray(strArr);
            if (readInt > 0) {
                this.imgs = strArr;
            }
            this.prom_act_pams = parcel.readString();
            this.price = parcel.readString();
            this.promoterPrice = parcel.readString();
            this.sell = parcel.readInt();
            this.landing_size = parcel.readString();
        }
    }

    public Promoter() {
        this.app_package_name = "";
        this.new_tip = 0;
        this.prom_act_pams = "";
        this.slot_act_pams = "";
        this.filterInstalledApp = false;
    }

    public Promoter(JSONObject jSONObject) {
        this.app_package_name = "";
        this.new_tip = 0;
        this.prom_act_pams = "";
        this.slot_act_pams = "";
        this.filterInstalledApp = false;
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        boolean z;
        if (jSONObject != null) {
            this.promoter = jSONObject.optString("promoter", "");
            this.category = jSONObject.optInt(f.aP, 0);
            this.content_type = jSONObject.optInt(f.aR);
            this.display_type = jSONObject.optInt(f.aW, 0);
            this.image_type = jSONObject.optInt(f.t, 0);
            this.img = jSONObject.optString("img", "");
            this.anim_in = jSONObject.optInt(f.ba, 0);
            this.landing_type = jSONObject.optInt(f.aU, 0);
            this.text_size = jSONObject.optString(f.bc, "");
            this.text_color = jSONObject.optString(f.be);
            this.text_font = jSONObject.optString(f.bd);
            this.title = jSONObject.optString("title", "");
            this.provider = jSONObject.optString("provider", "");
            this.ad_words = jSONObject.optString(f.aL, "");
            this.description = jSONObject.optString("description", "");
            if (jSONObject.optInt(f.m, 0) == 0) {
                z = false;
            } else {
                z = true;
            }
            this.filterInstalledApp = z;
            this.icon = jSONObject.optString(f.aY, "");
            this.url = jSONObject.optString("url", "");
            this.new_tip = jSONObject.optInt(f.bf, 0);
            this.app_version_code = jSONObject.optInt(f.bo, 0);
            this.url_in_app = jSONObject.optString(f.bb);
            this.size = jSONObject.optLong("size", 0L);
            this.app_package_name = jSONObject.optString(f.bm, "");
            this.app_version_name = jSONObject.optString(f.bn, "");
            this.prom_act_pams = jSONObject.optString(f.ah, "");
            this.price = jSONObject.optString(f.aS);
            this.promoterPrice = jSONObject.optString(f.Y, "");
            this.sell = jSONObject.optInt(f.ae);
            this.bid = jSONObject.optDouble(f.aZ, 0.0d);
            this.landing_size = jSONObject.optString(f.ac);
            try {
                if (jSONObject.has(f.bH)) {
                    String optString = jSONObject.optString(f.bH, "");
                    if (!TextUtils.isEmpty(optString)) {
                        this.imgs = optString.split("\\,");
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static <T extends Promoter> T getPromoterFromJson(JSONObject jSONObject, Class<T> cls) {
        try {
            return cls.getConstructor(JSONObject.class).newInstance(jSONObject);
        } catch (IllegalAccessException e) {
            g.d("IllegalAccessException", e);
            return null;
        } catch (IllegalArgumentException e2) {
            g.d("IllegalArgumentException", e2);
            return null;
        } catch (InstantiationException e3) {
            g.d("InstantiationException", e3);
            return null;
        } catch (NoSuchMethodException e4) {
            g.d("NoSuchMethodException", e4);
            return null;
        } catch (SecurityException e5) {
            g.d("SecurityException", e5);
            return null;
        } catch (InvocationTargetException e6) {
            g.d("InvocationTargetException", e6);
            return null;
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("promoter", this.promoter);
            jSONObject.put(f.aP, this.category);
            jSONObject.put(f.aR, this.content_type);
            jSONObject.put(f.aW, this.display_type);
            jSONObject.put("img", this.img);
            jSONObject.put(f.t, this.image_type);
            jSONObject.put(f.ba, this.anim_in);
            jSONObject.put(f.aW, this.display_type);
            jSONObject.put("img", this.img);
            jSONObject.put(f.aU, this.landing_type);
            jSONObject.put(f.bc, this.text_size);
            jSONObject.put(f.be, this.text_color);
            jSONObject.put(f.bd, this.text_font);
            jSONObject.put("title", this.title);
            jSONObject.put("provider", this.provider);
            jSONObject.put(f.aL, this.ad_words);
            jSONObject.put("description", this.description);
            jSONObject.put(f.aY, this.icon);
            jSONObject.put("url", this.url);
            jSONObject.put(f.bf, this.new_tip);
            jSONObject.put(f.bo, this.app_version_code);
            jSONObject.put(f.bb, this.url_in_app);
            jSONObject.put("size", this.size);
            jSONObject.put(f.bm, this.app_package_name);
            jSONObject.put(f.bn, this.app_version_name);
            jSONObject.put(f.aS, this.price);
            jSONObject.put(f.Y, this.promoterPrice);
            jSONObject.put(f.ae, this.sell);
            jSONObject.put(f.aZ, this.bid);
            jSONObject.put(f.ac, this.landing_size);
            if (this.imgs == null || this.imgs.length <= 0) {
                return jSONObject;
            }
            jSONObject.put(f.bH, Arrays.toString(this.imgs));
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public static Promoter buildMockPromoter() {
        return new Promoter();
    }

    public static Class<? extends Promoter> getAdapterPromoterClz(f fVar, e eVar) {
        if (e.TB_ITEM == eVar) {
            return a("com.taobao.newxp.TBItemPromoter");
        }
        if (e.TUAN == eVar) {
            return a("com.taobao.newxp.view.handler.UMTuanPromoter");
        }
        return Promoter.class;
    }

    private static Class<? extends Promoter> a(String str) {
        try {
            return Class.forName(str);
        } catch (Exception e) {
            Log.e(e.e, "can`t found the class " + str);
            return Promoter.class;
        }
    }
}
