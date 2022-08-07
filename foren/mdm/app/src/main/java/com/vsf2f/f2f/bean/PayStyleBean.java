package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class PayStyleBean implements Parcelable {
    public static final Parcelable.Creator<PayStyleBean> CREATOR = new Parcelable.Creator<PayStyleBean>() { // from class: com.vsf2f.f2f.bean.PayStyleBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PayStyleBean createFromParcel(Parcel in) {
            return new PayStyleBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PayStyleBean[] newArray(int size) {
            return new PayStyleBean[size];
        }
    };
    private String bankCode;
    private String bankName;
    private String bankNickName;
    private String bankNumber;
    private long createdAt;
    private int id;
    private int investorId;
    private int isDefault;
    private int status;
    private int type;
    private long updatedAt;

    protected PayStyleBean(Parcel in) {
        this.id = in.readInt();
        this.type = in.readInt();
        this.status = in.readInt();
        this.isDefault = in.readInt();
        this.investorId = in.readInt();
        this.bankName = in.readString();
        this.bankCode = in.readString();
        this.bankNumber = in.readString();
        this.bankNickName = in.readString();
        this.createdAt = in.readLong();
        this.updatedAt = in.readLong();
    }

    public PayStyleBean(String nickName) {
        this.bankNickName = nickName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvestorId() {
        return this.investorId;
    }

    public void setInvestorId(int investorId) {
        this.investorId = investorId;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankNumber() {
        return this.bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankNickName() {
        return this.bankNickName;
    }

    public void setBankNickName(String bankNickName) {
        this.bankNickName = bankNickName;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.type);
        dest.writeInt(this.status);
        dest.writeInt(this.isDefault);
        dest.writeInt(this.investorId);
        dest.writeString(this.bankName);
        dest.writeString(this.bankCode);
        dest.writeString(this.bankNumber);
        dest.writeString(this.bankNickName);
        dest.writeLong(this.createdAt);
        dest.writeLong(this.updatedAt);
    }
}
