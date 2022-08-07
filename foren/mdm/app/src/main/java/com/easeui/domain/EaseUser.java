package com.easeui.domain;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amap.api.maps.model.LatLng;
import com.easeui.utils.EaseCommonUtils;
import com.hyphenate.chat.EMContact;
import com.vsf2f.f2f.bean.FriendInfoBean;
import com.vsf2f.f2f.bean.FriendsListBean;
import com.vsf2f.f2f.bean.GroupMembersBean;
import com.vsf2f.f2f.bean.UserInfo;

/* loaded from: classes.dex */
public class EaseUser extends EMContact implements Cloneable, Parcelable {
    public static final Parcelable.Creator<EaseUser> CREATOR = new Parcelable.Creator<EaseUser>() { // from class: com.easeui.domain.EaseUser.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EaseUser createFromParcel(Parcel source) {
            return new EaseUser(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EaseUser[] newArray(int size) {
            return new EaseUser[size];
        }
    };
    protected String avatar;
    protected String friendsCount;
    protected int gender;
    private Bitmap headerBitmap;
    protected String initialLetter;
    protected long lastTime;
    protected String lat;
    protected LatLng latLng;
    protected String lng;
    protected String searchKey;
    protected int status;
    protected int visible;

    public EaseUser() {
    }

    public EaseUser(String username) {
        this.username = username;
    }

    public EaseUser(FriendsListBean.RowsBean rowsBean) {
        this.username = rowsBean.getFriendsUser();
        this.friendsCount = Integer.toString(rowsBean.getFriendsCount());
        this.nick = rowsBean.getFriendsNickname();
        this.avatar = rowsBean.getUserPic().getSpath();
        this.gender = rowsBean.getGender();
        this.visible = rowsBean.getVisible();
        this.lat = String.valueOf(rowsBean.getLat());
        this.lng = String.valueOf(rowsBean.getLng());
    }

    public EaseUser(String username, String nick, String avatar) {
        this.username = username;
        this.nick = nick != null ? nick : username;
        this.avatar = avatar;
    }

    public EaseUser(UserInfo info) {
        this.nick = info.getNickName();
        this.username = info.getUserName();
        this.avatar = info.getUserPic().getSpath();
    }

    public EaseUser(FriendInfoBean info) {
        this.username = info.getUserName() + "";
        if (!TextUtils.isEmpty(info.getFriendsNickname())) {
            this.nick = info.getFriendsNickname();
        } else if (!TextUtils.isEmpty(info.getNickName())) {
            this.nick = info.getNickName();
        } else {
            this.nick = info.getUserName();
        }
        if (info.getUserPic() != null) {
            this.avatar = info.getUserPic().getSpath();
        }
        this.friendsCount = info.getFriendsCount() + "";
    }

    public EaseUser(GroupMembersBean member) {
        this.username = member.getUserName();
        this.nick = member.getNickName();
        if (member.getUserPic() != null) {
            this.avatar = member.getUserPic().getSpath();
        }
    }

    public String getFriendsCount() {
        return this.friendsCount;
    }

    public void setFriendsCount(String friendsCount) {
        this.friendsCount = friendsCount;
    }

    @Override // com.hyphenate.chat.EMContact
    public String getNick() {
        return TextUtils.isEmpty(this.nick) ? getUsername() : this.nick;
    }

    public String getSearchKey() {
        return this.searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override // com.hyphenate.chat.EMContact
    public String getNickname() {
        return this.nick;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVisible() {
        return this.visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public LatLng getLatLng() {
        if (TextUtils.isEmpty(this.lat) || TextUtils.isEmpty(this.lng)) {
            return null;
        }
        this.latLng = new LatLng(Double.valueOf(getLat()).doubleValue(), Double.valueOf(getLng()).doubleValue());
        return this.latLng;
    }

    public String getInitialLetter() {
        return this.initialLetter == null ? EaseCommonUtils.setUserInitialLetter(getNick()) : this.initialLetter;
    }

    public void setInitialLetter(String initialLetter) {
        this.initialLetter = initialLetter;
    }

    public Bitmap getHeaderBitmap() {
        return this.headerBitmap;
    }

    public void setHeaderBitmap(Bitmap headerBitmap) {
        this.headerBitmap = headerBitmap;
    }

    public int hashCode() {
        return getUsername().hashCode() * 17;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof EaseUser)) {
            return false;
        }
        return getUsername().equals(((EaseUser) o).getUsername());
    }

    @Override // com.hyphenate.chat.EMContact
    public String toString() {
        return this.nick == null ? this.username : this.nick;
    }

    public Object clone() {
        try {
            return (EaseUser) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hyphenate.chat.EMContact, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.hyphenate.chat.EMContact, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.nick);
        dest.writeString(this.initialLetter);
        dest.writeString(this.avatar);
        dest.writeString(this.friendsCount);
        dest.writeInt(this.visible);
        dest.writeInt(this.status);
        dest.writeInt(this.gender);
        dest.writeLong(this.lastTime);
        dest.writeString(this.searchKey);
        dest.writeString(this.lng);
        dest.writeString(this.lat);
        dest.writeParcelable(this.latLng, flags);
    }

    protected EaseUser(Parcel in) {
        this.username = in.readString();
        this.nick = in.readString();
        this.initialLetter = in.readString();
        this.avatar = in.readString();
        this.friendsCount = in.readString();
        this.visible = in.readInt();
        this.status = in.readInt();
        this.gender = in.readInt();
        this.lastTime = in.readLong();
        this.searchKey = in.readString();
        this.lng = in.readString();
        this.lat = in.readString();
        this.latLng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
    }
}
