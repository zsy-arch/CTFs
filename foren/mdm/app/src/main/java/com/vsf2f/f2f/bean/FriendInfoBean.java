package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class FriendInfoBean implements Parcelable {
    public static final Parcelable.Creator<FriendInfoBean> CREATOR = new Parcelable.Creator<FriendInfoBean>() { // from class: com.vsf2f.f2f.bean.FriendInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FriendInfoBean createFromParcel(Parcel in) {
            return new FriendInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FriendInfoBean[] newArray(int size) {
            return new FriendInfoBean[size];
        }
    };
    private String age;
    private String content;
    private int customersId;
    private String email;
    private String firstKey;
    private int friendsCount;
    private long friendsJointime;
    private String friendsNickname;
    private int friendsStatus;
    private int gender;
    private int imStatus;
    private String imUuid;
    private int level;
    private String nickName;
    private int parentId;
    private String phone;
    private int pictureId;
    private int secondFriendsCount;
    private String userName;
    private UserPicBean userPic;

    protected FriendInfoBean(Parcel in) {
        this.customersId = in.readInt();
        this.imUuid = in.readString();
        this.imStatus = in.readInt();
        this.parentId = in.readInt();
        this.age = in.readString();
        this.email = in.readString();
        this.level = in.readInt();
        this.pictureId = in.readInt();
        this.nickName = in.readString();
        this.phone = in.readString();
        this.gender = in.readInt();
        this.userName = in.readString();
        this.firstKey = in.readString();
        this.friendsNickname = in.readString();
        this.friendsStatus = in.readInt();
        this.friendsJointime = in.readLong();
        this.friendsCount = in.readInt();
        this.secondFriendsCount = in.readInt();
        this.content = in.readString();
        this.userPic = (UserPicBean) in.readParcelable(UserPicBean.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.customersId);
        dest.writeString(this.imUuid);
        dest.writeInt(this.imStatus);
        dest.writeInt(this.parentId);
        dest.writeInt(this.level);
        dest.writeString(this.age);
        dest.writeInt(this.pictureId);
        dest.writeString(this.nickName);
        dest.writeString(this.phone);
        dest.writeInt(this.gender);
        dest.writeString(this.userName);
        dest.writeString(this.email);
        dest.writeString(this.firstKey);
        dest.writeString(this.friendsNickname);
        dest.writeInt(this.friendsStatus);
        dest.writeLong(this.friendsJointime);
        dest.writeInt(this.friendsCount);
        dest.writeInt(this.secondFriendsCount);
        dest.writeString(this.content);
        dest.writeParcelable(this.userPic, flags);
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCustomersId() {
        return this.customersId;
    }

    public void setCustomersId(int customersId) {
        this.customersId = customersId;
    }

    public String getImUuid() {
        return this.imUuid;
    }

    public void setImUuid(String imUuid) {
        this.imUuid = imUuid;
    }

    public int getImStatus() {
        return this.imStatus;
    }

    public void setImStatus(int imStatus) {
        this.imStatus = imStatus;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public UserPicBean getUserPic() {
        if (this.userPic == null) {
            this.userPic = new UserPicBean();
        }
        return this.userPic;
    }

    public void setUserPic(UserPicBean userPic) {
        this.userPic = userPic;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstKey() {
        return this.firstKey;
    }

    public void setFirstKey(String firstKey) {
        this.firstKey = firstKey;
    }

    public String getFriendsNickname() {
        return this.friendsNickname;
    }

    public void setFriendsNickname(String friendsNickname) {
        this.friendsNickname = friendsNickname;
    }

    public int getFriendsStatus() {
        return this.friendsStatus;
    }

    public void setFriendsStatus(int friendsStatus) {
        this.friendsStatus = friendsStatus;
    }

    public long getFriendsJointime() {
        return this.friendsJointime;
    }

    public void setFriendsJointime(long friendsJointime) {
        this.friendsJointime = friendsJointime;
    }

    public int getFriendsCount() {
        return this.friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public int getSecondFriendsCount() {
        return this.secondFriendsCount;
    }

    public void setSecondFriendsCount(int secondFriendsCount) {
        this.secondFriendsCount = secondFriendsCount;
    }
}
