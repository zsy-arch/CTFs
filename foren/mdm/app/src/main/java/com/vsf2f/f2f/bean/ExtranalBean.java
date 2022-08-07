package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ExtranalBean implements Parcelable {
    public static final Parcelable.Creator<ExtranalBean> CREATOR = new Parcelable.Creator<ExtranalBean>() { // from class: com.vsf2f.f2f.bean.ExtranalBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExtranalBean createFromParcel(Parcel in) {
            return new ExtranalBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExtranalBean[] newArray(int size) {
            return new ExtranalBean[size];
        }
    };
    private String fromTimeStr;
    private String profitMoney;
    private String recomName;
    private PicBean recomPic;
    private String recomUserName;
    private String wgdMoney;

    protected ExtranalBean(Parcel in) {
        this.wgdMoney = in.readString();
        this.profitMoney = in.readString();
        this.fromTimeStr = in.readString();
        this.recomName = in.readString();
        this.recomUserName = in.readString();
        this.recomPic = (PicBean) in.readParcelable(PicBean.class.getClassLoader());
    }

    public String getFromTimeStr() {
        return this.fromTimeStr;
    }

    public void setFromTimeStr(String fromTimeStr) {
        this.fromTimeStr = fromTimeStr;
    }

    public String getProfitMoney() {
        return this.profitMoney;
    }

    public void setProfitMoney(String profitMoney) {
        this.profitMoney = profitMoney;
    }

    public String getRecomName() {
        return this.recomName;
    }

    public void setRecomName(String recomName) {
        this.recomName = recomName;
    }

    public PicBean getRecomPic() {
        return this.recomPic;
    }

    public void setRecomPic(PicBean recomPic) {
        this.recomPic = recomPic;
    }

    public String getRecomUserName() {
        return this.recomUserName;
    }

    public void setRecomUserName(String recomUserName) {
        this.recomUserName = recomUserName;
    }

    public String getWgdMoney() {
        return this.wgdMoney;
    }

    public void setWgdMoney(String wgdMoney) {
        this.wgdMoney = wgdMoney;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.wgdMoney);
        dest.writeString(this.profitMoney);
        dest.writeString(this.fromTimeStr);
        dest.writeString(this.recomName);
        dest.writeString(this.recomUserName);
    }
}
