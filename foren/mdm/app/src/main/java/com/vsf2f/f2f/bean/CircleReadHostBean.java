package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CircleReadHostBean implements Parcelable {
    public static final Parcelable.Creator<CircleReadHostBean> CREATOR = new Parcelable.Creator<CircleReadHostBean>() { // from class: com.vsf2f.f2f.bean.CircleReadHostBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleReadHostBean createFromParcel(Parcel in) {
            return new CircleReadHostBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleReadHostBean[] newArray(int size) {
            return new CircleReadHostBean[size];
        }
    };
    private String collection;
    private String comment;
    private String forward;
    private String like;
    private String msgId;
    private String read;

    public CircleReadHostBean() {
    }

    protected CircleReadHostBean(Parcel in) {
        this.forward = in.readString();
        this.comment = in.readString();
        this.collection = in.readString();
        this.like = in.readString();
        this.read = in.readString();
        this.msgId = in.readString();
    }

    public String getCollection() {
        return this.collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getForward() {
        return this.forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getLike() {
        return this.like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getRead() {
        return this.read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.forward);
        dest.writeString(this.comment);
        dest.writeString(this.collection);
        dest.writeString(this.like);
        dest.writeString(this.read);
        dest.writeString(this.msgId);
    }

    public String toString() {
        return "CircleReadHostBean{collection='" + this.collection + "', forward='" + this.forward + "', comment='" + this.comment + "', like='" + this.like + "', read='" + this.read + "', msgId='" + this.msgId + "'}";
    }
}
