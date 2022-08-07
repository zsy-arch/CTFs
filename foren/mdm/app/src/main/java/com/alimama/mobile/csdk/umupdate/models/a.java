package com.alimama.mobile.csdk.umupdate.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alimama.mobile.csdk.umupdate.a.g;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BaseMMEntity.java */
/* loaded from: classes.dex */
public class a implements Parcelable {
    public String appkey;
    public int autofill;
    public String filterPromoter;
    public String keywords;
    public int landing_type;
    public int layoutType;
    public int page_index;
    public String psid;
    public e resType;
    public String sid;
    public String slotId;
    public String slot_act_params;
    public String tabId;
    public String tag;
    public f template;
    public String templateAttrs;
    public String urlParams;

    public void clear() {
        this.psid = "";
        this.sid = "";
        this.resType = null;
        this.template = null;
        this.slot_act_params = "";
        this.urlParams = "";
        this.templateAttrs = "";
    }

    public void warp(JSONObject jSONObject) {
        try {
            this.sid = jSONObject.optString(f.o, "");
            this.psid = jSONObject.optString(f.ai, "");
            this.urlParams = jSONObject.optString(f.X, this.urlParams);
            if (jSONObject.has(f.bg)) {
                this.template = f.a(jSONObject.getString(f.bg));
            }
            if (jSONObject.has(f.af)) {
                e a = e.a(jSONObject.optString(f.af, e.APP.toString()));
                if (a == null) {
                    a = e.APP;
                }
                this.resType = a;
            }
            this.slot_act_params = jSONObject.optString(f.ah, "");
        } catch (JSONException e) {
            g.a(e, "Parse json error", new Object[0]);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int ordinal;
        int i2 = -1;
        parcel.writeString(this.slotId);
        parcel.writeString(this.appkey);
        parcel.writeInt(this.autofill);
        parcel.writeInt(this.layoutType);
        parcel.writeString(this.keywords);
        parcel.writeString(this.tabId);
        parcel.writeString(this.filterPromoter);
        parcel.writeString(this.tag);
        parcel.writeInt(this.landing_type);
        parcel.writeString(this.sid);
        parcel.writeString(this.psid);
        if (this.resType == null) {
            ordinal = -1;
        } else {
            ordinal = this.resType.ordinal();
        }
        parcel.writeInt(ordinal);
        if (this.template != null) {
            i2 = this.template.ordinal();
        }
        parcel.writeInt(i2);
        parcel.writeString(this.templateAttrs);
        parcel.writeString(this.urlParams);
        parcel.writeString(this.slot_act_params);
        parcel.writeInt(this.page_index);
    }

    public a() {
        this.autofill = 1;
        this.layoutType = -1;
        this.keywords = "";
        this.tabId = "";
        this.filterPromoter = "";
        this.tag = "";
        this.landing_type = 0;
        this.sid = "";
        this.psid = "";
        this.slot_act_params = "";
        this.page_index = -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(Parcel parcel) {
        e eVar;
        f fVar = null;
        this.autofill = 1;
        this.layoutType = -1;
        this.keywords = "";
        this.tabId = "";
        this.filterPromoter = "";
        this.tag = "";
        this.landing_type = 0;
        this.sid = "";
        this.psid = "";
        this.slot_act_params = "";
        this.page_index = -1;
        this.slotId = parcel.readString();
        this.appkey = parcel.readString();
        this.autofill = parcel.readInt();
        this.layoutType = parcel.readInt();
        this.keywords = parcel.readString();
        this.tabId = parcel.readString();
        this.filterPromoter = parcel.readString();
        this.tag = parcel.readString();
        this.landing_type = parcel.readInt();
        this.sid = parcel.readString();
        this.psid = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt == -1) {
            eVar = null;
        } else {
            eVar = e.values()[readInt];
        }
        this.resType = eVar;
        int readInt2 = parcel.readInt();
        this.template = readInt2 != -1 ? f.values()[readInt2] : fVar;
        this.templateAttrs = parcel.readString();
        this.urlParams = parcel.readString();
        this.slot_act_params = parcel.readString();
        this.page_index = parcel.readInt();
    }
}
