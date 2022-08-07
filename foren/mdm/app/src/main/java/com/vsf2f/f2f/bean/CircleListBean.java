package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes2.dex */
public class CircleListBean implements Parcelable {
    public static final Parcelable.Creator<CircleListBean> CREATOR = new Parcelable.Creator<CircleListBean>() { // from class: com.vsf2f.f2f.bean.CircleListBean.1
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
    private List<CircleReadBean> infos;
    private long lastTime;

    public CircleListBean() {
    }

    public long getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public List<CircleReadBean> getInfos() {
        return this.infos;
    }

    public void setInfos(List<CircleReadBean> infos) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    public CircleListBean(Parcel in) {
        this.lastTime = in.readLong();
        this.infos = in.createTypedArrayList(CircleReadBean.CREATOR);
    }
}
