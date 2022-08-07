package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class UserBalanceBean implements Parcelable {
    public static final Parcelable.Creator<UserBalanceBean> CREATOR = new Parcelable.Creator<UserBalanceBean>() { // from class: com.vsf2f.f2f.bean.UserBalanceBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserBalanceBean createFromParcel(Parcel in) {
            return new UserBalanceBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserBalanceBean[] newArray(int size) {
            return new UserBalanceBean[size];
        }
    };
    private String cash;
    private String vm;

    protected UserBalanceBean(Parcel in) {
        this.vm = in.readString();
        this.cash = in.readString();
    }

    public String getCash() {
        return this.cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getVm() {
        return this.vm;
    }

    public void setVm(String vm) {
        this.vm = vm;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vm);
        dest.writeString(this.cash);
    }
}
