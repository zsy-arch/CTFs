package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ShareCircleInfo extends CircleReadBean implements Parcelable {
    public static final Parcelable.Creator<ShareCircleInfo> CREATOR = new Parcelable.Creator<ShareCircleInfo>() { // from class: com.vsf2f.f2f.bean.ShareCircleInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareCircleInfo createFromParcel(Parcel source) {
            return new ShareCircleInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareCircleInfo[] newArray(int size) {
            return new ShareCircleInfo[size];
        }
    };
    private String coverPicId;
    private String pic;
    private String publishTimeStr;

    public ShareCircleInfo() {
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCoverPicId() {
        return this.coverPicId;
    }

    public void setCoverPicId(String coverPicId) {
        this.coverPicId = coverPicId;
    }

    public String getPublishTimeStr() {
        return this.publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
    }

    @Override // com.vsf2f.f2f.bean.CircleReadBean, com.vsf2f.f2f.bean.CircleBaseBean, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.vsf2f.f2f.bean.CircleReadBean, com.vsf2f.f2f.bean.CircleBaseBean, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.pic);
        dest.writeString(this.coverPicId);
        dest.writeString(this.publishTimeStr);
    }

    protected ShareCircleInfo(Parcel in) {
        super(in);
        this.pic = in.readString();
        this.coverPicId = in.readString();
        this.publishTimeStr = in.readString();
    }
}
