package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class ShareThirdBean implements Parcelable, Serializable {
    public static final Parcelable.Creator<ShareThirdBean> CREATOR = new Parcelable.Creator<ShareThirdBean>() { // from class: com.vsf2f.f2f.bean.ShareThirdBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareThirdBean createFromParcel(Parcel in) {
            return new ShareThirdBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareThirdBean[] newArray(int size) {
            return new ShareThirdBean[size];
        }
    };
    private String context;
    private String pic;
    private String title;
    private String url;

    protected ShareThirdBean(Parcel in) {
        this.pic = in.readString();
        this.url = in.readString();
        this.title = in.readString();
        this.context = in.readString();
    }

    public ShareThirdBean() {
    }

    public ShareThirdBean(String title, String url, String pic) {
        this.title = title;
        this.url = url;
        this.pic = pic;
    }

    public String getTitle() {
        return this.title == null ? "" : this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return this.context == null ? "" : this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPic() {
        return this.pic == null ? "" : this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return this.url == null ? "" : this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "ShareThirdBean{context='" + this.context + "', title='" + this.title + "', pic='" + this.pic + "', url='" + this.url + "'}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.context);
        dest.writeString(this.pic);
        dest.writeString(this.url);
    }
}
