package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CircleCommentsBean implements Parcelable {
    public static final Parcelable.Creator<CircleListBean> CREATOR = new Parcelable.Creator<CircleListBean>() { // from class: com.vsf2f.f2f.bean.CircleCommentsBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleListBean createFromParcel(Parcel source) {
            return new CircleListBean(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CircleListBean[] newArray(int size) {
            return new CircleListBean[size];
        }
    };
    private List<CircleCommentBean> infos;
    private long lastTime;

    public CircleCommentsBean() {
    }

    public long getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public List<CircleCommentBean> getInfos() {
        if (this.infos == null) {
            this.infos = new ArrayList();
        }
        return this.infos;
    }

    public void setInfos(List<CircleCommentBean> infos) {
        this.infos = infos;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.lastTime);
        dest.writeTypedList(this.infos);
    }

    protected CircleCommentsBean(Parcel in) {
        this.lastTime = in.readLong();
        this.infos = in.createTypedArrayList(CircleCommentBean.CREATOR);
    }
}
