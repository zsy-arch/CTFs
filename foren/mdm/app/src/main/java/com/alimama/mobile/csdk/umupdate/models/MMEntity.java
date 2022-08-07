package com.alimama.mobile.csdk.umupdate.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MMEntity extends a implements Parcelable, Cloneable {
    public static Parcelable.Creator<MMEntity> CREATOR = new Parcelable.Creator<MMEntity>() { // from class: com.alimama.mobile.csdk.umupdate.models.MMEntity.1
        /* renamed from: a */
        public MMEntity createFromParcel(Parcel parcel) {
            return new MMEntity(parcel);
        }

        /* renamed from: a */
        public MMEntity[] newArray(int i) {
            return new MMEntity[i];
        }
    };
    protected int a;
    protected long b;
    protected boolean c;
    public int cache;
    public int displayStyle;
    public String displayType;
    public HashMap<String, Object> ecomparms;
    public String entryStr;
    public long expire;
    public boolean filterInstalledApp;
    public int image_type;
    public String iscache;
    public String ispreload;
    public String landingUrl;
    public String landing_image;
    public String landing_size;
    public d module;
    public int newTips;
    public String new_image;
    public String opensize;
    public int preload;
    public int sid_expired;
    public long[] timeline;
    public boolean wallSwitch;

    public MMEntity(String str) {
        this.module = d.UMENG;
        this.opensize = "";
        this.landing_image = "";
        this.landingUrl = "";
        this.new_image = "";
        this.image_type = 0;
        this.displayStyle = 0;
        this.displayType = "bigImg";
        this.newTips = -1;
        this.cache = -1;
        this.filterInstalledApp = true;
        this.wallSwitch = false;
        this.expire = 0L;
        this.sid_expired = 1;
        this.entryStr = "";
        this.timeline = new long[4];
        this.ecomparms = new HashMap<>();
        this.a = 0;
        this.b = -1L;
        this.c = false;
        this.ispreload = "";
        this.preload = 0;
        this.iscache = "";
        this.slotId = str;
        this.layoutType = 17;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private MMEntity(Parcel parcel) {
        super(parcel);
        boolean z = true;
        this.module = d.UMENG;
        this.opensize = "";
        this.landing_image = "";
        this.landingUrl = "";
        this.new_image = "";
        this.image_type = 0;
        this.displayStyle = 0;
        this.displayType = "bigImg";
        this.newTips = -1;
        this.cache = -1;
        this.filterInstalledApp = true;
        this.wallSwitch = false;
        this.expire = 0L;
        this.sid_expired = 1;
        this.entryStr = "";
        this.timeline = new long[4];
        this.ecomparms = new HashMap<>();
        this.a = 0;
        this.b = -1L;
        this.c = false;
        this.ispreload = "";
        this.preload = 0;
        this.iscache = "";
        int readInt = parcel.readInt();
        this.module = readInt == -1 ? null : d.values()[readInt];
        this.opensize = parcel.readString();
        this.landing_image = parcel.readString();
        this.landingUrl = parcel.readString();
        this.new_image = parcel.readString();
        this.image_type = parcel.readInt();
        this.displayStyle = parcel.readInt();
        this.displayType = parcel.readString();
        this.newTips = parcel.readInt();
        this.cache = parcel.readInt();
        this.filterInstalledApp = parcel.readByte() != 0;
        this.wallSwitch = parcel.readByte() != 0;
        this.a = parcel.readInt();
        this.b = parcel.readLong();
        this.c = parcel.readByte() == 0 ? false : z;
        this.expire = parcel.readLong();
        this.sid_expired = parcel.readInt();
        this.entryStr = parcel.readString();
        this.ispreload = parcel.readString();
        this.preload = parcel.readInt();
        this.iscache = parcel.readString();
        this.landing_size = parcel.readString();
        this.timeline = parcel.createLongArray();
        this.ecomparms = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    public String getTimeConsuming() {
        if (this.timeline != null && this.timeline.length == 4 && this.timeline[0] > 0) {
            long j = this.timeline[0];
            long j2 = this.timeline[1];
            long j3 = j2 - j;
            long j4 = this.timeline[3] - this.timeline[2];
            if (j3 > 0) {
                return j3 + "_" + j4;
            }
        }
        return null;
    }

    @Override // com.alimama.mobile.csdk.umupdate.models.a
    public void warp(JSONObject jSONObject) {
        super.warp(jSONObject);
        this.landing_size = jSONObject.optString(f.ac);
        this.filterInstalledApp = jSONObject.optInt(f.m, 1) == 1;
        this.cache = jSONObject.optInt("cache", -1);
        this.sid_expired = jSONObject.optInt(f.S, 1);
        this.expire = jSONObject.optLong(f.T, 0L);
        this.landing_image = jSONObject.optString(f.r, "");
        this.landingUrl = jSONObject.optString(f.ar, "");
        this.new_image = jSONObject.optString(f.s, "");
        this.displayType = jSONObject.optString(f.aW, "bigImg");
        String optString = jSONObject.optString(f.aj, "");
        if (!TextUtils.isEmpty(optString)) {
            d a = d.a(optString);
            if (a == null) {
                a = d.UMENG;
            }
            this.module = a;
        }
        this.image_type = jSONObject.optInt(f.t, 0);
        JSONArray optJSONArray = jSONObject.optJSONArray("walls");
        if (optJSONArray != null && optJSONArray.length() > 1) {
            this.wallSwitch = true;
        }
        if (jSONObject.has("opensize")) {
            try {
                this.opensize = "" + (Float.parseFloat(jSONObject.getString("opensize")) / 100.0f);
            } catch (Exception e) {
            }
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(f.aG);
        if (optJSONObject != null) {
            this.a = optJSONObject.optInt(f.aH);
            this.b = optJSONObject.optInt(f.aI) * 60 * 60 * 1000;
        } else {
            this.a = 0;
            this.b = -1L;
        }
        this.newTips = jSONObject.optInt(f.aF, -1);
        this.entryStr = jSONObject.optString(f.f3u, "");
        this.ispreload = jSONObject.optString(f.v, "");
        this.preload = jSONObject.optInt(f.q, 0);
        this.iscache = jSONObject.optString(f.w, "");
    }

    public void extendFields(MMEntity mMEntity) {
        this.tabId = mMEntity.tabId;
        this.slot_act_params = mMEntity.slot_act_params;
        this.urlParams = mMEntity.urlParams;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // com.alimama.mobile.csdk.umupdate.models.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.alimama.mobile.csdk.umupdate.models.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        byte b = 1;
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.module == null ? -1 : this.module.ordinal());
        parcel.writeString(this.opensize);
        parcel.writeString(this.landing_image);
        parcel.writeString(this.landingUrl);
        parcel.writeString(this.new_image);
        parcel.writeInt(this.image_type);
        parcel.writeInt(this.displayStyle);
        parcel.writeString(this.displayType);
        parcel.writeInt(this.newTips);
        parcel.writeInt(this.cache);
        parcel.writeByte(this.filterInstalledApp ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.wallSwitch ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.a);
        parcel.writeLong(this.b);
        if (!this.c) {
            b = 0;
        }
        parcel.writeByte(b);
        parcel.writeLong(this.expire);
        parcel.writeInt(this.sid_expired);
        parcel.writeString(this.entryStr);
        parcel.writeString(this.ispreload);
        parcel.writeInt(this.preload);
        parcel.writeString(this.iscache);
        parcel.writeString(this.landing_size);
        parcel.writeLongArray(this.timeline);
        parcel.writeMap(this.ecomparms);
    }
}
