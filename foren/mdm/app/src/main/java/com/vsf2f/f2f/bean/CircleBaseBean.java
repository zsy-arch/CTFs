package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public class CircleBaseBean implements Parcelable {
    public static final Parcelable.Creator<CircleBaseBean> CREATOR = new Parcelable.Creator<CircleBaseBean>() { // from class: com.vsf2f.f2f.bean.CircleBaseBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleBaseBean createFromParcel(Parcel source) {
            return new CircleBaseBean(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleBaseBean[] newArray(int size) {
            return new CircleBaseBean[size];
        }
    };
    private String content;
    private CircleReadUserPic coverPic;
    private CircleExtInfo extInfo;
    private String msgId;
    private String nickName;
    private String title;
    private int type;
    private String userName;

    public CircleBaseBean() {
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return TextUtils.isEmpty(this.content) ? "" : this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getNickName() {
        return TextUtils.isEmpty(this.nickName) ? this.userName : this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CircleExtInfo getExtInfo() {
        return this.extInfo;
    }

    public void setExtInfo(CircleExtInfo extInfo) {
        this.extInfo = extInfo;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CircleReadUserPic getCoverPic() {
        if (this.coverPic == null) {
            this.coverPic = new CircleReadUserPic();
        }
        return this.coverPic;
    }

    public void setCoverPic(CircleReadUserPic coverPic) {
        this.coverPic = coverPic;
    }

    public String toString() {
        return "CircleBaseBean{content='" + this.content + "', msgId='" + this.msgId + "', title='" + this.title + "', coverPic=" + this.coverPic + ", nickName='" + this.nickName + "'}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.msgId);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.userName);
        dest.writeString(this.nickName);
        dest.writeParcelable(this.extInfo, flags);
        dest.writeParcelable(this.coverPic, flags);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CircleBaseBean(Parcel in) {
        this.type = in.readInt();
        this.msgId = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.userName = in.readString();
        this.nickName = in.readString();
        this.extInfo = (CircleExtInfo) in.readParcelable(CircleExtInfo.class.getClassLoader());
        this.coverPic = (CircleReadUserPic) in.readParcelable(CircleReadUserPic.class.getClassLoader());
    }
}
