package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ChatUserBean implements Parcelable {
    public static final Parcelable.Creator<ChatUserBean> CREATOR = new Parcelable.Creator<ChatUserBean>() { // from class: com.vsf2f.f2f.bean.ChatUserBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChatUserBean createFromParcel(Parcel in) {
            return new ChatUserBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChatUserBean[] newArray(int size) {
            return new ChatUserBean[size];
        }
    };
    private String ext;
    private String user;

    public ChatUserBean() {
    }

    protected ChatUserBean(Parcel in) {
        this.user = in.readString();
        this.ext = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.user);
        dest.writeString(this.ext);
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getExt() {
        return this.ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
