package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes2.dex */
public class CircleReadBean extends CircleBaseBean implements Parcelable {
    public static final Parcelable.Creator<CircleReadBean> CREATOR = new Parcelable.Creator<CircleReadBean>() { // from class: com.vsf2f.f2f.bean.CircleReadBean.1
        @Override // android.os.Parcelable.Creator
        public CircleReadBean createFromParcel(Parcel source) {
            return new CircleReadBean(source);
        }

        @Override // android.os.Parcelable.Creator
        public CircleReadBean[] newArray(int size) {
            return new CircleReadBean[size];
        }
    };
    private boolean collection;
    private CircleBaseBean fwmi;
    private String herf;
    private boolean isShow;
    private boolean like;
    private CircleReadHostBean msgHost;
    private List<CircleReadPicListBean> picList;
    private String prevMsgId;
    private CirclePublisher publisher;
    private ShareThirdBean shareThird;
    private long time;
    private String udName;
    private CircleReadUserPic userPic;

    public CircleReadBean() {
        this.isShow = false;
    }

    public boolean isCollection() {
        return this.collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public CircleBaseBean getFwmi() {
        return this.fwmi;
    }

    public void setFwmi(CircleBaseBean fwmi) {
        this.fwmi = fwmi;
    }

    public boolean isLike() {
        return this.like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getPrevMsgId() {
        return this.prevMsgId;
    }

    public void setPrevMsgId(String prevMsgId) {
        this.prevMsgId = prevMsgId;
    }

    public ShareThirdBean getShareThird() {
        return this.shareThird;
    }

    public void setShareThird(ShareThirdBean shareThird) {
        this.shareThird = shareThird;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUdName() {
        return this.udName;
    }

    public void setUdName(String udName) {
        this.udName = udName;
    }

    public CircleReadUserPic getUserPic() {
        return this.userPic;
    }

    public void setUserPic(CircleReadUserPic userPic) {
        this.userPic = userPic;
    }

    public String getHerf() {
        return this.herf;
    }

    public void setHerf(String herf) {
        this.herf = herf;
    }

    public CircleReadHostBean getMsgHost() {
        return this.msgHost;
    }

    public CirclePublisher getPublisher() {
        if (this.publisher == null) {
            this.publisher = new CirclePublisher();
        }
        return this.publisher;
    }

    public void setPublisher(CirclePublisher publisher) {
        this.publisher = publisher;
    }

    public void setMsgHost(CircleReadHostBean msgHost) {
        this.msgHost = msgHost;
    }

    public List<CircleReadPicListBean> getPicList() {
        return this.picList;
    }

    public void setPicList(List<CircleReadPicListBean> picList) {
        this.picList = picList;
    }

    public boolean isShow() {
        return this.isShow;
    }

    public void setShow(boolean show) {
        this.isShow = show;
    }

    @Override // com.vsf2f.f2f.bean.CircleBaseBean
    public String toString() {
        return "CircleReadBean{collection=" + this.collection + ", time='" + this.time + "', udName='" + this.udName + "', prevMsgId='" + this.prevMsgId + "', like=" + this.like + ", userPic=" + this.userPic + ", msgHost=" + this.msgHost + ", picList=" + this.picList + ", fwmi=" + this.fwmi + ", shareThird=" + this.shareThird + '}';
    }

    @Override // com.vsf2f.f2f.bean.CircleBaseBean, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.vsf2f.f2f.bean.CircleBaseBean, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        byte b = 1;
        super.writeToParcel(dest, flags);
        dest.writeLong(this.time);
        dest.writeByte(this.like ? (byte) 1 : (byte) 0);
        dest.writeByte(this.collection ? (byte) 1 : (byte) 0);
        if (!this.isShow) {
            b = 0;
        }
        dest.writeByte(b);
        dest.writeString(this.herf);
        dest.writeString(this.udName);
        dest.writeString(this.prevMsgId);
        dest.writeParcelable(this.fwmi, flags);
        dest.writeParcelable(this.userPic, flags);
        dest.writeParcelable(this.msgHost, flags);
        dest.writeParcelable(this.shareThird, flags);
        dest.writeTypedList(this.picList);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CircleReadBean(Parcel in) {
        super(in);
        boolean z = true;
        this.isShow = false;
        this.time = in.readLong();
        this.like = in.readByte() != 0;
        this.collection = in.readByte() != 0;
        this.isShow = in.readByte() == 0 ? false : z;
        this.herf = in.readString();
        this.udName = in.readString();
        this.prevMsgId = in.readString();
        this.fwmi = (CircleBaseBean) in.readParcelable(CircleBaseBean.class.getClassLoader());
        this.userPic = (CircleReadUserPic) in.readParcelable(CircleReadUserPic.class.getClassLoader());
        this.msgHost = (CircleReadHostBean) in.readParcelable(CircleReadHostBean.class.getClassLoader());
        this.shareThird = (ShareThirdBean) in.readParcelable(ShareThirdBean.class.getClassLoader());
        this.picList = in.createTypedArrayList(CircleReadPicListBean.CREATOR);
    }
}
