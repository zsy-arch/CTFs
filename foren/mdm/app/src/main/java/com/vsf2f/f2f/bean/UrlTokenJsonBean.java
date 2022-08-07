package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class UrlTokenJsonBean implements Parcelable {
    public static final Parcelable.Creator<UrlTokenJsonBean> CREATOR = new Parcelable.Creator<UrlTokenJsonBean>() { // from class: com.vsf2f.f2f.bean.UrlTokenJsonBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UrlTokenJsonBean createFromParcel(Parcel in) {
            return new UrlTokenJsonBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UrlTokenJsonBean[] newArray(int size) {
            return new UrlTokenJsonBean[size];
        }
    };
    private String vs_access_token;
    private String vs_nonce_str;
    private String vs_request_token;
    private String vs_timestamp;

    public String getVs_nonce_str() {
        return this.vs_nonce_str;
    }

    public void setVs_nonce_str(String vs_nonce_str) {
        this.vs_nonce_str = vs_nonce_str;
    }

    public String getVs_access_token() {
        return this.vs_access_token;
    }

    public void setVs_access_token(String vs_access_token) {
        this.vs_access_token = vs_access_token;
    }

    public String getVs_request_token() {
        return this.vs_request_token;
    }

    public void setVs_request_token(String vs_request_token) {
        this.vs_request_token = vs_request_token;
    }

    public String getVs_timestamp() {
        return this.vs_timestamp;
    }

    public void setVs_timestamp(String vs_timestamp) {
        this.vs_timestamp = vs_timestamp;
    }

    public UrlTokenJsonBean() {
    }

    protected UrlTokenJsonBean(Parcel in) {
        this.vs_access_token = in.readString();
        this.vs_request_token = in.readString();
        this.vs_timestamp = in.readString();
        this.vs_nonce_str = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vs_access_token);
        dest.writeString(this.vs_request_token);
        dest.writeString(this.vs_timestamp);
        dest.writeString(this.vs_nonce_str);
    }
}
