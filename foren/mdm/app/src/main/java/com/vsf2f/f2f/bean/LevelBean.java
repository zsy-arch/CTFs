package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class LevelBean implements Parcelable {
    public static final Parcelable.Creator<LevelBean> CREATOR = new Parcelable.Creator<LevelBean>() { // from class: com.vsf2f.f2f.bean.LevelBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LevelBean createFromParcel(Parcel in) {
            return new LevelBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LevelBean[] newArray(int size) {
            return new LevelBean[size];
        }
    };
    private String AllInvestorMoney;
    private String AllProfitMoney;
    private int AllUnderCount;
    private boolean IsInvestment;
    private LevelUnderBean Under;

    protected LevelBean(Parcel in) {
        this.AllInvestorMoney = in.readString();
        this.AllProfitMoney = in.readString();
        this.AllUnderCount = in.readInt();
        this.IsInvestment = in.readByte() != 0;
        this.Under = (LevelUnderBean) in.readParcelable(LevelUnderBean.class.getClassLoader());
    }

    public String getAllInvestorMoney() {
        return this.AllInvestorMoney;
    }

    public void setAllInvestorMoney(String allInvestorMoney) {
        this.AllInvestorMoney = allInvestorMoney;
    }

    public String getAllProfitMoney() {
        return this.AllProfitMoney;
    }

    public void setAllProfitMoney(String allProfitMoney) {
        this.AllProfitMoney = allProfitMoney;
    }

    public int getAllUnderCount() {
        return this.AllUnderCount;
    }

    public void setAllUnderCount(int allUnderCount) {
        this.AllUnderCount = allUnderCount;
    }

    public boolean isInvestment() {
        return this.IsInvestment;
    }

    public void setIsInvestment(boolean isInvestment) {
        this.IsInvestment = isInvestment;
    }

    public LevelUnderBean getUnder() {
        return this.Under;
    }

    public void setUnder(LevelUnderBean under) {
        this.Under = under;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AllInvestorMoney);
        dest.writeString(this.AllProfitMoney);
        dest.writeInt(this.AllUnderCount);
        dest.writeByte((byte) (this.IsInvestment ? 1 : 0));
        dest.writeParcelable(this.Under, flags);
    }
}
