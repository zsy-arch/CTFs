package com.hyphenate.chat;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class EMContact implements Parcelable {
    public static final Parcelable.Creator<EMContact> CREATOR = new Parcelable.Creator<EMContact>() { // from class: com.hyphenate.chat.EMContact.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMContact createFromParcel(Parcel parcel) {
            return new EMContact(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EMContact[] newArray(int i) {
            return new EMContact[i];
        }
    };
    protected String nick;
    protected String username;

    /* JADX INFO: Access modifiers changed from: protected */
    public EMContact() {
    }

    private EMContact(Parcel parcel) {
        this.username = parcel.readString();
    }

    public EMContact(String str) {
        this.username = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getNick() {
        return getNickname();
    }

    public String getNickname() {
        return this.nick == null ? getUsername() : this.nick;
    }

    public String getUsername() {
        return this.username;
    }

    @Deprecated
    public void setNick(String str) {
        this.nick = str;
    }

    public void setNickname(String str) {
        this.nick = str;
    }

    public String toString() {
        return "<contact , username:" + this.username + ">";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.username);
    }
}
