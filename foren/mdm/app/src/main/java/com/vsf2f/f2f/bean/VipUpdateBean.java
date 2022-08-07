package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class VipUpdateBean implements Parcelable {
    public static final Parcelable.Creator<VipUpdateBean> CREATOR = new Parcelable.Creator<VipUpdateBean>() { // from class: com.vsf2f.f2f.bean.VipUpdateBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VipUpdateBean createFromParcel(Parcel in) {
            return new VipUpdateBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VipUpdateBean[] newArray(int size) {
            return new VipUpdateBean[size];
        }
    };
    private String exp;
    private String id;
    private int lv;
    private String lwm;
    private String name;

    protected VipUpdateBean(Parcel in) {
        this.lv = in.readInt();
        this.id = in.readString();
        this.exp = in.readString();
        this.lwm = in.readString();
        this.name = in.readString();
    }

    public String getExp() {
        return this.exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLv() {
        return this.lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public String getLwm() {
        return this.lwm;
    }

    public void setLwm(String lwm) {
        this.lwm = lwm;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.lv);
        dest.writeString(this.exp);
        dest.writeString(this.lwm);
    }
}
