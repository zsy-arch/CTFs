package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public class CircleCommentBean implements Parcelable {
    public static final Parcelable.Creator<CircleCommentBean> CREATOR = new Parcelable.Creator<CircleCommentBean>() { // from class: com.vsf2f.f2f.bean.CircleCommentBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleCommentBean createFromParcel(Parcel in) {
            return new CircleCommentBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleCommentBean[] newArray(int size) {
            return new CircleCommentBean[size];
        }
    };
    private String likeCount;
    private String msgContent;
    private String msgId;
    private String nickName;
    private long publishTime;
    private String replyNickName;
    private String replyUserName;
    private String userName;
    private PicBean userPic;

    public CircleCommentBean() {
    }

    protected CircleCommentBean(Parcel in) {
        this.userPic = (PicBean) in.readParcelable(PicBean.class.getClassLoader());
        this.likeCount = in.readString();
        this.msgId = in.readString();
        this.userName = in.readString();
        this.nickName = in.readString();
        this.msgContent = in.readString();
        this.replyUserName = in.readString();
        this.replyNickName = in.readString();
        this.publishTime = in.readLong();
    }

    public String getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getMsgContent() {
        return this.msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
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

    public String getReplyUserName() {
        return this.replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getReplyNickName() {
        return TextUtils.isEmpty(this.replyNickName) ? this.replyUserName : this.replyNickName;
    }

    public void setReplyNickName(String replyNickName) {
        this.replyNickName = replyNickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PicBean getUserPic() {
        return this.userPic;
    }

    public void setUserPic(PicBean userPic) {
        this.userPic = userPic;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.likeCount);
        dest.writeString(this.msgId);
        dest.writeString(this.userName);
        dest.writeString(this.nickName);
        dest.writeString(this.msgContent);
        dest.writeString(this.replyUserName);
        dest.writeString(this.replyNickName);
        dest.writeLong(this.publishTime);
        dest.writeParcelable(this.userPic, flags);
    }
}
