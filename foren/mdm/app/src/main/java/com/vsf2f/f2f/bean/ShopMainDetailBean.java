package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ShopMainDetailBean implements Parcelable {
    public static final Parcelable.Creator<ShopMainDetailBean> CREATOR = new Parcelable.Creator<ShopMainDetailBean>() { // from class: com.vsf2f.f2f.bean.ShopMainDetailBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopMainDetailBean createFromParcel(Parcel in) {
            return new ShopMainDetailBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShopMainDetailBean[] newArray(int size) {
            return new ShopMainDetailBean[size];
        }
    };
    private String alias;
    private boolean check;
    private String guid;
    private String id;
    private String name;

    protected ShopMainDetailBean(Parcel in) {
        this.id = in.readString();
        this.guid = in.readString();
        this.name = in.readString();
        this.alias = in.readString();
        this.check = in.readByte() != 0;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isCheck() {
        return this.check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
        dest.writeString(this.guid);
        dest.writeString(this.name);
        dest.writeString(this.alias);
        dest.writeByte((byte) (this.check ? 1 : 0));
    }
}
