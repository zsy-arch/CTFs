package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes2.dex */
public class MysixContactsBean implements Parcelable {
    public static final Parcelable.Creator<MysixContactsBean> CREATOR = new Parcelable.Creator<MysixContactsBean>() { // from class: com.vsf2f.f2f.bean.MysixContactsBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MysixContactsBean createFromParcel(Parcel in) {
            return new MysixContactsBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MysixContactsBean[] newArray(int size) {
            return new MysixContactsBean[size];
        }
    };
    private String AllInvestorMoney;
    private String AllProfitMoney;
    private String AllUnderCount;
    private boolean IsInvestment;
    private List<MysixContactsListBean> Under;

    protected MysixContactsBean(Parcel in) {
        this.AllInvestorMoney = in.readString();
        this.AllProfitMoney = in.readString();
        this.AllUnderCount = in.readString();
        this.IsInvestment = in.readByte() != 0;
        this.Under = in.createTypedArrayList(MysixContactsListBean.CREATOR);
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

    public String getAllUnderCount() {
        return this.AllUnderCount;
    }

    public void setAllUnderCount(String allUnderCount) {
        this.AllUnderCount = allUnderCount;
    }

    public boolean isInvestment() {
        return this.IsInvestment;
    }

    public void setIsInvestment(boolean isInvestment) {
        this.IsInvestment = isInvestment;
    }

    public List<MysixContactsListBean> getUnder() {
        return this.Under;
    }

    public void setUnder(List<MysixContactsListBean> under) {
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
        dest.writeString(this.AllUnderCount);
        dest.writeByte((byte) (this.IsInvestment ? 1 : 0));
        dest.writeTypedList(this.Under);
    }
}
